package alexei_drujinin;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/alexei_drujinin/",
        glue = "alexei_drujinin",
        tags =  "@Run",
        plugin = {"pretty"}
)
public class TestRunner {
}
