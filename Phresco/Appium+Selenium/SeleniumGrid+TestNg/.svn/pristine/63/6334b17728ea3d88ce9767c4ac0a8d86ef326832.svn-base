package com.photon.phresco.automation.cmd;

import java.net.URL;

import org.openqa.selenium.WebDriver;

public enum LaunchBrowser {
    
	LaunchDriver(LaunchDriver.class);

	private final Class<? extends AbstractCmd> cmdClass;
	private String appUrl;
	private URL nodeUrl;
	private String platform;
	private String testName;
	private String driverKey;
	
	private LaunchBrowser(Class<? extends AbstractCmd> cmdClass) {
    	this.cmdClass = cmdClass;
    }
    
    public void execute(String name, String value) {
    	try {
    		AbstractCmd instance = cmdClass.newInstance();
    		instance.setAppUrl(appUrl);;
    		instance.setNodeUrl(nodeUrl);
    		instance.setPlatform(platform);
    		instance.setTestName(testName);
    		System.out.println("Type testName ==== " + testName);
    		System.out.println("instance.getDriverMap() ======================== " + instance.getDriverMap());
    		System.out.println("Type key ==== " + driverKey);
    		WebDriver driver = instance.getDriverMap().get(driverKey);
    		System.out.println("Type driver ==== " + driver);
    		instance.setDriver(driver);
    		System.out.println("Type name ==== " + name + " =======>  value " + value);
			instance.action(name, value);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
    }
    
    public Class<? extends AbstractCmd> getCmdClass() {
    	return cmdClass;
    }
	
    public String gettestName() {
		System.out.println("The testName in Type is" + testName);			
		return testName;
		
	}
	
	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public URL getNodeUrl() {
		return nodeUrl;
	}

	public void setNodeUrl(URL url) {
		this.nodeUrl = url;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getDriverKey() {
		return driverKey;
	}
	
	public void setDriverKey(String driverKey) {
		this.driverKey = driverKey;
	}

};   



