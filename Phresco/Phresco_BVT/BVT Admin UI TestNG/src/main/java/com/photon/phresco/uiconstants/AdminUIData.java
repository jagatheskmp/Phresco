package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class AdminUIData {

	private ReadXMLFile readXml;
	
	public String INVALID_LOGIN_USERNAME="invalidLoginUsername";
	public String INVALID_LOGIN_PASSWORD="invalidLoginPassword";
	
	public String EMPTY_ERROR_MSG_LOGIN="emptyErrorMsgLogin";
	/*Create Module */
	public String CREATE_MODULE_NAME_VALUE="CreateModuleNameValue";
	public String CREATE_MODULE_DESCRIPTION_VALUE="createModuleDescriptionValue";
	public String CREATE_MODULE_HELPTEXT_VALUE="createModuleHelpTextValue";
	public String CREATE_MODULE_TECHNOLOGY_VALUE="createModuleTechnologyValue";
	public String CREATE_MODULE_TYPE_CUSTOM_MODULE_VALUE="createModuleModuleTypeValue";
	
	public String UPDATE_MODULE_NAME_VALUE="updateModuleNameValue";
	public String UPDATE_MODULE_DESCRIPTION_VALUE="updateModuleDescriptionValue";
	public String UPDATE_MODULE_HELP_TEXT_VALUE="updateModuleHelpTextValue";
	
	
	
	
	public String EMPTY_CREATE_MODULE_NAME_MSG="emptyCreateModuleNameMsg";
	public String EMPTY_CREATE_MODULE_UPLOAD_FILE_MSG="emptyCreateModuleUploadFileMsg";
	
	
	public String INVALID_CREATE_MODULE_UPLOAD_FILE="invalidCreateModuleUploadFileValue"; 

	public String INVALID_CREATE_MODULE_UPLOAD_FILE_MSG="invalidCreateModuleUploadFileMsg";


	
	
	public String EMPTY_CREATE_JSLIBRARIES_NAME_MSG="emptyCreateJSlibrariesNameMsg";
	public String EMPTY_CREATE_JSLIBRARIES_UPLOAD_FILE_MSG="emptyCreateJSlibrariesUploadFileMsg";
	
	public String INVALID_CRAETE_JSLIBRARIES_UPLOAD_FILE_NAME_MSG="invalidCreateJSlibrariesUploadFileMsg";
	
	/*Create JSlibraries */
	public String CREATE_JSLIBRARIES_NAME_VALUE="createJSlibrariesNameValue";
	public String CREATE_JSLIBRARIES_DESCRIPTION_VALUE="createJSlibrariesDescriptionValue";
	public String CREATE_JSLIBRARIES_HELPTEXT_VALUE="createJSlibrariesHelpTextValue";
	public String CREATE_JSLIBRARIES_PHP_TECHONOLGY_VALUE="createJSlibrariesTechnologyPHP";
	
	/*Update JSlibraries */
	public String UPDATE_FEATURE_JSLIBRARIES_NAME_VALUE="updateFeatureJSlibrariesNameValue";
	public String UPDATE_FEATURE_JSLIBRARIES_DESCRIPTION_VALUE="updateFeatureJSlibrariesDescriptionValue";
	public String UPDATE_FEATURE_JSLIBRARIES_HELP_TEXT_VALUE="updateFeatureJSlibrariesHelpTextValue";

	
	/*Create ApplicationTypes */
	public String CREATE_APPLICATION_TYPE_NAME_VALUE="createApplicationTypesNameValue";
	public String CREATE_APPLICATION_TYPE_DESCRIPTION_VALUE="createApplicationTypesDescriptionValue";
	
	
	public String CREATE_EMPTY_APPLICATION_TYPE_NAME_ERROR_MSG="createEmptyApplicationTypeNameErrorMsg";
	/*Update ApplicationTypes */
	public String UPDATE_APPLICATION_TYPE_DESCRIPTION_VALUE="updateApplicationTypesDescriptionValue";

	
	
	
	public String ERROR_CUSTOMER_NAME_MSG="errorCustomerNameMsg";
	public String ERROR_CUSTOMER_EMAIL_MSG="errorCustomerEmailMsg";
	public String ERROR_CUSTOMER_ADDRESS_MSG="errorCustomerAddressMsg";
	public String ERROR_CUSTOMER_COUNTRY_MSG="errorCustomerCountryMsg";
	public String ERROR_CUSTOMER_ZIPCODE_MSG="errorCustomerZipcodeMsg";
	public String ERROR_CUSTOMER_CONTACT_NUMBER_MSG="errorCustomerContactNumberMsg";
	public String ERROR_CUSTOMER_FAX_NSG="errorCustomerFaxNumberMsg";
	public String ERROR_CUSTOMER_LINCENCE_TYPE_MSG="errorCustomerLicenceTypeMsg";
	
	public String INVALID_CREATE_CUSTOMER_NAME_VALUE="invalidCreateCustomerNameValue";
	public String INVALID_CREATE_CUSTOMER_DESCRIPTION_VALUE="invalidCreateCustomerDescriptionValue";
	public String INVALID_CREATE_CUSTOMER_EMAIL_VALUE="invalidCreateCustomerEmailValue";
	public String INVALID_CREATE_CUSTOMER_ADDRESS_VALUE="invalidCreateCustomerAddressValue";
	public String INVALID_CREATE_CUSTOMER_COUNTRY_VALUE="invalidCreateCustomerCountryValue";
	public String INVALID_CREATE_CUSTOMER_STATE_VALUE="invalidCreateCustomerStateValue";
	public String INVALID_CREATE_CUSTOMER_ZIPCODE_VALUE="invalidCreateCustomerZipcodeValue";
	public String INVALID_CREATE_CUSTOMER_CONTACT_NUMBER_VALUE="invalidCreateCustomerContactNumberValue";
	public String INVALID_CREATE_CUSTOMER_FAX_VALUE="invalidCreateCustomerFaxValue";
	public String INVALID_CREATE_CUSTOMER_HELP_TEXT_VALUE="invalidCreateCustomerHelpTextValue";
	public String INVALID_CREATE_CUSTOMER_LICENCE_TYPE_VALUE="invalidCreateCustomerLicenceTypeValue";
	
	
	public String ERROR_INVALID_CREATE_CUSTOMER_EMAIL_VALUE="invalidCreateCustomerEmailMsg";
	/*Create Customer */
	public String CREATE_CUSTOMER_NAME_VALUE="createCustomerNameValue";            
	public String CREATE_CUSTOMER_DESCRIPTION_VALUE="createCustomerDescriptionValue";
	public String CREATE_CUSTOMER_EMAIL_VALUE="createCustomerEmailValue";
	public String CREATE_CUSTOMER_ADDRESS_VALUE="createCustomerAddressValue";
	public String CREATE_CUSTOMER_COUNTRY_VALUE="createCustomerCountryValue";
	public String CREATE_CUSTOMER_STATE_VALUE="createCustomerStateValue";
	public String CREATE_CUSTOMER_ZIPCODE_VALUE="createCustomerZipcodeValue";
	public String CREATE_CUSTOMER_CONTACT_NUMBER_VALUE="createCustomerContactNumberValue";
	public String CREATE_CUSTOMER_FAX_VALUE="createCustomerFaxValue";
	public String CREATE_CUSTOMER_HELPTEXT_VALUE="createCustomerHelpTextValue";
	public String CREATE_CUSTOMER_LICENCE_TYPE_VALUE="createCustomerLicenceTypeValue";
	
	/*Update Customer */
	public String UPDATE_CUSTOMER_DESCRIPTION_VALUE="updateCustomerDescriptionValue";
	public String UPDATE_CUSTOMER_NAME_VALUE="updateCustomerNameValue";
	public String UPDATE_CUSTOMER_EMAIL_VALUE="updateCustomerEmailValue";
	public String UPDATE_CUSTOMER_ADDRESS_VALUE="updateCustomerAddressValue";
	public String UPDATE_CUSTOMER_HELP_TEXT_VALUE="updateCustomerHelpTextValue";
	
	
	

	public String CREATE_WEB_LAYER_ARCHETYPES_NAME_VALUE="createWebLayerArchetypeNameValue";
	public String CREATE_WEB_LAYER_ARCHETYPES_DESCRIPTION_VALUE="createWebLayerArchetypeDescriptionValue";
	public String CREATE_WEB_LAYER_ARCHETYPES_TECHNOLGY_VERSION_VALUE="createWebLayerArchetypeTechnologyVersionValue";
	public String CREATE_WEB_LAYER_ARCHETYPES_VERSION_COMMENT_VALUE="createWebLayerArchetypeVersionCommentValue";
	public String CREATE_WEB_LAYER_ARCHETYPES_APPLICATION_TYPE_VALUE="createWebLayerArchetypeApplicationTypeValue";

	public String CREATE_APPLICATION_LAYER_ARCHETYPES_NAME_VALUE="createApplicationLayerArchetypeNameValue";
	public String CREATE_APPLICATION_LAYER_ARCHETYPES_DESCRIPTION_VALUE="createApplicationLayerArchetypeDescriptionValue";
	public String CREATE_APPLICATION_LAYER_ARCHETYPES_TECHNOLGY_VERSION_VALUE="createApplicationLayerArchetypeTechnologyVersionValue";
	public String CREATE_APPLICATION_LAYER_ARCHETYPES_VERSION_COMMENT_VALUE="createApplicationLayerArchetypeVersionCommentValue";
	public String CREATE_APPLICATION_LAYER_ARCHETYPES_APPLICATION_TYPE_VALUE="createApplicationLayerArchetypeApplicationTypeValue";

	public String CREATE_MOBILE_LAYER_ARCHETYPES_NAME_VALUE="createMobileLayerArchetypeNameValue";
	public String CREATE_MOBILE_LAYER_ARCHETYPES_DESCRIPTION_VALUE="createMobileLayerArchetypeDescriptionValue";
	public String CREATE_MOBILE_LAYER_ARCHETYPES_TECHNOLGY_VERSION_VALUE="createMobileLayerArchetypeTechnologyVersionValue";
	public String CREATE_MOBILE_LAYER_ARCHETYPES_VERSION_COMMENT_VALUE="createMobileLayerArchetypeVersionCommentValue";
	public String CREATE_MOBILE_LAYER_ARCHETYPES_APPLICATION_TYPE_VALUE="createMobileLayerArchetypeApplicationTypeValue";
	
	
	
	public String CREATE_EMPTY_ARCHETYPE_NAME_ERROR_MSG="createEmptyArchetypeNameErrorMsg";
	public String CREATE_EMPTY_ARCHETYPE_TECHNOLOGY_VERSION_ERROR_MSG="createEmptyArchetypeTechnologyVersionErrorMsg";
	public String CREATE_EMPTY_ARCHETYPE_JAR_ERROR_MSG="createEmptyArchetypeJarErrorMsg"; 
	public String CREATE_EMPTY_ARCHETYPE_FEATURE_ERROR_MSG="createEmptyArchetypeFeatureErrorMsg";
	public AdminUIData() {
		try {
			readXml = new ReadXMLFile();
			readXml.loadAdminUIData();
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
