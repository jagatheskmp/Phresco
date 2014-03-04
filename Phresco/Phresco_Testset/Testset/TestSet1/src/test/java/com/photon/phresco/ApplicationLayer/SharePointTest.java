package com.photon.phresco.ApplicationLayer;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.AppLayer.AppBaseScreen;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.SharePointConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class SharePointTest {

	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrsConst;
	private phresco_env_config phrscEnv;
	private AppBaseScreen appBaseScreen;
	private SharePointConstantsXml SharePointConst;
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
		SharePointConst = new SharePointConstantsXml();

		try {
			String applicationURL = phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT	+ "/";
			appBaseScreen = new AppBaseScreen(selectedBrowser, selectedPlatform, applicationURL, phrscEnv.CONTEXT, phrscEnv, phrsConst, userInfo, phrscData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSharepointHelloWorldProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testSharepointHelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.loginPage(methodName);
			appBaseScreen.SharePointProjectHelloWorldCreate(methodName, SharePointConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	} 

	@Test
	public void testSharepointHelloWorldProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testSharepointHelloWorldProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.sharePointProjectHelloworldEditApplication(methodName, SharePointConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testSharepointHelloWorldProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testSharepointHelloWorldProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.sharePointProjectHelloworldAddFeature(methodName, SharePointConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testSharepointHelloWorldProjectAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testSharepointHelloWorldProjectAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.sharepointServerConfiguration(methodName, SharePointConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testSharepointHelloWorldProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testSharepointHelloWorldProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.appLayerBuildTab(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testSharepointHelloWorldProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testSharepointHelloWorldProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.appLayerDeployProject(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testSharepointHelloWorldProjectUnitTest()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testSharepointHelloWorldProjectUnitTest()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.UnittestVerification(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testSharepointHelloWorldProjectPerformanceAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testSharepointHelloWorldProjectPerformanceAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.PerformanceTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testSharepointHelloWorldProjectLoadTestAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testSharepointHelloWorldProjectLoadTestAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.LoadTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testSharepointResourceManagementProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testSharepointManagementProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.SharePointProjectResourceCreate(methodName, SharePointConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	} 

	@Test
	public void testSharepointResourceManagementProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testSharepointResourceManagementProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.sharePointProjectResourceEditApplication(methodName, SharePointConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	@Test
	public void testSharepointResourceManagementProjectAddfeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testSharepointResourceManagementProjectAddfeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.sharePointProjectResourcesAddFeature(methodName, SharePointConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testSharepointResourceManagementProjectAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testSharepointProjectResourceManagementProjectAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.sharepointServerConfiguration(methodName, SharePointConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testSharepointResourceManagementProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testSharepointResourceManagementProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.appLayerBuildTab(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testSharepointResourceManagementProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testSharepointResourceManagementProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.appLayerDeployProject(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testSharepointResourceManagementProjectUnitTest()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testSharepointResourceManagementProjectUnitTest()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.UnittestVerification(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testSharepointResourceManagementProjectPerformanceAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testSharepointResourceManagementProjectPerformanceAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.PerformanceTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testSharepointResourceManagementProjectLoadTestAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testSharepointResourceManagementProjectLoadTestAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.LoadTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@AfterTest
	public void tearDown() {
		appBaseScreen.closeBrowser();
	}
}

