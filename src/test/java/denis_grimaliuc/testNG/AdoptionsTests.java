package denis_grimaliuc.testNG;

import denis_grimaliuc.AdoptPage;
import denis_grimaliuc.actions.BaseActions;
import denis_grimaliuc.components.Adoption;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static denis_grimaliuc.data.enums.Colors.GRAY_BACK_COLOR;
import static denis_grimaliuc.data.enums.Status.ADOPTED;
import static denis_grimaliuc.data.enums.Status.APPROVED;
import static denis_grimaliuc.data.enums.Status.AVAILABLE;
import static denis_grimaliuc.data.enums.Status.DENIED;
import static helpers.Helpers.stepResults;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;

public class AdoptionsTests {
    List<String> petNames = new ArrayList<>(List.of(new String[]{"Loki", "Milo", "Dylan", "Olli"}));
    WebDriver driver = null;
    WebDriverWait wait = null;
    Logger log = Logger.getLogger(DataProviderTests.class);
    AdoptPage page = null;
    BaseActions actions = null;


    @BeforeMethod
    public void before() {
        var pathToChrome = "src/main/resources/chromedriver_mac";
        System.setProperty("webdriver.chrome.driver", pathToChrome);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);
        stepResults = new ArrayList<>();
        page = new AdoptPage(driver);
        actions = new BaseActions(driver, page);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> driver.quit()));
    }

    @BeforeMethod
    public void setupPage() {
        int petsToAdopt = 2;
        actions.openRandomLocation();
        actions.addAPetToCurrentLocation(petsToAdopt);
        actions.adoptPets(petsToAdopt);
        actions.verifyAdoptsCount(1);
    }


    @AfterMethod
    public void after() {
        driver.quit();
    }

    /*
    Approve a group, approve button and deny are disabled
    Status of group is APPROVED
    Status of pet ADOPTED
     */
    @Test
    public void testStatusesInCustomLocations() {
        Adoption adopt = page.adoptions.get(0);
        adopt.approve.click();
        // Verify buttons are disabled
        wait.until(not(elementToBeClickable(adopt.approve)));
        assertFalse(adopt.deny.isEnabled());
        // Verify back color
        actions.verifyBackgroundColor(adopt.approve, GRAY_BACK_COLOR);
        actions.verifyBackgroundColor(adopt.deny, GRAY_BACK_COLOR);
        // Verify status
        assertThat(adopt.status.getText(), equalTo(APPROVED.toString()));
        actions.verifyPetsStatus(ADOPTED);
    }


    /*
    Create a group with several pets
    After approving a group
    refresh the page,
    pet should disappear
    Info in the game section is actual
     */
    @Test
    public void testAdoptsInCustomLocations() {
        Adoption adopt = page.adoptions.get(0);
        adopt.approve.click();
        driver.navigate().refresh();
        actions.verifyAdoptsCount(0);
        assertThat(page.petsIn.pets.size(), Matchers.equalTo(0));
        assertThat(page.theGame.adoptsInfo.getText(), equalTo("No adoptions. Go get those pets adopted!"));
        assertThat(page.theGame.petsInInfo.getText(), equalTo("No pets. Go rescue some pets!"));
    }


    @Test
    public void testAdoptCanBeDenied() {
        Adoption adopt = page.adoptions.get(0);
        adopt.deny.click();

        // Verify buttons are disabled
        wait.until(not(elementToBeClickable(adopt.approve)));
        assertFalse(adopt.deny.isEnabled());
        // Verify back color
        actions.verifyBackgroundColor(adopt.approve, GRAY_BACK_COLOR);
        actions.verifyBackgroundColor(adopt.deny, GRAY_BACK_COLOR);
        // Verify status
        actions.verifyStatusOfGroup(DENIED.toString(), 0);
        actions.verifyPetsStatus(AVAILABLE);
        // Verify adopt dispersed and pets no
        driver.navigate().refresh();
        actions.verifyAdoptsCount(0);
        assertThat(page.petsIn.pets.size(), Matchers.equalTo(2));
    }

}
