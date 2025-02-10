package IonErm.junit.neon_app;

import IonErm.poms.neon_app.HomePage;
import example.actions.BaseActions;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.interactions.Actions;

public class NeonAppBaseTest extends BaseTest {

    HomePage page = new HomePage(driver);
    BaseActions actions = new BaseActions(driver);

    @BeforeEach
    public void openNeonAppPage() {
        driver.get("https://neon-stream--stg-g1xzvf5x.web.app/");
        actions.waitForNumberOfElements(page.heroCarousel.placeholders, 0);
    }
}
