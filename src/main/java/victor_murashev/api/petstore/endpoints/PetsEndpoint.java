package victor_murashev.api.petstore.endpoints;

import io.restassured.response.Response;
import victor_murashev.api.petstore.PetstoreBaseRequest;

public class PetsEndpoint extends PetstoreBaseRequest {

    public static Response getPets(String location, String status) {
        var request = given();

        if (location != null && !location.isEmpty()) {
            request.queryParam("location", location);
        }
        if (status != null && !status.isEmpty()) {
            request.queryParam("status", status);
        }

        Response response = request
                .when()
                .get("/pets");

        return handleResponse(response);
    }

    public static Response getPets(String location) {
        return getPets(location, null);
    }

    public static Response getPets() {
        return getPets(null);
    }

    public static Response addPets(String location, String name) {
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

}
