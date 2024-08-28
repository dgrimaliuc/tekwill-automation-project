package serghei_rubailo.step_definition.junit.pet_store;

import internal.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import serghei_rubailo.ui.pet_store.pages.PetStore;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ComponentTests extends BaseTest {

    PetStore petStore = new PetStore(driver);

    @Test
    @DisplayName("Button Hover Color Test")
    public void buttonHoverColorTest() {

        petStore.openPage();

        String currentColor = petStore.location.changeLocationButton.getCssValue("background-color");

        actions.hover(petStore.location.changeLocationButton);

        String hoverColor = petStore.location.changeLocationButton.getCssValue("background-color");

        assertThat(currentColor, equalTo("rgba(37, 99, 235, 1)"));
        assertThat(hoverColor, equalTo("rgba(29, 78, 216, 1)"));
    }

}
