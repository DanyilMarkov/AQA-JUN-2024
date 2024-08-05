package org.prog.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.prog.cucumber.steps.WebSteps;
import org.prog.driver.WedDriverFactory;
import org.prog.page.GooglePage;
import org.prog.page.AlloPage;
import org.prog.util.CucumberStorage;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(
        features = "src/test/resources/features/allo.feature",
        glue = "org.prog.cucumber.steps",
        plugin = {"pretty",
                "json:target/cucumber-reports/Cucumber.json",
                "html:target/cucumber-report.html"
        })
public class CucumberRunner extends AbstractTestNGCucumberTests {

    private WebDriver driver;

    @BeforeSuite
    public void init() {
        driver = WedDriverFactory.getDriver();
        WebSteps.alloPage = new org.prog.page.AlloPage(driver);
    }

    @BeforeMethod
    public void beforeEach() {
        CucumberStorage.HOLDER.clear();
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
}
