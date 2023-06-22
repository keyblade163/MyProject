package quangtester.pages;

import static keywords.WebActionUI.*;

import org.openqa.selenium.By;

public class EditTierPage {
    String URL = "https://doanhnghiep-development.dpoint.vn/vieon-tier";
    By editRow1Tier = By.xpath("//div[@id='row-1']//i[@class='fa fa-pencil']");
    By tierName = By.name("name");
    By tierLevel = By.name("level");
    By tierMinPoint = By.name("minPoint");
    By tierMaxPoint = By.name("maxPoint");
    By tierBenefitDescription = By.name("//input[@placeholder='Nhập quyền lợi hạng thành viên']");
    By addNewTierBenefit = By.xpath("//span[@class='pl-1']");

    public void createNewTier() {
        clickOnElement(editRow1Tier);
    }

//Không lấy được locater nên bó tay ;___;

}
