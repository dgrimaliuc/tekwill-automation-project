package IonErm.junit.shopify;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static IonErm.utility.Shopify_Actions_EG.convertPrice;
import static org.hamcrest.MatcherAssert.assertThat;

public class Shopify_SortingTests extends ShopifyBaseTest {

    @Test
    @DisplayName("Ascending sorting test")
    public void ascendingTest() {
        List<Integer> prices = new ArrayList<>();
        for (var card : page.cards) {
            int price = convertPrice(card.cardPrice);
            prices.add(price);
        }
        Collections.sort(prices);
        page.dropdownButton.click();
        page.ascendingOrder.click();
        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < prices.size(); i++) {
            int price = prices.get(i);
            int cardPrice = convertPrice(page.cards.get(i).cardPrice);
            log.info("Price: " + price + " Card price: " + cardPrice);
            softAssertions.assertThat(price).isEqualTo(cardPrice);
        }
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("Descending sorting test")
    public void descendingTest() {
        List<Integer> prices = new ArrayList<>();
        for (var card : page.cards) {
            int price = convertPrice(card.cardPrice);
            prices.add(price);
        }
        Collections.sort(prices);
        Collections.reverse(prices);
        page.dropdownButton.click();
        page.descendingOrder.click();
        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < prices.size(); i++) {
            int price = prices.get(i);
            int cardPrice = convertPrice(page.cards.get(i).cardPrice);
            log.info("Price: " + price + " Card price: " + cardPrice);
            softAssertions.assertThat(price).isEqualTo(cardPrice);
        }
        softAssertions.assertAll();
    }

    @ParameterizedTest(name = "Sorting test {0}")
    @ValueSource(strings = {"Ascending", "Descending"})
    public void sortingTest(String order) {
        List<Integer> prices = new ArrayList<>();
        for (var card : page.cards) {
            int price = convertPrice(card.cardPrice);
            prices.add(price);
        }
        Collections.sort(prices);
        if (order.equals("Descending")) {
            Collections.reverse(prices);
        }
        page.dropdownButton.click();
        if (order.equals("Ascending")) {
            page.ascendingOrder.click();
        } else {
            page.descendingOrder.click();
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < prices.size(); i++) {
            int price = prices.get(i);
            int cardPrice = convertPrice(page.cards.get(i).cardPrice);
            log.info("Price: " + price + " Card price: " + cardPrice);
            softAssertions.assertThat(price).isEqualTo(cardPrice);
        }
        softAssertions.assertAll();
    }
}
