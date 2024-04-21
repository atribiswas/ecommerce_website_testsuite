package demo.utils;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ReportConfig {
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final String resourceFolderPath = System.getProperty("user.dir")+"/src/test/resources/";

    public static void setUp() throws IOException {
        if(extent!=null) return;

        ExtentSparkReporter spark = new ExtentSparkReporter("./reports/");

        spark.config().setTheme(Theme.DARK);
        spark.config().setReportName("PureZenz Test Suite");
        spark.config().setDocumentTitle("PureZenz Test Suite - Test Output");

        extent = new ExtentReports();
        
        extent.attachReporter(spark);

        try {
            spark.loadXMLConfig(new java.io.File(resourceFolderPath+"report-prop.xml"));
        } catch (java.io.FileNotFoundException e) {
            System.err.println("Error loading XML config: " + e.getMessage());
        }
    }

    public static void tearDown() {
        extent.flush();
    }

    public static ExtentReports getReporter(){
        return extent;
    }
}
