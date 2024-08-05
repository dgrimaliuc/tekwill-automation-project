package victor_murashev.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ListOperations {
    private List<String> fruits;

    @Given("I have a list with elements")
    public void i_have_a_list_with_elements(DataTable dataTable) {
        fruits = new ArrayList<>(dataTable.asList(String.class));
    }

    @When("I check the size of the list")
    public void i_check_the_size_of_the_list() {
        // preparing for size verification, no operation under this step
    }

    @Then("the size of the list should be {int}")
    public void the_size_of_the_list_should_be(int expectedSize) {
        assertThat("The size of the list is incorrect.", fruits.size(), equalTo(expectedSize));
    }


}
