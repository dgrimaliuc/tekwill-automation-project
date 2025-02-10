package Lilia_Rosca.LR_JUnit.Shopify;

import Lilia_Rosca.poms.LR_shopifyPage;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;

public class LR_ShopifyBaseTests extends BaseTest {
// 31.01
    LR_shopifyPage page = new LR_shopifyPage(driver);// va fi unic pentru toate testele pentru Shopify

    @BeforeEach
    public void openPage() {
        driver.get("https://shopify-eta-drab.vercel.app/#");
    }
}