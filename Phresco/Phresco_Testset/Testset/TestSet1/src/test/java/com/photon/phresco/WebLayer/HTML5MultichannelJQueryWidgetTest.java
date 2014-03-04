package com.photon.phresco.WebLayer;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.Weblayer.WebBaseScreen;
import com.photon.phresco.uiconstants.HTML5MultichannelJQueryWidgetConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class HTML5MultichannelJQueryWidgetTest {

	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrsConst;
	private phresco_env_config phrscEnv;
	private WebBaseScreen webBaseScreen;
	private HTML5MultichannelJQueryWidgetConstantsXml multiJQuery;
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
		multiJQuery = new HTML5MultichannelJQueryWidgetConstantsXml();
		try {
			String applicationURL = phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT + "/";
			webBaseScreen = new WebBaseScreen(selectedBrowser,selectedPlatform,applicationURL,phrscEnv.CONTEXT,phrscEnv,phrsConst,userInfo,phrscData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMultiJQueryWidgetHelloWorldProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiJQueryWidgetHelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.loginPage(methodName);
			webBaseScreen.multiJQueryWidgetProjectHelloWorldCreate(methodName, multiJQuery);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiJQueryWidgetHelloWorldProjectEditApplication()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiJQueryWidgetHelloWorldProjectEditApplication()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.multiJQueryWidgetProjectHelloWorldEditApplication(methodName,multiJQuery);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiJQueryWidgetHelloWorldProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiJQueryWidgetHelloWorldProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.multiJQueryWidgetProjectHelloWorldAddFeature(methodName, multiJQuery);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiJQueryWidgetHelloWorldProjectAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiJQueryWidgetProjectHelloWorldAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.multiJQueryWidgetProjectAddConfigurationServer(methodName, multiJQuery);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiJQueryWidgetHelloWorldProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiJQueryWidgetHelloWorldProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.webLayerBuildTab(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiJQueryWidgetHelloWorldProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiJQueryWidgetHelloWorldProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.webLayerDeploy(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiJQueryWidgetHelloWorldProjectUnitTestJs()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiJQueryWidgetHelloWorldProjectUnitTestJs()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.UnittestVerificationJs(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiJQueryWidgetHelloWorldProjectUnitTestJava()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiJQueryWidgetHelloWorldProjectUnitTestJava()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.UnittestVerificationJava(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	@Test
	public void testMultiJQueryWidgetHelloWorldProjectPerformanceTestForAganistServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiJQueryWidgetHelloWorldProjectPerformanceTestForAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.PerformanceTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiJQueryWidgetHelloWorldProjectLoadTestAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiJQueryWidgetHelloWorldProjectLoadTestAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.LoadTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiJQueryWidgetEshopProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiJQueryWidgetEshopProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.multiJQueryWidgetProjectEshopCreate(methodName, multiJQuery);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiJQueryWidgetEshopProjectEditApplication()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiJQueryWidgetEshopProjectEditApplication()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.multiJQueryWidgetProjectEshopEditApplication(methodName,multiJQuery);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiJQueryWidgetEshopProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiJQueryWidgetEshopProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.multiJQueryWidgetProjectEshopAddFeature(methodName, multiJQuery);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiJQueryWidgetEshopProjectAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiJQueryWidgetEshopProjectAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.multiJQueryWidgetProjectAddConfigurationServer(methodName, multiJQuery);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiJQueryWidgetEshopProjectAddConfigurationWebservice()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiJQueryWidgetProjectEshopAddConfigurationWebservice()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.multiJQueryWidgetProjectAddConfigurationWebService(methodName, multiJQuery);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiJQueryWidgetEshopProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiJQueryWidgetEshopProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.webLayerBuildTab(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiJQueryWidgetEshopProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiJQueryWidgetEshopProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.webLayerDeploy(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiJQueryWidgetEshopProjectUnitTestJs()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiJQueryWidgetEshopProjectUnitTestJs()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.UnittestVerificationJs(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testMultiJQueryWidgetEshopProjectUnitTestJava()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiJQueryWidgetEshopProjectUnitTestJava()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.UnittestVerificationJava(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiJQueryWidgetEshopProjectPerformanceTestForAganistServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiJQueryWidgetEshopProjectPerformanceTestForAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.PerformanceTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiJQueryWidgetEshopProjectPerformanceTestForAganistWebservice()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiJQueryWidgetEshopProjectPerformanceTestForAganistWebservice()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.PerformanceTestForAganistWebService(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiJQueryWidgetEshopProjectLoadTestAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiJQueryWidgetEshopProjectLoadTestAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.LoadTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiJQueryWidgetEshopProjectLoadTestAganistWebservices() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiJQueryWidgetEshopProjectLoadTestAganistWebservices()-------------");
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

