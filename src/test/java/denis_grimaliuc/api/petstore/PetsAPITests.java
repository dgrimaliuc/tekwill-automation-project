package denis_grimaliuc.api.petstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static denis_grimaliuc.api.petstore.endpoints.PetsEndpoint.getPets;
import static denis_grimaliuc.data.enums.Status.AVAILABLE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PetsAPITests {
    @Test
    @DisplayName("Get all pets test")
    public void getPetsTest() {
        var response = getPets("Chisinau", AVAILABLE);
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", greaterThan(0));


        var list = response
                .body()
                .jsonPath()
                .getList("status");

        assertThat(list.size(), greaterThan(0));

        for (var status : list) {
            assertThat(status, equalTo(AVAILABLE.toString()));
        }
    }

  
}
