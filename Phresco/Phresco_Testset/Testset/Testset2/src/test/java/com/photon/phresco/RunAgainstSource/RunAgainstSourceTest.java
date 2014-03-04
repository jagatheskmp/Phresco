package com.photon.phresco.RunAgainstSource;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.ImportApplicationSvnandGithub.ImportApplicationBaseScreen;
import com.photon.phresco.Screens.RunAgainstSourceScreen.RunAgainstSourceBaseScreen;
import com.photon.phresco.preconditions.CreateDbsql;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.RunAgainstSourceConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;

public class RunAgainstSourceTest {

	private UserInfoConstants userInfo;
	private PhrescoFrameworkData phrscData;
	private PhrescoUiConstantsXml phrscUi;
	private phresco_env_config phrscEnv;
	private RunAgainstSourceBaseScreen baseScreen;
	private CreateDbsql createdb;
	private RunAgainstSourceConstantsXml runAgainstSource;
	private String methodName;

	@Parameters(value = {"browser","platform"})
	@BeforeTest
	public void setUp(String browser, String platform) throws Exception
	{
		String selectedBrowser = browser;
		String selectedPlatform = platform;
		phrscEnv = new phresco_env_config();
		phrscUi = new PhrescoUiConstantsXml();
		userInfo = new UserInfoConstants();
		phrscData = new PhrescoFrameworkData();
		runAgainstSource = new RunAgainstSourceConstantsXml();
		createdb = new CreateDbsql();
		try {
			String applicationURL  =  phrscEnv.PROTOCOL + "://" + phrscEnv.HOST + ":" + phrscEnv.PORT + "/";
			baseScreen  =  new RunAgainstSourceBaseScreen(selectedBrowser,selectedPlatform,applicationURL, phrscEnv.CONTEXT,phrscEnv,phrscUi,userInfo,phrscData);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	@Test
	public void testNodeJsWebserviceHelloWorldProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsWebserviceHelloWorldProjectCreation()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			baseScreen.loginPage(methodName);
			baseScreen.NodeJsWebserviceProjectHelloWorldCreate(methodName, runAgainstSource);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}


	@Test
	public void testNodeJsWebserviceHelloWorldProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testNodeJsWebserviceHelloWorldProjectEdit()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.NodeJsWebserviceProjectHelloWorldEditApplication(methodName, runAgainstSource);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testNodeJsWebserviceHelloWorldProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testNodeJsWebserviceEshopdProjectAddFeature()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();


			baseScreen.NodeJsWebserviceProjectHelloWorldAddFeature(methodName, runAgainstSource);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testNodeJsHelloWorldProjectConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testNodeJsHelloWorldProjectConfigurationServer()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.NodeJsAddConfigurationServer(methodName, runAgainstSource);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testNodeJsHelloWorldRunAgainstSource()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("---------testNodeJsHelloWorldRunAgainstSource()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			baseScreen.appLayerRunAgainstSource(methodName);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testNodeJsWebserviceEshopProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testNodeJsWebserviceEshopProjectCreation()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.NodeJsWebserviceProjectEshopCreate(methodName, runAgainstSource);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}


	@Test
	public void testNodeJsWebserviceEshopProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testNodeJsWebserviceEshopProjectEdit()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();


			baseScreen.NodeJsWebserviceProjectEshopEditApplication(methodName, runAgainstSource);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testNodeJsWebserviceEshopProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testNodeJsWebserviceEshopdProjectAddFeature()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();


			baseScreen.NodeJsWebserviceProjectEshopAddFeature(methodName, runAgainstSource);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testNodeJsEshopdProjectConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testNodeJsEshopdProjectConfigurationServer()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.NodeJsAddConfigurationServer(methodName, runAgainstSource);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testNodeJsEshopRunDatabase()
	throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testNodeJsEshopRunDatabase()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			createdb.CreateDatabase(methodName)	;
			baseScreen.NodeJsAddConfigurationDatabase(methodName, runAgainstSource);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testNodeJsEshopRunAgainstSource()
	throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testNodeJsEshopRunAgainstSource()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();


			baseScreen.appLayerRunAgainstSource(methodName);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}



	@Test
	public void testJavaWebserviceHelloWorldProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testJavaWebserviceHelloWorldProjectCreation()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.javaWebserviceProjectHelloWorldCreate(methodName, runAgainstSource);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}


