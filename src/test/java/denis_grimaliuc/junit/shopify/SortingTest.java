package denis_grimaliuc.junit.shopify;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static denis_grimaliuc.actions.ShopifyActions.convertPrice;

public class SortingTest extends ShopifyBaseTest {


    @ParameterizedTest(name = "Sorting test {0}")
    @ValueSource(strings = {"ascending", "descending"})
    public void sortingTest(String order) {
        List<Integer> prices = new ArrayList<>();
        for (var card : page.cards) {
            int price = convertPrice(card.price);
            prices.add(price);
        }
        Collections.sort(prices);
        if (order.equals("descending")) {
            Collections.reverse(prices);
        }

        page.dropdownButton.click();

        if (order.equals("ascending")) {
            page.ascendingOrder.click();
        } else {
            page.descendingOrder.click();
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < prices.size(); i++) {
            int price = prices.get(i);
            int cardPrice = convertPrice(page.cards.get(i).price);
            log.info("Price: " + price + " Card price: " + cardPrice);
            softAssertions.assertThat(cardPrice).isEqualTo(price);
        }
        softAssertions.assertAll();
    }
}
