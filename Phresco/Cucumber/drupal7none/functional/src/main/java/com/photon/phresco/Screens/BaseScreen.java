/**
 * PHR_DrupalEshop
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
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import com.photon.phresco.selenium.util.GetCurrentDir;
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
	private static  PhrescoUiConstants phrsc;
	DesiredCapabilities capabilities;

	

	public BaseScreen() {

	}
	
	/**
	 * Invoking the super class method through passing the vale of Browser,URL, Context, then PhpData,UIConstants,UserInfoConstants Xml Values
	 * Then triggering instantiateBrowser
	 * @throws ScreenException
	 */
	public BaseScreen(String selectedBrowser, String applicationURL,
			String applicationContext, DrupalData drupalConstants,
			UIConstants uiConstants, UserInfoConstants userInfo) throws ScreenException {

		this.drupalConstants = drupalConstants;
		this.uiConstants = uiConstants;
		this.userInfo=userInfo;
	
		instantiateBrowser(selectedBrowser, applicationURL, applicationContext);
		
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
			WebDriverWait wait = new WebDriverWait(driver, 3);
			log.info("Waiting:--------One second----------");
			wait.until(presenceOfElementLocated(by));
		}

		catch (Exception e) {
             File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,
					new File(GetCurrentDir.getCurrentDirectory() + "\\"
							+ methodName + ".png"));
			throw new RuntimeException("waitForElementPresent"
					+ super.getClass().getSimpleName() + " failed", e);

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
	
