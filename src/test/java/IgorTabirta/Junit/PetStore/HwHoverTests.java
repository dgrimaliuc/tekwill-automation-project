package IgorTabirta.Junit.PetStore;

import IgorTabirta.UI.PetStore.Component.PetsSection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static denis_grimaliuc.actions.BaseActions.waitFor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HwHoverTests extends BasePetstoreTest {
    PetsSection petsSection = petStore.petsSection;

    @Test
    @DisplayName("Adoption hover test")
    public void AdoptionHoverTest() {


        petStore.openPage();
        var pet = petsSection.pets.get(0);
        pet.click();
        waitFor(1);
        var adoptionBtn = petStore.petsSection.adoptBtn;
        String defaultAdoptBackgroundColor = adoptionBtn.getCssValue("background-color");
        assertThat(defaultAdoptBackgroundColor, equalTo("rgba(249, 115, 22, 1)"));
        actions.hover(adoptionBtn);
        String hoverAdoptBackgroundColor = adoptionBtn.getCssValue("background-color");
        assertThat(hoverAdoptBackgroundColor, equalTo("rgba(234, 88, 12, 1)"));


    }
}
