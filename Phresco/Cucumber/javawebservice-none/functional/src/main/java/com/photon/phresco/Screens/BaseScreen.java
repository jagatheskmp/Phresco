/**
 * Archetype - phresco-javawebservice-archetype
 *
 * Copyright (C) 1999-2013 Photon Infotech Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.photon.phresco.Screens;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import com.opera.core.systems.OperaDriver;
import com.photon.phresco.selenium.util.Constants;
import com.photon.phresco.selenium.util.ScreenActionFailedException;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.PhrescoJavaWebserviceUiConstants;


public class BaseScreen {

	private WebDriver driver;
	private ChromeDriverService chromeService;
	DesiredCapabilities capabilities;
	private Log log = LogFactory.getLog("BaseScreen");

	public BaseScreen() {

	}

	public BaseScreen(String selectedBrowser, String applicationURL,String applicatinContext)
	throws AWTException, IOException, ScreenActionFailedException {

		try {			
			instantiateBrowser(selectedBrowser,applicationURL, applicatinContext);
		} 
		catch (ScreenException e) {
			e.printStackTrace();
		}

	}
	public void instantiateBrowser(String selectedBrowser,
			String applicationURL, String applicationContext)
					 throws ScreenException {

		if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_CHROME)) {

			try {
				// "D:/Selenium-jar/chromedriver_win_19.0.1068.0/chromedriver.exe"
				chromeService = new ChromeDriverService.Builder()
						.usingDriverExecutable(new File(getChromeLocation()))
						.usingAnyFreePort().build();
				log.info("-------------***LAUNCHING GOOGLECHROME***--------------");
				driver = new ChromeDriver(chromeService);
				//driver.manage().window().maximize();
				//windowResize();
				driver.navigate().to(applicationURL + applicationContext);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_IE)) {
			log.info("---------------***LAUNCHING INTERNET EXPLORE***-----------");
			driver = new InternetExplorerDriver();
			//windowResize();
			driver.navigate().to(applicationURL + applicationContext);

		} else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_FIREFOX)) {
			log.info("-------------***LAUNCHING FIREFOX***--------------");
			driver = new FirefoxDriver();
			//driver.manage().window().maximize();
			//windowResize();
			driver.navigate().to(applicationURL + applicationContext);

		}
		else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_FIREFOX)) {
			log.info("-------------***LAUNCHING SAFARI***--------------");
			driver = new SafariDriver();
			//driver.manage().window().maximize();
			//windowResize();
			driver.navigate().to(applicationURL + applicationContext);

		}

		else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_OPERA)) {
			log.info("-------------***LAUNCHING OPERA***--------------");
		    driver =new OperaDriver();
		    
			System.out.println("******entering window maximize********");
			Robot robot;
			try {
				robot = new Robot();
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.keyRelease(KeyEvent.VK_SPACE);
				robot.keyPress(KeyEvent.VK_X);
				robot.keyRelease(KeyEvent.VK_X);
			} catch (AWTException e) {
				e.printStackTrace();
			}
			driver.navigate().to(applicationURL + applicationContext);

		} else {
			throw new ScreenException(
					"------Only FireFox,InternetExplore,Chrome and Opera  works-----------");
		}
	}
	/*public static void windowResize()
	{
		phrsc = new PhrescoUiConstants();
		String resolution = phrsc.RESOLUTION;		
		if(resolution!=null)
		{
		String[] tokens = resolution.split("x");
		String resolutionX=tokens[0];
		String resolutionY=tokens[1];		
		int x= Integer.parseInt(resolutionX);
		int y= Integer.parseInt(resolutionY);
		Dimension screenResolution = new Dimension(x,y);
		driver.manage().window().setSize(screenResolution);
		}
		else{
			driver.manage().window().maximize();
		}
	}*/



	public void closeBrowser() {
		log.info("-------------***BROWSER CLOSING***--------------");		
		if (driver != null) {
			driver.quit();		
			if(chromeService!=null){
				chromeService.stop();
			}
		} else {
			throw new NullPointerException();
		}

	}

	public  String  getChromeLocation(){	
		log.info("getChromeLocation:*****CHROME TARGET LOCATION FOUND***");
		String directory = System.getProperty("user.dir");
		String targetDirectory = getChromeFile();		
		String location = directory + targetDirectory;	
		return location;
	}


	public  String getChromeFile(){
		if(System.getProperty("os.name").startsWith(Constants.WINDOWS_OS)){
			log.info("*******WINDOWS MACHINE FOUND*************");
			return Constants.WINDOWS_DIRECTORY + "/chromedriver.exe" ;			
		}else if(System.getProperty("os.name").startsWith(Constants.LINUX_OS)){
			log.info("*******LINUX MACHINE FOUND*************");
			return Constants.LINUX_DIRECTORY_64+"/chromedriver";
		}else if(System.getProperty("os.name").startsWith(Constants.MAC_OS)){
			log.info("*******MAC MACHINE FOUND*************");
			return Constants.MAC_DIRECTORY+"/chromedriver";
		}else{
			throw new NullPointerException("******PLATFORM NOT FOUND********");
		}

	}

	public boolean isTextPresent(String text)
	{
		if(text!=null)
		{
			boolean value=driver.findElement(By.tagName("body")).getText().contains(text);
			//System.out.println("--------TextCheck value---->"+text+"------------Result is-------------"+value); 
			Assert.assertTrue(value);
			return value;
		}
		else
		{
			throw new RuntimeException("---- Text not present----");
		}

	}




	public  void javaWebservcieHelloWorld(String methodName,PhrescoJavaWebserviceUiConstants javaWservice) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@javaWebservcieHelloWorld::******executing javaWebservcieHelloWorld scenario****");
		try {
			Thread.sleep(5000);	
			isTextPresent(javaWservice.ELEMENT);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}
}


