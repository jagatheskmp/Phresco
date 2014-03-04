package com.photon.phresco.ApplicationLayer;



import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.AppLayer.AppBaseScreen;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.PhpConstantsXml;
import com.photon.phresco.uiconstants.PhrescoFrameworkData;
import com.photon.phresco.uiconstants.PhrescoUiConstantsXml;
import com.photon.phresco.uiconstants.UserInfoConstants;
import com.photon.phresco.uiconstants.phresco_env_config;
import com.photon.phresco.preconditions.*;


public class PHPTest {

	private static String selectedBrowser;
	private static UserInfoConstants userInfo;
	private static PhrescoFrameworkData phrscData;
	private static PhrescoUiConstantsXml phrscUi;
	private static phresco_env_config phrscEnv;
	private static AppBaseScreen baseScreen;
	private static CreateDbsql createdb;
	private static PhpConstantsXml phpConst;
	private String methodName;

	@BeforeTest
	public static void setUp() throws Exception
	{
	
		phrscEnv=new phresco_env_config();
		phrscUi=new PhrescoUiConstantsXml();
		userInfo=new UserInfoConstants();
		phrscData=new PhrescoFrameworkData();
		phpConst=new PhpConstantsXml();
		createdb=new CreateDbsql();
		
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
	public void testPhpHelloWorldProjectCreation()
			throws InterruptedException, IOException, Exception {
		try {
			
			System.out
			.println("---------testPhpHelloWorldProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			baseScreen.loginPage(methodName);
			baseScreen.phpProjectHelloWorldCreate(methodName,phpConst);
			
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

			baseScreen.phpProjectHelloWorldEditApplication(methodName,phpConst);


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

			baseScreen.phpProjectHelloWorldAddFeature(methodName,phpConst);


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

			baseScreen.phpProjectAddConfigurationServer(methodName,phpConst);


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
			createdb.CreateDatabase(methodName);
			baseScreen.phpProjectAddConfigurationDatabase(methodName,phpConst);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	
	@Test
	public void testphpHelloworldProjectBuild()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testphpHelloworldProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.appLayerBuildTab(methodName);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testphpHelloworldProjectDeploy()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testphpHelloworldProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.appLayerDeploy(methodName);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testPhpHelloUnittest() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testPhpHelloUnittest()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();

		baseScreen.UnittestVerification(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
}
	
	@Test
	public void testPhpHelloPerformanceAganistServer() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testPhpHelloPerformanceAganistServer()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		
		baseScreen.PerformanceTestForAganistServer(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}

	@Test
	public void testPhpHelloPerformanceAganistDatabase() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testPhpHelloPerformanceAganistDatabase()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		
		baseScreen.PerformanceTestForAganistDatabase(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}
	
	
	@Test
	public void testPhpHelloLoadTestAganistServer() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testPhpHelloLoadTestAganistServer()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		
		baseScreen.LoadTestForAganistServer(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
	}
	
	@Test
	public void testPhpBlogProjectCreation()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testPhpBlogProjectCreation()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.phpProjectBlogCreate(methodName,phpConst);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}


	@Test
	public void testPhpBlogProjectEdit()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testPhpBlogProjectEdit()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.phpProjectBlogEditApplication(methodName,phpConst);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void testPhpProjectBlogAddFeature()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testPhpProjectBlogAddFeature()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.phpProjectBlogAddFeature(methodName,phpConst);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testphpProjectBlogAddConfigurationServer()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------testphpProjectBlogAddConfigurationServer()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.phpProjectAddConfigurationServer(methodName,phpConst);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testphpBlogDatabase()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testphpBlogDatabase()-------------");
			methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			createdb.CreateDatabase(methodName);
			baseScreen.phpProjectAddConfigurationDatabase(methodName,phpConst);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	@Test
	public void testphpBlogProjectBuild()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testphpBlogProjectBuild()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.appLayerBuildTab(methodName);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testphpBlogProjectDeploy()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testphpBlogProjectDeploy()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			baseScreen.appLayerDeploy(methodName);


		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	
	@Test
	public void testPhpBlogUnittest() 
		throws InterruptedException, IOException, Exception {
	try {

		System.out.println("---------testPhpBlogUnittest()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();

		baseScreen.UnittestVerification(methodName);


	} catch (Exception t) {
		t.printStackTrace();

	}
}

@Test
public void testPhpBlogPerformanceAganistServer() 
	throws InterruptedException, IOException, Exception {
try {

	System.out.println("---------testPhpBlogPerformanceAganistServer()-------------");
	methodName = Thread.currentThread().getStackTrace()[1]
			.getMethodName();
	
	
	baseScreen.PerformanceTestForAganistServer(methodName);


} catch (Exception t) {
	t.printStackTrace();

}
}

@Test
public void testPhpBlogPerformanceAganistDatabase() 
	throws InterruptedException, IOException, Exception {
try {

	System.out.println("---------testPhpBlogPerformanceAganistDatabase()-------------");
	methodName = Thread.currentThread().getStackTrace()[1]
			.getMethodName();
	
	
	baseScreen.PerformanceTestForAganistDatabase(methodName);


} catch (Exception t) {
	t.printStackTrace();

}
}

@Test
public void testPhpBlogLoadTestAganistServer() 
	throws InterruptedException, IOException, Exception {
try {

	System.out.println("---------testLoadTestAganistServer()-------------");
	methodName = Thread.currentThread().getStackTrace()[1]
			.getMethodName();
	
	
	baseScreen.LoadTestForAganistServer(methodName);


} catch (Exception t) {
	t.printStackTrace();

}
}
@Test
public void testprojectUpdation() 
	throws InterruptedException, IOException, Exception {
try {

	System.out.println("---------testprojectUpdation()-------------");
	methodName = Thread.currentThread().getStackTrace()[1]
			.getMethodName();
	
	
	baseScreen.PhpHellworldProjectUpdation(methodName,phpConst);


} catch (Exception t) {
	t.printStackTrace();

}
}


@Test
public void Phresco_videos() 
	throws InterruptedException, IOException, Exception {
try {

	System.out.println("---------Phresco_videos()-------------");
	methodName = Thread.currentThread().getStackTrace()[1]
			.getMethodName();
	

	baseScreen.PhrescoVideos(methodName);


} catch (Exception t) {
	t.printStackTrace();

}
}
	
	@AfterTest
	public static void tearDown() {
		baseScreen.closeBrowser();
	}



}

