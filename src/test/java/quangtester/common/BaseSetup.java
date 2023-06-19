package quangtester.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import keywords.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;
import helpers.PropertiesHelper;
import quangtester.listener.TestListener;
import utility.LogUtils;
@Listeners(TestListener.class)
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
        LogUtils.info("Launching Chrome Browser...");

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;

    }

    private static WebDriver initEdgeDriver() {
        LogUtils.info("Launching Edge Browser...");
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    @AfterMethod
    public void closeDriver() {
        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
        }
    }
}
