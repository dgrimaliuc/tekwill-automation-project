package example.api.petstore;

import io.restassured.response.Response;

public class PetsEndpoint extends PetstoreBaseRequest {


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

    public static Response getPets(String location, String status) {

        var request = given();

        if (location != null) {
            request.queryParam("location", location);
        }

        if (!status.isEmpty()) {
            request.queryParam("status", status);
        }


        var response = request.get("/pets");
        handleResponse(response);
        return response;
    }

    public static Response createPet(String name, String location) {
        return createPet(name, location, false);
    }

    public static Response createPet(String name, String location, boolean emptyBody) {

        if (location == null) {
            location = "";
        }
        if (name == null) {
            name = "";
        }

        var body = emptyBody ? ""
                :
                String.format("""
                        {"name":"%s", "location":"%s"}
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
}
