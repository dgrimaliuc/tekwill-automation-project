package denis_grimaliuc.junit;

import denis_grimaliuc.shopify.Card;
import denis_grimaliuc.shopify.CartItem;
import denis_grimaliuc.shopify.ShopifyPage;
import internal.BaseTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static denis_grimaliuc.shopify.ShopifyPage.formatPrice;
import static org.hamcrest.MatcherAssert.assertThat;

public class ShopifyJunitTests extends BaseTest {
    ShopifyPage shopifyPage = new ShopifyPage(driver);

    @BeforeEach
    public void setup() {
        log.info("Opening the Shopify page");
        driver.get("https://shopify-eta-drab.vercel.app/#");
    }

    @Test
    public void openShopifyPage() {
        Card card = shopifyPage.cards.getFirst();
        card.addToCartButton.click();

        String title = card.title.getText();
        String color = card.color.getAttribute("data-t");
        String price = card.price.getText();
        String size = card.size.getText();
        String image = card.image.getAttribute("src");
        shopifyPage.cartButton.click();
        CartItem cartItem = shopifyPage.cart.cartItems.getFirst();
        actions.shouldHaveAttribute(cartItem.image, "src", image);
        actions.shouldHaveTextToBe(cartItem.title, title);
        actions.shouldHaveTextToBe(cartItem.price, price);
        actions.shouldHaveTextToBe(cartItem.color, color);
        actions.shouldHaveTextToBe(cartItem.size, size);
        actions.shouldHaveTextToBe(cartItem.quantity, "1");
        actions.shouldHaveTextToBe(shopifyPage.cart.totalPrice, price);
    }

    @Test
    public void twoCartItemsTest() {
        Card f = shopifyPage.cards.get(1);
        Card s = shopifyPage.cards.get(2);
        Integer fPrice = formatPrice(f.price.getText());
        f.addToCartButton.click();
        Integer sPrice = formatPrice(s.price.getText());
        s.addToCartButton.click();
        shopifyPage.cartButton.click();
        actions.waitForNumberOfElements(shopifyPage.cart.cartItems, 2);
        actions.shouldHaveTextToBe(shopifyPage.cart.totalPrice, "Total: $" + (fPrice + sPrice));
    }

    @Test
    public void addOneItemTwiceTest() {
        shopifyPage.cards.getFirst().addToCartButton.click();
        shopifyPage.cards.getFirst().addToCartButton.click();
        shopifyPage.cartButton.click();
        actions.waitForNumberOfElements(shopifyPage.cart.cartItems, 1);
        actions.shouldHaveTextToBe(shopifyPage.cart.cartItems.getFirst().quantity, "2");
    }

    @Test
    public void removeCartItemTest() {

        shopifyPage.cards.getFirst().addToCartButton.click();
        shopifyPage.cartButton.click();

        actions.waitForNumberOfElements(shopifyPage.cart.cartItems, 1);
        shopifyPage.cart.cartItems.getFirst().remove.click();
        actions.shouldSee(shopifyPage.cart.emptyContainer);
        actions.shouldHaveTextToBe(shopifyPage.cart.emptyTitle, "Your cart is empty");
    }

    @Test
    public void plusCartItemTest() {

        shopifyPage.cards.getFirst().addToCartButton.click();
        shopifyPage.cartButton.click();

        actions.waitForNumberOfElements(shopifyPage.cart.cartItems, 1);
        CartItem item = shopifyPage.cart.cartItems.getFirst();
        int originalPrice = formatPrice(item.price.getText());

        item.plus.click();

        actions.shouldHaveTextToBe(item.price, "Price: $" + originalPrice * 2);
        actions.shouldHaveTextToBe(item.quantity, "2");
        actions.shouldHaveTextToBe(shopifyPage.cart.totalPrice, "Total: $" + originalPrice * 2);
    }

    @Test
    public void minusCartItemTest() {

        shopifyPage.cards.getFirst().addToCartButton.click();
        shopifyPage.cartButton.click();

        actions.waitForNumberOfElements(shopifyPage.cart.cartItems, 1);
        CartItem item = shopifyPage.cart.cartItems.getFirst();
        int originalPrice = formatPrice(item.price.getText());

        item.plus.click();
        item.minus.click();

        actions.shouldHaveTextToBe(item.price, "Price: $" + originalPrice);
        actions.shouldHaveTextToBe(item.quantity, "1");
        actions.shouldHaveTextToBe(shopifyPage.cart.totalPrice, "Total: $" + originalPrice);
    }

    @Test
    public void orderButtonTest() {
        shopifyPage.cards.getFirst().addToCartButton.click();
        shopifyPage.cartButton.click();

        shopifyPage.cart.orderButton.click();

        Alert alert = actions.waitForAlert();
        assertThat(alert.getText(), Matchers.equalTo("Order has been placed!"));
        alert.accept();
        shopifyPage.cartButton.click();
        actions.shouldSee(shopifyPage.cart.emptyContainer);
    }

    @Test
    public void ascendingOrderTest() {
        shopifyPage.blueColorFilter.click();

        List<Integer> sortedPrices = new ArrayList<>();

        for (WebElement price : shopifyPage.prices) {
            Integer intPrice = formatPrice(price.getText());
            sortedPrices.add(intPrice);
        }
        Collections.sort(sortedPrices);
        shopifyPage.dropdownButton.click();
        shopifyPage.ascendingOption.click();

        for (int i = 0; i < shopifyPage.cards.size(); i++) {

            Card card = shopifyPage.cards.get(i);
            actions.shouldHaveTextToBe(card.price, "$" + sortedPrices.get(i));
        }
    }

    @Test
    public void descendingOrderTest() {
        List<Integer> sortedPrices = new ArrayList<>();

        for (WebElement price : shopifyPage.prices) {
            Integer intPrice = formatPrice(price.getText());
            sortedPrices.add(intPrice);
        }

        Collections.sort(sortedPrices);
        Collections.reverse(sortedPrices);

        shopifyPage.dropdownButton.click();
        shopifyPage.descendingOption.click();

        for (int i = 0; i < shopifyPage.cards.size(); i++) {

            Card card = shopifyPage.cards.get(i);
            actions.shouldHaveTextToBe(card.price, "$" + sortedPrices.get(i));
        }
    }
}
