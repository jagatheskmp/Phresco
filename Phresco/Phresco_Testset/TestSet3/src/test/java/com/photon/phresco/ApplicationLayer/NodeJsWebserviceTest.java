package com.photon.phresco.ApplicationLayer;
import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.AppLayer.AppBaseScreen;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.NodeJsWebServiceConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;
import com.photon.phresco.preconditions.*;

public class NodeJsWebserviceTest {

	private static String selectedBrowser;
	private static UserInfoConstants userInfo;
	private static PhrescoFrameworkData phrscData;
	private static PhrescoUiConstantsXml phrscUi;
	private static phresco_env_config phrscEnv;
	private static AppBaseScreen baseScreen;
	private static CreateDbsql createdb;
	private static NodeJsWebServiceConstantsXml nodejs;
	private String methodName;

	@BeforeTest
	public static void setUp() throws Exception
	{
	
		phrscEnv=new phresco_env_config();
		phrscUi=new PhrescoUiConstantsXml();
		userInfo=new UserInfoConstants();
		phrscData=new PhrescoFrameworkData();
		nodejs=new NodeJsWebServiceConstantsXml();
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
	public void testNodeJsWebserviceHelloWorldProjectCreation()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testNodeJsWebserviceHelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			baseScreen.loginPage(methodName);
		   baseScreen.NodeJsWebserviceProjectHelloWorldCreate(methodName, nodejs);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	@Test
	public void testNodeJsWebserviceHelloWorldProjectEdit()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testNodeJsWebserviceHelloWorldProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.NodeJsWebserviceProjectHelloWorldEditApplication(methodName, nodejs);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testNodeJsWebserviceHelloWorldProjectAddFeature()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out.println("---------testNodeJsWebserviceEshopdProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
		
		baseScreen.NodeJsWebserviceProjectHelloWorldAddFeature(methodName, nodejs);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testNodeJsHelloWorldProjectConfigurationServer()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out.println("---------testNodeJsHelloWorldProjectConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
		baseScreen.NodeJsAddConfigurationServer(methodName, nodejs);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testNodeJsHelloworldDatabase()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out.println("---------testNodeJsHelloworldDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		createdb.CreateDatabase(methodName)	;
		baseScreen.NodeJsAddConfigurationDatabase(methodName, nodejs);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	@Test
	public void testNodeJsHelloWorldBuild()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out.println("---------testNodeJsHelloWorldBuildTab()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
		
		baseScreen.appLayerRunAgainstSource(methodName);
		Thread.sleep(10000);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testNodeJsHelloUnitTest()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out.println("---------testNodeJsHelloUnitTest()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
		baseScreen.UnittestVerification(methodName);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	
	@Test
	public void testNodeJsHelloPerformanceAganistServer() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testNodeJsHelloPerformanceAganistServer()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		
		baseScreen.PerformanceTestForAganistServer(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}

	
	
	@Test
	public void testNodeJsHelloLoadTestAganistServer() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testNodeJsHelloLoadTestAganistServer()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		
		baseScreen.LoadTestForAganistServer(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}
	
	
	@Test
	public void testNodeJsWebserviceEshopProjectCreation()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testNodeJsWebserviceEshopProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
		baseScreen.NodeJsWebserviceProjectEshopCreate(methodName, nodejs);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}


	@Test
	public void testNodeJsWebserviceEshopProjectEdit()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out.println("---------testNodeJsWebserviceEshopProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
		
		baseScreen.NodeJsWebserviceProjectEshopEditApplication(methodName, nodejs);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testNodeJsWebserviceEshopProjectAddFeature()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out.println("---------testNodeJsWebserviceEshopdProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
		
		baseScreen.NodeJsWebserviceProjectEshopAddFeature(methodName, nodejs);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testNodeJsEshopdProjectConfigurationServer()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out.println("---------testNodeJsEshopdProjectConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
		baseScreen.NodeJsAddConfigurationServer(methodName, nodejs);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testNodeJsEshopDatabase()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out.println("---------testNodeJsEshopDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		createdb.CreateDatabase(methodName)	;
		baseScreen.NodeJsAddConfigurationDatabase(methodName, nodejs);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testNodeJsEshopBuild()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out.println("---------testNodeJsEshopBuildTab()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
		
		baseScreen.appLayerRunAgainstSource(methodName);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testNodeJsEshopUnitTest()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out.println("---------testNodeJsEshopUnitTest()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
		baseScreen.UnittestVerification(methodName);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testNodeJsEshopPerformanceAganistServer() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testNodeJsEshopPerformanceAganistServer()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		
		baseScreen.PerformanceTestForAganistServer(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}

	@Test
	public void testNodeJsEshopPerformanceAganistDatabase() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testNodeJsEshopPerformanceAganistDatabase()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		
		baseScreen.PerformanceTestForAganistDatabase(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}
	
	@Test
	public void testNodeJsEshopLoadTestAganistServer() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testNodeJsEshopLoadTestAganistServer()-------------");
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

