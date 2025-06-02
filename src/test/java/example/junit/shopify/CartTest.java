package example.junit.shopify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CartTest extends ShopifyBaseTest {

    @Test
    @DisplayName("Order button test")
    public void orderButtonTest() {
        var firstCard = page.cards.getFirst();

        String title = firstCard.title.getText();
        String color = firstCard.color.getAttribute("data-t");
        String price = firstCard.price.getText();
        String size = firstCard.size.getText();
        String image = firstCard.image.getAttribute("src");
        firstCard.addToCart.click();

        page.cartButton.click();

        var cartItem = page.cart.cartItems.getFirst();
        assertThat(page.cart.totalPrice.getText(), equalTo("Total: " + price));
        assertThat(cartItem.title.getText(), equalTo(title));
        assertThat(cartItem.color.getText(), equalTo("Color: " + color));
        assertThat(cartItem.price.getText(), equalTo("Price: " + price));
        assertThat(cartItem.size.getText(), equalTo(size));
        assertThat(cartItem.image.getAttribute("src"), equalTo(image));
        assertThat(cartItem.quantity.getText(), equalTo("1"));

    }

    @Test
    @DisplayName("Empty cart Test")
    public void emptyCartTest() {
        page.cartButton.click();
        String cartText = page.cart.emptyCardTitle.getText();
        assertThat(cartText, equalTo("Your cart is empty"));
    }


    @Test
    @DisplayName("Order alert test")
    public void orderAlertTest() {
        page.cards.getFirst().addToCart.click();
        page.cartButton.click();
        page.cart.orderButton.click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText, equalTo("Order has been placed!"));
        alert.accept();
        page.cartButton.click();
        actions.shouldBeDisplayed(page.cart.emptyCardTitle);
    }

    @Test
    @DisplayName("Minus item test")
    public void minusItemTest() {
        page.cards.getFirst().addToCart.click();
        page.cards.getFirst().addToCart.click();
        String price = page.cards.getFirst().price.getText();
        page.cartButton.click();

        page.cart.cartItems.getFirst().minusButton.click();

        assertThat(page.cart.cartItems.getFirst().price.getText(), equalTo("Price: " + price));
        assertThat(page.cart.totalPrice.getText(), equalTo("Total: " + price));

        assertThat(page.cart.cartItems.getFirst().quantity.getText(), equalTo("1"));
        page.cart.cartItems.getFirst().minusButton.click();
        actions.shouldBeDisplayed(page.cart.emptyCardTitle);
    }


    @Test
    @DisplayName("Remove item test")
    public void removeItemTest() {
        page.cards.getFirst().addToCart.click();
        page.cartButton.click();

        page.cart.cartItems.getFirst().removeBtn.click();
        actions.waitForNumberOfElements(page.cart.cartItems, 0);
    }
}
