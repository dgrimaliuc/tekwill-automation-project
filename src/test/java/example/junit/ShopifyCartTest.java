package example.junit;

import example.ui.shopify.pages.ShopifyV2;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ShopifyCartTest extends BaseTest {

    ShopifyV2 shopifyV2 = new ShopifyV2(driver);

    @BeforeEach
    public void openShopify() {
        shopifyV2.open();
    }


    @Test
    @DisplayName("Add to cart test")
    public void addToCartTest() {
        var card = shopifyV2.cards.get(0);
        var expectedTitle = card.title.getText();
        card.addToCart.click();
        shopifyV2.cartIcon.click();
        var actualTitle = shopifyV2.cartSection.miniCards.get(0).title.getText();
        log.debug("Expected title: " + expectedTitle);
        log.debug("Actual title: " + actualTitle);
        assertThat(expectedTitle, equalTo(actualTitle));

    }
}


