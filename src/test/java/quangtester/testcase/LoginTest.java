package quangtester.testcase;

import dataproviders.DataLogin;
import helpers.ExcelHelper;
import org.testng.annotations.Test;
import quangtester.common.BaseSetup;
import quangtester.pages.LoginPage;

public class LoginTest extends BaseSetup {
    LoginPage loginPage;

    @Test
    public void loginTestSuccessExcel() {
        loginPage = new LoginPage();
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/datatest/Login.xlsx","Sheet1");
        loginPage.login(excelHelper.getCellData("EMAIL", 1),excelHelper.getCellData("PASSWORD",1));
    }

    @Test(dataProvider = "dataLoginVieon", dataProviderClass = DataLogin.class)
    public void loginTestDataProvider(String Username, String Password) {
        loginPage = new LoginPage();
        loginPage.login(Username, Password);
    }

    @Test
    public void loginTestExcel() {
        loginPage = new LoginPage();
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/datatest/Login.xlsx", "Sheet1");
        loginPage.login(excelHelper.getCellData("EMAIL", 1), "Dpoint@2021");
    }

    @Test(dataProvider = "data_provider_login_excel", dataProviderClass = DataLogin.class)
    public void loginTestDataProviderExcel(String Username, String Password) {
        loginPage = new LoginPage();
        loginPage.login(Username, Password);
    }


    @Test
    public void loginTestFailure() {
        loginPage = new LoginPage();
        loginPage.loginInvalid("VieonDpoint", "1");
    }

}
