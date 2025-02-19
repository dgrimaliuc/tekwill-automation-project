package Lilia_Rosca.LR_JUnit.api.petStore;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        var response =  given()
                               .queryParam("location", "Plett")
                               .pathParams("id", "59079dd7-a884-4ac8-a0f4-3b47f18b1a2e")
                               .get("/pets/{id}");
        handleResponse(response);
        response.then().assertThat()
                .statusCode(200)
                .time(lessThan(1500L))
                .body("id", not(emptyString())) // sau
                .body("id", equalTo("59079dd7-a884-4ac8-a0f4-3b47f18b1a2e")) // sau
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
                .time(lessThan(1000L)); // 1190, passed
    }
// 17.02
    @Test
    @DisplayName("Get pet by empty id test")
    public void getPetByEmptyIdTest() {
        var response = given()
                .get("/pets");
        handleResponse(response);
        response.then().assertThat()
                .statusCode(400)
                .time(lessThan(1000L))
                .body("error", equalTo("Pet ID is invalid"));
    }

    @Test
    @DisplayName("Get pet by not existing id test")
    public void getPetByNotExistingIdTest() {
        String petId = "59079dd7-a884-4ac8-a0f4-3b47f18b1a1e"; // not valid pet id
        var response = given()
                .queryParam("location", "Plett")
                .pathParams("id",petId)
                .get("/pets/{id}");
        handleResponse(response);
        response.then().assertThat()
                .time(lessThan(1500L))
                .statusCode(404)
                .body("error", equalTo(String.format("Pet %s not found in Plett", petId)));
    }

    @Test
    @DisplayName("Get multiple pets test")
    public void getMultiplePetsTest() {
        String location = "Chisinau";
        String status = "adopted";
        var response = given()
                .queryParam("location", location)
                .queryParam("status", status)
                .get("/pets");
        handleResponse(response);
        response.then().assertThat()
                .time(lessThan(1500L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/getPetsSchema.json"))
                .body("size()", greaterThan(0))
                .body("location", everyItem(equalTo(location)))
                .body("status", everyItem(equalTo(status)));
    }

    @Test
    @DisplayName("Get multiple pets with empty location test")
    public void getMultiplePetsWithEmptyLocationTest() {
        var response = given()
                .queryParam("location", "") // merge si asa ("q", "q")
                .get("/pets");
        handleResponse(response);
        response.then()
                .time(lessThan(1000L))
                .statusCode(400)
                .assertThat().body("error", equalTo("Missing location"));
    }

    @Test
    @DisplayName("Create pet positive test")
    public void createPetTest() {
        String location = "Chisinau";
        String name = "Fluffy";
        var response = given()
                .body(String.format("""
                                    {"name":"%s", "location":"%s"}
                                    """, name, location))
                .post("/pets");
        handleResponse(response);
        response.then().assertThat()
                .statusCode(201)
                .time(lessThan(1500L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
                .body("name", equalTo(name))
                .body("location", equalTo(location));
    }

    @Test
    @DisplayName("Create pet negative test")
    public void createPetNegativeTest() {
        var response = given()
                .body("""
                        {}
                        """)
                .post("/pets");
        handleResponse(response);
        response.then().assertThat()
                .statusCode(400)
                .time(lessThan(1500L))
                .body("error", equalTo("Props are required: name,location"));
    }

    @Test
    @DisplayName("Create empty body test")
    public void createEmptyBodyTest() {
        var response = given()
                .post("/pets");
        handleResponse(response);
        response.then().assertThat()
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

        var postResponse = given() // create new pet
                .body(String.format("""
                        {"name":"%s", "location":"%s"}
                        """, name, location))
                .post("/pets");

        String petId = postResponse.jsonPath().get("id");
        log.info("Created pet id: " + petId);

        var patchResponse = given()
                .pathParam("id", petId)
                .body(String.format("""
                        {"location":"%s", "status":"%s", "name":"%s"}
                        """, location, newStatus, newName))
                .patch("/pets/{id}");
        handleResponse(patchResponse);
        patchResponse.then().assertThat()
                .statusCode(200)
                .time(lessThan(1000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
                .body("name", equalTo(newName))
                .body("status", equalTo(newStatus));
    }

}