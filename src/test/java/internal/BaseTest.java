package internal;

import example.actions.BaseActions;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static example.actions.BaseActions.setDefaultTimeouts;
import static example.data.enums.OS.MAC;

@SuppressWarnings("unused")
public class BaseTest {

    public final Logger log = Logger.getLogger(BaseTest.class);
    public final WebDriver driver;
    public BaseActions actions = null;
    public WebDriverWait wait = null;

    public BaseTest() {
        var pathToChrome = "";
        if (MAC.isCurrentOs()) {
            pathToChrome = "src/main/resources/chromedriver_mac";
        } else {
            pathToChrome = "src/main/resources/chromedriver.exe";
        }

        System.setProperty("webdriver.chrome.driver", pathToChrome);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
//        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
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
