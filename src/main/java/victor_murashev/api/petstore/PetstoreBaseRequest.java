package victor_murashev.api.petstore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;

public class PetstoreBaseRequest {

    static {
        RestAssured.baseURI = "https://pet-store.nodeapp.workers.dev/api";
    }

    @SafeVarargs
    public static <T> void assertResponse(T resp, Matcher<T>... matchers) {
        assertThat(resp, Matchers.allOf(matchers));
    }

    public static RequestSpecification given() {
        System.out.println("--- Request: ");
        return RestAssured.given()
                .log().all(); // Log all request details
    }

    protected static Response handleResponse(Response response) {
        System.out.println("--- Response: ");
        return response
                .then()
                .log().all()  // Log all response details
                .extract()
                .response();
    }
}
