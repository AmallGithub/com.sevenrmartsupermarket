package com.sevenrmartsupermarket.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class DashBoardPage 
{
WebDriver driver;
@FindBy(xpath = "//a[@class='d-block']")
WebElement profileName;
@FindBy(xpath="//section//div[@class='container-fluid']//a")
List<WebElement> dashBoardCards;
@FindBy(xpath="//section//div[@class='container-fluid']//div//p")
List<WebElement> cardNames;


public DashBoardPage(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}

public String getProfileName()
{
	return profileName.getText();
}

public void navigateToCard(String name) 
{
	int i=0;
	for(i=0;i<cardNames.size();i++)
	{
		String infoName=cardNames.get(i).getText(); 
		if(infoName.equalsIgnoreCase(name))   //equalsIgnoreCase() is a method of the String class that compares two strings while ignoring case (upper or lower)
		{
			break;
		}
	}
	dashBoardCards.get(i).click();
}




}
