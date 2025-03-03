package Lilia_Rosca.LR_JUnit.api.neonStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Lilia_Rosca.api.neonStream.LR_NSUserEndpoint.*;
import static example.actions.BaseActions.getRandomString;
import static example.data.utils.MatcherUtils.matchesJsonSchemaFrom;
import static org.apache.logging.log4j.util.Base64Util.encode;
import static org.hamcrest.Matchers.*;

public class LR_neonStreamUsersTests {
// 24-26.02
    @Test
    @DisplayName("Create user using body test")
    public void createUserUsingBodyTest() {
        // String email = "test_lr_111@gmail.com";
        String email = ("lr_" + getRandomString(7).toLowerCase() + "@gmail.com");
        String password = "LR&123456test";
        createUser(email, password)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body("email", equalTo(email))
                .body("email", containsString("@")) // not necessary
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/neonStream/createUserSchema.json"));
               // at least a number, uppercase letter, lowercase letter, special character
        deleteUser(email, password);
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
        String email = "test_lr_111@gmail.com";
        String password = "";
        createUser(email, password)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(2000L))
                .body("error", equalTo("Missing required fields: password"));
    }

    @Test
    @DisplayName("Create user with wrong password test")
    public void createUserWithWrongPasswordTest() {
        String email = "test_lr_111@gmail.com";
        String password = "111"; // only numbers, too short
        createUser(email, password)
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(2000L))
                // at least 6 characters, a lower case, an upper case, non-alphanumeric
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

    @Test
    @DisplayName("Create user using auth test")
    public void createUserUsingAuthTest() {
        String email = "test_lr_222@gmail.com";
        String password = "LR&123456test";
        createUserAuth(email, password)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body("email", equalTo(email))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/neonStream/createUserSchema.json"));
    }

    @Test
    @DisplayName("Create user using Authorization with Basic64 test")
    public void createUserUsingAuthBasicTest() {
        String token = "dGVzdF9scl8xMUBnbWFpbC5jb206TFImMTIzNDU2dGVzdA"; // test_lr_11@gmail.com:LR&123456test
        createUser(token)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/neonStream/createUserSchema.json"));
    }

    @Test
    @DisplayName("Create user using invalid token test")
    public void createUserInvalidTokenTest() {
        String token = "dGVzdF9FImMTIzNDU2dGV";
        createUser(token)
                .then().assertThat()
                .statusCode(403)
                .time(lessThan(2000L))
                .body("error", equalTo("Invalid token"));
    }


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
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/neonStream/loginSchema.json"))
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

    @Test
    @DisplayName("Login using Authorization with Basic64 Test")
    public void loginAuthBasicTest(){
        String token = "dGVzdF9scl8yMjJAZ21haWwuY29tOkxSJjEyMzQ1NnRlc3Q";
        loginUser(token)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(2000L))
                .body(matchesJsonSchemaFrom("src/main/resources/schemes/neonStream/loginSchema.json"))
                .body("registered", equalTo(true));
    }

    @Test
    @DisplayName("Login with incorrect token Test")
    public void loginWithIncorrectTokenTest(){
        String token = "dGVzdMzQ1NnRlc3Q";
        loginUser(token)
                .then().assertThat()
                .statusCode(403)
                .time(lessThan(2000L))
                .body("error", equalTo("Invalid token"));
    }


    @Test
    @DisplayName("Delete User Test")
    public void deleteUserTest(){
        String email = ("lr_" + getRandomString(7).toLowerCase() + "@gmail.com");
        String password = "LR&123456test";
        createUser(email, password);
        deleteUser(email, password)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("message", equalTo("User '" + email + "' deleted"));
        loginUser(email, password)
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

    @Test // ?? same error
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

    @Test
    @DisplayName("Delete User empty body Test")
    public void deleteUserEmptyBodyTest(){
        deleteUser("", "")
                .then().assertThat()
                .statusCode(400)
                .time(lessThan(2000L))
                .body("error", equalTo("Missing required fields: email, password"));
    }

    @Test
    @DisplayName("Delete User using Authorization with Basic64 Test")
    public void deleteUserAuthBasicTest(){
        String email = ("lr_" + getRandomString(7).toLowerCase() + "@gmail.com");
        String password = "LR&123456test";
        String token = encode(email + ":" + password);
        createUser(token);
        deleteUser(token)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("message", equalTo("User '" + email + "' deleted"));
        loginUser(token)
                .then().assertThat()
                .body("error.message", equalTo("INVALID_LOGIN_CREDENTIALS"));
    }

    @Test
    @DisplayName("Delete User empty header Authorization with Basic64 Test")
    public void deleteUserEmptyHeaderAuthBasicTest(){
        deleteUser("")
                .then().assertThat()
                .statusCode(403)
                .time(lessThan(2000L))
                .body("error", equalTo("Invalid token"));
    }
}