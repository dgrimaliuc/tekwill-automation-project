package Lilia_Rosca.api.neonStream;

import example.api.petstore.NeonStreamBaseRequest;
import io.restassured.response.Response;

import static org.apache.logging.log4j.util.Base64Util.encode;

public class LR_NSUserEndpoint extends NeonStreamBaseRequest {
// 24-26.02
    public static Response createUser(String email, String password) {
        var request = given()
                .body(String.format("""
                        {
                        "email":"%s",
                        "password":"%s"
                        }
                        """, email, password));
        var response = request.post("/user/create");
        handleResponse(response);
        return response;
    }
/*    public static Response createUserAuth (String email, String password) {
        var request = given()
                .auth().basic(email, password); //.header("authorization", "Basic " + encode(email + ":" + password));
        var response = request.post("/user/create");
        handleResponse(response);
        return response;
    }*/

    public static Response loginUser (String email, String password) {
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

    // method for log in with Authorization header with Base64-encoded email and password

    public static Response deleteUser (String email, String password) {
        var request = given()
                .body(String.format("""
                        {
                        "email":"%s",
                        "password":"%s"
                        }
                        """, email, password));
        var response = request.delete("/user");
        handleResponse(response);
        return response;
    }

    // method for delete a user and all its content using valid Authorization header with Base64-encoded email and password
}
