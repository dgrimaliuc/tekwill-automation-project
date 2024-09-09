package chiril_bortnicov.api.petstore;

import chiril_bortnicov.data.enums.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chiril_bortnicov.api.petstore.endpoints.PetsEndpoint.*;
import static chiril_bortnicov.data.enums.Status.*;
import static chiril_bortnicov.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PetsAPITests {

    @Test
    @DisplayName("Get all pets test")
    public void getPetsTest() {
        String location = "Moscow";
        String name = "Tom";
        addPet(location, name);
        var response = getPets(location, AVAILABLE);
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
        response
                .then()
                .assertThat()
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/getPetsSchema.json"));
    }

    @Test
    @DisplayName("Add pet test")
    public void addPetTest() {
        String location = "Moscow";
        String name = "Tom";
        var response = addPet(location, name);

        response
                .then()
                .assertThat()
                .statusCode(201)
                .body("name", equalTo(name))
                .body("location", equalTo(location))
                .body("status", equalTo(AVAILABLE.toString()))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
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
        response
                .then()
                .assertThat()
                .statusCode(201)
                .body("name", equalTo(name))
                .body("location", equalTo(location))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
                .time(lessThan(1500L));
    }

    @Test
    @DisplayName("Delete pet test")
    public void deletePetTest() {
        String location = "Moscow";
        var response = deletePets(location);
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("message", equalTo("Removed"))
                .time(lessThan(1500L));
        getPets(location)
                .then()
                .assertThat()
                .body("size()", equalTo(0));
    }

    @Test
    @DisplayName("Get pets with !status test")
    public void getCustomStatusPetsTest() {
        String location = "Moscow";
        String name = "Tom";
        addPet(location, name);
        var response = getPets(location, "!" + AVAILABLE + "&!" + ADOPTED);
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("status", everyItem(equalTo(Status.ONHOLD.toString())));
    }

    @Test
    @DisplayName("Update pet test")
    public void updatePetTest() {
        var location = "Moscow";
        var newName = "Tom";
        var pet = addPet(location, "Tommy").jsonPath().getString("id");
        var response = updatePet(pet, location, ADOPTED.toString(), newName);
        response
                .then()
                .assertThat()
                .body("name", equalTo(newName))
                .body("location", equalTo(location))
                .body("status", equalTo(ADOPTED.toString()))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/addPetSchema.json"))
                .time(lessThan(1500L));
    }
}

