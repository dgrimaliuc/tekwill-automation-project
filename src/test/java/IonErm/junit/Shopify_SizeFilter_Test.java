package IonErm.junit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static IonErm.components.shopify.ColorSection.getColor;
import static IonErm.components.shopify.SizeSection.getSize;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.equalTo;

public class Shopify_SizeFilter_Test extends ShopifyBaseTest {

    @ParameterizedTest(name = "Size filter test: {0}")
    @ValueSource(strings = {"S", "M", "L", "XL"})
    @DisplayName("Simple test get size")
    public void getFilterSizeTest(String size) {
        driver.findElement(getSize(size)).click();
        for (var card : page.cards) {
            String actualSize = card.cardSize.getText();
            assertThat(actualSize, equalTo("Size: " + size));
        }
    }

    @Test
    @DisplayName("Simple custom test get size")
    public void getCustomSizeTest() {
        page.sizeSection.XL.click();
        page.sizeSection.M.click();
        for (var card : page.cards) {
            String actualSize = card.cardSize.getText();
            assertThat(actualSize, either(equalTo("Size: XL")).or(equalTo("Size: M")));
        }
    }
}
