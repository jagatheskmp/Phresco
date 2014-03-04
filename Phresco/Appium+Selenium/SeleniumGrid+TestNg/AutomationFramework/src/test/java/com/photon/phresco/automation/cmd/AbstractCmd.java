package com.photon.phresco.automation.cmd;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;

public abstract class AbstractCmd implements ICmd {

	static Logger log = Logger.getLogger("AbstractCmd");
	
	WebDriver driver;

	/**
	 * This method is to get the xpath of the Web element
	 * 
	 * @param xpath It is the identifier of an UI object
	 */
	protected void waitForElementPresent(String methodName, String locator, long waitingTime) throws IOException, Exception {
		try {

			log.info("ENTERING WAIT FOR ELEMENT PRESENT driver " + driver);
			waitForSeconds(waitingTime);
			By by = By.xpath(locator);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			waitForSeconds(ONE_THOUSAND_SECONDS);
			wait.until(presenceOfElementLocated(by));
		}

		catch (Exception e) {
			log.info("PRESENCE OF ELEMENT LOCATED" + e.getMessage());
			captureScreenShot(methodName);
			Assert.assertNull(e);

		}
	}
	
	/**
	 * This method is to get the xpath of the Web element
	 * 
	 * @param xpath
	 *            It is the identifier of an UI object
	 */
	protected WebElement getXpathWebElement(String xpath) throws Exception {
		log.info(" ENTERING XPATH WEBELEMENT "+xpath);

		try {
			return driver.findElement(By.xpath(xpath));

		} catch (Throwable t) {
			log.info("THROWABLE EXCEPTION IN GETXPATHWEBELEMENT"
					+ t.getMessage());

		}
		return null;
	}

	
	/**
	 * This method will wait for the given time
	 * 
	 * @param value
	 * Time to wait
	 */
	protected void waitForSeconds(long value) {
		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
			log.info("INTERRUPTEDEXCEPTION IN WAITFORSECONDS " + e.getMessage());
		}

	}
	
	/**
	 * This method will check for presence of specific xpath or Id of the
	 * Webelement
	 */
	protected static Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
		log.info("ENTERING PRESENCE OF ELEMENT LOCATED");
		return new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
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
	protected void captureScreenShot(String methodName) {
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



}
