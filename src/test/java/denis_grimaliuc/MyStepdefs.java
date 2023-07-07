package denis_grimaliuc;


import helpers.Helpers;
import helpers.PythonExecutor;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static helpers.Helpers.getValue;
import static helpers.Helpers.stepResults;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.hamcrest.MatcherAssert.assertThat;

public class MyStepdefs {

    Logger log = Helpers.createLogger(MyStepdefs.class);
    Helpers.Appender app = null;

    //    @Before
    public void beforeMethod(Scenario scenario) {
        app = new Helpers.Appender(scenario);
        log.addAppender(app);
        stepResults = new ArrayList<>();
    }

    //    @After
    public void afterMethod() {
        log.removeAllAppenders();
    }


    @When("^Execute Python Script \"(.+)\"$")
    public void executePythonScript(String script) {
        String scriptRes = getValue(script, String.class);
        System.out.println("Executing script: " + scriptRes);
        String res = PythonExecutor.executePythonScript(scriptRes);
        System.out.println("Result: " + res);
        stepResults.add(res);
    }

    @Then("^Assert that \"([^\"]*)\"$")
    public void assertThatPyScriptIsTrue(String pyScript) {
        String isTrue = getValue(pyScript, String.class);

        Boolean result = PythonExecutor.executePythonScript(isTrue, Boolean.class);
        System.out.println("Verify: " + isTrue);
        assertThat(result, Matchers.is(true));
        stepResults.add(EMPTY);
    }


    @Given("^I have two numbers \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iHaveTwoNumbersAnd(int arg0, int arg1) {
        log.info("I have two numbers: " + arg0 + ", " + arg1);
        stepResults.add(new int[]{arg0, arg1});

    }

    @Then("^The result in step \"([^\"]*)\" should be \"([^\"]*)\"$")
    public void theResultShouldBe(String step, Integer arg0) {
        Integer num = getValue(step, Integer.class);
        assertThat(num, Matchers.is(arg0));
        stepResults.add(EMPTY);

    }


    @Given("^I have two strings \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iHaveTwoStringsAnd(String arg0, String arg1) {
        log.info("I have two strings: " + arg0 + ", " + arg1);
        stepResults.add(new String[]{arg0, arg1});
    }


    @Given("^I have a string \"([^\"]*)\"$")
    public void iHaveAString(String arg0) {
        log.info("I have a string: " + arg0);
        stepResults.add(arg0);
    }

    @Given("^I have a list of maps with elements$")
    public void iHaveAListWithElements(List<Map<String, String>> elements) {
        stepResults.add(elements);
    }

    @Given("^I have a list$")
    public void iHaveAList(List<String> list) {
        log.info("I have a string: " + list);
        stepResults.add(list);
    }

}
