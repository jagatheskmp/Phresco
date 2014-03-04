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
	public void userclicksRegistration()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------Registration-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			welcomeScreen.createAccount(methodName);
			
		} catch (Exception t) {
	  }

	}
	
	
	@And("user Clicks login")
	public void userClickslogin()
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
			welcomeScreen.searchDrupal(methodName);
			
		} catch (Exception t) {
	  }

	}
	

	@After
	public  void tearDown() {
		welcomeScreen.closeBrowser();
	}

}