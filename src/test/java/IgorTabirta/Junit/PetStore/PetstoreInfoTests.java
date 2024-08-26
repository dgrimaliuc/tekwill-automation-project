package IgorTabirta.Junit.PetStore;

import IgorTabirta.UI.PetStore.Page.PetStore;
import internal.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static IgorTabirta.UI.PetStore.Component.PetsSection.petsLocator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PetstoreInfoTests extends BaseTest {

    PetStore petStore = new PetStore(driver);

    @BeforeEach
    public void OpenRandomLocation() {

        String randomLocation = RandomStringUtils.randomAlphabetic(17).toUpperCase();
        driver.get("https://petstore-eb41f.web.app/?location=" + randomLocation);

    }

    @Test
    @DisplayName("Default info test")
    public void DefaultInfoTest() {

        String actualPetsCount = petStore.infoSection.petsCount.getText();
        String actualAdoptionCount = petStore.infoSection.adoptionCount.getText();
        assertThat(actualPetsCount, equalTo("No pets. Go rescue some pets!"));
        assertThat(actualAdoptionCount, equalTo("No adoptions. Go get those pets adopted!"));
        System.out.println();
    }

    @Test
    @DisplayName("Get exact info test")
    public void getExactInfoTest() {
        var count = 3;

        petStore.petsSection.addPets(count);
        actions.waitForNumberOfElements(petsLocator, count);

        petStore.petsSection.pets.get(0).click();
        petStore.petsSection.adoptBtn.click();

        actions.shouldHaveTextEndsWith(petStore.infoSection.petsCount, ": " + count);
        actions.shouldHaveTextEndsWith(petStore.infoSection.adoptionCount, ": 1");
        //System.out.println();
    }

}

