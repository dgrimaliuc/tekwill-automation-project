package Leas_Liudmila.junit;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Execution(CONCURRENT)
public class FirstJUnitTask {


    @Test
    @DisplayName("The first test")
    public void stringLengthValidation() {
        String myText = "Java Help";
        Assertions.assertEquals(9, myText.length(), "Assert that the length of the string \"" + myText + "\" is 9");
    }

    @Test
    @DisplayName("The second test")
    void stringContainsValidation() {
        String myText = "Java Help";
        Assertions.assertTrue(myText.contains("He"), "Assert that the string \"" + myText + "\" contains \"He\"");
    }

    @Test
    @DisplayName("The third test")
    public void isEvenNumber() {
        int myNumber = 10;
        Assertions.assertEquals(0, myNumber % 2, "Assert \"" + myNumber + "\" is even");
    }

    @Test
    @DisplayName("The fourth test")
    public void isOddNumber() {
        int myNumber = 7;
        Assertions.assertEquals(1, myNumber % 2, "Assert \"" + myNumber + "\" is odd.");
    }


    @Test
    @DisplayName("The fifth test")
    void lisSizeValidation() {
        List<String> fruits = new LinkedList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        Assertions.assertEquals(3, fruits.size(), "Assert that the length of the array \"" + fruits + "\" is 3");
    }

}
