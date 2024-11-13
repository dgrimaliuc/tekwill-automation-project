package example.junit.petstore;

import example.ui.petstore.pages.PetStore;
import internal.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PetstoreInfoTests extends BaseTest {

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
        String actualAdoptionsCount = petStore.informationSection.adoptionsCount.getText();
        assertThat(actualPetsCount, equalTo("No pets. Go rescue some pets!"));
        assertThat(actualAdoptionsCount, equalTo("No adoptions. Go get those pets adopted!"));
    }

    @Test
    @DisplayName("Get exact info test")
    public void getExactInfoTest() {
        var petsCount = 3;
        petStore.addPets(petsCount);
        actions.waitForNumberOfElements(petStore.petsSection.pets, petsCount);

        petStore.petsSection.pets.get(0).click();
        petStore.petsSection.adoptBtn.click();

        actions.shouldHaveTextEndsWith(petStore.informationSection.petsCount, ": " + petsCount);
        actions.shouldHaveTextEndsWith(petStore.informationSection.adoptionsCount, ": 1");

    }
}
