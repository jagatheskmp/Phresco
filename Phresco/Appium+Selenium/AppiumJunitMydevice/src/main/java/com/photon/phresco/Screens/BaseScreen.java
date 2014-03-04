
package com.photon.phresco.Screens;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteStatus;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;
import com.photon.phresco.selenium.util.Constants;
import com.photon.phresco.selenium.util.GetCurrentDir;
import com.photon.phresco.selenium.util.ScreenActionFailedException;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.UserInfoConstants;


public class BaseScreen {

	private RemoteWebDriver driver;
	private Log log = LogFactory.getLog("BaseScreen");
	private WebElement element;
	@SuppressWarnings("unused")
	private UserInfoConstants userInfoConstants;
	private UIConstants uiConstants;
	private PhrescoUiConstants phrescoUiConstants;
	private DesiredCapabilities capabilities;
	private String ipNode;
	private String browsersName;
	private String iPhoneIpAddress ="172.16.27.206:3001";
	private String androidIpAddress ="172.16.22.224:8080";
	URL server;
	private Map<String, String> nodeIpaddress = new HashMap<String, String>();
	private Map<String, String> browserVersionMap = new HashMap<String,String>();
	public BaseScreen() {

	}

	public BaseScreen(String selectedBrowser, String selectedPlatform,
			String applicationURL, String applicationContext,
			UserInfoConstants userInfoConstants, UIConstants uiConstants,
			PhrescoUiConstants phrescoUiConstants) throws AWTException,
			IOException, ScreenActionFailedException {

		this.userInfoConstants = userInfoConstants;
		this.uiConstants = uiConstants;
		this.phrescoUiConstants = phrescoUiConstants;
		try {
			instantiateBrowser(selectedBrowser, selectedPlatform,
					applicationURL, applicationContext);
		} catch (ScreenException e) {
			e.printStackTrace();
		}

	}

