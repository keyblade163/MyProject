package keywords;

import com.aventstack.extentreports.Status;
import helpers.PropertiesHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import reports.extentreports.ExtentTestManager;
import utility.LogUtils;

import java.time.Duration;

public class WebActionUI {

    public static void waitForElementVisible(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static WebElement getWebElement(By by) {
        waitForElementVisible(by);
        return DriverManager.getDriver().findElement(by);
    }

    @Step("Click And Set Text {1} On Element: {0}")
    public static void setText(By by, String value) {
        waitForElementVisible(by);
        getWebElement(by).click();
        Actions action = new Actions(DriverManager.getDriver());
        action.sendKeys(value).build().perform();
//        getWebElement(by).sendKeys(value);
        LogUtils.info("Click And Set Text " + value + " on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Click And Set Text on element " + by);
    }

    @Step("Click On Element: {0}")
    public static void clickOnElement(By by) {
        scrollToElementWithJS(by);
        waitForElementVisible(by);
        getWebElement(by).click();
        LogUtils.info("Clicked on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Click on element " + by);
    }

    @Step("Clear Text on Element: {0}")
    public static void clearText(By by) {
        waitForPageLoaded();
        getWebElement(by).clear();
        LogUtils.info("Clear text on Element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Clear Text on element " + by);
    }

    @Step("Open URL: {0}")
    public static void openURL(String value) {
        DriverManager.getDriver().get(value);
        waitForPageLoaded();
        LogUtils.info("Open URL " + value);
        ExtentTestManager.logMessage(Status.PASS, "Open URL " + value);
    }

    public static void PressKeyEnter() {
        Actions action = new Actions(DriverManager.getDriver());
        action.sendKeys(Keys.ENTER).build().perform();
        LogUtils.info("Press Key Enter ");
        ExtentTestManager.logMessage(Status.PASS, "Press Key Enter");
    }

    public static void logConsole(String message) {
        System.out.println(message);
    }

    @Step("Get Text On Element: {0}")
    public static String getTextElement(By by) {
        waitForElementVisible(by);
        LogUtils.info("Get Text of Element " + by);
        LogUtils.info("=>>Text " + getWebElement(by).getText());
        ExtentTestManager.logMessage(Status.PASS, "Get Text on element " + by);
        return getWebElement(by).getText();

    }

    public static boolean verifyElementPresent(By by, int second) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(second), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Scroll to Element: {0}")
    public static void scrollToElementWithJS(By by) {
        //Dùng JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
        LogUtils.info("Scroll to element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Scroll to element " + by);
    }

    public static void waitForElementPresent(By by, int second) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(second));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    @Step("Clear text  on element Using Ctrl A: {0}")
    public static void clearTextCtrlA(By by) {
        waitForPageLoaded();
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(getWebElement(by)).build().perform();
        actions.click(getWebElement(by)).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
        actions.sendKeys(Keys.DELETE).build().perform();
        LogUtils.info("Clear text on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Clear Text on element With Ctrl A " + by);
    }

    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            LogUtils.info("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
