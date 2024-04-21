package demo.utils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverConfig {

    private static RemoteWebDriver driver = null;

    public static RemoteWebDriver getDriver() {
        if (driver == null) {
            try {
                driver = new ChromeDriver();
                driver.manage().deleteAllCookies();
                driver.manage().window().maximize();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
