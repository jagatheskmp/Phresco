package com.photon.phresco.ApplicationLayer;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.AppLayer.AppBaseScreen;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.WordPressConstantsXml;
import com.photon.phresco.uiconstants.phresco_env_config;
import com.photon.phresco.preconditions.*;

public class WordPressTest {

	private static String selectedBrowser;
	private static UserInfoConstants userInfo;
	private static PhrescoFrameworkData phrscData;
	private static PhrescoUiConstantsXml phrscUi;
	private static phresco_env_config phrscEnv;
	private static AppBaseScreen baseScreen;
	private static CreateDbsql createdb;
	private static WordPressConstantsXml wordPressConst;
	private String methodName;


	@BeforeTest
	public static void setUp() throws Exception
	{
	
		phrscEnv=new phresco_env_config();
		phrscUi=new PhrescoUiConstantsXml();
		userInfo=new UserInfoConstants();
		phrscData=new PhrescoFrameworkData();
		wordPressConst=new WordPressConstantsXml();
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
	public void testWordpressHelloWorldProjectCreation()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testWordpressHelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			baseScreen.loginPage(methodName);
			baseScreen.wordPressProjectHelloWorldCreate(methodName, wordPressConst);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testWordPressHelloWorldProjectEdit()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testWordPressHelloWorldProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.wordPressProjectHelloWorldEditApplication(methodName, wordPressConst);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testWordPressProjectHelloWorldAddFeature()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testWordPressProjectHelloWorldAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.wordPressProjectHelloWorldAddFeature(methodName, wordPressConst);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testWordPressProjectHelloWorldAddConfigurationServer()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testWordPressProjectHelloWorldAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.wordPressProjectHelloWorldAddConfigurationServer(methodName, wordPressConst);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testWordPressDatabase()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testWordPressDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			createdb.CreateDatabase(methodName);
			baseScreen.wordPressProjectHelloWorldAddConfigurationDatabase(methodName, wordPressConst);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testWordPressProjectHelloWorldBuild()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testWordPressProjectHelloWorldBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.appLayerBuildTab(methodName);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testWordPressProjectHelloWorldDeploy()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testWordPressProjectHelloWorldDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.appLayerDeploy(methodName);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	@Test
	public void testWordPressProjectHelloWorldUnitTest()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testWordPressProjectHelloWorldUnitTest()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.UnittestVerification(methodName);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	// performance test starts here
	
	@Test
	public void testWordPressHelloPerformanceAganistServer() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testWordPressHelloPerformanceAganistServer()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		
		baseScreen.PerformanceTestForAganistServer(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}

	@Test
	public void testWordPressHelloPerformanceAganistDatabase() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testWordPressHelloPerformanceAganistDatabase()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		
		baseScreen.PerformanceTestForAganistDatabase(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}
	@Test
	public void testWordPressHelloLoadTestAganistServer() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testWordPressHelloLoadTestAganistServer()-------------");
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
	
	