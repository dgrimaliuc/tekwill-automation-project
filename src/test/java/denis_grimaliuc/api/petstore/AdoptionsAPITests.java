package denis_grimaliuc.api.petstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static denis_grimaliuc.api.petstore.endpoints.AdoptionsEndpoint.getAdoptions;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class AdoptionsAPITests {


    @Test
    @DisplayName("Get all adoptions test")
    public void getAdoptionsTest() {
        var location = "Chisinau";
        getAdoptions(location, "")
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("location", everyItem(equalTo(location)))
                .time(lessThan(1000L));
    }

    @Test
    @DisplayName("Get custom status adoptions test")
    public void getAdoptionsCustomStatusTest() {
        var location = "Chisinau";
        getAdoptions(location, "!approved&!rejected&!denied")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("status", everyItem(equalTo("available")))
                .time(lessThan(1000L));
    }

}
