package Lilia_Rosca.LR_JUnit;

import Lilia_Rosca.poms.LR_shopifyPage;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static Lilia_Rosca.components.LR_shopify.LR_ColorSection.getColor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LR_ColorFilterTests extends LR_ShopifyBaseTests {
// 31.01
    @Test
    @DisplayName("Black color filter test")
    public void blackFilterTest() {
        page.colorSection.black.click();
        for (var card : page.cards) {
            String color = card.color.getAttribute("data-t");
            assertThat(color, equalTo("black"));
        }
    }

    @ParameterizedTest(name = "Color filter test: {0}")
    @ValueSource (strings = {"Black", "White", "Red", "Yellow", "Green", "Blue", "Purple", "Gray", "Beige", "Brown"} )
    public void colorFilterTest(String color) {
        driver.findElement(getColor(color)).click();
        for (var card : page.cards) {
            String actualColor = card.color.getAttribute("data-t");
            assertThat(actualColor, equalTo(color.toLowerCase()));
        }
    }

    // @Test - 2 colors
    @Test
    @DisplayName("Two colors filter test")
    public void twoColorFilterTest() {
        page.colorSection.blue.click();
        page.colorSection.green.click();
        for (var card : page.cards) {
            String color = card.color.getAttribute("data-t");
            assertThat(color, either(equalTo("blue"))
                                 .or(equalTo("green")));
        }
    }

}