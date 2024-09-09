package denis_grimaliuc.junit.petstore;

import denis_grimaliuc.ui.petstore.components.PetsSection;
import denis_grimaliuc.ui.petstore.pages.PetStore;
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
        petStore.petsSection.addPetBtn.click();

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
        petSection.petNameInput.sendKeys(petName);
        petStore.petsSection.addPetBtn.click();

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
        petStore.addPets(2);
        actions.waitForNumberOfElements(petSection.pets, 2);

        petSection.selectFirst(2);
        var isEnabled = petSection.deselectBtn.isEnabled();
        assertThat(isEnabled, equalTo(true));

        petSection.deselectBtn.click();
        for (var pet : petSection.pets) {
            Boolean checkedIcon = pet.isPetChecked();
            assertThat(checkedIcon, equalTo(false));
        }

        isEnabled = petSection.deselectBtn.isEnabled();
        assertThat(isEnabled, equalTo(false));
    }

    @Test
    @DisplayName("Default buttons state test")
    public void defaultButtonsStateTest() {

        var isDeselectBtnEnabled = petSection.deselectBtn.isEnabled();
        var isAdoptBtnEnabled = petSection.adoptBtn.isEnabled();
        assertThat(isDeselectBtnEnabled, equalTo(false));
        assertThat(isAdoptBtnEnabled, equalTo(false));
    }

    @Test
    @DisplayName("Verify empty state disappears when a pet is added")
    public void emptyStateDisappearsTest() {
        var defaultText = petSection.defaultText.getText();

        assertThat(defaultText, equalTo("No rows. Try reset filters"));
        petStore.addPets(1);
        actions.waitForNumberOfElements(petSection.pets, 1);
        actions.shouldNotSee(petSection.defaultText);

    }


}
