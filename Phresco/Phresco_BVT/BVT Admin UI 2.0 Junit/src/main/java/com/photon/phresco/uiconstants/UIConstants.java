package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class UIConstants {
	
	private ReadXMLFile readXml;
	

	public String LOGIN_USERNAME="usernameXpath";
	public String LOGIN_PASSWORD="passwordXpath";
	public String LOGIN_BUTTON="loginButtonXpath";
	

	public String DADHBOARD_LINK="dashboardLinkXpath";
	public String MOST_USED_ARCHETYPE="mostUsedArchetypesXpath";
	public String MOST_USED_FEATURE="mostusedFeatureXpath";
	public String MOST_USED_PILOT_PROJECT="mostusedPilotProjectsXpath";
	

	public String COMPONENTS_LINK="componentsLinkXpath";
	
	public String COMPONENT_FEATURE_TAB="componentsFeaturesTabXpath";
	
	public String FEATURE_MODULES_TAB="featuresModulesTabXpath";
	
	
	public String CREATE_MODULES_BUTTON="modulesCreateButtonXpath";
	
	
	public String MODULES_NAME="modulesNameXpath";
	public String MODULE_DESCRIPTION="modulesDescriptionXpath";
	public String MODULE_HELPTEXT="modulesHelpTextXpath";
	public String MODULE_TECHNOLOGY="modulesTechnologyXpath";
	public String MODULE_MODULETYPE="modulesModuleTypeXpath";
	public String MODULE_UPLOAD_FILE="modulesUploadFileXpath";
	public String MODULE_SELECT_DEPENDENCY="modulesSelectDependencyXpath";
	public String MODULE_DEPENDENCY_LOGIN_CHECKBOX="modulesDependencyLoginCheckboxXpath";
	public String MODULE_DEPENDENCY_OK_BUTTON="modulesDependencyOKButtonXpath";
	public String MODULE_SAVE_BUTTON="moduleSaveButtonXpath";
	public String MODULE_CANCEL_BUTTON="moduleCancelButtonXpath";
	

	
	public String UPDATE_MODULE_LINK="updateModulesLinkXpath";
	public String UPDATE_MODULE_OPTION_BUTTON="updateModuleOptionButtonXpath";
	public String UPDATE_MODULE_UPDATE_BUTTON="updateModuleButtonXpath";
	
	
	public String FEATURES_JSLIBRARIES_TAB="featuresJSlibrariesTabXpath";
	public String JSLIBRARIES_CREATE_BUTTON="JSlibrariesCreateButtonXpath";
	public String JSLIBRARIES_NAME="JSlibrariesNameXpath";
	public String JSLIBRARIES_DESCRIPTION="JSlibrariesDescriptionXpath";
	public String JSLIBRARIES_HELPTEXT="JSlibrariesHelpTextXpath";
	public String JSLIBRARIES_TECHNOLOGY="JSlibrariesTechnologyXpath";
	
	//public String MODULE_UPLOAD_FILE_JSLIBRARIES_XPATH="moduleuploadFileXpath";
	public String JSLIBRARIES_SELECT_DEPENDENCY="JSlibrariesSelectDependencyXpath";
	public String JSLIBRARIES_SELECT_JSON_CHECKBOX="dependencyJSonCheckboxButtonXpath";
	public String JSLIBRARIES_DEPENDENCY_OK_BUTTON="JSlibrariesDependencyOKButtonXpath";
	
	public String JSLIBRARIES_SAVE_BUTTON="JSlibrarieSaveButtonXpath";
	public String JSLIBRARIES_CANCEL_BUTTON="JSlibrariesCancelButtonXpath";
	

	public String UPDATE_JSLIBRARIES_LINK_XPATH="updateJSlibrariesLinkXpath";
	public String UPDATE_JSLIBRARIES_OPTION_BUTTON_XPATH="updateJSlibrariesOptionButtonXpath";
	public String UPDATE_JSLIBRARIES_UPDATE_BUTTON_XPATH="JSlibrariesUpdateButtonXpath";
	
	public String APPLICATION_TYPES_TAB="applicationTypesTabXpath";
	public String APPLICATION_TYPES_CREATE="applicationTypesCreateButtonXpath";
	public String APPLICATION_TYPE_NAME="applicationTypeNameXpath";
	public String APPLICATION_TYPE_DESCRIPTION="applicationTypeDescriptionXpath";
	public String APPLICATION_TYPE_SAVE_BUTTON="applicationTypeSaveButtonXpath";
	public String APPLICATION_TYPE_CANCEL_BUTTON="applicationTypeCancelButtonXpath";
	
	public String UPDATE_APPLICATION_TYPES_LINK="updateApplicationTypesLinkXpath";
	public String UPDATE_APPLICATION_TYPES_DESCRIPTION="updateApplicationTypesDescriptionXpath";
	public String UPDATE_APPLICATION_TYPES_UPDATE_BUTTON="updateDescriptionUpdateButtonXpath";

	public String ADMIN_LINK="adminLinkXpath";
	public String ADMIN_CUSTOMER_TAB="adminCustomerTabXpath";
	public String CUSTOMER_CREATE_BUTTON="customerCreateButtonXpath";
	public String CUSTOMER_NAME="customerNameXpath";
	public String CUSTOMER_DESCRIPTION="customerDescriptionXpath";
	public String CUSTOMER_EMAIL="customerEmailXpath";
	public String CUSTOMER_ADDRESS="customerAddressXpath";
	public String CUSTOMER_COUNTRY="customerCountryXpath";
	public String CUSTOMER_STATE="customerStateXpath";
	public String CUSTOMER_ZIPCODE="customerZipcodeXpath";
	public String CUSTOMER_CONTACT_NUMBER="customerContactNumberXpath";
	public String CUSTOMER_FAX="customerFaxXpath";
	public String CUSTOMER_HELP_TEXT="customerHelpTextXpath";
	public String CUSTOMER_LICENCE_TYPE="customerLicenceTypeXpath";
	public String CUSTOMER_SAVE_BUTTON="customerSaveButtonXpath";
	public String CUSTOMER_CANCEL_BUTTON="customerCancelButtonXpath";
	
	public String CUSTOMER_CALENDER_VALID_FROM="customerCalenderValidFromXpath";
	public String CUSTOMER_CALENDER_VALID_FROM_FRONT_ARROW="customerCalenderValidFromBackArrowXpath";
	public String CUSTOMER_CALENDER_VALID_FROM_DATE="customerCalenderValidFromDateXpath";
	
	public String CUSTOMER_CALENDER_VALID_UPTO="customerCalenderValidUptoXpath";
	public String CUSTOMER_CALENDER_VALID_UPTO_FRONT_ARROW="customerCalenderValidUptoBackArrowXpath";
	public String CUSTOMER_CALENDER_VALID_UPTO_DATE="customerCalenderValidUptoDateXpath";

	public String UPDATE_CUSTOMER_LINK="updateCustomerLinkXpath";
	public String UPDATE_CUSTOMER_UPDATE_BUTTON="updateCustomerUpdateButtonXpath";
	
	
	public String DELETE_MODULE_XPATH="deleteModuleXpath";
	
	public String DELETE_JSLIBRARIES_XPATH="deleteJSlibrariesXpath";
	
	
	public String ARCHETYPES_TAB="archetypeTabXpath";
	public String ARCHETYPES_CREATE_BUTTON="archetypeCreateButtonXpath";
	public String ARCHETYPES_NAME="archetypeNameXpath";
	public String ARCHETYPES_DESCRIPTION="archetypeDescriptionXpath";
	public String ARCHETYPES_TECHNOLOGY_VERSION="archetypeTechnologyVersionXpath";
	public String ARCHETYPES_VERSION_COMMENT="archetypeVersionCommentXpath";
	public String ARCHETYPES_APPLICATION_TYPE="archetypeApplicationTypeXpath";
	public String ARCHETYPES_UPLOAD_JAR="archetypeUploadJarXpath";
	public String ARCHETYPES_UPLOAD_PLUGIN_JAR="archetypeUploadPluginJarXpath";
	public String ARCHETYPES_APPLICATION_FEATURES_ALL="archetypeApplicationFeaturesAllXpath";
	public String ARCHETYPES_APPLICATION_FEATURES_CODE="archetypeApplicationFeaturesCodeXpath";
	public String ARCHETYPES_APPLICATION_FEATURES_BUILD="archetypeApplicationFeaturesBuildXpath";
	public String ARCHETYPES_APPLICATION_FEATURES_DEPLOY="archetypeApplicationFeaturesDeployXpath";
	public String ARCHETYPES_APPLICATION_FEATURES_RUN_AGAINST_SOURCE="archetypeApplicationFeaturesRunAgainstSourceXpath";
	public String ARCHETYPES_APPLICATION_FEATURES_UNIT_TEST="archetypeApplicationFeaturesUnitTestXpath";
	public String ARCHETYPES_APPLICATION_FEATURES_FUNCTONAL_TEST="archetypeApplicationFeaturesFunctionalTestXpath";
	public String ARCHETYPES_APPLICATION_FEATURES_PERFORMANCE_TEST="archetypeApplicationFeaturesPerformanceTestRunXpath";
	public String ARCHETYPES_APPLICATION_FEATURES_LOAD_TEST="archetypeApplicationFeaturesLoadTestXpath";
	public String ARCHETYPES_APPLICATION_FEATURES_REPORTS="archetypeApplicationFeaturesReportsXpath";
	public String ARCHETYPES_APPLICATION_FEATURES_CI="archetypeApplicationFeaturesCIXpath";
	public String ARCHETYPES_APPLICABLE_REPORTS_ALL="archetypeApplicableReportsAllXpath";
	public String ARCHETYPES_APPLICABLE_REPORTS_PROJECT_INFO_REPORT="archetypeApplicableReportsProjectInfoReportXpath";
	public String ARCHETYPES_APPLICABLE_REPORTS_JAVA_DOC_REPORT="archetypeApplicableReportsJavadocReportXpath";
	public String ARCHETYPES_APPLICABLE_REPORTS_COBERTURA_REPORT="archetypeApplicableReportsCoberturaReportXpath";
	public String ARCHETYPES_APPLICABLE_REPORTS_JDEPEND_REPORT="archetypeApplicableReportsJdependReportXpath";
	public String ARCHETYPES_APPLICABLE_REPORTS_JXR_REPORT="archetypeApplicableReportsJXRReportXpath";
	public String ARCHETYPES_APPLICABLE_REPORTS_PMD_REPORT="archetypeApplicableReportsPMDReportXpath";
	public String ARCHETYPES_APPLICABLE_REPORTS_SURFIRE_REPORT="archetypeApplicableReportsSurefireReportXpath";
	public String ARCHETYPES_SAVE_BUTTON="archetypeSaveButtonXpath";
	public String ARCHETYPES_CANCEL_BUTTON="archetypeCancelButtonXpath";
	

	public UIConstants() {
		try {
			readXml = new ReadXMLFile();
			readXml.loadUIConstants();
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
