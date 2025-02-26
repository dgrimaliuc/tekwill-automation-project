package Lilia_Rosca.LR_JUnit.api.petStore;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static Lilia_Rosca.api.petStore.LR_AdoptionsEndPoint.*;
import static Lilia_Rosca.api.petStore.LR_PetsEndpoint.*;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.Matchers.*;

public class LR_ApiAdoptionsTest {
    Logger log = Logger.getLogger(LR_ApiPetsTests.class);
// 21.02

    @Test // to modify with using method
    @DisplayName("Get adoption by id test")
    public void getAdoptionByIdTest() {
        String id = "fd2c91cb-5f05-46f7-8c8d-0d6ba92a3f8b";
        String location = "Plett";
        getAdoption(id, location)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(1000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addAdoptionSchema.json"))
                .body("id", equalTo(id))
                .body("location", equalTo(location));
    }

    @Test
    @DisplayName("Get adoption by wrong id test")
    public void getAdoptionByWrongIdTest() {
        String id = "111111";
        String location = "Plett";
        getAdoption(id, location)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(1000L))
                .body("error", equalTo("Adoption ID is invalid"));
    }

    @Test
    @DisplayName("Get adoption by inexisting test")
    public void getAdoptionByInexistingIdTest() {
        String id = "fd2c91cb-5f05-46f7-8c8d-0d6ba92a3f8a";
        String location = "Plett";
        getAdoption(id, location)
                .then().assertThat()
                .statusCode(404)
                .time(lessThan(2000L))
                .body("error", equalTo("Adoption " + id + " not found in Plett"));
    }

    @Test
    @DisplayName("Get adoption by id without location test")
    public void getAdoptionByIdWithoutLocationTest() {
        String id = "fd2c91cb-5f05-46f7-8c8d-0d6ba92a3f8b";
        getAdoption(id, null)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(1000L))
                .body("error", equalTo("Missing location"));
    }

    @Test
    @DisplayName("Get multiple adoptions test")
    public void getMultipleAdoptionsTest() {
        String location = "Chisinau";
        String status = "approved";
        getAdoptions(location, status)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(1000L))
                .body("size()", greaterThan(0))
                .body("status", everyItem(equalTo(status)))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/getAdoptionsSchema.json"));
    }

    @Test // ???
    @DisplayName("Get multiple adoptions with no location test")
    public void getMultipleAdoptionsWithNoLocationTest() {
        String location = null;
        String status = "approved";
        getAdoptions(location, status)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(1000L))
                .body("error", equalTo("Adoption ID is invalid"));  // ???? "Missing location"
    }

    @Test
    @DisplayName("Create adoption test")
    public void createAdoptionTest() {
        String location = "Chisinau";
        var petId1 = createPet("Jane", location).jsonPath().getString("id");
        var petId2 = createPet("Jane", location).jsonPath().getString("id");

        createAdoption(location, List.of(petId1, petId2))
                .then().assertThat()
                .time(lessThan(3000L))
                .statusCode(201)
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
                .then().assertThat()
                .time(lessThan(1000L))
                .statusCode(400)
                .body("error", equalTo("No pets selected"));
    }

    @Test
    @DisplayName("Create adoption without location test")
    public void createAdoptionWithoutLocationTest() {
        createAdoption(null, List.of())
                .then().assertThat()
                .time(lessThan(1000L))
                .statusCode(400)
                .body("error", equalTo("Missing required fields: location"));
    }

    @Test
    @DisplayName("Patch adoption status test")
    public void patchAdoptionStatusTest() {
        String location = "Chisinau";
        var petId = createPet("Joan",location).jsonPath().getString("id");
        var adoptionId = createAdoption(location, List.of(petId)).jsonPath().get("id").toString();

        getPet(petId, location)
                .then().assertThat()
                .body("status", equalTo("onhold"));

        var newStatus = "approved";
        patchAdoption(adoptionId, location, newStatus)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(2500L))
                .body("status", equalTo(newStatus));

        getPet(petId, location)
                .then().assertThat()
                .body("status", equalTo("adopted"));

    }
    @Test
    @DisplayName("Delete adoption test")
    public void deleteAdoptionTest() {
        String location = "Chisinau";
        var petId = createPet("Julie",location).jsonPath().getString("id");
        var adoptionId = createAdoption(location, List.of(petId)).jsonPath().get("id").toString();

        deleteAdoption(adoptionId, location)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(1000L))
                .body("message", equalTo("Adoption removed: " + adoptionId));
    }

    @Test
    @DisplayName("Delete all adoptions test")
    public void deleteAllAdoptionsTest() {
        String location = "Chisinau";
        deleteAllAdoptions(location)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body("message", equalTo("Removed all adoptions from " + location));

        getAdoptions(location, "")
                .then().assertThat()
                .statusCode(200)
                .body("size()", equalTo(0));
    }
}