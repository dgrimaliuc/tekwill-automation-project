//package Magda_Petrachi.ShopifyJunitTestMP;
//
//import Magda_Petrachi.Shopify.CardShopify;
//import Magda_Petrachi.Shopify.ShopifyPageMP;
//import example.actions.BaseActions;
//import example.components.shopify.CartItem;
//import internal.BaseTest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.Duration;
//
//import static Magda_Petrachi.Shopify.ShopifyPageMP.formatPrice;
//
//
//public class JunitMPTest extends BaseTest {
//
//    ShopifyPageMP shopifyPageMP = new ShopifyPageMP(driver);
//
//    @BeforeEach
//    void setup() {
//        log.info("Open Spotify page");
//        driver.get("https://shopify-eta-drab.vercel.app/#");
//        BaseActions.waitFor(5);
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
//    }
//
//
//    @Test
//    public void test() {
//
//        shopifyPageMP.cards.getFirst().addToCardButton.click();
//
//        CardShopify card = shopifyPageMP.cards.getFirst();
//
//        String title = card.title.getText();
//        String color = card.color.getAttribute("data-t");
//        String price = card.price.getText();
//        String size = card.size.getText();
//        String image = card.image.getAttribute("src");
//
//        CartItem cartItem = shopifyPageMP.cart.cartItems.getFirst();
//
//        actions.shouldHaveAttribute(cartItem.image, "src", image);
//        actions.shouldHaveTextToBe(cartItem.title, title);
//        actions.shouldHaveTextToBe(cartItem.price, price);
//        actions.shouldHaveTextToBe(cartItem.color, color);
//        actions.shouldHaveTextToBe(cartItem.size, size);
//        actions.shouldHaveTextToBe(cartItem.quantity, "1");
//
//    }
//
//    @Test
//    public void twoCartItemsTest() {
//        Integer fPrice = formatPrice(shopifyPageMP.cards.getFirst().price.getText());
//        shopifyPageMP.cards.getFirst().addToCartButton.click();
//
//        Integer sPrice = formatPrice(shopifyPageMP.cards.get(1).price.getText());
//        shopifyPageMP.cards.get(1).addToCartButton.click();
//
//        shopifyPageMP.cartButton.click();
//        actions.waitForNumberOfElements(shopifyPageMP.cart.cartItems, 2);
//
//        actions.shouldHaveTextToBe(shopifyPageMP.cart.totalPrice, "Total: $" + (fPrice + sPrice));
//    }
//
//    @Test
//    public void addOneItemTwiceTest() {
//        shopifyPageMP.cards.getFirst().addToCartButton.click();
//        shopifyPageMP.cards.getFirst().addToCartButton.click();
//        shopifyPageMP.cartButton.click();
//        actions.waitForNumberOfElements(shopifyPageMP.cart.cartItems, 1);
//        actions.shouldHaveTextToBe(shopifyPageMP.cart.cartItems.getFirst().quantity, "2");
//    }
//}
