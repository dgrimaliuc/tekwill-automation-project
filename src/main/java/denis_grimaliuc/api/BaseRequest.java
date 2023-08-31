package denis_grimaliuc.api;

import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;

public class BaseRequest {

    static {
        RestAssured.baseURI = "https://petstore-kafka.swagger.io/api";
    }

    @SafeVarargs
    public static <T> void assertResponse(T resp, Matcher<T>... matchers) {
        assertThat(resp, Matchers.allOf(matchers));
    }
}
