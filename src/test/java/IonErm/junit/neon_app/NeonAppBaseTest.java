package IonErm.junit.neon_app;

import IonErm.components.neon_app.Footer;
import IonErm.components.neon_app.Header;
import IonErm.poms.neon_app.*;
import example.actions.BaseActions;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;

public class NeonAppBaseTest extends BaseTest {

    HomePage homePage = new HomePage(driver);
    WatchlistPage wlPage = new WatchlistPage(driver);
    Header header = new Header(driver);
    Footer footer = new Footer(driver);
    SearchPage searchPage = new SearchPage(driver);
    MoviePage moviePage = new MoviePage(driver);
    SeriesPage seriesPage = new SeriesPage(driver);
    BaseActions actions = new BaseActions(driver);

    @BeforeEach
    public void openNeonAppPage() {
        driver.get("https://neon-stream--stg-g1xzvf5x.web.app/");
        actions.waitForNumberOfElements(homePage.heroCarousel.placeholders, 0);
    }
}
