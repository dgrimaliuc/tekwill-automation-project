package chiril_bortnicov.junit.petstore;

import chiril_bortnicov.ui.petstore.pages.PetStore;
import internal.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static chiril_bortnicov.ui.petstore.components.PetsSection.petsLocator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PetStoreInfoTests extends BaseTest {

    PetStore petStore = new PetStore(driver);

    @BeforeEach
    public void openRandomLocation() {
        String randomLocation = RandomStringUtils.randomAlphanumeric(17).toUpperCase();
        driver.get("https://petstore-eb41f.web.app/?location=" + randomLocation);
    }

    @Test
    @DisplayName("Default info test")
    public void defaultInfoTest() {
        String actualPetsCount = petStore.informationSection.petsCount.getText();
        String adoptionsPetsCount = petStore.informationSection.adoptionsCount.getText();

        assertThat(actualPetsCount, equalTo("No pets. Go rescue some pets!"));
        assertThat(adoptionsPetsCount, equalTo("No adoptions. Go get those pets adopted!"));
    }

    @Test
    @DisplayName("Get exact info test")
    public void getExactInfoTest() {
        var petsCount = 3;
        petStore.petsSection.addPets(petsCount);

        wait.until(ExpectedConditions.numberOfElementsToBe(petsLocator, petsCount));
        petStore.petsSection.pets.get(0).click();
        petStore.petsSection.adoptBtn.click();

        actions.shouldHaveTextEndsWith(petStore.informationSection.petsCount, ": " + petsCount);
        actions.shouldHaveTextEndsWith(petStore.informationSection.adoptionsCount, ": 1");
    }

}
