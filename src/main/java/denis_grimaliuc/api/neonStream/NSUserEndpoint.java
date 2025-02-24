package denis_grimaliuc.api.neonStream;

import example.api.petstore.NeonStreamBaseRequest;
import io.restassured.response.Response;

import static org.apache.logging.log4j.util.Base64Util.encode;

public class NSUserEndpoint extends NeonStreamBaseRequest {

    public static Response createUser(String email, String password) {
        var request = given()
                .header("authorization", "Basic " + encode(email + ":" + password));
        var response = request.post("/user/create");
        handleResponse(response);
        return response;
    }

    public static Response createUser() {
        var request = given()
                .body("""
                        {
                        "email": "",
                        "password": ""
                        }
                                                
                        """);
        var response = request.post("/user/create");
        handleResponse(response);
        return response;
    }
}
