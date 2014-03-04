package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class HTML5YUIMobileWidgetXml {
	private ReadXMLFile readXml;

	public String YUI_MOBILE_WIDGET_HELLOWORLD_PROJECT_NAME = "yuiMobileWidgetHelloworldNameValue";
	public String YUI_MOBILE_WIDGET_HELLOWORLD_PROJECT_DESC = "yuiMobileWidgetHelloworldDescriptionValue";
	public String YUI_MOBILE_WIDGET_ESHOP_PROJECT_NAME = "yuiMobileWidgetEshopNameValue";
	public String YUI_MOBILE_WIDGET_ESHOP_PROJECT_DESC = "yuiMobileWidgetEshopDescriptionValue";
	public String YUI_MOBILE_WIDGET_HELLOWORLD_LINK = "yuiMobileWidgetHelloWorldEditApplicationLinkXpath";
	public String YUI_MOBILE_WIDGET_ESHOP_LINK = "yuiMobileMobWidgetEshopEditApplicationLinkXpath";
	public String HTML_VALUE = "html5WebLayerValue";
	public String YUI_MOBILE_WIDGET_NAME = "yuiMobileWidgetValue";
	public String YUI_MOBILE_WIDGET_VERSION = "yuiMobileWidgetVersionValue";
	public String WIDGET_SERVER_VALUE = "widgetServerValue";
	public String WIDGET_SERVER_VERSION = "widgetServerVersionValue";
	public String PILOT_PROJECT_ESHOP_VALUE = "pilotProjectEshopValue";
	public String CONFIG_SERVER_NAME_VALUE = "configServNameValue";
	public String CONFIG_SERVER_DESC_VALUE = "configservDescValue";
	public String CONFIG_SERVER_ENV_VALUE = "configServEnvValue";
	public String CONFIG_SERVER_VALUE = "configServValue";
	public String CONFIG_SERVER_PROTOCOL_VALUE = "configServProtocolValue";
	public String CONFIG_SERVER_HOST_VALUE = "configServHostValue";
	public String CONFIG_SERVER_PORT_VALUE = "configServPortValue";
	public String CONFIG_SERVER_DEPLOY_VALUE = "configServDeployDirectoryValue";
	public String YUI_MOBILE_WIDGET_HELLOWORLD_SERVER_CONTEXT = "yuiMobileconfigServContextHelloWorld";
	public String YUI_MOBILE_WIDGET_ESHOP_SERVER_CONTEXT = "yuiMobileServContextValueEshop";
	public String CONFIG_WEBSERVICE_NAME_VALUE = "configWebServiceNameValue";
	public String CONFIG_WEBSERVICE_DESC_VALUE = "configWebserviceDesc";
	public String CONFIG_WEBSERVICE_ENV_VALUE = "configWebserviceEnvValue";
	public String CONFIG_WEBSERVICE_VALUE = "configWebserviceValue";
	public String CONFIG_WEBSERVICE_PROTOCAOL_VALUE = "configWebserviceProtocolValue";
	public String CONFIG_WEBSERVICE_HOST_VALUE = "configWebserviceHostValue";
	public String CONFIG_WEBSERVICE_PORT_VALUE = "configWebservicePortValue";
	public String YUI_MOBILE_WIDGET_ESHOP_WEBSERVICE_CONTEXT = "MobileWebserviceContextEshopValue";
	public String FUNCTIONAL_FRAMEWORK = "functionalFramework";

	public HTML5YUIMobileWidgetXml(){
		try {
			readXml = new ReadXMLFile();
			readXml.loadYUIMobileWidgetConstants();
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


