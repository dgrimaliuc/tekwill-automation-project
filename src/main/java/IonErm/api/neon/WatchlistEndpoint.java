package IonErm.api.neon;

import example.api.petstore.NeonStreamBaseRequest;
import io.restassured.response.Response;

public class WatchlistEndpoint extends NeonStreamBaseRequest {

    public static Response addInWatchlist(String id, String mediaType, String email, String password) {
        var request = given()
                .body(String.format("""
                        {
                            "id": "%s",
                            "mediaType": "%s",
                            "email": "%s",
                            "password": "%s"
                        }
                        
                        """, id, mediaType, email, password));
        var response = request.put("/watchlist");
        handleResponse(response);
        return response;
    }

    public static Response addWatchlistWithToken(String id, String mediaType, String token) {
        var request = given()
                .header("Authorization", "Basic " + token)
                .body(String.format("""
                        {
                            "id": "%s",
                            "mediaType": "%s"
                        }
                        """, id, mediaType));
        var response = request.put("/watchlist");
        handleResponse(response);
        return response;
    }

    public static Response getWatchlist(String email, String password) {
        var request = given()
                .auth().preemptive().basic(email, password);
        var response = request.get("/watchlist");
        handleResponse(response);
        return response;
    }

    public static Response patchWatchlist(String id, String mediaType, String email, String password) {
        var request = given()
                .body(String.format("""
                        {
                            "id": "%s",
                            "mediaType": "%s",
                            "email": "%s",
                            "password": "%s"
                        }
                        
                        """, id, mediaType, email, password));
        var response = request.patch("/watchlist");
        handleResponse(response);
        return response;
    }

    public static Response patchWatchlistWithToken(String id, String mediaType, String token) {
        var request = given()
                .header("Authorization", "Basic " + token)
                .body(String.format("""
                        {
                            "id": "%s",
                            "mediaType": "%s"
                        }
                        """, id, mediaType));
        var response = request.patch("/watchlist");
        handleResponse(response);
        return response;
    }

    public static Response deleteWatchlist(String email, String password) {
        var request = given()
                .auth().preemptive().basic(email, password);
        var response = request.delete("/watchlist");
        handleResponse(response);
        return response;
    }

    public static Response deleteWatchlistWithToken(String token) {
        var request = given()
                .header("Authorization", "Basic " + token);
        var response = request.delete("/watchlist");
        handleResponse(response);
        return response;
    }

    public static Response deleteWatchlistEmptyToken(String token) {
        var request = given();
        var response = request.delete("/watchlist");
        handleResponse(response);
        return response;
    }
}
