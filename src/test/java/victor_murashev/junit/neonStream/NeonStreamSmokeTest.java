package victor_murashev.junit.neonStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class NeonStreamSmokeTest extends NeonStreamBaseTest {

    @Test
    @DisplayName("Content is displayed")
    public void contentIsDisplayed() {
        wait.until(driver -> neonStreamPage.heroCarousel.cards.get(0).backgroundImage.isDisplayed());

        assertThat(neonStreamPage.heroCarousel.cards.get(0).title.isDisplayed(), equalTo(true));
        assertThat(neonStreamPage.heroCarousel.cards.get(0).description.isDisplayed(), equalTo(true));
        assertThat(neonStreamPage.heroCarousel.cards.get(0).backgroundImage.isDisplayed(), equalTo(true));
        assertThat(neonStreamPage.heroCarousel.cards.get(0).watchNowButton.isDisplayed(), equalTo(true));

    }

}
