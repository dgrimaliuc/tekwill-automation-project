package denis_grimaliuc.step_definitions;

import denis_grimaliuc.actions.BaseActions;
import denis_grimaliuc.ui.shopify.pages.Shopify;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class UIStepDefinition {
    Shopify shopifyPage = new Shopify();
    BaseActions actions = null;
    WebDriver driver = null;
    WebDriverWait wait = null;
    Logger log = Logger.getLogger(UIStepDefinition.class);
    HashMap<String, Object> stepResults = null;


    @Before
    public void before() {
        var pathToChrome = "src/main/resources/chromedriver_mac";
        System.setProperty("webdriver.chrome.driver", pathToChrome);
        driver = new ChromeDriver();
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

    @When("Select price under $25")
    public void selectPriceUnder() {
        driver.findElement(shopifyPage.priceUnder25).click();
    }

    @Then("Verify price is under $25")
    public void verifyPriceIsUnder$(int expectedPrice) {
        var els = driver.findElements(By.id("card-price"));
        for (WebElement el : els) {

            String price = el.getText().substring(1);
            Integer actual = Integer.parseInt(price);

            assertThat(actual, lessThanOrEqualTo(expectedPrice));
        }
    }

    @Then("Verify page title")
    public void verifyPageTitle() {
        String pageTitle = driver.getTitle();
        assertThat(pageTitle, equalTo("Ecommerce Website Template"));
    }
}
