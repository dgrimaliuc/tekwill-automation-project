package denis_grimaliuc.junit.neonStream;

import denis_grimaliuc.ui.neonStream.pages.NeonStreamHomePage;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;

public class NeonStreamBaseTest extends BaseTest {

    public NeonStreamHomePage neonStreamPage = new NeonStreamHomePage(driver);

    @BeforeEach
    public void openNeonStream() {
        neonStreamPage.openPage();
    }
}
