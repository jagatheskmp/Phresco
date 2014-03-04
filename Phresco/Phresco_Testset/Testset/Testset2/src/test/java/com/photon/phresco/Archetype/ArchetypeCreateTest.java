package com.photon.phresco.Archetype;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.ArchetypeScreen.ArchetypeBaseScreen;
import com.photon.phresco.preconditions.CreateDbsql;
import com.photon.phresco.uiconstants.ArchetypeCreationXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;


public class ArchetypeCreateTest {

	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrscUi;
	private phresco_env_config phrscEnv;
	private ArchetypeBaseScreen archetypeBasescreen;	
	private ArchetypeCreationXml archetypeCreate;
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
		archetypeCreate = new ArchetypeCreationXml();
		try {
			String applicationURL = phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT + "/";
			archetypeBasescreen = new ArchetypeBaseScreen(selectedBrowser,selectedPlatform,applicationURL, phrscEnv.CONTEXT,phrscEnv,phrscUi,userInfo,phrscData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testArchetypeCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testArchetypeCreation()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                      .getMethodName();
			archetypeBasescreen.loginPage(methodName);
			archetypeBasescreen.ArchetypeCreate(methodName, archetypeCreate);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testNodeJsWebserviceHelloWorldProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsWebserviceHelloWorldProjectEdit()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                      .getMethodName();
			archetypeBasescreen.NodeJsWebserviceProjectHelloWorldEditApplication(methodName, archetypeCreate);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testNodeJsWebserviceHelloWorldProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsWebserviceEshopdProjectAddFeature()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                      .getMethodName();
			archetypeBasescreen.NodeJsWebserviceProjectHelloWorldAddFeature(methodName, archetypeCreate);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testNodeJsHelloWorldProjectConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsHelloWorlddProjectConfiguration()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                      .getMethodName();
			archetypeBasescreen.NodeJsAddConfigurationServer(methodName, archetypeCreate);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testNodeJsHelloWorldRunAgainstSource()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsHelloWorldRunAgainstSource()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                      .getMethodName();
			archetypeBasescreen.appLayerRunAgainstSource(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetHelloWorldProjectEditApplication()
	throws InterruptedException, IOException, Exception {
		try {
			System.out
			.println("---------testMultiYuiWidgetHelloWorldProjectEditApplication()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                      .getMethodName();
			archetypeBasescreen.loginPage(methodName);
			archetypeBasescreen.MultiYuiWidgetProjectHelloWorldEditApplication(methodName, archetypeCreate);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testMultiYuiWidgetHelloWorldProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testMultiYuiWidgetHelloWorldProjectAddFeature()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                      .getMethodName();

			archetypeBasescreen.multiYuiWidgetProjectHelloWorldAddFeature(methodName, archetypeCreate);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testMultiYuiWidgetProjectHelloWorldAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetProjectHelloWorldAddConfigurationServer()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                      .getMethodName();
			archetypeBasescreen.multiYuiWidgetProjectAddConfigurationServer(methodName, archetypeCreate);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetProjectBuild()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                      .getMethodName();
			archetypeBasescreen.WidgetProjectBuild(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testMultiYuiWidgetProjectdeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testMultiYuiWidgetProjectdeploy()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                      .getMethodName();
			archetypeBasescreen.WidgetProjectDeploy(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAndroidNativeHelloWorldProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAndroidNativeHelloWorldProjectEdit()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                      .getMethodName();
			archetypeBasescreen.androidNativeHelloWorldProjectEditApplication(methodName, archetypeCreate);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAndroidNativeHelloWorldProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAndroidNativeHelloWorldProjectAddFeature()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                      .getMethodName();
			archetypeBasescreen.androidNativeHelloWorldProjectAddFeature(methodName, archetypeCreate);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAndroidNativeHelloWorldBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAndroidNativeHelloWorldBuild()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                      .getMethodName();
			archetypeBasescreen.androidNativeProjectBuild(methodName, archetypeCreate);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAndroidNativeHelloWorldDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAndroidNativeHelloWorldDeploy()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                      .getMethodName();
			archetypeBasescreen.androidNativeProjectDeploy(methodName, archetypeCreate);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@AfterTest
	public void tearDown() {
		archetypeBasescreen.closeBrowser();
	}



}

