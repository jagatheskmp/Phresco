package com.photon.phresco.mydevice.screen;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.photon.phresco.mydevice.model.TestNgParameters;
import com.photon.phresco.selenium.util.MagicNumbers;

public class ImplementationScreen extends AbstractBaseScreen {

	private Log log = LogFactory.getLog("ImplementationScreen");

	public ImplementationScreen(TestNgParameters tngparam) {
		super(tngparam);
	}

	public void login(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.THREE_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getLogin().trim(), methodName);
			getWebElement(uiId.getLogin().trim());
			sendKeys(uiDat.getLoginData());
			waitForElementPresent(uiId.getGo(), methodName);
			getWebElement(uiId.getGo());
			click();
		} catch (Exception e) {
			log.info("EXCEPTION IN LOGIN " + e.getMessage());
		}
	}

	public void menuBarVerification(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.FOUR_THOUSAND_SECONDS);
			isTextPresent(uiDat.getSearchProductText().trim(), methodName);
			waitForElementPresent(uiId.getMenubutton().trim(), methodName);
			getWebElement(uiId.getMenubutton().trim());
			click();
			waitForElementPresent(uiId.getLabelprint().trim(), methodName);
			getWebElement(uiId.getLabelprint().trim());
			click();
			isTextPresent(uiDat.getScanorKeyAisleText().trim(), methodName);
			waitForElementPresent(uiId.getMenubutton().trim(), methodName);
			getWebElement(uiId.getMenubutton().trim());
			click();
			waitForElementPresent(uiId.getSignprint().trim(), methodName);
			getWebElement(uiId.getSignprint().trim());
			click();
			isTextPresent(uiDat.getInputTypeText().trim(), methodName);
			waitForElementPresent(uiId.getMenubutton().trim(), methodName);
			getWebElement(uiId.getMenubutton().trim());
			click();
			waitForElementPresent(uiId.getFill().trim(), methodName);
			getWebElement(uiId.getFill().trim());
			click();
			isTextPresent(uiDat.getEnterPOGNumbert(), methodName);
		} catch (Exception e) {
			log.info("EXCEPTION IN MENUBARVERIFICATION " + e.getMessage());
		}
	}

	public void searchDPCI(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.FOUR_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getMenubutton().trim(), methodName);
			getWebElement(uiId.getMenubutton().trim());
			click();
			waitForElementPresent(uiId.getFind().trim(), methodName);
			getWebElement(uiId.getFind().trim());
			click();
			waitForElementPresent(uiId.getFindSearch().trim(), methodName);
			getWebElement(uiId.getFindSearch().trim());
			sendKeys(uiDat.getSearchDpci());
			keyboardEvent(uiDat.getEnterKeyboardEvent());
		} catch (Exception e) {
			log.info("EXCEPTION IN SEARCH DPCI " + e.getMessage());
		}
	}

	public void searchPlu(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.FOUR_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getFindSearchIcon().trim(), methodName);
			getWebElement(uiId.getFindSearchIcon().trim());
			click();
			waitForElementPresent(uiId.getFindSearch().trim(), methodName);
			getWebElement(uiId.getFindSearch().trim());
			sendKeys(uiDat.getSearchPlu());
			keyboardEvent(uiDat.getEnterKeyboardEvent());
		} catch (Exception e) {
			log.info("EXCEPTION IN SEARCH PLU " + e.getMessage());
		}
	}

	public void searchUpc(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.FOUR_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getFindSearchIcon().trim(), methodName);
			getWebElement(uiId.getFindSearchIcon().trim());
			click();
			waitForElementPresent(uiId.getFindSearch().trim(), methodName);
			getWebElement(uiId.getFindSearch().trim());
			sendKeys(uiDat.getSearchUpc());
			keyboardEvent(uiDat.getEnterKeyboardEvent());
		} catch (Exception e) {
			log.info("EXCEPTION IN SEARCH UPC " + e.getMessage());
		}
	}

	public void searchKeyword(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.FOUR_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getFindSearchIcon().trim(), methodName);
			getWebElement(uiId.getFindSearchIcon().trim());
			click();
			waitForElementPresent(uiId.getFindSearch().trim(), methodName);
			getWebElement(uiId.getFindSearch().trim());
			sendKeys(uiDat.getSearchKeyword());
			keyboardEvent(uiDat.getEnterKeyboardEvent());
		} catch (Exception e) {
			log.info("EXCEPTION IN SEARCH KEYWORD " + e.getMessage());
		}
	}
	public void searchWomensCategory(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.FOUR_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getFindSearchIcon().trim(), methodName);
			getWebElement(uiId.getFindSearchIcon().trim());
			click();
			waitForElementPresent(uiId.getFindSearch().trim(), methodName);
			getWebElement(uiId.getFindSearch().trim());
			sendKeys(uiDat.getSearchWomensCategory());
			keyboardEvent(uiDat.getEnterKeyboardEvent());
			waitForElementPresent(uiId.getFindSearchWomensCategory().trim(), methodName);
			getWebElement(uiId.getFindSearchWomensCategory().trim());
			click();
			waitForElementPresent(uiId.getCategoryDiscription().trim(), methodName);
			getWebElement(uiId.getCategoryDiscription().trim());
			click();
			waitForElementPresent(uiId.getCategoryViewAll().trim(), methodName);
			getWebElement(uiId.getCategoryViewAll().trim());
			click();
		} catch (Exception e) {
			log.info("EXCEPTION IN SEARCH WOMENSCATEGORY " + e.getMessage());
		}
	}
	public void searchMensCategory(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.FOUR_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getFindSearchIcon().trim(), methodName);
			getWebElement(uiId.getFindSearchIcon().trim());
			click();
			waitForElementPresent(uiId.getFindSearch().trim(), methodName);
			getWebElement(uiId.getFindSearch().trim());
			sendKeys(uiDat.getSearchMensCategory());
			keyboardEvent(uiDat.getEnterKeyboardEvent());
			waitForElementPresent(uiId.getFindSearchMensCategory().trim(), methodName);
			getWebElement(uiId.getFindSearchMensCategory().trim());
			click();
			waitForElementPresent(uiId.getCategoryDiscription().trim(), methodName);
			getWebElement(uiId.getCategoryDiscription().trim());
			click();
			waitForElementPresent(uiId.getCategoryViewAll().trim(), methodName);
			getWebElement(uiId.getCategoryViewAll().trim());
			click();
		} catch (Exception e) {
			log.info("EXCEPTION IN SEARCH MENSCATEGORY " + e.getMessage());
		}
	}
	public void searchElectronicsCategory(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.FOUR_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getFindSearchIcon().trim(), methodName);
			getWebElement(uiId.getFindSearchIcon().trim());
			click();
			waitForElementPresent(uiId.getFindSearch().trim(), methodName);
			getWebElement(uiId.getFindSearch().trim());
			sendKeys(uiDat.getSearchElectronicsCategory());
			keyboardEvent(uiDat.getEnterKeyboardEvent());
			waitForElementPresent(uiId.getFindSearchElectronicsCategory().trim(), methodName);
			getWebElement(uiId.getFindSearchElectronicsCategory().trim());
			click();
			waitForElementPresent(uiId.getCategoryDiscription().trim(), methodName);
			getWebElement(uiId.getCategoryDiscription().trim());
			click();
			waitForElementPresent(uiId.getCategoryViewAll().trim(), methodName);
			getWebElement(uiId.getCategoryViewAll().trim());
			click();
		} catch (Exception e) {
			log.info("EXCEPTION IN SEARCH ELECTRONICSCATEGORY " + e.getMessage());
		}
	}
	public void searchShoesCategory(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.FOUR_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getFindSearchIcon().trim(), methodName);
			getWebElement(uiId.getFindSearchIcon().trim());
			click();
			waitForElementPresent(uiId.getFindSearch().trim(), methodName);
			getWebElement(uiId.getFindSearch().trim());
			sendKeys(uiDat.getSearchShoesCategory());
			keyboardEvent(uiDat.getEnterKeyboardEvent());
			waitForElementPresent(uiId.getFindSearchShoesCategory().trim(), methodName);
			getWebElement(uiId.getFindSearchShoesCategory().trim());
			click();
			waitForElementPresent(uiId.getCategoryDiscription().trim(), methodName);
			getWebElement(uiId.getCategoryDiscription().trim());
			click();
			waitForElementPresent(uiId.getCategoryViewAll().trim(), methodName);
			getWebElement(uiId.getCategoryViewAll().trim());
			click();
		} catch (Exception e) {
			log.info("EXCEPTION IN SEARCH SHOESCATEGORY " + e.getMessage());
		}
	}
	public void labelPrint(String methodName) {
		try {
			waitForGivenTime(MagicNumbers.FOUR_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getMenubutton().trim(), methodName);
			getWebElement(uiId.getMenubutton().trim());
			click();
			waitForElementPresent(uiId.getLabelprint().trim(), methodName);
			getWebElement(uiId.getLabelprint().trim());
			click();
			waitForElementPresent(uiId.getItemPogNumber().trim(), methodName);
			getWebElement(uiId.getItemPogNumber().trim());
			sendKeys(uiDat.getItemPogNumber());
			keyboardEvent(uiDat.getEnterKeyboardEvent());
			waitForElementPresent(uiId.getItemPrintTitle().trim(), methodName);
			getWebElement(uiId.getItemPrintTitle().trim());
			click();
			waitForElementPresent(uiId.getItemQuantity().trim(), methodName);
			getWebElement(uiId.getItemQuantity().trim());
			clear();
			sendKeys(uiDat.getQuantityValue());
			keyboardEvent(uiDat.getEnterKeyboardEvent());
			waitForElementPresent(uiId.getGenericLabel().trim(), methodName);
			getWebElement(uiId.getGenericLabel().trim());
			click();
		} catch (Exception e) {
			log.info("EXCEPTION IN LABELPRINT " + e.getMessage());
		}
	}
	
	public void singleSignPrint(String methodName){
		try{
			waitForGivenTime(MagicNumbers.FOUR_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getMenubutton().trim(), methodName);
			getWebElement(uiId.getMenubutton().trim());
			click();
			waitForElementPresent(uiId.getSignprint().trim(), methodName);
			getWebElement(uiId.getSignprint().trim());
			click();
			waitForElementPresent(uiId.getSingleItem().trim(), methodName);
			getWebElement(uiId.getSingleItem().trim());
			click();
			waitForElementPresent(uiId.getSignDate().trim(), methodName);
			getWebElement(uiId.getSignDate().trim());
			sendKeys(uiDat.getSingleSignDate());
			waitForElementPresent(uiId.getSignSize().trim(), methodName);
			getWebElement(uiId.getSignSize().trim());
			sendKeys(uiDat.getSingleSignSize());
			waitForElementPresent(uiId.getSignNext().trim(), methodName);
			getWebElement(uiId.getSignNext().trim());
			click();
			waitForElementPresent(uiId.getSignSearch().trim(), methodName);
			getWebElement(uiId.getSignSearch().trim());
			sendKeys(uiDat.getSigleSignSearch());
			keyboardEvent(uiDat.getEnterKeyboardEvent());
			logout(methodName);
		} catch (Exception e) {
			log.info("EXCEPTION IN SINGLE SIGNPRINT " + e.getMessage());
		}
	}
	public void multiSignPrint(String methodName){
		try{
			login(methodName);
			waitForGivenTime(MagicNumbers.FOUR_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getMenubutton().trim(), methodName);
			getWebElement(uiId.getMenubutton().trim());
			click();
			waitForElementPresent(uiId.getSignprint().trim(), methodName);
			getWebElement(uiId.getSignprint().trim());
			click();
			waitForElementPresent(uiId.getMultiItemSign().trim(), methodName);
			getWebElement(uiId.getMultiItemSign().trim());
			click();
			waitForElementPresent(uiId.getMultiSignDate().trim(), methodName);
			getWebElement(uiId.getMultiSignDate().trim());
			sendKeys(uiDat.getMultiSignDate());
			waitForElementPresent(uiId.getMultiSignSize().trim(), methodName);
			getWebElement(uiId.getMultiSignSize().trim());
			sendKeys(uiDat.getMultiSignSize());
			waitForElementPresent(uiId.getSignNext().trim(), methodName);
			getWebElement(uiId.getSignNext().trim());
			click();
			waitForElementPresent(uiId.getSignSearch().trim(), methodName);
			getWebElement(uiId.getSignSearch().trim());
			sendKeys(uiDat.getMultiSignSearch());
			keyboardEvent(uiDat.getEnterKeyboardEvent());
			waitForElementPresent(uiId.getMultiSignNext().trim(), methodName);
			getWebElement(uiId.getMultiSignNext().trim());
			click();
			waitForElementPresent(uiId.getMultiSignQuantity().trim(), methodName);
			getWebElement(uiId.getMultiSignQuantity().trim());
			sendKeys(uiDat.getMultiSignQuantity());
			waitForElementPresent(uiId.getBatchNameButton().trim(), methodName);
			getWebElement(uiId.getBatchNameButton().trim());
			click();
			waitForElementPresent(uiId.getBatchNameField().trim(), methodName);
			getWebElement(uiId.getBatchNameField().trim());
			sendKeys(uiDat.getMultiSignBatchName());
			waitForElementPresent(uiId.getSignNext().trim(), methodName);
			getWebElement(uiId.getSignNext().trim());
			click();
			logout(methodName);
		} catch (Exception e) {
			log.info("EXCEPTION IN MULTI SIGNPRINT " + e.getMessage());
		}
	}
	public void PlanogramSignPrint(String methodName){
		try{
			login(methodName);
			waitForGivenTime(MagicNumbers.FOUR_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getMenubutton().trim(), methodName);
			getWebElement(uiId.getMenubutton().trim());
			click();
			waitForElementPresent(uiId.getSignprint().trim(), methodName);
			getWebElement(uiId.getSignprint().trim());
			click();
			waitForElementPresent(uiId.getPlanoGram().trim(), methodName);
			getWebElement(uiId.getPlanoGram().trim());
			click();
			waitForElementPresent(uiId.getPlanoGramSearch().trim(), methodName);
			getWebElement(uiId.getPlanoGramSearch().trim());
			sendKeys(uiDat.getPlanoSignSearch());
			keyboardEvent(uiDat.getEnterKeyboardEvent());
			waitForElementPresent(uiId.getPlanoGramSign().trim(), methodName);
			getWebElement(uiId.getPlanoGramSign().trim());
			sendKeys(uiDat.getPlanoSignFormat());
			waitForElementPresent(uiId.getBatchNameButton().trim(), methodName);
			getWebElement(uiId.getBatchNameButton().trim());
			click();
			waitForElementPresent(uiId.getBatchNameField().trim(), methodName);
			getWebElement(uiId.getBatchNameField().trim());
			sendKeys(uiDat.getPlanoSignBatchName());
			waitForElementPresent(uiId.getSignNext().trim(), methodName);
			getWebElement(uiId.getSignNext().trim());
			click();
			logout(methodName);
		} catch (Exception e) {
			log.info("EXCEPTION IN PLANOGRAM SIGNPRINT " + e.getMessage());
		}
	}
	public void rePrintExistingSign(String methodName){
		try{
			login(methodName);
			waitForGivenTime(MagicNumbers.FOUR_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getMenubutton().trim(), methodName);
			getWebElement(uiId.getMenubutton().trim());
			click();
			waitForElementPresent(uiId.getSignprint().trim(), methodName);
			getWebElement(uiId.getSignprint().trim());
			click();
			waitForElementPresent(uiId.getReprintExistingSign().trim(), methodName);
			getWebElement(uiId.getReprintExistingSign().trim());
			click();
			waitForElementPresent(uiId.getRePrintDate().trim(), methodName);
			getWebElement(uiId.getRePrintDate().trim());
			sendKeys(uiDat.getRePrintDateFormat());
		} catch (Exception e) {
			log.info("EXCEPTION IN RE-PRINT EXISTING SIGNPRINT " + e.getMessage());
		}
	}
	public void fill(String methodName){
		try{
			waitForGivenTime(MagicNumbers.FOUR_THOUSAND_SECONDS);
			waitForElementPresent(uiId.getMenubutton().trim(), methodName);
			getWebElement(uiId.getMenubutton().trim());
			click();
			waitForElementPresent(uiId.getFill().trim(), methodName);
			getWebElement(uiId.getFill().trim());
			click();
			waitForElementPresent(uiId.getFillMenuSearch().trim(), methodName);
			getWebElement(uiId.getFillMenuSearch().trim());
			sendKeys(uiDat.getFillSearchData());
			keyboardEvent(uiDat.getEnterKeyboardEvent());
			waitForElementPresent(uiId.getFillMenuContinue().trim(), methodName);
			getWebElement(uiId.getFillMenuContinue().trim());
			click();
		} catch (Exception e) {
			log.info("EXCEPTION IN FILL " + e.getMessage());
		}
	}

	public void logout(String methodName) {
		try {
			waitForElementPresent(uiId.getMenubutton(), methodName);
			getWebElement(uiId.getMenubutton());
			click();
			waitForElementPresent(uiId.getLogout(), methodName);
			getWebElement(uiId.getLogout());
			click();
		} catch (Exception e) {
			log.info("EXCEPTION IN LOGOUT " + e.getMessage());
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
