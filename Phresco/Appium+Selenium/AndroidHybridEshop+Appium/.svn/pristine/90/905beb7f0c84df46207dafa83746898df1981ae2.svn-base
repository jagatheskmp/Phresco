package com.photon.phresco.eshop.enumscreen;

import org.openqa.selenium.WebDriver;

import com.photon.phresco.eshop.launchApp.AndroidLauncher;

import com.photon.phresco.eshop.model.TestNgParameters;

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

	};
	
	public WebDriver execute(TestNgParameters tngparam) {
		return null;

	}

}
