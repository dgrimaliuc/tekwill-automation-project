package victor_murashev.api.petstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import victor_murashev.api.petstore.endpoints.PetsEndpoint;

import static denis_grimaliuc.data.enums.Status.AVAILABLE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class PetsAPITests extends PetsEndpoint {

    @Test
    @DisplayName("Get all pets for location")
    public void getAllPets() {
        var response = getPets("Chisinau", String.valueOf(AVAILABLE));

        response.then().assertThat()
                .statusCode(200)
                .body("size()", greaterThan(0));

        var petsList = response.body().jsonPath().getList("status");
        assertThat(petsList.size(), greaterThan(0));

        for (var status : petsList) {
            assertThat(status, equalTo(AVAILABLE.toString()));
        }
    }

    @Test
    @DisplayName("Get all pets without location")
    public void getAllPetsWithoutLocation() {
        var response = getPets();

        response.then().assertThat()
                .statusCode(400)
                .body("error", equalTo("Missing location"));


    }
}
