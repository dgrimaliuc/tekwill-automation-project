package victor_murashev.step_definitions;

import io.cucumber.java.en.*;

import java.time.LocalDate;
import java.time.Period;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DateCalculations {
    private LocalDate startDate;
    private LocalDate endDate;
    private Period period;

    @Given("I have the date {string}")
    public void i_have_the_date(String date) {
        if (startDate == null) {
            startDate = LocalDate.parse(date);
        } else {
            endDate = LocalDate.parse(date);
        }
    }

    @When("I calculate the difference in days")
    public void i_calculate_the_difference_in_days() {
        period = Period.between(startDate, endDate);
    }

    @Then("the difference should be {int} year {int} month and {int} days")
    public void the_difference_should_be_year_month_and_days(int expectedYearsValue, int expectedMonthsValue, int expectedDaysValue) {
        assertThat("The number of years is incorrect.", period.getYears(), equalTo(expectedYearsValue));
        assertThat("The number of months is incorrect.", period.getMonths(), equalTo(expectedMonthsValue));
        assertThat("The number of days is incorrect.", period.getDays(), equalTo(expectedDaysValue));
    }
}
