package serghei_rubailo.step_definition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import io.cucumber.java.sl.In;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class SRSimpleStepDefinition {

    private HashMap<String, Object> stepResults = new HashMap<>();

    @Given("I have a string {string}")
    public void i_have_a_string(String string) {
        stepResults.put("string", string);
    }

    @When("I check the length of the string")
    public void iCheckTheLengthOfTheString() {
        String myString = (String) stepResults.get("string");
        stepResults.put("length", myString.length());
    }

    @Then("the length should be {int}")
    public void theLengthShouldBe(Integer expectedLength) {
        Integer actualLength = (Integer) stepResults.get("length");
        assertThat(expectedLength, equalTo(actualLength));
    }

    @Given("I have the numbers {int} and {int}")
    public void iHaveTheNumbersAnd(int number1, int number2) {
        stepResults.put("number1", number1);
        stepResults.put("number2", number2);
    }

    @When("I add the numbers")
    public void iAddTheNumbers() {
        Integer number1 = (Integer) stepResults.get("number1");
        Integer number2 = (Integer) stepResults.get("number2");
        stepResults.put("sum", number1 + number2);
    }

    @Then("the result should be {int}")
    public void theResultShouldBe(int expectedResult) {
        Integer actualResult = (Integer) stepResults.get("sum");
        assertThat(expectedResult, equalTo(actualResult));
    }

    @Given("I have the following paragraph:")
    public void iHaveTheFollowingParagraph(String paragraph) {
        stepResults.put("paragraph", paragraph);
    }


    @When("I count the length of string")
    public void iCountTheLengthOfString() {
        String myParagraph = (String) stepResults.get("paragraph");
        stepResults.put("length", myParagraph.length());
    }

    @Given("I have a list with elements")
    public void iHaveAListWithElements(List<String> list) {
        stepResults.put("list" , list);
    }

    @When("I check the size of the list")
    public void iCheckTheSizeOfTheList() {
        List<String> myList = (List<String>) stepResults.get("list");
        stepResults.put("list_size", myList.size());
    }

    @Then("the size of the list should be {int}")
    public void theSizeOfTheListShouldBe(int expectedResult) {
        Integer acutalResult = (Integer) stepResults.get("list_size");
        assertThat(expectedResult, equalTo(acutalResult));
    }

    @Given("I have a map with the following key-value pairs:")
    public void iHaveAMapWithTheFollowingKeyValuePairs(Map<String, String> map) {
         stepResults.put("map", map);
    }

    @When("I retrieve the value for the key {string}")
    public void iRetrieveTheValueForTheKeyEmail(String key) {
        Map<String, String> myMap = (Map<String, String>) stepResults.get("map");
        stepResults.put("value", myMap.get(key));
    }

    @Then("the value should be {string}")
    public void theValueShouldBe(String expectedResult) {
        String actualResult = (String) stepResults.get("value");
        assertThat(expectedResult, equalTo(actualResult));
    }

    @Given("I have the following products:")
    public void iHaveTheFollowingProducts(List<Map<String, String>> mapList) {
        stepResults.put("map_list", mapList);
    }


    @When("I calculate the total price")
    public void iCalculateTheTotalPrice() {
        List<Map<String, String>> mapList = (List<Map<String, String>>) stepResults.get("map_list");
        int totalPrice = 0;

        for (Map<String, String> item : mapList) {
            totalPrice += Integer.parseInt(item.get("Price"));
        }
        stepResults.put("total_price", totalPrice);
    }

    @Then("the total price should be {int}")
    public void theTotalPriceShouldBe(int expectedResult) {
        int actualResult = (Integer) stepResults.get("total_price");
        assertThat(expectedResult, equalTo(actualResult));
    }

    @Given("I have a comma-separated string {string}")
    public void iHaveACommaSeparatedString(String commaString) {
        stepResults.put("comma_string", commaString);
    }

    @When("I split and convert the string to a list of integers")
    public void iSplitAndConvertTheStringToAListOfIntegers() {
        String commaString = (String) stepResults.get("comma_string");
        stepResults.put("comma_list", Arrays.asList(commaString.split(",")));
    }

    @Then("the list should be:")
    public void theListShouldBe(List expectedResult) {
        List actualResult = (List) stepResults.get("comma_list");
        assertThat(expectedResult, equalTo(actualResult));
    }

    @Given("I have the date {string}")
    public void iHaveTheDate(String date) {
        LocalDate lDate = LocalDate.parse(date);

        if (stepResults.get("list_dates") == null) {
            List<LocalDate> listDates = new ArrayList<>();
            listDates.add(lDate);
            stepResults.put("list_dates", listDates);
        } else {
            List<LocalDate> listDates = (List<LocalDate>) stepResults.get("list_dates");
            listDates.add(lDate);
        }
    }

    @When("I calculate the difference in days")
    public void iCalculateTheDifferenceInDays() {
        List<LocalDate> listDates = (List<LocalDate>) stepResults.get("list_dates");

        Period period = Period.between(listDates.get(0), listDates.get(1));
        stepResults.put("period", period);
    }

    @Then("the difference should be {int} year {int} month and {int} days")
    public void theDifferenceShouldBeYearMonthAndDays(int year, int month, int days) {
        Period actualResult = (Period) stepResults.get("period");
        assertThat(year, equalTo(actualResult.getYears()));
        assertThat(month, equalTo(actualResult.getMonths()));
        assertThat(days, equalTo(actualResult.getDays()));
    }
}
