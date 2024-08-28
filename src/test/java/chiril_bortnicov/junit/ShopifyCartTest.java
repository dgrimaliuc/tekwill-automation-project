package chiril_bortnicov.junit;

import chiril_bortnicov.ui.shopify.pages.ShopifyV2;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ShopifyCartTest extends BaseTest {

    ShopifyV2 shopifyV2 = new ShopifyV2(driver);

    @BeforeEach
    public void openShopify() {
        shopifyV2.open();
    }

    @Test
    @DisplayName("Add to cart test")
    public void addToCartTest() {
        var expectedCard = shopifyV2.cards.get(0);
        String expectedTitle = expectedCard.title.getText();
        String expectedPrice = expectedCard.price.getText();
        String expectedColor = expectedCard.color.getAttribute("data-t");
        String expectedSize = expectedCard.size.getText();

        expectedCard.addToCart.click();
        shopifyV2.cartIcon.click();


        var actualMini = shopifyV2.cartSection.miniCards.get(0);
        String actualTitle = actualMini.title.getText();
        String actualPrice = actualMini.price.getText();
        String actualColor = actualMini.color.getText();
        String actualSize = actualMini.size.getText();


        assertThat(actualTitle, equalTo(expectedTitle));
        assertThat(actualPrice, equalTo("Price: " + expectedPrice));
        assertThat(actualColor, equalTo("Color: " + expectedColor));
        assertThat(actualSize, equalTo(expectedSize));

    }
}
