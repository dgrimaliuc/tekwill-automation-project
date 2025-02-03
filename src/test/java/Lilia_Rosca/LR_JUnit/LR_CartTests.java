package Lilia_Rosca.LR_JUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
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
}
