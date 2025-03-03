package IonErm.junit.api.neon;

import example.api.petstore.NeonStreamBaseRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static IonErm.api.neon.WatchlistEndpoint.*;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class WatchlistApiTest extends NeonStreamBaseRequest {

    String email = "igovxn@gmail.com";
    String password = "&1aR61!xAAA";
    String token = "aWdvdnhuQGdtYWlsLmNvbTomMWFSNjEheEFBQQ==";

    @Test
    @DisplayName("Successfully add a movie to the watchlist using valid email and password in the request body")
    public void addAMoviePositiveTest() {
        addInWatchlist("635389", "movie", email, password)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/EG_Schemes/neon/addWatchlistSchema.json"));
    }

    @Test
    @DisplayName("Successfully add a TV show to the watchlist using valid Authorization header with Base64-encoded email and password")
    public void addATvEncodedPositiveTest() {
        addWatchlistWithToken("1396", "tv", token)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/EG_Schemes/neon/addWatchlistSchema.json"));
    }

    @Test
    @DisplayName("Successfully add both movie and TV to the watchlist, validate that last added item is on the first position")
    public void addMovieAndTvTest() {
        deleteWatchlist(email, password);
        addInWatchlist("138461", "movie", email, password)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/EG_Schemes/neon/addWatchlistSchema.json"));
        addInWatchlist("1396", "tv", email, password)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/EG_Schemes/neon/addWatchlistSchema.json")); //?
    }


    @Test
    @DisplayName("Fail to add an item to the watchlist with missing email")
    public void addWatchlistEmptyEmailTest() {
        addInWatchlist("635389", "movie", null, password)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error.message", equalTo("INVALID_EMAIL"));
    }

    @Test
    @DisplayName("Fail to add an item to the watchlist with for an unexisting user")
    public void addWatchlistUnexistingUserTest() {
        addInWatchlist("635389", "movie", "iovxn@gmail.com", password)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error.message", equalTo("INVALID_LOGIN_CREDENTIALS"));
    }

    @Test
    @DisplayName("Fail to add an item to the watchlist with an invalid mediaType")
    public void addWatchlistInvalidMediaTypeTest() {
        addInWatchlist("635389", "episode", email, password)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Invalid mediaType: episode. Must be 'tv' or 'movie'"));
    }

    @Test
    @DisplayName("Fail to add an item to the watchlist with an unexisting item id")
    public void addWatchlistUnexistingIdTest() {
        addInWatchlist("-----&", "tv", email, password)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Invalid id: The pre-requisite id is invalid or not found."));
    }

    @Test
    @DisplayName("Fail to add an item to the watchlist with an unexisting item id")
    public void addWatchlistInvalidIdTest() {
        addInWatchlist("1231231231231212312", "tv", email, password)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("The resource you requested could not be found."));
    }

    @Test
    @DisplayName("Add Movie and TV with the same id to the watchlist (id: 138461)")
    public void addMovieAndTvSameIdTest() {
        addInWatchlist("138461", "movie", email, password)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/EG_Schemes/neon/addWatchlistSchema.json"));
        addInWatchlist("138461", "tv", email, password)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/EG_Schemes/neon/addWatchlistSchema.json"));
    }

    @Test
    @DisplayName("Add the same item twice to the watchlist")
    public void addSameIdTest() {
        addInWatchlist("138461", "tv", email, password)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/EG_Schemes/neon/addWatchlistSchema.json"));
        addInWatchlist("138461", "tv", email, password)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Item already exists in watchlist"));
    }

    @Test
    @DisplayName("Successfully retrieve a watchlist using valid email and password")
    public void getWatchlistTest() {
        addInWatchlist("635389", "movie", email, password);
        getWatchlist(email, password)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L));
    }

    @Test
    @DisplayName("Fail to retrieve a watchlist for a currently created user (with empty watchlist)")
    public void getEmptyWatchlistTest() {
        deleteWatchlist(email, password);
        getWatchlist(email, password)
                .then()
                .assertThat()
                .statusCode(404)
                .time(lessThan(5000L))
                .body("error", equalTo("Watchlist collection doesn't exist for this user"));
    }

    @Test
    @DisplayName("Successfully remove a movie from the watchlist using valid email and password in the request body")
    public void patchOneWatchlistItemTest() {
        addInWatchlist("635389", "movie", email, password);
        patchWatchlist("635389", "movie", email, password)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L));
    }

    @Test
    @DisplayName("Successfully remove a TV show from the watchlist using a valid Authorization header")
    public void patchTvEncodeWatchlistTest() {
        addWatchlistWithToken("138461", "tv", token);
        patchWatchlistWithToken("138461", "tv", token)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L));
    }

    @Test
    @DisplayName("Successfully remove an item when multiple items exist in the watchlist")
    public void patchOneItemFromListOfWatchlistTest() {
        addWatchlistWithToken("138461", "tv", token);
        addWatchlistWithToken("950396", "movie", token);
        patchWatchlistWithToken("138461", "tv", token)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L));
    }

    @Test
    @DisplayName("Fail to remove an item with missing email and password")
    public void patchWatchlistEmptyEmailAndPasswordTest() {
        patchWatchlist("635389", "movie", "", "")
                .then()
                .assertThat()
                .statusCode(403)
                .time(lessThan(5000L))
                .body("error", equalTo("Not Authorized"));
    }

    @Test
    @DisplayName("Fail to remove an item when the user does not exist in the system")
    public void patchWatchlistUserNotExistTest() {
        patchWatchlist("635389", "movie", "qwe@gmail.com", "qwerty")
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error.message", equalTo("INVALID_LOGIN_CREDENTIALS"));
    }

    @Test
    @DisplayName("Fail to remove an item when the “id” and “mediaType” are missing in the request body")
    public void patchWatchlistNoIdAndMediaTypeTest() {
        patchWatchlist("", "", email, password)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Missing required fields: id, mediaType"));
    }

    @Test
    @DisplayName("Fail to remove an item when the item is not in the user’s watchlist")
    public void patchWatchlistEmptyItemTest() {
        deleteWatchlist(email, password);
        patchWatchlist("635389", "movie", email, password)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Item doesn't exists in watchlist"));
    }

    @Test
    @DisplayName("Remove an movie with the id which is identical to an tv in the same watchlist (id: 138461)")
    public void patchWatchlistIdenticalIdTest() {
        addInWatchlist("138461", "movie", email, password);
        patchWatchlist("138461", "tv", email, password)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Item doesn't exists in watchlist"));
    }

    @Test
    @DisplayName("Successfully delete the entire watchlist using valid email and password in the request body")
    public void deleteWatchlistValidTest() {
        addInWatchlist("138461", "movie", email, password);
        deleteWatchlist(email, password)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body("message", equalTo("Watchlist of " + email + " deleted"));
    }

    @Test
    @DisplayName("Successfully delete the entire watchlist using a valid Authorization header")
    public void deleteWatchlistValidTokenTest() {
        addInWatchlist("138461", "movie", email, password);
        deleteWatchlistWithToken(token)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body("message", equalTo("Watchlist of " + email + " deleted"));
    }

    @Test
    @DisplayName("Fail to delete watchlist when email or password is missing in the request body")
    public void deleteWatchlistEmptyEmailAndPasswordTest() {
        deleteWatchlist("", "")
                .then()
                .assertThat()
                .statusCode(403)
                .time(lessThan(5000L))
                .body("error", equalTo("Not Authorized"));
    }

    @Test
    @DisplayName("Fail to delete watchlist when the Authorization header is missing or invalid")
    public void deleteWatchlistEmptyTokenTest() {
        deleteWatchlistWithToken("")
                .then()
                .assertThat()
                .statusCode(403)
                .time(lessThan(5000L))
                .body("error", equalTo("Invalid token"));
    }

    @Test
    @DisplayName("Fail to delete watchlist when the Authorization header is missing or invalid")
    public void deleteWatchlistEmptyHeaderTest() {
        deleteWatchlistEmptyToken(token)
                .then()
                .assertThat()
                .statusCode(403)
                .time(lessThan(5000L))
                .body("error", equalTo("Not Authorized"));
    }

    @Test
    @DisplayName("Fail to delete watchlist when the user does not exist")
    public void deleteWatchlistUserNotExistTest() {
        deleteWatchlist("qwe@gmail.com", "qwerty")
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error.message", equalTo("INVALID_LOGIN_CREDENTIALS"));
    }

}
