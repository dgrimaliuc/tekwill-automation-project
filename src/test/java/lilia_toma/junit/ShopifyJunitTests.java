package lilia_toma.junit;

import example.actions.BaseActions;
import example.components.shopify.PriceSection;
import internal.BaseTest;
import lilia_toma.Shopify.Card;
import lilia_toma.Shopify.CardPrice;
import lilia_toma.Shopify.CartItem;
import lilia_toma.Shopify.ShopifyPageLT;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lilia_toma.Shopify.ShopifyPageLT.formatPrice;


public class ShopifyJunitTests extends BaseTest {

   ShopifyPageLT shopifyPageLT = new ShopifyPageLT(driver);

   @BeforeEach
   public void setup(){
       log.info("Opening the Shopify page");
       driver.get("https://shopify-eta-drab.vercel.app/#");
//       actions.waitForNumberOfElementsToBeMoreThan(shopifyPageLT.cards,0);
       BaseActions.waitFor(3);
   }


    @Test
    public void openShopifyPage(){
        shopifyPageLT.cards.getFirst().addToCartButton.click();
        Card card = shopifyPageLT.cards.getFirst();

        String title = card.title.getText();
       String color = card.color.getAttribute("data-t");
       String price = card.price.getText();
       String size = card.size.getText();
       String gender = card.gender.getText();
       String image = card.image.getAttribute("src");

        shopifyPageLT.cartButton.click();

        CartItem cartItem = shopifyPageLT.cart.cartItems.getFirst();

      actions.shouldHaveAttribute(cartItem.image, "src", image);
      actions.shouldHaveTextToBe(cartItem.title, title);
      actions.shouldHaveTextToBe(cartItem.color, color);
      actions.shouldHaveTextToBe(cartItem.price, price);
      actions.shouldHaveTextToBe(cartItem.size, size);
      actions.shouldHaveTextToBe(cartItem.quantity, "1");
      actions.shouldHaveTextToBe(shopifyPageLT.cart.totalPrice, price);
    }

    @Test
    public void twoCartItemsTest() {
        Integer fPrice = formatPrice(shopifyPageLT.cards.getFirst().price.getText());
        shopifyPageLT.cards.getFirst().addToCartButton.click();
        Integer sPrice = formatPrice(shopifyPageLT.cards.get(1).price.getText());
        shopifyPageLT.cards.get(1).addToCartButton.click();

        shopifyPageLT.cartButton.click();

        actions.waitForNumberOfElements(shopifyPageLT.cart.cartItems, 2);
        actions.shouldHaveTextToBe(shopifyPageLT.cart.totalPrice,"Total: $" + (fPrice + sPrice));
    }

    @Test
    public void addOneItemTwiceTest() {
        shopifyPageLT.cards.getFirst().addToCartButton.click();
        shopifyPageLT.cards.getFirst().addToCartButton.click();
        shopifyPageLT.cartButton.click();
        actions.shouldHaveTextToBe(shopifyPageLT.cart.cartItems.getFirst().quantity, "2");
    }

}

