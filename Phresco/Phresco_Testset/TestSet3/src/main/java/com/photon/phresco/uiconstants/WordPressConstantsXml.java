package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;



public class WordPressConstantsXml {
	private ReadXMLFile readXml; 
	
	public String WORDPRESS_HELLOWORLD_NAME_VALUE="wordPressHelloWorldNameValue";
	public String WORDPRESS_HELLOWORLD_CODE_VALUE="wordPressHelloWorldCodeValue";
	public String WORDPRESS_HELLOWORLD_DESCRIPTION_VALUE="wordPressHelloWorldDescriptionValue";
	public String WORDPRESS_HELLOWORLD_VERSION_VALUE="wordPressHelloWorldVersionValue";
	public String WORDPRESS_TECHNOLGY_VALUE="technologyWordPressValue";


	public String WORDPRESS_PROJECT_HELLOWORLD_CHECK_BOX="wordPressHelloWorldCheckBoxXpath";
	public String WORDPRESS_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK="wordPressHelloWorldEditApplicationLinkXpath";

	
	public String WORDPRESS_SERVER_VALUE="wordPressServerValue";
	public String WORDPRESS_DATABASE_VALUE="wordPressDatabaseValue";

	public String WORDPRESS_CONFIGURATION_SERVER_NAME_VALUE="wordPressConfigurationServerNameValue";
	public String WORDPRESS_CONFIGURATION_SERVER_DESCRIPTION_VALUE="wordPressConfigurationServerDescriptionValue";
	public String WORDPRESS_CONFIGURATION_SERVER_ENVIRONMENT_VALUE="wordPressConfigurationServerEnvironmentValue";
	public String WORDPRESS_CONFIGURATION_SERVER_TYPE_VALUE="wordPressConfigurationServerTypeValue";
	public String WORDPRESS_CONFIGURATION_SERVER_PROTOCOL_HTTP_VALUE="wordPressConfigurationServerProtocolHttpValue";
	public String WORDPRESS_CONFIGURATION_SERVER_PROTOCOL_HTTPS_VALUE="wordPressConfigurationServerProtocolHttpsValue";
	public String WORDPRESS_CONFIGURATION_SERVER_HOST_VALUE="wordPressConfigurationServerHostValue";
	public String WORDPRESS_CONFIGURATION_SERVER_PORT_VALUE="wordPressConfigurationServerPortValue";
	
	public String WORDPRESS_CONFIGURATION_SERVER_DEPLOY_DIRECTORY_VALUE="wordPressConfigurationServerDeployDirectoryValue";
	public String WORDPRESS_CONFIGURATION_SERVER_CONTEXT_VALUE_HELLOWORLD="wordPressConfigurationServerContextValueHelloWorld";

	public String WORDPRESS_CONFIGURATION_DATABASE_NAME_VALUE="wordPressConfigurationDatabaseNameValue";
	public String WORDPRESS_CONFIGURATION_DATABASE_DESCRIPTION_VALUE="wordPressConfigurationDatabaseDescriptionValue";
	public String WORDPRESS_CONFIGURATION_DATABASE_ENVIRONMENT_VALUE="wordPressConfigurationDatabaseEnvironmentValue";
	public String WORDPRESS_CONFIGURATION_DATABASE_TYPE_VALUE="wordPressConfigurationDatabaseTypeValue";
	public String WORDPRESS_CONFIGURATION_DATABASE_HOST_VALUE="wordPressConfigurationDatabaseHostValue";
	public String WORDPRESS_CONFIGURATION_DATABASE_PORT_VALUE="wordPressConfigurationDatabasePortValue";
	public String WORDPRESS_CONFIGURATION_DATABASE_DB_USERNAME_VALUE="wordPressConfigurationDatabaseDBUsernameValue";
	public String WORDPRESS_CONFIGURATION_DATABASE_DB_NAME_VALUE_HELLOWORLD="wordPressConfigurationDatabaseDBNameValueHelloWorld";
	

	public WordPressConstantsXml() {
		try {
			readXml = new ReadXMLFile();
			readXml.loadWordPressConstants();
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
