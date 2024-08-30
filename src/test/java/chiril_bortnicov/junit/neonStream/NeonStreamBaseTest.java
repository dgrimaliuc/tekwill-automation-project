package chiril_bortnicov.junit.neonStream;

import chiril_bortnicov.ui.neonStream.pages.NeonStreamHomePage;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;

public class NeonStreamBaseTest extends BaseTest {

    public NeonStreamHomePage neonStreamPage = new NeonStreamHomePage(driver);

    @BeforeEach
    public void openNeonStream() {
        neonStreamPage.openPage();
    }
}
