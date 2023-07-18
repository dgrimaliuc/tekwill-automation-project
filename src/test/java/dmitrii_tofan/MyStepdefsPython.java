package dmitrii_tofan;

import helpers.Helpers;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static helpers.Helpers.stepResults;

public class MyStepdefsPython {

    Logger log = Helpers.createLogger(MyStepdefsPython.class);
    Helpers.Appender app = null;


    @Before
    public void beforeMethod(Scenario scenario) {
        app = new Helpers.Appender(scenario);
        log.addAppender(app);
        stepResults = new ArrayList<>();
    }
//        @After
//    public void afterMethod() {
//        log.removeAllAppenders();
//        stepResults.clear();
//    }

    @Given("DTA I have a string {string}")
    public void dtaIHaveAString(String arg0) {
        log.info("I have a string: " + arg0);
        stepResults.add(arg0);
    }

    @When("DTA Execute Python String {string}")
    public void dtaExecutePythonString(List<Map<String, String>> elements) {

        {
            stepResults.add(elements);
        }
    }


    @Then("DTA Assert that {string}")
    public void dtaAssertThat(List<String> list) {
    }


//    @Given("If custom location is open {string}")
//    public void ifCustomLocationIsOpen(String customLocation) {
//        if (customLocation == null || customLocation.isEmpty()){
//            throw new RuntimeException("Custom location is empty");
//        }
//
//        driver.get(Path.of("https://petstore-kafka.swagger.io/?location=" + customLocation));
//
//    }
}
