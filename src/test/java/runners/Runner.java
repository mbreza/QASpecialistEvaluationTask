package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * Created by Mateusz Breza
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/htmlreports"},
        features = {"src/test/resources"},
        glue = {"bindings"}
)
public class Runner {
}