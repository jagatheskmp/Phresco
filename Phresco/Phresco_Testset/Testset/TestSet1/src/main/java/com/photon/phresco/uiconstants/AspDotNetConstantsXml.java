package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class AspDotNetConstantsXml {

	private ReadXMLFile readXml;

	public String ASP_DOTNET_HELLOWORLD_NAME_VALUE = "AspDotNetHelloWorldNameValue";
	public String ASP_DOTNET_HELLOWORLD_DESC_VALUE = "AspDotNetHelloWorldDescriptionValue";
	public String ASP_DOTNET_TECHNOLGY_VALUE = "technologyAspDotNetValue";
	public String ASP_DOTNET_HELLOWORLD_EDIT_APP_LINK = "AspDotNetHelloWorldEditApplicationLinkXpath";
	public String ASP_DOTNET_SERVER_NAME = "AspDotNetServerName";
	public String ASP_DOTNET_SERVER_DESC = "AspDotNetServerDescription";
	public String ASP_DOTNET_SERVER_ENV = "AspDotNetServerEnvironment";
	public String ASP_DOTNET_SERVER_TYPE = "AspDotNetServerType";
	public String ASP_DOTNET_SERVER_PROTOCOL = "AspDotNetServerProtocol";
	public String ASP_DOTNET_SERVER_HOST = "AspDotNetServerHost";
	public String ASP_DOTNET_SERVER_PORT = "AspDotNetServerPort";
	public String ASP_DOTNET_SERVER_DEPLOY_DIR = "AspDotNetServerDeployDir";
	public String ASP_DOTNET_SERVER_CONTEXT = "AspDotNetContextName";
	public String FUNCTIONAL_FRAMEWORK = "functionalFramework";

	public AspDotNetConstantsXml()
	{
		try {
			readXml = new ReadXMLFile();
			readXml.loadAspDotNetConstants();
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