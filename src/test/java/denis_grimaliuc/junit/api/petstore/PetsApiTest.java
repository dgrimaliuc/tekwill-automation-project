package denis_grimaliuc.junit.api.petstore;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static denis_grimaliuc.api.petStore.PetsEndpoint.*;
import static example.api.petstore.PetstoreBaseRequest.given;
import static example.api.petstore.PetstoreBaseRequest.handleResponse;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.Matchers.*;

public class PetsApiTest {

    Logger log = Logger.getLogger(PetsApiTest.class);

    @Test
    @DisplayName("Get pet by id")
    public void testGetPetById() {
        String location = "Chisinau";
        var postResponse = createPet("1111", location);
        String petId = postResponse.jsonPath().get("id");

        getPet(petId, location).then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(1000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"));


    }


    @Test
    @DisplayName("Get pet by empty id test")
    public void getPetByEmptyIdNegativeTest() {
        getPet("", "").then().assertThat()
                .statusCode(400)
                .time(lessThan(1000L))
                .body("error", equalTo("Pet ID is invalid"));
    }


    @Test
    @DisplayName("Get multiple pets with no location empty test")
    public void getPetsWithEmptyLocation() {
        getPets("", "adopted")
                .then()
                .statusCode(400)
                .time(lessThan(1000L))
                .assertThat().body("error", equalTo("Missing location"));
    }

    @Test
    @DisplayName("Get pet by unexciting id test")
    public void getPetByWrongId() {
        String petId = "f84c3504-8a04-4d8b-86fa-1ba1ab9a6e32";
        getPet(petId, "Chisinau").then().assertThat()
                .statusCode(404)
                .time(lessThan(1000L))
                .body("error", equalTo(String.format("Pet %s not found in Chisinau", petId)));
    }


    @Test
    @DisplayName("Get multiple pets test")
    public void testGetPets() {
        String location = "Chisinau";
        String status = "adopted";

        getPets(location, status)
                .then()
                .assertThat()
                .time(lessThan(1000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/getPetsSchema.json"))
                .body("size()", greaterThan(0))
                .body("location", everyItem(equalTo(location)))
                .body("status", everyItem(equalTo(status)));
    }


    @Test
    @DisplayName("Create pet positive test")
    public void createPetTest() {
        String location = "Chisinau";
        String name = "John";

        createPet(name, location)
                .then()
                .assertThat()
                .statusCode(201)
                .time(lessThan(1000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
                .body("name", equalTo(name))
                .body("location", equalTo(location));
    }


    @Test
    @DisplayName("Create pet negative test")
    public void createPetNegativeTest() {

        createPet(null, null)
                .then()
                .assertThat()
                .time(lessThan(1000L))
                .statusCode(400)
                .body("error", equalTo("Props are required: name,location"));

    }

    @Test
    @DisplayName("Create pet negative test")
    public void createPetEmptyBodyTest() {

        createPet(null, null, true)
                .then().assertThat()
                .time(lessThan(1000L))
                .statusCode(400)
                .body(equalTo("Unexpected end of JSON input"));

    }

    @Test
    @DisplayName("Patch pet test")
    public void patchPetTest() {
        String location = "Chisinau";
        String name = "John";
        String newName = "John Doe";
        String newStatus = "adopted";

        var petId = createPet(name, location).jsonPath().get("id").toString();
        log.info("Created Pet id: " + petId);

        patchPet(petId, String.format("""
                { "location": "%s",
                 "status": "%s",
                 "name": "%s"
                 }
                """, location, newStatus, newName)).then().assertThat()
                .statusCode(200)
                .time(lessThan(1000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
                .body("name", equalTo(newName))
                .body("status", equalTo(newStatus));
    }

    @Test
    @DisplayName("Patch pet invalid id test")
    public void patchPetInvalidIdTest() {
        String location = "Chisinau";

        String petId = "510692ec-01d3-4b33-a842-b2eeb979ab6f";
        patchPet(petId, String.format("""
                {"location": "%s"}
                """, location))
                .then()
                .assertThat()
                .body(equalTo("Pet not found"))
                .statusCode(404)
                .time(lessThan(1000L));
    }


    @Test
    @DisplayName("Patch pet empty id test")
    public void patchPetEmptyIdTest() {
        String petId = "a";
        patchPet(petId, """
                { }
                """).then()
                .assertThat()
                .body("error", equalTo("Props are required: id,location"))
                .statusCode(400)
                .time(lessThan(1000L));
    }

    @Test
    @DisplayName("Delete single pet test")
    public void deleteSinglePetTest() {
        var petId = createPet("John", "Chisinau").jsonPath().get("id").toString();
        System.out.println("Pet id: " + petId);

        deletePet(petId, "Chisinau")
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(1000L))
                .body("message", equalTo("Pet removed: " + petId));
    }

    @Test
    @DisplayName("Delete single pet with invalid id test")
    public void deleteSinglePetWithInvalidId() {
        deletePet("aaaa", "Chisinau")
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(1000L))
                .body("error", equalTo("Pet ID is invalid"));
    }

    @Test
    @DisplayName("Delete multiple pets test")
    public void deleteMultiplePetsTest() {
        String location = "Plett";
        var response = given()
                .queryParam("location", location)
                .delete("/pets");


        response.then().assertThat()
                .statusCode(200)
                .time(lessThan(1000L))
                .body("message", equalTo("Removed all pets from " + location));
    }

    @Test
    @DisplayName("Delete multiple pets with empty location test")
    public void deleteMultiplePetsWithEmptyLocationTest() {
        var response = given()
                .delete("/pets");

        handleResponse(response);

        response.then().assertThat()
                .statusCode(400)
                .time(lessThan(1000L))
                .body("error", equalTo("Location is required"));
    }
}
