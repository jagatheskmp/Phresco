package com.photon.phresco.Screens;

import java.awt.AWTException;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;



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


import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import com.google.common.base.Function;
import com.photon.phresco.selenium.util.Constants;
import com.photon.phresco.selenium.util.GetCurrentDir;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.AdminUIData;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.UserInfoConstants;


public class BaseScreen {

	private static WebDriver driver;
	private ChromeDriverService chromeService;
	private Log log = LogFactory.getLog("BaseScreen");
	private WebElement element;	
	private AdminUIData adminUIConstants;
	private UIConstants uiConstants;
	private UserInfoConstants userInfoConstants;
	private static PhrescoUiConstants phrsc;

	// private Log log = LogFactory.getLog(getClass());

	public BaseScreen() {

	}

	public BaseScreen(String selectedBrowser, String applicationURL, String applicatinContext, AdminUIData adminUIConstants, UIConstants uiConstants,UserInfoConstants userInfoConstants)
			throws ScreenException {
	
		this.adminUIConstants=adminUIConstants;
		this.uiConstants = uiConstants;
		this.userInfoConstants = userInfoConstants;
		
		instantiateBrowser(selectedBrowser, applicationURL, applicatinContext);

	}

	public void instantiateBrowser(String selectedBrowser,
			String applicationURL, String applicationContext)
			throws ScreenException {

		if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_CHROME)) {
			try {
				// "D:/Selenium-jar/chromedriver_win_19.0.1068.0/chromedriver.exe"
				chromeService = new ChromeDriverService.Builder()
						.usingDriverExecutable(
								new File(getChromeLocation()))
						.usingAnyFreePort().build();	
				
				log.info("-------------***LAUNCHING GOOGLECHROME***--------------");						
				driver=new ChromeDriver(chromeService);
				//driver.manage().window().maximize();
			//	driver = new ChromeDriver(chromeService, chromeOption);
				// driver.manage().timeouts().implicitlyWait(30,
				// TimeUnit.SECONDS);				
				//driver.navigate().to(applicationURL + applicationContext);
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
			//driver.manage().window().maximize();
			
			windowResize();
			driver.navigate().to(applicationURL + applicationContext);

		}

		else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_OPERA)) {
			log.info("-------------***LAUNCHING OPERA***--------------");
			// WebDriver driver = new OperaDriver();
			
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

	/*
	 * public static void windowMaximizeFirefox() {
	 * driver.manage().window().setPosition(new Point(0, 0)); java.awt.Dimension
	 * screenSize = java.awt.Toolkit.getDefaultToolkit() .getScreenSize();
	 * Dimension dim = new Dimension((int) screenSize.getWidth(), (int)
	 * screenSize.getHeight()); driver.manage().window().setSize(dim); }
	 */

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
			/*throw new RuntimeException("waitForElementPresent"
					+ super.getClass().getSimpleName() + " failed", e);*/
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
	/*..........Empty Login AdminUI scenario........*/
	public  void emptyLoginAdminUI(String methodName) throws Exception {
		 if (StringUtils.isEmpty(methodName)) {
           methodName = Thread.currentThread().getStackTrace()[1]
                           .getMethodName();
   }
	    	log.info("@testLoginPage::******executing emptyLoginAdminUI scenario****");
			try {
			Thread.sleep(2000);
				element=getXpathWebElement(uiConstants.LOGIN_USERNAME);
				waitForElementPresent(uiConstants.LOGIN_USERNAME,methodName);
				click();
				Thread.sleep(1000);
				
				element=getXpathWebElement(uiConstants.LOGIN_PASSWORD);
				waitForElementPresent(uiConstants.LOGIN_PASSWORD,methodName);
				click();
				Thread.sleep(1000);
				
			    element=getXpathWebElement(uiConstants.LOGIN_BUTTON);
				waitForElementPresent(uiConstants.LOGIN_BUTTON,methodName);
				element.click();
				Thread.sleep(3000);
				
				isTextPresent(adminUIConstants.EMPTY_ERROR_MSG_LOGIN);
				
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}		
	 }
	
	/*..........Invalid Login AdminUI scenario........*/
	 public  void invalidLoginAdminUI(String methodName) throws Exception {
		 if (StringUtils.isEmpty(methodName)) {
           methodName = Thread.currentThread().getStackTrace()[1]
                           .getMethodName();
   }
	    	log.info("@testLoginPage::******executing invalidLoginAdminUI scenario****");
			try {
					Thread.sleep(2000);		
				element=getXpathWebElement(uiConstants.LOGIN_USERNAME);
				waitForElementPresent(uiConstants.LOGIN_USERNAME,methodName);
				element.sendKeys(adminUIConstants.INVALID_LOGIN_USERNAME);
				Thread.sleep(1000);
			
			    element=getXpathWebElement(uiConstants.LOGIN_PASSWORD);
			    waitForElementPresent(uiConstants.LOGIN_PASSWORD,methodName);
				element.sendKeys(adminUIConstants.INVALID_LOGIN_PASSWORD);
				Thread.sleep(1000);
				
				element=getXpathWebElement(uiConstants.LOGIN_BUTTON);
			    waitForElementPresent(uiConstants.LOGIN_BUTTON,methodName);
				element.click();			
				Thread.sleep(3000);
				
				
				//isTextPresent("Phresco");
				
				
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}		
	 }
		/*.......... Valid Login AdminUI scenario........*/
	 public  void validLoginAdminUI(String methodName) throws Exception {
		 if (StringUtils.isEmpty(methodName)) {
           methodName = Thread.currentThread().getStackTrace()[1]
                           .getMethodName();
   }
	    	log.info("@testLoginPage::******executing validLoginAdminUI scenario****");
			try {
				Thread.sleep(5000);		
				element=getXpathWebElement(uiConstants.LOGIN_USERNAME);
				waitForElementPresent(uiConstants.LOGIN_USERNAME,methodName);
				element.sendKeys(userInfoConstants.USERNAME);
				Thread.sleep(1000);
				
				element=getXpathWebElement(uiConstants.LOGIN_PASSWORD);
				waitForElementPresent(uiConstants.LOGIN_PASSWORD,methodName);
				element.sendKeys(userInfoConstants.PASSWORD);
				Thread.sleep(1000);
				
				element=getXpathWebElement(uiConstants.LOGIN_BUTTON);
				waitForElementPresent(uiConstants.LOGIN_BUTTON,methodName);
				element.click();
				Thread.sleep(3000);
				
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}		
	 }
	 	/*...........Dashboard AdminUI Scenario.......*/
	 
	  	 public  void dashboardLink(String methodName) throws Exception {
		 if (StringUtils.isEmpty(methodName)) {
             methodName = Thread.currentThread().getStackTrace()[1]
                             .getMethodName();
     }
	    	log.info("@testDashboardPage::******Executing dashboardLink scenario****");
			try {
				Thread.sleep(2000);
				element=getXpathWebElement(uiConstants.DADHBOARD_LINK);
				waitForElementPresent(uiConstants.DADHBOARD_LINK,methodName);
				element.click();
				Thread.sleep(1000);
				
				element=getXpathWebElement(uiConstants.MOST_USED_ARCHETYPE);
				waitForElementPresent(uiConstants.MOST_USED_ARCHETYPE,methodName);
				element.click();
				Thread.sleep(1000);
				
				element=getXpathWebElement(uiConstants.MOST_USED_ARCHETYPE);
				waitForElementPresent(uiConstants.MOST_USED_ARCHETYPE,methodName);
				element.click();
				Thread.sleep(1000);
				
				element=getXpathWebElement(uiConstants.MOST_USED_ARCHETYPE);
				waitForElementPresent(uiConstants.MOST_USED_ARCHETYPE,methodName);
				element.click();
				Thread.sleep(3000);
				
				element=getXpathWebElement(uiConstants.MOST_USED_FEATURE);
				waitForElementPresent(uiConstants.MOST_USED_FEATURE,methodName);
				element.click();
				Thread.sleep(1000);
				
				element=getXpathWebElement(uiConstants.MOST_USED_FEATURE);
				waitForElementPresent(uiConstants.MOST_USED_FEATURE,methodName);
				element.click();
				Thread.sleep(1000);
				
				element=getXpathWebElement(uiConstants.MOST_USED_FEATURE);
				waitForElementPresent(uiConstants.MOST_USED_FEATURE,methodName);
				element.click();
				Thread.sleep(3000);
				
				element=getXpathWebElement(uiConstants.MOST_USED_PILOT_PROJECT);
				waitForElementPresent(uiConstants.MOST_USED_PILOT_PROJECT,methodName);
				element.click();
				Thread.sleep(1000);
				
				element=getXpathWebElement(uiConstants.MOST_USED_PILOT_PROJECT);
				waitForElementPresent(uiConstants.MOST_USED_PILOT_PROJECT,methodName);
				element.click();
				Thread.sleep(1000);
				
				element=getXpathWebElement(uiConstants.MOST_USED_PILOT_PROJECT);
				waitForElementPresent(uiConstants.MOST_USED_PILOT_PROJECT,methodName);
				element.click();
				Thread.sleep(3000);
				
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}		
	 }
	  	 
	  	 
	  	public  void createEmptyModules(String methodName) throws Exception {
			 if (StringUtils.isEmpty(methodName)) {
	             methodName = Thread.currentThread().getStackTrace()[1]
	                             .getMethodName();
	     }
		    	log.info("@testComponentsFeaturePage::******executing createEmptyModules scenario****");
				try {
					Thread.sleep(2000);
					element=getXpathWebElement(uiConstants.COMPONENTS_LINK);
					waitForElementPresent(uiConstants.COMPONENTS_LINK,methodName);
					element.click();
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.COMPONENT_FEATURE_TAB);
					waitForElementPresent(uiConstants.COMPONENT_FEATURE_TAB,methodName);
					element.click();
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.FEATURE_MODULES_TAB);
					waitForElementPresent(uiConstants.FEATURE_MODULES_TAB,methodName);
					element.click();
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.CREATE_MODULES_BUTTON);
					waitForElementPresent(uiConstants.CREATE_MODULES_BUTTON,methodName);
					element.click();
					Thread.sleep(1000);
					Thread.sleep(5000);
					
				
							//element = driver.findElement(By.id("fileUpload"));
							//element.sendKeys("file://C:/Documents and Settings/jagatheswaran_s/Desktop/a.txt");
				//driver.findElement(By.ByClassName.className("upload")).click();
					
					//driver.findElement(By.ByName.name("upload")).sendKeys("C:/Documents and Settings/jagatheswaran_s/Desktop/a.txt");
			//	driver.findElement(By.className("upload")).click();
				//.sendKeys("file:///C:/Documents and Settings/jagatheswaran_s/Desktop/a.txt");
					//Thread.sleep(5000);
				/*--------------------------------*/
					
					/*WebElement fileUpload = driver.findElement(By.id("feature-file-uploader"));
					fileUpload.sendKeys("D:\\Documents\\Pictures\\Sample.jpeg");
					driver.findElement(By.id("submit")).click();*/
				
					
					//element=getXpathWebElement("//div[@class='qq-upload-button btn btn-primary']");
					//Thread.sleep(10000);
					//element.click();
					//driver.findElement(By.name("uploadfile")).click(); 
					//fileInput.sendKeys("C:/Documents and Settings/jagatheswaran_s/Desktop/a.txt");
					//Thread.sleep(30000);
					//driver.findElement(By.xpath("//div[@class='qq-upload-button btn btn-primary']")).click();
					//fileInput.submit();
				//	Thread.sleep(10000);
					//fileInput.click();
					//Thread.sleep(5000);
					//fileInput.sendKeys("C:/Documents and Settings/jagatheswaran_s/Desktop/a.txt");
				
					//Thread.sleep(1000);
					//fileInput.sendKeys("C:/Documents and Settings/jagatheswaran_s/Desktop/a.txt");
				
				
					/*s.........................*/
				
				
				
				
				
				
					element=getXpathWebElement(uiConstants.MODULES_NAME);
					waitForElementPresent(uiConstants.MODULES_NAME,methodName);
					click();
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.MODULE_DESCRIPTION);
					waitForElementPresent(uiConstants.MODULE_DESCRIPTION,methodName);
					click();
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.MODULE_HELPTEXT);
					waitForElementPresent(uiConstants.MODULE_HELPTEXT,methodName);
					click();
					Thread.sleep(1000);
					 
					element=getXpathWebElement(uiConstants.MODULE_SAVE_BUTTON);
					waitForElementPresent(uiConstants.MODULE_SAVE_BUTTON, methodName);
					element.click();
					Thread.sleep(1000);
					
					isTextPresent(adminUIConstants.EMPTY_CREATE_MODULE_NAME_MSG);
					isTextPresent(adminUIConstants.EMPTY_CREATE_MODULE_UPLOAD_FILE_MSG);
					
					element=getXpathWebElement(uiConstants.MODULE_CANCEL_BUTTON);
					waitForElementPresent(uiConstants.MODULE_CANCEL_BUTTON, methodName);
					element.click();
					Thread.sleep(1000);
					
					
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}		
		 }
	  	
		public  void createInvalidModules(String methodName) throws Exception {
			 if (StringUtils.isEmpty(methodName)) {
	             methodName = Thread.currentThread().getStackTrace()[1]
	                             .getMethodName();
	     }
		    	log.info("@testComponentsFeatureModulePage::******executing createInvalidModules scenario****");
				try {
					Thread.sleep(2000);
					element=getXpathWebElement(uiConstants.CREATE_MODULES_BUTTON);
					waitForElementPresent(uiConstants.CREATE_MODULES_BUTTON,methodName);
					element.click();
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.MODULES_NAME);
					waitForElementPresent(uiConstants.MODULES_NAME,methodName);
					element.sendKeys(adminUIConstants.CREATE_MODULE_NAME_VALUE);
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.MODULE_DESCRIPTION);
					waitForElementPresent(uiConstants.MODULE_DESCRIPTION,methodName);
					element.sendKeys(adminUIConstants.CREATE_MODULE_DESCRIPTION_VALUE);
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.MODULE_HELPTEXT);
					waitForElementPresent(uiConstants.MODULE_HELPTEXT,methodName);
					element.sendKeys(adminUIConstants.CREATE_MODULE_HELPTEXT_VALUE);
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.MODULE_TECHNOLOGY);
					waitForElementPresent(uiConstants.MODULE_TECHNOLOGY, methodName);
					element.click();
					Thread.sleep(1000);
					
					element.sendKeys(adminUIConstants.CREATE_MODULE_TECHNOLOGY_VALUE);
					element.click();
					Thread.sleep(3000);
					
					element=getXpathWebElement(uiConstants.MODULE_MODULETYPE);
					waitForElementPresent(uiConstants.MODULE_MODULETYPE, methodName);
					element.click();
					element.sendKeys(adminUIConstants.CREATE_MODULE_TYPE_CUSTOM_MODULE_VALUE);
					element.click();
					Thread.sleep(3000);
					
					//adminUIConstants.INVALID_CREATE_MODULE_UPLOAD_FILE_NAME;
					
					
					element=getXpathWebElement(uiConstants.MODULE_SELECT_DEPENDENCY);
					waitForElementPresent(uiConstants.MODULE_SELECT_DEPENDENCY, methodName);
					element.click();
					Thread.sleep(3000);
					
					element=getXpathWebElement(uiConstants.MODULE_DEPENDENCY_LOGIN_CHECKBOX);
					waitForElementPresent(uiConstants.MODULE_DEPENDENCY_LOGIN_CHECKBOX, methodName);
					element.click();
					
					element=getXpathWebElement(uiConstants.MODULE_DEPENDENCY_OK_BUTTON);
					waitForElementPresent(uiConstants.MODULE_DEPENDENCY_OK_BUTTON, methodName);
					element.click();
					Thread.sleep(1000);
					 
					element=getXpathWebElement(uiConstants.MODULE_SAVE_BUTTON);
					waitForElementPresent(uiConstants.MODULE_SAVE_BUTTON, methodName);
					element.click();
					Thread.sleep(1000);
					
					
					isTextPresent(adminUIConstants.EMPTY_CREATE_MODULE_UPLOAD_FILE_MSG);
					
					element=getXpathWebElement(uiConstants.MODULE_CANCEL_BUTTON);
					waitForElementPresent(uiConstants.MODULE_CANCEL_BUTTON, methodName);
					element.click();
					Thread.sleep(1000);
					
									
					
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}		
		 }
	  	/*Create Components Features Module AdminUI Scenario*/
		 public  void createValidModules(String methodName) throws Exception {
			 if (StringUtils.isEmpty(methodName)) {
	             methodName = Thread.currentThread().getStackTrace()[1]
	                             .getMethodName();
	     }
		    	log.info("@testComponentsFeatureModulePage::******executing createValidModules scenario****");
				try {
					Thread.sleep(2000);					
					element=getXpathWebElement(uiConstants.CREATE_MODULES_BUTTON);
					waitForElementPresent(uiConstants.CREATE_MODULES_BUTTON,methodName);
					element.click();
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.MODULES_NAME);
					waitForElementPresent(uiConstants.MODULES_NAME,methodName);
					element.sendKeys(adminUIConstants.CREATE_MODULE_NAME_VALUE);
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.MODULE_DESCRIPTION);
					waitForElementPresent(uiConstants.MODULE_DESCRIPTION,methodName);
					element.sendKeys(adminUIConstants.CREATE_MODULE_DESCRIPTION_VALUE);
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.MODULE_HELPTEXT);
					waitForElementPresent(uiConstants.MODULE_HELPTEXT,methodName);
					element.sendKeys(adminUIConstants.CREATE_MODULE_HELPTEXT_VALUE);
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.MODULE_TECHNOLOGY);
					waitForElementPresent(uiConstants.MODULE_TECHNOLOGY, methodName);
					element.click();
					Thread.sleep(1000);
					
					element.sendKeys(adminUIConstants.CREATE_MODULE_TECHNOLOGY_VALUE);
					element.click();
					Thread.sleep(3000);
					
					element=getXpathWebElement(uiConstants.MODULE_MODULETYPE);
					waitForElementPresent(uiConstants.MODULE_MODULETYPE, methodName);
					element.click();
					element.sendKeys(adminUIConstants.CREATE_MODULE_TYPE_CUSTOM_MODULE_VALUE);
					element.click();
					Thread.sleep(3000);
					
					//element.findElement(By.className("file")).sendKeys("D:/Jagathes/selenium-server-standalone-2.25.0.jar");
					//element=getXpathWebElement(uiConstants.MODULEUPLOADFILE_XPATH);
					//waitForElementPresent(uiConstants.MODULEUPLOADFILE_XPATH, methodName);
					//element.click();
					element=getXpathWebElement(uiConstants.MODULE_SELECT_DEPENDENCY);
					waitForElementPresent(uiConstants.MODULE_SELECT_DEPENDENCY, methodName);
					element.click();
					Thread.sleep(3000);
					
					element=getXpathWebElement(uiConstants.MODULE_DEPENDENCY_LOGIN_CHECKBOX);
					waitForElementPresent(uiConstants.MODULE_DEPENDENCY_LOGIN_CHECKBOX, methodName);
					element.click();
					
					element=getXpathWebElement(uiConstants.MODULE_DEPENDENCY_OK_BUTTON);
					waitForElementPresent(uiConstants.MODULE_DEPENDENCY_OK_BUTTON, methodName);
					element.click();
					Thread.sleep(1000);	
					
					element=getXpathWebElement(uiConstants.MODULE_SAVE_BUTTON);
					waitForElementPresent(uiConstants.MODULE_SAVE_BUTTON, methodName);
					element.click();
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.MODULE_CANCEL_BUTTON);
					waitForElementPresent(uiConstants.MODULE_CANCEL_BUTTON, methodName);
					element.click();
					Thread.sleep(1000);
					
					
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}		
		 }
		 
		  	/*Update Components Features Module AdminUI Scenario*/
		 public  void updateModules(String methodName) throws Exception {
			 if (StringUtils.isEmpty(methodName)) {
	             methodName = Thread.currentThread().getStackTrace()[1]
	                             .getMethodName();
	     }
		    	log.info("@testComponentsFeatureModulePage::******executing updateModules scenario****");
				try {
					Thread.sleep(3000);
					element=getXpathWebElement(uiConstants.UPDATE_MODULE_LINK);
					waitForElementPresent(uiConstants.UPDATE_MODULE_LINK,methodName);
					element.click();
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.UPDATE_MODULE_OPTION_BUTTON);
					waitForElementPresent(uiConstants.UPDATE_MODULE_OPTION_BUTTON,methodName);
					element.click();
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.MODULES_NAME);
					waitForElementPresent(uiConstants.MODULES_NAME,methodName);
					clear();
					element.sendKeys(adminUIConstants.CREATE_MODULE_NAME_VALUE);
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.MODULE_DESCRIPTION);
					waitForElementPresent(uiConstants.MODULE_DESCRIPTION,methodName);
					element.sendKeys(adminUIConstants.CREATE_MODULE_DESCRIPTION_VALUE);
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.MODULE_HELPTEXT);
					waitForElementPresent(uiConstants.MODULE_HELPTEXT,methodName);
					element.sendKeys(adminUIConstants.CREATE_MODULE_HELPTEXT_VALUE);
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.UPDATE_MODULE_UPDATE_BUTTON);
					waitForElementPresent(uiConstants.UPDATE_MODULE_UPDATE_BUTTON,methodName);
					element.click();
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.MODULE_CANCEL_BUTTON);
					waitForElementPresent(uiConstants.MODULE_CANCEL_BUTTON, methodName);
					element.click();
					Thread.sleep(1000);
									
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}		
		 }
		  	/*Delete Components Features Module AdminUI Scenario*/
		 public  void deleteModules(String methodName) throws Exception {
			 if (StringUtils.isEmpty(methodName)) {
	             methodName = Thread.currentThread().getStackTrace()[1]
	                             .getMethodName();
	     }
		    	log.info("@testComponentsFeaturePage::******executing deleteModules scenario****");
				try {
					Thread.sleep(1000);
					//element=getXpathWebElement(uiConstants.DELETE_MODULE_XPATH);
					//waitForElementPresent(uiConstants.DELETE_MODULE_XPATH,methodName);
					element.click();
					Thread.sleep(1000);
					
									
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}		
		 }
		 
		 public  void createEmptyJSLibraries(String methodName) throws Exception {
			 if (StringUtils.isEmpty(methodName)) {
	             methodName = Thread.currentThread().getStackTrace()[1]
	                             .getMethodName();
	     }
		    	log.info("@testComponentsFeatureJSlibrariesPage::******executing createEmptyJSLibraries scenario****");
				try {
					Thread.sleep(2000);
					element=getXpathWebElement(uiConstants.FEATURES_JSLIBRARIES_TAB);
					waitForElementPresent(uiConstants.FEATURES_JSLIBRARIES_TAB,methodName);
					element.click();
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_CREATE_BUTTON);
					waitForElementPresent(uiConstants.JSLIBRARIES_CREATE_BUTTON,methodName);
					element.click();
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_NAME);
					waitForElementPresent(uiConstants.JSLIBRARIES_NAME,methodName);
					click();
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_DESCRIPTION);
					waitForElementPresent(uiConstants.JSLIBRARIES_DESCRIPTION,methodName);
					click();
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_HELPTEXT);
					waitForElementPresent(uiConstants.JSLIBRARIES_HELPTEXT,methodName);
					click();
					Thread.sleep(1000);
									
					element=getXpathWebElement(uiConstants.JSLIBRARIES_SAVE_BUTTON);
					waitForElementPresent(uiConstants.JSLIBRARIES_SAVE_BUTTON, methodName);
					element.click();
					Thread.sleep(1000);
					
					
					isTextPresent(adminUIConstants.EMPTY_CREATE_JSLIBRARIES_NAME_MSG);
					isTextPresent(adminUIConstants.EMPTY_CREATE_JSLIBRARIES_UPLOAD_FILE_MSG);
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_CANCEL_BUTTON);
					waitForElementPresent(uiConstants.JSLIBRARIES_CANCEL_BUTTON, methodName);
					element.click();
					Thread.sleep(1000);
					
					
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}		
		 }
		 public  void createInvalidJSLibraries(String methodName) throws Exception {
			 if (StringUtils.isEmpty(methodName)) {
	             methodName = Thread.currentThread().getStackTrace()[1]
	                             .getMethodName();
	     }
		    	log.info("@testComponentsFeatureJSlibrariesPage::******executing createInvalidJSLibraries scenario****");
				try {
					Thread.sleep(3000);					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_CREATE_BUTTON);
					waitForElementPresent(uiConstants.JSLIBRARIES_CREATE_BUTTON,methodName);
					element.click();
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_NAME);
					waitForElementPresent(uiConstants.JSLIBRARIES_NAME,methodName);
					element.sendKeys(adminUIConstants.CREATE_JSLIBRARIES_NAME_VALUE);
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_DESCRIPTION);
					waitForElementPresent(uiConstants.JSLIBRARIES_DESCRIPTION,methodName);
					element.sendKeys(adminUIConstants.CREATE_JSLIBRARIES_DESCRIPTION_VALUE);
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_HELPTEXT);
					waitForElementPresent(uiConstants.JSLIBRARIES_HELPTEXT,methodName);
					element.sendKeys(adminUIConstants.CREATE_JSLIBRARIES_HELPTEXT_VALUE);
					Thread.sleep(1000);
					
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_TECHNOLOGY);
					waitForElementPresent(uiConstants.JSLIBRARIES_TECHNOLOGY, methodName);
					element.click();
					Thread.sleep(1000);
					
					element.sendKeys(adminUIConstants.CREATE_JSLIBRARIES_PHP_TECHONOLGY_VALUE);
					element.click();
					Thread.sleep(3000);
					
					//element.findElement(By.className("file")).sendKeys("D:/Jagathes/selenium-server-standalone-2.25.0.jar");
					//element=getXpathWebElement(uiConstants.MODULEUPLOADFILE_XPATH);
					//waitForElementPresent(uiConstants.MODULEUPLOADFILE_XPATH, methodName);
					//element.click();
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_SELECT_DEPENDENCY);
					waitForElementPresent(uiConstants.JSLIBRARIES_SELECT_DEPENDENCY, methodName);
					element.click();
					Thread.sleep(3000);
					Thread.sleep(3000);
					element=getXpathWebElement(uiConstants.JSLIBRARIES_SELECT_JSON_CHECKBOX);
					waitForElementPresent(uiConstants.JSLIBRARIES_SELECT_JSON_CHECKBOX, methodName);
					element.click();
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_DEPENDENCY_OK_BUTTON);
					waitForElementPresent(uiConstants.JSLIBRARIES_DEPENDENCY_OK_BUTTON, methodName);
					element.click();
					Thread.sleep(1000);	
					
				
					element=getXpathWebElement(uiConstants.JSLIBRARIES_SAVE_BUTTON);
					waitForElementPresent(uiConstants.JSLIBRARIES_SAVE_BUTTON, methodName);
					element.click();
					Thread.sleep(1000);
					
					
					isTextPresent(adminUIConstants.EMPTY_CREATE_JSLIBRARIES_UPLOAD_FILE_MSG);
				
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_CANCEL_BUTTON);
					waitForElementPresent(uiConstants.JSLIBRARIES_CANCEL_BUTTON, methodName);
					element.click();
					Thread.sleep(1000);
					
					
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}		
		 }
		  	/*Create Components Features JSLibraries AdminUI Scenario*/
		 public  void createValidJSLibraries(String methodName) throws Exception {
			 if (StringUtils.isEmpty(methodName)) {
	             methodName = Thread.currentThread().getStackTrace()[1]
	                             .getMethodName();
	     }
		    	log.info("@testComponentsFeatureJSlibrariesPage::******executing createValidJSLibraries scenario****");
				try {
					Thread.sleep(3000);
					element=getXpathWebElement(uiConstants.JSLIBRARIES_CREATE_BUTTON);
					waitForElementPresent(uiConstants.JSLIBRARIES_CREATE_BUTTON,methodName);
					element.click();
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_NAME);
					waitForElementPresent(uiConstants.JSLIBRARIES_NAME,methodName);
					element.sendKeys(adminUIConstants.CREATE_JSLIBRARIES_NAME_VALUE);
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_DESCRIPTION);
					waitForElementPresent(uiConstants.JSLIBRARIES_DESCRIPTION,methodName);
					element.sendKeys(adminUIConstants.CREATE_JSLIBRARIES_DESCRIPTION_VALUE);
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_HELPTEXT);
					waitForElementPresent(uiConstants.JSLIBRARIES_HELPTEXT,methodName);
					element.sendKeys(adminUIConstants.CREATE_JSLIBRARIES_HELPTEXT_VALUE);
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_TECHNOLOGY);
					waitForElementPresent(uiConstants.JSLIBRARIES_TECHNOLOGY, methodName);
					element.click();
					Thread.sleep(1000);
					
					element.sendKeys(adminUIConstants.CREATE_JSLIBRARIES_PHP_TECHONOLGY_VALUE);
					element.click();
					Thread.sleep(3000);
					
					//element.findElement(By.className("file")).sendKeys("D:/Jagathes/selenium-server-standalone-2.25.0.jar");
					//element=getXpathWebElement(uiConstants.MODULEUPLOADFILE_XPATH);
					//waitForElementPresent(uiConstants.MODULEUPLOADFILE_XPATH, methodName);
					//element.click();
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_SELECT_DEPENDENCY);
					waitForElementPresent(uiConstants.JSLIBRARIES_SELECT_DEPENDENCY, methodName);
					element.click();
					Thread.sleep(3000);
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_SELECT_JSON_CHECKBOX);
					waitForElementPresent(uiConstants.JSLIBRARIES_SELECT_JSON_CHECKBOX, methodName);
					element.click();
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_DEPENDENCY_OK_BUTTON);
					waitForElementPresent(uiConstants.JSLIBRARIES_DEPENDENCY_OK_BUTTON, methodName);
					element.click();
					Thread.sleep(1000);	
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_SAVE_BUTTON);
					waitForElementPresent(uiConstants.JSLIBRARIES_SAVE_BUTTON, methodName);
					element.click();
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_CANCEL_BUTTON);
					waitForElementPresent(uiConstants.JSLIBRARIES_CANCEL_BUTTON, methodName);
					element.click();
					Thread.sleep(1000);
					
					
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}		
		 }
		 /*Update Components Features JSLibraries AdminUI Scenario*/
		 public  void updateJSLibraries(String methodName) throws Exception {
			 if (StringUtils.isEmpty(methodName)) {
	             methodName = Thread.currentThread().getStackTrace()[1]
	                             .getMethodName();
	     }
		    	log.info("@testComponentsFeatureJSlibrariesPage::******executing updateJSLibraries scenario****");
				try {
					Thread.sleep(1000);
					element=getXpathWebElement(uiConstants.UPDATE_JSLIBRARIES_LINK_XPATH);
					waitForElementPresent(uiConstants.UPDATE_JSLIBRARIES_LINK_XPATH,methodName);
					element.click();
					
					Thread.sleep(1000);
					element=getXpathWebElement(uiConstants.UPDATE_JSLIBRARIES_OPTION_BUTTON_XPATH);
					waitForElementPresent(uiConstants.UPDATE_JSLIBRARIES_OPTION_BUTTON_XPATH,methodName);
					element.click();
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_NAME);
					waitForElementPresent(uiConstants.JSLIBRARIES_NAME,methodName);
					element.sendKeys(adminUIConstants.UPDATE_FEATURE_JSLIBRARIES_NAME_VALUE);
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_DESCRIPTION);
					waitForElementPresent(uiConstants.JSLIBRARIES_DESCRIPTION,methodName);
					element.sendKeys(adminUIConstants.UPDATE_FEATURE_JSLIBRARIES_DESCRIPTION_VALUE);
					Thread.sleep(1000);
					
					element=getXpathWebElement(uiConstants.JSLIBRARIES_HELPTEXT);
					waitForElementPresent(uiConstants.JSLIBRARIES_HELPTEXT,methodName);
					element.sendKeys(adminUIConstants.UPDATE_FEATURE_JSLIBRARIES_HELP_TEXT_VALUE);
					Thread.sleep(1000);
					

					
					element=getXpathWebElement(uiConstants.UPDATE_JSLIBRARIES_UPDATE_BUTTON_XPATH);
					waitForElementPresent(uiConstants.UPDATE_JSLIBRARIES_UPDATE_BUTTON_XPATH,methodName);
					element.click();
									
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}		
		 }
		 /*Delete Components Features JSLibraries AdminUI Scenario*/
		 public  void deleteJSLibraries(String methodName) throws Exception {
			 if (StringUtils.isEmpty(methodName)) {
	             methodName = Thread.currentThread().getStackTrace()[1]
	                             .getMethodName();
	     }
		    	log.info("@testComponentsFeaturePage::******executing deleteJSLibraries scenario****");
				try {
					
					//element=getXpathWebElement(uiConstants.DELETE_JSLIBRARIES_XPATH);
					waitForElementPresent(uiConstants.DELETE_JSLIBRARIES_XPATH,methodName);
					element.click();
					
									
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}		
		 }
		 
		 public void createEmptyApplicationType(String methodName)
		 {
			 if (StringUtils.isEmpty(methodName)) {
	             methodName = Thread.currentThread().getStackTrace()[1]
                         .getMethodName();
			 }
			 log.info("@testComponentsApplicationTypesPage::******executing createEmptyApplicationType scenario****");
			 try {
				Thread.sleep(3000);
				element=getXpathWebElement(uiConstants.APPLICATION_TYPES_TAB);
				waitForElementPresent(uiConstants.APPLICATION_TYPES_TAB, methodName);
				element.click();
				Thread.sleep(1000);
				
				element=getXpathWebElement(uiConstants.APPLICATION_TYPES_CREATE);
				waitForElementPresent(uiConstants.APPLICATION_TYPES_CREATE, methodName);
				element.click();
				Thread.sleep(1000);
				
				element=getXpathWebElement(uiConstants.APPLICATION_TYPE_NAME);
				waitForElementPresent(uiConstants.APPLICATION_TYPE_NAME, methodName);
				element.click();
				Thread.sleep(1000);
				
				element=getXpathWebElement(uiConstants.APPLICATION_TYPE_DESCRIPTION);
				waitForElementPresent(uiConstants.APPLICATION_TYPE_DESCRIPTION, methodName);
				element.click();
				Thread.sleep(1000);
				
			/*	element=getXpathWebElement(uiConstants.APPLICATION_TYPE_SAVE_BUTTON);
				waitForElementPresent(uiConstants.APPLICATION_TYPE_SAVE_BUTTON, methodName);
				element.click();
				Thread.sleep(1000);*/
				//isTextPresent(adminUIConstants.CREATE_EMPTY_APPLICATION_TYPE_NAME_ERROR_MSG);
				
				element=getXpathWebElement(uiConstants.APPLICATION_TYPE_CANCEL_BUTTON);
				waitForElementPresent(uiConstants.APPLICATION_TYPE_CANCEL_BUTTON, methodName);
				element.click();
				Thread.sleep(1000);
				
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			 	 
		 }
		 
		 /*Create Components ApplicationType AdminUI Scenario*/
		 public void createValidApplicationType(String methodName)
		 {
			 if (StringUtils.isEmpty(methodName)) {
	             methodName = Thread.currentThread().getStackTrace()[1]
                         .getMethodName();
			 }
			 log.info("@testComponentsApplicationTypesPage::******executing createValidApplicationType scenario****");
			 try {
				Thread.sleep(3000);
				element=getXpathWebElement(uiConstants.APPLICATION_TYPES_TAB);
				waitForElementPresent(uiConstants.APPLICATION_TYPES_TAB, methodName);
				element.click();
				Thread.sleep(1000);
				
				element=getXpathWebElement(uiConstants.APPLICATION_TYPES_CREATE);
				waitForElementPresent(uiConstants.APPLICATION_TYPES_CREATE, methodName);
				element.click();
				Thread.sleep(1000);
				
				element=getXpathWebElement(uiConstants.APPLICATION_TYPE_NAME);
				waitForElementPresent(uiConstants.APPLICATION_TYPE_NAME, methodName);
				element.sendKeys(adminUIConstants.CREATE_APPLICATION_TYPE_NAME_VALUE);
				Thread.sleep(1000);
				
				element=getXpathWebElement(uiConstants.APPLICATION_TYPE_DESCRIPTION);
				waitForElementPresent(uiConstants.APPLICATION_TYPE_DESCRIPTION, methodName);
				element.sendKeys(adminUIConstants.CREATE_APPLICATION_TYPE_DESCRIPTION_VALUE);
				Thread.sleep(1000);
				
				/*element=getXpathWebElement(uiConstants.APPLICATION_TYPE_SAVE_BUTTON);
				waitForElementPresent(uiConstants.APPLICATION_TYPE_SAVE_BUTTON, methodName);
				element.click();
				Thread.sleep(1000);*/
				
				element=getXpathWebElement(uiConstants.APPLICATION_TYPE_CANCEL_BUTTON);
				waitForElementPresent(uiConstants.APPLICATION_TYPE_CANCEL_BUTTON, methodName);
				element.click();
				Thread.sleep(1000);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			 	 
		 }
		 /*Update Components ApplicationType AdminUI Scenario*/
		 public void updateApplicationType(String methodName)
		 {
			 if (StringUtils.isEmpty(methodName)) {
	             methodName = Thread.currentThread().getStackTrace()[1]
                         .getMethodName();
			 }
			 log.info("@testComponentsApplicationTypesPage::******executing updateApplicationType scenario****");
			 try {
				Thread.sleep(3000);
				element=getXpathWebElement(uiConstants.UPDATE_APPLICATION_TYPES_LINK);
				waitForElementPresent(uiConstants.UPDATE_APPLICATION_TYPES_LINK, methodName);
				element.click();
				Thread.sleep(1000);
				
				element=getXpathWebElement(uiConstants.UPDATE_APPLICATION_TYPES_DESCRIPTION);
				waitForElementPresent(uiConstants.UPDATE_APPLICATION_TYPES_DESCRIPTION, methodName);
				element.sendKeys(adminUIConstants.UPDATE_APPLICATION_TYPE_DESCRIPTION_VALUE);
				Thread.sleep(1000);
				
				element=getXpathWebElement(uiConstants.UPDATE_APPLICATION_TYPES_UPDATE_BUTTON);
				waitForElementPresent(uiConstants.UPDATE_APPLICATION_TYPES_UPDATE_BUTTON, methodName);
				element.click();
				Thread.sleep(1000);
				
				element=getXpathWebElement(uiConstants.APPLICATION_TYPE_CANCEL_BUTTON);
				waitForElementPresent(uiConstants.APPLICATION_TYPE_CANCEL_BUTTON, methodName);
				element.click();
				Thread.sleep(1000);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		 }
		 
		 /*Create Admin Customer AdminUI Scenario*/
		 public void createValidCustomer(String methodName)
		 {
			 if (StringUtils.isEmpty(methodName)) {
	             methodName = Thread.currentThread().getStackTrace()[1]
                         .getMethodName();
			 }
			 log.info("@testAdminCustomerPage::******executing createValidCustomer scenario****");
			 try {
							 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CREATE_BUTTON);
				 waitForElementPresent(uiConstants.CUSTOMER_CREATE_BUTTON, methodName);
				 element.click();
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_NAME);
				 waitForElementPresent(uiConstants.CUSTOMER_NAME, methodName);
				 element.sendKeys(adminUIConstants.CREATE_CUSTOMER_NAME_VALUE);
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_DESCRIPTION);
				 waitForElementPresent(uiConstants.CUSTOMER_DESCRIPTION, methodName); 
				 element.sendKeys(adminUIConstants.CREATE_CUSTOMER_DESCRIPTION_VALUE);
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_EMAIL);
				 waitForElementPresent(uiConstants.CUSTOMER_EMAIL, methodName);
				 element.sendKeys(adminUIConstants.CREATE_CUSTOMER_EMAIL_VALUE);
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_ADDRESS);
				 waitForElementPresent(uiConstants.CUSTOMER_ADDRESS, methodName);
				 element.sendKeys(adminUIConstants.CREATE_CUSTOMER_ADDRESS_VALUE);
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_COUNTRY);
				 waitForElementPresent(uiConstants.CUSTOMER_COUNTRY, methodName);
				 element.click();
				 Thread.sleep(6000);
				 element.sendKeys(adminUIConstants.CREATE_CUSTOMER_COUNTRY_VALUE);
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_STATE);
				 waitForElementPresent(uiConstants.CUSTOMER_STATE, methodName);
				 element.sendKeys(adminUIConstants.CREATE_CUSTOMER_STATE_VALUE);
				 Thread.sleep(1000); 
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_ZIPCODE);
				 waitForElementPresent(uiConstants.CUSTOMER_ZIPCODE, methodName);
				 element.sendKeys(adminUIConstants.CREATE_CUSTOMER_ZIPCODE_VALUE);
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CONTACT_NUMBER);
				 waitForElementPresent(uiConstants.CUSTOMER_CONTACT_NUMBER, methodName);
				 element.sendKeys(adminUIConstants.CREATE_CUSTOMER_CONTACT_NUMBER_VALUE);
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_FAX);
				 waitForElementPresent(uiConstants.CUSTOMER_FAX, methodName);
				 element.sendKeys(adminUIConstants.CREATE_CUSTOMER_FAX_VALUE);
			     Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_HELP_TEXT);
				 waitForElementPresent(uiConstants.CUSTOMER_HELP_TEXT, methodName);
				 element.sendKeys(adminUIConstants.CREATE_CUSTOMER_HELPTEXT_VALUE);
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_LICENCE_TYPE);
				 waitForElementPresent(uiConstants.CUSTOMER_LICENCE_TYPE, methodName);
				 element.click();
				 Thread.sleep(1000);
				 element.sendKeys(adminUIConstants.CREATE_CUSTOMER_LICENCE_TYPE_VALUE);
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CALENDER_VALID_FROM);
				 waitForElementPresent(uiConstants.CUSTOMER_CALENDER_VALID_FROM, methodName);
				 element.click();
				 Thread.sleep(3000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CALENDER_VALID_FROM_FRONT_ARROW);
				 waitForElementPresent(uiConstants.CUSTOMER_CALENDER_VALID_FROM_FRONT_ARROW, methodName);
				 element.click();
				 Thread.sleep(3000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CALENDER_VALID_FROM_DATE);
				 waitForElementPresent(uiConstants.CUSTOMER_CALENDER_VALID_FROM_DATE, methodName);
				 element.click();
				 Thread.sleep(3000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CALENDER_VALID_UPTO);
				 waitForElementPresent(uiConstants.CUSTOMER_CALENDER_VALID_UPTO, methodName);
				 element.click();
				 Thread.sleep(2000);
				 
				 for(int i=0;i<=9;i++)
				 {				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CALENDER_VALID_UPTO_FRONT_ARROW);
				 waitForElementPresent(uiConstants.CUSTOMER_CALENDER_VALID_UPTO_FRONT_ARROW, methodName);
				 element.click();
				 Thread.sleep(1000);				 
				 }
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CALENDER_VALID_UPTO_DATE);
				 waitForElementPresent(uiConstants.CUSTOMER_CALENDER_VALID_UPTO_DATE, methodName);
				 element.click();
				 Thread.sleep(3000);
				 
				 
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_SAVE_BUTTON);
				 waitForElementPresent(uiConstants.CUSTOMER_SAVE_BUTTON, methodName);
				 element.click();
				 Thread.sleep(6000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CANCEL_BUTTON);
				 waitForElementPresent(uiConstants.CUSTOMER_CANCEL_BUTTON, methodName);
				 element.click();
				 Thread.sleep(1000);
				 
				 
		} catch (Exception e) {
				
				e.printStackTrace();
			}
		 }
		 /*Update Admin Customer AdminUI Scenario*/
		 public void updateCustomer(String methodName)
		 {
			 if (StringUtils.isEmpty(methodName)) {
	             methodName = Thread.currentThread().getStackTrace()[1]
                         .getMethodName();
			 }
			 log.info("@testAdminCustomerPage::******executing updateCustomer scenario****");
			 try {
							 
				 element=getXpathWebElement(uiConstants.UPDATE_CUSTOMER_LINK);
				 waitForElementPresent(uiConstants.UPDATE_CUSTOMER_LINK, methodName);
				 element.click();
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_NAME);
				 waitForElementPresent(uiConstants.CUSTOMER_NAME, methodName);
				 element.sendKeys(adminUIConstants.UPDATE_CUSTOMER_NAME_VALUE);
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_DESCRIPTION);
				 waitForElementPresent(uiConstants.CUSTOMER_DESCRIPTION, methodName); 
				 element.sendKeys(adminUIConstants.UPDATE_CUSTOMER_DESCRIPTION_VALUE);
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_EMAIL);
				 waitForElementPresent(uiConstants.CUSTOMER_EMAIL, methodName);
				 element.sendKeys(adminUIConstants.UPDATE_CUSTOMER_EMAIL_VALUE);
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_ADDRESS);
				 waitForElementPresent(uiConstants.CUSTOMER_ADDRESS, methodName);
				 element.sendKeys(adminUIConstants.UPDATE_CUSTOMER_ADDRESS_VALUE);
				 Thread.sleep(1000);
				 				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_HELP_TEXT);
				 waitForElementPresent(uiConstants.CUSTOMER_HELP_TEXT, methodName);
				 element.sendKeys(adminUIConstants.UPDATE_CUSTOMER_HELP_TEXT_VALUE);
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CALENDER_VALID_FROM);
				 waitForElementPresent(uiConstants.CUSTOMER_CALENDER_VALID_FROM, methodName);
				 element.click();
				 Thread.sleep(3000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CALENDER_VALID_FROM_FRONT_ARROW);
				 waitForElementPresent(uiConstants.CUSTOMER_CALENDER_VALID_FROM_FRONT_ARROW, methodName);
				 element.click();
				 Thread.sleep(3000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CALENDER_VALID_FROM_DATE);
				 waitForElementPresent(uiConstants.CUSTOMER_CALENDER_VALID_FROM_DATE, methodName);
				 element.click();
				 Thread.sleep(3000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CALENDER_VALID_UPTO);
				 waitForElementPresent(uiConstants.CUSTOMER_CALENDER_VALID_UPTO, methodName);
				 element.click();
				 Thread.sleep(2000);
				 
				 for(int i=0;i<=9;i++)
				 {				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CALENDER_VALID_UPTO_FRONT_ARROW);
				 waitForElementPresent(uiConstants.CUSTOMER_CALENDER_VALID_UPTO_FRONT_ARROW, methodName);
				 element.click();
				 Thread.sleep(1000);				 
				 }
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CALENDER_VALID_UPTO_DATE);
				 waitForElementPresent(uiConstants.CUSTOMER_CALENDER_VALID_UPTO_DATE, methodName);
				 element.click();
				 Thread.sleep(3000);
				 				 
				 element=getXpathWebElement(uiConstants.UPDATE_CUSTOMER_UPDATE_BUTTON);
				 waitForElementPresent(uiConstants.UPDATE_CUSTOMER_UPDATE_BUTTON, methodName);
				 element.click();
				 Thread.sleep(6000);
				
				 
				 
		} catch (Exception e) {
				
				e.printStackTrace();
			}
		 }
		 
		 
		 
		 
		 public void createInvalidCustomer(String methodName)
		 {
			 if (StringUtils.isEmpty(methodName)) {
	             methodName = Thread.currentThread().getStackTrace()[1]
                         .getMethodName();
			 }
			 log.info("@testAdminCustomerPage::******executing createInvalidCustomer scenario****");
			 try {
					 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CREATE_BUTTON);
				 waitForElementPresent(uiConstants.CUSTOMER_CREATE_BUTTON, methodName);
				 element.click();
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_NAME);
				 waitForElementPresent(uiConstants.CUSTOMER_NAME, methodName);
				 element.sendKeys(adminUIConstants.INVALID_CREATE_CUSTOMER_NAME_VALUE);
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_DESCRIPTION);
				 waitForElementPresent(uiConstants.CUSTOMER_DESCRIPTION, methodName); 
				 element.sendKeys(adminUIConstants.INVALID_CREATE_CUSTOMER_DESCRIPTION_VALUE);
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_EMAIL);
				 waitForElementPresent(uiConstants.CUSTOMER_EMAIL, methodName);
				 element.sendKeys(adminUIConstants.INVALID_CREATE_CUSTOMER_EMAIL_VALUE);
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_ADDRESS);
				 waitForElementPresent(uiConstants.CUSTOMER_ADDRESS, methodName);
				 element.sendKeys(adminUIConstants.INVALID_CREATE_CUSTOMER_ADDRESS_VALUE);
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_COUNTRY);
				 waitForElementPresent(uiConstants.CUSTOMER_COUNTRY, methodName);
				 element.click();
				 Thread.sleep(3000);
				 element.sendKeys(adminUIConstants.INVALID_CREATE_CUSTOMER_COUNTRY_VALUE);
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_STATE);
				 waitForElementPresent(uiConstants.CUSTOMER_STATE, methodName);
				 element.sendKeys(adminUIConstants.INVALID_CREATE_CUSTOMER_STATE_VALUE);
				 Thread.sleep(1000); 
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_ZIPCODE);
				 waitForElementPresent(uiConstants.CUSTOMER_ZIPCODE, methodName);
				 element.sendKeys(adminUIConstants.INVALID_CREATE_CUSTOMER_ZIPCODE_VALUE);
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CONTACT_NUMBER);
				 waitForElementPresent(uiConstants.CUSTOMER_CONTACT_NUMBER, methodName);
				 element.sendKeys(adminUIConstants.INVALID_CREATE_CUSTOMER_CONTACT_NUMBER_VALUE);
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_FAX);
				 waitForElementPresent(uiConstants.CUSTOMER_FAX, methodName);
				 element.sendKeys(adminUIConstants.INVALID_CREATE_CUSTOMER_FAX_VALUE);
			     Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_HELP_TEXT);
				 waitForElementPresent(uiConstants.CUSTOMER_HELP_TEXT, methodName);
				 element.sendKeys(adminUIConstants.INVALID_CREATE_CUSTOMER_HELP_TEXT_VALUE);
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_LICENCE_TYPE);
				 waitForElementPresent(uiConstants.CUSTOMER_LICENCE_TYPE, methodName);
				 element.click();
				 Thread.sleep(1000);
				 element.sendKeys(adminUIConstants.INVALID_CREATE_CUSTOMER_LICENCE_TYPE_VALUE);
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CALENDER_VALID_FROM);
				 waitForElementPresent(uiConstants.CUSTOMER_CALENDER_VALID_FROM, methodName);
				 element.click();
				 Thread.sleep(3000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CALENDER_VALID_FROM_FRONT_ARROW);
				 waitForElementPresent(uiConstants.CUSTOMER_CALENDER_VALID_FROM_FRONT_ARROW, methodName);
				 element.click();
				 Thread.sleep(3000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CALENDER_VALID_FROM_DATE);
				 waitForElementPresent(uiConstants.CUSTOMER_CALENDER_VALID_FROM_DATE, methodName);
				 element.click();
				 Thread.sleep(3000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CALENDER_VALID_UPTO);
				 waitForElementPresent(uiConstants.CUSTOMER_CALENDER_VALID_UPTO, methodName);
				 element.click();
				 Thread.sleep(2000);
				 
				 for(int i=0;i<=9;i++)
				 {				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CALENDER_VALID_UPTO_FRONT_ARROW);
				 waitForElementPresent(uiConstants.CUSTOMER_CALENDER_VALID_UPTO_FRONT_ARROW, methodName);
				 element.click();
				 Thread.sleep(1000);				 
				 }
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CALENDER_VALID_UPTO_DATE);
				 waitForElementPresent(uiConstants.CUSTOMER_CALENDER_VALID_UPTO_DATE, methodName);
				 element.click();
				 Thread.sleep(3000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_SAVE_BUTTON);
				 waitForElementPresent(uiConstants.CUSTOMER_SAVE_BUTTON, methodName);
				 element.click();
				 Thread.sleep(3000);
				 isTextPresent(adminUIConstants.ERROR_INVALID_CREATE_CUSTOMER_EMAIL_VALUE);
				 Thread.sleep(3000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CANCEL_BUTTON);
				 waitForElementPresent(uiConstants.CUSTOMER_CANCEL_BUTTON, methodName);
				 element.click();
				 Thread.sleep(1000);
				 
				 
		} catch (Exception e) {
				
				e.printStackTrace();
			}
		 }
		 public void createEmptyCustomer(String methodName)
		 {
			 if (StringUtils.isEmpty(methodName)) {
	             methodName = Thread.currentThread().getStackTrace()[1]
                         .getMethodName();
			 }
			 log.info("@testAdminCustomerPage::******executing createEmptyCustomer scenario****");
			 try {
				 Thread.sleep(3000);
				 element=getXpathWebElement(uiConstants.ADMIN_LINK);
				 waitForElementPresent(uiConstants.ADMIN_LINK, methodName);
				 element.click();
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.ADMIN_CUSTOMER_TAB);
				 waitForElementPresent(uiConstants.ADMIN_CUSTOMER_TAB, methodName);
				 element.click();
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CREATE_BUTTON);
				 waitForElementPresent(uiConstants.CUSTOMER_CREATE_BUTTON, methodName);
				 element.click();
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_NAME);
				 waitForElementPresent(uiConstants.CUSTOMER_NAME, methodName);
				 click();
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_DESCRIPTION);
				 waitForElementPresent(uiConstants.CUSTOMER_DESCRIPTION, methodName); 
				 click();
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_EMAIL);
				 waitForElementPresent(uiConstants.CUSTOMER_EMAIL, methodName);
				 click();
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_ADDRESS);
				 waitForElementPresent(uiConstants.CUSTOMER_ADDRESS, methodName);
				 click();
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_COUNTRY);
				 waitForElementPresent(uiConstants.CUSTOMER_COUNTRY, methodName);
				 Thread.sleep(2000);
				 click();
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_STATE);
				 waitForElementPresent(uiConstants.CUSTOMER_STATE, methodName);
				 click();
				 Thread.sleep(1000); 
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_ZIPCODE);
				 waitForElementPresent(uiConstants.CUSTOMER_ZIPCODE, methodName);
				 click();
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_CONTACT_NUMBER);
				 waitForElementPresent(uiConstants.CUSTOMER_CONTACT_NUMBER, methodName);
				 click();
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_FAX);
				 waitForElementPresent(uiConstants.CUSTOMER_FAX, methodName);
				 click();
			     Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_HELP_TEXT);
				 waitForElementPresent(uiConstants.CUSTOMER_HELP_TEXT, methodName);
				 click();
				 Thread.sleep(1000);
				 
				 element=getXpathWebElement(uiConstants.CUSTOMER_LICENCE_TYPE);
				 waitForElementPresent(uiConstants.CUSTOMER_LICENCE_TYPE, methodName);
				 Thread.sleep(1000);
				 click();
				 Thread.sleep(1000);
				  
				 element=getXpathWebElement(uiConstants.CUSTOMER_SAVE_BUTTON);
				 waitForElementPresent(uiConstants.CUSTOMER_SAVE_BUTTON, methodName);
				 element.click();
				 Thread.sleep(6000);
				
				 isTextPresent(adminUIConstants.ERROR_CUSTOMER_NAME_MSG);
				 isTextPresent(adminUIConstants.ERROR_CUSTOMER_EMAIL_MSG);
			     isTextPresent(adminUIConstants.ERROR_CUSTOMER_ADDRESS_MSG);
				 isTextPresent(adminUIConstants.ERROR_CUSTOMER_COUNTRY_MSG);
				 isTextPresent(adminUIConstants.ERROR_CUSTOMER_ZIPCODE_MSG);
				 isTextPresent(adminUIConstants.ERROR_CUSTOMER_CONTACT_NUMBER_MSG);
				 isTextPresent(adminUIConstants.ERROR_CUSTOMER_FAX_NSG);
				 isTextPresent(adminUIConstants.ERROR_CUSTOMER_LINCENCE_TYPE_MSG);
				
				 
				
				 Thread.sleep(10000);
				 element=getXpathWebElement(uiConstants.CUSTOMER_CANCEL_BUTTON);
				 waitForElementPresent(uiConstants.CUSTOMER_CANCEL_BUTTON, methodName);
				 element.click();
				 Thread.sleep(1000);
				 
				 
		} catch (Exception e) {
				
				e.printStackTrace();
			}
		 }
	
		 
		 
		 
		 
		 
		 public void  createEmptyArchetypes(String methodName)
		 {
		  if (StringUtils.isEmpty(methodName)) {
		      methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		     		 log.info("@testAdminCustomerPage::******executing createEmptyArchetypes scenario****");
		  }
		      try {
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_TAB);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_TAB, methodName);
		     	 		element.click();
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_CREATE_BUTTON);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_CREATE_BUTTON, methodName);
		     	 		element.click();
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_NAME);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_NAME, methodName);
		     	 		element.click();
		     	 	
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_DESCRIPTION);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_DESCRIPTION, methodName);
		     	 		element.click();
		     	 		
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_TECHNOLOGY_VERSION);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_TECHNOLOGY_VERSION, methodName);
		     	 		element.click();
		     	 	
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_VERSION_COMMENT);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_VERSION_COMMENT, methodName);
		     	 		element.click();
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_SAVE_BUTTON);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_SAVE_BUTTON, methodName);
		     	 		element.click();
		     	 		
		     	 		
		     	 		isTextPresent(adminUIConstants.CREATE_EMPTY_ARCHETYPE_NAME_ERROR_MSG);
		     	 		isTextPresent(adminUIConstants.CREATE_EMPTY_ARCHETYPE_TECHNOLOGY_VERSION_ERROR_MSG);
		     	 		isTextPresent(adminUIConstants.CREATE_EMPTY_ARCHETYPE_JAR_ERROR_MSG);
		     	 		isTextPresent(adminUIConstants.CREATE_EMPTY_ARCHETYPE_FEATURE_ERROR_MSG);
		     	 	
		     	 		
		     	 		
		     	 		
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_CANCEL_BUTTON);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_CANCEL_BUTTON, methodName);
		     	 		element.click();
		     	 		     
		      }      
		  
		  
		 catch (Exception e) {
		 	
		 	e.printStackTrace();
		 }
		 }
		  
		 
		 public void  createInvalidArchetypes(String methodName)
		 {
		  if (StringUtils.isEmpty(methodName)) {
		      methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		     		 log.info("@testAdminCustomerPage::******executing createInvalidArchetypes scenario****");
		  }
		      try {
		     	 		   	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_CREATE_BUTTON);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_CREATE_BUTTON, methodName);
		     	 		element.click();
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_NAME);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_NAME, methodName);
		     	 		element.click();
		     	 		element.sendKeys(adminUIConstants.CREATE_WEB_LAYER_ARCHETYPES_NAME_VALUE);
		     	 		
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_DESCRIPTION);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_DESCRIPTION, methodName);
		     	 		element.click();
		     	 		element.sendKeys(adminUIConstants.CREATE_WEB_LAYER_ARCHETYPES_DESCRIPTION_VALUE);
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_TECHNOLOGY_VERSION);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_TECHNOLOGY_VERSION, methodName);
		     	 		element.click();
		     	 		element.sendKeys(adminUIConstants.CREATE_WEB_LAYER_ARCHETYPES_TECHNOLGY_VERSION_VALUE);
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_VERSION_COMMENT);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_VERSION_COMMENT, methodName);
		     	 		element.click();
		     	 		element.sendKeys(adminUIConstants.CREATE_WEB_LAYER_ARCHETYPES_VERSION_COMMENT_VALUE);
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_TYPE);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_TYPE, methodName);
		     	 		element.click();
		     	 		element.sendKeys(adminUIConstants.CREATE_WEB_LAYER_ARCHETYPES_APPLICATION_TYPE_VALUE);
		     	 		
		     	 		/*Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_UPLOAD_JAR);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_UPLOAD_JAR, methodName);
		     	 		element.click();
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_UPLOAD_PLUGIN_JAR);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_UPLOAD_PLUGIN_JAR, methodName);
		     	 		element.click();*/

		     	 		/*Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_ALL);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_ALL, methodName);
		     	 		element.click();*/
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_CODE);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_CODE, methodName);
		     	 		element.click();
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_BUILD);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_BUILD, methodName);
		     	 		element.click();
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_DEPLOY);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_DEPLOY, methodName);
		     	 		element.click();
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_RUN_AGAINST_SOURCE);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_RUN_AGAINST_SOURCE, methodName);
		     	 		element.click();
		     	 		
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_UNIT_TEST);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_UNIT_TEST, methodName);
		     	 		element.click();
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_FUNCTONAL_TEST);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_FUNCTONAL_TEST, methodName);
		     	 		element.click();
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_PERFORMANCE_TEST);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_PERFORMANCE_TEST, methodName);
		     	 		element.click();
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_LOAD_TEST);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_LOAD_TEST, methodName);
		     	 		element.click();
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_REPORTS);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_REPORTS, methodName);
		     	 		element.click();
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_CI);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_CI, methodName);
		     	 		element.click();
		     	 		
		     	 		/*Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_ALL);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_ALL, methodName);
		     	 		element.click();
		     	 		*/
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_PROJECT_INFO_REPORT);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_PROJECT_INFO_REPORT, methodName);
		     	 		element.click();
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JAVA_DOC_REPORT);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JAVA_DOC_REPORT, methodName);
		     	 		element.click();
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_COBERTURA_REPORT);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_COBERTURA_REPORT, methodName);
		     	 		element.click();
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JDEPEND_REPORT);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JDEPEND_REPORT, methodName);
		     	 		element.click();
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JXR_REPORT);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JXR_REPORT, methodName);
		     	 		element.click();
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_PMD_REPORT);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_PMD_REPORT, methodName);
		     	 		element.click();
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_SURFIRE_REPORT);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_SURFIRE_REPORT, methodName);
		     	 		element.click();
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_SAVE_BUTTON);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_SAVE_BUTTON, methodName);
		     	 		element.click();
		     	 		
		     	 		Thread.sleep(2000);
		     	 		element=getXpathWebElement(uiConstants.ARCHETYPES_CANCEL_BUTTON);
		     	 		waitForElementPresent(uiConstants.ARCHETYPES_CANCEL_BUTTON, methodName);
		     	 		element.click();
		     	 		
		     	 		
		     	 		
		     	 		
		     
		      }      
		  
		  
		 catch (Exception e) {
		 	
		 	e.printStackTrace();
		 }
		 }
		  
		 		


