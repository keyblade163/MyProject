package quangtester.testcase;

import dataproviders.DataLogin;
import helpers.ExcelHelper;
import org.testng.annotations.Test;
import quangtester.common.BaseSetup;
import quangtester.pages.LoginPage;

public class LoginTest extends BaseSetup {
    LoginPage loginPage;
    @Test(dataProvider = "dataLoginVieon", dataProviderClass = DataLogin.class)
    public void loginTestSuccess(String Username, String Password) {
        loginPage   = new LoginPage();
       // loginPage.login(PropertiesHelper.getValue("email"),PropertiesHelper.getValue("password"));
        loginPage.login(Username, Password);
    }

    @Test
    public void loginTestExcel(){
        loginPage = new LoginPage();
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/datatest/Login.xlsx","Sheet1");
        loginPage.login(excelHelper.getCellData("EMAIL",1),"Dpoint@2021");
    }

    @Test(dataProvider = "data_provider_login_excel", dataProviderClass = DataLogin.class)
    public void loginTestSuccess3(String Username, String Password) {
        loginPage   = new LoginPage();
        // loginPage.login(PropertiesHelper.getValue("email"),PropertiesHelper.getValue("password"));
        loginPage.login(Username, Password);

    }


    @Test
    public void loginTestFailure(){
        loginPage = new LoginPage();
        loginPage.loginInvalid("VieonDpoint","1");
    }

}
