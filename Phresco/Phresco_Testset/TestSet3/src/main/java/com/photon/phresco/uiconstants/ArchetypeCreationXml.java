package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class ArchetypeCreationXml {
	
	private ReadXMLFile readXml; 
	public String PROJECT_NAME="projectName";
	public String PROJECT_CODE="projectCode";
	public String PROJECT_DESC="projectDesc";
	public String PROJECT_VERSION="projetcVersion";
	public String NODEJS_SERVICE_TECHNOLGY_VALUE="technologynodeJsServiceValue";
	
	public String ANDROID_NATIVE_VALUE="androidNativeValue";
	public String ANDROID_NATIVE_VERSION_VALUE="androidNativeVersionValue";
	
	
	public String HTML_VALUE="html5WebLayerValue";
	public String MULTICHANNEL_YUI_WIDGET_NAME_VALUE="multiYuiWidgetValue";
	public String MULTICHANNEL_YUI_WIDGET_VERSION_VALUE="multiYuiWidgetVersionValue";
	
	

	public String NODEJS_SERVICE_PROJECT_HELLOWORLD_CHECK_BOX="nodeJsServiceHelloWorldCheckBoxXpath";
    public String NODEJS_SERVICE_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK="nodeJsServiceHelloWorldEditApplicationLinkXpath";
    
   
	public String NODEJS_SERVICE_SERVER_VALUE="nodeJsServiceServerValue";
	public String NODEJS_SERVICE_DATABASE_VALUE="nodeJsServiceDatabaseValue";
	
	public String NODEJS_SERVICE_CONFIGURATION_SERVER_NAME_VALUE="nodeJsServiceConfigurationServerNameValue";
	public String NODEJS_SERVICE_CONFIGURATION_SERVER_DESCRIPTION_VALUE="nodeJsServiceConfigurationServerDescriptionValue";
	public String NODEJS_SERVICE_CONFIGURATION_SERVER_ENVIRONMENT_VALUE="nodeJsServiceConfigurationServerEnvironmentValue";
	public String NODEJS_SERVICE_CONFIGURATION_SERVER_TYPE_VALUE="nodeJsServiceConfigurationServerTypeValue";
	public String NODEJS_SERVICE_CONFIGURATION_SERVER_PROTOCOL_HTTP_VALUE="nodeJsServiceConfigurationServerProtocolHttpValue";
	public String NODEJS_SERVICE_CONFIGURATION_SERVER_PROTOCOL_HTTPS_VALUE="nodeJsServiceConfigurationServerProtocolHttpsValue";
	public String NODEJS_SERVICE_CONFIGURATION_SERVER_HOST_VALUE="nodeJsServiceConfigurationServerHostValue";
	public String NODEJS_SERVICE_CONFIGURATION_SERVER_PORT_VALUE="nodeJsServiceConfigurationServerPortValue";
	
	public String NODEJS_SERVICE_CONFIGURATION_SERVER_DEPLOY_DIRECTORY_VALUE="nodeJsServiceConfigurationServerDeployDirectoryValue";
	public String NODEJS_SERVICE_CONFIGURATION_SERVER_CONTEXT_VALUE_HELLOWORLD="nodeJsServiceConfigurationServerContextValueHelloWorld";

	public String NODEJS_SERVICE_CONFIGURATION_DATABASE_NAME_VALUE="nodeJsServiceConfigurationDatabaseNameValue";
	public String NODEJS_SERVICE_CONFIGURATION_DATABASE_DESCRIPTION_VALUE="nodeJsServiceConfigurationDatabaseDescriptionValue";
	public String NODEJS_SERVICE_CONFIGURATION_DATABASE_ENVIRONMENT_VALUE="nodeJsServiceConfigurationDatabaseEnvironmentValue";
	public String NODEJS_SERVICE_CONFIGURATION_DATABASE_TYPE_VALUE="nodeJsServiceConfigurationDatabaseTypeValue";
	public String NODEJS_SERVICE_CONFIGURATION_DATABASE_HOST_VALUE="nodeJsServiceConfigurationDatabaseHostValue";
	public String NODEJS_SERVICE_CONFIGURATION_DATABASE_PORT_VALUE="nodeJsServiceConfigurationDatabasePortValue";
	public String NODEJS_SERVICE_CONFIGURATION_DATABASE_DB_USERNAME_VALUE="nodeJsServiceConfigurationDatabaseDBUsernameValue";
	public String NODEJS_SERVICE_CONFIGURATION_DATABASE_DB_NAME_HELLOWORLD="nodeJsServiceConfigurationDatabaseDBNameValueHelloWorld";
	
	public String MULTICHANNEL_YUI_WIDGET_HELLOWORLD_PROJECT_SERVER_CONTEXT="multiYuiWidgetconfigServContextValueHelloWorld";
	
	public String MULTICHANNEL_YUI_WIDGET_HELLOWORLD_PROJECT_CHECKBOX="multiYuiWidgetHelloWorldCheckBoxXpath";
    public String MULTICHANNEL_YUI_WIDGET_HELLOWORLD_PROJECT_LINK="multiYuiWidgetHelloWorldEditApplicationLinkXpath";
   	
   	public String MULTICHANNEL_YUI_WIDGET_SERVER="appInfoMultiYuiWidgetServerValue";
	public String MULTICHANNEL_YUI_WIDGET_TOMCAT_VERSION="TomacatServerVersionValue";

		
  	public String CONFIG_SERVER_NAME_VALUE="configServNameValue";
  	public String CONFIG_SERVER_DESC_VALUE="configservDescValue";
  	public String CONFIG_SERVER_ENV_VALUE="configServEnvValue";
  	public String CONFIG_SERVER_VALUE="configServValue";
  	public String CONFIG_SERVER_PROTOCOL_VALUE="configServProtocolValue";
  	public String CONFIG_SERVER_HOST_VALUE="configServHostValue";
 	public String CONFIG_SERVER_PORT_VALUE="configServPortValue";
    public String CONFIG_SERVER_DEPLOY_VALUE="configServDeployDirectoryValue";
   

	public String ANDROID_NATIVE_HELLOWORLD_CHECKBOX="androidNativeHelloWorldCheckBoxXpath";
	public String ANDROID_NATIVE_HELLOWORLD_EDIT_LINK="androidNativeHelloWorldEditApplicationLinkXpath";


	public String EMULATOR_VALUE="emulatorValue";
	public ArchetypeCreationXml() {
		try {
			readXml = new ReadXMLFile();
			readXml.loadArchetyeCreation();
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

