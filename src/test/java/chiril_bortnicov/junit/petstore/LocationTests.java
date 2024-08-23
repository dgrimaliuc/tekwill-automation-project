package chiril_bortnicov.junit.petstore;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class LocationTests extends BaseLocationTest {

    @Test
    @DisplayName("Change location test")
    public void changeLocationTest() {
        var expectedLocation = "New York";
        actions.clear(petStore.location.locationInput);
        petStore.location.locationInput.clear();
        petStore.location.locationInput.sendKeys(expectedLocation);
        petStore.location.changeLocationBtn.click();
        var url = driver.getCurrentUrl();

        assertThat(url, containsString("?location=" + expectedLocation.replaceAll(" ", "+")));

        var expectedTitleP = "Pets in " + expectedLocation;

        assertThat(petStore.petSection.title.getText(), equalTo(expectedTitleP));

        var expectedTitleA = "Adoptions in " + expectedLocation;


        assertThat(petStore.petSection.title.getText(), equalTo(expectedTitleP));

        assertThat(petStore.adoptionSection.title.getText(), equalTo(expectedTitleA));

    }
}
