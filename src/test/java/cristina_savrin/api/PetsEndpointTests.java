package cristina_savrin.api;

import cristina_savrin.data.enums.StatusCS;
import helpers.PropertiesReader;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static cristina_savrin.api.endpoints.PetsEndpointCS.*;
import static cristina_savrin.data.enums.StatusCS.ONHOLD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class PetsEndpointTests {

    Response response = null;

    @AfterMethod
    public void log() {
        if (PropertiesReader.getPropertyOrDefault("log_all", "false").equals("true")) {
            response.then().log().all();
        }
    }

    @Test(testName = "Get pets schema test")
    public void schemaGetTest() {
        response = getPets(StatusCS.AVAILABLE, "Chisinau");
        try {

            response.then()
                    .assertThat()
                    .body(JsonSchemaValidator
                            .matchesJsonSchema(
                                    new FileInputStream("src/main/resources/schemes/getPetsSchema.json")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(testName = "Get pets timing test")
    public void timingGetTest() {
        response = getPets(StatusCS.AVAILABLE, "Chisinau");
        response.then()
                .assertThat()
                .time(lessThan(2L), TimeUnit.SECONDS);
    }

    @Test(testName = "Add pet schema test")
    public void schemaAddTest() {
        response = addPet("Rover", "Chisinau");
        try {
            response.then()
                    .assertThat()
                    .body(JsonSchemaValidator
                            .matchesJsonSchema(
                                    new FileInputStream("src/main/resources/schemes/addPetSchema.json")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(testName = "Add pet timing test")
    public void timingAddTest() {
        response = addPet("Rover", "Chisinau");
        response.then()
                .assertThat()
                .time(lessThan(3L), TimeUnit.SECONDS);
    }

    @Test(testName = "Patch pet status test")
    public void patchStatusTest() {
        String location = "Chisinau" + System.currentTimeMillis();
        Response respAdd = addPet("Rover", location);
        String idOfAddedPet = respAdd.body().jsonPath().get("id");

        response = updateStatus(ONHOLD, idOfAddedPet);
        response.then()
                .assertThat()
                .time(lessThan(3L), TimeUnit.SECONDS);
        response.then()
                .assertThat()
                .body("status", Matchers.equalTo(ONHOLD.toString().toLowerCase()));
    }

    @Test(testName = "Patch pet status timing test")
    public void timingPatchTest() {
        String location = "Chisinau" + System.currentTimeMillis();
        Response respAdd = addPet("Rover", location);
        String idOfAddedPet = respAdd.body().jsonPath().get("id");

        response = updateStatus(ONHOLD, idOfAddedPet);
        response.then()
                .assertThat()
                .time(lessThan(3L), TimeUnit.SECONDS);

    }

    @Test(testName = "Add pet schema test")
    public void schemaPatchTest() {
        String location = "Chisinau" + System.currentTimeMillis();
        Response respAdd = addPet("Rover", location);
        String idOfAddedPet = respAdd.body().jsonPath().get("id");

        response = updateStatus(ONHOLD, idOfAddedPet);
        try {
            response.then()
                    .assertThat()
                    .body(JsonSchemaValidator
                            .matchesJsonSchema(
                                    new FileInputStream("src/main/resources/schemes/addPetSchema.json")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addPetTest() {
        String randomLocation = "Chisinau" + System.currentTimeMillis();
        Response resp = addPet("Rover", randomLocation);
        response = getPets(StatusCS.AVAILABLE, randomLocation);
        String id = resp.body().jsonPath().get("id");
        List<Object> pets = response
                .jsonPath()
                .getList("$", Map.class).stream().
                filter(it -> it.get("id").equals(id)).collect(Collectors.toList());
        assertThat(pets.size(), Matchers.equalTo(1));
    }
}