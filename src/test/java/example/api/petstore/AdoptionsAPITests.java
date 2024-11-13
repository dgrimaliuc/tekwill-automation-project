package example.api.petstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static example.api.petstore.endpoints.AdoptionsEndpoint.*;
import static example.api.petstore.endpoints.PetsEndpoint.addPet;
import static example.api.petstore.endpoints.PetsEndpoint.getPet;
import static example.data.enums.Status.AVAILABLE;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class AdoptionsAPITests {


    @Test
    @DisplayName("Get all adoptions test")
    public void getAdoptionsTest() {
        var location = "Chisinau 2";
        getAdoptions(location, "")
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("location", everyItem(equalTo(location)))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/getAdoptionsSchema.json"))
                .time(lessThan(1000L));
    }

    @Test
    @DisplayName("Get custom status adoptions test")
    public void getAdoptionsCustomStatusTest() {
        var location = "Chisinau";
        getAdoptions(location, "!approved&!rejected&!denied")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("status", everyItem(equalTo("available")))
                .time(lessThan(1000L));
    }

    @Test
    @DisplayName("Get all adoptions without location test")
    public void getAdoptionsWithoutLocationTest() {
        getAdoptions("", "")
                .then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo("Missing location"))
                .time(lessThan(1000L));

    }

    @Test
    @DisplayName("Create adoption test")
    public void createAdoptionTest() {
        var location = "Chisinau 2";
        var pet = addPet(location, "CAT")
                .jsonPath()
                .getString("id");
        List<String> pets = List.of(pet);

        createAdoption(location, pets)
                .then()
                .assertThat()
                .statusCode(201)
                .body("location", equalTo(location))
                .body("pets.size()", equalTo(pets.size()))
                .body("status", equalTo(AVAILABLE.toString()))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addAdoptionSchema.json"))
                .time(lessThan(1000L));
    }

    @Test
    @DisplayName("Create adoption Without Location test")
    public void createAdoptionWithoutLocationTest() {

        createAdoption("", List.of())
                .then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo("Props are required: location"))
                .time(lessThan(1000L));
    }

    @Test
    @DisplayName("Create adoption Without Pets test")
    public void createAdoptionWithoutPetsTest() {

        createAdoption("Paris", List.of())
                .then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo("No pets selected"))
                .time(lessThan(1000L));
    }


    @Test
    @DisplayName("Create adoption with invalid Pet test")
    public void createAdoptionWithInvalidPetTest() {
        var location = "Chisinau 2";
        var pet = "asdsads-wrgwgfwr-qdwq-123";
        System.out.println(pet);

        List<String> pets = List.of(pet);

        createAdoption(location, pets)
                .then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo("Invalid pets: " + pet))
                .time(lessThan(1000L));
    }

    @Test
    @DisplayName("Update adoption status test")
    public void updateAdoptionStatusTest() {
        var location = "Chisinau 2";
        var pet = addPet(location, "CAT")
                .jsonPath()
                .getString("id");
        List<String> pets = List.of(pet);

        String id = createAdoption(location, pets)
                .jsonPath().getString("id");

        var newStatus = "approved";

        updateAdoption(id, newStatus, location)
                .then()
                .assertThat()
                .statusCode(200)
                .body("status", equalTo(newStatus))
                .body("location", equalTo(location))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addAdoptionSchema.json"))
                .time(lessThan(1000L));
    }

    @Test
    @DisplayName("Delete adoptions test")
    public void deleteAdoptionTest() {
        var location = "Chisinau 2";

        var petId = addPet(location, "CAT")
                .jsonPath()
                .getString("id");

        createAdoption(location, List.of(petId));

        deleteAdoptions(location)
                .then()
                .assertThat()
                .statusCode(200)
                .body("message", equalTo("Removed"))
                .time(lessThan(1400L));

        getAdoptions(location, "")
                .then()
                .assertThat()
                .body("size()", equalTo(0));

        getPet(petId, location)
                .then()
                .assertThat()
                .body("status", equalTo(AVAILABLE.toString()));
    }

    @Test
    @DisplayName("Delete adoptions without location test")
    public void deleteAdoptionWithoutLocationTest() {
        deleteAdoptions("")
                .then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo("Location is required"))
                .time(lessThan(1000L));
    }

    @Test
    @DisplayName("Delete adoptions by id test")
    public void deleteAdoptionByIDTest() {
        deleteAdoption("a3b63f24-ce7d-4f4a-9d05-be9d4d04d50b", "Plett")
                .then()
                .assertThat()
                .statusCode(200)
//                .body("error", equalTo("Location is required"))
                .time(lessThan(1000L));
    }

    @Test
    @DisplayName("Get adoption by invalid format id test")
    public void getAdoptionByInvalidFormatId() {
        getAdoption("123", "Chisinau")
                .then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo("Adoption ID is invalid"))
                .time(lessThan(1000L));
    }

    @Test
    @DisplayName("Get adoption by invalid  id test")
    public void getAdoptionByInvalidId() {
        var id = "b0e52088-0767-4591-95b1-7cc434d610c1";
        var location = "Chisinau";

        getAdoption(id, location)
                .then()
                .assertThat()
                .statusCode(404)
                .body("error", equalTo("Adoption %s not found in %s".formatted("b0e52088-0767-4591-95b1-7cc434d610c1", location)))
                .time(lessThan(1000L));
    }
}
