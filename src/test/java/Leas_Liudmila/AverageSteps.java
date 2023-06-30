package Leas_Liudmila;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import static org.hamcrest.MatcherAssert.assertThat;
public class AverageSteps {

    Logger logger = Logger.getLogger(AverageSteps.class);
    Integer a, b, c, result;
    @Given("^I have three numbers \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iHaveThreeNumbersAnd(Integer num1, Integer num2, Integer num3)  {
        a = num1;
        b = num2;
        c = num3;
        logger.info("Numbers initialized: " + a + ", " + b + ", " + c);

    }

    @When("^I find average of three numbers$")
    public void iFindAverageOfThreeNumbers() {
        result = (a + b + c) / 3;
        logger.info("Average is calculated: " + result);
    }

    @Then("^the average should be \"([^\"]*)\"$")
    public void theAverageShouldBe(Integer expectedResult) {
        logger.info("Validating result");
        assertThat(result, Matchers.equalTo(expectedResult));

    }
}
