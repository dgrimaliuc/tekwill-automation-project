package denis_grimaliuc;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class DGStepDefenishn {
    @Then("I have a list")
    public void iHaveAList(List<Map<String, String>> map) {
        System.out.println(map);
    }

    @When("I check if it contains {string}")
    public void i_check_if_it_contains(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("It should return true")
    public void it_should_return_true() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
