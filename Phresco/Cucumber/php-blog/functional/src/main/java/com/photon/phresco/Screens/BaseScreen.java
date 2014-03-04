/**
 * PHR_PhpBlog
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
import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.opera.core.systems.OperaDriver;
import com.photon.phresco.selenium.util.Constants;
import com.photon.phresco.selenium.util.ScreenActionFailedException;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.PhpData;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.UserInfoConstants;

public class BaseScreen {

	private static WebDriver driver;
	private ChromeDriverService chromeService;
	private static Log log = LogFactory.getLog("BaseScreen");
	private static WebElement element;	
	private PhpData phpConstants;
	private UIConstants uiConstants;
	private UserInfoConstants uif;
	private static PhrescoUiConstants phrsc;
	DesiredCapabilities capabilities;


	// private Log log = LogFactory.getLog(getClass());

	public BaseScreen() {

	}

	public BaseScreen(String selectedBrowser, String applicationURL, String applicatinContext, PhpData PhpConstants, UIConstants uiConstants ,UserInfoConstants uif, PhpData phpConstants)
			 throws AWTException, IOException, ScreenActionFailedException {
	
		this.phpConstants = phpConstants;
		this.uiConstants = uiConstants;
		this.uif = uif;
		try {
			instantiateBrowser(selectedBrowser,applicationURL, applicatinContext);
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

	public String getChromeLocation() {
		log.info("getChromeLocation:*****CHROME TARGET LOCATION FOUND***");
		String directory = System.getProperty("user.dir");
		String targetDirectory = getChromeFile();
		String location = directory + targetDirectory;
		return location;
	}

	public String getChromeFile() {
		if (System.getProperty("os.name").startsWith(Constants.WINDOWS_OS)) {
			log.info("*******WINDOWS MACHINE FOUND*************");
			// getChromeLocation("/chromedriver.exe");
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
	

 
	public void getXpathWebElement(String xpath) throws Exception {
		log.info("Entering:-----getXpathWebElement-------");
		try {

			element = driver.findElement(By.xpath(xpath));

		} catch (Throwable t) {
			log.info("Entering:---------Exception in getXpathWebElement()-----------");
			t.printStackTrace();

		}

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
			WebDriverWait wait = new WebDriverWait(driver,3);
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

	public void RegisterChek(String methodName,UIConstants uiConstants) throws Exception {
        if (StringUtils.isEmpty(methodName)) {
            methodName = Thread.currentThread().getStackTrace()[1]
                    .getMethodName();
            
         }
        log.info("Entering:********Registration for New Account******");
        Thread.sleep(2000);
        waitForElementPresent(uiConstants.REGISTER,methodName);
        getXpathWebElement(this.uiConstants.REGISTER);
        click();
      	waitForElementPresent(uiConstants.USERNAMET,methodName);
    	getXpathWebElement(this.uiConstants.USERNAMET);
        sendKeys(this.uif.USERNAME);
        waitForElementPresent(uiConstants.EMAILTEXT,methodName);
        getXpathWebElement(this.uiConstants.EMAILTEXT);
        sendKeys(this.uif.EMAIL);
        waitForElementPresent(uiConstants.PASSWORDT,methodName);
        getXpathWebElement(this.uiConstants.PASSWORDT);
        sendKeys(this.uif.PASSWORD);
    	Thread.sleep(2000);
        waitForElementPresent(uiConstants.RBUTTON,methodName);
        getXpathWebElement(this.uiConstants.RBUTTON);
        click();
        waitForElementPresent(this.phpConstants.TEXTACCUPDATE, methodName);
        isTextPresent(phpConstants.TEXTACCUPDATE);
     	/*waitForElementPresent(uiConstants.DASHBOARD,methodName);
        getXpathWebElement(this.uiConstants.DASHBOARD);
        element.click();*/
        Thread.sleep(2000);
	}
	
	public void LoginChek(String methodName,UIConstants uiConstants) throws Exception {
        if (StringUtils.isEmpty(methodName)) {
            methodName = Thread.currentThread().getStackTrace()[1]
                    .getMethodName();
            
         }
        Thread.sleep(2000);
        waitForElementPresent(uiConstants.LOGLINK,methodName);
        getXpathWebElement(this.uiConstants.LOGLINK);
        element.click();
        Thread.sleep(3000);
        waitForElementPresent(uiConstants.AMAILTEXT,methodName);
        getcssWebElement(this.uiConstants.AMAILTEXT);
        element.sendKeys(this.uif.EMAIL);
        Thread.sleep(2000);
        waitForElementPresent(uiConstants.APASSWORDT,methodName);
        getXpathWebElement(this.uiConstants.APASSWORDT);
        element.sendKeys(this.uif.PASSWORD);
        Thread.sleep(2000);
        waitForElementPresent(uiConstants.ABUTTON,methodName);
        getXpathWebElement(this.uiConstants.ABUTTON);
        element.click();
        Thread.sleep(2000);
        waitForElementPresent(uiConstants.LBUTTON,methodName);
        getXpathWebElement(this.uiConstants.LBUTTON);
        element.click();
        Thread.sleep(5000);
        
	}
	
	public void FindChek(String methodName,UIConstants uiConstants,PhpData phpData) throws Exception {
        if (StringUtils.isEmpty(methodName)) {
            methodName = Thread.currentThread().getStackTrace()[1]
                    .getMethodName();
            ;
         }
        waitForElementPresent(uiConstants.PHRESCOBLOG,methodName);
        getXpathWebElement(this.uiConstants.PHRESCOBLOG);
        click();
        waitForElementPresent(uiConstants.SEARCHBOX,methodName);
        getXpathWebElement(uiConstants.SEARCHBOX);
        element.sendKeys(phpData.SEARCHBOXVALUE);
        Thread.sleep(5000);
        getXpathWebElement(uiConstants.SEARCHB);
        element.click();
        Thread.sleep(3000);
        
	}
	
	public void UpdateAccount(String methodName,UIConstants uiConstants,PhpData phpData) throws Exception {
        if (StringUtils.isEmpty(methodName)) {
            methodName = Thread.currentThread().getStackTrace()[1]
                    .getMethodName();
            
         }
        waitForElementPresent(uiConstants.LOGLINK,methodName);
        getXpathWebElement(this.uiConstants.LOGLINK);
        click();
        Thread.sleep(3000);
        waitForElementPresent(uiConstants.AMAILTEXT,methodName);
        getXpathWebElement(this.uiConstants.AMAILTEXT);
        sendKeys(this.uif.EMAIL);
        Thread.sleep(2000);
        waitForElementPresent(uiConstants.APASSWORDT,methodName);
        getXpathWebElement(this.uiConstants.APASSWORDT);
        sendKeys(this.uif.PASSWORD);
        Thread.sleep(2000);
        waitForElementPresent(uiConstants.ABUTTON,methodName);
        getXpathWebElement(this.uiConstants.ABUTTON);
        element.click();
        Thread.sleep(2000);
        waitForElementPresent(uiConstants.DASHBOARD,methodName);
        getXpathWebElement(this.uiConstants.DASHBOARD);
        element.click();
        Thread.sleep(2000);
        waitForElementPresent(uiConstants.MYACCOUNT,methodName);
        getXpathWebElement(this.uiConstants.MYACCOUNT);
        element.click();
        Thread.sleep(3000);
        waitForElementPresent(uiConstants.UFIELD,methodName);
        getXpathWebElement(this.uiConstants.UFIELD);
        element.clear();
        element.sendKeys(this.uif.UVALUE);
        Thread.sleep(2000);
        waitForElementPresent(uiConstants.EFIELD,methodName);
        getXpathWebElement(this.uiConstants.EFIELD);
        element.clear();
        element.sendKeys(this.uif.EVALUE);
        Thread.sleep(2000);
        waitForElementPresent(uiConstants.PFIELD,methodName);
        getXpathWebElement(this.uiConstants.PFIELD);
        element.clear();
        element.sendKeys(this.uif.PVALUE);
        Thread.sleep(2000);
        waitForElementPresent(uiConstants.UBUTTON,methodName);
        getXpathWebElement(this.uiConstants.UBUTTON);
        click();
        Thread.sleep(5000);
        isTextPresent(phpConstants.TEXTACCUPDATE);
      
	}
	
	public void CreateCategory(String methodName,UIConstants uiConstants,PhpData phpData) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
		} 
	   waitForElementPresent(uiConstants.CATLINK1,methodName);
        getXpathWebElement(this.uiConstants.CATLINK1);
	    click();
	    Thread.sleep(2000);
	    waitForElementPresent(uiConstants.CATFIELD,methodName);
	    getXpathWebElement(this.uiConstants.CATFIELD);
	    sendKeys(phpConstants.CATEGORYNAME);
	    Thread.sleep(2000);
	    waitForElementPresent(this.uiConstants.DESCRIPTIONFIELD, methodName);
	    getXpathWebElement(this.uiConstants.DESCRIPTIONFIELD);
	    sendKeys(phpConstants.CATEGORYDESCRIPTION);
	    Thread.sleep(2000);
	    getXpathWebElement(this.uiConstants.CATBUTTON);
	    click();
	    isTextPresent(phpConstants.TEXTCATEGORYADD);
	    Thread.sleep(3000);
	    
	}

	
	public void updateCategory(String methodName,UIConstants uiConstants,PhpData phpData) throws Exception {

		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
		}
		  /*getXpathWebElement(this.uiConstants.HOME);
	      click();
	      Thread.sleep(4000);
	      waitForElementPresent(uiConstants.DASHBOARD,methodName);
	      getXpathWebElement(this.uiConstants.DASHBOARD);
	      element.click();
	      Thread.sleep(2000);
	      waitForElementPresent(uiConstants.CATLINK1,methodName);
	      getXpathWebElement(this.uiConstants.CATLINK1);
		  click();
		  Thread.sleep(2000);*/
	      getXpathWebElement(this.uiConstants.EDIT1);
	      click();
	      Thread.sleep(3000);
	      waitForElementPresent(uiConstants.DESCRIPTIONFIELD, methodName);
	      getXpathWebElement(this.uiConstants.DESCRIPTIONFIELD);
	      sendKeys(phpConstants.CATDESCRIPTIONADD);
	      Thread.sleep(2000);
	      getXpathWebElement(this.uiConstants.SUBMITBUTTON);
	      click();
	      Thread.sleep(8000);
	      isTextPresent(phpConstants.TEXTCATEGORYUPDATE);
		  Thread.sleep(3000);
	   
		
		
    }
	
	public void deleteCategory(String methodName,UIConstants uiConstants,PhpData phpData) throws Exception {

		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
		}
		 
		 getXpathWebElement(this.uiConstants.LINKDELETE);
		 click();
		 Thread.sleep(3000);
		 Alert alert=driver.switchTo().alert();
		 alert.accept();
		 Thread.sleep(3000);
		 isTextPresent(phpConstants.TEXTCATEGORYREMOVE);
		 getXpathWebElement(this.uiConstants.HOMELAST);
		 element.click();
		 Thread.sleep(5000);
		
		 
    }
	
	public void CreateTopic(String methodName,UIConstants uiConstants,PhpData phpData) throws Exception {
			if (StringUtils.isEmpty(methodName)) {
				methodName = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
				
			
			}
			
	    waitForElementPresent(uiConstants.DASHBOARD,methodName);
	    getXpathWebElement(this.uiConstants.DASHBOARD);
		click();
	    Thread.sleep(3000);
	    getXpathWebElement(this.uiConstants.CLICKADD);
	    click();
	    Thread.sleep(2000);
	    waitForElementPresent(this.uiConstants.TOPICTITLE, methodName);
	    getXpathWebElement(this.uiConstants.TOPICTITLE);
	    sendKeys(phpConstants.TOPICTITLE);
	    Thread.sleep(2000);	
	    waitForElementPresent(this.uiConstants.TOPICTEXT, methodName);
	    getXpathWebElement(this.uiConstants.TOPICTEXT);
	    sendKeys(phpConstants.TOPICTEXT);
	    Thread.sleep(2000);	
	    getXpathWebElement(this.uiConstants.ONCECLICK);
	    click();
	    isTextPresent(phpConstants.TEXTTOPICADD);
	    Thread.sleep(5000);
	    
	    
	    }
	 
	 public void editTopic(String methodName,UIConstants uiConstants,PhpData phpData) throws Exception {
			if (StringUtils.isEmpty(methodName)) {
				methodName = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
			
			}
			getXpathWebElement(this.uiConstants.HOMELAST);
			element.click();
			waitForElementPresent(uiConstants.DASHBOARD,methodName);
		    getXpathWebElement(this.uiConstants.DASHBOARD);
			click();
		    Thread.sleep(3000);
			getXpathWebElement(this.uiConstants.EDIT2);
			click();
			Thread.sleep(2000);	
			waitForElementPresent(this.uiConstants.TOPICTITLE1, methodName);
			getXpathWebElement(this.uiConstants.TOPICTITLE1);
			element.sendKeys(phpConstants.TOPICTITLE1);
			Thread.sleep(3000);			    
		    waitForElementPresent(this.uiConstants.TOPICTEXT1, methodName);
		    getXpathWebElement(this.uiConstants.TOPICTEXT1);
		    element.sendKeys(phpConstants.TOPICTEXT1);
		    Thread.sleep(2000);	
		    getXpathWebElement(this.uiConstants.CLIKLAST);
		    click();
		    Thread.sleep(3000);
			
	    }
	    
	    public void deleteTopic(String methodName,UIConstants uiConstants,PhpData phpData) throws Exception {
			if (StringUtils.isEmpty(methodName)) {
				methodName = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
				;
			
			}
			    
			    getXpathWebElement(this.uiConstants.DELETE);
			    element.click();
			    Thread.sleep(3000);
			    Alert alert=driver.switchTo().alert();
			    alert.accept();
			    Thread.sleep(3000);
			    isTextPresent(phpConstants.TEXTTOPICREMOVE);
			    waitForElementPresent(uiConstants.LOGOFF, methodName);
				getXpathWebElement(this.uiConstants.LOGOFF);
			    click();
			    Thread.sleep(3000);
				/*getXpathWebElement(this.uiConstants.HOME);
			    click();*/
			    Thread.sleep(3000);
	
	    }
	 
	
	   
	public boolean isTextPresent(String text) {
        if (text!= null){
        boolean value=driver.findElement(By.tagName("body")).getText().contains(text);           
        System.out.println("-----TextCheck value-->"+value);   
        Assert.assertTrue(value);
        return value;
       }
        else
        {
            throw new RuntimeException("---- Text not existed----");
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

}