package com.photon.phresco.Listener;

import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.Reporter;

public class SeleniumStarterListener implements ISuiteListener {
	private static SeleniumServer server = null;
	private static RemoteControlConfiguration configuration = null;
	private static boolean isServerStarted = false;

	public void onStart(ISuite suite) {

		configuration = new RemoteControlConfiguration();
		configuration.setPort(4444);
		try {
			server = new SeleniumServer(configuration);
			server.boot();
			isServerStarted = true;
			Reporter.log("Started the selenium server", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onFinish(ISuite suite) {
		if (isServerStarted) {
			server.stop();
			Reporter.log("Stopped the selenium server", true);
		}
	}

}
