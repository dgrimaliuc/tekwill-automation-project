package denis_grimaliuc;

import denis_grimaliuc.poms.DGPetStorePage;
import example.actions.BaseActions;
import example.data.enums.OS;
import internal.ChromeDriverProvider;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

import static denis_grimaliuc.poms.DGPetStorePage.allAdoptions;
import static denis_grimaliuc.poms.DGPetStorePage.allPets;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DG_UIStepDefenishn {

    ChromeDriver driver = null;

    Map<String, String> context = null;
    WebDriverWait wait = null;
    BaseActions actions = null;
    DGPetStorePage page = null;
    Logger log = Logger.getLogger(DG_UIStepDefenishn.class);


    @Before
    public void before() {
        context = new HashMap<>();
        driver = new ChromeDriverProvider(OS.MAC).getDriver();
        actions = new BaseActions(driver);
        wait = new WebDriverWait(driver, 10);
        page = new DGPetStorePage(driver);
    }


    @Given("Open Petstore")
    public void open_petstore() {
        openPetstore("");
    }


    @Given("Open Petstore in {string}")
    public void openPetstore(String location) {
        driver.get("https://petstore-eb41f.web.app/?location=" + location);
        actions.waitUntilPageToLoad();
    }


    @Then("I see {string} in location input")
    public void i_see_in_location_input(String string) {
        String locationText = page.locationInput.getAttribute("value");
        assertThat(locationText, equalTo(string));

    }

    @Then("I see {string} in Pets Section title")
    public void i_see_in_pets_section_title(String string) {
        String titleText = page.petSectionTitle.getText();
        assertThat(titleText, equalTo("Pets in " + string));
    }

    @Then("I see {string} in Adoptions Section title")
    public void i_see_in_adoptions_section_title(String string) {
        String titleText = page.adoptSectionTitle.getText();
        assertThat(titleText, equalTo("Adoptions in " + string));
    }

    @Then("I validate the info section")
    public void i_validate_the_info_section() {
        actions.waitForNumberOfElementsToBeMoreThan(allPets, 0);
        var petsCountText = driver.findElement(By.xpath("//p[@data-t='pets-count']")).getText();
        var adoptionsCountText = driver.findElement(By.xpath("//p[@data-t='adoptions-count']")).getText();
        var petsCount = driver.findElements(allPets).size();
        var adoptionsCount = driver.findElements(allAdoptions).size();
        assertThat(petsCountText, equalTo("Pets in Chisinau: " + petsCount));
        assertThat(adoptionsCountText, equalTo("Adoptions in Chisinau: " + adoptionsCount));
    }


    @When("Change location to {string}")
    public void change_location_to(String newLocation) {
        actions.clear(page.locationInput);
        page.locationInput.sendKeys(newLocation);
        page.changeLocationBtn.click();
        actions.waitUntilPageToLoad();
    }

    @When("Open a random location")
    public void openRandomLocation() {
        String locationName = RandomStringUtils.randomAlphanumeric(17);
        log.info("Opening Random location with name: " + locationName);
        openPetstore(locationName);
        context.put("location_name", locationName);
    }


    @Then("I see correct location name in location input")
    public void iSeeCorrectLocationNameInLocationInput() {
        String location = context.get("location_name");
        i_see_in_location_input(location);

    }

    @Then("I see correct location name in Pets Section title")
    public void iSeeCorrectLocationNameInPetsSectionTitle() {
        String location = context.get("location_name");
        i_see_in_pets_section_title(location);
    }

    @Then("I see correct location name in Adoptions Section title")
    public void iSeeCorrectLocationNameInAdoptionsSectionTitle() {
        String location = context.get("location_name");
        i_see_in_adoptions_section_title(location);
    }


    @When("I open a random location in new tab")
    public void iOpenARandomLocationInNewTab() {
        page.openInNewTabBtn.click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        String newTabId = driver.getWindowHandles().toArray()[1].toString();
        driver.switchTo().window(newTabId);
    }

    @Given("Add a pet")
    public void addAPet() {
        log.info("Adding a pet");
        page.addPetButton.click();
        actions.waitForNumberOfElements(page.pets, 1);
        String petStatus = page.pets.getFirst().status.getText();
        assertThat(petStatus, equalTo("AVAILABLE"));

    }

    @When("Add a pet with name")
    public void addAPetWithName() {
        String petName = "Tyson";
        log.info("Adding a pet with name: " + petName);

        actions.clear(page.petNameInput);
        page.petNameInput.sendKeys(petName);
        addAPet();

        context.put("pet_name", petName);

    }


    @Then("I see the pet with name added")
    public void iSeeThePetWithNameAdded() {
        String petName = context.get("pet_name");
        actions.shouldHaveTextToBe(page.pets.getFirst().name, petName);
    }

    @And("Create adoption")
    public void createAdoption() {
        log.info("Creating adoption");
        page.pets.getFirst().click();
        page.adoptSelectedButton.click();
        actions.waitForNumberOfElements(page.adoptions, 1);
        String adoptionStatus = page.adoptions.getFirst().status.getText();
        assertThat(adoptionStatus, equalTo("AVAILABLE"));
    }

    @Then("I see the pet with name adopted")
    public void iSeeThePetWithNameAdopted() {
        String petName = context.get("pet_name");
        String newPetStatus = page.pets.getFirst().status.getText();
        assertThat(newPetStatus, equalTo("ONHOLD"));
        var adoption = page.adoptions.getFirst();
        String actualAdoptedPetName = adoption.pets.getFirst().getText();
        assertThat(actualAdoptedPetName, equalTo(petName));
    }
}
