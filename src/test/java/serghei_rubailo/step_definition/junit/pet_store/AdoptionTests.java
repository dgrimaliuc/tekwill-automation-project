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

    @Test
    @DisplayName("Adoption approve test")
    public void adoptionApproveTest() {
        petStore.petsSection.addPets(1);
        actions.waitForNumberOfElements(petStore.petsSection.pets, 1);

        petStore.petsSection.pets.get(0).click();
        petStore.petsSection.adoptButton.click();
        actions.waitForNumberOfElements(petStore.adoptionsSection.adoptions, 1);

        Adoption adoption = petStore.adoptionsSection.adoptions.get(0);
        adoption.approveButton.click();

        actions.shouldHaveTextToBe(petStore.adoptionsSection.adoptions.get(0).status, "APPROVED");
        actions.shouldHaveTextToBe(petStore.petsSection.pets.get(0).petStatus, "ADOPTED");
        assertThat(petStore.adoptionsSection.adoptions.get(0).approveButton.isEnabled(), equalTo(false));
        assertThat(petStore.adoptionsSection.adoptions.get(0).denyButton.isEnabled(), equalTo(false));
        actions.waitForBackgroundColor(petStore.adoptionsSection.adoptions.get(0).approveButton, "rgba(209, 213, 219, 1)");
        actions.waitForBackgroundColor(petStore.adoptionsSection.adoptions.get(0).denyButton, "rgba(209, 213, 219, 1)");

        driver.navigate().refresh();
        actions.waitForNumberOfElements(petStore.petsSection.pets, 0);
        actions.waitForNumberOfElements(petStore.adoptionsSection.adoptions, 0);
        assertThat(petStore.petsSection.defaultText.getText(), equalTo("No rows. Try reset filters"));
    }

    @Test
    @DisplayName("Adoption deny test")
    public void adoptionDenyTest() {
        petStore.petsSection.addPets(1);
        actions.waitForNumberOfElements(petStore.petsSection.pets, 1);

        petStore.petsSection.pets.get(0).click();
        petStore.petsSection.adoptButton.click();
        actions.waitForNumberOfElements(petStore.adoptionsSection.adoptions, 1);

        Adoption adoption = petStore.adoptionsSection.adoptions.get(0);
        adoption.denyButton.click();

        actions.shouldHaveTextToBe(petStore.adoptionsSection.adoptions.get(0).status, "DENIED");
        actions.shouldHaveTextToBe(petStore.petsSection.pets.get(0).petStatus, "AVAILABLE");
        assertThat(petStore.adoptionsSection.adoptions.get(0).approveButton.isEnabled(), equalTo(false));
        assertThat(petStore.adoptionsSection.adoptions.get(0).denyButton.isEnabled(), equalTo(false));

        driver.navigate().refresh();
        actions.waitForNumberOfElements(petStore.petsSection.pets, 1);
        actions.waitForNumberOfElements(petStore.adoptionsSection.adoptions, 0);
    }

    @Test
    @DisplayName("Adopt on hold pet")
    public void adoptOnHoldPet() {
        petStore.petsSection.addPets(1);
        actions.waitForNumberOfElements(petStore.petsSection.pets, 1);

        petStore.petsSection.pets.get(0).click();
        petStore.petsSection.adoptButton.click();
        actions.waitForNumberOfElements(petStore.adoptionsSection.adoptions, 1);

        petStore.petsSection.pets.get(0).click();
        petStore.petsSection.adoptButton.click();
        actions.waitForNumberOfElements(petStore.adoptionsSection.adoptions, 2);

        actions.shouldHaveTextToBe(petStore.adoptionsSection.adoptions.get(0).status, "REJECTED");

        assertThat(petStore.adoptionsSection.adoptions.get(0).pets.get(0).errorReason.getText(), equalTo("ONHOLD"));
        assertThat(petStore.adoptionsSection.adoptions.get(0).errorMessage.getText(), equalTo("Some of the pets could not be adopted"));

        driver.navigate().refresh();
        actions.waitForNumberOfElements(petStore.petsSection.pets, 1);
        actions.waitForNumberOfElements(petStore.adoptionsSection.adoptions, 1);
    }

}
