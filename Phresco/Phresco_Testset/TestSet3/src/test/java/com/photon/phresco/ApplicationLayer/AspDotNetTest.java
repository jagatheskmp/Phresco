package com.photon.phresco.ApplicationLayer;
import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.AppLayer.AppBaseScreen;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.AspDotNetConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class AspDotNetTest {

	private static String selectedBrowser;
	private static UserInfoConstants userInfo;
	private static PhrescoFrameworkData phrscData;
	private static PhrescoUiConstantsXml phrsConst;
	private static phresco_env_config phrscEnv;
	private static AppBaseScreen baseScreen;
	private static AspDotNetConstantsXml AspDotNetConst;
	private String methodName;


	@BeforeTest
	public static void setUp() throws Exception
	{
	
		phrscEnv=new phresco_env_config();
		phrsConst=new PhrescoUiConstantsXml();
		userInfo=new UserInfoConstants();
		phrscData=new PhrescoFrameworkData();
		AspDotNetConst=new AspDotNetConstantsXml();
		
		
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
					phrscEnv.CONTEXT,phrscEnv,phrsConst,userInfo,phrscData);
		} catch (ScreenException e) {

			e.printStackTrace();
		}

	}

	@Test
	public void testAspDotNetHelloWorldProjectCreation()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testAspDotNetHelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			baseScreen.loginPage(methodName);
		baseScreen.AspDotNetProjectHelloWorldCreate(methodName, AspDotNetConst);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	} 

	@Test
	public void testAspDotNetHelloWorldProjectEdit()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testAspDotNetHelloWorldProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			baseScreen.AspDotNetProjectHelloWorldEditApplication(methodName, AspDotNetConst);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testAspDotNetHelloWorldProjectAddFeature()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testAspDotNetHelloWorldProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			baseScreen.AspDotNetProjectHelloWorldAddFeature(methodName, AspDotNetConst);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	 @Test
	public void testAspDotNetProjectHelloWorldAddConfigurationServer()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testAspDotNetProjectHelloWorldAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.aspDotNetServerConfiguration(methodName, AspDotNetConst);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	 
	 @Test
		public void testAspDotNetCoreHelloWorldBuild()
				throws InterruptedException, IOException, Exception {
			try {

				System.out.println("---------testAspDotNetCoreHelloWorldBuild()-------------");
				methodName = Thread.currentThread().getStackTrace()[1]
						.getMethodName();

				baseScreen.appLayerBuildTab(methodName);


			} catch (Exception t) {
				t.printStackTrace();

			}
		}
		
		@Test
		public void testAspDotNetCoreHelloWorldDeploy()
				throws InterruptedException, IOException, Exception {
			try {

				System.out.println("---------testAspDotNetCoreHelloWorldDeploy()-------------");
				methodName = Thread.currentThread().getStackTrace()[1]
						.getMethodName();

				baseScreen.appLayerDeploy1(methodName);


			} catch (Exception t) {
				t.printStackTrace();

			}
		}
	 
		@Test
		public void testAspDotNetCoreHelloUnitTest()
				throws InterruptedException, IOException, Exception {
			try {

				System.out.println("---------testAspDotNetCoreHelloUnitTest()-------------");
				methodName = Thread.currentThread().getStackTrace()[1]
						.getMethodName();

				baseScreen.UnittestVerification(methodName);


			} catch (Exception t) {
				t.printStackTrace();

			}
		}
	 
		
// performance start here 
		
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

