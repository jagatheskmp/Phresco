package com.photon.phresco.MobileLayer;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.MobLayer.MobBaseScreen;
import com.photon.phresco.uiconstants.IPhoneLibraryConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class IPhoneLibraryTest {
	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrsConst;
	private phresco_env_config phrscEnv;
	private MobBaseScreen mobBaseScreen;
	private IPhoneLibraryConstantsXml iPhoneLibrary;
	private String methodName;

	@Parameters(value = {"browser","platform"})
	@BeforeTest
	public void setUp(String browser, String platform) throws Exception
	{
		String selectedBrowser = browser;
		String selectedPlatform = platform;
		phrscEnv = new phresco_env_config();
		phrsConst = new PhrescoUiConstantsXml();
		iPhoneLibrary = new IPhoneLibraryConstantsXml();
		userInfo = new UserInfoConstants();
		phrscData = new PhrescoFrameworkData();

		try {
			String applicationURL = phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT + "/";
			mobBaseScreen = new MobBaseScreen(selectedBrowser,selectedPlatform,applicationURL, phrscEnv.CONTEXT,phrscEnv,phrsConst,userInfo,phrscData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testiPhoneLibarayProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneLibarayProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.loginPage(methodName);
			mobBaseScreen.iPhoneLibraryCreate(methodName, iPhoneLibrary);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testiPhoneLibraryProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneLibraryProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneLibraryProjectEditApplication(methodName, iPhoneLibrary);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testiPhoneLibraryProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneLibraryProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneLibraryProjectAddFeature(methodName, iPhoneLibrary);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testiPhoneLibraryProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testiPhoneLibraryProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.iPhoneLibraryProjectBuild(methodName, iPhoneLibrary);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@AfterTest
	public void tearDown() {
		mobBaseScreen.closeBrowser();
	}
}


