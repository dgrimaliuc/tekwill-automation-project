package IonErm.api.neon;

import example.api.petstore.NeonStreamBaseRequest;
import io.restassured.response.Response;

public class UserEndpoint extends NeonStreamBaseRequest {

    public static Response createUser(String token) {
        var request = given()
                .header("Authorization", "Basic " + token);
        var response = request.post("/user/create");
        handleResponse(response);
        return response;
    }

    public static Response createUser(String email, String password) {
        var request = given()
                .body(String.format("""
                        {
                        "email": "%s",
                        "password": "%s"
                        }
                        
                        """, email, password));
        var response = request.post("/user/create");
        handleResponse(response);
        return response;
    }

    public static Response loginUser(String token) {
        var request = given()
                .header("Authorization", "Basic " + token);
        var response = request.post("/user/login");
        handleResponse(response);
        return response;
    }

    public static Response loginUser(String email, String password) {
        var request = given()
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

    public static Response deleteUser(String token) {
        var request = given()
                .header("Authorization", "Basic " + token);
        var response = request.delete("/user");
        handleResponse(response);
        return response;
    }
}
