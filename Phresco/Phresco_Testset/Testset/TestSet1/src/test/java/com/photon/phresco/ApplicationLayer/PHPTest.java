package com.photon.phresco.ApplicationLayer;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.AppLayer.AppBaseScreen;
import com.photon.phresco.preconditions.CreateDbsql;
import com.photon.phresco.uiconstants.PhpConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class PHPTest {

	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrsConst;
	private phresco_env_config phrscEnv;
	private AppBaseScreen appBaseScreen;
	private CreateDbsql createdb;
	private PhpConstantsXml phpConst;
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
		phpConst = new PhpConstantsXml();
		createdb = new CreateDbsql();

		try {
			String applicationURL = phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT	+ "/";
			appBaseScreen = new AppBaseScreen(selectedBrowser,selectedPlatform,applicationURL, phrscEnv.CONTEXT,phrscEnv,phrsConst,userInfo,phrscData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testPhpHelloWorldProjectCreation() throws InterruptedException,
	IOException, Exception {
		try {
			System.out.println("---------testPhpHelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.loginPage(methodName);
			appBaseScreen.phpProjectHelloWorldCreate(methodName, phpConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testPhpHelloWorldProjectEdit() throws InterruptedException,
	IOException, Exception {
		try {
			System.out.println("---------testPhpHelloWorldProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.phpProjectHelloWorldEditApplication(methodName, phpConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testPhpHelloWorldProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testPhpHelloWorldProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.phpProjectHelloWorldAddFeature(methodName, phpConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testPhpBlogProjectCreation() throws InterruptedException,
	IOException, Exception {
		try {
			System.out.println("---------testPhpBlogProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.phpProjectBlogCreate(methodName, phpConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testPhpBlogProjectEdit() throws InterruptedException,
	IOException, Exception {
		try {
			System.out.println("---------testPhpBlogProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.phpProjectBlogEditApplication(methodName, phpConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testPhpBlogProjectAddFeature() throws InterruptedException,
	IOException, Exception {
		try {
			System.out.println("---------testPhpProjectBlogAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.phpProjectBlogAddFeature(methodName, phpConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testPhpHelloWorldProjectAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("--------testPhpHelloWorldProjectAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.phpProjectAddConfigurationServer(methodName, phpConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testPhpHelloWorldProjectDatabase() throws InterruptedException,
	IOException, Exception {
		try {
			System.out.println("---------testPhpHelloWorldProjectDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			createdb.CreateDatabase(methodName);
			appBaseScreen.phpProjectAddConfigurationDatabase(methodName, phpConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testPhpHelloworldProjectBuild() throws InterruptedException,
	IOException, Exception {
		try {
			System.out.println("---------testPhpHelloworldProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.appLayerBuildTab(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testPhpHelloworldProjectDeploy() throws InterruptedException,
	IOException, Exception {
		try {
			System.out.println("---------testPhpHelloworldProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.appLayerDeploy(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testPhpHelloWorldProjectUnitTest() throws InterruptedException,
	IOException, Exception {
		try {
			System.out.println("---------testPhpHelloWorldProjectUnitTest()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.UnittestVerification(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testPhpHelloWorldProjectPerformanceAganistServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testPhpHelloWorldProjectPerformanceAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.PerformanceTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testPhpHelloWorldProjectPerformanceAganistDatabase()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testPhpHelloWorldProjectPerformanceAganistDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.PerformanceTestForAganistDatabase(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testPhpHelloWorldProjectLoadTestAganistServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testPhpHelloWorldProjectLoadTestAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.LoadTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testPhpBlogProjectAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testPhpBlogProjectAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.phpProjectAddConfigurationServer(methodName, phpConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testPhpBlogProjectDatabase() throws InterruptedException,
	IOException, Exception {
		try {
			System.out.println("---------testPhpBlogProjectDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			createdb.CreateDatabase(methodName);
			appBaseScreen.phpProjectAddConfigurationDatabase(methodName, phpConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testphpBlogProjectBuild() throws InterruptedException,
	IOException, Exception {
		try {
			System.out.println("---------testphpBlogProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.appLayerBuildTab(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testphpBlogProjectDeploy() throws InterruptedException,
	IOException, Exception {
		try {
			System.out.println("---------testphpBlogProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.appLayerDeploy(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testPhpBlogProjectUnitTest() throws InterruptedException,
	IOException, Exception {
		try {
			System.out.println("---------testPhpBlogProjectUnitTest()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.UnittestVerification(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testPhpBlogProjectPerformanceAganistServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testPhpBlogProjectPerformanceAganistServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.PerformanceTestForAganistServer(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testPhpBlogProjectPerformanceAganistDatabase()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testPhpBlogPerformanceAganistDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.PerformanceTestForAganistDatabase(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testPhpBlogProjectLoadTestAganistServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testPhpBlogProjectLoadTestAganistServer()-------------");
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
