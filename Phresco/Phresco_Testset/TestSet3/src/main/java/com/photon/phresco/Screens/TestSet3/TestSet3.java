package com.photon.phresco.Screens.TestSet3;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import junit.framework.Test;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;

import com.google.common.base.Function;
import com.photon.phresco.selenium.util.Constants;
import com.photon.phresco.selenium.util.GetCurrentDir;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.EnvironmentConstantsXml;
import com.photon.phresco.uiconstants.PhpConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.TestSet3Xml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class TestSet3 {

	private static WebDriver driver;
	private ChromeDriverService chromeService;
	private Log log = LogFactory.getLog("BaseScreen");
	private static WebElement element;	

	private PhrescoUiConstantsXml phrsc;
	
	private static phresco_env_config phrscEnv;
	private UserInfoConstants userInfo;

	public TestSet3() {

	}

	public TestSet3(String selectedBrowser, String applicationURL, String applicatinContext,phresco_env_config phrscEnv,PhrescoUiConstantsXml phrsc,UserInfoConstants userInfo,PhrescoFrameworkData phrscData)
			throws ScreenException {
		
		TestSet3.phrscEnv=phrscEnv;
		this.userInfo=userInfo;
		this.phrsc=phrsc;

		instantiateBrowser(selectedBrowser, applicationURL, applicatinContext);

	}

	public void instantiateBrowser(String selectedBrowser,
			String applicationURL, String applicationContext)
					throws ScreenException {

		if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_CHROME)) {
			try {
				chromeService = new ChromeDriverService.Builder()
				.usingDriverExecutable(
						new File(getChromeLocation()))
						.usingAnyFreePort().build();	

				log.info("-------------***LAUNCHING GOOGLECHROME***--------------");						
				driver=new ChromeDriver(chromeService);
				 windowResize();
				driver.navigate().to(applicationURL+applicationContext);


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
			windowResize();
			driver.navigate().to(applicationURL + applicationContext);

		}

		else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_OPERA)) {
			log.info("-------------***LAUNCHING OPERA***--------------");
			System.out.println("******entering window maximize********");
			Robot robot; try { robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_SPACE);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_SPACE);
			robot.keyPress(KeyEvent.VK_X); robot.keyRelease(KeyEvent.VK_X); }
			catch (AWTException e) {

				e.printStackTrace(); }




		} else {
			throw new ScreenException(
					"------Only FireFox,InternetExplore and Chrome works-----------");
		}

	}

	public static void windowResize()
	{
		
		String resolution = phrscEnv.RESOLUTION;	
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

			if (chromeService!=null) {				


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

			WebDriverWait wait = new WebDriverWait(driver, 20);			
			wait.until(presenceOfElementLocated(by));

		}

		catch (Exception e) {
			log.info("Entering:------presenceOfElementLocated()-----End"+"--("+ locator +")--");
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,
					new File(GetCurrentDir.getCurrentDirectory() + "\\"
							+ methodName + ".png"));
			Assert.assertNull(scrFile);

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
		log.info("@loginPage::****** executing ****");
		try {
			Thread.sleep(5000);	
			waitForElementPresent(phrsc.LOGIN_USERNAME,methodName);	
			getXpathWebElement(phrsc.LOGIN_USERNAME);
			click();
			sendKeys(userInfo.USERNAME);
			ToolTipVerification(phrsc.LOGIN_USERNAME);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.LOGIN_PASSWORD,methodName);
			getXpathWebElement(phrsc.LOGIN_PASSWORD);
			click();
			sendKeys(userInfo.PASSWORD);
			ToolTipVerification(phrsc.LOGIN_PASSWORD);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.LOGIN_BUTTON,methodName);
			getXpathWebElement(phrsc.LOGIN_BUTTON);
			click();
			Thread.sleep(2000);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}
	
	
	

	public  void phpProjectHelloWorldCreate(String methodName,PhpConstantsXml phpConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@phpProjectHelloWorldCreate::****** executing ****");
		try {
			Thread.sleep(3000);
			waitForElementPresent(phrsc.PROJECTS_TAB,methodName);
			getXpathWebElement(phrsc.PROJECTS_TAB);
			click();
			
		/*	Thread.sleep(2000);
			waitForElementPresent(phrsc.CUSTOMER_SELECT, methodName);
			getXpathWebElement(phrsc.CUSTOMER_SELECT);
			sendKeys("photon");*/
			
			Thread.sleep(500);
			waitForElementPresent(phrsc.ADD_APPLICATION_BUTTON,methodName);
			getXpathWebElement(phrsc.ADD_APPLICATION_BUTTON);
			click();
		
			Thread.sleep(500);
			waitForElementPresent(phrsc.ADDPROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_NAME);
			click();
			sendKeys(phpConst.PHP_HELLOWORLD_NAME_VALUE);
			ToolTipVerification(phrsc.ADDPROJECT_NAME);
			
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CODE, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CODE);
			click();
			sendKeys(phpConst.PHP_HELLOWORLD_CODE_VALUE);
			ToolTipVerification(phrsc.ADDPROJECT_CODE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_DESCRIPTION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_DESCRIPTION);
			click();
			sendKeys(phpConst.PHP_HELLOWORLD_DESCRIPTION_VALUE);
			ToolTipVerification(phrsc.ADDPROJECT_DESCRIPTION);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_VERSION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_VERSION);
			click();
			sendKeys(phpConst.PHP_HELLOWORLD_VERSION_VALUE);
			ToolTipVerification(phrsc.ADDPROJECT_VERSION);


			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY);
			selectText(element,phpConst.PHP_TECHNOLGY_VALUE);


			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(12000);
			isTextPresent(phrsc.PROJECT_CREATED_MESSAGE);
			Thread.sleep(10000);
			
			
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void phpProjectHelloWorldEditApplication(String methodName,PhpConstantsXml phpConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@phpProjectHelloWorldEditApplication::****** executing ****");
		try {
			
			Thread.sleep(2000);	
			waitForElementPresent(phpConst.PHP_PROJECT_HELLOWORLD_CHECK_BOX,methodName);
			getXpathWebElement(phpConst.PHP_PROJECT_HELLOWORLD_CHECK_BOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phpConst.PHP_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK,methodName);
          	getXpathWebElement(phpConst.PHP_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK);
			click();
						
			Thread.sleep(7000);
			getXpathWebElement(phrsc.APPINFO_SERVER_CHECKBOX);
			waitForElementPresent(phrsc.APPINFO_SERVER_CHECKBOX, methodName);
			click();
			
			Thread.sleep(2000);
			getXpathWebElement(phrsc.APPINFO_SERVER_SELECT);
			waitForElementPresent(phrsc.APPINFO_SERVER_SELECT, methodName);
			selectText(element, phpConst.PHP_SERVER_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPINFO_SERVERVERSION, methodName);
			getXpathWebElement(phrsc.APPINFO_SERVERVERSION);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPINFO_DATABASE_CHECKBOX, methodName);
			getXpathWebElement(phrsc.APPINFO_DATABASE_CHECKBOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPINFO_DATABASE_SELECT, methodName);
			getXpathWebElement(phrsc.APPINFO_DATABASE_SELECT);
			selectText(element, phpConst.PHP_DATABASE_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPINFO_DATABASEVERSION, methodName);
			getXpathWebElement(phrsc.APPINFO_DATABASEVERSION);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPINFO_NEXT, methodName);
			getXpathWebElement(phrsc.APPINFO_NEXT);
			click();
			Thread.sleep(2000);

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void phpProjectHelloWorldAddFeature(String methodName,PhpConstantsXml phpConstants) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@phpProjectHelloWorldAddFeature::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.FEATURE_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.FEATURE_FINISH_BUTTON);
			click();
			Thread.sleep(50000);


		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

