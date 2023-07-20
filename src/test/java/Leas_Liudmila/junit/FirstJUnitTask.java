package Leas_Liudmila.junit;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Execution(CONCURRENT)
public class FirstJUnitTask {

    private static Stream<Arguments> myText() {
        return Stream.of(
                Arguments.of("Java Help")
        );
    }


    @ParameterizedTest
    @MethodSource("myText")
    @DisplayName("The first test")
    void stringLengthValidation(String text) {
        Assertions.assertEquals(9, text.length(), "Assert that the length of the string \"" + text + "\" is 9");
    }

    @ParameterizedTest
    @MethodSource("myText")
    @DisplayName("The second test")
    void stringContainsValidation(String text) {
        Assertions.assertTrue(text.contains("He"), "Assert that the string \"" + text + "\" contains \"He\"");
    }

    @ParameterizedTest
    @DisplayName("The third test")
    @ValueSource(ints = 10)
    void isEvenNumber(int myNumber) {
        Assertions.assertEquals(0, myNumber % 2, "Assert \"" + myNumber + "\" is even");
    }

    @ParameterizedTest
    @DisplayName("The fourth test")
    @ValueSource(ints = 7)
    void isOddNumber(int myNumber) {
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
