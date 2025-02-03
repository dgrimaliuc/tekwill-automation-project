package Lilia_Rosca.LR_JUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static Lilia_Rosca.components.LR_shopify.LR_SizeSection.getSize;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LR_SizeFilterTests extends LR_ShopifyBaseTests {
// 31.01
    @Test
    @DisplayName("S size filter test")
    public void oneSizeFilterTest() {
        page.sizeSection.s.click();
        for (var card : page.cards) {
            String actualSize = card.size.getText();
            assertThat(actualSize, equalTo("Size: S"));
        }
    }
    @ParameterizedTest(name = "Size filter test: {0}")
    @ValueSource(strings = {"S","M","L","XL"})
    public void sizeFilterTest(String size) {
        driver.findElement(getSize(size)).click();
        for (var card : page.cards) {
            String actualSize = card.size.getText();
            assertThat(actualSize, equalTo("Size: " + size));
        }
    }

    //@Test - 2 sizes
    @Test
    @DisplayName("Combined size filter test")
    public void combinedSizeFilterTest() {
       page.sizeSection.l.click();
       page.sizeSection.m.click();
        for (var card : page.cards) {
            String actualSize = card.size.getText();
            assertThat(actualSize, either(equalTo("Size: L"))
                                      .or(equalTo("Size: M")));
        }
    }

}