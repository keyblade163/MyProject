package quangtester.common;

import helpers.CaptureHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import keywords.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import helpers.PropertiesHelper;

public class BaseSetup {
    public static WebDriver driver;

    @BeforeMethod
    @Parameters({"Browser"})
    public void createDriver(@Optional("Chrome") String browserName) {
        WebDriver driver = setupBrowser(browserName);
        PropertiesHelper.loadAllFiles();
        DriverManager.setDriver(driver);
    }

    public static WebDriver setupBrowser(String browserName) {
        WebDriver driver;
        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
            default:
                driver = initChromeDriver();
        }
        return driver;
    }

    private static WebDriver initChromeDriver() {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;

    }

    private static WebDriver initEdgeDriver() {
        WebDriver driver;
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    @AfterMethod
    public void closeDriver(ITestResult iTestResult) {
        if(iTestResult.getStatus() == ITestResult.FAILURE){
            CaptureHelper.captureScreenshot(iTestResult.getName());
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
        }
    }
}
