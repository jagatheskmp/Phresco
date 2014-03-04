package com.photon.phresco.automation.model;

public class NodeInfo {

	String id;
	String url;
	String platform;
	String browser;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getPlatform() {
		return platform;
	}
	
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	public String getBrowser() {
		return browser;
	}
	
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id:");
		sb.append(id);
		sb.append(",url:");
		sb.append(url);
		sb.append(",platform:");
		sb.append(platform);
		sb.append(",browser:");
		sb.append(browser);
		return sb.toString();
	}
}
