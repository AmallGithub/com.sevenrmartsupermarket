package com.sevenrmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmartsupermarket.listeners.RetryAnalzer;
import com.sevenrmartsupermarket.base.base;
import com.sevenrmartsupermarket.pages.AdminUsersPage;
import com.sevenrmartsupermarket.pages.DashBoardPage;
import com.sevenrmartsupermarket.pages.LoginPage;
import com.sevenrmartsupermarket.utilities.GeneralUtility;

public class AdminUsersTest extends base {

	LoginPage loginpage;
	DashBoardPage dashboardpage;
	AdminUsersPage adminuserspage;
	
	@Test(groups="sanity")
	public void verifyAdminUsersTitle() {
		loginpage = new LoginPage(driver);
		dashboardpage = new DashBoardPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		String actualTitle = adminuserspage.getAdminUsersTitle();
		String expecetdTitle = "Admin Users";
		Assert.assertEquals(actualTitle, expecetdTitle);
	}

	@Test(retryAnalyzer = RetryAnalzer.class)
	public void verifySuccessfulNewAdminUserCreation() {
		loginpage = new LoginPage(driver);
		dashboardpage = new DashBoardPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		adminuserspage.clickOnNewButton();
		adminuserspage.createNewAdminUser("Harish4567845", "harish@12673567", "admin");
		String actualAlertMsg = adminuserspage.getSuccessAlertMessage();
		System.out.println(actualAlertMsg);
		String expectedAlertMsg = "User Created Successfully";
		Assert.assertFalse(actualAlertMsg.contains(expectedAlertMsg));
		adminuserspage.clickOnAlertMsgCloseBtn();
		adminuserspage.deleteNewUser();
	}

	@Test(groups = {"sanity","regression"})
	public void verifySuccessfulDeletionofNewAdminUser() {
		loginpage = new LoginPage(driver);
		dashboardpage = new DashBoardPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		adminuserspage.clickOnNewButton();
		adminuserspage.createNewAdminUser("Harish4567845", "harish@12673567", "admin");
		adminuserspage.clickOnAlertMsgCloseBtn();
		adminuserspage.deleteNewUser();
		String actualSuccessDltMsg = adminuserspage.getSuccessAlertMessage();
		String expectedSuccessDltMsg = "User Deleted Successfully";
		Assert.assertTrue(actualSuccessDltMsg.contains(expectedSuccessDltMsg));
		adminuserspage.clickOnAlertMsgCloseBtn();

	}

	@Test
	public void verifyUnsuccessfulNewAdminUserCreation() {
		loginpage = new LoginPage(driver);
		dashboardpage = new DashBoardPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		adminuserspage.clickOnNewButton();
		adminuserspage.createNewAdminUser("Hari", "hari@123", "admin");
		String actualAlertMsg = adminuserspage.getFailedAlertMessage();
		System.out.println(actualAlertMsg);
		String expectedAlertMsg = "Username already exists.";
		Assert.assertTrue(actualAlertMsg.contains(expectedAlertMsg));
		adminuserspage.clickOnAlertMsgCloseBtn();
	}

	@Test
	    public void verifyUserTypeofNewUser() {
		loginpage = new LoginPage(driver);
		dashboardpage = new DashBoardPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		adminuserspage.clickOnNewButton();
		String username="Harish"+GeneralUtility.get_RandomFirstName();
		adminuserspage.createNewAdminUser("username", "harish@12673567", "admin");
		String actualUserType = adminuserspage.getUserTypeofaPerson("Harish4567845");
		String expecetedUserType = "admin";
		Assert.assertEquals(actualUserType, expecetedUserType);
		
	}
	
	
	
	

	@Test
	public void verifyTableStatusElementofAUserIsClickable() {
		loginpage = new LoginPage(driver);
		dashboardpage = new DashBoardPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		adminuserspage.clickOnNewButton();
		adminuserspage.createNewAdminUser("Harish4567845", "harish@12673567", "admin");
		boolean status = adminuserspage.statusOfTableElementIsClickable("Harish4567845");
		Assert.assertTrue(status);
		adminuserspage.deleteNewUser();

	}

	@Test
	public void verifyUserDetailIsUpdatedSuccessfully() {
		String userName = "athira";
		String newUserType = "admin";
		loginpage = new LoginPage(driver);
		dashboardpage = new DashBoardPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		String actualUserType = adminuserspage.getUserTypeofaPerson(userName);
		if (!actualUserType.equals(newUserType)) {
			adminuserspage.clickOnAUserUpdateBtnInTable(userName);
			adminuserspage.selectUserTypeAndUpdate(newUserType);
			String actualAlertMsg = adminuserspage.getSuccessAlertMessage();
			String expectedAlertMsg = "User Updated Successfully";
			Assert.assertTrue(actualAlertMsg.contains(expectedAlertMsg));
			adminuserspage.clickOnAlertMsgCloseBtn();
		} else {
			System.out.println("Actual Usertype and new Usertype are same");
		}

	}
	
	@Test
	public void verifySuccessfullDeletionofAUser()
	{
		loginpage = new LoginPage(driver);
		dashboardpage = new DashBoardPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		adminuserspage.clickOnNewButton();
		adminuserspage.createNewAdminUser("Harish4567845", "harish@12673567", "admin");
		adminuserspage.deleteAUser("Harish4567845");
		String actualSuccessDltMsg = adminuserspage.getSuccessAlertMessage();
		String expectedSuccessDltMsg = "User Deleted Successfully";
		Assert.assertTrue(actualSuccessDltMsg.contains(expectedSuccessDltMsg));
		adminuserspage.clickOnAlertMsgCloseBtn();
	}

}