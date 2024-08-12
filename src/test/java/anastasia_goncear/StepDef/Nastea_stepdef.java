package anastasia_goncear.StepDef;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;

public class Nastea_stepdef {


    @Given("I have a Ustring {string}")
    public void i_have_a_string(String string) {
        stepResults.put("my_string", string);
    }
    HashMap<Object, Object> stepResults = new HashMap<>();


    @Then("The string should Ucontain <subString>")
    public void theStringShouldUcontainSubString() {
        String myString = (String) stepResults.get("my_string");
        String string = "";
        assertThat(myString, startsWith(string));
    }
}
