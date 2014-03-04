package com.photon.phresco.automation.cmd;

import com.photon.phresco.automation.driver.ChromeDriver;
import com.photon.phresco.automation.driver.FirefoxDriver;
import com.photon.phresco.automation.driver.IDriver;

public class LaunchDriver extends AbstractCmd {

	public enum BrowserDriver {
		Firefox(FirefoxDriver.class),
		Chrome(ChromeDriver.class);

		public Class<? extends IDriver> cmdClass;

		private BrowserDriver(Class<? extends IDriver> cmdClass) {
	    	this.cmdClass = cmdClass;
	    }
	};

	@Override
	public void action(String name, String value) {
		BrowserDriver browserDriver = BrowserDriver.valueOf(value);
		try {
			IDriver iDriver = browserDriver.cmdClass.newInstance();
			driver = iDriver.launchDriver(getNodeUrl(), getAppUrl(), getPlatform());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
}
