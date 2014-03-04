package com.photon.phresco.mydevice.parser;
import com.photon.phresco.mydevice.model.UiIdentifiers;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;

public class ElementIdentifiersParser {

	public UiIdentifiers parseXml(String xmlFile) throws Exception {

		UiIdentifiers uiId = new UiIdentifiers();
		VTDGen vg = new VTDGen();

		if (vg.parseFile(xmlFile, true)) {
			VTDNav vn = vg.getNav();
			if (vn.matchElement("identifiers")) {
				if (vn.toElement(VTDNav.FC,"login")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setLogin(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"go")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setGo(vn.toString(index));
				}

				if (vn.toElement(VTDNav.NS,"menubutton")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setMenubutton(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"find")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setFind(vn.toString(index));
				}

				if (vn.toElement(VTDNav.NS,"labelprint")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setLabelprint(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"signprint")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setSignprint(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"fill")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setFill(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"findSearch")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setFindSearch(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"findSearchIcon")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setFindSearchIcon(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"findSearchWomensCategory")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setFindSearchWomensCategory(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"findSearchMensCategory")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setFindSearchMensCategory(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"findSearchElectronicsCategory")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setFindSearchElectronicsCategory(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"findSearchShoesCategory")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setFindSearchShoesCategory(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"categoryDiscription")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setCategoryDiscription(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"categoryViewAll")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setCategoryViewAll(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"categoryViewCancel")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setCategoryViewCancel(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"itemPogNumber")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setItemPogNumber(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"itemPrintTitle")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setItemPrintTitle(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"itemQuantity")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setItemQuantity(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"genericLabel")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setGenericLabel(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"singleItem")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setSingleItem(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"multiItemSign")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setMultiItemSign(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"planoGram")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setPlanoGram(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"reprintExistingSign")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setReprintExistingSign(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"signDate")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setSignDate(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"signSize")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setSignSize(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"signNext")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setSignNext(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"signSearch")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setSignSearch(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"multiSignDate")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setMultiSignDate(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"multiSignSize")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setMultiSignSize(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"multiSignNext")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setMultiSignNext(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"multiSignQuantity")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setMultiSignQuantity(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"batchNameButton")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setBatchNameButton(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"batchNameField")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setBatchNameField(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"planoGramSearch")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setPlanoGramSearch(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"planoGramSign")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setPlanoGramSign(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"rePrintDate")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setRePrintDate(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"fillMenuSearch")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setFillMenuSearch(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"fillMenuContinue")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setFillMenuContinue(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS,"logout")) {
					int indexName = vn.getAttrVal("type");
					int index = vn.getText();
					uiId.getElementType().put(vn.toString(index), vn.toString(indexName));
					uiId.setLogout(vn.toString(index));
				}
			}			
		}		
		return uiId;
	}
}
