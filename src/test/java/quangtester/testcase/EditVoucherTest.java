package quangtester.testcase;

import org.testng.annotations.Test;
import quangtester.common.BaseSetup;
import quangtester.pages.DashboardPage;
import quangtester.pages.voucher.EditVoucherPage;
import quangtester.pages.LoginPage;
import quangtester.pages.voucher.VoucherListViewPage;

public class EditVoucherTest extends BaseSetup {
    DashboardPage dashboardPage;
    LoginPage loginPage;
    EditVoucherPage editVoucherPage;
    VoucherListViewPage voucherListViewPage;

    @Test
    public void testEditVoucher() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.login("VieonDpoint", "Dpoint@2021");
        voucherListViewPage = dashboardPage.openVieonVoucherMenu();
        editVoucherPage = voucherListViewPage.searchEditGift();
        editVoucherPage.clickEditVoucherButton();
        editVoucherPage.editSearchVoucher();
        voucherListViewPage.checkSearchTableByColumn(1, "Quà vieon number 3");
    }
@Test
    public void checkNumberOfVoucher() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.login("VieonDpoint", "Dpoint@2021");
        voucherListViewPage = dashboardPage.openVieonVoucherMenu();
        voucherListViewPage.checkSearchTableByColumn(2,"Mã giảm giá 30.000");


    }
}

