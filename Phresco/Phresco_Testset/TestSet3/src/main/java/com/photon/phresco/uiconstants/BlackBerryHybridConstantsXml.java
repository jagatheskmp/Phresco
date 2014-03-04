package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class BlackBerryHybridConstantsXml {


		private ReadXMLFile readXml; 

		public String BLACKBERRY_HYBRID_PROJECT_NAME="blackBerryHybridNameValue";
		public String BLACKBERRY_HYBRID_PROJECT_CODE="blackBerryHybridCodeValue";
		public String BLACKBERRY_HYBRID_PROJECT_DESCRIPTION="blackBerryHybridDescriptionValue";
		public String BLACKBERRY_HYBRID_PROJECT_VERSION="blackBerryHybridVersionValue";
		public String BLACKBERRY_HYBRID_TECHNOLOGY_VALUE="technologyBlackBerryHybridValue";
		public String BLACKBERRY_HYBRID_TECHNOLOGY_VERSION="technologyBlackBerryHybridVersionValue";
		public String BLACKBERRY_HYBRID_CHECKBOX="blackBerryHybridCheckBoxXpath";
		public String BLACKBERRY_HYBRID_EDIT_LINK="blackBerryHybridEditApplicationLinkXpath";
		
		
		public String BLACKBERRY_HYBRID_CONFIG_SERVER_NAME ="blackBerryConfigServNameValue";
	  	public String BLACKBERRY_HYBRID_CONFIG_SERVER_DESCRIPTION = "blackBerryConfigservDescValue";
	  	public String BLACKBERRY_HYBRID_CONFIG_SERVER_ENVIRONMENT ="blackBerryconfigServEnvValue";
	  	public String BLACKBERRY_HYBRID_CONFIG_SERVER_VALUE = "blackBerryconfigServValue";
	  	public String BLACKBERRY_HYBRID_CONFIG_SERVER_PROTOCOL = "blackBerryconfigServProtocolValue";
	  	public String BLACKBERRY_HYBRID_CONFIG_SERVER_HOST = "blackBerryconfigServHostValue";
	 	public String BLACKBERRY_HYBRID_CONFIG_SERVER_PORT = "blackBerryconfigServPortValue";
	 	
	    public String BLACKBERRY_HYBRID_CONFIG_SERVER_DEPLOY_DIR="blackBerryconfigServDeployDirectoryValue";
	    public String BLACKBERRY_HYBRID_CONFIG_SERVER_CONTEXT="blackBerryconfigServContextValueEshop";
	    public String BLCAKBERRY_TARGET_VALUE="blackBerryBuildTargetValue";
	    
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