public  void phpProjectAddConfigurationServer(String methodName,PhpConstantsXml phpConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@phpProjectAddConfigurationServer::****** executing ****");
		try {
			
			if ( methodName == "testphpProjectHelloWorldAddConfigurationServer"){
				Thread.sleep(2000);		
				waitForElementPresent(phpConst.PHP_PROJECT_HELLOWORLD_CHECK_BOX,methodName);
				getXpathWebElement(phpConst.PHP_PROJECT_HELLOWORLD_CHECK_BOX);
				click();
				
				Thread.sleep(2000);
				waitForElementPresent(phpConst.PHP_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK,methodName);
				getXpathWebElement(phpConst.PHP_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK);
				click();
				Thread.sleep(2000);
			}
			else{
				Thread.sleep(2000);	
				waitForElementPresent(phpConst.PHP_PROJECT_BLOG_CHECK_BOX,methodName);	
				getXpathWebElement(phpConst.PHP_PROJECT_BLOG_CHECK_BOX);
				click();
				Thread.sleep(2000);
				waitForElementPresent(phpConst.PHP_PROJECT_BLOG_EDIT_APPLICATION_LINK,methodName);
				getXpathWebElement(phpConst.PHP_PROJECT_BLOG_EDIT_APPLICATION_LINK);
				click();
				
			}
			Thread.sleep(3000);
			waitForElementPresent(phrsc.CONFIGURATIONS_TAB,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_TAB);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATION_PRODUCTION_CLICK,methodName);
			getXpathWebElement(phrsc.CONFIGURATION_PRODUCTION_CLICK);
			click();
			Thread.sleep(3000);
			isTextPresent(phrsc.CONFIGUARTION_DEFAULT_MESSAGE);
			Thread.sleep(4000);
			waitForElementPresent(phrsc.CONFIGURATIONS_ADD,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_ADD);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_NAME,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_NAME);
			click();
			clear();
			ToolTipVerification(phrsc.CONFIGURATIONS_NAME); 
			sendKeys(phpConst.PHP_CONFIGURATION_SERVER_NAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DESCRIPTION,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DESCRIPTION);
			click();
			clear();
			ToolTipVerification(phrsc.CONFIGURATIONS_DESCRIPTION); 
			sendKeys(phpConst.PHP_CONFIGURATION_SERVER_DESCRIPTION_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT);
			click();
			selectText(element, phpConst.PHP_CONFIGURATION_SERVER_ENVIRONMENT_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_TYPE);
			selectText(element,phpConst.PHP_CONFIGURATION_SERVER_TYPE_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_PROTOCOL);
			selectText(element, phpConst.PHP_CONFIGURATION_SERVER_PROTOCOL_HTTP_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_HOST,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_HOST);
			click();
			clear();
			sendKeys(phpConst.PHP_CONFIGURATION_SERVER_HOST_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_PORT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_PORT);
			click();
			clear();
			sendKeys(phpConst.PHP_CONFIGURATION_SERVER_PORT_VALUE);
			
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_DEPLOYDIRECTORY,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_DEPLOYDIRECTORY);
			click();
			clear();
			sendKeys(phpConst.PHP_CONFIGURATION_SERVER_DEPLOY_DIRECTORY_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_CONTEXT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_CONTEXT);
			click();
			clear();
			if(methodName=="testphpProjectBlogAddConfigurationServer")
			{
				sendKeys(phpConst.PHP_CONFIGURATION_SERVER_CONTEXT_VALUE_BLOG);
				Thread.sleep(2000);
			}
			else{
				sendKeys(phpConst.PHP_CONFIGURATION_SERVER_CONTEXT_VALUE_HELLOWORLD);
				Thread.sleep(2000);
			}

			waitForElementPresent(phrsc.CONFIGURATIONS_SAVE,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SAVE);
			click();
			Thread.sleep(3000);
			isTextPresent(phrsc.SERVER_CREATED_MESSAGE);
            Thread.sleep(2000);   
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void phpProjectAddConfigurationDatabase(String methodName,PhpConstantsXml phpConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@phpProjectAddConfigurationDatabase::****** executing ****");
		try {
			Thread.sleep(3000);
			waitForElementPresent(phrsc.CONFIGURATIONS_TAB,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_TAB);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_ADD,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_ADD);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_NAME,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_NAME);
			click();
			clear();
			sendKeys(phpConst.PHP_CONFIGURATION_DATABASE_NAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DESCRIPTION,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DESCRIPTION);
			click();
			clear();
			sendKeys(phpConst.PHP_CONFIGURATION_DATABASE_DESCRIPTION_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT);
			click();
			selectText(element, phpConst.PHP_CONFIGURATION_DATABASE_ENVIRONMENT_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_TYPE);
			selectText(element,phpConst.PHP_CONFIGURATION_DATABASE_TYPE_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_HOST,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_HOST);
			click();
			clear();
			sendKeys(phpConst.PHP_CONFIGURATION_DATABASE_HOST_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_PORT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_PORT);
			click();
			clear();
			sendKeys(phpConst.PHP_CONFIGURATION_DATABASE_PORT_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_USERNAME,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_USERNAME);
			click();
			clear();
			sendKeys(phpConst.PHP_CONFIGURATION_DATABASE_DB_USERNAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_DBNAME,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_DBNAME);
			click();
			clear();
			if(methodName=="testphpBlogDatabase")
			{
			
				sendKeys(phpConst.PHP_CONFIGURATION_DATABASE_DB_NAME_VALUE_BLOG);
				Thread.sleep(2000);
			}
			else{
				sendKeys(phpConst.PHP_CONFIGURATION_DATABASE_DB_NAME_VALUE_HELLOWORLD);
				Thread.sleep(2000);
				
			}
			waitForElementPresent(phrsc.CONFIGURATIONSDB_SAVE,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONSDB_SAVE);
			click();
			Thread.sleep(3000);
			isTextPresent(phrsc.DATABASE_CREATED_MESSAGE);
			Thread.sleep(8000);
			log.info("success");

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

