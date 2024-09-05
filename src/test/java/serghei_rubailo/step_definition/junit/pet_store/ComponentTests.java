package serghei_rubailo.step_definition.junit.pet_store;

import internal.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import serghei_rubailo.ui.pet_store.components.Pet;
import serghei_rubailo.ui.pet_store.pages.PetStore;

import static denis_grimaliuc.actions.BaseActions.waitFor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ComponentTests extends BaseTest {

    PetStore petStore = new PetStore(driver);

    @Test
    @DisplayName("Button Change Location Hover Color Test")
    public void buttonChangeLocationHoverColorTest() {

        petStore.openPage();

        String currentColor = petStore.location.changeLocationButton.getCssValue("background-color");

        actions.hover(petStore.location.changeLocationButton);

        String hoverColor = petStore.location.changeLocationButton.getCssValue("background-color");

        assertThat(currentColor, equalTo("rgba(37, 99, 235, 1)"));
        assertThat(hoverColor, equalTo("rgba(29, 78, 216, 1)"));
    }

    @Test
    @DisplayName("Button Adopt Selected Pets Hover Color Test")
    public void buttonAdoptPetsHoverColorTest() {
        String randomLocation = RandomStringUtils.randomAlphanumeric(20);
        driver.get("https://petstore-eb41f.web.app/?location=" + randomLocation);

        petStore.petsSection.addPetButton.click();
        actions.waitForNumberOfElements(petStore.petsSection.pets, 1);

        Pet pet = petStore.petsSection.pets.get(0);
        pet.click();
        waitFor(1);
        String currentColor = petStore.petsSection.adoptButton.getCssValue("background-color");

        actions.hover(petStore.petsSection.adoptButton);

        String hoverColor = petStore.petsSection.adoptButton.getCssValue("background-color");

        assertThat(currentColor, equalTo("rgba(249, 115, 22, 1)"));
        assertThat(hoverColor, equalTo("rgba(234, 88, 12, 1)"));
    }

}
