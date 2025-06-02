package example.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class TheFirstStepDefinishn {

    Map<String, Object> context = new HashMap<>();
    Logger log = Logger.getLogger(TheFirstStepDefinishn.class);

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

    @When("I reverse the string")
    public void i_reverse_the_string() {
        String myString = (String) context.get("my_string");
        String reversedString = new StringBuilder(myString)
                .reverse()
                .toString();

        context.put("reversed_string", reversedString);

    }

    @Then("the reversed string should be {string}")
    public void the_reversed_string_should_be(String string) {
        String reversedString = (String) context.get("reversed_string");
        assertThat(reversedString, equalTo(string));
    }


    @When("I check the length of the string")
    public void iCheckTheLengthOfTheString() {
        String myString = (String) context.get("my_string");

        int length = myString.length();
        context.put("length_of_string", length);
    }

    @Then("the length should be {int}")
    public void theLengthShouldBe(Integer num) {
        Integer actualLength = (Integer) context.get("length_of_string");
        assertThat(actualLength, equalTo(num));
    }


    @Given("I have the following paragraph:")
    public void i_have_the_following_paragraph(String docString) {
        context.put("paragraph", docString);
    }

    @When("I count the number of words")
    public void i_count_the_number_of_words() {
        String paragraph = (String) context.get("paragraph");
        int wordsCount = paragraph.split("(\\s|\\n)").length;
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

    @Then("list should contain {string}")
    public void listShouldContain(String fruit) {
        List<String> list = (List<String>) context.get("my_list");
        assertThat(list, hasItem(fruit));
    }

    @When("I count the length of string")
    public void iCountTheLengthOfString() {
        String myString = context.get("paragraph").toString();
        int length = myString.length();
        context.put("length_of_string", length);
    }


    @Given("I have the numbers {int} and {int}")
    public void i_have_the_numbers_and(Integer int1, Integer int2) {
        log.info("I have two numbers: " + int1 + " and " + int2);
        context.put("first_number", int1);
        context.put("second_number", int2);
    }

    @When("I subtract the second number from the first")
    public void i_subtract_the_second_number_from_the_first() {
        log.info("I subtract the second number from the first");
        int firstNumber = (int) context.get("first_number");
        int secondNumber = (int) context.get("second_number");
        int result = firstNumber - secondNumber;
        context.put("result", result);
    }

    @Then("the result should be {int}")
    public void the_result_should_be(Integer int1) {
        int result = (int) context.get("result");
        log.info("The result should be " + int1 + " and actual is " + result);
        assertThat(result, equalTo(int1));
    }

    @Given("I have a number {int}")
    public void i_have_a_number(Integer int1) {
        context.put("my_number", int1);
    }

    @Then("the number should be even")
    public void the_number_should_be_even() {
        int myNum = (int) context.get("my_number");
        assertThat(myNum % 2 == 0, equalTo(true));
    }

    @When("I add the numbers")
    public void i_add_the_numbers() {
        int fNum = (int) context.get("first_number");
        int sNum = (int) context.get("second_number");
        int sum = fNum + sNum;
        context.put("result", sum);
    }


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
