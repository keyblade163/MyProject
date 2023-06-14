package quangtester.pages;

import keywords.WebActionUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static keywords.WebActionUI.*;

public class DashboardPage {
    By breadCrumb = By.xpath("//li[normalize-space()='Trang chủ']");
    By sideBarTitle = By.xpath("//a[normalize-space()='Chương trình loyalty']");
    By vieonTier = By.xpath("//a[text()='Hạng thành viên']");
    By vieonEarningRule = By.xpath("//a[text()='Cơ chế tích điểm']");
    By vieonActivity = By.xpath("//a[text()='Hoạt động tích điểm']");
    By vieonExpiredPoints = By.xpath("//a[text()='Hạn sử dụng điểm']");
    By vieonVoucher = By.xpath("//a[text()='Quản lý quà tặng']");


    public void verifyBreadCrumb() {
        Assert.assertEquals(getTextElement(breadCrumb), "Trang chủ");

    }

    public void verifySideBarTitle() {
        Assert.assertEquals(getTextElement(sideBarTitle), "Chương trình loyalty");
    }

    public void clickSidebarElement() {
        getWebElement(sideBarTitle).click();
    }

    public void checkSidebarItem() {
        Assert.assertEquals(getTextElement(vieonTier), "Hạng thành viên");
    }

    public void clickVieonTier() {
        getWebElement(vieonTier).click();
    }

    public void clickVieonActivity() {
        getWebElement(vieonActivity).click();
    }

    public void clickVieonEarningRule() {
        getWebElement(vieonEarningRule).click();
    }

    public void clickVieonExpiredPoint() {
        getWebElement(vieonExpiredPoints).click();
    }

    public void clickVieonVoucher() {
        getWebElement(vieonVoucher).click();
    }

    public void verifyDashboardPage() {
        waitForPageLoaded();
        verifyBreadCrumb();
        verifySideBarTitle();
    }


    public void openVieonTier() {
        waitForPageLoaded();
        verifyBreadCrumb();
        verifySideBarTitle();
        clickSidebarElement();
        checkSidebarItem();
        clickVieonTier();
    }

    public void openVieonActivity() {
        waitForPageLoaded();
        verifyBreadCrumb();
        verifySideBarTitle();
        clickSidebarElement();
        checkSidebarItem();
        clickVieonActivity();
    }

    public void openVieonEarningRule() {
        waitForPageLoaded();
        verifyBreadCrumb();
        verifySideBarTitle();
        clickSidebarElement();
        checkSidebarItem();
        clickVieonEarningRule();
    }

    public void openVieonExpiredPoints() {
        waitForPageLoaded();
        verifyBreadCrumb();
        verifySideBarTitle();
        clickSidebarElement();
        checkSidebarItem();
        clickVieonExpiredPoint();
    }

    public EditVoucherPage openVieonVoucher() {
        waitForPageLoaded();
        verifyBreadCrumb();
        verifySideBarTitle();
        clickSidebarElement();
        checkSidebarItem();
        clickVieonVoucher();
        return new EditVoucherPage();
    }

}
