package com.photon.phresco.automation.cmd;


public interface ICmd {

	static long ONE_THOUSAND_SECONDS = 1000;

	void action(String name, String value);
}
