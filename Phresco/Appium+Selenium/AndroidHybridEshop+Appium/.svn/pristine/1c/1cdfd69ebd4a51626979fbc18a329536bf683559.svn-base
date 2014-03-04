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
			waitForElementPresent(uiId.getRegSubmitButton().trim(), methodName);
			getWebElement(uiId.getRegSubmitButton().trim());
			click();
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
		} catch (Exception e) {
			log.info("EXCEPTION IN REGISTER " + e.getMessage());
		}
	}

	public void browseTelevision(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.TWO_THOUSAND_SECONDS);
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
			waitForGivenTime(MagicNumbers.FIVE_THOUSAND_SECONDS);
			
			for(int i=0;i<=8;i++){
				waitForGivenTime(MagicNumbers.ONE_THOUSAND_SECONDS);
				waitForElementPresent(uiId.getRemoveProduct().trim(), methodName);
				getWebElement(uiId.getRemoveProduct().trim());
				click();
				waitForGivenTime(MagicNumbers.ONE_THOUSAND_SECONDS);
			}
			waitForElementPresent(uiId.getCheckOutButton().trim(), methodName);
			getWebElement(uiId.getCheckOutButton().trim());
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
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			waitForElementPresent(uiId.getBillInfoLastName(), methodName);
			getWebElement(uiId.getBillInfoLastName());
			sendKeys(uiDat.getBillInfoLastNameValue());
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			waitForElementPresent(uiId.getBillInfoCompany(), methodName);
			getWebElement(uiId.getBillInfoCompany());
			sendKeys(uiDat.getBillInfoCompanyValue());
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			waitForElementPresent(uiId.getBillInfoAddress1(), methodName);
			getWebElement(uiId.getBillInfoAddress1());
			sendKeys(uiDat.getBillInfoAddress1Value());
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			waitForElementPresent(uiId.getBillInfoAddress2(), methodName);
			getWebElement(uiId.getBillInfoAddress2());
			sendKeys(uiDat.getBillInfoAddress2Value());
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			waitForElementPresent(uiId.getBillInfoCity(), methodName);
			getWebElement(uiId.getBillInfoCity());
			sendKeys(uiDat.getBillInfoCityValue());
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			waitForElementPresent(uiId.getBillInfoState(), methodName);
			getWebElement(uiId.getBillInfoState());
			sendKeys(uiDat.getBillInfoStateValue());
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			waitForElementPresent(uiId.getBillInfoPostCode(), methodName);
			getWebElement(uiId.getBillInfoPostCode());
			sendKeys(uiDat.getBillInfoPostCodeValue());
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			waitForElementPresent(uiId.getBillInfoPhoneNumber(), methodName);
			getWebElement(uiId.getBillInfoPhoneNumber());
			sendKeys(uiDat.getBillInfoPhoneNumberValue());
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			waitForElementPresent(uiId.getBillInfoButton(),methodName);
			getWebElement(uiId.getBillInfoButton());
			click();
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			waitForElementPresent(uiId.getBillCheckBox(),methodName);
			getWebElement(uiId.getBillCheckBox());
			click();
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			waitForElementPresent(uiId.getReviewOrder(), methodName);
			getWebElement(uiId.getReviewOrder());
			click();
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			waitForElementPresent(uiId.getSubmitOrder(), methodName);
			getWebElement(uiId.getSubmitOrder());
			click();
			waitForGivenTime(MagicNumbers.TWO_THOUSAND_SECONDS);


		} catch (Exception e) {
			log.info("EXCEPTION IN BROWSE CHECKOUT " + e.getMessage());
		}
	}

	public void uiValidationHomeScreen(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.ONE_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getHomeImage().trim(), methodName);
			getWebElement(uiId.getHomeImage().trim());
			getImageSrc(uiDat.getHomeImage());
			waitForElementPresent(uiId.getRegImage().trim(), methodName);
			getWebElement(uiId.getRegImage().trim());
			getImageSrc(uiDat.getRegImage());
			waitForElementPresent(uiId.getMycardImage().trim(), methodName);
			getWebElement(uiId.getMycardImage().trim());
			getImageSrc(uiDat.getMycardImage());
			waitForElementPresent(uiId.getSearchImage().trim(), methodName);
			getWebElement(uiId.getSearchImage().trim());
			getImageSrc(uiDat.getSearchImage());
			waitForElementPresent(uiId.getBrowseImage().trim(), methodName);
			getWebElement(uiId.getBrowseImage().trim());
			getImageSrc(uiDat.getBrowseImage());
			waitForElementPresent(uiId.getLoginImage().trim(), methodName);
			getWebElement(uiId.getLoginImage().trim());
			getImageSrc(uiDat.getLoginImage());
			waitForElementPresent(uiId.getSettingsImage().trim(), methodName);
			getWebElement(uiId.getSettingsImage().trim());
			getImageSrc(uiDat.getSettingsImage());
			waitForElementPresent(uiId.getSpecialOfferImage().trim(), methodName);
			getWebElement(uiId.getSpecialOfferImage().trim());
			getImageSrc(uiDat.getSpecialOfferImage());
			waitForElementPresent(uiId.getEventsImage().trim(), methodName);
			getWebElement(uiId.getEventsImage().trim());
			getImageSrc(uiDat.getEventsImage());
			isTextPresent(uiDat.getHomeText(), methodName);
			isTextPresent(uiDat.getRegText(), methodName);
			isTextPresent(uiDat.getMycardText(), methodName);
			isTextPresent(uiDat.getSearchText(), methodName);
			isTextPresent(uiDat.getBrowseText(), methodName);
			isTextPresent(uiDat.getLoginText(), methodName);
			isTextPresent(uiDat.getSettingsText(), methodName);
			isTextPresent(uiDat.getSpecialOfferText(), methodName);
			isTextPresent(uiDat.getEventsText(), methodName);
			waitForGivenTime(MagicNumbers.ONE_THOUSAND_SECONDS);
		} catch (Exception e) {
			log.info("EXCEPTION IN UIVALIDATION HOME SCREEN" + e.getMessage());
		}
	}
	
	public void uiValidationRegisterScreen(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.ONE_THOUSAND_SECONDS);
			buttonEnabled(uiId.getRegisterButton().trim());
			waitForElementPresent(uiId.getRegisterButton().trim(), methodName);
			getWebElement(uiId.getRegisterButton().trim());
			click();
			waitForGivenTime(MagicNumbers.ONE_THOUSAND_SECONDS);
			isTextPresent(uiDat.getFirstName().trim(), methodName);
			isTextPresent(uiDat.getFirstName().trim(), methodName);
			isTextPresent(uiDat.getEmailIdText().trim(), methodName);
			isTextPresent(uiDat.getPassword(), methodName);
			isTextPresent(uiDat.getPhoneText(), methodName);
			getWebElement(uiId.getRegFirstName().trim());
			getPlaceholderValue(uiDat.getFirstName());
			getWebElement(uiId.getRegLastName().trim());
			getPlaceholderValue(uiDat.getLastName());
			getWebElement(uiId.getRegEmail().trim());
			getPlaceholderValue(uiDat.getEmail());
			getWebElement(uiId.getRegPassword().trim());
			getPlaceholderValue(uiDat.getPassword());
			getWebElement(uiId.getRegPhoneNo().trim());
			getPlaceholderValue(uiDat.getPhoneNo());
			buttonEnabled(uiId.getRegSubmitButton().trim());
			buttonEnabled(uiId.getRegCancelButton().trim());
			buttonEnabled(uiId.getBackButton().trim());
			buttonEnabled(uiId.getRegLoginButton().trim());
			buttonEnabled(uiId.getHomeButton().trim());
			buttonEnabled(uiId.getHomeBrowseButton().trim());
			buttonEnabled(uiId.getHomeSpecialOfferButton());
			waitForGivenTime(MagicNumbers.ONE_THOUSAND_SECONDS);
		} catch (Exception e) {
			log.info("EXCEPTION IN UIVALIDATION REGISTER SCREEN" + e.getMessage());
		}
	}
	
	public void uiValidationLoginScreen(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.ONE_THOUSAND_SECONDS);
			buttonEnabled(uiId.getHomeButton().trim());
			waitForElementPresent(uiId.getHomeButton().trim(), methodName);
			getWebElement(uiId.getHomeButton().trim());
			click();
			waitForGivenTime(MagicNumbers.THREE_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getLoginButton().trim(), methodName);
			getWebElement(uiId.getLoginButton().trim());
			click();
			waitForGivenTime(MagicNumbers.ONE_THOUSAND_SECONDS);
			isTextPresent(uiDat.getEmailIdText().trim(), methodName);
			isTextPresent(uiDat.getPassword(), methodName);
			getWebElement(uiId.getRegEmail().trim());
			getPlaceholderValue(uiDat.getEmail());
			getWebElement(uiId.getRegPassword().trim());
			getPlaceholderValue(uiDat.getPassword());
			buttonEnabled(uiId.getSubmitButton().trim());
			waitForGivenTime(MagicNumbers.ONE_THOUSAND_SECONDS);
		} catch (Exception e) {
			log.info("EXCEPTION IN UIVALIDATION LOGIN SCREEN" + e.getMessage());
		}
	}
	
	public void uiValidationProductScreen(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.ONE_THOUSAND_SECONDS);
			buttonEnabled(uiId.getHomeButton().trim());
			waitForElementPresent(uiId.getHomeButton().trim(), methodName);
			getWebElement(uiId.getHomeButton().trim());
			click();
			buttonEnabled(uiId.getBrowseButton().trim());
			waitForElementPresent(uiId.getBrowseButton().trim(), methodName);
			getWebElement(uiId.getBrowseButton().trim());
			click();
			waitForElementPresent(uiId.getTvImage().trim(), methodName);
			getWebElement(uiId.getTvImage().trim());
			getImageSrc(uiDat.getTvImage().trim());
			isTextPresent(uiDat.getTvText(), methodName);
			waitForElementPresent(uiId.getComputerImage().trim(), methodName);
			getWebElement(uiId.getComputerImage().trim());
			getImageSrc(uiDat.getComputerImage().trim());
			isTextPresent(uiDat.getComputerText(), methodName);
			waitForElementPresent(uiId.getMobilePhoneImage().trim(), methodName);
			getWebElement(uiId.getMobilePhoneImage().trim());
			getImageSrc(uiDat.getMobilePhoneImage().trim());
			isTextPresent(uiDat.getMobilePhoneText(), methodName);
			waitForElementPresent(uiId.getAudioImage().trim(), methodName);
			getWebElement(uiId.getAudioImage().trim());
			getImageSrc(uiDat.getAudioImage().trim());
			isTextPresent(uiDat.getAudioText(), methodName);
			waitForElementPresent(uiId.getCameraImage().trim(), methodName);
			getWebElement(uiId.getCameraImage().trim());
			getImageSrc(uiDat.getCameraImage().trim());
			isTextPresent(uiDat.getCameraText(), methodName);
			waitForElementPresent(uiId.getTabletImage().trim(), methodName);
			getWebElement(uiId.getTabletImage().trim());
			getImageSrc(uiDat.getTabletImage().trim());
			isTextPresent(uiDat.getTabletText(), methodName);
			waitForElementPresent(uiId.getMovieImage().trim(), methodName);
			getWebElement(uiId.getMovieImage().trim());
			getImageSrc(uiDat.getMovieImage().trim());
			isTextPresent(uiDat.getMovieText(), methodName);
			waitForElementPresent(uiId.getVideoGameImage().trim(), methodName);
			getWebElement(uiId.getVideoGameImage().trim());
			getImageSrc(uiDat.getVideoGameImage().trim());
			isTextPresent(uiDat.getVideoGameText(), methodName);
			waitForElementPresent(uiId.getMp3PlayerImage().trim(), methodName);
			getWebElement(uiId.getMp3PlayerImage().trim());
			getImageSrc(uiDat.getMp3PlayerImage().trim());
			isTextPresent(uiDat.getMp3PlayerText(), methodName);
			waitForElementPresent(uiId.getAccessoriesImage().trim(), methodName);
			getWebElement(uiId.getAccessoriesImage().trim());
			getImageSrc(uiDat.getAccessoriesImage().trim());
			isTextPresent(uiDat.getAccessoriesText(), methodName);
			waitForGivenTime(MagicNumbers.ONE_THOUSAND_SECONDS);
		} catch (Exception e) {
			log.info("EXCEPTION IN UIVALIDATION LOGIN SCREEN" + e.getMessage());
		}
	}
	
	public void uiValidationBillingInfoScreen(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.ONE_THOUSAND_SECONDS);
			buttonEnabled(uiId.getHomeButton().trim());
			waitForElementPresent(uiId.getHomeButton().trim(), methodName);
			getWebElement(uiId.getHomeButton().trim());
			click();
			buttonEnabled(uiId.getBrowseButton().trim());
			waitForElementPresent(uiId.getBrowseButton().trim(), methodName);
			getWebElement(uiId.getBrowseButton().trim());
			click();
			buttonEnabled(uiId.getTelevision().trim());
			waitForElementPresent(uiId.getTelevision().trim(), methodName);
			getWebElement(uiId.getTelevision().trim());
			click();
			buttonEnabled(uiId.getLgElectronic().trim());
			waitForElementPresent(uiId.getLgElectronic().trim(), methodName);
			getWebElement(uiId.getLgElectronic().trim());
			click();
			buttonEnabled(uiId.getAddToCart().trim());
			waitForElementPresent(uiId.getAddToCart().trim(), methodName);
			getWebElement(uiId.getAddToCart().trim());
			click();
			buttonEnabled(uiId.getCheckOutButton().trim());
			waitForElementPresent(uiId.getCheckOutButton().trim(), methodName);
			getWebElement(uiId.getCheckOutButton().trim());
			click();
			waitForGivenTime(MagicNumbers.TWO_THOUSAND_SECONDS);
			isTextPresent(uiDat.getCheckOutText().trim(), methodName);
			isTextPresent(uiDat.getCustomerInformationText().trim(), methodName);
			isTextPresent(uiDat.getOrderInfoEmailText().trim(), methodName);
			waitForGivenTime(MagicNumbers.TWO_THOUSAND_SECONDS);
		//	isTextPresent(uiDat.getEmailIdText().trim(), methodName);
			buttonEnabled(uiId.getBillInfoEmail());
			isTextPresent(uiDat.getDeliveryInfoText().trim(), methodName);
			buttonEnabled(uiId.getDeliveryInfoButton());
			waitForElementPresent(uiId.getDeliveryInfoButton(),methodName);
			getWebElement(uiId.getDeliveryInfoButton());
			click();
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			isTextPresent(uiDat.getEnterDeliveryText().trim(), methodName);
			isTextPresent(uiDat.getFirstNameText().trim(), methodName);
			buttonEnabled(uiId.getBillInfoFirstName());
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			isTextPresent(uiDat.getLastNameText(), methodName);
			buttonEnabled(uiId.getBillInfoLastName());
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			isTextPresent(uiDat.getCompanyText(), methodName);
			buttonEnabled(uiId.getBillInfoCompany());
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			isTextPresent(uiDat.getAddres1Text(), methodName);
			buttonEnabled(uiId.getBillInfoAddress1());
			waitForGivenTime(MagicNumbers.HALF_SECONDS); 
			isTextPresent(uiDat.getAddress2Text(), methodName);
			buttonEnabled(uiId.getBillInfoAddress2());
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			isTextPresent(uiDat.getCityText(), methodName);
			buttonEnabled(uiId.getBillInfoCity());
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			isTextPresent(uiDat.getStateText(), methodName);
			buttonEnabled(uiId.getBillInfoState());
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			isTextPresent(uiDat.getStateText(), methodName);
			isTextPresent(uiDat.getCountryText(), methodName);
			isTextPresent(uiDat.getPostCodeText(), methodName);
			buttonEnabled(uiId.getBillInfoPostCode());
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			isTextPresent(uiDat.getPhonenoText(), methodName);
			buttonEnabled(uiId.getBillInfoPhoneNumber());
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			isTextPresent(uiDat.getBillingInfoText(), methodName);
			buttonEnabled(uiId.getBillInfoButton());
			waitForElementPresent(uiId.getBillInfoButton(),methodName);
			getWebElement(uiId.getBillInfoButton());
			click();
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			isTextPresent(uiDat.getEnterBillInfoText(), methodName);
			isTextPresent(uiDat.getSameBillandDeliText(), methodName);
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			buttonEnabled(uiId.getBillCheckBox());
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			buttonEnabled(uiId.getPaymentMethodsButton());
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			isTextPresent(uiDat.getPaymentMethodText(), methodName);
			buttonEnabled(uiId.getPaymentMethodsButton());
			waitForElementPresent(uiId.getPaymentMethodsButton(),methodName);
			getWebElement(uiId.getPaymentMethodsButton());
			click();
			isTextPresent(uiDat.getPaymentOptionText(), methodName);
			isTextPresent(uiDat.getMoneyOrderText(), methodName);
			isTextPresent(uiDat.getCashText(), methodName);
			isTextPresent(uiDat.getTotalItemText(), methodName);
			isTextPresent(uiDat.getOrderTotalText(), methodName);
			isTextPresent(uiDat.getChequeText(), methodName);
			waitForGivenTime(MagicNumbers.HALF_SECONDS);
			isTextPresent(uiDat.getOrderCommentsText(), methodName);
			buttonEnabled(uiId.getOrderCommentsButton());
			waitForElementPresent(uiId.getOrderCommentsButton(),methodName);
			getWebElement(uiId.getOrderCommentsButton());
			click();
			isTextPresent(uiDat.getRegardsOrderText(), methodName);
			buttonEnabled(uiId.getReviewOrder());
			waitForGivenTime(MagicNumbers.ONE_THOUSAND_SECONDS);
		} catch (Exception e) {
			log.info("EXCEPTION IN UIVALIDATION BILLING INFO SCREEN" + e.getMessage());
		}
	}
	
	public void driverQuit() {
		try {
			if(driver != null){
			quitDriver();
			}
		} catch (Exception e) {
			log.info("EXCEPTION IN DRIVER QUIT " + e.getMessage());
		}
	}
}
