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
                .get("pets/{id}");
        handleResponse(response);

        response
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src\\main\\resources\\schemes\\addPetSchema.json"));
    }
}
