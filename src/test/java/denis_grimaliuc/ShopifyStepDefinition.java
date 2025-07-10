package denis_grimaliuc;

import denis_grimaliuc.shopify.ShopifyPage;
import example.actions.BaseActions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static denis_grimaliuc.shopify.ShopifyPage.formatPrice;
import static example.actions.BaseActions.setDefaultTimeouts;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class ShopifyStepDefinition {

    public final Logger log = Logger.getLogger(ShopifyStepDefinition.class);
    public WebDriver driver = null;
    public BaseActions actions = null;
    public WebDriverWait wait = null;

    ShopifyPage shopifyPage;


    @Before
    public void setUp() {
        log.info("Setting up the Shopify test environment");
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();

        setDefaultTimeouts(driver);
        wait = new WebDriverWait(driver, 10);
        actions = new BaseActions(driver);
        shopifyPage = new ShopifyPage(driver);
    }


    @Given("I open the Shopify page")
    public void iOpenTheShopifyPage() {
        log.info("Opening the Shopify page");
        driver.get("https://shopify-eta-drab.vercel.app/#");
    }


    @Then("I should see the Shopify homepage")
    public void iShouldSeeTheShopifyHomepage() {

        log.info("Verifying the Shopify homepage is displayed");
        WebElement header = driver.findElement(By.xpath("//h1[text()='Browse all themes']"));
        assertThat(header.isDisplayed(), equalTo(true));
        assertThat(header.getText(), equalTo("Browse all themes"));

        WebElement filterSection = driver.findElement(By.xpath("//div[@id='filter_section']"));

        assertThat(filterSection.isDisplayed(), equalTo(true));

        WebElement productSection = driver.findElement(By.xpath("//div[@id='card-container']"));
        assertThat(productSection.isDisplayed(), equalTo(true));


    }

    @After
    public void tearDown() {
        log.info("Tearing down the Shopify test environment");
        if (driver != null) {
            driver.quit();
        }
    }

    @When("I select the Under 25$ filter")
    public void iSelectTheUnder$Filter() {
        shopifyPage.under25PriceFilter.click();
    }

    @Then("I should see products with price under 25\\$")
    public void iShouldSeeProductsWithPriceUnder$() {
        log.info("Verifying products with price under 25$ are displayed");
        actions.waitForNumberOfElementsToBeMoreThan(shopifyPage.cards, 0);

        for (WebElement price : shopifyPage.prices) {
            Integer intPrice = formatPrice(price.getText());

            assertThat(intPrice, lessThanOrEqualTo(25));

        }
    }

    @Then("I should see products with price under 25\\$ and over 100\\$")
    public void i_should_see_products_with_price_under_$_and_over_$() {
        log.info("Verifying products with price under 25$ and over 100$ are displayed");
        actions.waitForNumberOfElementsToBeMoreThan(shopifyPage.cards, 0);

        for (WebElement price : shopifyPage.prices) {
            String text = price.getText().replaceAll("\\$", "").trim();
            Integer intPrice = Integer.parseInt(text);

            assertThat(intPrice, either(
                    lessThanOrEqualTo(25))
                    .or(greaterThanOrEqualTo(100))
            );


        }
    }


    @When("I select the over 100$ filter")
    public void iSelectTheOver$Filter() {
        WebElement over100$Filter = driver.findElement(By.xpath("//input[@value='Over $100']"));
        over100$Filter.click();
    }

    @Then("I should see products with price over 100\\$")
    public void iShouldSeeProductsWithPriceOver$() {
        List<WebElement> cards = driver.findElements(By.xpath("//div[@class='card']"));
        assertThat(cards.size(), equalTo(1));

        List<WebElement> prices = driver.findElements(By.xpath("//p[@id='card-price']"));

        for (WebElement price : prices) {
            String text = price.getText().replaceAll("\\$", "").trim();
            Integer intPrice = Integer.parseInt(text);

            assertThat(intPrice, greaterThanOrEqualTo(100));

        }
    }

    @When("I select the filter from $25 to $50")
    public void iSelectTheFilterFrom$To$() {
        shopifyPage.price25to50Filter.click();
    }

    @Then("I should see products with price from $25 to $50")
    public void iShouldSeeProductsWithPriceFrom$To$() {

        log.info("Verifying products with price from $25 to $50 are displayed");
        actions.waitForNumberOfElementsToBeMoreThan(shopifyPage.cards, 0);

        for (WebElement price : shopifyPage.prices) {
            Integer intPrice = formatPrice(price.getText());

            assertThat(intPrice, both(
                    greaterThanOrEqualTo(25))
                    .and(
                            lessThanOrEqualTo(50))
            );

        }

    }


    @When("I select the filter from $50 to $100")
    public void iSelectTheFilterFrom$50To$100() {
        shopifyPage.price50to100Filter.click();
    }

    @Then("I should see products with price from $50 to $100")
    public void iShouldSeeProductsWithPriceFrom$50To$100() {

        log.info("Verifying products with price from $25 to $50 are displayed");
        actions.waitForNumberOfElementsToBeMoreThan(shopifyPage.cards, 0);

        for (WebElement price : shopifyPage.prices) {
            Integer intPrice = formatPrice(price.getText());

            assertThat(intPrice, both(
                    greaterThanOrEqualTo(50))
                    .and(
                            lessThanOrEqualTo(100))
            );

        }

    }

    @When("I select the Black color filter")
    public void iSelectTheBlackColorFilter() {
        shopifyPage.blackColorFilter.click();
    }

    @Then("I should see products with {string} color")
    public void iShouldSeeProductsWithBlackColor(String expectedColor) {

        log.info("Verifying products with price from $25 to $50 are displayed");
        actions.waitForNumberOfElementsToBeMoreThan(shopifyPage.cards, 0);

        for (WebElement color : shopifyPage.colors) {
            String actualColor = color.getAttribute("data-t");

            assertThat(actualColor, Matchers.equalTo(expectedColor.toLowerCase()));

        }

    }

    @When("I select the White color filter")
    public void iSelectTheWhiteColorFilter() {
        shopifyPage.whiteColorFilter.click();
    }

    @When("I select the Red color filter")
    public void iSelectTheRedColorFilter() {
        shopifyPage.redColorFilter.click();
    }

    @Then("I should see products with {string} and {string} colors")
    public void iShouldSeeProductsWithAndColors(String fColor, String sColor) {

        log.info("Verifying products with price from $25 to $50 are displayed");
        actions.waitForNumberOfElementsToBeMoreThan(shopifyPage.cards, 0);

        for (WebElement color : shopifyPage.colors) {
            String actualColor = color.getAttribute("data-t");

            assertThat(actualColor, either(
                    equalTo(fColor.toLowerCase()))
                    .or(
                            equalTo(sColor.toLowerCase())));

        }
    }

    @When("I select the S size filter")
    public void iSelectTheSSizeFilter() {
        shopifyPage.sSizeFilter.click();
    }

    @Then("I should see products with {string} size")
    public void iShouldSeeProductsWithSize(String expectedSize) {
        log.info("Verifying products with size " + expectedSize + " are displayed");
        actions.waitForNumberOfElementsToBeMoreThan(shopifyPage.cards, 0);


        for (WebElement sizeE : shopifyPage.sizes) {
            String actualSize = sizeE.getText().trim();
            assertThat(actualSize, equalTo("Size: " + expectedSize.toUpperCase()));
        }

    }

    @When("I select the M size filter")
    public void iSelectTheMSizeFilter() {
        shopifyPage.mSizeFilter.click();
    }

    @When("I select the XL size filter")
    public void iSelectTheXLSizeFilter() {
        shopifyPage.xlSizeFilter.click();
    }

    @Then("I should see products with {string} and {string} sizes")
    public void iShouldSeeProductsWithAndSizes(String fSize, String sSecond) {
        log.info("Verifying products with size " + fSize + " and " + sSecond + " are displayed");
        actions.waitForNumberOfElementsToBeMoreThan(shopifyPage.cards, 0);


        for (WebElement sizeE : shopifyPage.sizes) {
            String actualSize = sizeE.getText().trim();
            assertThat(actualSize, Matchers.either(
                    equalTo("Size: " + fSize.toUpperCase())).or(
                    equalTo("Size: " + sSecond.toUpperCase()))
            );
        }
    }

    @When("I select Male filter")
    public void iSelectMaleFilter() {
        shopifyPage.maleGenderFilter.click();
    }

    @Then("I should see products with {string} gender")
    public void iShouldSeeProductsWithGender(String expectedGender) {
        log.info("Verifying products with gender " + expectedGender + " are displayed");
        actions.waitForNumberOfElementsToBeMoreThan(shopifyPage.genders, 0);


        for (WebElement genderE : shopifyPage.genders) {
            String actualGender = genderE.getText().trim();
            assertThat(actualGender, equalTo(expectedGender.toLowerCase())
            );
        }
    }

    @When("I select Female filter")
    public void iSelectFemaleFilter() {
        shopifyPage.femaleGenderFilter.click();
    }

    @Then("I should see products with {string} and {string} genders")
    public void iShouldSeeProductsWithAndGenders(String fGender, String sGender) {
        log.info("Verifying products with gender " + fGender + " and " + sGender + " are displayed");
        actions.waitForNumberOfElementsToBeMoreThan(shopifyPage.genders, 0);


        for (WebElement genderE : shopifyPage.genders) {
            String actualGender = genderE.getText().trim();
            assertThat(actualGender, Matchers.either(
                            equalTo(fGender.toLowerCase()))
                    .or(
                            equalTo(sGender.toLowerCase())
                    )
            );
        }
    }
}
