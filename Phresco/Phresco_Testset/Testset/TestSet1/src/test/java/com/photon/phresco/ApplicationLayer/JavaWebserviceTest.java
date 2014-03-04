package com.photon.phresco.ApplicationLayer;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.photon.phresco.Screens.AppLayer.AppBaseScreen;
import com.photon.phresco.preconditions.CreateDbsql;
import com.photon.phresco.uiconstants.JavaWebServiceConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class JavaWebserviceTest {

	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrsConst;
	private phresco_env_config phrscEnv;
	private AppBaseScreen appBaseScreen;
	private CreateDbsql createdb;
	private JavaWebServiceConstantsXml JvaWebServiceConst;
	private String methodName;

	@Parameters(value = {"browser","platform"})
	@BeforeTest
	public void setUp(String browser, String platform) throws Exception
	{
		String selectedBrowser = browser;
		String selectedPlatform = platform;
		phrscEnv = new phresco_env_config();
		phrsConst = new PhrescoUiConstantsXml();
		userInfo = new UserInfoConstants();
		phrscData = new PhrescoFrameworkData();
		JvaWebServiceConst = new JavaWebServiceConstantsXml();
		createdb = new CreateDbsql();

		try {
			String applicationURL = phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT	+ "/";
			appBaseScreen = new AppBaseScreen(selectedBrowser,selectedPlatform,applicationURL,phrscEnv.CONTEXT,phrscEnv,phrsConst,userInfo,phrscData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testJavaWebServiceHelloWorldProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaWebServiceHelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.loginPage(methodName);
			appBaseScreen.javaWebserviceProjectHelloWorldCreate(methodName, JvaWebServiceConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJavaWebServiceHelloWorldProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaWebServiceHelloWorldProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.javaWebserviceProjectHelloWorldEditApplication(methodName, JvaWebServiceConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJavaWebServiceHelloWorldProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaWebServiceHelloWorldProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.javaWebserviceProjectHelloWorldAddFeature(methodName, JvaWebServiceConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testJavaWebServiceHelloWorldProjectAddServerConfiguration()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaWebServiceHelloWorldProjectAddServerConfiguration()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.javaWebserviceAddConfigurationServer(methodName, JvaWebServiceConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJavaWebServiceHelloWorldProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaWebServiceHelloWorldProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();			
			appBaseScreen.appLayerBuildTab(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testJavaWebServiceHelloWorldProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaWebServiceHelloWorldProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.appLayerDeploy(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJavaWebServiceHelloWorldProjectUnittest() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaWebServiceHelloWorldProjectUnittest()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.UnittestVerification(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJavaWebServiceHelloWorldProjectPerformanceAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaWebServiceHelloWorldProjectPerformanceAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.PerformanceTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testJavaWebServiceHelloWorldProjectPerformanceAganistDatabase() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaWebServiceHelloWorldProjectPerformanceAganistDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.PerformanceTestForAganistDatabase(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJavaWebServiceHelloWorldProjectLoadTestAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaWebServiceHelloWorldProjectLoadTestAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.LoadTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJavaWebServiceEshopProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaWebServiceEshopProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.javaWebserviceProjectEshopCreate(methodName, JvaWebServiceConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJavaWebServiceEshopProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaWebServiceEshopProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.javaWebserviceProjectEshopEditApplication(methodName, JvaWebServiceConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJavaWebServiceEshopProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testjavaWebServiceEshopProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.javaWebserviceProjectEshopAddFeature(methodName, JvaWebServiceConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJavaWebServiceEshopProjectAddServerConfiguration()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaWebServiceEshopProjectAddServerConfiguration()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.javaWebserviceAddConfigurationServer(methodName, JvaWebServiceConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJavaWebServiceEshopProjectDatabase()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaWebServiceEshopProjectDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			createdb.CreateDatabase(methodName);
			appBaseScreen.javaWebserviceAddConfigurationDatabase(methodName, JvaWebServiceConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJavaWebServiceEshopProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaWebserviceEshopBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.appLayerBuildTab(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testJavaWebServiceEshopProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaWebServiceEshopProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.appLayerDeploy(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJavaWebServiceEshopProjectUnitTest()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaWebServiceEshopProjectUnitTest()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.UnittestVerification(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJavaWebServiceEshopProjectPerformanceAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaWebServiceEshopProjectPerformanceAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.PerformanceTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJavaWebServiceEshopProjectPerformanceAganistDatabase() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaWebServiceEshopProjectPerformanceAganistDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.PerformanceTestForAganistDatabase(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJavaWebServiceEshopProjectLoadTestAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaWebServiceEshopProjectLoadTestAganistServer()-------------");
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

