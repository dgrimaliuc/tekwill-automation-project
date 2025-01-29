package Lilia_Rosca.LR_StepsDef;

import Lilia_Rosca.poms.LR_petStorePage;
import example.actions.BaseActions;
import example.data.enums.OS;
import internal.ChromeDriverProvider;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

import static Lilia_Rosca.poms.LR_petStorePage.allAdoptions;
import static denis_grimaliuc.poms.DGPetStorePage.allPets;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
// de sters dupa git pull 29.01
import static org.openqa.selenium.support.ui.ExpectedConditions.attributeToBe;

public class LR_UIStepDefinition {

    ChromeDriver driver = null;
    WebDriverWait wait = null;
    BaseActions actions = null;
    LR_petStorePage page = null;
    Map<String, String> context = null;

    Logger log = Logger.getLogger(LR_UIStepDefinition.class);

    @Before
    public void before() {
        context = new HashMap<>();
        driver = new ChromeDriverProvider(OS.WINDOWS).getDriver();
        actions = new BaseActions(driver);
        wait = new WebDriverWait(driver, 10);
        page = new LR_petStorePage(driver);
    }

    @Given("Open Petstore")
    public void open_petstore() {
        openPetstoreLocation("");
    }

    @Given("Open Petstore location {string}")
    public void openPetstoreLocation(String location) {
        driver.get("https://petstore-eb41f.web.app/?location=" + location);
        actions.waitUntilPageToLoad(); // asteapta pina se incarca pagina
    }

    @Then("I see {string} in location input")
    public void i_see_in_location_input(String string) {
        // var input = driver.findElement(By.xpath("//input[@id='location-input']"));
        // String locationText = input.getAttribute("value");
        String locationText = page.locationInput.getAttribute("value");
        assertThat(locationText, equalTo(string));
    }

    @Then("I see {string} in Pets Section title")
    public void i_see_in_pets_section_title(String string) {
        // var title = driver.findElement(By.xpath("//div[@data-t='pets-section']/h2"));
        // String titleText = title.getText();
        String titleText = page.petSectionTitle.getText();
        assertThat(titleText, equalTo("Pets in " + string));
    }

    @Then("I see {string} in Adoption Section title")
    public void i_see_in_adoption_section_title(String string) {
       // var title = driver.findElement(By.xpath("//div[@data-t='adoptions-section']/h2"));
       // String titleText = title.getText();
        String titleText = page.adoptionSectionTitle.getText();
        assertThat(titleText, equalTo("Adoptions in " + string));
    }

    @Then("I validate the Info Section")
    public void iValidateTheInfoSection() {
        // fara aceasta test crush
        //waitFor(1); //var 1
        // var 2 - wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//tr[@data-t='single-pet']"), 0));
        // var 3 - actions.waitForNumberOfElementsToBeMoreThan(By.xpath("//tr[@data-t='single-pet']"), 0);
        // var 4
        actions.waitForNumberOfElementsToBeMoreThan(allPets, 0);

        var petsCountText = driver.findElement(By.xpath("//p[@data-t ='pets-count']")).getText();
        var adoptionsCountText = driver.findElement(By.xpath("//p[@data-t ='adoptions-count']")).getText();
        var petsCount = driver.findElements(allPets).size();
        var adoptionsCount = driver.findElements(allAdoptions).size();
        assertThat(petsCountText, equalTo("Pets in Chisinau: " + petsCount));
        assertThat(adoptionsCountText, equalTo("Adoptions in Chisinau: " + adoptionsCount));
    }

    @When("Change location to {string}")
    public void change_location_to(String newLocation) {
        // page.locationInput.clear(); in cazul dat nu merge - cimpula are valoarea care se sterge doar cu backspace
        actions.clear(page.locationInput);
        page.locationInput.sendKeys(newLocation); // introduce valoare in location input
        page.buttonChangeLocation.click();
        actions.waitUntilPageToLoad(); // asteapta pina se incarca pagina
    }

    @When("Open a random location")
    public void open_a_random_location() {
        String locationName = RandomStringUtils.randomAlphanumeric(9);
        log.info("Opening a random location with name " + locationName);
        openPetstoreLocation(locationName);
        context.put("location_name", locationName);
    }

