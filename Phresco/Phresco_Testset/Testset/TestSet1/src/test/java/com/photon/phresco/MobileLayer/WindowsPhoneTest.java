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
import com.photon.phresco.uiconstants.WindowsPhoneConstantsXml;
import com.photon.phresco.uiconstants.phresco_env_config;

public class WindowsPhoneTest {

	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrsConst;
	private phresco_env_config phrscEnv;
	private MobBaseScreen mobBaseScreen;
	private WindowsPhoneConstantsXml windowsPhone;
	private String methodName;

	@Parameters(value = {"browser","platform"})
	@BeforeTest
	public  void setUp(String browser, String platform) throws Exception
	{
		String selectedBrowser = browser;
		String selectedPlatform = platform;
		phrscEnv=new phresco_env_config();
		phrsConst=new PhrescoUiConstantsXml();
		userInfo=new UserInfoConstants();
		phrscData=new PhrescoFrameworkData();
		windowsPhone=new WindowsPhoneConstantsXml();
		try {
			String applicationURL = phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT	+ "/";
			mobBaseScreen = new MobBaseScreen(selectedBrowser,selectedPlatform,applicationURL,phrscEnv.CONTEXT,phrscEnv,phrsConst,userInfo,phrscData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testWindowsPhoneProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testWindowsPhoneProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.loginPage(methodName);
			mobBaseScreen.windowsPhoneProjectCreate(methodName, windowsPhone);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testWindowsPhoneProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testWindowsPhoneProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.windowsPhoneProjectEditApplication(methodName, windowsPhone);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testWindowsPhoneProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testWindowsPhoneProjectAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.windowsPhoneProjectAddFeature(methodName, windowsPhone);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testWindowsPhoneProjectBuild()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testWindowsPhoneProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.windowsPhoneProjectBuild(methodName, windowsPhone);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test
	public void testWindowsPhoneProjectDeploy()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testWindowsPhoneProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			mobBaseScreen.windowsPhoneProjectDeploy(methodName, windowsPhone);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
	
	@AfterTest
	public void tearDown() {
		mobBaseScreen.closeBrowser();
	}
}





