package lilia_toma.junit;

import example.actions.BaseActions;
import example.components.shopify.PriceSection;
import internal.BaseTest;
import lilia_toma.Shopify.Card;
import lilia_toma.Shopify.CardPrice;
import lilia_toma.Shopify.ShopifyPageLT;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class ShopifyJunitTests extends BaseTest {

   ShopifyPageLT shopifyPageLT = new ShopifyPageLT(driver);

   @BeforeEach
   public void setup(){
       log.info("Opening the Shopify page");
       driver.get("https://shopify-eta-drab.vercel.app/#");
//       actions.waitForNumberOfElementsToBeMoreThan(shopifyPageLT.cards,0);
       BaseActions.waitFor(3);
   }

//   @Test
//    public void openShopifyPage() {
//       shopifyPageLT.addToCartButton.getFirst().click();
//
//       String title = shopifyPageLT.titles.getFirst().getText();
//       String color = shopifyPageLT.colors.getFirst().getAttribute("data-t");
//       String price = shopifyPageLT.prices.getFirst().getText();
//       String size = shopifyPageLT.sizes.getFirst().getText();
////       String gender = shopifyPageLT.genders.getFirst().getText();
//       String image = shopifyPageLT.images.getFirst().getAttribute("src");
////       System.out.println("Title " + title);
////       System.out.println("Color " + color);
////       System.out.println("Price " + price);
////       System.out.println("Size " + size);
////       System.out.println("Gender " + gender);
//
//      shopifyPageLT.cartButton.click();
//
//      actions.shouldHaveAttribute(shopifyPageLT.cartItemImage, "src", image);
//      actions.shouldHaveTextToBe(shopifyPageLT.cartItemTitle, title);
//      actions.shouldHaveTextToBe(shopifyPageLT.cartItemColor, color);
//      actions.shouldHaveTextToBe(shopifyPageLT.cartItemPrice, price);
//      actions.shouldHaveTextToBe(shopifyPageLT.cartItemSize, size);
//      actions.shouldHaveTextToBe(shopifyPageLT.cartTotalPrice, price);
//      actions.shouldHaveTextToBe(shopifyPageLT.cartItemQuantity, "1");
//    }

//    @Test
//    public void someTest(){
//       String title = shopifyPageLT.cards.get(1).title.getText();
//        System.out.println("Title of the second card: " + title);
//    }
//
//    @Test
//    public void openShopifyPage(){
//        shopifyPageLT.cards.getFirst().addToCartButton.click();
//        Card card = shopifyPageLT.cards.getFirst();
//
//        String title = card.title.getText();
//       String color = card.color.getAttribute("data-t");
//       String price = card.price.getText();
//       String size = card.size.getText();
//       String gender = card.gender.getText();
//       String image = card.image.getAttribute("src");
//
//        shopifyPageLT.cartButton.click();
//
//      actions.shouldHaveAttribute(shopifyPageLT.cartItemImage, "src", image);
//      actions.shouldHaveTextToBe(shopifyPageLT.cartItemTitle, title);
//      actions.shouldHaveTextToBe(shopifyPageLT.cartItemColor, color);
//      actions.shouldHaveTextToBe(shopifyPageLT.cartItemPrice, price);
//      actions.shouldHaveTextToBe(shopifyPageLT.cartItemSize, size);
//      actions.shouldHaveTextToBe(shopifyPageLT.cartTotalPrice, price);
//      actions.shouldHaveTextToBe(shopifyPageLT.cartItemQuantity, "1");
//    }

@Test
public void someTest(){
    String cardPrice = shopifyPageLT.cardPrice.getText();
    System.out.println("Price: " + cardPrice);
}

    @Test
    public void openShopifyPage(){
        shopifyPageLT.cardPrice.click();
        CardPrice cardPrice = shopifyPageLT.cardPrice;

        String priceUnder25C = cardPrice.priceUnder25C.getText();
        String price25To50C = cardPrice.price25To50C.getText();
        String price50To100C = cardPrice.price50To100C.getText();
        String priceOver100C = cardPrice.priceOver100C.getText();

        shopifyPageLT.cardPrice.click();

        shopifyPageLT.priceUnder25C.click();
        shopifyPageLT.price25To50C.click();
        shopifyPageLT.price50To100C.click();
        shopifyPageLT.priceOver100C.click();

        actions.shouldHaveTextToBe(shopifyPageLT.cardPrice, priceUnder25C);
        actions.shouldHaveTextToBe(shopifyPageLT.cardPrice, price25To50C);
        actions.shouldHaveTextToBe(shopifyPageLT.cardPrice, price50To100C);
        actions.shouldHaveTextToBe(shopifyPageLT.cardPrice, priceOver100C);
        MatcherAssert.assertThat(cardPrice, CoreMatchers.equalTo(cardPrice));

    }
}

