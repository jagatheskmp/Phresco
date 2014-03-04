package com.photon.phresco.Screens.AppLayer;

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
import com.photon.phresco.uiconstants.AspDotNetConstantsXml;
import com.photon.phresco.uiconstants.Drupal6ConstantsXml;
import com.photon.phresco.uiconstants.Drupal7ConstantsXml;
import com.photon.phresco.uiconstants.JavaStandaloneConstantsXml;
import com.photon.phresco.uiconstants.JavaWebServiceConstantsXml;
import com.photon.phresco.uiconstants.NodeJsWebServiceConstantsXml;
import com.photon.phresco.uiconstants.PhpConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.SharePointConstantsXml;
import com.photon.phresco.uiconstants.SiteCoreConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.WordPressConstantsXml;
import com.photon.phresco.uiconstants.phresco_env_config;

public class AppBaseScreen {

	private WebDriver driver;
	private ChromeDriverService chromeService;
	private Log log = LogFactory.getLog("BaseScreen");
	private WebElement element;
	private String resolution = null;
	private PhrescoUiConstantsXml phrsc;
	private phresco_env_config phrscEnv;
	private UserInfoConstants userInfo;
	DesiredCapabilities capabilities;

	public AppBaseScreen() {
	}
	public AppBaseScreen(String selectedBrowser, String selectedPlatform, String applicationURL,String applicatinContext,phresco_env_config phrscEnv, PhrescoUiConstantsXml phrsc, UserInfoConstants userInfo,PhrescoFrameworkData phrscData) throws ScreenException, MalformedURLException {
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

	public void phpProjectHelloWorldCreate(String methodName,
			PhpConstantsXml phpConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@phpProjectHelloWorldCreate::****** executing ****");
		try {
			Thread.sleep(3000);
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
			sendKeys(phpConst.PHP_HELLOWORLD_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			clear();
			sendKeys(phpConst.PHP_HELLOWORLD_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_CHECKALL, methodName);
			getXpathWebElement(phrsc.APP_LAYER_CHECKALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_LAYER_DROPDOWN);
			selectText(element, phpConst.PHP_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(phpConst.PHP_HELLOWORLD_NAME_VALUE, methodName);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void phpProjectHelloWorldEditApplication(String methodName,
			PhpConstantsXml phpConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@phpProjectHelloWorldEditApplication::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phpConst.PHP_HELLOWORLD_EDIT_APP_LINK,methodName);
			getXpathWebElement(phpConst.PHP_HELLOWORLD_EDIT_APP_LINK);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, phpConst.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);
			getXpathWebElement(phrsc.APP_INFO_SERVER_CHECK_ALL);
			waitForElementPresent(phrsc.APP_INFO_SERVER_CHECK_ALL, methodName);
			click();

			Thread.sleep(2000);
			getXpathWebElement(phrsc.APP_INFO_SERVER_DROPDOWN);
			waitForElementPresent(phrsc.APP_INFO_SERVER_DROPDOWN, methodName);
			selectText(element,phpConst.PHP_SERVER_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_VERSION_CHECKBOX, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_VERSION_CHECKBOX);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_DB_CHECK_ALL, methodName);
			getXpathWebElement(phrsc.APP_INFO_DB_CHECK_ALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_DB_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_DB_DROPDOWN);
			selectText(element, phpConst.PHP_DB_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			click();
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void phpProjectHelloWorldAddFeature(String methodName,
			PhpConstantsXml phpConstants) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@phpProjectHelloWorldAddFeature::****** executing ****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.PROJECT_FINISH_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_FINISH_BUTTON);
			click();			
			boolean updateTestResult = updateSuccessFailureLoop();
			System.out.println("updateTestResult::::"+updateTestResult);
			Assert.assertTrue(updateTestResult);
			Thread.sleep(3000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}


	public void phpProjectBlogCreate(String methodName, PhpConstantsXml phpConst)
	throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@phpProjectBlogCreate::****** executing ****");
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
			sendKeys(phpConst.PHP_BLOG_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			clear();
			sendKeys(phpConst.PHP_BLOG_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_CHECKALL, methodName);
			getXpathWebElement(phrsc.APP_LAYER_CHECKALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_LAYER_DROPDOWN);
			selectText(element, phpConst.PHP_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(phpConst.PHP_BLOG_NAME_VALUE, methodName);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void phpProjectBlogEditApplication(String methodName,
			PhpConstantsXml phpConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@phpProjectBlogEditApplication::****** executing ****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phpConst.PHP_BLOG_EDIT_APP_LINK, methodName);
			getXpathWebElement(phpConst.PHP_BLOG_EDIT_APP_LINK);
			click();

			Thread.sleep(10000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, phpConst.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN);
			selectText(element,phpConst.PHP_PILOT_PROJECT_BLOG_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			click();
			Thread.sleep(5000);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void phpProjectBlogAddFeature(String methodName,
			PhpConstantsXml phpConstants) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@phpProjectBlogAddFeature::****** executing ****");
		try {
			Thread.sleep(3000);
			waitForElementPresent(phrsc.PROJECT_FINISH_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_FINISH_BUTTON);
			click();		
			boolean updateTestResult = updateSuccessFailureLoop();
			System.out.println("updateTestResult::"+updateTestResult);
			Assert.assertTrue(updateTestResult);
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void phpProjectAddConfigurationServer(String methodName,
			PhpConstantsXml phpConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@phpProjectAddConfigurationServer::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.MENU_TAB, methodName);
			getXpathWebElement(phrsc.MENU_TAB);
			click();

			Thread.sleep(5000);
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
			sendKeys(phpConst.PHP_CONF_SERVER_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC, methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			sendKeys(phpConst.PHP_CONF_SERVER_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element, phpConst.PHP_CONF_SERVER_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE, methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,phpConst.PHP_CONF_SERVER_TYPE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element,phpConst.PHP_CONF_SERVER_PROTOCOL_HTTP_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST, methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();			
			sendKeys(phpConst.PHP_CONF_SERVER_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT, methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			sendKeys(phpConst.PHP_CONF_SERVER_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_BUTTON,methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_LOCATION,methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_LOCATION);
			click();
			sendKeys(phpConst.PHP_CONF_SERVER_DEPLOY_DIRECTORY);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_OK,methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_OK);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT,methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);
			click();
			if (methodName == "testPhpBlogProjectAddConfigurationServer") {
				sendKeys(phpConst.PHP_CONF_SERVER_CONTEXT_BLOG);
				Thread.sleep(2000);
			} else if (methodName == "testPhpHelloWorldProjectAddConfigurationServer") {
				sendKeys(phpConst.PHP_CONF_SERVER_CONTEXT_HELLOWORLD);
				Thread.sleep(2000);
			}
			waitForElementPresent(phrsc.CONFIG_SAVE_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_SAVE_BUTTON);
			click();
			boolean configServerTestResult = configServerSuccessFailureLoop();
			System.out.println("configServerTestResult::::::::"+configServerTestResult);
			Assert.assertTrue(configServerTestResult);
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void phpProjectAddConfigurationDatabase(String methodName,
			PhpConstantsXml phpConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@phpProjectAddConfigurationDatabase::****** executing ****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.CONFIG_ADD_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_ADD_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_NAME, methodName);
			getXpathWebElement(phrsc.CONFIG_NAME);
			click();
			clear();
			sendKeys(phpConst.PHP_CONF_DATABASE_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC, methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(phpConst.PHP_CONF_DATABASE_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,phpConst.PHP_CONF_DATABASE_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE, methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,phpConst.PHP_CONF_DATABASE_TYPE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST,methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(phpConst.PHP_CONF_DATABASE_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT,methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(phpConst.PHP_CONF_DATABASE_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_USERNAME,methodName);
			getXpathWebElement(phrsc.CONFIG_USERNAME);
			click();
			clear();
			sendKeys(phpConst.PHP_CONF_DB_USERNAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DB_NAME,methodName);
			getXpathWebElement(phrsc.CONFIG_DB_NAME);
			click();
			clear();
			if (methodName == "testPhpBlogProjectDatabase") {
				sendKeys(phpConst.PHP_CONF_DB_NAME_BLOG);
				Thread.sleep(2000);
			} else if (methodName == "testPhpHelloWorldProjectDatabase") {
				sendKeys(phpConst.PHP_CONF_DB_NAME_HELLOWORLD);
				Thread.sleep(2000);

			}
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
	public void drupal6ProjectHelloWorldCreate(String methodName,
			Drupal6ConstantsXml drupal6Const) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@drupal6ProjectHelloWorldCreate::****** executing ****");
		try {
			Thread.sleep(3000);
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
			sendKeys(drupal6Const.DRUPAL6_HELLOWORLD_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			sendKeys(drupal6Const.DRUPAL6_HELLOWORLD_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_CHECKALL, methodName);
			getXpathWebElement(phrsc.APP_LAYER_CHECKALL);
			click();

			Thread.sleep(2000);
			getXpathWebElement(phrsc.APP_LAYER_DROPDOWN);
			waitForElementPresent(phrsc.APP_LAYER_DROPDOWN, methodName);
			selectText(element,drupal6Const.DRUPAL6_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(5000);
			isTextPresent(drupal6Const.DRUPAL6_HELLOWORLD_NAME_VALUE, methodName);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public void drupal6ProjectHelloWorldEditApplication(String methodName,
			Drupal6ConstantsXml drupal6Const) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@drupal6ProjectHelloWorldEditApplication::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(drupal6Const.DRUPAL6_HELLOWORLD_EDIT_APP_LINK,methodName);
			getXpathWebElement(drupal6Const.DRUPAL6_HELLOWORLD_EDIT_APP_LINK);
			click();

			Thread.sleep(10000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, drupal6Const.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_CHECK_ALL, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_CHECK_ALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_DROPDOWN);
			selectText(element,drupal6Const.DRUPAL6_SERVER_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_VERSION_CHECKBOX, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_VERSION_CHECKBOX);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_DB_CHECK_ALL, methodName);
			getXpathWebElement(phrsc.APP_INFO_DB_CHECK_ALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_DB_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_DB_DROPDOWN);
			selectText(element,drupal6Const.DRUPAL6_DB_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			click();
			Thread.sleep(5000);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void drupal6ProjectHelloWorldAddFeature(String methodName,
			Drupal6ConstantsXml drupal6Const) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@drupal6ProjectHelloWorldAddFeature::****** executing ****");
		try {
			Thread.sleep(3000);
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

	public void drupal6ProjectHelloWorldAddConfigurationServer(
			String methodName, Drupal6ConstantsXml drupal6Const)
	throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@drupal6ProjectHelloWorldAddConfigurationServer::****** executing ****");
		try{
			Thread.sleep(5000);
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
			sendKeys(drupal6Const.DRUPAL6_CONF_SERVER_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC, methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			sendKeys(drupal6Const.DRUPAL6_CONF_SERVER_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,drupal6Const.DRUPAL6_CONF_SERVER_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE, methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,drupal6Const.DRUPAL6_CONF_SERVER_TYPE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL, methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element,drupal6Const.DRUPAL6_CONF_SERVER_PROTOCOL_HTTP_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST, methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(drupal6Const.DRUPAL6_CONF_SERVER_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT, methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(drupal6Const.DRUPAL6_CONF_SERVER_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_LOCATION,	methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_LOCATION);
			click();
			clear();
			sendKeys(drupal6Const.DRUPAL6_CONF_SERVER_DEPLOY_DIRECTORY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_OK,methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_OK);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT,	methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);
			click();
			clear();
			sendKeys(drupal6Const.DRUPAL6_CONF_SERVER_CONTEXT_HELLOWORLD);

			Thread.sleep(2000);
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

	public void drupal6ProjectHelloWorldAddConfigurationDatabase(
			String methodName, Drupal6ConstantsXml drupal6Const)
	throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@drupal6ProjectHelloWorldAddConfigurationDatabase::****** executing ****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.CONFIG_ADD_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_ADD_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_NAME, methodName);
			getXpathWebElement(phrsc.CONFIG_NAME);
			click();
			sendKeys(drupal6Const.DRUPAL6_CONF_DATABASE_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC, methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			sendKeys(drupal6Const.DRUPAL6_CONF_DATABASE_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,	methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,drupal6Const.DRUPAL6_CONF_DATABASE_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE, methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,drupal6Const.DRUPAL6_CONF_DATABASE_TYPE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST, methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			sendKeys(drupal6Const.DRUPAL6_CONF_DATABASE_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT, methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(drupal6Const.DRUPAL6_CONF_DATABASE_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_USERNAME, methodName);
			getXpathWebElement(phrsc.CONFIG_USERNAME);
			click();
			clear();
			sendKeys(drupal6Const.DRUPAL6_CONF_DB_USERNAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DB_NAME,	methodName);
			getXpathWebElement(phrsc.CONFIG_DB_NAME);
			click();
			clear();
			sendKeys(drupal6Const.DRUPAL6_CONF_DB_NAME);

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

	public void drupal7ProjectHelloWorldCreate(String methodName,
			Drupal7ConstantsXml drupal7Const) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@drupal7ProjectHelloWorldCreate::****** executing ****");
		try {
			Thread.sleep(3000);
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
			sendKeys(drupal7Const.DRUPAL7_HELLOWORLD_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			sendKeys(drupal7Const.DRUPAL7_HELLOWORLD_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_CHECKALL, methodName);
			getXpathWebElement(phrsc.APP_LAYER_CHECKALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_LAYER_DROPDOWN);
			selectText(element,drupal7Const.DRUPAL7_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(drupal7Const.DRUPAL7_HELLOWORLD_NAME_VALUE, methodName);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void drupal7ProjectHelloWorldEditApplication(String methodName,
			Drupal7ConstantsXml drupal7Const) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@drupal7ProjectHelloWorldEditApplication::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(drupal7Const.DRUPAL7_HELLOWORLD_EDIT_APP_LINK,methodName);
			getXpathWebElement(drupal7Const.DRUPAL7_HELLOWORLD_EDIT_APP_LINK);
			click();

			Thread.sleep(10000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, drupal7Const.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_CHECK_ALL, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_CHECK_ALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_DROPDOWN);
			selectText(element,drupal7Const.DRUPAL7_SERVER_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_VERSION_CHECKBOX, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_VERSION_CHECKBOX);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_DB_CHECK_ALL, methodName);
			getXpathWebElement(phrsc.APP_INFO_DB_CHECK_ALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_DB_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_DB_DROPDOWN);
			selectText(element,drupal7Const.DRUPAL7_DB_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			click();
			Thread.sleep(2000);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void drupal7ProjectAddFeature(String methodName,
			Drupal7ConstantsXml drupal7Const) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@drupal7ProjectHelloWorldAddFeature::****** executing ****");
		try {
			Thread.sleep(5000);
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

	public void drupal7ProjectEshopCreate(String methodName,
			Drupal7ConstantsXml drupal7Const) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@drupal7ProjectEshopCreate::****** executing ****");
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
			sendKeys(drupal7Const.DRUPAL7_ESHOP_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			sendKeys(drupal7Const.DRUPAL7_ESHOP_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_CHECKALL, methodName);
			getXpathWebElement(phrsc.APP_LAYER_CHECKALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_LAYER_DROPDOWN);
			selectText(element,drupal7Const.DRUPAL7_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(50000);
			isTextPresent(drupal7Const.DRUPAL7_ESHOP_NAME_VALUE, methodName);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void drupal7ProjectEshopEditApplication(String methodName,
			Drupal7ConstantsXml drupal7Const) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@drupal7ProjectEshopEditApplication::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(drupal7Const.DRUPAL7_ESHOP_EDIT_APP_LINK,methodName);
			getXpathWebElement(drupal7Const.DRUPAL7_ESHOP_EDIT_APP_LINK);
			click();

			Thread.sleep(10000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, drupal7Const.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN);
			selectText(element,drupal7Const.DRUPAL7_PILOT_PROJECT_VALUE);

			Thread.sleep(2000);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			click();
			Thread.sleep(2000);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void drupal7ProjectEshopAddFeature(String methodName,
			Drupal7ConstantsXml drupal7Const) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@drupal7ProjectEshopAddFeature::****** executing ****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.PROJECT_FINISH_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_FINISH_BUTTON);
			click();
			boolean updateTestResult = updateSuccessFailureLoop();
			System.out.println("updateTestResult::"+updateTestResult);
			Assert.assertTrue(updateTestResult);
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void drupal7ProjectAddConfigurationServer(String methodName,
			Drupal7ConstantsXml drupal7Const) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@drupal7ProjectAddConfigurationServer::****** executing ****");
		try {
			Thread.sleep(5000);
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
			sendKeys(drupal7Const.DRUPAL7_CONF_SERVER_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC, methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(drupal7Const.DRUPAL7_CONF_SERVER_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,	methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,drupal7Const.DRUPAL7_CONF_SERVER_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE, methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,drupal7Const.DRUPAL7_CONF_SERVER_TYPE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL, methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element,drupal7Const.DRUPAL7_CONF_SERVER_PROTOCOL_HTTP_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST, methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(drupal7Const.DRUPAL7_CONF_SERVER_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT, methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(drupal7Const.DRUPAL7_CONF_SERVER_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_LOCATION,	methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_LOCATION);
			click();
			clear();
			sendKeys(drupal7Const.DRUPAL7_CONF_SERVER_DEPLOY_DIRECTORY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_OK, methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_OK);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT,	methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);
			click();
			clear();
			if (methodName == "testDrupal7HelloWorldProjectAddConfigurationServer") {
				sendKeys(drupal7Const.DRUPAL7_CONF_SERVER_CONTEXT_HELLOWORLD);
				Thread.sleep(2000);
			} else if (methodName == "testDrupal7EshopProjectAddConfigurationServer") {
				sendKeys(drupal7Const.DRUPAL7_CONF_SERVER_CONTEXT_ESHOP);
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

	public void drupal7ProjectAddConfigurationDatabase(String methodName,
			Drupal7ConstantsXml drupal7Const) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@drupal7ProjectAddConfigurationDatabase::****** executing ****");
		try {
			Thread.sleep(3000);
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
			sendKeys(drupal7Const.DRUPAL7_CONF_DATABASE_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC, methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(drupal7Const.DRUPAL7_CONF_DATABASE_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,	methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,drupal7Const.DRUPAL7_CONF_DATABASE_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE, methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,drupal7Const.DRUPAL7_CONF_DATABASE_TYPE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST,methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(drupal7Const.DRUPAL7_CONF_DATABASE_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT,methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(drupal7Const.DRUPAL7_CONF_DATABASE_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_USERNAME, methodName);
			getXpathWebElement(phrsc.CONFIG_USERNAME);
			click();
			clear();
			sendKeys(drupal7Const.DRUPAL7_CONF_DB_USERNAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DB_NAME,	methodName);
			getXpathWebElement(phrsc.CONFIG_DB_NAME);
			click();
			clear();
			if (methodName == "testDrupal7HelloWorldProjectDatabase") {
				sendKeys(drupal7Const.DRUPAL7_CONF_DB_NAME_HELLOWORLD);
				Thread.sleep(2000);
			} else if (methodName == "testDrupal7EshopProjectDatabase") {
				sendKeys(drupal7Const.DRUPAL7_CONF_DB_NAME_ESHOP);
				Thread.sleep(2000);
			}

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
	public void wordPressProjectHelloWorldCreate(String methodName,
			WordPressConstantsXml wordPressConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@wordPressProjectHelloWorldCreate::****** executing ****");
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
			sendKeys(wordPressConst.WORDPRESS_HELLOWORLD_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			sendKeys(wordPressConst.WORDPRESS_HELLOWORLD_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_CHECKALL, methodName);
			getXpathWebElement(phrsc.APP_LAYER_CHECKALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_LAYER_DROPDOWN);
			selectText(element,wordPressConst.WORDPRESS_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(wordPressConst.WORDPRESS_HELLOWORLD_NAME_VALUE, methodName);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}


	public void wordPressProjectHelloWorldEditApplication(String methodName,
			WordPressConstantsXml wordPressConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@wordPressProjectHelloWorldEditApplication::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(wordPressConst.WORDPRESS_HELLOWORLD_EDIT_APP_LINK,methodName);
			getXpathWebElement(wordPressConst.WORDPRESS_HELLOWORLD_EDIT_APP_LINK);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, wordPressConst.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_CHECK_ALL, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_CHECK_ALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_DROPDOWN);
			selectText(element,wordPressConst.WORDPRESS_SERVER_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_VERSION_CHECKBOX, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_VERSION_CHECKBOX);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_DB_CHECK_ALL, methodName);
			getXpathWebElement(phrsc.APP_INFO_DB_CHECK_ALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_DB_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_DB_DROPDOWN);
			selectText(element,wordPressConst.WORDPRESS_DB_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			click();
			Thread.sleep(2000);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void wordPressProjectHelloWorldAddFeature(String methodName,
			WordPressConstantsXml wordPressConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@wordPressProjectHelloWorldAddFeature::****** executing ****");
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

	public void wordPressProjectHelloWorldAddConfigurationServer(
			String methodName, WordPressConstantsXml wordPressConst)
	throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@wordPressProjectHelloWorldAddConfigurationServer::****** executing ****");
		try {
			Thread.sleep(5000);
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
			sendKeys(wordPressConst.WORDPRESS_CONF_SERVER_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC, methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(wordPressConst.WORDPRESS_CONF_SERVER_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,	methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,wordPressConst.WORDPRESS_CONF_SERVER_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE, methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,wordPressConst.WORDPRESS_CONF_SERVER_TYPE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL, methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element,wordPressConst.WORDPRESS_CONF_SERVER_PROTOCOL_HTTP_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST, methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(wordPressConst.WORDPRESS_CONF_SERVER_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT, methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(wordPressConst.WORDPRESS_CONF_SERVER_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_LOCATION,	methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_LOCATION);
			click();
			clear();
			sendKeys(wordPressConst.WORDPRESS_CONF_SERVER_DEPLOY_DIRECTOR);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_OK, methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_OK);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT,	methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);
			click();
			clear();
			sendKeys(wordPressConst.WORDPRESS_CONF_SERVER_CONTEXT_HELLOWORLD);

			Thread.sleep(2000);
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

	public void wordPressProjectHelloWorldAddConfigurationDatabase(
			String methodName, WordPressConstantsXml wordPressConst)
	throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@wordPressProjectHelloWorldAddConfigurationDatabase::****** executing ****");
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
			sendKeys(wordPressConst.WORDPRESS_CONF_DATABASE_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC, methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(wordPressConst.WORDPRESS_CONF_DATABASE_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,	methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,wordPressConst.WORDPRESS_CONF_DATABASE_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE, methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,wordPressConst.WORDPRESS_CONF_DATABASE_TYPE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST, methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(wordPressConst.WORDPRESS_CONF_DATABASE_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT, methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(wordPressConst.WORDPRESS_CONF_DATABASE_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_USERNAME, methodName);
			getXpathWebElement(phrsc.CONFIG_USERNAME);
			click();
			clear();
			sendKeys(wordPressConst.WORDPRESS_CONF_DB_USERNAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DB_NAME,	methodName);
			getXpathWebElement(phrsc.CONFIG_DB_NAME);
			click();
			clear();
			sendKeys(wordPressConst.WORDPRESS_CONF_DB_NAME_HELLOWORLD);

			Thread.sleep(2000);
			getXpathWebElement(phrsc.CONFIG_SAVE_BUTTON);
			waitForElementPresent(phrsc.CONFIG_SAVE_BUTTON, methodName);
			click();
			boolean configDbTestResult = configDbSuccessFailureLoop();
			System.out.println("configDbTestResult::::::::"+configDbTestResult);
			Assert.assertTrue(configDbTestResult);
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void javaWebserviceProjectHelloWorldCreate(String methodName,
			JavaWebServiceConstantsXml JvaWebServiceConst) throws Exception {
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
			sendKeys(JvaWebServiceConst.JAVA_WS_HELLOWORLD_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			sendKeys(JvaWebServiceConst.JAVA_WS_HELLOWORLD_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_CHECKALL, methodName);
			getXpathWebElement(phrsc.APP_LAYER_CHECKALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_LAYER_DROPDOWN);
			selectText(element,JvaWebServiceConst.JAVA_WS_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(JvaWebServiceConst.JAVA_WS_HELLOWORLD_NAME_VALUE, methodName);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void javaWebserviceProjectHelloWorldEditApplication(
			String methodName, JavaWebServiceConstantsXml JvaWebServiceConst)
	throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@javaWebserviceProjectHelloWorldEditApplication::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(JvaWebServiceConst.JAVA_WS_PROJECT_HELLOWORLD_EDIT_APP_LINK,methodName);
			getXpathWebElement(JvaWebServiceConst.JAVA_WS_PROJECT_HELLOWORLD_EDIT_APP_LINK);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, JvaWebServiceConst.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_CHECK_ALL, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_CHECK_ALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_DROPDOWN);
			selectText(element,JvaWebServiceConst.JAVA_WS_SERVER_VALUE);

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
			selectText(element,JvaWebServiceConst.JAVA_WS_DB_VALUE);

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
			JavaWebServiceConstantsXml JvaWebServiceConst) throws Exception {
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
			JavaWebServiceConstantsXml JvaWebServiceConst) throws Exception {
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
			sendKeys(JvaWebServiceConst.JAVA_WS_ESHOP_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			sendKeys(JvaWebServiceConst.JAVA_WS_ESHOP_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_CHECKALL, methodName);
			getXpathWebElement(phrsc.APP_LAYER_CHECKALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_LAYER_DROPDOWN);
			selectText(element,JvaWebServiceConst.JAVA_WS_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(JvaWebServiceConst.JAVA_WS_ESHOP_NAME_VALUE, methodName);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void javaWebserviceProjectEshopEditApplication(String methodName,
			JavaWebServiceConstantsXml JvaWebServiceConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@javaWebserviceProjectEshopEditApplication::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(JvaWebServiceConst.JAVA_WS_PROJECT_ESHOP_EDIT_APP_LINK,methodName);
			getXpathWebElement(JvaWebServiceConst.JAVA_WS_PROJECT_ESHOP_EDIT_APP_LINK);
			click();

			Thread.sleep(10000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, JvaWebServiceConst.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN);
			selectText(element,JvaWebServiceConst.JAVA_WS_PILOT_PROJECT_VALUE);

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
			JavaWebServiceConstantsXml JvaWebServiceConst) throws Exception {
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
			JavaWebServiceConstantsXml JvaWebServiceConst) throws Exception {
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
			sendKeys(JvaWebServiceConst.JAVA_WS_CONF_SERVER_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC, methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(JvaWebServiceConst.JAVA_WS_CONF_SERVER_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,	methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,JvaWebServiceConst.JAVA_WS_CONF_SERVER_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE, methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,JvaWebServiceConst.JAVA_WS_CONF_SERVER_TYPE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL, methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element,JvaWebServiceConst.JAVA_WS_CONF_SERVER_PROTOCOL_HTTP_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST, methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(JvaWebServiceConst.JAVA_WS_CONF_SERVER_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT, methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(JvaWebServiceConst.JAVA_WS_CONF_SERVER_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_LOCATION,	methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_LOCATION);
			click();
			sendKeys(JvaWebServiceConst.JAVA_WS_CONF_SERVER_DEPLOY_DIRECTORY);

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
				sendKeys(JvaWebServiceConst.JAVA_WS_CONF_SERVER_CONTEXT_HELLOWORLD);
				Thread.sleep(2000);

			} else if (methodName == "testJavaWebServiceEshopProjectAddServerConfiguration") {
				sendKeys(JvaWebServiceConst.JAVA_WS_CONF_SERVER_CONTEXT_ESHOP);
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
			JavaWebServiceConstantsXml JvaWebServiceConst) throws Exception {
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
			sendKeys(JvaWebServiceConst.JAVA_WS_CONF_DATABASE_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC, methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(JvaWebServiceConst.JAVA_WS_CONF_DATABASE_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,	methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,JvaWebServiceConst.JAVA_WS_CONF_DATABASE_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE, methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,JvaWebServiceConst.JAVA_WS_CONF_DATABASE_TYPE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST, methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(JvaWebServiceConst.JAVA_WS_CONF_DATABASE_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT, methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(JvaWebServiceConst.JAVA_WS_CONF_DATABASE_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_USERNAME, methodName);
			getXpathWebElement(phrsc.CONFIG_USERNAME);
			click();
			clear();
			sendKeys(JvaWebServiceConst.JAVA_WS_CONF_DB_USERNAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DB_NAME,	methodName);
			getXpathWebElement(phrsc.CONFIG_DB_NAME);
			click();
			sendKeys(JvaWebServiceConst.JAVA_WS_CONF_DB_NAME_ESHOP);

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
	public void AspDotNetProjectHelloWorldCreate(String methodName,
			AspDotNetConstantsXml AspDotNetConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@AspDotNetProjectHelloWorldCreate::****** executing ****");
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
			sendKeys(AspDotNetConst.ASP_DOTNET_HELLOWORLD_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			sendKeys(AspDotNetConst.ASP_DOTNET_HELLOWORLD_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_CHECKALL, methodName);
			getXpathWebElement(phrsc.APP_LAYER_CHECKALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_LAYER_DROPDOWN);
			selectText(element,AspDotNetConst.ASP_DOTNET_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(35000);
			isTextPresent(AspDotNetConst.ASP_DOTNET_HELLOWORLD_NAME_VALUE, methodName);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void AspDotNetProjectHelloWorldEditApplication(String methodName,
			AspDotNetConstantsXml AspDotNetConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@AspDotNetProjectHelloWorldEditApplication::****** executing ****");
		try {

			Thread.sleep(2000);
			waitForElementPresent(AspDotNetConst.ASP_DOTNET_HELLOWORLD_EDIT_APP_LINK,methodName);
			getXpathWebElement(AspDotNetConst.ASP_DOTNET_HELLOWORLD_EDIT_APP_LINK);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			click();

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void AspDotNetProjectHelloWorldAddFeature(String methodName,
			AspDotNetConstantsXml AspDotNetConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@AspDotNetProjectHelloWorldAddFeature::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_FINISH_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_FINISH_BUTTON);
			click();
			boolean updateTestResult = updateSuccessFailureLoop();
			System.out.println("updateTestResult::::"+updateTestResult);
			Assert.assertTrue(updateTestResult);
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void AspDotNetServerConfiguration(String methodName,
			AspDotNetConstantsXml AspDotNetConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@AspDotNetServerConfiguration::****** executing ****");
		try {
			Thread.sleep(5000);
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
			sendKeys(AspDotNetConst.ASP_DOTNET_SERVER_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC, methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(AspDotNetConst.ASP_DOTNET_SERVER_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,	methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,AspDotNetConst.ASP_DOTNET_SERVER_ENV);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE, methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,AspDotNetConst.ASP_DOTNET_SERVER_TYPE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL, methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element,AspDotNetConst.ASP_DOTNET_SERVER_PROTOCOL);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST, methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(AspDotNetConst.ASP_DOTNET_SERVER_HOST);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT, methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(AspDotNetConst.ASP_DOTNET_SERVER_PORT);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT, methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);
			click();
			clear();
			sendKeys(AspDotNetConst.ASP_DOTNET_SERVER_CONTEXT);

			Thread.sleep(3000);
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

	public void SharePointProjectHelloWorldCreate(String methodName,
			SharePointConstantsXml SharePointConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@SharePointProjectHelloWorldCreate::****** executing ****");
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
			sendKeys(SharePointConst.SHARE_POINT_HELLOWORLD_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			sendKeys(SharePointConst.SHARE_POINT_HELLOWORLD_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_CHECKALL, methodName);
			getXpathWebElement(phrsc.APP_LAYER_CHECKALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_LAYER_DROPDOWN);
			selectText(element,SharePointConst.SHARE_POINT_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(SharePointConst.SHARE_POINT_HELLOWORLD_NAME_VALUE, methodName);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
	public void sharePointProjectHelloworldEditApplication(String methodName,
			SharePointConstantsXml SharePointConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@sharePointProjectHelloWorldEditApplication::****** executing ****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(SharePointConst.SHARE_POINT_HELLOWORLD_EDIT_APP_LINK,methodName);
			getXpathWebElement(SharePointConst.SHARE_POINT_HELLOWORLD_EDIT_APP_LINK);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, SharePointConst.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			click();
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void sharePointProjectHelloworldAddFeature(String methodName,
			SharePointConstantsXml SharePointConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@sharePointProjectHelloworldAddFeature::****** executing ****");
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

	public void SharePointProjectResourceCreate(String methodName,
			SharePointConstantsXml SharePointConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@SharePointProjectResourceCreate::****** executing ****");
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
			sendKeys(SharePointConst.SHARE_POINT_RESOURCE_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			sendKeys(SharePointConst.SHARE_POINT_RESOURCE_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_CHECKALL, methodName);
			getXpathWebElement(phrsc.APP_LAYER_CHECKALL);
			click();

			Thread.sleep(3000);
			waitForElementPresent(phrsc.APP_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_LAYER_DROPDOWN);
			selectText(element,SharePointConst.SHARE_POINT_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(SharePointConst.SHARE_POINT_RESOURCE_NAME_VALUE, methodName);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void sharePointProjectResourceEditApplication(String methodName,
			SharePointConstantsXml SharePointConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@sharePointProjectResourceEditApplication::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(SharePointConst.SHARE_POINT_RESOURCE_EDIT_APP_LINK,methodName);
			getXpathWebElement(SharePointConst.SHARE_POINT_RESOURCE_EDIT_APP_LINK);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN);
			selectText(element,SharePointConst.SHARE_POINT_PILOT_PROJECT);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			click();
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void sharePointProjectResourcesAddFeature(String methodName,
			SharePointConstantsXml SharePointConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@sharePointProjectResourcesAddFeature::****** executing ****");
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

	public void sharepointServerConfiguration(String methodName,
			SharePointConstantsXml SharePointConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@sharepointServerConfiguration::****** executing ****");
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
			sendKeys(SharePointConst.SHARE_POINT_CONF_SERVER_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC, methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(SharePointConst.SHARE_POINT_CONF_SERVER_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,	methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,SharePointConst.SHARE_POINT_CONF_SERVER_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE, methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,SharePointConst.SHARE_POINT_CONF_SERVER_TYPE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL, methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element,SharePointConst.SHARE_POINT_CONF_SERVER_PROTOCOL_HTTP_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST, methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(SharePointConst.SHARE_POINT_CONF_SERVER_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT,methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			if (methodName == "testSharepointHelloWorldProjectAddConfigurationServer") {
				sendKeys(SharePointConst.SHARE_POINT_CONF_SERVER_HW_PORT_VALUE);
			} else if (methodName == "testSharepointResourceManagementProjectAddConfigurationServer") {
				sendKeys(SharePointConst.SHARE_POINT_CONF_SERVER_RESOURCE_PORT_VALUE);
			}

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_LOCATION,	methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_LOCATION);
			click();
			clear();
			sendKeys(SharePointConst.SHARE_POINT_CONF_SERVER_DEPLOY_DIRECTORY);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_BROWSE_OK, methodName);
			getXpathWebElement(phrsc.CONFIG_BROWSE_OK);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT,	methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);
			click();
			clear();
			sendKeys(SharePointConst.SHARE_POINT_CONF_SERVER_CONTEXT);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_SAVE_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_SAVE_BUTTON);
			click();
			boolean configServerTestResult = configServerSuccessFailureLoop();
			System.out.println("configServerTestResult::::::::"+configServerTestResult);
			Assert.assertTrue(configServerTestResult);
			Thread.sleep(6000);
		}

		catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void siteCoreProjectHelloWorldCreate(String methodName,
			SiteCoreConstantsXml siteCoreConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@siteCoreProjectHelloWorldCreate::****** executing ****");
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
			sendKeys(siteCoreConst.SITECORE_HELLOWORLD_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			sendKeys(siteCoreConst.SITECORE_HELLOWORLD_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_CHECKALL, methodName);
			getXpathWebElement(phrsc.APP_LAYER_CHECKALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_LAYER_DROPDOWN);
			selectText(element,siteCoreConst.SITECORE_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(20000);
			isTextPresent(siteCoreConst.SITECORE_HELLOWORLD_NAME_VALUE, methodName);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}


	public void siteCoreProjectHelloWorldEditApplication(String methodName,
			SiteCoreConstantsXml siteCoreConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@siteCoreProjectHelloWorldEditApplication::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(siteCoreConst.SITECORE_HELLOWORLD_EDIT_APP_LINK,methodName);
			getXpathWebElement(siteCoreConst.SITECORE_HELLOWORLD_EDIT_APP_LINK);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			click();
			Thread.sleep(2000);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void siteCoreProjectHelloWorldAddFeature(String methodName,
			SiteCoreConstantsXml siteCoreConstants) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@siteCoreProjectHelloWorldAddFeature::****** executing ****");
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


	public void SiteCoreServerConfiguration(String methodName,
			SiteCoreConstantsXml siteCoreConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@SiteCoreServerConfiguration::****** executing ****");
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
			sendKeys(siteCoreConst.SITECORE_SERVER_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC, methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(siteCoreConst.SITECORE_SERVER_DESC);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,	methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,siteCoreConst.SITECORE_SERVER_ENV);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE, methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,siteCoreConst.SITECORE_SERVER_TYPE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL, methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element,siteCoreConst.SITECORE_SERVER_PROTOCOL);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST, methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(siteCoreConst.SITECORE_SERVER_HOST);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT, methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(siteCoreConst.SITECORE_SERVER_PORT);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_INSTALL_PATH_SITECORE, methodName);
			getXpathWebElement(phrsc.CONFIG_INSTALL_PATH_SITECORE);
			click();
			clear();
			sendKeys(siteCoreConst.SITECORE_SERVER_DEPLOY_DIR);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT, methodName);
			getXpathWebElement(phrsc.CONFIG_CONTEXT);
			click();
			clear();
			sendKeys(siteCoreConst.SITECORE_SERVER_CONTEXT);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_SAVE_BUTTON, methodName);
			getXpathWebElement(phrsc.CONFIG_SAVE_BUTTON);
			click();
			boolean configServerTestResult = configServerSuccessFailureLoop();
			System.out.println("configServerTestResult::::::::"+configServerTestResult);
			Assert.assertTrue(configServerTestResult);
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void NodeJsWebserviceProjectHelloWorldCreate(String methodName,
			NodeJsWebServiceConstantsXml nodejs) throws Exception {
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
			sendKeys(nodejs.NODEJS_HELLOWORLD_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			sendKeys(nodejs.NODEJS_HELLOWORLD_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_CHECKALL, methodName);
			getXpathWebElement(phrsc.APP_LAYER_CHECKALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_LAYER_DROPDOWN);
			selectText(element,nodejs.NODEJS_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(30000);
			isTextPresent(nodejs.NODEJS_HELLOWORLD_NAME_VALUE, methodName);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}


	public void NodeJsWebserviceProjectHelloWorldEditApplication(
			String methodName, NodeJsWebServiceConstantsXml nodejs)
	throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@NodeJsWebserviceProjectHelloWorldEditApplication::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(nodejs.NODEJS_HELLOWORLD_EDIT_APP_LINK,methodName);
			getXpathWebElement(nodejs.NODEJS_HELLOWORLD_EDIT_APP_LINK);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, nodejs.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_CHECK_ALL, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_CHECK_ALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_SERVER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_SERVER_DROPDOWN);
			selectText(element,nodejs.NODEJS_SERVER_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_DB_CHECK_ALL, methodName);
			getXpathWebElement(phrsc.APP_INFO_DB_CHECK_ALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_DB_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_DB_DROPDOWN);
			selectText(element,nodejs.NODEJS_DB_VALUE);

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
			NodeJsWebServiceConstantsXml nodejs) throws Exception {
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
			NodeJsWebServiceConstantsXml nodejs) throws Exception {
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
			sendKeys(nodejs.NODEJS_ESHOP_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			sendKeys(nodejs.NODEJS_ESHOP_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_CHECKALL, methodName);
			getXpathWebElement(phrsc.APP_LAYER_CHECKALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_LAYER_DROPDOWN);
			sendKeys( nodejs.NODEJS_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(20000);
			isTextPresent(nodejs.NODEJS_ESHOP_NAME_VALUE, methodName);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void NodeJsWebserviceProjectEshopEditApplication(String methodName,
			NodeJsWebServiceConstantsXml nodejs) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@NodeJsWebserviceProjectEshopEditApplication::****** executing ****");
		try {

			Thread.sleep(2000);
			waitForElementPresent(nodejs.NODEJS_ESHOP_EDIT_APP_LINK,methodName);
			getXpathWebElement(nodejs.NODEJS_ESHOP_EDIT_APP_LINK);
			click();

			Thread.sleep(10000);
			waitForElementPresent(phrsc.FUNCTIONAL_DROPDOWN, methodName);
			getXpathWebElement(phrsc.FUNCTIONAL_DROPDOWN);
			selectText(element, nodejs.FUNCTIONAL_FRAMEWORK);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_INFO_PILOT_PROJECT_DROPDOWN);
			selectText(element,nodejs.NODEJS_PILOT_PROJECT_VALUE);

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
			NodeJsWebServiceConstantsXml nodejs) throws Exception {
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
			NodeJsWebServiceConstantsXml nodejs) throws Exception {
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
			sendKeys(nodejs.NODEJS_SERVICE_CONF_SERVER_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC, methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(nodejs.NODEJS_SERVICE_CONF_SERVER_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE, methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,nodejs.NODEJS_SERVICE_CONF_SERVER_TYPE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PROTOCOL, methodName);
			getXpathWebElement(phrsc.CONFIG_PROTOCOL);
			selectText(element,nodejs.NODEJS_SERVICE_CONF_SERVER_PROTOCOL_HTTP_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST, methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(nodejs.NODEJS_SERVICE_CONF_SERVER_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT, methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(nodejs.NODEJS_SERVICE_CONF_SERVER_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_CONTEXT,	methodName);;
			getXpathWebElement(phrsc.CONFIG_CONTEXT);		
			click();
			clear();
			if (methodName == "testNodeJsHelloWorldProjectAddConfigurationServer") {
				sendKeys(nodejs.NODEJS_SERVICE_CONF_SERVER_CONTEXT_HELLOWORLD);
				Thread.sleep(2000);
			} else if (methodName=="testNodeJsEshopProjectAddConfigurationServer"){
				sendKeys(nodejs.NODEJS_SERVICE_CONF_SERVER_CONTEXT_ESHOP);
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
			NodeJsWebServiceConstantsXml nodejs) throws Exception {
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
			sendKeys(nodejs.NODEJS_SERVICE_CONF_DATABASE_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DESC, methodName);
			getXpathWebElement(phrsc.CONFIG_DESC);
			click();
			clear();
			sendKeys(nodejs.NODEJS_SERVICE_CONF_DATABASE_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_ENV,	methodName);
			getXpathWebElement(phrsc.CONFIG_ENV);
			selectText(element,	nodejs.NODEJS_SERVICE_CONF_DATABASE_ENV_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_TEMPLATE_TYPE, methodName);
			getXpathWebElement(phrsc.CONFIG_TEMPLATE_TYPE);
			selectText(element,nodejs.NODEJS_SERVICE_CONF_DATABASE_TYPE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_HOST, methodName);
			getXpathWebElement(phrsc.CONFIG_HOST);
			click();
			clear();
			sendKeys(nodejs.NODEJS_SERVICE_CONF_DATABASE_HOST_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_PORT, methodName);
			getXpathWebElement(phrsc.CONFIG_PORT);
			click();
			clear();
			sendKeys(nodejs.NODEJS_SERVICE_CONF_DATABASE_PORT_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_USERNAME, methodName);
			getXpathWebElement(phrsc.CONFIG_USERNAME);
			click();
			clear();
			sendKeys(nodejs.NODEJS_SERVICE_CONF_DB_USERNAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIG_DB_NAME,	methodName);
			getXpathWebElement(phrsc.CONFIG_DB_NAME);
			click();
			clear();
			sendKeys(nodejs.NODEJS_SERVICE_CONF_DB_NAME_ESHOP);

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

	public void javaStandAloneProjectHelloWorldCreate(String methodName,
			JavaStandaloneConstantsXml JvaStdAloneConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@javaStandAloneProjectHelloWorldCreate::****** executing ****");
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
			sendKeys(JvaStdAloneConst.JAVA_SA_HELLOWORLD_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_PROJECT_DESC, methodName);
			getXpathWebElement(phrsc.ADD_PROJECT_DESC);
			click();
			sendKeys(JvaStdAloneConst.JAVA_SA_HELLOWORLD_DESC_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_CHECKALL, methodName);
			getXpathWebElement(phrsc.APP_LAYER_CHECKALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.APP_LAYER_DROPDOWN, methodName);
			getXpathWebElement(phrsc.APP_LAYER_DROPDOWN);
			selectText(element, JvaStdAloneConst.JAVA_SA_HELLOWORLD_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(20000);
			isTextPresent(JvaStdAloneConst.JAVA_SA_HELLOWORLD_NAME_VALUE, methodName);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void javaStandaloneProjectHelloWorldEditApplication(
			String methodName, JavaStandaloneConstantsXml JvaStdAloneConst)
	throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@javaStandaloneProjectHelloWorldEditApplication::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(JvaStdAloneConst.JAVA_SA_HELLOWORLD_EDIT_APP_LINK,methodName);
			getXpathWebElement(JvaStdAloneConst.JAVA_SA_HELLOWORLD_EDIT_APP_LINK);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.PROJECT_NEXT_BUTTON, methodName);
			getXpathWebElement(phrsc.PROJECT_NEXT_BUTTON);
			click();
			Thread.sleep(2000);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void javaStandaloneProjectHelloWorldAddFeature(String methodName,
			JavaStandaloneConstantsXml JvaStdAloneConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@javaStandaloneProjectHelloWorldAddFeature::****** executing ****");
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

	public void UnittestVerification(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("**UnitTestVerification****");
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

			if(methodName=="testJavaWebServiceEshopProjectUnitTest" || methodName=="testJavaWebServiceHelloWorldProjectUnittest")
			{
				Thread.sleep(2000);
				waitForElementPresent(phrsc.TEST_AGAINST, methodName);
				getXpathWebElement(phrsc.TEST_AGAINST);
				selectText(element ,phrsc.JAVA_TEST_AGAINST_VALUE);

				Thread.sleep(2000);
				waitForElementPresent(phrsc.RUN_UNIT_TEST_BUTTON, methodName);
				getXpathWebElement(phrsc.RUN_UNIT_TEST_BUTTON);
				click();
			}
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
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PERFORMACE_TAB, methodName);
			getXpathWebElement(phrsc.PERFORMACE_TAB);
			click();

			Thread.sleep(2000);
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
			boolean performanceServerTestResult = successFailureLoop();
			System.out.println("performanceServerTestResult:::::::::"+performanceServerTestResult);
			Thread.sleep(2000);
			waitForElementPresent(phrsc.TEST_CLOSE_BUTTON, methodName);
			getXpathWebElement(phrsc.TEST_CLOSE_BUTTON).click();
			Assert.assertTrue(performanceServerTestResult);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void PerformanceTestForAganistDatabase(String methodName)
	throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("**PerformanceTestAganistDatabaseVerification****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PERFORMANCE_TEST_BUTTON, methodName);
			getXpathWebElement(phrsc.PERFORMANCE_TEST_BUTTON);
			click();

			Thread.sleep(5000);
			waitForElementPresent(phrsc.TEST_AGAINST, methodName);
			getXpathWebElement(phrsc.TEST_AGAINST);
			sendKeys(phrsc.TEST_AGAINST_DB);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.TEST_NAME, methodName);
			getXpathWebElement(phrsc.TEST_NAME);
			sendKeys(phrsc.TEST_RESULT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.DB_NAME_XPATH, methodName);
			getXpathWebElement(phrsc.DB_NAME_XPATH);
			click();
			clear();
			sendKeys(phrsc.DB_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.DB_QUERY_XPATH, methodName);
			getXpathWebElement(phrsc.DB_QUERY_XPATH);
			click();
			clear();
			sendKeys(phrsc.DB_QUERY_VALUE);			

			Thread.sleep(2000);
			waitForElementPresent(phrsc.PERFORMANCE_TEST_RUN, methodName);
			getXpathWebElement(phrsc.PERFORMANCE_TEST_RUN);
			click();
			Thread.sleep(2000);

			boolean performanceDbTestResult=successFailureLoop();
			System.out.println("performanceDbTestResult:::::::::"+performanceDbTestResult);
			Thread.sleep(2000);
			waitForElementPresent(phrsc.TEST_CLOSE_BUTTON, methodName);
			getXpathWebElement(phrsc.TEST_CLOSE_BUTTON).click();
			Assert.assertTrue(performanceDbTestResult);

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
			Thread.sleep(2000);
			waitForElementPresent(phrsc.LOAD_TAB, methodName);
			getXpathWebElement(phrsc.LOAD_TAB);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.LOAD_TEST_BUTTON, methodName);
			getXpathWebElement(phrsc.LOAD_TEST_BUTTON);
			click();

			Thread.sleep(10000);
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
			boolean loadTestResult = successFailureLoop();
			System.out.println("loadTestResult::::::::"+loadTestResult);
			Thread.sleep(2000);
			waitForElementPresent(phrsc.TEST_CLOSE_BUTTON, methodName);
			getXpathWebElement(phrsc.TEST_CLOSE_BUTTON).click();	
			Assert.assertTrue(loadTestResult);
		} catch (InterruptedException e) {

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
	public void javaStandaloneProjectHelloWorldBuildTab(String methodName,
			JavaStandaloneConstantsXml JvaStdAloneConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
		}
		log.info("@javaStandaloneProjectHelloWorldBuildTab::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.MENU_TAB, methodName);
			getXpathWebElement(phrsc.MENU_TAB);
			click();
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

}
