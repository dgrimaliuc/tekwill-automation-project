package cristina_savrin.junit.actions;

import cristina_savrin.AdoptPetsPage;
import helpers.Helpers;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static cristina_savrin.AdoptPetsPage.FIRST_ROW_IN_TABLE;
import static helpers.Helpers.addQuotes;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class PetsBaseActions {

    static Logger log = Logger.getLogger(PetsBaseActions.class);
    private final WebDriver driver;
    private final AdoptPetsPage page;
    private final WebDriverWait wait;

    public PetsBaseActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
        page = new AdoptPetsPage(driver, wait);
    }

    public String openRandomLocation() {
        String randomLocation = RandomStringUtils.random(10, true, true);
        log.info("Open Custom locations: " + addQuotes(randomLocation));
        driver.get("https://petstore-kafka.swagger.io/?location=" + randomLocation);
        Helpers.waitInSeconds(1);
        return randomLocation;
    }
    
    public void addAPetToCurrentLocation(String petName) {
        log.info("Add a new pet: " + addQuotes(petName));
        String clearShortcut = Keys.chord(Keys.CONTROL, "a") + Keys.BACK_SPACE;
        page.petNameInput.sendKeys(clearShortcut + petName);
        page.addPetBtn.click();
        verifyPetAdded(petName);
    }

    public void verifyPetAdded(String petName) {
        assertThat(page.pets.size(), equalTo(1));
        wait.until(ExpectedConditions.textToBe(FIRST_ROW_IN_TABLE, petName));
    }
}