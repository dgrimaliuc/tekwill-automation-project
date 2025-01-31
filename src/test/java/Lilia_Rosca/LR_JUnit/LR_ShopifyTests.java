package Lilia_Rosca.LR_JUnit;


import Lilia_Rosca.poms.LR_shopifyPage;
import example.ui.shopify.pages.Shopify;
import helpers.customElements.Components;
import internal.BaseTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LR_ShopifyTests extends BaseTest {
/*     @Test
    // @Disabled - testul nu va rulat
    @Tags({@Tag("smoke"), @Tag("regression")}) // ajuta la rularea doar testelor cu tag-uri specificate
    @DisplayName("Hello!") // afiseaza denumirea din paranteze, si nu cea reala din metoda
    public void dfgdghukgityutyh() {
        System.out.println("JUnit!!!");*/

    LR_shopifyPage page = new LR_shopifyPage(driver);

    @BeforeEach
    public void openPage() {
        driver.get("https://shopify-eta-drab.vercel.app/#");
    }
// 29.01
    @Test
    @DisplayName("Open Shopify page test")
    public void openPageTest() {
       // wait.until(ExpectedConditions.visibilityOf(page.colorSection)); sau
        actions.shouldBeDisplayed(page.priceSection);
        actions.shouldBeDisplayed(page.colorSection);
        actions.shouldBeDisplayed(page.sizeSection);
        actions.shouldBeDisplayed(page.genderSection);
        /*actions.*/waitForNumberOfElementsToBeMoreThan(page.cards, 0);
    }
    public void waitForNumberOfElementsToBeMoreThan(Components<?> elements, int count) {
        wait.until(driver -> elements.size() > count);
    } // de sters dupa git pull

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

/*    @Test
    @DisplayName("Price 50 to 100 test")
    public void price50To100Test() {
        actions.shouldBeDisplayed(page.priceSection);
        page.priceSection.p50To100.click();

        for (var card : page.cards) {
            String priceString = card.price.getText().replace("$", ""); //stergem $ din pret
            Integer price = Integer.parseInt(priceString); // transformam string in cifra
            assertThat(price,
                    both(greaterThan(50))
                            .and(lessThan(100)));
        }
    }*/

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
            String priceString = card.price.getText().replace("$", ""); //stergem $ din pret
            Integer price = Integer.parseInt(priceString); // transformam string in cifra
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
            String priceString = card.price.getText().replace("$", ""); //stergem $ din pret
            Integer price = Integer.parseInt(priceString); // transformam string in cifra
            assertThat(price, greaterThan(100));
        }
    }


}
