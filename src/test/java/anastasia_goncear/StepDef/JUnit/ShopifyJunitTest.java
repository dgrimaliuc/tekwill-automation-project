package anastasia_goncear.StepDef.JUnit;

import internal.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class ShopifyJunitTest extends BaseTest {


public ShopifyV2 pageShopify=new ShopifyV2(driver);
    @Test
    public void firsTest(){
        driver.get("https://shopify-eta-drab.vercel.app/");
    }
}
