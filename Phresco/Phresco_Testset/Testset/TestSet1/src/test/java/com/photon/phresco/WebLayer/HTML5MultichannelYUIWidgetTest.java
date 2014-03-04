package com.photon.phresco.WebLayer;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.Weblayer.WebBaseScreen;
import com.photon.phresco.uiconstants.HTML5MultichannelYUIWidgetConstantsxml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class HTML5MultichannelYUIWidgetTest {

	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrsConst;
	private phresco_env_config phrscEnv;
	private WebBaseScreen webBaseScreen;
	private HTML5MultichannelYUIWidgetConstantsxml multiYui;
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
		multiYui = new HTML5MultichannelYUIWidgetConstantsxml();

		try {
			String applicationURL = phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT	+ "/";
			webBaseScreen = new WebBaseScreen(selectedBrowser,selectedPlatform,applicationURL,phrscEnv.CONTEXT,phrscEnv,phrsConst,userInfo,phrscData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetHelloWorldProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetHelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.loginPage(methodName);
			webBaseScreen.multiYuiWidgetProjectHelloWorldCreate(methodName, multiYui);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetHelloWorldProjectEditApplication()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetHelloWorldProjectEditApplication()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.multiYuiWidgetProjectHelloWorldEditApplication(methodName, multiYui);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetHelloWorldProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetHelloWorldProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.multiYuiWidgetProjectHelloWorldAddFeature(methodName, multiYui);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetHelloWorldProjectAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetHelloWorldProjectAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.multiYuiWidgetProjectAddConfigurationServer(methodName, multiYui);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetHelloWorldProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetHelloWorldProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.webLayerBuildTab(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetHelloWorldProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetHelloWorldProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.webLayerDeploy(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetHelloWorldProjectUnitTestJs()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetHelloWorldProjectUnitTestJs()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.UnittestVerificationJs(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testMultiYuiWidgetHelloWorldProjectUnitTestJava()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetHelloWorldProjectUnitTestJava()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.UnittestVerificationJava(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetHelloWorldProjectPerformanceTestForAganistServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetHelloWorldProjectPerformanceTestForAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.PerformanceTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetHelloWorldProjectLoadTestAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetHelloWorldProjectLoadTestAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.LoadTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetEshopProjectCreate()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetEshopProjectsCreate()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.multiYuiWidgetProjectEshopCreate(methodName, multiYui);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetEshopProjectEditApplication()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetEshopProjectEditApplication()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.multiYuiWidgetProjectEshopEditApplication(methodName, multiYui);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetEshopProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetEshopProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.multiYuiWidgetProjectEshopAddFeature(methodName, multiYui);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetEshopProjectAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetEshopProjectAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.multiYuiWidgetProjectAddConfigurationServer(methodName, multiYui);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetEshopProjectAddConfigurationWebservice()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetEshopProjectAddConfigurationWebservice()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.multiYuiWidgetProjectAddConfigurationWebService(methodName, multiYui);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetEshopProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetEshopProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.webLayerBuildTab(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetEshopProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetEshopProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.webLayerDeploy(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetEshopProjectUnitTestJs()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetEshopProjectUnitTestJs()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.UnittestVerificationJs(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetEshopProjectUnitTestJava()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetEshopProjectUnitTestJava()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.UnittestVerificationJava(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testMultiYuiWidgetEshopProjectPerformanceTestForAganistServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetEshopProjectPerformanceTestForAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.PerformanceTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetEshopProjectPerformanceTestForAganistWebservice()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetEshopProjectPerformanceTestForAganistWebservice()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.PerformanceTestForAganistWebService(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetEshopProjectLoadTestAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetEshopProjectLoadTestAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.LoadTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetEshopProjectLoadTestAganistWebservices() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetEshopProjectLoadTestAganistWebservices()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.LoadTestForAganistWebService(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@AfterTest
	public void tearDown() {
		webBaseScreen.closeBrowser();
	}
}

