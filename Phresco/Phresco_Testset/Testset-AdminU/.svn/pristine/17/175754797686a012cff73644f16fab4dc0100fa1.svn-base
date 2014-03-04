package com.photon.phresco.testcases;

import java.io.IOException;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.photon.phresco.Screens.BaseScreen;
import com.photon.phresco.uiconstants.AdminUIData;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.UserInfoConstants;

public class ComponentsPageValidationTest {
	private  UIConstants uiConstants;
	private  PhrescoUiConstants phrescoUIConstants;
	private  BaseScreen baseScreen;
	private  String methodName;
	//private  String selectedBrowser;
	private  AdminUIData adminUIConstants;
	private  UserInfoConstants userInfoConstants;
	

	@Parameters(value = { "browser", "platform" })
	@BeforeTest
	public  void setUp(String browser, String platform) throws Exception {
		try {
			System.out.println("----------------------LoginPage-----------------");
			phrescoUIConstants = new PhrescoUiConstants();
			uiConstants = new UIConstants();
			userInfoConstants =new UserInfoConstants();
			adminUIConstants = new AdminUIData();
			String selectedBrowser = browser;
			String selectedPlatform = platform;
			
			methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			System.out
			.println("-----------Selected Browser to execute testcases--->>"
					+ selectedBrowser);
			
			String applicationURL = phrescoUIConstants.PROTOCOL + "://"
					+ phrescoUIConstants.HOST + ":" + phrescoUIConstants.PORT
					+ "/";
			
			baseScreen = new BaseScreen(selectedBrowser, selectedPlatform, applicationURL,phrescoUIConstants.CONTEXT,phrescoUIConstants,uiConstants,userInfoConstants,adminUIConstants);
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	 @Test
	 public void testValidLogin()
			throws InterruptedException, IOException, Exception {
		try {

			System.out.println("---------testLoginAdminUI()-------------");
			String methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
				baseScreen.validLoginAdminUI(methodName);
				baseScreen.customerSelection(methodName);
				//baseScreen.deleteArchetypes(methodName);
			
		} catch (Exception t) {
			t.printStackTrace();

		}
	 }
	 @Test
	 public void testComponentsPageValidation()
		throws InterruptedException, IOException, Exception {
	 try {

		System.out
				.println("---------testReusableComponentsAddvalidComponentPage()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		baseScreen.componentsPageValidation(methodName);

		
	 } catch (Exception t) {
		t.printStackTrace();

	 }
	 }
	
	 @Test
	 public void testModulesPageWithoutMandatoryFieldsValidation()
		throws InterruptedException, IOException, Exception {
	 try {

		System.out
				.println("---------testReusableComponentsAddvalidComponentPage()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		baseScreen.modulesPageWithoutMandatoryFieldsValidation(methodName);

		
	 } catch (Exception t) {
		t.printStackTrace();

	 }
	 }
	 
	 @Test
	 public void testJslibrariesPageWithoutMandatoryFieldsValidation()
		throws InterruptedException, IOException, Exception {
	 try {

		System.out
				.println("---------testReusableComponentsAddvalidComponentPage()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		baseScreen.jslibrariesPageWithoutMandatoryFieldsValidation(methodName);

		
	 } catch (Exception t) {
		t.printStackTrace();

	 }
	 }
	 
	 @Test
	 public void testComponentPageWithoutMandatoryFieldsValidation()
		throws InterruptedException, IOException, Exception {
	 try {

		System.out
				.println("---------testReusableComponentsAddvalidComponentPage()-------------");
		methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		baseScreen.componentPageWithoutMandatoryFieldsValidation(methodName);

		
	 } catch (Exception t) {
		t.printStackTrace();

	 }
	 }
	 
	 @Test
	 public void testArchetypeCancelButton()
				throws InterruptedException, IOException, Exception {
			 try {

				System.out
						.println("---------testReusableComponentsAddvalidComponentPage()-------------");
				methodName = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
				
				baseScreen.archetypeCancelButton(methodName);

				
			 } catch (Exception t) {
				t.printStackTrace();

			 }
			 }
			 
	 @Test
	 public void testArchetypeSaveWithoutMandatoryFields()
				throws InterruptedException, IOException, Exception {
			 try {

				System.out
						.println("---------testReusableComponentsAddvalidComponentPage()-------------");
				methodName = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
				
				baseScreen.archetypeSaveWithoutMandatoryFields(methodName);

				
			 } catch (Exception t) {
				t.printStackTrace();

			 }
			 }
	 @Test
	 public void testCreateArchetypeWithoutJar()
				throws InterruptedException, IOException, Exception {
			 try {

				System.out
						.println("---------testReusableComponentsAddvalidComponentPage()-------------");
				methodName = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
				
				baseScreen.createArchetypeWithoutJar(methodName);

				
			 } catch (Exception t) {
				t.printStackTrace();

			 }
			 }
			 
	
	 @Test
	 public void testCreateArchetypeWithAnotherCustomer()
				throws InterruptedException, IOException, Exception {
			 try {

				System.out
						.println("---------testReusableComponentsAddvalidComponentPage()-------------");
				methodName = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
				
				baseScreen.createArchetypeWithAnotherCustomer(methodName);

				
			 } catch (Exception t) {
				t.printStackTrace();

			 }
			 }
		
	 @Test
	 public void testDeleteArchetypeWithAnotherCustomer()
				throws InterruptedException, IOException, Exception {
			 try {

				System.out
						.println("---------testReusableComponentsAddvalidComponentPage()-------------");
				methodName = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
				
				baseScreen.deleteArchetypeWithAnotherCustomer(methodName);

				
			 } catch (Exception t) {
				t.printStackTrace();

			 }
			 }

	 
	 @Test
	 public void testCreateArchetypeWithoutTechnologyVersion()
				throws InterruptedException, IOException, Exception {
			 try {

				System.out
						.println("---------testReusableComponentsAddvalidComponentPage()-------------");
				methodName = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
				
				baseScreen.createArchetypeWithoutTechnologyVersion(methodName);

				
			 } catch (Exception t) {
				t.printStackTrace();

			 }
			 }

	 @Test
	 public void testCreateArchetypeWithoutApplicableFeatures()
				throws InterruptedException, IOException, Exception {
			 try {

				System.out
						.println("---------testReusableComponentsAddvalidComponentPage()-------------");
				methodName = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
				
				baseScreen.createArchetypeWithoutApplicableFeatures(methodName);

				
			 } catch (Exception t) {
				t.printStackTrace();

			 }
			 }

	 
	

	 @Test
	 public void testTechnologyGroupCreation()
				throws InterruptedException, IOException, Exception {
			 try {

				System.out
						.println("---------testReusableComponentsAddvalidComponentPage()-------------");
				methodName = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
				
				baseScreen.technologyGroupCreation(methodName);

				
			 } catch (Exception t) {
				t.printStackTrace();

			 }
			 }

	 @Test
	 public void testTechnologyGroupCancelButton()
				throws InterruptedException, IOException, Exception {
			 try {

				System.out
						.println("---------testReusableComponentsAddvalidComponentPage()-------------");
				methodName = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
				
				baseScreen.technologyGroupCancelButton(methodName);

				
			 } catch (Exception t) {
				t.printStackTrace();

			 }
			 }

	 @Test
	 public void testTechnologyGroupDeletion()
				throws InterruptedException, IOException, Exception {
			 try {

				System.out
						.println("---------testReusableComponentsAddvalidComponentPage()-------------");
				methodName = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
				
				baseScreen.technologyGroupDeletion(methodName);

				
			 } catch (Exception t) {
				t.printStackTrace();

			 }
			 }
	 

	 
 	@AfterTest
	public  void tearDown() {
		baseScreen.closeBrowser();
	}
}





