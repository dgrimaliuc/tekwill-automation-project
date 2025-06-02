package example.api.petstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static example.api.petstore.AdoptionsEndpoint.*;
import static example.api.petstore.PetsEndpoint.createPet;
import static example.api.petstore.PetsEndpoint.getPet;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.Matchers.*;

public class AdoptionsApiTest {


    @Test
    @DisplayName("Get adoption by id")
    public void testGetAdoptionById() {
        String location = "Chisinau";
        String id = "d2cdbcb8-f512-4b43-8f7f-63702a8831f6";
        getAdoption(id, location)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(1000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addAdoptionSchema.json"))
                .body("id", equalTo(id))
                .body("location", equalTo(location));
    }

    @Test
    @DisplayName("Get adoption by wrong id")
    public void testGetAdoptionByWrongId() {
        String location = "Chisinau";
        String id = "aaaaaa";
        getAdoption(id, location)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(1000L))
                .body("error", equalTo("Adoption ID is invalid"));
    }

    @Test
    @DisplayName("Get adoption by unexciting id")
    public void testGetAdoptionByUnexcitingId() {
        String location = "Chisinau";
        String id = "d2cdbcb8-f512-4b43-8f7f-63702a8831ff";
        getAdoption(id, location)
                .then()
                .assertThat()
                .statusCode(404)
                .time(lessThan(1000L))
                .body("error", equalTo("Adoption " + id + " not found in Chisinau"));
    }


    @Test
    @DisplayName("Get adoption by id without location")
    public void testGetAdoptionByIdWithoutLocation() {
        String id = "d2cdbcb8-f512-4b43-8f7f-63702a8831f6";
        getAdoption(id, null)
                .then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo("Missing location"))
                .time(lessThan(1000L));
    }

    @Test
    @DisplayName("Get multiple adoptions test")
    public void getMultipleAdoptionTests() {
        String location = "Chisinau";
        String status = "approved";
        getAdoptions(location, status)
                .then()
                .assertThat()
                .time(lessThan(1000L))
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/getAdoptionsSchema.json"))
                .body("status", everyItem(equalTo(status)));


    }

    @Test
    @DisplayName("Get multiple adoptions with no location test")
    public void getMultipleAdoptionWithNoLocationTests() {
        String location = null;
        String status = "approved";
        getAdoptions(location, status)
                .then()
                .assertThat()
                .time(lessThan(1000L))
                .statusCode(400)
                .body("error", equalTo("Missing location"));

    }

    @Test
    @DisplayName("Create adoption  test")
    public void createAdoptionTest() {
        String location = "Chisinau";

        var petId1 = createPet("John", location).jsonPath().getString("id");
        var petId2 = createPet("John", location).jsonPath().getString("id");

        createAdoption(location, List.of(petId1, petId2))
                .then()
                .assertThat()
                .statusCode(201)
                .time(lessThan(2000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addAdoptionSchema.json"))
                .body("status", equalTo("available"))
                .body("pets", hasItems(petId1, petId2))
                .body("pets", hasSize(2));
    }


    @Test
    @DisplayName("Create adoption empty pet list test")
    public void createAdoptionWithoutPetsTest() {
        String location = "Chisinau";


        createAdoption(location, List.of())
                .then()
                .assertThat()
                .time(lessThan(2000L))
                .statusCode(400)
                .body("error", equalTo("No pets selected"));
    }


    @Test
    @DisplayName("Create adoption without location test")
    public void createAdoptionWithoutLocationTest() {
        createAdoption(null, List.of())
                .then()
                .assertThat()
                .time(lessThan(2000L))
                .statusCode(400)
                .body("error", equalTo("Missing required fields: location"));
    }

    @Test
    @DisplayName("Patch adoption status test")
    public void patchAdoptionStatusTest() {
        String location = "Chisinau";


        var petId = createPet("John", location).jsonPath().getString("id");

        var adoptionId = createAdoption(location, List.of(petId)).jsonPath().get("id").toString();

        getPet(petId, location)
                .then()
                .assertThat()
                .body("status", equalTo("onhold"));

        var newStatus = "approved";
        patchAdoption(adoptionId, location, newStatus)
                .then()
                .assertThat()
                .time(lessThan(2000L))
                .statusCode(200)
                .body("status", equalTo(newStatus));

        getPet(petId, location)
                .then()
                .assertThat()
                .body("status", equalTo("adopted"));

    }

    @Test
    @DisplayName("Delete adoption test")
    public void deleteAdoptionTest() {
        String location = "Chisinau";

        var petId = createPet("John", location).jsonPath().getString("id");

        var adoptionId = createAdoption(location, List.of(petId)).jsonPath().get("id").toString();

        deleteAdoption(adoptionId, location)
                .then()
                .assertThat()
                .time(lessThan(2000L))
                .statusCode(200)
                .body("message", equalTo("Adoption removed: " + adoptionId));
    }

    @Test
    @DisplayName("Delete all adoptions")
    public void deleteAllAdoptionsTest() {
        String location = "Chisinau";
        deleteAllAdoption(location)
                .then()
                .assertThat()
                .time(lessThan(2000L))
                .statusCode(200)
                .body("message", equalTo("Removed all adoptions from " + location));

        getAdoptions(location, "").then().assertThat()
                .statusCode(200)
                .body("size()", equalTo(0));
    }


}
