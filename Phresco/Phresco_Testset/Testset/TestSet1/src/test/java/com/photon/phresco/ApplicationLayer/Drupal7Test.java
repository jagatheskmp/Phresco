package com.photon.phresco.ApplicationLayer;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.AppLayer.AppBaseScreen;
import com.photon.phresco.preconditions.CreateDbsql;
import com.photon.phresco.uiconstants.Drupal7ConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class Drupal7Test {
	private CreateDbsql createdb;
	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrsConst;
	private phresco_env_config phrscEnv;
	private AppBaseScreen appBaseScreen;
	private Drupal7ConstantsXml drupal7Const;
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
		drupal7Const = new Drupal7ConstantsXml();
		createdb = new CreateDbsql();

		try {
			String applicationURL = phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT	+ "/";
			appBaseScreen = new AppBaseScreen(selectedBrowser,selectedPlatform,applicationURL,phrscEnv.CONTEXT,phrscEnv,phrsConst,userInfo,phrscData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDrupal7HelloWorldProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal7HelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.loginPage(methodName);
			appBaseScreen.drupal7ProjectHelloWorldCreate(methodName,drupal7Const);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal7HelloWorldProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal7HelloWorldProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.drupal7ProjectHelloWorldEditApplication(methodName,drupal7Const);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal7HelloWorldProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal7HelloWorldProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.drupal7ProjectAddFeature(methodName,drupal7Const);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal7HelloWorldProjectAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal7HelloWorldProjectAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.drupal7ProjectAddConfigurationServer(methodName,drupal7Const);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal7HelloWorldProjectDatabase()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal7HelloWorldProjectDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			createdb.CreateDatabase(methodName);
			appBaseScreen.drupal7ProjectAddConfigurationDatabase(methodName,drupal7Const);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal7HelloWorldProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal7HelloWorldProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.appLayerBuildTab(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal7HelloWorldProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal7HelloWorldProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.appLayerDeploy(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal7HelloWorldProjectUnittest() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal7HelloWorldProjectUnittest()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.UnittestVerification(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}


	@Test
	public void testDrupal7HelloWorldProjectPerformanceAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal7HelloWorldProjectPerformanceAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.PerformanceTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal7HelloWorldProjectPerformanceAganistDatabase() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal7HelloWorldProjectPerformanceAganistDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.PerformanceTestForAganistDatabase(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal7HelloworldProjectLoadTestAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testDrupal7HelloworldProjectLoadTestAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.LoadTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal7EshopProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out
			.println("---------testDrupal7EshopProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.drupal7ProjectEshopCreate(methodName,drupal7Const);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal7EshopProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out
			.println("---------testDrupal7EshopProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.drupal7ProjectEshopEditApplication(methodName,drupal7Const);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal7EshopProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out
			.println("---------testDrupal7EshopProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.drupal7ProjectEshopAddFeature(methodName, drupal7Const);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal7EshopProjectAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out
			.println("---------testDrupal7EshopProjectAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.drupal7ProjectAddConfigurationServer(methodName,drupal7Const);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal7EshopProjectDatabase()
	throws InterruptedException, IOException, Exception {
		try {
			System.out
			.println("---------testDrupal7EshopProjectDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			createdb.CreateDatabase(methodName);
			appBaseScreen.drupal7ProjectAddConfigurationDatabase(methodName,drupal7Const);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal7EshopProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal7EshopProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.appLayerBuildTab(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	@Test
	public void testDrupal7EshopProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal7EshopProjectBuidTab()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.appLayerDeploy(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}


	@Test
	public void testDrupal7EshopProjectUnittest() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal7EshopProjectUnittest()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.UnittestVerification(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal7EshopProjectPerformanceAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal7EshopProjectPerformanceAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.PerformanceTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal7EshopProjectPerformanceAganistDatabase() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal7EshopProjectPerformanceAganistDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.PerformanceTestForAganistDatabase(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal7EshopProjectLoadTestAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal7EshopLoadProjectTestAganistServer()-------------");
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

