package Leas_Liudmila.testNG;

import Leas_Liudmila.LLAdoptPage;
import Leas_Liudmila.junit.actions.AdoptPageActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static Leas_Liudmila.data.enums.Colors.BLUE_BACKGROUND_COLOR;
import static Leas_Liudmila.data.enums.Colors.DEFAULT_BACKGROUND_COLOR;
import static helpers.Helpers.stepResults;

public class TestNgTests {
    WebDriver driver = null;
    AdoptPageActions myActions = null;
    WebDriverWait wait = null;
    LLAdoptPage myPageLL = null;

    @BeforeMethod
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

    @AfterMethod
    public void after() {
        driver.quit();
    }

    @Test(testName = "Hovered background color of DESELECT button")
    public void deselectBtnTest() {
        myActions.addPetWithRandomName(1);
        myPageLL.petsIn.pets.get(0).click();
        myActions.assertHoverState(myPageLL.petsIn.deselectBtnActive, BLUE_BACKGROUND_COLOR);
    }

    @Test(testName = "Default background color of DESELECT button")
    public void defaultDeselectBtnTest() {
        myActions.assertHoverState(myPageLL.petsIn.deselectBtnActive, DEFAULT_BACKGROUND_COLOR);
    }

    @Test(testName = "Default background color of ADOPT SELECTED PETS button")
    public void defaultAdoptSelPetsBtnTest() {
        myActions.assertHoverState(myPageLL.petsIn.adoptSelPetsBtnActive, DEFAULT_BACKGROUND_COLOR);
    }
}
