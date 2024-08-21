package IngaTitchiev.JUnit;

import IngaTitchiev.UI.shopify.components.Card;
import IngaTitchiev.UI.shopify.pages.ShopifyV2;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ShopifyPriceFilterTest extends BaseTest {


    ShopifyV2 shopifyV2 = new ShopifyV2(driver);

    @BeforeEach
    public void openShopify() {
        shopifyV2.open();
    }

    @Test
    @DisplayName("Price is under 25 test")
    public void priceUnder25Test() {
        shopifyV2.priceSection.under25.click();
        for (Card el : shopifyV2.cardsC) {
            String price = el.price.getText().substring(1);
            Integer actual = Integer.parseInt(price);
            assertThat(actual, lessThanOrEqualTo(25));
        }
    }

    @Test
    @DisplayName("Price is over 100 test")
    public void priceOver100Test() {
        shopifyV2.priceSection.over100.click();
        for (Card el : shopifyV2.cardsC) {
            String price = el.price.getText().substring(1);
            Integer actual = Integer.parseInt(price);
            assertThat(actual, greaterThanOrEqualTo(100));
        }
    }

    @ParameterizedTest(name = "Price is between {0} and {1} ")
    @CsvSource({"25,50", "50,100"})
    @DisplayName("Price is from 25 to 50 test")
    public void priceFrom25To50Test(int min, int max) {
        if (min == 25) {
            shopifyV2.priceSection.from25to50.click();
        } else {
            shopifyV2.priceSection.from50to100.click();
        }
        for (Card el : shopifyV2.cardsC) {
            String price = el.price.getText().substring(1);
            Integer actual = Integer.parseInt(price);
            assertThat(actual,
                    allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))
            );
        }
    }

    @Test
    @DisplayName("Combine price filter test")
    public void priceCombineTest() {
        shopifyV2.priceSection.under25.click();
        shopifyV2.priceSection.over100.click();
        for (Card el : shopifyV2.cardsC) {
            String price = el.price.getText().substring(1);
            Integer actual = Integer.parseInt(price);
            assertThat(actual,
                    either(lessThanOrEqualTo(25)).or(greaterThanOrEqualTo(100))
            );
        }
    }
}
