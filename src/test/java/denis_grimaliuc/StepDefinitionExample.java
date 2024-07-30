package denis_grimaliuc;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static helpers.Helpers.stepResults;
import static org.hamcrest.MatcherAssert.assertThat;

public class StepDefinitionExample {
    WebDriver driver = null;
    WebDriverWait wait = null;
    Logger log = Logger.getLogger(StepDefinitionExample.class);
//    AdoptPage page = null;

    @Before
    public void before() {
        var pathToChrome = "src/main/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChrome);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);
        stepResults = new ArrayList<>();
//        page = new AdoptPage(driver);
    }

    @After
    public void after() {
        driver.quit();
    }

    @Given("Adopt Page is Open")
    public void adoptPageIsOpen() {
        driver.get("https://petstore-kafka.swagger.io/?location=Chisinau");
    }

    @Then("Verify URL of Page")
    public void verifyURLOfPage() {
        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl, Matchers.equalTo("https://petstore-kafka.swagger.io/?location=Chisinau"));
    }

}
