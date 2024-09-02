package IgorTabirta.Junit.Neon;

import IgorTabirta.UI.Neon.Page.NeonPage;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;

public class NeonBaseTest extends BaseTest {

    public NeonPage neonPage = new NeonPage(driver);

    @BeforeEach
    public void openNeon() {
        neonPage.openNeonpage();
    }
}
