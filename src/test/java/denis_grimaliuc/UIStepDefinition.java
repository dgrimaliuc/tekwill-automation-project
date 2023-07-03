package denis_grimaliuc;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.hamcrest.MatcherAssert.assertThat;

public class UIStepDefinition {
    WebDriver driver = null;

    @Before
    public void before(){
        var pathToChrome = "src/main/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChrome);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void after(){
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
        WebElement button =  driver.findElement(By.xpath("//div[./input[@id='location-input']]//div[2]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(button).perform();
    }
}
