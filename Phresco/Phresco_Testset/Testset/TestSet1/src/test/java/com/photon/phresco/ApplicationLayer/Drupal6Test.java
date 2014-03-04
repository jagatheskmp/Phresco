package com.photon.phresco.ApplicationLayer;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.AppLayer.AppBaseScreen;
import com.photon.phresco.preconditions.CreateDbsql;
import com.photon.phresco.uiconstants.Drupal6ConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;


public class Drupal6Test {

	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrsConst;
	private phresco_env_config phrscEnv;
	private AppBaseScreen appBaseScreen;
	private Drupal6ConstantsXml drupal6Const;
	private CreateDbsql createdb;
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
		drupal6Const = new Drupal6ConstantsXml();
		createdb = new CreateDbsql();
		try {
			String applicationURL = phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT+ "/";
			appBaseScreen = new AppBaseScreen(selectedBrowser,selectedPlatform,applicationURL,phrscEnv.CONTEXT,phrscEnv,phrsConst,userInfo,phrscData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDrupal6HelloWorldProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testDrupal6HelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.loginPage(methodName);
			appBaseScreen.drupal6ProjectHelloWorldCreate(methodName,drupal6Const);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal6HelloWorldProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testDrupal6HelloWorldProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.drupal6ProjectHelloWorldEditApplication(methodName,drupal6Const);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal6HelloWorldProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal6HelloWorldProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.drupal6ProjectHelloWorldAddFeature(methodName,drupal6Const);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal6HelloWorldProjectAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal6ProjectHelloWorldAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.drupal6ProjectHelloWorldAddConfigurationServer(methodName,drupal6Const);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal6HelloWorldProjectDatabase()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal6HelloWorldProjectDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			createdb.CreateDatabase(methodName);
			appBaseScreen.drupal6ProjectHelloWorldAddConfigurationDatabase(methodName,drupal6Const);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}


	@Test
	public void testDrupal6HelloWorldProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal6HelloWorldProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.appLayerBuildTab(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal6HelloWorldProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal6HelloWorldProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.appLayerDeploy(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}


	@Test
	public void testDrupal6HelloWorldProjectUnittest() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal6HelloWorldProjectUnittest()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.UnittestVerification(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal6HelloWorldProjectPerformanceAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal6HelloWorldProjectPerformanceAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.PerformanceTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal6HelloWorldProjectPerformanceAganistDatabase() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal6HelloWorldProjectPerformanceAganistDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.PerformanceTestForAganistDatabase(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testDrupal6HelloWorldProjectLoadTestAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testDrupal6HelloWorldProjectLoadTestAganistServer()-------------");
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
