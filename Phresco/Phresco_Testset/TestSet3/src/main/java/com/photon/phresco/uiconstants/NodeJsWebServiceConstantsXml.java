package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class NodeJsWebServiceConstantsXml {

	private ReadXMLFile readXml;



	public String NODEJS_SERVICE_HELLOWORLD_NAME_VALUE="nodeJsServiceHelloWorldNameValue";
	public String NODEJS_SERVICE_HELLOWORLD_CODE_VALUE="nodeJsServiceHelloWorldCodeValue";
	public String NODEJS_SERVICE_HELLOWORLD_DESCRIPTION_VALUE="nodeJsServiceHelloWorldDescriptionValue";
	public String NODEJS_SERVICE_HELLOWORLD_VERSION_VALUE="nodeJsServiceHelloWorldVersionValue";
	public String NODEJS_SERVICE_TECHNOLGY_VALUE="technologynodeJsServiceValue";
	
	
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

	public String NODEJS_SERVICE_PROJECT_ESHOP_CHECK_BOX="nodeJsServiceESHOPCheckBoxXpath";
    public String NODEJS_SERVICE_PROJECT_ESHOP_EDIT_APPLICATION_LINK="nodeJsServiceESHOPEditApplicationLinkXpath";
	
	public String NODEJS_SERVICE_ESHOP_NAME_VALUE="nodeJsServiceESHOPNameValue";
	public String NODEJS_SERVICE_ESHOP_CODE_VALUE="nodeJsServiceESHOPCodeValue";
	public String NODEJS_SERVICE_ESHOP_DESCRIPTION_VALUE="nodeJsServiceESHOPDescriptionValue";
	public String NODEJS_SERVICE_ESHOP_VERSION_VALUE="nodeJsServiceESHOPVersionValue";

    
   	public String NODEJS_SERVICE_APP_INFO_PILOT_PROJECT_ESHOP_VALUE="nodeJsServicePilotProjectESHOPValue";
   	
   	public String NODEJS_SERVICE_CONFIGURATION_SERVER_CONTEXT_VALUE_ESHOP="nodeJsServiceConfigurationServerContextValueESHOP";
   	public String NODEJS_SERVICE_CONFIGURATION_DATABASE_DB_NAME_VALUE_ESHOP="nodeJsServiceConfigurationDatabaseDBNameValueESHOP";


	public NodeJsWebServiceConstantsXml()
	{
		try {
			readXml = new ReadXMLFile();
			readXml.loadNodeJsWebServiceConstants();
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
		}
		catch (Exception localException) {
			throw new RuntimeException("Loading "
					+ super.getClass().getSimpleName() + " failed",
					localException);
		}
	}
}