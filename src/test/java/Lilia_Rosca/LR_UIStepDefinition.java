package Lilia_Rosca;

import Lilia_Rosca.poms.LR_petStorePage;
import example.actions.BaseActions;
import example.data.enums.OS;
import internal.ChromeDriverProvider;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static Lilia_Rosca.poms.LR_petStorePage.allAdoptions;
import static denis_grimaliuc.poms.DGPetStorePage.allPets;
import static example.actions.BaseActions.waitFor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LR_UIStepDefinition {

    ChromeDriver driver = null;
    WebDriverWait wait = null;
    BaseActions actions = null;
    LR_petStorePage page = null;

    @Before
    public void before() {
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

}
