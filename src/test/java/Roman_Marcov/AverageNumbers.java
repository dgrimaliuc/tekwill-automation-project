package Roman_Marcov;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;

public class AverageNumbers {

    Logger logger = Logger.getLogger(Roman_Marcov.AverageNumbers.class);
    Integer a, b, c, result;

    @Given("^Given three numbers \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" RM$")
    public void givenThreeNumbersAndRM(Integer num1, Integer num2, Integer num3) {
        a = num1;
        b = num2;
        c = num3;
        logger.info("Numbers initialized: " + a + ", " + b + ", " + c);

    }

    @When("^Find average of three numbers$")
    public void findAverageOfThreeNumbers() {
        result = (a + b + c) / 3;
        logger.info("Average is calculated: " + result);
    }

    @Then("^Average should be \"([^\"]*)\"$")
    public void averageShouldBe(Integer expectedResult) {
        logger.info("Validating result");
        assertThat(result, Matchers.equalTo(expectedResult));

    }

}
