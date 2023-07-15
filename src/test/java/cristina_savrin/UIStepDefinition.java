package cristina_savrin;

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

import java.util.ArrayList;

import static helpers.Helpers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;

public class UIStepDefinition {
    WebDriver driver = null;
    WebDriverWait wait = null;
    AdoptPetsPage page = null;
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
        wait = new WebDriverWait(driver, 5);
        page = new AdoptPetsPage(driver, wait);
        stepResults = new ArrayList<>();
    }

    @Given("Open AdoptPage with random location")
    public void randomLocation() {

        String randomLocation = RandomStringUtils.random(10, true, true);
        log.debug("Open random location: " + addQuotes(randomLocation));
        driver.get("https://petstore-kafka.swagger.io/?location=" + randomLocation);
        wait.until(ExpectedConditions.textToBePresentInElement(page.petsTitle, "Pets in " + randomLocation));
        stepResults.add(randomLocation);
    }

    @Then("Verify random location {string} in [Text Input], [Pets in ..] and [Adoptions in ..]")
    public void verifyRandomLocation(String location) {

        location = Helpers.getValue(location, String.class);
        log.debug("Verify random location is displayed: " + addQuotes(location));

        assertThat(page.locationInput.getAttribute("value"), Matchers.equalTo(location));
        assertThat(page.petsTitle.getText(), Matchers.equalTo("Pets in " + location));
        assertThat(page.adoptsTitle.getText(), Matchers.equalTo("Adoptions in " + location));
    }

    @And("Verify [The game] section for random location {string}")
    public void verifyTheGameSection(String location) {

        ArrayList<String> gameExpected = new ArrayList<>();
        ArrayList<String> gameActual = new ArrayList<>();
        location = Helpers.getValue(location, String.class);

        gameExpected.add("The game");

        if (page.noPetsText.size() == 0) {
            gameExpected.add("WebSocket messages: 11");
            gameExpected.add("Pets in " + location + ": 5");
        } else {
            gameExpected.add("WebSocket messages: 1");
            gameExpected.add("No pets. Go rescue some pets!");
        }

        gameExpected.add("No adoptions. Go get those pets adopted!");
        log.debug("Verify current section contains proper info in [The game] section");

        gameActual.add(page.gameTitle.getText().trim());
        waitInSeconds(3);
        gameActual.add(page.websocket.getText().trim());
        gameActual.add(page.petsInInfo.getText().trim());
        gameActual.add(page.adoptsInfo.getText().trim());

        assertEquals(gameExpected, gameActual);
    }

    @Given("Add random pet")
    public void addRandomPet() {

        Helpers.waitInSeconds(1);
        String newPetName = page.petNameInput.getAttribute("value");
        log.debug("Adding random pet: " + addQuotes(newPetName));
        page.addPetBtn.click();
        wait.until(ExpectedConditions.textToBePresentInElement(page.lastAddedPetRow, newPetName + "\nAVAILABLE"));
    }

    @Then("Verify default text disappears")
    public void defaultTextDisappears() {
        log.debug("Verify that default text" + addQuotes("No rows. Try reset filters") + " disappears");
        assertThat(page.noPetsText.size(), equalTo(0));
    }

    @Given("Add random pets")
    public void addRandomPets() {

        int petsNum = 5;
        for (int p = 0; p < petsNum; p++) {
            addRandomPet();
        }
    }

    @And("Check [Adopt Selected Pets] and [Deselect] buttons are disabled by default")
    public void checkAdoptSelectedPetsAndDeselectButtonsAreDisabledByDefault() {

        log.debug("Verify [Adopt Selected Pets] and [Deselect] buttons are disabled by default");
        assertThat(page.adoptBtn.isEnabled(), equalTo(false));
        assertThat(page.deselectBtn.isEnabled(), equalTo(false));
    }

    @And("Adopt a pet")
    public void adoptAPet() {

        String petToAdopt = page.lastAddedPetName.getText();
        log.debug("Adopting pet: " + addQuotes(petToAdopt));
        page.lastAddedPetRow.click();
        page.adoptBtn.click();
        log.debug("Checking pet status is changed to ONHOLD");
        wait.until(ExpectedConditions.textToBePresentInElement(page.lastAddedPetRow, petToAdopt + "\nONHOLD"));
    }
}