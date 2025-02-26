package Lilia_Rosca.LR_JUnit.api.neonStream;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Lilia_Rosca.api.neonStream.LR_NSUserEndpoint.*;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.hamcrest.Matchers.*;

public class LR_neonStreamUsersTests {
// 24-26.02
    @Test // create a user valid email and password (meeting all complexity requirements) providing them in body
    @DisplayName("Create user using body test")
    public void createUserUsingBodyTest() {
        String email = "test_lr_111@gmail.com"; // (RandomStringUtils.randomAlphanumeric(7) + "@gmail.com");
        String password = "LR&123456test";
        createUser(email, password)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body("email", equalTo(email))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/neonStream/createUserSchema.json"));
               // at least a number, uppercase letter, lowercase letter, special character
    }

    @Test
    @DisplayName("Create user with invalid email test")
    public void createUserWithInvalidEmailTest() {
        String email = "test_lr_11@ gmail.com"; // containing space
        String password = "LR&123456test";
        createUser(email, password)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(2000L))
                .body("error.message", equalTo("INVALID_EMAIL"));
    }

    @Test
    @DisplayName("Create user with empty password test")
    public void createUserWithEmptyPasswordTest() {
        String email = "test_lr_111@gmail.com"; // containing space
        String password = "";
        createUser(email, password)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(2000L))
                .body("error", equalTo("Missing required fields: password"));
    }

    @Test // verify that all errors are returned in response
    @DisplayName("Create user with wrong password test")
    public void createUserWithWrongPasswordTest() {
        String email = "test_lr_111@gmail.com";
        String password = "111"; // only numbers, too short
        createUser(email, password)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(2000L))
                .body("error.message", equalTo("PASSWORD_DOES_NOT_MEET_REQUIREMENTS : Missing password requirements: [Password must contain at least 6 characters, Password must contain a lower case character, Password must contain an upper case character, Password must contain a non-alphanumeric character]"));
    }
    @Test
    @DisplayName("Create user with wrong password 2 test")
    public void createUserWithWrongPassword2Test() {
        String email = "test_lr_111@gmail.com";
        String password = "Aaaaaaaaaaaaaaaaaaaaaa%aaa"; // no numbers, too long
        createUser(email, password)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(2000L))
                .body("error.message", equalTo("PASSWORD_DOES_NOT_MEET_REQUIREMENTS : Missing password requirements: [Password may contain at most 25 characters, Password must contain a numeric character]"));
    }

    @Test
    @DisplayName("Create user with same email test")
    public void createUserWithSameEmailTest() {
        String email = "test_lr_111@gmail.com";
        String password = "LR&123456test";
        createUser(email, password)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(2000L))
                .body("error.message", equalTo("EMAIL_EXISTS"));
    }

    @Test //using Authorization header with Base64-encoded email and password (meeting all complexity requirements)
    @DisplayName("Create user using Basic auth test")
    public void createUserUsingBasicAuthTest() {
    }

    // Fail to create a new user with Authorization header containing invalid Base64 encoding
    //  atob ('string')

    @Test
    @DisplayName("Successfully Login Test")
    public void successfullyLoginTest(){
        String email = "test_lr_111@gmail.com";
        String password = "LR&123456test";
        loginUser(email, password)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body("email", equalTo(email))
                // schema - to create ??
                .body("registered", equalTo(true));
    }
    @Test
    @DisplayName("Login with incorrect email Test")
    public void loginWithIncorrectEmailTest(){
        String email = "test_lr_11@ gmail.com"; // + space
        String password = "LR&123456test";
        loginUser(email, password)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(2000L))
                .body("error.message", equalTo("INVALID_EMAIL"));
    }

    @Test
    @DisplayName("Login with incorrect password Test")
    public void loginWithIncorrectPasswordTest(){
        String email = "test_lr_111@gmail.com";
        String password = "R&123456test";
        loginUser(email, password)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(2000L))
                .body("error.message", equalTo("INVALID_LOGIN_CREDENTIALS"));
    }

    // Successfully log in using Authorization header with Base64-encoded email and password
    // Fail to log in with incorrect encoded Basic token


    @Test
    @DisplayName("Delete User Test")
    public void deleteUserTest(){
        String email = "test_lr_222@gmail.com";
        String password = "LR&123456test";
        createUser(email, password);
        deleteUser(email, password)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("message", equalTo("User '" + email + "' deleted"));
        loginUser(email, password) // is it necessary???
                .then().assertThat()
                .body("error.message", equalTo("INVALID_LOGIN_CREDENTIALS"));
    }

    @Test
    @DisplayName("Delete Inexisting User Test")
    public void deleteInexistingUserTest(){
        String email = "test_lr_333@gmail.com";
        String password = "LR&123456test";
        deleteUser(email, password)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(2000L))
                .body("error.message", equalTo("INVALID_LOGIN_CREDENTIALS"));
    }

    @Test
    @DisplayName("Delete already deleted User Test")
    public void deleteAlreadyDeletedUserTest(){
        String email = "test_lr_222@gmail.com";
        String password = "LR&123456test";
        deleteUser(email, password)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(2000L))
                .body("error.message", equalTo("INVALID_LOGIN_CREDENTIALS"));
    }
    // Successfully delete a user and all its content using valid Authorization header with Base64-encoded email and password
    // Fail to delete a user if the request is missing authentication (both body and headers empty)
}
