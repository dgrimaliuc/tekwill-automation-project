package chiril_bortnicov.junit.petstore;

import chiril_bortnicov.ui.petstore.pages.PetStore;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;

public class BaseLocationTest extends BaseTest {

    PetStore petStore = new PetStore(driver);

    @BeforeEach
    public void setUp() {
        petStore.openPage();
    }
}
