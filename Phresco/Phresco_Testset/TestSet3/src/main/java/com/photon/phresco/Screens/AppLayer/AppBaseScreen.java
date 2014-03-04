package com.photon.phresco.Screens.AppLayer;
import java.awt.AWTException;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

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

	private static WebDriver driver;
	private ChromeDriverService chromeService;
	private Log log = LogFactory.getLog("BaseScreen");
	private static WebElement element;	

	private PhrescoUiConstantsXml phrsc;
	
	private static phresco_env_config phrscEnv;
	private UserInfoConstants userInfo;

	public AppBaseScreen() {

	}

	public AppBaseScreen(String selectedBrowser, String applicationURL, String applicatinContext,phresco_env_config phrscEnv,PhrescoUiConstantsXml phrsc,UserInfoConstants userInfo,PhrescoFrameworkData phrscData)
			throws ScreenException {
		
		AppBaseScreen.phrscEnv=phrscEnv;
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
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.LOGIN_PASSWORD,methodName);
			getXpathWebElement(phrsc.LOGIN_PASSWORD);
			click();
			sendKeys(userInfo.PASSWORD);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.LOGIN_BUTTON,methodName);
			getXpathWebElement(phrsc.LOGIN_BUTTON);
			click();
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
		try {

			FileUtils.copyFile(screenshot,
					new File(GetCurrentDir.getCurrentDirectory() + File.separator + methodName + ".png"));
		} catch (Exception e1) {
			log.info("EXCEPTION IN CAPTURE SCREENSHOT " + e1.getMessage());
		}
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
			/* String tooltip=element.findElement(By.xpath(phrsc.ADDPROJECT_NAME)).getAttribute("title");
			System.out.println(tooltip);
			 */
			
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CODE, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CODE);
			click();
			
			sendKeys(phpConst.PHP_HELLOWORLD_CODE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_DESCRIPTION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_DESCRIPTION);
			click();
			sendKeys(phpConst.PHP_HELLOWORLD_DESCRIPTION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_VERSION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_VERSION);
			click();
			sendKeys(phpConst.PHP_HELLOWORLD_VERSION_VALUE);

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
			Thread.sleep(50000);

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

	

	public  void phpProjectBlogCreate(String methodName,PhpConstantsXml phpConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@phpProjectBlogCreate::****** executing ****");
		try {
			Thread.sleep(2000);	
			waitForElementPresent(phrsc.PROJECTS_TAB,methodName);	
			getXpathWebElement(phrsc.PROJECTS_TAB);
			click();

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.ADD_APPLICATION_BUTTON,methodName);	
			getXpathWebElement(phrsc.ADD_APPLICATION_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_NAME);
			click();
			sendKeys(phpConst.PHP_BLOG_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CODE, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CODE);
			click();
			sendKeys(phpConst.PHP_BLOG_CODE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_DESCRIPTION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_DESCRIPTION);
			click();
			sendKeys(phpConst.PHP_BLOG_DESCRIPTION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_VERSION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_VERSION);
			click();
			sendKeys(phpConst.PHP_BLOG_VERSION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL);
			click();

			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY);
			selectText(element,phpConst.PHP_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(40000);

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}


	public  void phpProjectBlogEditApplication(String methodName,PhpConstantsXml phpConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@phpProjectBlogEditApplication::****** executing ****");
		try {
			Thread.sleep(2000);		
			waitForElementPresent(phpConst.PHP_PROJECT_BLOG_CHECK_BOX,methodName);
			getXpathWebElement(phpConst.PHP_PROJECT_BLOG_CHECK_BOX);
			click();
			
			Thread.sleep(5000);
			waitForElementPresent(phpConst.PHP_PROJECT_BLOG_EDIT_APPLICATION_LINK,methodName);
			getXpathWebElement(phpConst.PHP_PROJECT_BLOG_EDIT_APPLICATION_LINK);
			click();
						
			Thread.sleep(7000);
			waitForElementPresent(phrsc.APPINFO_PILOT_PROJECT, methodName);
			getXpathWebElement(phrsc.APPINFO_PILOT_PROJECT);
			selectText(element,phpConst.PHP_APP_INFO_PILOT_PROJECT_BLOG_VALUE);
			
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

	public  void phpProjectBlogAddFeature(String methodName,PhpConstantsXml phpConstants) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@phpProjectBlogAddFeature::****** executing ****");
		try {
			
			Thread.sleep(3000);
			getXpathWebElement(phrsc.FEATURE_FINISH_BUTTON);
			waitForElementPresent(phrsc.FEATURE_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.FEATURE_FINISH_BUTTON);
			click();
			Thread.sleep(80000);
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
			waitForElementPresent(phrsc.CONFIGURATIONS_ADD,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_ADD);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_NAME,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_NAME);
			click();
			clear();
			sendKeys(phpConst.PHP_CONFIGURATION_SERVER_NAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DESCRIPTION,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DESCRIPTION);
			click();
			clear();
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
			Thread.sleep(2000);
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
			Thread.sleep(8000);
			log.info("success");

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void phpBlogProjectCloneConfigurationServer(String methodName,PhpConstantsXml phpConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@phpBlogProjectCloneConfigurationServer::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_TAB,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_TAB);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ENV_CONFIG_TAB,methodName);
			getXpathWebElement(phrsc.ENV_CONFIG_TAB);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ENV_NAME,methodName);
			getXpathWebElement(phrsc.ENV_NAME);
			click();
			clear();
			sendKeys(phpConst.ENV_NAME);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ENV_DESC,methodName);
			getXpathWebElement(phrsc.ENV_DESC);
			click();
			clear();
			sendKeys(phpConst.ENV_DESC);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ENV_ADD_BUTTON,methodName);
			getXpathWebElement(phrsc.ENV_ADD_BUTTON);
			click();
			selectText(element, phpConst.PHP_CONFIGURATION_DATABASE_ENVIRONMENT_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ENV_OK_BUTTON,methodName);
			getXpathWebElement(phrsc.ENV_OK_BUTTON);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ENV_PRODUCTION_CHECKBOX,methodName);
			getXpathWebElement(phrsc.ENV_PRODUCTION_CHECKBOX);
			click();
						
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ENV_FIRST_CLONE,methodName);
			getXpathWebElement(phrsc.ENV_FIRST_CLONE);
			click();
			
			waitForElementPresent(phrsc.ENV_CLONE_ENV_SELECT,methodName);
			getXpathWebElement(phrsc.ENV_CLONE_ENV_SELECT);
			selectText(element, phpConst.ENV_TESTING);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ENV_CLONE_BUTTON,methodName);
			getXpathWebElement(phrsc.ENV_CLONE_BUTTON);
			click();
			Thread.sleep(2000);
			isTextPresent(phrsc.ENV_TEXT_VERFIY);
			
			
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void phpBlogProjectCloneConfigurationDatabase(String methodName,PhpConstantsXml phpConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@phpBlogProjectCloneConfigurationDatabase::****** executing ****");
		try {
				
			Thread.sleep(5000);
			waitForElementPresent(phrsc.ENV_PRODUCTION_CHECKBOX,methodName);
			getXpathWebElement(phrsc.ENV_PRODUCTION_CHECKBOX);
			click();
						
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ENV_SECOND_CLONE,methodName);
			getXpathWebElement(phrsc.ENV_SECOND_CLONE);
			click();
			
			waitForElementPresent(phrsc.ENV_CLONE_ENV_SELECT,methodName);
			getXpathWebElement(phrsc.ENV_CLONE_ENV_SELECT);
			selectText(element, phpConst.ENV_TESTING);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ENV_CLONE_BUTTON,methodName);
			getXpathWebElement(phrsc.ENV_CLONE_BUTTON);
			click();
			Thread.sleep(2000);
			isTextPresent(phrsc.ENV_TEXT_VERFIY);
			
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	

	public  void drupal6ProjectHelloWorldCreate(String methodName,Drupal6ConstantsXml drupal6Const) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@drupal6ProjectHelloWorldCreate::****** executing ****");
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
			waitForElementPresent(phrsc.ADDPROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_NAME);
			click();
			sendKeys(drupal6Const.DRUPAL6_HELLOWORLD_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CODE, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CODE);
			click();
			sendKeys(drupal6Const.DRUPAL6_HELLOWORLD_CODE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_DESCRIPTION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_DESCRIPTION);
			click();
			sendKeys(drupal6Const.DRUPAL6_HELLOWORLD_DESCRIPTION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_VERSION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_VERSION);
			click();
			sendKeys(drupal6Const.DRUPAL6_HELLOWORLD_VERSION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL);
			click();

			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY, methodName);
			selectText(element,drupal6Const.DRUPAL6_TECHNOLGY_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(40000);

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void drupal6ProjectHelloWorldEditApplication(String methodName,Drupal6ConstantsXml drupal6Const) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@drupal6ProjectHelloWorldEditApplication::****** executing ****");
		try {
			
			Thread.sleep(2000);	
			waitForElementPresent(drupal6Const.DRUPAL6_PROJECT_HELLOWORLD_CHECK_BOX,methodName);
			getXpathWebElement(drupal6Const.DRUPAL6_PROJECT_HELLOWORLD_CHECK_BOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(drupal6Const.DRUPAL6_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK,methodName);
          	getXpathWebElement(drupal6Const.DRUPAL6_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK);
			click();
						
			Thread.sleep(7000);
			waitForElementPresent(phrsc.APPINFO_SERVER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.APPINFO_SERVER_CHECKBOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPINFO_SERVER_SELECT, methodName);
			getXpathWebElement(phrsc.APPINFO_SERVER_SELECT);
			selectText(element, drupal6Const.DRUPAL6_SERVER_VALUE);
			
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
			selectText(element, drupal6Const.DRUPAL6_DATABASE_VALUE);
			
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

	public  void drupal6ProjectHelloWorldAddFeature(String methodName,Drupal6ConstantsXml drupal6Const) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@drupal6ProjectHelloWorldAddFeature::****** executing ****");
		try {

			Thread.sleep(3000);
			waitForElementPresent(phrsc.FEATURE_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.FEATURE_FINISH_BUTTON);
			click();
			Thread.sleep(50000);


		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void drupal6ProjectHelloWorldAddConfigurationServer(String methodName,Drupal6ConstantsXml drupal6Const) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@drupal6ProjectHelloWorldAddConfigurationServer::****** executing ****");
		try {
			
			Thread.sleep(3000);	
			waitForElementPresent(drupal6Const.DRUPAL6_PROJECT_HELLOWORLD_CHECK_BOX,methodName);	
			getXpathWebElement(drupal6Const.DRUPAL6_PROJECT_HELLOWORLD_CHECK_BOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(drupal6Const.DRUPAL6_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK,methodName);
          	getXpathWebElement(drupal6Const.DRUPAL6_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK);
			click();
			
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
			sendKeys(drupal6Const.DRUPAL6_CONFIGURATION_SERVER_NAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DESCRIPTION,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DESCRIPTION);
			click();
			sendKeys(drupal6Const.DRUPAL6_CONFIGURATION_SERVER_DESCRIPTION_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT);
			click();
			selectText(element, drupal6Const.DRUPAL6_CONFIGURATION_SERVER_ENVIRONMENT_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_TYPE);
			selectText(element,drupal6Const.DRUPAL6_CONFIGURATION_SERVER_TYPE_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_PROTOCOL);
			selectText(element, drupal6Const.DRUPAL6_CONFIGURATION_SERVER_PROTOCOL_HTTP_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_HOST,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_HOST);
			click();
			sendKeys(drupal6Const.DRUPAL6_CONFIGURATION_SERVER_HOST_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_PORT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_PORT);
			click();
			clear();
			sendKeys(drupal6Const.DRUPAL6_CONFIGURATION_SERVER_PORT_VALUE);
			
	
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_DEPLOYDIRECTORY,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_DEPLOYDIRECTORY);
			click();
			sendKeys(drupal6Const.DRUPAL6_CONFIGURATION_SERVER_DEPLOY_DIRECTORY_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_CONTEXT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_CONTEXT);
			click();
			sendKeys(drupal6Const.DRUPAL6_CONFIGURATION_SERVER_CONTEXT_VALUE_HELLOWORLD);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SAVE,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SAVE);
			click();
			Thread.sleep(3000);


		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}



	public  void drupal6ProjectHelloWorldAddConfigurationDatabase(String methodName,Drupal6ConstantsXml drupal6Const) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@drupal6ProjectHelloWorldAddConfigurationDatabase::****** executing ****");
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
			sendKeys(drupal6Const.DRUPAL6_CONFIGURATION_DATABASE_NAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DESCRIPTION,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DESCRIPTION);
			click();
			sendKeys(drupal6Const.DRUPAL6_CONFIGURATION_DATABASE_DESCRIPTION_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT);
			click();
			selectText(element, drupal6Const.DRUPAL6_CONFIGURATION_DATABASE_ENVIRONMENT_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_TYPE);
			selectText(element,drupal6Const.DRUPAL6_CONFIGURATION_DATABASE_TYPE_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_HOST,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_HOST);
			click();
			sendKeys(drupal6Const.DRUPAL6_CONFIGURATION_DATABASE_HOST_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_PORT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_PORT);
			click();
			clear();
			sendKeys(drupal6Const.DRUPAL6_CONFIGURATION_DATABASE_PORT_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_USERNAME,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_USERNAME);
			click();
			sendKeys(drupal6Const.DRUPAL6_CONFIGURATION_DATABASE_DB_USERNAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_DBNAME,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_DBNAME);
			click();
			sendKeys(drupal6Const.DRUPAL6_CONFIGURATION_DATABASE_DB_NAME_VALUE_HELLOWORLD);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SAVE,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SAVE);
			click();
			Thread.sleep(5000);
			log.info("success");

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	public  void drupal7ProjectHelloWorldCreate(String methodName,Drupal7ConstantsXml drupal7Const) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@drupal7ProjectHelloWorldCreate::****** executing ****");
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
			waitForElementPresent(phrsc.ADDPROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_NAME);
			click();
			sendKeys(drupal7Const.DRUPAL7_HELLOWORLD_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CODE, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CODE);
			click();
			sendKeys(drupal7Const.DRUPAL7_HELLOWORLD_CODE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_DESCRIPTION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_DESCRIPTION);
			click();
			sendKeys(drupal7Const.DRUPAL7_HELLOWORLD_DESCRIPTION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_VERSION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_VERSION);
			click();
			sendKeys(drupal7Const.DRUPAL7_HELLOWORLD_VERSION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL);
			click();

			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY);
			selectText(element,drupal7Const.DRUPAL7_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(40000);

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void drupal7ProjectHelloWorldEditApplication(String methodName,Drupal7ConstantsXml drupal7Const) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@drupal7ProjectHelloWorldEditApplication::****** executing ****");
		try {
			
			Thread.sleep(2000);		
			waitForElementPresent(drupal7Const.DRUPAL7_PROJECT_HELLOWORLD_CHECK_BOX,methodName);
			getXpathWebElement(drupal7Const.DRUPAL7_PROJECT_HELLOWORLD_CHECK_BOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(drupal7Const.DRUPAL7_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK,methodName);
          	getXpathWebElement(drupal7Const.DRUPAL7_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK);
			click();
			
			Thread.sleep(4000);
			waitForElementPresent(phrsc.APPINFO_SERVER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.APPINFO_SERVER_CHECKBOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPINFO_SERVER_SELECT, methodName);
			getXpathWebElement(phrsc.APPINFO_SERVER_SELECT);
			selectText(element, drupal7Const.DRUPAL7_SERVER_VALUE);
			
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
			selectText(element, drupal7Const.DRUPAL7_DATABASE_VALUE);
		
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

	public  void drupal7ProjectAddFeature(String methodName,Drupal7ConstantsXml drupal7Const) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@drupal7ProjectHelloWorldAddFeature::****** executing ****");
		try {

			Thread.sleep(5000);
			waitForElementPresent(phrsc.FEATURE_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.FEATURE_FINISH_BUTTON);
			click();
			Thread.sleep(60000);


		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void drupal7ProjectAddConfigurationServer(String methodName,Drupal7ConstantsXml drupal7Const) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@drupal7ProjectAddConfigurationServer::****** executing ****");
		try {
			if(methodName=="testDrupal7ProjectHelloWorldAddConfigurationServer"){
				Thread.sleep(2000);		
				waitForElementPresent(drupal7Const.DRUPAL7_PROJECT_HELLOWORLD_CHECK_BOX,methodName);
				getXpathWebElement(drupal7Const.DRUPAL7_PROJECT_HELLOWORLD_CHECK_BOX);
				click();
				
				Thread.sleep(2000);
				waitForElementPresent(drupal7Const.DRUPAL7_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK,methodName);
	          	getXpathWebElement(drupal7Const.DRUPAL7_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK);
				click();	
			}
			else{
				Thread.sleep(2000);		
				waitForElementPresent(drupal7Const.DRUPAL7_PROJECT_ESHOP_CHECK_BOX,methodName);
				getXpathWebElement(drupal7Const.DRUPAL7_PROJECT_ESHOP_CHECK_BOX);
				click();
				
				Thread.sleep(2000);
				waitForElementPresent(drupal7Const.DRUPAL7_PROJECT_ESHOP_EDIT_APPLICATION_LINK,methodName);
	          	getXpathWebElement(drupal7Const.DRUPAL7_PROJECT_ESHOP_EDIT_APPLICATION_LINK);
				click();
				
			}
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
			sendKeys(drupal7Const.DRUPAL7_CONFIGURATION_SERVER_NAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DESCRIPTION,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DESCRIPTION);
			click();
			clear();
			sendKeys(drupal7Const.DRUPAL7_CONFIGURATION_SERVER_DESCRIPTION_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT);
			click();
			selectText(element, drupal7Const.DRUPAL7_CONFIGURATION_SERVER_ENVIRONMENT_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_TYPE);
			selectText(element,drupal7Const.DRUPAL7_CONFIGURATION_SERVER_TYPE_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_PROTOCOL);
			selectText(element, drupal7Const.DRUPAL7_CONFIGURATION_SERVER_PROTOCOL_HTTP_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_HOST,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_HOST);
			click();
			sendKeys(drupal7Const.DRUPAL7_CONFIGURATION_SERVER_HOST_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_PORT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_PORT);
			click();
			sendKeys(drupal7Const.DRUPAL7_CONFIGURATION_SERVER_PORT_VALUE);
			
	
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_DEPLOYDIRECTORY,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_DEPLOYDIRECTORY);
			click();
			sendKeys(drupal7Const.DRUPAL7_CONFIGURATION_SERVER_DEPLOY_DIRECTORY_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_CONTEXT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_CONTEXT);
			click();
			if(methodName=="testDrupal7ProjectHelloWorldAddConfigurationServer"){
				sendKeys(drupal7Const.DRUPAL7_CONFIGURATION_SERVER_CONTEXT_VALUE_HELLOWORLD);
				Thread.sleep(2000);
			}
			else{
				sendKeys(drupal7Const.DRUPAL7_CONFIGURATION_SERVER_CONTEXT_VALUE_ESHOP);
				Thread.sleep(2000);
				
			}

			waitForElementPresent(phrsc.CONFIGURATIONS_SAVE,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SAVE);
			click();
			Thread.sleep(3000);


		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}



	public  void drupal7ProjectAddConfigurationDatabase(String methodName,Drupal7ConstantsXml drupal7Const) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@drupal7ProjectAddConfigurationDatabase::****** executing ****");
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
			sendKeys(drupal7Const.DRUPAL7_CONFIGURATION_DATABASE_NAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DESCRIPTION,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DESCRIPTION);
			click();
			sendKeys(drupal7Const.DRUPAL7_CONFIGURATION_DATABASE_DESCRIPTION_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT);
			click();
			selectText(element, drupal7Const.DRUPAL7_CONFIGURATION_DATABASE_ENVIRONMENT_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_TYPE);
			selectText(element,drupal7Const.DRUPAL7_CONFIGURATION_DATABASE_TYPE_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_HOST,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_HOST);
			click();
			sendKeys(drupal7Const.DRUPAL7_CONFIGURATION_DATABASE_HOST_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_PORT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_PORT);
			click();
			sendKeys(drupal7Const.DRUPAL7_CONFIGURATION_DATABASE_PORT_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_USERNAME,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_USERNAME);
			click();
			sendKeys(drupal7Const.DRUPAL7_CONFIGURATION_DATABASE_DB_USERNAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_DBNAME,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_DBNAME);
			click();
			if(methodName=="testDrupal7HelloWorldDatabase"){
				sendKeys(drupal7Const.DRUPAL7_CONFIGURATION_DATABASE_DB_NAME_VALUE_HELLOWORLD);
				Thread.sleep(2000);
			}
			else{
				sendKeys(drupal7Const.DRUPAL7_CONFIGURATION_DATABASE_DB_NAME_VALUE_ESHOP);
				Thread.sleep(2000);
			}

			waitForElementPresent(phrsc.CONFIGURATIONS_SAVE,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SAVE);
			click();
			Thread.sleep(5000);
			log.info("success");

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	public  void drupal7ProjectEshopCreate(String methodName,Drupal7ConstantsXml drupal7Const) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@drupal7ProjectEshopCreate::****** executing ****");
		try {
			Thread.sleep(2000);	
			waitForElementPresent(phrsc.PROJECTS_TAB,methodName);	
			getXpathWebElement(phrsc.PROJECTS_TAB);
			click();

			Thread.sleep(2000);		
			waitForElementPresent(phrsc.ADD_APPLICATION_BUTTON,methodName);
			getXpathWebElement(phrsc.ADD_APPLICATION_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_NAME);
			click();
			sendKeys(drupal7Const.DRUPAL7_ESHOP_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CODE, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CODE);
			click();
			sendKeys(drupal7Const.DRUPAL7_ESHOP_CODE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_DESCRIPTION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_DESCRIPTION);
			click();
			sendKeys(drupal7Const.DRUPAL7_ESHOP_DESCRIPTION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_VERSION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_VERSION);
			click();
			sendKeys(drupal7Const.DRUPAL7_ESHOP_VERSION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL);
			click();

			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY);
			selectText(element,drupal7Const.DRUPAL7_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(40000);

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void drupal7ProjectEshopEditApplication(String methodName,Drupal7ConstantsXml drupal7Const) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@drupal7ProjectEshopEditApplication::****** executing ****");
		try {
			
			Thread.sleep(2000);		
			waitForElementPresent(drupal7Const.DRUPAL7_PROJECT_ESHOP_CHECK_BOX,methodName);
			getXpathWebElement(drupal7Const.DRUPAL7_PROJECT_ESHOP_CHECK_BOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(drupal7Const.DRUPAL7_PROJECT_ESHOP_EDIT_APPLICATION_LINK,methodName);
          	getXpathWebElement(drupal7Const.DRUPAL7_PROJECT_ESHOP_EDIT_APPLICATION_LINK);
			click();			
			
			Thread.sleep(4000);
			waitForElementPresent(phrsc.APPINFO_PILOT_PROJECT, methodName);
			getXpathWebElement(phrsc.APPINFO_PILOT_PROJECT);
			selectText(element,drupal7Const.DRUPAL7_APP_INFO_PILOT_PROJECT_ESHOP_VALUE);
			
			Thread.sleep(4000);
			getXpathWebElement(phrsc.APPINFO_NEXT);
			waitForElementPresent(phrsc.APPINFO_NEXT, methodName);
			click();
			Thread.sleep(2000);

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void wordPressProjectHelloWorldCreate(String methodName,WordPressConstantsXml wordPressConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@wordPressProjectHelloWorldCreate::****** executing ****");
		try {
			Thread.sleep(2000);	
			waitForElementPresent(phrsc.PROJECTS_TAB,methodName);	
			getXpathWebElement(phrsc.PROJECTS_TAB);
			click();

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.ADD_APPLICATION_BUTTON,methodName);	
			getXpathWebElement(phrsc.ADD_APPLICATION_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_NAME);
			click();
			sendKeys(wordPressConst.WORDPRESS_HELLOWORLD_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CODE, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CODE);
			click();
			sendKeys(wordPressConst.WORDPRESS_HELLOWORLD_CODE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_DESCRIPTION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_DESCRIPTION);
			click();
			sendKeys(wordPressConst.WORDPRESS_HELLOWORLD_DESCRIPTION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_VERSION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_VERSION);
			click();
			sendKeys(wordPressConst.WORDPRESS_HELLOWORLD_VERSION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY);
			selectText(element,wordPressConst.WORDPRESS_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(40000);

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void wordPressProjectHelloWorldEditApplication(String methodName,WordPressConstantsXml wordPressConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@wordPressProjectHelloWorldEditApplication::****** executing ****");
		try {
			
			Thread.sleep(2000);		
			waitForElementPresent(wordPressConst.WORDPRESS_PROJECT_HELLOWORLD_CHECK_BOX,methodName);
			getXpathWebElement(wordPressConst.WORDPRESS_PROJECT_HELLOWORLD_CHECK_BOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(wordPressConst.WORDPRESS_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK,methodName);
          	getXpathWebElement(wordPressConst.WORDPRESS_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK);
			click();
			
			Thread.sleep(5000);
			waitForElementPresent(phrsc.APPINFO_SERVER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.APPINFO_SERVER_CHECKBOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPINFO_SERVER_SELECT, methodName);
			getXpathWebElement(phrsc.APPINFO_SERVER_SELECT);
			selectText(element, wordPressConst.WORDPRESS_SERVER_VALUE);
			
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
			selectText(element, wordPressConst.WORDPRESS_DATABASE_VALUE);
			
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

	public  void wordPressProjectHelloWorldAddFeature(String methodName,WordPressConstantsXml wordPressConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@wordPressProjectHelloWorldAddFeature::****** executing ****");
		try {


			Thread.sleep(2000);
			waitForElementPresent(phrsc.FEATURE_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.FEATURE_FINISH_BUTTON);
			click();
			Thread.sleep(40000);


		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void wordPressProjectHelloWorldAddConfigurationServer(String methodName,WordPressConstantsXml wordPressConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@wordPressProjectHelloWorldAddConfigurationServer::****** executing ****");
		try {
			
			Thread.sleep(2000);		
			waitForElementPresent(wordPressConst.WORDPRESS_PROJECT_HELLOWORLD_CHECK_BOX,methodName);
			getXpathWebElement(wordPressConst.WORDPRESS_PROJECT_HELLOWORLD_CHECK_BOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(wordPressConst.WORDPRESS_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK,methodName);
          	getXpathWebElement(wordPressConst.WORDPRESS_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK);
			click();
			
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
			sendKeys(wordPressConst.WORDPRESS_CONFIGURATION_SERVER_NAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DESCRIPTION,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DESCRIPTION);
			click();
			clear();
			sendKeys(wordPressConst.WORDPRESS_CONFIGURATION_SERVER_DESCRIPTION_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT);
			click();
			selectText(element, wordPressConst.WORDPRESS_CONFIGURATION_SERVER_ENVIRONMENT_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_TYPE);
			selectText(element,wordPressConst.WORDPRESS_CONFIGURATION_SERVER_TYPE_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_PROTOCOL);
			selectText(element, wordPressConst.WORDPRESS_CONFIGURATION_SERVER_PROTOCOL_HTTP_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_HOST,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_HOST);
			click();
			clear();
			sendKeys(wordPressConst.WORDPRESS_CONFIGURATION_SERVER_HOST_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_PORT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_PORT);
			click();
			clear();
			sendKeys(wordPressConst.WORDPRESS_CONFIGURATION_SERVER_PORT_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_DEPLOYDIRECTORY,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_DEPLOYDIRECTORY);
			click();
			clear();
			sendKeys(wordPressConst.WORDPRESS_CONFIGURATION_SERVER_DEPLOY_DIRECTORY_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_CONTEXT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_CONTEXT);
			click();
			clear();
			sendKeys(wordPressConst.WORDPRESS_CONFIGURATION_SERVER_CONTEXT_VALUE_HELLOWORLD);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SAVE,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SAVE);
			click();
			Thread.sleep(3000);


		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}



	public  void wordPressProjectHelloWorldAddConfigurationDatabase(String methodName,WordPressConstantsXml wordPressConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@wordPressProjectHelloWorldAddConfigurationDatabase::****** executing ****");
		try {
						
			Thread.sleep(2000);
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
			sendKeys(wordPressConst.WORDPRESS_CONFIGURATION_DATABASE_NAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DESCRIPTION,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DESCRIPTION);
			click();
			clear();
			sendKeys(wordPressConst.WORDPRESS_CONFIGURATION_DATABASE_DESCRIPTION_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT);
			selectText(element, wordPressConst.WORDPRESS_CONFIGURATION_DATABASE_ENVIRONMENT_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_TYPE);
			selectText(element,wordPressConst.WORDPRESS_CONFIGURATION_DATABASE_TYPE_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_HOST,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_HOST);
			click();
			clear();
			sendKeys(wordPressConst.WORDPRESS_CONFIGURATION_DATABASE_HOST_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_PORT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_PORT);
			click();
			clear();
			sendKeys(wordPressConst.WORDPRESS_CONFIGURATION_DATABASE_PORT_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_USERNAME,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_USERNAME);
			click();
			clear();
			sendKeys(wordPressConst.WORDPRESS_CONFIGURATION_DATABASE_DB_USERNAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_DBNAME,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_DBNAME);
			click();
			clear();
			sendKeys(wordPressConst.WORDPRESS_CONFIGURATION_DATABASE_DB_NAME_VALUE_HELLOWORLD);
			
			Thread.sleep(2000);
			getXpathWebElement(phrsc.CONFIGURATIONS_SAVE);
			waitForElementPresent(phrsc.CONFIGURATIONS_SAVE,methodName);
			click();
			Thread.sleep(5000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	
	public  void siteCoreProjectHelloWorldCreate(String methodName,SiteCoreConstantsXml siteCoreConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@siteCoreProjectHelloWorldCreate::****** executing ****");
		try {
			Thread.sleep(2000);		
			waitForElementPresent(phrsc.PROJECTS_TAB,methodName);
			getXpathWebElement(phrsc.PROJECTS_TAB);
			click();

			Thread.sleep(2000);		
			waitForElementPresent(phrsc.ADD_APPLICATION_BUTTON,methodName);
			getXpathWebElement(phrsc.ADD_APPLICATION_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_NAME);
			click();
			sendKeys(siteCoreConst.SITECORE_HELLOWORLD_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CODE, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CODE);
			click();
			sendKeys(siteCoreConst.SITECORE_HELLOWORLD_CODE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_DESCRIPTION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_DESCRIPTION);
			click();
			sendKeys(siteCoreConst.SITECORE_HELLOWORLD_DESCRIPTION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_VERSION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_VERSION);
			click();
			sendKeys(siteCoreConst.SITECORE_HELLOWORLD_VERSION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL);
			click();

			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY);
			selectText(element,siteCoreConst.SITECORE_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(50000);

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void siteCoreProjectHelloWorldEditApplication(String methodName,SiteCoreConstantsXml siteCoreConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@siteCoreProjectHelloWorldEditApplication::****** executing ****");
		try {
			
			
			Thread.sleep(2000);		
			waitForElementPresent(siteCoreConst.SITECORE_PROJECT_HELLOWORLD_CHECK_BOX,methodName);
			getXpathWebElement(siteCoreConst.SITECORE_PROJECT_HELLOWORLD_CHECK_BOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(siteCoreConst.SITECORE_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK,methodName);
          	getXpathWebElement(siteCoreConst.SITECORE_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK);
			click();
			
			Thread.sleep(4000);
			waitForElementPresent(phrsc.APPINFO_SERVER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.APPINFO_SERVER_CHECKBOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPINFO_SERVER_SELECT, methodName);
			getXpathWebElement(phrsc.APPINFO_SERVER_SELECT);
			selectText(element, siteCoreConst.SITECORE_SERVER);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPINFO_SERVERVERSION, methodName);
			getXpathWebElement(phrsc.APPINFO_SERVERVERSION);
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

	public  void siteCoreProjectHelloWorldAddFeature(String methodName,SiteCoreConstantsXml siteCoreConstants) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@siteCoreProjectHelloWorldAddFeature::****** executing ****");
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
	
	public  void SiteCoreServerConfiguration(String methodName,SiteCoreConstantsXml siteCoreConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@SiteCoreServerConfiguration::****** executing ****");
		try {
			
			Thread.sleep(2000);		
			waitForElementPresent(siteCoreConst.SITECORE_PROJECT_HELLOWORLD_CHECK_BOX,methodName);
			getXpathWebElement(siteCoreConst.SITECORE_PROJECT_HELLOWORLD_CHECK_BOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(siteCoreConst.SITECORE_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK,methodName);
          	getXpathWebElement(siteCoreConst.SITECORE_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK);
			click();
			
			Thread.sleep(2000);	
			waitForElementPresent(phrsc.CONFIGURATIONS_TAB, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_TAB);
			click();
			
			Thread.sleep(10000);
			waitForElementPresent(phrsc.CONFIGURATIONS_ADD, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_ADD);
			click();
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_NAME, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_NAME);
			sendKeys(siteCoreConst.SITECORE_SERVER_NAME);
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DESCRIPTION, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DESCRIPTION);
			sendKeys(siteCoreConst.SITECORE_SERVER_DISCRIPTION);
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT);
			selectText(element,siteCoreConst.SITECORE_SERVER_ENVIRONMENT);
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_TYPE, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_TYPE);
			selectText(element,siteCoreConst.SITECORE_SERVER_TYPE);
			
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_PROTOCOL, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_PROTOCOL);
			selectText(element,siteCoreConst.SITECORE_SERVER_PROTOCOL);
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_HOST, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_HOST);
			sendKeys(siteCoreConst.SITECORE_SERVER_HOST);
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_PORT, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_PORT);
			sendKeys(siteCoreConst.SITECORE_SERVER_PORT);
		
			Thread.sleep(1000);
			waitForElementPresent(phrsc.ASPDOTNET_SITE_NAME, methodName);
			getXpathWebElement(phrsc.ASPDOTNET_SITE_NAME);
			sendKeys(siteCoreConst.SITECORE_SERVER_SITENAME);
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.ASPDOTNET_APP_NAME, methodName);
			getXpathWebElement(phrsc.ASPDOTNET_APP_NAME);
			sendKeys(siteCoreConst.SITECORE_SERVER_APPNAME);
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.SITECORE_INSTALLATION_PATH, methodName);
			getXpathWebElement(phrsc.SITECORE_INSTALLATION_PATH);
			sendKeys(siteCoreConst.SITECORE_SERVER_DEPLOY_DIR);
			
			Thread.sleep(3000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SAVE, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SAVE);
			click();
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	
	public  void AspDotNetProjectHelloWorldCreate(String methodName,AspDotNetConstantsXml AspDotNetConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@AspDotNetProjectHelloWorldCreate::****** executing ****");
		try {
			Thread.sleep(2000);	
			waitForElementPresent(phrsc.PROJECTS_TAB,methodName);	
			getXpathWebElement(phrsc.PROJECTS_TAB);
			click();

			Thread.sleep(2000);		
			waitForElementPresent(phrsc.ADD_APPLICATION_BUTTON,methodName);
			getXpathWebElement(phrsc.ADD_APPLICATION_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_NAME);
			click();
			sendKeys(AspDotNetConst.ASP_DOTNET_HELLOWORLD_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CODE, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CODE);
			click();
			sendKeys(AspDotNetConst.ASP_DOTNET_HELLOWORLD_CODE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_DESCRIPTION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_DESCRIPTION);
			click();
			sendKeys(AspDotNetConst.ASP_DOTNET_HELLOWORLD_DESCRIPTION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_VERSION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_VERSION);
			click();
			sendKeys(AspDotNetConst.ASP_DOTNET_HELLOWORLD_VERSION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL);
			click();

			Thread.sleep(3000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY);
			selectText(element,AspDotNetConst.ASP_DOTNET_TECHNOLGY_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(35000);

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
         
	public  void AspDotNetProjectHelloWorldEditApplication(String methodName,AspDotNetConstantsXml AspDotNetConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@AspDotNetProjectHelloWorldEditApplication::****** executing ****");
		try {
			
			Thread.sleep(2000);	
			waitForElementPresent(AspDotNetConst.ASPDOTNET_PROJECT_HELLOWORLD_CHECK_BOX,methodName);	
			getXpathWebElement(AspDotNetConst.ASPDOTNET_PROJECT_HELLOWORLD_CHECK_BOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(AspDotNetConst.ASPDOTNET_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK,methodName);
          	getXpathWebElement(AspDotNetConst.ASPDOTNET_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK);
			click();
							
			Thread.sleep(3000);
			waitForElementPresent(phrsc.APPINFO_SERVER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.APPINFO_SERVER_CHECKBOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPINFO_SERVER_SELECT, methodName);
			getXpathWebElement(phrsc.APPINFO_SERVER_SELECT);
			selectText(element, AspDotNetConst.ASPDOTNET_SERVER_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(AspDotNetConst.ASPDOTNET_SERVER_CHECKBOX, methodName);
			getXpathWebElement(AspDotNetConst.ASPDOTNET_SERVER_CHECKBOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPINFO_NEXT, methodName);
			getXpathWebElement(phrsc.APPINFO_NEXT);
			click();
			
			

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
    
	public  void AspDotNetProjectHelloWorldAddFeature(String methodName,AspDotNetConstantsXml AspDotNetConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@AspDotNetProjectHelloWorldAddFeature::****** executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.FEATURE_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.FEATURE_FINISH_BUTTON);
			click();
			Thread.sleep(40000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
		
			
	public  void SharePointProjectHelloWorldCreate(String methodName,SharePointConstantsXml SharePointConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@SharePointProjectHelloWorldCreate::****** executing ****");
		try {
			Thread.sleep(2000);		
			waitForElementPresent(phrsc.PROJECTS_TAB,methodName);
			getXpathWebElement(phrsc.PROJECTS_TAB);
			click();

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.ADD_APPLICATION_BUTTON,methodName);	
			getXpathWebElement(phrsc.ADD_APPLICATION_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_NAME);
			click();
			sendKeys(SharePointConst.SHARE_POINT_HELLOWORLD_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CODE, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CODE);
			click();
			sendKeys(SharePointConst.SHARE_POINT_HELLOWORLD_CODE_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_DESCRIPTION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_DESCRIPTION);
			click();
			sendKeys(SharePointConst.SHARE_POINT_HELLOWORLD_DESCRIPTION_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_VERSION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_VERSION);
			click();
			sendKeys(SharePointConst.SHARE_POINT_HELLOWORLD_VERSION_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL);
			click();
			
			Thread.sleep(3000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY);
			selectText(element,SharePointConst.SHARE_POINT_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(50000);

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	public  void sharePointProjectHelloworldEditApplication(String methodName,SharePointConstantsXml SharePointConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@sharePointProjectHelloWorldEditApplication::****** executing ****");
		try {
					
			Thread.sleep(2000);		
			waitForElementPresent(SharePointConst.SHARE_POINT_PROJECT_HELLOWORLD_CHECK_BOX,methodName);
			getXpathWebElement(SharePointConst.SHARE_POINT_PROJECT_HELLOWORLD_CHECK_BOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(SharePointConst.SHARE_POINT_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK,methodName);
          	getXpathWebElement(SharePointConst.SHARE_POINT_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK);
			click();
			
			Thread.sleep(4000);
			waitForElementPresent(phrsc.APPINFO_SERVER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.APPINFO_SERVER_CHECKBOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPINFO_SERVER_SELECT, methodName);
			getXpathWebElement(phrsc.APPINFO_SERVER_SELECT);
			selectText(element, SharePointConst.SHARE_POINT_SERVER_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPINFO_SERVERVERSION, methodName);
			getXpathWebElement(phrsc.APPINFO_SERVERVERSION);
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
	
	public  void sharePointProjectHelloworldAddFeature(String methodName,SharePointConstantsXml SharePointConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@sharePointProjectHelloworldAddFeature::****** executing ****");
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
	public  void SharePointProjectResourceCreate(String methodName,SharePointConstantsXml SharePointConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@SharePointProjectResourceCreate::****** executing ****");
		try {
			Thread.sleep(2000);		
			waitForElementPresent(phrsc.PROJECTS_TAB,methodName);
			getXpathWebElement(phrsc.PROJECTS_TAB);
			click();
			
			Thread.sleep(2000);	
			waitForElementPresent(phrsc.ADD_APPLICATION_BUTTON,methodName);	
			getXpathWebElement(phrsc.ADD_APPLICATION_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_NAME);
			click();
			sendKeys(SharePointConst.SHARE_POINT_RESOURCE_NAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CODE, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CODE);
			click();
			sendKeys(SharePointConst.SHARE_POINT_RESOURCE_CODE_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_DESCRIPTION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_DESCRIPTION);
			click();
			sendKeys(SharePointConst.SHARE_POINT_RESOURCE_DESCRIPTION_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_VERSION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_VERSION);
			click();
			sendKeys(SharePointConst.SHARE_POINT_RESOURCE_VERSION_VALUE);
			

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL);
			click();
			
			Thread.sleep(3000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY);
			selectText(element,SharePointConst.SHARE_POINT_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(50000);

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	public  void sharePointProjectResourceEditApplication(String methodName,SharePointConstantsXml SharePointConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@sharePointProjectResourceEditApplication::****** executing ****");
		try {
					
			Thread.sleep(2000);		
			waitForElementPresent(SharePointConst.SHARE_POINT_PROJECT_RESOURCE_CHECK_BOX,methodName);
			getXpathWebElement(SharePointConst.SHARE_POINT_PROJECT_RESOURCE_CHECK_BOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(SharePointConst.SHARE_POINT_PROJECT_RESOURCE_EDIT_APPLICATION_LINK,methodName);
          	getXpathWebElement(SharePointConst.SHARE_POINT_PROJECT_RESOURCE_EDIT_APPLICATION_LINK);
			click();
			
			Thread.sleep(4000);
			waitForElementPresent(phrsc.APPINFO_PILOT_PROJECT, methodName);
			getXpathWebElement(phrsc.APPINFO_PILOT_PROJECT);
			selectText(element,SharePointConst.SHARE_POINT_APP_INFO_PILOT_PROJECT_VALUE);
			
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
	
	
	public  void sharePointProjectResourcesAddFeature(String methodName,SharePointConstantsXml SharePointConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@sharePointProjectResourcesAddFeature::****** executing ****");
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
	public  void sharepointServerConfiguration(String methodName,SharePointConstantsXml SharePointConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@sharepointServerConfiguration::****** executing ****");
		try {
			
			if(methodName=="testsharepointHelloWorldAddConfigurationServer"){
				Thread.sleep(2000);		
				waitForElementPresent(SharePointConst.SHARE_POINT_PROJECT_HELLOWORLD_CHECK_BOX,methodName);
				getXpathWebElement(SharePointConst.SHARE_POINT_PROJECT_HELLOWORLD_CHECK_BOX);
				click();
				
				Thread.sleep(2000);
				waitForElementPresent(SharePointConst.SHARE_POINT_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK,methodName);
	          	getXpathWebElement(SharePointConst.SHARE_POINT_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK);
				click();
			}
			else if(methodName=="testsharepointResourceAddConfigurationServer"){
				Thread.sleep(2000);		
				waitForElementPresent(SharePointConst.SHARE_POINT_PROJECT_RESOURCE_CHECK_BOX,methodName);
				getXpathWebElement(SharePointConst.SHARE_POINT_PROJECT_RESOURCE_CHECK_BOX);
				click();
				
				Thread.sleep(2000);
				waitForElementPresent(SharePointConst.SHARE_POINT_PROJECT_RESOURCE_EDIT_APPLICATION_LINK,methodName);
	          	getXpathWebElement(SharePointConst.SHARE_POINT_PROJECT_RESOURCE_EDIT_APPLICATION_LINK);
				click();
				
			}
			Thread.sleep(4000);
			waitForElementPresent(phrsc.CONFIGURATIONS_TAB, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_TAB);
			click();
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_ADD, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_ADD);
			click();
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_NAME, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_NAME);
			sendKeys(SharePointConst.SHARE_POINT_CONFIGURATION_SERVER_NAME_VALUE);
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DESCRIPTION, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DESCRIPTION);
			sendKeys(SharePointConst.SHARE_POINT_CONFIGURATION_SERVER_DESCRIPTION_VALUE);
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT);
			selectText(element,SharePointConst.SHARE_POINT_CONFIGURATION_SERVER_ENVIRONMENT_VALUE);
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_TYPE, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_TYPE);
			selectText(element,SharePointConst.SHARE_POINT_CONFIGURATION_SERVER_TYPE_VALUE);
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_PROTOCOL, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_PROTOCOL);
			selectText(element,SharePointConst.SHARE_POINT_CONFIGURATION_SERVER_PROTOCOL_HTTP_VALUE);
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_HOST, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_HOST);
			sendKeys(SharePointConst.SHARE_POINT_CONFIGURATION_SERVER_HOST_VALUE);
			
			if(methodName=="testsharepointHelloWorldAddConfigurationServer"){
				Thread.sleep(1000);
				waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_PORT, methodName);
				getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_PORT);
				sendKeys(SharePointConst.SHARE_POINT_CONFIGURATION_SERVER_HW_PORT_VALUE);
			}
			else if(methodName=="testsharepointResourceAddConfigurationServer"){
				Thread.sleep(1000);
				waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_PORT, methodName);
				getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_PORT);
				sendKeys(SharePointConst.SHARE_POINT_CONFIGURATION_SERVER_RESOURCE_PORT_VALUE);
			}
		
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_DEPLOYDIRECTORY, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_DEPLOYDIRECTORY);
			sendKeys(SharePointConst.SHARE_POINT_CONFIGURATION_SERVER_DEPLOY_DIRECTORY_VALUE);
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_CONTEXT, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_CONTEXT);
	    	sendKeys(SharePointConst.SHARE_POINT_CONFIGURATION_SERVER_CONTEXT_VALUE);
							
			Thread.sleep(3000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SAVE, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SAVE);
			click();
			Thread.sleep(6000);
		}

		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	public  void javaStandAloneProjectHelloWorldCreate(String methodName,JavaStandaloneConstantsXml JvaStdAloneConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@javaStandAloneProjectHelloWorldCreate::****** executing ****");
		try {
			Thread.sleep(2000);		
			waitForElementPresent(phrsc.PROJECTS_TAB,methodName);
			getXpathWebElement(phrsc.PROJECTS_TAB);
			click();

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.ADD_APPLICATION_BUTTON,methodName);	
			getXpathWebElement(phrsc.ADD_APPLICATION_BUTTON);
			click();


			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_NAME);
			click();
			sendKeys(JvaStdAloneConst.JAVA_STANDALONE_HELLOWORLD_PROJECT_NAME);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CODE, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CODE);
			click();
			sendKeys(JvaStdAloneConst.JAVA_STANDALONE_HELLOWORLD_PROJECT_CODE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_DESCRIPTION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_DESCRIPTION);
			click();
			sendKeys(JvaStdAloneConst.JAVA_STANDALONE_HELLOWORLD_PROJECT_DESCRIPTION);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_VERSION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_VERSION);
			click();
			sendKeys(JvaStdAloneConst.JAVA_STANDALONE_HELLOWORLD_PROJECT_VERSION);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL);
			click();
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY);
			selectText(element,JvaStdAloneConst.JAVA_STANDALONE_HELLOWORLD_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(35000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	public  void javaStandaloneProjectHelloWorldBuildTab(String methodName,JavaStandaloneConstantsXml JvaStdAloneConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@javaStandaloneProjectHelloWorldBuildTab::****** executing ****");
		try {
			
			
			Thread.sleep(3000);		
			waitForElementPresent(JvaStdAloneConst.JAVA_STANDALONE_HELLOWORLD_CHECKBOX_XPATH,methodName);
			getXpathWebElement(JvaStdAloneConst.JAVA_STANDALONE_HELLOWORLD_CHECKBOX_XPATH);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(JvaStdAloneConst.JAVA_STANDALONE_HELLOWORLD_EDIT_LINK,methodName);
          	getXpathWebElement(JvaStdAloneConst.JAVA_STANDALONE_HELLOWORLD_EDIT_LINK);
			click();
			Thread.sleep(3000);	
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	public  void javaStandaloneProjectHelloWorldEditApplication(String methodName,JavaStandaloneConstantsXml JvaStdAloneConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@javaStandaloneProjectHelloWorldEditApplication::****** executing ****");
		try {
			
			Thread.sleep(2000);	
			waitForElementPresent(JvaStdAloneConst.JAVA_STANDALONE_HELLOWORLD_CHECKBOX_XPATH,methodName);	
			getXpathWebElement(JvaStdAloneConst.JAVA_STANDALONE_HELLOWORLD_CHECKBOX_XPATH);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(JvaStdAloneConst.JAVA_STANDALONE_HELLOWORLD_EDIT_LINK,methodName);
          	getXpathWebElement(JvaStdAloneConst.JAVA_STANDALONE_HELLOWORLD_EDIT_LINK);
			click();	
			
			Thread.sleep(5000);
			waitForElementPresent(phrsc.APPINFO_NEXT, methodName);
			getXpathWebElement(phrsc.APPINFO_NEXT);
			click();			
		
			Thread.sleep(2000);

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	
	
	public  void javaStandaloneProjectHelloWorldAddFeature(String methodName,JavaStandaloneConstantsXml JvaStdAloneConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@javaStandaloneProjectHelloWorldAddFeature::****** executing ****");
		try {
						
			Thread.sleep(3000);
			waitForElementPresent(phrsc.FEATURE_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.FEATURE_FINISH_BUTTON);
			click();
			Thread.sleep(50000);

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	
	public  void javaWebserviceProjectHelloWorldCreate(String methodName,JavaWebServiceConstantsXml JvaWebServiceConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@javaWebserviceProjectHelloWorldCreate::****** executing ****");
		try {
			Thread.sleep(2000);		
			waitForElementPresent(phrsc.PROJECTS_TAB,methodName);
			getXpathWebElement(phrsc.PROJECTS_TAB);
			click();

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.ADD_APPLICATION_BUTTON,methodName);	
			getXpathWebElement(phrsc.ADD_APPLICATION_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_NAME);
			click();
			sendKeys(JvaWebServiceConst.JAVAWEB_SERVICE_HELLOWORLD_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CODE, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CODE);
			click();
			sendKeys(JvaWebServiceConst.JAVAWEB_SERVICE_HELLOWORLD_CODE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_DESCRIPTION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_DESCRIPTION);
			click();
			sendKeys(JvaWebServiceConst.JAVAWEB_SERVICE_HELLOWORLD_DESCRIPTION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_VERSION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_VERSION);
			click();
			sendKeys(JvaWebServiceConst.JAVAWEB_SERVICE_HELLOWORLD_VERSION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY);
			selectText(element,JvaWebServiceConst.JAVAWEB_SERVICE_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(35000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	
	public  void javaWebserviceProjectHelloWorldEditApplication(String methodName,JavaWebServiceConstantsXml JvaWebServiceConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@javaWebserviceProjectHelloWorldEditApplication::****** executing ****");
		try {
			
			
			Thread.sleep(2000);	
			waitForElementPresent(JvaWebServiceConst.JAVAWEB_SERVICE_PROJECT_HELLOWORLD_CHECK_BOX,methodName);	
			getXpathWebElement(JvaWebServiceConst.JAVAWEB_SERVICE_PROJECT_HELLOWORLD_CHECK_BOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(JvaWebServiceConst.JAVAWEB_SERVICE_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK,methodName);
          	getXpathWebElement(JvaWebServiceConst.JAVAWEB_SERVICE_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK);
			click();
			
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPINFO_SERVER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.APPINFO_SERVER_CHECKBOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPINFO_SERVER_SELECT, methodName);
			getXpathWebElement(phrsc.APPINFO_SERVER_SELECT);
			selectText(element, JvaWebServiceConst.JAVAWEB_SERVICE_SERVER_VALUE);
			
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
			selectText(element, JvaWebServiceConst.JAVAWEB_SERVICE_DATABASE_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPINFO_DATABASEVERSION, methodName);
			getXpathWebElement(phrsc.APPINFO_DATABASEVERSION);
			click();
			
			Thread.sleep(5000);
			waitForElementPresent(phrsc.APPINFO_NEXT, methodName);
			getXpathWebElement(phrsc.APPINFO_NEXT);
			click();
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	
	public  void javaWebserviceProjectHelloWorldAddFeature(String methodName,JavaWebServiceConstantsXml JvaWebServiceConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@javaWebserviceProjectHelloWorldAddFeature::****** executing ****");
		try {
			Thread.sleep(3000);
			waitForElementPresent(phrsc.FEATURE_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.FEATURE_FINISH_BUTTON);
			click();
			Thread.sleep(50000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	public  void javaWebserviceAddConfigurationServer(String methodName,JavaWebServiceConstantsXml JvaWebServiceConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@javaWebserviceAddConfigurationServer****** executing ****");
		try {
			
			if ( methodName == "testjavaWebServiceHelloWorldAddServerConfiguration"){
				Thread.sleep(5000);	
				waitForElementPresent(JvaWebServiceConst.JAVAWEB_SERVICE_PROJECT_HELLOWORLD_CHECK_BOX,methodName);	
				getXpathWebElement(JvaWebServiceConst.JAVAWEB_SERVICE_PROJECT_HELLOWORLD_CHECK_BOX);
				click();
				
				Thread.sleep(3000);
				waitForElementPresent(JvaWebServiceConst.JAVAWEB_SERVICE_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK,methodName);
	          	getXpathWebElement(JvaWebServiceConst.JAVAWEB_SERVICE_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK);
				click();
				

			}
			else if( methodName=="testjavaWebServiceEshopAddServerConfiguration"){
				Thread.sleep(4000);	
				waitForElementPresent(JvaWebServiceConst.JAVAWEB_SERVICE_PROJECT_ESHOP_CHECK_BOX,methodName);	
				getXpathWebElement(JvaWebServiceConst.JAVAWEB_SERVICE_PROJECT_ESHOP_CHECK_BOX);
				click();
				
				Thread.sleep(2000);
				waitForElementPresent(JvaWebServiceConst.JAVAWEB_SERVICE_PROJECT_ESHOP_EDIT_APPLICATION_LINK,methodName);
	          	getXpathWebElement(JvaWebServiceConst.JAVAWEB_SERVICE_PROJECT_ESHOP_EDIT_APPLICATION_LINK);
				click();
				
			}
			Thread.sleep(2000);
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
			sendKeys(JvaWebServiceConst.JAVAWEB_SERVICE_CONFIGURATION_SERVER_NAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DESCRIPTION,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DESCRIPTION);
			click();
			sendKeys(JvaWebServiceConst.JAVAWEB_SERVICE_CONFIGURATION_SERVER_DESCRIPTION_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT);
			click();
			selectText(element, JvaWebServiceConst.JAVAWEB_SERVICE_CONFIGURATION_SERVER_ENVIRONMENT_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_TYPE);
			selectText(element,JvaWebServiceConst.JAVAWEB_SERVICE_CONFIGURATION_SERVER_TYPE_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_PROTOCOL);
			selectText(element, JvaWebServiceConst.JAVAWEB_SERVICE_CONFIGURATION_SERVER_PROTOCOL_HTTP_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_HOST,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_HOST);
			click();
			sendKeys(JvaWebServiceConst.JAVAWEB_SERVICE_CONFIGURATION_SERVER_HOST_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_PORT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_PORT);
			click();
			sendKeys(JvaWebServiceConst.JAVAWEB_SERVICE_CONFIGURATION_SERVER_PORT_VALUE);
			
		
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_DEPLOYDIRECTORY,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_DEPLOYDIRECTORY);
			click();
			sendKeys(JvaWebServiceConst.JAVAWEB_SERVICE_CONFIGURATION_SERVER_DEPLOY_DIRECTORY_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_CONTEXT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_CONTEXT);
			click();
			
			if(methodName=="testjavaWebServiceHelloWorldAddServerConfiguration"){
				sendKeys(JvaWebServiceConst.JAVAWEB_SERVICE_CONFIGURATION_SERVER_CONTEXT_VALUE_HELLOWORLD);
				Thread.sleep(2000);
			
			}
			else if( methodName=="testjavaWebServiceEshopAddServerConfiguration"){
				sendKeys(JvaWebServiceConst.JAVAWEB_SERVICE_CONFIGURATION_SERVER_CONTEXT_VALUE_ESHOP);
				Thread.sleep(2000);
			}

			waitForElementPresent(phrsc.CONFIGURATIONS_SAVE,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SAVE);
			click();
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	public  void javaWebserviceAddConfigurationDatabase(String methodName,JavaWebServiceConstantsXml JvaWebServiceConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@javaWebserviceAddConfigurationDatabase::****** executing ****");
		try {
						
			Thread.sleep(2000);
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
			sendKeys(JvaWebServiceConst.JAVAWEB_SERVICE_CONFIGURATION_DATABASE_NAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DESCRIPTION,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DESCRIPTION);
			click();
			clear();
			sendKeys(JvaWebServiceConst.JAVAWEB_SERVICE_CONFIGURATION_DATABASE_DESCRIPTION_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT);
			click();
			selectText(element, JvaWebServiceConst.JAVAWEB_SERVICE_CONFIGURATION_DATABASE_ENVIRONMENT_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_TYPE);
			selectText(element,JvaWebServiceConst.JAVAWEB_SERVICE_CONFIGURATION_DATABASE_TYPE_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_HOST,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_HOST);
			click();
			clear();
			sendKeys(JvaWebServiceConst.JAVAWEB_SERVICE_CONFIGURATION_DATABASE_HOST_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_PORT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_PORT);
			click();
			clear();
			sendKeys(JvaWebServiceConst.JAVAWEB_SERVICE_CONFIGURATION_DATABASE_PORT_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_USERNAME,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_USERNAME);
			click();
			clear();
			sendKeys(JvaWebServiceConst.JAVAWEB_SERVICE_CONFIGURATION_DATABASE_DB_USERNAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_DBNAME,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_DBNAME);
			click();
			sendKeys(JvaWebServiceConst.JAVAWEB_SERVICE_CONFIGURATION_DATABASE_DB_NAME_VALUE_ESHOP);
			
			Thread.sleep(2000);			
			waitForElementPresent(phrsc.CONFIGURATIONS_SAVE,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SAVE);
			click();
			Thread.sleep(5000);
			

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	public  void javaWebserviceProjectEshopCreate(String methodName,JavaWebServiceConstantsXml JvaWebServiceConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@javaWebserviceProjectEshopCreate::****** executing ****");
		try {
			Thread.sleep(20000);		
			waitForElementPresent(phrsc.PROJECTS_TAB,methodName);
			getXpathWebElement(phrsc.PROJECTS_TAB);
			click();

			Thread.sleep(2000);	
			waitForElementPresent(phrsc.ADD_APPLICATION_BUTTON,methodName);	
			getXpathWebElement(phrsc.ADD_APPLICATION_BUTTON);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_NAME);
			click();
			sendKeys(JvaWebServiceConst.JAVAWEB_SERVICE_ESHOP_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CODE, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CODE);
			click();
			sendKeys(JvaWebServiceConst.JAVAWEB_SERVICE_ESHOP_CODE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_DESCRIPTION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_DESCRIPTION);
			click();
			sendKeys(JvaWebServiceConst.JAVAWEB_SERVICE_ESHOP_DESCRIPTION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_VERSION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_VERSION);
			click();
			sendKeys(JvaWebServiceConst.JAVAWEB_SERVICE_ESHOP_VERSION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL);
			click();

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY);
			selectText(element,JvaWebServiceConst.JAVAWEB_SERVICE_TECHNOLGY_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(60000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	
	public  void javaWebserviceProjectEshopEditApplication(String methodName,JavaWebServiceConstantsXml JvaWebServiceConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@javaWebserviceProjectEshopEditApplication::****** executing ****");
		try {
			
			Thread.sleep(4000);	
			waitForElementPresent(JvaWebServiceConst.JAVAWEB_SERVICE_PROJECT_ESHOP_CHECK_BOX,methodName);	
			getXpathWebElement(JvaWebServiceConst.JAVAWEB_SERVICE_PROJECT_ESHOP_CHECK_BOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(JvaWebServiceConst.JAVAWEB_SERVICE_PROJECT_ESHOP_EDIT_APPLICATION_LINK,methodName);
          	getXpathWebElement(JvaWebServiceConst.JAVAWEB_SERVICE_PROJECT_ESHOP_EDIT_APPLICATION_LINK);
			click();
			
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPINFO_PILOT_PROJECT, methodName);
			getXpathWebElement(phrsc.APPINFO_PILOT_PROJECT);
			selectText(element,JvaWebServiceConst.JAVAWEB_SERVICE_APP_INFO_PILOT_PROJECT_ESHOP_VALUE);
			
			Thread.sleep(6000);
			waitForElementPresent(phrsc.APPINFO_NEXT, methodName);
			getXpathWebElement(phrsc.APPINFO_NEXT);
			click();			
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	public  void javaWebserviceProjectEshopAddFeature(String methodName,JavaWebServiceConstantsXml JvaWebServiceConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@javaWebserviceProjectEshopAddFeature::****** executing ****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.FEATURE_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.FEATURE_FINISH_BUTTON);
			click();
			Thread.sleep(90000);
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
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.BUILD_SKIP_UNIT_TEST, methodName);
			getXpathWebElement(phrsc.BUILD_SKIP_UNIT_TEST);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.BUILD_BUTTON, methodName);
			getXpathWebElement(phrsc.BUILD_BUTTON);
			click();
			successFailureLoop();
			
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	
		
	public  void appLayerDeploy(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@appLayerDeploy::******executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.DEPLOY_BTN, methodName);
			getXpathWebElement(phrsc.DEPLOY_BTN);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.SELECT_SQL, methodName);
			getXpathWebElement(phrsc.SELECT_SQL);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.DEPLOY_OK, methodName);
			getXpathWebElement(phrsc.DEPLOY_OK);
			click();
			Thread.sleep(20000);
			successFailureLoop();
	
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	
	public  void appLayerBuildTabClone(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@appLayerBuildTabClone::****** executing ****");
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
			
		/*	Thread.sleep(2000);
			waitForElementPresent(phrsc.SELECT_ENVIRONMENT, methodName);
			getXpathWebElement(phrsc.SELECT_ENVIRONMENT);
			click();*/
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.SELECT_SECOND_ENVIRONMENT, methodName);
			getXpathWebElement(phrsc.SELECT_SECOND_ENVIRONMENT);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.BUILD_BUTTON, methodName);
			getXpathWebElement(phrsc.BUILD_BUTTON);
			click();
			successFailureLoop();
			
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	
		
	public  void appLayerDeployClone(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@appLayerDeployClone::******executing Deploy****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.DEPLOY_BTN, methodName);
			getXpathWebElement(phrsc.DEPLOY_BTN);
			click();
			
		/*	Thread.sleep(2000);
			waitForElementPresent(phrsc.SELECT_SQL, methodName);
			getXpathWebElement(phrsc.SELECT_SQL);
			click();*/
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.EXECUTE_SQL, methodName);
			getXpathWebElement(phrsc.EXECUTE_SQL);
			click();
			
			Thread.sleep(5000);
			waitForElementPresent(phrsc.DEPLOY_OK, methodName);
			getXpathWebElement(phrsc.DEPLOY_OK);
			click();
			Thread.sleep(20000);
			successFailureLoop();
	
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	
	public  void appLayerDeploy1(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@appLayerDeploy1::******executing ****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.DEPLOY_BTN, methodName);
			getXpathWebElement(phrsc.DEPLOY_BTN);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.DEPLOY_OK, methodName);
			getXpathWebElement(phrsc.DEPLOY_OK);
			click();
			Thread.sleep(20000);
			successFailureLoop();
	
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	public  void aspDotNetServerConfiguration(String methodName,AspDotNetConstantsXml AspDotNetConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@aspDotNetServerConfiguration::****** executing ****");
		try {
			Thread.sleep(2000);	
			waitForElementPresent(AspDotNetConst.ASPDOTNET_PROJECT_HELLOWORLD_CHECK_BOX,methodName);	
			getXpathWebElement(AspDotNetConst.ASPDOTNET_PROJECT_HELLOWORLD_CHECK_BOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(AspDotNetConst.ASPDOTNET_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK,methodName);
          	getXpathWebElement(AspDotNetConst.ASPDOTNET_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK);
			click();
			
			Thread.sleep(3000);
			waitForElementPresent(phrsc.CONFIGURATIONS_TAB, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_TAB);
			click();
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_ADD, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_ADD);
			click();
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_NAME, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_NAME);
			sendKeys(AspDotNetConst.ASPDOTNET_SERVER_NAME);
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DESCRIPTION, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DESCRIPTION);
			sendKeys(AspDotNetConst.ASPDOTNET_SERVER_DISCRIPTION);
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT);
			selectText(element,AspDotNetConst.ASPDOTNET_SERVER_ENVIRONMENT);
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_TYPE, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_TYPE);
			selectText(element,AspDotNetConst.ASPDOTNET_SERVER_TYPE);
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_PROTOCOL, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_PROTOCOL);
			selectText(element,AspDotNetConst.ASPDOTNET_SERVER_PROTOCOL);
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_HOST, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_HOST);
			sendKeys(AspDotNetConst.ASPDOTNET_SERVER_HOST);
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_PORT, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_PORT);
			sendKeys(AspDotNetConst.ASPDOTNET_SERVER_PORT);
		
			Thread.sleep(1000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_DEPLOYDIRECTORY, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_DEPLOYDIRECTORY);
			sendKeys(AspDotNetConst.ASPDOTNET_SERVER_DEPLOY_DIR);
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.ASPDOTNET_SITE_NAME, methodName);
			getXpathWebElement(phrsc.ASPDOTNET_SITE_NAME);
			sendKeys(AspDotNetConst.ASPDOTNET_SERVER_SITENAME);
			
			Thread.sleep(1000);
			waitForElementPresent(phrsc.ASPDOTNET_APP_NAME, methodName);
			getXpathWebElement(phrsc.ASPDOTNET_APP_NAME);
			sendKeys(AspDotNetConst.ASPDOTNET_SERVER_APPNAME);
			
			Thread.sleep(3000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SAVE, methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SAVE);
			click();
			Thread.sleep(6000);
			
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	public  void NodeJsWebserviceProjectHelloWorldCreate(String methodName,NodeJsWebServiceConstantsXml nodejs) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@testNodeJsWebserviceProjectHelloWorldCreate::******executing NodeJsWebserviceProjectHelloWorldCreate scenario****");
		try {
			Thread.sleep(2000);		
			waitForElementPresent(phrsc.PROJECTS_TAB,methodName);
			getXpathWebElement(phrsc.PROJECTS_TAB);
			click();

			Thread.sleep(2000);		
			waitForElementPresent(phrsc.ADD_APPLICATION_BUTTON,methodName);
			getXpathWebElement(phrsc.ADD_APPLICATION_BUTTON);
			click();


			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_NAME);
			click();
			sendKeys(nodejs.NODEJS_SERVICE_HELLOWORLD_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CODE, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CODE);
			click();
			sendKeys(nodejs.NODEJS_SERVICE_HELLOWORLD_CODE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_DESCRIPTION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_DESCRIPTION);
			click();
			sendKeys(nodejs.NODEJS_SERVICE_HELLOWORLD_DESCRIPTION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_VERSION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_VERSION);
			click();
			sendKeys(nodejs.NODEJS_SERVICE_HELLOWORLD_VERSION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY);
			selectText(element,nodejs.NODEJS_SERVICE_TECHNOLGY_VALUE);


			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(50000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	public  void NodeJsWebserviceProjectHelloWorldEditApplication(String methodName,NodeJsWebServiceConstantsXml nodejs) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@NodeJsWebserviceProjectHelloWorldEditApplication::****** executing ****");
		try {
			
			
			Thread.sleep(2000);	
			waitForElementPresent(nodejs.NODEJS_SERVICE_PROJECT_HELLOWORLD_CHECK_BOX,methodName);	
			getXpathWebElement(nodejs.NODEJS_SERVICE_PROJECT_HELLOWORLD_CHECK_BOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(nodejs.NODEJS_SERVICE_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK,methodName);
          	getXpathWebElement(nodejs.NODEJS_SERVICE_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK);
			click();
			
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPINFO_SERVER_CHECKBOX, methodName);
			getXpathWebElement(phrsc.APPINFO_SERVER_CHECKBOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPINFO_SERVER_SELECT, methodName);
			getXpathWebElement(phrsc.APPINFO_SERVER_SELECT);
			selectText(element, nodejs.NODEJS_SERVICE_SERVER_VALUE);
			
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
			selectText(element, nodejs.NODEJS_SERVICE_DATABASE_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPINFO_DATABASEVERSION, methodName);
			getXpathWebElement(phrsc.APPINFO_DATABASEVERSION);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPINFO_NEXT, methodName);
			getXpathWebElement(phrsc.APPINFO_NEXT);
			click();
			
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	public  void NodeJsWebserviceProjectHelloWorldAddFeature(String methodName,NodeJsWebServiceConstantsXml nodejs) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@NodeJsWebserviceProjectHelloWorldAddFeature::****** executing ****");
		try {
			
			
			Thread.sleep(5000);
			waitForElementPresent(phrsc.FEATURE_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.FEATURE_FINISH_BUTTON);
			click();
			Thread.sleep(50000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	public  void NodeJsWebserviceProjectEshopCreate(String methodName,NodeJsWebServiceConstantsXml nodejs) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@NodeJsWebserviceProjectEshopCreate::****** executing ****");
		try {
			Thread.sleep(2000);		
			waitForElementPresent(phrsc.PROJECTS_TAB,methodName);
			getXpathWebElement(phrsc.PROJECTS_TAB);
			click();

			Thread.sleep(2000);		
			waitForElementPresent(phrsc.ADD_APPLICATION_BUTTON,methodName);
			getXpathWebElement(phrsc.ADD_APPLICATION_BUTTON);
			click();


			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_NAME, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_NAME);
			click();
			sendKeys(nodejs.NODEJS_SERVICE_ESHOP_NAME_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CODE, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CODE);
			click();
			sendKeys(nodejs.NODEJS_SERVICE_ESHOP_CODE_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_DESCRIPTION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_DESCRIPTION);
			click();
			sendKeys(nodejs.NODEJS_SERVICE_ESHOP_DESCRIPTION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_VERSION, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_VERSION);
			click();
			sendKeys(nodejs.NODEJS_SERVICE_ESHOP_VERSION_VALUE);

			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_CHECKBOX_ALL);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_APPLICATION_LAYER_TECHNOLOGY);
			selectText(element,nodejs.NODEJS_SERVICE_TECHNOLGY_VALUE);


			Thread.sleep(2000);
			waitForElementPresent(phrsc.ADDPROJECT_CREATE_BUTTON, methodName);
			getXpathWebElement(phrsc.ADDPROJECT_CREATE_BUTTON);
			click();
			Thread.sleep(50000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	public  void NodeJsWebserviceProjectEshopEditApplication(String methodName,NodeJsWebServiceConstantsXml nodejs) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@NodeJsWebserviceProjectEshopEditApplication::****** executing ****");
		try {
			
			
			Thread.sleep(2000);	
			waitForElementPresent(nodejs.NODEJS_SERVICE_PROJECT_ESHOP_CHECK_BOX,methodName);	
			getXpathWebElement(nodejs.NODEJS_SERVICE_PROJECT_ESHOP_CHECK_BOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(nodejs.NODEJS_SERVICE_PROJECT_ESHOP_EDIT_APPLICATION_LINK,methodName);
          	getXpathWebElement(nodejs.NODEJS_SERVICE_PROJECT_ESHOP_EDIT_APPLICATION_LINK);
			click();
						
			Thread.sleep(5000);
			waitForElementPresent(phrsc.APPINFO_PILOT_PROJECT, methodName);
			getXpathWebElement(phrsc.APPINFO_PILOT_PROJECT);
			selectText(element, nodejs.NODEJS_SERVICE_APP_INFO_PILOT_PROJECT_ESHOP_VALUE);
			
			Thread.sleep(5000);
			waitForElementPresent(phrsc.APPINFO_NEXT, methodName);
			getXpathWebElement(phrsc.APPINFO_NEXT);
			click();					
			Thread.sleep(5000);
		
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	public  void NodeJsWebserviceProjectEshopAddFeature(String methodName,NodeJsWebServiceConstantsXml nodejs) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@NodeJsWebserviceProjectEshopAddFeature::****** executing ****");
		try {
			
			Thread.sleep(5000);
			waitForElementPresent(phrsc.FEATURE_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.FEATURE_FINISH_BUTTON);
			click();
			Thread.sleep(110000);
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	public  void NodeJsAddConfigurationServer(String methodName,NodeJsWebServiceConstantsXml nodejs) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@NodeJsAddConfigurationServer::****** executing ****");
		try {
			
			if(methodName=="testNodeJsHelloWorldProjectConfigurationServer"){
			Thread.sleep(2000);
			waitForElementPresent(nodejs.NODEJS_SERVICE_PROJECT_HELLOWORLD_CHECK_BOX,methodName);
			getXpathWebElement(nodejs.NODEJS_SERVICE_PROJECT_HELLOWORLD_CHECK_BOX);
			click();
			
			Thread.sleep(2000);			
			waitForElementPresent(nodejs.NODEJS_SERVICE_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK,methodName);
			getXpathWebElement(nodejs.NODEJS_SERVICE_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK);
			click();
			Thread.sleep(2000);	
			}		
			else
			{
				Thread.sleep(2000);	
				waitForElementPresent(nodejs.NODEJS_SERVICE_PROJECT_ESHOP_CHECK_BOX,methodName);	
				getXpathWebElement(nodejs.NODEJS_SERVICE_PROJECT_ESHOP_CHECK_BOX);
				click();
				
				Thread.sleep(2000);
				waitForElementPresent(nodejs.NODEJS_SERVICE_PROJECT_ESHOP_EDIT_APPLICATION_LINK,methodName);
	          	getXpathWebElement(nodejs.NODEJS_SERVICE_PROJECT_ESHOP_EDIT_APPLICATION_LINK);
				click();
			}
			
			Thread.sleep(2000);
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
			sendKeys(nodejs.NODEJS_SERVICE_CONFIGURATION_SERVER_NAME_VALUE);
			Thread.sleep(2000);

			waitForElementPresent(phrsc.CONFIGURATIONS_DESCRIPTION,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DESCRIPTION);
			click();
			sendKeys(nodejs.NODEJS_SERVICE_CONFIGURATION_SERVER_DESCRIPTION_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT);
			click();
			selectText(element, nodejs.NODEJS_SERVICE_CONFIGURATION_SERVER_ENVIRONMENT_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_TYPE);
			selectText(element,nodejs.NODEJS_SERVICE_CONFIGURATION_SERVER_TYPE_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_PROTOCOL);
			selectText(element, nodejs.NODEJS_SERVICE_CONFIGURATION_SERVER_PROTOCOL_HTTP_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_HOST,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_HOST);
			click();
			sendKeys(nodejs.NODEJS_SERVICE_CONFIGURATION_SERVER_HOST_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_PORT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_PORT);
			click();
			sendKeys(nodejs.NODEJS_SERVICE_CONFIGURATION_SERVER_PORT_VALUE);

			Thread.sleep(2000);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_CONTEXT);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_CONTEXT,methodName);
			click();
			if(methodName=="testNodeJsHelloWorldProjectConfigurationServer"){				
				sendKeys(nodejs.NODEJS_SERVICE_CONFIGURATION_SERVER_CONTEXT_VALUE_HELLOWORLD);
				Thread.sleep(2000);
			}else{
				sendKeys(nodejs.NODEJS_SERVICE_CONFIGURATION_SERVER_CONTEXT_VALUE_ESHOP);
				Thread.sleep(2000);
			}
			waitForElementPresent(phrsc.CONFIGURATIONS_SAVE,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SAVE);
			click();
			Thread.sleep(3000);



		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	

	public  void NodeJsAddConfigurationDatabase(String methodName,NodeJsWebServiceConstantsXml nodejs) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@NodeJsAddConfigurationDatabase::****** executing ****");
		try {
						
			Thread.sleep(2000);
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
			sendKeys(nodejs.NODEJS_SERVICE_CONFIGURATION_DATABASE_NAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DESCRIPTION,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DESCRIPTION);
			click();
			sendKeys(nodejs.NODEJS_SERVICE_CONFIGURATION_DATABASE_DESCRIPTION_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT);
			click();
			selectText(element, nodejs.NODEJS_SERVICE_CONFIGURATION_DATABASE_ENVIRONMENT_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_TYPE,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_TYPE);
			selectText(element,nodejs.NODEJS_SERVICE_CONFIGURATION_DATABASE_TYPE_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_HOST,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_HOST);
			click();
			sendKeys(nodejs.NODEJS_SERVICE_CONFIGURATION_DATABASE_HOST_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_PORT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_PORT);
			click();
			sendKeys(nodejs.NODEJS_SERVICE_CONFIGURATION_DATABASE_PORT_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_USERNAME,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_USERNAME);
			click();
			sendKeys(nodejs.NODEJS_SERVICE_CONFIGURATION_DATABASE_DB_USERNAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DATABASE_DBNAME,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DATABASE_DBNAME);
			click();	
			
			if(methodName=="testNodeJsHelloworldDatabase")
			{
			sendKeys(nodejs.NODEJS_SERVICE_CONFIGURATION_DATABASE_DB_NAME_HELLOWORLD);
			}
			
			else if (methodName=="testNodeJsEshopDatabase")
			{
			sendKeys(nodejs.NODEJS_SERVICE_CONFIGURATION_DATABASE_DB_NAME_VALUE_ESHOP);
			}
			
			Thread.sleep(2000);			
			waitForElementPresent(phrsc.CONFIGURATIONS_SAVE,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SAVE);
			click();
			Thread.sleep(5000);
			log.info("success");

		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	
	public  void appLayerRunAgainstSource(String methodName) throws Exception {
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
			
			Thread.sleep(3000);
			waitForElementPresent(phrsc.NODEJS_RUNAGAINSTSOURCE, methodName);
			getXpathWebElement(phrsc.NODEJS_RUNAGAINSTSOURCE);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.SELECT_SQL, methodName);
			getXpathWebElement(phrsc.SELECT_SQL);
			click();
			
			Thread.sleep(5000);
			waitForElementPresent(phrsc.NODEJS_RUN, methodName);
			getXpathWebElement(phrsc.NODEJS_RUN);
			click();
			Thread.sleep(70000);
			RunAganstSource();
		}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	
//Videos Tab Verification
	
	
	public  void PhrescoVideos(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("****** @VerifyingPhrescoVideos ****");
		try {
			Thread.sleep(2000);	
			waitForElementPresent(phrsc.PHRESCO_VIDEOS,methodName);	
			getXpathWebElement(phrsc.PHRESCO_VIDEOS);
			click();
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PLAY_VIDEOS,methodName);	
			getXpathWebElement(phrsc.PLAY_VIDEOS);
			click();
			Thread.sleep(5000);
			
			waitForElementPresent(phrsc.SHAREPOINT_PROJECT_CREATION_VIDEOS,methodName);
			getXpathWebElement(phrsc.SHAREPOINT_PROJECT_CREATION_VIDEOS);
			click();
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PLAY_VIDEOS,methodName);	
			getXpathWebElement(phrsc.PLAY_VIDEOS);
			click();
			
			Thread.sleep(5000);
			waitForElementPresent(phrsc.SHAREPOINT_PROJECT_DEPLOY_VIDEOS,methodName);
			getXpathWebElement(phrsc.SHAREPOINT_PROJECT_DEPLOY_VIDEOS);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PLAY_VIDEOS,methodName);	
			getXpathWebElement(phrsc.PLAY_VIDEOS);
			click();
			Thread.sleep(2000);
			
			MouseOverEvents(phrsc.USER_IMAGE_ARROW);
			Thread.sleep(2000);
			
			waitForElementPresent(phrsc.ABOUT_PHRESCO,methodName);	
			getXpathWebElement(phrsc.ABOUT_PHRESCO);
			click();
			Thread.sleep(5000);
			
			ButtonEnabled(phrsc.UPDATEAPPLICATION);
			waitForElementPresent(phrsc.UPDATEAPPLICATION,methodName);
			getXpathWebElement(phrsc.UPDATEAPPLICATION);
			
			waitForElementPresent(phrsc.CANCELBUTTON,methodName);
			getXpathWebElement(phrsc.CANCELBUTTON);
			click();
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}

	
//App layer Unit Test
	
	public  void UnittestVerification(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("**UnitTestVerification****");
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
			waitForElementPresent(phrsc.UNITTESTTAP, methodName);
			getXpathWebElement(phrsc.UNITTESTTAP);
			click();
			
			successFailureLoop();
			
			waitForElementPresent(phrsc.UNIT_TEST_POPUP_OK, methodName);
			getXpathWebElement(phrsc.UNIT_TEST_POPUP_OK);
			click();
			Thread.sleep(5000);
			
			
	}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}


	//Performance Test Server Starts Here 
	
	public  void PerformanceTestForAganistServer(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("**PerformanceTestAganistServerVerification****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.QUALITYBTN, methodName);
			getXpathWebElement(phrsc.QUALITYBTN);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PERFORMANCETESTBTN, methodName);
			getXpathWebElement(phrsc.PERFORMANCETESTBTN);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PERFORMANCETESTTAP, methodName);
			getXpathWebElement(phrsc.PERFORMANCETESTTAP);
			click();
			
			Thread.sleep(10000);
			waitForElementPresent(phrsc.TESTAGANIST, methodName);
			getXpathWebElement(phrsc.TESTAGANIST);
			click();
			sendKeys(phrsc.TESTAGANIST_VALUE);
			
			waitForElementPresent(phrsc.SHOWSETTINGS_CHECKBOX, methodName);
			getXpathWebElement(phrsc.SHOWSETTINGS_CHECKBOX);
			click();
			
			waitForElementPresent(phrsc.CONFIGURATION, methodName);
			getXpathWebElement(phrsc.CONFIGURATION);
			click(); 
			sendKeys(phrsc.CONFIGURATION_VALUE1);
			
			waitForElementPresent(phrsc.TESTRESULT_NAME, methodName);
			getXpathWebElement(phrsc.TESTRESULT_NAME);
			click();
			sendKeys(phrsc.TESTRESULT_NAME_VALUE);
		
			waitForElementPresent(phrsc.NO_OF_USERS, methodName);
			getXpathWebElement(phrsc.NO_OF_USERS).clear();
			click();
			sendKeys(phrsc.NO_OF_USERS_VALUE);
			
			waitForElementPresent(phrsc.RAMP_UP_PERIOD, methodName);
			getXpathWebElement(phrsc.RAMP_UP_PERIOD).clear();
			click();
			sendKeys(phrsc.RAMP_UP_PERIOD_VALUE);
			
			waitForElementPresent(phrsc.LOOP_COUNT, methodName);
			getXpathWebElement(phrsc.LOOP_COUNT).clear();
			click();
			sendKeys(phrsc.LOOP_COUNT_VALUE);
			
			Thread.sleep(2000);
			
			waitForElementPresent(phrsc.TESTTAB_CLICK, methodName);
			getXpathWebElement(phrsc.TESTTAB_CLICK);
			click();
		    Thread.sleep(2000);
		    
			successFailureLoop();
			
			waitForElementPresent(phrsc.PERFORMANCE_POP_UP_CLOSE, methodName);
			getXpathWebElement(phrsc.PERFORMANCE_POP_UP_CLOSE);
			click();
			
			Thread.sleep(3000);
			
			
	}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	// Load Test for Application Layer
	
	public  void LoadTestForAganistServer(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("**LoadTestForAganistServer****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.QUALITYBTN, methodName);
			getXpathWebElement(phrsc.QUALITYBTN);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.LOADTESTBTN, methodName);
			getXpathWebElement(phrsc.LOADTESTBTN);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PERFORMANCETESTTAP, methodName);
			getXpathWebElement(phrsc.PERFORMANCETESTTAP);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.TESTAGANIST, methodName);
			getXpathWebElement(phrsc.TESTAGANIST);
			click();
			sendKeys(phrsc.TESTAGANIST_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.TESTRESULT_NAME_CLICK, methodName);
			getXpathWebElement(phrsc.TESTRESULT_NAME_CLICK).clear();
			click();
			sendKeys(phrsc.TESTRESULT_NAME_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.NO_OF_USERS, methodName);
			getXpathWebElement(phrsc.NO_OF_USERS).clear();
			click();
			sendKeys(phrsc.NO_OF_USERS_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.RAMP_UP_PERIOD, methodName);
			getXpathWebElement(phrsc.RAMP_UP_PERIOD).clear();
			click();
			sendKeys(phrsc.RAMP_UP_PERIOD_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.LOOP_COUNT, methodName);
			getXpathWebElement(phrsc.LOOP_COUNT).clear();
			click();
			sendKeys(phrsc.LOOP_COUNT_VALUE);
			
			/*Thread.sleep(5000);
			
			waitForElementPresent(phrsc.OPTION_DROP, methodName);
			getXpathWebElement(phrsc.OPTION_DROP);
			click();
			*/
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.KEY_CLICK, methodName);
			getXpathWebElement(phrsc.KEY_CLICK).clear();
			click();
			sendKeys(phrsc.KEY_VALUE);
			
			Thread.sleep(2000);			
			waitForElementPresent(phrsc.VALUE_FIELD_CLICK, methodName);
			getXpathWebElement(phrsc.VALUE_FIELD_CLICK).clear();
			click();
			sendKeys(phrsc.VALUE_PASS);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.TESTTAB_CLICK, methodName);
			getXpathWebElement(phrsc.TESTTAB_CLICK);
			click();
		    Thread.sleep(2000);
		    
			successFailureLoop();
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PERFORMANCE_POP_UP_CLOSE, methodName);
			getXpathWebElement(phrsc.PERFORMANCE_POP_UP_CLOSE);
			click();
			
			Thread.sleep(3000);
			
			
	}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}

	// performance for database
	
	public  void PerformanceTestForAganistDatabase(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("**PerformanceTestAganistDatabaseVerification****");
		try {
			Thread.sleep(2000);
			waitForElementPresent(phrsc.QUALITYBTN, methodName);
			getXpathWebElement(phrsc.QUALITYBTN);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PERFORMANCETESTBTN, methodName);
			getXpathWebElement(phrsc.PERFORMANCETESTBTN);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PERFORMANCETESTTAP, methodName);
			getXpathWebElement(phrsc.PERFORMANCETESTTAP);
			click();
			
			Thread.sleep(5000);
			waitForElementPresent(phrsc.TESTAGANIST, methodName);
			getXpathWebElement(phrsc.TESTAGANIST);
			click();
			sendKeys(phrsc.TESTAGANIST_VALUE1);
			
			
			waitForElementPresent(phrsc.SHOWSETTINGS_CHECKBOX, methodName);
			getXpathWebElement(phrsc.SHOWSETTINGS_CHECKBOX);
			click();
			
			
			waitForElementPresent(phrsc.TESTRESULT_NAME, methodName);
			getXpathWebElement(phrsc.TESTRESULT_NAME);
			click();
			sendKeys(phrsc.TESTRESULT_NAME_VALUE);
			
			
			waitForElementPresent(phrsc.NO_OF_USERS, methodName);
			getXpathWebElement(phrsc.NO_OF_USERS).clear();
			click();
			sendKeys(phrsc.NO_OF_USERS_VALUE);
			
			
			waitForElementPresent(phrsc.RAMP_UP_PERIOD, methodName);
			getXpathWebElement(phrsc.RAMP_UP_PERIOD).clear();
			click();
			sendKeys(phrsc.RAMP_UP_PERIOD_VALUE);
			
			
			waitForElementPresent(phrsc.LOOP_COUNT, methodName);
			getXpathWebElement(phrsc.LOOP_COUNT).clear();
			click();
			sendKeys(phrsc.LOOP_COUNT_VALUE);
			
			waitForElementPresent(phrsc.TESTTAB_CLICK, methodName);
			getXpathWebElement(phrsc.TESTTAB_CLICK);
			click();
		    Thread.sleep(2000);
		    
			successFailureLoop();
			
			waitForElementPresent(phrsc.PERFORMANCE_POP_UP_CLOSE, methodName);
			getXpathWebElement(phrsc.PERFORMANCE_POP_UP_CLOSE);
			click();
			
			Thread.sleep(10000);
			
		}
		
		
		
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}
	
	public  void LoadTestForAganistWebServices(String methodName) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("**LoadTestForAganistWebServices****");
		try {
			Thread.sleep(5000);
			waitForElementPresent(phrsc.QUALITYBTN, methodName);
			getXpathWebElement(phrsc.QUALITYBTN);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.LOADTESTBTN, methodName);
			getXpathWebElement(phrsc.LOADTESTBTN);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.PERFORMANCETESTTAP, methodName);
			getXpathWebElement(phrsc.PERFORMANCETESTTAP);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.TESTAGANIST, methodName);
			getXpathWebElement(phrsc.TESTAGANIST);
			click();
			sendKeys(phrsc.TESTAGANIST_VALUE2);
			
			waitForElementPresent(phrsc.TESTRESULT_NAME_CLICK, methodName);
			getXpathWebElement(phrsc.TESTRESULT_NAME_CLICK).clear();
			click();
			sendKeys(phrsc.TESTRESULT_NAME_VALUE);
		
			waitForElementPresent(phrsc.NO_OF_USERS, methodName);
			getXpathWebElement(phrsc.NO_OF_USERS).clear();
			click();
			sendKeys(phrsc.NO_OF_USERS_VALUE);
			
			waitForElementPresent(phrsc.RAMP_UP_PERIOD, methodName);
			getXpathWebElement(phrsc.RAMP_UP_PERIOD).clear();
			click();
			sendKeys(phrsc.RAMP_UP_PERIOD_VALUE);
			
			waitForElementPresent(phrsc.LOOP_COUNT, methodName);
			getXpathWebElement(phrsc.LOOP_COUNT).clear();
			click();
			sendKeys(phrsc.LOOP_COUNT_VALUE);
			
			Thread.sleep(5000);
			
			waitForElementPresent(phrsc.OPTION_DROP1, methodName);
			getXpathWebElement(phrsc.OPTION_DROP1);
			click();
			System.out.println("-----------Check DropDown Track--------------");
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.KEY_CLICK, methodName);
			getXpathWebElement(phrsc.KEY_CLICK).clear();
			click();
			sendKeys(phrsc.KEY_VALUE);
			
			Thread.sleep(2000);
			
			waitForElementPresent(phrsc.VALUE_FIELD_CLICK, methodName);
			getXpathWebElement(phrsc.VALUE_FIELD_CLICK).clear();
			click();
			sendKeys(phrsc.VALUE_PASS);
			
			waitForElementPresent(phrsc.TESTTAB_CLICK, methodName);
			getXpathWebElement(phrsc.TESTTAB_CLICK);
			click();
		    Thread.sleep(2000);
		    
			successFailureLoop();
			
			waitForElementPresent(phrsc.PERFORMANCE_POP_UP_CLOSE, methodName);
			getXpathWebElement(phrsc.PERFORMANCE_POP_UP_CLOSE);
			click();
			
			Thread.sleep(3000);
			
			
	}
		catch (InterruptedException e) {

			e.printStackTrace();
		}	
	}


	
// App Layer Project Updation
	
	public  void PhpHellworldProjectUpdation(String methodName,PhpConstantsXml phpConst) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@PhpHellworldProjectUpdation::****** executing ****");
		try {
			
			Thread.sleep(3000);	
			waitForElementPresent(phpConst.PHP_PROJECT_HELLOWORLD_CHECK_BOX,methodName);
			getXpathWebElement(phpConst.PHP_PROJECT_HELLOWORLD_CHECK_BOX);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phpConst.PHP_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK,methodName);
          	getXpathWebElement(phpConst.PHP_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK);
			click();
					
			Thread.sleep(2000);
			
			waitForElementPresent(phrsc.APPINFO_NEXT, methodName);
			getXpathWebElement(phrsc.APPINFO_NEXT);
			click();
			Thread.sleep(2000);

			waitForElementPresent(phrsc.ADD_FEATURE1_CLCIK,methodName);
			getXpathWebElement(phrsc.ADD_FEATURE1_CLCIK);
			click();
			Thread.sleep(2000);
			
			waitForElementPresent(phrsc.ADD_FEATURE2_CLCIK,methodName);
			getXpathWebElement(phrsc.ADD_FEATURE2_CLCIK);
			click();
			Thread.sleep(2000);
			
			waitForElementPresent(phrsc.TRACK_FEATURES,methodName);
			getXpathWebElement(phrsc.TRACK_FEATURES);
			click();
			
			waitForElementPresent(phrsc.FEATURE_FINISH_BUTTON,methodName);
			getXpathWebElement(phrsc.FEATURE_FINISH_BUTTON);
			click();
			Thread.sleep(30000);


		}
		catch (InterruptedException e) {

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
			System.out.println("--------TextCheck value---->"+text+"------------Result is-------------"+value); 
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
}
