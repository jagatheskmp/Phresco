package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class AndroidHybridConstantsXml {

	private ReadXMLFile readXml;

	public String ANDROID_HYBRID_HELLOWORLD_PROJECT_NAME = "androidHybridHelloWorldNameValue";
	public String ANDROID_HYBRID_HELLOWORLD_PROJECT_DESC = "androidHybridHelloWorldDescriptionValue";
	public String ANDROID_HYBRID_ESHOP_PROJECT_NAME = "androidHybridEshopNameValue";
	public String ANDROID_HYBRID_ESHOP_PROJECT_DESC = "androidHybridEshopDescriptionValue";
	public String ANDROID_HYBRID_HELLOWORLD_EDIT_LINK = "androidHybridHelloWorldEditApplicationLinkXpath";
	public String ANDROID_HYBRID_ESHOP_EDIT_LINK = "androidHybridEshopEditApplicationLinkXpath";
	public String ANDROID_HYBRID_VALUE = "androidHybridValue";
	public String ANDROID_HYBRID_PILOT_PROJECT_ESHOP = "pilotProjectEshopValue";
	public String ANDROID_HYBRID_CONFIG_SERVER_NAME  = "androidHybridConfigServNameValue";
	public String ANDROID_HYBRID_CONFIG_SERVER_DESC  = "androidHybridConfigservDescValue";
	public String ANDROID_HYBRID_CONFIG_SERVER_ENV  = "androidHybridconfigServEnvValue";
	public String ANDROID_HYBRID_CONFIG_SERVER_VALUE = "androidHybridconfigServValue";
	public String ANDROID_HYBRID_CONFIG_SERVER_PROTOCOL = "androidHybridconfigServProtocolValue";
	public String ANDROID_HYBRID_CONFIG_SERVER_HOST = "androidHybridconfigServHostValue";
	public String ANDROID_HYBRID_CONFIG_SERVER_PORT = "androidHybridconfigServPortValue";
	public String ANDROID_HYBRID_CONFIG_SERVER_DEPLOY_DIR = "androidHybridconfigServDeployDirectoryValue";
	public String ANDROID_HYBRID_ESHOP_CONFIG_SERVER_CONTEXT = "androidHybridconfigServContextValueEshop";
	public String EMULATOR_VALUE = "emulatorValue";
	public String SDK_VALUE = "sdkValue";
	public String FUNCTIONAL_FRAMEWORK = "functionalFramework";

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
