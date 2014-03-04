package com.photon.phresco.ApplicationLayer;



import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.AppLayer.AppBaseScreen;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.JavaWebServiceConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;
import com.photon.phresco.preconditions.*;



public class JavaWebserviceTest {

	private static String selectedBrowser;
	private static UserInfoConstants userInfo;
	private static PhrescoFrameworkData phrscData;
	private static PhrescoUiConstantsXml phrscUi;
	private static phresco_env_config phrscEnv;
	private static AppBaseScreen baseScreen;
	private static CreateDbsql createdb;
	private static JavaWebServiceConstantsXml JvaWebServiceConst;
	private String methodName;

	@BeforeTest
	public static void setUp() throws Exception
	{
	
		phrscEnv=new phresco_env_config();
		phrscUi=new PhrescoUiConstantsXml();
		userInfo=new UserInfoConstants();
		phrscData=new PhrescoFrameworkData();
		JvaWebServiceConst=new JavaWebServiceConstantsXml();
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
	public void testJavaWebserviceHelloWorldProjectCreation()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testJavaWebserviceHelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			baseScreen.loginPage(methodName);
		   baseScreen.javaWebserviceProjectHelloWorldCreate(methodName, JvaWebServiceConst);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	@Test
	public void testjavaWebServiceHelloWorldProjectEdit()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testjavaWebServiceHelloWorldProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.javaWebserviceProjectHelloWorldEditApplication(methodName, JvaWebServiceConst);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testjavaWebServiceHelloWorldProjectAddFeature()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testjavaWebServiceHelloWorldProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.javaWebserviceProjectHelloWorldAddFeature(methodName, JvaWebServiceConst);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testjavaWebServiceHelloWorldAddServerConfiguration()
		throws InterruptedException, IOException, Exception {
		try {
		
			System.out
			.println("---------testjavaWebServiceHelloWorldAddServerConfiguration()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		
			baseScreen.javaWebserviceAddConfigurationServer(methodName, JvaWebServiceConst);
		
		
		} catch (Exception t) {
			t.printStackTrace();
		
		}
	}
	
	
	@Test
	public void testJavaWebserviceHelloWorldBuildTab()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out.println("---------testJavaWebserviceHelloWorldBuildTab()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
		
		baseScreen.appLayerBuildTab(methodName);
		Thread.sleep(100000);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testJavaWebserviceHelloWorldDeploy()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out.println("---------testJavaWebserviceHelloWorldDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		
		baseScreen.appLayerDeploy(methodName);
		Thread.sleep(100000);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	@Test
	public void testJavaWebserviceHelloUnittest() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testJavaWebserviceHelloUnittest()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();

		baseScreen.UnittestVerification(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
}
	
	@Test
	public void testJavaWebserviceHelloPerformanceAganistServer() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testJavaWebserviceHelloPerformanceAganistServer()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		
		baseScreen.PerformanceTestForAganistServer(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}


	
	@Test
	public void testJavaWebserviceHelloLoadTestAganistServer() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testJavaWebserviceHelloLoadTestAganistServer()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		
		baseScreen.LoadTestForAganistServer(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}
	
	@Test
	public void testJavaWebserviceEshopProjectCreation()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testJavaWebserviceEshopProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			baseScreen.javaWebserviceProjectEshopCreate(methodName, JvaWebServiceConst);
			Thread.sleep(10000);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}


	@Test
	public void testJavaWebserviceEshopProjectEdit()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testJavaWebserviceEshopProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
		
		baseScreen.javaWebserviceProjectEshopEditApplication(methodName, JvaWebServiceConst);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	

@Test
public void testjavaWebServiceEshopProjectAddFeature()
		throws InterruptedException, IOException, Exception {
	try {

		System.out
		.println("---------testjavaWebServiceEshopProjectAddFeature()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();

		baseScreen.javaWebserviceProjectEshopAddFeature(methodName, JvaWebServiceConst);

	} catch (Exception t) {
		t.printStackTrace();

	}
}
	@Test
	public void testjavaWebServiceEshopAddServerConfiguration()
		throws InterruptedException, IOException, Exception {
		try {
		
			System.out
			.println("---------testjavaWebServiceEshopAddServerConfiguration()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		
			baseScreen.javaWebserviceAddConfigurationServer(methodName, JvaWebServiceConst);
		
		
		} catch (Exception t) {
			t.printStackTrace();
		
		}
	}
	
	@Test
	public void testjavaWebServiceEshopDatabase()
		throws InterruptedException, IOException, Exception {
		try {
		
			System.out
			.println("---------testjavaWebServiceEshopDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			createdb.CreateDatabase(methodName);
			baseScreen.javaWebserviceAddConfigurationDatabase(methodName, JvaWebServiceConst);
		
		
		} catch (Exception t) {
			t.printStackTrace();
		
		}
	}

	@Test
	public void testJavaWebserviceEshopBuildTab()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out.println("---------testJavaWebserviceEshopBuildTab()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
		
		baseScreen.appLayerBuildTab(methodName);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testJavaWebserviceEshopDeploy()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out.println("---------testJavaWebserviceEshopDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
		baseScreen.appLayerDeploy(methodName);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	@Test
	public void testJavaWebserviceEshopUnitTest()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out.println("---------testJavaWebserviceEshopUnitTest()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
		baseScreen.UnittestVerification(methodName);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	
	@Test
	public void testJavaWebserviceEshopPerformanceAganistServer() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testJavaWebserviceEshopPerformanceAganistServer()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		
		baseScreen.PerformanceTestForAganistServer(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}

	@Test
	public void testJavaWebserviceEshopPerformanceAganistDatabase() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testJavaWebserviceEshopPerformanceAganistDatabase()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		
		baseScreen.PerformanceTestForAganistDatabase(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}
	
	@Test
	public void testJavaWebserviceEshopLoadTestAganistServer() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testJavaWebserviceEshopLoadTestAganistServer()-------------");
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

