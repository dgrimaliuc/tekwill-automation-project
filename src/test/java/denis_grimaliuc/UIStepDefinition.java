package denis_grimaliuc;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static helpers.Helpers.addQuotes;
import static org.hamcrest.MatcherAssert.assertThat;

public class UIStepDefinition {
    WebDriver driver = null;
    WebDriverWait wait = null;
    Logger log = Logger.getLogger(UIStepDefinition.class);

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
    }

    @After
    public void after() {
        driver.close();
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

    @Then("Hover Change Location button")
    public void hoverChangeLocationButton() {
        WebElement button = driver.findElement(By.xpath("//div[./input[@id='location-input']]//div[2]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(button).perform();
    }

    @When("Change location to {string}")
    public void changeLocationTo(String location) {
        log.debug("Enter [" + location + "] in location text input");
        WebElement input = driver.findElement(By.xpath("//input[@id='location-input']"));
        input.clear();
        input.sendKeys(location);

        log.debug("Click on [Change location] button");
        WebElement changeLocationButton = driver.findElement(By.xpath("//div[./input[@id='location-input']]//div[1]/button"));
        changeLocationButton.click();
    }

    @Then("Verify location changed {string}")
    public void verifyLocationChanged(String newLocation) {
        log.debug("Verify new location is displayed: " + addQuotes(newLocation));
        WebElement input = driver.findElement(By.xpath("//input[@id='location-input']"));
        WebElement petsLocationEl = driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div[1]//h2"));
        WebElement adoptTitle = driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div[2]/h2"));

        assertThat(petsLocationEl.getText(), Matchers.equalTo("Pets in " + newLocation));
        assertThat(adoptTitle.getText(), Matchers.equalTo("Adoptions in " + newLocation));
        assertThat(input.getAttribute("value"), Matchers.equalTo(newLocation));
    }
}
