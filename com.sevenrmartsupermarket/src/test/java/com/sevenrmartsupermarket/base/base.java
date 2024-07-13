package com.sevenrmartsupermarket.base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.model.ScreenCapture;
import com.sevenrmartsupermarket.constants.constants;
import com.sevenrmartsupermarket.utilities.ScreenshotCapture;

import io.github.bonigarcia.wdm.WebDriverManager;

public class base {
	public WebDriver driver;
	ScreenshotCapture screenshotcapture = new ScreenshotCapture(); 
	Properties properties = new Properties();

	public base() {
		try {
			FileInputStream fis = new FileInputStream(constants.CONFIG_FILE_PATH);
			properties.load(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** initilaizing browser **/
	public void initialize(String browser, String url) {
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(constants.IMPLICIT_WAIT));
	}

	@Parameters("browser")

	@BeforeMethod(enabled = false)
	public void launchBrowser(String browser) {
		String url = properties.getProperty("url");
		initialize(browser, url);

	}

	@BeforeMethod(enabled = true, alwaysRun = true)
	public void launchBrowser() {
		String browser = properties.getProperty("browser");
		String url = properties.getProperty("url");
		initialize(browser, url);

	}

	@AfterMethod(alwaysRun = true)
	public void terminateBrowser(ITestResult itestresult) {
		if (itestresult.getStatus() == ITestResult.FAILURE) {
			screenshotcapture.takeScreenshot(driver, itestresult.getName());
		}
		driver.close();
	}

}
