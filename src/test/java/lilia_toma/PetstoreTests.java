package lilia_toma;

import internal.BaseTest;
import lilia.toma.PetStore.PetstorePage;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static example.actions.BaseActions.waitFor;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PetstoreTests extends BaseTest {
    PetstorePage pet = new PetstorePage(driver);

    @BeforeEach
    public void setup() {
        log.info("Opening the Petstore page");
        driver.get("https://petstore-eb41f.web.app/");
    }

    @Test
    public void theVisibilityTest() {
        actions.shouldBeDisplayed(pet.location);
        actions.shouldBeDisplayed(pet.theGame);
        actions.shouldBeDisplayed(pet.pets);
        actions.shouldBeDisplayed(pet.adoptions);
    }

    @ParameterizedTest(name = "Change location test '{0}'")
    @ValueSource(strings = {"Fălești", "0cnita", "Vadul-lui-Vodă", "/\1234.@#$%^&*()"})
    public void changeLocationButtonTest(String text) {
        pet.locationInput.click();
        pet.locationInput.clear();
        pet.locationInput.sendKeys(text);
    }

    @Test
    public void defaultLocationPlettTest() {
        actions.shouldBeDisplayed(pet.defaultPlett);
    }

    @Test
    public void openInNewTabButtonTest() {
        actions.shouldBeDisplayed(pet.openInNewTabBtn);
        pet.openInNewTabBtn.click();
        actions.shouldSee(pet.openInNewTabBtn);
    }

    @Test
    public void accesingRandomLocationTest() {
        String location = "Chisinau";
        String fullURL = "https://petstore-eb41f.web.app/" + "?location=" + location;
        driver.get(fullURL);
        waitFor(3);

        actions.shouldBeDisplayed(pet.verifyLocation);
        WebElement locationTitle = driver.findElement(By.cssSelector("h2.text-2xl.ml-4"));

        String locationInputValue = pet.locationInput.getAttribute("value");
        MatcherAssert.assertThat(locationInputValue, CoreMatchers.containsString(location));

        String petsText = pet.pets.getText();
        MatcherAssert.assertThat(petsText, CoreMatchers.containsString(location));

        String adoptionsText = pet.adoptions.getText();
        MatcherAssert.assertThat(adoptionsText, CoreMatchers.containsString(location));

        String theGameText = pet.theGame.getText();
        MatcherAssert.assertThat(theGameText, CoreMatchers.containsString(location));
    }

    @Test
    public void noRowsPetsTest() {
        String text = "burghinafaso";
        pet.locationInput.click();
        pet.locationInput.sendKeys(text);
        actions.shouldHaveTextToBe(pet.petsResult, "No rows. Try reset filters");
    }

    @Test
    public void adoptButtonDisabledTest() {
        assertFalse("Adopt Button este dezactivat by default, dar este activat.", pet.adoptButton.isEnabled());
    }

    @Test
    public void addPetButtonTest() {
        String petNameImputText = pet.petNameImput.getAttribute("value");

        String petsCountText = pet.petsCount.getText();
        int initialPetsCount = Integer.parseInt(petsCountText.replaceAll("[^0-9]", ""));

        pet.addPetButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.textToBePresentInElement((WebElement) pet.petsTable, petNameImputText));
        String petsTableText = ((WebElement) pet.petsTable).getText();
        MatcherAssert.assertThat("Numele generat automat este adaugat in lista.", petsTableText, containsString(petNameImputText));

        WebElement petItem = driver.findElement(By.xpath("//table[@id='pets-table']//tr[.//div[text()='" + petNameImputText + "']]"));
        WebElement statusPet = petItem.findElement(By.cssSelector("[data-t='pet-status']"));
        MatcherAssert.assertThat("Statusul animalului de companie este 'Available'.", statusPet.getText(), equalTo("AVAILABLE"));

//        pet.addPetButton.click();
        String newPetsCountText = pet.petsCount.getText();
        int newPetsCount = Integer.parseInt(newPetsCountText.replaceAll("[^0-9]", ""));
        int expectedPetsCount = initialPetsCount + 1;
        MatcherAssert.assertThat("Contorul de animale s-a incrementat cu 1.", newPetsCount, equalTo(expectedPetsCount));
    }

    @Test
    public void addPetCustomNameTest() {
        String text = "Sarmisegetusa";
        pet.petNameImput.click();
        pet.petNameImput.clear();
        assertTrue(pet.petNameImput.getAttribute("value").isEmpty());
        pet.petNameImput.sendKeys(text);
        pet.addPetButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement petCustomName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//table[@id='pets-table']//tr[.//div[text()='" + text + "']]")));
        String actualPetName = petCustomName.getText();
        MatcherAssert.assertThat("Animalutul a fost adaugat in lista.", actualPetName, CoreMatchers.containsString(text));
    }

    @Test
    public void selectAPetTest() { //minutul 35:50
        String petNameImputText = pet.petNameImput.getAttribute("value");
        pet.petNameImput.click();
        pet.addPetButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//table[@id='pets-table']//tr[.//div[text()='" + petNameImputText + "']]")));
        pet.petCheckbox.click();
        actions.shouldBeDisplayed(pet.petCheckboxIcon);
        wait.until(ExpectedConditions.elementToBeClickable(pet.adoptButton));
        assertTrue("Adopt Button este activat dupa bifarea unui animalut.", pet.adoptButton.isEnabled());
    }

    @Test
    public void deselectAllPetsTest() { //minutul 36:00
        pet.petNameImput.click();
        pet.addPetButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pets-table")));
        List<WebElement> petCheckboxes = driver.findElements(By.cssSelector("div[data-t='checkbox']"));
        assertTrue("Tabelul trebuie sa contina macar un animalut.", petCheckboxes.size() > 0);
        for (WebElement checkbox : petCheckboxes) {
            if(!checkbox.isSelected()) {
                checkbox.click();
            }
        }
        pet.petCheckbox.click();
        pet.deselectButton.click();
        assertFalse("Adopt Button este dezactivat by default, dar este activat.", pet.adoptButton.isEnabled());
    }

    @Test
    public void buttonHoverTest() {
        WebElement addButton = driver.findElement(By.cssSelector("[data-t='add-pet-button']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(addButton).perform();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String hoverColor = addButton.getCssValue("background-color");
        System.out.println("Culoarea butonului la hover este: " + hoverColor);
    }

}