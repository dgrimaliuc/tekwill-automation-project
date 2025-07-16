package example.junit.petstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class LocationTests extends BasePetstoreTest {
    private final String expectedLocation = "New York";

    @BeforeEach
    public void typeLocation() {
        petStore.location.locationInput.clear();
        petStore.location.locationInput.sendKeys(expectedLocation);
    }

    @Test
    @DisplayName("Change location test")
    public void changeLocationTest() {
        petStore.location.changeLocationBtn.click();
    }

    @Test
    @DisplayName("Change location in new tab test")
    public void changeLocationInNewTabTest() {
        petStore.location.changeLocationBtn.click();
        petStore.location.openInNewTabBtn.click();

        var tabs = driver.getWindowHandles();
        assertThat(tabs.size(), equalTo(2));
        driver.switchTo().window(tabs.toArray()[1].toString());
    }

    @Test
    @DisplayName("Open location test")
    public void openLocationTest() {
        var location = "SanFrancisco";
        driver.get("https://petstore-eb41f.web.app/?location=" + location);
    }
}
