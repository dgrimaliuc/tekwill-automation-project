package IngaTitchiev.JUnit.perstore;


import IngaTitchiev.JUnit.perstore.pages.PetStore;
import internal.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static denis_grimaliuc.actions.BaseActions.waitFor;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ComponentTests extends BaseTest {

    PetStore petStore = new PetStore(driver);

    @Test
    @DisplayName("Hover button test")
    public void hoverButtonTest() {
        var locationButton = petStore.location.changeLocationButton;
        petStore.openPage();
        String defaultBackgroundColor = locationButton.getCssValue("background-color");
        assertThat(defaultBackgroundColor, equalTo("rgba(37, 99, 235, 1)"));
        actions.hover(locationButton);
        waitFor(1);
        String hoverBackgroundColor = locationButton.getCssValue("background-color");
        assertThat(hoverBackgroundColor, either(equalTo("rgba(29, 79, 217, 1)")).or(equalTo("rgba(29, 78, 216, 1)")));
    }
    @Test
    @DisplayName("Hover adopt selected pets button test")
    public void hoverAdoptSelectedPetsButtonTest() {
        var adoptSelectedPetsButton = petStore.petsSection.adoptionButton;
        petStore.openPage();
        var pet = petStore.petsSection.pets.get(0);
       pet.checkbox.click();
       String defaultBackgroundColor = adoptSelectedPetsButton.getCssValue("background-color");
        System.out.println("back color"+ defaultBackgroundColor);
        assertThat(defaultBackgroundColor, either(equalTo("rgba(216, 196, 185, 1)")).or(equalTo("rgba(227, 168, 129, 1)")));
        actions.hover(adoptSelectedPetsButton);
        waitFor(2);
        String hoverBackgroundColor = adoptSelectedPetsButton.getCssValue("background-color");
        System.out.println("color after" + hoverBackgroundColor);
       assertThat(hoverBackgroundColor, either(equalTo("rgba(234, 88, 12, 1)")).or(equalTo("rgba(237, 145, 83, 1)")));
    }
}
