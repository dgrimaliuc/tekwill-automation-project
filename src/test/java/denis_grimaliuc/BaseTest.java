package denis_grimaliuc;

import denis_grimaliuc.actions.BaseActions;
import helpers.YamlReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Map;

import static java.lang.System.getProperty;

public class BaseTest {

    public WebDriver driver = null;
    public BaseActions actions = null;
    public WebDriverWait wait = null;
    public AdoptPage page = null;

    @BeforeMethod
    public void beforeTestNg() {
        mainBefore();
    }

    @BeforeEach
    public void beforeJunit() {
        mainBefore();
    }

    private void mainBefore() {
        Map<String, Object> settings = new YamlReader(getProperty("config")).read();
        Integer explicitTimeout = ((Map<String, Integer>) settings.get("timeouts")).get("explicit");
        var provider = new ChromeDriverProvider(getProperty("config"));
        driver = provider.getDriver();
        wait = new WebDriverWait(driver, explicitTimeout);
        page = new AdoptPage(driver);
        actions = new BaseActions(driver, page);
        //        actions.openCustomLocation("Chisinau");
        actions.openRandomLocation();

    }


    @AfterEach
    public void afterJunit() {
        driver.quit();
    }

    @AfterMethod
    public void afterTestNg() {
        driver.quit();
    }
}
