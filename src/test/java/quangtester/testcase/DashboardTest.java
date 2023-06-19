package quangtester.testcase;

import dataproviders.DataLogin;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import quangtester.common.BaseSetup;
import quangtester.pages.DashboardPage;
import quangtester.pages.LoginPage;

public class DashboardTest extends BaseSetup {
    DashboardPage dashboardPage;
    LoginPage loginPage;

    @Test(dataProvider = "data_provider_login_excel", dataProviderClass = DataLogin.class)
    public void openSideBarMenu(String value1, String value2){
        loginPage = new LoginPage();
        dashboardPage = loginPage.login(value1, value2);
        dashboardPage.verifyDashboardPage();
    }

    @Test(dataProvider = "dataLoginVieon", dataProviderClass = DataLogin.class)
    public void openVieonTier(String Username, String Password){
        loginPage = new LoginPage();
        dashboardPage = loginPage.login(Username,Password);
        dashboardPage.openVieonTierMenu();
    }

}
