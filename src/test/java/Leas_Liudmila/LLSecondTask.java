package Leas_Liudmila;

import helpers.Helpers;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static helpers.Helpers.stepResults;
import static org.hamcrest.MatcherAssert.assertThat;

public class LLSecondTask {

    Logger log = Helpers.createLogger(LLSecondTask.class);
    Helpers.Appender appLL = null;
    Map<String, Object> context = new HashMap<>();

    @Before
    public void beforeMethod(Scenario scenario) {
        appLL = new Helpers.Appender(scenario);
        log.addAppender(appLL);
        stepResults = new ArrayList<>();
    }

    @After
    public void afterMethod() {
        log.removeAllAppenders();
        stepResults.clear();
    }

    @Given("LL I have a string {string}")
    public void iHaveAStringLL(String arg0) {
        log.info("My string: " + arg0);
        context.put("ll_string", arg0);
    }

    @When("LL I check the length of the string")
    public void checkLength() {
        String myString = (String) context.get("ll_string");
        context.put("lengthString", myString.length());
    }


    @Then("^LL the length should be \"([^\"]*)\"$")
    public void valueComparison(Integer arg0) {
        Integer lengthOfString = (Integer) context.get("lengthString");

        assertThat(lengthOfString, Matchers.equalTo(arg0));
    }

    @Then("^LL the string should contain \"([^\"]*)\"$")
    public void verifyContainOfString(String arg0) {
        String newString = (String) context.get("ll_string");
        assertThat(newString, Matchers.containsString(arg0));
    }

    @Given("^LL I have a number \"([^\"]*)\"$")
    public void llIHaveANumber(Integer arg0) {
        log.info("My number: " + arg0);
        context.put("ll_number", arg0);
    }

    @Then("LL the number should be even")
    public void llTheNumberShouldBeEven() {
        Integer newNumber = (Integer) context.get("ll_number");
        assertThat(newNumber % 2, Matchers.equalTo(0));
    }

    @Then("LL the number should be odd")
    public void llTheNumberShouldBeOdd() {
        Integer newNumber = (Integer) context.get("ll_number");
        assertThat(newNumber % 2, Matchers.equalTo(1));
    }


    @Given("^LL I have a list with elements$")
    public void llIHaveAListWithElements(List<String> fruits) {
        context.put("myFruits", fruits);
    }

    @When("LL I check the size of the list")
    public void llICheckTheSizeOfTheList() {
        List<String> fruits = (List<String>) context.get("myFruits");
        context.put("listSize", fruits.size());
    }

    @Then("^LL the size of the list should be \"([^\"]*)\"$")
    public void llTheSizeOfTheListShouldBe(Integer arg0) {
        Integer newSize = (Integer) context.get("listSize");
        assertThat(newSize, Matchers.equalTo(arg0));
    }
}
