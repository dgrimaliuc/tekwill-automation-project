package serghei_rubailo.step_definition.junit.pet_store;

import internal.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import serghei_rubailo.ui.pet_store.components.Adoption;
import serghei_rubailo.ui.pet_store.components.Pet;
import serghei_rubailo.ui.pet_store.pages.PetStore;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AdoptionTests extends BaseTest {

    PetStore petStore = new PetStore(driver);

    @BeforeEach
    public void openPageWithRandomLocation() {
        String randomLocation = RandomStringUtils.randomAlphanumeric(20);
        driver.get("https://petstore-eb41f.web.app/?location=" + randomLocation);
    }

    @Test
    @DisplayName("Adoption creation test")
    public void adoptionCreationTest() {
        petStore.petsSection.addPetButton.click();
        actions.waitForNumberOfElements(petStore.petsSection.pets, 1);

        Pet pet = petStore.petsSection.pets.get(0);

        pet.petCheckbox.click();
        petStore.petsSection.adoptButton.click();
        actions.waitForNumberOfElements(petStore.adoptionsSection.adoptions, 1);

        Adoption adoption = petStore.adoptionsSection.adoptions.get(0);

        assertThat(pet.petStatus.getText(), equalTo("ONHOLD"));
        assertThat(pet.petName.getText(), equalTo(adoption.pets.get(0).getText()));
        assertThat(adoption.status.getText(), equalTo("AVAILABLE"));
    }
}
