package Lilia_Rosca.LR_JUnit.api.petStore;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Lilia_Rosca.api.petStore.LR_PetsEndpoint.*;
import static example.api.petstore.PetstoreBaseRequest.given;
import static example.api.petstore.PetstoreBaseRequest.handleResponse;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.*;

public class LR_ApiPetsTests {
    Logger log = Logger.getLogger(LR_ApiPetsTests.class);

// 14.02
    @Test
    @DisplayName("Get pet by id test")
    public void getPetByIdTest() {
        String location = "Chisinau";
        String name = "Joan";

        var postResponse = createPet(name, location);
        String petId = postResponse.jsonPath().get("id");

        getPet(petId, location)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(1000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"));
    }

// HW Create a api test to get adoptions, validate status code and time < 1000L
    @Test
    @DisplayName("Get adoption test")
    public void getAdoptionTest() {
        var response = given()
                .queryParam("location", "Plett")
                .pathParam("adoption_id", "0f736f0b-f20e-4f15-b2d6-ff7e923d027f")
                .get("/adoptions/{adoption_id}");
        handleResponse(response);
        response
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(1000L));
    }
// 17.02
    @Test
    @DisplayName("Get pet by empty id test")
    public void getPetByEmptyIdTest() {
        getPet("", "")
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(1000L))
                .body("error", equalTo("Pet ID is invalid"));
    }

    @Test
    @DisplayName("Get pet by not existing id test")
    public void getPetByNotExistingIdTest() {
        String petId = "59079dd7-a884-4ac8-a0f4-3b47f18b1a1e"; // not valid pet id
        getPet(petId, "Plett")
                .then().assertThat()
                .time(lessThan(1500L))
                .statusCode(404)
                .body("error", equalTo(String.format("Pet %s not found in Plett", petId)));
    }

    @Test
    @DisplayName("Get multiple pets test")
    public void getMultiplePetsTest() {
        String location = "Plett";
        String status = "available";
        getPets(location, status)
                .then().assertThat()
                .time(lessThan(1500L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/getPetsSchema.json"))
                .body("size()", greaterThan(0))
                .body("location", everyItem(equalTo(location)))
                .body("status", everyItem(equalTo(status)));
    }

    @Test
    @DisplayName("Get multiple pets with empty location test")
    public void getMultiplePetsWithEmptyLocationTest() {
        getPets("", "")
                .then()
                .time(lessThan(1000L))
                .statusCode(400)
                .assertThat().body("error", equalTo("Missing location"));
    }

    @Test
    @DisplayName("Create pet positive test")
    public void createPetTest() {
        String location = "Chisinau";
        String name = "Fluffy";
        createPet(name, location)
                .then().assertThat()
                .statusCode(201)
                .time(lessThan(1500L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
                .body("name", equalTo(name))
                .body("location", equalTo(location));
    }

    @Test
    @DisplayName("Create pet negative test")
    public void createPetNegativeTest() {
        createPet(null, null)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(1500L))
                .body("error", equalTo("Missing required fields: name,location"));
    }

    @Test
    @DisplayName("Create empty body test")
    public void createEmptyBodyTest() {
        createPet(null, null, true)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(1000L))
                .body(equalTo("Unexpected end of JSON input"));
    }

    @Test // HW 17.02
    @DisplayName("Patch pet test")
    public void patchPetTest() {
        String location = "Chisinau";
        String name = "Joan";
        String newName = "Jane";
        String newStatus = "adopted";

        var petId = createPet(name, location).jsonPath().get("id").toString();
        log.info("Created pet id: " + petId);

        patchPet(petId, String.format("""
                        {"location":"%s", "status":"%s", "name":"%s"}
                        """, location, newStatus, newName))
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(1000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
                .body("name", equalTo(newName))
                .body("status", equalTo(newStatus));
    }
// 19.02
    @Test
    @DisplayName("Patch pet invalid id test")
    public void patchPetInvalidIdTest() {
        String location = "Chisinau";
        String petId = "acb39ec1-3141-4745-b43c-8b276f14d4fc";

        patchPet(petId, String.format("""
                                      {"location":"%s"}
                                      """, location))
                .then().assertThat()
                .statusCode(404)
                .time(lessThan(1500L))
                .body(equalTo("Pet not found"));
    }

    @Test
    @DisplayName("Patch pet empty id test")
    public void patchPetEmptyIdTest() {
        String petId = "a";
        patchPet(petId, String.format("""
                                      {}
                                      """))
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(1000L))
                .body("error", equalTo("Missing required fields: id,location"));
    }

    @Test
    @DisplayName("Delete single pet test")
    public void deleteSinglePetTest() {
        String location = "Chisinau";
        String name = "Joan";
        var petId = createPet(name, location).jsonPath().get("id").toString();

        deletePet(petId, location)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(1000L))
                .body("message", equalTo("Pet removed: " + petId));
    }

    @Test
    @DisplayName("Delete single pet with invalid test")
    public void deleteSinglePetWithInvalidTest() {
        deletePet("aaaa", "Chisinau")
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(1000L))
                .body("error", equalTo("Pet ID is invalid"));
    }

    @Test // de sinstatator
    @DisplayName("Delete miultiple pets test")
    public void deleteMUltiplePetsTest() {
        String location = "Plett";
        deletePets(location)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(2500L))
                .body("message", equalTo("Removed all pets from " + location));

        getPets(location, null)
                .then().assertThat()
                .body("size()", equalTo(0));
    }

    @Test
    @DisplayName("Delete miultiple pets with empty location test")
    public void deleteMUltiplePetsEmptyLocationTest2() {
        deletePets(null)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(1000L))
                .body("error", equalTo("Location is required"));
    }
}