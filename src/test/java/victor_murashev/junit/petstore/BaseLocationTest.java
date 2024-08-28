package victor_murashev.junit.petstore;

import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import victor_murashev.ui.shopify.pages.PetStore;

public class BaseLocationTest extends BaseTest {

    PetStore petStore = new PetStore(driver);

    @BeforeEach
    public void openPage() {
        petStore.openPage();
    }

}
