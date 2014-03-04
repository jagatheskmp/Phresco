package com.photon.phresco.Screens.ImportApplicationSvnandGithub;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;

import com.google.common.base.Function;
import com.photon.phresco.selenium.util.Constants;
import com.photon.phresco.selenium.util.GetCurrentDir;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.ImportApplicationConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class ImportApplicationBaseScreen {

	private WebDriver driver;
	private ChromeDriverService chromeService;
	private Log log = LogFactory.getLog("BaseScreen");
	private WebElement element;	
	DesiredCapabilities capabilities;
	private PhrescoUiConstantsXml phrsc;
	private phresco_env_config phrscEnv;
	private UserInfoConstants userInfo;
	private String resolution = null;

	public ImportApplicationBaseScreen() {

	}	public ImportApplicationBaseScreen(String selectedBrowser, String selectedPlatform, String applicationURL,String applicatinContext,phresco_env_config phrscEnv, PhrescoUiConstantsXml phrsc, UserInfoConstants userInfo,PhrescoFrameworkData phrscData) throws ScreenException, MalformedURLException {
		this.phrscEnv=phrscEnv;
		this.userInfo = userInfo;
		this.phrsc = phrsc;
		instantiateBrowser(selectedBrowser,selectedPlatform, applicationURL, applicatinContext);
	}
	public void instantiateBrowser(String selectedBrowser,String selectedPlatform,String applicationURL, String applicationContext)
	throws ScreenException, MalformedURLException {
		URL server = new URL("http://localhost:4444/wd/hub/");
		if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_CHROME)) {
			try {
				log.info("-------------***LAUNCHING GOOGLE CHROME***--------------");
				capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("chrome");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_IE)) {
			log.info("---------------***LAUNCHING INTERNET EXPLORE***-----------");
			capabilities = new DesiredCapabilities();
			capabilities.setJavascriptEnabled(true);
			capabilities.setBrowserName("iexplorer");
		} else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_FIREFOX)) {
			log.info("-------------***LAUNCHING FIREFOX***--------------");
			capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("firefox");
		} else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_SAFARI)) {
			log.info("-------------***LAUNCHING SAFARI***--------------");
			try {
				capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("safari");
				capabilities.setCapability("safari.autostart ", true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (selectedBrowser.equalsIgnoreCase(Constants.HTML_UNIT_DRIVER)) {
			log.info("-------------***HTML_UNIT_DRIVER***--------------");
			capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("htmlunit"); 
		} else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_OPERA)) {
			log.info("-------------***LAUNCHING OPERA***--------------");
			System.out.println("******entering window maximize********");
			try {
				capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("opera");
				capabilities.setCapability("opera.autostart ",true);
			} catch (Exception e) {

				e.printStackTrace();
			}
		} else {
			throw new ScreenException(
			"------Only FireFox,InternetExplore and Chrome works-----------");
		}
		if (selectedPlatform.equalsIgnoreCase("WINDOWS")) {
			capabilities.setCapability(CapabilityType.PLATFORM,
					Platform.WINDOWS);
		} else if (selectedPlatform.equalsIgnoreCase("LINUX")) {
			capabilities.setCapability(CapabilityType.PLATFORM, Platform.LINUX);
		} else if (selectedPlatform.equalsIgnoreCase("MAC")) {
			capabilities.setCapability(CapabilityType.PLATFORM, Platform.MAC);
		}
		driver = new RemoteWebDriver(server, capabilities);
		driver.get(applicationURL + applicationContext);
		setBrowserResolution();
	}

	public void setBrowserResolution() {
		resolution = this.phrscEnv.RESOLUTION;
		if (resolution != null) {
			String[] tokens = resolution.split("x");
			String resolutionX = tokens[0];
			String resolutionY = tokens[1];
			int Xpath = Integer.parseInt(resolutionX);
			int Ypath = Integer.parseInt(resolutionY);
			Dimension screenResolution = new Dimension(Xpath, Ypath);
			driver.manage().window().setSize(screenResolution);
		} else {
			driver.manage().window().maximize();
		}
	}

	public void closeBrowser() {
		log.info("-------------***BROWSER CLOSING***--------------");
		if (driver != null) {
			driver.quit();
			if (chromeService != null) {
			}
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
	throws Exception {
		try {
			By by = null;
			log.info("Entering:--------waitForElementPresent()--------");
			if (locator.startsWith("//")) {
				log.info("Entering:--------Xpath checker--------");
				by = By.xpath(locator);
			} else {
				log.info("Entering:--------Non-Xpath checker----------------");
				by = By.id(locator);
			}
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(presenceOfElementLocated(by));
		}
		catch (Exception e) {
			log.info("presenceOfElementLocated" + e.getMessage());
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			File screenshot = ((TakesScreenshot)augmentedDriver).
			getScreenshotAs(OutputType.FILE);
			try{
				FileUtils.copyFile(screenshot,
						new File(GetCurrentDir.getCurrentDirectory() + "\\"
								+ methodName + ".png"));
			}
			catch (Exception e1) {
				log.info("presenceOfElementLocated" + e1.getMessage());
			}
			Assert.assertNull(e);
		}
	}

	Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
		log.info("Entering:------presenceOfElementLocated()-----Start");
		return new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		};
	}

	public void takesScreenshot(String methodName) {

		log.info("Entering:::::::::::takesScreenshot");
		File scrFile = ((TakesScreenshot) driver)
		.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile,
					new File(GetCurrentDir.getCurrentDirectory() + "\\"
							+ methodName + ".png"));
		} catch (Exception e1) {
			log.info("failureTestMethod" + e1.getMessage());
		}

	}
	public void selectText(WebElement element, String TextValue)
	throws ScreenException {
		log.info("Entering:----------get Select Text Webelement----------");
		try {
			Select selObj = new Select(element);
			selObj.selectByVisibleText(TextValue);
		} catch (Throwable t) {
			log.info("Entering:---------Exception in SelectextWebElement()--------");
			t.printStackTrace();
		}
	}


	public void loginPage(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@loginPage::****** executing ****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.LOGIN_USERNAME, methodName);
			getXpathWebElement(phrsc.LOGIN_USERNAME);
			click();
			sendKeys(userInfo.USERNAME);	

			Thread.sleep(2000);
			waitForElementPresent(phrsc.LOGIN_PASSWORD, methodName);
			getXpathWebElement(phrsc.LOGIN_PASSWORD);
			click();
			sendKeys(userInfo.PASSWORD);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.LOGIN_BUTTON, methodName);
			getXpathWebElement(phrsc.LOGIN_BUTTON);
			click();			

			Thread.sleep(2000);
			waitForElementPresent(phrsc.USER_NAME, methodName);
			getXpathWebElement(phrsc.USER_NAME);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADVANCE_UI, methodName);
			getXpathWebElement(phrsc.ADVANCE_UI);
			click();
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}


	public  void importApplicationHeadRevisionWithCredential(String methodName,ImportApplicationConstantsXml importApp) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@importApplicationHeadRevisionWithCredential::****** executing ****");
		try {
			Thread.sleep(5000);	
			waitForElementPresent(phrsc.PROJECT_BUTTON,methodName);	
			getXpathWebElement(phrsc.PROJECT_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.IMPORT_APPLICATION, methodName);
			getXpathWebElement(phrsc.IMPORT_APPLICATION);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_TYPE, methodName);
			getXpathWebElement(phrsc.REPO_TYPE);
			selectText(element,importApp.SVN);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_URL, methodName);
			getXpathWebElement(phrsc.REPO_URL);
			clear();
			sendKeys(importApp.SVN_REPO_URL);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_IMPORT_UPDATE_APPLICATION, methodName);
			getXpathWebElement(phrsc.REPO_IMPORT_UPDATE_APPLICATION);
			click();

			Thread.sleep(30000);


		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}


	public  void importApplicationHeadRevisionWithOtherCredential(String methodName,ImportApplicationConstantsXml importApp) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@importApplicationHeadRevisionWithOtherCredential::****** executing ****");
		try {
			Thread.sleep(5000);	
			waitForElementPresent(phrsc.PROJECT_BUTTON,methodName);	
			getXpathWebElement(phrsc.PROJECT_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.IMPORT_APPLICATION, methodName);
			getXpathWebElement(phrsc.IMPORT_APPLICATION);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_TYPE, methodName);
			getXpathWebElement(phrsc.REPO_TYPE);
			selectText(element,importApp.SVN);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_URL, methodName);
			getXpathWebElement(phrsc.REPO_URL);
			clear();
			sendKeys(importApp.SVN_REPO_URL);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_CREDENTIAL, methodName);
			getXpathWebElement(phrsc.REPO_CREDENTIAL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_USERNAME, methodName);
			getXpathWebElement(phrsc.REPO_USERNAME);
			sendKeys(importApp.OTHER_USERNAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_PASSWORD, methodName);
			getXpathWebElement(phrsc.REPO_PASSWORD);
			sendKeys(importApp.OTHER_PASSWORD);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_IMPORT_UPDATE_APPLICATION, methodName);
			getXpathWebElement(phrsc.REPO_IMPORT_UPDATE_APPLICATION);
			click();

			Thread.sleep(3000);

			Thread.sleep(30000);


		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}

	public  void importApplicationRevisionWithCredential(String methodName,ImportApplicationConstantsXml importApp) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@importApplicationRevisionWithCredential::****** executing ****");
		try {
			Thread.sleep(5000);	
			waitForElementPresent(phrsc.PROJECT_BUTTON,methodName);	
			getXpathWebElement(phrsc.PROJECT_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.IMPORT_APPLICATION, methodName);
			getXpathWebElement(phrsc.IMPORT_APPLICATION);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_TYPE, methodName);
			getXpathWebElement(phrsc.REPO_TYPE);
			selectText(element,importApp.SVN);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_URL, methodName);
			getXpathWebElement(phrsc.REPO_URL);
			clear();
			sendKeys(importApp.SVN_REPO_URL);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_REVISION, methodName);
			getXpathWebElement(phrsc.REPO_REVISION);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_REVISION_VALUE, methodName);
			getXpathWebElement(phrsc.REPO_REVISION_VALUE);
			sendKeys(importApp.SVN_REVISION);				

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_IMPORT_UPDATE_APPLICATION, methodName);
			getXpathWebElement(phrsc.REPO_IMPORT_UPDATE_APPLICATION);
			click();

			Thread.sleep(30000);



		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}


	public  void importApplicationRevisionWithOtherCredential(String methodName,ImportApplicationConstantsXml importApp) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@importApplicationRevisionWithOtherCredential::****** executing ****");
		try {
			Thread.sleep(5000);	
			waitForElementPresent(phrsc.PROJECT_BUTTON,methodName);	
			getXpathWebElement(phrsc.PROJECT_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.IMPORT_APPLICATION, methodName);
			getXpathWebElement(phrsc.IMPORT_APPLICATION);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_TYPE, methodName);
			getXpathWebElement(phrsc.REPO_TYPE);
			selectText(element,importApp.SVN);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_URL, methodName);
			getXpathWebElement(phrsc.REPO_URL);
			clear();
			sendKeys(importApp.SVN_REPO_URL);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_CREDENTIAL, methodName);
			getXpathWebElement(phrsc.REPO_CREDENTIAL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_USERNAME, methodName);
			getXpathWebElement(phrsc.REPO_USERNAME);
			sendKeys(importApp.OTHER_USERNAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_PASSWORD, methodName);
			getXpathWebElement(phrsc.REPO_PASSWORD);
			sendKeys(importApp.OTHER_PASSWORD);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_REVISION, methodName);
			getXpathWebElement(phrsc.REPO_REVISION);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_REVISION_VALUE, methodName);
			getXpathWebElement(phrsc.REPO_REVISION_VALUE);
			sendKeys(importApp.SVN_REVISION);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_IMPORT_UPDATE_APPLICATION, methodName);
			getXpathWebElement(phrsc.REPO_IMPORT_UPDATE_APPLICATION);
			click();

			Thread.sleep(30000);


		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}

	public  void importApplicationRevisionAndOtherCredential(String methodName,ImportApplicationConstantsXml importApp) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@importApplicationRevisionAndOtherCredential::****** executing ****");
		try {
			Thread.sleep(5000);	
			waitForElementPresent(phrsc.PROJECT_BUTTON,methodName);	
			getXpathWebElement(phrsc.PROJECT_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.IMPORT_APPLICATION, methodName);
			getXpathWebElement(phrsc.IMPORT_APPLICATION);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_TYPE, methodName);
			getXpathWebElement(phrsc.REPO_TYPE);
			selectText(element,importApp.SVN);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_URL, methodName);
			getXpathWebElement(phrsc.REPO_URL);
			clear();
			sendKeys(importApp.SVN_REPO_URL);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_CREDENTIAL, methodName);
			getXpathWebElement(phrsc.REPO_CREDENTIAL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_USERNAME, methodName);
			getXpathWebElement(phrsc.REPO_USERNAME);
			sendKeys(importApp.OTHER_USERNAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_PASSWORD, methodName);
			getXpathWebElement(phrsc.REPO_PASSWORD);
			sendKeys(importApp.OTHER_PASSWORD);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_REVISION, methodName);
			getXpathWebElement(phrsc.REPO_REVISION);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_REVISION_VALUE, methodName);
			getXpathWebElement(phrsc.REPO_REVISION_VALUE);
			sendKeys(importApp.SVN_REVISION);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_IMPORT_UPDATE_APPLICATION, methodName);
			getXpathWebElement(phrsc.REPO_IMPORT_UPDATE_APPLICATION);
			click();

			Thread.sleep(30000);


		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}


	public  void importApplicationGithub(String methodName,ImportApplicationConstantsXml importApp) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@importApplicationGithub::****** executing ****");
		try {
			Thread.sleep(5000);	
			waitForElementPresent(phrsc.PROJECT_BUTTON,methodName);	
			getXpathWebElement(phrsc.PROJECT_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.IMPORT_APPLICATION, methodName);
			getXpathWebElement(phrsc.IMPORT_APPLICATION);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_TYPE, methodName);
			getXpathWebElement(phrsc.REPO_TYPE);
			selectText(element,importApp.GITHUB);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_URL, methodName);
			getXpathWebElement(phrsc.REPO_URL);
			clear();
			sendKeys(importApp.GITHUB_URL);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_USERNAME, methodName);
			getXpathWebElement(phrsc.REPO_USERNAME);
			sendKeys(importApp.OTHER_USERNAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_PASSWORD, methodName);
			getXpathWebElement(phrsc.REPO_PASSWORD);
			sendKeys(importApp.OTHER_PASSWORD);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_IMPORT_UPDATE_APPLICATION, methodName);
			getXpathWebElement(phrsc.REPO_IMPORT_UPDATE_APPLICATION);
			click();				
			Thread.sleep(60000);


		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}


	public boolean isTextPresentVerify(String text) throws InterruptedException, ScreenException {

		if (text!= null){
			String build_failure="Invalid URL";
			String Project_Exists_Msg="Project Already Exists";

			for(int i=0;i<=500;i++){
				if(driver.findElement(By.tagName("body")).getText().contains(text)){
					break;

				}
				else{
					if(i==600){
						throw new RuntimeException("---- Time out for finding the Text----");
					}
					else if(driver.findElement(By.tagName("body")).getText().contains(build_failure)){
						System.out.println("*****Project Imported failed*****");
						throw new ScreenException("*****Project Imported Failed*****");

					}
					else if(driver.findElement(By.tagName("body")).getText().contains(Project_Exists_Msg))
					{
						System.out.println("*****Project Already Exists*****");
						throw new ScreenException("*****Project Already Exists*****");

					}
					Thread.sleep(1000);
				}
			}

		}
		else
		{
			throw new RuntimeException("---- Text not existed----");
		}

		return true;

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
	public void type(String text)throws ScreenException{
		log.info("Entering:********enterText operation start********");
		try{
			clear();
			element.sendKeys(text);

		}catch(Throwable t){
			t.printStackTrace();
		}
		log.info("Entering:********enterText operation end********");
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
	public boolean isTextPresent(String text)
	{
		if(text!=null)
		{
			boolean value=driver.findElement(By.tagName("body")).getText().contains(text);
			System.out.println("--------TextCheck value---->"+text+"------------Result is-------------"+value); 
			AssertJUnit.assertTrue(value);
			return value;
		}
		else
		{
			throw new RuntimeException("---- Text not present----");
		}

	}
}

