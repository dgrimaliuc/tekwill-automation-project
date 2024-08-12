package chiril_bortnicov.stepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.internal.Integers;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;



public class SimpleStepDef {

    private String str;
    private int actualLength;
    private int totalSum;
    HashMap<String, Object> stepResults = new HashMap<>();
    List<Map<String, String>> products;
    private int[] intArray;

    @Given("I have a string {string}")
    public void iHaveAString(String string) {
        str = string;
    }

    @When("I check the length of the string")
    public void iCheckTheLengthOfTheString() {
        actualLength = str.length();
    }

    @Then("the length should be {string}")
    public void theLengthShouldBe(String shouldBelengthString) {
        int expectedLength = Integer.parseInt(shouldBelengthString);
        assertThat("The length of string is incorrect", actualLength, equalTo(expectedLength));
    }


    @Given("I have the numbers {int} and {int}")
    public void iHaveTheNumbersAnd(int a, int b) {
        stepResults.put("num1", a);
        stepResults.put("num2", b);

    }

    @When("I subtract the second number from the list")
    public void iSubtractTheSecondNumberFromTheList() {
        Integer num1 = (Integer) stepResults.get("num1");
        Integer num2 = (Integer) stepResults.get("num2");
        Integer result = num1 - num2;
        stepResults.put("result", result);

    }

    @Then("The result should be {int}")
    public void theResultShouldBe(int expectedResult) {
        Integer actualResult = (Integer) stepResults.get("result");
        assertThat(actualResult, equalTo(expectedResult));
    }

    @When("I add the numbers")
    public void iAddTheNumbers() {
        Integer num1 = (Integer) stepResults.get("num1");
        Integer num2 = (Integer) stepResults.get("num2");
        Integer result = num1 + num2;
        stepResults.put("result", result);
    }

    @Then("the result should be {int}")
    public void theResultShouldBe2(int expectedResult2) {
        Integer actualResult = (Integer) stepResults.get("result");
        assertThat(actualResult, equalTo(expectedResult2));
    }

    @Given("I have the following paragraph:")
    public void iHaveTheFollowingParagraph(String paragraph) {
        stepResults.put("paragraph", paragraph);
    }

    @When("I count the length of string")
    public void iCountTheLengthOfString() {
        var paragraph = stepResults.get("paragraph").toString();
        var length = paragraph.length();
        stepResults.put("lengthOfString", length);
    }

    @Then("the length should be {int}")
    public void theLengthShouldBe(int lengthShouldBe) {
        Integer actual = (Integer) stepResults.get("lengthOfString");
        assertThat(actual, equalTo(lengthShouldBe));

    }

    @Given("I have a list with elements")
    public void iHaveAListWithElements(List<String> fruits) {
        stepResults.put("fruits", fruits);
    }

    @When("I check the size of the list")
    public void iCheckTheSizeOfTheList() {
        ArrayList<String> list = new ArrayList<>((List) stepResults.get("fruits"));
        stepResults.put("checkTheSize", list.size());
    }

    @Then("the size of the list should be {int}")
    public void theSizeOfTheListShouldBe(Integer shouldBe) {
        int size = (Integer) stepResults.get("checkTheSize");
        assertThat(size, equalTo(shouldBe));
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
    public void theValueShouldBe(String value) {
        String actual = (String) stepResults.get("my_value");
        assertThat(actual, equalTo(value));
    }

    @Given("I have the following products:")
    public void iHaveTheFollowingProducts(DataTable dataTable) {
        products = dataTable.asMaps(String.class, String.class);
    }

    @When("I calculate the total price")
    public void iCalculateTheTotalPrice() {
        totalSum = products.stream()
                .mapToInt(row -> Integer.parseInt(row.get("Price")))
                .sum();
}


    @Then("the total price should be {int}")
    public void theTotalPriceShouldBe(int price) {
        assertThat(totalSum, equalTo(price));
    }

    @Given("I have a comma-separated string {string}")
    public void iHaveACommaSeparatedString(String comma) {
        str = comma;
    }

    @When("I split and convert the string to a list of integers")
    public void iSplitAndConvertTheStringToAListOfIntegers() {
        intArray = Arrays.stream(str.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    @Then("The list should be:")
    public void TheListShouldBe(List<Integer> arr) {
        assertThat(intArray, equalTo(arr));
        }

    @Given("I have to dates {string} and {string}")
    public void iHaveToDatesAnd(String date1, String date2) {
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
        stepResults.put("yearsDiff", period.getYears());
        stepResults.put("monthsDiff", period.getMonths());
        stepResults.put("daysDiff", period.getDays());
        
    }

    @Then("The difference should be {int} year {int} month and {int} days")
    public void theDifferenceShouldBeYearMonthAndDays(int years, int month, int days) {
        Integer actYears = (Integer) stepResults.get("yearsDiff");
        Integer actMonths = (Integer) stepResults.get("monthsDiff");
        Integer actDays = (Integer) stepResults.get("daysDiff");
        assertThat(actYears, equalTo(years));
        assertThat(actMonths, equalTo(month));
        assertThat(actDays, equalTo(days));
    }
}
