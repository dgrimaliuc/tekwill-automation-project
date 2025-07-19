package Magda_Petrachi.NeoStreamJUnit;

import Magda_Petrachi.NeonStream.NeoStreamPage;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NeoStreamTests extends BaseTest {

    NeoStreamPage page = new NeoStreamPage(driver);

    @BeforeEach
    void setup() {
        log.info("Open Neo Stream page");
        driver.get("https://neon-stream.web.app/");
        actions.waitForNumberOfElements(page.placeholders, 0);
    }

    @Test
    public void smokeTestHeroCarusel() {
        actions.shouldSee(page.heroCarousel.cards.getFirst().title);
    }
}
