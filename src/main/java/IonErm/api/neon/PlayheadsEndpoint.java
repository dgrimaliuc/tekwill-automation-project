package IonErm.api.neon;

import example.api.petstore.NeonStreamBaseRequest;
import io.restassured.response.Response;

public class PlayheadsEndpoint extends NeonStreamBaseRequest {

    public static Response addPlayheads(String id, String mediaType, Integer playheads, Integer season, Integer episode, String email, String password) {
        var request = given()
                .body(String.format("""
                        {
                            "id": "%s",
                            "mediaType": "%s",
                            "playheads": "%d",
                            "season": %d,
                            "episode": %d,
                            "email": "%s",
                            "password": "%s"
                        }
                        """, id, mediaType, playheads, season, episode, email, password));
        var response = request.put("/playheads");
        handleResponse(response);
        return response;
    }

    public static Response addEpisodePlayheadsWithToken(String id, String mediaType, int playheads, int season, int episode, String token) {
        var request = given()
                .header("Authorization", "Basic " + token)
                .body(String.format("""
                        {
                            "id": "%s",
                            "mediaType": "%s",
                            "playheads": "%d",
                            "season": %d,
                            "episode": %d
                        }
                        """, id, mediaType, playheads, season, episode));
        var response = request.put("/playheads");
        handleResponse(response);
        return response;
    }

    public static Response deletePlayheads(String email, String password) {
        var request = given()
                .auth().preemptive().basic(email, password);
        var response = request.delete("/playheads");
        handleResponse(response);
        return response;
    }
}
