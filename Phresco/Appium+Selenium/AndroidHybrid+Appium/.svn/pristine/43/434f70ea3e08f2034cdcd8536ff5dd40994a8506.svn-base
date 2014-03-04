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

			}	
		}
		return uiDat;
	}

}
