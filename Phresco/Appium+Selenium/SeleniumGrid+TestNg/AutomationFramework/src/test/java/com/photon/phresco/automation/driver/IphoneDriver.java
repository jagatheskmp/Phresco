package com.photon.phresco.automation.driver;

import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IphoneDriver extends AbstractDriver {

	@Override
	public RemoteWebDriver launchDriver(URL serverUrl, String applicationUrl,
			String platform) {
		capabilities = DesiredCapabilities.iphone();
		capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
		capabilities.setCapability(CapabilityType.PLATFORM, Platform.valueOf(platform));
		return launchBrowser(serverUrl, applicationUrl);
	}

}
