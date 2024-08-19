package denis_grimaliuc.junit;

import denis_grimaliuc.ui.shopify.components.Card;
import denis_grimaliuc.ui.shopify.pages.ShopifyV2;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ShopifyPriceFiltersTest extends BaseTest {

    ShopifyV2 shopifyV2 = new ShopifyV2(driver);

    @BeforeEach
    public void openShopify() {
        shopifyV2.open();
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

    @Test
    @DisplayName("Combine price filters test")
    public void priceCombineTest() {
        shopifyV2.priceFilters.under25.click();
        shopifyV2.priceFilters.over100.click();

        for (Card card : shopifyV2.cards) {
            String price = card.price.getText().substring(1);
            Integer actual = Integer.parseInt(price);
            assertThat(actual,
                    either(lessThanOrEqualTo(25)).or(greaterThanOrEqualTo(100))
            );

        }
    }

    @ParameterizedTest(name = "Price is between {0} and {1}")
    @CsvSource({"25,50", "50,100"})
    @DisplayName("Price is between 25 and 50 test")
    public void priceIsBetween25and50Test(int min, int max) {
        if (min == 25) {
            shopifyV2.priceFilters.from25to50.click();
        } else if (min == 50) {
            shopifyV2.priceFilters.from50to100.click();
        }

        for (Card card : shopifyV2.cards) {
            String price = card.price.getText().substring(1);
            Integer actual = Integer.parseInt(price);
            assertThat(actual,
                    allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))
            );

        }
    }
}


