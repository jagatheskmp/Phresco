package com.photon.phresco.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.photon.phresco.model.Jquerymobilewidgets.Mobilewidget;

public class FactoryDataproviderTest {


	private  String firstName;
	private  String laststName;
	@Factory(dataProviderClass=com.photon.phresco.uiconstants.TestDataProvider.class,dataProvider="jqueryMobilewidgetdata")
	public FactoryDataproviderTest(Mobilewidget mobilewidget) {
		firstName = mobilewidget.getBillInfoFirstNameValue();
		laststName = mobilewidget.getBillInfoLastNameValue();
	}

	@Test
	public void testDataProvider() {
		System.out.println("firstName:::" +firstName);
		System.out.println("laststName::" +laststName);
	}  

	@Test
	public void testDataProvider2() {
		System.out.println("testDataProvider2:::" +firstName);
		System.out.println("testDataProvider2:::" +laststName);
	}  

	@BeforeClass
	public void beforeClass() {
		System.out.println("In a method which has beforeClass annotation");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("In a method which has afterClass annotation");

	}
}
