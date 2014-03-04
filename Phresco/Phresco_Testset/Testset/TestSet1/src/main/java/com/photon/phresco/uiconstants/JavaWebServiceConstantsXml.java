package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class JavaWebServiceConstantsXml {
	private ReadXMLFile readXml;
	
	public String JAVA_WS_HELLOWORLD_NAME_VALUE = "javaWebServiceHelloWorldNameValue";
	public String JAVA_WS_HELLOWORLD_DESC_VALUE = "javaWebServiceHelloWorldDescriptionValue";
	public String JAVA_WS_TECHNOLGY_VALUE = "technologyjavaWebServiceValue";
	public String JAVA_WS_ESHOP_NAME_VALUE = "javaWebServiceESHOPNameValue";
	public String JAVA_WS_ESHOP_DESC_VALUE = "javaWebServiceESHOPDescriptionValue";
	public String JAVA_WS_PROJECT_HELLOWORLD_EDIT_APP_LINK = "javaWebServiceHelloWorldEditApplicationLinkXpath";
	public String JAVA_WS_PROJECT_ESHOP_EDIT_APP_LINK = "javaWebServiceESHOPEditApplicationLinkXpath";
	public String JAVA_WS_SERVER_VALUE = "javaWebServiceServerValue";
	public String JAVA_WS_DB_VALUE = "javaWebServiceDatabaseValue";
	public String JAVA_WS_PILOT_PROJECT_VALUE = "javaWebServicePilotProjectESHOPValue";
	public String JAVA_WS_CONF_SERVER_NAME_VALUE = "javaWebServiceConfigurationServerNameValue";
	public String JAVA_WS_CONF_SERVER_DESC_VALUE = "javaWebServiceConfigurationServerDescriptionValue";
	public String JAVA_WS_CONF_SERVER_ENV_VALUE = "javaWebServiceConfigurationServerEnvironmentValue";
	public String JAVA_WS_CONF_SERVER_TYPE_VALUE = "javaWebServiceConfigurationServerTypeValue";
	public String JAVA_WS_CONF_SERVER_PROTOCOL_HTTP_VALUE = "javaWebServiceConfigurationServerProtocolHttpValue";
	public String JAVA_WS_CONF_SERVER_PROTOCOL_HTTPS_VALUE = "javaWebServiceConfigurationServerProtocolHttpsValue";
	public String JAVA_WS_CONF_SERVER_HOST_VALUE = "javaWebServiceConfigurationServerHostValue";
	public String JAVA_WS_CONF_SERVER_PORT_VALUE = "javaWebServiceConfigurationServerPortValue";
	public String JAVA_WS_CONF_SERVER_DEPLOY_DIRECTORY = "javaWebServiceConfigurationServerDeployDirectoryValue";
	public String JAVA_WS_CONF_SERVER_CONTEXT_HELLOWORLD = "javaWebServiceConfigurationServerContextValueHelloWorld";
	public String JAVA_WS_CONF_SERVER_CONTEXT_ESHOP = "javaWebServiceConfigurationServerContextValueESHOP";
	public String JAVA_WS_CONF_DATABASE_NAME_VALUE = "javaWebServiceConfigurationDatabaseNameValue";
	public String JAVA_WS_CONF_DATABASE_DESC_VALUE = "javaWebServiceConfigurationDatabaseDescriptionValue";
	public String JAVA_WS_CONF_DATABASE_ENV_VALUE = "javaWebServiceConfigurationDatabaseEnvironmentValue";
	public String JAVA_WS_CONF_DATABASE_TYPE_VALUE = "javaWebServiceConfigurationDatabaseTypeValue";
	public String JAVA_WS_CONF_DATABASE_HOST_VALUE = "javaWebServiceConfigurationDatabaseHostValue";
	public String JAVA_WS_CONF_DATABASE_PORT_VALUE = "javaWebServiceConfigurationDatabasePortValue";
	public String JAVA_WS_CONF_DB_USERNAME_VALUE = "javaWebServiceConfigurationDatabaseDBUsernameValue";
	public String JAVA_WS_CONF_DB_NAME_HELLOWORLD = "javaWebServiceConfigurationDatabaseDBNameValueHelloworld";
	public String JAVA_WS_CONF_DB_NAME_ESHOP = "javaWebServiceConfigurationDatabaseDBNameValueESHOP";
	public String FUNCTIONAL_FRAMEWORK = "functionalFramework";

	public JavaWebServiceConstantsXml()
	{
		try {
			readXml = new ReadXMLFile();
			readXml.loadJavaWebServiceConstants();
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