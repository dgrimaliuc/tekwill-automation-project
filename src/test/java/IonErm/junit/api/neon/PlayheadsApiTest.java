package IonErm.junit.api.neon;

import example.api.petstore.NeonStreamBaseRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static IonErm.api.neon.PlayheadsEndpoint.*;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class PlayheadsApiTest extends NeonStreamBaseRequest {

    String email = "igovxn@gmail.com";
    String password = "&1aR61!xAAA";
    String token = "aWdvdnhuQGdtYWlsLmNvbTomMWFSNjEheEFBQQ==";

    @Test
    @DisplayName("Successfully add a movie playhead with a valid playheads value (within duration range) using email and password in the request body")
    public void addPlayheadsPositiveTest() {
        deletePlayheads(email, password);
        addEpisodePlayheads("1396", "episode", 20, 1, 1, email, password)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/EG_Schemes/neon/addPlayheadersSchema.json"));
    }

    @Test
    @DisplayName("Successfully add an episode playhead with season and episode details using Authorization header")
    public void addAuthHeaderPlayheadsPositiveTest() {
        deletePlayheads(email, password);
        addEpisodePlayheadsWithToken("246", "episode", 1430, 1, 2, token)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/EG_Schemes/neon/addPlayheadersSchema.json"));
    }

    @Test
    @DisplayName("Fail to add a playhead when email is missing in the request body")
    public void playheadsEmptyEmailTest() {
        addEpisodePlayheads("1396", "episode", 20, 1, 1, "", password)
                .then()
                .assertThat()
                .statusCode(403)
                .time(lessThan(5000L))
                .body("error", equalTo("Not Authorized"));
    }

    @Test
    @DisplayName("Fail to add a playhead when email is null in the request body")
    public void playheadsNullEmailTest() {
        addEpisodePlayheads("1396", "episode", 20, 1, 1, null, password)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error.message", equalTo("INVALID_EMAIL"));
    }

    @Test
    @DisplayName("Fail to add a playhead when password is null in the request body")
    public void playheadsNullPasswordTest() {
        deletePlayheads(email, password);
        addEpisodePlayheads("1396", "episode", 20, 1, 1, email, null)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error.message", equalTo("INVALID_LOGIN_CREDENTIALS"));
    }

    @Test
    @DisplayName("Fail to add a playhead when content ID, mediaType and playheads parameters are missing")
    public void playheadsNotParametersTest() {
        addEpisodePlayheads("", "", null, null, null, email, password)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Missing required fields: id, mediaType"));
    }

    @Test
    @DisplayName("Fail to add a playhead when mediaType is invalid")
    public void playheadsInvalidMediaTypeTest() {
        deletePlayheads(email, password);
        addEpisodePlayheads("1396", "tv", 1000, 1, 2, email, password)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Invalid mediaType: tv. Must be 'episode' or 'movie'"));
    }

    @Test
    @DisplayName("Fail to add a playhead when content ID is invalid")
    public void playheadsInvalidIdTest() {
        deletePlayheads(email, password);
        addEpisodePlayheads("----*", "episode", 1000, 1, 2, email, password)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Invalid id: The pre-requisite id is invalid or not found."));
    }

    @Test
    @DisplayName("Fail to add a playhead when playheads value is negative, or exceeds the content duration")
    public void playheadsNegativeValueTest() {
        deletePlayheads(email, password);
        addEpisodePlayheads("1396", "episode", -3500, 1, 2, email, password)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Invalid playhead, must be between 0 and 2940"));
    }

    @Test
    @DisplayName("Fail to add a playhead when season or episode number is missing for mediaType ‘episode’")
    public void playheadsSeasonEpisodeMissingTest() {
        deletePlayheads(email, password);
        addEpisodePlayheads("1396", "episode", 1500, null, null, email, password)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Missing required fields: season, episode"));
    }

    @Test
    @DisplayName("Fail to add a playhead when user does not exist")
    public void playheadsUserNotExistTest() {
        deletePlayheads(email, password);
        addEpisodePlayheads("1396", "episode", 1500, 1, 2, "qwerty@gmail.com", "QweRty12")
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error.message", equalTo("INVALID_LOGIN_CREDENTIALS"));
    }
}
