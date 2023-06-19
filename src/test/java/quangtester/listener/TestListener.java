package quangtester.listener;

import com.aventstack.extentreports.Status;
import helpers.CaptureHelper;
import helpers.PropertiesHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.allurereport.AllureManager;
import reports.extentreports.ExtentReportManager;
import reports.extentreports.ExtentTestManager;
import utility.LogUtils;

public class TestListener implements ITestListener {
    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onFinish(ITestContext result) {
        LogUtils.info("End testing " + result.getName());
        ExtentReportManager.getExtentReports().flush();
    }

    @Override
    public void onStart(ITestContext result) {
        PropertiesHelper.loadAllFiles();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("Đây là test case bị fail: " + result.getName());
        CaptureHelper.captureScreenshot(result.getName());
        LogUtils.error(result.getThrowable().toString());
        CaptureHelper.stopRecord();

        ExtentTestManager.addScreenShot(result.getName());
        ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
        ExtentTestManager.logMessage(Status.FAIL, result.getName() + " is failed.");

        AllureManager.saveScreenshotPNG();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());
        CaptureHelper.stopRecord();
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("Đang chạy testcase " + result.getName());
        ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));
        CaptureHelper.startRecord(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("Đây là test case chạy thành công: " + result.getName());
        ExtentTestManager.logMessage(Status.PASS, result.getName() + " is passed.");
        CaptureHelper.stopRecord();

    }
}
