package ana_raetcaia;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.Matchers;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import static helpers.Helpers.waitInSeconds;
import static org.hamcrest.MatcherAssert.assertThat;

public class UIStepDefinition {

    WebDriver driver = null;
    WebDriverWait wait = null;
    Logger log = Logger.getLogger(UIStepDefinition.class);

    AdoptPage page = null;

    @Before
    public void before(){
        var pathToChrome = "src/main/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChrome);
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait = new WebDriverWait(driver, 5);

        page = new AdoptPage(driver);
    }

    @After
    public void after(){

        driver.quit();
    }

    @Given("Adopt Page is open")
    public void adoptPageIsOpen() {
        driver.get("https://petstore-kafka.swagger.io/?location=Plett");
        waitInSeconds(5);

    }

    @When("Open AdoptPage with custom location {string}")
    public void openAdoptPageWithCustomLocation(String customLocation) {
        log.info("Open AdoptPage with custom location ");
        driver.get("https://petstore-kafka.swagger.io/?location=Orhei");
    }

    @Then("Verify presence of {string} in input text")
    public void verifyPresenceOfInInputText(String newLocation) {
        log.info("Verify presence of " + newLocation + " in input text");
        assertThat(page.locationInput.getAttribute("value"), Matchers.equalTo(newLocation));
        waitInSeconds(5);
    }

    @Then("Verify presence of {string} in pets")
    public void verifyPresenceOfInPets(String newLocation) {
        log.info("Verify presence of " + newLocation + " in pets");
        assertThat(page.petsTitle.getText(), Matchers.equalTo("Pets in " + newLocation));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='root']/div/div[3]/div[1]//h2")));
    }

    @Then("Verify presence of {string} in adoptions")
    public void verifyPresenceOfInAdoptions(String newLocation) {
        log.info("Verify presence of " + newLocation + " in adoptions");
        assertThat(page.adoptsTitle.getText(), Matchers.equalTo("Adoptions in " + newLocation));
    }
    @Then("Verify section [The game]")
    public void verifySections() {
        log.info("Verify if sections contains default info in [The game] section ");
        assertThat(page.petsInInfo.getText().trim(), Matchers.equalTo("No pets. Go rescue some pets!"));
        assertThat(page.adoptsInfo.getText().trim(), Matchers.equalTo("No adoptions. Go get those pets adopted!"));
    }
}

