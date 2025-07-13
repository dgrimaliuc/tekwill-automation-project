package lilia_toma;

import example.actions.BaseActions;
import internal.BaseTest;
import internal.ChromeDriverProvider;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lilia_toma.Shopify.ShopifyPageLT;
import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Matcher;

import static example.actions.BaseActions.setDefaultTimeouts;
import static java.awt.Color.black;
import static java.awt.Color.green;
import static lilia_toma.Shopify.ShopifyPageLT.formatPrice;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.filter;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;

public class ShopifyStepDefinition {

    public final Logger log = Logger.getLogger(ShopifyStepDefinition.class);
    public WebDriver driver = null;
    public BaseActions actions = null;
    public WebDriverWait wait = null;

    ShopifyPageLT shopifyPage;

    @Before
    public void setUp() {
        log.info("Setting up the Shopify test environment");
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        setDefaultTimeouts(driver);
        wait = new WebDriverWait(driver, 10);
        actions = new BaseActions(driver);

        shopifyPage = new ShopifyPageLT(driver);
    }

    @Given("I open the Shopify page")
    public void iOpenTheShopifyPage() {
        log.info("Opening the Shopify page");
        driver.get("https://shopify-eta-drab.vercel.app/#");
    }

    @Given("I open the Elefant page")
    public void iOpenTheElefantPage() {
        log.info("Opening the Elefant page");
        driver.get("https://www.elefant.md/");
    }

    @Then("I should see the Shopify homepage")
    public void iShouldSeeTheShopifyHomepage() {
        log.info("Verifying the Shopify homepage is displayed");
        WebElement header = driver.findElement(By.xpath("//h1[text()='Browse all themes']"));
        MatcherAssert.assertThat(header.isDisplayed(), CoreMatchers.equalTo(true));
        MatcherAssert.assertThat(header.getText(), CoreMatchers.equalTo("Browse all themes"));
        WebElement filterSection = driver.findElement(By.xpath("//div[@id='filter_section']"));
        MatcherAssert.assertThat(filterSection.isDisplayed(), CoreMatchers.equalTo(true));
        WebElement productSection = driver.findElement(By.xpath("//div[@id='card-container']"));
        MatcherAssert.assertThat(productSection.isDisplayed(), CoreMatchers.equalTo(true));
        WebElement logoSection = driver.findElement(By.xpath("//img[@id='logo_img']"));
        MatcherAssert.assertThat(logoSection.isDisplayed(), CoreMatchers.equalTo(true));
        WebElement sortSectionCard = driver.findElement(By.xpath("//button[@id='dropdownButton']"));
        MatcherAssert.assertThat(sortSectionCard.isDisplayed(), CoreMatchers.equalTo(true));


    }

    @Then("I should see the Elefant homepage")
    public void iShouldSeeTheElefantHomepage() {
        log.info("Verifying the Elefant homepage is displayed");
        WebElement header = driver.findElement(By.xpath("//h1[text()='CATEGORII']"));
        MatcherAssert.assertThat(header.isDisplayed(), CoreMatchers.equalTo(true));
        MatcherAssert.assertThat(header.getText(), CoreMatchers.equalTo("CATEGORII"));
    }

    @After
    public void tearDown() {
        log.info("Rearing down the Shopify test environment");
        if (driver != null) {
            driver.quit();
        }
    }

    @When("I select the Under 25$ filter")
    public void iSelectTheUnder25$Filter() {
//        WebElement under25Filter = driver.findElement(By.xpath("//input[@value='Under $25']"));
//        under25Filter.click();
//        System.out.println();
        /** modalitate mai corecta de scriere cod si mai compact
         * cu ajutor POM ShopifyPageLT.java din main*/
        shopifyPage.under25PriceFilter.click();
    }

