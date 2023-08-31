package denis_grimaliuc.junit;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainJunitTest {

    @Test
    public void firstTest() {
        Assertions.assertEquals(10, 5 + 5);
    }
}
