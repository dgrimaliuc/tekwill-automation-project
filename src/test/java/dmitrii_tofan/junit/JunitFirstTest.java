package dmitrii_tofan.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

public class JunitFirstTest {

    @Test
    @DisplayName("first test")
    public void stringLengthValidation() {
        String JavaText = "Java Help";
        Assertions.assertEquals(9, JavaText.length(), "Assert that the length of the string " + JavaText + " is 9");
    }


    @Test
    @DisplayName("second test")
    public void stringContainsValidation() {
        String JavaText = "Java Help";
        Assertions.assertTrue(JavaText.contains("He"), "Assert that the string " + JavaText + " contains He");
    }

    @Test
    @DisplayName("number validation")
    public void evenNumberValidation() {
        int number = 10;
        Assertions.assertEquals(0, number % 2, "Assert" + number + "is even");
    }


    @Test
    @DisplayName("odd number validation")
    public void oddNumberValidation() {
        int number = 7;
        Assertions.assertEquals(1, number % 2, "Assert" + number + "is odd");
    }


    @Test
    @DisplayName("list size validation")
    public void listSizeValidation() {
        List<String> fruits = new LinkedList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        Assertions.assertEquals(3, fruits.size(), "Assert that the length of the list " + fruits + " is 3");

    }
}
