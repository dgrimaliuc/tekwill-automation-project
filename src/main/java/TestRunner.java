import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/",
        glue = {"actionLibraries"},
        tags = {"@Smoke"},
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber-html-reports/cucumber.json"})

public class TestRunner {

}