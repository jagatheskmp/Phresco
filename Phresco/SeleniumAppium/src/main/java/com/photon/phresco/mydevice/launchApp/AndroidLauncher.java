package com.photon.phresco.mydevice.launchApp;

import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.photon.phresco.mydevice.model.TestNgParameters;

public class AndroidLauncher {
	
	private WebDriver driver;
	private Log log = LogFactory.getLog("AndroidLauncher");
	
	public WebDriver androidDriver(TestNgParameters tngparam) throws Exception {
		log.info("LAUNCHING ANDROID APPLICATION");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("device", "Selendroid");
		capabilities.setCapability(CapabilityType.VERSION, tngparam.getVersion().get("version"));
		capabilities.setCapability(CapabilityType.PLATFORM, Platform.valueOf(tngparam.getPlatform().get("platform").toUpperCase()));
		capabilities.setCapability("app",tngparam.getApp().get("app") );
		capabilities.setCapability("app-package", tngparam.getPckage().get("pckage"));
		capabilities.setCapability("app-activity", "."+tngparam.getActivity().get("activity"));	
		driver = new RemoteWebDriver(new URL("http://"+tngparam.getServerIp().get("serverIp")+":4723/wd/hub"), capabilities);	 
		driver.switchTo().window("WEBVIEW");
		return driver;

	}
}
