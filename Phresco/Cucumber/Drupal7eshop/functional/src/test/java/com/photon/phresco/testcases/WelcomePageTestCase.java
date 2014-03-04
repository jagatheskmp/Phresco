/**
 * PHR_DrupalEshop
 *
 * Copyright (C) 1999-2013 Photon Infotech Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.photon.phresco.testcases;

import java.io.IOException;


import com.photon.phresco.Screens.WelcomeScreen;
import com.photon.phresco.uiconstants.DrupalData;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.UserInfoConstants;

import cucumber.annotation.*;
import cucumber.annotation.en.*;

public class WelcomePageTestCase {

	private static UIConstants uiConstants;
	private static PhrescoUiConstants phrescoUIConstants;
	private  static WelcomeScreen welcomeScreen;
	private  static String methodName;
	private static String selectedBrowser;
	private  static DrupalData drupalConstants;
	private  static UserInfoConstants userInfo;

	/**
	 * Initializing the Object of a class PhrescoUiConstants, UIConstants, DrupalData, UserInfoConstants
	 * @throws Exception
	 */

	@Given("^users calling browser$")
	public  void usersCallingbrowser() throws Exception {
		try {
			phrescoUIConstants = new PhrescoUiConstants();
			uiConstants = new UIConstants();
			drupalConstants = new DrupalData();
			userInfo = new UserInfoConstants();
			selectedBrowser = phrescoUIConstants.BROWSER;
			
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		    System.out.println("Selected Browser to execute testcases--->>"	+ selectedBrowser);
			String applicationURL = phrescoUIConstants.PROTOCOL + "://"
					+ phrescoUIConstants.HOST + ":" + phrescoUIConstants.PORT
					+ "/";
			welcomeScreen = new WelcomeScreen(selectedBrowser, applicationURL,
					phrescoUIConstants.CONTEXT, drupalConstants, uiConstants, userInfo);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * Capturing the URL values through String & passing those values into WelcomeScreen
	 * @throws Exception
	 */

	/*public  void launchingBrowser() throws Exception {
		try {
			String applicationURL = phrescoUIConstants.PROTOCOL + "://"
					+ phrescoUIConstants.HOST + ":" + phrescoUIConstants.PORT
					+ "/";
			selectedBrowser = phrescoUIConstants.BROWSER;
			welcomeScreen = new WelcomeScreen(selectedBrowser,selectedPlatform, applicationURL,
					phrescoUIConstants.CONTEXT, drupalConstants, uiConstants, userInfo);
		} catch (Exception exception) {
			exception.printStackTrace();

		}

	}*/
	
	/**
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws Exception
	 * In this Method just triggering testcases
	 */

	@Then("user clicks Registration")
	public void userclicksRegistrationoption()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------Registration-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			welcomeScreen.RegisterCheck(methodName);
			
		} catch (Exception t) {
	  }

	}
	
	
	@And("user Clicks login")
	public void userClicksloginoption()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------Login-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			welcomeScreen.loginDrupal(methodName);
			
		} catch (Exception t) {
	  }

	}
	
	
	@And("user Requests new password")
	public void userRequestsnewpassword()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------RequestNew-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			welcomeScreen.RequestNewPassword(methodName);
			
		} catch (Exception t) {
	  }

	}
	
	@And("user clicks Category")
	public void userclicksCategory()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testToCategoryLink()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			welcomeScreen.categorySelect(methodName);
			welcomeScreen.CategorySelectLast(methodName);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@And("user clicks checkout")
	public void userclicksCheckout()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testToCategoryCheckOut()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			welcomeScreen.checkoutCategory(methodName);

		
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	@And("user clicks specialtab")
	public void userclicksSpecialtab()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testToSpecialTab()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			welcomeScreen.homeSpecialTab(methodName);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@And("user clicks Homeshoppingcart")
	public void userclicksHomeshoppingcart()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testToShoppingCart()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			welcomeScreen.homeShoppingCart(methodName);
		
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@And("user clicks aboutus")
	public void userclicksAboutus()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------Aboutus()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
				    
		    welcomeScreen.aboutUs(methodName);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@And("user Clicks contactsus")
	public void userClickscontactsus()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------Cantactus()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		    welcomeScreen.contactUs(methodName);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@And("user clicks searchproducts")
	public void userclicksSearchproducts()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------Search()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
		    welcomeScreen.searchProducts(methodName);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@And("user clicks Logoff")
	public void userclicksLogoff()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------Logoff()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		  
			welcomeScreen.logOff(methodName);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	/**
	 * 	Triggering close method in BaseScreen 
	 */
	
	@After
	public  void tearDown() {
		welcomeScreen.closeBrowser();
	}

}