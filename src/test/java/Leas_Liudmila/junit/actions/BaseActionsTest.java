package Leas_Liudmila.junit.actions;

import Leas_Liudmila.LLAdoptPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static helpers.Helpers.stepResults;

public class BaseActionsTest {
    WebDriver driver = null;
    protected AdoptPageActions myActions = null;
    WebDriverWait wait = null;
    LLAdoptPage myPageLL = null;

    @BeforeEach
    public void before() {
        var pathToChrome = "src/main/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChrome);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        myActions = new AdoptPageActions(driver);
        wait = new WebDriverWait(driver, 5);
        stepResults = new ArrayList<>();
        myPageLL = new LLAdoptPage(driver);
        myActions.openRandomLocation();
    }
    @AfterEach
    public void after() {
        driver.quit();
    }
}
