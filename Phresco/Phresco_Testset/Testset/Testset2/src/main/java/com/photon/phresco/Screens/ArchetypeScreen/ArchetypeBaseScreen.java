package com.photon.phresco.Screens.ArchetypeScreen;


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
import com.photon.phresco.uiconstants.ArchetypeCreationXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;




public class ArchetypeBaseScreen {

	private WebDriver driver;
	private ChromeDriverService chromeService;
	private Log log = LogFactory.getLog("BaseScreen");
	private WebElement element;	
	DesiredCapabilities capabilities;
	private PhrescoUiConstantsXml phrsc;
	private phresco_env_config phrscEnv;
	private UserInfoConstants userInfo;
	private String resolution = null;

	public ArchetypeBaseScreen() {

	}	public ArchetypeBaseScreen(String selectedBrowser, String selectedPlatform, String applicationURL,String applicatinContext,phresco_env_config phrscEnv, PhrescoUiConstantsXml phrsc, UserInfoConstants userInfo,PhrescoFrameworkData phrscData) throws ScreenException, MalformedURLException {
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
	
	public  void ArchetypeCreate(String methodName,ArchetypeCreationXml archetypeCreate) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@ArchetypeCreateTest::****** executing ****");
		try {
			Thread.sleep(3000);
			waitForElementPresent(phrsc.PROJECT_BUTTON,methodName);
			getXpathWebElement(phrsc.PROJECT_BUTTON);
			click();
					
			Thread.sleep(500);
			waitForElementPresent(phrsc.ADD_PROJECT_BUTTON,methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_BUTTON);
			click();
		
			Thread.sleep(500);
			waitForElementPresent(phrsc.ADD_PROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_NAME);
			click();
			sendKeys(archetypeCreate.PROJECT_NAME);			

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			sendKeys(archetypeCreate.PROJECT_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_CHECKALL, methodName);
			getXpathWebElement(phrsc.APP_LAYER_CHECKALL);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_LAYER_DROPDOWN);
			selectText(element,archetypeCreate.NODEJS_SERVICE_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_CHECKBOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_DROPDOWN);
			selectText(element,archetypeCreate.HTML_VALUE);
			
			Thread.sleep(2000);			
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_DROPDOWN);
			selectText(element,archetypeCreate.MULTICHANNEL_YUI_WIDGET_NAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.WEB_LAYER_WIDGET_VERSION, methodName);
			getXpathWebElement(phrsc.WEB_LAYER_WIDGET_VERSION);
			selectText(element,archetypeCreate.MULTICHANNEL_YUI_WIDGET_VERSION_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.MOB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.MOB_LAYER_CHECKBOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ANDROID_CHECKBOX, methodName);
			getXpathWebElement(phrsc.ANDROID_CHECKBOX);			
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ANDROID_TECH_DROPDOWN, methodName);
			getXpathWebElement(phrsc.ANDROID_TECH_DROPDOWN);			
			selectText(element,archetypeCreate.ANDROID_NATIVE_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ANDROID_PHONE_CHECKBOX, methodName);
			getXpathWebElement(phrsc.ANDROID_PHONE_CHECKBOX);			
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ANDROID_TABLET_CHECKBOX, methodName);
			getXpathWebElement(phrsc.ANDROID_TABLET_CHECKBOX);			
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(archetypeCreate.MULTICHANNEL_YUI_WIDGET_NAME_VALUE, methodName);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void NodeJsWebserviceProjectHelloWorldEditApplication(String methodName,ArchetypeCreationXml archetypeCreate) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@NodeJsWebserviceProjectHelloWorldEditApplication::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(archetypeCreate.NODEJS_SERVICE_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK,methodName);
          	getXpathWebElement(archetypeCreate.NODEJS_SERVICE_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK);
			click();			
			
			Thread.sleep(5000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, archetypeCreate.FUNCTIONAL_FRAMEWORK);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_CHECK_ALL, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_CHECK_ALL);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_DROPDOWN);
			selectText(element, archetypeCreate.NODEJS_SERVICE_SERVER_VALUE);
			
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
	
	public  void NodeJsWebserviceProjectHelloWorldAddFeature(String methodName,ArchetypeCreationXml archetypeCreate) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@NodeJsWebserviceProjectHelloWorldAddFeature::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.PROJECT_FINISH_BUTTON);
			click();
			boolean updateTestResult = updateSuccessFailureLoop();
			System.out.println("updateTestResult::::"+updateTestResult);
			Assert.assertTrue(updateTestResult);
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	
	public  void NodeJsAddConfigurationServer(String methodName,ArchetypeCreationXml archetypeCreate) throws Exception {
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
			sendKeys(archetypeCreate.CONFIG_SERVER_NAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC,methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			sendKeys(archetypeCreate.CONFIG_SERVER_DESC_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element, archetypeCreate.CONFIG_SERVER_ENV_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,archetypeCreate.CONFIG_SERVER_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element, archetypeCreate.CONFIG_SERVER_PROTOCOL_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST,methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			sendKeys(archetypeCreate.CONFIG_SERVER_HOST_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT,methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			sendKeys(archetypeCreate.NODEJS_SERVICE_CONFIGURATION_SERVER_PORT_VALUE);

			Thread.sleep(2000);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);
			waitForElementPresent(phrsc.CONFIG_CONTEXT,methodName);
			click();
			sendKeys(archetypeCreate.NODEJS_SERVICE_CONFIGURATION_SERVER_CONTEXT_VALUE_HELLOWORLD);
				
			Thread.sleep(2000);			
			waitForElementPresent(phrsc.CONFIG_SAVE_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_SAVE_BUTTON);
			click();
			Thread.sleep(3000);
			boolean configServerTestResult = configServerSuccessFailureLoop();
			System.out.println("configServerTestResult::::::::"+configServerTestResult);
			Assert.assertTrue(configServerTestResult);
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	

	public  void MultiYuiWidgetProjectHelloWorldEditApplication(String methodName,ArchetypeCreationXml archetypeCreate) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@MultiYuiWidgetProjectHelloWorldEditApplication scenario****");
		try {		
			Thread.sleep(10000);
			waitForElementPresent(phrsc.PROJECT_BUTTON,methodName);
			getXpathWebElement(phrsc.PROJECT_BUTTON);
			click();
			
			Thread.sleep(5000);
			waitForElementPresent(archetypeCreate.MULTICHANNEL_YUI_WIDGET_HELLOWORLD_PROJECT_LINK,methodName);
			getXpathWebElement(archetypeCreate.MULTICHANNEL_YUI_WIDGET_HELLOWORLD_PROJECT_LINK);
			click();
			
			Thread.sleep(5000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, archetypeCreate.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.APP_INFO_SERVER_CHECK_ALL, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_CHECK_ALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_DROPDOWN);
			selectText(element,archetypeCreate.WIDGET_SERVER_VALUE);

			Thread.sleep(2000);			
			waitForElementPresent(archetypeCreate.WIDGET_SERVER_VERSION, methodName);
			getXpathWebElement(archetypeCreate.WIDGET_SERVER_VERSION);
			click();

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

	public  void multiYuiWidgetProjectHelloWorldAddFeature(String methodName,ArchetypeCreationXml archetypeCreate) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@multiYuiWidgetProjectHelloWorldAddFeature scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.PROJECT_FINISH_BUTTON);
			click();
			boolean updateTestResult = updateSuccessFailureLoop();
			System.out.println("updateTestResult::::"+updateTestResult);
			Assert.assertTrue(updateTestResult);
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void multiYuiWidgetProjectAddConfigurationServer(String methodName,ArchetypeCreationXml archetypeCreate) throws Exception {
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
			sendKeys(archetypeCreate.CONFIG_SERVER_NAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC,methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			sendKeys(archetypeCreate.CONFIG_SERVER_DESC_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element, archetypeCreate.CONFIG_SERVER_ENV_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,archetypeCreate.CONFIG_SERVER_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element, archetypeCreate.CONFIG_SERVER_PROTOCOL_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST,methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			sendKeys(archetypeCreate.CONFIG_SERVER_HOST_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT,methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			sendKeys(archetypeCreate.CONFIG_SERVER_PORT_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_BUTTON);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_LOCATION,methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_LOCATION);
			click();
			sendKeys(archetypeCreate.CONFIG_SERVER_DEPLOY_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_OK,methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_OK);
			click();
								
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT,methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);
			click();
			sendKeys(archetypeCreate.MULTICHANNEL_YUI_WIDGET_HELLOWORLD_PROJECT_SERVER_CONTEXT);
			Thread.sleep(2000);
						
			Thread.sleep(3000);
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
	
	public  void WidgetProjectBuild(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@WidgetProjectBuild::******executing WidgetProjectBuild scenario****");
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
			sendKeys(phrsc.BUILD_NAME);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.BUILD_NUMBER_XPATH, methodName);
			getXpathWebElement(phrsc.BUILD_NUMBER_XPATH);
			sendKeys(phrsc.BUILD_NUMBER);
						
			Thread.sleep(5000);
			waitForElementPresent(phrsc.BUILD_BUTTON, methodName);
			getXpathWebElement(phrsc.BUILD_BUTTON);
			click();
			boolean buildTestResult=successFailureLoop();
			System.out.println("buildTestResult:::::::"+buildTestResult);
			Assert.assertTrue(buildTestResult);
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
		
	
	public  void WidgetProjectDeploy(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
				log.info("@WidgetProjectDeploy scenario****");
		try {
			Thread.sleep(3000);
			waitForElementPresent(phrsc.DEPLOY_BUTTON, methodName);
			getXpathWebElement(phrsc.DEPLOY_BUTTON);
			click();
			
			Thread.sleep(5000);
			waitForElementPresent(phrsc.DEPLOY_OK, methodName);
			getXpathWebElement(phrsc.DEPLOY_OK);
			click();			
			Thread.sleep(5000);
			boolean deployTestResult = successFailureLoop();
			System.out.println("deployTestResult:::::"+deployTestResult);
			Assert.assertTrue(deployTestResult);
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	public  void androidNativeHelloWorldProjectEditApplication(String methodName,ArchetypeCreationXml archetypeCreate) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@androidNativeHelloWorldProjectEditApplication scenario****");
		try {
			Thread.sleep(10000);
			waitForElementPresent(phrsc.PROJECT_BUTTON,methodName);
			getXpathWebElement(phrsc.PROJECT_BUTTON);
			click();
			
			Thread.sleep(2000);		
			waitForElementPresent(archetypeCreate.ANDROID_NATIVE_HELLOWORLD_EDIT_LINK,methodName);
          	getXpathWebElement(archetypeCreate.ANDROID_NATIVE_HELLOWORLD_EDIT_LINK);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, archetypeCreate.FUNCTIONAL_FRAMEWORK_ROB);
			
			Thread.sleep(3000);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			click();
			Thread.sleep(2000);

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void androidNativeHelloWorldProjectAddFeature(String methodName,ArchetypeCreationXml archetypeCreate) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@androidNativeHelloWorldProjectAddFeature scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.PROJECT_FINISH_BUTTON);			
			click();
			Thread.sleep(2000);
			boolean updateTestResult = updateSuccessFailureLoop();
			System.out.println("updateTestResult::::"+updateTestResult);
			Assert.assertTrue(updateTestResult);
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	
	public  void androidNativeProjectBuild(String methodName,ArchetypeCreationXml archetypeCreate) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@androidNativeProjectBuild scenario****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.MENU_TAB, methodName);
			getXpathWebElement(phrsc.MENU_TAB);
			click();
			
			Thread.sleep(3000);
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
			sendKeys(phrsc.BUILD_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.BUILD_NUMBER_XPATH, methodName);
			getXpathWebElement(phrsc.BUILD_NUMBER_XPATH);
			sendKeys(phrsc.BUILD_NUMBER);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.SDK_SELECT, methodName);
			getXpathWebElement(phrsc.SDK_SELECT);
			selectText(element, archetypeCreate.SDK_VALUE);

			Thread.sleep(3000);
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
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	public  void androidNativeProjectDeploy(String methodName,ArchetypeCreationXml archetypeCreate) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
				log.info("@AndroidNativeProjectDeploy scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.DEPLOY_BUTTON, methodName);
			getXpathWebElement(phrsc.DEPLOY_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.DEPLOY_DEVICE, methodName);
			getXpathWebElement(phrsc.DEPLOY_DEVICE);
			selectText(element,archetypeCreate.EMULATOR_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.DEPLOY_OK, methodName);
			getXpathWebElement(phrsc.DEPLOY_OK);
			click();			
			Thread.sleep(2000);
			boolean deployTestResult = successFailureLoop();
			System.out.println("deployTestResult:::::"+deployTestResult);
			Assert.assertTrue(deployTestResult);
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
