package chiril_bortnicov.junit;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Month;

import static org.junit.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class ParametrizedJunitTests {

    @ParameterizedTest(name = "Test is {0} add")
    @ValueSource(ints = {1, 3, 5, 4, -3, 15, Integer.MAX_VALUE})
    public void testIsOdd(Integer number) {
        assertTrue("Testing Number is Odd: " + number, number % 2 != 0);
    }

    @ParameterizedTest(name = "Test is {0} even")
    @DisplayName("Verify is the given numbers are positive")
    @ValueSource(ints = {-10, 0, 5, 7, 100})
    public void testIsPositive(int number) {
        assertTrue("Testing Number is Positive: " + number, number > 0);
    }

    @ParameterizedTest
    @DisplayName("Ensure that provided strings are correctly converted to uppercase.")
    @CsvSource({"test,TEST", "tEst,TEST", "Java,JAVa"})
    public void testToUpperCase(String actual, String expected) {
        assertEquals(actual.toUpperCase(), expected);
    }

    @ParameterizedTest
    @DisplayName("Test if the given string pairs are correctly concatenated")
    @CsvSource({"hello,world,helloworld", "foo,bar,foobar", "java,script,javascript", "java,java,javascript"})
    public void testConcatString(String str1, String str2, String expected) {
        Assert.assertEquals(expected, str1 + str2);
    }

    private boolean isWinterMonth(Month month) {
        return month == Month.DECEMBER || month == Month.JANUARY || month == Month.FEBRUARY;
    }

    @ParameterizedTest
    @DisplayName("Verify if the provided months are in the winter season.")
    @EnumSource(value = Month.class, names = {"DECEMBER", "JANUARY", "FEBRUARY", "MARCH"})
    public void testWinterMonths(Month month) {
        assertTrue(isWinterMonth(month));
    }
}