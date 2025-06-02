package example.junit.shopify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static example.components.shopify.GenderSection.getGender;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GenderSectionFilterTest extends ShopifyBaseTest {
    @ParameterizedTest
    @ValueSource(strings = {"Male", "Female"})
    public void genderFilterTest(String gender) {
        driver.findElement(getGender(gender)).click();

        for (var card : page.cards) {
            String actualGender = card.gender.getText();
            assertThat(actualGender, equalTo(gender.toLowerCase()));

        }
    }

    @Test
    @DisplayName("Both gender filter test")
    public void bothGenderFilterTest() {
        page.genderSection.female.click();
        page.genderSection.male.click();

        for (var card : page.cards) {
            String actualGender = card.gender.getText();
            assertThat(actualGender, either(equalTo("male"))
                    .or(equalTo("female"))
            );

        }
    }
}
