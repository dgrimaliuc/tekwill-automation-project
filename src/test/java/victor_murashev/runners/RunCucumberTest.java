package victor_murashev.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/victor_murashev",
        glue = {"victor_murashev.step_definitions", "victor_murashev.hooks"},
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        tags = "@runAllTests",
        monochrome = true
        //strict = true
)
public class RunCucumberTest {
}
