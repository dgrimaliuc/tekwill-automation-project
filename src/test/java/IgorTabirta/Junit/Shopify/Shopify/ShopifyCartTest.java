package IgorTabirta.Junit.Shopify.Shopify;

import IgorTabirta.UI.Shopify.Page.ShopifyV2;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ShopifyCartTest extends BaseTest {
    ShopifyV2 shopifyV2 = new ShopifyV2(driver);

    @BeforeEach
    public void setShopifyV2() {
        shopifyV2.open();
    }

    @Test
    @DisplayName("Add to cart")
    public void addToCartTest() {
        var card = shopifyV2.cards.get(0);
        var expectedTitle = card.title.getText();
        card.addToCart.click();


    }

}
