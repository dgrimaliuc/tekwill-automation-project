package chiril_bortnicov.api.petstore.endpoints;

import chiril_bortnicov.api.petstore.PetStoreBaseRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.util.List;

public class AdoptionsEndpoint extends PetStoreBaseRequest {

    public static Response getAdoptions(String location, String status) {
        var request = given();
        if (location != null && !location.isEmpty()) {
            request.queryParam("location", location);
        }
        if (status != null && !status.isEmpty()) {
            request.queryParam("status", status);
        }
        return handleResponse(request
                .when().get("/adoptions"));
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
            jsonList = objectMapper.writeValueAsString(pets);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (handleResponse(given().body("""
                {
                "location": "%s",
                "pets": %s
                }
                """.formatted(location, jsonList)).when().post("/adoptions")));
    }

    public static Response updateAdoption(String id, String status, String location) {
        return handleResponse(given()
                .body("""
                        {
                        "status": "%s",
                        "location": "%s"
                        }
                        """.formatted(status, location))
                .pathParams("id", id)
                .when()
                .patch("/adoptions/{id}"));
    }

    public static Response deleteAdoption() {
        return deleteAdoption("");
    }

    public static Response deleteAdoption(String location) {
        var request = given();
        if (location != null && !location.isEmpty()) {
            request.queryParam("location", location);
        }
        return handleResponse(request
                .when()
                .delete("/adoptions"));
    }
}