public void  createWebLayerArchetypes(String methodName)
{
 if (StringUtils.isEmpty(methodName)) {
     methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		 log.info("@testAdminCustomerPage::******executing createWebLayerArchetypes scenario****");
 }
     try {
    	 		   	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_CREATE_BUTTON);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_CREATE_BUTTON, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_NAME);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_NAME, methodName);
    	 		element.click();
    	 		element.sendKeys(adminUIConstants.CREATE_WEB_LAYER_ARCHETYPES_NAME_VALUE);
    	 		
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_DESCRIPTION);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_DESCRIPTION, methodName);
    	 		element.click();
    	 		element.sendKeys(adminUIConstants.CREATE_WEB_LAYER_ARCHETYPES_DESCRIPTION_VALUE);
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_TECHNOLOGY_VERSION);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_TECHNOLOGY_VERSION, methodName);
    	 		element.click();
    	 		element.sendKeys(adminUIConstants.CREATE_WEB_LAYER_ARCHETYPES_TECHNOLGY_VERSION_VALUE);
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_VERSION_COMMENT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_VERSION_COMMENT, methodName);
    	 		element.click();
    	 		element.sendKeys(adminUIConstants.CREATE_WEB_LAYER_ARCHETYPES_VERSION_COMMENT_VALUE);
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_TYPE);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_TYPE, methodName);
    	 		element.click();
    	 		element.sendKeys(adminUIConstants.CREATE_WEB_LAYER_ARCHETYPES_APPLICATION_TYPE_VALUE);
    	 		
    	 		/*Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_UPLOAD_JAR);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_UPLOAD_JAR, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_UPLOAD_PLUGIN_JAR);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_UPLOAD_PLUGIN_JAR, methodName);
    	 		element.click();*/

    	 		/*Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_ALL);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_ALL, methodName);
    	 		element.click();*/
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_CODE);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_CODE, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_BUILD);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_BUILD, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_DEPLOY);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_DEPLOY, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_RUN_AGAINST_SOURCE);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_RUN_AGAINST_SOURCE, methodName);
    	 		element.click();
    	 		
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_UNIT_TEST);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_UNIT_TEST, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_FUNCTONAL_TEST);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_FUNCTONAL_TEST, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_PERFORMANCE_TEST);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_PERFORMANCE_TEST, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_LOAD_TEST);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_LOAD_TEST, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_REPORTS);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_REPORTS, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_CI);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_CI, methodName);
    	 		element.click();
    	 		
    	 		/*Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_ALL);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_ALL, methodName);
    	 		element.click();
    	 		*/
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_PROJECT_INFO_REPORT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_PROJECT_INFO_REPORT, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JAVA_DOC_REPORT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JAVA_DOC_REPORT, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_COBERTURA_REPORT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_COBERTURA_REPORT, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JDEPEND_REPORT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JDEPEND_REPORT, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JXR_REPORT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JXR_REPORT, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_PMD_REPORT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_PMD_REPORT, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_SURFIRE_REPORT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_SURFIRE_REPORT, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_SAVE_BUTTON);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_SAVE_BUTTON, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_CANCEL_BUTTON);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_CANCEL_BUTTON, methodName);
    	 		element.click();
    	 		
    	 		
    	 		
    	 		
    
     }      
 
 
