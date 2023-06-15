package quangtester.pages;

import static keywords.WebActionUI.*;

import keywords.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


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
    By toDate = By.xpath("//input[@placeholder='Đến ngày']");
    By userManual = By.xpath("//html[@dir='ltr']");
    By saveVoucher = By.xpath("//button[normalize-space()='Lưu']");

    public void clickEditGiftButton() {
        getWebElement(editVoucherButton).click();
    }
    public void iFrameUserManual(){
        DriverManager.getDriver().switchTo().frame(0);
        scrollToElementWithJS(userManual);
        waitForElementPresent(userManual, 3);
        clearTextCtrlA(userManual);
        setText(userManual,"Hướng dẫn automation");
        DriverManager.getDriver().switchTo().parentFrame();

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
        setText(voucherID,"Giảm 100%");
        setText(pointsToRedeem,"10");
        setText(totalVouchers,"100");
        setText(voucherForm,"Dùng Điểm Đổi");
        setText(voucherValidity,"500 Ngày");
        getWebElement(switchActivityButton).click();
        iFrameUserManual();
        getWebElement(saveVoucher).click();


    }


}
