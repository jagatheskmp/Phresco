package com.photon.phresco.testcases;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import com.photon.phresco.Screens.BaseScreen;
import com.photon.phresco.uiconstants.AdminUIData;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.UserInfoConstants;

public class LoginPageTest {
	private  UIConstants uiConstants;
	private  PhrescoUiConstants phrescoUIConstants;
	private  BaseScreen baseScreen;
	private  String methodName;
	//private static String selectedBrowser;
	private  AdminUIData adminUIConstants;
	private  UserInfoConstants userInfoConstants;
	

	@Parameters(value = { "browser", "platform" })
	@BeforeTest
	public  void setUp(String browser, String platform) throws Exception {
		try {
			System.out.println("----------------------LoginPage-----------------");
			phrescoUIConstants = new PhrescoUiConstants();
			uiConstants = new UIConstants();
			userInfoConstants =new UserInfoConstants();
			adminUIConstants = new AdminUIData();
			String selectedBrowser = browser;
			String selectedPlatform = platform;
			
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			System.out
			.println("-----------Selected Browser to execute testcases--->>"
					+ selectedBrowser);
			
			String applicationURL = phrescoUIConstants.PROTOCOL + "://"
					+ phrescoUIConstants.HOST + ":" + phrescoUIConstants.PORT
					+ "/";
			
			baseScreen = new BaseScreen(selectedBrowser, selectedPlatform, applicationURL,phrescoUIConstants.CONTEXT,phrescoUIConstants,uiConstants,userInfoConstants,adminUIConstants);
			
		} catch (Exception exception) {
			exception.printStackTrace();

		}

	}
	
	 @Test
	 public void testInValidUsernameValidPasswordLogin()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testLoginAdminUI()-------------");
			methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			
			baseScreen.invalidUsernameValidPassword(methodName);
			//baseScreen.customerSelection(methodName);

			
		} catch (Exception t) {
			t.printStackTrace();

		}
	 }
	 @Test
	 public void testValidUsernameInvalidPassword()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testLoginAdminUI()-------------");
			methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			
			baseScreen.validUsernameInvalidPassword(methodName);
			//baseScreen.customerSelection(methodName);

			
		} catch (Exception t) {
			t.printStackTrace();

		}
	 }
	
	 @Test
	 public void testInvalidUsernameInvalidPassword()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testLoginAdminUI()-------------");
			methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			
			baseScreen.invalidUsernameInvalidPassword(methodName);
			//baseScreen.customerSelection(methodName);

			
		} catch (Exception t) {
			t.printStackTrace();

		}
	 }
	
	 @Test
	 public void testWithoutUsernamePassword()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testLoginAdminUI()-------------");
			methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			
			baseScreen.witoutUsernamePassword(methodName);
			//baseScreen.customerSelection(methodName);

			
		} catch (Exception t) {
			t.printStackTrace();

		}
	 }
	
	 @Test
	 public void testInvalidUsernameEmptyPassword()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testLoginAdminUI()-------------");
			methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			
			baseScreen.invalidUsernameEmptyPassword(methodName);
			//baseScreen.customerSelection(methodName);

			
		} catch (Exception t) {
			t.printStackTrace();

		}
	 }
	
	 @Test
	 public void testValidUsernameEmptyPassword()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testLoginAdminUI()-------------");
			methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			
			baseScreen.validUsernameEmptyPassword(methodName);
			//baseScreen.customerSelection(methodName);

			
		} catch (Exception t) {
			t.printStackTrace();

		}
	 }
	 @Test
	 public void testDashboardPage()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testLoginAdminUI()-------------");
			methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			
			baseScreen.dashboardPage(methodName);
			//baseScreen.customerSelection(methodName);

			
		} catch (Exception t) {
			t.printStackTrace();

		}
	 }
	 @Test
	 public void testValidLogin()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testLoginAdminUI()-------------");
			String methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
				baseScreen.validLoginAdminUI(methodName);
				baseScreen.customerSelection(methodName);
				//baseScreen.deleteArchetypes(methodName);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	 }
	 
	@AfterTest
	public  void tearDown() {
		baseScreen.closeBrowser();
	}

	}
