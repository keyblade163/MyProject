package quangtester.pages;

import keywords.WebActionUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static keywords.WebActionUI.*;

public class CreateNewTier {
    By createTierButton = By.xpath("//div[@class='col-sm-12 col-lg-12']//button[@type='button']");
    By breadCrumb = By.xpath("//li[text()='Danh sách hạng thành viên']");
    By columnName = By.xpath("//div[text()='Tên hạng thành viên']");
    By columnTier = By.xpath("//div[text()='Cấp hạng']");
    By columnAction = By.xpath("//div[text()='Thao tác']");

   public void verifyTierPage(){
        Assert.assertEquals(getTextElement(createTierButton),"Tạo hạng");
   }

   public void clickButtonCreate(){
        getWebElement(createTierButton).click();
   }

}
