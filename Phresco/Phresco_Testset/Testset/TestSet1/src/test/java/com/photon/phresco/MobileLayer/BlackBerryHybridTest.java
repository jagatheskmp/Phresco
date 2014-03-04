package com.photon.phresco.MobileLayer;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.MobLayer.MobBaseScreen;
import com.photon.phresco.uiconstants.BlackBerryHybridConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class BlackBerryHybridTest {
	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrsConst;
	private phresco_env_config phrscEnv;
	private MobBaseScreen mobBaseScreen;
	private BlackBerryHybridConstantsXml blackBerryHybrid;
	private String methodName;


	@Parameters(value = {"browser","platform"})
	@BeforeTest
	public  void setUp(String browser, String platform) throws Exception
	{
		String selectedBrowser = browser;
		String selectedPlatform = platform;
		phrscEnv = new phresco_env_config();
		phrsConst = new PhrescoUiConstantsXml();
		userInfo = new UserInfoConstants();
		phrscData = new PhrescoFrameworkData();
		blackBerryHybrid = new BlackBerryHybridConstantsXml();

		try {
			String applicationURL = phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT + "/";
			mobBaseScreen = new MobBaseScreen(selectedBrowser,selectedPlatform,applicationURL, phrscEnv.CONTEXT,phrscEnv,phrsConst,userInfo,phrscData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBlackBerryHybridProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testBlackBerryHybridProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.loginPage(methodName);
			mobBaseScreen.blackBerryHybridCreate(methodName, blackBerryHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testBlackBerryHybridProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testBlackBerryHybridProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.blackBerryHybridProjectEditApplication(methodName, blackBerryHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testBlackBerryHybridProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testBlackBerryHybridProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.blackBerryHybridProjectAddFeature(methodName, blackBerryHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testBlackBerryHybridProjectAddServerConfiguration()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testBlackBerryHybridProjectAddCofigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.blackBerryHybridAddConfigurationServer(methodName, blackBerryHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testBlackBerryHybridProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testBlackBerryHybridProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.blackBerryProjectBuild(methodName, blackBerryHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testBlackBerryHybridProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testBlackBerryHybridProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.blackBerryProjectDeploy(methodName, blackBerryHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@AfterTest
	public void tearDown() {
		mobBaseScreen.closeBrowser();
	}
}


