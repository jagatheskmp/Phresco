package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class JavaWebServiceConstantsXml {

	private ReadXMLFile readXml;



	public String JAVAWEB_SERVICE_HELLOWORLD_NAME_VALUE="javaWebServiceHelloWorldNameValue";
	public String JAVAWEB_SERVICE_HELLOWORLD_CODE_VALUE="javaWebServiceHelloWorldCodeValue";
	public String JAVAWEB_SERVICE_HELLOWORLD_DESCRIPTION_VALUE="javaWebServiceHelloWorldDescriptionValue";
	public String JAVAWEB_SERVICE_HELLOWORLD_VERSION_VALUE="javaWebServiceHelloWorldVersionValue";
	public String JAVAWEB_SERVICE_TECHNOLGY_VALUE="technologyjavaWebServiceValue";
	
	
	public String JAVAWEB_SERVICE_PROJECT_HELLOWORLD_CHECK_BOX="javaWebServiceHelloWorldCheckBoxXpath";
    public String JAVAWEB_SERVICE_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK="javaWebServiceHelloWorldEditApplicationLinkXpath";
    
   
	public String JAVAWEB_SERVICE_SERVER_VALUE="javaWebServiceServerValue";
	public String JAVAWEB_SERVICE_DATABASE_VALUE="javaWebServiceDatabaseValue";
	
	public String JAVAWEB_SERVICE_CONFIGURATION_SERVER_NAME_VALUE="javaWebServiceConfigurationServerNameValue";
	public String JAVAWEB_SERVICE_CONFIGURATION_SERVER_DESCRIPTION_VALUE="javaWebServiceConfigurationServerDescriptionValue";
	public String JAVAWEB_SERVICE_CONFIGURATION_SERVER_ENVIRONMENT_VALUE="javaWebServiceConfigurationServerEnvironmentValue";
	public String JAVAWEB_SERVICE_CONFIGURATION_SERVER_TYPE_VALUE="javaWebServiceConfigurationServerTypeValue";
	public String JAVAWEB_SERVICE_CONFIGURATION_SERVER_PROTOCOL_HTTP_VALUE="javaWebServiceConfigurationServerProtocolHttpValue";
	public String JAVAWEB_SERVICE_CONFIGURATION_SERVER_PROTOCOL_HTTPS_VALUE="javaWebServiceConfigurationServerProtocolHttpsValue";
	public String JAVAWEB_SERVICE_CONFIGURATION_SERVER_HOST_VALUE="javaWebServiceConfigurationServerHostValue";
	public String JAVAWEB_SERVICE_CONFIGURATION_SERVER_PORT_VALUE="javaWebServiceConfigurationServerPortValue";
	public String JAVAWEB_SERVICE_CONFIGURATION_SERVER_DEPLOY_DIRECTORY_VALUE="javaWebServiceConfigurationServerDeployDirectoryValue";
	public String JAVAWEB_SERVICE_CONFIGURATION_SERVER_CONTEXT_VALUE_HELLOWORLD="javaWebServiceConfigurationServerContextValueHelloWorld";

	public String JAVAWEB_SERVICE_CONFIGURATION_DATABASE_NAME_VALUE="javaWebServiceConfigurationDatabaseNameValue";
	public String JAVAWEB_SERVICE_CONFIGURATION_DATABASE_DESCRIPTION_VALUE="javaWebServiceConfigurationDatabaseDescriptionValue";
	public String JAVAWEB_SERVICE_CONFIGURATION_DATABASE_ENVIRONMENT_VALUE="javaWebServiceConfigurationDatabaseEnvironmentValue";
	public String JAVAWEB_SERVICE_CONFIGURATION_DATABASE_TYPE_VALUE="javaWebServiceConfigurationDatabaseTypeValue";
	public String JAVAWEB_SERVICE_CONFIGURATION_DATABASE_HOST_VALUE="javaWebServiceConfigurationDatabaseHostValue";
	public String JAVAWEB_SERVICE_CONFIGURATION_DATABASE_PORT_VALUE="javaWebServiceConfigurationDatabasePortValue";
	public String JAVAWEB_SERVICE_CONFIGURATION_DATABASE_DB_USERNAME_VALUE="javaWebServiceConfigurationDatabaseDBUsernameValue";
	
	
	public String JAVAWEB_SERVICE_PROJECT_ESHOP_CHECK_BOX="javaWebServiceESHOPCheckBoxXpath";
    public String JAVAWEB_SERVICE_PROJECT_ESHOP_EDIT_APPLICATION_LINK="javaWebServiceESHOPEditApplicationLinkXpath";
	
	public String JAVAWEB_SERVICE_ESHOP_NAME_VALUE="javaWebServiceESHOPNameValue";
	public String JAVAWEB_SERVICE_ESHOP_CODE_VALUE="javaWebServiceESHOPCodeValue";
	public String JAVAWEB_SERVICE_ESHOP_DESCRIPTION_VALUE="javaWebServiceESHOPDescriptionValue";
	public String JAVAWEB_SERVICE_ESHOP_VERSION_VALUE="javaWebServiceESHOPVersionValue";

   
   	public String JAVAWEB_SERVICE_APP_INFO_PILOT_PROJECT_ESHOP_VALUE="javaWebServicePilotProjectESHOPValue";
   	
   	public String JAVAWEB_SERVICE_CONFIGURATION_SERVER_CONTEXT_VALUE_ESHOP="javaWebServiceConfigurationServerContextValueESHOP";
   	public String JAVAWEB_SERVICE_CONFIGURATION_DATABASE_DB_NAME_VALUE_ESHOP="javaWebServiceConfigurationDatabaseDBNameValueESHOP"; 
    
    
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