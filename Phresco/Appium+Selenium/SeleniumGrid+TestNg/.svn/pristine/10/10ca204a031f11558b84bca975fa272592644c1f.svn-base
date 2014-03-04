package com.photon.phresco.automation.cmd;

import java.net.URL;


public enum Type {
    
	Click(Click.class),
	LaunchDriver(LaunchDriver.class);
	
	private final Class<? extends AbstractCmd> cmdClass;
	private String name;
	private String value;
	private String appUrl;
	private URL nodeUrl;
	private String platform;

	private Type(Class<? extends AbstractCmd> cmdClass) {
    	this.cmdClass = cmdClass;
    }
    
    public void execute() {
    	try {
    		AbstractCmd instance = cmdClass.newInstance();
    		instance.setAppUrl(appUrl);;
    		instance.setNodeUrl(nodeUrl);
    		instance.setPlatform(platform);
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
    
};   
