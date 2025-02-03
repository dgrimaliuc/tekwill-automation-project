package denis_grimaliuc.junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.number.OrderingComparison.lessThan;


public class PriceFilterShopifyTest extends ShopifyBaseTest {


    @Test
    @DisplayName("Open Shopify page test")
    public void openPageTest() {
        actions.shouldBeDisplayed(page.colorsSection);
        actions.shouldBeDisplayed(page.sizeSection);
        actions.shouldBeDisplayed(page.genderSection);
        actions.waitForNumberOfElementsToBeMoreThan(page.cards, 0);
    }

    @Test
    @DisplayName("Under 25 filter price test")
    public void under25FilterTest() {
        page.priceSection.under25.click();

        for (var card : page.cards) {
            String priceString = card.price.getText().replace("$", "");
            Integer price = Integer.parseInt(priceString);
            assertThat(price, lessThan(25));
        }

    }

    @Test
    @DisplayName("Price 25 to 50 test")
    public void price25to50Test() {
        page.priceSection._25To50.click();

        for (var card : page.cards) {
            String priceString = card.price.getText().replace("$", "");
            Integer price = Integer.parseInt(priceString);
            assertThat(price,
                    both(greaterThan(25))
                            .and(lessThan(50)));
        }

    }

    @ParameterizedTest
    @CsvSource(value = {"25To50,25,50", "50to100,50,100"}, delimiter = ',')
    public void priceRangeTest(String priceFilter, int min, int max) {
        switch (priceFilter) {
            case "25To50":
                page.priceSection._25To50.click();
                break;
            case "50to100":
                page.priceSection._50To100.click();
        }


        for (var card : page.cards) {
            String priceString = card.price.getText().replace("$", "");
            Integer price = Integer.parseInt(priceString);
            assertThat(price,
                    both(greaterThan(min))
                            .and(lessThan(max)));
        }

    }

    @Test
    @DisplayName("Price 50 to 100 test")
    public void price50to100Test() {
        page.priceSection.over100.click();

        for (var card : page.cards) {
            String priceString = card.price.getText().replace("$", "");
            Integer price = Integer.parseInt(priceString);
            assertThat(price, greaterThan(100));
        }
    }

    @Test
    @DisplayName("Combined price filter test")
    public void combinedPriceFilterTest() {
        page.priceSection.under25.click();
        page.priceSection.over100.click();

        for (var card : page.cards) {
            String priceString = card.price.getText().replace("$", "");
            Integer price = Integer.parseInt(priceString);
            assertThat(price, either(lessThan(25))
                    .or(greaterThan(100)));
        }
    }

}
