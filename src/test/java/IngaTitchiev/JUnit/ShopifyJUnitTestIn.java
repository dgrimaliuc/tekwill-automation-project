package IngaTitchiev.JUnit;

import IngaTitchiev.UI.shopify.pages.ShopifyV2;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShopifyJUnitTestIn extends BaseTest {

    ShopifyV2 shopifyV2 = new ShopifyV2(driver);

    @BeforeEach
    public void openShopify() {
        shopifyV2.open();
    }

    @Test
    public void firstTest() {

        driver.get("https://shopify-eta-drab.vercel.app/");
        shopifyV2.priceSection.from25to50.click();
        System.out.println("Nuber of cards=" + shopifyV2.cards.size());
        shopifyV2.sizeSection.sizeM.click();
        shopifyV2.genderSection.male.click();
        shopifyV2.genderSection.female.click();
        shopifyV2.cardsC.get(0).addToCard.click();
        shopifyV2.cardsC.get(1).addToCard.click();
        shopifyV2.cardsC.get(2).addToCard.click();
        shopifyV2.cardsC.get(3).addToCard.click();
        System.out.println();

    }
}
