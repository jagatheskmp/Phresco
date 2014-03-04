/**
 * Archetype - phresco-javawebservice-archetype
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
import com.photon.phresco.uiconstants.PhrescoJavaWebserviceUiConstants;
import com.photon.phresco.uiconstants.PhrescoUiConstants;

import cucumber.annotation.*;
import cucumber.annotation.en.*;



public class JavaWebServiceTest {

	
	private  PhrescoUiConstants phrsc;
	private  PhrescoJavaWebserviceUiConstants javaWservice;
	private  WelcomeScreen welcomeScreen;
	private static String methodName;
	private static String selectedBrowser;
	private static String applicationURL;

	@Given("^users calling browser$")
	public  void usersCallingbrowser() throws Exception {
	{
		
		phrsc=new PhrescoUiConstants();
		javaWservice=new PhrescoJavaWebserviceUiConstants();
		
		selectedBrowser = phrsc.BROWSER;
		
		methodName = Thread.currentThread().getStackTrace()[1]
		                                					.getMethodName();
		System.out.println("Selected Browser to execute testcases--->>"
		                                					+ selectedBrowser);
		applicationURL = phrsc.PROTOCOL + "://"+ phrsc.HOST + ":" + phrsc.PORT+ "/";
	
		welcomeScreen = new WelcomeScreen(selectedBrowser, applicationURL,
				phrsc.CONTEXT);
		
	}
	}

	
	@Then("^the page title returned should be \"(.*)\"$")
	public void thePageTitleReturned(String expectedResults) 
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testHelloWorldPage()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			welcomeScreen.javaWebservcieHelloWorld(methodName, javaWservice);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@After
	public  void tearDown() {
		welcomeScreen.closeBrowser();
	}

}
	
	





