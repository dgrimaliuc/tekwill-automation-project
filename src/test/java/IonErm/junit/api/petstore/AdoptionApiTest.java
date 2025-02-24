package IonErm.junit.api.petstore;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static IonErm.api.petstore.AdoptionEndpoint.*;
import static IonErm.api.petstore.PetEndpoint.createPet;
import static IonErm.api.petstore.PetEndpoint.getPet;
import static IonErm.api.petstore.PetEndpoint.getPets;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.Matchers.*;

public class AdoptionApiTest {

    public static String name = RandomStringUtils.randomAlphanumeric(17).toUpperCase();
    public static String location = "Chisinau";
    public static String status = "available";
    Logger log = Logger.getLogger(AdoptionApiTest.class);

    @Test
    @DisplayName("Get adoption by id test")
    public void getAdoptionByIdTest() {
        String adoptionId = "b7a71341-a533-4021-a49f-c814146b6437";
        getAdoption(adoptionId, location)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addAdoptionSchema.json"))
                .body("location", equalTo(location))
                .body("id", equalTo(adoptionId));
    }

    @Test
    @DisplayName("Get adoption by wrong id test")
    public void getAdoptionByWrongIdTest() {
        String adoptionId = "aksjbfweklj";
        getAdoption(adoptionId, location)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Adoption ID is invalid"));
    }

    @Test
    @DisplayName("Get adoption by unexciting id test")
    public void getAdoptionByUnexcitingIdTest() {
        String adoptionId = "ddc6bcf4-702d-4d47-b81f-18a1c6cb38cc";
        getAdoption(adoptionId, location)
                .then()
                .assertThat()
                .statusCode(404)
                .time(lessThan(5000L))
                .body("error", equalTo("Adoption " + adoptionId + " not found in Chisinau"));
    }

    @Test
    @DisplayName("Get adoption by id with empty location test")
    public void getAdoptionWithEmptyLocation() {
        String adoptionId = "b7a71341-a533-4021-a49f-c814146b6437";
        getAdoption(adoptionId, null)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Missing location"));
    }

    @Test
    @DisplayName("Get multiple adoption test")
    public void getMultipleAdoptionTest() {
        getAdoptions(status, location)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/getAdoptionsSchema.json"))
                .body("location", everyItem(equalTo(location)))
                .body("status", everyItem(equalTo(status)));
    }

    @Test
    @DisplayName("Get multiple adoption with empty location test")
    public void getMultipleAdoptionEmptyLocationTest() {
        getAdoptions(status, null)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Missing location"));
    }

    @Test
    @DisplayName("Create adoption test")
    public void createAdoptionTest() {
        var petId1 = createPet(name, location).jsonPath().getString("id");
        var petId2 = createPet(name, location).jsonPath().getString("id");
        createAdoptions(location, List.of(petId1, petId2))
                .then().assertThat()
                .time(lessThan(4000L))
                .statusCode(201)
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addAdoptionSchema.json"))
                .body("status", equalTo(status))
                .body("pets", hasItems(petId1, petId2))
                .body("pets", hasSize(2));
    }

    @Test
    @DisplayName("Create adoption empty list test")
    public void createAdoptionEmptyListTest() {
        createAdoptions(location, List.of())
                .then().assertThat()
                .time(lessThan(4000L))
                .statusCode(400)
                .body("error", equalTo("No pets selected"));
    }

    @Test
    @DisplayName("Create adoption empty location test")
    public void createAdoptionEmptyLocationTest() {
        var petId = createPet(name, location).jsonPath().getString("id");
        createAdoptions(null, List.of(petId))
                .then().assertThat()
                .time(lessThan(4000L))
                .statusCode(400)
                .body("error", equalTo("Missing required fields: location"));
    }

    @Test
    @DisplayName("Patch adoption test")
    public void patchAdoptionTest() {
        String status = "approved";
        var petId = createPet(name, location).jsonPath().getString("id");
        var adoptionId = createAdoptions(location, List.of(petId)).jsonPath().getString("id").toString();
        getPet(petId, location)
                .then()
                .assertThat()
                .body("status", equalTo("onhold"));
        patchAdoptions(adoptionId, location, status)
                .then().assertThat()
                .time(lessThan(4000L))
                .statusCode(200)
                .body("status", equalTo(status));
        getPet(petId, location)
                .then()
                .assertThat()
                .body("status", equalTo("adopted"));
    }

    @Test
    @DisplayName("Delete adoption test")
    public void deleteAdoptionTest() {
        var petId = createPet(name, location).jsonPath().getString("id");
        var adoptionId = createAdoptions(location, List.of(petId)).jsonPath().getString("id").toString();
        deleteAdoption(adoptionId, location)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("message", equalTo("Adoption removed: " + adoptionId));
    }

    @Test
    @DisplayName("Delete all adoptions test")
    public void deleteAllAdoptionsTest() {
        deleteAdoptions(location)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("message", equalTo("Removed all adoptions from " + location));
        getAdoptions("", location)
                .then().assertThat().statusCode(200)
                .body("size()", equalTo(0));
        getPets(status, location)
                .then().assertThat().statusCode(200)
                .body("size()", greaterThan(0))
                .body("status", everyItem(equalTo(status)));
    }
}
