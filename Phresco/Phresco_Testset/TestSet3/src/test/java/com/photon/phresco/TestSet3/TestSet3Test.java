package com.photon.phresco.TestSet3;

import java.io.IOException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.photon.phresco.Screens.TestSet3.TestSet3;
import com.photon.phresco.preconditions.CreateDbsql;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.EnvironmentConstantsXml;
import com.photon.phresco.uiconstants.PhpConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.TestSet3Xml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;
public class TestSet3Test {

	private static String selectedBrowser;
	private static UserInfoConstants userInfo;
	private static PhrescoFrameworkData phrscData;
	private static PhrescoUiConstantsXml phrscUi;
	private static phresco_env_config phrscEnv;
	private static TestSet3 testsetbasescreen;	
	private static TestSet3Xml Testsetcreate;
	private static PhpConstantsXml phpConst;
	private static CreateDbsql createDb;
	private static EnvironmentConstantsXml envCons;

	private String methodName;

	@BeforeTest
	public static void setUp() throws Exception{
	
		phrscEnv=new phresco_env_config();
		phrscUi=new PhrescoUiConstantsXml();
		userInfo=new UserInfoConstants();
		phrscData=new PhrescoFrameworkData();
		Testsetcreate=new TestSet3Xml();
		phpConst=new PhpConstantsXml();
		createDb=new CreateDbsql();
		envCons=new EnvironmentConstantsXml();
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
			testsetbasescreen = new TestSet3(selectedBrowser, applicationURL,
					phrscEnv.CONTEXT,phrscEnv,phrscUi,userInfo,phrscData);
		} catch (ScreenException e) {

			e.printStackTrace();
		}


	}
	@Test
	public void testPhpHelloWorldProjectCreation()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testPhpHelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			testsetbasescreen.loginPage(methodName);
			testsetbasescreen.phpProjectHelloWorldCreate(methodName,phpConst);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testphpHelloWorldDatabase()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testphpHelloWorldDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			createDb.CreateDatabase(methodName);
			
} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testPhpHelloWorldProjectEdit()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testPhpHelloWorldProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			
			testsetbasescreen.phpProjectHelloWorldEditApplication(methodName,phpConst);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testPhpProjectHelloWorldAddFeature()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testPhpProjectHelloWorldAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			
			testsetbasescreen.phpProjectHelloWorldAddFeature(methodName,phpConst);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testphpProjectHelloWorldAddConfigurationServer()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testphpProjectHelloWorldAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			
			testsetbasescreen.phpProjectAddConfigurationServer(methodName,phpConst);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testphpHelloWorldDatabasecreation()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testphpHelloWorldDatabasecreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			
			testsetbasescreen.phpProjectAddConfigurationDatabase(methodName,phpConst);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testphpHelloworldProjectBuild()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testphpHelloworldProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			
			testsetbasescreen.appLayerBuildTab(methodName);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testQualitygroupsTooltips()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testQualitygroupsTooltips()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			
			testsetbasescreen.QualityTabToolTips(methodName);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testTemeChange_About_phresco()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testTemeChange_About_phresco()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
		
			testsetbasescreen.ThemeChange_About_phresco(methodName);
			testsetbasescreen.ThemeChange_Blue(methodName);
			testsetbasescreen.ThemeChange_Green(methodName);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testHelpTab_Verification()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testTemeChange_About_phresco()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			
			testsetbasescreen.Help_Tab_Verification(methodName);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testRootExpandDownloads()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------RootExpandForDownloadPage()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			testsetbasescreen.RootExpandForDownloadPage(methodName);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testReportTabVerification()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testReportTabVerification()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			
			testsetbasescreen.Report_Tab_Verification(methodName,Testsetcreate);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testEnvironmentAlretMessages()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testEnvironmentAlretMessages()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			
			testsetbasescreen.Environmentcreation(methodName,envCons);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testEnvironmentPopupAlretMessages()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testReportTabVerification()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			
			testsetbasescreen.EnvironmentcreationWarningMessage(methodName,envCons);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testProjectdeleteAlretMessages()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testProjectdeleteAlretMessages()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			
			testsetbasescreen.ProjectDeleteWarningMessages(methodName,envCons);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testProjectEditToolTipMessages()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testProjectEditToolTipMessages()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			
			testsetbasescreen.Project_Page_ToolTip_Verification(methodName);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testImportApplicationToolTipMessages()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testProjectEditToolTipMessages()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			
			testsetbasescreen.ImportApplicationTooltips(methodName);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testAddProjectPageWarniongMessages()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------AddProjectPageWarniongMessages()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			testsetbasescreen.AddProjectPageWarniongMessages(methodName);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
@AfterTest
	public static void tearDown() {
	testsetbasescreen.closeBrowser();
	}



}

