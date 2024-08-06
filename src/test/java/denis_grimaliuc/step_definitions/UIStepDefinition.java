package denis_grimaliuc.step_definitions;

import denis_grimaliuc.actions.BaseActions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UIStepDefinition {
    WebDriver driver = null;
    WebDriverWait wait = null;
    Logger log = Logger.getLogger(UIStepDefinition.class);
    HashMap<String, Object> stepResults = null;
    BaseActions actions = null;


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

    @When("Select price under {string}")
    public void selectPriceUnder(String value) {
        driver.findElement(By.xpath("//input[@value='" + value + "']")).click();
    }

    @Then("Verify price is under ${int}")
    public void verifyPriceIsUnder$(int expectedPrice) {
        var els = driver.findElements(By.id("card-price"));
        for (var el : els) {
            actions.scrollTo(el);
            var actual = Integer.parseInt(el.getText().substring(1));
            assertThat(actual, Matchers.lessThanOrEqualTo(expectedPrice));
        }
    }

}
