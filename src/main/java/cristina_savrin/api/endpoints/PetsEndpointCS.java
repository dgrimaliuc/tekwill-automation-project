package cristina_savrin.api.endpoints;

import cristina_savrin.api.BaseRequestCS;
import cristina_savrin.data.enums.StatusCS;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PetsEndpointCS extends BaseRequestCS {

    public static Response getPets() {
        return getPets(null, null);
    }

    public static Response getPets(StatusCS status, String location) {
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
        return resp;
    }

    public static Response addPet(String name, String location) {
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
        return resp;
    }

    public static Response updateStatus(StatusCS status, String id) {
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
        return resp;
    }
}