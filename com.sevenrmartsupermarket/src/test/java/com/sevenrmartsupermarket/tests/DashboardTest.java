package com.sevenrmartsupermarket.tests;

import org.testng.annotations.Test;

import com.sevenrmartsupermarket.base.base;
import com.sevenrmartsupermarket.pages.DashBoardPage;
import com.sevenrmartsupermarket.pages.LoginPage;

public class DashboardTest extends base {
	
	DashBoardPage dashboardpage;
	LoginPage loginpage;
	@Test
	public void verifyWhetherManagePagesCardIsClickable()
	{
		dashboardpage=new DashBoardPage(driver);
		loginpage=new LoginPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Manage Pages");
	}

}