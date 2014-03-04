/**
 * Archetype - phresco-html5-jquery-archetype
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

import cucumber.annotation.*;
import cucumber.annotation.en.*;

public class WelcomePageTestCase {

	private  static PhrescoUiConstants phrescoUIConstants;
	private  static WelcomeScreen welcomeScreen;
	private  static String selectedBrowser;
	private  static String methodName;

	@Given("the user is on the widgets page")
	public void The_user_is_on_the_Widgets_page() throws Exception {
		try {
			phrescoUIConstants = new PhrescoUiConstants();
			selectedBrowser = phrescoUIConstants.BROWSER;
			
						methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
				
			System.out
			.println("Selected Browser to execute testcases--->>"
					+ selectedBrowser);
			String applicationURL = phrescoUIConstants.PROTOCOL + "://"
					+ phrescoUIConstants.HOST + ":" + phrescoUIConstants.PORT
					+ "/";
			welcomeScreen = new WelcomeScreen(selectedBrowser,applicationURL,
					phrescoUIConstants.CONTEXT,phrescoUIConstants);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	
	/*@Then("^the page title returned should be \"(.*)\"$")
	public void thePageTitleReturned(String expectedResults) throws InterruptedException,
			IOException, Exception {
		try {
			System.out.println("----------testFailureScenario-------");
			welcomeScreen.testFailureCase(methodName);
			
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}*/

	@Then("^the page title returned should be \"(.*)\"$")
	public void thePageTitleReturned(String expectedResults)throws InterruptedException,
			IOException, Exception {
		try {
			
			System.out.println("----------ToVerifyTextPresent-------");
			welcomeScreen.testHellow_world_text(methodName,phrescoUIConstants);
			
		
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@After
	public  void tearDown() {
		welcomeScreen.closeBrowser();
	}

}