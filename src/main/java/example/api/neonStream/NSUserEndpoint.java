package example.api.neonStream;

import io.restassured.response.Response;

public class NSUserEndpoint extends NeonStreamBaseRequest {

    public static Response createUser(String email, String password) {
        var response = given()
                .auth().preemptive().basic(email, password)
                .post("/user/create");

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
