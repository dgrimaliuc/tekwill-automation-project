package PotoracMihai.stepdifinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UIStepDefinitionTask2 {
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
        assertThat(actualTitle, equalTo(stringExpectedTitle));
    }
}
