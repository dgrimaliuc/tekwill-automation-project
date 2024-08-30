package victor_murashev.junit.neonStream;

import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import victor_murashev.ui.neonStream.pages.NeonStreamHomePage;

public class NeonStreamBaseTest extends BaseTest {

    public NeonStreamHomePage neonStreamPage = new NeonStreamHomePage(driver);

    @BeforeEach
    public void openNeonStream() {
        neonStreamPage.openPage();

    }

}
