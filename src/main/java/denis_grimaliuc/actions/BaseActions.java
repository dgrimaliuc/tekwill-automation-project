package denis_grimaliuc.actions;

import denis_grimaliuc.AdoptPage;
import helpers.Helpers;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static denis_grimaliuc.AdoptPage.*;
import static helpers.Helpers.addQuotes;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class BaseActions {

    static Logger log = Logger.getLogger(BaseActions.class);
    private final WebDriver driver;
    private final AdoptPage page;
    private final WebDriverWait wait;

    public BaseActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
        page = new AdoptPage(driver, wait);
    }

    public void verifyNewTabOpened() {
        Object[] tabs = driver.getWindowHandles().toArray();
        assertThat(tabs.length, Matchers.equalTo(2));
        String currentTabId = driver.getWindowHandle();
        assertThat(currentTabId, equalTo(tabs[0]));
    }

    public String openRandomLocation() {
        String randomLocation = RandomStringUtils.random(10, true, true);
        log.info("Open Custom locations: " + addQuotes(randomLocation));
        driver.get("https://petstore-kafka.swagger.io/?location=" + randomLocation);
        Helpers.waitInSeconds(1);
        return randomLocation;
    }

    public void verifyPetAdded(String petName, int petsCountBeforeAdding) {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(ROWS, petsCountBeforeAdding));
        wait.until(ExpectedConditions.textToBe(FIRST_ROW_IN_TABLE, petName));
    }


    public String addAPetToCurrentLocation() {
        String newPetName = page.petNameInput.getAttribute("value");
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
        assertThat(page.pets.size(), Matchers.greaterThanOrEqualTo(petNum));
        for (int i = 0; i < petNum; i++) {
            page.pets.get(i).click();
        }
        page.adoptButton.click();
    }

    public void verifyAdoptIsCreated(int adoptCount) {
        log.info("Verify Current section contains: " + adoptCount + " adoptions");
        wait.until(ExpectedConditions.numberOfElementsToBe(ADOPT_ROWS, adoptCount));
    }

    public void addAPetToCurrentLocation(String petName) {
        log.info("Add a new pet: " + addQuotes(petName));
        int petsCountBeforeAdding = page.pets.size();
        String clearShortcut = Keys.chord(Keys.CONTROL, "a") + Keys.BACK_SPACE;
        page.petNameInput.sendKeys(clearShortcut + petName);
        page.addPetBtn.click();
        verifyPetAdded(petName, petsCountBeforeAdding);
    }

}
