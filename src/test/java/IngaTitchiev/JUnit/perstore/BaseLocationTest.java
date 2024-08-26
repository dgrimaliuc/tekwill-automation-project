package IngaTitchiev.JUnit.perstore;

import IngaTitchiev.JUnit.perstore.pages.PetStore;
import internal.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;

public class BaseLocationTest extends BaseTest {

    PetStore petStore = new PetStore(driver);

    @BeforeEach
    public void setUp() {
        String randomLocation = RandomStringUtils.randomAlphanumeric(17).toUpperCase();
        super.setUp();
        petStore.openPage();
    }
}
