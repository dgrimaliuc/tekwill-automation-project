package victor_murashev.junit.petstore;

import internal.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import victor_murashev.ui.shopify.pages.PetStore;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PetStoreInfoTest extends BaseTest {

    PetStore petStore = new PetStore(driver);

    @BeforeEach
    public void openRandomLocation() {
        String randomLocation = RandomStringUtils.randomAlphanumeric(17).toUpperCase();
        driver.get("https://petstore-eb41f.web.app/?location=" + randomLocation);
    }

    @Test
    @DisplayName("Default Pets Info Test")
    public void defaultPetsIntoTest() {
        String actualPetsCount = petStore.informationSection.petsCount.getText();
        String actualAdoptionCount = petStore.informationSection.adoptionCount.getText();

        assertThat(actualPetsCount, equalTo("No pets. Go rescue some pets!"));
        assertThat(actualAdoptionCount, equalTo("No adoptions. Go get those pets adopted!"));
    }

    @Test
    @DisplayName("Check non-zero Pets and Adoptions")
    public void checkNonZeroPetsAdoptions() throws InterruptedException {

        petStore.petsSection.addPets(3);
        petStore.petsSection.petsList.get(0).click();
        petStore.petsSection.adoptPetButton.click();
        System.out.println();
    }

}
