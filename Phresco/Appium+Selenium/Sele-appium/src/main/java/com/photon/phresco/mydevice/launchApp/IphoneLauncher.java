package com.photon.phresco.mydevice.launchApp;

import java.io.File;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.photon.phresco.mydevice.model.TestNgParameters;

public class IphoneLauncher {
	
	private  WebDriver driver;
	private Log log = LogFactory.getLog("IphoneLauncher");
	
	public  WebDriver iphoneDriver(TestNgParameters tngparam) throws Exception {
		log.info("LAUNCHING IOS APPLICATION");
		File app = new File(tngparam.getApp().get("app"));
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "iOS");
		capabilities.setCapability("device", "iPhone Simulator");
		capabilities.setCapability(CapabilityType.VERSION, tngparam.getVersion().get("version"));
		capabilities.setCapability(CapabilityType.PLATFORM, Platform.valueOf(tngparam.getPlatform().get("platform").toUpperCase()));
		capabilities.setCapability("app", app.getAbsolutePath());
		driver = new RemoteWebDriver(new URL("http://"+tngparam.getServerIp().get("serverIp")+":4723/wd/hub"), capabilities);  
		return driver;
	}
}
