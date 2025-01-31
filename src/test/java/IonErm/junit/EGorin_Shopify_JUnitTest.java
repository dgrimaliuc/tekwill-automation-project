package IonErm.junit;

import IonErm.poms.IonErm_ShopifyPage;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class EGorin_Shopify_JUnitTest extends BaseTest {

    IonErm_ShopifyPage page = new IonErm_ShopifyPage(driver);

    @BeforeEach
    public void openPage() {
        driver.get("https://shopify-eta-drab.vercel.app/");
    }

    @Test
    @DisplayName("Open the page test")
    public void openPageTest() {
        actions.shouldBeDisplayed(page.genderSection);
        actions.shouldBeDisplayed(page.colorSection);
        actions.shouldBeDisplayed(page.priceSection);
        actions.shouldBeDisplayed(page.sizeSection);
//        actions.waitForNumberOfElementsMoreThen(page.cards, 0);
    }

    @Test
    @DisplayName("Under 25 filter price test")
    public void under25Test() {
        actions.shouldBeDisplayed(page.priceSection);
        page.priceSection.under25.click();
        for (var card : page.cards) {
            String priceString = card.cardPrice.getText().replace("$", "");
            Integer price = Integer.parseInt(priceString);
            assertThat(price, lessThan(25));
        }
    }

    @Test
    @DisplayName("25 to 50 filter price test")
    public void price25To50() {
        actions.shouldBeDisplayed(page.priceSection);
        page.priceSection.price25to50.click();
        for (var card : page.cards) {
            String priceString = card.cardPrice.getText().replace("$", "");
            Integer price = Integer.parseInt(priceString);
            assertThat(price, both(greaterThan(25)).and(lessThan(50)));
        }
    }

    @Test
    @DisplayName("50 to 100 filter price test")
    public void price50To100() {
        actions.shouldBeDisplayed(page.priceSection);
        page.priceSection.price50to100.click();
        for (var card : page.cards) {
            String priceString = card.cardPrice.getText().replace("$", "");
            Integer price = Integer.parseInt(priceString);
            assertThat(price, both(greaterThan(50)).and(lessThan(100)));
        }
    }

    @ParameterizedTest
    @CsvSource(value = {"25to50,25,50", "50to100,50,100"}, delimiter = ',')
    @DisplayName("25 to 50 and 50 to 100 filter price test")
    public void pricerangeTest(String priceFilter, int min, int max) {
        actions.shouldBeDisplayed(page.priceSection);
        if (priceFilter.equals("25to50")) {
            page.priceSection.price25to50.click();
        } else if (priceFilter.equals("50to100")) {
            page.priceSection.price50to100.click();
        }
        for (var card : page.cards) {
            String priceString = card.cardPrice.getText().replace("$", "");
            Integer price = Integer.parseInt(priceString);
            assertThat(price, both(greaterThan(min)).and(lessThan(max)));
        }
    }

    @Test
    @DisplayName("over 100 filter price test")
    public void over100() {
        actions.shouldBeDisplayed(page.priceSection);
        page.priceSection.priceOver100.click();
        for (var card : page.cards) {
            String priceString = card.cardPrice.getText().replace("$", "");
            Integer price = Integer.parseInt(priceString);
            assertThat(price, greaterThan(100));
        }
    }
}