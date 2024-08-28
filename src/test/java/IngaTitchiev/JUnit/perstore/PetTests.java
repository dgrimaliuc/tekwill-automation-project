package IngaTitchiev.JUnit.perstore;

import IngaTitchiev.JUnit.perstore.components.PetsSection;
import IngaTitchiev.JUnit.perstore.pages.PetStore;

import internal.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PetTests extends BaseTest {

    PetStore petStore = new PetStore(driver);
   PetsSection petSection = petStore.petsSection;

    @BeforeEach
    public void openRandomLocation() {
        String randomLocation = RandomStringUtils.randomAlphanumeric(10).toUpperCase();
        driver.get("https://petstore-eb41f.web.app/?location=" + randomLocation);
    }

    @Test
    @DisplayName("Add pet test")
    public void addPetTest() {
        String petName = petStore.petsSection.petNameInput.getAttribute("value");
        petStore.petsSection.addPetButton.click();

        actions.waitForNumberOfElements(petStore.petsSection.pets, 1);
        var pet = petStore.petsSection.pets.get(0);
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
        actions.clear(petStore.petsSection.petNameInput);
        petStore.petsSection.petNameInput.sendKeys(petName);
        petStore.petsSection.addPetButton.click();

        actions.waitForNumberOfElements(petStore.petsSection.pets, 1);
        var pet = petStore.petsSection.pets.get(0);
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
        var petSection = petStore.petsSection;
        petSection.addPets(2);
        actions.waitForNumberOfElements(petSection.pets, 2);

        petSection.selectFirst(2);
        var isEnabled = petSection.deselectButton.isEnabled();
        assertThat(isEnabled, equalTo(true));

        petSection.deselectButton.click();
        for (var pet : petSection.pets) {
            Boolean checkedIcon = pet.isPetChecked();
            assertThat(checkedIcon, equalTo(false));
        }
        isEnabled = petSection.deselectButton.isEnabled();
        assertThat(isEnabled, equalTo(false));
    }

    @Test
    @DisplayName("Default buttons state test")
    public void defaultButtonsStateTest() {

        var isDeselectBtnEnabled = petSection.deselectButton.isEnabled();
        var isAdoptBtnEnabled = petSection.adoptionButton.isEnabled();
        assertThat(isDeselectBtnEnabled, equalTo(false));
        assertThat(isAdoptBtnEnabled, equalTo(false));
    }


}
