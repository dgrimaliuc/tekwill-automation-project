package Lilia_Rosca.LR_JUnit.NeonStream;

import Lilia_Rosca.poms.LR_NeonStream.LR_HomePage;
import example.actions.BaseActions;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;

public class LR_NeonStreamBaseTest extends BaseTest {
// 05.02
    LR_HomePage page = new LR_HomePage(driver);
    BaseActions actions = new BaseActions(driver);

    @BeforeEach
    public void OpenNSPage() {
        driver.get("https://neon-stream--stg-g1xzvf5x.web.app/");

    }

}
