package IgorTabirta.Junit.PetStore;

import IgorTabirta.UI.PetStore.Component.AdoptionsSection;
import IgorTabirta.UI.PetStore.Component.PetsSection;
import IgorTabirta.UI.PetStore.Page.PetStore;
import internal.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ClassWork_Tests extends BaseTest {

    PetStore petStore = new PetStore(driver);
    PetsSection petsSection = petStore.petsSection;
    AdoptionsSection adoptionsSection = petStore.adoptionsSection;

    @BeforeEach
    public void OpenRandomLocation() {

        String randomLocation = RandomStringUtils.randomAlphabetic(10).toUpperCase();
        driver.get("https://petstore-eb41f.web.app/?location=" + randomLocation);

    }


    @Test
    @DisplayName("Default Btn state test")
    public void DefaultBtnStateTest() {
        var petSection = petStore.petsSection;
        var isEnabled = petSection.deselectBtn.isEnabled();
        var isEnabled1 = petSection.adoptBtn.isEnabled();
        assertThat(isEnabled, equalTo(false));
        assertThat(isEnabled1, equalTo(false));


    }

    @Test
    @DisplayName("Adoption deny test")
    public void DenyAdoptionTest() {

        petsSection.addPetBtn.click();
        actions.waitForNumberOfElements(petsSection.pets, 1);
        var pet = petsSection.pets.get(0);
        pet.click();
        petsSection.adoptBtn.click();
        actions.waitForNumberOfElements(adoptionsSection.adoptions, 1);
        var adoption = adoptionsSection.adoptions.get(0);
        adoption.denyBtn.click();
        actions.shouldHaveTextToBe(adoption.status, "DENIED");
        assertThat(adoption.approveBtn.isEnabled(), equalTo(false));
        assertThat(adoption.denyBtn.isEnabled(), equalTo(false));
        assertThat(pet.status.getText(), equalTo("AVAILABLE"));
        driver.navigate().refresh();
        actions.waitForNumberOfElements(petsSection.pets, 1);
        actions.waitForNumberOfElements(adoptionsSection.adoptions, 0);


    }


}

