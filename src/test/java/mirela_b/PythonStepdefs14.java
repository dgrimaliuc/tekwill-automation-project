package mirela_b;

import helpers.PythonExecutor;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.assertj.core.util.Arrays;
import org.hamcrest.Matchers;

import static helpers.Helpers.stepResults;
import static javax.swing.text.SimpleAttributeSet.EMPTY;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static helpers.Helpers.getValue;

public class PythonStepdefs14 {

    Logger logger = Logger.getLogger(PythonStepdefs14.class);

    @Before
    public void beforeMethod() {
        stepResults = new ArrayList<>();
    }

    @After
    public void afterMethod() {
        stepResults.clear();
    }

    @Given("I have a String {string}")
    public void iHaveAString(String arg0) {
        stepResults.add(arg0);
        logger.info("The String text: " + arg0);
    }

    @Given("I have a number \"([^\"]*)\"$")
    public void iHaveANumber(Integer arg0) {
        stepResults.add(arg0);
        logger.info("The Number: " + arg0);
    }

    @Given("I have a list with elements {string}, {string}, {string}")
    public void iHaveAListWithElements(String arg0, String arg1, String arg2) {
        List<Object> list = new ArrayList<>();
        list.add(arg0);
        list.add(arg1);
        list.add(arg2);
        stepResults.add(list);
        logger.info("Added elements to list: " + list);
    }

    @When("Execute Python Script {string}")
    public void executePythonScript(String script) {
        String scriptRes = getValue(script, String.class);
        logger.info("Script: " + scriptRes);
        String result = PythonExecutor.executePythonScript(scriptRes);
        logger.info("Result: " + result);
        stepResults.add(result);
    }

    @Then("Assert that {string}")
    public void assertThatPyScript(String pyScript) {
        String isTrue = getValue(pyScript, String.class);
        Boolean result = PythonExecutor.executePythonScript(isTrue, Boolean.class);
        logger.info("Final Result: " + result);
        assertThat(result, Matchers.is(true));
        stepResults.add(EMPTY);
    }


}