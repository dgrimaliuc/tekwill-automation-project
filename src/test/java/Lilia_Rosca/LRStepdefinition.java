package Lilia_Rosca;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ro.Si;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LRStepdefinition {
Map<String, String> context = new HashMap<>();

    @Given("I have a string {string}")
    public void iHaveAString(String string) {
        context.put("my_string", string);
    }

    @When("I check if it contains {string}")
    public void iCheckIfItContains(String subString) {
        String myString = context.get("my_string");
        boolean contains = myString.contains(subString);
        context.put("contains", String.valueOf(contains));
    }

    @Then("It should return true")
    public void itShouldReturnTrue() {
        String contains = context.get("contains");
        assertThat(contains, equalTo("true"));
    }

    // reverse a string - 2 methods - to correct
 /*   @When("I reverse the string")
    public void iReverseTheString(String subString) {
        String myString = context.get("my_string");
        boolean contains = myString.contains(subString);
        context.put("contains", String.valueOf(contains));
    }

    @Then("The reversed string should be {string}")
    public void theReversedStringShouldBe(String arg0) {
    }*/

    /*@When("I check the length of the string")
    public void iCheckTheLengthOfTheString() {
        String len = context.get("my_string");
        int length = myString.length(); // de modificat map
        context.put("length of string ", length);
    }

    @Then("the length should be {string}")
    public void theLengthShouldBe(String arg0) {

    }*/
}
