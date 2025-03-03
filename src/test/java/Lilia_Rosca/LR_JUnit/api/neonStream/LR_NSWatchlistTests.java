package Lilia_Rosca.LR_JUnit.api.neonStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Lilia_Rosca.api.neonStream.LR_NSUserEndpoint.*;
import static Lilia_Rosca.api.neonStream.LR_NSWatchlistEndpoint.*;
import static example.actions.BaseActions.getRandomString;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.apache.logging.log4j.util.Base64Util.encode;
import static org.hamcrest.Matchers.*;

public class LR_NSWatchlistTests {
    @Test
    @DisplayName("Add to Watchlist a movie using Authorization in Header Test")
    public void addToWatchlistMovieHeaderTest() {
        String email = "test_lr_11@gmail.com";
        String password = "LR&123456test";
        String token = encode(email + ":" + password);
        // loginUser(token);
        var id = 912649;
        var mediaType = "movie";
        addToWatchlistHeader(token, id, mediaType)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body("id[0]", equalTo(id))
                .body("mediaType[0]", equalTo(mediaType))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/neonStream/addToWatchlistSchema.json"));
    }

    @Test
    @DisplayName("Add to Watchlist a tv using Authorization in body Test")
    public void addToWatchlistTvBodyTest() {
        String email = "test_lr_11@gmail.com";
        String password = "LR&123456test";
        // loginUser(email, password);
        var id = 74313;
        var mediaType = "tv";
        addToWatchlistBody(email, password, id, mediaType)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body("id[0]", equalTo(id))
                .body("mediaType[0]", equalTo(mediaType))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/neonStream/addToWatchlistSchema.json"));
    }

    @Test
    @DisplayName("Add to Watchlist a movie and a series Test")
    public void addToWatchlistMovieTvTest() {
        String email = "test_lr_11@gmail.com";
        String password = "LR&123456test";
        String token = encode(email + ":" + password);
        // loginUser(token);
        var id = 402431;
        var mediaType = "movie";
        addToWatchlistHeader(token, id, mediaType)
                .then().assertThat()
                .body("id[0]", equalTo(id))
                .body("mediaType[0]", equalTo(mediaType));
        var id2 = 73586;
        var mediaType2 = "tv";
        addToWatchlistHeader(token, id2, mediaType2)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body("id[0]", equalTo(id2))
                .body("mediaType[0]", equalTo(mediaType2))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/neonStream/addToWatchlistSchema.json"));
    }

    @Test
    @DisplayName("Fail to Add to Watchlist a movie missing email Test")
    public void addToWatchlistEmptyEmailTest() {
        addToWatchlistBody("", "LR&123456test", 1241982, "movie")
                .then().assertThat()
                .statusCode(403)
                .time(lessThan(2000L))
                .body("error", equalTo("Not Authorized"));
    }

    @Test
    @DisplayName("Fail to Add to Watchlist a movie invalid user Test")
    public void addToWatchlistInvalidUserTest() {
        addToWatchlistBody("test_lr_11@1gmail.com", "LR&123456test", 1241982, "movie")
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(2000L))
                .body("error.message", equalTo("INVALID_EMAIL"));
    }

    @Test
    @DisplayName("Fail to Add to Watchlist a movie invalid mediaType Test")
    public void addToWatchlistInvalidTypeTest() {
        addToWatchlistBody("test_lr_11@gmail.com", "LR&123456test", 1241982, "episode")
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(2000L))
                .body("error", equalTo("Invalid mediaType: episode. Must be 'tv' or 'movie'"));
    }

    @Test
    @DisplayName("Fail to Add to Watchlist a movie invalid id Test")
    public void addToWatchlistInvalidIdTest() {
        addToWatchlistBody("test_lr_11@gmail.com", "LR&123456test", 11111111, "movie")
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(2000L))
                .body("error", equalTo("The resource you requested could not be found."));
    }

    @Test
    @DisplayName("Add to Watchlist same item twice Test")
    public void addToWatchlistSameItemTwiceTest() {
        addToWatchlistBody("test_lr_11@gmail.com", "LR&123456test", 746036, "movie");
        addToWatchlistBody("test_lr_11@gmail.com", "LR&123456test", 746036, "movie")
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(3000L))
                .body("error", equalTo("Item already exists in watchlist"));
    }

    @Test
    @DisplayName("Add to Watchlist movie and series same id Test")
    public void addToWatchlistMovieTvSameIdTest() {
        var id = 138461;
        addToWatchlistBody("test_lr_11@gmail.com", "LR&123456test", id, "movie");
        addToWatchlistBody("test_lr_11@gmail.com", "LR&123456test", id, "tv")
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body("id[0]", equalTo(id))
                .body("id[1]", equalTo(id));
    }

    @Test
    @DisplayName("Get Watchlist NTest")
    public void getWatchlistTest() {
        String email = "test_lr_11@gmail.com";
        String password = "LR&123456test";
        String token = encode(email + ":" + password);
        getWatchlist(token)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body("size()", greaterThan(0))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/neonStream/addToWatchlistSchema.json"));
    }

    @Test
    @DisplayName("Get Watchlist New user Test")
    public void getWatchlistNewUserTest() {
        String email = ("lr_" + getRandomString(7).toLowerCase() + "@gmail.com");
        String password = "LR&123456test";
        String token = encode(email + ":" + password);
        createUser(token);

        getWatchlist(token)
                .then().assertThat()
                .statusCode(404)
                .time(lessThan(2000L))
                .body("error", equalTo("Watchlist collection doesn't exist for this user"));
        deleteUser(token);
    }
}
