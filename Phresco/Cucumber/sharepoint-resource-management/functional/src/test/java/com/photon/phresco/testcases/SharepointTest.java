/**
 * PHR_SharePointResourceManagement
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
import com.photon.phresco.uiconstants.SharepointData;
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
	
	@Then("user clicks Allocation")
	public void userclicksAllocation()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyTheVmAllocation()-------------");
			welcomeScreen.VM_Allocation(methodName);
			//welcomeScreen.billingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
@And("user clicks Valid allocation")
	public void userclicksValidallocation()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyVmvalidIP()-------------");
			welcomeScreen.VM_Allocation_VALID_IP(methodName);
			//welcomeScreen.billingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@And("user clicks Invalid allocation")
	public void userclicksInvalidallocation()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyVmAllocationInvalidIP()-------------");
			welcomeScreen.VM_Allocation_Invalid_ip(methodName);
			//welcomeScreen.billingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@And("user clicks create list")
	public void userclickscreatelist()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testToVerifycreatelist()-------------");
			welcomeScreen.create_list(methodName);
			//welcomeScreen.billingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@And("user clicks list")
	public void userclickslist()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testToVerifycreatelist()-------------");
			welcomeScreen.LIST(methodName);
			//welcomeScreen.billingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@And("user clicks mosterpage")
	public void userclicksmosterpage()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyMasterpage()-------------");
			welcomeScreen.MostersPage(methodName);
			//welcomeScreen.billingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	@And("user searchingall")
	public void usersearchingall()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyhome_search()-------------");
			welcomeScreen.HOME_SEARCH_ALL(methodName);
			//welcomeScreen.billingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@And("user clicks allocation list")
	public void userclicksallocationlist()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyvmallocationlist()-------------");
			welcomeScreen.VM_ALLOCATION_LIST(methodName);
			//welcomeScreen.billingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	@After
	public static void tearDown() {
		welcomeScreen.closeBrowser();
}
	
}