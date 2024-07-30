package denis_grimaliuc.junit;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Execution(CONCURRENT)
public class ParametrizedJunitTestExample {

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
        Assertions.assertEquals(expected, actual);
    }


    @BeforeEach
    public void beforeEach() {
        System.out.println(Thread.currentThread().getId());
    }

    @ParameterizedTest
    @MethodSource("testData")
    @DisplayName("Test test")
    void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input) {
        Assertions.assertFalse(input.isBlank(), "Assert \"" + input + "\" is not Blank");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE})
    void isGreaterThan0(Integer input) {
        Assertions.assertTrue(input > 0, "Assert \"" + input + "\" is > 0");
    }

    @ParameterizedTest
    @NullSource
    void isNotNull(Integer input) {
        Assertions.assertNull(input, "Assert \"" + input + "\" is > 0");
    }

    @ParameterizedTest(name = "{index} {0} == {1} ?")
    @CsvSource(value = {"test,TEST", "test,test", "tEst,TEST", "TEST,TEST", "Java,JAVA"})
    void isEqualIgnoreCase(String str1, String str2) {
        Assertions.assertEquals(str1, str2, "Assert \"" + str1 + "\" equals \"" + str2 + "\"");
    }

}
