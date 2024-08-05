package serghei_rubailo.step_definition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import serghei_rubailo.ui.shopify.pages.Shopify;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class SRUIStepDefinition {

    WebDriver driver = null;
    WebDriverWait wait = null;
    Logger log = Logger.getLogger(SRUIStepDefinition.class);
    HashMap<String, Object> stepResults = null;

    Shopify xpath = new Shopify();

    @Before
    public void before() {
        var pathToChrome = "src/main/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChrome);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        stepResults = new HashMap<>();
    }

    @After
    public void after() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Given("Shopify Page is Open")
    public void shopifyPageIsOpen() {
        driver.get("https://shopify-eta-drab.vercel.app/");
    }

    @When("Select price under ${int}")
    public void selectPriceUnder(int arg0) {
        driver.findElement(xpath.priceUnder25).click();
    }

    @Then("Verify price is under ${int}")
    public void verifyPriceIsUnder(int expectedPrice) {
        var els = driver.findElements(xpath.cardPrice);

        for (var el : els) {
            var actual = Integer.parseInt(el.getText().substring(1));
            assertThat(actual, Matchers.lessThanOrEqualTo(expectedPrice));
        }
    }

    @Then("Page Title is {string}")
    public void pageTitleIs(String expectedResult) {
        String actualResult = driver.getTitle();
        assertThat(actualResult, equalTo(expectedResult));
    }
}
