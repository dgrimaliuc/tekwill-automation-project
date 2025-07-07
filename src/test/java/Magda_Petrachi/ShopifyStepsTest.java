package Magda_Petrachi;

import example.actions.BaseActions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static example.actions.BaseActions.setDefaultTimeouts;
import static helpers.Helpers.log;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.*;


public class ShopifyStepsTest {

    public WebDriver driver;
    public BaseActions actions = null;

    @Before
    public void setUp() {
        log.info("Setting up the Shopify test environment");
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
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
    WebElement under25Filter = driver.findElement(By.xpath("//input[@value='Under $25']"));
    under25Filter.click();
    }

    @Then("Shoude find the products which have price under 25$")
    public void shoudeFindTheProductsWhichHavePriceUnder() {
        List<WebElement> cards = driver.findElements(By.xpath("//p[@id='card-price']"));
        MatcherAssert.assertThat(cards.size(), CoreMatchers.equalTo(4));
        List<WebElement> prices = driver.findElements(By.xpath("//p[@id='card-price']"));

        for (WebElement price : prices){
            String text = price.getText().replaceAll("\\$", "").trim();
            Integer inPrice = Integer.parseInt(text);

            MatcherAssert.assertThat(inPrice, lessThanOrEqualTo(25));
        }
    }


    @When("select filter between 20 and 50")
    public void selectFilterBetweenAnd() {
        WebElement selectFilterBetween = driver.findElement(By.xpath("//input[@value='$25 to $50']"));
        selectFilterBetween.click();
    }


    @Then("Shoude find the products which have prices between 20 and 50")
    public void shoudeFindTheProductsWhichHavePricesBetweenAnd() {
        List<WebElement> cards = driver.findElements(By.xpath("//p[@id='card-price']"));
        MatcherAssert.assertThat(cards.size(), CoreMatchers.equalTo(10));
        List<WebElement> prices = driver.findElements(By.xpath("//p[@id='card-price']"));

        for (WebElement price : prices){
            String text = price.getText().replaceAll("\\$", "").trim();
            Integer inPrice = Integer.parseInt(text);

            MatcherAssert.assertThat(inPrice, greaterThanOrEqualTo(25));
            MatcherAssert.assertThat(inPrice, lessThanOrEqualTo(50));
        }

    }

    @When("select filter between 50 and 100")
    public void selectFilterBetweenAnd2() {
        WebElement selectFilterBetween2= driver.findElement(By.xpath("//input[@value='$50 to $100']"));
        selectFilterBetween2.click();
    }

    @Then("Shoude find the products which have prices between 50 and 100")
    public void shoudeFindTheProductsWhichHavePricesBetweenAnd2() {
        List<WebElement> cards = driver.findElements(By.xpath("//p[@id='card-price']"));
        MatcherAssert.assertThat(cards.size(), CoreMatchers.equalTo(6));
        List<WebElement> prices = driver.findElements(By.xpath("//p[@id='card-price']"));

        for (WebElement price : prices){
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

            for (WebElement price : prices){
                String text = price.getText().replaceAll("\\$", "").trim();
                Integer inPrice = Integer.parseInt(text);

                MatcherAssert.assertThat(inPrice, greaterThanOrEqualTo(100));
            }
        }


    @After
    public void tearDown() {
        log.info("Tearing down the Shopify test environment");
        if (driver != null) {
            driver.quit();
        }
    }



}
