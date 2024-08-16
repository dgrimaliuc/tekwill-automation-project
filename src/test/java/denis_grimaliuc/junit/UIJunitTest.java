package denis_grimaliuc.junit;

import internal.BaseTest;
import org.junit.jupiter.api.Test;

public class UIJunitTest extends BaseTest {

    @Test
    public void test() {
        driver.get("https://shopify-eta-drab.vercel.app/");
        System.out.println();
    }


}
