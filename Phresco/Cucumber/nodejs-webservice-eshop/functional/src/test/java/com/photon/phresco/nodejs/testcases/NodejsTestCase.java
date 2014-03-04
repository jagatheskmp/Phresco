/**
 * PHR_NodeJSWebService
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
/*
 * Author by {phresco} QA Automation Team
 */
package com.photon.phresco.nodejs.testcases;

import java.io.IOException;



import com.photon.phresco.Screens.BaseScreen;
import com.photon.phresco.Screens.WelcomeScreen;
import com.photon.phresco.uiconstants.NodeJs;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UiConstants;


import cucumber.annotation.*;
import cucumber.annotation.en.*;
public class NodejsTestCase extends BaseScreen {

	private static PhrescoUiConstants phrsc;
	private static NodeJs nodejsconst;
	private static UiConstants uiconstants;
	private static String selectedBrowser;
    private static WelcomeScreen wel;
	
	private static String methodName;
	private static String contextName;
	public static String applicationContext;
	public static String applicationURL;

	@Given("^users calling browser$")
	public void usersCallingbrowser() throws Exception {

		try {
			phrsc = new PhrescoUiConstants();
			nodejsconst = new NodeJs();
			applicationURL = phrsc.PROTOCOL + "://" + phrsc.HOST + ":"
					+ phrsc.PORT + "/";
			selectedBrowser = phrsc.BROWSER;
			//selectedPlatform = platform;
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			System.out.println("Selected Browser to execute testcases--->>"
					+ selectedBrowser);
			wel = new WelcomeScreen(selectedBrowser, applicationURL, phrsc.CONTEXT, phrsc, nodejsconst,
					uiconstants);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Then("user clicks Nodejseshop")
	public void userclicksNodejseshop() throws InterruptedException, IOException,
			Exception {
		try {

			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			System.out.println("methodName = " + methodName);
			wel.nodejsCategories(methodName);
			System.out.println("Sample Testing*******************************");
			wel.nodejsEshop(methodName);
			System.out.println("TestCategory1*******************************");
			wel.nodejsCategory1(methodName);
			System.out.println("TestCategory2*******************************");
			wel.nodejsCategory2(methodName);
			System.out.println("TestCategory3*******************************");
			wel.nodejsCategory3(methodName);
			System.out
					.println("TestNewProducts*******************************");
			wel.Nodejsnewproducts(methodName);
			System.out
					.println("TestAPIProducts*******************************");
			wel.nodejsProducts(methodName);
			System.out
					.println("TestSearchComputer*******************************");
			wel.nodejsSearchComputer(methodName);
			System.out
					.println("TestAPISearchMobile*******************************");
			wel.nodejsSearchMobile(methodName);
			System.out
					.println("TestAPISpecialProducts*******************************");
			wel.nodejsSpecialproducts(methodName);

			// contextName=phrsc.CONTEXT;
			// wel.navigatePath(applicationURL, contextName);
		} catch (Exception t) {
			// wel.ScreenCapturer();
		}
	}
	
	@And("user clicks categories")
	public void userClickscategories() throws InterruptedException, IOException,
			Exception {
		try {

			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			contextName = phrsc.CONTEXT + nodejsconst.CONTEXCATEGORIES;
			System.out.println("The Context Name is:::::::::::::::"
					+ contextName);
			wel.navigatePath(applicationURL, contextName);
			wel.testCategories(applicationURL);

		} catch (Exception t) {
			// wel.ScreenCapturer();
		}
	}

	@And("user clicks categories1")
	public void userclicksCategories1() throws InterruptedException, IOException,
			Exception {
		try {

			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			contextName = phrsc.CONTEXT + nodejsconst.CONTEXTCATEGORY1;
			System.out.println("The Context Name is:::::::::::::::"
					+ contextName);
			wel.navigatePath(applicationURL, contextName);
			wel.testCategory1(applicationURL, phrsc, nodejsconst);

		} catch (Exception t) {
		}
	}

	@And("user clicks categories2")
	public void userclicksCategories2() throws InterruptedException, IOException,
			Exception {
		try {

			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			contextName = phrsc.CONTEXT + nodejsconst.CONTEXTCATEGORY2;
			System.out.println("The Context Name is:::::::::::::::"
					+ contextName);
			wel.navigatePath(applicationURL, contextName);
			wel.testCategory2(applicationURL, phrsc, nodejsconst);

		} catch (Exception t) {
			// wel.ScreenCapturer();
		}
	}

	@And("user clicks categories3")
	public void userclicksCategories3() throws InterruptedException, IOException,
			Exception {
		try {

			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			contextName = phrsc.CONTEXT + nodejsconst.CONTEXTCATEGORY3;
			System.out.println("The Context Name is:::::::::::::::"
					+ contextName);
			wel.navigatePath(applicationURL, contextName);
			wel.testCategory3(applicationURL);

		} catch (Exception t) {
			// wel.ScreenCapturer();
		}
	}

	@And("user clicks categories4")
	public void userclicksCategories4() throws InterruptedException, IOException,
			Exception {
		try {

			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			contextName = phrsc.CONTEXT + nodejsconst.CONTEXTNEWPRODUCTS;
			System.out.println("The Context Name is:::::::::::::::"
					+ contextName);
			wel.navigatePath(applicationURL, contextName);
			wel.testNewProducts(applicationURL);

		} catch (Exception t) {
			// wel.ScreenCapturer();
		}
	}

	@And("user clicks categories5")
	public void userclicksCategories5() throws InterruptedException, IOException,
			Exception {
		try {

			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			contextName = phrsc.CONTEXT + nodejsconst.CONTEXTPRODUCTS;
			System.out.println("The Context Name is:::::::::::::::"
					+ contextName);
			wel.navigatePath(applicationURL, contextName);
			wel.Products(applicationURL);

		} catch (Exception t) {
			// wel.ScreenCapturer();
		}
	}

	@And("user clicks categories6")
	public void userclicksCategories6() throws InterruptedException, IOException,
			Exception {
		try {

			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			contextName = phrsc.CONTEXT + nodejsconst.CONTEXTSEARCHCOMPUTER;
			System.out.println("The Context Name is:::::::::::::::"
					+ contextName);
			wel.navigatePath(applicationURL, contextName);
			wel.SearchComputer(applicationURL);

		} catch (Exception t) {
			// wel.ScreenCapturer();
		}
	}

	@And("user clicks categories7")
	public void userclicksCategories7() throws InterruptedException, IOException,
			Exception {
		try {

			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			contextName = phrsc.CONTEXT + nodejsconst.CONTEXTSEARCHMOBILE;
			System.out.println("The Context Name is:::::::::::::::"
					+ contextName);
			wel.navigatePath(applicationURL, contextName);
			wel.SearchMobile(applicationURL);

		} catch (Exception t) {
			// wel.ScreenCapturer();
		}
	}

	@And("user clicks categories8")
	public void userclicksCategories8() throws InterruptedException, IOException,
			Exception {
		try {

			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			contextName = phrsc.CONTEXT + nodejsconst.CONTEXTSPECIALPRODUCTS;
			System.out.println("The Context Name is:::::::::::::::"
					+ contextName);
			wel.navigatePath(applicationURL, contextName);
			wel.SpecialProducts(applicationURL);

		} catch (Exception t) {
			// wel.ScreenCapturer();
		}
	}

	@After
	public void tearDown() {
		wel.closeBrowser();
	}

}