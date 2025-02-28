package IonErm.junit.api.neon;

import example.api.petstore.NeonStreamBaseRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static IonErm.api.neon.UserEndpoint.*;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.Matchers.*;
import static example.actions.BaseActions.getRandomString;

public class UserApiTest extends NeonStreamBaseRequest {

    public String email = getRandomString(6).toLowerCase() + "@gmail.com";
    public String password = "&1aR61!xAAA";

    /*Create User Tests*/
    @Test
    @DisplayName("Create user test")
    public void createUserTest() {
        createUser(email, password)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body("email", equalTo(email))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/EG_Schemes/neon/createUserSchema.json"));
    }

    @Test
    @DisplayName("Create a new user using Authorization header with Base64-encoded")
    public void createEncodeUserTest() {
        createUser("aWdvdnhuQGdtYWlsLmNvbTomMWFSNjEheEFBQQ==")
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/EG_Schemes/neon/createUserSchema.json"));
    }

    @Test
    @DisplayName("Create user with empty email and password test")
    public void emptyEmailAndPasswordTest() {
        createUser("", "")
                .then()
                .assertThat().statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Missing required fields: email, password"));
    }

    @Test
    @DisplayName("Create user with invalid email format test")
    public void invalidEmailFormatTest() {
        String email = "hiho @gmail.com";
        createUser(email, password)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error.message", equalTo("INVALID_EMAIL"));
    }

    @Test
    @DisplayName("Create user with a empty password test")
    public void emptyPasswordTest() {
        createUser(email, "")
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error", equalTo("Missing required fields: password"));
    }

    @Test
    @DisplayName("Create user with a password not meeting the complexity requirements test")
    public void passwordNotMeetingTheComplexityRequirementsTest() {
        createUser(email, "&1aR61!xA&1aR61!xA&1aR61!xA&1aR61!xA")
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error.message", startsWith("PASSWORD_DOES_NOT_MEET_REQUIREMENTS"))
                .body("error.errors[0].message", containsString("Password may contain at most 25 characters"));
    }

    @Test
    @DisplayName("Create a new user with Authorization header containing invalid Base64 encoding")
    public void createInvalidEncodeUserTest() {
        createUser("wfihqweh++==")
                .then()
                .assertThat()
                .statusCode(403)
                .time(lessThan(5000L))
                .body("error", equalTo("Invalid token"));
    }

    @Test
    @DisplayName("Create a user with the same email twice")
    public void sameEmailTest() {
        createUser("theSame@gmail.com", password)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error.message", equalTo("EMAIL_EXISTS"));
    }


    /*Log In User Tests*/
    @Test
    @DisplayName("Successfully login in with valid email and password")
    public void positiveLoginTest() {
        loginUser("static@gmail.com", password)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body("email", equalTo("static@gmail.com"))
                .body("registered", equalTo(true))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/EG_Schemes/neon/loginUserSchema.json"));
    }

    @Test
    @DisplayName("Successfully log in using Authorization header with Base64-encoded email and password")
    public void positiveEncodedLoginTest() {
        loginUser("aWdvdnhuQGdtYWlsLmNvbTomMWFSNjEheEFBQQ==")
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body("registered", equalTo(true));
    }

    @Test
    @DisplayName("Login with incorrect email test")
    public void incorrectEmailLoginTest() {
        String incorrectEmail = "hihoagmail.com";
        loginUser(incorrectEmail, password)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(3000L))
                .body("error.message", equalTo("INVALID_EMAIL"));
    }

    @Test
    @DisplayName("Login with incorrect password test")
    public void incorrectPasswordLoginTest() {
        String wrongPass = "%&1aR61!xA";
        loginUser("static@gmail.com", wrongPass)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error.message", equalTo("INVALID_LOGIN_CREDENTIALS"));
    }

    @Test
    @DisplayName("Login  with incorrect encoded Basic token test")
    public void incorrectEncodeLoginTest() {
        loginUser("asdkj")
                .then()
                .assertThat()
                .statusCode(403)
                .time(lessThan(5000L))
                .body("error", equalTo("Invalid token"));
    }

    /*Delete User Tests*/
    @Test
    @DisplayName("Successfully delete a user and all its content using valid email and password in the request body test")
    public void deleteUserTest() {
        createUser(email, password);
        deleteUser(email, password)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body("message", equalTo("User '" + email + "' deleted"));
    }

    @Test
    @DisplayName("Successfully delete  delete a user and all its content using valid Authorization header with Base64-encoded test")
    public void deleteEncodedUserTest() {
        String token = "aWdvdnhuQGdtYWlsLmNvbTomMWFSNjEheEFBQQ==";
        deleteUser(token)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L)); //?
    }

    @Test
    @DisplayName("Successfully delete a user that does not exist test")
    public void deleteNotExistUserTest() {
        deleteUser(email, password)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error.message", equalTo("INVALID_LOGIN_CREDENTIALS"));
    }

    @Test
    @DisplayName("Successfully delete a user when the account is already deleted test")
    public void deleteAlreadyDeletedTest() {
        createUser(email, password);
        deleteUser(email, password);
        deleteUser(email, password)
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(5000L))
                .body("error.message", equalTo("INVALID_LOGIN_CREDENTIALS"));
    }
}
