package Leas_Liudmila.junit.actions;

import Leas_Liudmila.LLAdoptPage;
import Leas_Liudmila.data.enums.Colors;
import Leas_Liudmila.components.AdoptionsInSection;
import Leas_Liudmila.data.enums.Status;
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
import java.util.List;
import java.util.stream.Collectors;

import static helpers.Helpers.addQuotes;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotEquals;
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


    ArrayList<String> adoptedPetNames = new ArrayList<>();


    public String openRandomLocation() {
        String randomLocationLL = RandomStringUtils.random(10, true, true);
        log.debug("Open Custom locations: " + addQuotes(randomLocationLL));
        driver.get("https://petstore-kafka.swagger.io/?location=" + randomLocationLL);
        Helpers.waitInSeconds(1);
        return randomLocationLL;
    }
    public String openCustomLocation(String location) {
        log.debug("Open Custom locations: " + addQuotes(location));
        driver.get("https://petstore-kafka.swagger.io/?location=" + location);
        Helpers.waitInSeconds(1);
        return location;
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

    public ArrayList<String> addPetWithRandomName(int petsToAdd) {
        ArrayList<String> generatedPetNames = new ArrayList<>();
        for (int i = 0; i < petsToAdd; i++) {
            String newPetName = RandomStringUtils.random(10, true, true);
            generatedPetNames.add(newPetName);
            log.info("Add a new pet: " + addQuotes(newPetName));
            String clearShortcut = Keys.chord(Keys.CONTROL, "a") + Keys.BACK_SPACE;
            myPageLL.nameInput.sendKeys(clearShortcut + newPetName);
            myPageLL.buttonAddRescue.click();
        }
        return generatedPetNames;

    }

    public void addPetWithName(String newPetName) {
        log.info("Add a new pet: " + addQuotes(newPetName));
        String clearShortcut = Keys.chord(Keys.CONTROL, "a") + Keys.BACK_SPACE;
        //Helpers.waitInSeconds(1);
        myPageLL.nameInput.sendKeys(clearShortcut + newPetName);
        myPageLL.buttonAddRescue.click();
        Helpers.waitInSeconds(1);
    }

    public void addListOfPets(List<String> pets){
        for (String pet: pets) {
            addPetWithName(pet);
        }
    }

    public void addedPetCheck(String petName, int index) {
       wait.until(ExpectedConditions.textToBePresentInElement(myPageLL.petsIn.petsName.get(index), petName));
        assertThat(myPageLL.petsIn.pets.get(index).getText(), Matchers.equalTo(petName + "\nAVAILABLE"));
    }

    public void addedPetCheck(List <String> petNames) {
            for (int i = 0; i < petNames.size(); i++) {
                log.info("Pets In section contains the Pet Name: " + addQuotes(petNames.get(i)));
                String petName = petNames.get(i);
                boolean doesExists = myPageLL.petsIn.petsName.stream().anyMatch(petIn -> petIn.getText().equals(petName));
                assertTrue(doesExists);
            }
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
        myPageLL.petsIn.adoptSelPetsBtnActive.click();
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
        myPageLL.petsIn.adoptSelPetsBtnActive.click();
        Helpers.waitInSeconds(1);


    }


    public void adoptGroupsOfPets(int sectionsToCreate, int petsPerSection) {

        for (int i = 0; i < sectionsToCreate; i++) {
            List<WebElement> prefiltered = myPageLL.petsIn.pets.stream()
                    .filter(petInCursor -> petInCursor.getText().contains("AVAILABLE"))
                    .collect(Collectors.toList());

            if (prefiltered.size() > 0) {
                prefiltered.stream()
                        .limit(petsPerSection)
                        .forEach(petInCursor -> petInCursor.click());
                myPageLL.petsIn.adoptSelPetsBtnActive.click();

                Helpers.waitInSeconds(1);
            } else {
                break;
            }
        }
    }

    public void checkElementsInAdoptions(ArrayList<String> generatedPetNames) {
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

    public void approveAdoptionGroup(int indexOfGroup) {
        log.info("Adoptions in section approved" );
        myPageLL.adoptionsInSections.get(indexOfGroup).approveBtn.click();
    }

    public void checkStatusOfAdoptGroup(Status expectedStatus, int indexOfGroup) {
        log.info("Adoptions In section contains Status: " + addQuotes(String.valueOf(expectedStatus)));
        assertThat(myPageLL.adoptionsInSections.get(indexOfGroup).status.getText(), equalTo(String.valueOf(expectedStatus)));
    }

    public void checkPetStatusForName(Status expectedStatus, int indexOfGroup) {

        for (int i = 0; i < myPageLL.adoptionsInSections.get(indexOfGroup).petName.size(); i++) {
            String adoptedPetName = myPageLL.adoptionsInSections.get(indexOfGroup).petName.get(i).getText();
            adoptedPetNames.add(adoptedPetName);
        }

        for (String name : adoptedPetNames) {
            log.info("Pet status: " + addQuotes(name) + addQuotes(String.valueOf(expectedStatus)));
            String petToSearch = myPageLL.petsIn.pets
                    .stream()
                    .filter(pet -> pet.getText().contains(name))
                    .findFirst()
                    .get().getText();
            assertThat(petToSearch, equalTo(name + "\n" + expectedStatus));
        }
    }

    public void adoptsDisappeared(int indexOfGroup) {
        String groupName = myPageLL.adoptionsInSections.get(indexOfGroup).title.getText();
        driver.navigate().refresh();

        for (AdoptionsInSection group : myPageLL.adoptionsInSections) {
            assertNotEquals(groupName, group.title.getText());
        }
        log.info(addQuotes(groupName) + " disappeared");

        for (String name : adoptedPetNames) {
            log.info("Pet " + addQuotes(name) + " disappeared");
            List<WebElement> petToSearch = myPageLL.petsIn.pets
                    .stream()
                    .filter(pet -> pet.getText().contains(name))
                    .collect(Collectors.toList());

            assertThat(0,equalTo(petToSearch.size()));
        }
    }

    public void theGameSectionAftAdopt(int petsToAdd, int sectionsToCreate, int petsToAdopt, String generatedLocation){

        int leftPets = petsToAdd - petsToAdopt;
        assertThat(myPageLL.theGame.petsInInfo.getText(), Matchers.equalTo("Pets in " + generatedLocation + ": " + leftPets));
        log.info("Pets in " + generatedLocation + ": " + leftPets);
        assertThat(myPageLL.theGame.adoptionsInInfo.getText(), Matchers.equalTo("Adoptions in " + generatedLocation + ": " + (sectionsToCreate - 1)));
        log.info("Adoptions in " + generatedLocation + ": " + (sectionsToCreate - 1));

    }
}
