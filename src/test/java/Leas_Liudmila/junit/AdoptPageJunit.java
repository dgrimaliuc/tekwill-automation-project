package Leas_Liudmila.junit;

import Leas_Liudmila.LLAdoptPage;
import Leas_Liudmila.junit.actions.AdoptPageActions;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static helpers.Helpers.stepResults;

/* 1. 1 symbol pet name test
2. Space containing pet name test
3. Punctuation symbols containing pet name test
Also create a separate test
 4. duplicate name of pet (a pet with already existing name should be added without problems ) */
public class AdoptPageJunit {
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
    @DisplayName("1 symbol pet name test")
    public void oneSymbolName() {
        String newPetName = RandomStringUtils.random(1, true, true);
        myActions.addPetWithName(newPetName);
        myActions.addedPetCheck(newPetName);
    }

    @Test
    @DisplayName("Space containing pet name test")
    public void nameWithSpace() {
        String newPetName = RandomStringUtils.random(5, true, true);
        myActions.addPetWithName(newPetName + " " + newPetName);
        myActions.addedPetCheck(newPetName + " " + newPetName);
    }

    @Test
    @DisplayName("Punctuation symbols containing pet name test")
    public void nameWithSpecSymbols() {
        String characters = "~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        String newPetName = RandomStringUtils.random(15, characters);
        myActions.addPetWithName(newPetName);
        myActions.addedPetCheck(newPetName);
    }

    @Test
    @DisplayName("duplicate name of pet")
    public void duplicatedName() {
        String newPetName = "Charlie";
        myActions.addPetWithName(newPetName);
        myActions.addPetWithName(newPetName);
        myActions.duplicatedNameCheck(newPetName);
    }

    @Test
    @DisplayName("Verify that AdoptionsIn group contains Title, status, petNames, approve and deny buttons")
    public void checkFields(){
        String newPetName = RandomStringUtils.random(1, true, true);
        myActions.addPetWithName(newPetName);
        myActions.adoptPet(newPetName);
        WebElement pet = myPageLL.pets.get(0);



    }

}
