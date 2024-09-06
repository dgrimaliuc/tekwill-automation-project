package chiril_bortnicov.api.petstore.endpoints;

import chiril_bortnicov.api.petstore.PetStoreBaseRequest;
import chiril_bortnicov.data.enums.Status;
import io.restassured.response.Response;

public class PetsEndpoint extends PetStoreBaseRequest {

    public static Response getPets() {
        return getPets(null);
    }

    public static Response getPets(String location) {
        return getPets(location, null);
    }

    public static Response getPets(String location, Status status) {
        var request = given();
        if (location != null && !location.isEmpty()) {
            request.queryParam("location", location);
        }
        if (status != null && !status.toString().isEmpty()) {
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

    public static Response deletePet(String id) {
        var request = given();
        if (id != null && !id.isEmpty()) {
            request.queryParam("id", id);
        }
        return handleResponse(request
                .when()
                .delete("/pets" + id));
    }
}
