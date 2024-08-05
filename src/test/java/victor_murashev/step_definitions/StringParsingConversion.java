package victor_murashev.step_definitions;

import io.cucumber.java.en.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StringParsingConversion {

    private String commaSeparatedString;
    private List<Integer> integerList;

    @Given("I have a comma-separated string {string}")
    public void i_have_a_comma_separated_string(String commaSeparatedString) {
        this.commaSeparatedString = commaSeparatedString;
    }

    @When("I split and convert the string to a list of integers")
    public void i_split_and_convert_the_string_to_a_list_of_integers() {
        integerList = Stream.of(commaSeparatedString.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    @Then("the list should be:")
    public void the_list_should_be(io.cucumber.datatable.DataTable dataTable) {
        List<Integer> expectedList = dataTable.asList(Integer.class);
        assertThat("The list of integer numbers is incorrect.", integerList, equalTo(expectedList));
    }

}
