package quangtester.listener;

import helpers.CaptureHelper;
import helpers.PropertiesHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utility.LogUltils;

public class TestListener implements ITestListener {
    @Override
    public void onFinish(ITestContext result) {


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
        LogUltils.error("Đây là test case bị fail: " + result.getName());
        CaptureHelper.captureScreenshot(result.getName());
        LogUltils.error(result.getThrowable().toString());
    }

    @Override
    public void onTestSkipped(ITestResult result) {


    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUltils.info("Đang chạy testcase " + result.getName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUltils.info("Đây là test case chạy thành công: " + result.getName());

    }
}
