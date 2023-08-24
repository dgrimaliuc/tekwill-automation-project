package alexei_drujinin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.LinkedList;
import java.util.List;

public class TestsJUnit {


        @Test
        @DisplayName("String length")
        public void stringLength() {
            String str = "Java Help";
            Assertions.assertEquals(9, str.length(), "The lenght of string shoul be 9");
        }

        @Test
        @DisplayName("String contains validation")
        public void stringContains() {
            String str = "Java Help";
            Assertions.assertTrue(str.contains("He"), "String contains 'He'");
        }

        @Test
        @DisplayName("Even number validation")
        public void evenValidation () {
            int a = 10;
            Assertions.assertEquals(0, a % 2, "Number 10 is even ");
        }

        @Test
        @DisplayName("Odd number validation")
        public void numberValidation () {
            int x = 7;
            Assertions.assertEquals(1, x % 2, "Number 7 is odd ");
        }

        @Test
        @DisplayName("List size validation")
        public void listvalidation() {
            List <String> fruits = new LinkedList<>();
            fruits.add("Apple");
            fruits.add("Banana");
            fruits.add("Orange");
            Assertions.assertEquals(3, fruits.size(), "The size of thr list is '3'");
        }
    }


