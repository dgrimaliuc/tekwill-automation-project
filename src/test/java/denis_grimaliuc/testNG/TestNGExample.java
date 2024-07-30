package denis_grimaliuc.testNG;

import denis_grimaliuc.BaseTest;
import org.testng.annotations.Test;

public class TestNGExample extends BaseTest {

    @Test(testName = "Hover [Change Location] button test")
    public void changeLocationButtonTest() {
        System.out.println("Test passed");
    }


}
