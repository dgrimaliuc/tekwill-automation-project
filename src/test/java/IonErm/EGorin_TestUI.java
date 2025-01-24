package IonErm;

import IonErm.poms.IonErm_PetStorePage;
import example.actions.BaseActions;
import example.data.enums.OS;
import internal.ChromeDriverProvider;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static example.actions.BaseActions.waitFor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EGorin_TestUI {

    ChromeDriver driver = null;
    WebDriverWait wait = null;
    BaseActions actions = null;
    IonErm_PetStorePage page = null;

    @Before
    public void before() {
        driver = new ChromeDriverProvider(OS.WINDOWS).getDriver();
        wait = new WebDriverWait(driver, 10);
        actions = new BaseActions(driver);
        page = new IonErm_PetStorePage(driver);
    }

    @Given("Open the PetStore")
    public void open_the_pet_store() {
        openTheApp("");
        waitFor(2);
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
    }

    @Then("Validate the info section in {string}")
    public void validateTheInfoSectionIn(String location) {
//        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//tr[@data-t='single-pet']"), 0));
        actions.waitForNumberOfElementsToBeMoreThan(By.xpath("//tr[@data-t='single-pet']"), 0);
        var petsText = page.petsText.getText();
        var adoptionText = page.adoptionText.getText();
        var petsCount = driver.findElements(By.xpath("//tr[@data-t='single-pet']")).size();
        var adoptionsCount = driver.findElements(By.xpath("//div[@data-t='single-adoption']")).size();
        waitFor(2);
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


}