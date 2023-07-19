package cristina_savrin.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

public class JUnitFirstTest {

    @Test
    @DisplayName("Check string length")
    public void checkLength() {
        Assertions.assertEquals(9, "Java Help".length(), "Assertion: \"Java Help\" string length equals to 9");
    }

    @Test
    @DisplayName("Find substring")
    public void findSubstring() {
        Assertions.assertTrue("Java Help".contains("He"), "Assertion: \"Java Help\" contains substring 'He'");
    }

    @Test
    @DisplayName("Check even number")
    public void checkEvenNum() {
        Assertions.assertEquals(0, 10 % 2, "Assertion: 10 is even number");
    }

    @Test
    @DisplayName("Check odd number")
    public void checkOddNum() {
        Assertions.assertEquals(1, 7 % 2, "Assertion: 7 is odd number");
    }

    @Test
    @DisplayName("Check list size")
    public void checkListSize() {
        List<String> list = new LinkedList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Orange");
        Assertions.assertEquals(3, list.size(), "Assertion: list size equals to 3");
    }
}