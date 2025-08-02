package denis_grimaliuc;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyStepDefinition {


    Map<String, Object> context = new HashMap<>();

    int a, b, result;

    String str;

    boolean isTrue;

    String aString, bString, resultString;


    @Given("I have two numbers {int} and {int}")
    public void iHaveTwoNumbersAnd(int arg0, int arg1) {

        context.put("num1", arg0);
        context.put("num2", arg1);

        System.out.println("I have two numbers " + arg0 + " and " + arg1);
    }


    @When("I add the numbers")
    public void iAddTheNumbers() {
        var num1 = (int) context.get("num1");
        int num2 = (int) context.get("num2");

        var result = num1 + num2;

        context.put("result", result);

        System.out.println("I add the numbers, result is " + result);
    }


    @When("I subtract the numbers")
    public void iSubtractTheNumbers() {
        result = a - b;
    }


    @Then("the result should be {int}")
    public void theResultShouldBe(int arg0) {
        var result = (int) context.get("result");
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(arg0));
    }

    @Given("I have two strings {string} and {string}")
    public void iHaveTwoStringsAnd(String arg0, String arg1) {
        aString = arg0;
        bString = arg1;

    }

    @When("I concatenate the strings")
    public void iConcatenateTheStrings() {
        resultString = aString + bString;
    }

    @Then("the result should be {string}")
    public void theResultShouldBe(String arg0) {
        MatcherAssert.assertThat(resultString, CoreMatchers.equalTo(arg0));
    }

    @Given("I have a string {string}")
    public void iHaveAString(String arg0) {
        context.put("my_string", arg0);
    }

    @When("I check the length of the string")
    public void iCheckTheLengthOfTheString() {
        result = str.length();
        System.out.println("The length of the string is " + result);
    }


    @Then("the string should contain {string}")
    public void theStringShouldContain(String subString) {
        var str = (String) context.get("my_string");
        MatcherAssert.assertThat(str, CoreMatchers.containsString(subString));
    }

    @Given("I have a number {int}")
    public void iHaveANumber(int arg0) {
        a = arg0;
        System.out.println("I have a number " + a);
    }

    @When("I check if the number is even")
    public void iCheckIfTheNumberIsEven() {
        isTrue = (a % 2 == 0);
    }

    @Then("the number should be even")
    public void theNumberShouldBeEven() {
        MatcherAssert.assertThat(isTrue, CoreMatchers.is(true));
        MatcherAssert.assertThat(isTrue, CoreMatchers.is(true));

    }

    @Given("I have a test environment set up")
    public void iHaveATestEnvironmentSetUp() {
        System.out.println("I have a test environment set up");
    }

    @Given("I have a multiline string")
    public void iHaveAMultilineString(String str) {
        System.out.println(str);

    }

    @Given("I have a list of numbers")
    public void iHaveAListOfNumbers(List<String> list) {
        System.out.println(list);
    }

    @Given("I have a map")
    public void iHaveAMap(List<Map<String, Object>> map) {
        System.out.println("Map contents:" + map);
    }

    @When("I reverse the string")
    public void iReverseTheString() {
        var string = (String) context.get("my_string");
        var result = new StringBuilder(string).reverse().toString();
        context.put("reversed_string", result);
    }

    @Then("the reversed string should be {string}")
    public void theReversedStringShouldBe(String arg0) {
        var reversedString = (String) context.get("reversed_string");
        MatcherAssert.assertThat(reversedString, CoreMatchers.equalTo(arg0));

        List<Integer> nums1 = List.of(1, 2, 3);
        List<Integer> nums2 = List.of(1, 2, 3);

        MatcherAssert.assertThat(nums1, CoreMatchers.equalTo(nums2));

    }

    @Given("I have a list with elements")
    public void iHaveAListWithElements(List<String> list) {
        System.out.println("List contents: " + list);
        context.put("my_list", list);
    }

    @When("I check the size of the list")
    public void iCheckTheSizeOfTheList() {

        List<String> list = (List<String>) context.get("my_list");

        var size = list.size();

        context.put("list_size", size);
    }

    @Then("the size of the list should be {int}")
    public void theSizeOfTheListShouldBe(int expectedSize) {
        var actualSize = (int) context.get("list_size");
        MatcherAssert.assertThat(actualSize, CoreMatchers.equalTo(expectedSize));

    }

    @Given("I have a map with the following key-value pairs:")
    public void iHaveAMapWithTheFollowingKeyValuePairs(Map<String, Object> map) {
        System.out.println("Map contents: " + map);
        context.put("my_map", map);
    }

    @When("I retrieve the value for the key {string}")
    public void iRetrieveTheValueForTheKey(String key) {
        Map<String, Object> map = (Map<String, Object>) context.get("my_map");
        var value = map.get(key);
        context.put("retrieved_value", value);


        System.out.println("Retrieved value for key '" + key + "': " + value);
    }

    @Then("the value should be {string}")
    public void theValueShouldBe(String expectedValue) {
        var actualValue = (String) context.get("retrieved_value");
        MatcherAssert.assertThat(actualValue, CoreMatchers.equalTo(expectedValue));
    }

    @Given("I have a comma-separated string {string}")
    public void iHaveACommaSeparatedString(String string) {

        context.put("comma_separated_string", string);
    }

    @When("I split and convert the string to a list of integers")
    public void iSplitAndConvertTheStringToAListOfIntegers() {
        String str = (String) context.get("comma_separated_string");
        String[] numbers = str.split(",");

        List<Integer> list = new ArrayList<>();

        for (String i : numbers) {
            int value = Integer.parseInt(i.trim());
            list.add(value);
        }

        context.put("integer_list", list);

    }

    @Then("the list should be:")
    public void theListShouldBe(List<Integer> expectedList) {
        List<Integer> actualList = (List<Integer>) context.get("integer_list");
        MatcherAssert.assertThat(actualList, CoreMatchers.equalTo(expectedList));
    }

}
