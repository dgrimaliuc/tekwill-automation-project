package Leas_Liudmila.junit;

import Leas_Liudmila.junit.actions.BaseActionsTest;
import helpers.Helpers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CheckPetStatus extends BaseActionsTest {

    @Test
    @DisplayName("When a pet is added it's status is AVAILABLE")
    public void availableStatus() {
        int petsToAdopt = 2;
        String requiredStatus = "AVAILABLE";

        myActions.addPetWithRandomName(petsToAdopt);
        Helpers.waitInSeconds(1);
        myActions.checkPetStatus(petsToAdopt, requiredStatus);
    }


    @Test
    @DisplayName("When a pet is adopted it's status is ONHOLD")
    public void testAdoptedPetStatus() {
        int petsToAdopt = 2;
        String requiredStatus = "ONHOLD";

        myActions.addPetWithRandomName(petsToAdopt);
        Helpers.waitInSeconds(1);
        myActions.adoptAllPet();
        myActions.checkPetStatus(petsToAdopt, requiredStatus);

    }
}
