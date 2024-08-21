package IngaTitchiev.JUnit;

import IngaTitchiev.UI.shopify.pages.ShopifyV2;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HW1608TI extends BaseTest {
    ShopifyV2 shopifyV2 = new ShopifyV2(driver);

    @BeforeEach
    public void openShopify() {
        shopifyV2.open();
    }

    @Test
    public void Test_HW() {

        driver.get("https://shopify-eta-drab.vercel.app/");
        shopifyV2.priceSection.from25to50.click();
        shopifyV2.sizeSection.sizeL.click();
        shopifyV2.genderSection.female.click();
        shopifyV2.cardsC.get(0).addToCard.click();
        System.out.println();

    }

}
