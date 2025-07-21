package Magda_Petrachi.ShopifyCurcumber;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class SecondTasck {

    private Map<String, String> stringMap;
    private String retrievedValue;
    private String csvInput;
    private List<Integer> intList;


    @Given("I have a map with the following key-value pairs: MP")
    public void iHaveAMapWithTheFollowingKeyValuePairsMP(DataTable table) {
        stringMap = new HashMap<>();
        for (Map<String, String> row : table.asMaps()) {
            stringMap.put(row.get("key"), row.get("value"));
        }
    }

    @When("I retrieve the value for the key {string} MP")
    public void iRetrieveTheValueForTheKeyMP(String key) {
        retrievedValue = stringMap.get(key);
        System.out.println("Valoarea extrasă: " + retrievedValue);
    }

    @Then("the value should be {string} MP")
    public void theValueShouldBeMP(String expectedValue) {
        MatcherAssert.assertThat("Cheia nu există sau valoarea este greșită", retrievedValue, notNullValue());
        MatcherAssert.assertThat("Valoarea din map nu corespunde", retrievedValue, equalTo(expectedValue));
    }

    @Given("I have a comma-separated string {string} MP")
    public void iHaveACommaSeparatedStringMP(String input) {
        this.csvInput = input;
        System.out.println("Input primit: " + input);
    }

    @When("I split and convert the string to a list of integers MP")
    public void iSplitAndConvertTheStringToAListOfIntegersMP() {
        intList = Arrays.stream(csvInput.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        System.out.println("Lista convertită: " + intList);
    }

    @Then("the list should be: MP")
    public void theListShouldBeMP(DataTable table) {
        List<Integer> expected = table.asList().stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        assertThat("Lista convertită nu corespunde așteptărilor", intList, equalTo(expected));
    }
}


