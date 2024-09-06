package serghei_rubailo.step_definition.junit.pet_store;

import helpers.customElements.Components;
import internal.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import serghei_rubailo.ui.pet_store.components.Pet;
import serghei_rubailo.ui.pet_store.pages.PetStore;

import static denis_grimaliuc.actions.BaseActions.getRandomString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PetTest extends BaseTest {

    PetStore petStore = new PetStore(driver);

    @BeforeEach
    public void openPageWithRandomLocation() {
        String randomLocation = RandomStringUtils.randomAlphanumeric(20);
        driver.get("https://petstore-eb41f.web.app/?location=" + randomLocation);
    }

    @Test
    @DisplayName("Add pet test")
    public void addPetTest() {
        String expectedPetName = petStore.petsSection.petNameInput.getAttribute("value");
        petStore.petsSection.addPets(1);

        Components<Pet> pets = petStore.petsSection.pets;
        actions.waitForNumberOfElements(pets, 1);

        Pet pet = pets.get(0);
        assertThat(pet.petName.getText(), equalTo(expectedPetName));
        assertThat(pet.petStatus.getText(), equalTo("AVAILABLE"));
        assertThat(pet.isPetChecked(), equalTo(false));
    }

    @Test
    @DisplayName("Add pet with name test")
    public void addPetWithNameTest() {
        String expectedPetName = "Test Pet Name";

        petStore.petsSection.petNameInput.clear();
        petStore.petsSection.petNameInput.sendKeys(expectedPetName);
        petStore.petsSection.addPetButton.click();

        Components<Pet> pets = petStore.petsSection.pets;
        actions.waitForNumberOfElements(pets, 1);

        Pet pet = pets.get(0);

        assertThat(pet.petName.getText(), equalTo(expectedPetName));
        assertThat(pet.petStatus.getText(), equalTo("AVAILABLE"));
        assertThat(pet.isPetChecked(), equalTo(false));
    }

    @Test
    @DisplayName("Deselect Button test")
    public void deselectButtonTest() {
        int petCount = 2;
        petStore.petsSection.addPets(petCount);
        Components<Pet> pets = petStore.petsSection.pets;
        actions.waitForNumberOfElements(pets, petCount);
        petStore.petsSection.selectFirst(petCount);

        assertThat(petStore.petsSection.deselectButton.isEnabled(), equalTo(true));

        petStore.petsSection.deselectButton.click();

        for (Pet pet : pets) {
            assertThat(pet.isPetChecked(), equalTo(false));
        }

        assertThat(petStore.petsSection.deselectButton.isEnabled(), equalTo(false));
    }

    @Test
    @DisplayName("Adopt Select Pets and Deselect buttons are disabled by default")
    public void adoptDeselectButtonsDisabled() {
        assertThat(petStore.petsSection.deselectButton.isEnabled(), equalTo(false));
        assertThat(petStore.petsSection.adoptButton.isEnabled(), equalTo(false));
    }

    @Test
    @DisplayName("Empty state disappears when pet is added")
    public void emptyStateDisappearTest() {
        String randomLocation = getRandomString(18);
        petStore.openPage(randomLocation);

        String defaultText = petStore.petsSection.defaultText.getText();

        assertThat(defaultText, equalTo("No rows. Try reset filters"));
        petStore.petsSection.addPets(1);
        actions.shouldNotSee(petStore.petsSection.defaultText);

    }


}
