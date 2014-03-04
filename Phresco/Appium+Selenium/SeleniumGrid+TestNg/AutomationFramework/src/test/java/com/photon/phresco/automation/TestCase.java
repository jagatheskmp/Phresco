package com.photon.phresco.automation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITest;
import org.testng.annotations.Test;

import com.photon.phresco.automation.cmd.ICmd;
import com.photon.phresco.automation.modal.TestParameter;

public class TestCase implements ITest {

	private String testName = "";
	private WebDriver driver;
	private List<TestParameter> testParams;
	
	public TestCase(String name, RemoteWebDriver launchDriver, List<TestParameter> testParams) {
		this.testName = name;
		this.driver = launchDriver;
		this.testParams = testParams;
	}
	
	public TestCase(String name, List<TestParameter> testParams) {
		this.testName = name;
		this.testParams = testParams;
	}

	@Override
	public String getTestName() {
		System.out.println("testname "+ testName);
		return testName;
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	@Test
	public void testExecution() {
		try {
			for (TestParameter testParam : testParams) {
				Class<? extends ICmd> cls = (Class<? extends ICmd>) Class.forName("com.photon.phresco.automation.cmd."+testParam.getEvent());
				Constructor<? extends ICmd> ctor = cls.getConstructor(WebDriver.class);
				ICmd cmd = ctor.newInstance(driver);
				cmd.action(testParam.getName(), testParam.getXpath());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	
	}

}
