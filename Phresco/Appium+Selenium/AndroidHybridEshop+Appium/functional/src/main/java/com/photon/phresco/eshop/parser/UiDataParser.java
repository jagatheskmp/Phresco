package com.photon.phresco.eshop.parser;

import com.photon.phresco.eshop.model.UiData;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;

public class UiDataParser {

	public UiData parseXml(String xmlFile) throws Exception {
		UiData uiDat = new UiData();
		VTDGen vg = new VTDGen();

		if (vg.parseFile(xmlFile, true)) {
			VTDNav vn = vg.getNav();
			if (vn.matchElement("uiData")) {
				if (vn.toElement(VTDNav.FC, "loginEmail")) {
					int key = vn.getText();
					uiDat.setLoginEmail(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "loginPassword")) {
					int key = vn.getText();
					uiDat.setLoginPassword(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "loginFailedMsg")) {
					int key = vn.getText();
					uiDat.setLoginFailedMsg(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "regFirstName")) {
					int key = vn.getText();
					uiDat.setRegFirstName(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "regLastName")) {
					int key = vn.getText();
					uiDat.setRegLastName(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "regEmail")) {
					int key = vn.getText();
					uiDat.setRegEmail(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "regPassword")) {
					int key = vn.getText();
					uiDat.setRegPassword(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "regPhoneNo")) {
					int key = vn.getText();
					uiDat.setRegPhoneNo(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "billInfoCommentsValue")) {
					int key = vn.getText();
					uiDat.setBillInfoCommentsValue(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "billInfoPhoneNumberValue")) {
					int key = vn.getText();
					uiDat.setBillInfoPhoneNumberValue(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "billInfoStateValue")) {
					int key = vn.getText();
					uiDat.setBillInfoStateValue(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "billInfoPostCodeValue")) {
					int key = vn.getText();
					uiDat.setBillInfoPostCodeValue(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "billInfoAddress2Value")) {
					int key = vn.getText();
					uiDat.setBillInfoAddress2Value(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "billInfoCityValue")) {
					int key = vn.getText();
					uiDat.setBillInfoCityValue(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "billInfoCompanyValue")) {
					int key = vn.getText();
					uiDat.setBillInfoCompanyValue(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "billInfoAddress1Value")) {
					int key = vn.getText();
					uiDat.setBillInfoAddress1Value(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "billInfoLastNameValue")) {
					int key = vn.getText();
					uiDat.setBillInfoLastNameValue(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "billInfoFirstNameValue")) {
					int key = vn.getText();
					uiDat.setBillInfoFirstNameValue(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "billInfoEmailValue")) {
					int key = vn.getText();
					uiDat.setBillInfoEmailValue(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "cardInfoNameOnCardValue")) {
					int key = vn.getText();
					uiDat.setCardInfoNameOnCardValue(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "cardInfoCardNumberValue")) {
					int key = vn.getText();
					uiDat.setCardInfoCardNumberValue(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "cardInfoSecurityNumberValue")) {
					int key = vn.getText();
					uiDat.setCardInfoSecurityNumberValue(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "homeImage")) {
					int key = vn.getText();
					uiDat.setHomeImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "regImage")) {
					int key = vn.getText();
					uiDat.setRegImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "mycardImage")) {
					int key = vn.getText();
					uiDat.setMycardImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "searchImage")) {
					int key = vn.getText();
					uiDat.setSearchImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "browseImage")) {
					int key = vn.getText();
					uiDat.setBrowseImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "loginImage")) {
					int key = vn.getText();
					uiDat.setLoginImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "settingsImage")) {
					int key = vn.getText();
					uiDat.setSettingsImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "specialOfferImage")) {
					int key = vn.getText();
					uiDat.setSpecialOfferImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "eventsImage")) {
					int key = vn.getText();
					uiDat.setEventsImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "homeText")) {
					int key = vn.getText();
					uiDat.setHomeText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "regText")) {
					int key = vn.getText();
					uiDat.setRegText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "mycardText")) {
					int key = vn.getText();
					uiDat.setMycardText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "searchText")) {
					int key = vn.getText();
					uiDat.setSearchText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "browseText")) {
					int key = vn.getText();
					uiDat.setBrowseText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "loginText")) {
					int key = vn.getText();
					uiDat.setLoginText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "settingsText")) {
					int key = vn.getText();
					uiDat.setSettingsText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "specialOfferText")) {
					int key = vn.getText();
					uiDat.setSpecialOfferText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "eventsText")) {
					int key = vn.getText();
					uiDat.setEventsText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "firstName")) {
					int key = vn.getText();
					uiDat.setFirstName(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "lastName")) {
					int key = vn.getText();
					uiDat.setLastName(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "email")) {
					int key = vn.getText();
					uiDat.setEmail(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "password")) {
					int key = vn.getText();
					uiDat.setPassword(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "phoneNo")) {
					int key = vn.getText();
					uiDat.setPhoneNo(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "emailIdText")) {
					int key = vn.getText();
					uiDat.setEmailIdText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "phoneText")) {
					int key = vn.getText();
					uiDat.setPhoneText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "checkOutText")) {
					int key = vn.getText();
					uiDat.setCheckOutText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "customerInformationText")) {
					int key = vn.getText();
					uiDat.setCustomerInformationText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "orderInfoEmailText")) {
					int key = vn.getText();
					uiDat.setOrderInfoEmailText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "emailAddressText")) {
					int key = vn.getText();
					uiDat.setEmailAddressText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "deliveryInfoText")) {
					int key = vn.getText();
					uiDat.setDeliveryInfoText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "enterDeliveryText")) {
					int key = vn.getText();
					uiDat.setEnterDeliveryText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "firstNameText")) {
					int key = vn.getText();
					uiDat.setFirstNameText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "lastNameText")) {
					int key = vn.getText();
					uiDat.setLastNameText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "companyText")) {
					int key = vn.getText();
					uiDat.setCompanyText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "addres1Text")) {
					int key = vn.getText();
					uiDat.setAddres1Text(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "address2Text")) {
					int key = vn.getText();
					uiDat.setAddress2Text(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "cityText")) {
					int key = vn.getText();
					uiDat.setCityText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "stateText")) {
					int key = vn.getText();
					uiDat.setStateText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "countryText")) {
					int key = vn.getText();
					uiDat.setCountryText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "postCodeText")) {
					int key = vn.getText();
					uiDat.setPostCodeText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "phonenoText")) {
					int key = vn.getText();
					uiDat.setPhonenoText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "billingInfoText")) {
					int key = vn.getText();
					uiDat.setBillingInfoText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "enterBillInfoText")) {
					int key = vn.getText();
					uiDat.setEnterBillInfoText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "sameBillandDeliText")) {
					int key = vn.getText();
					uiDat.setSameBillandDeliText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "paymentMethodText")) {
					int key = vn.getText();
					uiDat.setPaymentMethodText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "paymentOptionText")) {
					int key = vn.getText();
					uiDat.setPaymentOptionText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "moneyOrderText")) {
					int key = vn.getText();
					uiDat.setMoneyOrderText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "cashText")) {
					int key = vn.getText();
					uiDat.setCashText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "totalItemText")) {
					int key = vn.getText();
					uiDat.setTotalItemText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "orderTotalText")) {
					int key = vn.getText();
					uiDat.setOrderTotalText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "chequeText")) {
					int key = vn.getText();
					uiDat.setChequeText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "orderCommentsText")) {
					int key = vn.getText();
					uiDat.setOrderCommentsText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "regardsOrderText")) {
					int key = vn.getText();
					uiDat.setRegardsOrderText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "tvImage")) {
					int key = vn.getText();
					uiDat.setTvImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "computerImage")) {
					int key = vn.getText();
					uiDat.setComputerImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "mobilePhoneImage")) {
					int key = vn.getText();
					uiDat.setMobilePhoneImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "audioImage")) {
					int key = vn.getText();
					uiDat.setAudioImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "cameraImage")) {
					int key = vn.getText();
					uiDat.setCameraImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "tabletImage")) {
					int key = vn.getText();
					uiDat.setTabletImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "movieImage")) {
					int key = vn.getText();
					uiDat.setMovieImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "videoGameImage")) {
					int key = vn.getText();
					uiDat.setVideoGameImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "mp3PlayerImage")) {
					int key = vn.getText();
					uiDat.setMp3PlayerImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "accessoriesImage")) {
					int key = vn.getText();
					uiDat.setAccessoriesImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "tvText")) {
					int key = vn.getText();
					uiDat.setTvText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "computerText")) {
					int key = vn.getText();
					uiDat.setComputerText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "mobilePhoneText")) {
					int key = vn.getText();
					uiDat.setMobilePhoneText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "audioText")) {
					int key = vn.getText();
					uiDat.setAudioText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "cameraText")) {
					int key = vn.getText();
					uiDat.setCameraText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "tabletText")) {
					int key = vn.getText();
					uiDat.setTabletText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "movieText")) {
					int key = vn.getText();
					uiDat.setMovieText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "videoGameText")) {
					int key = vn.getText();
					uiDat.setVideoGameText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "mp3PlayerText")) {
					int key = vn.getText();
					uiDat.setMp3PlayerText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "accessoriesText")) {
					int key = vn.getText();
					uiDat.setAccessoriesText(vn.toString(key));
				}

			}	
		}
		return uiDat;
	}

}	










