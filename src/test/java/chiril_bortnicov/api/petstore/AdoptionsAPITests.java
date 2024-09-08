package chiril_bortnicov.api.petstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chiril_bortnicov.api.petstore.endpoints.AdoptionsEndpoint.*;
import static chiril_bortnicov.api.petstore.endpoints.PetsEndpoint.addPet;
import static chiril_bortnicov.api.petstore.endpoints.PetsEndpoint.getPets;
import static chiril_bortnicov.data.enums.Status.AVAILABLE;
import static chiril_bortnicov.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;

public class AdoptionsAPITests {

    @Test
    @DisplayName("Get all adoptions test")
    public void getAllAdoptionsTest() {
        var location = "Moscow";
        getAdoptions(location, "")
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("location", everyItem(equalTo(location)))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/getAdoptionsSchema.json"))
                .time(lessThan(1500L));
    }

    @Test
    @DisplayName("Get custom status adoptions test")
    public void getCustomStatusAdoptionsTest() {
        var location = "Moscow";
        getAdoptions(location, "!approved&!rejected&!denied")
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("location", everyItem(equalTo(location)))
                .time(lessThan(1500L));
    }

    @Test
    @DisplayName("Get all adoptions without location test")
    public void getAllAdoptionsWithoutLocationTest() {
        getAdoptions("", "")
                .then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo("Missing location"))
                .time(lessThan(1500L));
    }

    @Test
    @DisplayName("Create adoption test")
    public void createAdoptionTest() {
        var location = "Moscow";
        var pet = addPet(location, "Tom")
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
                .time(lessThan(2500L));
    }

    @Test
    @DisplayName("Create adoption without location test")
    public void createAdoptionWithoutLocationTest() {
        createAdoption("", List.of())
                .then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo("Props are required: location"))
                .time(lessThan(1500L));
    }

    @Test
    @DisplayName("Create adoption without pets test")
    public void createAdoptionWithoutPetsTest() {
        createAdoption("Moscow", List.of())
                .then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo("No pets selected"))
                .time(lessThan(1500L));
    }

    @Test
    @DisplayName("Create adoption with invalid pet test")
    public void createAdoptionWithInvalidPetTest() {
        var location = "Moscow";
        var pet = "invalid";
        List<String> pets = List.of(pet);
        createAdoption(location, pets)
                .then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo("Invalid pets: " + pet))
                .time(lessThan(1500L));
    }

    @Test
    @DisplayName("Update adoption status test")
    public void updateAdoptionStatusTest() {
        var location = "Moscow";
        var pet = addPet(location, "Tom")
                .jsonPath()
                .getString("id");

        List<String> pets = List.of(pet);

        var id = createAdoption(location, pets).jsonPath().getString("id");
        var newStatus = "approved";
        updateAdoption(id, newStatus, location)
                .then()
                .assertThat()
                .statusCode(200)
                .body("status", equalTo(newStatus))
                .body("location", equalTo(location))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addAdoptionSchema.json"))
                .time(lessThan(2500L));
    }

    @Test
    @DisplayName("Delete adoptions test")
    public void deleteAdoptionsTest() {
        var location = "Moscow 2";
        var name = "CAT";
        var pet = addPet(location, name)
                .jsonPath()
                .getString("id");

        List<String> pets = List.of(pet);

        var createAdopt = createAdoption(location, pets);
        createAdopt
                .then()
                .assertThat()
                .statusCode(201);
        var delAdopt = deleteAdoption(location);
        delAdopt
                .then()
                .assertThat()
                .statusCode(200)
                .body("message", equalTo("Removed"))
                .time(lessThan(1500L));
        var availablePet = getPets(location);
        availablePet
                .then()
                .assertThat()
                .statusCode(200)
                .body("location", everyItem(equalTo(location)))
                .body("status", everyItem(equalTo(AVAILABLE.toString())))
                .time(lessThan(1500L));
    }

    @Test
    @DisplayName("Delete adoptions without location test")
    public void deleteAdoptionsWithoutLocationTest() {
        var location = "Moscow 3";
        var name = "CAT";
        var pet = addPet(location, name)
                .jsonPath()
                .getString("id");

        List<String> pets = List.of(pet);

        var createAdopt = createAdoption(location, pets);
        createAdopt
                .then()
                .assertThat()
                .statusCode(201);
        var delAdopt = deleteAdoption("");
        delAdopt
                .then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo("Location is required"))
                .time(lessThan(1500L));
    }
}
