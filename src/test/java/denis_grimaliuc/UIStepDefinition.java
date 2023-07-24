package denis_grimaliuc;

import helpers.Helpers;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static helpers.Helpers.addQuotes;
import static helpers.Helpers.stepResults;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class UIStepDefinition {
    WebDriver driver = null;
    WebDriverWait wait = null;
    Logger log = Logger.getLogger(UIStepDefinition.class);
    AdoptPage page = null;

    @Before
    public void before() {
        var pathToChrome = "src/main/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChrome);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);
        stepResults = new ArrayList<>();
        page = new AdoptPage(driver);
    }

    @After
    public void after() {
        driver.quit();
    }

    @Given("Adopt Page is Open")
    public void adoptPageIsOpen() {
        driver.get("https://petstore-kafka.swagger.io/?location=Chisinau");
    }

    @Then("Verify URL of Page")
    public void verifyURLOfPage() {
        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl, Matchers.equalTo("https://petstore-kafka.swagger.io/?location=Chisinau"));
    }

    @Then("Hover Change Location button")
    public void hoverChangeLocationButton() {
        WebElement button = driver.findElement(By.xpath("//div[./input[@id='location-input']]//div[2]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(button).perform();
    }

    @When("Change location to {string}")
    public void changeLocationTo(String location) {
        log.debug("Enter [" + location + "] in location text input");
        WebElement input = driver.findElement(By.xpath("//input[@id='location-input']"));
        input.clear();
        input.sendKeys(location);

        log.debug("Click on [Change location] button");
        WebElement changeLocationButton = driver.findElement(By.xpath("//div[./input[@id='location-input']]//div[1]/button"));
        changeLocationButton.click();
    }

    @Then("Verify location changed {string}")
    public void verifyLocationChanged(String newLocation) {
        log.debug("Verify new location is displayed: " + addQuotes(newLocation));
        WebElement input = driver.findElement(By.xpath("//input[@id='location-input']"));
        WebElement petsLocationEl = driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div[1]//h2"));
        WebElement adoptTitle = driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div[2]/h2"));

        assertThat(petsLocationEl.getText(), Matchers.equalTo("Pets in " + newLocation));
        assertThat(adoptTitle.getText(), Matchers.equalTo("Adoptions in " + newLocation));
        assertThat(input.getAttribute("value"), Matchers.equalTo(newLocation));
    }

    @Given("A custom location in open {string}")
    public void aCustomLocationInOpen(String customLocation) {

        log.debug("Open Custom locations: " + addQuotes(customLocation));
        if (customLocation == null || customLocation.isEmpty()) {
            throw new RuntimeException("Custom location is Empty");
        }
        driver.get("https://petstore-kafka.swagger.io/?location=" + customLocation);

    }

    @Then("Verify Text in Input field {string}")
    public void verifyTextInInputField(String customLocation) {
        customLocation = Helpers.getValue(customLocation, String.class);
        log.debug("Verify custom location in text input: " + addQuotes(customLocation));
        assertThat(page.locationInput.getAttribute("value"), Matchers.equalTo(customLocation));
    }

    @Then("Verify Pets in section title {string}")
    public void verifyPetsInSectionTitle(String arg0) {
        arg0 = Helpers.getValue(arg0, String.class);
        log.debug("Verify custom location in Pets In section: " + addQuotes(arg0));
        assertThat(page.petsTitle.getText(), Matchers.equalTo("Pets in " + arg0));
    }

    @Then("Verify Adopt in section title {string}")
    public void verifyAdoptInSectionTitle(String arg0) {
        arg0 = Helpers.getValue(arg0, String.class);
        log.debug("Verify custom location in Adopt In section: " + addQuotes(arg0));
        assertThat(page.adoptsTitle.getText(), Matchers.equalTo("Adoptions in " + arg0));
    }

    @Then("Verify Default text in The game section")
    public void verifyDefaultTextInTheGameSection() {
        assertThat(page.petsInInfo.getText().trim(), Matchers.equalTo("No pets. Go rescue some pets!"));
        assertThat(page.adoptsInfo.getText().trim(), Matchers.equalTo("No adoptions. Go get those pets adopted!"));
    }

    @Given("Open a Random location")
    public void openARandomLocation() {
        String randomLocation = RandomStringUtils.random(10, true, true);
        log.debug("Open Custom locations: " + addQuotes(randomLocation));
        driver.get("https://petstore-kafka.swagger.io/?location=" + randomLocation);
        stepResults.add(randomLocation);
    }

    @Then("Verify new tab can be opened")
    public void verifyNewTabCanBeOpened() {
        page.openNewTabBtn.click();
        Object[] tabs = driver.getWindowHandles().toArray();
        assertThat(tabs.length, Matchers.equalTo(2));
        String currentTabId = driver.getWindowHandle();
        assertThat(currentTabId, equalTo(tabs[0]));
    }

    @Then("Add a pet in current location")
    public void addAPetInCurrentLocation() {
        Helpers.waitInSeconds(1);
        String newPetName = page.petNameInput.getAttribute("value");
        page.addPetBtn.click();
        assertThat(page.pets.size(), equalTo(1));
        wait.until(ExpectedConditions.textToBe(By.xpath("//table/tbody/tr[1]"), newPetName + "\nAVAILABLE"));
    }

    @And("Verify pet is not added in previous location")
    public void verifyPetIsNotAddedInPreviousLocation() {
        driver.navigate().back();
        Helpers.waitInSeconds(1);
        assertThat(page.pets.size(), equalTo(1));
        wait.until(ExpectedConditions.textToBe(By.xpath("//table/tbody/tr[1]"), "No rows. Try reset filters"));
    }
}
