/**
 * Archetype - phresco-drupal6-archetype
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
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.opera.core.systems.OperaDriver;
import com.photon.phresco.selenium.util.Constants;
import com.photon.phresco.selenium.util.ScreenActionFailedException;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.DrupalData;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.UserInfoConstants;

public class BaseScreen {

	private static WebDriver driver;
	private ChromeDriverService chromeService;
	private static Log log = LogFactory.getLog("BaseScreen");
	private static WebElement element;
	private DrupalData drupalConstants;
	private UIConstants uiConstants;
	private UserInfoConstants userInfo;
	private static PhrescoUiConstants phrsc;
	DesiredCapabilities capabilities;
	
	public BaseScreen() {

	}

	public BaseScreen(String selectedBrowser, String applicationURL,
			String applicationContext, DrupalData drupalConstants,
			UIConstants uiConstants,UserInfoConstants userInfo)throws AWTException, IOException, ScreenActionFailedException  {

		this.drupalConstants = drupalConstants;
		this.uiConstants = uiConstants;
		this.userInfo=userInfo;
		try {
			instantiateBrowser(selectedBrowser, applicationURL, applicationContext);
		} catch (ScreenException e) {
			// TODO Auto-generated catch block
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
				windowResize();
				driver.navigate().to(applicationURL + applicationContext);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_IE)) {
			log.info("---------------***LAUNCHING INTERNET EXPLORE***-----------");
			driver = new InternetExplorerDriver();
			windowResize();
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
	public static void windowResize()
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
	}

	public void closeBrowser() {
		log.info("-------------***BROWSER CLOSING***--------------");
		if (driver != null) {

			driver.quit();
		}
		if (chromeService != null) {
			chromeService.stop();
		}
	}

	public  String getChromeLocation() {

		log.info("getChromeLocation:*****CHROME TARGET LOCATION FOUND***");
		String directory = System.getProperty("user.dir");
		String targetDirectory = getChromeFile();
		String location = directory + targetDirectory;
		return location;
	}

	public  String getChromeFile() {
		if (System.getProperty("os.name").startsWith(Constants.WINDOWS_OS)) {
			log.info("*******WINDOWS MACHINE FOUND*************");

			return Constants.WINDOWS_DIRECTORY + "/chromedriver.exe";
		} else if (System.getProperty("os.name").startsWith(Constants.LINUX_OS)) {
			log.info("*******LINUX MACHINE FOUND*************");
			return Constants.LINUX_DIRECTORY_64 + "/chromedriver";
		} else if (System.getProperty("os.name").startsWith(Constants.MAC_OS)) {
			log.info("*******MAC MACHINE FOUND*************");
			return Constants.MAC_DIRECTORY + "/chromedriver";
		} else {
			throw new NullPointerException("******PLATFORM NOT FOUND********");
		}

	}

	public WebElement getXpathWebElement(String xpath) throws Exception {
		log.info("Entering:-----getXpathWebElement-------");
		try {

			element = driver.findElement(By.xpath(xpath));

		} catch (Throwable t) {
			log.info("Entering:---------Exception in getXpathWebElement()-----------");
			t.printStackTrace();

		}
		return element;
	}

	public void getIdWebElement(String id) throws ScreenException {
		log.info("Entering:---getIdWebElement-----");
		try {
			element = driver.findElement(By.id(id));

		} catch (Throwable t) {
			log.info("Entering:---------Exception in getIdWebElement()----------");
			t.printStackTrace();

		}

	}

	public void getcssWebElement(String selector) throws ScreenException {
		log.info("Entering:----------getIdWebElement----------");
		try {
			element = driver.findElement(By.cssSelector(selector));

		} catch (Throwable t) {
			log.info("Entering:---------Exception in getIdWebElement()--------");

			t.printStackTrace();

		}

	}

	public void waitForElementPresent(String locator, String methodName)
			throws IOException, Exception {
		try {
			log.info("Entering:--------waitForElementPresent()--------");
			By by = By.xpath(locator);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			log.info("Waiting:--------One second----------");
			wait.until(presenceOfElementLocated(by));
		}

		catch (Exception e) {
			/*File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,
					new File(GetCurrentDir.getCurrentDirectory() + "\\"
							+ methodName + ".png"));
			throw new RuntimeException("waitForElementPresent"
					+ super.getClass().getSimpleName() + " failed", e);*/
			Assert.assertNull(e);
                         
		}
	}

	Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
		log.info("Entering:------presenceOfElementLocated()-----Start");
		return new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				log.info("Entering:*********presenceOfElementLocated()******End");
				return driver.findElement(locator);

			}

		};

	}
	
	public void LoginProject(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			;
		
		}
		Thread.sleep(3000);
	    waitForElementPresent(uiConstants.USERNAMEF,methodName);
	    getXpathWebElement(uiConstants.USERNAMEF);
	    sendKeys(userInfo.USEREMAIL_VALUE);	
	    Thread.sleep(3000);
	    waitForElementPresent(uiConstants.PASSWORDF,methodName);
	    getXpathWebElement(uiConstants.PASSWORDF);
	    sendKeys(userInfo.PASSWORD_VALUE);
	    Thread.sleep(3000);
	    getXpathWebElement(uiConstants.BUTTONCLIK);
	    click();
	    getXpathWebElement(uiConstants.LOGOUT);
	    click();
	    Thread.sleep(3000);
	   
	   
	}
	
	public void CreateAccount(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			;
			}
		 waitForElementPresent(uiConstants.REGISTERLINK,methodName);
	    getXpathWebElement(uiConstants.REGISTERLINK);
	    click();
	    Thread.sleep(1000);
	    waitForElementPresent(uiConstants.NEWUSERNAME,methodName);
	    getXpathWebElement(uiConstants.NEWUSERNAME);
	    sendKeys(userInfo.NEWUSER_VALUE);
	    Thread.sleep(3000);
	    waitForElementPresent(uiConstants.NEWEMAIL,methodName);
	    getXpathWebElement(uiConstants.NEWEMAIL);
	    sendKeys(userInfo.NEWEMAIL_VALUE);
	    getXpathWebElement(uiConstants.BUTTONCLIK);
	    click();
	    Thread.sleep(3000);
	    isTextPresent(drupalConstants.TEXT);
	    Thread.sleep(3000);
		
 }
	

	
	
	public void click() throws ScreenException {
		log.info("Entering:********click operation start********");
		try {
			element.click();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		log.info("Entering:********click operation end********");

	}

	public void clear() throws ScreenException {
		log.info("Entering:********clear operation start********");
		try {
			element.clear();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		log.info("Entering:********clear operation end********");

	}

	public void sendKeys(String text) throws ScreenException {
		log.info("Entering:********enterText operation start********");
		try {
			clear();
			element.sendKeys(text);

		} catch (Throwable t) {
			t.printStackTrace();
		}
		log.info("Entering:********enterText operation end********");
	}

	public void submit() throws ScreenException {
		log.info("Entering:********submit operation start********");
		try {
			element.submit();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		log.info("Entering:********submit operation end********");

	}


	public void isElementPresent(String element) throws Exception {

		WebElement testElement = getXpathWebElement(element);
		if (testElement.isDisplayed() && testElement.isEnabled()) {

			log.info("---Element found---");
		} else {
			throw new RuntimeException("--Element not found---");
			// Assert.fail("--Element is not present--"+testElement);

		}

	}
	
	
	public boolean isTextPresent(String text) {
        boolean value=driver.findElement(By.tagName("body")).getText().contains(text);            
        System.out.println("-----TextCheck value-->"+value);
        Assert.assertTrue(value==true);
        
        return value;
        
        
        
    }   

}
