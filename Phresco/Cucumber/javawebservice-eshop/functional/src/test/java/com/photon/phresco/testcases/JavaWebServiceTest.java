/**
 * PHR_JavaWebService
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
package com.photon.phresco.testcases;

import java.io.IOException;

import com.photon.phresco.Screens.TestJson;
import com.photon.phresco.Screens.WelcomeScreen;
import com.photon.phresco.uiconstants.PhrescoJavaWebserviceUiConstants;
import com.photon.phresco.uiconstants.PhrescoUiConstants;

import cucumber.annotation.After;
import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;

public class JavaWebServiceTest {

	private static PhrescoJavaWebserviceUiConstants jws;
	private static String selectedBrowser;
	

	private static PhrescoUiConstants phrsc;
	private static String contextName;
	private static WelcomeScreen wel;
	private static String serverURL;

	private static String methodName;

	
	@Given("^users calling browser$")
	public void usersCallingbrowser() throws Exception {

		phrsc = new PhrescoUiConstants();
		jws = new PhrescoJavaWebserviceUiConstants();
		serverURL = phrsc.PROTOCOL + "://" + phrsc.HOST + ":" + phrsc.PORT
				+ "/";
		selectedBrowser = phrsc.BROWSER;
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Selected Browser to execute testcases--->>"
				+ selectedBrowser);
		wel = new WelcomeScreen(selectedBrowser, serverURL,
				phrsc.CONTEXT);
		

	}

	@Then("user clicks Nodejseshop")
	public void userclicksNodejseshop() throws InterruptedException, IOException,
			Exception {
		try {

			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			System.out.println("methodName = " + methodName);
			System.out.println("Sample Testing*******************************");
			Thread.sleep(5000);
			wel.JwsCategories(methodName);
			wel.JwsCategory1(methodName);
			wel.JwsCategory2(methodName);
			wel.JwsCategory3(methodName);
			wel.Jwsnewproducts(methodName);
			wel.JwsProducts(methodName);
			wel.JwsSearchComputer(methodName);
			wel.JwsSearchMobile(methodName);
			wel.JwsSpecialproducts(methodName);
			wel.JwsEshop(methodName);
			System.out.println("Sample Testing*******************************");

		} catch (Exception t) {
			new RuntimeException(t);
		}
	}

	@And("user clicks categories")
	public void userClickscategories() throws InterruptedException, IOException,
			Exception {
		try {
			System.out
					.println("***********Entering: PageNavigationTestMethod*************");
			System.out.println("***********restApiURL*************" + serverURL
					+ contextName);
			contextName = phrsc.CONTEXT + jws.CONTEXT_REST_API
					+ jws.CONTEXT_CATEGORIES;
			wel.restApiGeneral(serverURL, contextName);
			TestJson nodejson = new TestJson();
			nodejson.testCategories(serverURL, phrsc, jws);

		} catch (Exception t) {
			new RuntimeException(t);
		}
	}

	/*
	 * @Test public void testCategory1() throws InterruptedException,
	 * IOException, Exception { try{
	 * 
	 * assertNotNull("Browser name should not be null",selectedBrowser);
	 * assertNotNull("selenium-port number should not be null", SELENIUM_PORT);
	 * assertNotNull(wel); methodName =
	 * Thread.currentThread().getStackTrace()[1] .getMethodName();
	 * System.out.println("methodName = " + methodName);
	 * contextName=phrsc.CONTEXT; wel.restApiCategories(serverURL,contextName);
	 * wel.JwsCategory1(methodName);
	 * System.out.println("*******************End of testCategory1************"
	 * );
	 * 
	 * 
	 * } catch(Exception t){ new RuntimeException(t); } }
	 */
	@And("user clicks categories1")
	public void userclicksCategories1() throws InterruptedException, IOException,
			Exception {
		try {

			System.out
					.println("***********Entering: PageNavigationTestMethod*************");
			System.out.println("***********Page:Navigation*************");

			contextName = phrsc.CONTEXT + jws.CONTEXT_REST_API
					+ jws.CONTEXT_CATEGORY1;
			wel.restApiGeneral(serverURL, contextName);
			TestJson nodejson = new TestJson();
			nodejson.testCategory1();

		} catch (Exception t) {
			new RuntimeException(t);
		}
	}

	@And("user clicks categories2")
	public void userclicksCategories2() throws InterruptedException,
			IOException, Exception {
		try {

			System.out
					.println("***********Entering: PageNavigationTestMethod*************");
			System.out.println("***********Page:Navigation*************");
			contextName = phrsc.CONTEXT + jws.CONTEXT_REST_API
					+ jws.CONTEXT_CATEGORY2;
			wel.restApiGeneral(serverURL, contextName);
			TestJson nodejson = new TestJson();
			nodejson.testCategory2();

		} catch (Exception t) {
			new RuntimeException(t);
		}
	}

	@And("user clicks categories3")
	public void userclicksCategories3() throws InterruptedException,
			IOException, Exception {
		try {

			System.out
					.println("***********Entering: PageNavigationTestMethod*************");
			System.out.println("***********Page:Navigation*************");
			contextName = phrsc.CONTEXT + jws.CONTEXT_REST_API
					+ jws.CONTEXT_CATEGORY3;
			wel.restApiGeneral(serverURL, contextName);
			TestJson nodejson = new TestJson();
			nodejson.testCategory3();

		} catch (Exception t) {
			new RuntimeException(t);
		}
	}
	@And("user clicks Newproducts")
	public void userclicksNewproducts() throws InterruptedException,
			IOException, Exception {
		try {

			System.out
					.println("***********Entering: PageNavigationTestMethod*************");
			System.out.println("***********Page:Navigation*************");
			contextName = (phrsc.CONTEXT + jws.CONTEXT_REST_API + jws.CONTEXT_NEWPRODUCTS);
			wel.restApiGeneral(serverURL, contextName);
			TestJson nodejson = new TestJson();
			nodejson.testNewProducts();

		} catch (Exception t) {
			new RuntimeException(t);
		}
	}

	@And("user clicks Products")
	public void userclicksProducts() throws InterruptedException,
			IOException, Exception {
		try {

			System.out
					.println("***********Entering: PageNavigationTestMethod*************");
			System.out.println("***********Page:Navigation*************");
			contextName = phrsc.CONTEXT + jws.CONTEXT_REST_API
					+ jws.CONTEXT_PRODUCTS;
			wel.restApiGeneral(serverURL, contextName);
			TestJson nodejson = new TestJson();
			nodejson.Products();

		} catch (Exception t) {
			new RuntimeException(t);
		}
	}
	@And("user clicks Computer")
	public void userclicksComputer() throws InterruptedException,
			IOException, Exception {
		try {

			System.out
					.println("***********Entering: PageNavigationTestMethod*************");
			System.out.println("***********Page:Navigation*************");
			contextName = phrsc.CONTEXT + jws.CONTEXT_REST_API
					+ jws.CONTEXT_SEARCHCOUMPUTER;
			wel.restApiGeneral(serverURL, contextName);
			TestJson nodejson = new TestJson();
			nodejson.SearchComputer();

		} catch (Exception t) {
			new RuntimeException(t);
		}
	}
	@And("user clicks Mobile")
	public void userclicksMobile() throws InterruptedException,
			IOException, Exception {
		try {

			System.out
					.println("***********Entering: PageNavigationTestMethod*************");
			System.out.println("***********Page:Navigation*************");
			contextName = phrsc.CONTEXT + jws.CONTEXT_REST_API
					+ jws.CONTEXT_SEARCHMOBILE;
			wel.restApiGeneral(serverURL, contextName);
			TestJson nodejson = new TestJson();
			nodejson.SearchMobile();

		} catch (Exception t) {
			new RuntimeException(t);
		}
	}
	@And("user clicks Specialproducts")
	public void userclicksSpecialproducts() throws InterruptedException,
			IOException, Exception {
		try {

			System.out
					.println("***********Entering: PageNavigationTestMethod*************");
			System.out.println("***********Page:Navigation*************");
			contextName = phrsc.CONTEXT + jws.CONTEXT_REST_API
					+ jws.CONTEXT_SPECIAL_PRODUCTS;
			wel.restApiGeneral(serverURL, contextName);
			TestJson nodejson = new TestJson();
			nodejson.SpecialProducts();

		} catch (Exception t) {
			new RuntimeException(t);
		}
	}
	@And("user clicks Welcomepage")
	public void userclicksWelcomepage() throws InterruptedException,
			IOException, Exception {
		try {

			System.out
					.println("***********Entering: PageNavigationTestMethod*************");
			System.out.println("***********Page:Navigation*************");
			contextName = phrsc.CONTEXT;
			wel.restApiGeneral(serverURL, contextName);

		} catch (Exception t) {
			new RuntimeException(t);
		}
	}

	@After
	public void tearDown() {
		clean();
	}

	private void clean() {
		wel.closeBrowser();
	}

}