	public void instantiateBrowser(String selectedBrowser,
			String selectedPlatform, String applicationURL,
			String applicationContext) throws ScreenException,
			MalformedURLException {


		browsersName = selectedBrowser;

		server = new URL("http://localhost:4444/wd/hub/");
		if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_CHROME)) {
			log.info(" LAUNCHING GOOGLECHROME ");
			try {
				new DesiredCapabilities();
				capabilities = DesiredCapabilities.chrome();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_IE)) {
			log.info(" LAUNCHING INTERNET EXPLORE ");
			try {
				new DesiredCapabilities();
				capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setJavascriptEnabled(true);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_OPERA)) {
			log.info(" LAUNCHING OPERA ");
			try {
				new DesiredCapabilities();
				capabilities = DesiredCapabilities.opera();
				/*capabilities.setCapability("opera.autostart ", true);*/
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_SAFARI)) {
			log.info(" LAUNCHING SAFARI ");
			try {
				new DesiredCapabilities();
				capabilities = DesiredCapabilities.safari();
				capabilities.setCapability("safari.autostart ", true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_FIREFOX)) {
			log.info(" LAUNCHING FIREFOX ");
			new DesiredCapabilities();
			capabilities = DesiredCapabilities.firefox();
		} else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_HEADLESS)) {
			log.info(" LAUNCHING HTMLUNIT ");
			new DesiredCapabilities();
			capabilities = DesiredCapabilities.htmlUnit();
			capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);

		} else if (selectedBrowser.equalsIgnoreCase(Constants.IPHONE_WEBDRIVER)) {
			try {
				log.info(" LAUNCHING IPHONE DRIVER");
				new DesiredCapabilities();
				capabilities = DesiredCapabilities.iphone();
				capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (selectedBrowser.equalsIgnoreCase(Constants.ANDROID)) {
			try {
				log.info(" LAUNCHING ANDROID DRIVER");
				new DesiredCapabilities();
				capabilities = DesiredCapabilities.android();
				capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			throw new ScreenException(
			" Only FireFox,InternetExplore Chrome ,Htmlunit,android webdriver and iPhoneWebdriver works");
		}

		/**
		 * These 3 steps common for all the browsers
		 */

		if (selectedPlatform.equalsIgnoreCase("WINDOWS")) {
			capabilities.setCapability(CapabilityType.PLATFORM,
					Platform.WINDOWS);
		} else if (selectedPlatform.equalsIgnoreCase("LINUX")) {
			capabilities.setCapability(CapabilityType.PLATFORM, Platform.LINUX);
		} else if (selectedPlatform.equalsIgnoreCase("MAC")) {
			capabilities.setCapability(CapabilityType.PLATFORM, Platform.MAC);
		}
		driver = new RemoteWebDriver(server, capabilities);
		//windowResize();
		driver.navigate().to(applicationURL + applicationContext);
	}

	public void windowResize() {

		String resolution = phrescoUiConstants.getResolution();
		if (resolution != null) {
			String[] tokens = resolution.split("x");
			String resolutionX = tokens[0];
			String resolutionY = tokens[1];
			int x = Integer.parseInt(resolutionX);
			int y = Integer.parseInt(resolutionY);
			Dimension screenResolution = new Dimension(x, y);
			driver.manage().window().setSize(screenResolution);
		} else {
			driver.manage().window().maximize();
			RemoteStatus remoteStatus = driver.getRemoteStatus();
			System.out.println("**************remoteStatus::::::"+remoteStatus);
		}
	}



	public void getXpathWebElement(String xpath) throws Exception {
		log.info(" ENTERING XPATH WEBELEMENT ");
		try {

			element = driver.findElement(By.xpath(xpath));
			highlightElement(driver,element);

		} catch (Throwable t) {
			log.info("THROWABLE EXCEPTION IN GETXPATHWEBELEMENT "
					+ t.getMessage());

		}

	}

	public void getIdWebElement(String id) throws ScreenException {
		log.info(" ENTERING ID WEBELEMENT ");
		try {
			element = driver.findElement(By.id(id));

		} catch (Throwable t) {
			log.info("THROWABLE EXCEPTION IN GETIDWEBELEMENT " + t.getMessage());

		}

	}

	public void getcssWebElement(String selector) throws ScreenException {
		log.info(" ENTERING CSS WEBELEMENT ");
		try {
			element = driver.findElement(By.cssSelector(selector));

		} catch (Throwable t) {
			log.info("THROWABLE EXCEPTION IN GETCSSWEBELEMENT "
					+ t.getMessage());

		}

	}

	/**
	 * This method waits for presence of specific xpath or Id of the Web element
	 * and takes screen shot if it is not available
	 * 
	 * @param locator
	 *            It is the Identifier of the UI object
	 * 
	 * @param methodName
	 *            It stores screenshot in the method Name from which the call is
	 *            triggered
	 * 
	 * @param waitingTime
	 *            It is the specifies time to wait
	 */
	public void waitForElementPresent(String locator, String methodName)
	throws IOException, Exception {
		try {

			log.info("ENTERING WAIT FOR ELEMENT PRESENT");
			By by = By.xpath(locator);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(presenceOfElementLocated(by));
		}

		catch (Exception e) {
			log.info("PRESENCE OF ELEMENT LOCATED" + e.getMessage());
			if (!browsersName.equalsIgnoreCase(Constants.BROWSER_HEADLESS)) {
				captureScreenShot(methodName);
			}

			Assert.assertNull(e);

		}
	}

	Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
		log.info("ENTERING PRESENCE OF ELEMENT LOCATED");
		return new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				log.info(" WAITING FOR ELEMENT TO PRESENT ");
				return driver.findElement(locator);

			}

		};

	}

	/**
	 * It will capture the ScreenShot with the given name
	 * 
	 * @param methodName
	 *            It stores screenshot in the method Name from which the call is
	 *            triggered
	 */
	public void captureScreenShot(String methodName) {
		log.info("ENTERING IN CAPTURE SCREENSHOT ");
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		File screenshot = ((TakesScreenshot) augmentedDriver)
		.getScreenshotAs(OutputType.FILE);
		try {

			FileUtils.copyFile(screenshot,
					new File(GetCurrentDir.getCurrentDirectory()
							+ File.separator + methodName + ".png"));
		} catch (Exception e1) {
			log.info("EXCEPTION IN CAPTURE SCREENSHOT " + e1.getMessage());
		}
	}

	/**
	 * This method is to click on a particular Web element
	 */
	public void click() throws Exception {
		log.info("ENTERING CLICK OPERATION");
		try {

			element.click();
		} catch (Throwable t) {
			log.info("THROWABLE EXCEPTION IN CLICK" + t.getMessage());
		}

	}
	public void highlightElement(WebDriver driver, WebElement element) {

		/* for (int i = 0; i < 2; i++) {*/

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].setAttribute('style', arguments[1]);",

				element, "color: yellow;background: cyan; border: 4px solid Red;");

		/*js.executeScript("arguments[0].setAttribute('style', arguments[1]);",

	                element, "");
		 */


		//}

	}



	/**
	 * This method is to clear a particular Text from the Application UI
	 */
	public void clear() throws Exception {
		log.info("ENTERING CLEAR OPERATION");
		try {
			element.clear();
		} catch (Throwable t) {
			log.info("THROWABLE EXCEPTION IN CLEAR" + t.getMessage());
		}

	}

	/**
	 * This method is to verify the presence of particular Text.It will capture
	 * screenshot if the given text is not present
	 * 
	 * @param text
	 *            The text to be found in the UI
	 * 
	 * @param methodName
	 *            It stores screenshot in the method Name from which the call is
	 *            triggered
	 * 
	 * @throws InterruptedException
	 */
	public void isTextPresent(String text, String methodName) {
		log.info("ENTERING TEXT PRESENT");
		if (text != null) {
			boolean value = driver.findElement(By.tagName("body")).getText()
			.contains(text);
			if (value) {
				Assert.assertTrue(value);
			} else if (!value) {
				captureScreenShot(methodName);
				Assert.assertTrue(value);
			}
		} else {
			throw new RuntimeException(" Text is null ");
		}

	}

	/**
	 * This method is to type a particular Text its an alternate of the type
	 * method
	 * 
	 * @param text
	 *            The text to be passed as value for the Text field in the UI
	 */
	public void sendKeys(String text) throws Exception {
		log.info("ENTERING VALUES IN TEXTBOX ");
		try {
			clear();
			element.sendKeys(text);

		} catch (Throwable t) {
			log.info("THROWABLE EXCEPTION IN SENDKEYS" + t.getMessage());
		}
	}

	/**
	 * This method is to click on the submit button
	 */
	public void submit() throws Exception {
		log.info("ENTERING SUBMIT OPERATION");
		try {
			element.submit();
		} catch (Throwable t) {
			log.info("THROWABLE EXCEPTION IN SUBMIT" + t.getMessage());
		}

	}


	public void closeBrowser() {
		log.info(" BROWSER CLOSING ");
		if (driver != null) {
			driver.close();
			driver.quit();
		}

	}


}
