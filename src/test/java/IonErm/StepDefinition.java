package IonErm;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StepDefinition {

    Map<String, Object> context = new HashMap<>();

    @Given("I have a string {string}")
    public void iHaveAString(String string) {
        context.put("myString", string);
    }

    @When("I check if it contains {string}")
    public void iCheckIfItContains(String subString) {
        String myString = (String) context.get("myString");
        boolean contains = myString.contains(subString);
        context.put("contains", contains);
    }

    @Then("I should return true")
    public void iShouldReturnTrue() {
        Boolean contains = (Boolean) context.get("contains");
        assertThat(contains, equalTo(true));
    }

    @When("I reverse the string")
    public void i_reverse_the_string() {
        String myString = (String) context.get("myString");
        String reversedString = new StringBuilder(myString).reverse().toString();
        context.put("reversed_string", reversedString);
    }

    @Then("The reversed string should be {string}")
    public void the_reversed_string_should_be(String string) {
        String reversedString = (String) context.get("reversed_string");
        assertThat(reversedString, equalTo(string));
    }

    @When("I check the length of the string")
    public void i_check_the_length_of_the_string() {
        String myString = (String) context.get("myString");
        int stringLength = myString.length();
        context.put("stringLength", stringLength);
    }

    @Then("the length should be {int}")
    public void the_length_should_be(Integer expectedLength) {
        Integer actualLength = (Integer) context.get("stringLength");
        assertThat(actualLength, equalTo(expectedLength));
    }

    @Given("I have the following paragraph:")
    public void i_have_the_following_paragraph(String docString) {
        context.put("docString", docString);
    }

    @When("I count the length of string")
    public void i_count_the_length_of_string() {
        String docString = (String) context.get("docString");
        int stringLength = docString.length();
        context.put("stringLength", stringLength);
    }

    @When("I count the number of words")
    public void i_count_the_number_of_words() {
        String string = (String) context.get("docString");
        int wordsCount = string.split(("\\s|\\n")).length;
        context.put("wordsCount", wordsCount);
    }

    @Then("the word count should be {int}")
    public void the_word_count_should_be(Integer int1) {
        Integer actualLength = (Integer) context.get("wordsCount");
        assertThat(actualLength, equalTo(int1));
    }

    @Given("I have the numbers {int} and {int}")
    public void i_have_the_numbers_and(Integer int1, Integer int2) {
        context.put("int1", int1);
        context.put("int2", int2);
    }

    @When("I subtract the second number from the first")
    public void i_subtract_the_second_number_from_the_first() {
        Integer int1 = (int) context.get("int1");
        Integer int2 = (int) context.get("int2");
        Integer result = int1 - int2;
        context.put("result", result);
    }

    @Then("the result should be {int}")
    public void the_result_should_be(Integer result) {
        Integer actualResult = (Integer) context.get("result");
        assertThat(actualResult, equalTo(result));
    }

    @Given("I have a number '{int}'")
    public void i_have_a_number(Integer number) {
        context.put("number", number);
    }

    @Then("the number should be even")
    public void the_number_should_be_even() {
        Integer checkEvenNumber = (Integer) context.get("number");
        if (checkEvenNumber % 2 == 0) {
            System.out.println(checkEvenNumber + " is even.");
        } else {
            System.out.println(checkEvenNumber + " is odd.");
        }
    }

    @When("I add the numbers")
    public void i_add_the_numbers() {
        Integer int1 = (int) context.get("int1");
        Integer int2 = (int) context.get("int2");
        Integer result = int1 + int2;
        context.put("result", result);
    }

    @Given("I have a list with elements")
    public void i_have_a_list_with_elements(List<String> string) {
        context.put("list", string);
    }

    @When("I check the size of the list")
    public void i_check_the_size_of_the_list() {
        List<String> fruit = (List<String>) context.get("list");
        int sizeOfList = fruit.size();
        context.put("sizeOfList", sizeOfList);
    }

    @Then("the size of the list should be {int}")
    public void the_size_of_the_list_should_be(Integer size) {
        Integer actualResult = (Integer) context.get("sizeOfList");
        assertThat(actualResult, equalTo(size));
    }

    @Given("I have a map with the following key-value pairs:")
    public void i_have_a_map_with_the_following_key_value_pairs(Map<String, String> string) {
        context.put("map", string);
    }

    @When("I retrieve the value for the key {string}")
    public void i_retrieve_the_value_for_the_key_email(String key) {
        Map<String, String> map = (Map<String, String>) context.get("map");
        String retrievedValue = map.get(key);
        context.put("retrievedValue", retrievedValue);

    }

    @Then("the value should be {string}")
    public void the_value_should_be_john_example_com(String expectedValue) {
        String actualResult = (String) context.get("retrievedValue");
        assertThat(actualResult, equalTo(expectedValue));
        System.out.println("The retrieved value is: " + actualResult);
    }
}
