package com.photon.phresco.ApplicationLayer;
import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.AppLayer.AppBaseScreen;
import com.photon.phresco.uiconstants.AspDotNetConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class AspDotNetTest {

	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrsConst;
	private phresco_env_config phrscEnv;
	private AppBaseScreen appBaseScreen;
	private AspDotNetConstantsXml AspDotNetConst;
	private String methodName;

	@Parameters(value = {"browser","platform"})
	@BeforeTest
	public void setUp(String browser, String platform) throws Exception
	{
		String selectedBrowser  =  browser;
		String selectedPlatform  =  platform;
		phrscEnv = new phresco_env_config();
		phrsConst = new PhrescoUiConstantsXml();
		userInfo = new UserInfoConstants();
		phrscData = new PhrescoFrameworkData();
		AspDotNetConst = new AspDotNetConstantsXml();
		try {
			String applicationURL  =  phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT + "/";
			appBaseScreen  =  new AppBaseScreen(selectedBrowser,selectedPlatform,applicationURL,phrscEnv.CONTEXT,phrscEnv,phrsConst,userInfo,phrscData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAspDotNetHelloWorldProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testAspDotNetHelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			appBaseScreen.loginPage(methodName);
			appBaseScreen.AspDotNetProjectHelloWorldCreate(methodName, AspDotNetConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	} 

	@Test
	public void testAspDotNetHelloWorldProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAspDotNetHelloWorldProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.AspDotNetProjectHelloWorldEditApplication(methodName, AspDotNetConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAspDotNetHelloWorldProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAspDotNetHelloWorldProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.AspDotNetProjectHelloWorldAddFeature(methodName, AspDotNetConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAspDotNetHelloWorldProjectAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAspDotNetHelloWorldProjectAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.AspDotNetServerConfiguration(methodName, AspDotNetConst);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAspDotNetHelloWorldProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAspDotNetHelloWorldProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.appLayerBuildTab(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAspDotNetHelloWorldProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAspDotNetHelloWorldProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			appBaseScreen.appLayerDeployProject(methodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@AfterTest
	public void tearDown() {
		appBaseScreen.closeBrowser();
	}
}

