package denis_grimaliuc.junit;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

@Execution(CONCURRENT)
public class ParametrizedJunitExampleTests {

    private static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of("Hello"),
                Arguments.of("World"),
                Arguments.of("")
        );
    }

    private static List<String> testDataList() {
        List<String> list = new LinkedList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Orange");
        list.add("Pineapple");
        //   return Arrays.asList("Apple", "Banana", "Orange", "Pineapple");
        return list;
    }

    private static Stream<Arguments> numbers() {
        return Stream.of(
                Arguments.of(1 + 2, 3),
                Arguments.of(3 + 4, 7),
                Arguments.of(5 + 6, 11)
        );
    }


    @ParameterizedTest
    @MethodSource("numbers")
    @DisplayName("My First Test")
    public void firstTest(Integer actual, Integer expected) {
        assertEquals(expected, actual);
    }


    @BeforeEach
    public void beforeEach() {
        System.out.println(Thread.currentThread().getId());
    }

    @ParameterizedTest
    @MethodSource("testData")
    @DisplayName("Is Blank Test")
    void isBlankTest(String input) {
        assertFalse(input.isBlank(), "Assert \"" + input + "\" is not Blank");
    }

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Argument Provider Is Blank Test")
    void argumentProviderTest(String input) {
        assertFalse(input.isBlank(), "Assert \"" + input + "\" is not Blank");
    }

    @ParameterizedTest
    @EnumSource(Month.class) // names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER" .... }
    @DisplayName("Month test")
    void assertMonth(Month input) {
        assertFalse(input.toString().isBlank(), "Assert \"" + input + "\" is not Blank");
    }


    @ParameterizedTest(name = "input={0}")
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE})
    void isGreaterThan0(Integer input) {
        assertTrue(input > 0, "Assert \"" + input + "\" is > 0");
    }


    @ParameterizedTest
    @NullSource
    @DisplayName("Null test")
    void isNotNull(Integer input) {
        assertNull(input, "Assert \"" + input + "\" is > 0");
    }

    @ParameterizedTest(name = "{index} {0} == {1} ?")
    @CsvSource(value = {"test,TEST", "test,test", "tEst,TEST", "TEST,TEST", "Java,JAVA"})
    void isEqualStringTest(String str1, String str2) {
        assertEquals("Assert \"" + str1 + "\" equals \"" + str2 + "\"", str1, str2);
    }

    @ParameterizedTest(name = "{index} {0} == {1} ?")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1, delimiter = ',')
    void isEqualCSVFileSourceTest(String str1, String str2) {
        assertEquals("Assert \"" + str1 + "\" equals \"" + str2 + "\"", str1, str2);
    }

}
