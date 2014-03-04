package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class BlackBerryHybridConstantsXml {
	private ReadXMLFile readXml; 
	public String BLACKBERRY_HYBRID_PROJECT_NAME = "blackBerryHybridNameValue";
	public String BLACKBERRY_HYBRID_PROJECT_DESC = "blackBerryHybridDescriptionValue";
	public String BLACKBERRY_HYBRID_TECHNOLOGY_VALUE = "BlackBerryTechnologyHybridValue";
	public String BLACKBERRY_HYBRID_EDIT_LINK = "blackBerryHybridEditApplicationLinkXpath";
	public String BLACKBERRY_HYBRID_CONFIG_SERVER_NAME ="blackBerryConfigServNameValue";
	public String BLACKBERRY_HYBRID_CONFIG_SERVER_DESC = "blackBerryConfigservDescValue";
	public String BLACKBERRY_HYBRID_CONFIG_SERVER_ENV ="blackBerryconfigServEnvValue";
	public String BLACKBERRY_HYBRID_CONFIG_SERVER_VALUE = "blackBerryconfigServValue";
	public String BLACKBERRY_HYBRID_CONFIG_SERVER_PROTOCOL = "blackBerryconfigServProtocolValue";
	public String BLACKBERRY_HYBRID_CONFIG_SERVER_HOST = "blackBerryconfigServHostValue";
	public String BLACKBERRY_HYBRID_CONFIG_SERVER_PORT = "blackBerryconfigServPortValue";
	public String BLACKBERRY_HYBRID_CONFIG_SERVER_CONTEXT = "blackBerryconfigServContextValueEshop";
	public String BLCAKBERRY_TARGET_VALUE = "blackBerryBuildTargetValue";
	public String BLACKBERRY_HYBRID_SERVER_VALUE = "blackBerryServerValue";
	public String FUNCTIONAL_FRAMEWORK = "functionalFramework";

	public BlackBerryHybridConstantsXml() {
		try {
			readXml = new ReadXMLFile();
			readXml.loadBlackBerryHybridConstants();
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


