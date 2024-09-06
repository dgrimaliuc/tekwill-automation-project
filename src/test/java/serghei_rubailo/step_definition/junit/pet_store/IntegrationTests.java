package serghei_rubailo.step_definition.junit.pet_store;

import internal.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import serghei_rubailo.ui.pet_store.components.Pet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class IntegrationTests extends BasePetStoreTest {

    @Test
    @DisplayName("Pets in different locations")
    public void integrationLocationTest() {
        petStore.petsSection.addPetButton.click();
        actions.waitForNumberOfElements(petStore.petsSection.pets, 1);

        Pet pet = petStore.petsSection.pets.get(0);
        pet.click();
        petStore.petsSection.adoptButton.click();
        actions.waitForNumberOfElements(petStore.adoptionsSection.adoptions, 1);

        String randomLocation = RandomStringUtils.randomAlphanumeric(17).toUpperCase();
        petStore.openPage(randomLocation);

        String defaultText = petStore.petsSection.defaultText.getText();
        assertThat(defaultText, equalTo("No rows. Try reset filters"));

    }

}
