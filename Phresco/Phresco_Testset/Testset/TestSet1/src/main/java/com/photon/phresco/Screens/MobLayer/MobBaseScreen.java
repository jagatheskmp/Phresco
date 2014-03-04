
package com.photon.phresco.Screens.MobLayer;
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
import com.photon.phresco.uiconstants.AndroidHybridConstantsXml;
import com.photon.phresco.uiconstants.AndroidLibraryConstantsXml;
import com.photon.phresco.uiconstants.AndroidNativeConstantsXml;
import com.photon.phresco.uiconstants.BlackBerryHybridConstantsXml;
import com.photon.phresco.uiconstants.IPhoneHybridConstantsXml;
import com.photon.phresco.uiconstants.IPhoneLibraryConstantsXml;
import com.photon.phresco.uiconstants.IPhoneNativeConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.WindowsMetroConstantsXml;
import com.photon.phresco.uiconstants.WindowsPhoneConstantsXml;
import com.photon.phresco.uiconstants.phresco_env_config;
public class MobBaseScreen {

	private WebDriver driver;
	private ChromeDriverService chromeService;
	private Log log = LogFactory.getLog("BaseScreen");
	private  WebElement element;	
	private String resolution = null;
	DesiredCapabilities capabilities;
	private PhrescoUiConstantsXml phrsc;
	private  phresco_env_config phrscEnv;
	private UserInfoConstants userInfo;

	public MobBaseScreen() {

	}

