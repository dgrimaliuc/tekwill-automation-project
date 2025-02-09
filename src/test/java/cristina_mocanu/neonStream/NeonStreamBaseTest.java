package cristina_mocanu.neonStream;

import cristina_mocanu_poms.neonStream.HomePage;
import example.actions.BaseActions;
import example.ui.neonStream.pages.NeonStreamHomePage;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NeonStreamBaseTest extends BaseTest {

    HomePage page = new HomePage(driver);
    BaseActions actions = new BaseActions(driver);

    @BeforeEach
    public void openNSPage() {
        driver.get("https://neon-stream--stg-g1xzvf5x.web.app/");
        actions.waitForNumberOfElements(page.heroCarousel.placeholders, 0);

    }
public void waitForCurrentURLContains(String url){
        log.trace("Waiting for current URL contains: ");
        wait.until(ExpectedConditions.urlContains(url));
}


    }


