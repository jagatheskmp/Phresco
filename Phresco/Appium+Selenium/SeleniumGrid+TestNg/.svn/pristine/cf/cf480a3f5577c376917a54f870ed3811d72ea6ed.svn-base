package com.photon.phresco.automation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;

import com.photon.phresco.automation.cmd.AbstractCmd;
import com.photon.phresco.automation.cmd.Type;
import com.photon.phresco.automation.parser.XmlParser;

public class AutomationFactory {

//	private RemoteWebDriver launchDriver;
	
	/*@Parameters(value = { "appurl", "nodeurl", "browser", "platform" })
	@BeforeTest
	public void launchBrowser(String appurl, String nodeurl, String browser, String platform) {
		Type typeDriver = Type.LaunchDriver;
		typeDriver.setAppUrl(appurl);
		typeDriver.setPlatform(platform);
		typeDriver.setValue(browser);
		try {
			typeDriver.setNodeUrl(new URL(nodeurl));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		typeDriver.execute();
		launchDriver = (RemoteWebDriver) AbstractCmd.driver;
	}*/
	
	@Parameters(value = { "suite"})
	@Factory
	public Object[] createTestCase(String suite) {
//		IDriver driver = new FirefoxDriver();
		List<TestCase> testCases = new ArrayList<TestCase>(1);
		try {
//			launchDriver = driver.launchDriver(new URL(XmlParser.getHubUrl()), XmlParser.getAppUrl(), XmlParser.getPlatform());

//			List<TestCase> listTestCases= XmlParser.getTestCases();
			XmlParser parser = new XmlParser();
			boolean isXmlParse = parser.parseXml("/Users/bharatkumarradha/WorkOffice/work/Automation/svn/SeleniumGrid+TestNg/AutomationFramework/src/test/resources/automation.xml");

			if (isXmlParse) {
				testCases = parser.getTestCases(suite);
			}
 			/*for (TestCase testCase : listTestCases) {
				testCase.setDriver(launchDriver);
				testCases.add(testCase);
			}*/

			System.out.println("testCases size ============= " + testCases.size());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testCases.toArray();
	}
	
	/*@AfterTest
	public void quitBrowser() {
		if (launchDriver != null) {
			launchDriver.quit();
		}
	}*/
}
