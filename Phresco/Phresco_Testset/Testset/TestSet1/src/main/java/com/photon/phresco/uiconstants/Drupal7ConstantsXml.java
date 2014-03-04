
package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class Drupal7ConstantsXml {

	private ReadXMLFile readXml;

	public String DRUPAL7_HELLOWORLD_NAME_VALUE = "drupal7HelloWorldNameValue";
	public String DRUPAL7_HELLOWORLD_DESC_VALUE = "drupal7HelloWorldDescriptionValue";
	public String DRUPAL7_TECHNOLGY_VALUE = "technologyDrupal7Value";
	public String DRUPAL7_ESHOP_NAME_VALUE = "drupal7EshopNameValue";
	public String DRUPAL7_ESHOP_DESC_VALUE = "drupal7EshopDescriptionValue";
	public String DRUPAL7_HELLOWORLD_EDIT_APP_LINK = "drupal7HelloWorldEditApplicationLinkXpath";
	public String DRUPAL7_ESHOP_EDIT_APP_LINK = "drupal7EshopEditApplicationLinkXpath";
	public String DRUPAL7_SERVER_VALUE = "drupal7ServerValue";
	public String DRUPAL7_DB_VALUE = "drupal7DatabaseValue";	
	public String DRUPAL7_PILOT_PROJECT_VALUE = "drupal7PilotProjectEshopValue";
	public String DRUPAL7_CONF_SERVER_NAME_VALUE = "drupal7ConfigurationServerNameValue";
	public String DRUPAL7_CONF_SERVER_DESC_VALUE = "drupal7ConfigurationServerDescriptionValue";
	public String DRUPAL7_CONF_SERVER_ENV_VALUE = "drupal7ConfigurationServerEnvironmentValue";
	public String DRUPAL7_CONF_SERVER_TYPE_VALUE = "drupal7ConfigurationServerTypeValue";
	public String DRUPAL7_CONF_SERVER_PROTOCOL_HTTP_VALUE = "drupal7ConfigurationServerProtocolHttpValue";
	public String DRUPAL7_CONF_SERVER_PROTOCOL_HTTPS_VALUE = "drupal7ConfigurationServerProtocolHttpsValue";
	public String DRUPAL7_CONF_SERVER_HOST_VALUE = "drupal7ConfigurationServerHostValue";
	public String DRUPAL7_CONF_SERVER_PORT_VALUE = "drupal7ConfigurationServerPortValue";
	public String DRUPAL7_CONF_SERVER_DEPLOY_DIRECTORY_VALUE = "drupal7ConfigurationServerDeployDirectoryValue";
	public String DRUPAL7_CONF_SERVER_CONTEXT_HELLOWORLD = "drupal7ConfigurationServerContextValueHelloWorld";
	public String DRUPAL7_CONF_SERVER_CONTEXT_ESHOP = "drupal7ConfigurationServerContextValueEshop";
	public String DRUPAL7_CONF_DATABASE_NAME_VALUE = "drupal7ConfigurationDatabaseNameValue";
	public String DRUPAL7_CONF_DATABASE_DESC_VALUE = "drupal7ConfigurationDatabaseDescriptionValue";
	public String DRUPAL7_CONF_DATABASE_ENV_VALUE = "drupal7ConfigurationDatabaseEnvironmentValue";
	public String DRUPAL7_CONF_DATABASE_TYPE_VALUE = "drupal7ConfigurationDatabaseTypeValue";
	public String DRUPAL7_CONF_DATABASE_HOST_VALUE = "drupal7ConfigurationDatabaseHostValue";
	public String DRUPAL7_CONF_DATABASE_PORT_VALUE = "drupal7ConfigurationDatabasePortValue";
	public String DRUPAL7_CONF_DB_USERNAME_VALUE = "drupal7ConfigurationDatabaseDBUsernameValue";
	public String DRUPAL7_CONF_DB_NAME_HELLOWORLD = "drupal7ConfigurationDatabaseDBNameValueHelloWorld";
	public String DRUPAL7_CONF_DB_NAME_ESHOP = "drupal7ConfigurationDatabaseDBNameValueEshop";
	public String FUNCTIONAL_FRAMEWORK = "functionalFramework";

	public Drupal7ConstantsXml()
	{
		try {
			readXml = new ReadXMLFile();
			readXml.loadDrupal7Constants();
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
