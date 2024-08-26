package chiril_bortnicov.junit.petstore;

import chiril_bortnicov.ui.petstore.pages.PetStore;
import internal.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;

public class BasePetStoreTest extends BaseTest {

    PetStore petStore = new PetStore(driver);

    @BeforeEach
    public void setUp() {
        String randomLocation = RandomStringUtils.randomAlphanumeric(17).toUpperCase();
        super.setUp();
        petStore.openPage(randomLocation);
    }

}
