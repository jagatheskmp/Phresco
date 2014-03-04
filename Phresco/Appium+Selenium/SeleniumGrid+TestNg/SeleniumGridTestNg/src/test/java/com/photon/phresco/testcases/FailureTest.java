
package com.photon.phresco.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.WelcomeScreen;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.UserInfoConstants;

public class FailureTest {

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

	@Test
	public void AwithoutGroup() throws InterruptedException,
	IOException, Exception {
		try {
			System.out.println("*************withoutGroup***********************");
			Assert.assertNotNull(welcomeScreen);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void verifyAudioDevices()
	throws InterruptedException, IOException, 
	Exception {
		try {

			System.out
			.println("--------- verifyAudioDevices() in group1-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

		welcomeScreen.clickOnBrowse(methodName);
			welcomeScreen.AudioDevices(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}

	}

	@Test
	public void verifyCameras()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------verifyCameras() in group1-------------");
			welcomeScreen.clickOnBrowseTab(methodName);
			welcomeScreen.Cameras(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test 
	public void verifyVideoGames()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------verifyVideoGames() in group2-------------");
			welcomeScreen.clickOnBrowseTab(methodName);
			welcomeScreen.VideoGames(methodName);
			welcomeScreen.Cameras(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void verifyTelevision()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------verifyTelevision() in group2-------------");
			welcomeScreen.clickOnBrowseTab(methodName);
			welcomeScreen.Television(methodName);
			welcomeScreen.Cameras(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	



	@AfterTest(alwaysRun=true)
	public void tearDown() {
		welcomeScreen.closeBrowser();
	}
}
