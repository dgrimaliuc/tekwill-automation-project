package chiril_bortnicov.junit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.testng.AssertJUnit.*;

public class TestExample {

    @Test
    @Tag("fast")
    @DisplayName("Sout test 1")
    public void test() {
        System.out.println("Test");
    }

    @Test
    @Tags({@Tag("fast"), @Tag("model")})
    @DisplayName("Sout test 2")
    public void test2() {
        System.out.println("Test");
    }

    @BeforeAll
    static void initAll() {
        System.out.println("Before all test methods");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("After all test methods");
    }

    @BeforeEach
    void init() {
        System.out.println("Before each test method");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each test method");
    }

    private static Stream<Arguments> numbers() {
        return Stream.of(
                Arguments.of(1 + 2, 3),
                Arguments.of(3 + 4, 7),
                Arguments.of(5 + 6, 11)
        );
    }

    private static List<String> testDataList() {
        List<String> list = new LinkedList<>();
      /*  list.add("Apple");
        list.add("Banana");
        list.add("Orange");
        list.add("Pineapple"); */
        return Arrays.asList("Apple", "Banana", "Orange", "Pineapple");
    }

    @ParameterizedTest(name = "{0} == {1} ?")
    @MethodSource("numbers")
    @DisplayName("My First Parameterized Test")
    public void firstTest(Integer actual, Integer expected) {
        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "input={0}")
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE})
    void isGreaterThan0(Integer input) {
        Assertions.assertTrue(input > 0, "Assert \"" + input + "\" is > 0");
    }


    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World", "Java", "JUnit", ""})
    void isNotBlank(String input) {
        Assertions.assertFalse(input.isBlank(), "Assert \"" + input + "\" is not Blank");
    }

    @ParameterizedTest(name = "{index} {0} == {1} ?")
    @CsvSource(value = {"test,TEST", "test,test", "tEst,TEST", "TEST,TEST", "Java,JAVA"})
    void isEqualStringTest(String str1, String str2) {
        assertEquals("Assert \"" + str1 + "\" equals \"" + str2 + "\"", str1, str2);
    }

    @ParameterizedTest(name = "{index} {0} == {1} ?")
    @CsvFileSource(resources = "chiril_bortnicov/data.csv", numLinesToSkip = 1, delimiter = ',')
    void isEqualCSVFileSourceTest(String str1, String str2) {
        assertEquals("Assert \"" + str1 + "\" equals \"" + str2 + "\"", str1, str2);
    }

    @ParameterizedTest
    @MethodSource("testDataList")
    @DisplayName("Is Blank Test")
    void isBlankTest(String input) {
        Assertions.assertFalse(input.isBlank(), "Assert \"" + input + "\" is not Blank");
    }


}



