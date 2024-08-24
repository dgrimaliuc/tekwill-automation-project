package serghei_rubailo.step_definition.junit.pet_store;

import internal.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class LocationTests extends BaseLocationTest {

    @Test
    @DisplayName("Change Location test")
    public void changeLocationTest() {

        String expectedLocation = "Test Location";

        petStore.location.locationInput.clear();
        petStore.location.locationInput.sendKeys(expectedLocation);
        petStore.location.changeLocationButton.click();

        String actualURL = driver.getCurrentUrl();

        assertThat(actualURL, containsString("?location=" + expectedLocation.replaceAll(" ", "+")));
        assertThat(petStore.petsSection.header.getText(), containsString(expectedLocation));
        assertThat(petStore.adoptionsSection.header.getText(), containsString(expectedLocation));
    }

}
