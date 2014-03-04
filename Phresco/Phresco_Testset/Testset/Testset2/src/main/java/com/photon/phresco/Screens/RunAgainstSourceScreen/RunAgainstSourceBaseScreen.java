package com.photon.phresco.Screens.RunAgainstSourceScreen;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;
import com.photon.phresco.selenium.util.Constants;
import com.photon.phresco.selenium.util.GetCurrentDir;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.RunAgainstSourceConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class RunAgainstSourceBaseScreen {

	private WebDriver driver;
	private ChromeDriverService chromeService;
	private Log log = LogFactory.getLog("BaseScreen");
	private WebElement element;	
	DesiredCapabilities capabilities;
	private PhrescoUiConstantsXml phrsc;
	
	private phresco_env_config phrscEnv;
	private UserInfoConstants userInfo;

	private String resolution = null;

	public RunAgainstSourceBaseScreen() {

	}	public RunAgainstSourceBaseScreen(String selectedBrowser, String selectedPlatform, String applicationURL,String applicatinContext,phresco_env_config phrscEnv, PhrescoUiConstantsXml phrsc, UserInfoConstants userInfo,PhrescoFrameworkData phrscData) throws ScreenException, MalformedURLException {
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
	
	
	public void NodeJsWebserviceProjectHelloWorldCreate(String methodName,
			RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@testNodeJsWebserviceProjectHelloWorldCreate::******executing NodeJsWebserviceProjectHelloWorldCreate scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_BUTTON, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_NAME);
			click();
			sendKeys(runAgainstSource.NODEJS_HELLOWORLD_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			sendKeys(runAgainstSource.NODEJS_HELLOWORLD_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_CHECKALL, methodName);
			getXpathWebElement(phrsc.APP_LAYER_CHECKALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_LAYER_DROPDOWN);
			selectText(element,runAgainstSource.NODEJS_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(runAgainstSource.NODEJS_HELLOWORLD_NAME_VALUE, methodName);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}


	public void NodeJsWebserviceProjectHelloWorldEditApplication(
			String methodName, RunAgainstSourceConstantsXml runAgainstSource)
	throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@NodeJsWebserviceProjectHelloWorldEditApplication::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(runAgainstSource.NODEJS_HELLOWORLD_EDIT_APP_LINK,methodName);
			getXpathWebElement(runAgainstSource.NODEJS_HELLOWORLD_EDIT_APP_LINK);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, runAgainstSource.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_CHECK_ALL, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_CHECK_ALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_DROPDOWN);
			selectText(element,runAgainstSource.NODEJS_SERVER_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_DB_CHECK_ALL, methodName);
			getXpathWebElement(phrsc.APP_INFO_DB_CHECK_ALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_DB_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_DB_DROPDOWN);
			selectText(element,runAgainstSource.NODEJS_DB_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			click();
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void NodeJsWebserviceProjectHelloWorldAddFeature(String methodName,
			RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@NodeJsWebserviceProjectHelloWorldAddFeature::****** executing ****");
		try {

			Thread.sleep(5000);
			waitForElementPresent(phrsc.PROJECT_FINISH_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_FINISH_BUTTON);
			click();
			boolean updateTestResult = updateSuccessFailureLoop();
			System.out.println("updateTestResult::::"+updateTestResult);
			Assert.assertTrue(updateTestResult);
			Thread.sleep(50000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}


	public void NodeJsWebserviceProjectEshopCreate(String methodName,
			RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@NodeJsWebserviceProjectEshopCreate::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_BUTTON, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_NAME);
			click();
			clear();
			sendKeys(runAgainstSource.NODEJS_ESHOP_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			sendKeys(runAgainstSource.NODEJS_ESHOP_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_CHECKALL, methodName);
			getXpathWebElement(phrsc.APP_LAYER_CHECKALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_LAYER_DROPDOWN);
			sendKeys( runAgainstSource.NODEJS_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(20000);
			isTextPresent(runAgainstSource.NODEJS_ESHOP_NAME_VALUE, methodName);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void NodeJsWebserviceProjectEshopEditApplication(String methodName,
			RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@NodeJsWebserviceProjectEshopEditApplication::****** executing ****");
		try {

			Thread.sleep(2000);
			waitForElementPresent(runAgainstSource.NODEJS_ESHOP_EDIT_APP_LINK,methodName);
			getXpathWebElement(runAgainstSource.NODEJS_ESHOP_EDIT_APP_LINK);
			click();

			Thread.sleep(10000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, runAgainstSource.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN);
			selectText(element,runAgainstSource.NODEJS_PILOT_PROJECT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			click();
			Thread.sleep(2000);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void NodeJsWebserviceProjectEshopAddFeature(String methodName,
			RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@NodeJsWebserviceProjectEshopAddFeature::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_FINISH_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_FINISH_BUTTON);
			click();
			boolean updateTestResult = updateSuccessFailureLoop();
			System.out.println("updateTestResult::::"+updateTestResult);
			Assert.assertTrue(updateTestResult);
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}


	public void NodeJsAddConfigurationServer(String methodName,
			RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@NodeJsAddConfigurationServer::****** executing ****");
		try {		
			Thread.sleep(2000);
			waitForElementPresent(phrsc.MENU_TAB, methodName);
			getXpathWebElement(phrsc.MENU_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TAB, methodName);
			getXpathWebElement(phrsc.CONFIG_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ADD_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_ADD_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_NAME, methodName);
			getXpathWebElement(phrsc.CONFIG_NAME);
			click();
			clear();
			sendKeys(runAgainstSource.NODEJS_SERVICE_CONF_SERVER_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC, methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(runAgainstSource.NODEJS_SERVICE_CONF_SERVER_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE, methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,runAgainstSource.NODEJS_SERVICE_CONF_SERVER_TYPE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL, methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element,runAgainstSource.NODEJS_SERVICE_CONF_SERVER_PROTOCOL_HTTP_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST, methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(runAgainstSource.NODEJS_SERVICE_CONF_SERVER_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT, methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(runAgainstSource.NODEJS_SERVICE_CONF_SERVER_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT,	methodName);;
			getXpathWebElement(phrsc.CONFIG_CONTEXT);		
			click();
			clear();
			if (methodName == "testNodeJsHelloWorldProjectAddConfigurationServer") {
				sendKeys(runAgainstSource.NODEJS_SERVICE_CONF_SERVER_CONTEXT_HELLOWORLD);
				Thread.sleep(2000);
			} else if (methodName=="testNodeJsEshopProjectAddConfigurationServer"){
				sendKeys(runAgainstSource.NODEJS_SERVICE_CONF_SERVER_CONTEXT_ESHOP);
				Thread.sleep(2000);
			}
			waitForElementPresent(phrsc.CONFIG_SAVE_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_SAVE_BUTTON);
			click();
			boolean configServerTestResult = configServerSuccessFailureLoop();
			System.out.println("configServerTestResult::::::::"+configServerTestResult);
			Assert.assertTrue(configServerTestResult);
			Thread.sleep(3000);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void NodeJsAddConfigurationDatabase(String methodName,
			RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@NodeJsAddConfigurationDatabase::****** executing ****");
		try {

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TAB, methodName);
			getXpathWebElement(phrsc.CONFIG_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ADD_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_ADD_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_NAME, methodName);
			getXpathWebElement(phrsc.CONFIG_NAME);
			click();
			clear();
			sendKeys(runAgainstSource.NODEJS_SERVICE_CONF_DATABASE_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC, methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(runAgainstSource.NODEJS_SERVICE_CONF_DATABASE_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,	methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,	runAgainstSource.NODEJS_SERVICE_CONF_DATABASE_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE, methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,runAgainstSource.NODEJS_SERVICE_CONF_DATABASE_TYPE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST, methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(runAgainstSource.NODEJS_SERVICE_CONF_DATABASE_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT, methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(runAgainstSource.NODEJS_SERVICE_CONF_DATABASE_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_USERNAME, methodName);
			getXpathWebElement(phrsc.CONFIG_USERNAME);
			click();
			clear();
			sendKeys(runAgainstSource.NODEJS_SERVICE_CONF_DB_USERNAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DB_NAME,	methodName);
			getXpathWebElement(phrsc.CONFIG_DB_NAME);
			click();
			clear();
			sendKeys(runAgainstSource.NODEJS_SERVICE_CONF_DB_NAME_ESHOP);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_SAVE_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_SAVE_BUTTON);
			click();
			boolean configDbTestResult = configDbSuccessFailureLoop();
			System.out.println("configDbTestResult::::::::"+configDbTestResult);
			Assert.assertTrue(configDbTestResult);
			Thread.sleep(5000);
			log.info("success");

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
	
	public void javaWebserviceProjectHelloWorldCreate(String methodName,
			RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@javaWebserviceProjectHelloWorldCreate::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_BUTTON, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_NAME);
			click();
			sendKeys(runAgainstSource.JAVA_WS_HELLOWORLD_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			sendKeys(runAgainstSource.JAVA_WS_HELLOWORLD_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_CHECKALL, methodName);
			getXpathWebElement(phrsc.APP_LAYER_CHECKALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_LAYER_DROPDOWN);
			selectText(element,runAgainstSource.JAVA_WS_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(runAgainstSource.JAVA_WS_HELLOWORLD_NAME_VALUE, methodName);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void javaWebserviceProjectHelloWorldEditApplication(
			String methodName, RunAgainstSourceConstantsXml runAgainstSource)
	throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@javaWebserviceProjectHelloWorldEditApplication::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(runAgainstSource.JAVA_WS_PROJECT_HELLOWORLD_EDIT_APP_LINK,methodName);
			getXpathWebElement(runAgainstSource.JAVA_WS_PROJECT_HELLOWORLD_EDIT_APP_LINK);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, runAgainstSource.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_CHECK_ALL, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_CHECK_ALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_DROPDOWN);
			selectText(element,runAgainstSource.JAVA_WS_SERVER_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_VERSION_TOMCAT, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_VERSION_TOMCAT);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_DB_CHECK_ALL, methodName);
			getXpathWebElement(phrsc.APP_INFO_DB_CHECK_ALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_DB_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_DB_DROPDOWN);
			selectText(element,runAgainstSource.JAVA_WS_DB_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			click();
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void javaWebserviceProjectHelloWorldAddFeature(String methodName,
			RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@javaWebserviceProjectHelloWorldAddFeature::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_FINISH_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_FINISH_BUTTON);
			click();
			boolean updateTestResult = updateSuccessFailureLoop();
			System.out.println("updateTestResult::"+updateTestResult);
			Assert.assertTrue(updateTestResult);
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
	public void javaWebserviceProjectEshopCreate(String methodName,
			RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@javaWebserviceProjectEshopCreate::****** executing ****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.PROJECT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_BUTTON, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_NAME);
			click();
			sendKeys(runAgainstSource.JAVA_WS_ESHOP_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			sendKeys(runAgainstSource.JAVA_WS_ESHOP_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_CHECKALL, methodName);
			getXpathWebElement(phrsc.APP_LAYER_CHECKALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_LAYER_DROPDOWN);
			selectText(element,runAgainstSource.JAVA_WS_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(runAgainstSource.JAVA_WS_ESHOP_NAME_VALUE, methodName);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void javaWebserviceProjectEshopEditApplication(String methodName,
			RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@javaWebserviceProjectEshopEditApplication::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(runAgainstSource.JAVA_WS_PROJECT_ESHOP_EDIT_APP_LINK,methodName);
			getXpathWebElement(runAgainstSource.JAVA_WS_PROJECT_ESHOP_EDIT_APP_LINK);
			click();

			Thread.sleep(10000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, runAgainstSource.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN);
			selectText(element,runAgainstSource.JAVA_WS_PILOT_PROJECT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			click();
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void javaWebserviceProjectEshopAddFeature(String methodName,
			RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@javaWebserviceProjectEshopAddFeature::****** executing ****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.PROJECT_FINISH_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_FINISH_BUTTON);
			click();
			boolean updateTestResult = updateSuccessFailureLoop();
			System.out.println("updateTestResult::"+updateTestResult);
			Assert.assertTrue(updateTestResult);
			Thread.sleep(50000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}


	public void javaWebserviceAddConfigurationServer(String methodName,
			RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@javaWebserviceAddConfigurationServer****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.MENU_TAB, methodName);
			getXpathWebElement(phrsc.MENU_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TAB, methodName);
			getXpathWebElement(phrsc.CONFIG_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ADD_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_ADD_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_NAME, methodName);
			getXpathWebElement(phrsc.CONFIG_NAME);
			click();
			clear();
			sendKeys(runAgainstSource.JAVA_WS_CONF_SERVER_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC, methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(runAgainstSource.JAVA_WS_CONF_SERVER_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,	methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,runAgainstSource.JAVA_WS_CONF_SERVER_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE, methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,runAgainstSource.JAVA_WS_CONF_SERVER_TYPE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL, methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element,runAgainstSource.JAVA_WS_CONF_SERVER_PROTOCOL_HTTP_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST, methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(runAgainstSource.JAVA_WS_CONF_SERVER_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT, methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(runAgainstSource.JAVA_WS_CONF_SERVER_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_LOCATION,	methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_LOCATION);
			click();
			sendKeys(runAgainstSource.JAVA_WS_CONF_SERVER_DEPLOY_DIRECTORY);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_OK, methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_OK);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT,	methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);
			click();
			clear();
			if (methodName == "testJavaWebServiceHelloWorldProjectAddServerConfiguration") {
				sendKeys(runAgainstSource.JAVA_WS_CONF_SERVER_CONTEXT_HELLOWORLD);
				Thread.sleep(2000);

			} else if (methodName == "testJavaWebServiceEshopProjectAddServerConfiguration") {
				sendKeys(runAgainstSource.JAVA_WS_CONF_SERVER_CONTEXT_ESHOP);
				Thread.sleep(2000);
			}

			waitForElementPresent(phrsc.CONFIG_SAVE_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_SAVE_BUTTON);
			click();
			boolean configServerTestResult = configServerSuccessFailureLoop();
			System.out.println("configServerTestResult::::::::"+configServerTestResult);
			Assert.assertTrue(configServerTestResult);
			Thread.sleep(3000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void javaWebserviceAddConfigurationDatabase(String methodName,
			RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@javaWebserviceAddConfigurationDatabase::****** executing ****");
		try {

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TAB, methodName);
			getXpathWebElement(phrsc.CONFIG_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ADD_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_ADD_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_NAME, methodName);
			getXpathWebElement(phrsc.CONFIG_NAME);
			click();
			clear();
			sendKeys(runAgainstSource.JAVA_WS_CONF_DATABASE_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC, methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(runAgainstSource.JAVA_WS_CONF_DATABASE_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,	methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,runAgainstSource.JAVA_WS_CONF_DATABASE_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE, methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,runAgainstSource.JAVA_WS_CONF_DATABASE_TYPE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST, methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(runAgainstSource.JAVA_WS_CONF_DATABASE_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT, methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(runAgainstSource.JAVA_WS_CONF_DATABASE_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_USERNAME, methodName);
			getXpathWebElement(phrsc.CONFIG_USERNAME);
			click();
			clear();
			sendKeys(runAgainstSource.JAVA_WS_CONF_DB_USERNAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DB_NAME,	methodName);
			getXpathWebElement(phrsc.CONFIG_DB_NAME);
			click();
			sendKeys(runAgainstSource.JAVA_WS_CONF_DB_NAME_ESHOP);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_SAVE_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_SAVE_BUTTON);
			click();
			boolean configDbTestResult = configDbSuccessFailureLoop();
			System.out.println("configDbTestResult::::::::"+configDbTestResult);
			Assert.assertTrue(configDbTestResult);
			Thread.sleep(5000);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
	
	
	public  void multiYuiWidgetProjectHelloWorldCreate(String methodName, RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
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
			sendKeys(runAgainstSource.MULTICHANNEL_YUI_WIDGET_HELLOWORLD_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			clear();
			sendKeys(runAgainstSource.MULTICHANNEL_YUI_WIDGET_HELLOWORLD_PROJECT_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_CHECKBOX);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_DROPDOWN);
			selectText(element,runAgainstSource.HTML_VALUE);

			Thread.sleep(2000);			
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_DROPDOWN);
			selectText(element,runAgainstSource.MULTICHANNEL_YUI_WIDGET_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_VERSION, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_VERSION);
			selectText(element, runAgainstSource.MULTICHANNEL_YUI_WIDGET_VERSION);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(runAgainstSource.MULTICHANNEL_YUI_WIDGET_HELLOWORLD_PROJECT_NAME, methodName);
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void multiYuiWidgetProjectHelloWorldEditApplication(String methodName,RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@multiYuiWidgetProjectHelloWorldEditApplication scenario****");
		try {					
			Thread.sleep(2000);
			waitForElementPresent(runAgainstSource.MULTICHANNEL_YUI_WIDGET_HELLOWORLD_LINK,methodName);
			getXpathWebElement(runAgainstSource.MULTICHANNEL_YUI_WIDGET_HELLOWORLD_LINK);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, runAgainstSource.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.APP_INFO_SERVER_CHECK_ALL, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_CHECK_ALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_DROPDOWN);
			selectText(element,runAgainstSource.WIDGET_SERVER_VALUE);

			Thread.sleep(2000);			
			waitForElementPresent(runAgainstSource.WIDGET_SERVER_VERSION, methodName);
			getXpathWebElement(runAgainstSource.WIDGET_SERVER_VERSION);
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

	public  void multiYuiWidgetProjectHelloWorldAddFeature(String methodName,RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
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

	public  void multiYuiWidgetProjectEshopCreate(String methodName, RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
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
			sendKeys(runAgainstSource.MULTICHANNEL_YUI_WIDGET_ESHOP_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			clear();
			sendKeys(runAgainstSource.MULTICHANNEL_YUI_WIDGET_ESHOP_PROJECT_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_CHECKBOX);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_DROPDOWN);
			selectText(element,runAgainstSource.HTML_VALUE);

			Thread.sleep(2000);			
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_DROPDOWN);
			selectText(element,runAgainstSource.MULTICHANNEL_YUI_WIDGET_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_VERSION, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_VERSION);
			selectText(element, runAgainstSource.MULTICHANNEL_YUI_WIDGET_VERSION);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(runAgainstSource.MULTICHANNEL_YUI_WIDGET_ESHOP_PROJECT_NAME, methodName);
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void multiYuiWidgetProjectEshopEditApplication(String methodName,RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@multiYuiWidgetProjectEshopEditApplicationscenario****");
		try {						
			Thread.sleep(2000);
			waitForElementPresent(runAgainstSource.MULTICHANNEL_YUI_WIDGET_ESHOP_LINK,methodName);
			getXpathWebElement(runAgainstSource.MULTICHANNEL_YUI_WIDGET_ESHOP_LINK);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, runAgainstSource.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN);
			selectText(element,runAgainstSource.PILOT_PROJECT_ESHOP_VALUE);

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

	public  void multiYuiWidgetProjectEshopAddFeature(String methodName,RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
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

	public  void multiYuiWidgetProjectAddConfigurationServer(String methodName,RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
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
			sendKeys(runAgainstSource.CONFIG_SERVER_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC,methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(runAgainstSource.CONFIG_SERVER_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,runAgainstSource.CONFIG_SERVER_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,runAgainstSource.CONFIG_SERVER_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element,runAgainstSource.CONFIG_SERVER_PROTOCOL_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST,methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(runAgainstSource.CONFIG_SERVER_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT,methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(runAgainstSource.MULTI_YUI_CONFIG_ESHOP_SERVER_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_LOCATION,methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_LOCATION);
			click();
			clear();
			sendKeys(runAgainstSource.CONFIG_SERVER_DEPLOY_VALUE);

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
				sendKeys(runAgainstSource.MULTICHANNEL_YUI_WIDGET_HELLOWORLD_SERVER_CONTEXT);
				Thread.sleep(2000);
			}
			else if(methodName=="testMultiYuiWidgetEshopProjectAddConfigurationServer")
			{
				sendKeys(runAgainstSource.MULTICHANNEL_YUI_WIDGET_ESHOP_SERVER_CONTEXT);
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


	public  void multiYuiWidgetProjectAddConfigurationWebService(String methodName,RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
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
			sendKeys(runAgainstSource.CONFIG_WEBSERVICE_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC,methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(runAgainstSource.CONFIG_WEBSERVICE_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,runAgainstSource.CONFIG_WEBSERVICE_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,runAgainstSource.CONFIG_WEBSERVICE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element,runAgainstSource.CONFIG_WEBSERVICE_PROTOCAOL_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST,methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(runAgainstSource.CONFIG_WEBSERVICE_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT,methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(runAgainstSource.CONFIG_WEBSERVICE_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT,methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);
			click();
			clear();
			sendKeys(runAgainstSource.MULTICHANNEL_YUI_WIDGET_ESHOP_WEBSERVICE_CONTEXT);

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


	public  void yuiMobileWidgetProjectHelloWorldCreate(String methodName, RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
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
			sendKeys(runAgainstSource.YUI_MOBILE_WIDGET_HELLOWORLD_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			clear();
			sendKeys(runAgainstSource.YUI_MOBILE_WIDGET_HELLOWORLD_PROJECT_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_CHECKBOX);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_DROPDOWN);
			selectText(element,runAgainstSource.HTML_VALUE);

			Thread.sleep(2000);			
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_DROPDOWN);
			selectText(element,runAgainstSource.YUI_MOBILE_WIDGET_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_VERSION, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_VERSION);
			selectText(element, runAgainstSource.YUI_MOBILE_WIDGET_VERSION);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(runAgainstSource.YUI_MOBILE_WIDGET_HELLOWORLD_PROJECT_NAME, methodName);
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void yuiMobileWidgetProjectHelloWorldEditApplication(String methodName,RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@yuiMobileWidgetProjectHelloWorldEditApplication scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(runAgainstSource.YUI_MOBILE_WIDGET_HELLOWORLD_LINK,methodName);
			getXpathWebElement(runAgainstSource.YUI_MOBILE_WIDGET_HELLOWORLD_LINK);
			click();

			Thread.sleep(10000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, runAgainstSource.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.APP_INFO_SERVER_CHECK_ALL, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_CHECK_ALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_DROPDOWN);
			selectText(element,runAgainstSource.WIDGET_SERVER_VALUE);

			Thread.sleep(2000);			
			waitForElementPresent(runAgainstSource.WIDGET_SERVER_VERSION, methodName);
			getXpathWebElement(runAgainstSource.WIDGET_SERVER_VERSION);
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

	public  void yuiMobileWidgetProjectHelloWorldAddFeature(String methodName,RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
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

	public  void yuiMobileWidgetProjectEshopCreate(String methodName, RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
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
			sendKeys(runAgainstSource.YUI_MOBILE_WIDGET_ESHOP_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			clear();
			sendKeys(runAgainstSource.YUI_MOBILE_WIDGET_ESHOP_PROJECT_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_CHECKBOX);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_DROPDOWN);
			selectText(element,runAgainstSource.HTML_VALUE);

			Thread.sleep(2000);			
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_DROPDOWN);
			selectText(element,runAgainstSource.YUI_MOBILE_WIDGET_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_VERSION, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_VERSION);
			selectText(element, runAgainstSource.YUI_MOBILE_WIDGET_VERSION);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(runAgainstSource.YUI_MOBILE_WIDGET_HELLOWORLD_PROJECT_NAME, methodName);
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void yuiMobileWidgetProjectEshopEditApplication(String methodName,RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@yuiMobileWidgetProjectEshopEditApplication scenario****");
		try {			
			Thread.sleep(2000);		
			waitForElementPresent(runAgainstSource.YUI_MOBILE_WIDGET_ESHOP_LINK,methodName);
			getXpathWebElement(runAgainstSource.YUI_MOBILE_WIDGET_ESHOP_LINK);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, runAgainstSource.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN);
			selectText(element,runAgainstSource.PILOT_PROJECT_ESHOP_VALUE);

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

	public  void yuiMobileWidgetProjectEshopAddFeature(String methodName,RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
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

	public  void yuiMobileWidgetProjectAddConfigurationServer(String methodName,RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
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
			sendKeys(runAgainstSource.CONFIG_SERVER_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC,methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(runAgainstSource.CONFIG_SERVER_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,runAgainstSource.CONFIG_SERVER_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,runAgainstSource.CONFIG_SERVER_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element,runAgainstSource.CONFIG_SERVER_PROTOCOL_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST,methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(runAgainstSource.CONFIG_SERVER_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT,methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(runAgainstSource.YUI_MOBILE_HW_CONFIG_SERVER_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_LOCATION,methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_LOCATION);
			click();
			clear();
			sendKeys(runAgainstSource.CONFIG_SERVER_DEPLOY_VALUE);

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
				sendKeys(runAgainstSource.YUI_MOBILE_WIDGET_HELLOWORLD_SERVER_CONTEXT);
				Thread.sleep(2000);
			}
			else if(methodName=="testYuiMobileWidgetEshopProjectAddConfigurationServer")
			{
				sendKeys(runAgainstSource.YUI_MOBILE_WIDGET_ESHOP_SERVER_CONTEXT);
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

	public  void yuiMobileWidgetProjectAddConfigurationWebService(String methodName,RunAgainstSourceConstantsXml runAgainstSource) throws Exception {
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
			sendKeys(runAgainstSource.CONFIG_WEBSERVICE_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC,methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(runAgainstSource.CONFIG_WEBSERVICE_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,runAgainstSource.CONFIG_WEBSERVICE_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,runAgainstSource.CONFIG_WEBSERVICE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element,runAgainstSource.CONFIG_WEBSERVICE_PROTOCAOL_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST,methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(runAgainstSource.CONFIG_WEBSERVICE_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT,methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(runAgainstSource.CONFIG_WEBSERVICE_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT,methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);
			click();
			clear();
			sendKeys(runAgainstSource.MOBILE_WIDGET_ESHOP_WEBSERVICE_CONTEXT);

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


	public void click() throws ScreenException {
		log.info("Entering:********click operation start********");
		try {
			element.click();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		log.info("Entering:********click operation end********");

	}

	public void type(String text) throws ScreenException {
		log.info("Entering:********enterText operation start********");
		try {
			clear();
			element.sendKeys(text);

		} catch (Throwable t) {
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

	public void ButtonEnabled(String Xpath) throws InterruptedException,
	IOException, Exception {
		Thread.sleep(3000);
		try {
			if (driver.findElement(By.xpath(Xpath)).isEnabled()) {
				System.out
				.println("-------New_ReleaseAvailable----------------");
			} else if (driver.findElement(By.xpath(Xpath)).isSelected()) {
				{
					System.out
					.println("------- New_Release_Available----------------");
				}
			} else {
				System.out.println("----------No_Release_Available----------");
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public void MouseOverEvents(String Xpath) throws NullPointerException,
	IOException {
		log.info("@Entering:---------------MouseOverEvents()------------------------");
		try {

			element = driver.findElement(By.xpath(Xpath));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();

		} catch (Throwable t) {
			log.info("--------Exception in MouseOverEvents-------------- ");
		}
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
		String RUN_AGAINST_SOURCE = "server started";
		String RUN_AGAINST_SOURCE_FAILED = "server started";
		boolean statusStringFound = false;	
		while (!statusStringFound) {
			if (driver.findElement(By.tagName("body")).getText().contains(BUILD_SUCCESS)) {
				return BUILD_SUCCESS;
			} else if (driver.findElement(By.tagName("body")).getText().contains(BUILD_FAILURE))  {
				return BUILD_FAILURE;
			} else if (driver.findElement(By.tagName("body")).getText().contains(RUN_AGAINST_SOURCE)) {
				return BUILD_SUCCESS;
			}
			else if (driver.findElement(By.tagName("body")).getText().contains(RUN_AGAINST_SOURCE_FAILED)) {
				return BUILD_FAILURE;
			}

		}
		return null;
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
	public boolean successFailureLoop2() throws InterruptedException, IOException, Exception {
		String foundString = buildStatusVerifier2();
		if(foundString.equalsIgnoreCase("Selected Features"))
		{
			return true;
		}
		else
			return false;			
	}

	private String buildStatusVerifier2() {
		String SELECT_FEATURE = "Selected Features";
		boolean statusStringFound = false;		
		while (!statusStringFound) { 
			if (driver.findElement(By.tagName("body")).getText().contains(SELECT_FEATURE)) {
				return SELECT_FEATURE;
			} 
		}
		return null;
	}

	public void appLayerBuildTab(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@appLayerBuildTab::****** executing ****");
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

			if(methodName == "testJavaWebServiceHelloWorldProjectBuild" || methodName == "testJavaWebServiceEshopProjectBuild" ){
				Thread.sleep(2000);
				waitForElementPresent(phrsc.SKIP_UNIT_TEST, methodName);
				getXpathWebElement(phrsc.SKIP_UNIT_TEST);
				click();
			}
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

	public void appLayerDeploy(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@appLayerDeploy::******executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.DEPLOY_BUTTON, methodName);
			getXpathWebElement(phrsc.DEPLOY_BUTTON);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.SELECT_SQL, methodName);
			getXpathWebElement(phrsc.SELECT_SQL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.DEPLOY_OK, methodName);
			getXpathWebElement(phrsc.DEPLOY_OK);
			click();
			Thread.sleep(5000);
			boolean deployTestResult = successFailureLoop();
			System.out.println("deployTestResult:::::"+deployTestResult);
			Assert.assertTrue(deployTestResult);
			Thread.sleep(2000);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
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

	public void appLayerDeployProject(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@appLayerDeployProject::******executing ****");
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
			boolean deployTestResult=successFailureLoop();
			System.out.println("deployTestResult::::::"+deployTestResult);
			Assert.assertTrue(deployTestResult);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void appLayerRunAgainstSource(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@appLayerRunAgainstSource::******executing Deploy****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.BUILD_TAB, methodName);
			getXpathWebElement(phrsc.BUILD_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.RUN_AGAINST_SOURCE_BUTTON, methodName);
			getXpathWebElement(phrsc.RUN_AGAINST_SOURCE_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.SELECT_SQL, methodName);
			getXpathWebElement(phrsc.SELECT_SQL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.RUN_AGAINST_SOURCE_RUN_BUTTON, methodName);
			getXpathWebElement(phrsc.RUN_AGAINST_SOURCE_RUN_BUTTON);
			click();
			Thread.sleep(2000);
			boolean runAgainstTestResult=successFailureLoop();
			System.out.println("runAgainstTestResult::::::"+runAgainstTestResult);
			Assert.assertTrue(runAgainstTestResult);
			Thread.sleep(5000);
			waitForElementPresent(phrsc.RUN_AGAINST_SOURCE_STOP_BUTTON, methodName);
			getXpathWebElement(phrsc.RUN_AGAINST_SOURCE_STOP_BUTTON);
			click();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}


}
