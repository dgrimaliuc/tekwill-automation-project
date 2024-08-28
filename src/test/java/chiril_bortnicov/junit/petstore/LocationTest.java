package chiril_bortnicov.junit.petstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class LocationTest extends BaseLocationTest {

    @Test
    @DisplayName("Change location test")
    public void changeLocationTest() {
        var expectedLocation = "New York";
        petStore.location.locationInput.clear();
        petStore.location.locationInput.sendKeys(expectedLocation);
        petStore.location.changeLocationBtn.click();
        var url = driver.getCurrentUrl();

        assertThat(url, containsString("?location=" + expectedLocation.replaceAll(" ", "+")));
        var expectedTitleP = "Pets in " + expectedLocation;
        assertThat(petStore.petsSection.title.getText(), equalTo(expectedTitleP));

        var expectedTitleA = "Adoptions in " + expectedLocation;
        assertThat(petStore.adoptionsSection.title.getText(), equalTo(expectedTitleA));

    }
}
