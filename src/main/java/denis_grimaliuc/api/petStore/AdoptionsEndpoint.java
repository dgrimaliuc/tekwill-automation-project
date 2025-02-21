package denis_grimaliuc.api.petStore;

import example.api.petstore.PetstoreBaseRequest;
import io.restassured.response.Response;

public class AdoptionsEndpoint extends PetstoreBaseRequest {

    public static Response getAdoption(String id, String location) {
        var request = given();

        if (location != null) {
            request.queryParam("location", location);
        }

        request.pathParam("id", id);
        var response = request.get("/adoptions/{id}");
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

        var response = request.get("/adoptions");
        handleResponse(response);
        return response;
    }
}
