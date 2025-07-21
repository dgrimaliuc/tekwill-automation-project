package Magda_Petrachi.ShopifyCurcumber;

import Magda_Petrachi.Shopify.ShopifyPageMP;
import example.actions.BaseActions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static Magda_Petrachi.Shopify.ShopifyPageMP.formatPrice;
import static example.actions.BaseActions.setDefaultTimeouts;
import static helpers.Helpers.log;
import static org.hamcrest.Matchers.*;


public class ShopifyStepsTest {

    public WebDriver driver;
    public BaseActions actions = null;
    ShopifyPageMP shopifyPage;

    @Before
    public void setUp() {
        log.info("Setting up the Shopify test environment");
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        shopifyPage = new ShopifyPageMP(driver);
        actions = new BaseActions(driver);
        setDefaultTimeouts(driver);
    }

    @Given("I open Shopify page")
    public void iOpenShopifyPage() {
        log.info("Opening the Shopify page");
        driver.get("https://shopify-eta-drab.vercel.app/#");
    }


    @Then("I should see the Shopify page")
    public void iShouldSeeTheShopifyPage() {
        log.info("Verifying the Shopify homepage is displayed");
        WebElement header = driver.findElement(By.xpath("//h1[text()='Browse all themes']"));
        MatcherAssert.assertThat(header.isDisplayed(), CoreMatchers.equalTo(true));
        MatcherAssert.assertThat(header.getText(), CoreMatchers.equalTo("Browse all themes"));
    }


    @Then("I find the card button")
    public void iFindTheCardButton() {
        log.info("Verifying the card button is displayed");
        WebElement button = driver.findElement(By.xpath("//li[@class='cart-container']//span[@class='cart-button']"));
        MatcherAssert.assertThat(button.isDisplayed(), CoreMatchers.equalTo(true));
    }

    @Then("I find the filter section")
    public void iFindTheFilterSection() {
        WebElement filterSection = driver.findElement(By.xpath("//div[@id='filter_section']"));
        MatcherAssert.assertThat(filterSection.isDisplayed(), CoreMatchers.equalTo(true));
    }


    @When("select filter under 25")
    public void selectFilterUnder() {
        shopifyPage.under25Filter.click();
    }

    @Then("Shoude find the products which have price under 25$")
    public void shoudeFindTheProductsWhichHavePriceUnder() {
        log.info("Verifying product with price under 25");
//        actions.waitForNumberOfElements(shopifyPage.cards, 0);

        for (WebElement price : shopifyPage.prices) {
            Integer inPrice = formatPrice(price.getText());

            MatcherAssert.assertThat(inPrice, lessThanOrEqualTo(25));
        }
    }


    @When("select filter between 20 and 50")
    public void selectFilterBetween20And50() {
        shopifyPage.price25to50Filter.click();
    }


    @Then("Shoude find the products which have prices between 20 and 50")
    public void shoudeFindTheProductsWhichHavePricesBetweenAnd() {
        log.info("Verificarea pretului de la 25 la 50");
//        actions.waitForNumberOfElementsToBeMoreThan(shopifyPage.cards, 0);

        for (WebElement price : shopifyPage.prices) {
            Integer inPrice = formatPrice(price.getText());

            MatcherAssert.assertThat(inPrice, either(greaterThanOrEqualTo(25)).or(lessThanOrEqualTo(50)));

        }

    }

    @When("select filter between 50 and 100")
    public void selectFilterBetweenAnd2() {
        WebElement selectFilterBetween2 = driver.findElement(By.xpath("//input[@value='$50 to $100']"));
        selectFilterBetween2.click();
    }

    @Then("Shoude find the products which have prices between 50 and 100")
    public void shoudeFindTheProductsWhichHavePricesBetweenAnd2() {
        List<WebElement> cards = driver.findElements(By.xpath("//p[@id='card-price']"));
        MatcherAssert.assertThat(cards.size(), CoreMatchers.equalTo(6));
        List<WebElement> prices = driver.findElements(By.xpath("//p[@id='card-price']"));

        for (WebElement price : prices) {
            String text = price.getText().replaceAll("\\$", "").trim();
            Integer inPrice = Integer.parseInt(text);

            MatcherAssert.assertThat(inPrice, greaterThanOrEqualTo(50));
            MatcherAssert.assertThat(inPrice, lessThanOrEqualTo(100));
        }
    }

    @When("select filter over 100")
    public void selectFilterOver() {
        WebElement selectFilterOver = driver.findElement(By.xpath("//input[@value='Over $100']"));
        selectFilterOver.click();
    }

    @Then("Shoude find the products which have price over 100")
    public void shoudeFindTheProductsWhichHavePriceOver() {
        List<WebElement> cards = driver.findElements(By.xpath("//p[@id='card-price']"));
        MatcherAssert.assertThat(cards.size(), CoreMatchers.equalTo(1));
        List<WebElement> prices = driver.findElements(By.xpath("//p[@id='card-price']"));

        for (WebElement price : prices) {
            String text = price.getText().replaceAll("\\$", "").trim();
            Integer inPrice = Integer.parseInt(text);

            MatcherAssert.assertThat(inPrice, greaterThanOrEqualTo(100));
        }
    }

    @When("I select the Black color filter")
    public void iSelectTheBlackColorFilter() {
        shopifyPage.blackColorFilter.click();
    }

    @Then("I should see product with {string} color")
    public void iShouldSeeProductWithColor(String expectedColor) {
        log.info("Verifying product with black color");
//        actions.waitForNumberOfElementsToBeMoreThan(shopifyPage.cards, 0);

        for (WebElement color : shopifyPage.colors) {
            String actualColor = color.getAttribute("data-t");
            MatcherAssert.assertThat(actualColor, Matchers.equalTo(expectedColor.toLowerCase()));
        }
    }

    @When("I select the White color filter")
    public void iSelectTheWhiteColorFilter() {
        shopifyPage.blackColorFilter.click();
    }

    @After
    public void tearDown() {
        log.info("Tearing down the Shopify test environment");
        if (driver != null) {
            driver.quit();
        }
    }


}
