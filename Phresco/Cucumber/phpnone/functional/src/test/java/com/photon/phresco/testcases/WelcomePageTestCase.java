/**
 * Archetype - phresco-php-archetype
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
import com.photon.phresco.uiconstants.PhpData;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.UserInfoConstants;

import cucumber.annotation.*;
import cucumber.annotation.en.*;


public class WelcomePageTestCase {

	private  static UIConstants uiConstants;
	private  static PhrescoUiConstants phrescoUIConstants;
	private  static WelcomeScreen welcomeScreen;
	private  static String methodName;
	private  static String selectedBrowser;
	private  static PhpData PhpConstants;
	private  static UserInfoConstants userInfo;

	@Given("^users calling browser$")
	public  void usersCallingbrowser() throws Exception {
		try {
			phrescoUIConstants = new PhrescoUiConstants();
			uiConstants = new UIConstants();
			PhpConstants = new PhpData();
			userInfo= new UserInfoConstants();
			selectedBrowser = phrescoUIConstants.BROWSER;
			
			
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
	
			/*Reporter.log("Selected Browser to execute testcases--->>"
					+ selectedBrowser);*/
			System.out
			.println("Selected Browser to execute testcases--->>"
					+ selectedBrowser);
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			String applicationURL = phrescoUIConstants.PROTOCOL + "://"
					+ phrescoUIConstants.HOST + ":" + phrescoUIConstants.PORT
					+ "/";
			welcomeScreen = new WelcomeScreen(selectedBrowser, applicationURL,
					phrescoUIConstants.CONTEXT, PhpConstants, uiConstants, userInfo);
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
			welcomeScreen = new WelcomeScreen(selectedBrowser, selectedPlatform, applicationURL,
					phrescoUIConstants.CONTEXT, PhpConstants, uiConstants, userInfo);
		} catch (Exception exception) {

			exception.printStackTrace();

		}

	}*/
	
	@Then("^the page title returned should be \"(.*)\"$")
	public void thePageTitleReturned(String expectedResults)
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyTextPresent()-------------");
		
		     welcomeScreen.VerifyTextPresent(methodName);
			
			
		
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Then("^the page title returned should not be \"(.*)\"$")
	public void thePageTitleReturnednotbe(String expectedResults)
		throws InterruptedException, IOException, Exception {
	try {

		System.out
				.println("---------testToVerifyTextNotPresent()-------------");
	
	     welcomeScreen.VerifyTextNotPresent(methodName);
		
		
	
	} catch (Exception t) {
		t.printStackTrace();

	}
}
	
	@After
	public  void tearDown() {
		welcomeScreen.closeBrowser();
	}

}