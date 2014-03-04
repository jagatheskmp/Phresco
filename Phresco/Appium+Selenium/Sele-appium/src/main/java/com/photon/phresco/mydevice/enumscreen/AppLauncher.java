package com.photon.phresco.mydevice.enumscreen;

import org.openqa.selenium.WebDriver;
import com.photon.phresco.mydevice.launchApp.AndroidLauncher;
import com.photon.phresco.mydevice.launchApp.IphoneLauncher;
import com.photon.phresco.mydevice.model.TestNgParameters;

public enum AppLauncher {
	
	ANDROID() {
		public WebDriver execute(TestNgParameters tngparam) {
			try {
				AndroidLauncher al=new AndroidLauncher();
				return al.androidDriver(tngparam);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;

		}

	},
	IOS() {
		public WebDriver execute(TestNgParameters tngparam) {
			try {
				IphoneLauncher il =new IphoneLauncher();
				return il.iphoneDriver(tngparam);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	};
	
	public WebDriver execute(TestNgParameters tngparam) {
		return null;

	}

}
