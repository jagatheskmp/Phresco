package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class AndroidHybridConstantsXml {

	private ReadXMLFile readXml;
	public String ANDROID_HYBRID_ESHOP_PROJECT_NAME="androidHybridEshopNameValue";
	public String ANDROID_HYBRID_ESHOP_PROJECT_CODE="androidHybridEshopCodeValue";
	public String ANDROID_HYBRID_ESHOP_PROJECT_DESCRIPTION="androidHybridEshopDescriptionValue";
	public String ANDROID_HYBRID_ESHOP_PROJECT_VERSION="androidHybridEshopVersionValue";
	
	public String ANDROID_HYBRID_VALUE="androidHybridValue";
	public String ANDROID_HYBRID_VERSION_VALUE="androidHybridVersionValue";
	
	public String ANDROID_HYBRID_ESHOP_CHECKBOX="androidHybridEshopCheckBoxXpath";
	public String ANDROID_HYBRID_ESHOP_EDIT_LINK="androidHybridEshopEditApplicationLinkXpath";
	

	public String ANDROID_HYBRID_PILOT_PROJECT_ESHOP="pilotProjectEshopValue";

	
	public String ANDROID_HYBRID_CONFIG_SERVER_NAME ="androidHybridConfigServNameValue";
  	public String ANDROID_HYBRID_CONFIG_SERVER_DESCRIPTION ="androidHybridConfigservDescValue";
  	public String ANDROID_HYBRID_CONFIG_SERVER_ENVIRONMENT ="androidHybridconfigServEnvValue";
  	public String ANDROID_HYBRID_CONFIG_SERVER_VALUE = "androidHybridconfigServValue";
  	public String ANDROID_HYBRID_CONFIG_SERVER_PROTOCOL = "androidHybridconfigServProtocolValue";
  	public String ANDROID_HYBRID_CONFIG_SERVER_HOST = "androidHybridconfigServHostValue";
 	public String ANDROID_HYBRID_CONFIG_SERVER_PORT = "androidHybridconfigServPortValue";
 	public String ANDROID_HYBRID_CONFIG_SERVER_TYPE = "androidHybridconfigServerTypeValue";
	public String ANDROID_HYBRID_CONFIG_SERVER_VERSION= "androidHybridconfigServVersionValue";
    public String ANDROID_HYBRID_CONFIG_SERVER_DEPLOY_DIR="androidHybridconfigServDeployDirectoryValue";
   
    public String ANDROID_HYBRID_ESHOP_CONFIG_SERVER_CONTEXT="androidHybridconfigServContextValueEshop";
   

    public String ANDROID_HYBRID_HELLOWORLD_PROJECT_NAME="androidHybridHelloWorldNameValue";
	public String ANDROID_HYBRID_HELLOWORLD_PROJECT_CODE="androidHybridHelloWorldCodeValue";
	public String ANDROID_HYBRID_HELLOWORLD_PROJECT_DESCRIPTION="androidHybridHelloWorldDescriptionValue";
	public String ANDROID_HYBRID_HELLOWORLD_PROJECT_VERSION="androidHybridHelloWorldVersionValue";

	
	public String ANDROID_HYBRID_HELLOWORLD_CHECKBOX="androidHybridHelloWorldCheckBoxXpath";
	public String ANDROID_HYBRID_HELLOWORLD_EDIT_LINK="androidHybridHelloWorldEditApplicationLinkXpath";
	

	
	public String ANDROID_HYBRID_ESHOP_SERVER_VERSION="androidHybridServerVersion";

	public String EMULATOR_VALUE="emulatorValue";
	
	public AndroidHybridConstantsXml() {
		try {
			readXml = new ReadXMLFile();
			readXml.loadAndroidHybridConstants();
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
