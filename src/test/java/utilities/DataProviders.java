package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public Object[][] getData() throws IOException{
		String path= ".\\testData\\TutorialNinja_logintest.xlsx";
		ExcelUtility xlutil= new ExcelUtility(path);
		int totalRows= xlutil.getRowCount("Sheet1");
		int totalCols= xlutil.getCellCount("Sheet1", 1);
		
		Object[][] data = new Object[totalRows][totalCols]; //created 2D array to store data

       
        for (int i = 1; i <= totalRows; i++) { // i=1 se start kiya header skip karne ke liye
            for (int j = 0; j < totalCols; j++) {
                data[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
            }
        }
        
        return data; // Ye data Selenium test ko milega
    }
		
}
	
	/* this is the sample code which is created above for accessing excel data using data provider
	 
	public class DataProviders {

	    @DataProvider(name = "LoginData")
	    public Object[][] getAllData() throws IOException {
	        String path = System.getProperty("user.dir") + "/src/test/resources/testdata/Data.xlsx";
	        String sheetName = "Sheet1";

	        // 1. Excel file setup karein
	        ExcelUtils.setExcelFile(path, sheetName);

	        int totalRows = ExcelUtils.getRowCount();
	        int totalCols = ExcelUtils.getColumnCount(1); // Row 1 se column count lein

	        // 2. 2D Array banayein (Rows - 1 kyunki header nahi chahiye)
	        Object[][] data = new Object[totalRows][totalCols];

	        // 3. Loop chalakar data read karein
	        for (int i = 1; i <= totalRows; i++) { // i=1 se start kiya header skip karne ke liye
	            for (int j = 0; j < totalCols; j++) {
	                data[i - 1][j] = ExcelUtils.getCellData(i, j);
	            }
	        }
	        
	        return data; // Ye data Selenium test ko milega
	    }
	}
	*/


