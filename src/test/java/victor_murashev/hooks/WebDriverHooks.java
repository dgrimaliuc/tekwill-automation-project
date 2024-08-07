package victor_murashev.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class WebDriverHooks {

    private static WebDriver driver; // Static instance for shared access
    private static WebDriverWait wait;
    private final Logger log = Logger.getLogger(WebDriverHooks.class.getName());

    @Before(order = 0)
    public void setUp() {
        if (driver == null) { // Initialize once
            String pathToChrome = "src/main/resources/chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", pathToChrome);

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");

            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 10);

            log.info("Browser started successfully.");
        }
    }

    @After(order = 0)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
            log.info("Browser closed successfully.");
        }
    }

    // Static method to get the driver
    public static WebDriver getDriver() {
        return driver;
    }

    // Static method to get the wait
    public static WebDriverWait getWait() {
        return wait;
    }
}