public  void appLayerBuildTab(String methodName) throws Exception {
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
			waitForElementPresent(phrsc.GENERATEBUILD, methodName);
			getXpathWebElement(phrsc.GENERATEBUILD);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.BUILD_NAME, methodName);
			getXpathWebElement(phrsc.BUILD_NAME);
			sendKeys(phrsc.BUILD_VALUE_NAME);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.BUILD_NUMBER, methodName);
			getXpathWebElement(phrsc.BUILD_NUMBER);
			sendKeys(phrsc.BUILD_PROJECT_NUMBER);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.SELECT_ENVIRONMENT, methodName);
			getXpathWebElement(phrsc.SELECT_ENVIRONMENT);
			click();
			
			/*Thread.sleep(2000);
			waitForElementPresent(phrsc.SELECT_SECOND_ENVIRONMENT, methodName);
			getXpathWebElement(phrsc.SELECT_SECOND_ENVIRONMENT);
			click();
			*/
			
			/*Thread.sleep(2000);
			waitForElementPresent(phrsc.BUILD_SKIP_UNIT_TEST, methodName);
			getXpathWebElement(phrsc.BUILD_SKIP_UNIT_TEST);
			click();*/
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.BUILD_BUTTON, methodName);
			getXpathWebElement(phrsc.BUILD_BUTTON);
			click();
			successFailureLoop();
			
			Thread.sleep(8000);
		// Build Delete Message Catpure	
			waitForElementPresent(phrsc.BUILD_CHECK_BOX_CLICK, methodName);
			getXpathWebElement(phrsc.BUILD_CHECK_BOX_CLICK);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.BUILD_DELETE_BUTTON_CLCIK, methodName);
			getXpathWebElement(phrsc.BUILD_DELETE_BUTTON_CLCIK);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.BUILD_DELETE_POPUP_CLICK, methodName);
			getXpathWebElement(phrsc.BUILD_DELETE_POPUP_CLICK);
			click();
			Thread.sleep(3000);
			isTextPresent(phrsc.BUILD_DELETE_WARNING_MESSAGE);
			 
			Thread.sleep(2000);
			 
			
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
public  void QualityTabToolTips(String methodName) throws Exception {
	if (StringUtils.isEmpty(methodName)) {
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
	}
	log.info("@QualityTabToolTips::****** executing ****");
	try {
		Thread.sleep(2000);
		waitForElementPresent(phrsc.QUALITYBTN, methodName);
		getXpathWebElement(phrsc.QUALITYBTN);
		click();
		
		Thread.sleep(2000);
		waitForElementPresent(phrsc.UNITTESTBTN, methodName);
		getXpathWebElement(phrsc.UNITTESTBTN);
		click();
		
		Thread.sleep(2000);
		waitForElementPresent(phrsc.OPENFOLDER_TOOLTIP, methodName);
		getXpathWebElement(phrsc.OPENFOLDER_TOOLTIP);
		ToolTipVerification(phrsc.OPENFOLDER_TOOLTIP);
		
		Thread.sleep(2000);
		waitForElementPresent(phrsc.COPY_PATH_TOOLTIP, methodName);
		getXpathWebElement(phrsc.COPY_PATH_TOOLTIP);
		ToolTipVerification(phrsc.COPY_PATH_TOOLTIP);
		
		Thread.sleep(2000);
		waitForElementPresent(phrsc.FUNCTIONAL_TEST_CLICK, methodName);
		getXpathWebElement(phrsc.FUNCTIONAL_TEST_CLICK);
		click();
		
		Thread.sleep(2000);
		waitForElementPresent(phrsc.OPENFOLDER_TOOLTIP, methodName);
		getXpathWebElement(phrsc.OPENFOLDER_TOOLTIP);
		ToolTipVerification(phrsc.OPENFOLDER_TOOLTIP);
		
		Thread.sleep(2000);
		waitForElementPresent(phrsc.COPY_PATH_TOOLTIP, methodName);
		getXpathWebElement(phrsc.COPY_PATH_TOOLTIP);
		ToolTipVerification(phrsc.COPY_PATH_TOOLTIP);
		
		Thread.sleep(2000);
		waitForElementPresent(phrsc.PERFORMANCETESTBTN, methodName);
		getXpathWebElement(phrsc.PERFORMANCETESTBTN);
		click();
		
		Thread.sleep(2000);
		waitForElementPresent(phrsc.OPENFOLDER_TOOLTIP, methodName);
		getXpathWebElement(phrsc.OPENFOLDER_TOOLTIP);
		ToolTipVerification(phrsc.OPENFOLDER_TOOLTIP);
		
		Thread.sleep(2000);
		waitForElementPresent(phrsc.COPY_PATH_TOOLTIP, methodName);
		getXpathWebElement(phrsc.COPY_PATH_TOOLTIP);
		ToolTipVerification(phrsc.COPY_PATH_TOOLTIP);
		
		Thread.sleep(2000);
		waitForElementPresent(phrsc.LOADTESTBTN, methodName);
		getXpathWebElement(phrsc.LOADTESTBTN);
		click();
		
		Thread.sleep(2000);
		waitForElementPresent(phrsc.OPENFOLDER_TOOLTIP, methodName);
		getXpathWebElement(phrsc.OPENFOLDER_TOOLTIP);
		ToolTipVerification(phrsc.OPENFOLDER_TOOLTIP);
		
		Thread.sleep(2000);
		waitForElementPresent(phrsc.COPY_PATH_TOOLTIP, methodName);
		getXpathWebElement(phrsc.COPY_PATH_TOOLTIP);
		ToolTipVerification(phrsc.COPY_PATH_TOOLTIP);
		Thread.sleep(5000);
	}
	catch (InterruptedException e) {

		e.printStackTrace();
	}	
}

	public  void ThemeChange_About_phresco(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("****** @ThemeChange_About_phresco ****");
		try {
		
			Thread.sleep(5000);
			MouseOverEvents(phrsc.USER_IMAGE_ARROW);
			
			/*
			waitForElementPresent(phrsc.ABOUT_PHRESCO,methodName);	
			getXpathWebElement(phrsc.ABOUT_PHRESCO);
			click();
			Thread.sleep(5000);
			
			ButtonEnabled(phrsc.UPDATEAPPLICATION);
			waitForElementPresent(phrsc.UPDATEAPPLICATION,methodName);
			getXpathWebElement(phrsc.UPDATEAPPLICATION);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CANCELBUTTON,methodName);
			getXpathWebElement(phrsc.CANCELBUTTON);
			click();
			*/
			Thread.sleep(2000);
			MouseOverEvents(phrsc.USER_IMAGE_ARROW);
		
			waitForElementPresent(phrsc.RED_THEME_CHANGE,methodName);
			getXpathWebElement(phrsc.RED_THEME_CHANGE);
			click();
			
			Thread.sleep(5000);	
		
		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}
	
	
	public  void ThemeChange_Blue(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("****** @ThemeChange_Blue****");
		try {
		
			Thread.sleep(2000);
			MouseOverEvents(phrsc.BLUE_IMAGE_ARROW);
			waitForElementPresent(phrsc.BLUE_THEME_CHANGE,methodName);
			getXpathWebElement(phrsc.BLUE_THEME_CHANGE);
			click();
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}
	public  void ThemeChange_Green(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("****** @ThemeChange_Green****");
		try {

			Thread.sleep(2000);
			MouseOverEvents(phrsc.GREEN_IMAGE_ARROW);
			waitForElementPresent(phrsc.GREEN_THEME_CHANGE,methodName);
			getXpathWebElement(phrsc.GREEN_THEME_CHANGE);
			click();
			Thread.sleep(2000);
			
		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}
	
	public  void Help_Tab_Verification(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("****** @Help_Tab_Verification ****");
		try {
		
			Thread.sleep(2000);
			
			waitForElementPresent(phrsc.HELP_TAB_VEIFICATION,methodName);
			getXpathWebElement(phrsc.HELP_TAB_VEIFICATION);
			click();
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}
	
	public  void Report_Tab_Verification(String methodName ,TestSet3Xml Test) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("****** @Report_Tab_Verification ****");
		try {
		
			Thread.sleep(2000);
			
			waitForElementPresent(phrsc.REPORT_TAB_CLICK,methodName);
			getXpathWebElement(phrsc.REPORT_TAB_CLICK);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURE_TAB_CLICK,methodName);
			getXpathWebElement(phrsc.CONFIGURE_TAB_CLICK);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURE_REPORTS_TAB_CLICK,methodName);
			getXpathWebElement(phrsc.CONFIGURE_REPORTS_TAB_CLICK);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPORTS_TAB_OK,methodName);
			getXpathWebElement(phrsc.REPORTS_TAB_OK);
			click();
			Thread.sleep(2000);
			isTextPresent(Test.REPORT_ALERT_MESSAGE);
			
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}
	
	public  void RootExpandForDownloadPage(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("****** @RootExpandForDownloadPage****");
		try {
		
			Thread.sleep(2000);
			
			waitForElementPresent(phrsc.DOWNLOAD_TAB,methodName);
			getXpathWebElement(phrsc.DOWNLOAD_TAB);
			click();
			 
			Thread.sleep(5000);
			waitForElementPresent(phrsc.DOWNLOAD_SERVER_ROOT_CLICK,methodName);
			getXpathWebElement(phrsc.DOWNLOAD_SERVER_ROOT_CLICK);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.DOWNLOAD_DATABASE_ROOT_CLICK,methodName);
			getXpathWebElement(phrsc.DOWNLOAD_DATABASE_ROOT_CLICK);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.DOWNLOAD_OTHERS_ROOT_CLICK,methodName);
			getXpathWebElement(phrsc.DOWNLOAD_OTHERS_ROOT_CLICK);
			click();
			
			Thread.sleep(3000);
			 
		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}
	
	
	
	public  void Environmentcreation(String methodName,EnvironmentConstantsXml envCons) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@Creating a New Environment:****** Environmentcreation ****");
		try {
			Thread.sleep(2000);	
			waitForElementPresent(phrsc.SETTIND_TAB,methodName);	
			getXpathWebElement(phrsc.SETTIND_TAB);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ENVIRONMENT,methodName);
			getXpathWebElement(phrsc.ENVIRONMENT);
			click();
			
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ENV_NAME,methodName);
			getXpathWebElement(phrsc.ENV_NAME);
			click();
			sendKeys(envCons.ENV_NAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.DECSCRIPTION,methodName);
            getXpathWebElement(phrsc.DECSCRIPTION).clear();
            click();
            sendKeys(envCons.DECSCRIPTION_VALUE);
            
            Thread.sleep(2000);
            waitForElementPresent(phrsc.ADD,methodName);
            getXpathWebElement(phrsc.ADD);
            click();
            
            Thread.sleep(2000);
            waitForElementPresent(phrsc.SET_AS_DEFAULT,methodName);
            getXpathWebElement(phrsc.SET_AS_DEFAULT);
            click();
            isTextPresent(phrsc.ALRETMESSAGE_ENV_POPUP);
            
            Thread.sleep(2000);
            waitForElementPresent(phrsc.ENV_OK,methodName);
            getXpathWebElement(phrsc.ENV_OK);
            click();
            isTextPresent(phrsc.ENV_CREATED_SUCCESS);
            Thread.sleep(5000);
            
		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}
	
	public  void EnvironmentcreationWarningMessage(String methodName,EnvironmentConstantsXml envCons) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@Checking Warning message in Environment pop up:****** Environment pop up validation****");
		try {
			Thread.sleep(2000);	
			waitForElementPresent(phrsc.SETTIND_TAB,methodName);	
			getXpathWebElement(phrsc.SETTIND_TAB);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ENV_ROOT_CLCIK,methodName);
			getXpathWebElement(phrsc.ENV_ROOT_CLCIK);
			click();
			
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ENV_DELETE_BUTTON_CLCIK,methodName);
            getXpathWebElement(phrsc.ENV_DELETE_BUTTON_CLCIK);
            click();
            
            Thread.sleep(2000);
            waitForElementPresent(phrsc.ENV_DELETE_POPUP_OK,methodName);
            getXpathWebElement(phrsc.ENV_DELETE_POPUP_OK);
            click();
            isTextPresent(phrsc.ENV_DELETED_SUCCESS);
            Thread.sleep(2000);
            
		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}


	public  void ProjectDeleteWarningMessages(String methodName,EnvironmentConstantsXml envCons) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@ProjectDelete:****** ProjectDeleteWarningMessages****");
		try {
			/*Thread.sleep(2000);	
			waitForElementPresent(phrsc.PROJECT_CHECKBOX_CLCIK,methodName);	
			getXpathWebElement(phrsc.PROJECT_CHECKBOX_CLCIK);
			click();*/
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_DELETE_BUTTON_CLCIK,methodName);
			getXpathWebElement(phrsc.PROJECT_DELETE_BUTTON_CLCIK);
			click();
			
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_DELETE_POPUP_OK,methodName);
            getXpathWebElement(phrsc.PROJECT_DELETE_POPUP_OK);
            click();
            
            Thread.sleep(6000);
           
            isTextPresent(phrsc.PROJECT_DELETED_SUCCESS_MESSAGE);
            Thread.sleep(2000);
            
		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}
	
	public  void Project_Page_ToolTip_Verification(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("****** @Project_Page_ToolTip_Verification ****");
		try {
		

			Thread.sleep(3000);
			waitForElementPresent(phrsc.PROJECTS_TAB,methodName);
			getXpathWebElement(phrsc.PROJECTS_TAB);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PROJECT_CHECKBOX_CLCIK,methodName);	
			getXpathWebElement(phrsc.PROJECT_CHECKBOX_CLCIK);
			click();
			
			waitForElementPresent(phrsc.SONAR_REPORT_TOOLTIP,methodName);	
			getXpathWebElement(phrsc.SONAR_REPORT_TOOLTIP);
			ToolTipVerification(phrsc.SONAR_REPORT_TOOLTIP);
			Thread.sleep(2000);
			
			waitForElementPresent(phrsc.REPOSITORY_ADD_REPO_TOOLTIP,methodName);	
			getXpathWebElement(phrsc.REPOSITORY_ADD_REPO_TOOLTIP);
			ToolTipVerification(phrsc.REPOSITORY_ADD_REPO_TOOLTIP);
			Thread.sleep(2000);
			
			waitForElementPresent(phrsc.REPOSITORY_COMMIT_TOOLTIP,methodName);	
			getXpathWebElement(phrsc.REPOSITORY_COMMIT_TOOLTIP);
			ToolTipVerification(phrsc.REPOSITORY_COMMIT_TOOLTIP);
			Thread.sleep(2000);
			
			waitForElementPresent(phrsc.REPOSITORY_UPDATE_TOOLTIP,methodName);	
			getXpathWebElement(phrsc.REPOSITORY_UPDATE_TOOLTIP);
			ToolTipVerification(phrsc.REPOSITORY_UPDATE_TOOLTIP);
			Thread.sleep(2000);
			
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}

	public  void ImportApplicationTooltips(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("****** @ImportApplicationTooltips ****");
		try {
		
			Thread.sleep(3000);
			waitForElementPresent(phrsc.PROJECTS_TAB,methodName);
			getXpathWebElement(phrsc.PROJECTS_TAB);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.IMPORT_APPICATION_TAB,methodName);
			getXpathWebElement(phrsc.IMPORT_APPICATION_TAB);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.REPO_URL,methodName);
			getXpathWebElement(phrsc.REPO_URL).clear();
			click();
			sendKeys(phrsc.REPO_URL_PASS_VALUE);
			/*	
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CREDENTIAL_CHECK_BOX,methodName);
			getXpathWebElement(phrsc.CREDENTIAL_CHECK_BOX);
			click();
			*/
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPLICATION_USERNAME,methodName);	
			getXpathWebElement(phrsc.APPLICATION_USERNAME);
			//click();
			//sendKeys(userInfo.USERNAME);
			ToolTipVerification(phrsc.APPLICATION_USERNAME);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPLICATION_PASSWORD,methodName);
			getXpathWebElement(phrsc.APPLICATION_PASSWORD);
			//sendKeys(userInfo.PASSWORD);
			ToolTipVerification(phrsc.APPLICATION_PASSWORD);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.IMPORT_APPLICATION_SUBMIT,methodName);
			getXpathWebElement(phrsc.IMPORT_APPLICATION_SUBMIT);
			click();
			Thread.sleep(2000);
			isTextPresent(phrsc.INVALID_URL);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.IMPORT_APPLICATION_CANCEL,methodName);
			getXpathWebElement(phrsc.IMPORT_APPLICATION_CANCEL);
			click();
			Thread.sleep(3000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}
	
	public  void AddProjectPageWarniongMessages(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("****** @Help_Tab_Verification ****");
		try {
		
			Thread.sleep(3000);
			waitForElementPresent(phrsc.PROJECTS_TAB,methodName);
			getXpathWebElement(phrsc.PROJECTS_TAB);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADD_APPLICATION_BUTTON,methodName);
			getXpathWebElement(phrsc.ADD_APPLICATION_BUTTON);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(5000);
			isTextPresent(phrsc.ADDPROJECT_NAME_MESSAGE);
			isTextPresent(phrsc.ADDPROJECT_CODE_MESSAGE);
			isTextPresent(phrsc.ADDPROJECT_VERSION_MESSAGE);
			isTextPresent(phrsc.ADDPROJECT_CREATE_MESSAGE);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}

