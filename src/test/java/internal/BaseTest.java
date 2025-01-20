package internal;

import example.actions.BaseActions;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static example.actions.BaseActions.setDefaultTimeouts;

@SuppressWarnings("unused")
public class BaseTest {

    public final Logger log = Logger.getLogger(BaseTest.class);
    public final WebDriver driver;
    public BaseActions actions = null;
    public WebDriverWait wait = null;

    public BaseTest() {
        driver = new ChromeDriverProvider().getDriver();
        setDefaultTimeouts(driver);
    }


    @BeforeEach
    public void setUp() {
        wait = new WebDriverWait(driver, 10);
        actions = new BaseActions(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
