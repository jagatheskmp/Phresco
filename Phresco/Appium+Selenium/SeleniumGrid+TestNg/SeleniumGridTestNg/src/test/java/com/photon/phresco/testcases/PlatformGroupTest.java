package com.photon.phresco.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.WelcomeScreen;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.UserInfoConstants;


public class PlatformGroupTest {
	private PhrescoUiConstants phrescoUiConstants;
	private WelcomeScreen welcomeScreen;
	private String methodName;
	private UIConstants uiConstants;
	private UserInfoConstants userInfoConstants;

	@Parameters(value = { "browser", "platform" })
	@BeforeTest(alwaysRun=true)
	public void setUp(String browser, String platform) throws Exception {
		try {
			phrescoUiConstants = new PhrescoUiConstants();
			userInfoConstants = new UserInfoConstants();
			uiConstants = new UIConstants();
			String selectedBrowser = browser;
			String selectedPlatform = platform;

			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			String applicationURL = phrescoUiConstants.getProtocol() + "://"
			+ phrescoUiConstants.getHost() + ":"
			+ phrescoUiConstants.getPort() + "/";
			welcomeScreen = new WelcomeScreen(selectedBrowser,
					selectedPlatform, applicationURL,
					phrescoUiConstants.getContext(), userInfoConstants,
					uiConstants,  phrescoUiConstants);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}


	@Test(groups = { "windows.checkintest" })
	public void testWindowsOnly() {
		try {
			System.out
			.println("--------- testWindowsToo() -------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			welcomeScreen.clickOnBrowse(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}


	@Test(groups = {"mac.checkintest"} )
	public void testMacOnly() {
		try {
			System.out
			.println("--------- testmacOnly() -------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			welcomeScreen.clickOnBrowse(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}


	@Test(groups = { "windows.functest"} )
	public void testWindowsToo() {
		try {
			System.out
			.println("--------- testWindowsToo()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			welcomeScreen.clickOnBrowse(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@AfterTest(alwaysRun=true)
	public void tearDown() {
		welcomeScreen.closeBrowser();
	}
}