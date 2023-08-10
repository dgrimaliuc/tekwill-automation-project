package Leas_Liudmila.testNG;

import Leas_Liudmila.LLAdoptPage;
import Leas_Liudmila.junit.actions.AdoptPageActions;
import helpers.Helpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static Leas_Liudmila.data.enums.Status.ADOPTED;
import static Leas_Liudmila.data.enums.Status.APPROVED;

public class AdoptionTests {
    WebDriver driver = null;
    AdoptPageActions myActions = null;
    WebDriverWait wait = null;
    LLAdoptPage myPageLL = null;

    int petsToAdopt = 3;
    int sectionsToCreate = 2;
    int petsToAdd = 10;

    String generatedLocation;

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
        // stepResults = new ArrayList<>();
        myPageLL = new LLAdoptPage(driver);
    }

    @AfterMethod
    public void after() {
        driver.quit();
    }

    //10 Pets added to location
    //Two Adoptions with 3 pets each are created

    @BeforeMethod
    public void setupPage() {

        generatedLocation = myActions.openRandomLocation();
        myActions.addPetWithRandomName(petsToAdd);

        Helpers.waitInSeconds(1);
        myActions.adoptGroupsOfPets(sectionsToCreate, petsToAdopt);
    }

    @Test
    public void adoptedGroupTest() {
        //Approve second adoption
        int indexOfGroup = 1;
        Helpers.waitInSeconds(1);
        myActions.approveAdoptionGroup(indexOfGroup);
        Helpers.waitInSeconds(1);
        //Verify status of adoption changed to APPROVED
        myActions.checkStatusOfAdoptGroup(APPROVED, indexOfGroup);
        //Verify status of pets changed to ADOPTED
        myActions.checkPetStatusForName(ADOPTED, indexOfGroup);
        //Verify adopted group and adopted Pet disappeared
        myActions.adoptsDisappeared(indexOfGroup);
        myActions.theGameSectionAftAdopt(petsToAdd, sectionsToCreate, petsToAdopt, generatedLocation);


    }
}
