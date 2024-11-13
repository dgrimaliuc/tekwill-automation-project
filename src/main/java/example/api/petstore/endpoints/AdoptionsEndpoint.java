package example.api.petstore.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.api.petstore.PetstoreBaseRequest;
import io.restassured.response.Response;

import java.util.List;

public class AdoptionsEndpoint extends PetstoreBaseRequest {

    public static Response getAdoption(String id, String location) {
        var request = given()
                .pathParam("adoption_id", id);

        if (location != null && !location.isEmpty()) {
            request.queryParam("location", location);
        }

        var response = request.when().get("/adoptions/{adoption_id}");
        return handleResponse(response);
    }

    public static Response getAdoptions(String location, String status) {
        var request = given();

        if (location != null && !location.isEmpty()) {
            request.queryParam("location", location);
        }

        if (status != null && !status.isEmpty()) {
            request.queryParam("status", status);
        }

        var response = request.when().get("/adoptions");

        return handleResponse(response);
    }

    public static Response createAdoption(String location) {
        return createAdoption(location, List.of());

    }

    public static Response createAdoption() {
        return createAdoption("", List.of());
    }

    public static Response createAdoption(String location, List<String> pets) {

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList;
        try {
            // Convert list to JSON
            jsonList = objectMapper.writeValueAsString(pets);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        var response = given().body("""
                {
                "location": "%s",
                "pets": %s
                }
                """.formatted(location, jsonList)).when().post("/adoptions");

        return handleResponse(response);
    }

    public static Response updateAdoption(String id, String status, String location) {
        var response = given()
                .body("""
                        {
                        "status":"%s",
                        "location":"%s"
                        }
                        """.formatted(status, location))
                .pathParams("adoption_id", id)
                .when()
                .patch("/adoptions/{adoption_id}");

        return handleResponse(response);
    }

    public static Response deleteAdoptions(String location) {
        var request = given();

        if (location != null && !location.isEmpty()) {
            request.queryParam("location", location);
        }

        var response = request
                .when()
                .delete("/adoptions");
        return handleResponse(response);
    }

    public static Response deleteAdoption(String id, String location) {
        var request = given();

        if (location != null && !location.isEmpty()) {
            request.queryParam("location", location);
        }

        var response = request
                .when()
                .delete("/adoptions/{adoption_id}", id);

        return handleResponse(response);
    }


}
