package Lilia_Rosca.LR_JUnit.Shopify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static Lilia_Rosca.components.LR_shopify.LR_GenderSection.getGender;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.equalTo;

public class LR_GenderFilterTests extends LR_ShopifyBaseTests {
// 31.01
    @ParameterizedTest
    @ValueSource(strings = {"Male", "Female"})
    public void genderFilterTest (String gender) {
        driver.findElement(getGender(gender)).click();
        for(var card : page.cards) {
            String actualGender = card.gender.getText();
            assertThat(actualGender, equalTo(gender.toLowerCase()));
        }
    }
    // @Test - 2 genders
    @Test
    @DisplayName("Both genders filter test")
    public void bothGendersFilterTest () {
        page.genderSection.female.click();
        page.genderSection.male.click();
        for(var card : page.cards) {
            String actualGender = card.gender.getText();
            assertThat(actualGender, either(equalTo("female"))
                                        .or(equalTo("male")));
        }
    }

}