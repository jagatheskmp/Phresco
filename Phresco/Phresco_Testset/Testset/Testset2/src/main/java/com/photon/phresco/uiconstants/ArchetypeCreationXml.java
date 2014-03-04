package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class ArchetypeCreationXml {
	
	private ReadXMLFile readXml; 
	
	public String PROJECT_NAME = "projectName";
	public String PROJECT_DESC = "projectDesc";
	public String NODEJS_SERVICE_TECHNOLGY_VALUE = "technologynodeJsServiceValue";
	public String HTML_VALUE = "html5WebLayerValue";
	public String MULTICHANNEL_YUI_WIDGET_NAME_VALUE = "multiYuiWidgetValue";
	public String MULTICHANNEL_YUI_WIDGET_VERSION_VALUE = "multiYuiWidgetVersionValue";
	public String WIDGET_SERVER_VALUE = "widgetServerValue";
	public String WIDGET_SERVER_VERSION = "widgetServerVersionValue";
	public String ANDROID_NATIVE_VALUE = "androidNativeValue";
    public String NODEJS_SERVICE_PROJECT_HELLOWORLD_EDIT_APPLICATION_LINK = "nodeJsServiceHelloWorldEditApplicationLinkXpath";
	public String NODEJS_SERVICE_SERVER_VALUE = "nodeJsServiceServerValue";
	public String NODEJS_SERVICE_CONFIGURATION_SERVER_PORT_VALUE = "nodeJsServiceConfigurationServerPortValue";
	public String NODEJS_SERVICE_CONFIGURATION_SERVER_CONTEXT_VALUE_HELLOWORLD = "nodeJsServiceConfigurationServerContextValueHelloWorld";
	public String MULTICHANNEL_YUI_WIDGET_HELLOWORLD_PROJECT_SERVER_CONTEXT = "multiYuiWidgetconfigServContextValueHelloWorld";
    public String MULTICHANNEL_YUI_WIDGET_HELLOWORLD_PROJECT_LINK = "multiYuiWidgetHelloWorldEditApplicationLinkXpath";
  	public String CONFIG_SERVER_NAME_VALUE = "configServNameValue";
  	public String CONFIG_SERVER_DESC_VALUE = "configservDescValue";
  	public String CONFIG_SERVER_ENV_VALUE = "configServEnvValue";
  	public String CONFIG_SERVER_VALUE = "configServValue";
  	public String CONFIG_SERVER_PROTOCOL_VALUE = "configServProtocolValue";
  	public String CONFIG_SERVER_HOST_VALUE = "configServHostValue";
 	public String CONFIG_SERVER_PORT_VALUE = "configServPortValue";
    public String CONFIG_SERVER_DEPLOY_VALUE = "configServDeployDirectoryValue";
	public String ANDROID_NATIVE_HELLOWORLD_EDIT_LINK = "androidNativeHelloWorldEditApplicationLinkXpath";
	public String EMULATOR_VALUE = "emulatorValue";
	public String FUNCTIONAL_FRAMEWORK = "functionalFramework";
	public String FUNCTIONAL_FRAMEWORK_ROB = "functionalFrameworkRob";
	public String SDK_VALUE = "sdkValue";
	public ArchetypeCreationXml() {
		try {
			readXml = new ReadXMLFile();
			readXml.loadArchetyeCreation();
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

