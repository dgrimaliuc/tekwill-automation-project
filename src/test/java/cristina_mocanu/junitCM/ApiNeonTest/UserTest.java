package cristina_mocanu.junitCM.ApiNeonTest;

import example.api.petstore.NeonStreamBaseRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static cristinamocanu.neonStreamApi.CMNSUserEndpoint.*;
import static example.actions.BaseActions.getRandomString;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.apache.logging.log4j.util.Base64Util.encode;
import static org.assertj.core.api.BDDAssertions.then;
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

    @Test
    @DisplayName("Successfully add a movie playhead using email and password in the request body")
    public void addMovieItemToPlayheadTest() {
        var newMail = email;
        var newPassword = password;
        createUserCM(newMail, newPassword);
        loginUser(newMail, newPassword);
        addItemInPlayheadsRequestBody(newMail, newPassword, "movie", "762509", 750)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(4000L))
                .body("id[0]", equalTo(762509))
                .body("playheads[0]", equalTo("750"));


    }


    @Test
    @DisplayName("Successfully add an episode playhead with season and episode details using Authorization header")
    public void addEpisodeItemToPlayheadTest() {
        var newMail = email;
        var newPassword = password;
        createUserCM(newMail, newPassword);
        loginUser(newMail, newPassword);
       addItemInPlayheadsHeader(newMail, newPassword, "episode", "4614", 200, "2","2" )
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(4000L));

    }



    @Test
    @DisplayName("Fail to add a playhead when email or password is missing in the request body")
    public void failToAddEmailPlayheadTest() {
        var newMail = email;
        var newPassword = password;
        createUserCM(newMail, newPassword);
        loginUser(newMail, newPassword);
        addItemInPlayheadsRequestBody("", newPassword, "movie", "762509", 750)
                .then()
                .assertThat()
                .statusCode(403)
                .time(lessThan(4000L))
                .body("error", equalTo("Not Authorized"));

    }

    @Test
    @DisplayName("Fail to add a playhead when content ID, mediaType and playheads parameters are missing")
    public void failToAddParametersTest() {
        var newMail = email;
        var newPassword = password;
        createUserCM(newMail, newPassword);
        loginUser(newMail, newPassword);
        addItemInPlayheadsRequestBody(newMail, newPassword, "", "", 750)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(4000L))
                .body("error", equalTo("Missing required fields: id, mediaType"));

}


    @Test
    @DisplayName(" Fail to add a playhead when mediaType is invalid (e.g., ‘tv’)")
    public void invalidMediaTypeTest() {
        var newMail = email;
        var newPassword = password;
        createUserCM(newMail, newPassword);
        loginUser(newMail, newPassword);
        addItemInPlayheadsRequestBody(newMail, newPassword, "tv", "762509", 750)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(4000L))
                .body("error", equalTo("Invalid mediaType: tv. Must be 'episode' or 'movie'"));

    }


    @Test
    @DisplayName("Fail to add a playhead when content ID is invalid")
    public void invalidContentIdTest() {
        var newMail = email;
        var newPassword = password;
        createUserCM(newMail, newPassword);
        loginUser(newMail, newPassword);
        addItemInPlayheadsRequestBody(newMail, newPassword, "movie", "76250985732652093", 750)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(4000L))
                .body("error", equalTo("The resource you requested could not be found."));
    }



    @Test
    @DisplayName("Fail to add a playhead when playheads value is negative, or exceeds the content duration")
    public void invalidPlayheadTest() {
        var newMail = email;
        var newPassword = password;
        createUserCM(newMail, newPassword);
        loginUser(newMail, newPassword);
        addItemInPlayheadsRequestBody(newMail, newPassword, "movie", "762509", 14750)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(4000L))
                .body("error", equalTo("Invalid playhead, must be between 0 and 7080"));
}



    @Test
    @DisplayName("Fail to add a playhead when season or episode number is missing for mediaType ‘episode'")
    public void missingSeasonPlayheadTest() {
        var newMail = email;
        var newPassword = password;
        createUserCM(newMail, newPassword);
        loginUser(newMail, newPassword);
        addItemInPlayheadsHeader(newMail, newPassword, "episode", "4614", 200, "","2" )
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(4000L))
                .body("error", equalTo("Missing required fields: season"));
}
    @Test
    @DisplayName("Fail to add a playhead when user does not exist")
    public void invalidUserPlayheadTest() {
        var newMail = email;
        var newPassword = password;

        loginUser(newMail, newPassword);
        addItemInPlayheadsRequestBody(newMail, newPassword, "movie", "762509", 14750)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(4000L))
                .body("error.message", equalTo("INVALID_LOGIN_CREDENTIALS"));


}


    @Test
    @DisplayName("Successfully delete a user and all its content using valid email and password in the request body")
    public void deleteUserTest() {
    createUserCM(email, password);
     deleteUserHeader(email, password)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L));


    }
    @Test
    @DisplayName("Fail to delete a user that does not exist")
    public void failTodDeleteUserTest() {
        createUserCM("emailnotexist@gmail.com", password);
        deleteUserHeader(email, password)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error.message",equalTo("INVALID_LOGIN_CREDENTIALS"));


}

    @Test
    @DisplayName("Fail to delete a user when the account is already deleted")
    public void failToDeleteAlreadyDeletedUserTest() {

        deleteUserHeader("emailfordelete1@gmail.com", "1235Aa@123")
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error.message",equalTo("INVALID_LOGIN_CREDENTIALS"));

}

    @Test
    @DisplayName(" Fail to delete a user if the request is missing authentication")
    public void missingAuthDeleteUserTest() {
        createUserCM(email, password);
        deleteUserHeader("", "")
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Missing required fields: email, password"));

    }}