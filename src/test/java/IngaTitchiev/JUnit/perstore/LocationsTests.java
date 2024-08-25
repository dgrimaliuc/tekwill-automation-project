package IngaTitchiev.JUnit.perstore;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static denis_grimaliuc.actions.BaseActions.waitFor;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class LocationsTests extends BaseLocationTest {
    private final String expectedLocation = "SanFrancesco";

    @BeforeEach
    public void typeLocation() {
        //petStore.location.locationInput.clear();
        actions.clear(petStore.location.locationInput);
       // waitFor(2);
            petStore.location.locationInput.sendKeys(expectedLocation);
        waitFor(2);
    }
    @Test
    @DisplayName("Change location test")
    public void changeLocationTest() {

        petStore.location.changeLocationButton.click();
                   }
    @Test
    @DisplayName("Change location in new tab test")
    public void changeLocationInNewTabTest() {
        petStore.location.changeLocationButton.click();
        petStore.location.openInNewTabButton.click();

        var tabs = driver.getWindowHandles();
        assertThat(tabs.size(), equalTo(2));
        driver.switchTo().window(tabs.toArray()[1].toString());
    }

    @Test
    @DisplayName("Open location test")
    public void openLocationTest() {
        var location = "SanFrancesco";
        driver.get("https://petstore-eb41f.web.app/?location=" + location);

        var url = driver.getCurrentUrl();
        assertThat(url, containsString("?location=" + location));
        var expectedTitleP = "Pets in " + location;
        assertThat(petStore.petsSection.title.getText(), equalTo(expectedTitleP));
        var expectedTitleA = "Adoptions in " + location;
        assertThat(petStore.adoptionSection.title.getText(), equalTo(expectedTitleA));

    }

    @AfterEach
    public void validateLocation() {
        var url = driver.getCurrentUrl();
        assertThat(url, containsString("?location=" + expectedLocation.replaceAll(" ", "+")));
        var expectedTitleP = "Pets in " + expectedLocation;
        assertThat(petStore.petsSection.title.getText(), equalTo(expectedTitleP));
        var expectedTitleA = "Adoptions in " + expectedLocation;
        assertThat(petStore.adoptionSection.title.getText(), equalTo(expectedTitleA));
    }

}
