package alina_gutul;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;

public class MainStepAvg {
    Logger logger = Logger.getLogger(MainStepAvg.class);
    Integer a, b, c, result;

    @Given("^Given I have three numbers \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" AG$")
    public void given_i_have_three_numbers_and_ag(Integer num1, Integer num2, Integer num3) {
        a = num1;
        b = num2;
        c = num3;
        logger.info("Numbers initialized: " + a + ", " + b + ", " + c);
    }

    @When("^I find avg of three numbers$")
    public void i_find_avg_of_three_numbers() {
        result = (a + b + c) / 3;
        logger.info("Average is calculated: " + result);
    }

    @Then("^average should be \"([^\"]*)\"$")
    public void theAverageShouldBe(Integer expectedResult) {
        logger.info("Validating result" + expectedResult);
        assertThat(result, Matchers.equalTo(expectedResult));

    }

}