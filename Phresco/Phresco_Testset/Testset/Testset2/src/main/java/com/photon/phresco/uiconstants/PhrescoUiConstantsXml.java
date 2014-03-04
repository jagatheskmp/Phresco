package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class PhrescoUiConstantsXml {
	public ReadXMLFile readXml;
	
	public String LOGIN_USERNAME = "userNameXpath";
	public String LOGIN_PASSWORD = "passwordXpath";
	public String LOGIN_BUTTON = "loginButtonXpath";	
	public String USER_NAME = "userName";	
	public String ADVANCE_UI = "advanceUiXpath";	
	public String PROJECT_BUTTON = "projectTabXpath";
	public String ADD_PROJECT_BUTTON = "addprojectButtonXpath";
	public String ADD_PROJECT_NAME = "projectNameXpath";
	public String ADD_PROJECT_DESC = "projectDescriptionXpath";
	public String APP_LAYER_CHECKALL = "appLayerCheckAll";
	public String APP_LAYER_DROPDOWN = "appLayerTechDropDown";
	public String PROJECT_CREATE_BUTTON = "createProject";	
	public String FUNCTIONAL_DROPDOWN = "functionalFramework";
	public String APP_INFO_SERVER_CHECK_ALL = "serverCheckAll";
	public String APP_INFO_SERVER_DROPDOWN = "serverDropDown";
	public String APP_INFO_SERVER_VERSION_CHECKBOX = "serverVersionCheckbox";
	public String APP_INFO_SERVER_VERSION_TOMCAT = "serverVersionTomact";
	public String APP_INFO_DB_CHECK_ALL = "dbCheckAll";
	public String APP_INFO_DB_DROPDOWN ="dbDropDown";
	public String PROJECT_NEXT_BUTTON = "nextButton";
	public String PROJECT_FINISH_BUTTON ="finishButton";
	public String APP_INFO_PILOT_PROJECT_DROPDOWN = "pilotProjectDropDown";	
	public String MENU_TAB = "menuTab";
	public String CONFIG_TAB = "configTab";
	public String CONFIG_ADD_BUTTON = "configAddButton";
	public String CONFIG_NAME = "configName";
	public String CONFIG_DESC = "configDesc";
	public String CONFIG_ENV = "configEnv";
	public String CONFIG_TEMPLATE_TYPE = "configTemplateType";
	public String CONFIG_INSTALL_PATH_SITECORE = "configInstallationPathSiteCore";
	public String CONFIG_PROTOCOL = "configProtocol";
	public String CONFIG_PORT = "configPort";
	public String CONFIG_HOST = "configHost";
	public String CONFIG_USERNAME = "configUsername";
	public String CONFIG_DB_NAME = "configDbName";
	public String CONFIG_BROWSE_BUTTON = "configBrowseButton";
	public String CONFIG_BROWSE_LOCATION = "configBrowseLocation";
	public String CONFIG_BROWSE_OK = "configBrowseOk";
	public String CONFIG_CONTEXT = "configContext";
	public String CONFIG_SAVE_BUTTON = "configSaveButton";
	public String BUILD_TAB = "buildTabXpath";
	public String GENERATE_BUILD = "generateBuildXpath";
	public String BUILD_NAME_XPATH = "buildNameXpath";
	public String BUILD_NUMBER_XPATH = "buildNumberXpath";
	public String BUILD_BUTTON = "buildButton";
	public String BUILD_NAME = "buildName";	
	public String BUILD_NUMBER = "buildNumber";
	public String DEPLOY_BUTTON  = "deployButton";
	public String SELECT_SQL = "selectSql";
	public String DEPLOY_OK = "deployOk";
	public String DEPLOY_SDK_VERSION = "deploySdkVersion";
	public String DEPLOY_SDK_FAMILY = "deploySdkFamily";
	public String DEPLOY_DEVICE = "deployDevice";
	public String RUN_AGAINST_SOURCE_BUTTON  = "runAgainstSourceButton";
	public String RUN_AGAINST_SOURCE_RUN_BUTTON = "runAgainstSourceRunButton";
	public String RUN_AGAINST_SOURCE_STOP_BUTTON = "runAgainstSourceStopButton";
	public String BUILD_ENV = "buildEnv";
	public String SDK_SELECT = "sdkDropdown";
	public String TARGET_SELECT = "targetDropdown";
	public String SKIP_UNIT_TEST = "skipUnitTest";
	public String QUALITY_TAB = "qualityTab";
	public String UNIT_TAB = "unitTab";
	public String UNIT_TEST_BUTTON = "unitTestButton";
	public String UNIT_TEST_APPLICATION_TYPE = "unitTestApplicationType";
	public String RUN_UNIT_TEST_BUTTON = "runUnitTestButton";
	public String TEST_CLOSE_BUTTON  ="testCloseButton";
	public String PERFORMACE_TAB = "performanceTab";
	public String PERFORMANCE_TEST_BUTTON = "performanceTestButton";
	public String TEST_AGAINST = "testAgainst";
	public String TEST_NAME = "testName";
	public String CONTEXT_NAME_XPATH = "contextNameXpath";
	public String CONTEXT_VALUE_XPATH = "contextValueXpath";
	public String PERFORMANCE_TEST_RUN = "performanceTestRun";
	public String DB_NAME_XPATH = "dbNameXpath";
	public String DB_QUERY_XPATH = "dbQueryXpath";
	public String LOAD_TAB = "loadTab";
	public String LOAD_TEST_BUTTON = "loadTestButton";
	public String LOAD_TEST_RUN = "loadTestRun";
	public String TEST_AGAINST_SERVER = "testAgainstServer";
	public String TEST_AGAINST_DB = "testAgainstDb";
	public String TEST_AGAINST_WEB_SERVICE = "testAgainstWebServices";
	public String TEST_RESULT_NAME = "testResultName";
	public String CONTEXT_NAME_VALUE = "contextNameValue";
	public String CONTEXT_VALUE = "contextValue";
	public String DB_NAME_VALUE = "dbNameValue";
	public String DB_QUERY_VALUE ="dbQueryValue";
	public String JAVA_TEST_AGAINST_VALUE = "javaTestAgainstValue";
	public String JS_TEST_AGAINST_VALUE = "jsTestAgainstValue";
	public String WEB_LAYER_CHECKBOX = "webLayerCheckbox";
	public String WEB_LAYER_DROPDOWN = "webLayerDropdown";
	public String WEB_LAYER_WIDGET_DROPDOWN = "webLayerWidgetDropdown";
	public String WEB_LAYER_WIDGET_VERSION = "webLayerWidgetVersion";
	public String MOB_LAYER_CHECKBOX = "mobLayerCheckbox";
	public String ANDROID_CHECKBOX = "androidCheckbox";
	public String ANDROID_TECH_DROPDOWN = "androidTechDropdown";
	public String ANDROID_TECH_VERSION = "androidTechVersion";
	public String ANDROID_PHONE_CHECKBOX = "androidPhone";
	public String ANDROID_TABLET_CHECKBOX = "androidTablet";
	public String WINDOWS_CHECKBOX = "windowsCheckbox";
	public String WINDOWS_TECH_DROPDOWN = "windowsTechDropdown";
	public String WINDOWS_TECH_VERSION = "windowsVersion";
	public String WINDOWS_PHONE_CHECKBOX = "windowsPhone";
	public String WINDOWS_TABLET_CHECKBOX = "windowsTablet";
	public String BLACKBERRY_CHECKBOX = "blackBerryCheckbox";
	public String BLACKBERRY_TECH_DROPDOWN = "blackBerryTechDropdown";
	public String BLACKBERRY_TECH_VERSION = "balckBerryVersion";
	public String BLACKBERRY_PHONE_CHECKBOX = "blackBerryPhone";
	public String BLACKBERRY_TABLET_CHECKBOX = "blackBerryTablet";
	public String IPHONE_CHECKBOX = "iphoneCheckbox";
	public String IPHONE_TECH_DROPDOWN = "iphoneTechDropdown";
	public String IPHONE_TECH_VERSION = "iphoneVersion";
	public String IPHONE_PHONE_CHECKBOX = "iphonePhone";
	public String IPHONE_TABLET_CHECKBOX = "iphoneTablet";
	public String IMPORT_APPLICATION = "importApplicationButton";
	public String REPO_TYPE = "repoType";
	public String REPO_URL = "repoUrl";
	public String REPO_USERNAME = "repoUserName";
	public String REPO_PASSWORD = "repoPassword";
	public String REPO_REVISION = "repoRevision";
	public String REPO_REVISION_VALUE = "reporevisionValue";
	public String REPO_HEAD_REVISION = "repoHeadRevision";
	public String REPO_CREDENTIAL = "repoCrendential";
	public String REPO_IMPORT_UPDATE_APPLICATION = "repoImportUpdateApplication";
	
	public String ADD_ENV_BUTTON = "addEnv";
	public String ENV_NAME = "envName";
	public String ENV_DESC = "envDesc";
	public String ENV_ADD_BUTTON = "envAddButton";
	public String ENV_OK_BUTTON = "envOKbutton";
	public String CLONE_PRODUCTION_LINK = "cloneProduction";
	public String CLONE_DB = "cloneDb";
	public String CLONE_ENV_SELECT = "cloneEnvSelect";
	public String CLONE_CONFIG_BUTTON = "cloneConfiButton";
	public String CLONE_SERVER = "cloneServer";
	public String FIRST_BUILD_ENV = "firstBuildEnv";
	public String SECOND_BUILD_ENV = "secondBuildEnv";
	
	public PhrescoUiConstantsXml() {
		try {
			readXml = new ReadXMLFile();		
			readXml.loadPhrescoFrameworkUiConstants();
			Field[] arrayOfField1 = super.getClass().getFields();
			Field[] arrayOfField2 = arrayOfField1;
			int i = arrayOfField2.length;
			for (int j = 0; j < i; ++j) {
				Field localField = arrayOfField2[j];
				Object localObject = localField.get(this);
				if (localObject instanceof String)
					localField
					.set(this, readXml.getValue((String) localObject));

			}
		} catch (Exception localException) {
			throw new RuntimeException("Loading "
					+ super.getClass().getSimpleName() + " failed",
					localException);
		}
	}
}
