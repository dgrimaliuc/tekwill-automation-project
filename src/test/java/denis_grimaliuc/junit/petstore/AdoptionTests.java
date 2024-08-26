package denis_grimaliuc.junit.petstore;

import denis_grimaliuc.ui.petstore.components.Adoption;
import denis_grimaliuc.ui.petstore.components.AdoptionsSection;
import denis_grimaliuc.ui.petstore.components.PetsSection;
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
}
