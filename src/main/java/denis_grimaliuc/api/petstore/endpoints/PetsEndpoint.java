package denis_grimaliuc.api.petstore.endpoints;

import denis_grimaliuc.api.petstore.PetstoreBaseRequest;
import denis_grimaliuc.data.enums.Status;
import io.restassured.response.Response;

public class PetsEndpoint extends PetstoreBaseRequest {

    public static Response getPets() {
        return getPets(null);

    }

    public static Response getPets(String location) {
        return getPets(location, "");
    }

    public static Response getPet(String id, String location) {
        var request = given().pathParams("pet_id", id)
                .queryParam("location", location);

        var response = request
                .when()
                .get("/pets/{pet_id}");

        return handleResponse(response);
    }

    public static Response getPets(String location, Status status) {
        return getPets(location, status.toString());
    }

    public static Response getPets(String location, String status) {
        var request = given();

        if (location != null && !location.isEmpty()) {
            request.queryParam("location", location);
        }
        if (status != null && !status.isEmpty()) {
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

    public static Response deletePets(String location) {
        var request = given();

        if (location != null && !location.isEmpty()) {
            request.queryParam("location", location);
        }

        var response = request.when().delete("/pets");
        return handleResponse(response);
    }

    public static Response updatePet(String id, String location, String status, String name) {
        var response = given()
                .body("""
                        {
                        "location": "%s",
                        "name": "%s",
                        "status": "%s"
                        }
                        """.formatted(location, name, status))
                .pathParams("pet_id", id)
                .when()
                .patch("/pets/{pet_id}");

        return handleResponse(response);
    }
}
