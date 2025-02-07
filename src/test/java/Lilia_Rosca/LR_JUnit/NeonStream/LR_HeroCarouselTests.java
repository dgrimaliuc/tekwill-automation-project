package Lilia_Rosca.LR_JUnit.NeonStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LR_HeroCarouselTests extends LR_NeonStreamBaseTest{
// 05.02
    @Test
    @DisplayName("Smoke Hero Carousel Test")
    public void smokeHeroCarouselTest() {
        actions.shouldBeDisplayed(page.heroCarousel);
    }
    // Hero Carousel Timing Test
    // click on a tab test
    // arrows test
    // hero card Watch now click test
    // hero card Title click test

}
