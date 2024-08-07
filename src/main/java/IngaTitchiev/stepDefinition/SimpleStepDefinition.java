package IngaTitchiev.stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class SimpleStepDef {
    private String javaString;
    private int actualLength;
    private int number1;
    private int number2;
    private Integer result;
    private String paragraph;
    private int length;
    private List<String> list;
    private Map<String, String> map;
    private String retrievedValue;
    private Integer totalPrice;
    private List<Integer> list1;
    private LocalDate startDate;
    private LocalDate endDate;
    private Period period;

    @Given("I have a string {string}")
    public void i_have_a_string(String string) {
        javaString=string;
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }
    @Then("I should see {string}")
    public void i_should_see(String string) {

    }

    @Then("the length should be {string}")
    public void theLengthShouldBe(String str1) {
        assertEquals(actualLength, Integer.parseInt(str1));
    }

    @When("I check the length of the string")
    public void i_check_the_length_of_the_string() {
        actualLength=javaString.length();
    }
    @Given("I have the numbers {int} and {int}")
    public void i_have_the_numbers_and(Integer int1, Integer int2) {
        number1=int1;
        number2=int2;
        //throw new io.cucumber.java.PendingException();
    }
    @When("I add the numbers")
    public void i_add_the_numbers() {
        result=number1+number2;
        //throw new io.cucumber.java.PendingException();
    }
    @Then("the result should be {int}")
    public void the_result_should_be(Integer int1) {
        assertEquals(result, int1);
        //throw new io.cucumber.java.PendingException();
    }
    @Given("I have the following paragraph:")
    public void i_have_the_following_paragraph(String docString) {
        paragraph = docString;
    }

    @Then("the length should be {int}")
    public void theLengthShouldBe(int expectedLength) {
        assertEquals(expectedLength, length);
    }

    @When("I count the length of string")
    public void iCountTheLengthOfString() {
        length = paragraph.length();
    }

    @Given("I have a list with elements")
    public void iHaveAListWithElements(List<String> dataTable) {
        list = new ArrayList<>(dataTable);

    }

    @Given("I have a map with the following key-value pairs:")
    public void i_have_a_map_with_the_following_key_value_pairs(List<Map<String, String>> keyValuePairs) {
        map = new HashMap<>();
        for (Map<String, String> pair : keyValuePairs) {
            map.put(pair.get("key"), pair.get("value"));
        }
    }
    @When("I check the size of the list")
    public void iCheckTheSizeOfTheList() {
    }

    @Then("the size of the list should be {int}")
    public void theSizeOfTheListShouldBe(int expectedSize) {
        assertEquals(expectedSize, list.size());
    }

    @When("I retrieve the value for the key email")
    public void iRetrieveTheValueForTheKeyEmail(String key) {
        retrievedValue = map.get(key);}

    @Then("the value should be john@example.com")
    public void theValueShouldBeJohnExampleCom(String expectedValue) {
        assertEquals(expectedValue, retrievedValue);
    }

    @Given("I have the following products:")
    public void i_have_the_following_products(List<Map<String, Integer>> products) {
        totalPrice = 0;
        for (Map<String, Integer> product : products) {
            totalPrice += product.get("Price");
        }
    }
    @When("I calculate the total price")
    public void i_calculate_the_total_price() {
    }
    @Then("the total price should be {int}")
    public void the_total_price_should_be(Integer expectedTotalPrice) {
        assertEquals(expectedTotalPrice, totalPrice);
    }

    @Given("I have a comma-separated string “1,2,3,4,5”")
    public void iHaveACommaSeparatedString(String commaSeparatedString) {
        // Convert the comma-separated string to a list of integers
        String[] stringArray = commaSeparatedString.split(",");
        list1 = new ArrayList<>();
        for (String str : stringArray) {
            list1.add(Integer.parseInt(str));
        }
    }

    @When("I split and convert the string to a list of integers")
    public void iSplitAndConvertTheStringToAListOfIntegers() {
    }

    @Then("the list should be:")
    public void theListShouldBe(List<Integer> expectedList) {
        assertEquals(expectedList, list1);
    }

    @Given("I have the date {string}")
    public void i_have_the_date(String startDateString) {
        startDate = LocalDate.parse(startDateString);
    }

    @Given("I have the date {string}")
    public void i_have_the_date_2(String endDateString) {
        endDate = LocalDate.parse(endDateString);
    }

    @When("I calculate the difference in days")
    public void i_calculate_the_difference_in_days() {
        period = Period.between(startDate, endDate);
    }

    @Then("the difference should be {int} year {int} month and {int} days")
    public void the_difference_should_be(int expectedYears, int expectedMonths, int expectedDays) {
        assertEquals(expectedYears, period.getYears());
        assertEquals(expectedMonths, period.getMonths());
        assertEquals(expectedDays, period.getDays());
    }
}

