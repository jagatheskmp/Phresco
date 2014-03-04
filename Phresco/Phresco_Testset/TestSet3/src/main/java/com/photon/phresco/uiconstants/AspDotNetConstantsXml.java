package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class AspDotNetConstantsXml {

	private ReadXMLFile readXml;



	public String ASP_DOTNET_HELLOWORLD_NAME_VALUE="AspDotNetHelloWorldNameValue";
	public String ASP_DOTNET_HELLOWORLD_CODE_VALUE="AspDotNetHelloWorldCodeValue";
	public String ASP_DOTNET_HELLOWORLD_DESCRIPTION_VALUE="AspDotNetHelloWorldDescriptionValue";
	public String ASP_DOTNET_HELLOWORLD_VERSION_VALUE="AspDotNetHelloWorldVersionValue";
	public String ASP_DOTNET_TECHNOLGY_VALUE="technologyAspDotNetValue";
	
	
	public String ASPDOTNET_PROJECT_HELLOWORLD_CHECK_BOX="AspDotNetHelloWorldCheckBoxXpath";
    public String ASPDOTNET_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK="AspDotNetHelloWorldEditApplicationLinkXpath";
    
    
	public String ASPDOTNET_SERVER_VALUE="AspDotNetServerValue";
	public String ASPDOTNET_SERVER_CHECKBOX="AspDotNetServerCheckBox";
	
	//Server Configuration
	public String ASPDOTNET_SERVER_NAME="AspDotNetServerName";
	public String ASPDOTNET_SERVER_DISCRIPTION="AspDotNetServerDiscription";
	public String ASPDOTNET_SERVER_ENVIRONMENT="AspDotNetServerEnvironment";
	public String ASPDOTNET_SERVER_TYPE="AspDotNetServerType";
	public String ASPDOTNET_SERVER_PROTOCOL="AspDotNetServerProtocol";
	public String ASPDOTNET_SERVER_HOST="AspDotNetServerHost";
	public String ASPDOTNET_SERVER_PORT="AspDotNetServerPort";
	
	public String ASPDOTNET_SERVER_DEPLOY_DIR="AspDotNetServerDeployDir";
	public String ASPDOTNET_SERVER_SITENAME="AspDotNetServerSiteName";
	public String ASPDOTNET_SERVER_APPNAME="AspDotNetServerAppName";
	
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