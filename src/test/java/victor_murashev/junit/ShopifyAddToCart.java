package victor_murashev.junit;

import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import victor_murashev.ui.shopify.pages.ShopifyPOM;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ShopifyAddToCart extends BaseTest {


    ShopifyPOM shopifypom = new ShopifyPOM(driver);

    @BeforeEach
    public void openShopify() {
        shopifypom.open();
    }

    @Test
    @DisplayName("Adding themes to cart")
    public void addCartTest() {
        var getFirstCard = shopifypom.cards.get(0);


        var expectedThemeTitle = getFirstCard.title.getText();
        getFirstCard.addToCart.click();
        shopifypom.cartIcon.click();

        var actualThemeTitle = shopifypom.cartSection.themeNamesInCart.get(0).getText();

        log.info("expectedThemeTitle = " + expectedThemeTitle);
        log.info("actualThemeTitle = " + actualThemeTitle);

        assertThat(expectedThemeTitle, equalTo(actualThemeTitle));

    }
}
