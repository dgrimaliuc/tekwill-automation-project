package serghei_rubailo.step_definition.junit.pet_store;

import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import serghei_rubailo.ui.pet_store.pages.PetStore;

public class BaseLocationTest extends BaseTest {

    PetStore petStore = new PetStore(driver);

    @BeforeEach
    public void setUp() {
        petStore.openPage();
    }

}