/*	public void RegisterCheck(String methodName) throws Exception {
			if (StringUtils.isEmpty(methodName)) {
				methodName = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
				;
			  }
				log.info("Entering:********Registration Check******");
			
			    waitForElementPresent(this.uiConstants.SIGNUPLINK,methodName);
			    element=getXpathWebElement(this.uiConstants.SIGNUPLINK);
			    click();
			    Thread.sleep(3000);
			    waitForElementPresent(this.uiConstants.USERNAME, methodName);
			    getXpathWebElement(this.uiConstants.USERNAME);
			    element.sendKeys(userInfo.USERNAME_VALUE);			    
			    waitForElementPresent(this.uiConstants.EMAILFILED,methodName);
			    getXpathWebElement(this.uiConstants.EMAILFILED);
			    click();
			    sendKeys(userInfo.EMAIL_VALUE);
			    Thread.sleep(2000);
			    waitForElementPresent(this.uiConstants.PASSWORD, methodName);
			    getXpathWebElement(this.uiConstants.PASSWORD);
			    element.sendKeys(userInfo.PASSWORD_VALUE);
			    waitForElementPresent(this.uiConstants.CONFIRMPASSWORD,methodName);
			    getXpathWebElement(this.uiConstants.CONFIRMPASSWORD);
			    click();
			    sendKeys(userInfo.CONFIRM_PASSWORD);
			    Thread.sleep(2000);
			    element=getXpathWebElement(this.uiConstants.SUBMITBUTTON);
			    click();
			    Thread.sleep(3000);			    
	            waitForElementPresent(drupalConstants.TEXTREGISTRATION,methodName);
			    isTextPresent(drupalConstants.TEXTREGISTRATION);
			    Thread.sleep(3000);
			    element=getXpathWebElement(this.uiConstants.LOGOFFBUTTON);
			    click();
	}
	
	
	
	public void loginDrupal(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			;
		
        
		} 
		log.info("Entering:******Login Account******");
		
		element=getXpathWebElement(uiConstants.LOGIN);
		element.click();
		waitForElementPresent(this.uiConstants.USERNAME, methodName);
		getXpathWebElement(this.uiConstants.USERNAME);
		element.sendKeys(userInfo.USERNAME_VALUE);			    
		waitForElementPresent(this.uiConstants.PASSWORDLOGIN, methodName);
		getXpathWebElement(this.uiConstants.PASSWORDLOGIN).click();
		element.sendKeys(userInfo.PASSWORDLOGIN_VALUE);
		element=getXpathWebElement(this.uiConstants.SUBMITBUTTON);
		element.click();
		isTextPresent(drupalConstants.TEXTLOGINN);
		element=getXpathWebElement(uiConstants.LOGOFFBUTTON);
		element.click();
		
		
	}
	
	
	public void RequestNewPassword(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			;
		  }
		    log.info("Entering:******RequestNewPassword********");
		    
		    element=getXpathWebElement(uiConstants.LOGIN);
			element.click();
		    waitForElementPresent(this.uiConstants.REQUESTLINK,methodName);
		    getXpathWebElement(this.uiConstants.REQUESTLINK);
		    click();
		    Thread.sleep(2000);
		    waitForElementPresent(this.uiConstants.EMAILTEXTFIELD,methodName);
		    getXpathWebElement(this.uiConstants.EMAILTEXTFIELD);
		    click();
		    sendKeys(userInfo.EMAIL_VALUE);
		    Thread.sleep(2000);
		    element=getXpathWebElement(this.uiConstants.REQUESTBUTTON);
		    click();
		    Thread.sleep(5000);
		    isTextPresent(drupalConstants.TEXTREQUESTNEWPWD);
		    
		    
	}
	*/
	

	public void createAccount(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			;
		
        
		} 
		waitForElementPresent(this.uiConstants.CREATENEWACC, methodName);
		getXpathWebElement(this.uiConstants.CREATENEWACC);
		click();
		Thread.sleep(3000);
		waitForElementPresent(this.uiConstants.NAME, methodName);
		getXpathWebElement(this.uiConstants.NAME);
		sendKeys(this.userInfo.NAME);
		waitForElementPresent(this.uiConstants.EMAIL, methodName);
		getXpathWebElement(this.uiConstants.EMAIL);
		sendKeys(this.userInfo.EMAIL);
		waitForElementPresent(this.uiConstants.SUBMIT, methodName);
		getXpathWebElement(this.uiConstants.SUBMIT);
		click();
		waitForElementPresent(this.drupalConstants.TEXTVALUE, methodName);
		isTextPresent(drupalConstants.TEXTVALUE);
		Thread.sleep(5000);
	}
	
	
	public void loginDrupal(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			;
		
        
		} 
		
		waitForElementPresent(this.uiConstants.LOGIN, methodName);
		getXpathWebElement(uiConstants.LOGIN);
		click();
		Thread.sleep(3000);
		waitForElementPresent(this.uiConstants.NAME, methodName);
		getXpathWebElement(this.uiConstants.NAME);
		sendKeys(this.userInfo.NAME);
		waitForElementPresent(this.uiConstants.LOGINPASSWORD, methodName);
		getXpathWebElement(this.uiConstants.LOGINPASSWORD);
		sendKeys(this.userInfo.LOGINPASSWORD);
		waitForElementPresent(this.uiConstants.SUBMIT, methodName);
		getXpathWebElement(this.uiConstants.SUBMIT);
		click();
		Thread.sleep(3000);
		waitForElementPresent(this.uiConstants.LOGOUT, methodName);
		getXpathWebElement(this.uiConstants.LOGOUT);
		click();
		Thread.sleep(3000);
		
		
	    }
	
	public void searchDrupal(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			;

		} 
		
		waitForElementPresent(this.uiConstants.SEARCH, methodName);
		getXpathWebElement(this.uiConstants.SEARCH);
		sendKeys(this.drupalConstants.SEARCH);
		waitForElementPresent(this.uiConstants.SUBMIT, methodName);
		getXpathWebElement(this.uiConstants.SUBMIT);
		click();
		Thread.sleep(3000);
		
		
	}
	

	  
	  /*  public void scrollDownUP()
	    {
	    	try{
	    	driver.findElement(By.id("© Photon Infotech 2012")).sendKeys(Keys.END);
	    	Thread.sleep(5000);
	    	driver.findElement(By.id("© Photon Infotech 2012")).sendKeys(Keys.UP);
	    	}catch (Throwable t) {
				t.printStackTrace();
			}

	    }
	    */
	    
	    public void selectValue(String valToBeSelected){
	    	List <WebElement> options = driver.findElements(By.tagName("option"));
			for (WebElement option : options) {
				if (valToBeSelected.equalsIgnoreCase(option.getText())){
					option.click();
				}
			    }
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

	/*public void isTextPresent(String textValue) {
		
		if (textValue != null) {
			Boolean textCheck = driver.getPageSource().contains(textValue);
			
			System.out.println("-----TextCheck value-->"+textCheck);
			//Assert.assertTrue(textCheck);
			Assert.assertTrue("Text present", textCheck);
		} else {

			throw new RuntimeException("---- Text not existed----");
			
		}
	}*/

	public void isElementPresent(String element) throws Exception {

		WebElement testElement = getXpathWebElement(element);
		if (testElement.isDisplayed() && testElement.isEnabled()) {

			log.info("---Element found---");
		} else {
			throw new RuntimeException("--Element not found---");
			// Assert.fail("--Element is not present--"+testElement);

		}

	}
	/**
	 *  
	 * @param text
	 */
	public void isTextPresent(String text) {
		if (text!= null){
		boolean textCheck=driver.findElement(By.tagName("body")).getText().contains(text);	
		Assert.assertTrue("Text present", textCheck); 
	    
	    }
		else
		{
			throw new RuntimeException("---- Text not existed----");
		}
	    
	    
	    
	}	
	
	


}

