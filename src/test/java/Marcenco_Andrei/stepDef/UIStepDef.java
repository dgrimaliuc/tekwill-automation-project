package Marcenco_Andrei.stepDef;

import denis_grimaliuc.step_definitions.UIStepDefinition;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
//import java.util.logging.Logger;

public class UIStepDef {
    WebDriver driver = null;
    WebDriverWait wait = null;
//    Logger log = Logger.getLogger(UIStepDefinition.class);
    HashMap<String, Object> stepResults = null;


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

    @Given("Shopify Page is Open AM")
    public void openShopify(){
        driver.get("https://shopify-eta-drab.vercel.app/");
    }



    @Then("the title is {string}")
    public void theTitleIs(String arg0) {
        String actual = driver.getTitle();
        String name = "Ecommerce Website Template";
        assertThat(actual, Matchers.equalTo(name));
    }
}