    @Then("I should see products with price under 25\\$")
    public void iShouldSeeProductsWithPriceUnder25$() {
        log.info("Verifying products with price under 25$ are displayed");
//        List<WebElement> cards = driver.findElements(By.xpath("//div[@class='card']"));
//        MatcherAssert.assertThat(cards.size(), CoreMatchers.equalTo(4));
//        List<WebElement> prices = driver.findElements(By.xpath("//p[@id='card-price']"));
        MatcherAssert.assertThat(shopifyPage.cards.size(), CoreMatchers.equalTo(4));

//        for (WebElement price : prices) {
        for (WebElement price : shopifyPage.prices) {
            String text = price.getText().replaceAll("\\$", "").trim();
            Integer intPrice = Integer.parseInt(text);

            MatcherAssert.assertThat(intPrice, lessThanOrEqualTo(25));

        }
    }

    @When("I select the Over 100$ filter")
    public void iSelectTheOver100$Filter() {
//        WebElement over100Filter = driver.findElement(By.xpath("//input[@value='Over $100']"));
//        over100Filter.click();
//        System.out.println();
//    }
        shopifyPage.over100PriceFilter.click();


//        @Then("I should see products with price Over 100\\$")
//        public void iShouldSeeProductsWithPriceOver100$ () {
//            List<WebElement> cards = driver.findElements(By.xpath("//div[@class='card']"));
//            MatcherAssert.assertThat(cards.size(), CoreMatchers.equalTo(1));
//            List<WebElement> prices = driver.findElements(By.xpath("//p[@id='card-price']"));
//
//            for (WebElement price : prices) {
//                String text = price.getText().replaceAll("\\$", "").trim();
//                Integer intPrice = Integer.parseInt(text);
//
//                MatcherAssert.assertThat(intPrice, greaterThanOrEqualTo(100));
//            }
//        }

    }

    @Then("I should see products with price Over 100\\$")
    public void iShouldSeeProductsWithPriceOver100$() {
        MatcherAssert.assertThat(shopifyPage.cards.size(), CoreMatchers.equalTo(1));

        for (WebElement price : shopifyPage.prices) {
            String text = price.getText().replaceAll("\\$", "").trim();
            Integer intPrice = Integer.parseInt(text);

            MatcherAssert.assertThat(intPrice, greaterThanOrEqualTo(100));
        }
    }

    @When("I select the filter from 25\\$ to 50\\$")
    public void iSelectTheFilterFrom25$To50$() {
        shopifyPage.price25to50Filter.click();
    }

    @Then("I should see products with price from 25\\$ to 50\\$")
    public void iShouldSeeProductsWithPriceFrom25$To50$() {
        log.info("Verifying products with price from 25$ to 50$ are displayed");
//        MatcherAssert.assertThat(shopifyPage.cards.size(), CoreMatchers.equalTo(10));
        /** pentru mai multe elemente pe pagina web folosim actions more than 0 */
        actions.waitForNumberOfElementsToBeMoreThan(shopifyPage.cards, 0);

        for (WebElement price : shopifyPage.prices) {
//            String text = price.getText().replaceAll("\\$", "").trim();
//            Integer intPrice = Integer.parseInt(text);
//
//            MatcherAssert.assertThat(intPrice, greaterThanOrEqualTo(25));
//            MatcherAssert.assertThat(intPrice, lessThanOrEqualTo(50));
            /** alta modalitate cu POM, stergem simbolul $ */
            Integer intPrice = formatPrice(price.getText());

            MatcherAssert.assertThat(intPrice, both(greaterThanOrEqualTo(25))
                    .and(lessThanOrEqualTo(50)));
        }
    }

    @When("I select the filter from 50\\$ to 100\\$")
    public void iSelectTheFilterFrom50$To100$() {
        shopifyPage.price50to100Filter.click();
    }

