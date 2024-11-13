package example.junit.petstore;

import example.ui.petstore.pages.PetStore;
import internal.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static example.actions.BaseActions.waitFor;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ComponentTests extends BaseTest {
    PetStore petStore = new PetStore(driver);

    @Test
    @DisplayName("Hover button test")
    public void hoverButtonTest() {
        var locationButton = petStore.location.changeLocationBtn;
        petStore.openPage();
        String defaultBackgroundColor = locationButton.getCssValue("background-color");
        assertThat(defaultBackgroundColor, equalTo("rgba(37, 99, 235, 1)"));
        actions.hover(locationButton);
        waitFor(1);
        String hoverBackgroundColor = locationButton.getCssValue("background-color");
        assertThat(hoverBackgroundColor, either(equalTo("rgba(29, 79, 217, 1)")).or(equalTo("rgba(29, 78, 216, 1)")));
    }
}
