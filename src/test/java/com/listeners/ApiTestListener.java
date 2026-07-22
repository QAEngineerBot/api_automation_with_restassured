package com.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.api.utils.AllureEnvWriterUtil;

public class ApiTestListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		AllureEnvWriterUtil.createAllureEnvironmentProperties();
	}
}
