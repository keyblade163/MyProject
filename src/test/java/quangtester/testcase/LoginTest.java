package quangtester.testcase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import quangtester.common.BaseSetup;
import quangtester.helpers.PropertiesHelper;
import quangtester.pages.LoginPage;

public class LoginTest extends BaseSetup {
    LoginPage loginPage;
    @Test
    public void loginTestSuccess(){
        loginPage   = new LoginPage();
        loginPage.login(PropertiesHelper.getValue("email"),PropertiesHelper.getValue("password"));

    }
    @Test
    public void loginTestFailure(){
        loginPage = new LoginPage();
        loginPage.loginInvalid("VieonDpoint","1");
    }

}
