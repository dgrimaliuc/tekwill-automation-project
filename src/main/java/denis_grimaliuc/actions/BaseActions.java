package denis_grimaliuc.actions;

import denis_grimaliuc.components.Adoption;
import denis_grimaliuc.components.Pet;
import denis_grimaliuc.data.enums.Colors;
import denis_grimaliuc.data.enums.Status;
import denis_grimaliuc.pages.AdoptPage;
import helpers.Helpers;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static denis_grimaliuc.data.enums.OS.*;
import static denis_grimaliuc.pages.AdoptPage.*;
import static helpers.Helpers.addQuotes;
import static helpers.PropertiesReader.getProperty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

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
        driver.get(getProperty("base_url") + "?location=" + location);
        Helpers.waitInSeconds(1);
        return location;
    }

    public void verifyPetInGroup(String petName, int petCount) {
        log.info("Verify pet with name: " + addQuotes(petName) + " is added to group");
        driver.navigate().refresh();
        Adoption group = page.adoptions.get(0);
        assertThat(group.petNames.size(), Matchers.is(petCount));
        boolean isFound = false;
        for (WebElement name : group.petNames) {
            if (name.getText().equals(petName))
                isFound = true;
        }
        assertTrue(isFound);
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
        assertNotEquals(petNum, 0);
        assertThat(page.petsIn.pets.size(), Matchers.greaterThanOrEqualTo(petNum));
        for (int i = 0; i < petNum; i++) {
            page.petsIn.pets.get(i).name.click();
        }
        page.petsIn.adoptButton.click();
    }

    public void verifyPetsStatus(Status expectedStatus) {
        for (Pet pet : page.petsIn.pets) {
            String actualStatus = pet.status.getText();
            assertEquals(expectedStatus.toString(), actualStatus);

        }

    }

    public void verifyPetsStatus(String petName, String expectedStatus) {
        List<Pet> foundPets = new ArrayList<>();
        for (Pet pet : page.petsIn.pets) {
            if (pet.name.getText().equals(petName) && pet.status.getText().equals(expectedStatus)) {
                foundPets.add(pet);
            }

        }
        assertThat(foundPets.size(), Matchers.greaterThanOrEqualTo(0));
    }

    public void verifyAdoptsCount(int adoptCount) {
        log.info("Verify Current section contains: " + adoptCount + " adoptions");
        wait.until(ExpectedConditions.numberOfElementsToBe(ADOPT_ROWS, adoptCount));
    }

    public void approveGroupByIndex(int indexOfGroup) {
        log.info("Approve group by index: " + indexOfGroup);
        Adoption group = page.adoptions.get(indexOfGroup);
        group.approve.click();
    }

    public void verifyStatusOfGroup(String status, int indexOfGroup) {
        log.info("Verify status of group by index: " + indexOfGroup);
        Adoption group = page.adoptions.get(0);
        wait.until(ExpectedConditions.textToBePresentInElement(group.status, status));
    }

    public void addAPetsToCurrentLocation(List<String> petNames) {
        for (String petName : petNames) {
            addAPetToCurrentLocation(petName);
        }
    }

    public String clearShortcut() {
        Keys key;
        if (WINDOWS.isCurrentOs()) {
            key = Keys.CONTROL;
        } else if (MAC.isCurrentOs()) {
            key = Keys.COMMAND;
        } else {
            throw new NoSuchElementException("Unsupported OS type: " + addQuotes(getCurrentOS()));
        }

        return Keys.chord(key, "a") + Keys.BACK_SPACE;
    }


    public void addAPetToCurrentLocation(String petName) {
        log.info("Add a new pet: " + addQuotes(petName));
        int petsCountBeforeAdding = page.petsIn.pets.size();
        page.petsIn.petNameInput.sendKeys(clearShortcut() + petName);
        page.petsIn.addPetBtn.click();
        verifyPetAddedInPetSection(petName, petsCountBeforeAdding);
    }

    public void verifyPetAddedInPetSection(String petName, int petCountBefore) {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(ROWS, petCountBefore));
        wait.until(ExpectedConditions.textToBe(FIRST_ROW_IN_TABLE, petName));

    }

    public void assertHoverState(WebElement button, Colors color) {
        hover(button);
        verifyBackgroundColor(button, color);
    }

    public void verifyBackgroundColor(WebElement element, Colors color) {
        String backColor = element.getCssValue("background-color");
        log.info("Assert: " + addQuotes(backColor) + " is equals to " + addQuotes(color.color));
        wait.until(ExpectedConditions.attributeContains(element, "background-color", color.color));
    }

    public void hover(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

}
