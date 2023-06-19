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
        editVoucherPage = dashboardPage.openVieonVoucherMenu();
        editVoucherPage.searchEditGift("Quà vieon number 3");
        editVoucherPage.clickEditGiftButton();
        voucherListViewPage = editVoucherPage.editFirstVoucher();
        voucherListViewPage.checkSearchTableByColumn(2,"voucher");
    }
}

