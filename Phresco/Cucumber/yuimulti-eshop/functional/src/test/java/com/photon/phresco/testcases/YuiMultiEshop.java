package com.photon.phresco.testcases;

import java.io.IOException;


import com.photon.phresco.Screens.WelcomeScreen;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.WidgetData;

import cucumber.annotation.*;
import cucumber.annotation.en.*;

public class YuiMultiEshop {


	private static UIConstants uiConstants;
	private static PhrescoUiConstants phrescoUIConstants;
	private static WelcomeScreen welcomeScreen;
	private static String methodName;
	private static String selectedBrowser;
	private static WidgetData WidgetConstants;

	// private Log log = LogFactory.getLog(getClass());

	/*@Before
	public static void setUp() throws Exception {
		try {
			phrescoUIConstants = new PhrescoUiConstants();
			uiConstants = new UIConstants();
			// assertNotNull(uiConstants);
			WidgetConstants = new WidgetData();
			launchingBrowser();
			// menuScreen = welcomeScreen.menuScreen(uiConstants);
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
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
			welcomeScreen = new WelcomeScreen(selectedBrowser, applicationURL,
					phrescoUIConstants.CONTEXT, WidgetConstants, uiConstants);
		} catch (Exception exception) {
			exception.printStackTrace();

		}

	}

	@Test
	public void testWelcomePageScreen() throws InterruptedException,
			IOException, Exception {
		try {
			//Assert.assertNotNull(welcomeScreen);
			// Thread.sleep(10000);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}*/
	@Given("^the user is on the Welcome page$")
	public void theUserisontheWelcomepage() throws InterruptedException,
			IOException, Exception {
		try {
			phrescoUIConstants = new PhrescoUiConstants();
			uiConstants = new UIConstants();
			// assertNotNull(uiConstants);
			WidgetConstants = new WidgetData();
			String applicationURL = phrescoUIConstants.PROTOCOL + "://"
					+ phrescoUIConstants.HOST + ":" + phrescoUIConstants.PORT
					+ "/";
			selectedBrowser = phrescoUIConstants.BROWSER;
			welcomeScreen = new WelcomeScreen(selectedBrowser, applicationURL,
					phrescoUIConstants.CONTEXT, WidgetConstants, uiConstants);
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			//Assert.assertNotNull(welcomeScreen);
			// Thread.sleep(10000);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Then("he clicks AudioDevices in the page")
	public void heClicksAudioDevicesinthepage()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyTheAudioDevicesAddToCart()-------------");
			welcomeScreen.AudioDevices(methodName);
			welcomeScreen.billingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@And("he clicks Cameras in the page")
	public void heClicksCamerasinthepage() throws InterruptedException,
			IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyTheCamerasAddToCart()-------------");
			welcomeScreen.Cameras(methodName);
			welcomeScreen.billingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@And("he clicks VideoGames in the page")
	public void heClicksVideoGamesinthepage()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyTheVideoGamesAddToCart()-------------");
			welcomeScreen.VideoGames(methodName);
			welcomeScreen.billingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@And("he clicks Television in the page")
	public void heClicksTelevisioninthepage()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyTheTelevisionAddToCart()-------------");
			welcomeScreen.Television(methodName);
			welcomeScreen.billingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@And("he clicks Tablets in the page")
	public void heClicksTabletsinthepage() throws InterruptedException,
			IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyTheTabletsAddToCart()-------------");
			welcomeScreen.Tablets(methodName);
			welcomeScreen.billingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@And("he clicks MP3Players in the page")
	public void heClicksMP3Playersinthepage()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyTheMP3PlayersAddToCart()-------------");
			welcomeScreen.MP3Players(methodName);
			welcomeScreen.billingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@And("he clicks MoviesAndMusic in the page")
	public void heClicksMoviesAndMusicinthepage()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyTheMoviesAndMusicAddToCart()-------------");
			welcomeScreen.MoviesnMusic(methodName);
			welcomeScreen.billingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@And("he clicks MobilePhones in the page")
	public void heClicksMobilePhonesinthepage()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testToVerifyTheMobilePhonesAddToCart()-------------");
			welcomeScreen.MobilePhones(methodName);
			welcomeScreen.billingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@And("he clicks Accessories in the page")
	public void heClicksAccessoriesinthepage()
			throws InterruptedException, IOException, Exception {
		try {
			System.out
					.println("---------testToVerifyTheAccessoriesAddToCart()-------------");
			welcomeScreen.Accessories(methodName);
			welcomeScreen.billingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@And("he clicks Computers in the page")
	public void heClicksComputersinthepage()
			throws InterruptedException, IOException, Exception {
		try {
			System.out
					.println("---------testToVerifyTheComputersAddToCart()-------------");
			welcomeScreen.Computers(methodName);
			welcomeScreen.billingInfo(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@After
	public static void tearDown() {
		welcomeScreen.closeBrowser();
	}

	
	
}