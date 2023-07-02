package cristina_savrin;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;

public class MyStepdefs {
    Logger logger = Logger.getLogger(MyStepdefs.class);
    int x, y, z, avg;

    @Given("^I have three numbers \"(\\d+)\", \"(\\d+)\" and \"(\\d+)\"$")
    public void iHaveThreeNumbersAnd(int arg0, int arg1, int arg2) {
        x = arg0;
        y = arg1;
        z = arg2;
        logger.info("I have three numbers: " + x + ", " + y + ", " + z);
    }

    @When("^I find average of three numbers$")
    public void iFindAverageOfThreeNumbers() {
        avg = (x + y + z) / 3;
        logger.info("The average of three numbers is: " + avg);
    }

    @Then("^the average should be \"(\\d+)\"$")
    public void theAverageShouldBe(int arg0) {
        logger.info("Assertion");
        assertThat(avg, Matchers.equalTo(arg0));
    }
}