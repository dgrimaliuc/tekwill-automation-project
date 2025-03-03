package cristina_mocanu.junitCM.ApiNeonTest;

import example.api.petstore.NeonStreamBaseRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static cristinamocanu.neonStreamApi.CMNSUserEndpoint.*;
import static example.actions.BaseActions.getRandomString;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.apache.logging.log4j.util.Base64Util.encode;
import static org.hamcrest.Matchers.*;

public class UserTest extends NeonStreamBaseRequest {
    public String email = getRandomString(5).toLowerCase() + "@gmail.com";
    public String password = "Cm1234$$";

    @Test
    public void createUserTest() {
        createUserCM(email, password)
                .then()
                .assertThat()
                .time(lessThan(2000L))
                .statusCode(200)
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/CM_schema/createUserSchema.json"));
    }

    @Test
    @DisplayName("Create a user with the same email twice")
    public void createUserTwiceTest() {
        createUserCM("cristinam2@gmail.com", "1235Aa@123")
                .then()
                .assertThat()
                .time(lessThan(2000L))
                .statusCode(400)
                .body("error.message", equalTo("EMAIL_EXISTS"));
    }


    @Test
    public void createUserEncodeTest() {
        createUserCM()
                .header(encode("TGFsYTNAZ21haWwuY29tOjEyM0JiQDEyMw=="));
    }


