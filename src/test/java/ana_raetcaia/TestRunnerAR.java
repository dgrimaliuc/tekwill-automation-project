package ana_raetcaia;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/ana_raetcaia"},
        glue = "ana_raetcaia",
//        tags = "@Smoke",
        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber-html-reports/cucumber.json"}
)
class TestRunnerAR {

}