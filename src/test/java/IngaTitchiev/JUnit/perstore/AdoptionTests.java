package IngaTitchiev.JUnit.perstore;


import IngaTitchiev.JUnit.perstore.components.Adoption;
import IngaTitchiev.JUnit.perstore.components.AdoptionSection;
import IngaTitchiev.JUnit.perstore.components.PetsSection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AdoptionTests extends BaseLocationTest{

    PetsSection petsSection = petStore.petsSection;
    AdoptionSection adoptionsSection = petStore.adoptionSection;

    @Test
    @DisplayName("Adoption create test")
    public void createAdoptionTest() {
        String petName = petsSection.petNameInput.getAttribute("value");
        petsSection.addPetButton.click();
        actions.waitForNumberOfElements(petsSection.pets, 1);
        var pet = petsSection.pets.get(0);
        pet.click();

        petsSection.adoptionButton.click();
        actions.waitForNumberOfElements(adoptionsSection.adoptions, 1);

        String newPetStatus = pet.status.getText();
        assertThat(newPetStatus, equalTo("ONHOLD"));

        Adoption adoption = adoptionsSection.adoptions.get(0);
        String adoptionStatus = adoption.status.getText();
        String adoptionPetName = adoption.pets.get(0).getText();
        assertThat(adoptionStatus, equalTo("AVAILABLE"));
        assertThat(adoptionPetName, equalTo(petName));

    }
}