    @Test
    @DisplayName("Create an user with empty password test")
    public void emptyPasswordTest() {
        createUserCM("cristina3@gmail.com", "")
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(2000L))
                .body("error", equalTo("Missing required fields: password"));
    }

    @Test
    @DisplayName("Create an user with an invalid email format")
    public void invalidEmailTest() {
        createUserCM("cris gmail.com", "AAa12345$")
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(2000L))
                .body("error.message", equalTo("INVALID_EMAIL"));
    }

    @Test
    @DisplayName("Create an user with a password not meeting the complexity requirements")
    public void longPasswordTest() {
        createUserCM("cristina34@gmail.com", "Parolaaceastaestefoartelunga123456789@$%")
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(2000L))
                .body("error.message", equalTo("PASSWORD_DOES_NOT_MEET_REQUIREMENTS : Missing password requirements: [Password may contain at most 25 characters]"));
    }


    @Test
    @DisplayName("Successfully login with valid email and password")
    public void loginTest() {
        loginUser("emaillogin2@gmail.com", "1235Aa@123")
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body("email", equalTo("emaillogin2@gmail.com"))
                .body("registered", equalTo(true));
    }

    @Test
    @DisplayName("Successfully log in with Authorization header")
    public void encodedLoginTest() {
        loginUser("emaillogin2@gmail.com", "1235Aa@123")
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body("email", equalTo("emaillogin2@gmail.com"))
                .body("registered", equalTo(true));
    }

    @Test
    @DisplayName("Login with incorrect email test")
    public void incorrectEmailLoginTest() {
        loginUser("emaillogin2jmail.com", "123Aa@123")
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(2000L))
                .body("error.message", equalTo("INVALID_EMAIL"));
    }


    @Test
    @DisplayName("Login with incorrect password test")
    public void incorrectPasswordLoginTest() {
        loginUser("emaillogin2@gmail.com", "123Aa@123")
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(2000L))
                .body("error.message", equalTo("INVALID_LOGIN_CREDENTIALS"));
    }


    @Test
    @DisplayName("Successfully add movie item to watchlist")
    public void addMovieItemToWatchlistTest() {
        var newMail = email;
        var newPassword = password;
        createUserCM(newMail, newPassword);
        loginUser(newMail, newPassword);
        addItemToWatchlistRequestBody(newMail, newPassword, "movie", "1126166")
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(4000L))
                .body("id[0]", equalTo(1126166))
                .body("mediaType[0]", equalTo("movie"));
    }

    @Test
    @DisplayName("Successfully add TV item to watchlist")
    public void addTVItemToWatchlistTest() {
        var newMail = email;
        var newPassword = password;
        createUserCM(newMail, newPassword);
        loginUser(newMail, newPassword);
        addItemToWatchlistHeader(newMail, newPassword, "tv", "4385")
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(4000L))
                .body("id[0]", equalTo(4385))
                .body("mediaType[0]", equalTo("tv"));
    }


    @Test
    @DisplayName("Successfully add Movie and TV item to watchlist")
    public void addTVAndMovieItemToWatchlistTest() {
        var newMail = email;
        var newPassword = password;
        createUserCM(newMail, newPassword);
        loginUser(newMail, newPassword);
        addItemToWatchlistHeader(newMail, newPassword, "tv", "4385");
        addItemToWatchlistHeader(newMail, newPassword, "movie", "762509")
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(4000L))
                .body("id[0]", equalTo(762509))
                .body("mediaType[0]", equalTo("movie"));
    }


    @Test
    @DisplayName("Successfully get watchlist")
    public void getWatchlistTest() {
        var newMail = email;
        var newPassword = password;
        createUserCM(newMail, newPassword);
        loginUser(newMail, newPassword);
        addItemToWatchlistHeader(newMail, newPassword, "movie", "1126166");


        getWatchlist(newMail, newPassword)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(4000L))
                .body("id[0]", equalTo(1126166))
                .body("mediaType[0]", equalTo("movie"));
    }

    @Test
    @DisplayName("Fail to retrieve a watchlist for a currently created user")
    public void getEmptyWatchlistForNewUserTest() {
        var newMail = email;
        var newPassword = password;
        createUserCM(newMail, newPassword);
        loginUser(newMail, newPassword);


        getWatchlist(newMail, newPassword)
                .then()
                .assertThat()
                .statusCode(404)
                .time(lessThan(4000L))
                .body("error", equalTo("Watchlist collection doesn't exist for this user"));

    }


    @Test
    @DisplayName("Remove Movie from Watchlist")
    public void removeItemFromWatchlistTest() {

        var newMail = email;
        var newPassword = password;
        createUserCM(newMail, newPassword);
        loginUser(newMail, newPassword);
        addItemToWatchlistHeader(newMail, newPassword, "movie", "1126166");


        removeItemToWatchlistHeader(newMail, newPassword, "movie", "1126166")
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body(equalTo("[]"));


    }

    @Test
    @DisplayName("Remove TV from Watchlist")
    public void removeItemFromWatchlistTestBody() {

        var newMail = email;
        var newPassword = password;
        createUserCM(newMail, newPassword);
        loginUser(newMail, newPassword);
        addItemToWatchlistHeader(newMail, newPassword, "tv", "3091");


        removeItemToWatchlistRequestBody(newMail, newPassword, "tv", "3091")
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body(equalTo("[]"));


    }


    @Test
    @DisplayName("Remove Multiple items from Watchlist")
    public void removeMultipleItemFromWatchlistTest() {

        var newMail = email;
        var newPassword = password;
        createUserCM(newMail, newPassword);
        loginUser(newMail, newPassword);
        addItemToWatchlistHeader(newMail, newPassword, "movie", "1126166");
        addItemToWatchlistHeader(newMail, newPassword, "tv", "2734");
        addItemToWatchlistHeader(newMail, newPassword, "tv", "108978");

        removeItemToWatchlistHeader(newMail, newPassword, "movie", "1126166")
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body("id[1]", equalTo(2734))
                .body("id[0]", equalTo(108978));


    }


    @Test
    @DisplayName("Delete Multiple items from Watchlist")
    public void deleteMultipleItemFromWatchlistTest() {

        var newMail = email;
        var newPassword = password;
        createUserCM(newMail, newPassword);
        loginUser(newMail, newPassword);
        addItemToWatchlistHeader(newMail, newPassword, "movie", "1126166");
        addItemToWatchlistHeader(newMail, newPassword, "tv", "2734");
        addItemToWatchlistHeader(newMail, newPassword, "tv", "108978");


        deleteItemToWatchlistRequestBody(newMail, newPassword)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body("message", equalTo("Watchlist of " + newMail + " deleted"));

    }



    @Test
    @DisplayName("Delete Multiple items from Watchlist with Header")
    public void deleteMultipleItemFromWatchlistTestHeader() {

        var newMail = email;
        var newPassword = password;
        createUserCM(newMail, newPassword);
        loginUser(newMail, newPassword);
        addItemToWatchlistHeader(newMail, newPassword, "movie", "1126166");
        addItemToWatchlistHeader(newMail, newPassword, "tv", "2734");
        addItemToWatchlistHeader(newMail, newPassword, "tv", "108978");


        deleteItemToWatchlistHeader(newMail, newPassword)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body("message", equalTo("Watchlist of " + newMail + " deleted"));

    }



    @Test
    @DisplayName("Fail to delete items from Watchlist")
    public void failDeleteItemFromWatchlistTest() {

        var newMail = email;
        var newPassword = password;
        createUserCM(newMail, newPassword);
        loginUser(newMail, newPassword);
        addItemToWatchlistHeader(newMail, newPassword, "movie", "1126166");
        addItemToWatchlistHeader(newMail, newPassword, "tv", "2734");
        addItemToWatchlistHeader(newMail, newPassword, "tv", "108978");


        deleteItemToWatchlistRequestBody(newMail, "")
                .then()
                .assertThat()
                .statusCode(403)
                .time(lessThan(2000L))
                .body("error", equalTo("Not Authorized"));

    }


    @Test
    @DisplayName("Fail to delete items from an nonexistent user")
    public void failToDeleteItemsFromNonexistentUser() {
        deleteItemToWatchlistRequestBody(email, password)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(2000L))
                .body("error.message", equalTo("INVALID_LOGIN_CREDENTIALS"));

    }
}
