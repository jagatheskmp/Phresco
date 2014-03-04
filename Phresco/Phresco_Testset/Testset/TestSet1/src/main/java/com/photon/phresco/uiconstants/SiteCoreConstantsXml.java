package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class SiteCoreConstantsXml {
	private ReadXMLFile readXml;

	public String SITECORE_HELLOWORLD_NAME_VALUE = "siteCoreHelloWorldNameValue";
	public String SITECORE_HELLOWORLD_DESC_VALUE = "siteCoreHelloWorldDescriptionValue";
	public String SITECORE_TECHNOLGY_VALUE = "technologySiteCoreValue";
	public String SITECORE_HELLOWORLD_EDIT_APP_LINK = "siteCoreHelloWorldEditApplicationLinkXpath";
	public String SITECORE_SERVER_NAME = "siteCoreServerName";
	public String SITECORE_SERVER_DESC = "siteCoreServerDiscription";
	public String SITECORE_SERVER_ENV = "siteCoreServerEnvironment";
	public String SITECORE_SERVER_TYPE = "siteCoreServerType";
	public String SITECORE_SERVER_PROTOCOL = "siteCoreServerProtocol";
	public String SITECORE_SERVER_HOST = "siteCoreServerHost";
	public String SITECORE_SERVER_PORT = "siteCoreServerPort";
	public String SITECORE_SERVER_DEPLOY_DIR = "siteCoreServerDeployDir";
	public String SITECORE_SERVER_CONTEXT = "siteCoreContextName";
	public String FUNCTIONAL_FRAMEWORK = "functionalFramework";

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
