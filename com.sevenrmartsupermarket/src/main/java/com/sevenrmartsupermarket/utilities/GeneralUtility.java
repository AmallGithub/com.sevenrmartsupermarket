package com.sevenrmartsupermarket.utilities;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.github.javafaker.Faker;

public class GeneralUtility {
	public String get_Attribute(WebElement element, String atrribute) {
		return element.getAttribute(atrribute);
	}

	public String get_CssValue(WebElement element, String attribute) {
		return element.getCssValue(attribute);
	}

	public List<String> get_TextofElements(List<WebElement> elements) {
		List<String> datas = new ArrayList<String>();
		for (WebElement element : elements) {
			datas.add(element.getText());
		}
		return datas;
	}
	
	public static String get_RandomFirstName()
	{
		Faker faker=new Faker();
		return faker.name().firstName();
	}
	
	public Boolean is_TextPresent(WebElement element,String expectedData)
	{
		return element.getText().contains(expectedData);
	}
}