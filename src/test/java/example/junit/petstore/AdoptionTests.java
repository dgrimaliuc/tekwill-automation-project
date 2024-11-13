package example.junit.petstore;

import example.ui.petstore.components.Adoption;
import example.ui.petstore.components.AdoptionsSection;
import example.ui.petstore.components.PetsSection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AdoptionTests extends BasePetstoreTest {

    PetsSection petsSection = petStore.petsSection;
    AdoptionsSection adoptionsSection = petStore.adoptionsSection;

    @Test
    @DisplayName("Adoption create test")
    public void createAdoptionTest() {
        var petsCount = 1;
        var petName = petStore.addPets(petsCount).get(0).get("name");

        driver.navigate().refresh();
        actions.waitForNumberOfElements(petsSection.pets, petsCount);

        var uiPet = petsSection.pets.get(0);
        uiPet.click();
        petsSection.adoptBtn.click();
        actions.waitForNumberOfElements(adoptionsSection.adoptions, 1);

        String newPetStatus = uiPet.status.getText();
        assertThat(newPetStatus, equalTo("ONHOLD"));

        Adoption adoption = adoptionsSection.adoptions.get(0);
        String adoptionStatus = adoption.status.getText();
        String adoptionPetName = adoption.pets.get(0).getText();
        assertThat(adoptionStatus, equalTo("AVAILABLE"));
        assertThat(adoptionPetName, equalTo(petName));
    }

    @Test
    @DisplayName("Adoption approve test")
    public void approveAdoptionTest() {
        petsSection.addPetBtn.click();
        actions.waitForNumberOfElements(petsSection.pets, 1);
        var pet = petsSection.pets.get(0);
        pet.click();
        petsSection.adoptBtn.click();
        actions.waitForNumberOfElements(adoptionsSection.adoptions, 1);
        var adoption = adoptionsSection.adoptions.get(0);
        adoption.approveButton.click();

        actions.shouldHaveTextToBe(adoption.status, "APPROVED");
        assertThat(adoption.approveButton.isEnabled(), equalTo(false));
        assertThat(adoption.denyButton.isEnabled(), equalTo(false));

        assertThat(pet.status.getText(), equalTo("ADOPTED"));
        driver.navigate().refresh();
        actions.waitForNumberOfElements(petsSection.pets, 0);
        actions.waitForNumberOfElements(adoptionsSection.adoptions, 0);
    }

    @Test
    @DisplayName("Adoption deny test")
    public void denyAdoptionTest() {
        petsSection.addPetBtn.click();
        actions.waitForNumberOfElements(petsSection.pets, 1);
        var pet = petsSection.pets.get(0);
        pet.click();
        petsSection.adoptBtn.click();
        actions.waitForNumberOfElements(adoptionsSection.adoptions, 1);
        var adoption = adoptionsSection.adoptions.get(0);
        adoption.denyButton.click();

        actions.shouldHaveTextToBe(adoption.status, "DENIED");
        assertThat(adoption.approveButton.isEnabled(), equalTo(false));
        assertThat(adoption.denyButton.isEnabled(), equalTo(false));
        assertThat(pet.status.getText(), equalTo("AVAILABLE"));
        driver.navigate().refresh();

        actions.waitForNumberOfElements(petsSection.pets, 1);
        actions.waitForNumberOfElements(adoptionsSection.adoptions, 0);
    }

    @Test
    @DisplayName("Adopt onhold pet")
    public void adoptOnHoldPet() {
        petsSection.addPetBtn.click();
        actions.waitForNumberOfElements(petsSection.pets, 1);
        var pet = petsSection.pets.get(0);
        pet.click();
        petsSection.adoptBtn.click();
        actions.waitForNumberOfElements(adoptionsSection.adoptions, 1);

        // Create another adoption
        pet.click();
        petsSection.adoptBtn.click();

        actions.waitForNumberOfElements(adoptionsSection.adoptions, 2);
        var adoption = adoptionsSection.adoptions.get(0);
        actions.shouldHaveTextToBe(adoption.status, "REJECTED");
        assertThat(adoption.pets.get(0).status.getText(), equalTo("ONHOLD"));
        assertThat(adoption.error.getText().trim(), equalTo("Some of the pets could not be adopted"));
        assertThat(adoption.approveButton.isEnabled(), equalTo(false));
        assertThat(adoption.denyButton.isEnabled(), equalTo(false));

        driver.navigate().refresh();
        actions.waitForNumberOfElements(petsSection.pets, 1);
        actions.waitForNumberOfElements(adoptionsSection.adoptions, 1);

    }
}
