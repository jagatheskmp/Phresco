package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class PhpConstantsXml {

	private ReadXMLFile readXml;

	public String PHP_HELLOWORLD_NAME_VALUE = "phpHelloWorldNameValue";
	public String PHP_HELLOWORLD_DESC_VALUE = "phpHelloWorldDescriptionValue";
	public String PHP_TECHNOLGY_VALUE = "technologyPHPValue";		
	public String PHP_BLOG_NAME_VALUE = "phpBlogNameValue";
	public String PHP_BLOG_DESC_VALUE = "phpBlogDescriptionValue";
	public String PHP_HELLOWORLD_EDIT_APP_LINK = "phpHelloWorldEditApplicationLinkXpath"; 
	public String PHP_BLOG_EDIT_APP_LINK = "phpBlogEditApplicationLinkXpath";	
	public String PHP_SERVER_VALUE = "phpServerValue";
	public String PHP_DB_VALUE = "phpDatabaseValue";	
	public String PHP_PILOT_PROJECT_BLOG_VALUE = "phpPilotProjectBlogValue"; 
	public String PHP_CONF_SERVER_NAME_VALUE = "phpConfigurationServerNameValue";
	public String PHP_CONF_SERVER_DESC_VALUE = "phpConfigurationServerDescriptionValue";
	public String PHP_CONF_SERVER_ENV_VALUE = "phpConfigurationServerEnvironmentValue";
	public String PHP_CONF_SERVER_TYPE_VALUE = "phpConfigurationServerTypeValue";
	public String PHP_CONF_SERVER_PROTOCOL_HTTP_VALUE = "phpConfigurationServerProtocolHttpValue";
	public String PHP_CONF_SERVER_PROTOCOL_HTTPS_VALUE = "phpConfigurationServerProtocolHttpsValue";
	public String PHP_CONF_SERVER_HOST_VALUE = "phpConfigurationServerHostValue";
	public String PHP_CONF_SERVER_PORT_VALUE = "phpConfigurationServerPortValue";	
	public String PHP_CONF_SERVER_DEPLOY_DIRECTORY = "phpConfigurationServerDeployDirectoryValue";
	public String PHP_CONF_SERVER_CONTEXT_HELLOWORLD = "phpConfigurationServerContextValueHelloWorld";
	public String PHP_CONF_SERVER_CONTEXT_BLOG = "phpConfigurationServerContextValueBlog";
	public String PHP_CONF_DATABASE_NAME_VALUE = "phpConfigurationDatabaseNameValue";
	public String PHP_CONF_DATABASE_DESC_VALUE = "phpConfigurationDatabaseDescriptionValue";
	public String PHP_CONF_DATABASE_ENV_VALUE = "phpConfigurationDatabaseEnvironmentValue";
	public String PHP_CONF_DATABASE_TYPE_VALUE = "phpConfigurationDatabaseTypeValue";
	public String PHP_CONF_DATABASE_HOST_VALUE = "phpConfigurationDatabaseHostValue";
	public String PHP_CONF_DATABASE_PORT_VALUE = "phpConfigurationDatabasePortValue";
	public String PHP_CONF_DB_USERNAME_VALUE = "phpConfigurationDatabaseDBUsernameValue";
	public String PHP_CONF_DB_NAME_HELLOWORLD = "phpConfigurationDatabaseDBNameValueHelloWorld";	
	public String PHP_CONF_DB_NAME_BLOG = "phpConfigurationDatabaseDBNameValueBlog"; 
	public String FUNCTIONAL_FRAMEWORK = "functionalFramework";

	public PhpConstantsXml()
	{
		try {
			readXml = new ReadXMLFile();
			readXml.loadPhpConstants();
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