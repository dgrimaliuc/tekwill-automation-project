package example.api.petstore;

import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;

public class BaseRequest {


    @SafeVarargs
    public static <T> void assertResponse(T resp, Matcher<T>... matchers) {
        assertThat(resp, Matchers.allOf(matchers));
    }


    public static Response handleResponse(Response response) {
        System.out.println("--- Response: ");
        return response
                .then()
                .log().all()  // Log all response details
                .extract()
                .response();
    }
}
