package denis_grimaliuc.actions;

import denis_grimaliuc.AdoptPage;
import helpers.Helpers;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static denis_grimaliuc.AdoptPage.ADOPT_ROWS;
import static denis_grimaliuc.AdoptPage.FIRST_ROW_IN_TABLE;
import static denis_grimaliuc.AdoptPage.ROWS;
import static helpers.Helpers.addQuotes;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class BaseActions {

    static Logger log = Logger.getLogger(BaseActions.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final AdoptPage page;

    public BaseActions(WebDriver driver, AdoptPage page) {
        this.driver = driver;
        this.page = page;
        wait = new WebDriverWait(driver, 5);
    }

    public static Object executeScript(WebDriver driver, String script, Object object) {
        return ((JavascriptExecutor) driver).executeScript(script, object);
    }

    public static void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void verifyNewTabOpened() {
        Object[] tabs = driver.getWindowHandles().toArray();
        assertThat(tabs.length, Matchers.equalTo(2));
        String currentTabId = driver.getWindowHandle();
        assertThat(currentTabId, equalTo(tabs[0]));
    }

    public String openRandomLocation() {
        String randomLocation = RandomStringUtils.random(10, true, true);
        return openCustomLocation(randomLocation);
    }

    public String openCustomLocation(String location) {
        log.info("Open Custom locations: " + addQuotes(location));
        driver.get("https://petstore-kafka.swagger.io/?location=" + location);
        Helpers.waitInSeconds(1);
        return location;
    }

    public void verifyPetAdded(String petName, int petsCountBeforeAdding) {
        driver.navigate().refresh();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(ROWS, petsCountBeforeAdding));
        wait.until(ExpectedConditions.textToBe(FIRST_ROW_IN_TABLE, petName));
    }


    public void verifyPetNameIsPresentInGroup(String petName, int index) {
        log.info("Verify group by index: " + index + " contains pet with name: " + petName);
        WebElement group = driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div[2]//div[@class='mt-2 border-gray-400 border p-4 rounded-md']" + "[" + index + "]"));
        List<WebElement> petNames = group.findElements(By.xpath("//div[contains(@class,'rounded-md')]"));
        boolean isFound = false;
        for (WebElement name : petNames) {
            if (name.getText().equals(petName))
                isFound = true;
        }
        assertThat(isFound, Matchers.is(true));

    }

    public String addAPetToCurrentLocation() {
        String newPetName = page.petsIn.petNameInput.getAttribute("value");
        addAPetToCurrentLocation(newPetName);
        return newPetName;
    }


    public void addAPetToCurrentLocation(int petNum) {
        for (int i = 0; i < petNum; i++) {
            String newPetName = RandomStringUtils.random(10, true, true);
            addAPetToCurrentLocation(newPetName);
        }
    }

    public void adoptPets(int petNum) {
        assertThat(page.petsIn.pets.size(), Matchers.greaterThanOrEqualTo(petNum));
        for (int i = 0; i < petNum; i++) {
            page.petsIn.pets.get(i).click();
        }
        page.petsIn.adoptButton.click();
    }

    public void verifyAdoptIsCreated(int adoptCount) {
        log.info("Verify Current section contains: " + adoptCount + " adoptions");
        driver.navigate().refresh();
        wait.until(ExpectedConditions.numberOfElementsToBe(ADOPT_ROWS, adoptCount));
    }

    public void approveGroupByIndex(int indexOfGroup) {
        log.info("Approve group by index: " + indexOfGroup);
        WebElement group = driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div[2]//div[@class='mt-2 border-gray-400 border p-4 rounded-md']" + "[" + indexOfGroup + "]"));
        WebElement approveBtn = group.findElement(By.xpath("//button[text()=' Approve ']"));
        approveBtn.click();
    }

    public void verifyStatusOfGroup(String status, int indexOfGroup) {
        log.info("Verify status of group by index: " + indexOfGroup);
        WebElement group = driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div[2]//div[@class='mt-2 border-gray-400 border p-4 rounded-md']" + "[" + indexOfGroup + "]"));
        WebElement statusOfGr = group.findElements(By.tagName("span")).get(1);
        wait.until(ExpectedConditions.textToBePresentInElement(statusOfGr, status));
    }

    public void addAPetToCurrentLocation(String petName) {
        log.info("Add a new pet: " + addQuotes(petName));
        int petsCountBeforeAdding = page.petsIn.pets.size();
        String clearShortcut = Keys.chord(Keys.COMMAND, "a") + Keys.BACK_SPACE;
        page.petsIn.petNameInput.sendKeys(clearShortcut + petName);
        page.petsIn.addPetBtn.click();
        verifyPetAdded(petName, petsCountBeforeAdding);
    }

}