	@Test
	public void testjavaWebServiceHelloWorldProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testjavaWebServiceHelloWorldProjectEdit()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.javaWebserviceProjectHelloWorldEditApplication(methodName, runAgainstSource);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testjavaWebServiceHelloWorldProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testjavaWebServiceHelloWorldProjectAddFeature()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.javaWebserviceProjectHelloWorldAddFeature(methodName, runAgainstSource);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testjavaWebServiceHelloWorldAddServerConfiguration()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testjavaWebServiceHelloWorldAddServerConfiguration()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.javaWebserviceAddConfigurationServer(methodName, runAgainstSource);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}


	@Test
	public void testJavaWebserviceHelloWorldRunAgainstSource()
	throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testJavaWebserviceHelloWorldRunAgainstSource()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();



			baseScreen.appLayerRunAgainstSource(methodName);
			Thread.sleep(100000);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testJavaWebserviceEshopProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testJavaWebserviceEshopProjectCreation()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.javaWebserviceProjectEshopCreate(methodName, runAgainstSource);
			Thread.sleep(10000);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}


	@Test
	public void testJavaWebserviceEshopProjectEdit()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testJavaWebserviceEshopProjectEdit()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();


			baseScreen.javaWebserviceProjectEshopEditApplication(methodName, runAgainstSource);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}


	@Test
	public void testjavaWebServiceEshopProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testjavaWebServiceEshopProjectAddFeature()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.javaWebserviceProjectEshopAddFeature(methodName, runAgainstSource);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testjavaWebServiceEshopAddServerConfiguration()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testjavaWebServiceEshopAddServerConfiguration()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.javaWebserviceAddConfigurationServer(methodName, runAgainstSource);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testjavaWebServiceEshopRunDatabase()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testjavaWebServiceEshopRunDatabase()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			createdb.CreateDatabase(methodName);
			baseScreen.javaWebserviceAddConfigurationDatabase(methodName, runAgainstSource);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}


	@Test
	public void testJavaWebserviceEshopRunAgainstSource()
	throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testJavaWebserviceEshopRunAgainstSource()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.appLayerRunAgainstSource(methodName);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testMultiYuiWidgetHelloWorldProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testMultiYuiWidgetHelloWorldProjectCreation()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.loginPage(methodName);
			baseScreen.multiYuiWidgetProjectHelloWorldCreate(methodName, runAgainstSource);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testMultiYuiWidgetHelloWorldProjectEditApplication()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testMultiYuiWidgetHelloWorldProjectEditApplication()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.multiYuiWidgetProjectHelloWorldEditApplication(methodName, runAgainstSource);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testMultiYuiWidgetHelloWorldProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testMultiYuiWidgetHelloWorldProjectAddFeature()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.multiYuiWidgetProjectHelloWorldAddFeature(methodName, runAgainstSource);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testMultiYuiWidgetProjectHelloWorldAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testMultiYuiWidgetProjectHelloWorldAddConfigurationServer()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.multiYuiWidgetProjectAddConfigurationServer(methodName, runAgainstSource);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	/*@Test
	public void testMultiYuiWidgetHelloWorldProjectRunAgainstSource()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testMultiYuiWidgetHelloWorldProjectRunAgainstSource()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.webLayerRunAgainstSource(methodName);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}*/

	@Test
	public void testMultiYuiWidgetProjectEshopCreate()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testMultiYuiWidgetProjectEshopCreate()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			baseScreen.loginPage(methodName);
			baseScreen.multiYuiWidgetProjectEshopCreate(methodName, runAgainstSource);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}


	@Test
	public void testMultiYuiWidgetEshopProjectEditApplication()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testMultiYuiWidgetEshopProjectEditApplication()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.multiYuiWidgetProjectEshopEditApplication(methodName, runAgainstSource);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}


	@Test
	public void testMultiYuiWidgetProjectEshopAddFeature()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testMultiYuiWidgetProjectEshopAddFeature()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.multiYuiWidgetProjectEshopAddFeature(methodName, runAgainstSource);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testMultiYuiWidgetProjectEshopAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testMultiYuiWidgetProjectEshopAddConfigurationServer()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.multiYuiWidgetProjectAddConfigurationServer(methodName, runAgainstSource);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}


	@Test
	public void testMultiYuiWidgetProjectEshopAddConfigurationWebservice()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testMultiYuiWidgetProjectEshopAddConfigurationWebservice()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.multiYuiWidgetProjectAddConfigurationWebService(methodName, runAgainstSource);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}


	/*@Test
	public void testMultiYuiWidgetProjectEshopRunAgainstSource()
	throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testMultiYuiWidgetProjectEshopRunAgainstSource()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.webLayerRunAgainstSource(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
*/


	@Test
	public void testYuiMobileWidgetHelloWorldProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testYuiMobileWidgetHelloWorldProjectCreation()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.loginPage(methodName);
			baseScreen.yuiMobileWidgetProjectHelloWorldCreate(methodName, runAgainstSource);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testYuiMobileWidgetHelloWorldProjectEditApplication()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testYuiMobileWidgetHelloWorldProjectEditApplication()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.yuiMobileWidgetProjectHelloWorldEditApplication(methodName, runAgainstSource);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testYuiMobileWidgetHelloWorldProjectAddFeature()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testYuiMobileWidgetHelloWorldProjectAddFeature()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.yuiMobileWidgetProjectHelloWorldAddFeature(methodName, runAgainstSource);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testYuiMobileWidgetProjectHelloWorldAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testYuiMobileWidgetProjectHelloWorldAddConfigurationServer()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.yuiMobileWidgetProjectAddConfigurationServer(methodName, runAgainstSource);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	/*@Test
	public void testYuiMobileWidgetHelloWorldProjectRunAgainstSource()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testYuiMobileWidgetHelloWorldProjectRunAgainstSource()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.webLayerRunAgainstSource(methodName);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}*/


	@Test
	public void testYuiMobileWidgetEshopProjectCreation()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testYuiMobileWidgetEshopProjectCreation()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.yuiMobileWidgetProjectEshopCreate(methodName, runAgainstSource);

		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testYuiMobileWidgetEshopProjectEditApplication()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testYuiMobileWidgetEshopProjectEditApplication()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.yuiMobileWidgetProjectEshopEditApplication(methodName, runAgainstSource);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testYuiMobileWidgetProjectEshopAddFeature()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testYuiMobileWidgetProjectEshopAddFeature()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.yuiMobileWidgetProjectEshopAddFeature(methodName, runAgainstSource);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testYuiMobileWidgetProjectEshopAddConfigurationServer()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testYuiMobileWidgetProjectEshopAddConfigurationServer()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.yuiMobileWidgetProjectAddConfigurationServer(methodName, runAgainstSource);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testYuiMobileWidgetProjectEshopAddConfigurationWebservice()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testYuiMobileWidgetProjectEshopAddConfigurationWebservice()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.yuiMobileWidgetProjectAddConfigurationWebService(methodName, runAgainstSource);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	/*@Test
	public void testYuiMobileWidgetProjectEshoprunAgainstSource()
	throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testYuiMobileWidgetProjectEshoprunAgainstSource()-------------");
			methodName  =  Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			baseScreen.webLayerRunAgainstSource(methodName);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
*/



	@AfterTest
	public void tearDown() {
		baseScreen.closeBrowser();
	}



}

