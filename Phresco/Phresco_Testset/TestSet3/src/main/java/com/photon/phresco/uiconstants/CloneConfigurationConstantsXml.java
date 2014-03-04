package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class CloneConfigurationConstantsXml {

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
		
		
		
		public String JQUERY_WIDGET_ESHOP_PROJECT_NAME="jQueryMobWidgetEshopNameValue";
		public String JQUERY_WIDGET_ESHOP_PROJECT_CODE="jQueryMobWidgetEshopCodeValue";
		public String JQUERY_WIDGET_ESHOP_PROJECT_DESCRIPTION="jQueryMobWidgetEshopDescriptionValue";
		public String JQUERY_WIDGET_ESHOP_PROJECT_VERSION="jQueryMobWidgetEshopVersionValue";

		public String WEBLAYER_HTML5_VALUE="html5WebLayerValue";
		public String JQUERY_MOB_WIDGET_VALUE="jQueryMobWidgetValue";
		public String JQUERY_MOB_WIDGET_VERSION_VALUE="jQueryMobWidgetVersionValue";
		
		public String JQUERY_MOB_WIDGET_ESHOP_CHECKBOX="jQueryMobWidgetEshopCheckBoxXpath";
		public String JQUERY_MOB_WIDGET_ESHOP_EDIT_LINK="jQueryMobWidgetEshopEditApplicationLinkXpath";

		public String PILOT_PROJECT_ESHOP="pilotProjectEshopValue";
			
		public String CONFIG_SERVER_NAME_VALUE="configServNameValue";
	  	public String CONFIG_SERVER_DESC_VALUE="configservDescValue";
	  	public String CONFIG_SERVER_ENV_VALUE="configServEnvValue";
	  	public String CONFIG_SERVER_VALUE="configServValue";
	  	public String CONFIG_SERVER_PROTOCOL_VALUE="configServProtocolValue";
	  	public String CONFIG_SERVER_HOST_VALUE="configServHostValue";
	 	public String CONFIG_SERVER_PORT_VALUE="configServPortValue";
	    public String CONFIG_SERVER_DEPLOY_VALUE="configServDeployDirectoryValue";
	      
	    public String JQUERY_MOB_WIDGET_ESHOP_SERVER_CONTEXT_VALUE="jqueryconfigServContextValueEshop";
	 
	  	public String CONFIG_WEBSERVICE_NAME_VALUE="configWebServiceNameValue";
	 	public String CONFIG_WEBSERVICE_DESC_VALUE="configWebserviceDesc";
	  	public String CONFIG_WEBSERVICE_ENV_VALUE="configWebserviceEnvValue";
	  	public String CONFIG_WEBSERVICE_VALUE="configWebserviceValue";
	  	public String CONFIG_WEBSERVICE_PROTOCAOL_VALUE="configWebserviceProtocolValue";
	  	public String CONFIG_WEBSERVICE_HOST_VALUE="configWebserviceHostValue";
	  	public String CONFIG_WEBSERVICE_PORT_VALUE="configWebservicePortValue";
	    public String JQUERY_WIDGET_ESHOP_CONFIG_WEBSERVICE_CONTEXT="jqueryconfigWebserviceContextEshopValue";
	    
	   	public String ENV_NAME="envName";
	   	public String ENV_DESC="envDesc";
	   	public String ENV_TESTING="envTesting";

		public CloneConfigurationConstantsXml()
		{
			try {
				readXml = new ReadXMLFile();
				readXml.loadCloneConfiguration();
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