    @Then("I should see products with price from 50\\$ to 100\\$")
    public void iShouldSeeProductsWithPriceFrom50$To100$() {
        log.info("Verifying products with price from 50$ to 100$ are displayed");
        actions.waitForNumberOfElementsToBeMoreThan(shopifyPage.cards, 0);

        for (WebElement price : shopifyPage.prices) {
            Integer intPrice = formatPrice(price.getText());

            MatcherAssert.assertThat(intPrice, both(greaterThanOrEqualTo(50))
                    .and(lessThanOrEqualTo(100)));
        }
    }

    @Then("I should see products with price under $25 and over $100")
    public void iShouldSeeProductsWithPriceUnder25$AndOver100$() {
        log.info("Verifying products with price under $25 and over $100 are displayed");
        actions.waitForNumberOfElementsToBeMoreThan(shopifyPage.cards, 0);

        for (WebElement price : shopifyPage.prices) {
            Integer intPrice = formatPrice(price.getText());

            MatcherAssert.assertThat(intPrice, either(lessThanOrEqualTo(25))
                    .or(greaterThanOrEqualTo(100)));
        }
    }

    /** ------------ Testarea rubricii Color de pe pagina web Shopify -----------*/

    @When("I select the Black color filter")
    public void iSelectTheBlackColorFilter() {
        shopifyPage.blackColorFilter.click();
    }

    @Then("I should see products with Black color")
    public void iShouldSeeProductsWithBlackColor() {
        log.info("Verifying products with Black color are displayed");
        actions.waitForNumberOfElementsToBeMoreThan(shopifyPage.colors, 0);

        for (WebElement color : shopifyPage.colors) {
            String actualColor = color.getAttribute("data-t");

            MatcherAssert.assertThat(actualColor, CoreMatchers.equalTo("black"));
        }
    }

    /** pentru ca sa facem un test pentru toate culorile dar nu pt fiecare aparte*/
    @Then("I should see products with {string} color")
    public void iShouldSeeProductsWithColor(String expectedColor) {
        log.info("Verifying products with expected color are displayed");
        actions.waitForNumberOfElementsToBeMoreThan(shopifyPage.colors, 0);

        for (WebElement color : shopifyPage.colors) {
            String actualColor = color.getAttribute("data-t");

            MatcherAssert.assertThat(actualColor, CoreMatchers.equalTo(expectedColor.toLowerCase()));
        }
    }

    @When("I select the White color filter")
    public void iSelectTheWhiteColorFilter() {
        shopifyPage.whiteColorFilter.click();
    }

    @Then("I should see products with White color")
    public void iShouldSeeProductsWithWhiteColor() {
        log.info("Verifying products with White color are displayed");
        actions.waitForNumberOfElementsToBeMoreThan(shopifyPage.colors, 0);

        for (WebElement color : shopifyPage.colors) {
            String actualColor = color.getAttribute("data-t");

            MatcherAssert.assertThat(actualColor, CoreMatchers.equalTo("white"));
        }
    }

    @When("I select the Green color filter")
    public void iSelectTheGreenColorFilter() {
        shopifyPage.greenColorFilter.click();
    }

    @Then("I should see products with Green color")
    public void iShouldSeeProductsWithGreenColor() {
        log.info("Verifying products with Green color are displayed");
        actions.waitForNumberOfElementsToBeMoreThan(shopifyPage.colors, 0);

        for (WebElement color : shopifyPage.colors) {
            String actualColor = color.getAttribute("data-t");

            MatcherAssert.assertThat(actualColor, CoreMatchers.equalTo("green"));
        }
    }

    @When("I select the Purple color filter")
    public void iSelectThePurpleColorFilter() {
        shopifyPage.purpleColorFilter.click();
    }

    @Then("I should see products with Purple color")
    public void iShouldSeeProductsWithPurpleColor() {
        log.info("Verifying products with Purple color are displayed");
        actions.waitForNumberOfElementsToBeMoreThan(shopifyPage.colors, 0);

        for (WebElement color : shopifyPage.colors) {
            String actualColor = color.getAttribute("data-t");

            MatcherAssert.assertThat(actualColor, CoreMatchers.equalTo("purple"));
        }
    }
}



