package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import java.io.IOException;

@CucumberOptions(
        features = {"src/test/feature/doDeleteApiCall.feature","src/test/feature/doGetApiCall.feature" ,"src/test/feature/doPostApiCall.feature","src/test/feature/doPutApiCall.feature"},
        //,"src/test/feature/doDeleteApiCall.feature"
        glue = {"stepDefinitions"},
        plugin = {"pretty", "junit:src\\test\\testReport/pickledReports"},
        monochrome = true
)
public class Runner extends AbstractTestNGCucumberTests{


    public Runner() throws IOException {
    }
}
