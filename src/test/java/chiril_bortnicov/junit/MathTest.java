package chiril_bortnicov.junit;

import org.junit.jupiter.api.Test;


public class MathTest {
    @Test
    public void Test(){
        int sum = 2 + 3;
        int actual = 5;
        boolean result = (sum == actual);
        System.out.println(result);
    }

}
