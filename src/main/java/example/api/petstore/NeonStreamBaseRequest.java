package example.api.petstore;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class NeonStreamBaseRequest extends BaseRequest {

    static {
        RestAssured.baseURI = "https://user-collection.nodeapp.workers.dev";
    }


    public static RequestSpecification given() {
        System.out.println("--- Request: ");
        return RestAssured.given()
                .log().all();
    }


}
