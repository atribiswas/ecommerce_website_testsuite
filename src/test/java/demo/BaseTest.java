package demo;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.Logger;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import demo.utils.LogConfig;
import demo.utils.ReportConfig;

public class BaseTest {
    private Logger logger;
    private ExtentReports extent;
    protected ExtentTest reporter;

    public BaseTest() throws IOException{

        System.out.println("Setting up suite");
        LogConfig.setUp();

        ReportConfig.setUp();
    }

    public void tearDown(){
        System.out.println("Tearing down suite");
        LogConfig.tearDown();
        ReportConfig.tearDown();
    }

    public void createReporter(String testName) throws IOException {
        System.out.println(String.format("Setting up Method %s",testName));
        extent = ReportConfig.getReporter();
        logger = LogConfig.getLogger();

        reporter = extent.createTest(testName);

        info(String.format("Starting Test %s",testName));
    }

    @AfterMethod
    public void assessTest(ITestResult result) throws IOException {
        System.out.println("Tearing down Method");
        if (result.getStatus() == ITestResult.FAILURE) {
            error(result.getMethod().getMethodName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            skip("Test skipped: " + result.getThrowable());
        } else {
            info(String.format("Success for Test %s",result.getMethod().getMethodName()));
        }
    }

    protected void info(String message) throws IOException{
        logger.info(message);
        reporter.info(message);
    }
    protected void error(String message) throws IOException{
        logger.error(String.format("Failure ", message));
        reporter.fail(message);
    }
    protected void skip(String message){
        logger.warn(message);
        reporter.skip(message);
    }
    protected void debug(String message){
        logger.debug(message);
    }
}
