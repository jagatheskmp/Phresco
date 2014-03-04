/**
 * Archetype - Sharepoint
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

import junit.framework.Assert;

import com.photon.phresco.Screens.WelcomeScreen;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.SharepointData;
import com.photon.phresco.uiconstants.UIConstants;

import cucumber.annotation.*;
import cucumber.annotation.en.*;
public class SharepointTest {

	private static UIConstants uiConstants;
	private static PhrescoUiConstants phrescoUIConstants;
	private static WelcomeScreen welcomeScreen;
	private static String methodName;
	private static String selectedBrowser;
	private static SharepointData WidgetConstants;

	// private Log log = LogFactory.getLog(getClass());

	@Given("^users calling browser$")
	public static void usersCallingbrowser() throws Exception {
		try {
			phrescoUIConstants = new PhrescoUiConstants();
			uiConstants = new UIConstants();
			//assertNotNull(uiConstants);
			WidgetConstants = new SharepointData();
			selectedBrowser = phrescoUIConstants.BROWSER;
			
			// menuScreen = welcomeScreen.menuScreen(uiConstants);
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			System.out.println("Selected Browser to execute testcases--->>"	+ selectedBrowser);
			String applicationURL = phrescoUIConstants.PROTOCOL + "://"
					+ phrescoUIConstants.HOST + ":" + phrescoUIConstants.PORT
					+ "/";
			
			welcomeScreen = new WelcomeScreen(selectedBrowser, applicationURL,
					phrescoUIConstants.CONTEXT, WidgetConstants, uiConstants);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
		
	@Then("user checks WelcomePage")
	public void userchecksWelcomePage() throws InterruptedException,
			IOException, Exception {
		try {
			Assert.assertNotNull(welcomeScreen);
			// Thread.sleep(10000);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@And("^the page title returned should be \"(.*)\"$")
	public void thePageTitleReturned(String expectedResults)
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyTheVmAllocation()-------------");
			welcomeScreen.check(methodName);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	@After
	public static void tearDown() {
		welcomeScreen.closeBrowser();
}
	
}