package com.sevenrmartsupermarket.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.sevenrmartsupermarket.constants.constants;

public class ScreenshotCapture {
	TakesScreenshot takescreenshot; // used for capturing screenshots

	public void takeScreenshot(WebDriver driver, String imageName) {
		try {
			takescreenshot = (TakesScreenshot) driver;  //typecasting
			File screenshot = takescreenshot.getScreenshotAs(OutputType.FILE);
			String timeStamp = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss").format(new Date());
			String destination = constants.SCREENSHOT_FILE_PATH + imageName + "_" + timeStamp + ".png";
			File filePath = new File(destination);
			FileHandler.copy(screenshot, filePath);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}