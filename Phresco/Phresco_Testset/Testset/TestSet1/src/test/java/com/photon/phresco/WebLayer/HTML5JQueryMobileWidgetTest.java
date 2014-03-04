package com.photon.phresco.WebLayer;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.Weblayer.WebBaseScreen;
import com.photon.phresco.uiconstants.HTML5JQueryMobileWidgetConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class HTML5JQueryMobileWidgetTest {

	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrsConst;
	private phresco_env_config phrscEnv;
	private WebBaseScreen webBaseScreen;
	private HTML5JQueryMobileWidgetConstantsXml jQueryMobile;
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
		jQueryMobile = new HTML5JQueryMobileWidgetConstantsXml();
		try {
			String applicationURL = phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT + "/";
			webBaseScreen = new WebBaseScreen(selectedBrowser,selectedPlatform,applicationURL,phrscEnv.CONTEXT,phrscEnv,phrsConst,userInfo,phrscData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testJQueryMobileWidgetHelloWorldProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {			
			System.out.println("---------testJQueryMobileWidgetHelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.loginPage(methodName);
			webBaseScreen.jQueryMobileWidgetProjectHelloWorldCreate(methodName, jQueryMobile);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testJQueryMobileWidgetHelloWorldProjectEditApplication()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetHelloWorldProjectEditApplication()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.jQueryMobileWidgetProjectHelloWorldEditApplication(methodName, jQueryMobile);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJQueryMobileWidgetHelloWorldProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetHelloWorldProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.jQueryMobileWidgetProjectHelloWorldAddFeature(methodName, jQueryMobile);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJQueryMobileWidgetHelloWorldProjectAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetHelloWorldProjectAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.JQueryMobileWidgetProjectAddConfigurationServer(methodName, jQueryMobile);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJQueryMobileWidgetHelloWorldProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetHelloWorldProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.webLayerBuildTab(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJQueryMobileWidgetHelloWorldProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetHelloWorldProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.webLayerDeploy(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJQueryMobileWidgetHelloWorldProjectUnitTestJava()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetHelloWorldProjectUnitTestJava()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.UnittestVerificationJava(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testJQueryMobileWidgetHelloWorldProjectUnitTestJs()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetHelloWorldProjectUnitTestJs()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.UnittestVerificationJs(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJQueryMobileWidgetHelloWorldProjectPerformanceTestForAganistServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetHelloWorldProjectPerformanceTestForAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.PerformanceTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJQueryMobileWidgetHelloWorldProjectLoadTestAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetHelloWorldProjectLoadTestAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.LoadTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJQueryMobileWidgetHelloWorldProjectCodeSourceJs() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetHelloWorldProjectCodeSourceJs()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.codeValidationSource(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testJQueryMobileWidgetHelloWorldProjectCodeSourceJava() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetHelloWorldProjectCodeSourceJava()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.codeValidationSource(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testJQueryMobileWidgetHelloWorldProjectCodeSourceHtml() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetHelloWorldProjectCodeSourceHtml()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.codeValidationSource(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testJQueryMobileWidgetHelloWorldProjectCodeSourceJsf() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetHelloWorldProjectCodeSourceJsf()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.codeValidationSource(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testJQueryMobileWidgetHelloWorldProjectCodeFunctional() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetHelloWorldProjectCodeFunctional()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.codeValidationFunctional(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testJQueryMobileWidgetEshopProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetEshopProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.jQueryMobileWidgetProjectEshopCreate(methodName, jQueryMobile);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testJQueryMobileWidgetEshopProjectEditApplication()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetEshopProjectEditApplication()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.jQueryMobileWidgetProjectEshopEditApplication(methodName,jQueryMobile);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJQueryMobileWidgetEshopProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testjQueryMobileWidgetEshopProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.jQueryMobileWidgetProjectEshopAddFeature(methodName,jQueryMobile);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJQueryMobileWidgetEshopProjectAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetEshopProjectAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.JQueryMobileWidgetProjectAddConfigurationServer(methodName,jQueryMobile);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJQueryMobileWidgetEshopProjectAddConfigurationWebservice()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetEshopProjectAddConfigurationWebservice()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.JQueryMobileWidgetProjectEshopAddConfigurationWebService(methodName,jQueryMobile);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJQueryMobileWidgetEshopProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetEshopProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.webLayerBuildTab(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJQueryMobileWidgetEshopProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetEshopProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.webLayerDeploy(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJQueryMobileWidgetEshopProjectUnitTestJs()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetEshopProjectUnitTestJs()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.UnittestVerificationJs(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testJQueryMobileWidgetEshopProjectUnitTestJava()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetEshopProjectUnitTestJava()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.UnittestVerificationJava(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJQueryMobileWidgetEshopProjectPerformanceTestForAganistServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetEshopProjectPerformanceTestForAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.PerformanceTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJQueryMobileWidgetEshopProjectPerformanceTestForAganistWebservice()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetEshopProjectPerformanceTestForAganistWebservice()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.PerformanceTestForAganistWebService(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJQueryMobileWidgetEshopProjectLoadTestAganistServer() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetEshopProjectLoadTestAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.LoadTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJQueryMobileWidgetEshopProjectLoadTestAganistWebservices() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetEshopProjectLoadTestAganistWebservices()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.LoadTestForAganistWebService(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJQueryMobileWidgetEshopProjectCodeSourceJs() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetEshopProjectCodeSourceJs()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.codeValidationSource(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testJQueryMobileWidgetEshopProjectCodeSourceJava() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetEshopProjectCodeSourceJava()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.codeValidationSource(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testJQueryMobileWidgetEshopProjectCodeSourceHtml() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetEshopProjectCodeSourceHtml()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.codeValidationSource(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testJQueryMobileWidgetEshopProjectCodeSourceJsf() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetEshopProjectCodeSourceJsf()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.codeValidationSource(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testJQueryMobileWidgetEshopProjectCodeFunctional() 
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJQueryMobileWidgetEshopProjectCodeFunctional()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			webBaseScreen.codeValidationFunctional(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	
	@AfterTest
	public void tearDown() {
		webBaseScreen.closeBrowser();
	}
}
