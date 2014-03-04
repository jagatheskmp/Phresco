package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class NodeJsWebServiceConstantsXml {

	private ReadXMLFile readXml;

	public String NODEJS_HELLOWORLD_NAME_VALUE = "nodeJsServiceHelloWorldNameValue";
	public String NODEJS_HELLOWORLD_DESC_VALUE = "nodeJsServiceHelloWorldDescriptionValue";
	public String NODEJS_TECHNOLGY_VALUE = "technologynodeJsServiceValue";
	public String NODEJS_ESHOP_NAME_VALUE = "nodeJsServiceESHOPNameValue";
	public String NODEJS_ESHOP_DESC_VALUE = "nodeJsServiceESHOPDescriptionValue";
	public String NODEJS_HELLOWORLD_EDIT_APP_LINK = "nodeJsServiceHelloWorldEditApplicationLinkXpath";
	public String NODEJS_ESHOP_EDIT_APP_LINK = "nodeJsServiceESHOPEditApplicationLinkXpath";    
	public String NODEJS_SERVER_VALUE = "nodeJsServiceServerValue";
	public String NODEJS_DB_VALUE = "nodeJsServiceDatabaseValue";
	public String NODEJS_PILOT_PROJECT_VALUE = "nodeJsServicePilotProjectESHOPValue";
	public String NODEJS_SERVICE_CONF_SERVER_NAME_VALUE = "nodeJsServiceConfigurationServerNameValue";
	public String NODEJS_SERVICE_CONF_SERVER_DESC_VALUE = "nodeJsServiceConfigurationServerDescriptionValue";
	public String NODEJS_SERVICE_CONF_SERVER_ENV_VALUE = "nodeJsServiceConfigurationServerEnvironmentValue";
	public String NODEJS_SERVICE_CONF_SERVER_TYPE_VALUE = "nodeJsServiceConfigurationServerTypeValue";
	public String NODEJS_SERVICE_CONF_SERVER_PROTOCOL_HTTP_VALUE = "nodeJsServiceConfigurationServerProtocolHttpValue";
	public String NODEJS_SERVICE_CONF_SERVER_PROTOCOL_HTTPS_VALUE = "nodeJsServiceConfigurationServerProtocolHttpsValue";
	public String NODEJS_SERVICE_CONF_SERVER_HOST_VALUE = "nodeJsServiceConfigurationServerHostValue";
	public String NODEJS_SERVICE_CONF_SERVER_PORT_VALUE = "nodeJsServiceConfigurationServerPortValue";
	public String NODEJS_SERVICE_CONF_SERVER_CONTEXT_HELLOWORLD = "nodeJsServiceConfigurationServerContextValueHelloWorld";
	public String NODEJS_SERVICE_CONF_SERVER_CONTEXT_ESHOP = "nodeJsServiceConfigurationServerContextValueESHOP";
	public String NODEJS_SERVICE_CONF_DATABASE_NAME_VALUE = "nodeJsServiceConfigurationDatabaseNameValue";
	public String NODEJS_SERVICE_CONF_DATABASE_DESC_VALUE = "nodeJsServiceConfigurationDatabaseDescriptionValue";
	public String NODEJS_SERVICE_CONF_DATABASE_ENV_VALUE = "nodeJsServiceConfigurationDatabaseEnvironmentValue";
	public String NODEJS_SERVICE_CONF_DATABASE_TYPE_VALUE = "nodeJsServiceConfigurationDatabaseTypeValue";
	public String NODEJS_SERVICE_CONF_DATABASE_HOST_VALUE = "nodeJsServiceConfigurationDatabaseHostValue";
	public String NODEJS_SERVICE_CONF_DATABASE_PORT_VALUE = "nodeJsServiceConfigurationDatabasePortValue";
	public String NODEJS_SERVICE_CONF_DB_USERNAME = "nodeJsServiceConfigurationDatabaseDBUsernameValue";
	public String NODEJS_SERVICE_CONF_DB_NAME_HELLOWORLD = "nodeJsServiceConfigurationDatabaseDBNameValueHelloWorld";
	public String NODEJS_SERVICE_CONF_DB_NAME_ESHOP = "nodeJsServiceConfigurationDatabaseDBNameValueESHOP";
	public String FUNCTIONAL_FRAMEWORK = "functionalFramework";


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