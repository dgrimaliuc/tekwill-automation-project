package cristina_mocanu.junitCM.api_petstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static example.api.petstore.PetstoreBaseRequest.given;
import static example.api.petstore.PetstoreBaseRequest.handleResponse;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.Matchers.*;

public class PetsApiTests {

    @Test
    @DisplayName("Get pet by id")
    public void testGetPetById() {

        var response = given()
                .queryParam("location", "Chisinau")
                .pathParam("id", "13aae175-3af3-4dc2-b2be-8677bea56efe")
                .get("/pets/{id}");

        handleResponse(response);

        response
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(1000L))
                .body(matchesJsonSchemaFrom("src/main/resourses/schemes/addPet"));


    }


    @Test
    @DisplayName("Get adoptions by id")
    public void testGetAdoptionsById(){


        var response = given()
                .queryParam("location", "Chisinau")
                .pathParam("id", "94a48cb6-2ea6-49b6-8408-2139779508e6")
                .get("/adoptions/{id}");


        handleResponse(response);


        response
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(1000L))
                .body("id", equalTo("94a48cb6-2ea6-49b6-8408-2139779508e6"));
}}
