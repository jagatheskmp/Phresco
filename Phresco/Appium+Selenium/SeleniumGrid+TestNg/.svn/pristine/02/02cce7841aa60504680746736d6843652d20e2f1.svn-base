package com.photon.phresco.automation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Factory;

import com.photon.phresco.automation.driver.FirefoxDriver;
import com.photon.phresco.automation.driver.IDriver;
import com.photon.phresco.automation.parser.XmlParser;

public class AutomationFactory {

	private RemoteWebDriver launchDriver;
	
	@Factory
	public Object[] createTestCase() {
		IDriver driver = new FirefoxDriver();
		List<Object> testCases = new ArrayList<Object>();  
		try {
			launchDriver = driver.launchDriver(new URL(XmlParser.getHubUrl()), XmlParser.getAppUrl(), XmlParser.getPlatform());

			List<TestCase> listTestCases= XmlParser.getTestCases();
			for (TestCase testCase : listTestCases) {
				testCase.setDriver(launchDriver);
				testCases.add(testCase);
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testCases.toArray();
	}
	
	@AfterTest
	public void quitBrowser() {
		if (launchDriver != null) {
			launchDriver.quit();
		}
	}
}
