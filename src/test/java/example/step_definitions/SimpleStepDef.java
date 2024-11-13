package example.step_definitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleStepDef {

    HashMap<String, Object> stepResults = new HashMap<>();


    @Before
    public void before() {
        stepResults = new HashMap<>();
    }

    @Given("I have a string {string}")
    public void i_have_a_string(String string) {
        stepResults.put("my_string", string);
    }

    @Then("It starts with {string}")
    public void it_starts_with(String string) {
        String myString = (String) stepResults.get("my_string");
        assertThat(myString, startsWith(string));
    }

    @Then("I should see {string}")
    public void i_should_see(String string) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("Test")
    public void test() {

    }

    @Then("Test2")
    public void test2() {
        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
    }

    @Then("I should see")
    public void iShouldsSee() {

    }

    @Then("I send List")
    public void iSendList(List<Integer> list) {
//        System.out.println(list);

    }

    @Then("I send Map")
    public void iSendMap(Map<String, String> map) {
        System.out.println(map);
        System.out.println(map.get("key2"));
    }

    @Then("I send Data Table")
    public void iSendDataTable(List<Map<String, String>> dataTable) {
        System.out.println(dataTable);
    }

    @Then("The string should contain {string}")
    public void theStringShouldContain(String subString) {
        String myString = (String) stepResults.get("my_string");
        assertThat(myString, containsString(subString));
    }

    @When("I reverse the string")
    public void iReverseTheString() {
        String myString = (String) stepResults.get("my_string");
        var reversedString = new StringBuilder(myString).reverse().toString();
        stepResults.put("reversed_string", reversedString);

    }

    @Then("the reversed string should be {string}")
    public void theReversedStringShouldBe(String expectedString) {
        String actual = stepResults.get("reversed_string").toString();
        assertThat(actual, equalTo(expectedString));
    }

    @Given("I have the numbers {int} and {int}")
    public void iHaveTheNumbersAnd(int a, int b) {
        stepResults.put("number1", a);
        stepResults.put("number2", b);
    }

    @When("I subtract the second number from the first")
    public void iSubtractTheSecondNumberFromTheFirst() {
        Integer number1 = (Integer) stepResults.get("number1");
        Integer number2 = (Integer) stepResults.get("number2");
        Integer result = number1 - number2;
        stepResults.put("result", result);
    }

    @Then("The result should be {int}")
    public void theResultShouldBe(int expectedResult) {
        Integer actualResult = (Integer) stepResults.get("result");
        assertThat(actualResult, equalTo(expectedResult));
    }

    @Given("I have a number {int}")
    public void iHaveANumber(Integer num) {
        stepResults.put("number", num);
    }

    @Then("The number should be even")
    public void theNumberShouldBeEven() {
        Integer number = (Integer) stepResults.get("number");
        Integer result = number % 2;
        assertThat(result, equalTo(0));
    }

    @Given("I have the following paragraph:")
    public void iHaveTheFollowingParagraph(String paragraph) {
        stepResults.put("paragraph", paragraph);
    }

    @When("I count the number of words")
    public void iCountTheNumberOfWords() {
        var paragraph = stepResults.get("paragraph").toString();
        var words = paragraph.split("\\s+").length;
        stepResults.put("words_length", words);
    }

    @Then("the word count should be {int}")
    public void theWordCountShouldBe(int expected) {
        Integer actual = (Integer) stepResults.get("words_length");
        assertThat(actual, equalTo(expected));
    }

    @Given("I have the following list of integers:")
    public void iHaveTheFollowingListOfIntegers(List<Integer> numbers) {
        stepResults.put("numbers", numbers);
    }

    @When("I sort the list")
    public void iSortTheList() {
        ArrayList<Integer> list = new ArrayList<>((List) stepResults.get("numbers"));
        Collections.sort(list);
        stepResults.put("sorted_numbers", list);
    }

    @Then("the sorted list should be:")
    public void theSortedListShouldBe(List<Integer> expectedList) {
        ArrayList<Integer> actualList = new ArrayList<>((List) stepResults.get("sorted_numbers"));
        assertThat(actualList, equalTo(expectedList));
    }

    @Given("I have a map with the following key-value pairs:")
    public void iHaveAMapWithTheFollowingKeyValuePairs(Map<String, String> map) {
        stepResults.put("my_map", map);
    }

    @When("I retrieve the value for the key {string}")
    public void iRetrieveTheValueForTheKey(String key) {
        Map<String, String> myMap = (Map<String, String>) stepResults.get("my_map");
        String value = myMap.get(key);
        stepResults.put("my_value", value);
    }

    @Then("the value should be {string}")
    public void theValueShouldBe(String expected) {
        String actual = (String) stepResults.get("my_value");
        assertThat(actual, equalTo(expected));
    }

    @Given("I have the following products:")
    public void iHaveTheFollowingProducts(List<Map<String, String>> products) {
        stepResults.put("products", products);
    }

    @When("I calculate the total price")
    public void iCalculateTheTotalPrice() {
        List<Map<String, String>> products = (List<Map<String, String>>) stepResults.get("products");
        int total = 0;
        for (Map<String, String> product : products) {
            Integer price = Integer.parseInt(product.get("Price"));
            total += price;
        }
        stepResults.put("total_price", total);

    }

    @Then("the total price should be {int}")
    public void theTotalPriceShouldBe(int expectedPrice) {
        Integer actualPrice = (Integer) stepResults.get("total_price");
        assertThat(actualPrice, equalTo(expectedPrice));
    }


    @Given("I have two dates {string} and {string}")
    public void iHaveTwoDatesAnd(String date1, String date2) {
        stepResults.put("date1", date1);
        stepResults.put("date2", date2);
    }

    @When("I calculate the difference in days")
    public void iCalculateTheDifferenceInDays() {
        String date1 = (String) stepResults.get("date1");
        String date2 = (String) stepResults.get("date2");

        LocalDate lDate1 = LocalDate.parse(date1);
        LocalDate lDate2 = LocalDate.parse(date2);

        var period = Period.between(lDate1, lDate2);
        stepResults.put("days_difference", period.getDays());
    }

    @Then("the difference should be {int} days")
    public void theDifferenceShouldBeDays(int expectedDays) {
        Integer actualDays = (Integer) stepResults.get("days_difference");
        assertThat(actualDays, equalTo(expectedDays));
    }
}
