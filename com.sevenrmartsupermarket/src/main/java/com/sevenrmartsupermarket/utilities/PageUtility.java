package com.sevenrmartsupermarket.utilities;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.ui.Select;

import com.sevenrmartsupermarket.constants.constants;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class PageUtility {

	WebDriver driver;

	JavascriptExecutor js;

	public PageUtility(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor) driver;
	}

	public void mouseMove(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();

	}

	public void select_Index(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public void select_Value(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	public void select_VisibleText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	public void scrollAndClick(WebElement element) {
		int index = 0;
		while (!isClicked(element)) {
			js.executeScript("window.scrollBy(0," + index + ")");
			index = index + 3;
		}
	}

	public boolean isClicked(WebElement element) {
		try {
			element.click();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public void js_Click(WebElement element)
	{
		js.executeScript("arguments[0].click()",element);
	}

}