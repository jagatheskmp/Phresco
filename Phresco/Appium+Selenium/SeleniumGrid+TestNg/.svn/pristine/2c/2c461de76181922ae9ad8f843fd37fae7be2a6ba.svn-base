package com.photon.phresco.Listener;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.xml.XmlTest;

public class WebDriverListener implements IInvokedMethodListener {

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			XmlTest xmlTest = method.getTestMethod().getXmlTest();
			System.out.println("xmlTest:::" + xmlTest);
			Map<String, String> parameters = xmlTest.getParameters();
			System.out.println("parameters::" + parameters);
			String browserName = parameters.get("browserName");
			WebDriver driver = LocalDriverFactory.createInstance(browserName);

			LocalDriverManager.setWebDriver(driver);
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			WebDriver driver = LocalDriverManager.getDriver();
			if (driver != null) {
				driver.quit();
			}
		}
	}
}