package Leas_Liudmila.junit;

import Leas_Liudmila.junit.actions.BaseActionsTest;
import helpers.Helpers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AdoptionInTest extends BaseActionsTest {

    @Test
    @DisplayName("Verify that Adoption In sections contain Title, status, petNames, approve and deny buttons")
    public void checkElementsArePresent() {
        int petsToAdopt = 3;

        myActions.addPetWithRandomName(petsToAdopt);
        Helpers.waitInSeconds(1);
        myActions.adoptAllPet();
        myActions.checkElementsInAdoptions();

    }

}
