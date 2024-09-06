package victor_murashev.api.petstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import victor_murashev.api.petstore.endpoints.PetsEndpoint;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static denis_grimaliuc.data.enums.Status.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

public class PetsAPITests extends PetsEndpoint {

    @Test
    @DisplayName("Get all Available pets for location")
    public void getAllPets() {
        var response = getPets("Chisinau", String.valueOf(AVAILABLE));

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("location", everyItem(equalTo("Chisinau")))
                .body("status", everyItem(equalTo(AVAILABLE.toString())))
                .time((lessThan(1000L)));

        /*var petsList = response.body().jsonPath().getList("status");
        assertThat(petsList.size(), greaterThan(0));

        for (var status : petsList) {
            assertThat(status, equalTo(AVAILABLE.toString()));
        }*/
    }

    @Test
    @DisplayName("Get all pets without location")
    public void getAllPetsWithoutLocation() {
        var response = getPets();

        response.then().assertThat()
                .statusCode(400)
                .body("error", equalTo("Missing location"));


    }

    @Test
    @DisplayName("Get all pets for location (without Status)")
    public void getPetsWithoutStatus() {
        var response = getPets("Chisinau", String.valueOf(AVAILABLE));

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("location", everyItem(equalTo("Chisinau")))
                .body("status", everyItem(either(equalTo(AVAILABLE.toString()))
                        .or(equalTo(ONHOLD.toString()))
                        .or(equalTo(ADOPTED.toString()))))
                .time((lessThan(1500L)));
    }

    @Test
    @DisplayName("Get and check all fields presence")
    public void getCheckAllFields() {
        var response = getPets("Chisinau");

        try {
            response
                    .then()
                    .assertThat()
                    .body(matchesJsonSchema(new FileInputStream("src/main/resources/schemes/addPetSchema.json")))
                    .time((lessThan(1500L)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Adding a pet")
    public void addPet() throws FileNotFoundException {

        String location = "Balti";
        String petName = "Kuzia";

        var response = addPets(location, petName);

        response
                .then()
                .assertThat()
                .statusCode(201)
                .body("location", equalTo(location))
                .body("name", equalTo(petName))
                .body("status", equalTo(AVAILABLE.toString()))
                .body(matchesJsonSchema(new FileInputStream("src/main/resources/schemes/addPetSchema.json")))
                .time(lessThan(1500L));

    }


}
