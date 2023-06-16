package quangtester.pages;

import static keywords.WebActionUI.*;

import keywords.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class EditVoucherPage {
    String voucherURL = "https://doanhnghiep-development.dpoint.vn/vieon-gift";

    By editVoucherButton = By.xpath("//div[@id='row-13']//child::i[@class='fa fa-pencil']");
    By voucherName = By.name("voucherName");
    By voucherID = By.name("voucherRefId");
    By pointsToRedeem = By.name("pointsToRedeem");
    By totalVouchers = By.name("totalVouchers");
    By voucherForm = By.name("voucherForm");
    By voucherValidity = By.name("voucherValidity");
    By switchActivityButton = By.xpath("//div[@class ='react-switch-bg']");
    By fromDate = By.xpath("//input[@placeholder='Từ ngày']");
    By chooseDateFrom = By.xpath("//div[@aria-label='Choose Tuesday, June 6th, 2023']");
    By toDate = By.xpath("//input[@placeholder='Đến ngày']");
    By chooseDateTo = By.xpath("//div[@aria-label='Choose Friday, June 30th, 2023']");
    By userManual = By.xpath("//html[@dir='ltr']");
    By saveVoucher = By.xpath("//button[normalize-space()='Lưu']");
    By alertMessage = By.xpath("//div[@role='alert']");

    public void clickEditGiftButton() {
       clickOnElement(editVoucherButton);
    }

    public void iFrameUserManual() {
        DriverManager.getDriver().switchTo().frame(1);
        scrollToElementWithJS(userManual);
        waitForElementPresent(userManual, 3);
        clearTextCtrlA(userManual);
        setText(userManual, "Hướng dẫn automation");
        DriverManager.getDriver().switchTo().parentFrame();
    }

    public void dateTimeHandle() {
        clickOnElement(fromDate);
        clickOnElement(chooseDateFrom);
        clickOnElement(toDate);
        clickOnElement(chooseDateTo);
    }
    public void verifySaveSuccess(){
        Assert.assertTrue(verifyElementPresent(alertMessage, 2),"Lưu không thành công");
    }

    public void editFirstVoucher() {
        sleep(3);
        clearTextCtrlA(voucherName);
        clearTextCtrlA(voucherID);
        clearTextCtrlA(pointsToRedeem);
        clearTextCtrlA(totalVouchers);
        clearTextCtrlA(voucherForm);
        clearTextCtrlA(voucherValidity);
        setText(voucherName, "Quà vieon number 3");
        setText(voucherID, "Giảm 100%");
        setText(pointsToRedeem, "10");
        setText(totalVouchers, "100");
        setText(voucherForm, "Dùng Điểm Đổi");
        setText(voucherValidity, "500 Ngày");
        clickOnElement(switchActivityButton);
        iFrameUserManual();
        dateTimeHandle();
        clickOnElement(saveVoucher);
        verifySaveSuccess();


    }


}
