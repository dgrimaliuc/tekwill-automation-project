package denis_grimaliuc.testNG;

import denis_grimaliuc.actions.BaseActions;
import denis_grimaliuc.pages.AdoptPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static helpers.Helpers.getExcelData;
import static helpers.Helpers.stepResults;

public class DataProviderTests {

    List<String> petNames = new ArrayList<>(List.of(new String[]{"Loki", "Milo", "Dylan", "Olli"}));
    WebDriver driver = null;
    WebDriverWait wait = null;
    Logger log = Logger.getLogger(DataProviderTests.class);
    AdoptPage page = null;
    BaseActions actions = null;

    @Test
    public void softAssertionTest() {
        var softAssertion = new SoftAssert();
        softAssertion.assertEquals(3, 1);
        softAssertion.assertEquals(3, 3);
        softAssertion.assertEquals(2, 4);
        softAssertion.assertNotEquals(true, false);
        softAssertion.assertEquals(false, true);
        softAssertion.assertAll();
    }

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

    @AfterMethod
    public void after() {
        driver.quit();
    }


    // 1.	Location input test empty, space containing, start/end with space, long location tests
    @DataProvider(name = "testDataNumbers")
    public Object[][] provideTestData() {
        return getExcelData("src/main/resources/dataProviders/Locations.xlsx", "Locations");
    }

    @Test(dataProvider = "testDataNumbers")
    public void testAddition(String location) {
        var soft = new SoftAssert();
        actions.openCustomLocation(location);
        String petsInTitle = page.petsIn.sectionTitle.getText();
        String adoptionTitle = page.adoptsTitle.getText();
        String locationInput = page.locationInput.getAttribute("value");

        soft.assertEquals(petsInTitle, "Pets in " + location);
        soft.assertEquals(adoptionTitle, "Adoptions in " + location);
        soft.assertEquals(locationInput, location);
        soft.assertAll();
    }


    // 2.	Create pets using parameters like: location, petnames
    @DataProvider(name = "locationPetNames")
    public Object[][] provideTestData2() {
        return getExcelData("src/main/resources/dataProviders/PetsInCustomLocations.xlsx", "Pets");
    }

    @Test(dataProvider = "locationPetNames")
    public void testPetNameInCustomLocations(String location, String petNames) {
        String[] pets = petNames.split(" ");
        actions.openCustomLocation(location);
        actions.addAPetsToCurrentLocation(List.of(pets));

    }

    //3.	Create adoptions using parameters: location, petnames
    @DataProvider(name = "locationAdopts")
    public Object[][] provideTestData3() {
        return new Object[][]{
                {"Chisinau", petNames},
                {"Belt", petNames},
                {"Falesti", petNames},
        };
    }

    @Test(dataProvider = "locationPetNames")
    public void testAdoptsInCustomLocations(String location, String petNames) {
        String[] pets = petNames.split(" ");
        actions.openCustomLocation(location);
        actions.addAPetsToCurrentLocation(List.of(pets));
        int adoptionCount = page.adoptions.size();
        actions.adoptPets(pets.length);
        actions.verifyAdoptsCount(adoptionCount + 1);
        for (String petName : pets) {
            actions.verifyPetsStatus(petName, "ONHOLD");
        }


    }


}
