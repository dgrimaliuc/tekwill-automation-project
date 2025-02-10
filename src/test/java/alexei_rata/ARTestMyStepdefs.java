package alexei_rata;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.Matchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class ARTestMyStepdefs {

    @Given("I defined a list")
    public void iDefinedAList(List<String> list) {
        System.out.println(list);
    }

    @Given("I defined a multiline string")
    public void iDefinedAMultilineString(String multistring) {
        System.out.println(multistring);
    }

    @Given("I defined a map")
    public void iDefinedAMap(Map<String, String> map) {
        System.out.println(map);
    }

    @Given("I defined a list of map")
    public void iDefinedAListOfMap(List<Map<String, String>> lmap) {
        System.out.println(lmap);
    }


    Map<String, Object> context = new HashMap<>();

    @Given("I Have a string {string}")
    public void iHaveAString(String arg0) {
        context.put("my_string", arg0);
    }

    @When("I check if it contains {string}")
    public void iCheckIfItContains(String subarg0) {
        String myString = (String) context.get("my_string");
        boolean contains = myString.contains(subarg0);
        context.put("contains", String.valueOf(contains));
    }

    @Then("It should return true")
    public void itShouldReturnTrue() {
        String contains = (String) context.get("contains");
        assertThat(contains, equalTo("true"));
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
    public void i_check_the_length_of_the_string() {
        String myStringLen = (String) context.get("my_string");

        int lengthString = myStringLen.length();
        context.put("length_of_String", lengthString);
    }

    @Then("the length must to be {int}")
    public void the_length_must_to_be(Integer int1) {
        Integer actualLength = (Integer) context.get("length_of_String");
        assertThat(actualLength, equalTo(int1));
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

    @When("I count the length of string")
    public void i_count_the_length_of_string() {
        String paragraph = (String) context.get("paragraph");
        int stringLength = paragraph.length();
        context.put("string_length", stringLength);
    }

    @Then("The word count should be {int}")
    public void the_word_count_should_be(Integer expectedCount) {
        Integer actualLength = (Integer) context.get("words_count");
        assertThat(actualLength, equalTo(expectedCount));
    }


    @Then("the length should be {int}")
    public void the_length_should_be(Integer excpectedLength) {
        Integer actualStringLength = (Integer) context.get("string_length");
        assertThat(actualStringLength, equalTo(excpectedLength));
    }


    @Given("I have a list with elements")
    public void i_have_a_list_with_elements(List<String> string) {
        context.put("fruits_list", string);
    }

    @When("I check if list contains {string}")
    public void i_check_if_list_contains(String fruit) {
        List<String> list = (java.util.List<String>) context.get("fruits_list");
        boolean contains = list.contains(fruit);
        context.put("contains", contains);
    }

    @Then("The list should contain banana")
    public void the_list_should_contain() {
        Boolean contains = (Boolean) context.get("contains");
        assertThat(contains, equalTo(true));
    }

    @Then("The list should contain {string}")
    public void theListShouldContain(String expectedFruit) {
        List<String> list = (java.util.List<String>) context.get("fruits_list");
        assertThat(list, hasItem(expectedFruit));
    }

    @When("I check the size of the list")
    public void i_check_the_size_of_the_list() {
        List<String> fruit = (List<String>) context.get("fruits_list");
        int sizeOfList = fruit.size();
        context.put("sizeOfList", sizeOfList);
    }

    @Then("the size of the list should be {int}")
    public void the_size_of_the_list_should_be(Integer size) {
        Integer actualResult = (Integer) context.get("sizeOfList");
        assertThat(actualResult, Matchers.equalTo(size));
    }


    @Given("I have the numbers {int} and {int}")
    public void i_have_the_numbers_and(Integer intNr1, Integer intNr2) {
        context.put("first_number", intNr1);
        context.put("second_number", intNr2);
    }

    @When("I subtract the second number from the first")
    public void i_subtract_the_second_number_from_the_first() {
        int firstNumber = (int) context.get("first_number");
        int secondNumber = (int) context.get("second_number");
        int result = firstNumber - secondNumber;
        context.put("resultToShow", result);
    }

    @When("I add the numbers")
    public void i_add_the_numbers() {
        int firstNumber = (int) context.get("first_number");
        int secondNumber = (int) context.get("second_number");
        int result = firstNumber + secondNumber;
        context.put("resultToShow", result);
    }

    @Then("the result should be {int}")
    public void the_result_should_be(Integer excpectedResult) {
        Integer actualResultValue = (Integer) context.get("resultToShow");
        assertThat(actualResultValue, equalTo(excpectedResult));
    }

    @Given("I have a number “{int}”")
    public void i_have_a_number(Integer int1) {
        context.put("my_number", int1);
    }

    @Then("the number should be even")
    public void the_number_should_be_even() {
        Integer resultValue = (Integer) context.get("my_number");
        assertThat(resultValue % 2 == 0, equalTo(true));
    }

    @Given("I have a map with the following key-value pairs:")
    public void i_have_a_map_with_the_following_key_value_pairs(Map<String, String> map) {
        context.put("my_map", map);
    }

    @When("I retrieve the value for the key {string}")
    public void i_retrieve_the_value_for_the_key(String key) {
        Map<String, String> myMap = (Map<String, String>) context.get("my_map");
        String value = myMap.get(key);

        context.put("valueToDislay", value);
    }

    @Then("the value should be {string}")
    public void the_value_should_be(String expectedValue) {
        String actualValue = (String) context.get("valueToDislay");
        assertThat(actualValue, equalTo(expectedValue));
    }

}