    @Then("I see correct location name in location input")
    public void i_see_correct_location_name_in_location_input() {
        String location = context.get("location_name");
        i_see_in_location_input(location);
    }

    @Then("I see correct location name in Pets Section title")
    public void i_see_correct_location_name_in_pets_section_title() {
        String location = context.get("location_name");
        i_see_in_pets_section_title(location);
    }

    @Then("I see correct location name in Adoption Section title")
    public void i_see_correct_location_name_in_adoption_section_title() {
        String location = context.get("location_name");
        i_see_in_adoption_section_title(location);
    }

/*    @When("I open a random location in new tab") - atunci cinda va lucra - introduci locatia, apesi butonul, se deschide pagina
    public void iOpenARandomLocationInNewTab() {
        String locationName = RandomStringUtils.randomAlphanumeric(9);
        actions.clear(page.locationInput);
        page.locationInput.sendKeys(locationName);
        page.buttonOpenInNewTab.click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2)); // va astepta pina se deschide a doua pagina
        String newTabId = driver.getWindowHandles().toArray()[1].toString();
        driver.switchTo().window(newTabId); // driver.getWindowHandles() - in debug arata id la toate paginile deschise
        context.put("location_name", locationName);
    }*/
    @When("I open a random location in new tab")
    public void iOpenARandomLocationInNewTab() {
        page.buttonOpenInNewTab.click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2)); // va astepta pina se deschide a doua pagina
        String newTabId = driver.getWindowHandles().toArray()[1].toString();
        driver.switchTo().window(newTabId); // driver.getWindowHandles() - in debug arata id la toate paginile deschise
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
        String petName = "Fluffy";
        log.info("Adding a pet with name " + petName);
        actions.clear(page.petNameInput);
        page.petNameInput.sendKeys(petName);
/*        page.addPetButton.click();
        actions.waitForNumberOfElements(page.pets, 1);*/ // sau
        addAPet(); // reutilizam metoda de mai sus pentru a elimina dublarile de cod
        context.put("pet_name", petName);

    }

    @Then("I see the pet with name added")
    public void iSeeThePetWithNameAdded() {
        String petName = context.get("pet_name");
        actions.shouldHaveTextToBe(page.pets.getFirst().name, petName);
    }

    @And("Create adoption")
    public void createAdoption() {
        page.pets.getFirst().click();
        page.adoptSelectedButton.click();
        actions.waitForNumberOfElements(page.adoptions, 1);
        String adoptionStatus = page.adoptions.getFirst().status.getText();
    }

    @Then("I see the pet with name adopted")
    public void iSeeThePetWithNameAdopted() {
        String petName = context.get("pet_name");
        String actualAdoptedPetName = page.adoptions.getFirst().pets.getFirst().getText();
        assertThat(actualAdoptedPetName, equalTo(petName));

        String newPetStatus = page.pets.getFirst().status.getText();
        assertThat(newPetStatus, equalTo("ONHOLD"));
    }
// 27.01
    @Then("I hover the Add pet button it is highlighted")
    public void iHoverTheAddPetButtonItIsHighlighted() {
    //    System.out.println("Before: " + page.addPetButton.getCssValue("background-color")); - culoare butonului
        actions.hover(page.addPetButton);
    //    System.out.println("After: " + page.addPetButton.getCssValue("background-color")); - culoare  butonului cu mouse pe el
        String backColour = page.addPetButton.getCssValue("background-color");
        assertThat(backColour, equalTo("rgba(29, 78, 216, 1)"));
    }
// HW 27.01 ex 1
    @Then("I hover the Adopt Selected pets button it is highlighted")
    public void iHoverTheAdoptSelectedPetsButtonItIsHighlighted() {
        actions.hover(page.adoptSelectedButton);
        String backColour = page.adoptSelectedButton.getCssValue("background-color");
        assertThat(backColour, equalTo("rgba(209, 213, 219, 1)"));
    }
