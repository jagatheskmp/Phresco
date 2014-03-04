package com.photon.phresco.ApplicationLayer;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.photon.phresco.Screens.AppLayer.AppBaseScreen;
import com.photon.phresco.preconditions.CreateDbsql;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.Drupal6ConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;


public class Drupal6Test {

	private static String selectedBrowser;
	private static UserInfoConstants userInfo;
	private static PhrescoFrameworkData phrscData;
	private static PhrescoUiConstantsXml phrscUi;
	private static phresco_env_config phrscEnv;
	private static AppBaseScreen baseScreen;
	private static Drupal6ConstantsXml drupal6Const;
	private static CreateDbsql createdb;
	private String methodName;

	@BeforeTest
	public static void setUp() throws Exception{
	
		phrscEnv=new phresco_env_config();
		phrscUi=new PhrescoUiConstantsXml();
		userInfo=new UserInfoConstants();
		phrscData=new PhrescoFrameworkData();
		drupal6Const=new Drupal6ConstantsXml();
		createdb=new CreateDbsql();
		
		try {
			launchingBrowser();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static void launchingBrowser() throws Exception {

		String applicationURL = phrscEnv.PROTOCOL + "://"
				+ phrscEnv.HOST + ":" + phrscEnv.PORT
				+ "/";
		selectedBrowser = phrscEnv.BROWSER;
		try {
			baseScreen = new AppBaseScreen(selectedBrowser, applicationURL,
					phrscEnv.CONTEXT,phrscEnv,phrscUi,userInfo,phrscData);
		} catch (ScreenException e) {

			e.printStackTrace();
		}


	}
	@Test
	public void testDrupal6HelloWorldProjectCreation()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testDrupal6HelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			baseScreen.loginPage(methodName);
			baseScreen.drupal6ProjectHelloWorldCreate(methodName,drupal6Const);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testDrupal6HelloWorldProjectEdit()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testDrupal6HelloWorldProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.drupal6ProjectHelloWorldEditApplication(methodName,drupal6Const);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testDrupal6ProjectHelloWorldAddFeature()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testDrupal6ProjectHelloWorldAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.drupal6ProjectHelloWorldAddFeature(methodName,drupal6Const);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testDrupal6ProjectHelloWorldAddConfigurationServer()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testDrupal6ProjectHelloWorldAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.drupal6ProjectHelloWorldAddConfigurationServer(methodName,drupal6Const);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testDrupal6HelloWorldDatabase()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testDrupal6ProjectHelloWorldAddConfigurationDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			createdb.CreateDatabase(methodName);
			baseScreen.drupal6ProjectHelloWorldAddConfigurationDatabase(methodName,drupal6Const);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	@Test
	public void testDrupal6ProjectHelloWorldBuildTab()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testDrupal6ProjectHelloWorldBuildTab()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.appLayerBuildTab(methodName);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testDrupal6ProjectHelloWorldDeploy()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testDrupal6ProjectHelloWorldBuildTab()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			baseScreen.appLayerDeploy(methodName);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	@Test


	public void testDrupal6HelloUnittest() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testingForUnittest()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();

		baseScreen.UnittestVerification(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}
	
	// performance test start here
	
	@Test
	public void testDrupal6HelloPerformanceAganistServer() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testDrupal6HelloPerformanceAganistServer()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		
		baseScreen.PerformanceTestForAganistServer(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}

	@Test
	public void tesDrupal6HelloPerformanceAganistDatabase() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------tesDrupal6HelloPerformanceAganistDatabase()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		
		baseScreen.PerformanceTestForAganistDatabase(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}
	
	@Test
	public void testDrupal6HelloLoadTestAganistServer() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testDrupal6HelloLoadTestAganistServer()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		
		baseScreen.LoadTestForAganistServer(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}

	@AfterTest
	public static void tearDown() {
		baseScreen.closeBrowser();
	}



}

