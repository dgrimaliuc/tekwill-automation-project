package Leas_Liudmila;

import helpers.Helpers;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static helpers.Helpers.addQuotes;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class task5LL {

    WebDriver driver = null;
    WebDriverWait wait = null;
    Logger log = Logger.getLogger(TextCheck.class);

    LLAdoptPage myPageLL = null;

    Map<String, Object> context = new HashMap<>();

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

        myPageLL = new LLAdoptPage(driver, wait);

    }

   @After
    public void after() {
        driver.close();
    }

    @Given("New Adopt Page with random location is open")
    public void pageWRandomLocation() {
        String randomLocationLL = RandomStringUtils.random(10, true, true);
        log.debug("Open Custom locations: " + addQuotes(randomLocationLL));
        driver.get("https://petstore-kafka.swagger.io/?location=" + randomLocationLL);
        Helpers.waitInSeconds(1);
        context.put("randomLocation", randomLocationLL);
    }
    @When("A random pet name is entered and Add Rescue button is clicked")
    public void enterARandomPetNameAndClickAddRescueButton() {
        String randomNameLL = RandomStringUtils.random(10, true, false);
        context.put("randomNameLL", randomNameLL);
        log.debug("Pet Name" + addQuotes(randomNameLL));
        myPageLL.nameInput.clear();
        myPageLL.nameInput.sendKeys(randomNameLL);
        myPageLL.buttonAddRescue.click();
        driver.navigate().refresh();
        wait.until(ExpectedConditions.textToBePresentInElement(myPageLL.firstAddedPet, randomNameLL));
        assertThat(myPageLL.firstAddedPet.getText(), Matchers.equalTo(randomNameLL + "\nAVAILABLE"));
    }

    @Then("The text {string} disappears when a pet is added")
    public void theTextDisappearsWhenAPetIsAdded(String arg0) {
        log.debug(String.format("The following text disappears: \n" + addQuotes(arg0)));

        assertThat(myPageLL.noRowsText.size(), equalTo(0));
    }

    @Then("[Adopt selected] and [Deselect] buttons are disabled when pets are not selected")
    public void adoptSelectedAndDeselectButtonsAreDisabledWhenPetsAreNotSelected() {
        log.debug("[Adopt selected] and [Deselect] buttons are disabled when pets are not selected");

        assertThat(myPageLL.deselectBtnInactive.size(), equalTo(1));
        assertThat(myPageLL.adoptSelPetsBtnInactive.size(), equalTo(1));



    }


    @When("several pets are added")
    public void severalPetsAreAdded() {
        int petsToRescue = 3;
        for(int i = 0; i < petsToRescue; i++) {
            enterARandomPetNameAndClickAddRescueButton();
        }
        String randomLocation = (String) context.get("randomLocation");

        assertThat(myPageLL.secondRowOfGameSection.getText().trim(), Matchers.equalTo("Pets in " + randomLocation + ": " + petsToRescue));
    }


    @When("A pet is selected and [Adopt selected pets] button is clicked")
    public void aPetIsSelectedAndAdoptSelectedPetsButtonIsClicked() {
        enterARandomPetNameAndClickAddRescueButton();
        String randomNameLL = (String) context.get("randomNameLL");

        myPageLL.firstAddedPet.click();
        myPageLL.adoptSelPetsBtnActive.click();
        driver.navigate().refresh();
        wait.until(ExpectedConditions.textToBePresentInElement(myPageLL.firstAddedPet, randomNameLL));
        assertThat(myPageLL.firstAddedPet.getText(), Matchers.equalTo(randomNameLL + "\nONHOLD"));

    }


}
