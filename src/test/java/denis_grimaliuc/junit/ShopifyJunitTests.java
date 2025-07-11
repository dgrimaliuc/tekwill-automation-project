package denis_grimaliuc.junit;

import denis_grimaliuc.shopify.Card;
import denis_grimaliuc.shopify.CartItem;
import denis_grimaliuc.shopify.ShopifyPage;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static denis_grimaliuc.shopify.ShopifyPage.formatPrice;

public class ShopifyJunitTests extends BaseTest {

    ShopifyPage shopifyPage = new ShopifyPage(driver);

    @BeforeEach
    public void setup() {
        log.info("Opening the Shopify page");
        driver.get("https://shopify-eta-drab.vercel.app/#");
        actions.waitForNumberOfElementsToBeMoreThan(shopifyPage.cards, 0);
    }

    @Test
    public void openShopifyPage() {
        shopifyPage.cards.getFirst().addToCartButton.click();
        Card card = shopifyPage.cards.getFirst();

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
        Integer fPrice = formatPrice(shopifyPage.cards.getFirst().price.getText());
        shopifyPage.cards.getFirst().addToCartButton.click();

        Integer sPrice = formatPrice(shopifyPage.cards.get(1).price.getText());
        shopifyPage.cards.get(1).addToCartButton.click();

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

}
