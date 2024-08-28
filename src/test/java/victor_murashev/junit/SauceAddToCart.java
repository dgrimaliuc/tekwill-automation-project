package victor_murashev.junit;

import helpers.BasePage;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import victor_murashev.ui.shopify.pages.SaucePOM;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SauceAddToCart extends BaseTest {

    SaucePOM saucepom = new SaucePOM(driver);

    @BeforeEach
    public void openSauce() {saucepom.openSauceEntry();}

    @Test
    @DisplayName("Add products to cart")
    public void addProductToCartTest() {
        var getFirstCard = saucepom.inventories.get(0);
        var expectedProductTitle = getFirstCard.title.getText();

        getFirstCard.addToCart.click();
        saucepom.cartIcon.click();

        var actualProductTitle = saucepom.sauceCartSection.productNamesInCart.get(0).getText();

       log.info("expectedProductTitle = " + expectedProductTitle);
       log.info("actualProductTitle = " + actualProductTitle);

        assertThat(expectedProductTitle, equalTo(actualProductTitle));
    }



}
