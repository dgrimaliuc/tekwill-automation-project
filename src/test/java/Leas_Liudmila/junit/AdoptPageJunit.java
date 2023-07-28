package Leas_Liudmila.junit;

import Leas_Liudmila.junit.actions.BaseActionsTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/* 1. 1 symbol pet name test
2. Space containing pet name test
3. Punctuation symbols containing pet name test
Also create a separate test
 4. duplicate name of pet (a pet with already existing name should be added without problems ) */
public class AdoptPageJunit extends BaseActionsTest {


    @Test
    @DisplayName("1 symbol pet name test")
    public void oneSymbolName() {
        String newPetName = RandomStringUtils.random(1, true, true);
        myActions.addPetWithName(newPetName);
        myActions.addedPetCheck(newPetName);
    }

    @Test
    @DisplayName("Space containing pet name test")
    public void nameWithSpace() {
        String newPetName = RandomStringUtils.random(5, true, true);
        myActions.addPetWithName(newPetName + " " + newPetName);
        myActions.addedPetCheck(newPetName + " " + newPetName);
    }

    @Test
    @DisplayName("Punctuation symbols containing pet name test")
    public void nameWithSpecSymbols() {
        String characters = "~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        String newPetName = RandomStringUtils.random(15, characters);
        myActions.addPetWithName(newPetName);
        myActions.addedPetCheck(newPetName);
    }

    @Test
    @DisplayName("duplicate name of pet")
    public void duplicatedName() {
        String newPetName = "Charlie";
        myActions.addPetWithName(newPetName);
        myActions.addPetWithName(newPetName);
        myActions.duplicatedNameCheck(newPetName);
    }

}
