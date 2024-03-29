
package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UIConstants {

	private Log log = LogFactory.getLog("JquerMobile Widget UIConstants");

	public String browse = "browseWidget";
	public String browseTab = "browseWidgetTab";
	public String browseBack = "browsepgBackButton";
	public String television = "televisiontab";
	public String computers = "computerstab";
	public String mobile = "mobiletab";
	public String audioDevice = "audioDevicestab";
	public String camera = "camerastab";
	public String tablets = "tabletstab";
	public String movieAndMusic = "moviesNmusictab";
	public String videoGame = "videoGamestab";
	public String mp3Player = "mp3Playerstab";
	public String accessories = "accessoriestab";
	public String more = "moretab";
	public String home = "hometab";
	public String teleProd1Det = "teleProd1Details";
	public String compProd1Det = "compProd1Details";
	public String mobileProd1Det = "mobileProd1Details";
	public String audioProd1Det = "audioProd1Details";
	public String camerasProd1Det = "camerasProd1Details";
	public String tabletsProd1Det = "tabProd1Details";
	public String mAndMProd1Det = "MnMProd1Details";
	public String videoGameProd1Det = "vidGamesProd1Details";
	public String mp3Prod1Det = "mp3Prod1Details";
	public String accProd1Det = "accessoriesProd1Details";
	public String state = "billInfoState";
	public String review = "review";
	public String phoneNumber = "billInfoPhoneNumber";
	public String addToCart = "addToCart";
	public String updateCart = "updateCart";
	public String viewCart = "viewMyCart";
	public String checkOut = "checkout";
	public String customerInformation = "custInfo";
	public String email = "billInfoEmail";
	public String deliveryInfo = "deliveryInfo";
	public String firstname = "billInfoFirstName";
	public String lastname = "billInfoLastName";
	public String company = "billInfoCompany";
	public String address1 = "billInfoAddress1";
	public String address2 = "billInfoAddress2";
	public String city = "billInfoCity";
	public String postcode = "billInfoPostCode";
	public String billingInfo = "billInfo";
	public String checkAddress = "billInfocheckAddress";
	public String paymentMethod = "billInfopaymentMethods";
	public String cashOnDelivery = "billInfoPayMethodCOD";
	public String chequeOrCash = "billInfoPayMethodCheqOrCash";
	public String orderComments = "billInfoComments";
	public String giveComments = "billInfoGiveComments";
	public String reviewOrder = "reviewOrder";
	public String submitorder = "submitOrder";
	public String homeIcon ="homeIcon";
	
	public UIConstants() {
		//Field localField = null;
		try {
			ReadXMLFile readXml = new ReadXMLFile();
			readXml.loadUIConstants();
			Field[] arrayOfField = this.getClass().getDeclaredFields();
			for (Field field : arrayOfField) {
				field.setAccessible(true);
				Object localObject = field.get(this);
				if (localObject instanceof String) {
					field.set(this, readXml.getValue((String) localObject));
				}
			}
		} catch (Exception localException) {
			log.info("Exception in JquerMobile Widget UIConstants"
					+ localException.getMessage());
		}
	}

	public String getBrowse() {
		return browse;
	}

	public void setBrowse(String browse) {
		this.browse = browse;
	}

	public String getBrowseTab() {
		return browseTab;
	}

	public void setBrowseTab(String browseTab) {
		this.browseTab = browseTab;
	}

	public String getBrowseBack() {
		return browseBack;
	}

	public void setBrowseBack(String browseBack) {
		this.browseBack = browseBack;
	}

	public String getTelevision() {
		return television;
	}

	public void setTelevision(String television) {
		this.television = television;
	}

	public String getComputers() {
		return computers;
	}

	public void setComputers(String computers) {
		this.computers = computers;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAudioDevice() {
		return audioDevice;
	}

	public void setAudioDevice(String audioDevice) {
		this.audioDevice = audioDevice;
	}

	public String getCamera() {
		return camera;
	}

	public void setCamera(String camera) {
		this.camera = camera;
	}

	public String getTablets() {
		return tablets;
	}

	public void setTablets(String tablets) {
		this.tablets = tablets;
	}

	public String getMovieAndMusic() {
		return movieAndMusic;
	}

	public void setMovieAndMusic(String movieAndMusic) {
		this.movieAndMusic = movieAndMusic;
	}

	public String getVideoGame() {
		return videoGame;
	}

	public void setVideoGame(String videoGame) {
		this.videoGame = videoGame;
	}

	public String getMp3Player() {
		return mp3Player;
	}

	public void setMp3Player(String mp3Player) {
		this.mp3Player = mp3Player;
	}

	public String getAccessories() {
		return accessories;
	}

	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getTeleProd1Det() {
		return teleProd1Det;
	}

	public void setTeleProd1Det(String teleProd1Det) {
		this.teleProd1Det = teleProd1Det;
	}

	public String getCompProd1Det() {
		return compProd1Det;
	}

	public void setCompProd1Det(String compProd1Det) {
		this.compProd1Det = compProd1Det;
	}

	public String getMobileProd1Det() {
		return mobileProd1Det;
	}

	public void setMobileProd1Det(String mobileProd1Det) {
		this.mobileProd1Det = mobileProd1Det;
	}

	public String getAudioProd1Det() {
		return audioProd1Det;
	}

	public void setAudioProd1Det(String audioProd1Det) {
		this.audioProd1Det = audioProd1Det;
	}

	public String getCamerasProd1Det() {
		return camerasProd1Det;
	}

	public void setCamerasProd1Det(String camerasProd1Det) {
		this.camerasProd1Det = camerasProd1Det;
	}

	public String getTabletsProd1Det() {
		return tabletsProd1Det;
	}

	public void setTabletsProd1Det(String tabletsProd1Det) {
		this.tabletsProd1Det = tabletsProd1Det;
	}

	public String getmAndMProd1Det() {
		return mAndMProd1Det;
	}

	public void setmAndMProd1Det(String mAndMProd1Det) {
		this.mAndMProd1Det = mAndMProd1Det;
	}

	public String getVideoGameProd1Det() {
		return videoGameProd1Det;
	}

	public void setVideoGameProd1Det(String videoGameProd1Det) {
		this.videoGameProd1Det = videoGameProd1Det;
	}

	public String getMp3Prod1Det() {
		return mp3Prod1Det;
	}

	public void setMp3Prod1Det(String mp3Prod1Det) {
		this.mp3Prod1Det = mp3Prod1Det;
	}

	public String getAccProd1Det() {
		return accProd1Det;
	}

	public void setAccProd1Det(String accProd1Det) {
		this.accProd1Det = accProd1Det;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddToCart() {
		return addToCart;
	}

	public void setAddToCart(String addToCart) {
		this.addToCart = addToCart;
	}

	public String getUpdateCart() {
		return updateCart;
	}

	public void setUpdateCart(String updateCart) {
		this.updateCart = updateCart;
	}

	public String getViewCart() {
		return viewCart;
	}

	public void setViewCart(String viewCart) {
		this.viewCart = viewCart;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

	public String getCustomerInformation() {
		return customerInformation;
	}

	public void setCustomerInformation(String customerInformation) {
		this.customerInformation = customerInformation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDeliveryInfo() {
		return deliveryInfo;
	}

	public void setDeliveryInfo(String deliveryInfo) {
		this.deliveryInfo = deliveryInfo;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getBillingInfo() {
		return billingInfo;
	}

	public void setBillingInfo(String billingInfo) {
		this.billingInfo = billingInfo;
	}

	public String getCheckAddress() {
		return checkAddress;
	}

	public void setCheckAddress(String checkAddress) {
		this.checkAddress = checkAddress;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getCashOnDelivery() {
		return cashOnDelivery;
	}

	public void setCashOnDelivery(String cashOnDelivery) {
		this.cashOnDelivery = cashOnDelivery;
	}

	public String getChequeOrCash() {
		return chequeOrCash;
	}

	public void setChequeOrCash(String chequeOrCash) {
		this.chequeOrCash = chequeOrCash;
	}

	public String getOrderComments() {
		return orderComments;
	}

	public void setOrderComments(String orderComments) {
		this.orderComments = orderComments;
	}

	public String getGiveComments() {
		return giveComments;
	}

	public void setGiveComments(String giveComments) {
		this.giveComments = giveComments;
	}

	public String getReviewOrder() {
		return reviewOrder;
	}

	public void setReviewOrder(String reviewOrder) {
		this.reviewOrder = reviewOrder;
	}

	public String getSubmitorder() {
		return submitorder;
	}

	public void setSubmitorder(String submitorder) {
		this.submitorder = submitorder;
	}

	public String getHomeIcon() {
		return homeIcon;
	}

	public void setHomeIcon(String homeIcon) {
		this.homeIcon = homeIcon;
	}

	
}
