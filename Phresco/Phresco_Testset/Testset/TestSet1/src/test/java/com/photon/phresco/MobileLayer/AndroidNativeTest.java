package com.photon.phresco.MobileLayer;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.MobLayer.MobBaseScreen;
import com.photon.phresco.uiconstants.AndroidNativeConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class AndroidNativeTest {

	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrsConst;
	private phresco_env_config phrscEnv;
	private MobBaseScreen mobBaseScreen;
	private AndroidNativeConstantsXml androidNative;
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
		androidNative = new AndroidNativeConstantsXml();

		try {
			String applicationURL = phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT + "/";
			mobBaseScreen = new MobBaseScreen(selectedBrowser,selectedPlatform,applicationURL, phrscEnv.CONTEXT,phrscEnv,phrsConst,userInfo,phrscData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAndroidNativeHelloWorldProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAndroidNativeHelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.loginPage(methodName);
			mobBaseScreen.androidNativeHelloWorldCreate(methodName, androidNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAndroidNativeHelloWorldProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAndroidNativeHelloWorldProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.androidNativeHelloWorldProjectEditApplication(methodName, androidNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAndroidNativeHelloWorldProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAndroidNativeHelloWorldProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.androidNativeHelloWorldProjectAddFeature(methodName, androidNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAndroidNativeHelloWorldProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAndroidNativeHelloWorldProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.androidNativeProjectBuild(methodName, androidNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAndroidNativeHelloWorldProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAndroidNativeHelloWorldProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.androidNativeProjectDeploy(methodName, androidNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAndroidNativeEshopProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAndroidNativeEshopProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.androidNativeEshopCreate(methodName, androidNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAndroidNativeEshopProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAndroidNativeEshopProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.androidNativeEshopProjectEditApplication(methodName, androidNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAndroidNativeEshopProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAndroidNativeEshopProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.androidNativeEshopProjectAddFeature(methodName, androidNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAndroidNativeEshopProjectAddConfigurationWebService()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAndroidNativeEshopProjectAddConfigurationWebService()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.androidNativeEshopProjectAddConfigurationWebService(methodName, androidNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAndroidNativeEshopProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAndroidNativeEshopProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.androidNativeProjectBuild(methodName, androidNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAndroidNativeEshopProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAndroidNativeEshopProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.androidNativeProjectDeploy(methodName, androidNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	/*@Test
	public void testAndroidNativeEshopProjectUnitTest()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testAndroidNativeEshopProjectUnitTest()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			mobBaseScreen.UnittestVerification(methodName);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}*/

	@AfterTest
	public void tearDown() {
		mobBaseScreen.closeBrowser();
	}
}


