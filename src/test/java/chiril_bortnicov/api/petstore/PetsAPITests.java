package chiril_bortnicov.api.petstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static chiril_bortnicov.api.petstore.endpoints.PetsEndpoint.*;
import static chiril_bortnicov.data.enums.Status.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PetsAPITests {

    @Test
    @DisplayName("Get all pets test")
    public void getPetsTest() {
        var response = getPets("Moscow", AVAILABLE);
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", greaterThan(0));

        var list = response
                .getBody()
                .jsonPath()
                .getList("status");

        assertThat(list.size(), greaterThan(0));

        for (var status : list) {
            assertThat(status, equalTo("available"));
        }
    }

    @Test
    @DisplayName("Get all pets test2")
    public void getAllPetsTest2() {
        var response = getPets("Moscow", AVAILABLE);
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("location", everyItem(equalTo("Moscow")))
                .body("status", everyItem(equalTo(AVAILABLE.toString())))
                .time(lessThan(1500L));
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
    @DisplayName("Get all pets without status test")
    public void getAllPetsWithoutStatusTest() {
        var response = getPets("Moscow");
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("location", everyItem(equalTo("Moscow")))
                .body("status", everyItem(either(equalTo(ADOPTED.toString()))
                        .or(equalTo(ONHOLD.toString()))
                        .or(equalTo(AVAILABLE.toString()))))
                .time(lessThan(1500L));
    }

    @Test
    @DisplayName("Get all pets schema test")
    public void getAllPetsSchemaTest() {
        var response = getPets("Moscow");
        try {
            response
                    .then()
                    .assertThat()
                    .body(matchesJsonSchema(new FileInputStream("src/main/resources/schemes/getPetsSchema.json")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Add pet test")
    public void addPetTest() throws FileNotFoundException {
        String location = "Moscow";
        String name = "Tom";
        var response = addPet(location, name);

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo(name))
                .body("location", equalTo(location))
                .body("status", equalTo(AVAILABLE.toString()))
                .body(matchesJsonSchema(new FileInputStream("src/main/resources/schemes/addPetSchema.json")))
                .time(lessThan(1500L));
    }

    @Test
    @DisplayName("Add pet without location test")
    public void addPetWithoutLocationTest() {
        var response = addPet("", "Tom");
        response
                .then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo("Props are required: location"))
                .time(lessThan(1500L));
    }

    @Test
    @DisplayName("Add the pet and verify (status code,body,time,schema) test")
    public void addThePetAndVerifyStatusCodeBodyTimeSchemaTest() {
        String location = "Moscow";
        String name = "Tom";
        var response = addPet(location, name);
        try {
            response
                    .then()
                    .assertThat()
                    .statusCode(200)
                    .body("name", equalTo(name))
                    .body("location", equalTo(location))
                    .body(matchesJsonSchema(new FileInputStream("src/main/resources/schemes/addPetSchema.json")))
                    .time(lessThan(1500L));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Delete pet test")
    public void deletePetTest() {
        String location = "Moscow";
        String name = "Tom";
        var response = addPet(location, name);
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo(name))
                .body("location", equalTo(location))
                .time(lessThan(1500L));
        String petId = response.jsonPath().getString("id");
        var delResponse = deletePet(petId);
        delResponse
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(1500L));
        var getResponse = getPets(location);
        getResponse
                .then()
                .assertThat()
                .statusCode(200)
                .body("find { it.id == '%s' }", equalTo(null));
    }
}
