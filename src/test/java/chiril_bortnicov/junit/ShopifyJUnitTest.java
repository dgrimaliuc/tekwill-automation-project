package chiril_bortnicov.junit;

import chiril_bortnicov.ui.shopify.components.Card;
import chiril_bortnicov.ui.shopify.pages.ShopifyV2;
import internal.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ShopifyJUnitTest extends BaseTest {

    ShopifyV2 shopifyV2 = new ShopifyV2(driver);
    Map<String, String> itemDetails;

    @BeforeEach
    public void openShopify() {
        shopifyV2.open();
    }

    @Test
    public void firstTest() {
        for (int i = 0; i < 4; i++) {
            shopifyV2.cards.get(i).addToCart.click();
        }

    }

    @Test
    @DisplayName("Price is under 25 test")
    public void priceUnder25Test() {
        shopifyV2.priceFilters.under25.click();

        for (Card card : shopifyV2.cards) {
            String price = card.price.getText().substring(1);
            Integer actual = Integer.parseInt(price);
            assertThat(actual, lessThanOrEqualTo(25));
        }
    }

    @Test
    @DisplayName("Price is over 100 test")
    public void priceOver100Test() {
        shopifyV2.priceFilters.over100.click();

        for (Card card : shopifyV2.cards) {
            String price = card.price.getText().substring(1);
            Integer actual = Integer.parseInt(price);
            assertThat(actual, greaterThanOrEqualTo(100));
        }
    }

    @ParameterizedTest(name = "Prices is between {0} and {1}")
    @CsvSource({"25,50", "50,100"})
    @DisplayName("Price is between 25 and 50 test")
    public void priceBetween25And50Test(int min, int max) {
        if (min == 25) {
            shopifyV2.priceFilters.from25to50.click();
        } else if (min == 50) {
            shopifyV2.priceFilters.from50to100.click();
        }

        for (Card card : shopifyV2.cards) {
            String price = card.price.getText().substring(1);
            Integer actual = Integer.parseInt(price);
            assertThat(actual, allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max)));
        }
    }

    @Test
    @DisplayName("Combine price filters test")
    public void priceCombineTest() {
        shopifyV2.priceFilters.under25.click();
        shopifyV2.priceFilters.over100.click();

        for (Card card : shopifyV2.cards) {
            String price = card.price.getText().substring(1);
            Integer actual = Integer.parseInt(price);
            assertThat(actual, either(lessThanOrEqualTo(25)).or(greaterThanOrEqualTo(100)));
        }
    }

    @Test
    @DisplayName("Adding cart item in cart")
    public void addingCartItemInCart() {
        WebElement firstCardElement = shopifyV2.cards.get(0);
        Card firstCard = new Card(firstCardElement);

        itemDetails = collectItemDetails(firstCard);

        firstCard.addToCart.click();
        shopifyV2.cartButton.click();


        WebElement cartItem = shopifyV2.cards.get(0);
        verifyCartItem(cartItem, itemDetails);
    }

    private Map<String, String> collectItemDetails(Card card) {
        Map<String, String> details = new HashMap<>();
        details.put("title", card.title.getText());
        details.put("price", card.price.getText());
        details.put("color", card.colors.getAttribute("data-t"));
        details.put("size", card.size.getText());
        details.put("gender", card.gender.getText());
        details.put("image", card.image.getAttribute("src"));
        return details;
    }

    private void verifyCartItem(WebElement cartItem, Map<String, String> itemDetails) {
        String title = cartItem.findElement(By.tagName("h4")).getText();
        String price = cartItem.findElement(By.cssSelector(".item-price")).getText();
        String color = cartItem.findElement(By.xpath("//p[contains(text(),'Color')]")).getText();
        String size = cartItem.findElement(By.xpath("//p[contains(text(),'Size')]")).getText();
        String image = cartItem.findElement(By.tagName("img")).getAttribute("src");
        String total = driver.findElement(By.cssSelector(".total-price")).getText();

        assertThat(title, equalTo(itemDetails.get("title")));
        assertThat(price, equalTo("Price: " + itemDetails.get("price")));
        assertThat(color, equalTo("Color: " + itemDetails.get("color")));
        assertThat(size, equalTo(itemDetails.get("size")));
        assertThat(image, equalTo(itemDetails.get("image")));
        assertThat(total, equalTo("Total: " + itemDetails.get("price")));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
