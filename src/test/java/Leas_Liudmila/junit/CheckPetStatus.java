package Leas_Liudmila.junit;

import Leas_Liudmila.junit.actions.BaseActionsTest;
import helpers.Helpers;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CheckPetStatus extends BaseActionsTest {

    @Test
    @DisplayName("When a pet is added it's status is AVAILABLE")
    public void availableStatus() {
        ArrayList<String> generatedPetNames = new ArrayList<>();
        int petsToAdopt = 2;
        String requiredStatus = "AVAILABLE";

        for (int i = 0; i < petsToAdopt; i++) {
            String newPetName = RandomStringUtils.random(10, true, true);
            generatedPetNames.add(newPetName);
            myActions.addPetWithName(newPetName);
        }

        Helpers.waitInSeconds(1);
        myActions.checkPetStatus(petsToAdopt, requiredStatus);
    }


    @Test
    @DisplayName("When a pet is adopted it's status is ONHOLD")
    public void testAdoptedPetStatus() {
        ArrayList<String> generatedPetNames = new ArrayList<>();
        int petsToAdopt = 2;
        String requiredStatus = "ONHOLD";

        for (int i = 0; i < petsToAdopt; i++) {
            String newPetName = RandomStringUtils.random(10, true, true);
            generatedPetNames.add(newPetName);
            myActions.addPetWithName(newPetName);
        }

        Helpers.waitInSeconds(1);
        myActions.adoptAllPet();
        myActions.checkPetStatus(petsToAdopt, requiredStatus);

    }
}
