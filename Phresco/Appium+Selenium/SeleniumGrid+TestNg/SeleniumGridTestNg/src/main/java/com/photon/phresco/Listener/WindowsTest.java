package com.photon.phresco.Listener;



import org.testng.Assert;
import org.testng.ITest;
import org.testng.ITestResult;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners({ com.photon.phresco.Listener.MyListener.class })
public class WindowsTest implements ITest {
	@Test
	public void method1() {
		Assert.assertTrue(true);

	}

	public String getTestName() {
		return "WindowsTest";
	}

	

}


