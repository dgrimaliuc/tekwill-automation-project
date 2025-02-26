package IonErm.api.neon;

import example.api.petstore.NeonStreamBaseRequest;
import io.restassured.response.Response;

import static org.apache.logging.log4j.util.Base64Util.encode;

public class UserEndpoint extends NeonStreamBaseRequest {

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

    public static Response loginUser(String email, String password) {
        var request = given()
                .header("authentication", "Basic " + encode(email + ":" + password))
                .body(String.format("""
                        {
                            "email": "%s",
                            "password": "%s"
                        }
                        """, email, password));
        var response = request.post("/user/login");
        handleResponse(response);
        return response;
    }

    public static Response deleteUser(String email, String password) {
        var request = given()
                .header("authentication", "Basic " + encode(email + ":" + password))
                .body(String.format("""
                        {
                            "email": "%s",
                            "password": "%s"
                        }
                        """, email, password));
        var response = request.delete("/user");
        handleResponse(response);
        return response;
    }
}
