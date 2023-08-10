package Roman_Marcov.junit;

import Roman_Marcov.junit.actions.ActionsPage;
import org.apache.commons.lang.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import roman_marcov.AdoptPagePets;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static helpers.Helpers.stepResults;
import static org.hamcrest.MatcherAssert.assertThat;

public class JUnitParametrizedTest {
    WebDriver driver = null;
    WebDriverWait wait = null;
    AdoptPagePets page = null;
    ActionsPage actions = null;


    @BeforeEach
    public void before() {
        var pathToChrome = "src/main/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChrome);
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        actions = new ActionsPage(driver);
        wait = new WebDriverWait(driver, 3);
        page = new AdoptPagePets(driver, wait);
        stepResults = new ArrayList<>();
        actions.openRandomLocation();
    }

    @AfterEach
    public void after() {
        driver.quit();
    }

    /**
     * 1 symbol pet name test
     * Space containing pet name test
     * Punctuation symbols containing pet name test
     * Also create a separate test
     * duplicate name of pet (a pet with already existing name should be added without problems )
     */
    @ParameterizedTest(name = "Test name pet: \"{0}\"")
    @ValueSource(strings = {"k", "Pet and NamePet", "~`!@#$%^&*()-_=+.,"})
    public void testNamePetCanBe(String name) {
        actions.addedPetCheck(name);
    }

    @Test
    @DisplayName("1 symbol pet name test")
    public void oneSymbolName() {
        String newPet = RandomStringUtils.random(1, true, true);
        actions.addPetWithName(newPet);
        actions.addedPetCheck(newPet);
    }

    @Test
    @DisplayName("Space containing pet name test")
    public void nameWithSpace() {
        String newPetName = RandomStringUtils.random(5, true, true);
        actions.addPetWithName(newPetName + " " + newPetName);
        actions.addedPetCheck(newPetName + " " + newPetName);
    }

    @Test
    @DisplayName("Punctuation symbols containing pet name test")
    public void nameWithSpecSymbols() {
        String characters = "~`!@#$%^&*()-_=+.,";
        String newPetName = RandomStringUtils.random(10, characters);
        actions.addPetWithName(newPetName);
        actions.addedPetCheck(newPetName);
    }

    @Test
    @DisplayName("Test two different pets can be added with the same Name")
    public void testDuplicatePetName() {
        String petName = "Pet Name";
        actions.addAPetToCurrentLocation(petName);
        actions.addAPetToCurrentLocation(petName);
        assertThat(page.pets.size(), Matchers.equalTo(2));
        assertThat(page.pets.get(0).getText(), Matchers.containsString(petName));
        assertThat(page.pets.get(1).getText(), Matchers.containsString(petName));
    }
}