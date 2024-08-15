package victor_murashev.junit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleMathTest {

    @ParameterizedTest
    @CsvSource({
            "2, 3, 5",
            "6, 3, 9",
            "10, -5, 6"
    })
    public void testAddition(int a, int b, int expected) {
        int result = a + b;
        assertEquals(expected, result, a + " + " + b + " should equal " + expected);
    }
}
