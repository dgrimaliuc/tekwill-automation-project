package denis_grimaliuc.junit;

import denis_grimaliuc.AdoptPage;
import denis_grimaliuc.UIStepDefinition;
import denis_grimaliuc.junit.actions.BaseActions;
import helpers.Helpers;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static denis_grimaliuc.AdoptPage.FIRST_ROW_IN_TABLE;
import static helpers.Helpers.stepResults;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class UIJunitTests {

    WebDriver driver = null;
    BaseActions actions = null;
    WebDriverWait wait = null;
    Logger log = Logger.getLogger(UIStepDefinition.class);
    AdoptPage page = null;

    @BeforeEach
    public void before() {
        var pathToChrome = "src/main/resources/chromedriver_mac";
        System.setProperty("webdriver.chrome.driver", pathToChrome);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        actions = new BaseActions(driver);
        wait = new WebDriverWait(driver, 5);
        stepResults = new ArrayList<>();
        page = new AdoptPage(driver, wait);
        //        Runtime.getRuntime().addShutdownHook(new Thread(() -> driver.quit()));
        actions.openRandomLocation();
    }

    @AfterEach
    public void after() {
        driver.quit();
    }

    @Test
    @DisplayName("Verify Pet can have Long name test")
    public void longNameTest() {
        String randomPetName = RandomStringUtils.random(100, true, true);
        actions.addAPetToCurrentLocation(randomPetName);
    }

    @Test
    @DisplayName("Added pet in different location is not reflected in current one test")
    public void test1() {
        actions.openRandomLocation();
        actions.addAPetToCurrentLocation();

        driver.navigate().back();
        Helpers.waitInSeconds(1);
        assertThat(page.pets.size(), equalTo(1));
        wait.until(ExpectedConditions.textToBe(FIRST_ROW_IN_TABLE, "No rows. Try reset filters"));
    }

    @Test
    @DisplayName("Window can be opened in new tab test")
    public void test2() {
        page.openNewTabBtn.click();
        actions.verifyNewTabOpened();
    }
}
