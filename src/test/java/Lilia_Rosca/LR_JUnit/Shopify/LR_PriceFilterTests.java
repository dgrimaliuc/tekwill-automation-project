package Lilia_Rosca.LR_JUnit.Shopify;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LR_PriceFilterTests extends LR_ShopifyBaseTests {
/*     @Test
    // @Disabled - testul nu va rulat
    @Tags({@Tag("smoke"), @Tag("regression")}) // ajuta la rularea doar testelor cu tag-uri specificate
    @DisplayName("Hello!") // afiseaza denumirea din paranteze, si nu cea reala din metoda
    public void dfgdghukgityutyh() {
        System.out.println("JUnit!!!");*/

// 29.01
    @Test
    @DisplayName("Open Shopify page test")
    public void openPageTest() {
       // wait.until(ExpectedConditions.visibilityOf(page.colorSection)); sau
        actions.shouldBeDisplayed(page.priceSection);
        actions.shouldBeDisplayed(page.colorSection);
        actions.shouldBeDisplayed(page.sizeSection);
        actions.shouldBeDisplayed(page.genderSection);
        actions.waitForNumberOfElementsToBeMoreThan(page.cards, 0);
    }
/*  public void waitForNumberOfElementsToBeMoreThan(Components<?> elements, int count) {
        wait.until(driver -> elements.size() > count);
    } // de sters dupa git pull
*/
    @Test
    @DisplayName("Under 25 filter price test")
    public void under25FilterTest() {
        actions.shouldBeDisplayed(page.priceSection);
        page.priceSection.under25.click();

        for (var card : page.cards) {
            String priceString = card.price.getText().replace("$", ""); //stergem $ din pret
            Integer price = Integer.parseInt(priceString); // transformam string in cifra
            assertThat(price, lessThan(26));
        }
    }

    // sau pentru 25 - 50 si 50 - 100
    @ParameterizedTest
    @CsvSource(value = {"25To50,25,50", "50To100,50,100"}, delimiter = ',')
    public void priceRangeTest(String priceFilter, int min, int max) {
        switch (priceFilter){
            case "25To50":
                page.priceSection.p25To50.click();
                break;
            case "50To100":
                page.priceSection.p50To100.click();
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
    @DisplayName("Over 100 filter price test")
    public void over100FilterTest() {
        actions.shouldBeDisplayed(page.priceSection);
        page.priceSection.over100.click();

        for (var card : page.cards) {
            String priceString = card.price.getText().replace("$", "");
            Integer price = Integer.parseInt(priceString);
            assertThat(price, greaterThan(100));
        }
    }

// 31.01
    @Test
    @DisplayName("Combined price filter test")
    public void combinedPriceFilterTest() {
        actions.shouldBeDisplayed(page.priceSection); // nu este necesar
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