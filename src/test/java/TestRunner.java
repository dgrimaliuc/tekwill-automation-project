import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/"},
        glue = "denis_grimaliuc",
//        tags = "@Smoke",
        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber-html-reports/cucumber.json"}
)

public class TestRunner {

}