package Leas_Liudmila.testNG;

import Leas_Liudmila.LLAdoptPage;
import Leas_Liudmila.junit.actions.AdoptPageActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static helpers.Helpers.getExcelData;

public class DataProviderTests {
    WebDriver driver = null;
    AdoptPageActions myActions = null;
    WebDriverWait wait = null;
    LLAdoptPage myPageLL = null;

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

    @DataProvider(name = "TestData")
    public Object[][] testData(){
        return getExcelData("src/test/resources/Leas_Liudmila/dataProviders/AdoptionsData.xlsx", "testData");
    }

    @Test(dataProvider = "TestData")
    public void testLocationWithPets(String location, String petNames){
        String[] pets = petNames.split(",");
        myActions.openCustomLocation(location);
        myActions.addListOfPets(List.of(pets));
        myActions.addedPetCheck(List.of(pets));
    }
}
