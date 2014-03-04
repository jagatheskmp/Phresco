package com.photon.phresco.testcases;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.photon.phresco.Screens.BaseScreen;
import com.photon.phresco.uiconstants.AdminUIData;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.UserInfoConstants;

public class ComponentsPage {
	private static UIConstants uiConstants;
	private static PhrescoUiConstants phrescoUIConstants;
	private static BaseScreen baseScreen;
	private static String methodName;
	private static String selectedBrowser;
	private static AdminUIData adminUIConstants;
	private static UserInfoConstants userInfoConstants;
	

	@BeforeClass
	public static void setUp() throws Exception {
		try {
			phrescoUIConstants = new PhrescoUiConstants();
			uiConstants = new UIConstants();
			userInfoConstants =new UserInfoConstants();
			// assertNotNull(uiConstants);
			adminUIConstants = new AdminUIData();
			launchingBrowser();
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public static void launchingBrowser() throws Exception {
		try {
			String applicationURL = phrescoUIConstants.PROTOCOL + "://"
					+ phrescoUIConstants.HOST + ":" + phrescoUIConstants.PORT
					+ "/";
			selectedBrowser = phrescoUIConstants.BROWSER;
			baseScreen = new BaseScreen(selectedBrowser, applicationURL,
					phrescoUIConstants.CONTEXT, adminUIConstants,
					uiConstants,userInfoConstants);
		} catch (Exception exception) {
			exception.printStackTrace();

		}
	}

	
	@Test
	public void componentsFeaturesCreateEmptyModulesPage()throws InterruptedException, IOException, Exception{
		try {
			baseScreen.validLoginAdminUI(methodName);
			System.out
					.println("---------componentsFeaturesCreateEmptyModulesPage()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			baseScreen.createEmptyModules(methodName);
		
			

			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	/*@Test
	public void componentsFeaturesCreateInvalidModulesPage()throws InterruptedException, IOException, Exception{
		try {
			
			System.out
					.println("---------componentsFeaturesCreateInvalidModulesPage()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
		
			baseScreen.createInvalidModules(methodName);
			
			

			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void componentsFeaturesCreateValidModulesPage()throws InterruptedException, IOException, Exception{
		try {
	
			System.out
					.println("---------componentsFeaturesCreateValidModulesPage()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
			baseScreen.createValidModules(methodName);
			
			

			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	
	@Test
	public void componentsFeaturesUpdateValidModulesPage()throws InterruptedException, IOException, Exception{
		try {
			
			System.out
					.println("---------componentsFeaturesUpdateValidModulesPage()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			
					baseScreen.updateModules(methodName);
					
			

			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
@Test
	public void componentsFeaturesCreateEmptyJSlibrariesPage()
			throws InterruptedException, IOException, Exception {
		try {
	
			System.out
					.println("---------componentsFeaturesCreateEmptyJSlibrariesPage()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			baseScreen.createEmptyJSLibraries(methodName);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
@Test
public void componentsFeaturesCreateInvalidJSlibrariesPage()
		throws InterruptedException, IOException, Exception {
	try {

		System.out
				.println("---------componentsFeaturesCreateInvalidJSlibrariesPage()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		baseScreen.createInvalidJSLibraries(methodName);
	
		
	} catch (Exception t) {
		t.printStackTrace();

	}
}

@Test
public void componentsFeaturesCreateValidJSlibrariesPage()
		throws InterruptedException, IOException, Exception {
	try {

		System.out
				.println("---------componentsFeaturesCreateValidJSlibrariesPage()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		baseScreen.createValidJSLibraries(methodName);
	
		
	} catch (Exception t) {
		t.printStackTrace();

	}
}

@Test
public void componentsFeaturesUpdateJSlibrariesPage()
		throws InterruptedException, IOException, Exception {
	try {

		System.out
				.println("---------componentsFeaturesUpdateJSlibrariesPage()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		baseScreen.updateJSLibraries(methodName);
	
		
	} catch (Exception t) {
		t.printStackTrace();

	}
}

@Test
public void testEmptyApplicationTypePage()
		throws InterruptedException, IOException, Exception {
	try {

		System.out
				.println("---------testEmptyApplicationTypePage()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		baseScreen.createEmptyApplicationType(methodName);
	

		
	} catch (Exception t) {
		t.printStackTrace();

	}
}

	@Test
	public void testApplicationTypePage()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testComponentsApplicationTypePage()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			baseScreen.createValidApplicationType(methodName);
		

			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	
	@Test
	public void testUpdateApplicationTypePage()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testUpdateApplicationTypePage()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			baseScreen.updateApplicationType(methodName);

			
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	

	@Test
	public void testCreateEmptyArchetypesPage()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testCreateEmptyArchetypesPage()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			baseScreen.createEmptyArchetypes(methodName);
		
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
	@Test
	public void testCreateInvalidArchetypesPage()
			throws InterruptedException, IOException, Exception {
		try {

			System.out
					.println("---------testCreateInvalidArchetypesPage()-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			baseScreen.createInvalidArchetypes(methodName);
		
		} catch (Exception t) {
			t.printStackTrace();

		}
	}
@Test
public void testCreateWebLayerArchetypesPage()
		throws InterruptedException, IOException, Exception {
	try {

		System.out
				.println("---------testCreateWebLayerArchetypesPage()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		baseScreen.createWebLayerArchetypes(methodName);
	
	} catch (Exception t) {
		t.printStackTrace();

	}
}

@Test
public void testCreateApplicationLayerArchetypesPage()
		throws InterruptedException, IOException, Exception {
	try {

		System.out
				.println("---------testCreateApplicationLayerArchetypesPage()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		baseScreen.createApplicationLayerArchetypes(methodName);
	
	} catch (Exception t) {
		t.printStackTrace();

	}
}
@Test
public void testCreateMobileLayerArchetypesPage()
		throws InterruptedException, IOException, Exception {
	try {

		System.out
				.println("---------testCreateMobileLayerArchetypesPage()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		baseScreen.createMobileLayerArchetypes(methodName);
	
	} catch (Exception t) {
		t.printStackTrace();

	}
}
*/
@AfterClass
	public static void tearDown() {
		baseScreen.closeBrowser();
	}

}
