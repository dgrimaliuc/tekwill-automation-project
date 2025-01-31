package IonErm;

import IonErm.poms.IonErm_PetStorePage;
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

import static example.actions.BaseActions.waitFor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EGorin_TestUI {

    ChromeDriver driver = null;
    WebDriverWait wait = null;
    BaseActions actions = null;
    IonErm_PetStorePage page = null;
    Map<String, String> context = null;
    Logger log = Logger.getLogger(EGorin_TestUI.class);

    @Before
    public void before() {
        context = new HashMap<>();
        driver = new ChromeDriverProvider(OS.WINDOWS).getDriver();
        wait = new WebDriverWait(driver, 10);
        actions = new BaseActions(driver);
        page = new IonErm_PetStorePage(driver);
    }

    @Given("Open the PetStore")
    public void open_the_pet_store() {
        openTheApp("");
        actions.waitUntilPageToLoad();
    }

    @Then("I see {string} in location input")
    public void i_see_in_location_input(String string) {
        String locationText = page.locationInput.getAttribute("value");
        assertThat(locationText, equalTo(string));
    }

    @Then("I see {string} Pet section title")
    public void i_see_pet_section_title(String string) {
        String titleText = page.sectionTitle.getText();
        assertThat(titleText, equalTo("Pets in " + string));
    }

    @Then("I see {string} in Adoptions Section title")
    public void i_see_in_adoptions_section_title(String string) {
        String adoptionText = page.adoptionsSection.getText();
        assertThat(adoptionText, equalTo("Adoptions in " + string));
    }

    @Then("I click open new tab")
    public void iClickOpenNewTab() {
        page.clickOpenNewTabBtn.click();
    }

    //1
    @Given("Change location on {string}")
    public void change_location_on(String string) {
        var location = page.locationInput;
        location.clear();
        location.sendKeys(string);
    }

    @Then("Add pet in your town")
    public void addPet() {
        var addPet = driver.findElement(By.xpath("//div[@class='flex']//button[@data-t='change-location']"));
        addPet.click();
    }

    @Then("Adopt the selected pet")
    public void adoptPet() {
        var adopt = driver.findElement(By.xpath("//button[@data-t='adopt-button']"));
        adopt.click();
    }

    //Homework
    @Given("Open the PetStore in {string}")
    public void open_the_pet_store_in_chisinau(String location) {
        openTheApp(location);
    }

    @Then("Verify the Pets in Chisinau: 5")
    public void verify_the_pets_in_chisinau() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        boolean isTextCorrect = wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//p[@data-t='pets-count']//span"),
                "Pets in Chisinau: 5"));
        assertThat("Text did not match after waiting", isTextCorrect, equalTo(true));
    }

    @Then("Verify the Adoptions in Chisinau: 2")
    public void verify_the_adoptions_in_chisinau() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        boolean isTextCorrect = wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//p[@data-t='adoptions-count']//span"),
                "Adoptions in Chisinau: 2"));
        assertThat("Text did not match after waiting", isTextCorrect, equalTo(true));
    }

    //Classwork

    @Given("Open the App {string}")
    public void openTheApp(String location) {
        driver.get("https://petstore-eb41f.web.app/?location=" + location);
        actions.waitUntilPageToLoad();
    }

    @Then("Validate the info section in {string}")
    public void validateTheInfoSectionIn(String location) {
        actions.waitForNumberOfElementsToBeMoreThan(By.xpath("//tr[@data-t='single-pet']"), 0);
        var petsText = page.petsText.getText();
        var adoptionText = page.adoptionText.getText();
        var petsCount = driver.findElements(By.xpath("//tr[@data-t='single-pet']")).size();
        var adoptionsCount = driver.findElements(By.xpath("//div[@data-t='single-adoption']")).size();
        assertThat(petsText, equalTo("Pets in " + location + ": " + petsCount));
        assertThat(adoptionText, equalTo("Adoptions in " + location + ": " + adoptionsCount));
    }

    @Then("Change location to {string}")
    public void change_location_to(String newLocation) {
        actions.clear(page.locationInput);
        page.locationInput.sendKeys(newLocation);
        page.clickChangeLocationBtn.click();
        actions.waitUntilPageToLoad();
    }

    //Homework

    @Then("Add Pets")
    public void addPets() {
        page.clickAddPetsBtn.click();
        page.petsCount.click();
        page.clickAdoptSelectedPetsBtn.click();
        actions.waitUntilPageToLoad();
    }

    //Classwork

    @Then("Open random location")
    public void openRandomLocation() {
        String locationName = RandomStringUtils.randomAlphanumeric(16);
        log.info("Open Random Location With Name: " + locationName);
        openTheApp(locationName);
        context.put("location_name", locationName);
    }

    @Then("I see the correct location name in location input")
    public void iSeeTheCorrectLocationNameInLocationInput() {
        String location = context.get("location_name");
        i_see_in_location_input(location);
    }

    @Then("I see the correct location name inPet section title")
    public void iSeeTheCorrectLocationNameInPetSectionTitle() {
        String location = context.get("location_name");
        i_see_pet_section_title(location);
    }

    @Then("I see the correct location name in in Adoptions Section title")
    public void iSeeTheCorrectLocationNameInInAdoptionsSectionTitle() {
        String location = context.get("location_name");
        i_see_in_adoptions_section_title(location);
    }

    @Then("I open random location in new tab")
    public void iOpenRandomLocationInNewTab() {
        page.clickOpenNewTabBtn.click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        String newTabId = driver.getWindowHandles().toArray()[1].toString();
        driver.switchTo().window(newTabId);
    }

    @Given("Add a pet")
    public void addAPet() {
        log.info("Adding a pet");
        page.clickAddPetsBtn.click();
        actions.waitForNumberOfElements(page.pets, 1);
        String petStatus = page.pets.getFirst().status.getText();
        assertThat(petStatus, equalTo("AVAILABLE"));
    }

    @Given("Add a pet with name")
    public void addAPetWithName() {
        String petName = "Uri";
        log.info("Adding a pet name: " + petName);
        actions.clear(page.petNameInput);
        page.petNameInput.sendKeys(petName);
        addAPet();
        context.put("pet_name", petName);
    }

    @Given("I see the pet with name")
    public void iSeeThePetWithName() {
        String petName = context.get("pet_name");
        actions.shouldHaveTextToBe(page.pets.getFirst().name, petName);
    }

    @Given("Create adoption")
    public void createAdoption() {
        log.info("Create adoption");
        page.pets.getFirst().click();
        page.clickAdoptSelectedPetsBtn.click();
        actions.waitForNumberOfElements(page.adoptions, 1);
        String adoptionStatus = page.adoptions.getFirst().status.getText();
        assertThat(adoptionStatus, equalTo("AVAILABLE"));
    }

    @Then("I see the pet with name adopted")
    public void iSeeThePetWithNameAdopted() {
        String petName = context.get("pet_name");
        String newPetStatus = page.pets.getFirst().status.getText();
        assertThat(newPetStatus, equalTo("ONHOLD"));
        String actualAdoptedPetName = page.adoptions.getFirst().pets.getFirst().getText();
        assertThat(actualAdoptedPetName, equalTo(petName));
    }

    @Then("I hover add pet button is highlighted")
    public void iHoverAddPetButtonIsHighlighted() {
        actions.hover(page.clickAddPetsBtn);
        String backColor = page.clickAddPetsBtn.getCssValue("background-color");
        log.info("The background-color is: " + backColor);
        assertThat(backColor, equalTo("rgba(29, 78, 216, 1)"));
    }

    @And("Approve first adoption pet")
    public void approveFirstAdoptionPet() {
        var adoption = page.adoptions.getFirst();
        adoption.clickApproveBtn.click();
    }

    @Then("I see pet adopted")
    public void i_see_pet_adopted() {
        var adoption = page.adoptions.getFirst();
        actions.shouldHaveTextToBe(adoption.status, "APPROVED");
        actions.shouldHaveTextToBe(page.pets.getFirst().status, "ADOPTED");
    }

    @When("I reload page")
    public void i_reload_page() {
        driver.navigate().refresh();
        actions.waitUntilPageToLoad();
    }

    @Then("Pet and Adoption sections are empty")
    public void pet_and_adoption_sections_are_empty() {
        actions.shouldSee(page.emptyPetSection);
        actions.waitForNumberOfElements(page.adoptions, 0);
    }

    @When("I select a pet")
    public void i_select_a_pet() {
        page.pets.getFirst().click();
    }

    @Then("Button is enabled and Pets are selected")
    public void buttonIsEnabledAndPetsAreSelected() {
        assertThat(page.clickDiselectBtn.getAttribute("disabled"), equalTo(null));
        assertThat(page.pets.getFirst().checkedIcon.isDisplayed(), equalTo(true));
    }

    @When("When I deselect the same pet")
    public void when_i_deselect_the_same_pet() {
        page.pets.getFirst().click();
    }

    @Then("Button is disabled and pets are not selected")
    public void button_is_disabled_and_pets_are_not_selected() {
        assertThat(page.clickDiselectBtn.getAttribute("disabled"), equalTo("true"));
        actions.shouldNotBeDisplayed(page.pets.getFirst().checkedIcon);
    }

    @When("Deny first adoption")
    public void deny_first_adoption() {
        var adoption = page.adoptions.getFirst();
        adoption.clickDenyBtn.click();
        actions.shouldHaveTextToBe(adoption.status, "DENIED");
    }

    @When("I see pet not adopted")
    public void i_see_pet_not_adopted() {
        actions.shouldHaveTextToBe(page.pets.getFirst().status, "AVAILABLE");
    }

    @When("Adoptions sections is empty")
    public void adoptions_sections_is_empty() {
        actions.waitForNumberOfElements(page.adoptions, 1);
    }

    @Given("Adopt the firs Pet")
    public void adopt_the_firs_pet() {
        page.pets.getFirst().click();
        page.clickAdoptSelectedPetsBtn.click();
    }

    @Then("Adoption with rejected status is displayed")
    public void adoption_with_rejected_status_is_displayed() {
        actions.waitForNumberOfElements(page.adoptions, 2);
        var adoption = page.adoptions.getFirst();
        actions.shouldHaveTextToBe(adoption.status, "REJECTED");
        actions.shouldHaveTextToBe(adoption.pets.getFirst().errorReason, "ONHOLD");
        actions.shouldHaveTextToBe(adoption.errorMessage, "Some of the pets could not be adopted");
        actions.shouldBeDisplayed(adoption.clickApproveBtn);
        actions.shouldBeDisplayed(adoption.clickDenyBtn);
    }

    @Then("Rejected adoption is Not displayed")
    public void rejectedAdoptionIsNotDisplayed() {
        actions.waitForNumberOfElements(page.adoptions, 1);
        actions.shouldHaveTextToBe(page.adoptions.getFirst().status, "AVAILABLE");
    }

    @Then("I hover adopt selected pets button is highlighted")
    public void iHoverAdoptSelectedPetsButtonIsHighlighted() {
        actions.hover(page.clickAdoptSelectedPetsBtn);
        String backColor = page.clickAdoptSelectedPetsBtn.getCssValue("background-color");
        log.info("The background-color is: " + backColor);
        assertThat(backColor, equalTo("rgba(234, 88, 12, 1)"));
    }
}