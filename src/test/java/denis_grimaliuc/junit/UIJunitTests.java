package denis_grimaliuc.junit;

import denis_grimaliuc.AdoptPage;
import denis_grimaliuc.UIStepDefinition;
import denis_grimaliuc.actions.BaseActions;
import helpers.Helpers;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static denis_grimaliuc.AdoptPage.FIRST_ROW_IN_TABLE;
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
        wait = new WebDriverWait(driver, 5);
        //        Runtime.getRuntime().addShutdownHook(new Thread(() -> driver.quit()));

        page = new AdoptPage(driver);
        actions = new BaseActions(driver, page);
        //        actions.openCustomLocation("Chisinau");
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
    @DisplayName("Verify Pet can have empty name test")
    public void emptyNameTest() {
        String randomPetName = "";
        actions.addAPetToCurrentLocation(randomPetName);
    }


    /**
     * Test: Pet name can be
     * 1 symbol pet name test
     * Space containing pet name test
     * Punctuation symbols containing pet name test
     */
    @ParameterizedTest(name = "Test Pet name can Be \"{0}\"")
    @ValueSource(strings = {"N", "John  Will  123", "!Q#!$%^&*()_"})
    public void testPetNameCanBe(String name) {
        actions.addAPetToCurrentLocation(name);
    }


    /**
     * Duplicate name of pet (a pet with already existing name should be added without problems )
     */
    @Test
    @DisplayName("Test two different pets can be added with the same Name")
    public void testDuplicatePetName() {
        String petName = "Pet Name";
        actions.addAPetToCurrentLocation(petName);
        actions.addAPetToCurrentLocation(petName);
        assertThat(page.petsIn.pets.size(), Matchers.equalTo(2));
        assertThat(page.petsIn.pets.get(0).getText(), Matchers.containsString(petName));
        assertThat(page.petsIn.pets.get(1).getText(), Matchers.containsString(petName));

    }

    /**
     * 12.	Add a pet in Current location, see result in the Game section, status of pet is changed
     */
    @Test
    @DisplayName("Test two different pets can be added with the same Name")
    public void addAPetAndVerifyTheGameSection() {
        int sumOfPets = 5;
        actions.addAPetToCurrentLocation(sumOfPets);
        String validationText = page.theGame.petsInInfo.getText();
        log.info("Verify '" + validationText + "' matches pattern: \"Pets\\\\sin\\\\s\\\\w+:\\\\s\"");
        assertThat(validationText, Matchers.matchesPattern("Pets\\sin\\s\\w+:\\s" + sumOfPets));
    }

    /**
     * Add a pet in Current location, see result in the Game section, status of pet is changed
     */
    @Test
    @DisplayName("Verify User can adopt a Pet")
    public void verifyAdoptionCanBeCreated() {
        int petsToAdopt = 2;
        actions.addAPetToCurrentLocation(3);
        actions.adoptPets(petsToAdopt);
        actions.verifyAdoptIsCreated(1);
        System.out.println();

    }

    @Test
    @DisplayName("Verify Pet Names in adoption")
    public void verifyPetNamesInAdoption() {
        String petName = "SomePetName";
        actions.addAPetToCurrentLocation(petName);
        actions.adoptPets(1);
        actions.verifyPetInGroup(petName, 1);

    }

    @Test
    @DisplayName("When a pet is added it's status is AVAILABLE")
    public void testAddedPet() {
        String petName = "SomePetName";
        actions.addAPetToCurrentLocation(petName);
        WebElement pet = page.petsIn.pets.get(0);
        String status = page.getStatusOfPet(pet);
        assertThat(status, Matchers.is("AVAILABLE"));

    }

    @Test
    @DisplayName("When a pet is adopted it's status is ONHOLD")
    public void testAdoptedPetStatus() {
        String petName = "SomePetName";
        actions.addAPetToCurrentLocation(petName);
        actions.adoptPets(1);
        actions.verifyAdoptIsCreated(1);
        WebElement pet = page.petsIn.pets.get(0);
        String status = page.getStatusOfPet(pet);
        assertThat(status, Matchers.is("ONHOLD"));

    }


    @Test
    @DisplayName("When a pet is adopted it's name should be inside Adopt Group")
    public void testAdoptedPetNameInGroup() {
        String petName = "SomePetName";
        actions.addAPetToCurrentLocation(petName);
        actions.adoptPets(1);
        actions.verifyAdoptIsCreated(1);
        actions.verifyPetNameIsPresentInGroup(petName, 1);
    }

    @Test
    @DisplayName("When a group is approved the status of it should be APPROVED")
    public void testAdoptionStatus() {
        actions.addAPetToCurrentLocation(3);
        actions.adoptPets(3);
        actions.verifyAdoptIsCreated(1);
        actions.approveGroupByIndex(0);
        actions.verifyStatusOfGroup("APPROVED", 1);

    }

    @Test
    @DisplayName("Added pet in different location is not reflected in current one test")
    public void test1() {
        actions.openRandomLocation();
        actions.addAPetToCurrentLocation();

        driver.navigate().back();
        Helpers.waitInSeconds(1);
        assertThat(page.petsIn.pets.size(), equalTo(0));
        wait.until(ExpectedConditions.textToBe(FIRST_ROW_IN_TABLE, "No rows. Try reset filters"));
    }

    @Test
    @DisplayName("Window can be opened in new tab test")
    public void test2() {
        page.openNewTabBtn.click();
        actions.verifyNewTabOpened();
    }
}
