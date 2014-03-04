package com.photon.phresco.mydevice.screen;

import java.io.File;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;
import com.photon.phresco.mydevice.enumscreen.AppLauncher;
import com.photon.phresco.mydevice.enumscreen.ElementTypeFinder;
import com.photon.phresco.mydevice.enumscreen.LoadXmlParser;
import com.photon.phresco.mydevice.model.TestNgParameters;
import com.photon.phresco.mydevice.model.UiData;
import com.photon.phresco.mydevice.model.UiIdentifiers;
import com.photon.phresco.mydevice.parser.UiDataParser;
import com.photon.phresco.selenium.util.GetCurrentDir;

public class AbstractBaseScreen {

	private Log log = LogFactory.getLog("AbstractBaseScreen");
	private WebDriver driver;
	private WebElement element;
	private By by;
	protected UiIdentifiers uiId;
	protected UiData uiDat;
	JavascriptExecutor js;
	protected String devices;

	public AbstractBaseScreen(TestNgParameters tngparam) {
		try {
			launchDriver(tngparam);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WebDriver launchDriver(TestNgParameters tngparam){
		try {
			devices = tngparam.getDevice().get("device").toUpperCase();
			uiId = LoadXmlParser.valueOf(
					tngparam.getDevice().get("device").toUpperCase()).execute();
			UiDataParser uiDatp = new UiDataParser();
			uiDat = uiDatp.parseXml("./src/main/resources/data.xml");
			driver = AppLauncher.valueOf(tngparam.getDevice().get("device").toUpperCase()).execute(tngparam);
		} catch (Exception e) {
			log.info("EXCEPTION IN LAUNCHDRIVER " + e.getMessage());
		}
		return driver;
	}

	public void getWebElement(String elementIdentifier){
		log.info(" ENTERING WEBELEMENT ");
		try {
			by = webElementType(elementIdentifier);
			element = driver.findElement(by);

		} catch (Exception e) {
			log.info("EXCEPTION IN GETWEBELEMENT " + e.getMessage());

		}

	}

	public By webElementType(String elementIdentifier){

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
	public void waitForElementPresent(String locator, String methodName){
		log.info("ENTERING WAIT FOR ELEMENT PRESENT");
		try {
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

	Function<WebDriver, WebElement> presenceOfElementLocated(final By locator){
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
	public void captureScreenShot(String methodName){
		log.info("ENTERING IN CAPTURE SCREENSHOT ");
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		File screenshot = ((TakesScreenshot) augmentedDriver)
				.getScreenshotAs(OutputType.FILE);
		try {

			FileUtils.copyFile(screenshot,
					new File(GetCurrentDir.getCurrentDirectory()
							+ File.separator + methodName + ".png"));
		} catch (Exception e) {
			log.info("EXCEPTION IN CAPTURE SCREENSHOT " + e.getMessage());
		}
	}

	/**
	 * This method is to click on a particular Web element
	 */
	public void click(){
		log.info("ENTERING CLICK OPERATION");
		try {
			element.click();
		} catch (Exception e) {
			log.info("EXCEPTION IN CLICK" + e.getMessage());
		}

	}

	/**
	 * This method is to clear a particular Text from the Application UI
	 */
	public void clear(){
		log.info("ENTERING CLEAR OPERATION");
		try {
			element.clear();
		} catch (Exception e) {
			log.info("EXCEPTION IN CLEAR" + e.getMessage());
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
	public void isTextPresent(String text, String methodName){
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

	/**
	 * This method is to type a particular Text its an alternate of the type
	 * method
	 * 
	 * @param text
	 *            The text to be passed as value for the Text field in the UI
	 */
	public void sendKeys(String text){
		log.info("ENTERING VALUES IN TEXTBOX ");
		try {
			element.sendKeys(text);

		} catch (Exception e) {
			log.info("EXCEPTION IN SENDKEYS" + e.getMessage());
		}
	}

	/**
	 * This method is to type a particular keyboard event its an alternate of
	 * the type method
	 * 
	 * @param text
	 *            The text to be passed as value for the Text field in the UI
	 */
	public void keyboardEvent(String text){
		log.info("ENTERING KEYBOARD EVENT VALUES ");
		try {
			element.sendKeys(Keys.valueOf(text.toUpperCase()));

		} catch (Exception e) {
			log.info("EXCEPTION IN KEYBOARD EVENT" + e.getMessage());
		}
	}

	/**
	 * This method is to click on the submit button
	 */
	public void submit(){
		log.info("ENTERING SUBMIT OPERATION");
		try {
			element.submit();
		} catch (Exception e) {
			log.info("EXCEPTION IN SUBMIT" + e.getMessage());
		}

	}

	/**
	 * This method is to click on the submit button
	 */
	public void quitDriver(){
		log.info("ENTERING QUITDRIVER OPERATION");
		try {
			if (driver != null) {
				driver.quit();
			}
		} catch (Exception e) {
			log.info("EXCEPTION IN QUITDRIVER" + e.getMessage());
		}

	}
	
	public void clickCordinates(String cordinates){
		log.info("ENTERING CLICK OPERATION BASED ON CO-ORDINATES ");
		try {
			String [] cord = cordinates.split("X");
			int x = Integer.parseInt(cord[0]);
			int y = Integer.parseInt(cord[1]);
			new Actions(driver).moveToElement(element, x,y).click().perform();

		} catch (Exception e) {
			log.info("EXCEPTION IN CLICK CO-RDINATES" + e.getMessage());
		}
	}

	public void waitForGivenTime(long time){
		log.info("ENTERING WAIT FOR GIVEN TIME");
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			log.info("EXCEPTION IN WAIT FOR GIVEN TIME"+ e.getMessage());
		}

	}

}
