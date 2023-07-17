package ana_raetcaia;


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


public class MyStepdefs2{

    Map<String, Object> context = new HashMap<>();

    Logger log = Helpers.createLogger(MyStepdefs2.class);

    @Given("I have a string {string}")
    public void iHaveAString(String arg0) {
        context.put("string_to_check", arg0);
        log.info("I have a string: " + arg0);
    }

    @When("I check the length of the string")
    public void iCheckTheLengthOfTheString() {
        String myString = (String) context.get("string_to_check");
        context.put("the_length_of_string", myString.length());
        log.info("I check the length of the string: " + myString);
    }

    @Then("^the length should be \"([^\"]*)\"$")
    public void theLengthShouldBe(Integer arg0) {
        Integer theLengthOfString = (Integer) context.get("the_length_of_string");
        assertThat(theLengthOfString, Matchers.equalTo(arg0));
        log.info("The length should be: " + arg0);
    }

    @Then("the string should contain {string}")
    public void theStringShouldContain(String arg0) {
        String string = (String) context.get("string_to_check");

        assertThat(string, Matchers.containsString(arg0));
        log.info("The string should contain: " + arg0);
    }

    @Given("^I have a number \"([^\"]*)\"$")
    public void iHaveANumber(Integer arg0) {
        context.put("number_to_check", arg0);
        log.info("I have a number: " + arg0);
    }

    @Then("the number should be even")
    public void theNumberShouldBeEven() {
        Integer number= (Integer) context.get("number_to_check");
        assertThat(number % 2, Matchers.is(0));
        log.info("The even number: " + number);
    }

    @Then("the number should be odd")
    public void theNumberShouldBeOdd() {
        Integer number= (Integer) context.get("number_to_check");
        assertThat(number % 2, Matchers.is(1));
        log.info("The odd number: " + number);
    }


    @Given("I have a list with elements")
    public void iHaveAListWithElements(List<String> fruits) {
        context.put("list_to_check", fruits);
        log.info("The list of fruits: " + fruits);
    }

    @When("I check the size of the list")
    public void iCheckTheSizeOfTheList() {
        List<String> fruits = (List<String>) context.get("list_to_check");
       context.put("the length_of_list", fruits.size());
        log.info("Check the size of the list: " + fruits);
    }

    @Then("^the size of the list should be \"([^\"]*)\"$")
    public void theSizeOfTheListShouldBe(Integer arg0) {
        Integer size = (Integer) context.get("the length_of_list");
        assertThat(size, Matchers.equalTo(arg0));
        log.info("The size of the list should be: " + size);
    }
}
