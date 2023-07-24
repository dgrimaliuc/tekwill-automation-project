package Roman_Marcov;

import cristina_savrin.UIStepDefinition;
import helpers.Helpers;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import roman_marcov.AdoptPagePets;

import java.util.ArrayList;

import static helpers.Helpers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class UIStepDefenition {

    WebDriver driver = null;
    WebDriverWait wait = null;
    AdoptPagePets page = null;
    Logger log = Logger.getLogger(UIStepDefinition.class);


    @After
    public void after() {
        driver.close();
    }

    @Before
    public void before() {

        var pathToChrome = "src/main/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChrome);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-debugging-port=9222");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 3);
        page = new AdoptPagePets(driver, wait);
        stepResults = new ArrayList<>();
    }

    @Given("Open Page with random location")

    public void openPageWithRandomLocation() {
        String openPageWithRandomLocation;
        openPageWithRandomLocation = RandomStringUtils.random(10, true, true);
        log.debug("Open random location: " + addQuotes(openPageWithRandomLocation));
        driver.get("https://petstore-kafka.swagger.io/?location=" + openPageWithRandomLocation);
        wait.until(ExpectedConditions.textToBePresentInElement(page.petsTitle, "Pets in " + openPageWithRandomLocation));
        stepResults.add(openPageWithRandomLocation);
    }

    @Then("Verify the text is present in [Text Input] {string}")
    public void verifyInTextInput(String location) {
        location = Helpers.getValue(location, String.class);
        log.debug("Verify the text is displayed in [Text Input]: " + addQuotes(location));
        assertThat(page.locationInput.getAttribute("value"), Matchers.equalTo(location));

    }

    @Then("Verify the text is present in [Pets in ..] {string}")
    public void verifyInPetsIn(String location) {
        location = Helpers.getValue(location, String.class);
        log.debug("Verify the text is displayed in [Pets in ..]: " + addQuotes(location));
        assertThat(page.petsTitle.getText(), Matchers.equalTo("Pets in " + location));
    }

    @Then("Verify the text is present in [Adoptions in ..] {string}")
    public void verifyAdoptionsIn(String location) {
        location = Helpers.getValue(location, String.class);
        log.debug("Verify the text is displayed in [Adoptions in ..]: " + addQuotes(location));
        assertThat(page.adoptationsTitle.getText(), Matchers.equalTo("Adoptions in " + location));
    }

    @And("Verify current section contains default info in [The game] section")
    public void verifyCurrentSectionContainsDefaultInfoInTheGameSection() {
        waitInSeconds(3);
        assertThat(page.petsInInfo.getText().trim(), Matchers.equalTo("No pets. Go rescue some pets!"));
        assertThat(page.adoptsInfo.getText().trim(), Matchers.equalTo("No adoptions. Go get those pets adopted!"));
    }
}
