
package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class Drupal6ConstantsXml {

	private ReadXMLFile readXml;

	public String DRUPAL6_HELLOWORLD_NAME_VALUE = "drupal6HelloWorldNameValue";
	public String DRUPAL6_HELLOWORLD_DESC_VALUE = "drupal6HelloWorldDescriptionValue";
	public String DRUPAL6_TECHNOLGY_VALUE = "technologyDrupal6Value";
	public String DRUPAL6_HELLOWORLD_EDIT_APP_LINK = "drupal6HelloWorldEditApplicationLinkXpath";
  	public String DRUPAL6_SERVER_VALUE = "drupal6ServerValue";
	public String DRUPAL6_DB_VALUE = "drupal6DatabaseValue";
	public String DRUPAL6_CONF_SERVER_NAME_VALUE = "drupal6ConfigurationServerNameValue";
	public String DRUPAL6_CONF_SERVER_DESC_VALUE = "drupal6ConfigurationServerDescriptionValue";
	public String DRUPAL6_CONF_SERVER_ENV_VALUE = "drupal6ConfigurationServerEnvironmentValue";
	public String DRUPAL6_CONF_SERVER_TYPE_VALUE = "drupal6ConfigurationServerTypeValue";
	public String DRUPAL6_CONF_SERVER_PROTOCOL_HTTP_VALUE = "drupal6ConfigurationServerProtocolHttpValue";
	public String DRUPAL6_CONF_SERVER_PROTOCOL_HTTPS_VALUE = "drupal6ConfigurationServerProtocolHttpsValue";
	public String DRUPAL6_CONF_SERVER_HOST_VALUE = "drupal6ConfigurationServerHostValue";
	public String DRUPAL6_CONF_SERVER_PORT_VALUE = "drupal6ConfigurationServerPortValue";
	public String DRUPAL6_CONF_SERVER_DEPLOY_DIRECTORY_VALUE = "drupal6ConfigurationServerDeployDirectoryValue";
	public String DRUPAL6_CONF_SERVER_CONTEXT_HELLOWORLD = "drupal6ConfigurationServerContextValueHelloWorld";
	public String DRUPAL6_CONF_DATABASE_NAME_VALUE = "drupal6ConfigurationDatabaseNameValue";
	public String DRUPAL6_CONF_DATABASE_DESC_VALUE = "drupal6ConfigurationDatabaseDescriptionValue";
	public String DRUPAL6_CONF_DATABASE_ENV_VALUE = "drupal6ConfigurationDatabaseEnvironmentValue";
	public String DRUPAL6_CONF_DATABASE_TYPE_VALUE = "drupal6ConfigurationDatabaseTypeValue";
	public String DRUPAL6_CONF_DATABASE_HOST_VALUE = "drupal6ConfigurationDatabaseHostValue";
	public String DRUPAL6_CONF_DATABASE_PORT_VALUE = "drupal6ConfigurationDatabasePortValue";
	public String DRUPAL6_CONF_DB_USERNAME_VALUE = "drupal6ConfigurationDatabaseDBUsernameValue";
	public String DRUPAL6_CONF_DB_NAME = "drupal6ConfigurationDatabaseDBNameValueHelloWorld";
 	public String FUNCTIONAL_FRAMEWORK = "functionalFramework";
 	
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