// 27.01 - continuare
    @And("Approve first adoption")
    public void approveFirstAdoption() {
        var adoption = page.adoptions.getFirst();
        adoption.approveButton.click();
    }

    @Then("I see the pet adopted")
    public void iSeeThePetAdopted() {
        var adoption = page.adoptions.getFirst();
        actions.shouldHaveTextToBe(adoption.status, "APPROVED");
        actions.shouldHaveTextToBe(page.pets.getFirst().status, "ADOPTED");
    }

    @When("I reload page")
    public void iReloadPage() {
        driver.navigate().refresh();
        actions.waitUntilPageToLoad();
    }

    @Then("Pets and Adoptions are empty")
    public void petsAndAdoptionsAreEmpty() {
        actions.shouldSee(page.emptyPageMessage);
        adoptionSectionIsEmpty(); // modificat 27.01
    }

    @When("I select a pet")
    public void iSelectAPet() {
        page.pets.getFirst().click();
    }

/*  nu este necesar
public void shouldHaveAttribute(WebElement element, String attribute, String value) {
        wait.until(attributeToBe(element, attribute, value));
}*/

    @Then("Button is enabled and pet is selected")
    public void buttonIsEnabledAndPetIsSelected() {
        page.deselectButton.getAttribute("disabled");
        // dupa git pull de modificat in - actions.shouldHaveAttribute(page.deselectButton, "disabled", null);
        // ceva nu e bine shouldHaveAttribute(page.deselectButton, "disabled", null);
        assertThat(page.deselectButton.getAttribute("disabled"), equalTo(null));
        assertThat(page.pets.getFirst().checkedIcon.isDisplayed(), equalTo(true));
    }

    @When("I deselect the same pet")
    public void iDeselectTheSamePet() {
        page.pets.getFirst().click();
    }

    @Then("Button is disabled and pet is not selected")
    public void buttonIsDisabledAndPetIsNotSelected() {
        assertThat(page.deselectButton.getAttribute("disabled"), equalTo("true"));
        actions.shouldNotBeDisplayed(page.pets.getFirst().checkedIcon);
 //       assertThat(page.pets.getFirst().checkedIcon.isDisplayed(), equalTo(true));
 //       driver.findElements(null).size() == 0; // verifica daca elementul cu xpath indicat este
    }

    @And("Deny first adoption")
    public void denyFirstAdoption() {
        var adoption = page.adoptions.getFirst();
        adoption.denyButton.click();
        actions.shouldHaveTextToBe(adoption.status, "DENIED");
    }

    @Then("I see pet not adopted")
    public void iSeePetNotAdopted() {
        actions.shouldHaveTextToBe(page.pets.getFirst().status, "AVAILABLE");
    }

    @Then("Adoption section is empty")
    public void adoptionSectionIsEmpty() {
        actions.waitForNumberOfElements(page.adoptions, 0);
    }

    @And("Adopt first pet")
    public void adoptFirstPet() {
        page.pets.getFirst().click();
        page.adoptSelectedButton.click();
    }
/*    // dupa git pull de sters
    public void shouldBeDisabled(WebElement element) {
        log.trace("Check if element is disabled " + element);
        wait.until(ExpectedConditions.attributeToBe(element, "disabled", "true"));
    }*/

    @Then("Adoption with rejected status is displayed")
    public void adoptionWithRejectedStatusIsDisplayed() {
        actions.waitForNumberOfElements(page.adoptions, 2);
        var adoption = page.adoptions.getFirst();
        actions.shouldHaveTextToBe(adoption.status, "REJECTED");
        actions.shouldHaveTextToBe(adoption.pets.getFirst().errorReason, "ONHOLD");
        actions.shouldHaveTextToBe(adoption.error, "Some of the pets could not be adopted");
        actions.shouldBeDisabled(adoption.approveButton);
        actions.shouldBeDisabled(adoption.denyButton);
    }

    @Then("Rejected adoption is not displayed")
    public void rejectedAdoptionIsNotDisplayed() {
        actions.waitForNumberOfElements(page.adoptions, 1);
        actions.shouldHaveTextToBe(page.adoptions.getFirst().status, "AVAILABLE");
    }
}
