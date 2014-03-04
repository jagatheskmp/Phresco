package com.photon.phresco.MobileLayer;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.MobLayer.MobBaseScreen;
import com.photon.phresco.uiconstants.AndroidLibraryConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class AndroidLibraryTest {

	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrsConst;
	private phresco_env_config phrscEnv;
	private MobBaseScreen mobBaseScreen;
	private AndroidLibraryConstantsXml androidLibrary;
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
		androidLibrary = new AndroidLibraryConstantsXml();

		try {
			String applicationURL = phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT + "/";
			mobBaseScreen = new MobBaseScreen(selectedBrowser,selectedPlatform,applicationURL,phrscEnv.CONTEXT,phrscEnv,phrsConst,userInfo,phrscData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAndroidLibarayProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testandroidLibarayProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.loginPage(methodName);
			mobBaseScreen.androidLibraryCreate(methodName, androidLibrary);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testAndroidLibraryProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testandroidHybridEshopProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.androidLibraryProjectEditApplication(methodName, androidLibrary);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testAndroidLibraryProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAndroidLibraryProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.androidLibraryProjectAddFeature(methodName, androidLibrary);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testAndroidLibraryProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testAndroidLibraryProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.androidLibraryProjectBuild(methodName, androidLibrary);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@AfterTest
	public void tearDown() {
		mobBaseScreen.closeBrowser();
	}

}







