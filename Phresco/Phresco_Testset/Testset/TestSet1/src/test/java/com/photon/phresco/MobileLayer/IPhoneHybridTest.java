package com.photon.phresco.MobileLayer;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.MobLayer.MobBaseScreen;
import com.photon.phresco.uiconstants.IPhoneHybridConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class IPhoneHybridTest {

	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrsConst;
	private phresco_env_config phrscEnv;
	private MobBaseScreen mobBaseScreen;
	private IPhoneHybridConstantsXml iPhoneHybrid;
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
		iPhoneHybrid = new IPhoneHybridConstantsXml();

		try {
			String applicationURL = phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT	+ "/";
			mobBaseScreen = new MobBaseScreen(selectedBrowser,selectedPlatform,applicationURL,phrscEnv.CONTEXT,phrscEnv,phrsConst,userInfo,phrscData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testiPhoneHybridHelloWorldProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneHybridHelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.loginPage(methodName);
			mobBaseScreen.iPhoneHybridHelloWorldCreate(methodName, iPhoneHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testiPhoneHybridHelloWorldProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneHybridHelloWorldProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneHybridHelloWorldProjectEditApplication(methodName, iPhoneHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testiPhoneHybridHelloWorldProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneHybridHelloWorldProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneHybridHelloWorldProjectAddFeature(methodName, iPhoneHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testiPhoneHybridHelloWorldProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneHybridHelloWorldProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneHybridProjectBuild(methodName, iPhoneHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testiPhoneHybridHelloWorldProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneHybridHelloWorldProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneHybridProjectDeploy(methodName, iPhoneHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testiPhoneHybridEshopProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneHybridEshopProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneHybridEshopCreate(methodName, iPhoneHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testiPhoneHybridEshopProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneHybridEshopProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneHybridEshopProjectEditApplication(methodName, iPhoneHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testiPhoneHybridEshopProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneHybridEshopProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneHybridEshopProjectAddFeature(methodName, iPhoneHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testiPhoneHybridEshopProjectAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneHybridEshopProjectAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneHybridEshopProjectAddConfigurationServer(methodName, iPhoneHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testiPhoneHybridEshopProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneHybridEshopProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneHybridProjectBuild(methodName, iPhoneHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testiPhoneHybridEshopProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneHybridEshopProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneHybridProjectDeploy(methodName, iPhoneHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testiPhoneHybridEshopUnitTest()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneHybridEshopUnitTest()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneHybridUnitTest(methodName, iPhoneHybrid);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@AfterTest
	public void tearDown() {
		mobBaseScreen.closeBrowser();
	}
}





