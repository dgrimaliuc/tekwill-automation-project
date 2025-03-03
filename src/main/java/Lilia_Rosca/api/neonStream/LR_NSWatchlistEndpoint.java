package Lilia_Rosca.api.neonStream;

import example.api.petstore.NeonStreamBaseRequest;
import io.restassured.response.Response;

public class LR_NSWatchlistEndpoint extends NeonStreamBaseRequest {
// 28.02
    public static Response addToWatchlistHeader (String token, int id, String mediaType) {
        var request = given()
                .header("authorization", "Basic " +token)
                .body(String.format("""
                            {
                            "id":"%s",
                            "mediaType":"%s"
                            }
                            """, id, mediaType));
        var response = request.put("/watchlist");
        handleResponse(response);
        return response;
    }

    public static Response addToWatchlistBody (String email, String password, int id, String mediaType) {
        var request = given()
                .body(String.format("""
                            {
                            "email":"%s",
                            "password":"%s",
                            "id":"%s",
                            "mediaType":"%s"
                            }
                            """,email, password, id, mediaType));
        var response = request.put("/watchlist");
        handleResponse(response);
        return response;
    }
    public static Response getWatchlist (String token) {
        var request = given()
                .header("authorization", "Basic " + token);
        var response = request.get("/watchlist");
        handleResponse(response);
        return response;
    }

}
