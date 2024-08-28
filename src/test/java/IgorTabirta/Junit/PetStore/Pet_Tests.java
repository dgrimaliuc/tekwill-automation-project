package IgorTabirta.Junit.PetStore;

import IgorTabirta.UI.PetStore.Page.PetStore;
import internal.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Pet_Tests extends BaseTest {

    PetStore petStore = new PetStore(driver);

    @BeforeEach
    public void OpenRandomLocation() {

        String randomLocation = RandomStringUtils.randomAlphabetic(10).toUpperCase();
        driver.get("https://petstore-eb41f.web.app/?location=" + randomLocation);

    }

    @Test
    @DisplayName("Add Pet Test")
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
    @DisplayName("Add Pet with name test")
    public void addPetWithNameTest() {
        String petName = "Dog";
        actions.clear(petStore.petsSection.petNameInput);
        petStore.petsSection.petNameInput.sendKeys(petName);
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
    @DisplayName("Deselect Btn test")
    public void DeselectBtnTest() {
        var petSection = petStore.petsSection;
        petStore.petsSection.addPets(3);
        actions.waitForNumberOfElements(petStore.petsSection.pets, 3);
        var isEnabled = petSection.deselectBtn.isEnabled();
        assertThat(isEnabled, equalTo(false));
        petSection.selectFirst(3);

        isEnabled = petSection.deselectBtn.isEnabled();
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
    @DisplayName("Verify empty state disappears when a pet is aded")
    public void emptyStateDisappersTest() {

        String randomLocation = RandomStringUtils.randomAlphabetic(17).toUpperCase();
        petStore.openPage(randomLocation);
        var defaultText = petStore.petsSection.defaultText.getText();
        assertThat(defaultText, equalTo("No rows. Try reset filters"));
        petStore.petsSection.addPets(1);
        petStore.petsSection.addPetBtn.click();
        actions.shouldNotSee(petStore.petsSection.defaultText);

    }

}

