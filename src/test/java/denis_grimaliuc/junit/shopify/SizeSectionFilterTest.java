package denis_grimaliuc.junit.shopify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static denis_grimaliuc.components.shopify.SizeSection.getSize;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class SizeSectionFilterTest extends ShopifyBaseTest {

    @ParameterizedTest(name = "Size filter test: {0}")
    @ValueSource(strings = {"S", "XL", "L", "M"})
    public void sizeTest(String size) {
        driver.findElement(getSize(size)).click();

        for (var card : page.cards) {
            String actualSize = card.size.getText();
            assertThat(actualSize, equalTo("Size: " + size));
        }
    }

    @Test
    @DisplayName("Two sizes filter test")
    public void twoSizesTest() {
        page.sizeSection.s.click();
        page.sizeSection.xl.click();

        for (var card : page.cards) {
            String actualSize = card.size.getText();
            assertThat(actualSize, either(equalTo("Size: S")).or(equalTo("Size: XL")));

        }
    }
}
