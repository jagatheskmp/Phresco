package com.photon.phresco.eshop.screen;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.photon.phresco.eshop.model.TestNgParameters;
import com.photon.phresco.selenium.util.MagicNumbers;

public class ImplementationScreen extends AbstractBaseScreen {

	private Log log = LogFactory.getLog("ImplementationScreen");

	public ImplementationScreen(TestNgParameters tngparam) {
		super(tngparam);
	}

	public void login(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getHomeButton().trim(), methodName);
			getWebElement(uiId.getHomeButton().trim());
			click();
			waitForElementPresent(uiId.getLoginButton().trim(), methodName);
			getWebElement(uiId.getLoginButton().trim());
			click();
			waitForElementPresent(uiId.getLoginEmail().trim(), methodName);
			getWebElement(uiId.getLoginEmail().trim());
			sendKeys(uiDat.getLoginEmail().trim());
			waitForElementPresent(uiId.getLoginPassword().trim(), methodName);
			getWebElement(uiId.getLoginPassword().trim());
			sendKeys(uiDat.getLoginPassword().trim());
			waitForElementPresent(uiId.getSubmitButton().trim(), methodName);
			getWebElement(uiId.getSubmitButton().trim());
			click();
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			isTextPresent(uiDat.getLoginFailedMsg(), methodName);
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
		} catch (Exception e) {
			log.info("EXCEPTION IN LOGIN " + e.getMessage());
		}
	}


	public void register(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.ONE_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getLoginButton().trim(), methodName);
			getWebElement(uiId.getLoginButton().trim());
			click();
			waitForElementPresent(uiId.getRegisterButton().trim(), methodName);
			getWebElement(uiId.getRegisterButton().trim());
			click();
			waitForElementPresent(uiId.getRegFirstName().trim(), methodName);
			getWebElement(uiId.getRegFirstName().trim());
			sendKeys(uiDat.getRegFirstName());
			waitForElementPresent(uiId.getRegLastName().trim(), methodName);
			getWebElement(uiId.getRegLastName().trim());
			sendKeys(uiDat.getRegLastName());
			waitForElementPresent(uiId.getRegEmail().trim(), methodName);
			getWebElement(uiId.getRegEmail().trim());
			sendKeys(uiDat.getRegEmail());
			waitForElementPresent(uiId.getRegPassword().trim(), methodName);
			getWebElement(uiId.getRegPassword().trim());
			sendKeys(uiDat.getRegPassword());
			waitForElementPresent(uiId.getRegPhoneNo().trim(), methodName);
			getWebElement(uiId.getRegPhoneNo().trim());
			sendKeys(uiDat.getRegPhoneNo());
			waitForElementPresent(uiId.getRegisterButton().trim(), methodName);
			getWebElement(uiId.getRegisterButton().trim());
			click();
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
		} catch (Exception e) {
			log.info("EXCEPTION IN REGISTER " + e.getMessage());
		}
	}

	public void browseTelevision(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getHomeButton().trim(), methodName);
			getWebElement(uiId.getHomeButton().trim());
			click();
			waitForElementPresent(uiId.getBrowseButton().trim(), methodName);
			getWebElement(uiId.getBrowseButton().trim());
			click();
			waitForElementPresent(uiId.getTelevision().trim(), methodName);
			getWebElement(uiId.getTelevision().trim());
			click();
			waitForElementPresent(uiId.getLgElectronic().trim(), methodName);
			getWebElement(uiId.getLgElectronic().trim());
			click();
			waitForElementPresent(uiId.getAddToCart().trim(), methodName);
			getWebElement(uiId.getAddToCart().trim());
			click();
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
		} catch (Exception e) {
			log.info("EXCEPTION IN BROWSE TELEVISION " + e.getMessage());
		}
	}

	public void browseComputer(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getHomeButton().trim(), methodName);
			getWebElement(uiId.getHomeButton().trim());
			click();
			waitForElementPresent(uiId.getBrowseButton().trim(), methodName);
			getWebElement(uiId.getBrowseButton().trim());
			click();
			waitForElementPresent(uiId.getComputer().trim(), methodName);
			getWebElement(uiId.getComputer().trim());
			click();
			waitForElementPresent(uiId.getApple().trim(), methodName);
			getWebElement(uiId.getApple().trim());
			click();
			waitForElementPresent(uiId.getAddToCart().trim(), methodName);
			getWebElement(uiId.getAddToCart().trim());
			click();
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
		} catch (Exception e) {
			log.info("EXCEPTION IN BROWSE COMPUTER " + e.getMessage());
		}
	}

	public void browseMobilePhone(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getHomeButton().trim(), methodName);
			getWebElement(uiId.getHomeButton().trim());
			click();
			waitForElementPresent(uiId.getBrowseButton().trim(), methodName);
			getWebElement(uiId.getBrowseButton().trim());
			click();
			waitForElementPresent(uiId.getMobilePhone().trim(), methodName);
			getWebElement(uiId.getMobilePhone().trim());
			click();
			waitForElementPresent(uiId.getAppleIphone().trim(), methodName);
			getWebElement(uiId.getAppleIphone().trim());
			click();
			waitForElementPresent(uiId.getAddToCart().trim(), methodName);
			getWebElement(uiId.getAddToCart().trim());
			click();
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
		} catch (Exception e) {
			log.info("EXCEPTION IN BROWSE MOBILEPHONE " + e.getMessage());
		}
	}

	public void browseAudioDevice(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getHomeButton().trim(), methodName);
			getWebElement(uiId.getHomeButton().trim());
			click();
			waitForElementPresent(uiId.getBrowseButton().trim(), methodName);
			getWebElement(uiId.getBrowseButton().trim());
			click();
			waitForElementPresent(uiId.getAudioDevice().trim(), methodName);
			getWebElement(uiId.getAudioDevice().trim());
			click();
			waitForElementPresent(uiId.getMicroPhone().trim(), methodName);
			getWebElement(uiId.getMicroPhone().trim());
			click();
			waitForElementPresent(uiId.getAddToCart().trim(), methodName);
			getWebElement(uiId.getAddToCart().trim());
			click();
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
		} catch (Exception e) {
			log.info("EXCEPTION IN BROWSE AUDIODEVICE " + e.getMessage());
		}
	}

	public void browseCamera(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getHomeButton().trim(), methodName);
			getWebElement(uiId.getHomeButton().trim());
			click();
			waitForElementPresent(uiId.getBrowseButton().trim(), methodName);
			getWebElement(uiId.getBrowseButton().trim());
			click();
			waitForElementPresent(uiId.getCamera().trim(), methodName);
			getWebElement(uiId.getCamera().trim());
			click();
			waitForElementPresent(uiId.getCanon().trim(), methodName);
			getWebElement(uiId.getCanon().trim());
			click();
			waitForElementPresent(uiId.getAddToCart().trim(), methodName);
			getWebElement(uiId.getAddToCart().trim());
			click();
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
		} catch (Exception e) {
			log.info("EXCEPTION IN BROWSE CAMERA " + e.getMessage());
		}
	}

	public void browseTablet(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getHomeButton().trim(), methodName);
			getWebElement(uiId.getHomeButton().trim());
			click();
			waitForElementPresent(uiId.getBrowseButton().trim(), methodName);
			getWebElement(uiId.getBrowseButton().trim());
			click();
			waitForElementPresent(uiId.getTablet().trim(), methodName);
			getWebElement(uiId.getTablet().trim());
			click();
			click();
			waitForElementPresent(uiId.getAcer().trim(), methodName);
			getWebElement(uiId.getAcer().trim());
			click();
			waitForElementPresent(uiId.getAddToCart().trim(), methodName);
			getWebElement(uiId.getAddToCart().trim());
			click();
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
		} catch (Exception e) {
			log.info("EXCEPTION IN BROWSE TABLET " + e.getMessage());
		}
	}

	public void browseMovieAndMusic(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getHomeButton().trim(), methodName);
			getWebElement(uiId.getHomeButton().trim());
			click();
			waitForElementPresent(uiId.getBrowseButton().trim(), methodName);
			getWebElement(uiId.getBrowseButton().trim());
			click();
			waitForElementPresent(uiId.getMovieMusic().trim(), methodName);
			getWebElement(uiId.getMovieMusic().trim());
			click();
			click();
			waitForElementPresent(uiId.getSony().trim(), methodName);
			getWebElement(uiId.getSony().trim());
			click();
			waitForElementPresent(uiId.getAddToCart().trim(), methodName);
			getWebElement(uiId.getAddToCart().trim());
			click();
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
		} catch (Exception e) {
			log.info("EXCEPTION IN BROWSE MOVIEANDMUSIC " + e.getMessage());
		}
	}

	public void browseVideoGames(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getHomeButton().trim(), methodName);
			getWebElement(uiId.getHomeButton().trim());
			click();
			waitForElementPresent(uiId.getBrowseButton().trim(), methodName);
			getWebElement(uiId.getBrowseButton().trim());
			click();
			waitForElementPresent(uiId.getVideoGames().trim(), methodName);
			getWebElement(uiId.getVideoGames().trim());
			click();
			waitForElementPresent(uiId.getModernWarfare().trim(), methodName);
			getWebElement(uiId.getModernWarfare().trim());
			click();
			waitForElementPresent(uiId.getAddToCart().trim(), methodName);
			getWebElement(uiId.getAddToCart().trim());
			click();
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
		} catch (Exception e) {
			log.info("EXCEPTION IN BROWSE VIDEOGAMES " + e.getMessage());
		}
	}

	public void browseMp3Players(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getHomeButton().trim(), methodName);
			getWebElement(uiId.getHomeButton().trim());
			click();
			waitForElementPresent(uiId.getBrowseButton().trim(), methodName);
			getWebElement(uiId.getBrowseButton().trim());
			click();
			waitForElementPresent(uiId.getMp3Player().trim(), methodName);
			getWebElement(uiId.getMp3Player().trim());
			click();
			waitForElementPresent(uiId.getAluratek().trim(), methodName);
			getWebElement(uiId.getAluratek().trim());
			click();
			waitForElementPresent(uiId.getAddToCart().trim(), methodName);
			getWebElement(uiId.getAddToCart().trim());
			click();
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
		} catch (Exception e) {
			log.info("EXCEPTION IN BROWSE MP3PLAYERS " + e.getMessage());
		}
	}

	public void browseAccessories(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getHomeButton().trim(), methodName);
			getWebElement(uiId.getHomeButton().trim());
			click();
			waitForElementPresent(uiId.getBrowseButton().trim(), methodName);
			getWebElement(uiId.getBrowseButton().trim());
			click();
			waitForElementPresent(uiId.getAccessories().trim(), methodName);
			getWebElement(uiId.getAccessories().trim());
			click();
			waitForElementPresent(uiId.getBracket1().trim(), methodName);
			getWebElement(uiId.getBracket1().trim());
			click();
			waitForElementPresent(uiId.getAddToCart().trim(), methodName);
			getWebElement(uiId.getAddToCart().trim());
			click();
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
		} catch (Exception e) {
			log.info("EXCEPTION IN BROWSE ACCESSORIES " + e.getMessage());
		}
	}

	public void checkout(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getCheckOutButton().trim(), methodName);
			getWebElement(uiId.getCheckOutButton().trim());
			click();
			click();
			waitForElementPresent(uiId.getBillInfoEmail(), methodName);
			getWebElement(uiId.getBillInfoEmail());                
			sendKeys(uiDat.getBillInfoEmailValue());
			waitForElementPresent(uiId.getDeliveryInfoButton(),methodName);
			getWebElement(uiId.getDeliveryInfoButton());
			click();
			waitForElementPresent(uiId.getBillInfoFirstName(), methodName);
			getWebElement(uiId.getBillInfoFirstName());
			sendKeys(uiDat.getBillInfoFirstNameValue());
			waitForElementPresent(uiId.getBillInfoLastName(), methodName);
			getWebElement(uiId.getBillInfoLastName());
			sendKeys(uiDat.getBillInfoLastNameValue());
			waitForElementPresent(uiId.getBillInfoCompany(), methodName);
			getWebElement(uiId.getBillInfoCompany());
			sendKeys(uiDat.getBillInfoCompanyValue());
			waitForElementPresent(uiId.getBillInfoAddress1(), methodName);
			getWebElement(uiId.getBillInfoAddress1());
			sendKeys(uiDat.getBillInfoAddress1Value());
			waitForElementPresent(uiId.getBillInfoAddress2(), methodName);
			getWebElement(uiId.getBillInfoAddress2());
			sendKeys(uiDat.getBillInfoAddress2Value());
			waitForElementPresent(uiId.getBillInfoCity(), methodName);
			getWebElement(uiId.getBillInfoCity());
			sendKeys(uiDat.getBillInfoCityValue());
			waitForElementPresent(uiId.getBillInfoState(), methodName);
			getWebElement(uiId.getBillInfoState());
			sendKeys(uiDat.getBillInfoStateValue());
			waitForElementPresent(uiId.getBillInfoPostCode(), methodName);
			getWebElement(uiId.getBillInfoPostCode());
			sendKeys(uiDat.getBillInfoPostCodeValue());
			waitForElementPresent(uiId.getBillInfoPhoneNumber(), methodName);
			getWebElement(uiId.getBillInfoPhoneNumber());
			sendKeys(uiDat.getBillInfoPhoneNumberValue());
			waitForElementPresent(uiId.getBillInfoButton(),methodName);
			getWebElement(uiId.getBillInfoButton());
			click();
			waitForElementPresent(uiId.getBillCheckBox(),methodName);
			getWebElement(uiId.getBillCheckBox());
			click();
			waitForElementPresent(uiId.getReviewOrder(), methodName);
			getWebElement(uiId.getReviewOrder());
			click();
			waitForElementPresent(uiId.getSubmitOrder(), methodName);
			getWebElement(uiId.getSubmitOrder());
			click();
			waitForGivenTime(MagicNumbers.TWO_THOUSAND_SECONDS);
			
			
		} catch (Exception e) {
			log.info("EXCEPTION IN BROWSE CHECKOUT " + e.getMessage());
		}
	}



	public void driverQuit() {
		try {
			quitDriver();
		} catch (Exception e) {
			log.info("EXCEPTION IN DRIVER QUIT " + e.getMessage());
		}
	}
}
