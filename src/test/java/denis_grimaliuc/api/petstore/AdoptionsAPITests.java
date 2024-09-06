package denis_grimaliuc.api.petstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static denis_grimaliuc.api.petstore.endpoints.AdoptionsEndpoint.createAdoption;
import static denis_grimaliuc.api.petstore.endpoints.AdoptionsEndpoint.getAdoptions;
import static denis_grimaliuc.api.petstore.endpoints.PetsEndpoint.addPet;
import static denis_grimaliuc.data.enums.Status.AVAILABLE;
import static denis_grimaliuc.data.utils.MatcherUtils.matchesJsonSchemaFrom;
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
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/getAdoptionsSchema.json"))
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

    @Test
    @DisplayName("Get all adoptions without location test")
    public void getAdoptionsWithoutLocationTest() {
        getAdoptions("", "")
                .then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo("Missing location"))
                .time(lessThan(1000L));

    }

    @Test
    @DisplayName("Create adoption test")
    public void createAdoptionTest() {
        var location = "Chisinau 2";
        var pet = addPet(location, "CAT")
                .jsonPath()
                .getString("id");

        System.out.println(pet);

        List<String> pets = List.of(pet);

        createAdoption(location, pets)
                .then()
                .assertThat()
                .statusCode(201)
                .body("location", equalTo(location))
                .body("pets.size()", equalTo(pets.size()))
                .body("status", equalTo(AVAILABLE.toString()))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addAdoptionSchema.json"))
                .time(lessThan(1000L));

    }

}