catch (Exception e) {
	
	e.printStackTrace();
}
}
 

public void  createApplicationLayerArchetypes(String methodName)
{
 if (StringUtils.isEmpty(methodName)) {
     methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		 log.info("@testAdminCustomerPage::******executing createApplicationLayerArchetypes scenario****");
 }
     try {
    	 		   	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_CREATE_BUTTON);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_CREATE_BUTTON, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_NAME);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_NAME, methodName);
    	 		element.click();
    	 		element.sendKeys(adminUIConstants.CREATE_APPLICATION_LAYER_ARCHETYPES_NAME_VALUE);
    	 		
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_DESCRIPTION);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_DESCRIPTION, methodName);
    	 		element.click();
    	 		element.sendKeys(adminUIConstants.CREATE_APPLICATION_LAYER_ARCHETYPES_DESCRIPTION_VALUE);
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_TECHNOLOGY_VERSION);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_TECHNOLOGY_VERSION, methodName);
    	 		element.click();
    	 		element.sendKeys(adminUIConstants.CREATE_APPLICATION_LAYER_ARCHETYPES_TECHNOLGY_VERSION_VALUE);
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_VERSION_COMMENT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_VERSION_COMMENT, methodName);
    	 		element.click();
    	 		element.sendKeys(adminUIConstants.CREATE_APPLICATION_LAYER_ARCHETYPES_VERSION_COMMENT_VALUE);
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_TYPE);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_TYPE, methodName);
    	 		element.click();
    	 		element.sendKeys(adminUIConstants.CREATE_APPLICATION_LAYER_ARCHETYPES_APPLICATION_TYPE_VALUE);
    	 		
    	 	/*	Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_UPLOAD_JAR);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_UPLOAD_JAR, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_UPLOAD_PLUGIN_JAR);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_UPLOAD_PLUGIN_JAR, methodName);
    	 		element.click();*/

    	 		/*Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_ALL);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_ALL, methodName);
    	 		element.click();*/
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_CODE);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_CODE, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_BUILD);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_BUILD, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_DEPLOY);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_DEPLOY, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_RUN_AGAINST_SOURCE);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_RUN_AGAINST_SOURCE, methodName);
    	 		element.click();
    	 		
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_UNIT_TEST);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_UNIT_TEST, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_FUNCTONAL_TEST);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_FUNCTONAL_TEST, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_PERFORMANCE_TEST);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_PERFORMANCE_TEST, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_LOAD_TEST);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_LOAD_TEST, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_REPORTS);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_REPORTS, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_CI);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_CI, methodName);
    	 		element.click();
    	 		
    	 		/*Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_ALL);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_ALL, methodName);
    	 		element.click();
    	 		*/
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_PROJECT_INFO_REPORT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_PROJECT_INFO_REPORT, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JAVA_DOC_REPORT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JAVA_DOC_REPORT, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_COBERTURA_REPORT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_COBERTURA_REPORT, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JDEPEND_REPORT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JDEPEND_REPORT, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JXR_REPORT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JXR_REPORT, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_PMD_REPORT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_PMD_REPORT, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_SURFIRE_REPORT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_SURFIRE_REPORT, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_SAVE_BUTTON);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_SAVE_BUTTON, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_CANCEL_BUTTON);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_CANCEL_BUTTON, methodName);
    	 		element.click();
    	 		
    	 		
    	 		
    	 		
    
     }      
 
 
