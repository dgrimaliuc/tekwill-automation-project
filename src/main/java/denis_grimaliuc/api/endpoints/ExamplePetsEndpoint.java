package denis_grimaliuc.api.endpoints;

import denis_grimaliuc.api.petstore.PetstoreBaseRequest;
import denis_grimaliuc.data.enums.Status;
import io.restassured.response.Response;

public class ExamplePetsEndpoint extends PetstoreBaseRequest {
    public static Response getPetsA() {
        return getPetsA(null, null);
    }

    public static Response getPetsA(Status status, String location) {
        var req = given()
                .header("Accept", "application/json");

        if (status != null) {
            req.queryParam("status", status.toString().toLowerCase());
        }
        if (location != null && !location.isEmpty()) {
            req.queryParam("location", location);
        }
        Response resp = req.when()
                .get("/pets");

        resp.then()
                .assertThat()
                .statusCode(200);
        return handleResponse(resp);
    }

    public static Response addPetA(String name, String location) {
        Response resp = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(String.format("""
                        {
                          "name": "%s",
                          "location": "%s",
                          "status": "available"
                        }
                        """, name, location)
                )
                .when()
                .post("/pets");

        resp.then()
                .assertThat()
                .statusCode(201);

        return handleResponse(resp);
    }

    public static Response updateStatusA(Status status, String id) {
        Response resp = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .pathParam("id", id)
                .body(String.format("""
                        {
                          "status": "%s"
                        }
                        """, status.toString().toLowerCase()))
                .when()
                .patch("/pets/{id}");

        resp.then().assertThat().statusCode(201);

        return handleResponse(resp);
    }
}
