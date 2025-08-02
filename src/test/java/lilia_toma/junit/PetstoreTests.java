package lilia_toma.junit;

import internal.BaseTest;
import lilia_toma.Petstore.PetstorePage;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static example.actions.BaseActions.waitFor;

public class PetstoreTests extends BaseTest {
    PetstorePage pet = new PetstorePage(driver);


    @BeforeEach
    public void setup() {
        log.info("Opening the Petstore page");
        driver.get("https://petstore-eb41f.web.app/");
    }

    @Test
    public void theVisibilityTest() {
        actions.shouldBeDisplayed(pet.location);
        actions.shouldBeDisplayed(pet.theGame);
        actions.shouldBeDisplayed(pet.pets);
        actions.shouldBeDisplayed(pet.adoptions);
    }

    @ParameterizedTest(name = "Change location test '{0}'")
    @ValueSource(strings = {"Fălești", "0cnita", "Vadul-lui-Vodă", "/\1234.@#$%^&*()"})
    public void changeLocationButtonTest(String text) {
        pet.locationInput.click();
        pet.locationInput.clear();
        pet.locationInput.sendKeys(text);
        }

    @Test
    public void defaultLocationPlettTest() {
        actions.shouldBeDisplayed(pet.defaultPlett);
    }

    @Test
    public void openInNewTabButtonTest() {
        actions.shouldBeDisplayed(pet.openInNewTabBtn);
        pet.openInNewTabBtn.click();
        actions.shouldSee(pet.openInNewTabBtn);
    }

    @Test
    public void accesingRandomLocationTest() {
        String location = "Chisinau";
        String fullURL = "https://petstore-eb41f.web.app/" + "?location=" + location;
        driver.get(fullURL);
        waitFor(3);

        actions.shouldBeDisplayed(pet.verifyLocation);
        WebElement locationTitle = driver.findElement(By.cssSelector("h2.text-2xl.ml-4"));

        String locationInputValue = pet.locationInput.getAttribute("value");
        MatcherAssert.assertThat(locationInputValue, CoreMatchers.containsString(location));

        String petsText = pet.pets.getText();
        MatcherAssert.assertThat(petsText, CoreMatchers.containsString(location));

        String adoptionsText = pet.adoptions.getText();
        MatcherAssert.assertThat(adoptionsText, CoreMatchers.containsString(location));

        String theGameText = pet.theGame.getText();
        MatcherAssert.assertThat(theGameText, CoreMatchers.containsString(location));
    }
}
