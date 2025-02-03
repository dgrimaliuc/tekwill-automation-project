package IonErm.junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static IonErm.components.shopify.ColorSection.getColor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.equalTo;

public class Shopify_ColorsFilter_Test extends ShopifyBaseTest {

    @ParameterizedTest(name = "Color filter test: {0}")
    @ValueSource(strings = {"Black", "White", "Red", "Yellow", "Green", "Blue", "Purple", "Gray", "Beige", "Brown"})
    @DisplayName("Colors filter test")
    public void colorsFilterTest(String color) {
        driver.findElement(getColor(color)).click();
        for (var card : page.cards) {
            String actualColor = card.cardColor.getAttribute("data-t");
            assertThat(actualColor, equalTo(color.toLowerCase()));
        }
    }

    @Test
    @DisplayName("Choose custom color")
    public void noFilterTest() {
        page.colorSection.beige.click();
        page.colorSection.gray.click();
        for (var card : page.cards) {
            String actualColor = card.cardColor.getAttribute("data-t");
            assertThat(actualColor, either(equalTo("beige")).or(equalTo("gray")));
        }
    }
}