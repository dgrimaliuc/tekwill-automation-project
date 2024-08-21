package IgorTabirta.Junit.Shopify.Shopify;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumTest {
    @Test
    public void testAddition() {
        int result = 2 + 3;
        assertEquals(5, result, "2 + 3 should equal 5");
    }
}
