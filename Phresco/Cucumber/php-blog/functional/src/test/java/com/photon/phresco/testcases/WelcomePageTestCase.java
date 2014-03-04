/**
 * PHR_PhpBlog
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

import cucumber.annotation.After;
import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;

public class WelcomePageTestCase {

	private  static UIConstants uiConstants;
	private  static PhrescoUiConstants phrescoUIConstants;
	private  static WelcomeScreen welcomeScreen;
	private  static String methodName;
	private  static String selectedBrowser;
	private  static PhpData phpConstants;
	private  static UserInfoConstants uif;

	// private Log log = LogFactory.getLog(getClass());

	//@BeforeGroups(groups = { "fast" })
	//@Parameters({"browser"})
	
	@Given("^the user is on the Welcome page$")
	public  void theuserisontheWelcomepage() throws Exception {
		try {
			phrescoUIConstants = new PhrescoUiConstants();
			uiConstants = new UIConstants();
			// assertNotNull(uiConstants);
			phpConstants = new PhpData();
			uif= new UserInfoConstants();
			selectedBrowser = phrescoUIConstants.BROWSER;
			
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			System.out.println("Selected Browser to execute testcases--->>"
					+ selectedBrowser);
			String applicationURL = phrescoUIConstants.PROTOCOL + "://"
					+ phrescoUIConstants.HOST + ":" + phrescoUIConstants.PORT
					+ "/";
			
			welcomeScreen = new WelcomeScreen(selectedBrowser, applicationURL,
					phrescoUIConstants.CONTEXT, phpConstants, uiConstants,uif);
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			Thread.sleep(5000);
			 
			/*System.out.println("*****************************************************************");
			phrescoUIConstants = new PhrescoUiConstants();
			uiConstants = new UIConstants();
			// assertNotNull(uiConstants);
			phpConstants = new PhpData();
			uif= new UserInfoConstants();
			selectedBrowser = phrescoUIConstants.BROWSER;
			
			
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
				Reporter.log("Selected Browser to execute testcases--->>"
					+ selectedBrowser);
			System.out
			.println("Selected Browser to execute testcases--->>"
					+ selectedBrowser);
			String applicationURL = phrescoUIConstants.PROTOCOL + "://"
					+ phrescoUIConstants.HOST + ":" + phrescoUIConstants.PORT
					+ "/";
			
			welcomeScreen = new WelcomeScreen(selectedBrowser,applicationURL,
					phrescoUIConstants.CONTEXT, phpConstants, uiConstants, uif);*/
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}


	@Then("^user clicks Registration$")
	public void userclicksRegistration()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testToVerifyRegistration()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			welcomeScreen.RegisterChek(methodName,uiConstants);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	

	
	@And("^user clicks Login$")
	public void userclicksLogin()
			throws InterruptedException, IOException, Exception {
		try {


			System.out.println("---------testToVerifyTheLogin()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			welcomeScreen.LoginChek(methodName,uiConstants);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	/*@And("^user clicks Search$")
	public void userclicksSearch()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testTosearch()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			welcomeScreen.FindChek(methodName,uiConstants,phpConstants);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	@And("^user clicks Update$")
	public void userclicksUpdate()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testToUpdateCategory()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			welcomeScreen.UpdateAccount(methodName,uiConstants,phpConstants);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@And("^user Create Category$")
	public void userCreateCategory()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out.println("---------testToCreateCategory()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			welcomeScreen.CreateCategory(methodName,uiConstants,phpConstants);			
		    
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@And("^user Update Category$")
	public void userUpdateCategory()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out.println("---------testToUpdateCategory()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
					
		    welcomeScreen.updateCategory(methodName,uiConstants,phpConstants);
			
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@And("^user Delete Category$")
	public void userDeleteCategory()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testToDeleteCategory()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			welcomeScreen.deleteCategory(methodName,uiConstants,phpConstants);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@And("^user Create Topic$")
	public void userCreateTopic()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testToCreateTopic()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			welcomeScreen.CreateTopic(methodName,uiConstants,phpConstants);
		
			
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@And("user Edit Topic")
	public void userEditTopic()
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testToEditTopic()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
   
		welcomeScreen.editTopic(methodName,uiConstants,phpConstants);
	
		
		
	} catch (Exception t) {
		t.printStackTrace();

	}
}

	@And("user Delete Topic")
	public void userDeleteTopic()
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testToDeleteTopic()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
    
		welcomeScreen.deleteTopic(methodName,uiConstants,phpConstants);
		
		
	} catch (Exception t) {
		t.printStackTrace();

	}
}*/


@After
public  void tearDown() {
	System.out.println("============================================");
	welcomeScreen.closeBrowser();
}

}
