package alexei_rata.junit.api.petstore;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static example.api.petstore.PetstoreBaseRequest.given;
import static example.api.petstore.PetstoreBaseRequest.handleResponse;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.Matchers.*;

public class ARPetApiTest {

    Logger log = Logger.getLogger(ARPetApiTest.class);


    @Test
    @DisplayName("Get pet by id test")
    public void testGetPetById() {
        var response = given()
                .queryParam("location", "Plett")
                .pathParams("id", "c373fa4d-c5ae-4cba-8390-c8a3578976d2")
                .get("/pets/{id}");
        handleResponse(response);

        response
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
        ;
    }


    @Test
    @DisplayName("Get pet by id with no params, negative test")
    public void testGetPetByIdNegative() {
        var response = given()
                .get("/pets");
        handleResponse(response);

        response
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
        var response = given()
                .get("/pets/AR_Wrong-ID");
        handleResponse(response);

        response
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
        String loaction = "Plett";
        String petId = "d2ffa944-94d0-4deb-bfdd-46c07d0b80cc";
        var response = given()
                .queryParam("location", loaction)
                .pathParams("id", petId)
                .get("/pets/{id}");
        handleResponse(response);

        response
                .then()
                .assertThat()
                .statusCode(404)
                .time(lessThan(5000L))
                .body("error", equalTo("Pet " + petId + " not found in " + loaction))       //verify 1st method
                .body("error", equalTo(String.format("Pet %s not found in %s", petId, loaction)))   //verify 2nd method
        ;
    }


    @Test
    @DisplayName("Get multiple pets test")
    public void testGetMultiPets() {
        String location = "Plett";
        String status1 = "adopted";
        String status2 = "available";

        var response = given()
                .queryParam("location", location)
                .queryParam("status", status2)
                .get("/pets");
        handleResponse(response);

        response
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

        var response = given()
                .queryParam("location", "")
                .get("/pets");
        handleResponse(response);

        response.then().assertThat()
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

        var response = given()
                .body(String.format("""
                        {
                            "name":"%s",
                            "location":"%s"
                        }
                        """, petName, location))
                .post("/pets");
        handleResponse(response);

        response
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

        var response = given()
                .body("""
                        { }
                        """)
                .post("/pets");
        handleResponse(response);

        response
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
        var response1 = given()
                .post("/pets");
        handleResponse(response1);

        response1
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

        var postResponse = given()
                .body(String.format("""
                        {
                            "name":"%s",
                            "location":"%s"
                        }
                        """, petName, location))
                .post("/pets");
        handleResponse(postResponse);
        String petId = postResponse.jsonPath().get("id");
        log.info("Pet id: " + petId);

        var patchResponse = given()
                .pathParam("id", petId)
                .body(String.format("""
                        {
                            "location":"%s",
                            "status":"%s",
                            "name":"%s"
                        }
                        """, location, newStatus, newName))
                .patch("/pets/{id}");

        handleResponse(patchResponse);
        patchResponse
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
    @DisplayName("Patch a pet status and name test. HW02 (25)")
    public void testPatchPetHW() {
        String location = "Plett";
        String petName = "ARHWPetPatch01";
        String newName = petName + "_Updated";
        String newStatus = "adopted";

        var postResponse = given()
                .body(String.format("""
                        { "name":"%s", "location":"%s" }
                        """, petName, location))
                .post("/pets");
        handleResponse(postResponse);

        String petId = postResponse.jsonPath().get("id");
        log.info("New added Pet id: " + petId);

        var patchResponse = given()
                .pathParam("id", petId)
                .body(String.format("""
                        { "location":"%s", "status":"%s", "name":"%s" }
                        """, location, newStatus, newName))
                .patch("/pets/{id}");

        handleResponse(patchResponse);
        patchResponse.then().assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
                .body("name", equalTo(newName))
                .body("status", equalTo(newStatus));
    }

}
