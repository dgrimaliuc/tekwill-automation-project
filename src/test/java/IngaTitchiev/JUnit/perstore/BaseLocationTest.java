package IngaTitchiev.JUnit.perstore;

import IngaTitchiev.JUnit.perstore.pages.PetStore;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;

public class BaseLocationTest extends BaseTest {

    PetStore petStore = new PetStore(driver);

    @BeforeEach
    public void setUp() {
        petStore.openPage();
    }
}
