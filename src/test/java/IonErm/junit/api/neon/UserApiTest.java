package IonErm.junit.api.neon;

import example.api.petstore.NeonStreamBaseRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static IonErm.api.neon.UserEndpoint.*;
import static IonErm.utility.RandomGenerator.generateRandomEmail;
import static IonErm.utility.RandomGenerator.generateRandomPassword;
import static org.hamcrest.Matchers.*;

public class UserApiTest extends NeonStreamBaseRequest {

    public String email = generateRandomEmail();
    public String password = generateRandomPassword();

    /*Create User Tests*/
    @Test
    @DisplayName("Create user test")
    public void createUserTest() {
        createUser(email, password)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("email", equalTo(email));
    }

    @Test
    @DisplayName("Create user with empty email and password test")
    public void emptyEmailAndPasswordTest() {
        createUser()
                .then()
                .assertThat().statusCode(400)
                .time(lessThan(3000L))
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
                .time(lessThan(3000L))
                .body("error.message", equalTo("INVALID_EMAIL"));
    }

    @Test
    @DisplayName("Create user with a empty password test")
    public void emptyPasswordTest() {
        createUser(email, "")
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(3000L))
                .body("error", equalTo("Missing required fields: password"));
    }

    @Test
    @DisplayName("Create user with a password not meeting the complexity requirements test")
    public void passwordNotMeetingTheComplexityRequirementsTest() {
        createUser(email, "&1aR61!xA&1aR61!xA&1aR61!xA&1aR61!xA")
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(3000L))
                .body("error.message", startsWith("PASSWORD_DOES_NOT_MEET_REQUIREMENTS"))
                .body("error.errors[0].message", containsString("Password may contain at most 25 characters"));
    }

    @Test
    @DisplayName("Create a user with the same email twice")
    public void sameEmailTest() {
        createUser("hiho@gmail.com", "&1aR61!xA")
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(3000L))
                .body("error.message", equalTo("EMAIL_EXISTS"));
    }

    /*Log In User Tests*/
    @Test
    @DisplayName("Successfully login in with valid email and password")
    public void positiveLoginTest() {
        loginUser("hiho@gmail.com", "&1aR61!xA")
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("email", equalTo("hiho@gmail.com"))
                .body("registered", equalTo(true));
    }

    @Test
    @DisplayName("Successfully log in using Authorization header with Base64-encoded email and password")
    public void positiveEncodedLoginTest() {
        loginUser("aGlob0BnbWFpbC5jb206JjFhUjYxIXhB")
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("email", equalTo("hiho@gmail.com"))
                .body("registered", equalTo(true));
    }

    @Test
    @DisplayName("Login with incorrect email test")
    public void incorrectEmailLoginTest() {
        loginUser("hihoagmail.com", "&1aR61!xA")
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(3000L))
                .body("error.message", equalTo("INVALID_EMAIL"));
    }

    @Test
    @DisplayName("Login with incorrect password test")
    public void incorrectPasswordLoginTest() {
        loginUser("hiho@gmail.com", "%&1aR61!xA")
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(3000L))
                .body("error.message", equalTo("INVALID_LOGIN_CREDENTIALS"));
    }

    /*Delete User Tests*/
    @Test
    @DisplayName("Successfully delete a user and all its content using valid email and password in the request body")
    public void deleteUserTest() {
        deleteUser("hiho@gmail.com", "&1aR61!xA")
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body("message", equalTo("User 'hiho@gmail.com' deleted"));
    }
}
