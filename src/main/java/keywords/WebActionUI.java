package keywords;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
        waitForElementVisible(by);
        getWebElement(by).click();
        getWebElement(by).sendKeys(value);
        logConsole("Set text " + value + " on element " + by);
    }

    public static void clearText(By by) {
        waitForPageLoaded();
        getWebElement(by).clear();
        logConsole("Clear text on Element" + by);
    }

    public static void logConsole(String message) {
        System.out.println(message);
    }

    public static String getTextElement(By by) {
        waitForElementVisible(by);
        logConsole("Get Text of Element " + by);
        logConsole("=>>Text " + getWebElement(by).getText());
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
        logConsole("Scroll to element " + by);
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
        logConsole("Clear text on element " + by);
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
            System.out.println("Javascript is NOT Ready.");
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
