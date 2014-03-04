package com.photon.phresco.automation.cmd;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Click extends AbstractCmd {

	public Click(WebDriver driver) {
		this.driver = driver;
	}
	

	@Override
	public void action(String name, String xpath) {
		try {
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
