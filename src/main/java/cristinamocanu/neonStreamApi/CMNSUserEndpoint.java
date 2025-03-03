package cristinamocanu.neonStreamApi;

import example.api.petstore.NeonStreamBaseRequest;
import io.restassured.response.Response;

import static org.apache.logging.log4j.util.Base64Util.encode;

public class CMNSUserEndpoint extends NeonStreamBaseRequest {


    public static Response createUserCM(String email, String password) {
        var request = given()
                .header("authorization", "Basic " + encode(email + ":" + password));
        var response = request.post("/user/create");
        handleResponse(response);
        return response;
    }

    public static Response createUserCM() {
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


    public static Response loginUser (String email, String password) {

        var request = given()
                .header("authorization", "Basic " + encode(email + ":" + password))
                .body(String.format(
                        """
                                {
                                "email" : "%s",
                                "password" : "%s"
                                }
                                """, email, password));
        var response = request.post("/user/login");
        handleResponse(response);
        return response;
    }



    public static Response deleteUser(String email, String password){
        var request = given()
                .header("authorization", "basic" + encode(email + ":" + password))
                .body(String.format("""
                         "email" : "%s",
                         "password" : "%s"
                    
                        """,email, password));

        var response = request.delete("/user");
        handleResponse(response);
        return response;
    }

public static Response addItemToWatchlistHeader(String email, String password, String mediaType, String contentId) {
    var request = given()
            .header("Authorization", "Basic " + encode(email + ":" + password))
            .body(String.format(
                    """
                    {
                    "mediaType": "%s",
                    "id": "%s"
                    }
                    """, mediaType, contentId));

    var response = request.put("/watchlist");
    handleResponse(response);

    return response;

    }
    public static Response addItemToWatchlistRequestBody (String email, String password, String mediaType, String contentId) {
        var request = given()
                .body(String.format(
                        """
                        { 
                        "email": "%s",
                        "password": "%s",
                        "mediaType": "%s",
                        "id": "%s"
                        }
                        
                        """, email, password, mediaType, contentId));

        var response = request.put("/watchlist");
        handleResponse(response);
        return response;
}
    public static Response getWatchlist(String email, String password) {

        var request = given()
                .header("Authorization", "Basic " + encode(email + ":" + password));

        var response = request.get("/watchlist");
        handleResponse(response);

        return response;



    }

    public static Response removeItemToWatchlistRequestBody (String email, String password, String mediaType, String contentId) {
        var request = given()
                .body(String.format(
                        """
                        { 
                        "email": "%s",
                        "password": "%s",
                        "mediaType": "%s",
                        "id": "%s"
                        }
                        
                        """, email, password, mediaType, contentId));

        var response = request.patch("/watchlist");
        handleResponse(response);
        return response;
    }



    public static Response removeItemToWatchlistHeader(String email, String password, String mediaType, String contentId) {
        var request = given()
                .header("Authorization", "Basic " + encode(email + ":" + password))
                .body(String.format(
                        """
                        {
                        "mediaType": "%s",
                        "id": "%s"
                        }
                        """, mediaType, contentId));

        var response = request.patch("/watchlist");
        handleResponse(response);

        return response;

    }
    public static Response deleteItemToWatchlistHeader(String email, String password) {
        var request = given()
                .header("Authorization", "Basic " + encode(email + ":" + password));


        var response = request.delete("/watchlist");
        handleResponse(response);

        return response;
}

    public static Response deleteItemToWatchlistRequestBody (String email, String password) {
        var request = given()
                .body(String.format(
                        """
                        { 
                        "email": "%s",
                        "password": "%s"
                        }
                 
                        
                        """, email, password));

        var response = request.delete("/watchlist");
        handleResponse(response);
        return response;
    }}




