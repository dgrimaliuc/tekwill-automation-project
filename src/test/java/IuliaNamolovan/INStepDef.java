package IuliaNamolovan;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class INStepDef {

    // test For list
    @Then("I have a list")
    public void i_have_a_list(List<Map<String, String>> map) {
        System.out.println(map);
    }

    //test for Hello World!
    Map<String, Object> context = new HashMap<>();

    @Given("I have a string {string}")
    public void i_have_a_string(String string) {
        context.put("my_string", string);
    }

    @When("I check if it contains {string}")
    public void i_check_if_it_contains(String subString) {

        String myString = (String) context.get("my_string");
        boolean contains = myString.contains(subString);
        context.put("contains", contains);
    }

    @Then("It should return true")
    public void it_should_return_true() {
        Boolean contains = (Boolean) context.get("contains");
        assertThat(contains, equalTo(true));
    }

    @When("I revers a string")
    public void i_revers_a_string() {
        String myString = (String) context.get("my_string");
        String reversedString = new StringBuilder(myString)
                .reverse()
                .toString();
        context.put("reversed_String", reversedString);
    }


    @Then("the reversed string should be {string}")
    public void the_reversed_string_should_be(String string) {
        String reversedString = (String) context.get("reversed_String");
        assertThat(reversedString, equalTo(string));
    }

    @When("I check the length of the string")
    public void i_check_the_length_of_the_string() {
        String myString = (String) context.get("my_string");
        int length = myString.length();
        context.put("lenght_of_string", length);
    }

    @Then("the length should be {int}")
    public void the_length_should_be(Integer num) {
        Integer actuallength = (Integer) context.get("lenght_of_string");
        //System.out.println(num);
        assertThat(actuallength,equalTo(num));
    }

    // next ex
   //
    @Given("I have the following paragraph:")
    public void i_have_the_following_paragraph(String docString) {
       context.put("paragraph", docString);
    }

    /*@Given("I have a number “{int}”")
   public void i_have_a_number(Integer int1) {
        }*/
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
    public void i_have_a_list_with_elements(List<String> string) {
        context.put("my_list", string);
    }

    @When("I check if list contains {string}")
    public void i_check_if_list_contains(String fruit) {
        List<String> list = (List<String>) context.get("my_list");
        boolean contains = list.contains(fruit);
        context.put("contains", contains);
    }

    @Then("List should contain banana")
    public void listShouldContainBanana() {
        Boolean contains = (Boolean) context.get("contains");
        assertThat(contains, equalTo(true));
    }

    // HomeWork



    @When("I count the length of string")
    public void i_count_the_length_of_string() {
        String myString= context.get("paragraph").toString();
        int length = myString.length();
        context.put("lenght_of_string", length);
        //String paragraph = (String) context.get("paragraph");
        //int wordsCount = paragraph.split("(\\s|\\n)").length;
        //context.put("words_count", wordsCount);

        //String paragraph = (String) context.get("paragraph");
       // int length = paragraph.length("(\\\\s|\\\\n)\");
       // context.put("length_of_paragraph", length);
    }


    @Given("I have the numbers {int} and {int}")
    public void i_have_the_numbers_and(Integer int1, Integer int2) {
        context.put("first_number", int1);
        context.put("second_number", int2);
    }

    @When("I subtract the second number from the first")
    public void i_subtract_the_second_number_from_the_first() {
        Integer firstNumber = (Integer) context.get("first_number");
        Integer secondNumber = (Integer) context.get("second_number");
        int result = firstNumber - secondNumber;
        context.put("result", result);
    }

    @Then("the result should be {int}")
    public void the_result_should_be(Integer expectedResult) {
        Integer actualResult = (Integer) context.get("result");
        assertThat(expectedResult, equalTo(actualResult));
    }

    //next

    @Given("I have a number {int}")
    public void i_have_a_number(Integer int3) {
       context.put("number", int3);
        }

    @Then("the number should be even")
    public void the_number_should_be_even() {
        int Mynumber = (int) context.get("number");
        assertThat(Mynumber % 2 == 0, equalTo(true));
    }

    @When("I add the numbers")
    public void i_add_the_numbers() {
        int firstNumber = (int) context.get("first_number");
        int secondNumber = (int) context.get("second_number");
        int result = firstNumber + secondNumber;
        context.put("result", result);    }

    // 5
    @When("I check the size of the list")
    public void i_check_the_size_of_the_list() {
        List<String> myList = (List<String>) context.get("my_list");
        int actualSize = myList.size();
        context.put("list_size", actualSize);
        context.put("myList", myList);
    }

    @Then("the size of the list should be {int}")
    public void the_size_of_the_list_should_be(Integer expectedSize) {
        List<String> myList = (List<String>) context.get("my_list");
        assertThat(expectedSize, equalTo(myList.size()));
    }

    // 6
    @Given("I have a map with the following key-value pairs:")
    public void i_have_a_map_with_the_following_key_value_pairs(Map<String, String> map) {
        context.put("my_map", map);

    }

    @When("I retrieve the value for the key {string}")
    public void i_retrieve_the_value_for_the_key_email( String key) {
        Map<String, String> my_Map = (Map<String, String> ) context.get("my_map");
        String value = my_Map.get(key);
        context.put("value", value);
        }

    @Then("the value should be {string}")
    public void the_value_should_be_john_example_com(String expectedValue) {
        String actualValue = (String) context.get("value");
        assertThat( actualValue, equalTo(expectedValue));
    }
}











