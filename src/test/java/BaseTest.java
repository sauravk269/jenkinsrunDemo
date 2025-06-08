import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ScreenshotUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    protected WebDriver driver;
private static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> testLog = new ThreadLocal<>();
    @BeforeSuite
    public void BeforeSuite(){
     if(extent==null){
         String timeStamp= new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
         String report_path=System.getProperty("user.dir")+"/reports/ExtentReport_"+timeStamp+".html";
         ExtentSparkReporter spark= new ExtentSparkReporter(report_path);
         spark.config().setReportName("Jenkins job Report");
         spark.config().setDocumentTitle("Test Report");
         extent= new ExtentReports();
         extent.attachReporter(spark);
     }
    }
    @BeforeClass
    public void setUpClass(ITestContext context) {
        ExtentTest test = extent.createTest(getClass().getSimpleName());
        testLog.set(test);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in/");
    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        ExtentTest test = testLog.get();
        if(result.getStatus()==ITestResult.FAILURE){
            ScreenshotUtils.TakeScreenshot(driver,result.getTestName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Skipped: " + result.getThrowable());
        }

    }
    @AfterClass
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
@AfterSuite
    public void afterSuite(){
        if(extent!=null){
            extent.flush();
        }
    }
}
