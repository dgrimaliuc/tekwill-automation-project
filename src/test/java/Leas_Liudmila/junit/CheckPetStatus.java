package Leas_Liudmila.junit;

import Leas_Liudmila.LLAdoptPage;
import Leas_Liudmila.junit.actions.AdoptPageActions;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static helpers.Helpers.stepResults;

public class CheckPetStatus {
    WebDriver driver = null;
    AdoptPageActions myActions = null;
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
        myPageLL = new LLAdoptPage(driver, wait);
        myActions.openRandomLocation();
    }

    @AfterEach
    public void after() {
        driver.quit();
    }

    @Test
    @DisplayName("When a pet is added it's status is AVAILABLE")
    public void availableStatus() {
        String newPetName = RandomStringUtils.random(10, true, true);
        String status = "AVAILABLE";
        myActions.addPetWithName(newPetName);
        myActions.checkPetStatus(newPetName, status);
    }

    @Test
    @DisplayName("When a pet is added it's status is AVAILABLE")
    public void onHoldStatus() {
        String newPetName = RandomStringUtils.random(10, true, true);
        String status = "ONHOLD";
        myActions.addPetWithName(newPetName);
        myActions.adoptPet(newPetName);
        myActions.checkPetStatus(newPetName, status);
    }

}
