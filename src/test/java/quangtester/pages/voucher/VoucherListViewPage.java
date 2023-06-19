package quangtester.pages.voucher;

import keywords.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static keywords.WebActionUI.PressKeyEnter;
import static keywords.WebActionUI.getWebElement;

public class VoucherListViewPage {
    By searchGift = By.xpath("//input[@placeholder='Nhập nội dung tìm kiếm']");

    public void searchEditGift() {
        getWebElement(searchGift).sendKeys("voucher");
        PressKeyEnter();
    }

    public void checkSearchTableByColumn(int column, String value) {
        searchEditGift();
        //Xác định số dòng của table sau khi search
        List<WebElement> row = DriverManager.getDriver().findElements(By.xpath("//div[@class='sc-ikkxIA ipBIwE rdt_TableBody']/child::div"));
        int rowTotal = row.size(); //Lấy ra số dòng
        System.out.println("Số dòng tìm thấy: " + rowTotal);

        //Duyệt từng dòng
        for (int i = 1; i <= rowTotal; i++) {
            WebElement elementCheck = DriverManager.getDriver().findElement(By.xpath("//div[@class='sc-ikkxIA ipBIwE rdt_TableBody']/child::div[" + i + "]/div[" + column + "]"));

            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].scrollIntoView(true);", elementCheck);

            System.out.print(value + " - ");
            System.out.println(elementCheck.getText());
            Assert.assertTrue(elementCheck.getText().contains(value), "Dòng số " + i + " không chứa giá trị tìm kiếm.");
        }
    }
}

