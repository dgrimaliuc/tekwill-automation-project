package anastasia_goncear.StepDef;

import denis_grimaliuc.actions.BaseActions;
import denis_grimaliuc.ui.shopify.pages.Shopify;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static denis_grimaliuc.ui.shopify.pages.Shopify.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class NasteaUIStepDef {
    Shopify shopifyPage = new Shopify();
    BaseActions actions = null;
    Map<String, Object> stepResults = null;
    WebDriver driver = null;
    WebDriverWait wait = null;


    @Before
    public void before() {
        var pathToChrome = "src/main/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChrome);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
//        wait = new WebDriverWait(driver, 10);
//        actions = new BaseActions(driver);
        stepResults = new HashMap<>();
    }

    @After
    public void after() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("Shopify Page is Open AN")
    public void shopifyPageIsOpenAN() {
        driver.get("https://shopify-eta-drab.vercel.app/");

        //  Logger log = Logger.getLogger(NAsteaUIStepDef.class);

    }

    @Given("I open the Shopify homepage")
    public void iOpenTheShopifyHomepage() {
        driver.get("https://shopify-eta-drab.vercel.app/");
    }

    @Then("the page title should be {string}")
    public void thePageTitleShouldBe(String string) {
        String pageTitle = driver.getTitle();
        String expectedTitle = "Ecommerce Website Template";
        Assert.assertEquals(pageTitle, expectedTitle);
        driver.quit();
    }

    @When("Select the price {string}")
    public void selectThePrice(String option) {
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

    @Then("Verify the price is {string}")
    public void verifyThePriceIs(String filter) {
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

    @When("Select prices between {string}")
    public void selectPricesBetween(String option) {
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
    @Then("Check prices is between $<min> and $<max>")
    public void checkPricesIsBetween$MinAnd$Max(int min, int max) {
        var els = driver.findElements(shopifyPage.cardPrices);
        for (WebElement el : els) {
            String price = el.getText().substring(1);
            Integer actual = Integer.parseInt(price);

            assertThat(actual,
                    allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))
            );
        }
    }


@When("Select products size {string}")
    public void selectProductsSize(String size) {
        WebElement filter = driver.findElement(getSizeByName(size));
        filter.click();
    }
    @Then("Verify products size is {string}")
    public void verifyProductsSizeIs(String size) {
        var sizes = driver.findElements(shopifyPage.cardSizes);
        for (WebElement el : sizes) {
            String actual = el.getText();
            assertThat(actual, equalTo("Size: " + size));
        }
    }

    @When("Select a color {string}")
    public void selectAColor(String color) {
        WebElement filter = driver.findElement(getColorByName(color));
        filter.click();
    }

    @Then("Verify the color is {string}")
    public void verifyTheColorIs(String color) {
        var els = driver.findElements(shopifyPage.cardColors);
        for (WebElement el : els) {
            String actual = el.getAttribute("data-t");
            assertThat(actual, equalTo(color.toLowerCase()));
        }
    }

    @When("Select a gender {string}")
    public void selectAGender(String gender) {
        WebElement filter = driver.findElement(getGenderByName(gender));
        filter.click();
    }

    @Then("Verify the gender is {string}")
    public void verifyTheGenderIs(String gender) {
        var genders = driver.findElements(shopifyPage.cardGenders);
        for (var el : genders) {
            String text = el.getText();
            assertThat(text, equalTo(gender.toLowerCase()));
        }
    }

    @When("Select a price {string}")
    public void selectAPrice(String option) {
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

    @When("Select  color {string}")
    public void selectColor(String color) {
        WebElement filter = driver.findElement(getColorByName(color));
        filter.click();
    }

    @When("Select   gender {string}")
    public void selectGender(String gender) {
        WebElement filter = driver.findElement(getGenderByName(gender));
        filter.click();

    }

    @Then("The Message is {string} is displayed")
    public void theMessageIsIsDisplayed(String message) {
        WebElement cardContainer = driver.findElement(shopifyPage.cardContainer);
        String containerText = cardContainer.getText();
        assertThat(containerText, equalTo(message));
    }


    @When("I  sort items by price {string}")
    public void iSortItemsByPrice(String sortingType) {
        var prices = driver.findElements(shopifyPage.cardPrices);
        List<Integer> sortedPrices = new ArrayList<>();
        for (WebElement price : prices) {
            String stringPrice = price.getText().substring(1);
            Integer intPrice = Integer.parseInt(stringPrice);
            sortedPrices.add(intPrice);
        }
        Collections.sort(sortedPrices);
        if (sortingType.equals("Descending")) {
            Collections.reverse(sortedPrices);
        }


        stepResults.put("sortedPrices", sortedPrices);
    }

    @When("Select  sorting {string}")
    public void selectSorting(String arg0) {
        // implement
    }

    @Then("Verify  sorting is {string}")
    public void verifySortingIs(String sortingType) {
        List<Integer> sortedPrices = (List<Integer>) stepResults.get("sortedPrices");

        if (sortingType.equals("Ascending")) {

            for (int i = 0; i < sortedPrices.size() - 1; i++) {
                Integer expectedPrice = sortedPrices.get(i);
                Integer actualPrice = Integer.parseInt(driver.findElements(shopifyPage.cardPrices).get(i).getText().substring(1));
                assertThat(actualPrice, equalTo(expectedPrice));
            }
        }
    }}











