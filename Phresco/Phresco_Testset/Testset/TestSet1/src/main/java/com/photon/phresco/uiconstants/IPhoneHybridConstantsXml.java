package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class IPhoneHybridConstantsXml {

	private ReadXMLFile readXml; 

	public String IPHONE_HYBRID_HELLOWORLD_PROJECT_NAME = "iPhoneHybridHelloWorldNameValue";
	public String IPHONE_HYBRID_HELLOWORLD_PROJECT_DESC = "iPhoneHybridHelloWorldDescriptionValue";
	public String IPHONE_HYBRID_ESHOP_PROJECT_NAME = "iPhoneHybridEshopNameValue";
	public String IPHONE_HYBRID_ESHOP_PROJECT_DESC = "iPhoneHybridEshopDescriptionValue";
	public String IPHONE_HYBRID_HELLOWORLD_EDIT_LINK = "iPhoneHybridHelloWorldEditApplicationLinkXpath";
	public String IPHONE_HYBRID_ESHOP_EDIT_LINK = "iPhoneHybridEshopEditApplicationLinkXpath";
	public String IPHONE_HYBRID_VALUE = "iPhoneHybridValue";
	public String IPHONE_HYBRID_PILOT_PROJECT_ESHOP = "pilotProjectEshopValue";
	public String IPHONE_HYBRID_CONFIG_SERVER_NAME = "iPhoneHybridConfigServNameValue";
	public String IPHONE_HYBRID_CONFIG_SERVER_DESC = "iPhoneHybridConfigservDescValue";
	public String IPHONE_HYBRID_CONFIG_SERVER_ENV = "iPhoneHybridconfigServEnvValue";
	public String IPHONE_HYBRID_CONFIG_SERVER_VALUE = "iPhoneHybridconfigServValue";
	public String IPHONE_HYBRID_CONFIG_SERVER_PROTOCOL = "iPhoneHybridconfigServProtocolValue";
	public String IPHONE_HYBRID_CONFIG_SERVER_HOST = "iPhoneHybridconfigServHostValue";
	public String IPHONE_HYBRID_CONFIG_SERVER_PORT = "iPhoneHybridconfigServPortValue";
	public String IPHONE_HYBRID_CONFIG_SERVER_DEPLOY_DIR = "iPhoneHybridconfigServDeployDirectoryValue";
	public String IPHONE_HYBRID_ESHOP_CONFIG_SERVER_CONTEXT = "iPhoneHybridconfigServContextValueEshop";
	public String IPHONE_HYBRID_BUILD_SDK_VALUE = "sdkValue";
	public String IPHONE_HYBRID_BUILD_TARGET_VALUE = "targetValue";
	public String IPHONE_HYBRID_DEPLOY_SDK_VERSION = "sdkVersion";
	public String IPHONE_HYBRID_DEPLOY_SDK_FAMILY = "sdkFamily";
	public String IPHONE_HYBRID_UNIT_TEST_SDK_VALUE = "unitTestSdkValue";
	public String IPHONE_HYBRID_UNIT_TEST_TARGET_VALUE = "unitTestTargetValue";
	public String FUNCTIONAL_FRAMEWORK = "functionalFramework";

	public IPhoneHybridConstantsXml() {
		try {
			readXml = new ReadXMLFile();
			readXml.loadiPhoneHybridConstants();
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

