package IgorTabirta.Junit.HW;

import IgorTabirta.UI.ShopifyV2;
import internal.BaseTest;
import org.junit.Before;
import org.junit.jupiter.api.Test;


public class ShopifyJunitTest extends BaseTest {
    
    ShopifyV2 shopifyV2 = new ShopifyV2(driver);

    @Before
    public void OpenShopify() {
        shopifyV2.open();
    }

    @Test
    public void firstTest() {

        shopifyV2.sizeFilterS.click();
        shopifyV2.priceFilterUnder$25.click();
        shopifyV2.sizeFilterSection.sizeXL.click();

    }


}
