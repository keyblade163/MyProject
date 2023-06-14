package quangtester.testcase;

import org.testng.annotations.Test;
import quangtester.common.BaseSetup;
import quangtester.pages.DashboardPage;
import quangtester.pages.EditVoucherPage;
import quangtester.pages.LoginPage;

import java.lang.reflect.Method;

public class EditVoucherTest extends BaseSetup {
    DashboardPage dashboardPage;
    LoginPage loginPage;
    EditVoucherPage editVoucherPage;

    @Test
    public void testEditVoucher() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.login("VieonDpoint", "Dpoint@2021");
        editVoucherPage = dashboardPage.openVieonVoucher();
        editVoucherPage.clickEditGiftButton();
        editVoucherPage.editFirstVoucher();


    }

}
