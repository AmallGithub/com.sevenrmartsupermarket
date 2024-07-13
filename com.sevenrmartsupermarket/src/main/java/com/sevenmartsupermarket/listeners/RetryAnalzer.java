package com.sevenmartsupermarket.listeners;



import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.model.ITest;

public class RetryAnalzer implements IRetryAnalyzer {
	int counter = 0;
	int retryLimit = 2;

	@Override
	public boolean retry(ITestResult result) {

		if (counter < retryLimit) {
			counter++;
			return true;
		}
		return false;
	}

}