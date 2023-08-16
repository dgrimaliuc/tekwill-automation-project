package alexei_drujinin;

import helpers.Helpers;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;

public class AutomationTask2 {
    Map<String, Object> context = new HashMap<>();
    Logger log = Helpers.createLogger(AutomationTask2.class);


    @Given("I have a string {string}")
    public void iHaveAStringLL(String testString) {
        context.put("Checking string", testString);
        log.info("I have one string: " + testString);
    }

    @When("Check the length of the string")
    public void CheckingStringLength() {
        String myString = (String) context.get("Checking string");
        context.put("length of the string", myString.length());
    }

    @Then("The string length should be {int}")
    public void valueComparison(int expectedLength) {
        Integer stringLength = (Integer) context.get("length of the string");
        assertThat(stringLength, Matchers.equalTo(expectedLength));
    }

    @Then("String should contain {string}")
    public void stringShouldContain(String letters) {
        String GivenString = (String) context.get("Checking string");
        assertThat(GivenString, Matchers.containsString(letters));
    }

    @Given("I have a number {int}")
    public void iHaveANumber(Integer arg0) {
        log.info("Number to check: " + arg0);
        context.put("My number: ", arg0);
    }

    @Then("The number should be even")
    public void theNumberShouldBeEven() {
        Integer myNumber = (Integer) context.get("My number: ");
        assertThat(myNumber % 2, Matchers.equalTo(0));
    }

    @Then("The number should be odd")
    public void theNumberShouldBeOdd() {
        Integer myNumber = (Integer) context.get("My number: ");
        assertThat(myNumber % 2, Matchers.equalTo(1));
    }

    @Given("I have a list with elements")
    public void iHaveAListWithElements(List<String> fruits) {
        context.put("fruitsList", fruits);
    }

    @When("I check the size of the list")
    public void checkTheSizeOfTheList() {
        List<String> fruits = (List<String>) context.get("fruitsList");
        context.put("list size", fruits.size());
    }

    @Then("The size of the list should be {int}")
    public void theSizeOfTheListShouldBe(int expectedSize) {
        Integer listSize = (Integer) context.get("list size");
        assertThat(listSize, Matchers.equalTo(expectedSize));
    }
}
