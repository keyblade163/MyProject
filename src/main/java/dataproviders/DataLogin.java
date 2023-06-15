package dataproviders;

import helpers.ExcelHelper;
import helpers.Helpers;
import org.testng.annotations.DataProvider;

public class DataLogin {
    @DataProvider(name = "dataLoginVieon", parallel = true)
    public Object[][] dataLoginVieon() {
        return new Object[][]{{"VieonDpoint","Dpoint@2021"}, {"VieonDpoint","Dpoint@2021"}};
    }

    @DataProvider(name = "data_provider_login_excel")
    public Object[][] dataLoginHRMFromExcel() {
        ExcelHelper excelHelpers = new ExcelHelper();
        Object[][] data = excelHelpers.getExcelData(Helpers.getCurrentDir() + "src/test/resources/datatest/Login.xlsx", "Sheet1");
        System.out.println("Login Data from Excel: " + data);
        return data;
    }

//    @DataProvider(name = "data_provider_login_excel_custom_row")
//    public Object[][] dataLoginCRMFromExcelCustomRow() {
//        ExcelHelper excelHelpers = new ExcelHelper();
//        Object[][] data = excelHelpers.getDataHashTable(SystemsHelper.getCurrentDir() + "src/test/resources/datatest/Login.xlsx", "Login", 1, 2);
//        System.out.println("Login Data from Excel: " + data);
//        return data;
//    }
}
