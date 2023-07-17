package ana_raetcaia;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.Matchers;
import org.apache.log4j.Logger;

import static org.hamcrest.MatcherAssert.assertThat;

public class MyStepdefs {

    Logger logger = Logger.getLogger(MyStepdefs.class);
    Integer a, b, c, avg;

    @Given("^I have three numbers \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iHaveThreeNumbersAndAR(Integer num1, Integer num2, Integer num3) {
        a = num1;
        b = num2;
        c = num3;
        logger.info("Numbers initialised: " + a + "," + b + "," + c);
    }

    @When("^I find average of three numbers$")
    public void iFindAverageOfThreeNumbersAR() {
        avg = (a + b + c) / 3;
        logger.info("Average of numbers: " + avg);
    }

    @Then("The average should be \"([^\"]*)\"$")
    public void theAverageShouldBeAR(Integer expectedResult) {
        logger.info("Validating avg");
        assertThat(avg, Matchers.is(5));
    }

}
