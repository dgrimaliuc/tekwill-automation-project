package cristina_mocanu.junitCM;

import cristina_mocanu_poms.ShopifyPage;
import internal.BaseTest;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class ShopifyTest extends BaseTest {
   ShopifyPage page = new ShopifyPage(driver);
@BeforeEach
public void openPage(){

    driver.get("https://shopify-eta-drab.vercel.app/");
    actions.shouldBeDisplayed(page.priceSection);

}
@Test
@DisplayName("Open Shopify page test")
    public void openPageTest() {
    actions.shouldBeDisplayed(page.priceSection);
    actions.shouldBeDisplayed(page.colorSection);
    actions.shouldBeDisplayed(page.sizeSection);
    actions.shouldBeDisplayed(page.genderSection);
    actions.waitForNumberOfElements(page.cards, 0);
}
@Test
@DisplayName("Under 25 filter price test")
public void under25FilterTest () {

    page.priceSection.under25.click();


   for (var card: page.cards) {
       String priceString = card.price.getText().replace ("$", "");
   Integer price = Integer.parseInt(priceString);
   assertThat(price, lessThan(26));

   }

    }
    @Test
    @DisplayName("Price 25$ to 50$ Test")
            public void price25to50Test(){
    page.priceSection._25to50.click();
        for (var card: page.cards) {
            String priceString = card.price.getText().replace ("$", "");
            Integer price = Integer.parseInt(priceString);
            assertThat(price,
                    both(greaterThan(25)
                    ).and(
                 lessThan(50)
                            ));

        }}

    @Test
    @DisplayName("Price 50$ to 100$ Test")
    public void price50to100Test(){
        page.priceSection._50to100.click();
        for (var card: page.cards) {
            String priceString = card.price.getText().replace ("$", "");
            Integer price = Integer.parseInt(priceString);
            assertThat(price,
                    both(greaterThan(50)
                    ).and(
                            lessThan(100)
                    ));
        }}

            @Test
            @DisplayName("Over 100 filter price test")
            public void over100FilterTest () {

                page.priceSection.over100.click();

                for (var card: page.cards) {
                    String priceString = card.price.getText().replace ("$", "");
                    Integer price = Integer.parseInt(priceString);
                    assertThat(price, greaterThan(100));
}}}

