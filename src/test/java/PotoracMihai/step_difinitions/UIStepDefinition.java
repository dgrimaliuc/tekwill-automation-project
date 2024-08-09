package PotoracMihai.step_difinitions;

import PotoracMihai.ui.shopify.pages.Shopify;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UIStepDefinition {
    WebDriver driver = null;
    WebDriverWait wait = null;
    //Logger log = Logger.getLogger(UIStepDefinition.class);
    HashMap<String, Object> stepResults = null;


    @Before
    public void before() {
        var pathToChrome = "src/main/resources/chromedriver-127.exe";
        System.setProperty("webdriver.chrome.driver", pathToChrome);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        driver = new ChromeDriver(options);
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

    @Given("Shopify Page is Open PM")
    public void shopifyPageIsOpenPM() {
        driver.get("https://shopify-eta-drab.vercel.app/");

    }

    @When("I verify the page title PM")
    public void iVerifyThePageTitlePM() {
        String title = driver.getTitle();
        System.out.println("Title: " + title);
    }

    @Then("I should see the page title {string}")
    public void iShouldSeeThePageTitle(String stringExpectedTitle) {
        String actualTitle = driver.getTitle();
        System.out.println("Actual Title: " + actualTitle);
        System.out.println("Expected Title: " + stringExpectedTitle);
    }



    @When("Select price over $100")
    public void selectPriceOver() {
        WebElement filter = driver.findElement(Shopify.priceOver100);
        filter.click();
    }


    @Then("Verify price is over $100")
    public void verifyPriceIsOver() {
        WebElement filter = driver.findElement(Shopify.priceOver100);
        boolean isSelected = filter.isSelected();
        System.out.println("Is selected: " + isSelected);
    }




    @When("Select color {string}")
    public void selectColor(String color) {
        WebElement filter = driver.findElement(Shopify.getColorByName(color));
        filter.click();
    }

    @Then("Verify color is {string}")
    public void verifyColorIs(String color) {
        WebElement filter = driver.findElement(Shopify.getColorByName(color));
        boolean isSelected = filter.isSelected();
        System.out.println("Is selected: " + isSelected);


    }

    @When("Select size {string}")
    public void selectSize(String size) {
    }
}
