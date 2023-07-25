package Leas_Liudmila.junit.actions;

import Leas_Liudmila.LLAdoptPage;
import helpers.Helpers;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static helpers.Helpers.addQuotes;
import static org.hamcrest.MatcherAssert.assertThat;

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

    public String openRandomLocation() {
        String randomLocationLL = RandomStringUtils.random(10, true, true);
        log.debug("Open Custom locations: " + addQuotes(randomLocationLL));
        driver.get("https://petstore-kafka.swagger.io/?location=" + randomLocationLL);
        Helpers.waitInSeconds(1);
        return randomLocationLL;
    }


    public void addPetWithName(String newPetName) {
        log.info("Add a new pet: " + addQuotes(newPetName));
        myPageLL.nameInput.clear();
        Helpers.waitInSeconds(1);
        myPageLL.nameInput.sendKeys(newPetName);
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


}
