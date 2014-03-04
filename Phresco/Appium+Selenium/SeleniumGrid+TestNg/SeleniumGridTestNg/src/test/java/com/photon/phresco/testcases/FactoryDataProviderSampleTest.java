package com.photon.phresco.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

public class FactoryDataProviderSampleTest extends WebTest  {
	@Factory(dataProvider = "dp")
	public FactoryDataProviderSampleTest(int n) {
	  super(n);
	}
	 
	@DataProvider
	static public Object[][] dp() {
	  return new Object[][] {
	    new Object[] { 41 },
	    new Object[] { 42 },
	    new Object[] { 45 },
	  };
	}
}
