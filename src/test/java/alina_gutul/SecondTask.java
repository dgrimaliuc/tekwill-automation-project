package alina_gutul;

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
    Logger log = Helpers.createLogger(SecondTask.class);
    Map<String, Object> context = new HashMap<>();

    @Given("AG I have a string {string}")
    public void agIHaveAString(String inputString) {
        log.info("My string: " + inputString);
        context.put("input_string", inputString);
    }

    @When("^AG I check the length of the string$")
    public void agCheckLength() {
        String myString = (String) context.getOrDefault("input_string", "");
        context.put("string_length", myString.length());
    }

    @Then("^AG the length should be \"([^\"]*)\"$")
    public void agValueComparison(Integer expectedLength) {
        Integer lengthOfString = (Integer) context.get("string_length");
        assertThat(lengthOfString, Matchers.equalTo(expectedLength));
    }

    @Then("^AG the string should contain \"([^\"]*)\"$")
    public void agTheStringShouldContain(String substring) {
        String inputString = (String) context.get("input_string");
        assertThat(inputString, Matchers.containsString(substring));
        log.info("input_string: " + inputString);
    }

    @Given("^AG I have a number \"([^\"]*)\"$")
    public void agIHaveANumber(Integer number) {
        log.info("My number: " + number);
        context.put("number", number);
    }

    @Then("AG the number should be even")
    public void agTheNumberShouldBeEven() {
        Integer num = (Integer) context.get("number");
        assertThat(num % 2, Matchers.equalTo(0));
    }

    @Then("AG the number should be odd")
    public void agTheNumberShouldBeOdd() {
        Integer num = (Integer) context.get("number");
        assertThat(num % 2, Matchers.equalTo(1));
    }

    @Given("^AG I have a list with elements$")
    public void agIHaveAListWithElements(List<String> elements) {
        context.put("elements_list", elements);
    }

    @When("^AG I check the size of the list$")
    public void agCheckListSize() {
        List<String> elements = (List<String>) context.get("elements_list");
        context.put("list_size", elements.size());
    }

    @Then("^AG the size of the list should be \"([^\"]*)\"$")
    public void agTheSizeOfTheListShouldBe(Integer expectedSize) {
        Integer size = (Integer) context.get("list_size");
        assertThat(size, Matchers.equalTo(expectedSize));
    }
}


