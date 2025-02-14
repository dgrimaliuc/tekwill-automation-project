package Lilia_Rosca.LR_JUnit.NeonStream;

import Lilia_Rosca.components.LR_NeonStream.LR_Footer;
import Lilia_Rosca.components.LR_NeonStream.LR_Header;
import Lilia_Rosca.poms.LR_NeonStream.*;
import example.actions.BaseActions;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;

public class LR_NeonStreamBaseTest extends BaseTest {
// 05.02
    BaseActions actions = new BaseActions(driver);
    LR_HomePage homePage = new LR_HomePage(driver);

// 10.02
    LR_WatchListPage wlPage = new LR_WatchListPage(driver);
    LR_MoviePage moviePage = new LR_MoviePage(driver);
    LR_SeriesPage seriesPage = new LR_SeriesPage(driver);
    LR_Header header = new LR_Header(driver);

// 12.02
    LR_SearchPage searchPage = new LR_SearchPage(driver);
    LR_Footer footer = new LR_Footer(driver);


    @BeforeEach
    public void OpenNSPage() {
        driver.get("https://neon-stream--stg-g1xzvf5x.web.app/");
        actions.waitForNumberOfElements(homePage.heroCarousel.placeHolders, 0);
    }

}