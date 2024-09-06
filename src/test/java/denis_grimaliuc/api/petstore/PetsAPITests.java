package denis_grimaliuc.api.petstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static denis_grimaliuc.api.petstore.endpoints.PetsEndpoint.*;
import static denis_grimaliuc.data.enums.Status.*;
import static denis_grimaliuc.data.utils.MatcherUtils.matchesJsonSchemaFrom;
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
                .body("size()", greaterThan(0))
                .body("location", everyItem(equalTo("Chisinau")))
                .body("status", everyItem(equalTo(AVAILABLE.toString())))
                .time(lessThan(1000L));
    }

    @Test
    @DisplayName("Get all pets schema test")
    public void getPetsSchemaTest() {
        var response = getPets("Chisinau");

        response
                .then()
                .assertThat()
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/getPetsSchema.json"));


    }

    @Test
    @DisplayName("Get all pets without status test")
    public void getPetsWithoutStatusTest() {
        var response = getPets("Chisinau");
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("location", everyItem(equalTo("Chisinau")))
                .body("status", everyItem(either(equalTo(ADOPTED.toString()))
                        .or(equalTo(ONHOLD.toString()))
                        .or(equalTo(AVAILABLE.toString()))))
                .time(lessThan(1000L));
    }

    @Test
    @DisplayName("Get all pets without location test")
    public void getPetsWithoutLocationTest() {
        var response = getPets();
        response
                .then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo("Missing location"));
    }

    @Test
    @DisplayName("Add pet test")
    public void addPetTest() {
        String name = "Rex";
        String location = "Chisinau";

        var response = addPet(location, name);

        response
                .then()
                .assertThat()
                .statusCode(201)
                .body("name", equalTo(name))
                .body("location", equalTo(location))
                .body("status", equalTo(AVAILABLE.toString()))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
                .time(lessThan(1000L));
    }

    @Test
    @DisplayName("Add pet without location test")
    public void addPetWithoutLocationTest() {
        var response = addPet("", "Rex");
        response
                .then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo("Props are required: location"))
                .time(lessThan(1000L));
    }

    @Test
    @DisplayName("Delete pets from location test")
    public void deletePetsTest() {
        String location = "Plett";
        var response = deletePets(location);

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("message", equalTo("Removed"))
                .time(lessThan(1000L));

        getPets(location)
                .then()
                .assertThat()
                .body("size()", equalTo(0));
    }

    @Test
    @DisplayName("Get pets with !status test")
    public void getCustomStatusPets() {
        getPets("Chisinau", "!" + AVAILABLE + "&!" + ADOPTED)
                .then()
                .assertThat()
                .statusCode(200)
                .body("status", everyItem(equalTo(ONHOLD.toString())));

    }
}
