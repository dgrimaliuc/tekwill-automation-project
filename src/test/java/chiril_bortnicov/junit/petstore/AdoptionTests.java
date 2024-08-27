package chiril_bortnicov.junit.petstore;

import chiril_bortnicov.ui.petstore.components.Adoption;
import chiril_bortnicov.ui.petstore.components.AdoptionsSection;
import chiril_bortnicov.ui.petstore.components.PetsSection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static denis_grimaliuc.actions.BaseActions.waitFor;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AdoptionTests extends BasePetStoreTest {

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
        String adoptionPetName = adoption.pets.get(0).getText();
        assertThat(adoptionStatus, equalTo("AVAILABLE"));
        assertThat(adoptionPetName, equalTo(petName));
    }

    @Test
    @DisplayName("Adoption approve test")
    public void approveAdoptionTest() {
        petsSection.addPets(1);
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
    @DisplayName("Adoption deny test")
    public void denyAdoptionTest() {
        petsSection.addPetBtn.click();
        actions.waitForNumberOfElements(petsSection.pets, 1);
        var pet = petsSection.pets.get(0);
        pet.click();
        petsSection.adoptBtn.click();
        actions.waitForNumberOfElements(adoptionsSection.adoptions, 1);
        var adoption = adoptionsSection.adoptions.get(0);
        adoption.denyBtn.click();
        waitFor(1);

        assertThat(adoption.status.getText(), equalTo("DENIED"));
        assertThat(adoption.approveBtn.isEnabled(), equalTo(false));
        assertThat(adoption.denyBtn.isEnabled(), equalTo(false));

        assertThat(pet.status.getText(), equalTo("AVAILABLE"));
        driver.navigate().refresh();
        actions.waitForNumberOfElements(petsSection.pets, 1);
        actions.waitForNumberOfElements(adoptionsSection.adoptions, 0);
    }

    @Test
    @DisplayName("Adopt onhold test")
    public void adoptOnHoldTest() {
        petsSection.addPets(1);
        actions.waitForNumberOfElements(petsSection.pets, 1);
        var pet = petsSection.pets.get(0);
        pet.click();
        petsSection.adoptBtn.click();
        actions.waitForNumberOfElements(adoptionsSection.adoptions, 1);

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

