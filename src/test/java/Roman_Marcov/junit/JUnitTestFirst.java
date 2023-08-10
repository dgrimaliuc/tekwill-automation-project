package Roman_Marcov.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

public class JUnitTestFirst  {

    /**
     * Scenario: String length validation
     * Given I have a string "Java Help"
     * When I check the length of the string
     * Then the length should be "9"
     */
    @Test
    @DisplayName("String length validation")
    public void lengthValidation() {
        Assertions.assertEquals(9, "Java Help".length(), "Assertion: 'Java Help' string length equals to 9");
    }

    /**
     * Scenario: String contains validation
     * Given I have a string "Java Help"
     * Then the string should contain "He"
     */
    @Test
    @DisplayName("String contains validation")
    public void containsValidation() {
        Assertions.assertTrue("Java Help".contains("He"), "Assertion: 'Java Help' contains substring 'He'");
    }

    /**
     * Scenario: Even number validation
     * Given I have a number "10"
     * Then the number should be even
     */
    @Test
    @DisplayName("Even number validation")
    public void checkEvenNumber() {
        Assertions.assertEquals(0, 10 % 2, "Assertion: 10 is even number");
    }

    /**
     * Scenario: Odd number validation
     * Given I have a number "7"
     * Then the number should be odd
     */
    @Test
    @DisplayName("Odd number validation")
    public void checkOddNum() {
        Assertions.assertEquals(1, 7 % 2, "Assertion: 7 is odd number");
    }

    /**
     * Scenario: List size validation
     * Given I have a list with elements "Apple", "Banana", "Orange"
     * When I check the size of the list
     * Then the size of the list should be "3"
     **/

    @Test
    @DisplayName("List size validation")
    public void checkListSize() {
        List<String> list = new LinkedList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Orange");
        Assertions.assertEquals(3, list.size(), "Assertion: list size equals to 3");
    }
}
