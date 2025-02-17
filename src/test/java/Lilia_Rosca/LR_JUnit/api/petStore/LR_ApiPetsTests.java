package Lilia_Rosca.LR_JUnit.api.petStore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static example.api.petstore.PetstoreBaseRequest.given;
import static example.api.petstore.PetstoreBaseRequest.handleResponse;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.*;

public class LR_ApiPetsTests {
// 14.02
    @Test
    @DisplayName("Get pet by id test")
    public void getPetByIdTest() {
        var response =  given()
                               .queryParam("location", "Plett")
                               .pathParams("id", "5c7f225e-e1f9-4477-a030-7e8db93244dd")
                               .get("/pets/{id}");
        handleResponse(response);
        response
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(1400L)) // 1372
                .body("id", not(emptyString())) // sau
                .body("id", equalTo("5c7f225e-e1f9-4477-a030-7e8db93244dd")) // sau
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
                .time(lessThan(1200L)); // 1190
    }

}