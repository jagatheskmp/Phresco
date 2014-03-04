package com.photon.phresco.automation.cmd;

import java.io.IOException;

import org.openqa.selenium.WebElement;

public class Click extends AbstractCmd {

	public Click() {
	}
	

	@Override
	public void action(String name, String xpath) {
		try {
			System.out.println("click testName === " + testName);
//			driver = getDriverMap().get(testName);
//			WebDriver webDriver = driverMap.get(testName);			System.out.println("The testName in Click "+testName);
			waitForElementPresent(name, xpath, 30);
			WebElement element = getXpathWebElement(xpath);
			element.click();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
