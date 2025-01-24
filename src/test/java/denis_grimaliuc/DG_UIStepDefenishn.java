package denis_grimaliuc;

import denis_grimaliuc.poms.DGPetStorePage;
import example.actions.BaseActions;
import example.data.enums.OS;
import internal.ChromeDriverProvider;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static denis_grimaliuc.poms.DGPetStorePage.allAdoptions;
import static denis_grimaliuc.poms.DGPetStorePage.allPets;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DG_UIStepDefenishn {

    ChromeDriver driver = null;
    WebDriverWait wait = null;
    BaseActions actions = null;
    DGPetStorePage page = null;

    @Before
    public void before() {
        driver = new ChromeDriverProvider(OS.MAC).getDriver();
        actions = new BaseActions(driver);
        wait = new WebDriverWait(driver, 10);
        page = new DGPetStorePage(driver);
    }


    @Given("Open Petstore")
    public void open_petstore() {
        open_petstore_in_chisinau("");
    }


    @Given("Open Petstore in {string}")
    public void open_petstore_in_chisinau(String location) {
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
}
