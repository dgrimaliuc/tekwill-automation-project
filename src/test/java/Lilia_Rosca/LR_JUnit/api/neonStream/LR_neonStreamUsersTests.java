package Lilia_Rosca.LR_JUnit.api.neonStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Lilia_Rosca.api.neonStream.LR_NSUserEndpoint.createUser;
import static org.apache.commons.io.IOUtils.length;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class LR_neonStreamUsersTests {
// 24.02
    @Test //valid email and password (meeting all complexity requirements) providing them in body
    @DisplayName("Create user using body test")
    public void createUserUsingBodyTest() {
        ;
    }

    @Test //using Authorization header with Base64-encoded email and password (meeting all complexity requirements)
    @DisplayName("Create user using Basic auth test")
    public void createUserUsingBasicAuthTest() {
        String email = "test_lr_111@gamil.com";
        String password = "LR&123456test";
        int passwordLength = length(password);
        createUser(email, password)
                .then().assertThat()
                .statusCode(200)
                .time(lessThan(1000L))
                .header(email,containsString("@"))
         //       .header("password",);

        ;
    }


}
