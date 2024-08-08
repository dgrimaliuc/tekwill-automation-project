package chiril_bortnicov.stepDefinition;

import io.cucumber.core.logging.Logger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.equalTo;

public class UIStepDefinition {

    WebDriver driver = null;
    WebDriverWait wait = null;
   // Logger log = Logger.getLogger(UIStepDefinition.class);
    HashMap<String, Object> stepResults = null;


    @Before
    public void before() {
        var pathToChrome = "src/main/resources/chromedriver-127.exe";
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

    @When("I Enter to the Page")
    public void iEnterToThePage() {
        driver.get("https://shopify-eta-drab.vercel.app/");
    }

    @Given("I check if the title page is named correctly")
    public void iCheckIfTheTitlePageIsNamedCorrectly() {
        String actual = driver.getTitle();
        String name = "Ecommerce Website Template";
        assertThat(actual, Matchers.equalTo(name));
    }
}
