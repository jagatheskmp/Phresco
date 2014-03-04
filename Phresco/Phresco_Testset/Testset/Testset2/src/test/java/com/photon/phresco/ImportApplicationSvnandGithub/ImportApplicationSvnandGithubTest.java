package com.photon.phresco.ImportApplicationSvnandGithub;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.CloneConfiguration.CloneConfigurationBaseScreen;
import com.photon.phresco.Screens.ImportApplicationSvnandGithub.ImportApplicationBaseScreen;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.ImportApplicationConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;



public class ImportApplicationSvnandGithubTest {

	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrscUi;
	private phresco_env_config phrscEnv;
	private ImportApplicationBaseScreen baseScreen;
	private ImportApplicationConstantsXml importApp;
	private String methodName;

	@Parameters(value  =  {"browser","platform"})
	@BeforeTest
	public void setUp(String browser, String platform) throws Exception
	{
		String selectedBrowser  =  browser;
		String selectedPlatform  =  platform;
		phrscEnv = new phresco_env_config();
		phrscUi = new PhrescoUiConstantsXml();
		userInfo = new UserInfoConstants();
		phrscData = new PhrescoFrameworkData();
		importApp = new ImportApplicationConstantsXml();
		

		try {
			String applicationURL = phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT + "/";
			baseScreen = new ImportApplicationBaseScreen(selectedBrowser,selectedPlatform,applicationURL, phrscEnv.CONTEXT,phrscEnv,phrscUi,userInfo,phrscData);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testImportApplicationSvnHeadRevisionWithCredential()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testImportApplicationSvnHeadRevisionWithCredential()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			baseScreen.loginPage(methodName);
			baseScreen.importApplicationHeadRevisionWithCredential(methodName, importApp);
		
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testImportApplicationSvnHeadRevisionWithOtherCredential()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testImportApplicationSvnHeadRevisionWithOtherCredential()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			baseScreen.importApplicationHeadRevisionWithOtherCredential(methodName, importApp);
		
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	@Test
	public void testImportApplicationSvnRevisionWithCredential()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testImportApplicationSvnRevisionWithCredential()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			
			baseScreen.importApplicationRevisionWithCredential(methodName, importApp);
		
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testImportApplicationSvnRevisionWithOtherCredential()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testImportApplicationSvnRevisionWithOtherCredential()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			baseScreen.importApplicationRevisionWithOtherCredential(methodName, importApp);
		
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testImportApplicationGithub()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testImportApplicationGithub()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			
			baseScreen.importApplicationGithub(methodName, importApp);
		
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	

	
		@AfterTest
	public void tearDown() {
		baseScreen.closeBrowser();
	}



}



