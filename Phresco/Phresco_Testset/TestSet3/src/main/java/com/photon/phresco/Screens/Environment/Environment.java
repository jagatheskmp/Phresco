package com.photon.phresco.Screens.Environment;
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
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;
import com.photon.phresco.uiconstants.EnvironmentConstantsXml;


public class Environment {

	private static WebDriver driver;
	private ChromeDriverService chromeService;
	private Log log = LogFactory.getLog("BaseScreen");
	private static WebElement element;	

	private PhrescoUiConstantsXml phrsc;
	
	private static phresco_env_config phrscEnv;
	private UserInfoConstants userInfo;
	
	

	public Environment() {

	}

	public Environment(String selectedBrowser, String applicationURL, String applicatinContext,phresco_env_config phrscEnv,PhrescoUiConstantsXml phrsc,UserInfoConstants userInfo,PhrescoFrameworkData phrscData)
			throws ScreenException {
		
		Environment.phrscEnv=phrscEnv;
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
			Thread.sleep(2000);

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
            waitForElementPresent(phrsc.ENV_OK,methodName);
            getXpathWebElement(phrsc.ENV_OK);
            click();
            Thread.sleep(5000);
            
		} catch (InterruptedException e) {

			e.printStackTrace();
		}		
	}
	
	public  void GlobalSettingsConfiguration(String methodName,EnvironmentConstantsXml envCons) throws Exception {
		if (StringUtils.isEmpty(methodName)) {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		}
		log.info("@Creating GlobalSettings***** GlobalSettingsTab ****");
		try {
			Thread.sleep(2000);	
			waitForElementPresent(phrsc.SETTIND_TAB,methodName);	
			getXpathWebElement(phrsc.SETTIND_TAB);
			click();
			System.out.println("value passes settings");
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_ADD,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_ADD);
			click();
			Thread.sleep(2000);
			System.out.println("value passes add");
           
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_NAME,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_NAME).clear();
			click();
			sendKeys(envCons.CONFIGURATION_SERVER_NAME_VALUE);
			System.out.println("value passes name");
			Thread.sleep(2000);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_DESCRIPTION,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_DESCRIPTION);
			click();
			clear();
			sendKeys(envCons.CONFIGURATION_SERVER_DESCRIPTION_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_ADD_EVIRONMENT_SELECT);
			click();
			sendKeys(envCons.CONFIGURATION_SERVER_ENVIRONMENT_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.ENV_TYPE_VALUE_SERVER,methodName);
			getXpathWebElement(phrsc.ENV_TYPE_VALUE_SERVER);
			click();
			selectText(element,envCons.CONFIGURATION_SERVER_TYPE_VALUE);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.APPLIES_To,methodName);
			getXpathWebElement(phrsc.APPLIES_To);
			click();
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_PROTOCOL,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_PROTOCOL);
			click();
			sendKeys(envCons.CONFIGURATION_SERVER_PROTOCOL_HTTP_VALUE);
			Thread.sleep(2000);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_HOST,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_HOST);
			click();
			sendKeys(envCons.CONFIGURATION_SERVER_HOST_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_PORT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_PORT);
			click();
			sendKeys(envCons.CONFIGURATION_SERVER_PORT_VALUE);
			

			Thread.sleep(2000);
			waitForElementPresent(phrsc.SERVER_TYPE,methodName);
			getXpathWebElement(phrsc.SERVER_TYPE);
			click();
			sendKeys(envCons.SERVERTYPE_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_DEPLOYDIRECTORY,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_DEPLOYDIRECTORY);
			click();
			clear();
			sendKeys(envCons.CONFIGURATION_SERVER_DEPLOY_DIRECTORY_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.CONFIGURATIONS_SERVER_CONTEXT,methodName);
			getXpathWebElement(phrsc.CONFIGURATIONS_SERVER_CONTEXT);
			click();
			clear();
			sendKeys(envCons.CONTEXT_VALUE);
			
			Thread.sleep(2000);
			waitForElementPresent(phrsc.SAVEBTN,methodName);
			getXpathWebElement(phrsc.SAVEBTN);
			click();
			
			Thread.sleep(5000);
			
			waitForElementPresent(phrsc.ENV_STATUS_VERIFICATION,methodName);
			getXpathWebElement(phrsc.ENV_STATUS_VERIFICATION);
			click();
            Thread.sleep(3000);
            
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
