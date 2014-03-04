package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class IPhoneNativeConstantsXml {
	private ReadXMLFile readXml; 
	
	public String IPHONE_NATIVE_ESHOP_PROJECT_NAME="iPhoneNativeEshopNameValue";
	public String IPHONE_NATIVE_ESHOP_PROJECT_CODE="iPhoneNativeEshopCodeValue";
	public String IPHONE_NATIVE_ESHOP_PROJECT_DESCRIPTION="iPhoneNativeEshopDescriptionValue";
	public String IPHONE_NATIVE_ESHOP_PROJECT_VERSION="iPhoneNativeEshopVersionValue";

	public String IPHONE_NATIVE_VALUE="iPhoneNativeValue";
	public String IPHONE_NATIVE_VERSION_VALUE="iPhoneNativeVersionValue";
	
	public String IPHONE_NATIVE_ESHOP_CHECKBOX="iPhoneNativeEshopCheckBoxXpath";
	public String IPHONE_NATIVE_ESHOP_EDIT_LINK="iPhoneNativeEshopEditApplicationLinkXpath";
	

	public String IPHONE_NATIVE_PILOT_PROJECT_ESHOP="pilotProjectEshopValue";
	
    public String IPHONE_NATIVE_CONFIG_WEBSERVICE_NAME="iPhoneNativeConfigWebServiceNameValue";
 	public String IPHONE_NATIVE_CONFIG_WEBSERVICE_DESCRIPTION = "iPhoneNativeConfigWebserviceDesc";
  	public String IPHONE_NATIVE_CONFIG_WEBSERVICE_ENV = "iPhoneNativeconfigWebserviceEnvValue";
  	public String IPHONE_NATIVE_CONFIG_WEBSERVICE_VALUE = "iPhoneNativeconfigWebserviceValue";
  	public String IPHONE_NATIVE_CONFIG_WEBSERVICE_PROTOCOL = "iPhoneNativeconfigWebserviceProtocolValue";
  	public String IPHONE_NATIVE_CONFIG_WEBSERVICE_HOST = "iPhoneNativeconfigWebserviceHostValue";
  	public String IPHONE_NATIVE_CONFIG_WEBSERVICE_PORT = "iPhoneNativeconfigWebservicePortValue";
  	
 	public String IPHONE_NATIVE_ESHOP_CONFIG_WEBSERVICE_CONTEXT = "iPhoneNativeEshopconfigWebserviceContextEshopValue";
 	
 	

    public String IPHONE_NATIVE_HELLOWORLD_PROJECT_NAME="iPhoneNativeHelloWorldNameValue";
	public String IPHONE_NATIVE_HELLOWORLD_PROJECT_CODE="iPhoneNativeHelloWorldCodeValue";
	public String IPHONE_NATIVE_HELLOWORLD_PROJECT_DESCRIPTION="iPhoneNativeHelloWorldDescriptionValue";
	public String IPHONE_NATIVE_HELLOWORLD_PROJECT_VERSION="iPhoneNativeHelloWorldVersionValue";

	
	public String IPHONE_NATIVE_HELLOWORLD_CHECKBOX="iPhoneNativeHelloWorldCheckBoxXpath";
	public String IPHONE_NATIVE_HELLOWORLD_EDIT_LINK="iPhoneNativeHelloWorldEditApplicationLinkXpath";
	

	public IPhoneNativeConstantsXml() {
		try {
			readXml = new ReadXMLFile();
			readXml.loadiPhoneNativeConstants();
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
