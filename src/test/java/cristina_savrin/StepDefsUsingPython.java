package cristina_savrin;

import helpers.Helpers;
import helpers.PythonExecutor;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static helpers.Helpers.getValue;
import static helpers.Helpers.stepResults;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.hamcrest.MatcherAssert.assertThat;
//import static helpers.Helpers.stepResults;

public class StepDefsUsingPython {

    Logger logger = Logger.getLogger(StepDefsUsingPython.class);

    @Before
    public void beforeMethod() {
        stepResults = new ArrayList<>();
    }

    @After
    public void afterMethod() {
        stepResults.clear();
    }

    @Given("^I have a string \"([^\"]*)\" CS$")
    public void iHaveAString(String arg0) {
        logger.info("I have a string: " + arg0);
        stepResults.add(arg0);
    }

    @When("^Execute Python Script \"(.+)\" CS$")
    public void executePythonScript(String script) {
        String scriptRes = getValue(script, String.class);
        logger.info("Executing script: " + scriptRes);
        String res = PythonExecutor.executePythonScript(scriptRes);
        logger.info("Result: " + res);
        stepResults.add(res);
    }

    @Then("^Assert that \"([^\"]*)\" CS$")
    public void assertThatPyScriptIsTrue(String pyScript) {
        String isTrue = getValue(pyScript, String.class);

        Boolean result = PythonExecutor.executePythonScript(isTrue, Boolean.class);
        logger.info("Assertion: " + isTrue);
        assertThat(result, Matchers.is(true));
        stepResults.add(EMPTY);
    }

    @Then("^the string should contain \"([^\"]*)\" CS$")
    public void theStringShouldContainCS(String arg0) {
        String string = (String) stepResults.get(0);
        logger.info("Substring assertion");
        assertThat(string, Matchers.containsString(arg0));
        stepResults.add(EMPTY);
    }

    @Given("^I have a number \"(\\d+)\" CS$")
    public void iHaveANumberCS(Integer arg0) {
        logger.info("I have a number: " + arg0);
        stepResults.add(arg0);
    }

    @Then("^the number should be even CS$")
    public void theNumberShouldBeEvenCS() {
        Integer num = (Integer) stepResults.get(0);
        logger.info("Even number assertion");
        assertThat(0, Matchers.equalTo(num % 2));
        stepResults.add(EMPTY);
    }

    @Then("^the number should be odd CS$")
    public void theNumberShouldBeOddCS() {
        Integer num = (Integer) stepResults.get(0);
        logger.info("Odd number assertion");
        assertThat(1, Matchers.equalTo(num % 2));
        stepResults.add(EMPTY);
    }

    @Given("^I have a list with elements CS$")
    public void iHaveAListWithElementsCS(List<String> fruits) {
        logger.info("I have a list: " + fruits);
        stepResults.add(fruits);
    }

    @Then("^the size of the list should be \"([^\"]*)\" CS$")
    public void theSizeOfTheListShouldBeCS(String arg0) {
        String size = (String) stepResults.get(1);
        logger.info("List size assertion");
        assertThat(arg0, Matchers.equalTo(size));
        stepResults.add(EMPTY);
    }
}