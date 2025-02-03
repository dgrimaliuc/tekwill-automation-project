package denis_grimaliuc.junit;

import denis_grimaliuc.poms.ShopifyPage;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;

public class ShopifyBaseTest extends BaseTest {
    ShopifyPage page = new ShopifyPage(driver);

    @BeforeEach
    public void openPage() {
        driver.get("https://shopify-eta-drab.vercel.app/");
        actions.shouldBeDisplayed(page.priceSection);
    }
}
