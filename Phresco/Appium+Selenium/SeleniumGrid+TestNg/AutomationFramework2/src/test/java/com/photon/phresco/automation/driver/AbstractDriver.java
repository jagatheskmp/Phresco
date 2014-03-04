package com.photon.phresco.automation.driver;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class AbstractDriver implements IDriver {
	
	DesiredCapabilities capabilities;
	RemoteWebDriver remoteDriver;
	
	protected RemoteWebDriver launchBrowser(URL nodeurl, String appurl) {
		System.out.println("appurl " + appurl);
		System.out.println("nodeurl " + nodeurl);
		remoteDriver = new RemoteWebDriver(nodeurl, capabilities);
		remoteDriver.navigate().to(appurl);
		return remoteDriver;
	}

}
