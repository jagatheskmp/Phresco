package com.photon.phresco.automation.util;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;

public class ActionCommand {

	public static final long ONE_THOUSAND_SECONDS = 1000;

	
	protected WebElement element;
//	public WebDriver driver;
//	public String browserName;
//	public String appUrl;
//	public String resolution;
//	public String platForm;
	static Logger log = Logger.getLogger("SeleniumScreen");

	public ActionCommand(String browserName, String platform, String url,
			String resolution) {
//		launchApp = new LaunchApplication();
//		this.browserName = browserName;
//		this.appUrl = url;
//		this.resolution = resolution;
//		this.platForm = platform;
		/*launchApplications(this.browserName, this.platForm, this.appUrl,
				this.resolution);*/
	}

	/**
	 * This method is to get the xpath of the Web element
	 * 
	 * @param xpath
	 *            It is the identifier of an UI object
	 */
	public static WebElement getXpathWebElement(WebDriver driver, String xpath) throws Exception {
		log.info(" ENTERING XPATH WEBELEMENT ");

		try {
			/*if (browserName.toUpperCase().equals("HTMLUNIT")) {

				element = driver.findElement(By.xpath(xpath));

			} else {
				element = driver.findElement(By.xpath(xpath));
			}*/
			return driver.findElement(By.xpath(xpath));

		} catch (Throwable t) {
			log.info("THROWABLE EXCEPTION IN GETXPATHWEBELEMENT"
					+ t.getMessage());

		}
//		return element;
		return null;
	}

	/**
	 * This method is to click on a particular Web element
	 */
	public static void click(WebElement element) throws Exception {
		log.info("ENTERING CLICK OPERATION");
		try {
			element.click();
		} catch (Throwable t) {
			log.info("THROWABLE EXCEPTION IN CLICK" + t.getMessage());
		}

	}

	/**
	 * This method is to clear a particular Text from the Application UI
	 */
	public static void clear(WebElement element) throws Exception {
		log.info("ENTERING CLEAR OPERATION");
		try {
			element.clear();
		} catch (Throwable t) {
			log.info("THROWABLE EXCEPTION IN CLEAR" + t.getMessage());
		}

	}

	/**
	 * This method is to type a particular Text its an alternate of the type
	 * method
	 * 
	 * @param text
	 *            The text to be passed as value for the Text field in the UI
	 */
	public static void sendKeys(WebElement element, String text) throws Exception {
		log.info("ENTERING VALUES IN TEXTBOX OPERATION OR SENDKEYS");
		try {
			clear(element);
			element.sendKeys(text);

		} catch (Throwable t) {
			log.info("THROWABLE EXCEPTION IN SENDKEYS" + t.getMessage());
		}
	}

	/**
	 * This method is to click on the submit button
	 */
	public static void submit(WebElement element) throws Exception {
		log.info("ENTERING SUBMIT OPERATION");
		try {
			element.submit();
		} catch (Throwable t) {
			log.info("THROWABLE EXCEPTION IN SUBMIT" + t.getMessage());
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
	public static void isTextPresent(WebDriver driver, String text, String methodName) {
		log.info("ENTERING TEXT PRESENT");
		if (text != null) {
			boolean value = driver.findElement(By.tagName("body")).getText()
					.contains(text);
			if (value) {
				Assert.assertTrue(value);
			} else if (!value) {
				captureScreenShot(driver, methodName);
				Assert.assertTrue(value);
			}
		} else {
			throw new RuntimeException(" Text is null ");
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
	public static void waitForElementPresent(WebDriver driver, String locator, String methodName,
			long waitingTime) throws IOException, Exception {
		try {

			log.info("ENTERING WAIT FOR ELEMENT PRESENT");
			waitForSeconds(waitingTime);
			By by = By.xpath(locator);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			waitForSeconds(ONE_THOUSAND_SECONDS);
			wait.until(presenceOfElementLocated(by));
		}

		catch (Exception e) {
			log.info("PRESENCE OF ELEMENT LOCATED" + e.getMessage());
			captureScreenShot(driver, methodName);
			Assert.assertNull(e);

		}
	}

	/**
	 * It will capture the ScreenShot with the given name
	 * 
	 * @param methodName
	 *            It stores screenshot in the method Name from which the call is
	 *            triggered
	 */
	public static void captureScreenShot(WebDriver driver, String methodName) {
		log.info("ENTERING IN CAPTURE SCREENSHOT ");
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		File screenshot = ((TakesScreenshot) augmentedDriver)
				.getScreenshotAs(OutputType.FILE);
		try {

			/*FileUtils.copyFile(screenshot,
					new File(GetCurrentDir.getCurrentDirectory()
							+ File.separator + methodName + ".png"));*/
		} catch (Exception e1) {
			log.info("EXCEPTION IN CAPTURE SCREENSHOT " + e1.getMessage());
		}
	}

	/**
	 * This method will check for presence of specific xpath or Id of the
	 * Webelement
	 */
	static Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
		log.info("ENTERING PRESENCE OF ELEMENT LOCATED");
		return new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);

			}

		};

	}

