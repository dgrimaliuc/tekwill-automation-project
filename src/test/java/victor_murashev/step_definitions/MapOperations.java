package victor_murashev.step_definitions;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MapOperations {
    private Map<String, String> dataMap;
    private String retrievedValue;

    @Given("I have a map with the following key-value pairs:")
    public void i_have_a_map_with_the_following_key_value_pairs(DataTable dataTable) {
        dataMap = new HashMap<>();
        dataTable.asMaps().forEach(row -> dataMap.put(row.get("key"), row.get("value")));
    }

    @When("I retrieve the value for the key {string}")
    public void i_retrieve_the_value_for_the_key(String key) {
        retrievedValue = dataMap.get(key);
    }

    @Then("the value should be {string}")
    public void the_value_should_be(String expectedValue) {
        assertThat("The retrieved email is incorrect.", retrievedValue, equalTo(expectedValue));
    }
}
