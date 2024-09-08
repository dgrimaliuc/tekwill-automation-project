package chiril_bortnicov.api.petstore.endpoints;

import chiril_bortnicov.api.petstore.PetStoreBaseRequest;
import chiril_bortnicov.data.enums.Status;
import io.restassured.response.Response;

public class PetsEndpoint extends PetStoreBaseRequest {

    public static Response getPets() {
        return getPets(null);
    }

    public static Response getPets(String location) {
        return getPets(location, "");
    }

    public static Response getPets(String location, Status status) {
        return getPets(location, status.toString());
    }


    public static Response getPets(String location, String status) {
        var request = given();
        if (location != null && !location.isEmpty()) {
            request.queryParam("location", location);
        }
        if (status != null && !status.isEmpty()) {
            request.queryParam("status", status);
        }
        return handleResponse(request
                .when()
                .get("/pets"));
    }

    public static Response addPet() {
        return addPet("", "");
    }

    public static Response addPet(String location, String name) {
        var response = given()
                .body(String.format("""
                        {
                        "location":"%s",
                        "name":"%s"
                        }
                        """, location, name))
                .when()
                .post("/pets");
        return handleResponse(response);
    }

    public static Response deletePets(String location) {
        var request = given();
        if (location != null && !location.isEmpty()) {
            request.queryParam("location", location);
        }
        return handleResponse(request
                .when()
                .delete("/pets"));
    }

    public static Response updatePet(String id, String location, String status, String name) {
        var response = given()
                .body("""
                        {
                        "location": "%s",
                        "status": "%s",
                        "name": "%s"
                        }
                        """.formatted(location, status, name))
                .pathParams("id", id)
                .when()
                .patch("/pets/{id}");
        return handleResponse(response);
    }
}
