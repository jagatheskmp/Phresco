package com.photon.phresco.ApplicationLayer;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.AppLayer.AppBaseScreen;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.Drupal7ConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;
import com.photon.phresco.preconditions.*;

public class Drupal7Test {

	private static String selectedBrowser;
	private static CreateDbsql createdb;
	private static UserInfoConstants userInfo;
	private static PhrescoFrameworkData phrscData;
	private static PhrescoUiConstantsXml phrscUi;
	private static phresco_env_config phrscEnv;
	private static AppBaseScreen baseScreen;
	private static Drupal7ConstantsXml drupal7Const;
	private String methodName;


	@BeforeTest
	public static void setUp() throws Exception{
	
		phrscEnv=new phresco_env_config();
		phrscUi=new PhrescoUiConstantsXml();
		userInfo=new UserInfoConstants();
		phrscData=new PhrescoFrameworkData();
		drupal7Const=new Drupal7ConstantsXml();
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
	public void testDrupal7HelloWorldProjectCreation()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testDrupal7HelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			baseScreen.loginPage(methodName);
			baseScreen.drupal7ProjectHelloWorldCreate(methodName,drupal7Const);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testDrupal7HelloWorldProjectEdit()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testDrupal7HelloWorldProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.drupal7ProjectHelloWorldEditApplication(methodName,drupal7Const);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testDrupal7ProjectHelloWorldAddFeature()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testDrupal7ProjectHelloWorldAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.drupal7ProjectAddFeature(methodName,drupal7Const);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testDrupal7ProjectHelloWorldAddConfigurationServer()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testDrupal7ProjectHelloWorldAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.drupal7ProjectAddConfigurationServer(methodName,drupal7Const);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testDrupal7HelloWorldDatabase()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testDrupal7HelloWorldDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			createdb.CreateDatabase(methodName);
			baseScreen.drupal7ProjectAddConfigurationDatabase(methodName,drupal7Const);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testDrupal7HelloworldProjectBuidTab()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testDrupal7HelloworldProjectBuidTab()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.appLayerBuildTab(methodName);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testDrupal7HelloworldProjectDeploy()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testDrupal7HelloworldProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.appLayerDeploy(methodName);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test


	public void testDrupal7HelloUnittest() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testDrupal7HelloUnittest()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();

		baseScreen.UnittestVerification(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}
	
	// performance test start here
	
	@Test
	public void testDrupal7HelloPerformanceAganistServer() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testDrupal7HelloPerformanceAganistServer()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		
		baseScreen.PerformanceTestForAganistServer(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}

	@Test
	public void tesDrupal7HelloPerformanceAganistDatabase() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------tesDrupal7HelloPerformanceAganistDatabase()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		
		baseScreen.PerformanceTestForAganistDatabase(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}
	
	@Test
	public void testDrupal7HelloLoadTestAganistServer() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testDrupal7HelloLoadTestAganistServer()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		
		baseScreen.LoadTestForAganistServer(methodName);


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
			baseScreen.drupal7ProjectEshopCreate(methodName,drupal7Const);
			
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

			baseScreen.drupal7ProjectEshopEditApplication(methodName,drupal7Const);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testDrupal7EshopProjectEshopAddFeature()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testDrupal7EshopProjectEshopAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.drupal7ProjectAddFeature(methodName, drupal7Const);


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

			baseScreen.drupal7ProjectAddConfigurationServer(methodName,drupal7Const);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testDrupal7EshopDatabase()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testDrupal7EshopProjectAddConfigurationDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			createdb.CreateDatabase(methodName);
			baseScreen.drupal7ProjectAddConfigurationDatabase(methodName,drupal7Const);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	@Test
	public void testDrupal7EshopProjectBuidTab()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testDrupal7EshopProjectBuidTab()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.appLayerBuildTab(methodName);


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

			baseScreen.appLayerDeploy(methodName);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	@Test


	public void testDrupal7EshopUnittest() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testDrupal7EshopUnittest()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();

		baseScreen.UnittestVerification(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}
	
	// performance test start here
	
	@Test
	public void testDrupal7EshopPerformanceAganistServer() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testDrupal7EshopPerformanceAganistServer()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		
		baseScreen.PerformanceTestForAganistServer(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}

	@Test
	public void tesDrupal7EshopPerformanceAganistDatabase() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------tesDrupal7EshopPerformanceAganistDatabase()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		
		baseScreen.PerformanceTestForAganistDatabase(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}
	
	@Test
	public void testDrupal7EshopLoadTestAganistServer() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testDrupal7EshopLoadTestAganistServer()-------------");
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

