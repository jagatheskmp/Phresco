package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class SharePointConstantsXml {

	private ReadXMLFile readXml;



	public String SHARE_POINT_HELLOWORLD_NAME_VALUE="SharePointHelloWorldNameValue";
	public String SHARE_POINT_HELLOWORLD_CODE_VALUE="SharePointHelloWorldCodeValue";
	public String SHARE_POINT_HELLOWORLD_DESCRIPTION_VALUE="SharePointHelloWorldDescriptionValue";
	public String SHARE_POINT_HELLOWORLD_VERSION_VALUE="SharePointHelloWorldVersionValue";
	public String SHARE_POINT_TECHNOLGY_VALUE="technologySharePointValue";
	
	
	public String SHARE_POINT_PROJECT_HELLOWORLD_CHECK_BOX="SharePointHelloWorldCheckBoxXpath";
    public String SHARE_POINT_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK="SharePointHelloWorldEditApplicationLinkXpath";
    
  	public String SHARE_POINT_APP_INFO_PILOT_PROJECT_VALUE="SharePointPilotProjectValue";
	public String SHARE_POINT_SERVER_VALUE="SharePointServerValue";
	public String SHARE_POINT_SERVER_CHECKBOX="SharePointServerCheckBox";
	
	public String SHARE_POINT_RESOURCE_NAME_VALUE="SharePointResourceNameValue";
	public String SHARE_POINT_RESOURCE_CODE_VALUE="SharePointResourceCodeValue";
	public String SHARE_POINT_RESOURCE_DESCRIPTION_VALUE="SharePointResourceDescriptionValue";
	public String SHARE_POINT_RESOURCE_VERSION_VALUE="SharePointResourceVersionValue";
	
	public String SHARE_POINT_PROJECT_RESOURCE_CHECK_BOX="SharePointResourceCheckBoxXpath";
    public String SHARE_POINT_PROJECT_RESOURCE_EDIT_APPLICATION_LINK="SharePointResourceEditApplicationLinkXpath";
	
	public String SHARE_POINT_CONFIGURATION_SERVER_NAME_VALUE="SharePointConfigurationServerNameValue";
	public String SHARE_POINT_CONFIGURATION_SERVER_DESCRIPTION_VALUE="SharePointConfigurationServerDescriptionValue";
	public String SHARE_POINT_CONFIGURATION_SERVER_ENVIRONMENT_VALUE="SharePointConfigurationServerEnvironmentValue";
	public String SHARE_POINT_CONFIGURATION_SERVER_TYPE_VALUE="SharePointConfigurationServerTypeValue";
	public String SHARE_POINT_CONFIGURATION_SERVER_PROTOCOL_HTTP_VALUE="SharePointConfigurationServerProtocolHttpValue";

	public String SHARE_POINT_CONFIGURATION_SERVER_HOST_VALUE="SharePointConfigurationServerHostValue";
	public String SHARE_POINT_CONFIGURATION_SERVER_HW_PORT_VALUE="SharePointConfigurationServerHelloWorldPortValue";
	public String SHARE_POINT_CONFIGURATION_SERVER_RESOURCE_PORT_VALUE="SharePointConfigurationServerResourcePortValue";
	
	public String SHARE_POINT_CONFIGURATION_SERVER_DEPLOY_DIRECTORY_VALUE="SharePointConfigurationServerDeployDirectoryValue";
	public String SHARE_POINT_CONFIGURATION_SERVER_CONTEXT_VALUE="SharePointConfigurationServerContextValue";
	
     
	public SharePointConstantsXml()
	{
		try {
			readXml = new ReadXMLFile();
			readXml.loadSharePointConstants();
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