package victor_murashev.api.petstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static denis_grimaliuc.api.petstore.endpoints.AdoptionsEndpoint.*;
import static denis_grimaliuc.api.petstore.endpoints.PetsEndpoint.addPet;
import static denis_grimaliuc.data.enums.Status.AVAILABLE;
import static denis_grimaliuc.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;

public class AdoptionsAPITests {

    String location = "Cahul";
    String emptyLocation = "";
    String name = "Simon";
    String newStatus = "approved";

    //Lesson tests training

    @Test
    @DisplayName("Get all adoptions test")
    public void getAllAdoptionsTest() {
        getAdoptions(location, "")
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("location", everyItem(equalTo(location)))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/getAdoptionsSchema.json"))
                .time(lessThan(1500L));

    }

    @Test
    @DisplayName("Get adoptions with Status test")
    public void getAdoptionsWithStatusTest() {
        getAdoptions(location, "!approved&!rejected&!denied")
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("status", everyItem(equalTo("available")))
                .time(lessThan(1500L));

    }

    @Test
    @DisplayName("Get adoptions with Available-Only Status test")
    public void getAdoptionsWithAvailbleOnlyStatusTest() {
        getAdoptions(location, AVAILABLE.toString())
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("status", everyItem(equalTo("available")))
                .time(lessThan(1500L));

    }

    //Homework part

    @Test
    @DisplayName("Create adoption test")
    public void createAdoptionTest() {

        var pet = addPet(location, name)
                .jsonPath()
                .getString("id");
        List<String> pets = List.of(pet);

        createAdoption(location, pets)
                .then()
                .assertThat()
                .statusCode(201)
                .body("location", equalTo(location))
                .body("pets.size()", equalTo(pets.size()))
                .body("status", equalTo(AVAILABLE.toString()))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addAdoptionSchema.json"))
                .time(lessThan(2000L));
    }

    @Test
    @DisplayName("Update adoption status test")
    public void updateAdoptionStatusTest() {

        var pet = addPet(location, name)
                .jsonPath()
                .getString("id");

        List<String> pets = List.of(pet);

        String AdoptionId = createAdoption(location, pets)
                .jsonPath().getString("id");


        updateAdoption(AdoptionId, newStatus, location)
                .then()
                .assertThat()
                .statusCode(200)
                .body("status", equalTo(newStatus))
                .body("location", equalTo(location))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addAdoptionSchema.json"))
                .time(lessThan(2000L));
    }

    @Test
    @DisplayName("Delete adoptions test")
    public void deleteAdoptionTest() {
        createAdoptionTest();

        deleteAdoptions(location)
                .then()
                .assertThat()
                .statusCode(200)
                .body("message", equalTo("Removed"))
                .time(lessThan(2500L));

        getAdoptions(location, "")
                .then()
                .assertThat()
                .body("size()", equalTo(0));
    }

    @Test
    @DisplayName("Delete adoptions without location test")
    public void deleteAdoptionWithoutLocationTest() {

        deleteAdoptions(emptyLocation)
                .then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo("Location is required"))
                .time(lessThan(2000L));
    }


}
