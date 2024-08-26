package IgorTabirta.Junit.PetStore;

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


}

