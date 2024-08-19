package serghei_rubailo.step_definition.junit;

import internal.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import serghei_rubailo.ui.shopify.pages.ShopifyV2;
import serghei_rubailo.ui.shopify.pages.components.Card;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class ShopifyJunitTest extends BaseTest {

    ShopifyV2 shopifyV2 = new ShopifyV2(driver);

    @BeforeEach
    public void openShopify() {
        shopifyV2.open();
    }

    @Test
    public void priceUnder25Test() {
        shopifyV2.pricesFilterSection.under25.click();

        for (Card card : shopifyV2.cards) {
            String price = card.price.getText().substring(1);
            Integer actual = Integer.parseInt(price);
            assertThat(actual, lessThanOrEqualTo(25));
        }
    }
    
}
