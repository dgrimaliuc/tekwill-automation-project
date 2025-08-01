package Magda_Petrachi.PetStore;

import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;

public class PetStoreTest extends BaseTest {

    PetStorePage home = new PetStorePage(driver);

    @BeforeEach
    void setup() {
        log.info("Open Pet Store page");
        driver.get("https://petstore-eb41f.web.app/");
    }

    //
    @Test
    public void checkLocationOnPage() {

        actions.waitForCurrentURLContains("location=Plett");
//        BaseActions.waitFor(5);
        actions.shouldSee(home.infoSection.title);
        actions.shouldSee(home.petInSection.title);
        actions.shouldSee(home.adoptionPetSection.title);
    }

    @Test
    public void newLocationButtonTest() {
        home.locationSection.locationInput.click();
        String query = "Falesti";
        driver.get("https://petstore-eb41f.web.app/?location=" + query);
        actions.shouldHaveAttribute(home.locationSection.locationInput, "value", query);
    }

    @Test
    public void numberLocationButtonTest() {
        home.locationSection.locationInput.click();
        String query = "42425";
        driver.get("https://petstore-eb41f.web.app/?location=" + query);
        actions.shouldHaveAttribute(home.locationSection.locationInput, "value", query);
    }


    @Test
    public void openNewTabClickTest() {
        home.locationSection.openNewTab.click();

    }


    @Test
    public void openLocationWithURLlocation() {
        String location = "Falesti";
        driver.get("https://petstore-eb41f.web.app/?location=Falesti");
        actions.waitForCurrentURLContains("=Falesti");

        actions.shouldSee(home.petInSection.title);
        actions.shouldSee(home.adoptionPetSection.title);
    }

    @Test
    public void emptyPetSectionTest() {
        home.locationSection.locationInput.click();
        home.locationSection.locationInput.clear();
        String query = "aaaaaaa";
        driver.get("https://petstore-eb41f.web.app/?location=" + query);
        actions.shouldHaveTextToBe(home.petInSection.noResult, "No rows. Try reset filters");
    }

    @Test
    public void buttonAdoptIsDisableTest() {
        assertFalse("Adoption pet button is be disabled", home.petInSection.adoptionButton.isEnabled());
    }


}

