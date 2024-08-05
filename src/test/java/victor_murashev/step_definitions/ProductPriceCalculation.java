package victor_murashev.step_definitions;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProductPriceCalculation {
    private List<Map<String, String>> products;
    private int totalPrice;

    @Given("I have the following products:")
    public void i_have_the_following_products(DataTable dataTable) {
        // Converting the DataTable into a List of Maps
        products = dataTable.asMaps(String.class, String.class);
    }

    @When("I calculate the total price")
    public void i_calculate_the_total_price() {
        totalPrice = products.stream()
                .mapToInt(product -> Integer.parseInt(product.get("Price")))
                .sum();
    }

    @Then("the total price should be {int}")
    public void the_total_price_should_be(int expectedTotalPrice) {
        assertThat("The total price is incorrect.", totalPrice, equalTo(expectedTotalPrice));
    }
}
