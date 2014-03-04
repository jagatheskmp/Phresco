package com.photon.phresco.ApplicationLayer;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.AppLayer.AppBaseScreen;
import com.photon.phresco.uiconstants.JavaStandaloneConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class JavaStandAloneTest {

	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrsConst;
	private phresco_env_config phrscEnv;
	private AppBaseScreen appBaseScreen;
	private JavaStandaloneConstantsXml JvaStdAloneConst;
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
		JvaStdAloneConst = new JavaStandaloneConstantsXml();

		try {
			String applicationURL = phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT + "/";
			appBaseScreen = new AppBaseScreen(selectedBrowser,selectedPlatform,applicationURL,phrscEnv.CONTEXT,phrscEnv,phrsConst,userInfo,phrscData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Test
	public void testJavaStandAloneProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaStandAloneProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.loginPage(methodName);
			appBaseScreen.javaStandAloneProjectHelloWorldCreate(methodName, JvaStdAloneConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJavaStandAloneProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaStandAloneProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.javaStandaloneProjectHelloWorldEditApplication(methodName, JvaStdAloneConst);	
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJavaStandAloneProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaStandAloneProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.javaStandaloneProjectHelloWorldAddFeature(methodName, JvaStdAloneConst);	
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJavaStandAloneProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaStandAloneProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.javaStandaloneProjectHelloWorldBuildTab(methodName,JvaStdAloneConst);
			appBaseScreen.appLayerBuildTab(methodName);	
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testJavaStandAloneProjectUnitTest()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testJavaStandAloneProjectUnitTest()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.UnittestVerification(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@AfterTest
	public void tearDown() {
		appBaseScreen.closeBrowser();
	}
}







