package com.photon.phresco.eshop.parser;
import com.photon.phresco.eshop.model.UiIdentifiers;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;

public class ElementIdentifiersParser {

	public UiIdentifiers parseXml(String xmlFile) throws Exception {

		UiIdentifiers uiId = new UiIdentifiers();
		VTDGen vg = new VTDGen();

		if (vg.parseFile(xmlFile, true)) {
			VTDNav vn = vg.getNav();
			if (vn.matchElement("identifiers")) {
				if (vn.toElement(VTDNav.FC,"loginButton")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setLoginButton(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"loginEmail")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setLoginEmail(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"loginPassword")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setLoginPassword(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"submitButton")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setSubmitButton(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"backButton")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setBackButton(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"registerButton")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setRegisterButton(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"regFirstName")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setRegFirstName(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"regLastName")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setRegLastName(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"regEmail")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setRegEmail(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"regPassword")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setRegPassword(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"regPhoneNo")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setRegPhoneNo(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"regSubmitButton")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setRegSubmitButton(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"regCancelButton")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setRegCancelButton(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"regLoginButton")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setRegLoginButton(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"homeButton")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setHomeButton(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"homeBrowseButton")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setHomeBrowseButton(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"homeSpecialOfferButton")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setHomeSpecialOfferButton(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"browseButton")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setBrowseButton(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"television")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setTelevision(vn.toString(key));
				}			
				if (vn.toElement(VTDNav.NS,"lgElectronic")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setLgElectronic(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"addToCart")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setAddToCart(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"computer")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setComputer(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"apple")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setApple(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"mobilePhone")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setMobilePhone(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"appleIphone")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setAppleIphone(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"audioDevice")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setAudioDevice(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"microPhone")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setMicroPhone(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"camera")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setCamera(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"canon")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setCanon(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"tablet")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setTablet(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"acer")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setAcer(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"movieMusic")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setMovieMusic(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"sony")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setSony(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"videoGames")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setVideoGames(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"modernWarfare")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setModernWarfare(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"mp3Player")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setMp3Player(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"aluratek")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setAluratek(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"accessories")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setAccessories(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"bracket1")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setBracket1(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"checkOutButton")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setCheckOutButton(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"reviewOrder")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setReviewOrder(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"submitOrder")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setSubmitOrder(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"billInfoEmail")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setBillInfoEmail(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"billInfoFirstName")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setBillInfoFirstName(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"billInfoLastName")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setBillInfoLastName(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"billInfoCompany")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setBillInfoCompany(vn.toString(key));
				}

				if (vn.toElement(VTDNav.NS,"billInfoAddress1")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setBillInfoAddress1(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"billInfoAddress2")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setBillInfoAddress2(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"billInfoCity")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setBillInfoCity(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"billInfoState")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setBillInfoState(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"billInfoPostCode")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setBillInfoPostCode(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"billInfoPhoneNumber")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setBillInfoPhoneNumber(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"billInfoComments")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setBillInfoComments(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"cardInfoCardType")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setCardInfoCardType(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"cardInfoCardNumber")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setCardInfoCardNumber(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"cardInfoSecurityNumber")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setCardInfoSecurityNumber(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"cardInfoNameOnCard")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setCardInfoNameOnCard(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"billInfoButton")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setBillInfoButton(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"deliveryInfoButton")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setDeliveryInfoButton(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"billCheckBox")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setBillCheckBox(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"homeImage")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setHomeImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"regImage")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setRegImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"mycardImage")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setMycardImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"searchImage")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setSearchImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"browseImage")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setBrowseImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"loginImage")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setLoginImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"settingsImage")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setSettingsImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"specialOfferImage")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setSpecialOfferImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"eventsImage")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setEventsImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"removeProduct")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setRemoveProduct(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"paymentMethodsButton")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setPaymentMethodsButton(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"orderCommentsButton")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setOrderCommentsButton(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"tvImage")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setTvImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"computerImage")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setComputerImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"mobilePhoneImage")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setMobilePhoneImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"audioImage")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setAudioImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"cameraImage")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setCameraImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"tabletImage")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setTabletImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"movieImage")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setMovieImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"videoGameImage")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setVideoGameImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"mp3PlayerImage")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setMp3PlayerImage(vn.toString(key));
				}
				if (vn.toElement(VTDNav.NS,"accessoriesImage")) {
					int value = vn.getAttrVal("type");
					int key = vn.getText();
					uiId.getElementType().put(vn.toString(key), vn.toString(value));
					uiId.setAccessoriesImage(vn.toString(key));
				}
			}			
		}		
		return uiId;
	}
}
