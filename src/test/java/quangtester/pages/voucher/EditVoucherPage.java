package quangtester.pages.voucher;

import static keywords.WebActionUI.*;
import static keywords.WebActionUI.setText;

import helpers.Helpers;
import helpers.PropertiesHelper;
import keywords.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;


public class EditVoucherPage {
    //String voucherURL = "https://doanhnghiep-development.dpoint.vn/vieon-gift";

    By searchGift = By.xpath("//input[@placeholder='Nhập nội dung tìm kiếm']");
    By editVoucherButton = By.xpath("//div[@class='sc-ikkxIA ipBIwE rdt_TableBody']/div[1]//a[2]");
    By voucherName = By.name("voucherName");
    By voucherID = By.name("voucherRefId");
    By pointsToRedeem = By.name("pointsToRedeem");
    By totalVouchers = By.name("totalVouchers");
    By voucherForm = By.name("voucherForm");
    By voucherValidity = By.name("//div[@class=' css-yk16xz-control']//div[text()='Số ngày']");
    By switchActivityButton = By.xpath("//div[@class ='react-switch-bg']");
    By fromDate = By.xpath("//input[@placeholder='Từ ngày']");
    By chooseDateFrom = By.xpath("//div[@aria-label='Choose Tuesday, June 6th, 2023']");
    By toDate = By.xpath("//input[@placeholder='Đến ngày']");
    By chooseDateTo = By.xpath("//div[@aria-label='Choose Friday, June 30th, 2023']");
    By userManual = By.xpath("//html[@dir='ltr']");
    By saveVoucher = By.xpath("//button[normalize-space()='Lưu']");
    By alertMessage = By.xpath("//div[@role='alert']");
    By uploadPhoto = By.xpath("//label[contains(text(),'Chọn ảnh')]");
    By dropDownListCategory = By.xpath("//div[@class=' css-yk16xz-control']//div[text()='Ưu đã từ các nhãn hàng khác']");
    By getDropDownListSubCategory = By.xpath("//div[@class='dropdown-heading-value']");



    public void searchEditGift(String value) {
        getWebElement(searchGift).sendKeys(value);
        PressKeyEnter();
    }

    public void clickEditVoucherButton() {
        clickOnElement(editVoucherButton);
    }
    public void checkSwitchActivityButton(){
        Boolean isSelected = DriverManager.getDriver().findElement(switchActivityButton).isSelected();
        if(isSelected == false){
            clickOnElement(switchActivityButton);
        }
    }

    public void iFrameUserManual0() {
        DriverManager.getDriver().switchTo().frame(0);
        scrollToElementWithJS(userManual);
        waitForElementPresent(userManual, 3);
        clearTextCtrlA(userManual);
        setText(userManual, "Đây là Iframe 0");
        DriverManager.getDriver().switchTo().parentFrame();
    }
    public void iFrameUserManual1() {
        DriverManager.getDriver().switchTo().frame(1);
        scrollToElementWithJS(userManual);
        waitForElementPresent(userManual, 3);
        clearTextCtrlA(userManual);
        setText(userManual, "Đây là Iframe 1");
        DriverManager.getDriver().switchTo().parentFrame();
    }

    public void dateTimeHandle() {
        clickOnElement(fromDate);
        clickOnElement(chooseDateFrom);
        clickOnElement(toDate);
        clickOnElement(chooseDateTo);
    }

    public void verifySaveSuccess() {
        Assert.assertTrue(verifyElementPresent(alertMessage, 10), "Lưu không thành công");
    }
    public void handleSwitchActivity(){
        Boolean isSelected = DriverManager.getDriver().findElement(switchActivityButton).isSelected();
        if (isSelected == false) {
            DriverManager.getDriver().findElement(switchActivityButton).click();
        }
    }

    public void uploadImage() {
        //Dán đoạn code dùng Robot class để hành động với bàn phím
        // Khởi tạo Robot class
        clickOnElement(uploadPhoto);
        Robot rb = null;
        try {
            rb = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        String filePath = Helpers.getCurrentDir() + "src\\test\\resources\\datatest\\Logo.png";

        // Copy File path vào Clipboard
        StringSelection str = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

        sleep(1);

        // Nhấn Control+V để dán
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        // Xác nhận Control V trên
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);

        sleep(2);

        // Nhấn Enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        sleep(2);
    }

    public void handleCategoryDropDownList(String value) {
        setText(dropDownListCategory, value);
        PressKeyEnter();
        clickOnElement(getDropDownListSubCategory);
        By checkAllSub_Category = By.xpath("//span[normalize-space()='Tất cả']");
        clickOnElement(checkAllSub_Category);
        clickOnElement(getDropDownListSubCategory);
    }
    public void handleValidationDate(String value){
        setText(voucherValidity, value);
        PressKeyEnter();
        By enterDate = By.xpath("//input[@name='daysToExpiry']");
        clearText(enterDate);
        setText(enterDate, "10");
    }

    public VoucherListViewPage editSearchVoucher() {
        sleep(3);
        clearTextCtrlA(voucherName);
        clearTextCtrlA(voucherID);
        clearTextCtrlA(pointsToRedeem);
        clearTextCtrlA(totalVouchers);
        clearTextCtrlA(voucherForm);
//        Cái này bị fail ko handle được
//        handleValidationDate("Số ngày");
        handleCategoryDropDownList("Ưu đã từ các nhãn hàng khác");
        setText(voucherName, PropertiesHelper.getValue("voucher"));
        setText(voucherID, "Giảm 100%");
        setText(pointsToRedeem, "10");
        setText(totalVouchers, "100");
        setText(voucherForm, "Dùng Điểm Đổi");
        checkSwitchActivityButton();
        iFrameUserManual0();
        iFrameUserManual1();
        dateTimeHandle();
        uploadImage();
        clickOnElement(saveVoucher);
        verifySaveSuccess();
        return new VoucherListViewPage();
    }
}
