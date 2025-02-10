package Lilia_Rosca.LR_StepsDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class LRStepdefinition {
    Map<String, Object> context = new HashMap<>(); //pentru a trasminte parametru de la o etoda la alta

    @Given("I have a string {string}")
    public void iHaveAString(String string) {
        context.put("my_string", string);
    }

    @When("I check if it contains {string}")
    public void iCheckIfItContains(String subString) {
        String myString = (String) context.get("my_string");
        boolean contains = myString.contains(subString);
        context.put("contains", contains);
    }

    @Then("It should return true")
    public void itShouldReturnTrue() {
        Boolean contains = (Boolean) context.get("contains");
        assertThat(contains, equalTo(true));
    }

    // reverse a string - 2 methods
    @When("I reverse the string")
    public void iReverseTheString() {
        String myString = (String) context.get("my_string");
        String reversedString = new StringBuilder(myString)
                .reverse()
                .toString();
        context.put("reversedString", String.valueOf(reversedString));
    }

    @Then("The reversed string should be {string}")
    public void theReversedStringShouldBe(String string) {
        String reversedString = (String) context.get("reversedString");
        assertThat(reversedString, equalTo(string));
    }

    // length of a string
    @When("I check the length of the string")
    public void iCheckTheLengthOfTheString() {
        String myString = (String) context.get("my_string");
        int len = myString.length(); // de modificat map
        context.put("length_of_string", len);
    }

    @Then("the length should be {int}")
    public void theLengthShouldBe(Integer num) {
        Integer actualLength = (Integer) context.get("length_of_string");
        assertThat(actualLength, equalTo(num));
    }

    @Given("I have the following paragraph:")
    public void i_have_the_following_paragraph(String docString) {
        context.put("paragrath", docString);
    }

    @When("I count the number of words")
    public void i_count_the_number_of_words() {
        String paragrath = (String) context.get("paragrath");
        int wordsCount = paragrath.split("(\\s|\\n)").length;
        context.put("words_count", wordsCount);
    }

    @Then("the word count should be {int}")
    public void the_word_count_should_be(Integer expectedWordsCount) {
        int actualWordsCount = (int) context.get("words_count");
        assertThat(actualWordsCount, equalTo(expectedWordsCount));
    }

    @Given("I have a list with elements")
    public void iHaveAListWithElements(List<String> string) {
        context.put("my_list", string);
    }

    /* in trei pasi
        @When("I check if list contains {string}")
        public void iCheckIfListContainsBanana(String fruit) {
            List<String> list = (List<String>) context.get("my_list");
            boolean contains = list.contains(fruit);
            context.put("contains", contains);
        }
        @Then("list should contain banana")
        public void listShouldContain() {
            Boolean contains = (Boolean) context.get("contains");
            assertThat(contains, equalTo(true));
        }*/
    @Then("list should contain {string}")
    public void listShouldContain(String fruit) {
        List<String> list = (List<String>) context.get("my_list");
        assertThat(list, hasItem(fruit));
    }

 // HW 17.01
 // EX 1 Given si Then este mai sus
    @When("I count the length of string")
    public void i_count_the_length_of_string() {
        String myString = (String) context.get("paragrath").toString();
        int len = myString.length();
        context.put("length_of_string", len);
    }
  // Ex 2
    @Given("I have the numbers {int} and {int}")
    public void i_have_the_numbers_and(Integer int1, Integer int2) {
        context.put("num_1", int1);
        context.put("num_2", int2);
    }
    @When("I subtract the second number from the first")
    public void i_subtract_the_second_number_from_the_first() {
        int firstNum = (int) context.get("num_1");
        int secondNum = (int) context.get("num_2");
        int result = firstNum - secondNum;
        context.put("myResult", result);
    }
    @Then("the result should be {int}")
    public void the_result_should_be(Integer int1) {
        int result = (int) context.get("myResult");
        assertThat(result, equalTo(int1));
    }
  // Ex 3
    @Given("I have a number {int}")
    public void i_have_a_number(Integer int1) {
        context.put("num_1", int1);
    }
    @Then("the number should be even")
    public void the_number_should_be_even() {
        int num =(int) context.get("num_1");
        int evenNum = num % 2;
        assertThat(evenNum, equalTo(0));
        // Denis - assertThat(evenNum % 2, equalTo(true));
        }
 // Ex 4 Given and Then sunt mai sus
     @When("I add the numbers")
     public void i_add_the_numbers() {
         int firstNum = (int) context.get("num_1");
         int secondNum = (int) context.get("num_2");
         int result = firstNum + secondNum;
         context.put("myResult", result);
     }
 // Ex 5 Given este mai sus
     @When("I check the size of the list")
     public void i_check_the_size_of_the_list() {
         List<String> list = (List<String>) context.get("my_list");
         int lengthList = list.size();
         context.put("myLength", lengthList);
     }
    @Then("the size of the list should be {int}")
    public void the_size_of_the_list_should_be(Integer int1) {
        int length = (int) context.get("myLength");
         assertThat(length, equalTo(int1));
    }
 // Ex 6
     @Given("I have a map with the following key-value pairs:")
     public void i_have_a_map_with_the_following_key_value_pairs(Map<String, String> map) {
        context.put("my_map", map);
     }
    @When("I retrieve the value for the key {string}")
    public void i_retrieve_the_value_for_the_key(String key) {
        Map<String, String> myMap = (Map<String, String>) context.get("my_map");
        String value = myMap.get(key);
        context.put("value", value);
    }
    @Then("the value should be {string}")
    public void the_value_should_be(String expectedValue) {
        String actualValue = (String) context.get("value");
        assertThat(actualValue, equalTo(expectedValue));
    }

}
