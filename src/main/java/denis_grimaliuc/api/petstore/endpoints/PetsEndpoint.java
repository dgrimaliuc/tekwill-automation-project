package denis_grimaliuc.api.petstore.endpoints;

import denis_grimaliuc.api.petstore.PetstoreBaseRequest;
import denis_grimaliuc.data.enums.Status;
import io.restassured.response.Response;

public class PetsEndpoint extends PetstoreBaseRequest {

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

        var response = request
                .when()
                .get("/pets");

        return handleResponse(response);
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
}
