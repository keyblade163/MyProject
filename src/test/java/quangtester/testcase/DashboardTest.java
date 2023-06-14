package quangtester.testcase;

import org.testng.annotations.Test;
import quangtester.common.BaseSetup;
import quangtester.pages.DashboardPage;
import quangtester.pages.LoginPage;

public class DashboardTest extends BaseSetup {
    DashboardPage dashboardPage;
    LoginPage loginPage;

    @Test
    public void openSideBarMenu(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.login("VieonDpoint","Dpoint@2021");
        dashboardPage.verifyDashboardPage();
    }

    @Test
    public void openVieonTier(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.login("VieonDpoint","Dpoint@2021");
        dashboardPage.openVieonTier();
    }

}
