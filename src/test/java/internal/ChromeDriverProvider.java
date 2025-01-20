package internal;

import example.data.enums.OS;
import helpers.YamlReader;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static example.data.enums.OS.*;
import static helpers.Helpers.addQuotes;
import static java.lang.System.getProperty;

public class ChromeDriverProvider {

    private final String pathToConfig;
    private final ChromeOptions options = new ChromeOptions();


    public ChromeDriverProvider(OS os) {
        System.setProperty("current_os", os.name().toLowerCase());
        this.pathToConfig = getProperty("config");
        System.setProperty("webdriver.chrome.driver", getBinaryPath());
    }

    public ChromeDriverProvider() {
        this.pathToConfig = getProperty("config");
        System.setProperty("webdriver.chrome.driver", getBinaryPath());
    }

    public static Map<String, Object> getSettings() {
        return new YamlReader().read();
    }

    private static String getBinaryPath() {
        if (WINDOWS.isCurrentOs()) {
            return "src/main/resources/chromedriver.exe";
        } else if (LINUX.isCurrentOs() || MAC.isCurrentOs()) {
            return "src/main/resources/chromedriver";
        } else throw new NoSuchElementException("Unsupported OS type: " + addQuotes(getCurrentOS()));
    }


    public void setCapabilities(Map<String, Object> settings) {
        Map<String, Object> capabilities = (Map) settings.get("capabilities");
        for (Map.Entry<String, Object> entry : capabilities.entrySet()) {
            options.setCapability(entry.getKey(), entry.getValue());
        }
    }

    public void setArguments(Map<String, Object> settings) {
        options.addArguments((List) settings.get("args"));
    }

    private void setImplicitTimeout(ChromeDriver driver, Map<String, Object> settings) {
        int timeout = ((Map<String, Integer>) settings.get("timeouts")).get("implicit");
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(timeout, TimeUnit.SECONDS);
    }

    private ChromeDriver createDriver() {
        ChromeDriver driver = new ChromeDriver(options);
        Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
        return driver;
    }

    public ChromeDriver getDriver() {
        Map<String, Object> settings = getSettings();
        setArguments(settings);
        setCapabilities(settings);
        ChromeDriver driver = createDriver();
        setImplicitTimeout(driver, settings);
        return driver;
    }
}
