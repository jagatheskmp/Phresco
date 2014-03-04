
package com.photon.phresco.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.VersionScreen;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.UserInfoConstants;

public class VersionEshop {

	private PhrescoUiConstants phrescoUiConstants;
	private VersionScreen versionScreen;
	private String methodName;
	private UIConstants uiConstants;
	private UserInfoConstants userInfoConstants;

	@Parameters(value = { "browser", "platform" ,"version" })
	@BeforeTest(alwaysRun=true)
	public void setUp(String browser, String platform, @Optional("")String version) throws Exception {
		try {
			phrescoUiConstants = new PhrescoUiConstants();
			userInfoConstants = new UserInfoConstants();
			uiConstants = new UIConstants();
			String selectedBrowser = browser;
			String selectedPlatform = platform;
			String selectedVersion = version;

			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			String applicationURL = phrescoUiConstants.getProtocol() + "://"
			+ phrescoUiConstants.getHost() + ":"
			+ phrescoUiConstants.getPort() + "/";
			versionScreen = new VersionScreen(selectedBrowser,
					selectedPlatform, applicationURL,
					phrescoUiConstants.getContext(), userInfoConstants,
					uiConstants,  phrescoUiConstants,selectedVersion);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Test
	public void withoutGroup() throws InterruptedException,
	IOException, Exception {
		try {
			System.out.println("*************withoutGroup***********************");
			Assert.assertNotNull(versionScreen);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test(groups = { "group1"})
	public void verifyAudioDevices()
	throws InterruptedException, IOException, 
	Exception {
		try {

			System.out
			.println("--------- verifyAudioDevices() in group1-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			versionScreen.clickOnBrowse(methodName);
			versionScreen.AudioDevices(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}

	}

	@Test(groups = { "group1"})
	public void verifyCameras()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------verifyCameras() in group1-------------");
			versionScreen.clickOnBrowseTab(methodName);
			versionScreen.Cameras(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test (groups = { "group2"})
	public void verifyVideoGames()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------verifyVideoGames() in group2-------------");
			versionScreen.clickOnBrowseTab(methodName);
			versionScreen.VideoGames(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test (groups = { "group2"})
	public void verifyTelevision()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------verifyTelevision() in group2-------------");
			versionScreen.clickOnBrowseTab(methodName);
			versionScreen.Television(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}


	@Test (groups = { "group2","group1"})
	public void verifyTelevisionandCamera()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------verifyTelevisionandCamera() in group1 and group2-------------");
			versionScreen.clickOnBrowseTab(methodName);
			versionScreen.Television(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}


	@AfterTest(alwaysRun=true)
	public void tearDown() {
		versionScreen.closeBrowser();
	}
}

