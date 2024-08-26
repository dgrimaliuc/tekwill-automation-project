package IgorTabirta.Junit.PetStore;

import IgorTabirta.UI.PetStore.Page.PetStore;
import internal.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ComponentsTests extends BaseTest {

    PetStore petStore = new PetStore(driver);

    @Test
    @DisplayName("Hover button test")
    public void hoverBtnTest() {
        var locationButton = petStore.location.changeLocationButton;
        petStore.openPage();
        String defaultBackgroundColor = locationButton.getCssValue("background-color");

        assertThat(defaultBackgroundColor, equalTo("rgba(37, 99, 235, 1)"));
        actions.hover(locationButton);
        String hoverBackgroundColor = locationButton.getCssValue("background-color");
        assertThat(hoverBackgroundColor, equalTo("rgba(29, 78, 216, 1)"));
    }

}
