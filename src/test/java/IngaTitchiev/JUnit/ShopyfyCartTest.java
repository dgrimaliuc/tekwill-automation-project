package IngaTitchiev.JUnit;

import IngaTitchiev.UI.shopify.pages.ShopifyV2;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ShopyfyCartTest extends BaseTest {

    ShopifyV2 shopifyV2 = new ShopifyV2(driver);

    @BeforeEach
    public void openShopify() {
        shopifyV2.open();
    }

    @Test
    @DisplayName("Add to cart test")
    public void add_cart_Test() {
        var card = shopifyV2.cardsC.get(0);
        var expectedTitle = card.getText();
        card.addToCard.click();
        shopifyV2.cartItem.click();
        var actualTitle = shopifyV2.cartSection.miniCards.get(0).title.getText();
        log.info(expectedTitle);
        log.info(actualTitle);
        assertThat(expectedTitle, equalTo(actualTitle));
    }

}
