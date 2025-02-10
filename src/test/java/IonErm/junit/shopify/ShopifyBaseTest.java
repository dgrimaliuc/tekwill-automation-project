package IonErm.junit.shopify;

import IonErm.poms.shopify.IonErm_ShopifyPage;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;

public class ShopifyBaseTest extends BaseTest {

    IonErm_ShopifyPage page = new IonErm_ShopifyPage(driver);

    @BeforeEach
    public void openPage() {
        driver.get("https://shopify-eta-drab.vercel.app/");
        actions.shouldBeDisplayed(page.priceSection);
    }
}
