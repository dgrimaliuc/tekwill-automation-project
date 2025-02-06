package denis_grimaliuc.junit.neonStream;

import denis_grimaliuc.poms.neonStream.HomePage;
import example.actions.BaseActions;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;

public class NeonStreamBaseTest extends BaseTest {
    HomePage page = new HomePage(driver);
    BaseActions actions = new BaseActions(driver);

    @BeforeEach
    public void openNSPage() {
        driver.get("https://neon-stream--stg-g1xzvf5x.web.app/");
        actions.waitForNumberOfElements(page.heroCarousel.placeholders, 0);
    }

}
