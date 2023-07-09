package cristina_savrin;

import helpers.PythonExecutor;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;

import java.util.ArrayList;
import java.util.List;

import static helpers.Helpers.getValue;
import static helpers.Helpers.stepResults;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.hamcrest.MatcherAssert.assertThat;

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

    @Given("^I have a number \"(\\d+)\" CS$")
    public void iHaveANumberCS(Integer arg0) {
        logger.info("I have a number: " + arg0);
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

    @Given("^I have a list with elements CS$")
    public void iHaveAListWithElementsCS(List<String> fruits) {
        logger.info("I have a list: " + fruits);
        stepResults.add(fruits);
    }
}