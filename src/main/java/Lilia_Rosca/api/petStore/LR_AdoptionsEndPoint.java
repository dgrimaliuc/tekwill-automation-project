package Lilia_Rosca.api.petStore;

import com.google.gson.Gson;
import example.api.petstore.PetstoreBaseRequest;
import io.restassured.response.Response;

import java.util.List;

public class LR_AdoptionsEndPoint extends PetstoreBaseRequest {
// 21.02
    public static final String adoptionEndpoint = "/adoptions";

    public static Response getAdoption(String id, String location) {
        var request = given();
        if (location != null) {
            request.queryParam("location", location);
        }
        request.pathParam("id", id);

        var response = request.get( adoptionEndpoint + "/{id}"); // = "/adoptions/{id}"
        handleResponse(response);
        return response;
    }

    public static Response getAdoptions(String location, String status) {
        var request = given();
        if (location != null) {
            request.queryParam("location", location);
        }
        if (location != null) {
            request.queryParam("status", status);
        }

        var response = request.get(adoptionEndpoint);
        handleResponse(response);
        return response;
    }
    public static Response createAdoption(String location, List<String> pets) {
        var request = given();
        Gson gson = new Gson();
        if (location == null) {
            location ="";
        }
        var response = request.body(String.format("""
                                                  {"location":"%s", "pets":%s}
                                                  """, location, gson.toJson(pets)))
                               .post(adoptionEndpoint);
        handleResponse(response);
        return response;
    }

    public static Response patchAdoption(String id, String location, String status) {
        var request = given();
        request.pathParam("id", id);
        var response = request
                .body(String.format("""
                                    {"location":"%s", "status":"%s"}
                                    """, location, status))
                .patch(adoptionEndpoint + "/{id}");
        handleResponse(response);
        return response;
    }

    public static Response deleteAdoption(String id, String location) {
        var response = given()
                .pathParam("id",id)
                .queryParam("location", location)
                .delete(adoptionEndpoint + "/{id}");
        handleResponse(response);
        return response;
    }

    public static Response deleteAllAdoptions(String location) {
        var response = given()
                .queryParam("location", location)
                .delete(adoptionEndpoint);
        handleResponse(response);
        return response;
    }
}