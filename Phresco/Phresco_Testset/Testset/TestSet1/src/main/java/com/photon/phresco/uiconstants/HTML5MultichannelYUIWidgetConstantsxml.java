package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class HTML5MultichannelYUIWidgetConstantsxml {
	private ReadXMLFile readXml;

	public String MULTICHANNEL_YUI_WIDGET_HELLOWORLD_PROJECT_NAME = "multiYuiWidgetHelloworldNameValue";
	public String MULTICHANNEL_YUI_WIDGET_HELLOWORLD_PROJECT_DESC = "multiYuiWidgetHelloworldDescriptionValue";
	public String MULTICHANNEL_YUI_WIDGET_ESHOP_PROJECT_NAME = "multiYuiWidgetEshopNameValue";
	public String MULTICHANNEL_YUI_WIDGET_ESHOP_PROJECT_DESC = "multiYuiWidgetEshopDescriptionValue";
	public String MULTICHANNEL_YUI_WIDGET_HELLOWORLD_LINK = "multiYuiWidgetHelloWorldEditApplicationLinkXpath";
	public String MULTICHANNEL_YUI_WIDGET_ESHOP_LINK = "multiYuiMobWidgetEshopEditApplicationLinkXpath";
	public String WIDGET_SERVER_VALUE = "widgetServerValue";
	public String WIDGET_SERVER_VERSION = "widgetServerVersionValue";
	public String HTML_VALUE = "html5WebLayerValue";
	public String MULTICHANNEL_YUI_WIDGET_NAME = "multiYuiWidgetValue";
	public String MULTICHANNEL_YUI_WIDGET_VERSION = "multiYuiWidgetVersionValue";
	public String PILOT_PROJECT_ESHOP_VALUE = "pilotProjectEshopValue";
	public String CONFIG_SERVER_NAME_VALUE = "configServNameValue";
	public String CONFIG_SERVER_DESC_VALUE = "configservDescValue";
	public String CONFIG_SERVER_ENV_VALUE = "configServEnvValue";
	public String CONFIG_SERVER_VALUE = "configServValue";
	public String CONFIG_SERVER_PROTOCOL_VALUE = "configServProtocolValue";
	public String CONFIG_SERVER_HOST_VALUE = "configServHostValue";
	public String CONFIG_SERVER_PORT_VALUE = "configServPortValue";
	public String CONFIG_SERVER_DEPLOY_VALUE = "configServDeployDirectoryValue";
	public String MULTICHANNEL_YUI_WIDGET_HELLOWORLD_SERVER_CONTEXT = "multiYuiWidgetconfigServContextValueHelloWorld";
	public String MULTICHANNEL_YUI_WIDGET_ESHOP_SERVER_CONTEXT = "multiYuiServContextValueEshop";
	public String CONFIG_WEBSERVICE_NAME_VALUE = "configWebServiceNameValue";
	public String CONFIG_WEBSERVICE_DESC_VALUE = "configWebserviceDesc";
	public String CONFIG_WEBSERVICE_ENV_VALUE = "configWebserviceEnvValue";
	public String CONFIG_WEBSERVICE_VALUE = "configWebserviceValue";
	public String CONFIG_WEBSERVICE_PROTOCAOL_VALUE = "configWebserviceProtocolValue";
	public String CONFIG_WEBSERVICE_HOST_VALUE = "configWebserviceHostValue";
	public String CONFIG_WEBSERVICE_PORT_VALUE = "configWebservicePortValue";
	public String MULTICHANNEL_YUI_WIDGET_ESHOP_WEBSERVICE_CONTEXT = "multiYuiWebserviceContextEshopValue";
	public String FUNCTIONAL_FRAMEWORK = "functionalFramework";

	public HTML5MultichannelYUIWidgetConstantsxml() {
		try {
			readXml = new ReadXMLFile();
			readXml.loadMultiChannelYUIWidgetConstants();
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

