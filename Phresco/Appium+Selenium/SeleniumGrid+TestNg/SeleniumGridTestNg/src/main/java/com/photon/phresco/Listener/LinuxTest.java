package com.photon.phresco.Listener;

import junit.framework.Assert;

import org.testng.ITest;
import org.testng.annotations.Test;

public class LinuxTest implements ITest{

	
	@Test
	public void linuxTestcase()
	{
		Assert.assertTrue(true);
	}
	
	public String getTestName() {
		return "LinuxTest";
	}
}

