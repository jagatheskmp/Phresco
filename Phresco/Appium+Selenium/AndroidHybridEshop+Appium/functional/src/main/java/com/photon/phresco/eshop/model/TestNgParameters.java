package com.photon.phresco.eshop.model;

import java.util.HashMap;
import java.util.Map;

public class TestNgParameters {

	private Map<String, String> device =new HashMap<String, String>();
	private Map<String, String> version =new HashMap<String, String>();
	private Map<String, String> platform =new HashMap<String, String>();
	private Map<String, String> pckage =new HashMap<String, String>();
	private Map<String, String> activity =new HashMap<String, String>();
	private Map<String, String> app =new HashMap<String, String>();
	private Map<String, String> serverIp =new HashMap<String, String>();

	public Map<String, String> getDevice() {
		return device;
	}
	public Map<String, String> getVersion() {
		return version;
	}
	public Map<String, String> getPlatform() {
		return platform;
	}
	public Map<String, String> getPckage() {
		return pckage;
	}
	public Map<String, String> getActivity() {
		return activity;
	}
	public Map<String, String> getApp() {
		return app;
	}
	public Map<String, String> getServerIp() {
		return serverIp;
	}

}
