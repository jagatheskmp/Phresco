package com.photon.phresco.testcases;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.photon.phresco.Screens.BaseScreen;
import com.photon.phresco.uiconstants.AdminUIData;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.UserInfoConstants;

public class LoginPageTest {
	private static UIConstants uiConstants;
	private static PhrescoUiConstants phrescoUIConstants;
	private static BaseScreen baseScreen;
	private static String methodName;
	private static String selectedBrowser;
	private static AdminUIData adminUIConstants;
	private static UserInfoConstants userInfoConstants;
	

	@BeforeTest
	public static void setUp() throws Exception {
		try {
			System.out.println("----------------------LoginPage-----------------");
			phrescoUIConstants = new PhrescoUiConstants();
			uiConstants = new UIConstants();
			userInfoConstants =new UserInfoConstants();
			adminUIConstants = new AdminUIData();
			launchingBrowser();
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public static void launchingBrowser() throws Exception {
		try {
			String applicationURL = phrescoUIConstants.PROTOCOL + "://"
					+ phrescoUIConstants.HOST + ":" + phrescoUIConstants.PORT
					+ "/";
			selectedBrowser = phrescoUIConstants.BROWSER;
			baseScreen = new BaseScreen(selectedBrowser, applicationURL,
					phrescoUIConstants.CONTEXT, adminUIConstants,
					uiConstants,userInfoConstants);
		} catch (Exception exception) {
			exception.printStackTrace();

		}

	}

@Test
public void testEmptyLogin()
		throws InterruptedException, IOException, Exception {
	try {

		System.out
				.println("---------testEmptyLoginAdminUI()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		baseScreen.emptyLoginAdminUI(methodName);
	

		
	} catch (Exception t) {
		t.printStackTrace();

	}
}
@Test
public void testInvalidLogin()
		throws InterruptedException, IOException, Exception {
	try {

		System.out
				.println("---------testInvalidLoginAdminUI()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
	
		baseScreen.invalidLoginAdminUI(methodName);
	

		
	} catch (Exception t) {
		t.printStackTrace();

	}
}
@Test
public void testValidLogin()
		throws InterruptedException, IOException, Exception {
	try {

		System.out
				.println("---------testLoginAdminUI()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
			baseScreen.validLoginAdminUI(methodName);

		
	} catch (Exception t) {
		t.printStackTrace();

	}
}

@AfterTest
public static void tearDown() {
	baseScreen.closeBrowser();
}

}