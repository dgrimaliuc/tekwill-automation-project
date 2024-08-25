package chiril_bortnicov.junit.petstore;

import chiril_bortnicov.ui.petstore.components.PetsSection;
import chiril_bortnicov.ui.petstore.pages.PetStore;
import internal.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PetTests extends BaseTest {

    PetStore petStore = new PetStore(driver);

    PetsSection petsSection = petStore.petsSection;

    @BeforeEach
    public void openRandomLocation() {
        String randomLocation = RandomStringUtils.randomAlphanumeric(10).toUpperCase();
        driver.get("https://petstore-eb41f.web.app/?location=" + randomLocation);
    }

    @Test
    @DisplayName("Add pet test")
    public void addPetTest() {
        String petName = petsSection.petNameInput.getAttribute("value");
        petsSection.addPetBtn.click();

        actions.waitForNumberOfElements(petsSection.pets, 1);
        var pet = petsSection.pets.get(0);
        String actualName = pet.name.getText();
        String actualStatus = pet.status.getText();
        Boolean checkedIcon = pet.isPetChecked();

        assertThat(actualName, equalTo(petName));
        assertThat(actualStatus, equalTo("AVAILABLE"));
        assertThat(checkedIcon, equalTo(false));
    }

    @Test
    @DisplayName("Add pet with name test")
    public void addPetWithNameTest() {
        String petName = "Dog";
        actions.clear(petsSection.petNameInput);
        petsSection.petNameInput.sendKeys(petName);
        petsSection.addPetBtn.click();

        actions.waitForNumberOfElements(petsSection.pets, 1);
        var pet = petsSection.pets.get(0);
        String actualName = pet.name.getText();
        String actualStatus = pet.status.getText();
        Boolean checkedIcon = pet.isPetChecked();

        assertThat(actualName, equalTo(petName));
        assertThat(actualStatus, equalTo("AVAILABLE"));
        assertThat(checkedIcon, equalTo(false));
    }

    @Test
    @DisplayName("Deselect button test")
    public void deselectButtonTest() {
        petsSection.addPets(3);
        actions.waitForNumberOfElements(petsSection.pets, 3);
        var isEnabled = petsSection.deselectBtn.isEnabled();
        assertThat(isEnabled, equalTo(false));
        petsSection.selectFirst(3);

        isEnabled = petsSection.deselectBtn.isEnabled();
        assertThat(isEnabled, equalTo(true));
        petsSection.deselectBtn.click();

        for (var pet : petsSection.pets) {
            Boolean checkedIcon = pet.isPetChecked();
            assertThat(checkedIcon, equalTo(false));
        }
        isEnabled = petsSection.deselectBtn.isEnabled();
        assertThat(isEnabled, equalTo(false));
    }

    @Test
    @DisplayName("After adding pet verify if Adopt Selected Button and Deselect Button is inactive")
    public void afterAddingPetVerifyIfButtonsIsInactive() {
        petsSection.addPets(2);
        actions.waitForNumberOfElements(petsSection.pets, 2);

        for (var pet : petsSection.pets) {
            Boolean checkedIcon = pet.isPetChecked();
            assertThat(checkedIcon, equalTo(false));
        }
        var isDeselectBtnInactive = !petsSection.deselectBtn.isEnabled();
        var isAdoptBtnInactive = !petsSection.adoptBtn.isEnabled();

        assertThat(isDeselectBtnInactive, equalTo(true));
        assertThat(isAdoptBtnInactive, equalTo(true));
    }

    @Test
    @DisplayName("Default buttons state test")
    public void defaultButtonsStateTest() {
        var isDeselectBtnEnabled = petsSection.deselectBtn.isEnabled();
        var isAdoptBtnEnabled = petsSection.adoptBtn.isEnabled();

        assertThat(isDeselectBtnEnabled, equalTo(false));
        assertThat(isAdoptBtnEnabled, equalTo(false));
    }
}
