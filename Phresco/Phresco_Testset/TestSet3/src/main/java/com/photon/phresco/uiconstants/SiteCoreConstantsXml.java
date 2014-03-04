package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class SiteCoreConstantsXml {
	private ReadXMLFile readXml;
	
	public String SITECORE_HELLOWORLD_NAME_VALUE="siteCoreHelloWorldNameValue";
	public String SITECORE_HELLOWORLD_CODE_VALUE="siteCoreHelloWorldCodeValue";
	public String SITECORE_HELLOWORLD_DESCRIPTION_VALUE="siteCoreHelloWorldDescriptionValue";
	public String SITECORE_HELLOWORLD_VERSION_VALUE="siteCoreHelloWorldVersionValue";
	public String SITECORE_TECHNOLGY_VALUE="technologySiteCoreValue";
	public String SITECORE_SERVER ="siteCoreSever";
	
	
	public String SITECORE_PROJECT_HELLOWORLD_CHECK_BOX="siteCoreHelloWorldCheckBoxXpath";
    public String SITECORE_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK="siteCoreHelloWorldEditApplicationLinkXpath";
    

	//Server Configuration
	public String SITECORE_SERVER_NAME="siteCoreServerName";
	public String SITECORE_SERVER_DISCRIPTION="siteCoreServerDiscription";
	public String SITECORE_SERVER_ENVIRONMENT="siteCoreServerEnvironment";
	public String SITECORE_SERVER_TYPE="siteCoreServerType";
	public String SITECORE_SERVER_PROTOCOL="siteCoreServerProtocol";
	public String SITECORE_SERVER_HOST="siteCoreServerHost";
	public String SITECORE_SERVER_PORT="siteCoreServerPort";
	
	public String SITECORE_SERVER_DEPLOY_DIR="siteCoreServerDeployDir";
	public String SITECORE_SERVER_SITENAME="siteCoreServerSiteName";
	public String SITECORE_SERVER_APPNAME="siteCoreServerAppName";


	
	
	public SiteCoreConstantsXml() {
		try {
			readXml = new ReadXMLFile();
			readXml.loadSiteCoreConstants();
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
