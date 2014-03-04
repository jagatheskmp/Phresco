package com.photon.phresco.automation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.automation.cmd.AbstractCmd;
import com.photon.phresco.automation.cmd.Type;
import com.photon.phresco.automation.parser.Step;

public class TestCase implements ITest {

	private String testName = "";
	private List<Step> testParams;
	
	public TestCase(String name, List<Step> testParams) {
		this.testName = name;
		this.testParams = testParams;
	}

	@Override
	public String getTestName() {
		System.out.println("testname "+ testName);
		return testName;
	}
	
	@Parameters(value = { "appurl", "nodeurl", "browser", "platform" })
	@BeforeClass
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
	}
	
	@Test
	public void testExecution() {
		try {
			for (Step testParam : testParams) {
				System.out.println("*******************step name " + testParam.getName());
				Type type = Type.valueOf(testParam.getAction());
				type.setName(testParam.getName());
				type.setValue(testParam.getValue());
				type.execute();
				
//				cmdClass.
//				Class<? extends ICmd> cls = (Class<? extends ICmd>) Class.forName("com.photon.phresco.automation.cmd."+testParam.getAction());
//				Constructor<? extends ICmd> ctor = cmdClass.getConstructor(testParam.getValue().getClass());
//				ICmd cmd = ctor.newInstance(testParam.getValue());
//				cmd.action(testParam.getName(), testParam.getXpath());
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	
	}
	
	@AfterClass
	public void quitBrowser() {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@quitBrowser/");
		if (AbstractCmd.driver != null) {
			System.out.println("if.....");
			AbstractCmd.driver.quit();
			AbstractCmd.driver = null;
		}
	}
}
