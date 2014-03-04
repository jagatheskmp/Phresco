package com.photon.phresco.automation.driver;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class AbstractDriver implements IDriver {
	
	DesiredCapabilities capabilities;
	RemoteWebDriver remoteDriver;
	
	protected RemoteWebDriver launchBrowser(URL serverUrl, String applicationUrl) {
		remoteDriver = new RemoteWebDriver(serverUrl, capabilities);
		System.out.println("launching browser..");
		remoteDriver.navigate().to(applicationUrl);
		return remoteDriver;
	}

}
