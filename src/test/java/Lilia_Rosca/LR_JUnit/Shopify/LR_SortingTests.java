package Lilia_Rosca.LR_JUnit.Shopify;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Lilia_Rosca.actions.LR_ShopifyActions.convertPrice;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

public class LR_SortingTests extends LR_ShopifyBaseTests{
//05.02
    @Test
    @DisplayName("Ascending Sorting Test")
    public void ascendingSortingTest() {
        List<Integer> prices = new ArrayList<>();
        for (var card : page.cards) {
            int price = convertPrice(card.price);
            prices.add(price);
        }
        Collections.sort(prices);
//        System.out.println(prices);
        page.dropdownButton.click();
        page.ascendingOrder.click();

        for (int i = 0; i < prices.size(); i++) {
           int price = prices.get(i);
           int cardPrice = convertPrice(page.cards.get(i).price);
           log.info("Price: " + price + " Card price: " + cardPrice);
           assertThat(cardPrice, equalTo(price));
        }
    }
    // var 2 - softAssertion
    @Test
    @DisplayName("Ascending Sorting with softAssertion Test")
    public void ascendingSoftAssertionTest() {
        List<Integer> prices = new ArrayList<>();
        for (var card : page.cards) {
            int price = convertPrice(card.price);
            prices.add(price);
        }
        Collections.sort(prices); // fara aceasta da eroare AssertionFailedError
        page.dropdownButton.click();
        page.ascendingOrder.click();
        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < prices.size(); i++) {
            int price = prices.get(i);
            int cardPrice = convertPrice(page.cards.get(i).price);
            log.info("Price: " + price + " Card price: " + cardPrice);
            softAssertions.assertThat(cardPrice).isEqualTo(price);
        }
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("Descending Sorting Test")
    public void descendingSortingTest() {
        List<Integer> prices = new ArrayList<>();
        for (var card : page.cards) {
            int price = convertPrice(card.price);
            prices.add(price);
        }
        Collections.sort(prices);
        Collections.reverse(prices);
        page.dropdownButton.click();
        page.descendingOrder.click();
        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < prices.size(); i++) {
            int price = prices.get(i);
            int cardPrice = convertPrice(page.cards.get(i).price);
            log.info("Price: " + price + " Card price: " + cardPrice);
            softAssertions.assertThat(cardPrice).isEqualTo(price);
        }
        softAssertions.assertAll();
    }

    @ParameterizedTest(name = "Sorting test {0}")
    @ValueSource(strings = {"ascending", "descending"})
    public void sortingTest(String order) {
        List<Integer> prices = new ArrayList<>();
        for (var card : page.cards) {
            int price = convertPrice(card.price);
            prices.add(price);
        }
        Collections.sort(prices);

        if(order.equals("descending")) {
            Collections.reverse(prices);
        }
        page.dropdownButton.click();

        if(order.equals("ascending")) {
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
