package Gontari_Anna;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;

public class MainStepdefs {
    Logger logger = Logger.getLogger(MainStepdefs.class);
    Integer a, b, c, result;
    @Given("^three numbers \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void threeNumbersAnd(Integer arg0, Integer arg1, Integer arg2) {
        a = arg0;
        b = arg1;
        c = arg2;
        logger.info("Numbers initialised " + a + "," + b + "," + c);
    }

    @When("^find average of three numbers$")
    public void findAverageOfThreeNumbers() {
        result = (a + b + c) / 3;
        logger.info("Calculations have been made " + result);
    }

    @Then("^average should be \"([^\"]*)\"$")
    public void averageShouldBe(Integer expectedResult) {
        logger.info("Validating result ");
        assertThat (result, Matchers.is(expectedResult));
    }
}
