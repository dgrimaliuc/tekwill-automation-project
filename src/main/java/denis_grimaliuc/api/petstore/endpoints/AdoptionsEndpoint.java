package denis_grimaliuc.api.petstore.endpoints;

import denis_grimaliuc.api.petstore.PetstoreBaseRequest;
import io.restassured.response.Response;

public class AdoptionsEndpoint extends PetstoreBaseRequest {

    public static Response getAdoptions(String location, String status) {
        var request = given();

        if (location != null && !location.isEmpty()) {
            request.queryParam("location", location);
        }

        if (status != null && !status.isEmpty()) {
            request.queryParam("status", status);
        }

        var response = request
                .when()
                .get("/adoptions");

        return handleResponse(response);
    }
}
