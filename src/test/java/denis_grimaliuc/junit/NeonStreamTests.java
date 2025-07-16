package denis_grimaliuc.junit;

import denis_grimaliuc.neonStream.NeonStreamPage;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NeonStreamTests extends BaseTest {
    NeonStreamPage page = new NeonStreamPage(driver);

    @BeforeEach
    public void setup() {
        log.info("Opening the Neon Stream page");
        driver.get("https://neon-stream.web.app");
        actions.waitForNumberOfElements(page.placeholders, 0);
    }

    @Test
    public void smokeHeroCarousel() {
        actions.shouldSee(page.heroCarousel.cards.getFirst().title);
    }
}