	/**
	 * This method is to select a particular Text from the Application UI
	 * 
	 * @param element
	 *            Instance to the WebElement class
	 * @param textValue
	 *            Text to be selected in the UI
	 */
	public static void selectText(WebElement element, String textValue)
			throws Exception {
		log.info("ENTERING SELECT TEXT");

		Select selObj = new Select(element);
		selObj.selectByVisibleText(textValue);

	}

	/**
	 * This method will check any active Ajax call is available. It will wait
	 * until ajax call is inactive in the specified time
	 * 
	 * @param driver
	 *            It's the currently running driver object
	 * 
	 * @param timeoutInSeconds
	 *            Time to wait for checking the ajax calls
	 */
	public static void waitForAjax(WebDriver driver, int timeoutInSeconds) {
		log.info("ENTERING CHECKING ACTIVE AJAX CALLS ");
		if (driver instanceof JavascriptExecutor) {
			JavascriptExecutor jsDriver = (JavascriptExecutor) driver;

			for (int i = 0; i < timeoutInSeconds; i++) {
				Object numberOfAjaxConnections = jsDriver
						.executeScript("return jQuery.active");
				if (numberOfAjaxConnections instanceof Long) {
					Long n = (Long) numberOfAjaxConnections;
					log.info("Number of active jquery ajax calls: " + n);
					if (n.longValue() == 0L)
						break;
				}
				waitForSeconds(ONE_THOUSAND_SECONDS);
			}
		} else {
			log.info("Web driver: " + driver + " cannot execute javascript");
		}
	}

	/**
	 * This method will wait for the given time
	 * 
	 * @param value
	 *            Time to wait
	 */
	public static void waitForSeconds(long value) {
		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
			log.info("INTERRUPTEDEXCEPTION IN WAITFORSECONDS " + e.getMessage());
		}

	}

	/**
	 * This method sets different resolution for the window
	 */

	public static void windowResize(WebDriver driver, String resolution) {

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
		}
	}

	/**
	 * This method will launch the application
	 * 
	 * @param browsername
	 *            Get the browsername from the suite
	 * 
	 * @param platform
	 *            Get the platform from the suite
	 * 
	 * @param applicationurl
	 *            Get the applicationurl
	 * 
	 * @param resolution
	 *            Get the resolution
	 */
	/*public void launchApplications(String browsername, String platform,
			String applicationurl, String resolution) {
		log.info("ENTERING LAUNCHAPPLICATION");
		try {
			if (StringUtils.isNotEmpty(this.browserName)
					&& StringUtils.isNotEmpty(appUrl)) {

				driver = launchApp.launchBrowser(browsername, platform);
				windowResize(resolution);
				driver.get(applicationurl);
				waitForAjax(driver, 30);
			} else {
				throw new RuntimeException(
						" BROWSERNAME OR BROWSER URL IS NULL ");
			}
		} catch (Exception e) {
			log.info("EXCEPTION IN LAUNCHAPPLICATION" + e.getMessage());
		}

	}*/

}
