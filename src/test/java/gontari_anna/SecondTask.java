package gontari_anna;

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

public class SecondTask {
    Map<String, Object> context = new HashMap<>();
    Logger log = Helpers.createLogger(SecondTask.class);

    @Given("I have one string {string}")
    public void IHaveOneString(String MyOwnString) {
        context.put("Checking string", MyOwnString);
        log.info("I have one string: " + MyOwnString);
    }
    @When("Checking string length")
    public void CheckingStringLength() {
        String myString = (String) context.get("Checking string");
        context.put("length_of_the_string", myString.length());
        log.info("Checking string's length: " + myString);
    }

    @Then("^The string length should be \"([^\"]*)\"$")
    public void theLengthShouldBe (Integer MyOwnString) {
        Integer StringLength = (Integer) context.get("length_of_the_string");
        assertThat (StringLength, Matchers.equalTo(MyOwnString));
        log.info("The length is: " + MyOwnString);
    }

    @Then("GA string should contain {string}")
    public void gaStringShouldContain(String letters) {
        String GivenString = (String) context.get("Checking string");
        assertThat(GivenString, Matchers.containsString(letters));
        log.info("The string should contain: " + letters);
    }

    @Given("^GA has number \"([^\"]*)\"$")
    public void gaHasNumber(Integer arg0) {
        context.put("The_number_to_check", arg0);
        log.info("My number is: " + arg0);
    }

    @Then("GA number should be even")
    public void gaNumberShouldBeEven() {
        Integer myNumber = (Integer) context.get("The_number_to_check");
        assertThat(myNumber %2, Matchers.is(0));
        log.info("There is an even number: " + myNumber);
    }

    @Then("GA number should be odd")
    public void gaNumberShouldBeOdd() {
        Integer myNumber = (Integer) context.get("The_number_to_check");
        assertThat(myNumber %2, Matchers.is(1));
        log.info("There is an odd number: " + myNumber);
    }

    @Given("GA has a list with elements")
    public void gaHasAListWithElements (List<String> myFruits) {
        context.put("the_list_to_check", myFruits);
        log.info("The list of fruits: " + myFruits);
    }

    @When("GA checks the size of the list")
    public void gaChecksTheSizeOfTheList() {
        List<String> myFruits = (List<String>) context.get("the_list_to_check");
        context.put("List's length", myFruits.size());
        log.info("The list of fruits: " + myFruits);
    }

    @Then("^GA size of the list should be \"([^\"]*)\"$")
    public void gaSizeOfTheListShouldBe(Integer arg0) {
        Integer listSize = (Integer) context.get("list_length");
        assertThat(listSize, Matchers.equalTo(arg0));
        log.info("My list size is: " + listSize);
    }
}
