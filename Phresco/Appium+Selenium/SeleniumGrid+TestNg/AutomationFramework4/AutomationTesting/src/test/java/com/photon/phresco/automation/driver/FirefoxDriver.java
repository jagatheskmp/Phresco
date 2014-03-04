package com.photon.phresco.automation.driver;

import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FirefoxDriver extends AbstractDriver {

	public FirefoxDriver() {
	}
	
	@Override
	public RemoteWebDriver launchDriver(URL nodeurl, String appurl, String platform) {
		capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability(CapabilityType.PLATFORM, Platform.valueOf(platform));
		return launchBrowser(nodeurl, appurl);
	}

}
