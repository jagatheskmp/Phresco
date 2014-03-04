package com.photon.phresco.Environment;
import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.Environment.Environment;
import com.photon.phresco.preconditions.CreateDbsql;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.EnvironmentConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;


public class EnvironmentTest {

	private static String selectedBrowser;
	private static UserInfoConstants userInfo;
	private static PhrescoFrameworkData phrscData;
	private static PhrescoUiConstantsXml phrscUi;
	private static phresco_env_config phrscEnv;
	private static Environment environmentbasescreen;	
	private static EnvironmentConstantsXml environmentcreate;
	private static CreateDbsql createDb;

	private String methodName;

	@BeforeTest
	public static void setUp() throws Exception{
	
		phrscEnv=new phresco_env_config();
		phrscUi=new PhrescoUiConstantsXml();
		userInfo=new UserInfoConstants();
		phrscData=new PhrescoFrameworkData();
		environmentcreate=new EnvironmentConstantsXml();
		createDb=new CreateDbsql();
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
			environmentbasescreen = new Environment(selectedBrowser, applicationURL,
					phrscEnv.CONTEXT,phrscEnv,phrscUi,userInfo,phrscData);
		} catch (ScreenException e) {

			e.printStackTrace();
		}


	}
	@Test
	public void testEnvironmentCreation()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testEnvironmentCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			environmentbasescreen.loginPage(methodName);
			environmentbasescreen.Environmentcreation(methodName,environmentcreate);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	@Test
	public void testGlobalSettigsConfiguration()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testGlobalSettigsConfiguration()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			environmentbasescreen.GlobalSettingsConfiguration(methodName,environmentcreate);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testEnvironmentDatabase()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testEnvironmentDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			createDb.CreateDatabase(methodName);
			
} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
@AfterTest
	public static void tearDown() {
		environmentbasescreen.closeBrowser();
	}



}

