package alexei_rata.junit.api.petstore;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static alexei_rata.api.petStore.ARPetsEndpoint.*;
import static example.api.petstore.PetstoreBaseRequest.given;
import static example.api.petstore.PetstoreBaseRequest.handleResponse;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.Matchers.*;

public class ARPetApiTest {

    Logger log = Logger.getLogger(ARPetApiTest.class);


    @Test
    @DisplayName("Get pet by id test")
    public void testGetPetById() {
        String location = "Plett";
        String petName = "ARPetDelete01";

        var petId = createPet(location, petName).jsonPath().get("id").toString();
        log.info("Created new pet with ID: " + petId);

        getSinglePet(location, petId)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
        ;
    }


    @Test
    @DisplayName("Get pet by no params, negative test")
    public void testGetPetByIdNegative() {

        getSinglePet("", "")
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Pet ID is invalid"))
        ;
    }


    @Test
    @DisplayName("Get pet by wrong format id, negative test")
    public void testGetPetByWrongIdNegative() {

        getSinglePet("", "AR_Wrong-ID")
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Pet ID is invalid"))
        ;
    }


    @Test
    @DisplayName("Get pet by missing id, negative test")
    public void testGetPetByMissingIdNegative() {
        String location = "Plett";
        String petId = "d2ffa944-94d0-4deb-bfdd-46c07d0b80cc";

        getSinglePet(location, petId)
                .then()
                .assertThat()
                .statusCode(404)
                .time(lessThan(5000L))
                .body("error", equalTo("Pet " + petId + " not found in " + location))       //verify 1st method
                .body("error", equalTo(String.format("Pet %s not found in %s", petId, location)))   //verify 2nd method
        ;
    }


    @Test
    @DisplayName("Get multiple pets test")
    public void testGetMultiPets() {
        String location = "Plett";
        String status1 = "adopted";
        String status2 = "available";

        getPets(location, status2)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/getPetsSchema.json"))
                .body("size()", greaterThan(0))
                .body("location", everyItem(equalTo(location)))
                .body("status", everyItem(equalTo(status2)))
        ;
    }


    @Test
    @DisplayName("Get multiple pets with empty location, negative test")
    public void testGetMultiPetsWithEmptyoLocation() {
        getPets("", "available")
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Missing location"))
        ;
    }


    @Test
    @DisplayName("Create pet, positive test")
    public void testCreatePet() {
        String location = "Plett";
        String petName = "ARPet08";
        String status2 = "available";

        createPet(location, petName)
                .then()
                .assertThat()
                .statusCode(201)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
                .body("location", equalTo(location))
                .body("name", equalTo(petName))
                .body("status", equalTo(status2)) //current step is redundant because the status is tested via schema
        ;
    }


    @Test
    @DisplayName("Create pet when no params in body, negative test")
    public void testCreatePetNegative() {
        createPet(null, null)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Props are required: name,location"))
        ;
    }


    @Test
    @DisplayName("Create pet when body does not exist, negative test")
    public void testCreatePetNegativeNoBody() {
        createPet(null, null, true)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body(equalTo("Unexpected end of JSON input"))
        ;
    }


    @Test
    @DisplayName("Patch pet test")
    public void testPatchPet() {
        String location = "Plett";
        String petName = "ARPetPatch02";
        String newName = petName + "_Updated";
        String newStatus = "onhold";

        var petId = createPet(location, petName).jsonPath().get("id").toString();
        log.info("Created new pet with ID: " + petId);

        patchPet(petId, String.format("""
                {
                    "location":"%s",
                    "status":"%s",
                    "name":"%s"
                }
                """, location, newStatus, newName))
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
                .body("name", equalTo(newName))
                .body("status", equalTo(newStatus))
        ;
    }


    @Test
    @DisplayName("Patch pet invalid id, negative test")
    public void testPatchPetInvalidId() {
        String location = "Plett";
        String petId = "6d9e4bd5-03ab-4e2d-9f57-32d3916588ad";

        patchPet(petId, String.format("""
                {
                    "location":"%s"
                }
                """, location))
                .then()
                .assertThat()
                .statusCode(404)
                .time(lessThan(5000L))
                .body(equalTo("Pet not found"))
        ;

    }


    @Test
    @DisplayName("Patch pet empty id and location, negative test")
    public void testPatchPetEmptyIdAndLocation() {
        String petId = "AR";

        patchPet(petId, """
                {  }
                """)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Props are required: id,location"))
        ;

    }

    @Test
    @DisplayName("Delete single pet")
    public void testDeleteSinglePet() {
        String location = "Plett";
        String petName = "ARPetDelete01";

        var petId = createPet(location, petName).jsonPath().get("id").toString();
        log.info("Created new pet with ID: " + petId);

        deletePet(location, petId)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body("message", equalTo("Pet removed: " + petId))
        ;
    }


    @Test
    @DisplayName("Delete single pet with invalid id test")
    public void testDeleteSinglePetWithInvalidId() {
        String location = "Plett";

        deletePet(location, "deletePet")
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Pet ID is invalid"))
        ;
    }


    @Test
    @DisplayName("Delete multiple pets test")
    public void testDeleteMultiplePet() {
        String location = "Plett";

        deletePets(location)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body("message", equalTo("Removed all pets from " + location))
        ;
    }


    @Test
    @DisplayName("Delete multiple pets with invalid location test")
    public void testDeleteMultiplePetWithInvalidLocation() {
        deletePets(null)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Location is required"))
        ;
    }


    @Test
    @DisplayName("Get adoption by id test. HW01 (24)")
    public void testGetAdoptionByIdHW() {
        var response = given()
                .queryParam("location", "Plett")
                .pathParams("id", "85242850-8b71-4f58-ac09-68f666218c55")
                .get("/adoptions/{id}");
        handleResponse(response);

        response
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addAdoptionSchema.json"));
    }

    @Test
    @DisplayName("Patch a pet status and name test. HW02 (25,26)")
    public void testPatchPetHW() {
        String location = "Plett";
        String petName = "ARHWPetPatch01";
        String newName = petName + "_Updated";
        String newStatus = "adopted";

        var petId = createPet(location, petName).jsonPath().get("id").toString();
        log.info("New added Pet id: " + petId);

        patchPet(petId, String.format("""
                { "location":"%s", "status":"%s", "name":"%s" }
                """, location, newStatus, newName))
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
                .body("name", equalTo(newName))
                .body("status", equalTo(newStatus));
    }

}
