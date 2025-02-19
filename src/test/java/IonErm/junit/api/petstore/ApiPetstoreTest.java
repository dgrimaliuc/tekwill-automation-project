package IonErm.junit.api.petstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static example.api.petstore.PetstoreBaseRequest.given;
import static example.api.petstore.PetstoreBaseRequest.handleResponse;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.Matchers.*;

public class ApiPetstoreTest {

    @Test
    @DisplayName("Get pet by id")
    public void getPetById() {
        var response = given()
                .queryParam("location", "Chisinau")
                .pathParams("id", "3a63fc13-dc03-4c01-be52-abc1b68e466f")
                .get("/pets/{id}");
        handleResponse(response);
        response
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src\\main\\resources\\schemes\\addPetSchema.json"));
    }

    @Test
    @DisplayName("Get pet by empty id  test")
    public void getPetByEmptyIdNegativeTest() {
        var response = given()
                .get("/pets");
        handleResponse(response);
        response
                .then()
                .statusCode(400)
                .assertThat()
                .time(lessThan(5000L))
                .body("error", equalTo("Pet ID is invalid"));
    }

    @Test
    @DisplayName("Get pet by wrong id  test")
    public void getPetByWrongIdNegativeTest() {
        var response = given()
                .queryParam("location", "Chisinau")
                .get("/pets/3a63fc13-dc03-4c01-be52-abc1b68e466w");
        handleResponse(response);
        response
                .then()
                .statusCode(400)
                .assertThat()
                .time(lessThan(5000L))
                .body("error", equalTo("Pet ID is invalid"));
    }

    @Test
    @DisplayName("Get pet by unexciting id  test")
    public void getPetByUnexcitingIdTest() {
        String pet_id = "53c6fa1b-876a-4690-b76f-abd3f022de6b";
        var response = given()
                .queryParam("location", "Chisinau")
                .pathParams("id", pet_id)
                .get("/pets/{id}");
        handleResponse(response);
        response
                .then()
                .statusCode(404)
                .assertThat()
                .time(lessThan(5000L))
                .body("error", equalTo("Pet 53c6fa1b-876a-4690-b76f-abd3f022de6b not found in Chisinau"));
    }

    @Test
    @DisplayName("Get multiple pets test")
    public void getMultiplePetsTest() {
        String location = "Chisinau";
        String statusAvailable = "available";
        String statusAdopted = "adopted";
        var response = given()
                .queryParam("location", location)
                .queryParam("status", statusAdopted)
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
                .body("status", everyItem(equalTo(statusAdopted)));
    }


    @Test
    @DisplayName("Get all pets test")
    public void getAllPetsTest() {
        String location = "Chisinau";
        String status = "available";
        var response = given()
                .queryParam("location", location)
                .queryParam("status", status)
                .get("/pets");
        handleResponse(response);
        response
                .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/getPetsSchema.json"))
                .body("size()", greaterThan(0))
                .body("location", everyItem(equalTo(location)))
                .body("status", everyItem(equalTo(status)));
    }

    @Test
    @DisplayName("Get all pets negative test")
    public void getAllPetsNegativeTest() {
        var response = given()
                .queryParam("q", "Q")
                .get("/pets");
        handleResponse(response);
        response
                .then()
                .statusCode(400)
                .time(lessThan(3000L))
                .body("error", equalTo("Missing location"));

    }

    @Test
    @DisplayName("Add pet positive test")
    public void addPetTest() {
        String location = "Chisinau";
        String name = "QQQQ2";
        var response = given()
                .body(String.format("""
                        {
                          "location":"%s",
                          "name":"%s"
                        }
                        """, location, name))
                .post("/pets");
        handleResponse(response);
        response
                .then()
                .assertThat()
                .statusCode(201)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
                .body("name", equalTo(name))
                .body("location", equalTo(location));
    }

    @Test
    @DisplayName("Add pet negative test")
    public void addPetNegativeTest() {
        var response = given()
                .body("""
                        {}
                        """)
                .post("/pets");
        handleResponse(response);
        response
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Props are required: name,location"));
    }

    @Test
    @DisplayName("Empty body test")
    public void emptyBodyTest() {
        var response = given()
                .post("/pets");
        handleResponse(response);
        response
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body(equalTo("Unexpected end of JSON input"));
    }

    @Test
    @DisplayName("Patch a single pet test")
    public void patchAPetTest() {
        String location = "Chisinau";
        String name = "PoP";
        String newName = "MNBV";
        String newStatus = "adopted";
        var postResponse = given()
                .body(String.format("""
                        {
                          "location":"%s",
                          "name":"%s"
                        }
                        """, location, name))
                .post("/pets");
        String pet_id = postResponse.jsonPath().get("id");

        var patchResponse = given()
                .pathParams("id", pet_id)
                .body(String.format(
                        """
                                {
                                   "location":"%s",
                                   "status":"%s",
                                   "name":"%s"
                                }
                                """, location, newStatus, newName))
                .patch("/pets{id}");
        handleResponse(patchResponse);
        patchResponse
                .then()
                .assertThat()
                .time(lessThan(5000L))
                .statusCode(200)
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
                .body("location", equalTo(location))
                .body("name", equalTo(newName))
                .body("status", equalTo(newStatus));
    }

    @Test
    @DisplayName("Patch single pet test")
    public void patchSinglePetTestTest() {
        String location = "Chisinau";
        String name = "UOUO";
        String newName = "WQWQWQW";
        String newStatus = "available";
        var postResponse = given()
                .body(String.format(
                        """
                                {
                                    "name":"%s",
                                    "location":"%s"
                                }
                                """, name, location
                ))
                .post("/pets");
        String pet_id = postResponse.jsonPath().get("id");
        var patchResponse = given()
                .pathParams("id", pet_id)
                .body(String.format(
                        """
                                {
                                    "name":"%s",
                                    "location":"%s",
                                    "status":"%s"
                                }
                                """, newName, location, newStatus
                ))
                .patch("/pets{id}");
        String newPet_id = patchResponse.jsonPath().get("id");
        handleResponse(patchResponse);
        patchResponse
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(4000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
                .body("name", equalTo(newName))
                .body("id", equalTo(newPet_id))
                .body("location", equalTo(location))
                .body("status", equalTo(newStatus));
    }

    @Test
    @DisplayName("Delete single pet")
    public void deleteSinglePet() {
        String pet_id = "54a144a4-1bbf-453a-8b67-d2182b36371c";
        String location = "Chisinau";
        var response = given()
                .queryParam("location", location)
                .pathParam("id", pet_id)
                .delete("/pets{id}");
        handleResponse(response);
        response
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("message", equalTo("Pet removed: " + pet_id));

    }
}
