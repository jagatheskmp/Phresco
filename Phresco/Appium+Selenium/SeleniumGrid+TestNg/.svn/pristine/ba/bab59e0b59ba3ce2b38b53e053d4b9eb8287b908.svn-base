package com.photon.phresco.automation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.automation.cmd.AbstractCmd;
import com.photon.phresco.automation.cmd.Type;
import com.photon.phresco.automation.parser.Step;

public class TestCase implements ITest {

	private String testName = "";
	private List<Step> testParams;
	private String driverKey = "";
	
	public TestCase(String name, List<Step> testParams) {
		this.testName = name;
		this.testParams = testParams;
	}

	@Override
	public String getTestName() {
		System.out.println("test case name "+ testName);
		return testName;
	}
	
	@Parameters(value = { "appurl", "nodeurl", "browser", "platform" })
	@BeforeClass
	public void launchBrowser(String appurl, String nodeurl, String browser, String platform) {
		System.out.println("############################################## browser " + browser);
		Type typeDriver = Type.LaunchDriver;
		typeDriver.setAppUrl(appurl);
		typeDriver.setPlatform(platform);
//		typeDriver.setBrowser(browser);
//		typeDriver.setValue(browser);
		typeDriver.setTestName(testName);
		driverKey = platform + browser + testName;
		try {
			typeDriver.setNodeUrl(new URL(nodeurl));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		System.out.println("############################################## typeDriver " + typeDriver);
		typeDriver.execute(testName, browser);
	}
	
	@Test
	public void testExecution() {
		try {
			for (Step testParam : testParams) {
				System.out.println("*******************step name " + testParam.getName());
				System.out.println("browser.............");
				Type type = Type.valueOf(testParam.getAction());
//				type.setName(testParam.getName());
//				type.setValue(testParam.getValue());
				type.setTestName(testName);
				type.setDriverKey(driverKey);
				type.execute(testParam.getName(), testParam.getValue());
				
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
		WebDriver webDriver = AbstractCmd.getDriverMap().get(driverKey);
		if (webDriver != null) {
			System.out.println("if.....");
			webDriver.quit();
		System.out.println("after quit in testcase.java");
			AbstractCmd.getDriverMap().remove(testName);
		}
	}
}
