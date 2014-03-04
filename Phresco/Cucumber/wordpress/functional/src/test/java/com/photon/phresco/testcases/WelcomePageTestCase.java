/**
 * Archetype - phresco-wordpress-archetype
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
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.WordPress;

import cucumber.annotation.*;
import cucumber.annotation.en.*;

public class WelcomePageTestCase {

	private  UIConstants uiConstants;
	private  PhrescoUiConstants phrescoUIConstants;
	private  WelcomeScreen welcomeScreen;
	private  String methodName;
	private  String selectedBrowser;
	private  WordPress wordConstants;
	private  UserInfoConstants userInfo;

	@Given("^users calling browser$")
	public  void usersCallingbrowser() throws Exception {
		try {
			phrescoUIConstants = new PhrescoUiConstants();
			uiConstants = new UIConstants();
			wordConstants = new WordPress();
			userInfo= new UserInfoConstants();
			selectedBrowser = phrescoUIConstants.BROWSER;
			
			
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
				/*Reporter.log("Selected Browser to execute testcases--->>"
					+ selectedBrowser);*/
			System.out
			.println("Selected Browser to execute testcases--->>"
					+ selectedBrowser);
			String applicationURL = phrescoUIConstants.PROTOCOL + "://"
					+ phrescoUIConstants.HOST + ":" + phrescoUIConstants.PORT
					+ "/";
			welcomeScreen = new WelcomeScreen(selectedBrowser, applicationURL,
					phrescoUIConstants.CONTEXT, wordConstants, uiConstants, userInfo);
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

/*	public  void launchingBrowser() throws Exception {
		try {
			String applicationURL = phrescoUIConstants.PROTOCOL + "://"
					+ phrescoUIConstants.HOST + ":" + phrescoUIConstants.PORT
					+ "/";
			selectedBrowser = phrescoUIConstants.BROWSER;
			welcomeScreen = new WelcomeScreen(selectedBrowser,selectedPlatform, applicationURL,
					phrescoUIConstants.CONTEXT, wordConstants, uiConstants, userInfo);
		} catch (Exception exception) {

			exception.printStackTrace();

		}

	}*/
	
	
	@Then("user Clicks login")
	public void userClickslogin()
			throws InterruptedException, IOException, Exception {
		try {
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			System.out.println("---------testToLogin()-------------");
		
			welcomeScreen.Login(methodName);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@And("user Post Comment")
	public void userPostComment()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("--------testToSearch()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		     welcomeScreen.PostComent(methodName);
			
			
		
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
@After
	public  void tearDown() {
		welcomeScreen.closeBrowser();
	}

}