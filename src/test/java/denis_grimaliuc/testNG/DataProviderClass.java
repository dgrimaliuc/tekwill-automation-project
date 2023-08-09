package denis_grimaliuc.testNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static helpers.Helpers.getExcelData;

public class DataProviderClass {


    //This method handles the excel - opens it and reads the data from the respective cells using a for-loop & returns it in the form of a string array
    @DataProvider(name = "excel-data")
    public Object[][] excelDP() {
        //We are creating an object from the excel sheet data by calling a method that reads data from the excel stored locally in our system
        Object[][] arrObj = getExcelData(System.getProperty("user.dir") + "/src/main/resources/dataProviders/DataProvider.xlsx", "Sheet_NR1");
        return arrObj;
    }

    @Test(dataProvider = "excel-data")
    public void test(String id, String fName, String lName, String age, String salary) {
        System.out.printf("Id=%s, fName=%s, lName=%s, Age=%s, Salary=%s\n", id, fName, lName, age, salary);
    }

}