catch (Exception e) {
	
	e.printStackTrace();
}
}
 

public void  createMobileLayerArchetypes(String methodName)
{
 if (StringUtils.isEmpty(methodName)) {
     methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		 log.info("@testAdminCustomerPage::******executing createMobileLayerArchetypes scenario****");
 }
     try {
    	 		   	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_CREATE_BUTTON);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_CREATE_BUTTON, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_NAME);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_NAME, methodName);
    	 		element.click();
    	 		element.sendKeys(adminUIConstants.CREATE_WEB_LAYER_ARCHETYPES_NAME_VALUE);
    	 		
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_DESCRIPTION);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_DESCRIPTION, methodName);
    	 		element.click();
    	 		element.sendKeys(adminUIConstants.CREATE_WEB_LAYER_ARCHETYPES_DESCRIPTION_VALUE);
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_TECHNOLOGY_VERSION);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_TECHNOLOGY_VERSION, methodName);
    	 		element.click();
    	 		element.sendKeys(adminUIConstants.CREATE_WEB_LAYER_ARCHETYPES_TECHNOLGY_VERSION_VALUE);
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_VERSION_COMMENT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_VERSION_COMMENT, methodName);
    	 		element.click();
    	 		element.sendKeys(adminUIConstants.CREATE_WEB_LAYER_ARCHETYPES_VERSION_COMMENT_VALUE);
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_TYPE);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_TYPE, methodName);
    	 		element.click();
    	 		element.sendKeys(adminUIConstants.CREATE_WEB_LAYER_ARCHETYPES_APPLICATION_TYPE_VALUE);
    	 		
    	 	/*	Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_UPLOAD_JAR);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_UPLOAD_JAR, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_UPLOAD_PLUGIN_JAR);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_UPLOAD_PLUGIN_JAR, methodName);
    	 		element.click();*/

    	 		/*Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_ALL);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_ALL, methodName);
    	 		element.click();*/
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_CODE);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_CODE, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_BUILD);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_BUILD, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_DEPLOY);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_DEPLOY, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_RUN_AGAINST_SOURCE);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_RUN_AGAINST_SOURCE, methodName);
    	 		element.click();
    	 		
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_UNIT_TEST);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_UNIT_TEST, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_FUNCTONAL_TEST);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_FUNCTONAL_TEST, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_PERFORMANCE_TEST);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_PERFORMANCE_TEST, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_LOAD_TEST);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_LOAD_TEST, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_REPORTS);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_REPORTS, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICATION_FEATURES_CI);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICATION_FEATURES_CI, methodName);
    	 		element.click();
    	 		
    	 		/*Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_ALL);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_ALL, methodName);
    	 		element.click();
    	 		*/
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_PROJECT_INFO_REPORT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_PROJECT_INFO_REPORT, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JAVA_DOC_REPORT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JAVA_DOC_REPORT, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_COBERTURA_REPORT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_COBERTURA_REPORT, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JDEPEND_REPORT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JDEPEND_REPORT, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JXR_REPORT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_JXR_REPORT, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_PMD_REPORT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_PMD_REPORT, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_SURFIRE_REPORT);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_APPLICABLE_REPORTS_SURFIRE_REPORT, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_SAVE_BUTTON);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_SAVE_BUTTON, methodName);
    	 		element.click();
    	 		
    	 		Thread.sleep(2000);
    	 		element=getXpathWebElement(uiConstants.ARCHETYPES_CANCEL_BUTTON);
    	 		waitForElementPresent(uiConstants.ARCHETYPES_CANCEL_BUTTON, methodName);
    	 		element.click();
    	 		
    	 		
    	 		
    	 		
    
     }      
 
 
catch (Exception e) {
	
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
	public boolean isTextPresent(String text)
	{
		if(text!=null)
		{
			boolean value=driver.findElement(By.tagName("body")).getText().contains(text);
			System.out.println("--------TextCheck value---->"+text+"------------Result is-------------"+value); 
			Assert.assertTrue(value);
			return value;
		}
		 else
	        {
	            throw new RuntimeException("---- Text not present----");
	        }
	       
	}
       
     
       
       
  
}
