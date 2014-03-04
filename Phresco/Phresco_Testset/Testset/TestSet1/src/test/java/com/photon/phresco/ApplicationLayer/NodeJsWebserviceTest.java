package com.photon.phresco.ApplicationLayer;
import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.AppLayer.AppBaseScreen;
import com.photon.phresco.preconditions.CreateDbsql;
import com.photon.phresco.uiconstants.NodeJsWebServiceConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class NodeJsWebserviceTest {

	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrsConst;
	private phresco_env_config phrscEnv;
	private AppBaseScreen appBaseScreen;
	private CreateDbsql createdb;
	private NodeJsWebServiceConstantsXml nodejs;
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
		nodejs = new NodeJsWebServiceConstantsXml();
		createdb = new CreateDbsql();
		try {
			String applicationURL = phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT	+ "/";
			appBaseScreen = new AppBaseScreen(selectedBrowser,selectedPlatform,applicationURL,phrscEnv.CONTEXT,phrscEnv,phrsConst,userInfo,phrscData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNodeJsHelloWorldProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsHelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.loginPage(methodName);
			appBaseScreen.NodeJsWebserviceProjectHelloWorldCreate(methodName, nodejs);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testNodeJsHelloWorldProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsHelloWorldProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.NodeJsWebserviceProjectHelloWorldEditApplication(methodName, nodejs);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testNodeJsHelloWorldProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsHelloWorldProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.NodeJsWebserviceProjectHelloWorldAddFeature(methodName, nodejs);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testNodeJsHelloWorldProjectAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsHelloWorldProjectAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.NodeJsAddConfigurationServer(methodName, nodejs);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testNodeJsHelloWorldProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsHelloWorldProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.appLayerRunAgainstSource(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testNodeJsHelloWorldProjectUnitTest()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsHelloWorldProjectUnitTest()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.UnittestVerification(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testNodeJsHelloWorldProjectPerformanceAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsHelloWorldProjectPerformanceAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.PerformanceTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testNodeJsHelloWorldProjectPerformanceAganistDatabase() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsHelloWorldProjectPerformanceAganistDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.PerformanceTestForAganistDatabase(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testNodeJsHelloWorldProjectLoadTestAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsHelloWorldProjectLoadTestAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.LoadTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testNodeJsEshopProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsEshopProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.NodeJsWebserviceProjectEshopCreate(methodName, nodejs);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testNodeJsEshopProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsEshopProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.NodeJsWebserviceProjectEshopEditApplication(methodName, nodejs);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testNodeJsEshopProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsEshopdProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.NodeJsWebserviceProjectEshopAddFeature(methodName, nodejs);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testNodeJsEshopProjectAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsEshopProjectConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.NodeJsAddConfigurationServer(methodName, nodejs);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testNodeJsEshopProjectDatabase()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsEshopProjectDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			createdb.CreateDatabase(methodName)	;
			appBaseScreen.NodeJsAddConfigurationDatabase(methodName, nodejs);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testNodeJsEshopProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsEshopProjectBuildTab()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.appLayerRunAgainstSource(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testNodeJsEshopProjectUnitTest()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsEshopProjectUnitTest()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.UnittestVerification(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testNodeJsEshopProjectPerformanceAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsEshopProjectPerformanceAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.PerformanceTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testNodeJsEshopProjectPerformanceAganistDatabase() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsEshopProjectPerformanceAganistDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.PerformanceTestForAganistDatabase(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testNodeJsEshopProjectLoadTestAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsEshopProjectLoadTestAganistServer()-------------");
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
