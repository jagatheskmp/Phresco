package com.photon.phresco.ApplicationLayer;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.AppLayer.AppBaseScreen;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.JavaStandaloneConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class JavaStandAloneTest {

		private static String selectedBrowser;
		private static UserInfoConstants userInfo;
		private static PhrescoFrameworkData phrscData;
		private static PhrescoUiConstantsXml phrscUi;
		private static phresco_env_config phrscEnv;
		private static AppBaseScreen baseScreen;
		private static JavaStandaloneConstantsXml JvaStdAloneConst;
		private String methodName;


		@BeforeTest
		public static void setUp() throws Exception
		{
		
			phrscEnv=new phresco_env_config();
			phrscUi=new PhrescoUiConstantsXml();
			userInfo=new UserInfoConstants();
			phrscData=new PhrescoFrameworkData();
			JvaStdAloneConst=new JavaStandaloneConstantsXml();
			
			try {
				launchingBrowser();
			} catch (Exception e) {

				e.printStackTrace();
			}

		}

		public static void launchingBrowser() throws Exception {

			String applicationURL = phrscEnv.PROTOCOL + "://"
					+ phrscEnv.HOST + ":" + phrscEnv.PORT
					+ "/";
			selectedBrowser = phrscEnv.BROWSER;
			try {
				baseScreen = new AppBaseScreen(selectedBrowser, applicationURL,
						phrscEnv.CONTEXT,phrscEnv,phrscUi,userInfo,phrscData);
			} catch (ScreenException e) {

				e.printStackTrace();
			}


		}
		@Test
		public void testJavaStandAloneProjectCreation()
				throws InterruptedException, IOException, Exception {
			try {
				
				System.out
				.println("---------testJavaStandAloneProjectCreation()-------------");
				methodName = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
				
				baseScreen.loginPage(methodName);
				baseScreen.javaStandAloneProjectHelloWorldCreate(methodName, JvaStdAloneConst);
				
			} catch (Exception t) {
				t.printStackTrace();

			}
		}
	
		@Test
		public void testJavaStandAloneProjectEditApplication()
				throws InterruptedException, IOException, Exception {
			try {
		
		System.out.println("---------testJavaStandAloneProjectEditApplication()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		baseScreen.javaStandaloneProjectHelloWorldEditApplication(methodName, JvaStdAloneConst);	
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
		
		baseScreen.javaStandaloneProjectHelloWorldAddFeature(methodName, JvaStdAloneConst);	
	} catch (Exception t) {
		t.printStackTrace();

	}
}
		@Test
		public void testJavaStandAloneProjectBuildTab()
				throws InterruptedException, IOException, Exception {
			try {
		
		System.out.println("---------testJavaStandAloneProjectBuildTab()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		baseScreen.javaStandaloneProjectHelloWorldBuildTab(methodName,JvaStdAloneConst);
		baseScreen.appLayerBuildTab(methodName);	
		Thread.sleep(20000);
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

				baseScreen.UnittestVerification(methodName);

			} catch (Exception t) {
				t.printStackTrace();

			}
		}

		@AfterTest
		public static void tearDown() {
			baseScreen.closeBrowser();
		}

	}
		






