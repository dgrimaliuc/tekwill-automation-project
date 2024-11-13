package example.junit;

import internal.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UIJunitTest extends BaseTest {

    @Test
    @DisplayName("Adding cart item in cart test")
    public void addItemInCartTest() {
        driver.get("https://shopify-eta-drab.vercel.app/");
        System.out.println();
    }


}
