package example.api;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import static example.api.endpoints.ExampleAdoptionsEndpoint.*;
import static example.api.endpoints.ExamplePetsEndpoint.getPetsA;
import static example.data.enums.Status.APPROVED;
import static example.data.enums.Status.AVAILABLE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class AdoptionsAPITestExample {
    Response response = null;


    @Test
    public void getAdoptionsSchemaTest() throws FileNotFoundException {

        response = getAdoptionsA(AVAILABLE, "Chisinau");
        response.then().assertThat().body(JsonSchemaValidator
                .matchesJsonSchema(new FileInputStream("src/main/resources/schemes/getAdoptionsSchema.json")));
        response.then().assertThat().body("size()", greaterThan(0));
    }

    @Test
    public void getAdoptionsTimingTest() {
        response = getAdoptionsA(AVAILABLE, "Chisinau");
        response.then()
                .assertThat()
                .time(lessThan(3L), TimeUnit.SECONDS);

    }

    @Test
    public void addAdoptionSchemaTest() throws FileNotFoundException {
        Response pets = getPetsA(AVAILABLE, "Chisinau");
        String[] petIds = pets.jsonPath().getList("id")
                .stream().limit(4).toArray(String[]::new);

        response = addAdoptionA("Chisinau", petIds);
        response.then().assertThat().body(JsonSchemaValidator
                .matchesJsonSchema(new FileInputStream("src/main/resources/schemes/addAdoptionSchema.json")));

    }

    @Test
    public void addAdoptionTimingTest() {
        Response pets = getPetsA(AVAILABLE, "Chisinau");
        String[] petIds = pets.jsonPath().getList("id")
                .stream().limit(4).toArray(String[]::new);
        response = addAdoptionA("Chisinau", petIds);
        response.then()
                .assertThat()
                .time(lessThan(3L), TimeUnit.SECONDS);

    }

    @Test
    public void updateAdoptionStatusSchemaTest() throws FileNotFoundException {
        String id = getAdoptionsA(APPROVED, "trash").
                jsonPath()
                .getList("id", String.class)
                .get(0);

        response = updateStatusA(AVAILABLE, id);
        response.then().assertThat()
                .body("status", equalTo("available"))
                .body(JsonSchemaValidator
                        .matchesJsonSchema(new FileInputStream("src/main/resources/schemes/addAdoptionSchema.json")));

    }

    @Test
    public void updateAdoptionStatusTimingTest() {
        String id = getAdoptionsA(AVAILABLE, "Chisinau").
                jsonPath()
                .getList("id", String.class)
                .get(0);

        response = updateStatusA(AVAILABLE, id);
        response.then()
                .assertThat()
                .time(lessThan(3L), TimeUnit.SECONDS);

    }
}
