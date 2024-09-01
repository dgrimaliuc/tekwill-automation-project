package victor_murashev.junit.petstore;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LocationTests extends BaseLocationTest {

    private final String expectedLocation = "SanFrancisco";

    @BeforeEach
    public void typeLocation() throws InterruptedException {
        petStore.location.locationInput.clear();
        Thread.sleep(1000);
        petStore.location.locationInput.clear();
        petStore.location.locationInput.sendKeys(expectedLocation);
    }

    @Test
    @DisplayName("Change location test")

    public void changeLocationTest() {

        petStore.location.changeLocationBtn.click();
        var currentUrl = driver.getCurrentUrl();


    }

    @Test
    @DisplayName("Change location in new tab")
    public void changeLocationInNewTab() {
        petStore.location.changeLocationBtn.click();
        petStore.location.openInNewTabBtn.click();

        var tabs = driver.getWindowHandles();
        assertThat(tabs.size(), equalTo(2));

        driver.switchTo().window(tabs.toArray()[1].toString());


    }

    @AfterEach
    public void validateLocation() {
        var url = driver.getCurrentUrl();
        var tabs = driver.getWindowHandles();
        assertThat(url, CoreMatchers.containsString("?location=" + expectedLocation.replaceAll(" ", "+")));
        var expectedTitleP = "Pets in " + expectedLocation;
        assertThat(petStore.petsSection.title.getText(), CoreMatchers.equalTo(expectedTitleP));
        var expectedTitleA = "Adoptions in " + expectedLocation;
        assertThat(petStore.adoptionSection.title.getText(), CoreMatchers.equalTo(expectedTitleA));
    }

}
