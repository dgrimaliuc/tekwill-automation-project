package cristina_savrin.junit;

import cristina_savrin.AdoptPetsPage;
import cristina_savrin.junit.actions.PetsBaseActions;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static cristina_savrin.AdoptPetsPage.FIRST_ROW_IN_TABLE;
import static cristina_savrin.AdoptPetsPage.SECOND_ROW_IN_TABLE;
import static helpers.Helpers.waitInSeconds;

@Execution(ExecutionMode.SAME_THREAD)
public class PetsJUnitTests {

    WebDriver driver = null;
    PetsBaseActions actions = null;
    WebDriverWait wait = null;
    Logger log = Logger.getLogger(PetsJUnitTests.class);
    AdoptPetsPage page = null;

    @BeforeEach
    public void before() {
        var pathToChrome = "src/main/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChrome);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-debugging-port=9222");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        actions = new PetsBaseActions(driver);
        wait = new WebDriverWait(driver, 5);
        page = new AdoptPetsPage(driver, wait);
        actions.openRandomLocation();
    }

    @AfterEach
    public void after() {
        driver.quit();
    }

    @ParameterizedTest
    @ValueSource(strings = {"x", "Pet Name", "Pet!@#$%^&*()"})
    void addPets(String petName) {
        actions.addAPetToCurrentLocation(petName);
    }

    @Test
    @DisplayName("Duplicate pet name")
    public void duplicateNameTest() {
        String randomPetName = RandomStringUtils.random(10, true, true);
        actions.addAPetToCurrentLocation(randomPetName);
        actions.addAPetToCurrentLocation(randomPetName);
        waitInSeconds(1);
        Assertions.assertEquals(driver.findElement(FIRST_ROW_IN_TABLE).getText(), driver.findElement(SECOND_ROW_IN_TABLE).getText());
    }
}