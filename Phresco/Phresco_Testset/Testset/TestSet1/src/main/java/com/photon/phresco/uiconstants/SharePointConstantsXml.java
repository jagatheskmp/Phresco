package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class SharePointConstantsXml {

	private ReadXMLFile readXml;

	public String SHARE_POINT_HELLOWORLD_NAME_VALUE = "SharePointHelloWorldNameValue";
	public String SHARE_POINT_HELLOWORLD_DESC_VALUE = "SharePointHelloWorldDescriptionValue";
	public String SHARE_POINT_TECHNOLGY_VALUE = "technologySharePointValue";
	public String SHARE_POINT_PILOT_PROJECT = "pilotProject";
	public String SHARE_POINT_RESOURCE_NAME_VALUE = "SharePointResourceNameValue";
	public String SHARE_POINT_RESOURCE_DESC_VALUE = "SharePointResourceDescriptionValue";
	public String SHARE_POINT_HELLOWORLD_EDIT_APP_LINK = "SharePointHelloWorldEditApplicationLinkXpath";
	public String SHARE_POINT_RESOURCE_EDIT_APP_LINK = "SharePointResourceEditApplicationLinkXpath";
	public String SHARE_POINT_CONF_SERVER_NAME_VALUE = "SharePointConfigurationServerNameValue";
	public String SHARE_POINT_CONF_SERVER_DESC_VALUE = "SharePointConfigurationServerDescriptionValue";
	public String SHARE_POINT_CONF_SERVER_ENV_VALUE = "SharePointConfigurationServerEnvironmentValue";
	public String SHARE_POINT_CONF_SERVER_TYPE_VALUE = "SharePointConfigurationServerTypeValue";
	public String SHARE_POINT_CONF_SERVER_PROTOCOL_HTTP_VALUE = "SharePointConfigurationServerProtocolHttpValue";
	public String SHARE_POINT_CONF_SERVER_HOST_VALUE = "SharePointConfigurationServerHostValue";
	public String SHARE_POINT_CONF_SERVER_HW_PORT_VALUE = "SharePointConfigurationServerHelloWorldPortValue";
	public String SHARE_POINT_CONF_SERVER_RESOURCE_PORT_VALUE = "SharePointConfigurationServerResourcePortValue";
	public String SHARE_POINT_CONF_SERVER_DEPLOY_DIRECTORY = "SharePointConfigurationServerDeployDirectoryValue";
	public String SHARE_POINT_CONF_SERVER_CONTEXT = "SharePointConfigurationServerContextValue";
	public String FUNCTIONAL_FRAMEWORK = "functionalFramework";

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