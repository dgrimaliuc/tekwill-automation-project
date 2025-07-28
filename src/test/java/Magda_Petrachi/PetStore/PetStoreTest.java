package Magda_Petrachi.PetStore;

import example.actions.BaseActions;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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
        BaseActions.waitFor(5);
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
    public void newTabAndLocationButtonTest() {
        home.locationSection.locationInput.click();
        String query = "Falesti";
        driver.get("https://petstore-eb41f.web.app/?location=" + query);
        actions.shouldHaveAttribute(home.locationSection.locationInput, "value", query);
        home.locationSection.openNewTab.click();
        assertThat(home.petInSection.title, equalTo("Pets in" + query));
//        actions.shouldHaveAttribute(home.locationSection.locationInput, "value", query);
//        actions.shouldHaveAttribute(home.petInSection.title, "value", query);
//        actions.shouldHaveAttribute(home.adoptionPetSection.title, "value", query);
    }


    @Test
    public void openLocationWithURLlocation() {
        driver.get("https://petstore-eb41f.web.app/?location=Falesti");
        actions.waitForCurrentURLContains("Falesti");
        String location = "Falesti";
        actions.shouldSee(home.petInSection.title);
        actions.shouldSee(home.adoptionPetSection.title);
    }


}

