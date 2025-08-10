package Magda_Petrachi.PetStoreAPI;

import com.google.gson.Gson;
import example.api.petstore.PetstoreBaseRequest;
import io.restassured.response.Response;
import jdk.internal.classfile.components.ClassPrinter;

import java.util.HashMap;
import java.util.List;

import static groovy.json.JsonOutput.toJson;

public class AdoptionEndPoint extends PetstoreBaseRequest {
    private static final String adoptionEndpoint = "/adoptions";

    public static HashMap<String, String> addAdoption(String location, String... pets) {
        var request = given();
        ClassPrinter.Node gson = null;
        request.body("""
                {
                    "pets": "%s",
                    "location": "%s"
                }         
                """.formatted(location,
                toJson(pets)
        ));

        var response = request.post("/adoption");
        handleResponse(response);

        return response.jsonPath().get("$");
    }


    public static Response getAdoption(String id, String location) {
        var request = given();

        if (location != null) {
            request.queryParam("location", location);
        }

        request.pathParam("id", id);
        var response = request.get(adoptionEndpoint + "/{id}");
        handleResponse(response);
        return response;
    }

    public static Response getAdoptions(String location, String status) {
        var request = given();

        if (location != null) {
            request.queryParam("location", location);
        }

        if (status != null) {
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
            location = "";
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
        var response = request.body(String.format("""
                        {"location": "%s", "status": "%s"}
                        """, location, status))
                .patch(adoptionEndpoint + "/{id}");

        handleResponse(response);
        return response;
    }

    public static Response deleteAdoption(String id, String location) {

        var response = given()
                .pathParam("id", id)
                .queryParam("location", location)
                .delete(adoptionEndpoint + "/{id}");
        handleResponse(response);
        return response;
    }

    public static Response deleteAllAdoption(String location) {

        var response = given()
                .queryParam("location", location)
                .delete(adoptionEndpoint);
        handleResponse(response);
        return response;
    }
}
