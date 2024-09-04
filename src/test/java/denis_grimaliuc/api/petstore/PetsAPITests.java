package denis_grimaliuc.api.petstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static denis_grimaliuc.api.petstore.endpoints.PetsEndpoint.addPet;
import static denis_grimaliuc.api.petstore.endpoints.PetsEndpoint.getPets;
import static denis_grimaliuc.data.enums.Status.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
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
        try {
            response
                    .then()
                    .assertThat()
                    .body(matchesJsonSchema(new FileInputStream("src/main/resources/schemes/getPetsSchema.json")));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    public void addPetTest() throws FileNotFoundException {
        String name = "Rex";
        String location = "Chisinau";

        var response = addPet(location, name);

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo(name))
                .body("location", equalTo(location))
                .body("status", equalTo(AVAILABLE.toString()))
                .body(matchesJsonSchema(new FileInputStream("src/main/resources/schemes/addPetSchema.json")))
                .time(lessThan(1000L));

    }
}
