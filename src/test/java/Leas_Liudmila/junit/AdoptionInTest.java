package Leas_Liudmila.junit;

import Leas_Liudmila.junit.actions.BaseActionsTest;
import helpers.Helpers;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AdoptionInTest extends BaseActionsTest {

    @Test
    @DisplayName("Verify that Adoption In sections contain Title, status, petNames, approve and deny buttons")
    public void checkElementsArePresent(){
        ArrayList<String> generatedPetNames = new ArrayList<>();
        int petsToAdopt = 3;
        for (int i = 0; i < petsToAdopt; i++) {
            String newPetName = RandomStringUtils.random(10, true, true);
            generatedPetNames.add(newPetName);
            myActions.addPetWithName(newPetName);
        }
        Helpers.waitInSeconds(1);
        myActions.adoptAllPet();
        myActions.checkElementsInAdoptions(generatedPetNames);

    }

}
