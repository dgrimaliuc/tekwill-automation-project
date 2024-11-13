package example.junit.neonStream;

import example.ui.neonStream.pages.NeonStreamHomePage;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;

public class NeonStreamBaseTest extends BaseTest {

    public NeonStreamHomePage neonStreamPage = new NeonStreamHomePage(driver);

    @BeforeEach
    public void openNeonStream() {
        neonStreamPage.openPage();
    }
}
