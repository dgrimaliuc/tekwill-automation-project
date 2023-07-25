package Leas_Liudmila.junit;

import Leas_Liudmila.LLAdoptPage;
import Leas_Liudmila.junit.actions.AdoptPageActions;
import helpers.Helpers;
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
        myPageLL = new LLAdoptPage(driver);
        myActions.openRandomLocation();
    }

    @AfterEach
    public void after() {
        driver.quit();
    }

    @Test
    @DisplayName("When a pet is added it's status is AVAILABLE")
    public void availableStatus() {
        ArrayList<String> generatedPetNames = new ArrayList<>();
        int petsToAdopt = 2;
        String requiredStatus = "AVAILABLE";

        for (int i = 0; i < petsToAdopt; i++) {
            String newPetName = RandomStringUtils.random(10, true, true);
            generatedPetNames.add(newPetName);
            myActions.addPetWithName(newPetName);
        }

        Helpers.waitInSeconds(1);
        myActions.checkPetStatus(petsToAdopt, requiredStatus);
    }


    @Test
    @DisplayName("When a pet is adopted it's status is ONHOLD")
    public void testAdoptedPetStatus() {
        ArrayList<String> generatedPetNames = new ArrayList<>();
        int petsToAdopt = 2;
        String requiredStatus = "ONHOLD";

        for (int i = 0; i < petsToAdopt; i++) {
            String newPetName = RandomStringUtils.random(10, true, true);
            generatedPetNames.add(newPetName);
            myActions.addPetWithName(newPetName);
        }

        Helpers.waitInSeconds(1);
        myActions.adoptAllPet();
        myActions.checkPetStatus(petsToAdopt, requiredStatus);

    }
}
