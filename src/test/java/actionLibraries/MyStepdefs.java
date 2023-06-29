package actionLibraries;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;

public class MyStepdefs {

    Logger logger = Logger.getLogger(MyStepdefs.class);

    Integer a, b, result;

    @Before
    public void beforeMethod() {
        System.out.println("Before");
    }

    @After
    public void afterMethod() {
        System.out.println("afterMethod");
    }

    @Given("^I have two numbers \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iHaveTwoNumbersAnd(Integer num1, Integer num2) {
        a = num1;
        b = num2;
        logger.info("Numbers initialized: " + a + ", " + b);
    }

    @When("^I add the numbers$")
    public void iAddTheNumbers() {
        result = a + b;
        logger.info("Result calculated: " + result);
    }

    @Then("^the result should be \"([^\"]*)\"$")
    public void theResultShouldBe(Integer expectedResult) {
        logger.info("Validating result");
        assertThat(result, Matchers.equalTo(expectedResult));

    }

    @Given("^validate password \"([^\"]*)\"$")
    public void validatePassword(String password) {
        assertThat(password, Matchers.matchesPattern("[\\w]{5,32}"));

    }

    @Given("^Echo Background$")
    public void echoBackground() {
        System.out.println("Background");
    }
}
