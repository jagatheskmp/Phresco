package com.photon.phresco.WebLayer;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.Weblayer.WebBaseScreen;
import com.photon.phresco.uiconstants.HTML5YUIMobileWidgetXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class HTML5YUIMobileWidgetTest {
	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrsConst;
	private phresco_env_config phrscEnv;
	private WebBaseScreen webBaseScreen;
	private HTML5YUIMobileWidgetXml yuiMobile;
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
		yuiMobile = new HTML5YUIMobileWidgetXml();

		try {
			String applicationURL = phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT + "/";
			webBaseScreen = new WebBaseScreen(selectedBrowser,selectedPlatform,applicationURL,phrscEnv.CONTEXT,phrscEnv,phrsConst,userInfo,phrscData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testYuiMobileWidgetHelloWorldProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testYuiMobileWidgetHelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.loginPage(methodName);
			webBaseScreen.yuiMobileWidgetProjectHelloWorldCreate(methodName, yuiMobile);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testYuiMobileWidgetHelloWorldProjectEditApplication()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testYuiMobileWidgetHelloWorldProjectEditApplication()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.yuiMobileWidgetProjectHelloWorldEditApplication(methodName, yuiMobile);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testYuiMobileWidgetHelloWorldProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testYuiMobileWidgetHelloWorldProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.yuiMobileWidgetProjectHelloWorldAddFeature(methodName, yuiMobile);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testYuiMobileWidgetHelloWorldProjectAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testYuiMobileWidgetHelloWorldProjectAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.yuiMobileWidgetProjectAddConfigurationServer(methodName, yuiMobile);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testYuiMobileWidgetHelloWorldProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testYuiMobileWidgetHelloWorldProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.webLayerBuildTab(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testYuiMobileWidgetHelloWorldProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testYuiMobileWidgetHelloWorldProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.webLayerDeploy(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testYuiMobileWidgetHelloWorldProjectUnitTestJs()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testYuiMobileWidgetHelloWorldProjectUnitTestJs()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.UnittestVerificationJs(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testYuiMobileWidgetHelloWorldProjectUnitTestJava()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testYuiMobileWidgetHelloWorldProjectUnitTestJava()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.UnittestVerificationJava(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testYuiMobileWidgetHelloWorldProjectPerformanceTestForAganistServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testYuiMobileWidgetHelloWorldProjectPerformanceTestForAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.PerformanceTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testYuiMobileWidgetHelloWorldProjectLoadTestAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testYuiMobileWidgetHelloWorldProjectLoadTestAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.LoadTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testYuiMobileWidgetEshopProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testYuiMobileWidgetEshopProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.yuiMobileWidgetProjectEshopCreate(methodName, yuiMobile);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testYuiMobileWidgetEshopProjectEditApplication()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testYuiMobileWidgetEshopProjectEditApplication()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.yuiMobileWidgetProjectEshopEditApplication(methodName, yuiMobile);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testYuiMobileWidgetEshopProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testYuiMobileWidgetEshopProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.yuiMobileWidgetProjectEshopAddFeature(methodName, yuiMobile);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testYuiMobileWidgetEshopProjectAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testYuiMobileWidgetEshopProjectAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.yuiMobileWidgetProjectAddConfigurationServer(methodName, yuiMobile);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testYuiMobileWidgetEshopProjectAddConfigurationWebservice()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testYuiMobileWidgetEshopProjectAddConfigurationWebservice()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.yuiMobileWidgetProjectAddConfigurationWebService(methodName, yuiMobile);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testYuiMobileWidgetEshopProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testYuiMobileWidgetEshopProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.webLayerBuildTab(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testYuiMobileWidgetEshopProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testYuiMobileWidgetEshopProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.webLayerDeploy(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testYuiMobileWidgetEshopProjectUnitTestJs()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testYuiMobileWidgetEshopProjectUnitTestJs()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.UnittestVerificationJs(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testYuiMobileWidgetEshopProjectUnitTestJava()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testYuiMobileWidgetEshopProjectUnitTestJava()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.UnittestVerificationJava(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testYuiMobileWidgetEshopProjectPerformanceTestForAganistServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testYuiMobileWidgetEshopProjectPerformanceTestForAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.PerformanceTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testYuiMobileWidgetEshopProjectPerformanceTestForAganistWebservice()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testYuiMobileWidgetEshopProjectPerformanceTestForAganistWebservice()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.PerformanceTestForAganistWebService(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testYuiMobileWidgetEshopProjectLoadTestAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testYuiMobileWidgetEshopProjectLoadTestAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.LoadTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testYuiMobileWidgetEshopProjectLoadTestAganistWebservices() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testYuiMobileWidgetEshopProjectLoadTestAganistWebservices()-------------");
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

