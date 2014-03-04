
package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class Drupal6ConstantsXml {

	private ReadXMLFile readXml;


	public String DRUPAL6_HELLOWORLD_NAME_VALUE="drupal6HelloWorldNameValue";
	public String DRUPAL6_HELLOWORLD_CODE_VALUE="drupal6HelloWorldCodeValue";
	public String DRUPAL6_HELLOWORLD_DESCRIPTION_VALUE="drupal6HelloWorldDescriptionValue";
	public String DRUPAL6_HELLOWORLD_VERSION_VALUE="drupal6HelloWorldVersionValue";
	public String DRUPAL6_TECHNOLGY_VALUE="technologyDrupal6Value";
	
	
	public String DRUPAL6_PROJECT_HELLOWORLD_CHECK_BOX="drupal6HelloWorldCheckBoxXpath";
    public String DRUPAL6_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK="drupal6HelloWorldEditApplicationLinkXpath";
    
  	public String DRUPAL6_SERVER_VALUE="drupal6ServerValue";
	public String DRUPAL6_DATABASE_VALUE="drupal6DatabaseValue";
	
	public String DRUPAL6_CONFIGURATION_SERVER_NAME_VALUE="drupal6ConfigurationServerNameValue";
	public String DRUPAL6_CONFIGURATION_SERVER_DESCRIPTION_VALUE="drupal6ConfigurationServerDescriptionValue";
	public String DRUPAL6_CONFIGURATION_SERVER_ENVIRONMENT_VALUE="drupal6ConfigurationServerEnvironmentValue";
	public String DRUPAL6_CONFIGURATION_SERVER_TYPE_VALUE="drupal6ConfigurationServerTypeValue";
	public String DRUPAL6_CONFIGURATION_SERVER_PROTOCOL_HTTP_VALUE="drupal6ConfigurationServerProtocolHttpValue";
	public String DRUPAL6_CONFIGURATION_SERVER_PROTOCOL_HTTPS_VALUE="drupal6ConfigurationServerProtocolHttpsValue";
	public String DRUPAL6_CONFIGURATION_SERVER_HOST_VALUE="drupal6ConfigurationServerHostValue";
	public String DRUPAL6_CONFIGURATION_SERVER_PORT_VALUE="drupal6ConfigurationServerPortValue";
	
	public String DRUPAL6_CONFIGURATION_SERVER_DEPLOY_DIRECTORY_VALUE="drupal6ConfigurationServerDeployDirectoryValue";
	public String DRUPAL6_CONFIGURATION_SERVER_CONTEXT_VALUE_HELLOWORLD="drupal6ConfigurationServerContextValueHelloWorld";

	public String DRUPAL6_CONFIGURATION_DATABASE_NAME_VALUE="drupal6ConfigurationDatabaseNameValue";
	public String DRUPAL6_CONFIGURATION_DATABASE_DESCRIPTION_VALUE="drupal6ConfigurationDatabaseDescriptionValue";
	public String DRUPAL6_CONFIGURATION_DATABASE_ENVIRONMENT_VALUE="drupal6ConfigurationDatabaseEnvironmentValue";
	public String DRUPAL6_CONFIGURATION_DATABASE_TYPE_VALUE="drupal6ConfigurationDatabaseTypeValue";
	public String DRUPAL6_CONFIGURATION_DATABASE_HOST_VALUE="drupal6ConfigurationDatabaseHostValue";
	public String DRUPAL6_CONFIGURATION_DATABASE_PORT_VALUE="drupal6ConfigurationDatabasePortValue";
	public String DRUPAL6_CONFIGURATION_DATABASE_DB_USERNAME_VALUE="drupal6ConfigurationDatabaseDBUsernameValue";
	public String DRUPAL6_CONFIGURATION_DATABASE_DB_NAME_VALUE_HELLOWORLD="drupal6ConfigurationDatabaseDBNameValueHelloWorld";
	
		 

	public Drupal6ConstantsXml()
	{
		try {
			readXml = new ReadXMLFile();
			readXml.loadDrupal6Constants();
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
