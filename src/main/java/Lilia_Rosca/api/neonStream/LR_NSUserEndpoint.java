package Lilia_Rosca.api.neonStream;

import example.api.petstore.NeonStreamBaseRequest;
import io.restassured.response.Response;

import static org.apache.logging.log4j.util.Base64Util.encode;

public class LR_NSUserEndpoint extends NeonStreamBaseRequest {
// 24.02
    public static Response createUser (String email, String password) { // Denis
        var request = given()
                .header("authorization", "Basic " + encode(email + ":" + password));
        var response = request.post("/user/create");
        handleResponse(response);
        return response;
    }

    public static Response createUser() { // Denis
        var request = given()
                .body("""
                        {
                        "email":"",
                        "password":""
                        }
                        """);
        var response = request.post("/user/create");
        handleResponse(response);
        return response;
    }

    public static Response login (String email, String password) {
        var request = given()
                .body(String.format("""
                        {
                        "email":"%s",
                        "password":"%s"
                        }
                        """, email, password));
        var response = request.post("/user/login");
        handleResponse(response);
        return response;
    }
}
