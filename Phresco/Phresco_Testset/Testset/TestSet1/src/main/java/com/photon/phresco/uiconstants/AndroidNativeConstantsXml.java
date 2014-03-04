package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class AndroidNativeConstantsXml {
	private ReadXMLFile readXml; 

	public String ANDROID_NATIVE_HELLOWORLD_PROJECT_NAME = "androidNativeHelloWorldNameValue";
	public String ANDROID_NATIVE_HELLOWORLD_PROJECT_DESC = "androidNativeHelloWorldDescriptionValue";
	public String ANDROID_NATIVE_ESHOP_PROJECT_NAME = "androidNativeEshopNameValue";
	public String ANDROID_NATIVE_ESHOP_PROJECT_DESC = "androidNativeEshopDescriptionValue";
	public String ANDROID_NATIVE_HELLOWORLD_EDIT_LINK = "androidNativeHelloWorldEditApplicationLinkXpath";
	public String ANDROID_NATIVE_ESHOP_EDIT_LINK = "androidNativeEshopEditApplicationLinkXpath";
	public String ANDROID_NATIVE_VALUE = "androidNativeValue";
	public String ANDROID_NATIVE_PILOT_PROJECT_ESHOP = "pilotProjectEshopValue";
	public String ANDROID_NATIVE_CONFIG_WEBSERVICE_NAME = "androidNativeConfigWebServiceNameValue";
	public String ANDROID_NATIVE_CONFIG_WEBSERVICE_DESC = "androidNativeConfigWebserviceDesc";
	public String ANDROID_NATIVE_CONFIG_WEBSERVICE_ENV = "androidNativeconfigWebserviceEnvValue";
	public String ANDROID_NATIVE_CONFIG_WEBSERVICE_VALUE = "androidNativeconfigWebserviceValue";
	public String ANDROID_NATIVE_CONFIG_WEBSERVICE_PROTOCOL = "androidNativeconfigWebserviceProtocolValue";
	public String ANDROID_NATIVE_CONFIG_WEBSERVICE_HOST = "androidNativeconfigWebserviceHostValue";
	public String ANDROID_NATIVE_CONFIG_WEBSERVICE_PORT = "androidNativeconfigWebservicePortValue";
	public String ANDROID_NATIVE_ESHOP_CONFIG_WEBSERVICE_CONTEXT = "androidNativeEshopconfigWebserviceContextEshopValue";
	public String EMULATOR_VALUE = "emulatorValue";
	public String SDK_VALUE = "sdkValue";
	public String FUNCTIONAL_FRAMEWORK = "functionalFramework";

	public AndroidNativeConstantsXml() {
		try {
			readXml = new ReadXMLFile();
			readXml.loadAndroidNativeConstants();
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
