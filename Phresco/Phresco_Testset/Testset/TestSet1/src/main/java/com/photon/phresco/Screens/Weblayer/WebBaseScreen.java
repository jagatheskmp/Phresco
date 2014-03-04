package com.photon.phresco.Screens.Weblayer;

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
import org.openqa.selenium.chrome.ChromeDriverService;
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
import com.photon.phresco.uiconstants.HTML5JQueryMobileWidgetConstantsXml;
import com.photon.phresco.uiconstants.HTML5MultichannelJQueryWidgetConstantsXml;
import com.photon.phresco.uiconstants.HTML5MultichannelYUIWidgetConstantsxml;
import com.photon.phresco.uiconstants.HTML5YUIMobileWidgetXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class WebBaseScreen {

	private  WebDriver driver;
	private ChromeDriverService chromeService;
	private Log log = LogFactory.getLog("BaseScreen");
	private  WebElement element;	
	DesiredCapabilities capabilities;
	private PhrescoUiConstantsXml phrsc;
	private  phresco_env_config phrscEnv;
	private UserInfoConstants userInfo;
	private String resolution = null;

	public WebBaseScreen() {

	}

	public WebBaseScreen(String selectedBrowser, String selectedPlatform, String applicationURL,
			String applicatinContext,phresco_env_config phrscEnv, PhrescoUiConstantsXml phrsc, UserInfoConstants userInfo,
			PhrescoFrameworkData phrscData) throws ScreenException, MalformedURLException {
		this.phrscEnv=phrscEnv;
		this.userInfo = userInfo;
		this.phrsc = phrsc;

		instantiateBrowser(selectedBrowser,selectedPlatform, applicationURL, applicatinContext);

	}

	public void instantiateBrowser(String selectedBrowser,String selectedPlatform,
			String applicationURL, String applicationContext)
	throws ScreenException, MalformedURLException {
		URL server = new URL("http://localhost:4444/wd/hub/");
		if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_CHROME)) {
			try {
				log.info("---------------***LAUNCHING GOOGLE CHROME***-----------");
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
		}
		else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_SAFARI)) {
			log.info("-------------***LAUNCHING SAFARI***--------------");
			try {

				capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("safari");
				capabilities.setCapability("safari.autostart ", true);
				System.out.println("-----------checking the SAFARI-------");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (selectedBrowser.equalsIgnoreCase(Constants.HTML_UNIT_DRIVER)) {
			log.info("-------------***HTML_UNIT_DRIVER***--------------");
			capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("htmlunit"); 
			System.out.println("-----------checking the HTML_UNIT_DRIVER-------");
		}
		else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_OPERA)) {
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
			By by=null;
			log.info("Entering:--------waitForElementPresent()--------");
			if(locator.startsWith("//")){
				log.info("Entering:--------Xpath checker--------");
				by = By.xpath(locator);	
			}else{
				log.info("Entering:--------Non-Xpath checker----------------");
				by=By.id(locator);
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

	public void selectText(WebElement element,String TextValue) throws ScreenException {
		log.info("Entering:----------get Select Text Webelement----------");
		try {
			Select selObj=new Select(element);
			selObj.selectByVisibleText(TextValue);
		} catch (Throwable t) {
			log.info("Entering:---------Exception in SelectextWebElement()--------");
			t.printStackTrace();
		}
	}

	public  void loginPage(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@testLoginPage scenario****");
		try {
			Thread.sleep(2000);	
			waitForElementPresent(phrsc.LOGIN_USERNAME,methodName);
			getXpathWebElement(phrsc.LOGIN_USERNAME);			
			click();
			sendKeys(userInfo.USERNAME);
			Thread.sleep(2000);

			waitForElementPresent(phrsc.LOGIN_PASSWORD,methodName);
			getXpathWebElement(phrsc.LOGIN_PASSWORD);			
			click();
			sendKeys(userInfo.PASSWORD);
			Thread.sleep(2000);

			waitForElementPresent(phrsc.LOGIN_BUTTON,methodName);
			getXpathWebElement(phrsc.LOGIN_BUTTON);
			click();
			Thread.sleep(2000);

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

	/**************************JQUERYMOBILEWIDGET PROJECT******************************/

	public  void jQueryMobileWidgetProjectHelloWorldCreate(String methodName,HTML5JQueryMobileWidgetConstantsXml jQueryMobWidgetConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@jQueryMobileWidgetProjectHelloWorldCreate scenario****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.PROJECT_BUTTON,methodName);
			getXpathWebElement(phrsc.PROJECT_BUTTON);
			click();

			Thread.sleep(2000);		
			waitForElementPresent(phrsc.ADD_PROJECT_BUTTON,methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_NAME);
			click();
			clear();
			sendKeys(jQueryMobWidgetConst.JQUERY_MOB_WIDGET_HELLOWORLD_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			clear();
			sendKeys(jQueryMobWidgetConst.JQUERY_MOB_WIDGET_HELLOWORLD_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_CHECKBOX);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_DROPDOWN);
			selectText(element,jQueryMobWidgetConst.WEB_LAYER_HTML5_VALUE);

			Thread.sleep(2000);			
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_DROPDOWN);
			selectText(element,jQueryMobWidgetConst.JQUERY_MOB_WIDGET_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_VERSION, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_VERSION);
			selectText(element, jQueryMobWidgetConst.JQUERY_MOB_WIDGET_VERSION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(jQueryMobWidgetConst.JQUERY_MOB_WIDGET_HELLOWORLD_NAME, methodName);
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void jQueryMobileWidgetProjectHelloWorldEditApplication(String methodName,HTML5JQueryMobileWidgetConstantsXml jQueryMobWidgetConst ) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@jQueryMobileWidgetProjectHelloWorldEditApplication scenario****");
		try {							
			Thread.sleep(2000);
			waitForElementPresent(jQueryMobWidgetConst.JQUERY_MOB_WIDGET_HELLOWORLD_EDIT_LINK,methodName);
			getXpathWebElement(jQueryMobWidgetConst.JQUERY_MOB_WIDGET_HELLOWORLD_EDIT_LINK);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element,jQueryMobWidgetConst.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.APP_INFO_SERVER_CHECK_ALL, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_CHECK_ALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_DROPDOWN);
			selectText(element,jQueryMobWidgetConst.WIDGET_SERVER_VALUE);

			Thread.sleep(2000);			
			waitForElementPresent(jQueryMobWidgetConst.WIDGET_SERVER_VERSION, methodName);
			getXpathWebElement(jQueryMobWidgetConst.WIDGET_SERVER_VERSION);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			click();
			Thread.sleep(5000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void jQueryMobileWidgetProjectHelloWorldAddFeature(String methodName,HTML5JQueryMobileWidgetConstantsXml jQueryMobWidgetConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@jQueryMobileWidgetProjectHelloWorldAddFeature scenario****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.PROJECT_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.PROJECT_FINISH_BUTTON);
			click();
			Thread.sleep(2000);
			boolean updateSuccess = updateSuccessFailureLoop();
			System.out.println("updateSuccess::::"+updateSuccess);
			Assert.assertTrue(updateSuccess);
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void jQueryMobileWidgetProjectEshopCreate(String methodName,HTML5JQueryMobileWidgetConstantsXml jQueryMobWidgetConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("***********@jQueryMobileWidgetProjectEshopCreate Scneraio*******************");
		try {
			Thread.sleep(5000);	
			waitForElementPresent(phrsc.PROJECT_BUTTON,methodName);
			getXpathWebElement(phrsc.PROJECT_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_BUTTON,methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_NAME);
			click();
			clear();
			sendKeys(jQueryMobWidgetConst.JQUERY_MOB_WIDGET_ESHOP_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			clear();
			sendKeys(jQueryMobWidgetConst.JQUERY_MOB_WIDGET_ESHOP_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_CHECKBOX);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_DROPDOWN);
			selectText(element,jQueryMobWidgetConst.WEB_LAYER_HTML5_VALUE);

			Thread.sleep(2000);		
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_DROPDOWN);
			selectText(element,jQueryMobWidgetConst.JQUERY_MOB_WIDGET_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_VERSION, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_VERSION);
			selectText(element, jQueryMobWidgetConst.JQUERY_MOB_WIDGET_VERSION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(jQueryMobWidgetConst.JQUERY_MOB_WIDGET_ESHOP_NAME, methodName);
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void jQueryMobileWidgetProjectEshopEditApplication(String methodName,HTML5JQueryMobileWidgetConstantsXml jQueryMobWidgetConst ) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@jQueryMobileWidgetProjectEshopEditApplication scenario****");
		try {

			Thread.sleep(2000);			
			waitForElementPresent(jQueryMobWidgetConst.JQUERY_MOB_WIDGET_ESHOP_EDIT_LINK,methodName);
			getXpathWebElement(jQueryMobWidgetConst.JQUERY_MOB_WIDGET_ESHOP_EDIT_LINK);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, jQueryMobWidgetConst.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN);
			selectText(element,jQueryMobWidgetConst.PILOT_PROJECT_ESHOP);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			click();
			Thread.sleep(5000);

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void jQueryMobileWidgetProjectEshopAddFeature(String methodName,HTML5JQueryMobileWidgetConstantsXml jQueryMobWidgetConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@jQueryMobileWidgetProjectEshopAddFeature scenario****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.PROJECT_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.PROJECT_FINISH_BUTTON);
			click();
			Thread.sleep(2000);
			boolean updateSuccess = updateSuccessFailureLoop();
			System.out.println("updateSuccess::::"+updateSuccess);
			Assert.assertTrue(updateSuccess);
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void JQueryMobileWidgetProjectAddConfigurationServer(String methodName,HTML5JQueryMobileWidgetConstantsXml jQueryMobWidgetConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@JQueryMobileWidgetProjectEshopAddConfigurationServer  scenario****");
		try {

			Thread.sleep(5000);
			waitForElementPresent(phrsc.MENU_TAB, methodName);
			getXpathWebElement(phrsc.MENU_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TAB,methodName);
			getXpathWebElement(phrsc.CONFIG_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ADD_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_ADD_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_NAME,methodName);
			getXpathWebElement(phrsc.CONFIG_NAME);
			click();
			clear();
			sendKeys(jQueryMobWidgetConst.CONFIG_SERVER_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC,methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(jQueryMobWidgetConst.CONFIG_SERVER_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,jQueryMobWidgetConst.CONFIG_SERVER_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,jQueryMobWidgetConst.CONFIG_SERVER_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_SAVE_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element,jQueryMobWidgetConst.CONFIG_SERVER_PROTOCOL_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST,methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(jQueryMobWidgetConst.CONFIG_SERVER_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT,methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(jQueryMobWidgetConst.CONFIG_SERVER_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_LOCATION,methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_LOCATION);
			click();
			clear();
			sendKeys(jQueryMobWidgetConst.CONFIG_SERVER_DEPLOY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_OK, methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_OK);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT, methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);
			click();
			clear();
			if(methodName=="testJQueryMobileWidgetEshopProjectAddConfigurationServer")
			{
				sendKeys(jQueryMobWidgetConst.JQUERY_MOB_WIDGET_ESHOP_SERVER_CONTEXT);
				Thread.sleep(2000);
			}
			else if(methodName=="testJQueryMobileWidgetHelloWorldProjectAddConfigurationServer")
			{
				sendKeys(jQueryMobWidgetConst.JQUERY_MOB_WIDGET_HW_SERVER_CONTEXT);
				Thread.sleep(2000);
			}

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_SAVE_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_SAVE_BUTTON);
			click();
			Thread.sleep(2000);
			boolean configServerTestResult = configServerSuccessFailureLoop();
			System.out.println("configServerTestResult::::::::"+configServerTestResult);
			Assert.assertTrue(configServerTestResult);
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}


	public  void JQueryMobileWidgetProjectEshopAddConfigurationWebService(String methodName,HTML5JQueryMobileWidgetConstantsXml jQueryMobWidgetConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@JQueryMobileWidgetProjectEshopAddConfigurationWebService  scenario****");
		try {		
			Thread.sleep(5000);
			waitForElementPresent(phrsc.CONFIG_ADD_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_ADD_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_NAME,methodName);
			getXpathWebElement(phrsc.CONFIG_NAME);
			click();
			clear();
			sendKeys(jQueryMobWidgetConst.CONFIG_WEBSERVICE_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC,methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(jQueryMobWidgetConst.CONFIG_WEBSERVICE_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,jQueryMobWidgetConst.CONFIG_WEBSERVICE_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,jQueryMobWidgetConst.CONFIG_WEBSERVICE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element,jQueryMobWidgetConst.CONFIG_WEBSERVICE_PROTOCAOL_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST,methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(jQueryMobWidgetConst.CONFIG_WEBSERVICE_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT,methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(jQueryMobWidgetConst.CONFIG_WEBSERVICE_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT,methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);
			click();
			clear();
			sendKeys(jQueryMobWidgetConst.JQUERY_MOB_WIDGET_ESHOP_WEBSERVICE_CONTEXT);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.CONFIG_SAVE_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_SAVE_BUTTON);
			click();
			Thread.sleep(2000);
			boolean configWebServiceTestResult = configWebServiceSuccessFailureLoop();
			System.out.println("configWebServiceTestResult::::::::"+configWebServiceTestResult);
			Assert.assertTrue(configWebServiceTestResult);
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void multiJQueryWidgetProjectHelloWorldCreate(String methodName, HTML5MultichannelJQueryWidgetConstantsXml multiJQuery) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@multiJQueryWidgetProjectHelloWorldCreate scenario****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.PROJECT_BUTTON,methodName);
			getXpathWebElement(phrsc.PROJECT_BUTTON);
			click();

			Thread.sleep(2000);		
			waitForElementPresent(phrsc.ADD_PROJECT_BUTTON,methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_NAME);
			click();
			clear();
			sendKeys(multiJQuery.MULTICHANNEL_JQUERY_WIDGET_HELLOWORLD_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			clear();
			sendKeys(multiJQuery.MULTICHANNEL_JQUERY_WIDGET_HELLOWORLD_PROJECT_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_CHECKBOX);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_DROPDOWN);
			selectText(element,multiJQuery.HTML_VALUE);

			Thread.sleep(2000);			
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_DROPDOWN);
			selectText(element,multiJQuery.MULTICHANNEL_JQUERY_WIDGET_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_VERSION, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_VERSION);
			selectText(element,multiJQuery.MULTICHANNEL_JQUERY_WIDGET_VERSION);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(multiJQuery.MULTICHANNEL_JQUERY_WIDGET_HELLOWORLD_PROJECT_NAME, methodName);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void multiJQueryWidgetProjectHelloWorldEditApplication(String methodName,HTML5MultichannelJQueryWidgetConstantsXml multiJQuery) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@multiJQueryWidgetProjectHelloWorldEditApplication scenario****");
		try {						
			Thread.sleep(2000);
			waitForElementPresent(multiJQuery.MULTICHANNEL_JQUERY_WIDGET_HELLOWORLD_LINK,methodName);
			getXpathWebElement(multiJQuery.MULTICHANNEL_JQUERY_WIDGET_HELLOWORLD_LINK);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);			
			selectText(element, multiJQuery.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.APP_INFO_SERVER_CHECK_ALL, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_CHECK_ALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_DROPDOWN);
			selectText(element,multiJQuery.WIDGET_SERVER_VALUE);

			Thread.sleep(2000);			
			waitForElementPresent(multiJQuery.WIDGET_SERVER_VERSION, methodName);
			getXpathWebElement(multiJQuery.WIDGET_SERVER_VERSION);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			click();
			Thread.sleep(5000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void multiJQueryWidgetProjectHelloWorldAddFeature(String methodName,HTML5MultichannelJQueryWidgetConstantsXml multiJQuery) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@multiJQueryWidgetProjectHelloWorldAddFeature scenario****");
		try {			
			Thread.sleep(5000);
			waitForElementPresent(phrsc.PROJECT_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.PROJECT_FINISH_BUTTON);
			click();

			Thread.sleep(2000);
			boolean updateSuccess = updateSuccessFailureLoop();
			System.out.println("updateSuccess::::"+updateSuccess);
			Assert.assertTrue(updateSuccess);
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void multiJQueryWidgetProjectEshopCreate(String methodName, HTML5MultichannelJQueryWidgetConstantsXml multiJQuery) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@multiJQueryWidgetProjectEshopCreate scenario****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.PROJECT_BUTTON,methodName);
			getXpathWebElement(phrsc.PROJECT_BUTTON);
			click();

			Thread.sleep(2000);		
			waitForElementPresent(phrsc.ADD_PROJECT_BUTTON,methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_NAME);
			click();
			clear();
			sendKeys(multiJQuery.MULTICHANNEL_JQUERY_WIDGET_ESHOP_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			clear();
			sendKeys(multiJQuery.MULTICHANNEL_JQUERY_WIDGET_ESHOP_PROJECT_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_CHECKBOX);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_DROPDOWN);
			selectText(element,multiJQuery.HTML_VALUE);

			Thread.sleep(2000);			
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_DROPDOWN);
			selectText(element,multiJQuery.MULTICHANNEL_JQUERY_WIDGET_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_VERSION, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_VERSION);
			selectText(element, multiJQuery.MULTICHANNEL_JQUERY_WIDGET_VERSION);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(multiJQuery.MULTICHANNEL_JQUERY_WIDGET_ESHOP_PROJECT_NAME, methodName);
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void multiJQueryWidgetProjectEshopEditApplication(String methodName,HTML5MultichannelJQueryWidgetConstantsXml multiJQuery) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@multiJQueryWidgetProjectEshopEditApplication scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(multiJQuery.MULTICHANNEL_JQUERY_WIDGET_ESHOP_LINK,methodName);
			getXpathWebElement(multiJQuery.MULTICHANNEL_JQUERY_WIDGET_ESHOP_LINK);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, multiJQuery.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN);
			selectText(element,multiJQuery.PILOT_PROJECT_ESHOP_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			click();
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void multiJQueryWidgetProjectEshopAddFeature(String methodName,HTML5MultichannelJQueryWidgetConstantsXml multiJQuery) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@multiJQueryWidgetProjectEshopAddFeature  scenario****");
		try {			
			Thread.sleep(5000);
			waitForElementPresent(phrsc.PROJECT_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.PROJECT_FINISH_BUTTON);
			click();

			Thread.sleep(2000);
			boolean updateSuccess = updateSuccessFailureLoop();
			System.out.println("updateSuccess::::"+updateSuccess);
			Assert.assertTrue(updateSuccess);
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void multiJQueryWidgetProjectAddConfigurationServer(String methodName,HTML5MultichannelJQueryWidgetConstantsXml multiJQuery) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@multiJQueryWidgetProjectAddConfigurationServerscenario****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.MENU_TAB, methodName);
			getXpathWebElement(phrsc.MENU_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TAB,methodName);
			getXpathWebElement(phrsc.CONFIG_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ADD_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_ADD_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_NAME,methodName);
			getXpathWebElement(phrsc.CONFIG_NAME);
			click();
			clear();
			sendKeys(multiJQuery.CONFIG_SERVER_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC,methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(multiJQuery.CONFIG_SERVER_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,multiJQuery.CONFIG_SERVER_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,multiJQuery.CONFIG_SERVER_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element,multiJQuery.CONFIG_SERVER_PROTOCOL_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST,methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			sendKeys(multiJQuery.CONFIG_SERVER_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT,methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(multiJQuery.CONFIG_SERVER_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_LOCATION,methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_LOCATION);
			click();
			clear();
			sendKeys(multiJQuery.CONFIG_SERVER_DEPLOY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_OK, methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_OK);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT,methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);
			click();
			clear();
			if(methodName=="testMultiJQueryWidgetHelloWorldProjectAddConfigurationServer")
			{
				sendKeys(multiJQuery.MULTICHANNEL_JQUERY_WIDGET_HELLOWORLD_SERVER_CONTEXT);
				Thread.sleep(2000);
			}
			else if(methodName=="testMultiJQueryWidgetEshopProjectAddConfigurationServer")
			{
				sendKeys(multiJQuery.MULTICHANNEL_JQUERY_WIDGET_ESHOP_SERVER_CONTEXT);
				Thread.sleep(2000);
			}

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_SAVE_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_SAVE_BUTTON);
			click();
			Thread.sleep(2000);
			boolean configServerTestResult = configServerSuccessFailureLoop();
			System.out.println("configServerTestResult::::::::"+configServerTestResult);
			Assert.assertTrue(configServerTestResult);
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void multiJQueryWidgetProjectAddConfigurationWebService(String methodName,HTML5MultichannelJQueryWidgetConstantsXml multiJQuery) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@multiJQueryWidgetProjectAddConfigurationWebService  scenario****");
		try {			
			Thread.sleep(5000);
			waitForElementPresent(phrsc.CONFIG_ADD_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_ADD_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_NAME,methodName);
			getXpathWebElement(phrsc.CONFIG_NAME);
			click();
			clear();
			sendKeys(multiJQuery.CONFIG_WEBSERVICE_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC,methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(multiJQuery.CONFIG_WEBSERVICE_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,multiJQuery.CONFIG_WEBSERVICE_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,multiJQuery.CONFIG_WEBSERVICE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element,multiJQuery.CONFIG_WEBSERVICE_PROTOCAOL_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST,methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(multiJQuery.CONFIG_WEBSERVICE_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT,methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(multiJQuery.CONFIG_WEBSERVICE_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT,methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);
			click();
			clear();
			sendKeys(multiJQuery.MULTICHANNEL_JQUERY_WIDGET_ESHOP_WEBSERVICE_CONTEXT);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.CONFIG_SAVE_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_SAVE_BUTTON);
			click();
			Thread.sleep(2000);
			boolean configWebServiceTestResult = configWebServiceSuccessFailureLoop();
			System.out.println("configWebServiceTestResult::::::::"+configWebServiceTestResult);
			Assert.assertTrue(configWebServiceTestResult);
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void multiYuiWidgetProjectHelloWorldCreate(String methodName, HTML5MultichannelYUIWidgetConstantsxml multiYui) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@multiYuiWidgetProjectHelloWorldCreate scenario****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.PROJECT_BUTTON,methodName);
			getXpathWebElement(phrsc.PROJECT_BUTTON);
			click();		

			Thread.sleep(2000);		
			waitForElementPresent(phrsc.ADD_PROJECT_BUTTON,methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_NAME);
			click();
			clear();
			sendKeys(multiYui.MULTICHANNEL_YUI_WIDGET_HELLOWORLD_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			clear();
			sendKeys(multiYui.MULTICHANNEL_YUI_WIDGET_HELLOWORLD_PROJECT_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_CHECKBOX);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_DROPDOWN);
			selectText(element,multiYui.HTML_VALUE);

			Thread.sleep(2000);			
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_DROPDOWN);
			selectText(element,multiYui.MULTICHANNEL_YUI_WIDGET_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_VERSION, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_VERSION);
			selectText(element, multiYui.MULTICHANNEL_YUI_WIDGET_VERSION);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(multiYui.MULTICHANNEL_YUI_WIDGET_HELLOWORLD_PROJECT_NAME, methodName);
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void multiYuiWidgetProjectHelloWorldEditApplication(String methodName,HTML5MultichannelYUIWidgetConstantsxml multiYui) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@multiYuiWidgetProjectHelloWorldEditApplication scenario****");
		try {					
			Thread.sleep(2000);
			waitForElementPresent(multiYui.MULTICHANNEL_YUI_WIDGET_HELLOWORLD_LINK,methodName);
			getXpathWebElement(multiYui.MULTICHANNEL_YUI_WIDGET_HELLOWORLD_LINK);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, multiYui.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.APP_INFO_SERVER_CHECK_ALL, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_CHECK_ALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_DROPDOWN);
			selectText(element,multiYui.WIDGET_SERVER_VALUE);

			Thread.sleep(2000);			
			waitForElementPresent(multiYui.WIDGET_SERVER_VERSION, methodName);
			getXpathWebElement(multiYui.WIDGET_SERVER_VERSION);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			click();
			Thread.sleep(5000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void multiYuiWidgetProjectHelloWorldAddFeature(String methodName,HTML5MultichannelYUIWidgetConstantsxml multiYui) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@multiYuiWidgetProjectHelloWorldAddFeature scenario****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.PROJECT_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.PROJECT_FINISH_BUTTON);
			click();
			Thread.sleep(2000);
			boolean updateSuccess = updateSuccessFailureLoop();
			System.out.println("updateSuccess::::"+updateSuccess);
			Assert.assertTrue(updateSuccess);
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void multiYuiWidgetProjectEshopCreate(String methodName, HTML5MultichannelYUIWidgetConstantsxml multiYui) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@multiYuiWidgetProjectEshopCreate scenario****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.PROJECT_BUTTON,methodName);
			getXpathWebElement(phrsc.PROJECT_BUTTON);
			click();			

			Thread.sleep(2000);		
			waitForElementPresent(phrsc.ADD_PROJECT_BUTTON,methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_NAME);
			click();
			clear();
			sendKeys(multiYui.MULTICHANNEL_YUI_WIDGET_ESHOP_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			clear();
			sendKeys(multiYui.MULTICHANNEL_YUI_WIDGET_ESHOP_PROJECT_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_CHECKBOX);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_DROPDOWN);
			selectText(element,multiYui.HTML_VALUE);

			Thread.sleep(2000);			
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_DROPDOWN);
			selectText(element,multiYui.MULTICHANNEL_YUI_WIDGET_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_VERSION, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_VERSION);
			selectText(element, multiYui.MULTICHANNEL_YUI_WIDGET_VERSION);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(multiYui.MULTICHANNEL_YUI_WIDGET_ESHOP_PROJECT_NAME, methodName);
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void multiYuiWidgetProjectEshopEditApplication(String methodName,HTML5MultichannelYUIWidgetConstantsxml multiYui) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@multiYuiWidgetProjectEshopEditApplicationscenario****");
		try {						
			Thread.sleep(2000);
			waitForElementPresent(multiYui.MULTICHANNEL_YUI_WIDGET_ESHOP_LINK,methodName);
			getXpathWebElement(multiYui.MULTICHANNEL_YUI_WIDGET_ESHOP_LINK);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, multiYui.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN);
			selectText(element,multiYui.PILOT_PROJECT_ESHOP_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			click();
			Thread.sleep(5000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void multiYuiWidgetProjectEshopAddFeature(String methodName,HTML5MultichannelYUIWidgetConstantsxml multiYui) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@multiYuiWidgetProjectEshopAddFeature  scenario****");
		try {			
			Thread.sleep(5000);
			waitForElementPresent(phrsc.PROJECT_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.PROJECT_FINISH_BUTTON);
			click();
			Thread.sleep(2000);
			boolean updateSuccess = updateSuccessFailureLoop();
			System.out.println("updateSuccess::::"+updateSuccess);
			Assert.assertTrue(updateSuccess);
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}

	public  void multiYuiWidgetProjectAddConfigurationServer(String methodName,HTML5MultichannelYUIWidgetConstantsxml multiYui) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@multiYuiWidgetProjectAddConfigurationServer scenario****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.MENU_TAB, methodName);
			getXpathWebElement(phrsc.MENU_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TAB,methodName);
			getXpathWebElement(phrsc.CONFIG_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ADD_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_ADD_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_NAME,methodName);
			getXpathWebElement(phrsc.CONFIG_NAME);
			click();
			clear();
			sendKeys(multiYui.CONFIG_SERVER_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC,methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(multiYui.CONFIG_SERVER_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,multiYui.CONFIG_SERVER_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,multiYui.CONFIG_SERVER_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element,multiYui.CONFIG_SERVER_PROTOCOL_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST,methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(multiYui.CONFIG_SERVER_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT,methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(multiYui.CONFIG_SERVER_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_LOCATION,methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_LOCATION);
			click();
			clear();
			sendKeys(multiYui.CONFIG_SERVER_DEPLOY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_OK, methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_OK);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT,methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);
			click();
			clear();
			if(methodName=="testMultiYuiWidgetHelloWorldProjectAddConfigurationServer")
			{
				sendKeys(multiYui.MULTICHANNEL_YUI_WIDGET_HELLOWORLD_SERVER_CONTEXT);
				Thread.sleep(2000);
			}
			else if(methodName=="testMultiYuiWidgetEshopProjectAddConfigurationServer")
			{
				sendKeys(multiYui.MULTICHANNEL_YUI_WIDGET_ESHOP_SERVER_CONTEXT);
				Thread.sleep(2000);
			}
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_SAVE_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_SAVE_BUTTON);
			click();
			Thread.sleep(2000);
			boolean configServerTestResult = configServerSuccessFailureLoop();
			System.out.println("configServerTestResult::::::::"+configServerTestResult);
			Assert.assertTrue(configServerTestResult);
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}


	public  void multiYuiWidgetProjectAddConfigurationWebService(String methodName,HTML5MultichannelYUIWidgetConstantsxml multiYui) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@multiYuiWidgetProjectAddConfigurationWebService  scenario****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.CONFIG_ADD_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_ADD_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_NAME,methodName);
			getXpathWebElement(phrsc.CONFIG_NAME);
			click();
			clear();
			sendKeys(multiYui.CONFIG_WEBSERVICE_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC,methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(multiYui.CONFIG_WEBSERVICE_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,multiYui.CONFIG_WEBSERVICE_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,multiYui.CONFIG_WEBSERVICE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element,multiYui.CONFIG_WEBSERVICE_PROTOCAOL_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST,methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(multiYui.CONFIG_WEBSERVICE_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT,methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(multiYui.CONFIG_WEBSERVICE_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT,methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);
			click();
			clear();
			sendKeys(multiYui.MULTICHANNEL_YUI_WIDGET_ESHOP_WEBSERVICE_CONTEXT);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.CONFIG_SAVE_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_SAVE_BUTTON);
			click();
			Thread.sleep(2000);
			boolean configWebServiceTestResult = configWebServiceSuccessFailureLoop();
			System.out.println("configWebServiceTestResult::::::::"+configWebServiceTestResult);
			Assert.assertTrue(configWebServiceTestResult);
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}


	public  void yuiMobileWidgetProjectHelloWorldCreate(String methodName, HTML5YUIMobileWidgetXml yuiMobile) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@yuiMobileWidgetProjectHelloWorldCreate scenario****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.PROJECT_BUTTON,methodName);
			getXpathWebElement(phrsc.PROJECT_BUTTON);
			click();	

			Thread.sleep(2000);		
			waitForElementPresent(phrsc.ADD_PROJECT_BUTTON,methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_NAME);
			click();
			clear();
			sendKeys(yuiMobile.YUI_MOBILE_WIDGET_HELLOWORLD_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			clear();
			sendKeys(yuiMobile.YUI_MOBILE_WIDGET_HELLOWORLD_PROJECT_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_CHECKBOX);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_DROPDOWN);
			selectText(element,yuiMobile.HTML_VALUE);

			Thread.sleep(2000);			
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_DROPDOWN);
			selectText(element,yuiMobile.YUI_MOBILE_WIDGET_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_VERSION, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_VERSION);
			selectText(element, yuiMobile.YUI_MOBILE_WIDGET_VERSION);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(yuiMobile.YUI_MOBILE_WIDGET_HELLOWORLD_PROJECT_NAME, methodName);
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void yuiMobileWidgetProjectHelloWorldEditApplication(String methodName,HTML5YUIMobileWidgetXml yuiMobile) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@yuiMobileWidgetProjectHelloWorldEditApplication scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(yuiMobile.YUI_MOBILE_WIDGET_HELLOWORLD_LINK,methodName);
			getXpathWebElement(yuiMobile.YUI_MOBILE_WIDGET_HELLOWORLD_LINK);
			click();

			Thread.sleep(10000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, yuiMobile.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.APP_INFO_SERVER_CHECK_ALL, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_CHECK_ALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_DROPDOWN);
			selectText(element,yuiMobile.WIDGET_SERVER_VALUE);

			Thread.sleep(2000);			
			waitForElementPresent(yuiMobile.WIDGET_SERVER_VERSION, methodName);
			getXpathWebElement(yuiMobile.WIDGET_SERVER_VERSION);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			click();
			Thread.sleep(5000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void yuiMobileWidgetProjectHelloWorldAddFeature(String methodName,HTML5YUIMobileWidgetXml yuiMobile) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@yuiMobileWidgetProjectHelloWorldAddFeature scenario****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.PROJECT_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.PROJECT_FINISH_BUTTON);
			click();
			Thread.sleep(2000);
			boolean updateSuccess = updateSuccessFailureLoop();
			System.out.println("updateSuccess::::"+updateSuccess);
			Assert.assertTrue(updateSuccess);
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void yuiMobileWidgetProjectEshopCreate(String methodName, HTML5YUIMobileWidgetXml yuiMobile) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@yuiMobileWidgetProjectEshopCreate scenario****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.PROJECT_BUTTON,methodName);
			getXpathWebElement(phrsc.PROJECT_BUTTON);
			click();		

			Thread.sleep(2000);		
			waitForElementPresent(phrsc.ADD_PROJECT_BUTTON,methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_NAME);
			click();
			clear();
			sendKeys(yuiMobile.YUI_MOBILE_WIDGET_ESHOP_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			clear();
			sendKeys(yuiMobile.YUI_MOBILE_WIDGET_ESHOP_PROJECT_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_CHECKBOX);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_DROPDOWN);
			selectText(element,yuiMobile.HTML_VALUE);

			Thread.sleep(2000);			
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_DROPDOWN);
			selectText(element,yuiMobile.YUI_MOBILE_WIDGET_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_VERSION, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_VERSION);
			selectText(element, yuiMobile.YUI_MOBILE_WIDGET_VERSION);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(yuiMobile.YUI_MOBILE_WIDGET_HELLOWORLD_PROJECT_NAME, methodName);
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void yuiMobileWidgetProjectEshopEditApplication(String methodName,HTML5YUIMobileWidgetXml yuiMobile) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@yuiMobileWidgetProjectEshopEditApplication scenario****");
		try {			
			Thread.sleep(2000);		
			waitForElementPresent(yuiMobile.YUI_MOBILE_WIDGET_ESHOP_LINK,methodName);
			getXpathWebElement(yuiMobile.YUI_MOBILE_WIDGET_ESHOP_LINK);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, yuiMobile.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN);
			selectText(element,yuiMobile.PILOT_PROJECT_ESHOP_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			click();
			Thread.sleep(5000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void yuiMobileWidgetProjectEshopAddFeature(String methodName,HTML5YUIMobileWidgetXml yuiMobile) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@yuiMobileWidgetProjectEshopAddFeature scenario****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.PROJECT_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.PROJECT_FINISH_BUTTON);
			click();
			Thread.sleep(2000);
			boolean updateSuccess = updateSuccessFailureLoop();
			System.out.println("updateSuccess::::"+updateSuccess);
			Assert.assertTrue(updateSuccess);
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void yuiMobileWidgetProjectAddConfigurationServer(String methodName,HTML5YUIMobileWidgetXml yuiMobile) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@yuiMobileWidgetProjectAddConfigurationServer scenario****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.MENU_TAB, methodName);
			getXpathWebElement(phrsc.MENU_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TAB,methodName);
			getXpathWebElement(phrsc.CONFIG_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ADD_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_ADD_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_NAME,methodName);
			getXpathWebElement(phrsc.CONFIG_NAME);
			click();
			clear();
			sendKeys(yuiMobile.CONFIG_SERVER_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC,methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(yuiMobile.CONFIG_SERVER_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,yuiMobile.CONFIG_SERVER_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,yuiMobile.CONFIG_SERVER_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element,yuiMobile.CONFIG_SERVER_PROTOCOL_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST,methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(yuiMobile.CONFIG_SERVER_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT,methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(yuiMobile.CONFIG_SERVER_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_LOCATION,methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_LOCATION);
			click();
			clear();
			sendKeys(yuiMobile.CONFIG_SERVER_DEPLOY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_OK, methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_OK);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT,methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);
			click();
			clear();
			if(methodName=="testYuiMobileWidgetHelloWorldProjectAddConfigurationServer")
			{
				sendKeys(yuiMobile.YUI_MOBILE_WIDGET_HELLOWORLD_SERVER_CONTEXT);
				Thread.sleep(2000);
			}
			else if(methodName=="testYuiMobileWidgetEshopProjectAddConfigurationServer")
			{
				sendKeys(yuiMobile.YUI_MOBILE_WIDGET_ESHOP_SERVER_CONTEXT);
				Thread.sleep(2000);
			}

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_SAVE_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_SAVE_BUTTON);
			click();
			Thread.sleep(2000);
			boolean configServerTestResult = configServerSuccessFailureLoop();
			System.out.println("configServerTestResult::::::::"+configServerTestResult);
			Assert.assertTrue(configServerTestResult);
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void yuiMobileWidgetProjectAddConfigurationWebService(String methodName,HTML5YUIMobileWidgetXml yuiMobile) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@yuiMobileWidgetProjectAddConfigurationWebService  scenario****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.CONFIG_ADD_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_ADD_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_NAME,methodName);
			getXpathWebElement(phrsc.CONFIG_NAME);
			click();
			clear();
			sendKeys(yuiMobile.CONFIG_WEBSERVICE_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC,methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(yuiMobile.CONFIG_WEBSERVICE_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,yuiMobile.CONFIG_WEBSERVICE_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,yuiMobile.CONFIG_WEBSERVICE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element,yuiMobile.CONFIG_WEBSERVICE_PROTOCAOL_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST,methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(yuiMobile.CONFIG_WEBSERVICE_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT,methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(yuiMobile.CONFIG_WEBSERVICE_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT,methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);
			click();
			clear();
			sendKeys(yuiMobile.YUI_MOBILE_WIDGET_ESHOP_WEBSERVICE_CONTEXT);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.CONFIG_SAVE_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_SAVE_BUTTON);
			click();
			Thread.sleep(2000);
			boolean configWebServiceTestResult = configWebServiceSuccessFailureLoop();
			System.out.println("configWebServiceTestResult::::::::"+configWebServiceTestResult);
			Assert.assertTrue(configWebServiceTestResult);
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public void UnittestVerificationJs(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("**UnittestVerificationJs****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.QUALITY_TAB, methodName);
			getXpathWebElement(phrsc.QUALITY_TAB);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.UNIT_TAB, methodName);
			getXpathWebElement(phrsc.UNIT_TAB);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.UNIT_TEST_BUTTON, methodName);
			getXpathWebElement(phrsc.UNIT_TEST_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.TEST_AGAINST, methodName);
			getXpathWebElement(phrsc.TEST_AGAINST);
			selectText(element ,phrsc.JS_TEST_AGAINST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.RUN_UNIT_TEST_BUTTON, methodName);
			getXpathWebElement(phrsc.RUN_UNIT_TEST_BUTTON);
			click();

			boolean unitTestResult = successFailureLoop();
			System.out.println("unitTestResult:::::"+unitTestResult);
			Thread.sleep(2000);
			waitForElementPresent(phrsc.TEST_CLOSE_BUTTON, methodName);
			getXpathWebElement(phrsc.TEST_CLOSE_BUTTON).click();
			Assert.assertTrue(unitTestResult);		
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}


	public void UnittestVerificationJava(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("**UnittestVerificationJava****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.QUALITY_TAB, methodName);
			getXpathWebElement(phrsc.QUALITY_TAB);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.UNIT_TAB, methodName);
			getXpathWebElement(phrsc.UNIT_TAB);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.UNIT_TEST_BUTTON, methodName);
			getXpathWebElement(phrsc.UNIT_TEST_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.TEST_AGAINST, methodName);
			getXpathWebElement(phrsc.TEST_AGAINST);
			selectText(element ,phrsc.JAVA_TEST_AGAINST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.RUN_UNIT_TEST_BUTTON, methodName);
			getXpathWebElement(phrsc.RUN_UNIT_TEST_BUTTON);
			click();

			boolean unitTestResult = successFailureLoop();
			System.out.println("unitTestResult:::::"+unitTestResult);
			Thread.sleep(2000);
			waitForElementPresent(phrsc.TEST_CLOSE_BUTTON, methodName);
			getXpathWebElement(phrsc.TEST_CLOSE_BUTTON).click();
			Assert.assertTrue(unitTestResult);		
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void PerformanceTestForAganistServer(String methodName)
	throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("**PerformanceTestAganistServerVerification****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.PERFORMACE_TAB, methodName);
			getXpathWebElement(phrsc.PERFORMACE_TAB);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.PERFORMANCE_TEST_BUTTON, methodName);
			getXpathWebElement(phrsc.PERFORMANCE_TEST_BUTTON);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.TEST_AGAINST, methodName);
			getXpathWebElement(phrsc.TEST_AGAINST);
			sendKeys(phrsc.TEST_AGAINST_SERVER);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.TEST_NAME, methodName);
			getXpathWebElement(phrsc.TEST_NAME);
			sendKeys(phrsc.TEST_RESULT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONTEXT_NAME_XPATH, methodName);
			getXpathWebElement(phrsc.CONTEXT_NAME_XPATH);
			click();
			clear();
			sendKeys(phrsc.CONTEXT_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONTEXT_VALUE_XPATH, methodName);
			getXpathWebElement(phrsc.CONTEXT_VALUE_XPATH);
			click();
			clear();
			sendKeys(phrsc.CONTEXT_VALUE);			

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PERFORMANCE_TEST_RUN, methodName);
			getXpathWebElement(phrsc.PERFORMANCE_TEST_RUN);
			click();
			Thread.sleep(2000);

			boolean performanceTestServerResult = successFailureLoop();
			System.out.println("performanceServerTestResult:::::::::"+performanceTestServerResult);
			Thread.sleep(2000);
			waitForElementPresent(phrsc.TEST_CLOSE_BUTTON, methodName);
			getXpathWebElement(phrsc.TEST_CLOSE_BUTTON).click();
			Assert.assertTrue(performanceTestServerResult);
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void PerformanceTestForAganistWebService(String methodName)
	throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("**PerformanceTestForAganistWebService****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PERFORMACE_TAB, methodName);
			getXpathWebElement(phrsc.PERFORMACE_TAB);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.PERFORMANCE_TEST_BUTTON, methodName);
			getXpathWebElement(phrsc.PERFORMANCE_TEST_BUTTON);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.TEST_AGAINST, methodName);
			getXpathWebElement(phrsc.TEST_AGAINST);
			sendKeys(phrsc.TEST_AGAINST_WEB_SERVICE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.TEST_NAME, methodName);
			getXpathWebElement(phrsc.TEST_NAME);
			sendKeys(phrsc.TEST_RESULT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONTEXT_NAME_XPATH, methodName);
			getXpathWebElement(phrsc.CONTEXT_NAME_XPATH);
			click();
			clear();
			sendKeys(phrsc.CONTEXT_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONTEXT_VALUE_XPATH, methodName);
			getXpathWebElement(phrsc.CONTEXT_VALUE_XPATH);
			click();
			clear();
			sendKeys(phrsc.CONTEXT_VALUE);			

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PERFORMANCE_TEST_RUN, methodName);
			getXpathWebElement(phrsc.PERFORMANCE_TEST_RUN);
			click();
			Thread.sleep(2000);

			boolean performanceTestWebServiceResult = successFailureLoop();
			System.out.println("performanceTestWebServiceResult:::::::::"+performanceTestWebServiceResult);
			Thread.sleep(2000);
			waitForElementPresent(phrsc.TEST_CLOSE_BUTTON, methodName);
			getXpathWebElement(phrsc.TEST_CLOSE_BUTTON).click();
			Assert.assertTrue(performanceTestWebServiceResult);
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void LoadTestForAganistServer(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("**LoadTestForAganistServer****");
		try {			
			Thread.sleep(5000);
			waitForElementPresent(phrsc.LOAD_TAB, methodName);
			getXpathWebElement(phrsc.LOAD_TAB);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.LOAD_TEST_BUTTON, methodName);
			getXpathWebElement(phrsc.LOAD_TEST_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.TEST_AGAINST, methodName);
			getXpathWebElement(phrsc.TEST_AGAINST);
			sendKeys(phrsc.TEST_AGAINST_SERVER);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.TEST_NAME, methodName);
			getXpathWebElement(phrsc.TEST_NAME);
			sendKeys(phrsc.TEST_RESULT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONTEXT_NAME_XPATH, methodName);
			getXpathWebElement(phrsc.CONTEXT_NAME_XPATH);
			click();
			clear();
			sendKeys(phrsc.CONTEXT_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONTEXT_VALUE_XPATH, methodName);
			getXpathWebElement(phrsc.CONTEXT_VALUE_XPATH);
			click();
			clear();
			sendKeys(phrsc.CONTEXT_VALUE);	

			Thread.sleep(2000);
			waitForElementPresent(phrsc.LOAD_TEST_RUN, methodName);
			getXpathWebElement(phrsc.LOAD_TEST_RUN).click();			
			Thread.sleep(2000);

			boolean loadTestServerResult = successFailureLoop();
			System.out.println("loadTestServerResult::::::::"+loadTestServerResult);
			Thread.sleep(2000);
			waitForElementPresent(phrsc.TEST_CLOSE_BUTTON, methodName);
			getXpathWebElement(phrsc.TEST_CLOSE_BUTTON).click();	
			Assert.assertTrue(loadTestServerResult);
			Thread.sleep(3000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}


	public void LoadTestForAganistWebService(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("**LoadTestForAganistWebService****");
		try {			
			Thread.sleep(5000);
			waitForElementPresent(phrsc.LOAD_TAB, methodName);
			getXpathWebElement(phrsc.LOAD_TAB);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.LOAD_TEST_BUTTON, methodName);
			getXpathWebElement(phrsc.LOAD_TEST_BUTTON);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.TEST_AGAINST, methodName);
			getXpathWebElement(phrsc.TEST_AGAINST);
			sendKeys(phrsc.TEST_AGAINST_WEB_SERVICE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.TEST_NAME, methodName);
			getXpathWebElement(phrsc.TEST_NAME);
			sendKeys(phrsc.TEST_RESULT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONTEXT_NAME_XPATH, methodName);
			getXpathWebElement(phrsc.CONTEXT_NAME_XPATH);
			click();
			clear();
			sendKeys(phrsc.CONTEXT_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONTEXT_VALUE_XPATH, methodName);
			getXpathWebElement(phrsc.CONTEXT_VALUE_XPATH);
			click();
			clear();
			sendKeys(phrsc.CONTEXT_VALUE);	

			Thread.sleep(2000);
			waitForElementPresent(phrsc.LOAD_TEST_RUN, methodName);
			getXpathWebElement(phrsc.LOAD_TEST_RUN).click();			
			Thread.sleep(2000);

			boolean loadTestWebServiceResult = successFailureLoop();
			System.out.println("loadTestWebServiceResult::::::::"+loadTestWebServiceResult);
			Thread.sleep(2000);
			waitForElementPresent(phrsc.TEST_CLOSE_BUTTON, methodName);
			getXpathWebElement(phrsc.TEST_CLOSE_BUTTON).click();	
			Assert.assertTrue(loadTestWebServiceResult);
			Thread.sleep(3000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}


	public boolean isTextPresentBuild(String text) throws InterruptedException, ScreenException {

		if (text!= null){
			String build_failure="BUILD FAILURE";

			for(int i=0;i<50;i++){
				if(driver.findElement(By.tagName("body")).getText().contains(text)){
					break;
				}
				else{
					if(i==55){
						throw new RuntimeException("---- Time out for finding the Text----");
					}
					else if(driver.findElement(By.tagName("body")).getText().contains(build_failure)){
						System.out.println("*****BUILD FAILURE*****");

						throw new ScreenException("*****BUILD FAILURE*****");
					}
					Thread.sleep(2000);
				}
			}
		}
		else
		{
			throw new RuntimeException("---- Text not existed----");
		}
		return true;
	}

	public boolean successFailureLoop() throws InterruptedException, IOException, Exception {
		String foundString = buildStatusVerifier();
		if(foundString.equalsIgnoreCase("BUILD SUCCESS"))
		{
			return true;
		}
		else
			return false;			
	}

	private String buildStatusVerifier() {
		String BUILD_SUCCESS = "BUILD SUCCESS";
		String BUILD_FAILURE = "BUILD FAILURE";
		boolean statusStringFound = false;		
		while (!statusStringFound) { 
			if (driver.findElement(By.tagName("body")).getText().contains(BUILD_SUCCESS)) {
				return BUILD_SUCCESS;
			} else if (driver.findElement(By.tagName("body")).getText().contains(BUILD_FAILURE))  {
				return BUILD_FAILURE;
			}
		}
		return null;
	}

	public boolean configServerSuccessFailureLoop() throws InterruptedException, IOException, Exception {
		String foundString = serverStatusVerifier();
		if(foundString.equalsIgnoreCase("Configuration Server created successfully"))
		{
			return true;
		}
		else
			return false;			
	}

	private String serverStatusVerifier() {
		String CONFIG_SERVER = "Configuration Server created successfully";
		boolean statusStringFound = false;		
		while (!statusStringFound) { 
			if (driver.findElement(By.tagName("body")).getText().contains(CONFIG_SERVER)) {
				return CONFIG_SERVER;
			} 
		}
		return null;
	}

	public boolean configDbSuccessFailureLoop() throws InterruptedException, IOException, Exception {
		String foundString = dbStatusVerifier();
		if(foundString.equalsIgnoreCase("Configuration Database created successfully"))
		{
			return true;
		}
		else
			return false;			
	}

	private String dbStatusVerifier() {
		String CONFIG_DB = "Configuration Database created successfully";
		boolean statusStringFound = false;		
		while (!statusStringFound) { 
			if (driver.findElement(By.tagName("body")).getText().contains(CONFIG_DB)) {
				return CONFIG_DB;
			} 
		}
		return null;
	}

	public boolean configWebServiceSuccessFailureLoop() throws InterruptedException, IOException, Exception {
		String foundString = webServiceStatusVerifier();
		if(foundString.equalsIgnoreCase("Configuration eshopService created successfully"))
		{
			return true;
		}
		else
			return false;			
	}

	private String webServiceStatusVerifier() {
		String CONFIG_WS = "Configuration eshopService created successfully";
		boolean statusStringFound = false;		
		while (!statusStringFound) { 
			if (driver.findElement(By.tagName("body")).getText().contains(CONFIG_WS)) {
				return CONFIG_WS;
			} 
		}
		return null;
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
			element.clear();
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
	public void webLayerBuildTab(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@webLayerBuildTab::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.BUILD_TAB, methodName);
			getXpathWebElement(phrsc.BUILD_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.GENERATE_BUILD, methodName);
			getXpathWebElement(phrsc.GENERATE_BUILD);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.BUILD_NAME_XPATH, methodName);
			getXpathWebElement(phrsc.BUILD_NAME_XPATH);
			click();
			sendKeys(phrsc.BUILD_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.BUILD_NUMBER_XPATH, methodName);
			getXpathWebElement(phrsc.BUILD_NUMBER_XPATH);
			sendKeys(phrsc.BUILD_NUMBER);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.SKIP_UNIT_TEST, methodName);
			getXpathWebElement(phrsc.SKIP_UNIT_TEST);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.BUILD_BUTTON, methodName);
			getXpathWebElement(phrsc.BUILD_BUTTON);
			click();
			Thread.sleep(2000);
			boolean buildTestResult=successFailureLoop();
			System.out.println("buildTestResult:::::::"+buildTestResult);
			Assert.assertTrue(buildTestResult);
			Thread.sleep(2000);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	
	public void codeValidationSource(String methodName) throws Exception
	{
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@codeValidationSource::******executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CODE_MENU_TAB, methodName);
			getXpathWebElement(phrsc.CODE_MENU_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CODE_VALIDATE_BUTTON, methodName);
			getXpathWebElement(phrsc.CODE_VALIDATE_BUTTON);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CODE_AGAINST, methodName);
			getXpathWebElement(phrsc.CODE_AGAINST);
			selectText(element, phrsc.CODE_SOURCE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CODE_TECHNOLOGY, methodName);
			getXpathWebElement(phrsc.CODE_TECHNOLOGY);
			if(methodName == "testJQueryMobileWidgetHelloWorldProjectCodeSourceJs" || methodName == "testJQueryMobileWidgetEshopProjectCodeSourceJs")
			{
				selectText(element, phrsc.JS_TEST_AGAINST_VALUE);
			}
			if(methodName == "testJQueryMobileWidgetHelloWorldProjectCodeSourceJava" || methodName == "testJQueryMobileWidgetEshopProjectCodeSourceJava")
			{
				selectText(element, phrsc.JAVA_TEST_AGAINST_VALUE);
			}
			if(methodName == "testJQueryMobileWidgetHelloWorldProjectCodeSourceJsf" || methodName == "testJQueryMobileWidgetEshopProjectCodeSourceJsf")
			{
				selectText(element, phrsc.CODE_JSF_VALUE);
			}
			if(methodName == "testJQueryMobileWidgetHelloWorldProjectCodeSourceHtml" || methodName == "testJQueryMobileWidgetEshopProjectCodeSourceHtml")
			{
				selectText(element, phrsc.CODE_HTML_VALUE);
			}
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CODE_SKIP_UNIT_TEST, methodName);
			getXpathWebElement(phrsc.CODE_SKIP_UNIT_TEST);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CODE_OK_BUTTON, methodName);
			getXpathWebElement(phrsc.CODE_OK_BUTTON);
			click();

			Thread.sleep(2000);
			boolean codeTestResult = successFailureLoop();
			System.out.println("codeTestResult:::::"+codeTestResult);
			Assert.assertTrue(codeTestResult);
			Thread.sleep(10000);
			waitForElementPresent(phrsc.TEST_CLOSE_BUTTON, methodName);
			getXpathWebElement(phrsc.TEST_CLOSE_BUTTON);
			click();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	public void codeValidationFunctional(String methodName) throws Exception
	{
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@codeValidationFunctional::******executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CODE_MENU_TAB, methodName);
			getXpathWebElement(phrsc.CODE_MENU_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CODE_VALIDATE_BUTTON, methodName);
			getXpathWebElement(phrsc.CODE_VALIDATE_BUTTON);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CODE_AGAINST, methodName);
			getXpathWebElement(phrsc.CODE_AGAINST);
			selectText(element, phrsc.CODE_FUNCTIONAL_TEST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CODE_OK_BUTTON, methodName);
			getXpathWebElement(phrsc.CODE_OK_BUTTON);
			click();

			Thread.sleep(2000);
			boolean codeTestResult = successFailureLoop();
			System.out.println("codeTestResult:::::"+codeTestResult);
			Assert.assertTrue(codeTestResult);
			Thread.sleep(10000);
			waitForElementPresent(phrsc.TEST_CLOSE_BUTTON, methodName);
			getXpathWebElement(phrsc.TEST_CLOSE_BUTTON);
			click();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	public void webLayerDeploy(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@webLayerDeploy::******executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.DEPLOY_BUTTON, methodName);
			getXpathWebElement(phrsc.DEPLOY_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.DEPLOY_OK, methodName);
			getXpathWebElement(phrsc.DEPLOY_OK);
			click();
			Thread.sleep(2000);
			boolean deployTestResult = successFailureLoop();
			System.out.println("deployTestResult:::::"+deployTestResult);
			Assert.assertTrue(deployTestResult);
			Thread.sleep(2000);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public boolean isTextPresent(String text,String methodName) {
		if (text != null) {
			boolean value = driver.findElement(By.tagName("body")).getText()
			.contains(text);
			System.out.println("--------TextCheck value---->" + text
					+ "------------Result is-------------" + value);
			if(!value)
			{
				takesScreenshot(methodName);
			}
			Assert.assertTrue(value);
			return value;
		} else {
			throw new RuntimeException("---- Text not present----");
		}
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

	public boolean updateSuccessFailureLoop() throws InterruptedException, IOException, Exception {
		String foundString = updateStatusVerifier();
		if(foundString.equalsIgnoreCase("Application updated successfully"))
		{
			return true;
		}
		else
			return false;			
	}

	private String updateStatusVerifier() {
		String UPDATE_SUCCESS = "Application updated successfully";
		boolean statusStringFound = false;		
		while (!statusStringFound) { 
			if (driver.findElement(By.tagName("body")).getText().contains(UPDATE_SUCCESS)) {
				return UPDATE_SUCCESS;
			} 
		}
		return null;
	}


}
