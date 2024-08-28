package chiril_bortnicov.junit.petstore;


import org.apache.commons.lang.RandomStringUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static denis_grimaliuc.actions.BaseActions.waitFor;
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

        assertThat(petStore.petsSection.title.getText(), equalTo(expectedTitleP));

        var expectedTitleA = "Adoptions in " + expectedLocation;


        assertThat(petStore.petsSection.title.getText(), equalTo(expectedTitleP));

        assertThat(petStore.adoptionsSection.title.getText(), equalTo(expectedTitleA));

    }

    @Test
    @DisplayName("Change location in new tab test")
    public void changeLocationInNewTabTest() {
        petStore.location.changeLocationBtn.click();
        petStore.location.openInNewTabBtn.click();

        var tabs = driver.getWindowHandles();
        assertThat(tabs.size(), CoreMatchers.equalTo(2));
        driver.switchTo().window(tabs.toArray()[1].toString());
    }

    @Test
    @DisplayName("Add pet in different location is not reflected in current one test")
    public void integrationLocationTest() {
        var petsSection = petStore.petsSection;
        petsSection.addPets(1);
        waitFor(1);
        var pet = petsSection.pets.get(0);
        pet.click();
        petsSection.adoptBtn.click();
        waitFor(1);

        petStore.openPage(RandomStringUtils.randomAlphanumeric(17).toUpperCase());

        String defaultText = petsSection.defaultText.getText();
        assertThat(defaultText, equalTo("No rows. Try reset filters"));

        assertThat(petStore.adoptionsSection.adoptions.size(), equalTo(0));
    }
}
