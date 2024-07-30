package denis_grimaliuc.api.endpoints;

import denis_grimaliuc.api.BaseRequest;
import denis_grimaliuc.data.enums.Status;
import helpers.Helpers;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class AdoptionsEndpoint extends BaseRequest {

    public static Response getAdoptions(Status status, String location) {
        Response resp = given()
                .queryParam("status", status.toString().toLowerCase())
                .queryParam("location", location)
                .when()
                .get("/adoptions");

        resp.then().assertThat().statusCode(200);
        return resp;
    }
    

    public static Response addAdoption(String location, String... petIds) {
        var req = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json");
        String pets = Arrays.stream(petIds)
                .map(Helpers::addQuotes)
                .collect(Collectors.joining(","));

        req.body(String.format("""
                {
                  "pets": [
                    %s
                  ],
                  "location": "%s",
                  "status": "available"
                }
                """, pets, location));
        Response resp = req.post("/adoptions");
        resp.then().statusCode(201);
        return resp;
    }

    public static Response updateStatus(Status status, String id) {
        Response resp = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .pathParam("id", id)
                .body(String.format("""
                        {
                          "status": "%s"
                        }
                        """, status.toString().toLowerCase()))
                .patch("/adoptions/{id}");

        resp.then().assertThat().statusCode(200);

        return resp;

    }
}

