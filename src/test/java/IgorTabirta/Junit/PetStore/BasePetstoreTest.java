package IgorTabirta.Junit.PetStore;

import IgorTabirta.UI.PetStore.Page.PetStore;
import internal.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;

public class BasePetstoreTest extends BaseTest {

    PetStore petStore = new PetStore(driver);

    @BeforeEach
    public void setUp() {
        String randomLocation = RandomStringUtils.randomAlphabetic(17).toUpperCase();
        super.setUp();
        petStore.openPage(randomLocation);
    }
}
