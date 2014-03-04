package com.photon.phresco.eshop.screen;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;
import com.photon.phresco.eshop.enumscreen.AppLauncher;
import com.photon.phresco.eshop.enumscreen.ElementTypeFinder;
import com.photon.phresco.eshop.enumscreen.LoadXmlParser;
import com.photon.phresco.eshop.model.TestNgParameters;
import com.photon.phresco.eshop.model.UiData;
import com.photon.phresco.eshop.model.UiIdentifiers;
import com.photon.phresco.eshop.parser.UiDataParser;
import com.photon.phresco.selenium.util.GetCurrentDir;

public class AbstractBaseScreen {

	private Log log = LogFactory.getLog("AbstractBaseScreen");
	protected WebDriver driver;
	private WebElement element;
	private By by;
	protected UiIdentifiers uiId;
	protected UiData uiDat;
	JavascriptExecutor js;
	private String devices;

	public AbstractBaseScreen(TestNgParameters tngparam) {
		try {
			launchDriver(tngparam);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WebDriver launchDriver(TestNgParameters tngparam) throws Exception {
		devices = tngparam.getDevice().get("device").toUpperCase();
		uiId = LoadXmlParser.valueOf(
				tngparam.getDevice().get("device").toUpperCase()).execute();
		UiDataParser uiDatp = new UiDataParser();
		uiDat = uiDatp.parseXml("./src/main/resources/data.xml");
		return driver = AppLauncher.valueOf(tngparam.getDevice().get("device").toUpperCase()).execute(
						tngparam);
	}

	public void getWebElement(String elementIdentifier) throws Exception {
		log.info(" ENTERING WEBELEMENT ");
		try {
			by = webElementType(elementIdentifier);
			element = driver.findElement(by);

		} catch (Throwable t) {
			log.info("THROWABLE EXCEPTION IN GETWEBELEMENT " + t.getMessage());

		}

	}

	public By webElementType(String elementIdentifier) {

		String elementType = uiId.getElementType().get(elementIdentifier)
				.toUpperCase();
		by = ElementTypeFinder.valueOf(elementType).execute(elementIdentifier);

		return by;

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
			by = webElementType(locator);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(by));
		}

		catch (Exception e) {
			log.info("EXCEPTION IN WAIT FOR ELEMENT PRESENT" + e.getMessage());
			captureScreenShot(methodName);
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
	
	public void doubleClick() throws Exception {
		log.info("ENTERING doubleClick OPERATION");
		try {
			Actions action = new Actions(driver);
			action.doubleClick(element);
			action.perform();
		} catch (Throwable t) {
			log.info("THROWABLE EXCEPTION IN doubleClick" + t.getMessage());
		}

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
			if (devices.equals("ANDROID")) {
				boolean value = driver.findElement(By.tagName("body"))
						.getText().contains(text);
				if (!value) {
					captureScreenShot(methodName);
					Assert.assertTrue(value);
				}
			} else if (devices.equals("IOS")) {
				boolean value = driver.getPageSource().contains(text);
				if (!value) {
					captureScreenShot(methodName);
					Assert.assertTrue(value);
				}
			}
		} else {
			throw new RuntimeException(" Text is null ");
		}
	}

	public void setHighlightElement() {
		for (int i = 0; i < 2; i++) {

			js = (JavascriptExecutor) driver;
			js.executeScript(
					"arguments[0].setAttribute('style', arguments[1]);",
					element,
					"color: blue;background: lime; border: 3px solid red;");
		}
	}

	public void reSetHighlightElement() {
		for (int i = 0; i < 2; i++) {
			js.executeScript(
					"arguments[0].setAttribute('style', arguments[1]);",
					element, "");
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
	 * This method is to type a particular keyboard event its an alternate of
	 * the type method
	 * 
	 * @param text
	 *            The text to be passed as value for the Text field in the UI
	 */
	public void keyboardEvent(String text) throws Exception {
		log.info("ENTERING KEYBOARD EVENT VALUES ");
		try {
			element.sendKeys(Keys.valueOf(text.toUpperCase()));

		} catch (Throwable t) {
			log.info("THROWABLE EXCEPTION IN KEYBOARD EVENT" + t.getMessage());
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

	/**
	 * This method is to click on the submit button
	 */
	public void quitDriver() throws Exception {
		log.info("ENTERING QUITDRIVER OPERATION");
		try {
			if (driver != null) {
				driver.quit();
			}
		} catch (Throwable t) {
			log.info("THROWABLE EXCEPTION IN QUITDRIVER" + t.getMessage());
		}

	}

	public void waitForGivenTime(long time) throws Exception {
		log.info("ENTERING WAIT FOR GIVEN TIME");
		try {
			Thread.sleep(time);
		} catch (Throwable t) {
			log.info("THROWABLE EXCEPTION IN WAIT FOR GIVEN TIME"
					+ t.getMessage());
		}

	}

}
