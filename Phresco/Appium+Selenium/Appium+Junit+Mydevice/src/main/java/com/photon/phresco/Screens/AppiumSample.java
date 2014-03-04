package com.photon.phresco.Screens;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AppiumSample {


	private WebDriver driver;
	@Before
	public void setUp()
	{
		
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", "selendroid");
        capabilities.setCapability(CapabilityType.VERSION, "4.2");
        capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
        capabilities.setCapability("app", "D:/MyDeviceAndroid/do_not_checkin/target/MyDeviceAndroid.apk");
        capabilities.setCapability("app-package", "com.target.stores.mystoremanager.prod");
        capabilities.setCapability("app-activity", ".MyStoreManager");
        try {
			driver = new RemoteWebDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void jaga() throws InterruptedException
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Hai");
		Thread.sleep(5000);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}