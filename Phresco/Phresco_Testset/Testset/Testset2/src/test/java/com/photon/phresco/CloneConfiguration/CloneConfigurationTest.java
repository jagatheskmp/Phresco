package com.photon.phresco.CloneConfiguration;


import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.ArchetypeScreen.ArchetypeBaseScreen;
import com.photon.phresco.Screens.CloneConfiguration.CloneConfigurationBaseScreen;
import com.photon.phresco.preconditions.CreateDbsql;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.CloneConfigurationConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;


public class CloneConfigurationTest {

	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrscUi;
	private phresco_env_config phrscEnv;
	private CloneConfigurationBaseScreen baseScreen;
	private CreateDbsql createdb;
	private CloneConfigurationConstantsXml cloneConst;
	private String methodName;

	@Parameters(value = {"browser","platform"})
	@BeforeTest
	public void setUp(String browser, String platform) throws Exception
	{
		String selectedBrowser = browser;
		String selectedPlatform = platform;
		phrscEnv = new phresco_env_config();
		phrscUi = new PhrescoUiConstantsXml();
		userInfo = new UserInfoConstants();
		phrscData = new PhrescoFrameworkData();
		cloneConst = new CloneConfigurationConstantsXml();
		createdb = new CreateDbsql();

		try {
			String applicationURL = phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT + "/";
			baseScreen = new CloneConfigurationBaseScreen(selectedBrowser,selectedPlatform,applicationURL, phrscEnv.CONTEXT,phrscEnv,phrscUi,userInfo,phrscData);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testPhpHelloWorldProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testPhpHelloWorldProjectCreation()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                      .getMethodName();
			baseScreen.loginPage(methodName);
			baseScreen.phpProjectHelloWorldCreate(methodName,cloneConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testPhpHelloWorldProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testPhpHelloWorldProjectEdit()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                      .getMethodName();
			baseScreen.phpProjectHelloWorldEditApplication(methodName,cloneConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testPhpHelloWorldProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testPhpProjectHelloWorldProjectAddFeature()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                      .getMethodName();
			baseScreen.phpProjectHelloWorldAddFeature(methodName,cloneConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testphpHelloWorldProjectAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testphpHelloWorldProjectAddConfigurationServer()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                      .getMethodName();
			baseScreen.phpProjectAddConfigurationServer(methodName,cloneConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testphpHelloWorldDatabaseClone()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testphpHelloWorldDatabaseClone()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                      .getMethodName();
			createdb.CreateDatabase(methodName);
			baseScreen.phpProjectAddConfigurationDatabase(methodName,cloneConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testphpHelloWorldProjectCloneConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testphpHelloWorldProjectCloneConfigurationServer()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                      .getMethodName();
			baseScreen.phpBlogProjectCloneConfigurationServer(methodName, cloneConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testphpHelloWorldProjectCloneConfigurationDatabase()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testphpHelloWorldProjectCloneConfigurationDatabase()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                      .getMethodName();
			baseScreen.phpBlogProjectCloneConfigurationDatabase(methodName, cloneConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testphpHelloWorldProjectCloneBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testphpHelloWorldProjectCloneBuild()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                      .getMethodName();
			baseScreen.appLayerBuildTabClone(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testphpHelloWorldProjectCloneDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testphpHelloWorldProjectCloneDeploy()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                      .getMethodName();
			baseScreen.appLayerDeploy(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@AfterTest
	public void tearDown() {
		baseScreen.closeBrowser();
	}
}

