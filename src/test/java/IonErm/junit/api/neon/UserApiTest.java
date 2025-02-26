package IonErm.junit.api.neon;

import example.api.petstore.NeonStreamBaseRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static IonErm.api.neon.UserEndpoint.*;
import static IonErm.utility.RandomGenerator.generateRandomEmail;
import static IonErm.utility.RandomGenerator.generateRandomPassword;
import static org.apache.logging.log4j.util.Base64Util.encode;
import static org.hamcrest.Matchers.*;

public class UserApiTest extends NeonStreamBaseRequest {

    public String email = generateRandomEmail();
    public String password = generateRandomPassword();

    /*
    Random Email: pvjusq@gmail.com
    Random Password: aA%$Y!FH1MO1!gm  <-encoded

    Random Email: mmjlqlvot@gmail.com
    Random Password: ^a8DvFrn*EJ1ArF!aoU^xr

    Random Email: xdppui@gmail.com
    Random Password: a1@m!tVAO

    Random Email: hcyfekko@gmail.com
    Random Password: aEL5bmDr8!@Q1XoXAe9%M0ft#

    Random Email: sztkkuv@gmail.com
    Random Password: AYwBe&V4!a1
     */

    /*Create User Tests*/
    @Test
    @DisplayName("Create random user test")
    public void createRandomUserTest() {
        createUser(email, password)
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(3000L));
    }

    @Test
    @DisplayName("Create encode user test")
    public void createEncodeUserTest() {
        createUser()
                .header(encode("cHZqdXNxQGdtYWlsLmNvbTphQSUkWSFGSDFNTzEhZ20="));

    }

    @Test
    @DisplayName("Create user test")
    public void createUserTest() {
        createUser("hiho@gmail.com", "&1aR61!xA")
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("email", equalTo("hiho@gmail.com"));
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
        createUser("hihoj @gmail.com", "&1aR61!xA")
                .then()
                .assertThat()
                .statusCode(400)
                .time(lessThan(3000L))
                .body("error.message", equalTo("INVALID_EMAIL"));
    }

    @Test
    @DisplayName("Create user with a empty password test")
    public void emptyPasswordTest() {
        createUser("hiho@gmail.com", "")
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(3000L))
                .body("error", equalTo("Missing required fields: password"));
    }

    @Test
    @DisplayName("Create user with a password not meeting the complexity requirements test")
    public void passwordNotMeetingTheComplexityRequirementsTest() {
        createUser("hiho@gmail.com", "&1aR61!xA&1aR61!xA&1aR61!xA&1aR61!xA")
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(3000L))
                .body("error.message", startsWith("PASSWORD_DOES_NOT_MEET_REQUIREMENTS"))
                .body("error.errors[0].message", containsString("Password may contain at most 25 characters"));
    }

    @Test
    @DisplayName("Create user with Authorization header containing invalid Base64 encoding test")
    public void invalidEncodingTest() {

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
    public void positiveEncodeLoginTest() {

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

    @Test
    @DisplayName("Login with incorrect encoded Basic token test")
    public void incorrectEncodetTokenLoginTest() {

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

    @Test
    @DisplayName("Successfully delete a user and all its content using valid Authorization header with Base64-encoded email and password")
    public void deleteEncodedUserTest() {
//        String email = "mmjlqlvot@gmail.com";
//        String password = "^a8DvFrn*EJ1ArF!aoU^xr";
//        createUser(email, password);
//        deleteUser(email, password);
//
    }
}
