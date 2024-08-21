package IngaTitchiev.JUnit.perstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class LocationsTests extends BaseLocationTest {


    @Test
    @DisplayName("Change location test")
    public void changeLocationTest() {
        var expectedLocation = "Chisinau";
        petStore.location.locationInput.clear();
        petStore.location.locationInput.sendKeys(expectedLocation);
        petStore.location.changeLocationButton.click();
        var url = driver.getCurrentUrl();

        assertThat(url, containsString("?location=" + expectedLocation.replaceAll(" ", "+")));
        var expectedTitleP = "Pets in " + expectedLocation;
        assertThat(petStore.petSection.title.getText(), equalTo(expectedTitleP));

        var expectedTitleA = "Adoptions in " + expectedLocation;
        assertThat(petStore.adoptionSection.title.getText(), equalTo(expectedTitleA));

    }
}
