package IngaTitchiev.JUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Execution(CONCURRENT)
public class ParametrizedJUnitTest {

    @ParameterizedTest(name = "Test {0} is odd")
    @ValueSource(ints = {1, 3, 5, 6, -3, 15, Integer.MAX_VALUE})
    public void testisOdd(Integer number) {
        System.out.println("Testing number is odd " + number);
        assertThat(number % 2 != 0, equalTo(true));

    }

    @ParameterizedTest(name = "Test {0} is positive")
    @ValueSource(ints = {-10, 0, 5, 7, 100})
    public void testisPositive(Integer number) {

        Assertions.assertTrue(number > 0, "Testing number is positive" + number);
    }

    @ParameterizedTest
    @CsvSource({"test, TEST", "tEst,TEST", "Java, JAVa"})
    public void testToUpperCase(String actual, String expected) {
        Assertions.assertEquals(actual.toUpperCase(), expected);
    }

    @ParameterizedTest
    @DisplayName("Test if the given string pairs  are correctly conncatenated")
    @CsvSource({"Hello, word, Helloword", "for,best, forbest", "Java, script, Javascript"})
    public void testConcatString(String str1, String str2, String str3) {
        Assertions.assertEquals(str3, str1 + str2);
    }

    private boolean isWinterMonth(Month month) {
        return month == Month.DECEMBER || month == Month.JANUARY || month == Month.FEBRUARY;
    }

    @ParameterizedTest
    @DisplayName("Test if the given monhths are in the winter season")
    @EnumSource(value = Month.class, names = {"DECEMBER", "JANUARY", "FEBRUARY"})
    public void testWinterMonth(Month month) {
        Assertions.assertTrue(isWinterMonth(month));
    }
}
