package denis_grimaliuc.testNG;

import denis_grimaliuc.BaseTest;
import org.testng.annotations.Test;

import static denis_grimaliuc.data.enums.Colors.*;

public class TestNGClass extends BaseTest {

    @Test(testName = "Hover [Change Location] button test")
    public void changeLocationButtonTest() {
        actions.assertHoverState(page.changeLocationBtn, BLUE_BACK_COLOR);
    }

    @Test(testName = "Hover [Open In New Tab] button test")
    public void openInNewTabTest() {
        actions.assertHoverState(page.openNewTabBtn, BLUE_BACK_COLOR);
    }

    @Test(testName = "Hover [Add Rescue] button test")
    public void addPetButtonTest() {
        actions.assertHoverState(page.petsIn.addPetBtn, BLUE_BACK_COLOR);
    }

    @Test(testName = "Hover [Deselect] button test")
    public void deselectButtonTest() {
        actions.addAPetToCurrentLocation(1);
        page.petsIn.pets.get(0).name.click();
        actions.assertHoverState(page.petsIn.deselect, BLUE_BACK_COLOR);
    }

    @Test(testName = "Hover [Adopt Selected] button test")
    public void adoptSelectedPetsButtonTest() {
        actions.addAPetToCurrentLocation(1);
        page.petsIn.pets.get(0).name.click();
        actions.assertHoverState(page.petsIn.adoptButton, ORANGE_BACK_COLOR);
    }

    @Test(testName = "Hover [Approve Adopt] button test")
    public void approveAdoptButtonTest() {
        int petsToAdopt = 1;
        actions.addAPetToCurrentLocation(petsToAdopt);
        actions.adoptPets(petsToAdopt);
        actions.verifyAdoptsCount(1);
        actions.assertHoverState(page.adoptions.get(0).approve, GREEN_BACK_COLOR);
    }

    @Test(testName = "Hover [Deny Adopt] button test")
    public void denyAdoptButtonTest() {
        int petsToAdopt = 1;
        actions.addAPetToCurrentLocation(petsToAdopt);
        actions.adoptPets(petsToAdopt);
        actions.verifyAdoptsCount(1);
        actions.assertHoverState(page.adoptions.get(0).deny, RED_BACK_COLOR);
    }
}
