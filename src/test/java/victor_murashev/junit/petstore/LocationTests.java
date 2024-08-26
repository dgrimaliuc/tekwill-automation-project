package victor_murashev.junit.petstore;

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
        petStore.location.locationInput.clear();
        petStore.location.locationInput.sendKeys("New York");
        petStore.location.changeLocationBtn.click();
        var currentUrl = driver.getCurrentUrl();

        assertThat(currentUrl, containsString("?location=" + expectedLocation.replaceAll(" ", "+")));

        var expectedTitlePets = "Pets in " + expectedLocation;
        assertThat(petStore.petsSection.title.getText(), equalTo(expectedTitlePets));

        var expectedTitleAdoption = "Adoptions in " + expectedLocation;
        assertThat(petStore.adoptionSection.title.getText(), equalTo(expectedTitleAdoption));

        System.out.println();
    }
}
