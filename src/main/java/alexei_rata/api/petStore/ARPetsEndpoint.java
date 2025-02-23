package alexei_rata.api.petStore;

import example.api.petstore.PetstoreBaseRequest;
import io.restassured.response.Response;

public class ARPetsEndpoint extends PetstoreBaseRequest {

    public static Response getSinglePet(String location, String id) {
        var request = given();

        if (location != null) {
            request.queryParam("location", location);
        }

        if (id != null) {
            request.pathParams("id", id);
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


    public static Response createPet(String location, String name) {
        return createPet(location, name, false);
    }


    public static Response createPet(String location, String name, boolean emptyBody) {

        if (location == null) {
            location = "";
        }
        if (name == null) {
            name = "";
        }

        var body = emptyBody ? ""
                :
                String.format("""
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

    public static Response patchPet(String petId, String body) {

        var request = given();

        if (petId == null) {
            petId = "";
        }

        var response = request
                .pathParam("id", petId)
                .body(body)
                .patch("/pets/{id}");

        handleResponse(response);
        return response;
    }


    public static Response deletePet(String location, String id) {
        var request = given();

        if (location == null) {
            location = "";
        }
        if (id == null) {
            id = "";
        }

        var response = request
                .pathParams("id", id)
                .queryParam("location", location)
                .delete("/pets/{id}");

        handleResponse(response);
        return response;
    }

    public static Response deletePets(String location) {
        var request = given();

        if (location != null) {
            request.queryParam("location", location);
        }

        var response = request.delete("/pets");
        handleResponse(response);
        return response;
    }

}
