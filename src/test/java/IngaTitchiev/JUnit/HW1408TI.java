package IngaTitchiev.JUnit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HW1408TI {
    @Test
    public void testSum() {
        int sum = 2 + 3;
        assertEquals(5, sum, "2+ 3 should equal 5");
    }
}
