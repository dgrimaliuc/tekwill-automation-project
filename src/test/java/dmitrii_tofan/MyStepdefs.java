package dmitrii_tofan;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static helpers.Helpers.addQuotes;
import static org.hamcrest.MatcherAssert.assertThat;


public class MyStepdefs {
    WebDriver driver = null;
    Logger log = Logger.getLogger(this.getClass());
    WebDriverWait wait = null;

    @Before
    public void before() {
        var pathToChrome = "src/main/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChrome);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void after() {
        driver.close();
    }

    @Given("If custom location is open {string}")
    public void ifCustomLocationIsOpen(String customLocation) {
        if (customLocation == null || customLocation.isEmpty()) {
            throw new RuntimeException("Custom location is empty");
        }
        driver.get("https://petstore-kafka.swagger.io/?location=" + customLocation);
    }

    @Then("Verify custom location {string}")
    public void verifyCustomLocation(String customLocation) {
        log.debug("Verify a Custom locations in text input: " + addQuotes(customLocation));
        WebElement input = driver.findElement(By.xpath("//*[@id='location-input']"));
        assertThat(input.getAttribute("value"), Matchers.equalTo(customLocation));
    }

    @And("Verify pets in section title {string}")
    public void verifyPetsInSectionTitle(String arg0) {
        log.debug("Verify a Custom locations in Pets In section: " + addQuotes(arg0));
        WebElement petsLocationEl = driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div[1]//h2"));
        assertThat(petsLocationEl.getText(), Matchers.equalTo("Pets in " + arg0));
    }

    @Then("Verify Adopt in section title {string}")
    public void verifyAdoptInSectionTitle(String arg0) {
        log.debug("Verify a Custom locations in Adopt In section: " + addQuotes(arg0));
        WebElement adoptTitle = driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div[2]/h2"));
        assertThat(adoptTitle.getText().trim(), Matchers.equalTo("Adoptions in " + arg0));
    }

    @Then("Verify Default text in the game section")
    public void verifyDefaultTextInTheGameSection() {
        WebElement petsInfo = driver.findElement(By.xpath("//div[@class='p-8']/p[2]"));
        WebElement adoptsInfo = driver.findElement(By.xpath("//div[@class='p-8']/p[3]"));
        assertThat(petsInfo.getText().trim(), Matchers.equalTo("No pets. Go rescue some pets!"));
        assertThat(adoptsInfo.getText(), Matchers.equalTo("No adoptions. Go get those pets adopted!"));
    }
}


