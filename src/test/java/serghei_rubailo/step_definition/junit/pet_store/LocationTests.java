package serghei_rubailo.step_definition.junit.pet_store;

import internal.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class LocationTests extends BaseLocationTest {

    private final String expectedLocation = "Test Location";

    @BeforeEach
    public void fillLocation() {
        petStore.location.locationInput.clear();
        petStore.location.locationInput.sendKeys(expectedLocation);
    }

    @Test
    @DisplayName("Change Location test")
    public void changeLocationTest() {
        petStore.location.changeLocationButton.click();
    }

    @Test
    @DisplayName("Change location in the new tab test")
    public void changeLocationNewTabTest() {
        petStore.location.changeLocationButton.click();
        petStore.location.openInNewTabButton.click();

        var tabs = driver.getWindowHandles();
        driver.switchTo().window(tabs.toArray()[1].toString());

        assertThat(tabs.size(), equalTo(2));
    }

    @Test
    @DisplayName("Change location in the location url")
    public void changeLocationInURL() {
        driver.get("https://petstore-eb41f.web.app/?location=" + expectedLocation.replaceAll(" ", "+"));
    }

    @AfterEach
    public void validateLocation() {
        String actualURL = driver.getCurrentUrl();

        assertThat(actualURL, containsString("?location=" + expectedLocation.replaceAll(" ", "+")));
        assertThat(petStore.petsSection.header.getText(), containsString(expectedLocation));
        assertThat(petStore.adoptionsSection.header.getText(), containsString(expectedLocation));
    }
}
