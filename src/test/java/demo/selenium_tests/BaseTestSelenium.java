package demo.selenium_tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import demo.BaseTest;
import demo.utils.DriverConfig;

public class BaseTestSelenium extends BaseTest{

    protected WebDriver driver;

    public BaseTestSelenium() throws IOException{
        super();
        driver = DriverConfig.getDriver();
    }

    @Override
    public void tearDown(){
        System.out.println("Tearing down selenium test");
        driver.close();
        driver.quit();
        driver = null;
        super.tearDown();
    }

    @Override
    protected void info(String message) throws IOException {
        try{
            String storedFilePath = String.format("%s/reports/info-screenshot-%s.jpg",System.getProperty("user.dir"),message.toLowerCase().replace(" ", "_"));

            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file,new File(storedFilePath));
            super.reporter.addScreenCaptureFromPath(storedFilePath);
        }
        catch(Exception e){
            message = String.format("(Failed SS)... %s", message);
        }
        super.info(message);
    }

    @Override
    protected void error(String message) throws IOException {
        try{
            String storedFilePath = String.format("%s/reports/error-screenshot-%s.jpg",System.getProperty("user.dir"),message.toLowerCase().replace(" ", "_"));

            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file,new File(storedFilePath));
            super.reporter.addScreenCaptureFromPath(storedFilePath);
        }
        catch(Exception e){
            message = String.format("(Failed SS)... %s", message);
        }
        super.error(message);
    }

}
