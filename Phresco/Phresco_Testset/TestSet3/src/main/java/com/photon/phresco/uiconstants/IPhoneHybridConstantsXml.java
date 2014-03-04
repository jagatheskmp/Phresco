package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class IPhoneHybridConstantsXml {
	
	private ReadXMLFile readXml; 
	
	public String IPHONE_HYBRID_ESHOP_PROJECT_NAME="iPhoneHybridEshopNameValue";
	public String IPHONE_HYBRID_ESHOP_PROJECT_CODE="iPhoneHybridEshopCodeValue";
	public String IPHONE_HYBRID_ESHOP_PROJECT_DESCRIPTION="iPhoneHybridEshopDescriptionValue";
	public String IPHONE_HYBRID_ESHOP_PROJECT_VERSION="iPhoneHybridEshopVersionValue";

	
	public String IPHONE_HYBRID_VALUE="iPhoneHybridValue";
	public String IPHONE_HYBRID_VERSION_VALUE="iPhoneHybridVersionValue";
	
	public String IPHONE_HYBRID_ESHOP_CHECKBOX="iPhoneHybridEshopCheckBoxXpath";
	public String IPHONE_HYBRID_ESHOP_EDIT_LINK="iPhoneHybridEshopEditApplicationLinkXpath";
	
	
	public String IPHONE_HYBRID_PILOT_PROJECT_ESHOP="pilotProjectEshopValue";

	
	public String IPHONE_HYBRID_CONFIG_SERVER_NAME ="iPhoneHybridConfigServNameValue";
  	public String IPHONE_HYBRID_CONFIG_SERVER_DESCRIPTION = "iPhoneHybridConfigservDescValue";
  	public String IPHONE_HYBRID_CONFIG_SERVER_ENVIRONMENT ="iPhoneHybridconfigServEnvValue";
  	public String IPHONE_HYBRID_CONFIG_SERVER_VALUE = "iPhoneHybridconfigServValue";
  	public String IPHONE_HYBRID_CONFIG_SERVER_PROTOCOL = "iPhoneHybridconfigServProtocolValue";
  	public String IPHONE_HYBRID_CONFIG_SERVER_HOST = "iPhoneHybridconfigServHostValue";
 	public String IPHONE_HYBRID_CONFIG_SERVER_PORT = "iPhoneHybridconfigServPortValue";
 
    public String IPHONE_HYBRID_CONFIG_SERVER_DEPLOY_DIR="iPhoneHybridconfigServDeployDirectoryValue";
    public String IPHONE_HYBRID_ESHOP_CONFIG_SERVER_CONTEXT="iPhoneHybridconfigServContextValueEshop";
    
     
    public String IPHONE_HYBRID_HELLOWORLD_PROJECT_NAME="iPhoneHybridHelloWorldNameValue";
	public String IPHONE_HYBRID_HELLOWORLD_PROJECT_CODE="iPhoneHybridHelloWorldCodeValue";
	public String IPHONE_HYBRID_HELLOWORLD_PROJECT_DESCRIPTION="iPhoneHybridHelloWorldDescriptionValue";
	public String IPHONE_HYBRID_HELLOWORLD_PROJECT_VERSION="iPhoneHybridHelloWorldVersionValue";

	
	public String IPHONE_HYBRID_HELLOWORLD_CHECKBOX="iPhoneHybridHelloWorldCheckBoxXpath";
	public String IPHONE_HYBRID_HELLOWORLD_EDIT_LINK="iPhoneHybridHelloWorldEditApplicationLinkXpath";
	
	

	public IPhoneHybridConstantsXml() {
		try {
			readXml=new ReadXMLFile();
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

