package Runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)

@CucumberOptions(
        strict = true,
        features = "classpath:Features",
        glue = {"StepDefinition", "Runner"},
        monochrome = false,
        plugin = {"pretty",
                "json:target/cucumber.json", "html:target/site/cucumber-pretty"},
        tags = "@KiwisaverCalculator",
        publish = true
)
public class RunTest {

}

