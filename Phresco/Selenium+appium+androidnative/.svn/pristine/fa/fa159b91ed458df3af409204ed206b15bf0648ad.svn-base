package com.photon.phresco.mydevice.parser;

import com.photon.phresco.mydevice.model.UiData;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;

public class UiDataParser {

	public UiData parseXml(String xmlFile) throws Exception {
		UiData uiDat = new UiData();
		VTDGen vg = new VTDGen();

		if (vg.parseFile(xmlFile, true)) {
			VTDNav vn = vg.getNav();
			if (vn.matchElement("uiData")) {
				if (vn.toElement(VTDNav.FC, "loginData")) {
					int key = vn.getText();
					uiDat.setLoginData(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "searchProductText")) {
					int key = vn.getText();
					uiDat.setSearchProductText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "scanorKeyAisleText")) {
					int key = vn.getText();
					uiDat.setScanorKeyAisleText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "inputTypeText")) {
					int key = vn.getText();
					uiDat.setInputTypeText(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "enterPOGNumbert")) {
					int key = vn.getText();
					uiDat.setEnterPOGNumbert(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "enterKeyboardEvent")) {
					int key = vn.getText();
					uiDat.setEnterKeyboardEvent(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "searchDpci")) {
					int key = vn.getText();
					uiDat.setSearchDpci(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "searchPlu")) {
					int key = vn.getText();
					uiDat.setSearchPlu(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "searchUpc")) {
					int key = vn.getText();
					uiDat.setSearchUpc(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "searchKeyword")) {
					int key = vn.getText();
					uiDat.setSearchKeyword(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "searchWomensCategory")) {
					int key = vn.getText();
					uiDat.setSearchWomensCategory(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "searchMensCategory")) {
					int key = vn.getText();
					uiDat.setSearchMensCategory(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "searchElectronicsCategory")) {
					int key = vn.getText();
					uiDat.setSearchElectronicsCategory(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "searchShoesCategory")) {
					int key = vn.getText();
					uiDat.setSearchShoesCategory(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "itemPogNumber")) {
					int key = vn.getText();
					uiDat.setItemPogNumber(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "quantityValue")) {
					int key = vn.getText();
					uiDat.setQuantityValue(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "singleSignDate")) {
					int key = vn.getText();
					uiDat.setSingleSignDate(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "singleSignSize")) {
					int key = vn.getText();
					uiDat.setSingleSignSize(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "sigleSignSearch")) {
					int key = vn.getText();
					uiDat.setSigleSignSearch(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "multiSignDate")) {
					int key = vn.getText();
					uiDat.setMultiSignDate(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "multiSignSize")) {
					int key = vn.getText();
					uiDat.setMultiSignSize(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "multiSignSearch")) {
					int key = vn.getText();
					uiDat.setMultiSignSearch(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "multiSignQuantity")) {
					int key = vn.getText();
					uiDat.setMultiSignQuantity(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "multiSignBatchName")) {
					int key = vn.getText();
					uiDat.setMultiSignBatchName(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "planoSignSearch")) {
					int key = vn.getText();
					uiDat.setPlanoSignSearch(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "planoSignFormat")) {
					int key = vn.getText();
					uiDat.setPlanoSignFormat(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "panoSignBatchName")) {
					int key = vn.getText();
					uiDat.setPlanoSignBatchName(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "rePrintDateFormat")) {
					int key = vn.getText();
					uiDat.setRePrintDateFormat(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS, "fillSearchData")) {
					int key = vn.getText();
					uiDat.setFillSearchData(vn.toString(key));
				}
			}
		}
		return uiDat;
	}

}
