package denis_grimaliuc.testNG;

import denis_grimaliuc.AdoptPage;
import denis_grimaliuc.UIStepDefinition;
import denis_grimaliuc.actions.BaseActions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static denis_grimaliuc.data.enums.Colors.BLUE_BACK_COLOR;
import static denis_grimaliuc.data.enums.Colors.GREEN_BACK_COLOR;
import static denis_grimaliuc.data.enums.Colors.ORANGE_BACK_COLOR;
import static denis_grimaliuc.data.enums.Colors.RED_BACK_COLOR;

public class TestNGClass {

    WebDriver driver = null;
    BaseActions actions = null;
    WebDriverWait wait = null;
    Logger log = Logger.getLogger(UIStepDefinition.class);
    AdoptPage page = null;

    @BeforeMethod
    public void before() {
        var pathToChrome = "src/main/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChrome);
        ChromeOptions options = new ChromeOptions();
        // Set desired options
        options.addArguments("--headless"); // Run Chrome in headless mode
        // Create ChromeDriver with the specified options
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> driver.quit()));

        page = new AdoptPage(driver);
        actions = new BaseActions(driver, page);
        //        actions.openCustomLocation("Chisinau");
        actions.openRandomLocation();
    }


    @AfterMethod
    public void after() {
        driver.quit();
    }


    @Test(testName = "Hover [Change Location] button test")
    public void changeLocationButtonTest() {
        actions.assertHoverState(page.changeLocationBtn, BLUE_BACK_COLOR);
    }

    @Test(testName = "Hover [Open In New Tab] button test")
    public void openInNewTabTest() {
        actions.assertHoverState(page.openNewTabBtn, BLUE_BACK_COLOR);
    }

    @Test(testName = "Hover [Add Rescue] button test")
    public void addPetButtonTest() {
        actions.assertHoverState(page.petsIn.addPetBtn, BLUE_BACK_COLOR);
    }

    @Test(testName = "Hover [Deselect] button test")
    public void deselectButtonTest() {
        actions.addAPetToCurrentLocation(1);
        page.petsIn.pets.get(0).name.click();
        actions.assertHoverState(page.petsIn.deselect, BLUE_BACK_COLOR);
    }

    @Test(testName = "Hover [Adopt Selected] button test")
    public void adoptSelectedPetsButtonTest() {
        actions.addAPetToCurrentLocation(1);
        page.petsIn.pets.get(0).name.click();
        actions.assertHoverState(page.petsIn.adoptButton, ORANGE_BACK_COLOR);
    }

    @Test(testName = "Hover [Approve Adopt] button test")
    public void approveAdoptButtonTest() {
        int petsToAdopt = 1;
        actions.addAPetToCurrentLocation(petsToAdopt);
        actions.adoptPets(petsToAdopt);
        actions.verifyAdoptsCount(1);
        actions.assertHoverState(page.adoptions.get(0).approve, GREEN_BACK_COLOR);
    }

    @Test(testName = "Hover [Deny Adopt] button test")
    public void denyAdoptButtonTest() {
        int petsToAdopt = 1;
        actions.addAPetToCurrentLocation(petsToAdopt);
        actions.adoptPets(petsToAdopt);
        actions.verifyAdoptsCount(1);
        actions.assertHoverState(page.adoptions.get(0).deny, RED_BACK_COLOR);
    }
}
