package chiril_bortnicov.junit.petstore;

import chiril_bortnicov.ui.petstore.components.PetsSection;
import chiril_bortnicov.ui.petstore.pages.PetStore;
import internal.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static denis_grimaliuc.actions.BaseActions.waitFor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.equalTo;

public class ComponentTests extends BaseTest {

    PetStore petStore = new PetStore(driver);

    PetsSection petsSection = petStore.petsSection;

    @Test
    @DisplayName("Hover button test")
    public void hoverBtnTest() {
        var locationBtn = petStore.location.changeLocationBtn;
        petStore.openPage();
        String defaultBackGroundColor = locationBtn.getCssValue("background-color");
        assertThat(defaultBackGroundColor, equalTo("rgba(37, 99, 235, 1)"));
        actions.hover(locationBtn);
        waitFor(1);
        String hoverBackGroundColor = locationBtn.getCssValue("background-color");
        assertThat(hoverBackGroundColor, either(equalTo("rgba(29, 79, 217, 1)")).or(equalTo("rgba(29, 78, 216, 1)")));
    }

    @Test
    @DisplayName("Hover button test(AdoptSelectedPets)")
    public void hoverBtnTest2() {
        var locationBtn = petsSection.adoptBtn;
        petStore.openPage();
        petsSection.addPetBtn.click();
        petsSection.checkbox.click();
        String defaultBackGroundColor = locationBtn.getCssValue("background-color");
        System.out.println(defaultBackGroundColor);
        assertThat(defaultBackGroundColor, equalTo("rgba(234, 88, 12, 1)"));//rgba(249, 115, 22, 1)
        //Expected: "rgba(234, 88, 12, 1)" but: was "rgba(227, 168, 129, 1)"
        actions.hover(locationBtn);
        waitFor(1);
        String hoverBackGroundColor = locationBtn.getCssValue("background-color");
        assertThat(hoverBackGroundColor, (equalTo("rgba(249, 115, 22, 1)")));//rgba(234, 88, 12, 1)
    }
}
