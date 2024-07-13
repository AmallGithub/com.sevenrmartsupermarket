package DataProviders;

import org.testng.annotations.DataProvider;

import com.sevenrmartsupermarket.utilities.ExcelReader;

public class Dataproviders 
{
	ExcelReader excelreader=new ExcelReader();
	@DataProvider(name = "credentials")
	public Object[][] enterCredentials() {
		excelreader.setExcelFile("Logindata", "new");
		return excelreader.getMultidimentionalData(2, 3);
}
}
