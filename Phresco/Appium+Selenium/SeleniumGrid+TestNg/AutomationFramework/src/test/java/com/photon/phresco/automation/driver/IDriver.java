package com.photon.phresco.automation.driver;

import java.net.URL;

import org.openqa.selenium.remote.RemoteWebDriver;

public interface IDriver {

	RemoteWebDriver launchDriver(URL serverUrl, String applicationUrl, String platform);
}
