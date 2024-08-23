package denis_grimaliuc.junit;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Month;

import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Execution(CONCURRENT)
public class ParametrizedJunitTests {

    @BeforeEach
    public void setUp() {
        System.out.println(Thread.currentThread().getId());
    }

    @ParameterizedTest(name = "Test is {0} odd")
    @ValueSource(ints = {1, 3, 5, 4, -3, 15, Integer.MAX_VALUE})
    public void testIsOdd(Integer number) {
        Assertions.assertTrue(number % 2 != 0, "Testing Number is Odd: " + number);
    }

    @ParameterizedTest(name = "Test is {0} even")
    @DisplayName("Verify if the given numbers are positive")
    @ValueSource(ints = {-10, 0, 5, 7, 100})
    public void testIsPositive(int number) {
        Assertions.assertTrue(number > 0, "Testing Number is Positive: " + number);
    }

    @ParameterizedTest
    @DisplayName("Ensure that the provided strings are correctly converted to uppercase.")
    @CsvSource({"test,TEST", "tEst,TEST", "Java,JAVa"})
    public void testToUpperCase(String actual, String expected) {
        Assertions.assertEquals(actual.toUpperCase(), expected);
    }

    @ParameterizedTest
    @DisplayName("Test if the given string pairs are correctly concatenated")
    @CsvSource({"hello,world,helloworld", "foo,bar,foobar", "java,script,javascript", "java,java,javascript"})
    public void testConcatStrings(String srt1, String str2, String expected) {
        Assertions.assertEquals(expected, srt1 + str2);
    }


    private boolean isWinterMonth(Month month) {
        return month == Month.DECEMBER || month == Month.JANUARY || month == Month.FEBRUARY;
    }

    @ParameterizedTest
    @DisplayName("Verify if the provided months are in the winter season.")
    @EnumSource(value = Month.class, names = {"DECEMBER", "JANUARY", "FEBRUARY", "MARCH"})
    public void testWinterMonths(Month month) {
        Assertions.assertTrue(isWinterMonth(month));
    }

}
