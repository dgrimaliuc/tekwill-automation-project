package dmitrii_tofan;

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

public class DtSecondHw {

        Logger log = Helpers.createLogger(DtSecondHw.class);
        Map<String, Object> context = new HashMap<>();

        @Given("DT I have a string {string}")
        public void iHaveAString(String arg0) {
                log.info("My string: " + arg0);
                context.put("dt_string", arg0);
        }

        @When("^DT I check the length of the string$")
        public void checkLength() {
                String myString = (String) context.getOrDefault("dt_string", "");
                context.put("lengthString", myString.length());
        }

        @Then("^DT the length should be \"([^\"]*)\"$")
        public void valueComparison(Integer arg0) {
                Integer lengthOfString = (Integer) context.get("lengthString");
                assertThat(lengthOfString, Matchers.equalTo(arg0));

        }

        @Then("^DT the string should contain \"([^\"]*)\"$")
        public void dtTheStringShouldContain(String arg0) {
                String string = (String) context.get("dt_string");
                assertThat(string, Matchers.containsString(arg0));
                log.info("dt_string: " + arg0);
        }

        @Given("^DT I have a number \"([^\"]*)\"$")
        public void dtIHaveANumber(Integer arg0) {
                log.info("My number: " + arg0);
                context.put("dt_num", arg0);
        }

        @Then("DT the number should be even")
        public void dtTheNumberShouldBeEven() {
                Integer num1 = (Integer) context.get("dt_num");
                assertThat(num1 % 2, Matchers.equalTo(0));

        }

        @Then("DT the number should be odd")
        public void theNumberShouldBeOdd() {
                Integer num2 = (Integer) context.get("dt_num");
                assertThat(num2 % 2, Matchers.equalTo(1));
        }

        @Given("^DT I have a list with elements$")
        public void dtIHaveAListWithElements(List<String> fruits) {
                context.put("fruits_list", fruits);
        }

        @When("^DT I check the size of the list$")
        public void dtICheckTheSizeOfTheList() {
                List<String> fruits = (List<String>) context.get("fruits_list");
                context.put("the_length_of_list", fruits.size());
        }

        @Then("^DT the size of the list should be \"([^\"]*)\"$")
        public void dtTheSizeOfTheListShouldBe(Integer arg0) {
                Integer size = (Integer) context.get("the_length_of_list");
                assertThat(size, Matchers.equalTo(arg0));
        }
}
