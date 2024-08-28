package IgorTabirta.Junit.PetStore;

import IgorTabirta.UI.PetStore.Component.Adoption;
import IgorTabirta.UI.PetStore.Component.AdoptionsSection;
import IgorTabirta.UI.PetStore.Component.PetsSection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AdoptionTests extends BasePetstoreTest {
    PetsSection petsSection = petStore.petsSection;
    AdoptionsSection adoptionsSection = petStore.adoptionsSection;

    @Test
    @DisplayName("Adoption create test")
    public void createAdoptionTest() {
        String petName = petsSection.petNameInput.getAttribute("value");
        petsSection.addPetBtn.click();
        actions.waitForNumberOfElements(petsSection.pets, 1);
        var pet = petsSection.pets.get(0);
        pet.click();


        petsSection.adoptBtn.click();
        actions.waitForNumberOfElements(adoptionsSection.adoptions, 1);


        String newPetStatus = pet.status.getText();
        assertThat(newPetStatus, equalTo("ONHOLD"));
        Adoption adoption = adoptionsSection.adoptions.get(0);
        String adoptionStatus = adoption.status.getText();
        String adoptionPetName = adoption.name.get(0).getText();
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
        adoption.approveBtn.click();
        actions.shouldHaveTextToBe(adoption.status, "APPROVED");
        assertThat(adoption.approveBtn.isEnabled(), equalTo(false));
        assertThat(adoption.denyBtn.isEnabled(), equalTo(false));

        assertThat(pet.status.getText(), equalTo("ADOPTED"));
        driver.navigate().refresh();
        actions.waitForNumberOfElements(petsSection.pets, 0);
        actions.waitForNumberOfElements(adoptionsSection.adoptions, 0);
    }

    @Test
    @DisplayName("Adopt onhold pet")
    public void AdoptOnHoldPet() {

        petsSection.addPetBtn.click();
        actions.waitForNumberOfElements(petsSection.pets, 1);
        var pet = petsSection.pets.get(0);
        pet.click();
        petsSection.adoptBtn.click();
        actions.waitForNumberOfElements(adoptionsSection.adoptions, 1);
        // create another adoption
        pet.click();
        petsSection.adoptBtn.click();


        actions.waitForNumberOfElements(adoptionsSection.adoptions, 2);
        var adoption = adoptionsSection.adoptions.get(0);
        actions.shouldHaveTextToBe(adoption.status, "REJECTED");
        assertThat(adoption.pets.get(0).status.getText(), equalTo("ONHOLD"));
        assertThat(adoption.error.getText().trim(), equalTo("Some of the pets could not be adopted"));
        assertThat(adoption.approveBtn.isEnabled(), equalTo(false));
        assertThat(adoption.denyBtn.isEnabled(), equalTo(false));

        driver.navigate().refresh();
        actions.waitForNumberOfElements(petsSection.pets, 1);
        actions.waitForNumberOfElements(adoptionsSection.adoptions, 1);


    }
}
