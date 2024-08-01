package denis_grimaliuc.step_definitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleStepDef {

    HashMap<String, Object> stepResults = null;


    @Before
    public void before() {
        stepResults = new HashMap<>();
    }

    @Given("I have a string {string}")
    public void i_have_a_string(String string) {
        stepResults.put("my_string", string);
    }

    @Then("It starts with {string}")
    public void it_starts_with(String string) {
        String myString = (String) stepResults.get("my_string");
        assertThat(myString, startsWith(string));
    }

    @Then("I should see {string}")
    public void i_should_see(String string) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("Test")
    public void test() {

    }

    @Then("Test2")
    public void test2() {
        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
    }

}
