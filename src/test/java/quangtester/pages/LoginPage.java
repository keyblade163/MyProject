package quangtester.pages;

import keywords.DriverManager;
import org.openqa.selenium.By;
import org.testng.Assert;
import helpers.PropertiesHelper;

import static keywords.WebActionUI.*;

public class LoginPage {
    //private String URL = "https://doanhnghiep-development.dpoint.vn/login";


    //Lưu object
//Dùng đối tượng by
    By headerPage = By.xpath("//h1[normalize-space()='Đăng nhập']");
    By inputEmail = By.xpath("//input[@placeholder='Nhập tên đăng nhập']");
    By inputPassword = By.xpath("//input[@placeholder ='Mật khẩu']");
    By buttonLogin = By.xpath("//button[text()='Đăng nhập']");
    By errorToastMessage = By.xpath("//div[@role='alert']");



    //Tạo hàm xử lý
    public void verifyHeaderPage() {
        Assert.assertEquals(getTextElement(headerPage), "Đăng nhập");
    }

    public void enterEmail(String email) {
        setText(inputEmail, email);
    }

    public void enterPassword(String password) {
       setText(inputPassword, password);
    }

    public void clickButtonLogin() {
        waitForElementVisible(buttonLogin);
        getWebElement(buttonLogin).click();
    }

    public void verifyErrorToastMessage() {
        waitForElementVisible(errorToastMessage);
        Assert.assertTrue(getWebElement(errorToastMessage).isDisplayed());
        Assert.assertEquals(getTextElement(errorToastMessage),"Tài khoản hoặc mật khẩu không chính xác. Xin vui lòng thử lại");
    }

    public DashboardPage login(String email, String password) {
            DriverManager.getDriver().get(PropertiesHelper.getValue("url"));
            verifyHeaderPage();
            enterEmail(email);
            enterPassword(password);
            clickButtonLogin();
            Assert.assertTrue(!verifyElementPresent(errorToastMessage, 5), "Login không thành công");
            return new DashboardPage();
    }

    public void loginInvalid(String email, String password) {
        openURL("url");
       // DriverManager.getDriver().get(PropertiesHelper.getValue("url"));
        verifyHeaderPage();
        enterEmail(email);
        enterPassword(password);
        clickButtonLogin();
        Assert.assertTrue(!verifyElementPresent(errorToastMessage, 5), "Login không thành công");
        verifyErrorToastMessage();
    }


}



