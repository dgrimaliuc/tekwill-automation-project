package IgorTabirta.Junit.PetStore;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class IntegrationTests extends BasePetstoreTest {

    @Test
    @DisplayName("Added pet in different location is not reflected in current one test")
    public void integrationLocationTest() {

        petStore.petsSection.addPets(1);
        actions.waitForNumberOfElements(petStore.petsSection.pets, 1);
        var pet = petStore.petsSection.pets.get(0);
        pet.click();
        petStore.petsSection.adoptBtn.click();
        actions.waitForNumberOfElements(petStore.adoptionsSection.adoptions, 1);

        String randomLocation = RandomStringUtils.randomAlphabetic(17).toUpperCase();
        petStore.openPage(randomLocation);

        String defaultText = petStore.petsSection.defaultText.getText();
        assertThat(defaultText, equalTo("No rows. Try reset filters"));
        assertThat(petStore.adoptionsSection.adoptions.size(), equalTo(0));


    }


}
