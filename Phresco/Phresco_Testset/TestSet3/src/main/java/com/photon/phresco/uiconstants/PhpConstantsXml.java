package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class PhpConstantsXml {

	private ReadXMLFile readXml;



	public String PHP_HELLOWORLD_NAME_VALUE="phpHelloWorldNameValue";
	public String PHP_HELLOWORLD_CODE_VALUE="phpHelloWorldCodeValue";
	public String PHP_HELLOWORLD_DESCRIPTION_VALUE="phpHelloWorldDescriptionValue";
	public String PHP_HELLOWORLD_VERSION_VALUE="phpHelloWorldVersionValue";
	public String PHP_TECHNOLGY_VALUE="technologyPHPValue";
	
	
	public String PHP_PROJECT_HELLOWORLD_CHECK_BOX="phpHelloWorldCheckBoxXpath";
    public String PHP_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK="phpHelloWorldEditApplicationLinkXpath";
 
	public String PHP_SERVER_VALUE="phpServerValue";
	public String PHP_DATABASE_VALUE="phpDatabaseValue";
	
	public String PHP_CONFIGURATION_SERVER_NAME_VALUE="phpConfigurationServerNameValue";
	public String PHP_CONFIGURATION_SERVER_DESCRIPTION_VALUE="phpConfigurationServerDescriptionValue";
	public String PHP_CONFIGURATION_SERVER_ENVIRONMENT_VALUE="phpConfigurationServerEnvironmentValue";
	public String PHP_CONFIGURATION_SERVER_TYPE_VALUE="phpConfigurationServerTypeValue";
	public String PHP_CONFIGURATION_SERVER_PROTOCOL_HTTP_VALUE="phpConfigurationServerProtocolHttpValue";
	public String PHP_CONFIGURATION_SERVER_PROTOCOL_HTTPS_VALUE="phpConfigurationServerProtocolHttpsValue";
	public String PHP_CONFIGURATION_SERVER_HOST_VALUE="phpConfigurationServerHostValue";
	public String PHP_CONFIGURATION_SERVER_PORT_VALUE="phpConfigurationServerPortValue";
	
	public String PHP_CONFIGURATION_SERVER_DEPLOY_DIRECTORY_VALUE="phpConfigurationServerDeployDirectoryValue";
	public String PHP_CONFIGURATION_SERVER_CONTEXT_VALUE_HELLOWORLD="phpConfigurationServerContextValueHelloWorld";

	public String PHP_CONFIGURATION_DATABASE_NAME_VALUE="phpConfigurationDatabaseNameValue";
	public String PHP_CONFIGURATION_DATABASE_DESCRIPTION_VALUE="phpConfigurationDatabaseDescriptionValue";
	public String PHP_CONFIGURATION_DATABASE_ENVIRONMENT_VALUE="phpConfigurationDatabaseEnvironmentValue";
	public String PHP_CONFIGURATION_DATABASE_TYPE_VALUE="phpConfigurationDatabaseTypeValue";
	public String PHP_CONFIGURATION_DATABASE_HOST_VALUE="phpConfigurationDatabaseHostValue";
	public String PHP_CONFIGURATION_DATABASE_PORT_VALUE="phpConfigurationDatabasePortValue";
	public String PHP_CONFIGURATION_DATABASE_DB_USERNAME_VALUE="phpConfigurationDatabaseDBUsernameValue";
	public String PHP_CONFIGURATION_DATABASE_DB_NAME_VALUE_HELLOWORLD="phpConfigurationDatabaseDBNameValueHelloWorld";
	
		 
	public String PHP_PROJECT_BLOG_CHECK_BOX="phpBlogCheckBoxXpath";
    public String PHP_PROJECT_BLOG_EDIT_APPLICATION_LINK="phpBlogEditApplicationLinkXpath";
	
	public String PHP_BLOG_NAME_VALUE="phpBlogNameValue";
	public String PHP_BLOG_CODE_VALUE="phpBlogCodeValue";
	public String PHP_BLOG_DESCRIPTION_VALUE="phpBlogDescriptionValue";
	public String PHP_BLOG_VERSION_VALUE="phpBlogVersionValue";

   
   	public String PHP_APP_INFO_PILOT_PROJECT_BLOG_VALUE="phpPilotProjectBlogValue";
   	
   	public String PHP_CONFIGURATION_SERVER_CONTEXT_VALUE_BLOG="phpConfigurationServerContextValueBlog";
   	public String PHP_CONFIGURATION_DATABASE_DB_NAME_VALUE_BLOG="phpConfigurationDatabaseDBNameValueBlog";

    
   	public String ENV_NAME="envName";
   	public String ENV_DESC="envDesc";
   	public String ENV_TESTING="envTesting";

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