package keywords;

import helpers.PropertiesHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utility.LogUltils;

import javax.print.DocFlavor;
import java.sql.Driver;
import java.time.Duration;

public class WebActionUI {

    public static void waitForElementVisible(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static WebElement getWebElement(By by) {
        waitForElementVisible(by);
        return DriverManager.getDriver().findElement(by);
    }

    public static void setText(By by, String value) {
//        waitForElementVisible(by);
        getWebElement(by).click();
        Actions action = new Actions(DriverManager.getDriver());
        action.sendKeys(value).build().perform();
//        getWebElement(by).sendKeys(value);
        LogUltils.info("Set text " + value + " on element " + by);
    }

    public static void clickOnElement(By by) {
        scrollToElementWithJS(by);
        waitForElementVisible(by);
        getWebElement(by).click();
        LogUltils.info("Clicked on element " + by);
    }

    public static void clearText(By by) {
        waitForPageLoaded();
        getWebElement(by).clear();
        LogUltils.info("Clear text on Element" + by);
    }

    public static void openURL(String value) {
        DriverManager.getDriver().get(PropertiesHelper.getValue(value));
    }

    public static void logConsole(String message) {
        System.out.println(message);
    }

    public static String getTextElement(By by) {
        waitForElementVisible(by);
        LogUltils.info("Get Text of Element " + by);
        LogUltils.info("=>>Text " + getWebElement(by).getText());
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

    public static void scrollToElementWithJS(By by) {
        //Dùng JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
        LogUltils.info("Scroll to element " + by);
    }

    public static void waitForElementPresent(By by, int second) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(second));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }


    public static void clearTextCtrlA(By by) {
        waitForPageLoaded();
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(getWebElement(by)).build().perform();
        actions.click(getWebElement(by)).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
        actions.sendKeys(Keys.DELETE).build().perform();
        LogUltils.info("Clear text on element " + by);
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
            LogUltils.info("Javascript is NOT Ready.");
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
