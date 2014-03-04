package com.photon.phresco.Listener;

import junit.framework.Assert;

import org.testng.ITest;
import org.testng.annotations.Test;

public class MacTest implements ITest{

	@Test
	public void mac1Testcase()
	{
		Assert.assertTrue(false);
	}
	
	@Test(description="mac2Testcase")
	public void mac2Testcase()
	{
		Assert.assertTrue(true);
	}
	
	
	public String getTestName() {
		return "MacTest";
	}
}
