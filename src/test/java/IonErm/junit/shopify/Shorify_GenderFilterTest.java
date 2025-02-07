package IonErm.junit.shopify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static IonErm.components.shopify.GenderSection.getGender;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.equalTo;

public class Shorify_GenderFilterTest extends ShopifyBaseTest {

    @Test
    @DisplayName("Gender custom test")
    public void genderTest() {
        page.genderSection.male.click();
        page.genderSection.female.click();
        for (var card : page.cards) {
            String actualGender = card.cardGender.getText();
            assertThat(actualGender, either(equalTo("male")).or(equalTo("female")));
        }
    }

    @ParameterizedTest(name = "Get gender {0}")
    @DisplayName("Gender parameterized test")
    @ValueSource(strings = {"Male", "Female"})
    public void genderParaTest(String gender) {
        driver.findElement(getGender(gender)).click();
        for (var card : page.cards) {
            String actualGender = card.cardGender.getText();
            assertThat(actualGender, equalTo(gender.toLowerCase()));
        }
    }
}