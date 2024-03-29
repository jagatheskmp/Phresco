
package com.photon.phresco.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.WelcomeScreen;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.UserInfoConstants;


@Listeners({ com.photon.phresco.Listener.MyListener.class })
public class ReportWithNodeIP implements ITest{

	private PhrescoUiConstants phrescoUiConstants;
	private WelcomeScreen welcomeScreen;
	private String methodName;
	private UIConstants uiConstants;
	private UserInfoConstants userInfoConstants;
	private String nodeIP ;
	private String selectedBrowser;
	private String selectedPlatform;
	
	String ipNode;
	String browserVersion;

	@Parameters(value = { "browser", "platform" })
	@BeforeClass(alwaysRun=true)
	public void setUp(String browser, String platform) throws Exception {
		try {
			phrescoUiConstants = new PhrescoUiConstants();
			userInfoConstants = new UserInfoConstants();
			uiConstants = new UIConstants();
			 selectedBrowser = browser;
			 selectedPlatform = platform;

			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			String applicationURL = phrescoUiConstants.getProtocol() + "://"
			+ phrescoUiConstants.getHost() + ":"
			+ phrescoUiConstants.getPort() + "/";
			welcomeScreen = new WelcomeScreen(selectedBrowser,
					selectedPlatform, applicationURL,
					phrescoUiConstants.getContext(), userInfoConstants,
					uiConstants,  phrescoUiConstants);
			nodeIP = welcomeScreen.getNodeIpaddress().get("nodeIP");
			browserVersion = welcomeScreen.getBrowserVersionMap().get("browserVersion");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	@Test
	public void AwithoutGroup() throws InterruptedException,
	IOException, Exception {
		methodName = Thread.currentThread().getStackTrace()[1]
                .getMethodName();
		
		try {
			System.out.println("*************withoutGroup***********************" );
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
			.println("--------- verifyAudioDevices() -------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			Thread.sleep(5000);
			welcomeScreen.clickOnBrowse(nodeIP+"-"+methodName+"-"+selectedBrowser);
			welcomeScreen.AudioDevices(nodeIP+"-"+methodName+"-"+selectedBrowser);
		} catch (Exception t) {
			t.printStackTrace();

		}

	}

	@Test
	public void verifyCameras()
	throws InterruptedException, IOException, Exception {
		try {
			methodName = Thread.currentThread().getStackTrace()[1]
                    .getMethodName();

			System.out
			.println("---------verifyCameras()-------------");
			welcomeScreen.clickOnBrowseTab(nodeIP+"-"+methodName+"-"+selectedBrowser);
			welcomeScreen.Cameras(nodeIP+"-"+methodName+"-"+selectedBrowser);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test 
	public void verifyVideoGames()
	throws InterruptedException, IOException, Exception {
		try {
			methodName = Thread.currentThread().getStackTrace()[1]
                    .getMethodName();
			System.out
			.println("---------verifyVideoGames()-------------");
			welcomeScreen.clickOnBrowseTab(nodeIP+"-"+methodName+"-"+selectedBrowser);
			welcomeScreen.VideoGames(nodeIP+"-"+methodName+"-"+selectedBrowser);
			welcomeScreen.Cameras(nodeIP+"-"+methodName+"-"+selectedBrowser);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test 
	public void verifyTelevision()
	throws InterruptedException, IOException, Exception {
		try {
			methodName = Thread.currentThread().getStackTrace()[1]
                    .getMethodName();
			System.out
			.println("---------verifyTelevision()-------------");
			welcomeScreen.clickOnBrowseTab(nodeIP+"-"+methodName+"-"+selectedBrowser);
			welcomeScreen.Television(nodeIP+"-"+methodName+"-"+selectedBrowser);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}


	
	
	public String getTestName() {
		return "NodeIPAddress:" + nodeIP +"-"+ "Browser&Version&Platform:" + browserVersion ;
	}

	@AfterTest(alwaysRun=true)
	public void tearDown() {
		welcomeScreen.closeBrowser();
	}
}

