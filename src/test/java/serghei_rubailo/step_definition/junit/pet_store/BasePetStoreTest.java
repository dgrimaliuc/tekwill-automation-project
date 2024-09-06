package serghei_rubailo.step_definition.junit.pet_store;

import internal.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import serghei_rubailo.ui.pet_store.pages.PetStore;

public class BasePetStoreTest extends BaseTest {

    PetStore petStore = new PetStore(driver);

    @BeforeEach
    public void setUp() {
        String randomLocation = RandomStringUtils.randomAlphanumeric(17).toUpperCase();
        super.setUp();
        petStore.openPage(randomLocation);
    }

}
