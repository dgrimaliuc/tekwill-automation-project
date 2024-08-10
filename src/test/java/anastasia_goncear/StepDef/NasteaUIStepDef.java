package anastasia_goncear.StepDef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class NasteaUIStepDef {
    Map<String, Object> stepResults = null;
    WebDriver driver = null;
    WebDriverWait wait = null;


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

   @Given("Shopify Page is Open AN")
   public void shopifyPageIsOpenAN() {
         driver.get("https://shopify-eta-drab.vercel.app/");

        //  Logger log = Logger.getLogger(NAsteaUIStepDef.class);

    }

    @Given("I open the Shopify homepage")
    public void iOpenTheShopifyHomepage() {
        driver.get("https://shopify-eta-drab.vercel.app/");
    }

    @Then("the page title should be {string}")
    public void thePageTitleShouldBe(String string) {
        String pageTitle = driver.getTitle();
        String expectedTitle = "Ecommerce Website Template";
        Assert.assertEquals(pageTitle, expectedTitle);
        driver.quit();
    }
}

