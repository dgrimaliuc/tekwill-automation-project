package victor_murashev.step_definitions;

import denis_grimaliuc.actions.BaseActions;
import denis_grimaliuc.ui.shopify.pages.Shopify;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static denis_grimaliuc.ui.shopify.pages.Shopify.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UIStepDefinition {
    Shopify shopifyPage = new Shopify();
    BaseActions actions = null;
    WebDriver driver = null;
    WebDriverWait wait = null;
    //    Logger log = Logger.getLogger(UIStepDefinition.class);
    HashMap<String, Object> stepResults = null;


    @Before
    public void before() {
        var pathToChrome = "src/main/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChrome);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        stepResults = new HashMap<>();
        actions = new BaseActions(driver);
    }

    @After
    public void after() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted during delay");
        }
    }

    @Given("Adopt Page is Open")
    public void adoptPageIsOpen() {
        driver.get("https://petstore-eb41f.web.app/");
    }

    @Then("Verify URL of Page")
    public void verifyURLOfPage() {
        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl, equalTo("https://petstore-kafka.swagger.io/?location=Chisinau"));
    }

    @Given("Shopify Page is Open")
    public void shopifyPageIsOpen() {
        driver.get("https://shopify-eta-drab.vercel.app/");
    }

    @When("Select price {string}")
    public void selectPriceUnder(String option) {
        WebElement filter;
        if (option.equals("Under $25")) {
            filter = driver.findElement(shopifyPage.priceUnder25);
        } else if (option.equals("Over $100")) {
            filter = driver.findElement(shopifyPage.priceOver100);
        } else {
            throw new RuntimeException("Filter not found: " + option);
        }

        filter.click();
    }

    @Then("Verify price is {string}")
    public void verifyPriceIsUnder$(String filter) {
        var els = driver.findElements(shopifyPage.cardPrices);
        for (WebElement el : els) {
            String price = el.getText().substring(1);
            Integer actual = Integer.parseInt(price);

            if (filter.equals("Under $25")) {
                assertThat(actual, lessThanOrEqualTo(25));
            } else if (filter.equals("Over $100")) {
                assertThat(actual, greaterThanOrEqualTo(100));
            }

        }
    }

    @Then("Verify page title")
    public void verifyPageTitle() {
        String pageTitle = driver.getTitle();
        assertThat(pageTitle, equalTo("Ecommerce Website Template"));
    }

    @When("Select price between {string}")
    public void selectPriceBetween$And$(String filterName) {
        WebElement filter;
        if (filterName.equals("$25 to $50")) {
            filter = driver.findElement(shopifyPage.price25to50);
        } else if (filterName.equals("$50 to $100")) {
            filter = driver.findElement(shopifyPage.price50to100);
        } else {
            throw new RuntimeException("Filter not found: " + filterName);
        }

        filter.click();
    }

    @Then("Verify price is between ${int} and ${int}")
    public void verifyPriceIsBetween$And$(int min, int max) {
        var els = driver.findElements(shopifyPage.cardPrices);
        for (WebElement el : els) {
            String price = el.getText().substring(1);
            Integer actual = Integer.parseInt(price);

            assertThat(actual,
                    allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))
            );
        }
    }

    @When("Select color {string}")
    public void selectColor(String color) {
        WebElement filter = driver.findElement(getColorByName(color));
        filter.click();
    }

    @Then("Verify color is {string}")
    public void verifyColorIs(String color) {
        var els = driver.findElements(shopifyPage.cardColors);
        for (WebElement el : els) {
            String actual = el.getAttribute("data-t");
            assertThat(actual, equalTo(color.toLowerCase()));
        }
    }


    @When("Select colors {string} and {string}")
    public void selectColors(String color1, String color2) {
        try {
            delay(500);
            WebElement filter1 = driver.findElement(getColorByName(color1));
            filter1.click();
            delay(1000);
            // Re-locate the second element after interacting with the first
            WebElement filter2 = driver.findElement(getColorByName(color2));
            filter2.click();
            delay(1000);
        } catch (StaleElementReferenceException e) {
            // Re-locate and retry if stale element exception occurs
            WebElement filter1 = driver.findElement(getColorByName(color1));
            filter1.click();
            delay(1000);
            WebElement filter2 = driver.findElement(getColorByName(color2));
            filter2.click();
            delay(1000);
        }
    }


    @Then("Verify colors are {string} and {string}")
    public void verifyColorsAre(String color1, String color2) {
        var elements = driver.findElements(shopifyPage.cardColors);
        for (WebElement el : elements) {
            String actualColor = el.getAttribute("data-t").toLowerCase();
            assertThat(actualColor, anyOf(equalTo(color1.toLowerCase()), equalTo(color2.toLowerCase())));
        }
    }




    @When("Select size {string}")
    public void selectSize(String size) {
        WebElement filter = driver.findElement(getSizeByName(size));
        filter.click();
    }

    @Then("Verify size is {string}")
    public void verifySizeIs(String size) {
        var sizes = driver.findElements(shopifyPage.cardSizes);
        for (WebElement el : sizes) {
            String actual = el.getText();
            assertThat(actual, equalTo("Size: " + size));
        }
    }

    @When("Select sizes {string} and {string}")
    public void selectSizes(String size1, String size2) {
        WebElement filter1 = driver.findElement(getSizeByName(size1));
        filter1.click();
        delay(1000);
        WebElement filter2 = driver.findElement(getSizeByName(size2));
        filter2.click();
        delay(1000);
    }

    @Then("Verify sizes are {string} and {string}")
    public void verify_sizes_are(String size1, String size2) {
        var sizes = driver.findElements(shopifyPage.cardSizes);
        for (WebElement el : sizes) {
            String actualSize = el.getText().toLowerCase();
            assertThat(actualSize, anyOf(equalTo(size1.toLowerCase()), equalTo(size2.toLowerCase())));
        }
        delay(1000);
    }

    @When("Select gender {string}")
    public void selectGender(String gender) {
        WebElement filter = driver.findElement(getGenderByName(gender));
        filter.click();
    }

    @Then("Verify gender is {string}")
    public void verifyGenderIs(String gender) {
        var genders = driver.findElements(shopifyPage.cardGenders);
        for (var el : genders) {
            String text = el.getText();
            assertThat(text, equalTo(gender.toLowerCase()));
        }
    }

    @Then("Verify price is Under $25 and Over $100")
    public void verifyPriceIsUnder$AndOver$() {
        var els = driver.findElements(shopifyPage.cardPrices);
        for (WebElement el : els) {
            String price = el.getText().substring(1);
            Integer actual = Integer.parseInt(price);
            assertThat(actual,
                    either(lessThanOrEqualTo(25)).or(greaterThanOrEqualTo(100))
            );
        }
    }

    @Then("The Message {string} is displayed")
    public void theMessageIsDisplayed(String message) {
        WebElement cardContainer = driver.findElement(shopifyPage.cardContainer);
        String containerText = cardContainer.getText();
        assertThat(containerText, equalTo(message));
    }
}
