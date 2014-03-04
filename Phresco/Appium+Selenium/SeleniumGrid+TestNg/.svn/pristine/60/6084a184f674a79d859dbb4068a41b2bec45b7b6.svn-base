package com.photon.phresco.automation.cmd;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.photon.phresco.automation.driver.ChromeDriver;
import com.photon.phresco.automation.driver.FirefoxDriver;
import com.photon.phresco.automation.driver.IDriver;

public class LaunchDriver extends AbstractCmd {

	public enum BrowserDriver {
		Firefox(FirefoxDriver.class),
		Chrome(ChromeDriver.class);
		//HashMap<String, AbstractCmd> map1 = new HashMap<String, AbstractCmd>();

		public Class<? extends IDriver> cmdClass;

		private BrowserDriver(Class<? extends IDriver> cmdClass) {
	    	this.cmdClass = cmdClass;
	    }
	};
	
	@Override
	public void action(String name, String value) {
		System.out.println("value ======= " + value);
		BrowserDriver browserDriver = BrowserDriver.valueOf(value);
		try {
			IDriver iDriver = browserDriver.cmdClass.newInstance();
			RemoteWebDriver launchDriver = iDriver.launchDriver(getNodeUrl(), getAppUrl(), getPlatform());
			System.out.println("launchDriver::"+launchDriver);
			System.out.println("getPlatform::"+getPlatform());
			System.out.println("broser...::"+value);
			AbstractCmd.getDriverMap().put(getPlatform()+value+testName, launchDriver);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
}
