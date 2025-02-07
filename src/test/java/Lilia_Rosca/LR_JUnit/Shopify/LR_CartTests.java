package Lilia_Rosca.LR_JUnit.Shopify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;

import static Lilia_Rosca.actions.LR_ShopifyActions.convertPrice;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class LR_CartTests extends LR_ShopifyBaseTests{
// 31.01
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
        assertThat(cartItem.title.getText(), equalTo(title));
        assertThat(cartItem.color.getText(), equalTo("Color: " + color));
        assertThat(cartItem.price.getText(), equalTo("Price: " + price));
        assertThat(cartItem.size.getText(), equalTo(size));
        assertThat(cartItem.image.getAttribute("src"), equalTo(image));
        assertThat(cartItem.quantity.getText(), equalTo("1"));

        assertThat(page.cart.totalPrice.getText(), equalTo("Total: " + price));
    }
// 03.02
    @Test
    @DisplayName("Empty cart test")
    public void emptyCartTest() {
        page.cartButton.click();
        String cartText = page.cart.emptyCartTitle.getText();
        assertThat(cartText, equalTo("Your cart is empty"));
    }

    @Test
    @DisplayName("Add two items in cart test") // 2 items in cart test + total amount
    public void addTwoItemsInCartTest() {
        page.cards.getFirst().addToCart.click();
        page.cards.get(1).addToCart.click();
        String firstItemTitle = page.cards.getFirst().title.getText();
        String secondItemTitle = page.cards.get(1).title.getText();

        page.cartButton.click();
        var cartItems = page.cart.cartItems;
        assertThat(cartItems.getFirst().title.getText(), equalTo(firstItemTitle));
        assertThat(cartItems.get(1).title.getText(), equalTo(secondItemTitle));

        var firstPrice = convertPrice(cartItems.getFirst().price); //
        var secondPrice = convertPrice(cartItems.get(1).price);
        log.info("First price: " + firstPrice); // nu e necesar
        log.info("Second price: " + secondPrice);
        log.info("Total price: " + (firstPrice + secondPrice));

        String actualTotalPrice = page.cart.totalPrice.getText();
        log.info("Verify Actual " + actualTotalPrice + " with Expected " + "Total: $" + (firstPrice + secondPrice));
        assertThat(actualTotalPrice, equalTo("Total: $" + (firstPrice + secondPrice)));
    }

    @Test
    @DisplayName("Add several times one single item test") // 3 times same item + quantity + sum
    public void addSeveralTimesOneSingleItemTest() {
        int count = 3;
        int price = convertPrice(page.cards.getFirst().price);
        for (int i = 0; i < count; i++) {
            page.cards.getFirst().addToCart.click();
        }
        page.cartButton.click();
        int totalExpectedPrice = price * count;
        var firstItem = page.cart.cartItems.getFirst();

        assertThat(firstItem.price.getText(), containsString(String.valueOf(totalExpectedPrice)));
        assertThat(page.cart.totalPrice.getText(), containsString(String.valueOf(totalExpectedPrice)));
        assertThat(firstItem.quantity.getText(), equalTo(String.valueOf(count)));
    }

    @Test
    @DisplayName("Order alert test") // order button
    public void orderAlertTest() {
        page.cards.getFirst().addToCart.click();
        page.cartButton.click();
        page.cart.orderButton.click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText, equalTo("Order has been placed!"));
/*        page.cartButton.click(); // ceva nu merge la click
        actions.shouldBeDisplayed(page.cart.emptyCartTitle);*/
    }

    @Test
    @DisplayName("Minus item test") // minus item test - quantity + price
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
        actions.shouldBeDisplayed(page.cart.emptyCartTitle);
    }

    @Test
    @DisplayName("Plus item test")
    public void plusItemTest() {
        page.cards.getFirst().addToCart.click();
        int price = convertPrice(page.cards.getFirst().price) * 2;

        page.cartButton.click();
        page.cart.cartItems.getFirst().minusButton.click();

        assertThat(page.cart.cartItems.getFirst().quantity.getText(), equalTo("2"));
        assertThat(page.cart.cartItems.getFirst().price.getText(), equalTo("Price: $" + price));
        assertThat(page.cart.totalPrice.getText(), equalTo("Total: $" + price));
    }

    @Test
    @DisplayName("Remove item test")
    public void removeItemTest() {
        page.cards.getFirst().addToCart.click();
        page.cartButton.click();
        page.cart.cartItems.getFirst().removeButton.click();
        actions.waitForNumberOfElements(page.cart.cartItems, 0);
    }

}
