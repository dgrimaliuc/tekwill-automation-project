package Marcenco_Andrei.JUnit;

import andrei.marcenco.ui.shopify.ShopifyAM;
import internal.BaseTest;
import org.junit.jupiter.api.Test;

public class JUnit extends BaseTest {

    ShopifyAM shopifyAM = new ShopifyAM(driver);

    @Test
    public void firstTest() {
        driver.get("https://shopify-eta-drab.vercel.app/");
//        shopifyAM.sizeFilterAM.xl.click();
//        shopifyAM.priceFilterAM.under25.click();
//        shopifyAM.priceFilterAM.over100.click();
        shopifyAM.addToCart.click();
        System.out.println();


    }


}
