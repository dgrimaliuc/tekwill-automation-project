package PotoracMihai.sptepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class SimpleStepDefinition {

    HashMap <String,Object> stepResult = new HashMap <>();

    @Given("I have a string {string}")
    public void i_have_a_string(String string) {
        stepResult.put("my_string",string);
    }

    @Then("I should see {string}")
    public void i_should_see(String string) {
    }


    @Then("the string should contain {string}")
    public void theStringShouldContain(String subString) {
        String myString = (String) stepResult.get("my_string");
        assertThat(myString,containsString(subString));
    }

    @When("I reverse the string")
    public void iReverseTheString() {
        String myString = (String) stepResult.get("my_string");
        var reversString = new StringBuilder(myString).reverse().toString();
        stepResult.put("revers_string",reversString);
    }

    @Then("the reversed string should be {string}")
    public void theReversedStringShouldBe(String expectedString) {
        String actual = stepResult.get("revers_string").toString();
        assertThat(actual,equalTo(expectedString));
    }

    @When("I check the length of the string")
    public void iCheckTheLengthOfTheString() {
        String myString = (String) stepResult.get("my_string");
        var length = myString.length();
        stepResult.put("string_length",length);
    }

    @Then("the length should be {int}")
    public void theLengthShouldBe(int numberExpected) {
        int actual = (int) stepResult.get("string_length");
        assertThat(actual,equalTo(numberExpected));
    }

    @Given("I have the numbers {int} and {int}")
    public void iHaveTheNumbersAnd(int a, int b) {
        stepResult.put("number1",a);
        stepResult.put("number2",b);
    }

    @When("I add the numbers")
    public void iAddTheNumbers() {
        var number1 = (int) stepResult.get("number1");
        var number2 = (int) stepResult.get("number2");
        var sum = number1 + number2;
        stepResult.put("sum",sum);
    }

    @Then("the result should be {int}")
    public void theResultShouldBe(int resultExpected) {
        int actual = (int) stepResult.get("sum");
        assertThat(actual,equalTo(resultExpected));
    }

    @Given("I have the following paragraph:")
    public void iHaveTheFollowingParagraph(String paragraph) {
        stepResult.put("paragraph",paragraph);

    }

    @When("I count the length of string")
    public void iCountTheLengthOfString() {
        var paragraph = (String) stepResult.get("paragraph");
        var length = paragraph.length();
        stepResult.put("string_length",length);

    }

    @Given("I have a list with elements")
    public void iHaveAListWithElements(List<String> words) {
        stepResult.put("list",words);
    }

    @When("I check the size of the list")
    public void iCheckTheSizeOfTheList() {
        var list = (List<String>) stepResult.get("list");
        var size = list.size();
        stepResult.put("list_size",size);
    }

    @Then("the size of the list should be {int}")
    public void theSizeOfTheListShouldBe(int expectedSize) {
        int actual = (int) stepResult.get("list_size");
        assertThat(actual,equalTo(expectedSize));
    }

    @Given("I have a map with the following key-value pairs:")
    public void iHaveAMapWithTheFollowingKeyValuePairs(Map<String,String> map) {
        stepResult.put("my_map",map);
    }

    @When("I retrieve the value for the key {string}")
    public void iRetrieveTheValueForTheKey(String key) {
        Map<String, String> myMap = (Map<String, String>) stepResult.get("my_map");
        String value = myMap.get(key);
        stepResult.put("my_value", value);
    }

    @Then("the value should be {string}")
    public void theValueShouldBe(String expectedValue) {
        String actual = (String) stepResult.get("my_value");
        assertThat(actual, equalTo(expectedValue));
    }

    @Given("I have the following products:")
    public void iHaveTheFollowingProducts(List<Map<String, String>> products) {
        stepResult.put("products", products);
    }

    @When("I calculate the total price")
    public void iCalculateTheTotalPrice() {
        List<Map<String, String>> products = (List<Map<String, String>>) stepResult.get("products");
        int totalPrice = 0;
        for(Map<String, String> product : products){
            Integer price = Integer.parseInt(product.get("Price"));
            totalPrice += price;
        }
        stepResult.put("total_price", totalPrice);
    }

    @Then("the total price should be {int}")
    public void theTotalPriceShouldBe(int totalPriceExpected) {
        Integer actual = (Integer) stepResult.get("total_price");
        assertThat(actual, equalTo(totalPriceExpected));
    }


    @Given("I have a comma-separated string {string}")
    public void iHaveACommaSeparatedString(String commaSeparatedString) {
        stepResult.put("comma_separated_string", commaSeparatedString);
    }

    @When("I split and convert the string to a list of integers")
    public void iSplitAndConvertTheStringToAListOfIntegers() {
        String commaSeparatedString = (String) stepResult.get("comma_separated_string");
        String[] split = commaSeparatedString.split(",");
        List<Integer> integers = new java.util.ArrayList<>();
        for(String s : split){
            integers.add(Integer.parseInt(s));
        }
        stepResult.put("integers", integers);

    }

    @Then("the list should be:")
    public void theListShouldBe(List<Integer> expectedList) {
        List<Integer> actual = (List<Integer>) stepResult.get("integers");
        assertThat(actual, equalTo(expectedList));
    }


    @Given("I have the two dates {string} and {string}")
    public void iHaveTheTwoDatesAnd(String date1, String date2) {
        stepResult.put("date1", date1);
        stepResult.put("date2", date2);
    }

    @When("I calculate the difference in days")
    public void iCalculateTheDifferenceInDays() {
        String date1 = (String) stepResult.get("date1");
        String date2 = (String) stepResult.get("date2");
        LocalDate lDate1 = LocalDate.parse(date1);
        LocalDate lDate2 = LocalDate.parse(date2);
        var period = Period.between(lDate1, lDate2);
        stepResult.put("days_difference", period.getDays());
    }


    @Then("the difference should be {int} days")
    public void theDifferenceShouldBeDays(int daysExpected) {
        Integer actual = (Integer) stepResult.get("days_difference");
        assertThat(actual, equalTo(daysExpected));
    }
}

