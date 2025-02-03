package IonErm.junit;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
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
}