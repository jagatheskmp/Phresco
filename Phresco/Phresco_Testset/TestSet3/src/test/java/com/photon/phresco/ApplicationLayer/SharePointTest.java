package com.photon.phresco.ApplicationLayer;



import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.AppLayer.AppBaseScreen;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.SharePointConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class SharePointTest {

	private static String selectedBrowser;
	private static UserInfoConstants userInfo;
	private static PhrescoFrameworkData phrscData;
	private static PhrescoUiConstantsXml phrscUi;
	private static phresco_env_config phrscEnv;
	private static AppBaseScreen baseScreen;
	private static SharePointConstantsXml SharePointConst;
	private String methodName;


	@BeforeTest
	public static void setUp() throws Exception
	{
	
		phrscEnv=new phresco_env_config();
		phrscUi=new PhrescoUiConstantsXml();
		userInfo=new UserInfoConstants();
		phrscData=new PhrescoFrameworkData();
		SharePointConst=new SharePointConstantsXml();
		
		
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
	public void testSharepointHelloWorldProjectCreation()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out.println("---------testSharepointHelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			baseScreen.loginPage(methodName);
			baseScreen.SharePointProjectHelloWorldCreate(methodName, SharePointConst);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	} 

	@Test
	public void testSharepointHelloWorldProjectEdit()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testSharepointHelloWorldProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			baseScreen.sharePointProjectHelloworldEditApplication(methodName, SharePointConst);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testSharepointHelloWorldProjectAddFeature()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testSharepointHelloWorldProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			baseScreen.sharePointProjectHelloworldAddFeature(methodName, SharePointConst);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testsharepointHelloWorldAddConfigurationServer()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testsharepointHelloWorldAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.sharepointServerConfiguration(methodName, SharePointConst);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testsharepointHelloWorldBuild()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testsharepointHelloWorldBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.appLayerBuildTab(methodName);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testsharepointHelloWorldDeploy()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testsharepointHelloWorldDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.appLayerDeploy1(methodName);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testSharepointResourceProjectCreation()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out.println("---------testSharepointResourceProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			baseScreen.SharePointProjectResourceCreate(methodName, SharePointConst);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	} 

	@Test
	public void testSharepointResourceProjectEdit()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testSharepointResourceProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			baseScreen.sharePointProjectResourceEditApplication(methodName, SharePointConst);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testSharepointResourceProjectAddfeature()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testSharepointResourceProjectAddfeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			baseScreen.sharePointProjectResourcesAddFeature(methodName, SharePointConst);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
		
	@Test
	public void testsharepointResourceAddConfigurationServer()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testSharepointProjectResourceAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.sharepointServerConfiguration(methodName, SharePointConst);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testSharepointResourceBuild()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testSharepointResourceBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.appLayerBuildTab(methodName);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testSharepointResourceDeploy()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testSharepointResourceDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.appLayerDeploy1(methodName);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testSharepointResourceUnitTest()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testSharepointResourceUnitTest()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.UnittestVerification(methodName);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testSharepointHelloUnitTest()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testSharepointHelloUnitTest()-------------");
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

