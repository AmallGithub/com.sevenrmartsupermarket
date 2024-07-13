package com.sevenrmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.sevenrmartsupermarket.base.base;
import com.sevenrmartsupermarket.pages.DashBoardPage;
import com.sevenrmartsupermarket.pages.LoginPage;
import com.sevenrmartsupermarket.utilities.ExcelReader;
import com.sevenrmartsupermarket.utilities.ScreenshotCapture;

import DataProviders.Dataproviders;

public class LoginTest extends base {
	
	LoginPage loginpage;
	DashBoardPage dashboardpage;
	ExcelReader excelreader=new ExcelReader();
	
	@Test(dataProvider = "credentials",dataProviderClass = Dataproviders.class)
	public void verifyLogin(String username,String password,String profilename)
	{
		ScreenshotCapture screenshotcapture=new ScreenshotCapture();
		screenshotcapture.takeScreenshot(driver, "Lakshmi");
		
//		excelreader.setExcelFile("Logindata", "credentials");
//		String userName=excelreader.getCellData(5, 0);
//		String password=excelreader.getCellData(5, 1);
////		
//		System.out.println(userName);
//		System.out.println(password);
		
		loginpage=new LoginPage(driver);
		dashboardpage=new DashBoardPage(driver);
	//	loginpage.login();
		loginpage.login(username, password);
		String actualProfileName=dashboardpage.getProfileName();
		System.out.println(actualProfileName);
	//	String expecetedProfileName="Admin";
		Assert.assertEquals(actualProfileName, profilename);
		
	}
	@Test()
	public void verifyInvalidLogin()
	{
		 
//		excelreader.setExcelFile("Logindata", "credentials");
//		String userName=excelreader.getCellData(1, 0);
//		String password=excelreader.getCellData(1, 1);
		
		loginpage=new LoginPage(driver);
		loginpage.login();
		
//		ScreenshotCapture screenshotcapture=new ScreenshotCapture();
//		screenshotcapture.takeScreenshot(driver, "New1");
		
		String actualAlertMessage=loginpage.getInvalidAlertMessage();
		System.out.println(actualAlertMessage);
		String expectedAlertMessage="Alert!";
		Assert.assertTrue(actualAlertMessage.contains(expectedAlertMessage));
	}
	

}