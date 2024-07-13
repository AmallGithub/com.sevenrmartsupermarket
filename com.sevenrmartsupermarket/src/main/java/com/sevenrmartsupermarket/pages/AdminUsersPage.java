package com.sevenrmartsupermarket.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermarket.utilities.GeneralUtility;
import com.sevenrmartsupermarket.utilities.PageUtility;
import com.sevenrmartsupermarket.utilities.WaitUtility;

public class AdminUsersPage {

	WebDriver driver;

	PageUtility pageutility = new PageUtility(driver);
	WaitUtility waitutility=new WaitUtility(driver);
	GeneralUtility generalutility=new GeneralUtility();

	@FindBy(xpath = "//div[@class=\"content-header\"]//h1")
	WebElement adminUserHeader;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-danger']")
	WebElement newButton;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-primary']")
	WebElement searchButton;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-warning']")
	WebElement resetButton;
	@FindBy(xpath = "//label[@for='username']//following::input[@id='username']")
	WebElement userNameField;
	@FindBy(xpath = "//label[@for='password']//following::input[@id='password']")
	WebElement passwordField;
	@FindBy(xpath = "//label[@for='user_type']//following::select[@id='user_type']")
	WebElement userTypeSelectDrpDown;
	@FindBy(xpath = "//div[@class='card-footer center']//following::button[@type='submit']")
	WebElement saveButton;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement successAlertMsgBox;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	WebElement failedAlertMsgBox;
	@FindBy(xpath = "//button[@data-dismiss='alert']")
	WebElement alertMsgBoxCloseBtn;
	@FindBy(xpath="//table[@class='table table-bordered table-hover table-sm']//tbody//tr[1]//td[5]//a[@class='btn btn-sm btn btn-danger btncss']")
	WebElement firstTContntDeleteBtn;
	@FindBy(xpath="//table[@class='table table-bordered table-hover table-sm']//tbody//tr//td[1]")
	List<WebElement> tableNames;
	@FindBy(xpath="//table[@class='table table-bordered table-hover table-sm']//tbody//tr//td[3]")
	List<WebElement> tableStatuses;
	@FindBy(xpath="//table[@class='table table-bordered table-hover table-sm']//tbody//tr//td[5]//a[@class='btn btn-sm btn btn-primary btncss']")
	List<WebElement> tableUpdateBtns;
	@FindBy(xpath="//button[@class='btn btn-block-sm btn-info' and @name='Update']")
	WebElement updateButton;
	

	public AdminUsersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getAdminUsersTitle() {
		return adminUserHeader.getText();
	}

	public void clickOnNewButton() {
		newButton.click();
	}

	public void enterUsername(String userName) {
		userNameField.sendKeys(userName);
	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}

	public void selectUserType(String value) {
		pageutility.select_Value(userTypeSelectDrpDown, value);
	}

	public void clickOnSaveBtn() {
		saveButton.click();
	}

	public String getSuccessAlertMessage() {

		// waitutility.waitForvisibilityOfElement("//div[@class='alert alert-success
		// alert-dismissible']", 10);
		return successAlertMsgBox.getText();
	}

	public String getFailedAlertMessage() {
		// waitutility.waitForvisibilityOfElement("//div[@class='alert alert-danger
		// alert-dismissible']", 10);
		return failedAlertMsgBox.getText();
	}

	public void clickOnAlertMsgCloseBtn() {
		// waitutility.waitForElementToBeClickable(alertMsgBoxCloseBtn, 10);
		alertMsgBoxCloseBtn.click();

	}
	public void clickOnUpdateButton()
	{
		updateButton.click();
	}

	public void createNewAdminUser(String userName, String password, String userType) {
		enterUsername(userName);
		enterPassword(password);
		selectUserType(userType);
		clickOnSaveBtn();
	}
	public void deleteNewUser()
	{
		firstTContntDeleteBtn.click();
		driver.switchTo().alert().accept();
	}
	
	public String getUserTypeofaPerson(String userName)
	{
		List<String> names=new ArrayList<String>();
		names=generalutility.get_TextofElements(tableNames);
		int i=0;
		for(i=0;i<names.size();i++)
		{
			if(names.get(i).equals(userName))
			{
				i++;
				break;
			}
		}
		WebElement tUserType=driver.findElement(By.xpath("//table[@class='table table-bordered table-hover table-sm']//tbody//tr["+i+"]//td[2]"));
		return tUserType.getText();
	}

	
	public boolean statusOfTableElementIsClickable(String userName)
	{
		List<String> names=new ArrayList<String>();
		names=generalutility.get_TextofElements(tableNames);
		int i=0;
		for(i=0;i<names.size();i++)
		{
			if(names.get(i).equals(userName))
			{
				i++;
				break;
			}
		}
		WebElement tUserStatus=driver.findElement(By.xpath("//table[@class='table table-bordered table-hover table-sm']//tbody//tr["+i+"]//td[3]//span"));
		tUserStatus.click();

		return true;
	}
	public void clickOnAUserUpdateBtnInTable(String userName)
	{
		List<String> names=new ArrayList<String>();
		names=generalutility.get_TextofElements(tableNames);
		int i=0;
		for(i=0;i<names.size();i++)
		{
			if(names.get(i).equals(userName))
			{
				i++;
				break;
			}
		}
		WebElement tUpdateButton=driver.findElement(By.xpath("//table[@class='table table-bordered table-hover table-sm']//tbody//tr["+i+"]//td[5]//a[@class='btn btn-sm btn btn-primary btncss']"));
		tUpdateButton.click();
		
	}
	public void selectUserTypeAndUpdate(String newUserTypeValue)
	{
		selectUserType(newUserTypeValue);
		clickOnUpdateButton();
	}
	
	public void deleteAUser(String userName)
	{
		List<String> names=new ArrayList<String>();
		names=generalutility.get_TextofElements(tableNames);
		int i=0;
		for(i=0;i<names.size();i++)
		{
			if(names.get(i).equals(userName))
			{
				i++;
				break;
			}
		}
		WebElement tDeleteButton=driver.findElement(By.xpath("//table[@class='table table-bordered table-hover table-sm']//tbody//tr["+i+"]//td[5]//a[@class='btn btn-sm btn btn-danger btncss']"));
		tDeleteButton.click();
		driver.switchTo().alert().accept();
	}
	


}