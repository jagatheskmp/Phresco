package com.photon.phresco.testcases;

import java.io.IOException;

//import org.junit.Assert;

//import org.junit.Test;

import com.photon.phresco.Screens.WelcomeScreen;
import com.photon.phresco.uiconstants.MobileWidgetData;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;

import cucumber.annotation.*;
import cucumber.annotation.en.*;
//import static org.junit.Assert.assertEquals;

public class JqueryMobileTestCase {

	private static UIConstants uiConstants;
	private static PhrescoUiConstants phrescoUIConstants;
	private static WelcomeScreen welcomeScreen;
	private static String methodName;
	private static String selectedBrowser;
	private static MobileWidgetData mobileWidgetConstants;

	// private Log log = LogFactory.getLog(getClass());

	/*@Before
	public static void setUp() throws Exception {
		try {
			phrescoUIConstants = new PhrescoUiConstants();
			uiConstants = new UIConstants();
			// assertNotNull(uiConstants);
			mobileWidgetConstants = new MobileWidgetData();
			String applicationURL = phrescoUIConstants.PROTOCOL + "://"
					+ phrescoUIConstants.HOST + ":" + phrescoUIConstants.PORT
					+ "/";
			selectedBrowser = phrescoUIConstants.BROWSER;
			welcomeScreen = new WelcomeScreen(selectedBrowser, applicationURL,
					phrescoUIConstants.CONTEXT, mobileWidgetConstants,
					uiConstants);
		} catch (Exception exception) {
			exception.printStackTrace();

		}

	}*/

	@Given("^the user is on the Welcome page$")
	public void theUserisontheWelcomepage() throws InterruptedException,
			IOException, Exception {
		try {
			phrescoUIConstants = new PhrescoUiConstants();
			uiConstants = new UIConstants();
			// assertNotNull(uiConstants);
			mobileWidgetConstants = new MobileWidgetData();
			String applicationURL = phrescoUIConstants.PROTOCOL + "://"
					+ phrescoUIConstants.HOST + ":" + phrescoUIConstants.PORT
					+ "/";
			selectedBrowser = phrescoUIConstants.BROWSER;
			welcomeScreen = new WelcomeScreen(selectedBrowser, applicationURL,
					phrescoUIConstants.CONTEXT, mobileWidgetConstants,
					uiConstants);
			
			//Assert.assertNotNull(welcomeScreen);
			// Thread.sleep(10000);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Then("he clicks Browse button for adding AudioDevices in the page")
	public void heClicksBrowsebuttonforaddingAudioDevicesinthepage()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyTheAudioDevicesAddToCart()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			welcomeScreen.clickOnBrowse(methodName);			
			welcomeScreen.AudioDevices(methodName);			
			welcomeScreen.BillingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@And("he clicks Browse button for adding Cameras in the page")
	public void heClicksBrowsebuttonforaddingCamerasinthepage() throws InterruptedException,
			IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyTheCamerasAddToCart()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			welcomeScreen.clickOnBrowse(methodName);
			welcomeScreen.Cameras(methodName);
			welcomeScreen.BillingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@And("he clicks Browse button for adding VideoGames in the page")
	public void heClicksBrowsebuttonforaddingVideoGamesinthepage()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyTheVideoGamesAddToCart()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			welcomeScreen.clickOnBrowse(methodName);
			welcomeScreen.VideoGames(methodName);
			welcomeScreen.BillingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@And("he clicks Browse button for adding Television in the page")
	public void heClicksBrowsebuttonforaddingTelevisioninthepage()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyTheTelevisionAddToCart()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			welcomeScreen.clickOnBrowse(methodName);
			welcomeScreen.Television(methodName);
			welcomeScreen.BillingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@And("he clicks Browse button for adding Tablets in the page")
	public void heClicksBrowsebuttonforaddingTabletsinthepage() throws InterruptedException,
			IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyTheTabletsAddToCart()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			welcomeScreen.clickOnBrowse(methodName);
			welcomeScreen.Tablets(methodName);
			welcomeScreen.BillingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@And("he clicks Browse button for adding MP3Players in the page")
	public void heClicksBrowsebuttonforaddingMP3Playersinthepage()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyTheMP3PlayersAddToCart()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			welcomeScreen.clickOnBrowse(methodName);
			welcomeScreen.MP3Players(methodName);
			welcomeScreen.BillingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@And("he clicks Browse button for adding MoviesAndMusic in the page")
	public void heClicksBrowsebuttonforaddingMoviesAndMusicinthepage()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyTheMoviesAndMusicAddToCart()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			welcomeScreen.clickOnBrowse(methodName);
			welcomeScreen.MoviesnMusic(methodName);
			welcomeScreen.BillingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@And("he clicks Browse button for adding MobilePhones in the page")
	public void heClicksBrowsebuttonforaddingMobilePhonesinthepage()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyTheMobilePhonesAddToCart()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			welcomeScreen.clickOnBrowse(methodName);
			welcomeScreen.MobilePhones(methodName);
			welcomeScreen.BillingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@And("he clicks Browse button for adding Accessories in the page")
	public void heClicksBrowsebuttonforaddingAccessoriesinthepage()
			throws InterruptedException, IOException, Exception {
		try {
			System.out
					.println("---------testToVerifyTheAccessoriesAddToCart()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			welcomeScreen.clickOnBrowse(methodName);
			welcomeScreen.Accessories(methodName);
			welcomeScreen.BillingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@And("he clicks Browse button for adding Computers in the page")
	public void heClicksBrowsebuttonforaddingComputersinthepage()
			throws InterruptedException, IOException, Exception {
		try {
			System.out
					.println("---------testToVerifyTheComputersAddToCart()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			welcomeScreen.clickOnBrowse(methodName);
			welcomeScreen.Computers(methodName);
			welcomeScreen.BillingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@After
	public static void tearDown() {
		welcomeScreen.closeBrowser();
	}

}