	public MobBaseScreen(String selectedBrowser, String selectedPlatform, String applicationURL,
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
			System.out.println("-----------checking the firefox-------");
		} else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_SAFARI)) {
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
			capabilities.setBrowserName("html"); 
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

	public  void loginPage(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@testLoginPage::******executing LoginPage scenario****");
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
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}



	public  void iPhoneNativeHelloWorldCreate(String methodName,IPhoneNativeConstantsXml iPhoneNative) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@iPhoneNativeHelloWorldCreate scenario****");
		try {
			Thread.sleep(2000);		
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
			sendKeys(iPhoneNative.IPHONE_NATIVE_HELLOWORLD_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);			
			click();
			sendKeys(iPhoneNative.IPHONE_NATIVE_HELLOWORLD_PROJECT_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.MOB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.MOB_LAYER_CHECKBOX);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.IPHONE_CHECKBOX, methodName);
			getXpathWebElement(phrsc.IPHONE_CHECKBOX);			
			click();

			Thread.sleep(2000);			
			waitForElementPresent(phrsc.IPHONE_TECH_DROPDOWN, methodName);
			getXpathWebElement(phrsc.IPHONE_TECH_DROPDOWN);			
			selectText(element,iPhoneNative.IPHONE_NATIVE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.IPHONE_PHONE_CHECKBOX, methodName);
			getXpathWebElement(phrsc.IPHONE_PHONE_CHECKBOX);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.IPHONE_TABLET_CHECKBOX, methodName);
			getXpathWebElement(phrsc.IPHONE_TABLET_CHECKBOX);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);			
			click();
			Thread.sleep(30000);
			isTextPresent(iPhoneNative.IPHONE_NATIVE_HELLOWORLD_PROJECT_NAME, methodName);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void iPhoneNativeHelloWorldProjectEditApplication(String methodName,IPhoneNativeConstantsXml iPhoneNative ) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@iPhoneNativeHelloWorldProjectEditApplication scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(iPhoneNative.IPHONE_NATIVE_HELLOWORLD_EDIT_LINK,methodName);
			getXpathWebElement(iPhoneNative.IPHONE_NATIVE_HELLOWORLD_EDIT_LINK);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, iPhoneNative.FUNCTIONAL_FRAMEWORK);

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

	public  void iPhoneNativeHelloWorldProjectAddFeature(String methodName,IPhoneNativeConstantsXml iPhoneNative) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@iPhoneNativeHelloWorldProjectAddFeature scenario****");
		try {
			Thread.sleep(2000);
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


	public  void iPhoneNativeEshopCreate(String methodName,IPhoneNativeConstantsXml iPhoneNative) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@iPhoneNativeEshopCreate scenario****");
		try {
			Thread.sleep(2000);	
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
			sendKeys(iPhoneNative.IPHONE_NATIVE_ESHOP_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);			
			click();
			sendKeys(iPhoneNative.IPHONE_NATIVE_ESHOP_PROJECT_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.MOB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.MOB_LAYER_CHECKBOX);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.IPHONE_CHECKBOX, methodName);
			getXpathWebElement(phrsc.IPHONE_CHECKBOX);			
			click();

			Thread.sleep(2000);		
			waitForElementPresent(phrsc.IPHONE_TECH_DROPDOWN, methodName);
			getXpathWebElement(phrsc.IPHONE_TECH_DROPDOWN);			
			selectText(element,iPhoneNative.IPHONE_NATIVE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.IPHONE_PHONE_CHECKBOX, methodName);
			getXpathWebElement(phrsc.IPHONE_PHONE_CHECKBOX);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.IPHONE_TABLET_CHECKBOX, methodName);
			getXpathWebElement(phrsc.IPHONE_TABLET_CHECKBOX);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);			
			click();
			Thread.sleep(30000);
			isTextPresent(iPhoneNative.IPHONE_NATIVE_ESHOP_PROJECT_NAME, methodName);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void iPhoneNativeEshopProjectEditApplication(String methodName,IPhoneNativeConstantsXml iPhoneNative ) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@iPhoneNativeEshopProjectEditApplication scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(iPhoneNative.IPHONE_NATIVE_ESHOP_EDIT_LINK,methodName);
			getXpathWebElement(iPhoneNative.IPHONE_NATIVE_ESHOP_EDIT_LINK);		          	
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, iPhoneNative.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN);			
			selectText(element,iPhoneNative.IPHONE_NATIVE_PILOT_PROJECT_ESHOP);			

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

	public  void iPhoneNativeEshopProjectAddFeature(String methodName,IPhoneNativeConstantsXml iPhoneNative) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@iPhoneNativeEshopProjectAddFeature scenario****");
		try {
			Thread.sleep(2000);
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

	public  void iPhoneNativeEshopProjectAddConfigurationWebService(String methodName,IPhoneNativeConstantsXml iPhoneNative) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@iPhoneNativeEshopProjectAddConfigurationWebService scenario****");
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

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ADD_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_ADD_BUTTON);		
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_NAME,methodName);
			getXpathWebElement(phrsc.CONFIG_NAME);			
			click();		
			sendKeys(iPhoneNative.IPHONE_NATIVE_CONFIG_WEBSERVICE_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC,methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);			
			click();
			sendKeys(iPhoneNative.IPHONE_NATIVE_CONFIG_WEBSERVICE_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,iPhoneNative.IPHONE_NATIVE_CONFIG_WEBSERVICE_ENV);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);			
			selectText(element,iPhoneNative.IPHONE_NATIVE_CONFIG_WEBSERVICE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);			
			selectText(element, iPhoneNative.IPHONE_NATIVE_CONFIG_WEBSERVICE_PROTOCOL);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST,methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);			
			click();
			sendKeys(iPhoneNative.IPHONE_NATIVE_CONFIG_WEBSERVICE_HOST);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT,methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);			
			click();
			sendKeys(iPhoneNative.IPHONE_NATIVE_CONFIG_WEBSERVICE_PORT);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT,methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);			
			click();
			sendKeys(iPhoneNative.IPHONE_NATIVE_ESHOP_WEBSERVICE_CONTEXT);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.CONFIG_SAVE_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_SAVE_BUTTON);			
			click();
			Thread.sleep(2000);			
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}


	public  void iPhoneNativeProjectBuild(String methodName,IPhoneNativeConstantsXml iPhoneNative) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@iPhoneNativeProjectBuild scenario****");
		try {
			if(methodName == "testiPhoneNativeHelloWorldProjectBuild"){
				Thread.sleep(2000);
				waitForElementPresent(phrsc.MENU_TAB, methodName);
				getXpathWebElement(phrsc.MENU_TAB);
				click();	
			}
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

			Thread.sleep(2000);
			waitForElementPresent(phrsc.SDK_SELECT, methodName);
			getXpathWebElement(phrsc.SDK_SELECT);
			selectText(element,iPhoneNative.IPHONE_NATIVE_BUILD_SDK_VALUE);

			/*Thread.sleep(2000);
			waitForElementPresent(phrsc.TARGET_SELECT, methodName);
			getXpathWebElement(phrsc.TARGET_SELECT);
			selectText(element,iPhoneNative.IPHONE_NATIVE_BUILD_TARGET_VALUE);*/

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


	public  void iPhoneNativeProjectDeploy(String methodName,IPhoneNativeConstantsXml iPhoneNative) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@iPhoneNativeProjectDeploy scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.DEPLOY_BUTTON, methodName);
			getXpathWebElement(phrsc.DEPLOY_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.DEPLOY_SDK_VERSION, methodName);
			getXpathWebElement(phrsc.DEPLOY_SDK_VERSION);
			selectText(element, iPhoneNative.IPHONE_NATIVE_DEPLOY_SDK_VERSION);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.DEPLOY_SDK_FAMILY, methodName);
			getXpathWebElement(phrsc.DEPLOY_SDK_FAMILY);
			selectText(element, iPhoneNative.IPHONE_NATIVE_DEPLOY_SDK_FAMILY);

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

	public  void iphoneNativeUnitTestLogic(String methodName,IPhoneNativeConstantsXml iphoneNative) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("**iphoneNativeUnitTestLogic****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.QUALITY_TAB, methodName);
			getXpathWebElement(phrsc.QUALITY_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.UNIT_TAB, methodName);
			getXpathWebElement(phrsc.UNIT_TAB);
			click();

			Thread.sleep(1000);
			waitForElementPresent(phrsc.UNIT_TEST_BUTTON, methodName);
			getXpathWebElement(phrsc.UNIT_TEST_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.SDK_SELECT, methodName);
			getXpathWebElement(phrsc.SDK_SELECT);
			selectText(element, iphoneNative.IPHONE_NATIVE_UNIT_TEST_SDK_VALUE);

			Thread.sleep(2000);			
			waitForElementPresent(phrsc.TARGET_SELECT, methodName);
			getXpathWebElement(phrsc.TARGET_SELECT);
			selectText(element, iphoneNative.IPHONE_NATIVE_UNIT_TEST_LOGIC_VALUE);

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
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}


	public  void iphoneNativeUnitTestApplication(String methodName,IPhoneNativeConstantsXml iphoneNative) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("**iphoneNativeUnitTestApplication****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.UNIT_TAB, methodName);
			getXpathWebElement(phrsc.UNIT_TAB);
			click();

			Thread.sleep(1000);
			waitForElementPresent(phrsc.UNIT_TEST_BUTTON, methodName);
			getXpathWebElement(phrsc.UNIT_TEST_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.SDK_SELECT, methodName);
			getXpathWebElement(phrsc.SDK_SELECT);
			selectText(element, iphoneNative.IPHONE_NATIVE_UNIT_TEST_SDK_VALUE);

			Thread.sleep(2000);			
			waitForElementPresent(phrsc.TARGET_SELECT, methodName);
			getXpathWebElement(phrsc.TARGET_SELECT);
			selectText(element, iphoneNative.IPHONE_NATIVE_UNIT_TEST_APPLICATION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.UNIT_TEST_APPLICATION_TYPE, methodName);
			getXpathWebElement(phrsc.UNIT_TEST_APPLICATION_TYPE);
			click();
			
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
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void iPhoneHybridHelloWorldCreate(String methodName,IPhoneHybridConstantsXml iPhoneHybrid) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@iPhoneHybridHelloWorldCreate scenario****");
		try {
			Thread.sleep(2000);	
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
			sendKeys(iPhoneHybrid.IPHONE_HYBRID_HELLOWORLD_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);			
			click();
			sendKeys(iPhoneHybrid.IPHONE_HYBRID_HELLOWORLD_PROJECT_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.MOB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.MOB_LAYER_CHECKBOX);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.IPHONE_CHECKBOX, methodName);
			getXpathWebElement(phrsc.IPHONE_CHECKBOX);			
			click();

			Thread.sleep(2000);		
			waitForElementPresent(phrsc.IPHONE_TECH_DROPDOWN, methodName);
			getXpathWebElement(phrsc.IPHONE_TECH_DROPDOWN);			
			selectText(element, iPhoneHybrid.IPHONE_HYBRID_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.IPHONE_PHONE_CHECKBOX, methodName);
			getXpathWebElement(phrsc.IPHONE_PHONE_CHECKBOX);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.IPHONE_TABLET_CHECKBOX, methodName);
			getXpathWebElement(phrsc.IPHONE_TABLET_CHECKBOX);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);		
			click();
			Thread.sleep(30000);
			isTextPresent(iPhoneHybrid.IPHONE_HYBRID_HELLOWORLD_PROJECT_NAME, methodName);

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void iPhoneHybridHelloWorldProjectEditApplication(String methodName,IPhoneHybridConstantsXml iPhoneHybrid ) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@iPhoneHybridHelloWorldProjectEditApplication scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(iPhoneHybrid.IPHONE_HYBRID_HELLOWORLD_EDIT_LINK,methodName);
			getXpathWebElement(iPhoneHybrid.IPHONE_HYBRID_HELLOWORLD_EDIT_LINK);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, iPhoneHybrid.FUNCTIONAL_FRAMEWORK);

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

	public  void iPhoneHybridHelloWorldProjectAddFeature(String methodName,IPhoneHybridConstantsXml iPhoneHybrid) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@iPhoneHybridHelloWorldProjectAddFeature scenario****");
		try {
			Thread.sleep(2000);
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

	public  void iPhoneHybridProjectBuild(String methodName,IPhoneHybridConstantsXml iPhoneHybrid) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@iPhoneHybridProjectBuild scenario****");
		try {
			if(methodName == "testiPhoneHybridHelloWorldProjectBuild"){
				Thread.sleep(2000);
				waitForElementPresent(phrsc.MENU_TAB, methodName);
				getXpathWebElement(phrsc.MENU_TAB);
				click();
			}
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

			Thread.sleep(2000);
			waitForElementPresent(phrsc.SDK_SELECT, methodName);
			getXpathWebElement(phrsc.SDK_SELECT);
			selectText(element, iPhoneHybrid.IPHONE_HYBRID_BUILD_SDK_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.TARGET_SELECT, methodName);
			getXpathWebElement(phrsc.TARGET_SELECT);
			selectText(element, iPhoneHybrid.IPHONE_HYBRID_BUILD_TARGET_VALUE);

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

	public  void iPhoneHybridProjectDeploy(String methodName,IPhoneHybridConstantsXml iPhoneHybrid) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@iPhoneHybridProjectDeploy scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.DEPLOY_BUTTON, methodName);
			getXpathWebElement(phrsc.DEPLOY_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.DEPLOY_SDK_VERSION, methodName);
			getXpathWebElement(phrsc.DEPLOY_SDK_VERSION);
			selectText(element, iPhoneHybrid.IPHONE_HYBRID_DEPLOY_SDK_VERSION);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.DEPLOY_SDK_FAMILY, methodName);
			getXpathWebElement(phrsc.DEPLOY_SDK_FAMILY);
			selectText(element, iPhoneHybrid.IPHONE_HYBRID_DEPLOY_SDK_FAMILY);

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

	public  void iPhoneHybridUnitTest(String methodName,IPhoneHybridConstantsXml iphoneHybrid) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("**iphoneHybridUnitTest****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.QUALITY_TAB, methodName);
			getXpathWebElement(phrsc.QUALITY_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.UNIT_TAB, methodName);
			getXpathWebElement(phrsc.UNIT_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.UNIT_TEST_BUTTON, methodName);
			getXpathWebElement(phrsc.UNIT_TEST_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.SDK_SELECT, methodName);
			getXpathWebElement(phrsc.SDK_SELECT);
			selectText(element, iphoneHybrid.IPHONE_HYBRID_UNIT_TEST_SDK_VALUE);

			Thread.sleep(2000);			
			waitForElementPresent(phrsc.TARGET_SELECT, methodName);
			getXpathWebElement(phrsc.TARGET_SELECT);
			selectText(element, iphoneHybrid.IPHONE_HYBRID_UNIT_TEST_TARGET_VALUE);

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
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void iPhoneHybridEshopCreate(String methodName,IPhoneHybridConstantsXml iPhoneHybrid) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@iPhoneHybridEshopCreate scenario****");
		try {
			Thread.sleep(2000);		
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
			sendKeys(iPhoneHybrid.IPHONE_HYBRID_ESHOP_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);			
			click();
			sendKeys(iPhoneHybrid.IPHONE_HYBRID_ESHOP_PROJECT_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.MOB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.MOB_LAYER_CHECKBOX);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.IPHONE_CHECKBOX, methodName);
			getXpathWebElement(phrsc.IPHONE_CHECKBOX);			
			click();

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.IPHONE_TECH_DROPDOWN, methodName);
			getXpathWebElement(phrsc.IPHONE_TECH_DROPDOWN);			
			selectText(element,iPhoneHybrid.IPHONE_HYBRID_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.IPHONE_PHONE_CHECKBOX, methodName);
			getXpathWebElement(phrsc.IPHONE_PHONE_CHECKBOX);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.IPHONE_TABLET_CHECKBOX, methodName);
			getXpathWebElement(phrsc.IPHONE_TABLET_CHECKBOX);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);			
			click();
			Thread.sleep(30000);
			isTextPresent(iPhoneHybrid.IPHONE_HYBRID_ESHOP_PROJECT_NAME, methodName);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void iPhoneHybridEshopProjectEditApplication(String methodName,IPhoneHybridConstantsXml iPhoneHybrid ) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@iPhoneHybridEshopProjectEditApplication scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(iPhoneHybrid.IPHONE_HYBRID_ESHOP_EDIT_LINK,methodName);
			getXpathWebElement(iPhoneHybrid.IPHONE_HYBRID_ESHOP_EDIT_LINK);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, iPhoneHybrid.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN);			
			selectText(element,iPhoneHybrid.IPHONE_HYBRID_PILOT_PROJECT_ESHOP);

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

	public  void iPhoneHybridEshopProjectAddFeature(String methodName,IPhoneHybridConstantsXml iPhoneHybrid) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@iPhoneHybridEshopProjectAddFeature scenario****");
		try {
			Thread.sleep(2000);
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

	public  void iPhoneHybridEshopProjectAddConfigurationServer(String methodName,IPhoneHybridConstantsXml iPhoneHybrid) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@iPhoneHybridEshopProjectAddConfigurationServer scenario****");
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
			sendKeys(iPhoneHybrid.IPHONE_HYBRID_CONFIG_SERVER_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC,methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);			
			click();			
			sendKeys(iPhoneHybrid.IPHONE_HYBRID_CONFIG_SERVER_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);			
			selectText(element,iPhoneHybrid.IPHONE_HYBRID_CONFIG_SERVER_ENV);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);			
			selectText(element,iPhoneHybrid.IPHONE_HYBRID_CONFIG_SERVER_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);			
			selectText(element,iPhoneHybrid.IPHONE_HYBRID_CONFIG_SERVER_PROTOCOL);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST,methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);			
			click();			
			sendKeys(iPhoneHybrid.IPHONE_HYBRID_CONFIG_SERVER_HOST);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT,methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);			
			click();			
			sendKeys(iPhoneHybrid.IPHONE_HYBRID_CONFIG_SERVER_PORT);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT,methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);			
			click();			
			sendKeys(iPhoneHybrid.IPHONE_HYBRID_ESHOP_CONFIG_SERVER_CONTEXT);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_SAVE_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_SAVE_BUTTON);			
			click();
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void iPhoneLibraryCreate(String methodName,IPhoneLibraryConstantsXml iPhoneLibrary) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@iPhoneLibraryCreate  scenario****");
		try {
			Thread.sleep(2000);	
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
			sendKeys(iPhoneLibrary.IPHONE_LIBRARY_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);			
			click();
			sendKeys(iPhoneLibrary.IPHONE_LIBRARY_PROJECT_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.MOB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.MOB_LAYER_CHECKBOX);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.IPHONE_CHECKBOX, methodName);
			getXpathWebElement(phrsc.IPHONE_CHECKBOX);			
			click();

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.IPHONE_TECH_DROPDOWN, methodName);
			getXpathWebElement(phrsc.IPHONE_TECH_DROPDOWN);			
			selectText(element,iPhoneLibrary.IPHONE_LIBRARY_TECHNOLOGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);			
			click();
			Thread.sleep(30000);
			isTextPresent(iPhoneLibrary.IPHONE_LIBRARY_PROJECT_NAME, methodName);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void iPhoneLibraryProjectEditApplication(String methodName,IPhoneLibraryConstantsXml iPhoneLibrary ) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@iPhoneLibraryProjectEditApplication  scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(iPhoneLibrary.IPHONE_LIBRARY_EDIT_LINK,methodName);
			getXpathWebElement(iPhoneLibrary.IPHONE_LIBRARY_EDIT_LINK);			
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

	public  void iPhoneLibraryProjectAddFeature(String methodName,IPhoneLibraryConstantsXml iPhoneLibrary) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@iPhoneLibraryProjectAddFeature: scenario****");
		try {
			Thread.sleep(2000);
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

	public  void iPhoneLibraryProjectBuild(String methodName,IPhoneLibraryConstantsXml iPhoneLibrary) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@iPhoneLibraryProjectBuild scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.MENU_TAB, methodName);
			getXpathWebElement(phrsc.MENU_TAB);
			click();

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

			Thread.sleep(2000);
			waitForElementPresent(phrsc.SDK_SELECT, methodName);
			getXpathWebElement(phrsc.SDK_SELECT);
			selectText(element, iPhoneLibrary.IPHONE_LIBRARY_SDK_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.TARGET_SELECT, methodName);
			getXpathWebElement(phrsc.TARGET_SELECT);
			selectText(element, iPhoneLibrary.IPHONE_LIBRARY_TARGET_VALUE);

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

	public  void iphoneLibraryUnitTest(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("**iphoneLibraryUnitTest****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.QUALITY_TAB, methodName);
			getXpathWebElement(phrsc.QUALITY_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.UNIT_TAB, methodName);
			getXpathWebElement(phrsc.UNIT_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.UNIT_TEST_BUTTON, methodName);
			getXpathWebElement(phrsc.UNIT_TEST_BUTTON);
			click();

			boolean unitTestResult = successFailureLoop();
			System.out.println("unitTestResult:::::"+unitTestResult);
			Thread.sleep(2000);
			waitForElementPresent(phrsc.TEST_CLOSE_BUTTON, methodName);
			getXpathWebElement(phrsc.TEST_CLOSE_BUTTON).click();
			Assert.assertTrue(unitTestResult);		
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void androidNativeEshopCreate(String methodName,AndroidNativeConstantsXml androidNative) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@androidNativeEshopCreate scenario****");
		try {
			Thread.sleep(2000);	
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
			sendKeys(androidNative.ANDROID_NATIVE_ESHOP_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			sendKeys(androidNative.ANDROID_NATIVE_ESHOP_PROJECT_DESC);

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
			selectText(element, androidNative.ANDROID_NATIVE_VALUE);

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
			isTextPresent(androidNative.ANDROID_NATIVE_ESHOP_PROJECT_NAME, methodName);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void androidNativeEshopProjectEditApplication(String methodName,AndroidNativeConstantsXml androidNative ) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@androidNativeEshopProjectEditApplication scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(androidNative.ANDROID_NATIVE_ESHOP_EDIT_LINK,methodName);
			getXpathWebElement(androidNative.ANDROID_NATIVE_ESHOP_EDIT_LINK);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, androidNative.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN);
			selectText(element,androidNative.ANDROID_NATIVE_PILOT_PROJECT_ESHOP);

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

	public  void androidNativeEshopProjectAddFeature(String methodName,AndroidNativeConstantsXml androidNative) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@androidNativeEshopProjectAddFeature scenario****");
		try {
			Thread.sleep(2000);
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

	public  void androidNativeEshopProjectAddConfigurationWebService(String methodName,AndroidNativeConstantsXml androidNative) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@androidNativeEshopProjectAddConfigurationWebService scenario****");
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
			sendKeys(androidNative.ANDROID_NATIVE_CONFIG_WEBSERVICE_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC,methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);			
			click();
			sendKeys(androidNative.ANDROID_NATIVE_CONFIG_WEBSERVICE_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);			
			selectText(element,androidNative.ANDROID_NATIVE_CONFIG_WEBSERVICE_ENV);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);		
			selectText(element,androidNative.ANDROID_NATIVE_CONFIG_WEBSERVICE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);			
			selectText(element,androidNative.ANDROID_NATIVE_CONFIG_WEBSERVICE_PROTOCOL);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST,methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);			
			click();
			sendKeys(androidNative.ANDROID_NATIVE_CONFIG_WEBSERVICE_HOST);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT,methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);			
			click();
			sendKeys(androidNative.ANDROID_NATIVE_CONFIG_WEBSERVICE_PORT);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT,methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);			
			click();
			sendKeys(androidNative.ANDROID_NATIVE_ESHOP_CONFIG_WEBSERVICE_CONTEXT);

			Thread.sleep(2000);		
			waitForElementPresent(phrsc.CONFIG_SAVE_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_SAVE_BUTTON);			
			click();
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void androidNativeHelloWorldCreate(String methodName,AndroidNativeConstantsXml androidNative) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@androidNativeHelloWorldCreate scenario****");
		try {
			Thread.sleep(2000);		
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
			sendKeys(androidNative.ANDROID_NATIVE_HELLOWORLD_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			sendKeys(androidNative.ANDROID_NATIVE_HELLOWORLD_PROJECT_DESC);

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
			selectText(element,androidNative.ANDROID_NATIVE_VALUE);

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
			isTextPresent(androidNative.ANDROID_NATIVE_HELLOWORLD_PROJECT_NAME, methodName);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void androidNativeHelloWorldProjectEditApplication(String methodName,AndroidNativeConstantsXml androidNative ) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@androidNativeHelloWorldProjectEditApplication scenario****");
		try {
			Thread.sleep(2000);		
			waitForElementPresent(androidNative.ANDROID_NATIVE_HELLOWORLD_EDIT_LINK,methodName);
			getXpathWebElement(androidNative.ANDROID_NATIVE_HELLOWORLD_EDIT_LINK);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, androidNative.FUNCTIONAL_FRAMEWORK);

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

	public  void androidNativeHelloWorldProjectAddFeature(String methodName,AndroidNativeConstantsXml androidNative) throws Exception {
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
			boolean updateSuccess = updateSuccessFailureLoop();
			System.out.println("updateSuccess::::"+updateSuccess);
			Assert.assertTrue(updateSuccess);
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void androidNativeProjectBuild(String methodName,AndroidNativeConstantsXml androidNative) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@androidNativeProjectBuild scenario****");
		try {
			if(methodName == "testAndroidNativeHelloWorldProjectBuild"){
				Thread.sleep(2000);
				waitForElementPresent(phrsc.MENU_TAB, methodName);
				getXpathWebElement(phrsc.MENU_TAB);
				click();
			}
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
			selectText(element, androidNative.SDK_VALUE);

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

	public  void androidNativeProjectDeploy(String methodName,AndroidNativeConstantsXml androidNative) throws Exception {
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
			selectText(element,androidNative.EMULATOR_VALUE);

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

	public  void androidHybridHelloWorldCreate(String methodName,AndroidHybridConstantsXml androidHybrid) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@androidHybridHelloWorldCreate scenario****");
		try {
			Thread.sleep(2000);		
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
			sendKeys(androidHybrid.ANDROID_HYBRID_HELLOWORLD_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);			
			click();
			sendKeys(androidHybrid.ANDROID_HYBRID_HELLOWORLD_PROJECT_DESC);

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
			selectText(element, androidHybrid.ANDROID_HYBRID_VALUE);

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
			isTextPresent(androidHybrid.ANDROID_HYBRID_HELLOWORLD_PROJECT_NAME, methodName);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void androidHybridHelloWorldProjectEditApplication(String methodName,AndroidHybridConstantsXml androidHybrid ) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@androidHybridHelloWorldProjectEditApplication: scenario****");
		try {
			Thread.sleep(2000);			
			waitForElementPresent(androidHybrid.ANDROID_HYBRID_HELLOWORLD_EDIT_LINK,methodName);
			getXpathWebElement(androidHybrid.ANDROID_HYBRID_HELLOWORLD_EDIT_LINK);			
			click();

			Thread.sleep(10000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, androidHybrid.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			click();
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void androidHybridHelloWorldProjectAddFeature(String methodName,AndroidHybridConstantsXml androidHybrid) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@androidHybridHelloWorldProjectAddFeature::******executing androidHybridHelloWorldProjectAddFeature scenario****");
		try {
			Thread.sleep(2000);
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

	public  void androidHybridProjectBuild(String methodName,AndroidHybridConstantsXml androidHybrid) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@androidHybridProjectBuild scenario****");
		try {
			if(methodName == "testAndroidHybridHelloWorldProjectBuild"){		
				Thread.sleep(2000);
				waitForElementPresent(phrsc.MENU_TAB, methodName);
				getXpathWebElement(phrsc.MENU_TAB);
				click();
			}
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

			Thread.sleep(2000);
			waitForElementPresent(phrsc.SKIP_UNIT_TEST, methodName);
			getXpathWebElement(phrsc.SKIP_UNIT_TEST);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.SDK_SELECT, methodName);
			getXpathWebElement(phrsc.SDK_SELECT);
			selectText(element, androidHybrid.SDK_VALUE);

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

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void androidHybridProjectDeploy(String methodName,AndroidHybridConstantsXml androidHybrid) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@androidHybridProjectDeploy scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.DEPLOY_BUTTON, methodName);
			getXpathWebElement(phrsc.DEPLOY_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.DEPLOY_DEVICE, methodName);
			getXpathWebElement(phrsc.DEPLOY_DEVICE);
			selectText(element,androidHybrid.EMULATOR_VALUE);

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


	public  void androidHybridEshopCreate(String methodName,AndroidHybridConstantsXml androidHybrid) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@androidHybridEshopCreate scenario****");
		try {
			Thread.sleep(2000);		
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
			sendKeys(androidHybrid.ANDROID_HYBRID_ESHOP_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);			
			click();
			sendKeys(androidHybrid.ANDROID_HYBRID_ESHOP_PROJECT_DESC);

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
			selectText(element,androidHybrid.ANDROID_HYBRID_VALUE);

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
			isTextPresent(androidHybrid.ANDROID_HYBRID_ESHOP_PROJECT_NAME,methodName);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void androidHybridEshopProjectEditApplication(String methodName,AndroidHybridConstantsXml androidHybrid ) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@androidHybridEshopProjectEditApplication scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(androidHybrid.ANDROID_HYBRID_ESHOP_EDIT_LINK,methodName);
			getXpathWebElement(androidHybrid.ANDROID_HYBRID_ESHOP_EDIT_LINK);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, androidHybrid.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN);			
			selectText(element,androidHybrid.ANDROID_HYBRID_PILOT_PROJECT_ESHOP);

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

	public  void androidHybridEshopProjectAddFeature(String methodName,AndroidHybridConstantsXml androidHybrid) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@androidHybridEshopProjectAddFeature scenario****");
		try {
			Thread.sleep(2000);
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

	public  void androidHybridProjectAddConfigurationServer(String methodName,AndroidHybridConstantsXml androidHybrid) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@androidHybridEshopProjectAddConfigurationServer scenario****");
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
			sendKeys(androidHybrid.ANDROID_HYBRID_CONFIG_SERVER_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC,methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);			
			click();
			sendKeys(androidHybrid.ANDROID_HYBRID_CONFIG_SERVER_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);			
			selectText(element,androidHybrid.ANDROID_HYBRID_CONFIG_SERVER_ENV);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);			
			selectText(element,androidHybrid.ANDROID_HYBRID_CONFIG_SERVER_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);			
			selectText(element,androidHybrid.ANDROID_HYBRID_CONFIG_SERVER_PROTOCOL);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST,methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);			
			click();
			sendKeys(androidHybrid.ANDROID_HYBRID_CONFIG_SERVER_HOST);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT,methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);			
			click();
			sendKeys(androidHybrid.ANDROID_HYBRID_CONFIG_SERVER_PORT);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.CONFIG_CONTEXT,methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);		
			click();
			sendKeys(androidHybrid.ANDROID_HYBRID_ESHOP_CONFIG_SERVER_CONTEXT);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.CONFIG_SAVE_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_SAVE_BUTTON);			
			click();
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void androidLibraryCreate(String methodName,AndroidLibraryConstantsXml androidLibrary) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@androidLibraryCreate scenario****");
		try {
			Thread.sleep(2000);	
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
			sendKeys(androidLibrary.ANDROID_LIBRARY_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);			
			click();
			sendKeys(androidLibrary.ANDROID_LIBRARY_PROJECT_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.MOB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.MOB_LAYER_CHECKBOX);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.MOB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.ANDROID_CHECKBOX);
			click();

			Thread.sleep(2000);		
			waitForElementPresent(phrsc.ANDROID_TECH_DROPDOWN, methodName);
			getXpathWebElement(phrsc.ANDROID_TECH_DROPDOWN);			
			selectText(element,androidLibrary.ANDROID_LIBRARY_TECHNOLOGY_VALUE);

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
			isTextPresent(androidLibrary.ANDROID_LIBRARY_PROJECT_NAME, methodName);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void androidLibraryProjectEditApplication(String methodName,AndroidLibraryConstantsXml androidLibrary ) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@androidLibraryProjectEditApplication  scenario****");
		try {
			Thread.sleep(10000);
			waitForElementPresent(androidLibrary.ANDROID_LIBRARY_EDIT_LINK,methodName);
			getXpathWebElement(androidLibrary.ANDROID_LIBRARY_EDIT_LINK);			
			click();

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);			
			click();
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void androidLibraryProjectAddFeature(String methodName,AndroidLibraryConstantsXml androidLibrary) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@androidLibraryProjectAddFeature  scenario****");
		try {
			Thread.sleep(2000);
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

	public  void androidLibraryProjectBuild(String methodName,AndroidLibraryConstantsXml androidLibrary) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@androidLibraryProjectBuild scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.MENU_TAB, methodName);
			getXpathWebElement(phrsc.MENU_TAB);
			click();

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

			Thread.sleep(2000);
			waitForElementPresent(phrsc.SDK_SELECT, methodName);
			getXpathWebElement(phrsc.SDK_SELECT);
			selectText(element, androidLibrary.SDK_VALUE);

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
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void blackBerryHybridCreate(String methodName,BlackBerryHybridConstantsXml blackBerryHybrid) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@blackBerryHybridCreate scenario****");
		try {
			Thread.sleep(2000);		
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
			sendKeys(blackBerryHybrid.BLACKBERRY_HYBRID_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);			
			click();
			sendKeys(blackBerryHybrid.BLACKBERRY_HYBRID_PROJECT_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.MOB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.MOB_LAYER_CHECKBOX);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.BLACKBERRY_CHECKBOX, methodName);
			getXpathWebElement(phrsc.BLACKBERRY_CHECKBOX);			
			click();

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.BLACKBERRY_TECH_DROPDOWN, methodName);
			getXpathWebElement(phrsc.BLACKBERRY_TECH_DROPDOWN);			
			selectText(element,blackBerryHybrid.BLACKBERRY_HYBRID_TECHNOLOGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.BLACKBERRY_PHONE_CHECKBOX, methodName);
			getXpathWebElement(phrsc.BLACKBERRY_PHONE_CHECKBOX);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.BLACKBERRY_TABLET_CHECKBOX, methodName);
			getXpathWebElement(phrsc.BLACKBERRY_TABLET_CHECKBOX);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);			
			click();
			Thread.sleep(30000);
			isTextPresent(blackBerryHybrid.BLACKBERRY_HYBRID_PROJECT_NAME, methodName);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void blackBerryHybridProjectEditApplication(String methodName,BlackBerryHybridConstantsXml blackBerryHybrid ) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@blackBerryHybridProjectEditApplication scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(blackBerryHybrid.BLACKBERRY_HYBRID_EDIT_LINK,methodName);
			getXpathWebElement(blackBerryHybrid.BLACKBERRY_HYBRID_EDIT_LINK);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_CHECK_ALL, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_CHECK_ALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_DROPDOWN);
			selectText(element,blackBerryHybrid.BLACKBERRY_HYBRID_SERVER_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_VERSION_TOMCAT, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_VERSION_TOMCAT);
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

	public  void blackBerryHybridProjectAddFeature(String methodName,BlackBerryHybridConstantsXml blackBerryHybrid) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@blackBerryHybridProjectAddFeature scenario****");
		try {	
			Thread.sleep(3000);
			waitForElementPresent(phrsc.PROJECT_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.PROJECT_FINISH_BUTTON);			
			click();
			Thread.sleep(2000);
			boolean updateSuccess = updateSuccessFailureLoop();
			System.out.println("updateSuccess::::"+updateSuccess);
			Assert.assertTrue(updateSuccess);
			Thread.sleep(4000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void blackBerryHybridAddConfigurationServer(String methodName,BlackBerryHybridConstantsXml blackBerryHybrid) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@blackBerryHybridAddConfigurationServer scenario****");
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
			sendKeys(blackBerryHybrid.BLACKBERRY_HYBRID_CONFIG_SERVER_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC,methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);	
			click();
			sendKeys(blackBerryHybrid.BLACKBERRY_HYBRID_CONFIG_SERVER_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);			
			selectText(element,blackBerryHybrid.BLACKBERRY_HYBRID_CONFIG_SERVER_ENV);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);			
			selectText(element,blackBerryHybrid.BLACKBERRY_HYBRID_CONFIG_SERVER_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);			
			selectText(element,blackBerryHybrid.BLACKBERRY_HYBRID_CONFIG_SERVER_PROTOCOL);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST,methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);			
			click();
			sendKeys(blackBerryHybrid.BLACKBERRY_HYBRID_CONFIG_SERVER_HOST);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT,methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);			
			click();
			sendKeys(blackBerryHybrid.BLACKBERRY_HYBRID_CONFIG_SERVER_PORT);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.CONFIG_CONTEXT,methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);		
			click();
			sendKeys(blackBerryHybrid.BLACKBERRY_HYBRID_CONFIG_SERVER_CONTEXT);

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.CONFIG_SAVE_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_SAVE_BUTTON);			
			click();
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void blackBerryProjectBuild(String methodName,BlackBerryHybridConstantsXml blackBerryHybrid) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@blackBerryProjectBuild scenario****");
		try {
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

	public  void blackBerryProjectDeploy(String methodName,BlackBerryHybridConstantsXml blackBerryHybrid) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@blackBerryProjectDeploy scenario****");
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
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void windowsMetroProjectCreate(String methodName,WindowsMetroConstantsXml windowsMetro) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@windowsMetroProjectCreate scenario****");
		try {
			Thread.sleep(2000);		
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
			sendKeys(windowsMetro.WINDOWS_METRO_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);			
			click();
			sendKeys(windowsMetro.WINDOWS_METRO_PROJECT_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.MOB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.MOB_LAYER_CHECKBOX);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WINDOWS_CHECKBOX, methodName);
			getXpathWebElement(phrsc.WINDOWS_CHECKBOX);			
			click();

			Thread.sleep(2000);		
			waitForElementPresent(phrsc.WINDOWS_TECH_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WINDOWS_TECH_DROPDOWN);			
			selectText(element, windowsMetro.WINDOWS_METRO_TECHNOLOGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WINDOWS_PHONE_CHECKBOX, methodName);
			getXpathWebElement(phrsc.WINDOWS_PHONE_CHECKBOX);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WINDOWS_TABLET_CHECKBOX, methodName);
			getXpathWebElement(phrsc.WINDOWS_TABLET_CHECKBOX);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);		
			click();
			Thread.sleep(30000);
			isTextPresent(windowsMetro.WINDOWS_METRO_PROJECT_NAME, methodName);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void windowsMetroProjectEditApplication(String methodName,WindowsMetroConstantsXml windowsMetro) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@windowsMetroProjectEditApplication scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(windowsMetro.WINDOWS_METRO_EDIT_LINK,methodName);
			getXpathWebElement(windowsMetro.WINDOWS_METRO_EDIT_LINK);			
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

	public  void windowsMetroProjectAddFeature(String methodName,WindowsMetroConstantsXml windowsMetro) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@windowsMetroProjectAddFeature scenario****");
		try {
			Thread.sleep(2000);
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

	public  void windowsMetroProjectBuild(String methodName,WindowsMetroConstantsXml windowsMetro) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@windowsMetroProjectBuild scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.MENU_TAB, methodName);
			getXpathWebElement(phrsc.MENU_TAB);
			click();

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

	public  void windowsMetroProjectDeploy(String methodName,WindowsMetroConstantsXml windowsMetro) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@windowsMetroProjectDeploy scenario****");
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
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void windowsPhoneProjectCreate(String methodName,WindowsPhoneConstantsXml windowsPhone) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@windowsPhoneProjectCreate scenario****");
		try {
			Thread.sleep(2000);		
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
			sendKeys(windowsPhone.WINDOWS_PHONE_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);			
			click();
			sendKeys(windowsPhone.WINDOWS_PHONE_PROJECT_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.MOB_LAYER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.MOB_LAYER_CHECKBOX);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WINDOWS_CHECKBOX, methodName);
			getXpathWebElement(phrsc.WINDOWS_CHECKBOX);			
			click();

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.WINDOWS_TECH_DROPDOWN, methodName);
			getXpathWebElement(phrsc.WINDOWS_TECH_DROPDOWN);			
			selectText(element,windowsPhone.WINDOWS_PHONE_TECHNOLOGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WINDOWS_PHONE_CHECKBOX, methodName);
			getXpathWebElement(phrsc.WINDOWS_PHONE_CHECKBOX);			
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.WINDOWS_TABLET_CHECKBOX, methodName);
			getXpathWebElement(phrsc.WINDOWS_TABLET_CHECKBOX);		
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);			
			click();
			Thread.sleep(30000);
			isTextPresent(windowsPhone.WINDOWS_PHONE_PROJECT_NAME, methodName);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void windowsPhoneProjectEditApplication(String methodName,WindowsPhoneConstantsXml windowsPhone) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@windowsPhoneProjectEditApplication: scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(windowsPhone.WINDOWS_PHONE_EDIT_LINK,methodName);
			getXpathWebElement(windowsPhone.WINDOWS_PHONE_EDIT_LINK);			
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

	public  void windowsPhoneProjectAddFeature(String methodName,WindowsPhoneConstantsXml windowsPhone) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@windowsPhoneProjectAddFeature scenario****");
		try {
			Thread.sleep(2000);
			getXpathWebElement(phrsc.PROJECT_FINISH_BUTTON);
			waitForElementPresent(phrsc.PROJECT_FINISH_BUTTON,methodName);
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

	public  void windowsPhoneProjectBuild(String methodName,WindowsPhoneConstantsXml windowsPhone) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@windowsPhoneProjectBuild scenario****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.MENU_TAB, methodName);
			getXpathWebElement(phrsc.MENU_TAB);
			click();

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

	public  void windowsPhoneProjectDeploy(String methodName,WindowsPhoneConstantsXml windowsPhone) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@windowsPhoneProjectDeploy scenario****");
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
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public boolean isTextPresentBuild(String text) throws InterruptedException, ScreenException {

		if (text!= null){
			String build_failure="ERROR";

			for(int i=0;i<=200;i++){
				if(driver.findElement(By.tagName("body")).getText().contains(text)){
					break;
				}
				else{
					if(i==204){
						throw new RuntimeException("---- Time out for finding the Text----");
					}
					else if(driver.findElement(By.tagName("body")).getText().contains(build_failure)){
						System.out.println("*****Build Failure*****");
						throw new ScreenException("*****Build Failure*****");

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
		while (!statusStringFound) { /*&& end <=70000*/
			if (driver.findElement(By.tagName("body")).getText().contains(BUILD_SUCCESS)) {
				return BUILD_SUCCESS;
			} else if (driver.findElement(By.tagName("body")).getText().contains(BUILD_FAILURE))  {
				return BUILD_FAILURE;
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
