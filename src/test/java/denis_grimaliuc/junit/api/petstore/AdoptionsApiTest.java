package denis_grimaliuc.junit.api.petstore;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static denis_grimaliuc.api.petStore.AdoptionsEndpoint.getAdoption;
import static denis_grimaliuc.api.petStore.AdoptionsEndpoint.getAdoptions;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.Matchers.*;

public class AdoptionsApiTest {


    Logger log = Logger.getLogger(PetsApiTest.class);


    @Test
    @DisplayName("Get adoption by id")
    public void testGetAdoptionById() {
        String location = "Chisinau";
        String id = "d2cdbcb8-f512-4b43-8f7f-63702a8831f6";
        getAdoption(id, location)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(1000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addAdoptionSchema.json"))
                .body("id", equalTo(id))
                .body("location", equalTo(location));
    }

    @Test
    @DisplayName("Get adoption by wrong id")
    public void testGetAdoptionByWrongId() {
        String location = "Chisinau";
        String id = "aaaaaa";
        getAdoption(id, location)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(1000L))
                .body("error", equalTo("Adoption ID is invalid"));
    }

    @Test
    @DisplayName("Get adoption by unexciting id")
    public void testGetAdoptionByUnexcitingId() {
        String location = "Chisinau";
        String id = "d2cdbcb8-f512-4b43-8f7f-63702a8831ff";
        getAdoption(id, location)
                .then()
                .assertThat()
                .statusCode(404)
                .time(lessThan(1000L))
                .body("error", equalTo("Adoption " + id + " not found in Chisinau"));
    }


    @Test
    @DisplayName("Get adoption by id without location")
    public void testGetAdoptionByIdWithoutLocation() {
        String id = "d2cdbcb8-f512-4b43-8f7f-63702a8831f6";
        getAdoption(id, null)
                .then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo("Missing location"))
                .time(lessThan(1000L));
    }

    @Test
    @DisplayName("Get multiple adoptions test")
    public void getMultipleAdoptionTests() {
        String location = "Chisinau";
        String status = "approved";
        getAdoptions(location, status)
                .then()
                .assertThat()
                .time(lessThan(1000L))
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/getAdoptionsSchema.json"))
                .body("status", everyItem(equalTo(status)));


    }


}
