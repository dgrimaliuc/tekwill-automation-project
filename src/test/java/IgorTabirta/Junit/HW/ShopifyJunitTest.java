package IgorTabirta.Junit.HW;

import IgorTabirta.UI.ShopifyV2;
import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ShopifyJunitTest extends BaseTest {

    ShopifyV2 shopifyV2 = new ShopifyV2(driver);

    @BeforeEach
    public void OpenShopify() {
        shopifyV2.open();
    }


    @Test
    public void firstTest() {


        /*shopifyV2.sizeFilterS.click();
        shopifyV2.priceFilterUnder$25.click();
        shopifyV2.sizeFilterSection.sizeXL.click();*/

        driver.get("https://shopify-eta-drab.vercel.app/");

        /*shopifyV2.PriceFilterSection.under25.click();
        shopifyV2.PriceFilterSection.over100.click();

        shopifyV2.SizeFilterSection.sizeS.click();
        shopifyV2.SizeFilterSection.sizeXL.click();*/


        /*shopifyV2.cards.get(0).addToCart.click();
        shopifyV2.cards.get(1).addToCart.click();
        shopifyV2.cards.get(2).addToCart.click();
        shopifyV2.cards.get(20).addToCart.click();*/

        /*shopifyV2.genderFilters.female.click();
        shopifyV2.genderFilters.male.click();*/

        /*shopifyV2.ColorFilterSection.Black.click();
        shopifyV2.ColorFilterSection.Yellow.click();
        shopifyV2.ColorFilterSection.Purple.click();*/

        shopifyV2.addToCart.addToCart.click();
        shopifyV2.addToCart.addToCart.click();
        shopifyV2.addToCart.addToCart.click();
        System.out.println();


    }


}