public boolean isTextPresentVerify(String text) throws InterruptedException, ScreenException {

        if (text!= null){
	         String build_failure="ERROR";
	    
	         for(int i=0;i<=400;i++){
		         if(driver.findElement(By.tagName("body")).getText().contains(text)){
		        	 break;
		         }
		         else{
		        	 if(i==400){
		        		 throw new RuntimeException("---- Time out for finding the Text----");
			         }
			        else if(driver.findElement(By.tagName("body")).getText().contains(build_failure)){
			        	System.out.println("*****OPERATION FAILED*****");
			        	throw new ScreenException("*****OPERATION FAILED*****");
			      
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

	
public boolean isTextDiplay(String text) throws InterruptedException, ScreenException
{
	 boolean r=false;
		
		if(text!=null)
		{
			for (int i=0;i<500;i++)
			{
			boolean value;
			if(value=driver.findElement(By.tagName("body")).getText().contains(text)){
			break;
		    }
			System.out.println("--------TextCheck value---->"+text+"-------Result is --------"+value);
			AssertJUnit.assertTrue(value);
			return value;
			}
		}
		else
		{
			throw new RuntimeException("---- Text not present----");
		}
		return false;
		}



	public void successFailureLoop() throws InterruptedException, IOException,
	Exception {
			if (isTextPresentVerify("BUILD SUCCESS")) {
				System.out.println("*****OPERATION SUCCEEDED*****");
				} 
	}
	
	public void RunAganstSource() throws InterruptedException, IOException,
	Exception {

		for(int i=0;i<10;i++){
		if(driver.findElement(By.xpath(phrsc.NODEJS_BUILDSTOPBTN)).isEnabled()){
			System.out.println("-------isEnabled()----------------");
		break;	
		}else{
 		   if(i==11){
			   throw new RuntimeException("---- Time out for finding the Text----");
		   }else if(!driver.findElement(By.xpath(phrsc.NODEJS_BUILDSTOPBTN)).isSelected()){
			   Thread.sleep(2000);
		   }
		  
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
			System.out.println("--------TextCheck value---->"+text+"-------Result is --------"+value);
			AssertJUnit.assertTrue(value);
			return value;
		}
		else
		{
			throw new RuntimeException("---- Text not present----");
		}

	}
	
	public void ButtonEnabled(String Xpath) throws InterruptedException, IOException,
	Exception {
		     Thread.sleep(3000);
		     try
		     {
			 if(driver.findElement(By.xpath(Xpath)).isEnabled()){
				System.out.println("-------New_ReleaseAvailable----------------");
			    }
			else if(driver.findElement(By.xpath(Xpath)).isSelected()){ 		   {
	 			System.out.println("------- New_Release_Available----------------");
	 		   }
			}else
			{
				System.out.println("----------No_Release_Available----------");
			}
			 }
		     catch(Throwable t)
		     {
		    	 t.printStackTrace();
		     }
	}
	
	public void MouseOverEvents(String Xpath) throws NullPointerException,IOException
	{
		log.info("@Entering:---------------MouseOverEvents()------------------------");
		try
		{
		
		element=driver.findElement(By.xpath(Xpath));
		Actions builder=new Actions(driver);
		builder.moveToElement(element).perform();
		
		}
		catch(Throwable t)
		{
			log.info("--------Exception in MouseOverEvents-------------- ");
		}
	}

public void ToolTipVerification(String Xpath) throws Exception
{   
	String toolTip;
	log.info("@Entering:---------------ToolTipVerification()------------------------");
	try
	{
	
	toolTip=driver.findElement(By.xpath(Xpath)).getAttribute("title");
	System.out.println(toolTip);
	}
	catch(Throwable t)
	{
		log.info("--------Exception in ToolTipVerification-------------- ");
	}
   }
public void ToolTipVerification1(String Xpath,TestSet3Xml test) throws Exception
{   
	String toolTip,s;
	
	log.info("@Entering:---------------ToolTipVerification()------------------------");
	try
	{
	
	toolTip=driver.findElement(By.xpath(Xpath)).getAttribute("title");
	System.out.println(toolTip);
	s=toolTip;
	Boolean flag=s.equalsIgnoreCase(toolTip);	
	System.out.println(flag);
	}
	catch(Throwable t)
	{
		log.info("--------Exception in ToolTipVerification-------------- ");
	}
   }
}


