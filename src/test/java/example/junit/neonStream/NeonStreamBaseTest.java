package example.junit.neonStream;

import example.actions.BaseActions;
import example.components.neonStream.Footer;
import example.components.neonStream.Header;
import example.poms.neonStream.*;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;

public class NeonStreamBaseTest extends BaseTest {
    HomePage homePage = new HomePage(driver);
    WatchListPage wlPage = new WatchListPage(driver);
    SearchPage searchPage = new SearchPage(driver);
    MoviePage moviePage = new MoviePage(driver);
    SeriesPage seriesPage = new SeriesPage(driver);
    Header header = new Header(driver);
    Footer footer = new Footer(driver);
    BaseActions actions = new BaseActions(driver);

    @BeforeEach
    public void openNSPage() {
        driver.get("https://neon-stream--stg-g1xzvf5x.web.app/");
        actions.waitForNumberOfElements(homePage.heroCarousel.placeholders, 0);
    }

}
