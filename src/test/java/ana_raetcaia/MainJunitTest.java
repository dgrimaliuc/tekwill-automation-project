package ana_raetcaia;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.LinkedList;
import java.util.List;


public class MainJunitTest {

    //    1. Scenario: String length validation
//    Given I have a string "Java Help"
//    When I check the length of the string
//    Then the length should be "9"
    @Test
    @DisplayName("String length")
    public void stringLength() {
        String str = "Java Help";
        Assertions.assertEquals(9, str.length(), "The lenght of string shoul be 9");
    }

    //    2. Scenario: String contains validation
//    Given I have a string "Java Help"
//    Then the string should contain "HE"
    @Test
    @DisplayName("String contains validation")
    public void stringContains() {
        String str = "Java Help";
        Assertions.assertTrue(str.contains("He"), "String contains 'He'");
    }
//        3. Scenario: Even number validation
//        Given I have a number "10"
//        Then the number should be even
    @Test
    @DisplayName("Even number validation")
    public void evenValidation () {
        int a = 10;
        Assertions.assertEquals(0, a % 2, "Number 10 is even ");
    }
//4. Scenario: Odd number validation
//    Given I have a number "7"
//    Then the number should be odd
    @Test
    @DisplayName("Odd number validation")
    public void numberValidation () {
        int x = 7;
        Assertions.assertEquals(1, x % 2, "Number 7 is odd ");
    }
//5. Scenario: List size validation
//    Given I have a list with elements "Apple", "Banana", "Orange"
//    When I check the size of the list
//    Then the size of the list should be "3"
    @Test
    @DisplayName("List size validation")
    public void listvalidation() {
        List <String> fruits = new LinkedList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        Assertions.assertEquals(3, fruits.size(), "The size of thr list is '3'");
        }
    }




