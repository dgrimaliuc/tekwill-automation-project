package IonErm.junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Shopify_AlertCart_Test extends ShopifyBaseTest {

    @Test
    @DisplayName("Test Alert")
    public void testAlert() {
        page.cards.getFirst().clickAddToCartBtn.click();
        page.cartButton.click();
        page.cart.orderButton.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText, equalTo("Order has been placed!"));
        alert.accept();
        page.cartButton.click();
        actions.shouldBeDisplayed(page.cart.emptyCartTitle);
    }
}
