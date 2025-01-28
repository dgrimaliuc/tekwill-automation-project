package IonErm.pro;

import example.actions.BaseActions;
import example.data.enums.OS;
import internal.ChromeDriverProvider;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.chrome.ChromeDriver;

import static example.actions.BaseActions.waitFor;

public class EGorin_PetStore_Test {

    ChromeDriver driver = null;
    BaseActions actions = null;

    @Before
    public void before() {
        driver = new ChromeDriverProvider(OS.WINDOWS).getDriver();
        actions = new BaseActions(driver);
    }

    @Given("Open the app {string}")
    public void open_the_app(String location) {
        driver.get("https://petstore-eb41f.web.app/?location=" + location);
        actions.waitUntilPageToLoad();
    }

    @Then("Put a random string and change location")
    public void put_a_random_string_and_change_location() {
        String randomName = RandomStringUtils.randomAlphanumeric(10);
        open_the_app(randomName);
        waitFor(2);
    }

    @Then("I see input location")
    public void iSeeInputLocation() {
    }

    @Then("Open in new tab")
    public void open_in_new_tab() {

    }

}
