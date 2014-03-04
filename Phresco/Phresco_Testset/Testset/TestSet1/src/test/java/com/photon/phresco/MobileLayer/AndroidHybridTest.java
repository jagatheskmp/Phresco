package com.photon.phresco.MobileLayer;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.MobLayer.MobBaseScreen;
import com.photon.phresco.uiconstants.AndroidHybridConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class AndroidHybridTest {
	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrsConst;
	private phresco_env_config phrscEnv;
	private MobBaseScreen mobBaseScreen;
	private AndroidHybridConstantsXml androidHybrid;
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
		androidHybrid = new AndroidHybridConstantsXml();
		try {
			String applicationURL = phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT + "/";
			mobBaseScreen = new MobBaseScreen(selectedBrowser,selectedPlatform,applicationURL, phrscEnv.CONTEXT,phrscEnv,phrsConst,userInfo,phrscData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAndroidHybridHelloWorldProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testandroidHybridHelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.loginPage(methodName);
			mobBaseScreen.androidHybridHelloWorldCreate(methodName, androidHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testandroidHybridHelloWorldProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testandroidHybridHelloWorldProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.androidHybridHelloWorldProjectEditApplication(methodName, androidHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testandroidHybridHelloWorldProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testandroidHybridHelloWorldProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.androidHybridHelloWorldProjectAddFeature(methodName, androidHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAndroidHybridHelloWorldProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAndroidHybridHelloWorldProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.androidHybridProjectBuild(methodName, androidHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAndroidHybridHelloWorldProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAndroidHybridHelloWorldProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.androidHybridProjectDeploy(methodName, androidHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAndroidHybridEshopProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAndroidHybridEshopProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.androidHybridEshopCreate(methodName, androidHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testandroidHybridEshopProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testandroidHybridEshopProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.androidHybridEshopProjectEditApplication(methodName, androidHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testandroidHybridEshopProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testandroidHybridEshopProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.androidHybridEshopProjectAddFeature(methodName, androidHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testandroidHybridEshopProjectAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testandroidHybridEshopProjectAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.androidHybridProjectAddConfigurationServer(methodName, androidHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAndroidHybridEshopProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAndroidHybridEshopProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.androidHybridProjectBuild(methodName, androidHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAndroidHybridEshopProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAndroidHybridEshopProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.androidHybridProjectDeploy(methodName, androidHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@AfterTest
	public void tearDown() {
		mobBaseScreen.closeBrowser();
	}
}





