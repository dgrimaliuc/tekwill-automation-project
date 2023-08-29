package cristina_savrin.api;

import helpers.PropertiesReader;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import static cristina_savrin.api.endpoints.AdoptionsEndpointCS.*;
import static cristina_savrin.api.endpoints.PetsEndpointCS.getPets;
import static cristina_savrin.data.enums.StatusCS.APPROVED;
import static cristina_savrin.data.enums.StatusCS.AVAILABLE;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class AdoptionsEndpointTests {

    Response response = null;

    @AfterEach
    public void log() {
        if (PropertiesReader.getPropertyOrDefault("log_all", "false").equals("true")) {
            response.then().log().all();
        }
    }

    @Test
    public void getAdoptionsSchemaTest() throws FileNotFoundException {
        response = getAdoptions(AVAILABLE, "Chisinau");
        response.then().assertThat().body(JsonSchemaValidator
                .matchesJsonSchema(new FileInputStream("src/main/resources/schemes/getAdoptionsSchema.json")));
    }

    @Test
    public void getAdoptionsTimingTest() {
        response = getAdoptions(AVAILABLE, "Chisinau");
        response.then().assertThat()
                .time(lessThan(3L), TimeUnit.SECONDS);
    }

    @Test
    public void addAdoptionSchemaTest() throws FileNotFoundException {
        Response pets = getPets(AVAILABLE, "Chisinau");
        String[] petIds = pets.jsonPath().getList("id").
                stream().limit(4).toArray(String[]::new);
        response = addAdoption("Chisinau", petIds);
        response.then().assertThat().body(JsonSchemaValidator
                .matchesJsonSchema(new FileInputStream("src/main/resources/schemes/addAdoptionsSchema.json")));
    }

    @Test
    public void addAdoptionTimingTest() {
        Response pets = getPets(AVAILABLE, "Chisinau");
        String[] petIds = pets.jsonPath().getList("id").
                stream().limit(4).toArray(String[]::new);
        response = addAdoption("Chisinau", petIds);
        response.then()
                .assertThat()
                .time(lessThan(3L), TimeUnit.SECONDS);
    }

    @Test
    public void updateAdoptionStatusSchemaTest() throws FileNotFoundException {
        String id = getAdoptions(APPROVED, "trash")
                .jsonPath()
                .getList("id", String.class)
                .get(0);

        response = updateStatus(AVAILABLE, id);
        response.then().assertThat()
                .body("status", equalTo("available"))
                .body(JsonSchemaValidator
                        .matchesJsonSchema(new FileInputStream("src/main/resources/schemes/addAdoptionsSchema.json")));
    }

    @Test
    public void updateAdoptionStatusTimingTest() {
        String id = getAdoptions(APPROVED, "Chisinau")
                .jsonPath()
                .getList("id", String.class)
                .get(0);

        response = updateStatus(AVAILABLE, id);
        response.then()
                .assertThat()
                .time(lessThan(3L), TimeUnit.SECONDS);
    }
}