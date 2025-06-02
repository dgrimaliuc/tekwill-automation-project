package example.junit.shopify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static example.components.shopify.ColorsSection.getColor;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ColorsFilterSectionTest extends ShopifyBaseTest {

    @ParameterizedTest(name = "Color filter test: {0}")
    @ValueSource(strings = {"Red", "Black", "Blue", "Green", "Yellow", "White", "Purple", "Gray", "Beige", "Brown"})
    public void blackFilterTest(String color) {
        driver.findElement(getColor(color)).click();
        for (var card : page.cards) {
            String actualColor = card.color.getAttribute("data-t");
            assertThat(actualColor, equalTo(color.toLowerCase()));
        }
    }

    @Test
    public void noFilterTest() {

        page.colorsSection.blue.click();
        page.colorsSection.red.click();

        for (var card : page.cards) {
            String actualColor = card.color.getAttribute("data-t");
            assertThat(actualColor, either(equalTo("blue"))
                    .or(equalTo("red")));
        }
    }


}
