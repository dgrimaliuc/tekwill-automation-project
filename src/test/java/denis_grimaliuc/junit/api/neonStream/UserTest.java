package denis_grimaliuc.junit.api.neonStream;

import example.api.petstore.NeonStreamBaseRequest;
import org.junit.jupiter.api.Test;

import static denis_grimaliuc.api.neonStream.NSUserEndpoint.createUser;

public class UserTest extends NeonStreamBaseRequest {

    @Test
    public void createUserTest() {
        createUser("aaawwSSs@gmail.com", "tEesSW22@22");

    }
}
