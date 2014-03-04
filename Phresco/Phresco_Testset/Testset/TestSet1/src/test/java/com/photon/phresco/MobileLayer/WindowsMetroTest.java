package com.photon.phresco.MobileLayer;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.MobLayer.MobBaseScreen;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.WindowsMetroConstantsXml;
import com.photon.phresco.uiconstants.phresco_env_config;

public class WindowsMetroTest {

	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrsConst;
	private phresco_env_config phrscEnv;
	private MobBaseScreen mobBaseScreen;
	private WindowsMetroConstantsXml windowsMetro;
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
		windowsMetro = new WindowsMetroConstantsXml();
		try {
			String applicationURL = phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT + "/";
			mobBaseScreen = new MobBaseScreen(selectedBrowser,selectedPlatform,applicationURL, phrscEnv.CONTEXT,phrscEnv,phrsConst,userInfo,phrscData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testWindowsMetroProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testWindowsMetroProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.loginPage(methodName);
			mobBaseScreen.windowsMetroProjectCreate(methodName, windowsMetro);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testWindowsMetroProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testWindowsMetroProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.windowsMetroProjectEditApplication(methodName, windowsMetro);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testWindowsMetroProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testWindowsMetroProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.windowsMetroProjectAddFeature(methodName, windowsMetro);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testWindowsMetroProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testWindowsMetroProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.windowsMetroProjectBuild(methodName, windowsMetro);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testWindowsMetroProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testWindowsMetroProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.windowsMetroProjectDeploy(methodName, windowsMetro);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@AfterTest
	public void tearDown() {
		mobBaseScreen.closeBrowser();
	}
}





