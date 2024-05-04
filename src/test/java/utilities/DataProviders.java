package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {

		String path = ".\\TestData\\Opencart_LoginData.xlsx";

		ExcelUtility exUtils = new ExcelUtility(path);

		int totalrows = exUtils.getRowCount("Sheet1");
		int totalcell = exUtils.getCellCount("Sheet1", 1);

		String loginData[][] = new String[totalrows][totalcell];
		for (int i = 1; i <= totalrows; i++) {
			for (int j = 0; j < totalcell; j++) {
				loginData[i-1][j]=exUtils.getCellData("Sheet1", i, j);
			}
		}
		return loginData;

	}
}
