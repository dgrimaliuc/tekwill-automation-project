package IonErm.api.petstore;

import example.api.petstore.PetstoreBaseRequest;
import io.restassured.response.Response;

public class PetEndpoint extends PetstoreBaseRequest {

    public static Response getPet(String id, String location) {
        var request = given();
        if (location != null) {
            request.queryParam("location", location);
        }
        if (id != null) {
            request.pathParam("id", id);
        }
        var response = request.get("/pets/{id}");
        handleResponse(response);
        return response;
    }

    public static Response getPets(String status, String location) {
        var request = given();
        if (!status.isEmpty()) {
            request.queryParam("status", status);
        }
        if (location != null) {
            request.queryParam("location", location);
        }
        var response = request.get("/pets");
        handleResponse(response);
        return response;
    }

    public static Response createPet(String name, String location) {
        return createPet(name, location, false);
    }

    public static Response createPet(String name, String location, boolean empty_body) {
        if (name == null) {
            name = "";
        }
        if (location == null) {
            location = "";
        }
        var body = empty_body ? "" : String.format("""
                {
                    "name":"%s",
                    "location":"%s"
                }
                """, name, location);
        var response = given()
                .body(body)
                .post("/pets");
        handleResponse(response);
        return response;
    }

    public static Response patchPet(String id, String body) {
        var request = given();
        if (id == null) {
            id = "";
        }
        var response = request.pathParam("id", id)
                .body(body)
                .patch("/pets/{id}");
        handleResponse(response);
        return response;
    }

    public static Response deletePet(String id, String location) {
        var request = given();
        if (id == null) {
            id = "";
        }
        if (location == null) {
            location = "";
        }
        var response = request
                .pathParam("id", id)
                .queryParam("location", location)
                .delete("/pets/{id}");
        handleResponse(response);
        return response;
    }

    public static Response deletePets(String location) {
        var request = given();
        if (location == null) {
            location = "";
        }
        var response = request
                .queryParam("location", location)
                .delete("/pets");
        handleResponse(response);
        return response;
    }
}
