package serghei_rubailo.step_definition.junit.pet_store;

import internal.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import serghei_rubailo.ui.pet_store.pages.PetStore;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PetStoreInfoTest extends BaseTest {

    PetStore petStore = new PetStore(driver);

    @BeforeEach
    public void openPageWithRandomLocation() {
        String randomLocation = RandomStringUtils.randomAlphanumeric(20);
        driver.get("https://petstore-eb41f.web.app/?location=" + randomLocation);
    }

    @Test
    @DisplayName("Random Location Test")
    public void randomLocationTest() {
        assertThat(petStore.petsInfoSection.petsCountText.getText(), equalTo("No pets. Go rescue some pets!"));
        assertThat(petStore.petsInfoSection.adoptionsCountText.getText(), equalTo("No adoptions. Go get those pets adopted!"));
    }

    @Test
    @DisplayName("Pets in Location test")
    public void petsInLocationTest() {
        int petsCount = 5;

        petStore.petsSection.addPets(petsCount);
        actions.waitForNumberOfElements(petStore.petsSection.pets, petsCount);

        petStore.petsSection.pets.get(0).click();
        petStore.petsSection.adoptButton.click();

        actions.shouldHaveTextEndsWith(petStore.petsInfoSection.petsCountText, ": " + petsCount);
        actions.shouldHaveTextEndsWith(petStore.petsInfoSection.adoptionsCountText, ": 1");
    }
}
