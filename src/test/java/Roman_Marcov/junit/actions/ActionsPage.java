package Roman_Marcov.junit.actions;

import helpers.Helpers;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import roman_marcov.AdoptPagePets;

import static helpers.Helpers.addQuotes;
import static org.hamcrest.MatcherAssert.assertThat;

public class ActionsPage {
    static Logger log = Logger.getLogger(ActionsPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final AdoptPagePets pagePets;

    public ActionsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
        pagePets = new AdoptPagePets(driver, wait);
    }

    public String openRandomLocation() {
        String openRandomLocation = RandomStringUtils.random(10, true, true);
        log.info("Open Custom locations: " + addQuotes(openRandomLocation));
        driver.get("https://petstore-kafka.swagger.io/?location=" + openRandomLocation);
        Helpers.waitInSeconds(1);
        return openRandomLocation;
    }

    public String addAPetToCurrentLocation(String petName) {
        String PetName = pagePets.petNameInput.getAttribute("value");
        addAPetToCurrentLocation(PetName);
        return PetName;
    }

    public void addPetWithName(String newPetName) {
        log.info("Add a new pet: " + addQuotes(newPetName));
        pagePets.nameInput.clear();
        pagePets.nameInput.sendKeys(newPetName);
        pagePets.buttonAddRescue.click();
    }

    public void addedPetCheck(String PetName) {
        wait.until(ExpectedConditions.textToBePresentInElement(pagePets.firstAddedPet, PetName));
        assertThat(pagePets.firstAddedPet.getText(), Matchers.equalTo(PetName + "\nAVAILABLE"));
    }

}
