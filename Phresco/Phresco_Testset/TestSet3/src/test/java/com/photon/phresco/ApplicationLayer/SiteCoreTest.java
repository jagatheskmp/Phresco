package com.photon.phresco.ApplicationLayer;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.AppLayer.AppBaseScreen;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.SiteCoreConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class SiteCoreTest {

	private static String selectedBrowser;
	private static UserInfoConstants userInfo;
	private static PhrescoFrameworkData phrscData;
	private static PhrescoUiConstantsXml phrscUi;
	private static phresco_env_config phrscEnv;
	private static AppBaseScreen baseScreen;
	private static SiteCoreConstantsXml siteCoreConst;
	
	private String methodName;

	@BeforeTest
	public static void setUp() throws Exception
	{
	
		phrscEnv=new phresco_env_config();
		phrscUi=new PhrescoUiConstantsXml();
		userInfo=new UserInfoConstants();
		phrscData=new PhrescoFrameworkData();
		siteCoreConst=new SiteCoreConstantsXml();
		
		
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
	public void testSiteCoreHelloWorldProjectCreation()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out.println("---------testSiteCoreHelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			baseScreen.loginPage(methodName);
			baseScreen.siteCoreProjectHelloWorldCreate(methodName,siteCoreConst);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testSiteCoreHelloWorldProjectEdit()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testSiteCoreHelloWorldProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.siteCoreProjectHelloWorldEditApplication(methodName,siteCoreConst);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testSiteCoreProjectHelloWorldAddFeature()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testSiteCoreProjectHelloWorldAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.siteCoreProjectHelloWorldAddFeature(methodName,siteCoreConst);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	@Test
	public void testSiteCoreHelloWorldAddConfigurationServer()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testSiteCoreHelloWorldAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.SiteCoreServerConfiguration(methodName,siteCoreConst);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testSiteCoreHelloWorldBuild()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testSiteCoreHelloWorldBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.appLayerBuildTab(methodName);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testSiteCoreHelloWorldDeploy()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testSiteCoreHelloWorldDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.appLayerDeploy1(methodName);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	@Test
	public void testSiteCoreHelloWorldUnitTest()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testSiteCoreHelloWorldUnitTest()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.UnittestVerification(methodName);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	// performance starts here
	
	@Test
	public void testPerformanceAganistServer() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testPerformanceAganistServer()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		
		baseScreen.PerformanceTestForAganistServer(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}

	@Test
	public void testPerformanceAganistDatabase() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testPerformanceAganistDatabase()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		
		baseScreen.PerformanceTestForAganistDatabase(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}
	@Test
	public void testLoadTestAganistServer() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testLoadTestAganistServer()-------------");
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
