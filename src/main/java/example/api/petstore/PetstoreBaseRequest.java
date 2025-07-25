package example.api.petstore;

import example.api.BaseRequest;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class PetstoreBaseRequest extends BaseRequest {

    static {
        RestAssured.baseURI = "https://pet-store.nodeapp.workers.dev/api";
    }


    public static RequestSpecification given() {
        System.out.println("--- Request: ");
        return RestAssured.given()
                .log().all(); // Log all request details
    }
}
