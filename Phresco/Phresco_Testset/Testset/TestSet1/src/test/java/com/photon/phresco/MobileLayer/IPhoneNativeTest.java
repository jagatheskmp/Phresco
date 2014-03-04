package com.photon.phresco.MobileLayer;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.MobLayer.MobBaseScreen;
import com.photon.phresco.uiconstants.IPhoneNativeConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class IPhoneNativeTest {
	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrsConst;
	private phresco_env_config phrscEnv;
	private MobBaseScreen mobBaseScreen;
	private IPhoneNativeConstantsXml iPhoneNative;
	private String methodName;

	@Parameters(value = {"browser","platform"})
	@BeforeTest
	public void setUp(String browser, String platform) throws Exception
	{
		String selectedBrowser = browser;
		String selectedPlatform = platform;
		phrscEnv = new phresco_env_config();
		phrsConst = new PhrescoUiConstantsXml();
		userInfo = new UserInfoConstants();
		phrscData = new PhrescoFrameworkData();
		iPhoneNative = new IPhoneNativeConstantsXml();
		try {
			String applicationURL = phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT + "/";
			mobBaseScreen = new MobBaseScreen(selectedBrowser,selectedPlatform,applicationURL, phrscEnv.CONTEXT,phrscEnv,phrsConst,userInfo,phrscData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testiPhoneNativeHelloWorldProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneNativeHelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.loginPage(methodName);
			mobBaseScreen.iPhoneNativeHelloWorldCreate(methodName, iPhoneNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testiPhoneNativeHelloWorldProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneNativeHelloWorldProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneNativeHelloWorldProjectEditApplication(methodName, iPhoneNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testiPhoneNativeHelloWorldProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneNativeHelloWorldProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneNativeHelloWorldProjectAddFeature(methodName, iPhoneNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testiPhoneNativeHelloWorldProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneNativeHelloWorldProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneNativeProjectBuild(methodName, iPhoneNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testiPhoneNativeHelloWorldProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneNativeHelloWorldProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneNativeProjectDeploy(methodName, iPhoneNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testiPhoneNativeHelloWorldProjectUnitTest()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneNativeHelloWorldProjectUnitTest()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iphoneNativeUnitTestLogic(methodName, iPhoneNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testiPhoneNativeEshopProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneNativeEshopProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneNativeEshopCreate(methodName, iPhoneNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testiPhoneNativeEshopProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneNativeEshopProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneNativeEshopProjectEditApplication(methodName, iPhoneNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testiPhoneNativeEshopProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneNativeEshopProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneNativeEshopProjectAddFeature(methodName, iPhoneNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testiPhoneNativeEshopProjectAddConfigurationWebService()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneNativeEshopProjectAddConfigurationWebService()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneNativeEshopProjectAddConfigurationWebService(methodName, iPhoneNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testiPhoneNativeEshopProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneNativeHelloWorldProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneNativeProjectBuild(methodName, iPhoneNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testiPhoneNativeEshopProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneNativeEshopProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneNativeProjectDeploy(methodName, iPhoneNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testiPhoneNativeEshopProjectUnitTestLogic()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneNativeEshopProjectUnitTestLogic()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iphoneNativeUnitTestLogic(methodName, iPhoneNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testiPhoneNativeEshopProjectUnitTestApplication()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneNativeEshopProjectUnitTestApplication()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iphoneNativeUnitTestApplication(methodName, iPhoneNative);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@AfterTest
	public void tearDown() {
		mobBaseScreen.closeBrowser();
	}
}


