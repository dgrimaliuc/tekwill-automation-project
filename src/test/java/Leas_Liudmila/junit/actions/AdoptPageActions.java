package Leas_Liudmila.junit.actions;

import Leas_Liudmila.LLAdoptPage;
import Leas_Liudmila.data.enums.Colors;
import helpers.Helpers;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

import static helpers.Helpers.addQuotes;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

public class AdoptPageActions {
    static Logger log = Logger.getLogger(AdoptPageActions.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final LLAdoptPage myPageLL;

    public AdoptPageActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
        myPageLL = new LLAdoptPage(driver);
    }

    ArrayList<String> generatedPetNames = new ArrayList<>();


    public String openRandomLocation() {
        String randomLocationLL = RandomStringUtils.random(10, true, true);
        log.debug("Open Custom locations: " + addQuotes(randomLocationLL));
        driver.get("https://petstore-kafka.swagger.io/?location=" + randomLocationLL);
        Helpers.waitInSeconds(1);
        return randomLocationLL;
    }


    public void addPetWithRandomName(int petsToAdd) {

        for (int i = 0; i < petsToAdd; i++) {
            String newPetName = RandomStringUtils.random(10, true, true);
            generatedPetNames.add(newPetName);
            log.info("Add a new pet: " + addQuotes(newPetName));
            String clearShortcut = Keys.chord(Keys.CONTROL, "a") + Keys.BACK_SPACE;
            myPageLL.nameInput.sendKeys(clearShortcut + newPetName);
            myPageLL.buttonAddRescue.click();
        }

    }

    public void addPetWithName(String newPetName) {
        log.info("Add a new pet: " + addQuotes(newPetName));
        String clearShortcut = Keys.chord(Keys.CONTROL, "a") + Keys.BACK_SPACE;
        myPageLL.nameInput.sendKeys(clearShortcut + newPetName);
        myPageLL.buttonAddRescue.click();
    }

    public void addedPetCheck(String petName) {
        wait.until(ExpectedConditions.textToBePresentInElement(myPageLL.firstAddedPet, petName));
        assertThat(myPageLL.firstAddedPet.getText(), Matchers.equalTo(petName + "\nAVAILABLE"));
    }

    public void duplicatedNameCheck(String duplicatedName) {
        log.info("Duplicated pet names with the following name can be added: " + addQuotes(duplicatedName));
        wait.until(ExpectedConditions.textToBePresentInElement(myPageLL.firstAddedPet, duplicatedName));
        assertThat(myPageLL.addedPets.get(0).getText(), Matchers.equalTo(duplicatedName + "\nAVAILABLE"));
        wait.until(ExpectedConditions.textToBePresentInElement(myPageLL.secondAddedPet, duplicatedName));
        assertThat(myPageLL.addedPets.get(1).getText(), Matchers.equalTo(duplicatedName + "\nAVAILABLE"));
    }


    public void adoptPet(String petName) {
        log.info(petName + " is adopted");
        myPageLL.pets.get(0).click();
        myPageLL.adoptSelPetsBtnActive.click();
        Helpers.waitInSeconds(1);

    }

    public void checkPetStatus(int petsToAdopt, String requiredStatus) {
        for (int i = 0; i < petsToAdopt; i++) {
            WebElement pet = myPageLL.petsIn.pets.get(i);
            String status = myPageLL.getStatusOfPet(pet);
            assertThat(status, Matchers.is(requiredStatus));
            log.info("Pet Name and status: " + pet.getText());
        }
    }

    public void adoptAllPet() {
        for (WebElement pet : myPageLL.pets) {
            pet.click();
        }
        myPageLL.adoptSelPetsBtnActive.click();
        Helpers.waitInSeconds(1);

    }

    public void checkElementsInAdoptions() {
        String title = "Adoption:";
        String defaultStatus = "AVAILABLE";
        String approveBtn = "APPROVE";
        String denyBtn = "DENY";

        log.info("Adoptions In section contains title: " + addQuotes(title));
        assertThat(myPageLL.adoptionsInSections.get(0).title.getText(), containsString(title));
        log.info("Adoptions In section contains default Status: " + addQuotes(defaultStatus));
        assertThat(myPageLL.adoptionsInSections.get(0).status.getText(), equalTo(defaultStatus));
        log.info("Adoptions In section contains Approve button: " + addQuotes(approveBtn));
        assertThat(myPageLL.adoptionsInSections.get(0).approveBtn.getText(), equalTo(approveBtn));
        log.info("Adoptions In section contains Deny button: " + addQuotes(denyBtn));
        assertThat(myPageLL.adoptionsInSections.get(0).denyBtn.getText(), equalTo(denyBtn));

        for (int i = 0; i < generatedPetNames.size(); i++) {
            log.info("Adoptions In section contains the Pet Name: " + addQuotes(generatedPetNames.get(i)));
            String adoptedPetName = myPageLL.adoptionsInSections.get(0).petName.get(i).getText();
            assertTrue(generatedPetNames.stream().anyMatch(petTitle -> petTitle.equals(adoptedPetName)));
        }
    }

    public void assertHoverState(WebElement button, Colors color) {
        Actions actions = new Actions(driver);
        actions.moveToElement(button).perform();
        String backgroundColor = button.getCssValue("background-color");
        log.info("Background color " + addQuotes(backgroundColor) + " is equal to " + addQuotes(color.color));
        wait.until(ExpectedConditions.attributeContains(button, "background-color", color.color));


    }


}
