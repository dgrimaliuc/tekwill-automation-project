package Roman_Marcov;

import helpers.Helpers;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

public class PythonExec {
    Logger log = Helpers.createLogger(PythonExec.class);
    Map<String, Object> context = new HashMap<>();

    @Given("^I have a string \"([^\"]*)\" MR$")
    public void iHaveAStringMR(String str0) {
        log.info("My string: " + str0);
        context.put("MR_String", str0);

    }

    @When("I check the length of the string MR")
    public void iCheckTheLength() {
        String myString = (String) context.get("MR_String");
        context.put("iCheckTheLength", myString.length());
    }

    @Then("^the length should be \"([^\"]*)\" MR$")
    public void lengthShouldBe(Integer arg0) {
        Integer lengthOfString = (Integer) context.get("iCheckTheLength");
        assertThat(lengthOfString, Matchers.equalTo(arg0));
    }

    @Then("^the string should contain \"([^\"]*)\" MR$")
    public void stringShouldContain(String str0) {
        String newString = (String) context.get("MR_String");
        assertThat(newString, Matchers.containsString(str0));
    }

    @Given("^I have a number \"([^\"]*)\" MR$")
    public void iHaveANumberMR(Integer arg0) {
        log.info("My number: " + arg0);
        context.put("MR_Number", arg0);
    }

    @Then("the number should be even MR")
    public void theNumberShouldBeEvenMR() {
        Integer newNumber = (Integer) context.get("MR_Number");
        assertThat(newNumber % 2, Matchers.equalTo(0));
    }


    @Then("the number should be odd MR")
    public void theNumberShouldBeOddMR() {
        Integer newNumber = (Integer) context.get("MR_Number");
        assertThat(newNumber % 2, Matchers.equalTo(1));
    }


    @Given("I have a list with elements MR")
    public void iHaveAListWithElementsMR(List<String> fruits) {
        context.put("myFruits", fruits);
    }


    @When("I check the size of the list MR")
    public void iCheckTheSizeOfTheListMR() {
        List<String> fruits = (List<String>) context.get("myFruits");
        context.put("listSize", fruits.size());
    }

    @Then("^the size of the list should be \"([^\"]*)\" MR$")
    public void theSizeOfTheListShouldBeMR(Integer arg0) {
        Integer newSize = (Integer) context.get("listSize");
        assertThat(newSize, Matchers.equalTo(arg0));
    }
}
