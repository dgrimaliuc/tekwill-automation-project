package Lilia_Rosca.LR_JUnit.NeonStream;

import Lilia_Rosca.components.LR_NeonStream.LR_Header;
import Lilia_Rosca.poms.LR_NeonStream.LR_HomePage;
import Lilia_Rosca.poms.LR_NeonStream.LR_MoviePage;
import Lilia_Rosca.poms.LR_NeonStream.LR_WatchListPage;
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
    LR_Header header = new LR_Header(driver);


    @BeforeEach
    public void OpenNSPage() {
        driver.get("https://neon-stream--stg-g1xzvf5x.web.app/");
        actions.waitForNumberOfElements(homePage.heroCarousel.placeHolders, 0);
    }

}