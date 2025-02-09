package IonErm.junit.shopify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static IonErm.utility.Shopify_Actions_EG.convertPrice;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class Shopify_Cart_Test extends ShopifyBaseTest {

    @Test
    @DisplayName("Cart button test")
    public void cartButtonTest() {
        var firstCard = page.cards.getFirst();
        String title = firstCard.cardTitle.getText();
        String price = firstCard.cardPrice.getText();
        String color = firstCard.cardColor.getAttribute("data-t");
        String size = firstCard.cardSize.getText();
        String image = firstCard.cardImage.getAttribute("src");
        firstCard.clickAddToCartBtn.click();

        page.cartButton.click();
        var cartItem = page.cart.cartItems.getFirst();
        assertThat(page.cart.totalPrice.getText(), equalTo("Total: " + price));
        assertThat(cartItem.title.getText(), equalTo(title));
        assertThat(cartItem.price.getText(), equalTo("Price: " + price));
        assertThat(cartItem.color.getText(), equalTo("Color: " + color));
        assertThat(cartItem.size.getText(), equalTo(size));
        assertThat(cartItem.cartImage.getAttribute("src"), equalTo(image));
        assertThat(cartItem.quantity.getText(), equalTo("1"));
    }

    @Test
    @DisplayName("Empty cart test")
    public void emptyCartTest() {
        page.cartButton.click();
        String emptyText = page.cart.emptyCartTitle.getText();
        assertThat(emptyText, equalTo("Your cart is empty"));
    }

    @Test
    @DisplayName("Add two items in cart")
    public void addTwoItems() {
        page.cards.getFirst().clickAddToCartBtn.click();
        page.cards.get(1).clickAddToCartBtn.click();
        String firstTitle = page.cards.getFirst().cardTitle.getText();
        String secondTitle = page.cards.get(1).cardTitle.getText();
        page.cartButton.click();
        var cartItems = page.cart.cartItems;
        assertThat(cartItems.get(0).title.getText(), equalTo(firstTitle));
        assertThat(cartItems.get(1).title.getText(), equalTo(secondTitle));

        var firstPrice = convertPrice(cartItems.get(0).price);
        var secondPrice = convertPrice(cartItems.get(1).price);
        log.info("First price: " + firstPrice);
        log.info("Second price: " + secondPrice);
        log.info("Total price: " + (firstPrice + secondPrice));
        String actualTotalPrice = page.cart.totalPrice.getText();
        log.info("Verify actual " + actualTotalPrice + " with expected total price: $" + (firstPrice + secondPrice));
        assertThat(actualTotalPrice, equalTo("Total: $" + (firstPrice + secondPrice)));
    }

    @Test
    @DisplayName("Add several times one single item test")
    public void addSeveralTimeOneSingleItemTest() {
        int count = 4;
        int price = convertPrice(page.cards.getFirst().cardPrice);
        for (int i = 0; i < count; i++) {
            page.cards.getFirst().clickAddToCartBtn.click();
        }
        page.cartButton.click();
        int totalExpectedPrice = price * count;
        assertThat(page.cart.cartItems.getFirst().price.getText(),
                containsString(String.valueOf(totalExpectedPrice)));
        assertThat(page.cart.totalPrice.getText(),
                containsString(String.valueOf(totalExpectedPrice)));
        assertThat(page.cart.cartItems.getFirst().quantity.getText(),
                equalTo(String.valueOf(count)));
    }

    @Test
    @DisplayName("Minus item test")
    public void minusItemTest() {
        page.cards.getFirst().clickAddToCartBtn.click();
        page.cards.getFirst().clickAddToCartBtn.click();
        String price = page.cards.getFirst().cardPrice.getText();
        page.cartButton.click();
        page.cart.cartItems.getFirst().minus.click();
        assertThat(page.cart.cartItems.getFirst().price.getText(), equalTo("Price: " + price));
        assertThat(page.cart.cartItems.getFirst().quantity.getText(), equalTo("1"));
        assertThat(page.cart.totalPrice.getText(), equalTo("Total: " + price));
        page.cart.cartItems.getFirst().minus.click();
        actions.shouldBeDisplayed(page.cart.emptyCartTitle);
    }

    @Test
    @DisplayName("Plus item test")
    public void plusItemTest() {
        page.cards.getFirst().clickAddToCartBtn.click();
        int price = convertPrice(page.cards.getFirst().cardPrice) * 2;
        page.cartButton.click();
        page.cart.cartItems.getFirst().plus.click();
        assertThat(page.cart.cartItems.getFirst().quantity.getText(), equalTo("2"));
        assertThat(page.cart.cartItems.getFirst().price.getText(), equalTo("Price: $" + price));
        assertThat(page.cart.totalPrice.getText(), equalTo("Total: $" + price));
    }

    @Test
    @DisplayName("Remove item test")
    public void removeItemTest() {
        page.cards.getFirst().clickAddToCartBtn.click();
        page.cartButton.click();
        page.cart.cartItems.getFirst().removeButton.click();
        actions.waitForNumberOfElements(page.cart.cartItems, 0);
    }
}